package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.79
 * @version 0.4.11.1
 * @author Innoxia
 */
public class GenericActions {
	
	private static String quickSexDescription = "";

	private static SexType getPlayerOngoingMainSex(GameCharacter partner) {
		for(Entry<SexAreaInterface, Map<GameCharacter, Set<SexAreaInterface>>> entry1 : Main.sex.getOngoingActionsMap(Main.game.getPlayer()).entrySet()) {
			// If penetrating an internal orifice, prefer that:
			if(entry1.getValue().containsKey(partner)
					&& entry1.getKey().isPenetration()
					&& ((SexAreaPenetration)entry1.getKey()).isTakesVirginity()
					&& entry1.getValue().get(partner).stream().anyMatch(orifice -> orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice())) {
				return new SexType(SexParticipantType.NORMAL, entry1.getKey(), entry1.getValue().get(partner).stream().filter(orifice -> orifice.isOrifice() && ((SexAreaOrifice)orifice).isInternalOrifice()).findFirst().get());

			// If being penetrated, prefer that:
			} else if(entry1.getValue().containsKey(partner)
					&& entry1.getKey().isOrifice()
					&& ((SexAreaOrifice)entry1.getKey()).isInternalOrifice()
					&& entry1.getValue().get(partner).stream().anyMatch(penetration -> penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity())) {
				return new SexType(SexParticipantType.NORMAL, entry1.getKey(), entry1.getValue().get(partner).stream().filter(penetration -> penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity()).findFirst().get());
			}
		}
		return null;
	}
	
	private static SexType getForeplayPreference(GameCharacter dom, GameCharacter sub) {
		if(dom.isPlayer()) {
			SexType playerMainSexType = getPlayerOngoingMainSex(sub);
			if(playerMainSexType!=null) {
				return playerMainSexType;	
			}
		}
		
		SexType preference = Main.sex.getForeplayPreference(dom, sub);
		List<SexAreaInterface> domBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(dom);
		if(domBanned==null) {
			domBanned = new ArrayList<>();
		}
		List<SexAreaInterface> subBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(sub);
		if(subBanned==null) {
			subBanned = new ArrayList<>();
		}
		
		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom)!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		if(preference!=null) {
			if(domBanned.contains(preference.getPerformingSexArea())
					|| subBanned.contains(preference.getTargetedSexArea())
					|| !dom.isAbleToAccessCoverableArea(preference.getPerformingSexArea().getRelatedCoverableArea(dom), true)
					|| !sub.isAbleToAccessCoverableArea(preference.getTargetedSexArea().getRelatedCoverableArea(sub), true)) {
				preference = null;
			}
		}
		if(preference==null && dom.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !domBanned.contains(SexAreaOrifice.MOUTH)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
				
			} else if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
			}
		}
		
		if(sexTypesBanned.contains(preference)) {
			preference = null;
		}
		
		if(preference==null) {
			preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH); // At least give them something...
		}
		return preference;
	}
	
	private static SexType getMainSexPreference(GameCharacter dom, GameCharacter sub) {
		if(dom.isPlayer()) {
			SexType playerMainSexType = getPlayerOngoingMainSex(sub);
			if(playerMainSexType!=null) {
				return playerMainSexType;	
			}
		}
		
		SexType preference = Main.sex.getMainSexPreference(dom, sub);
		List<SexAreaInterface> domBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(dom);
		if(domBanned==null) {
			domBanned = new ArrayList<>();
		}
		List<SexAreaInterface> subBanned = Main.sex.getInitialSexManager().getAreasBannedMap().get(sub);
		if(subBanned==null) {
			subBanned = new ArrayList<>();
		}

		List<SexType> sexTypesBanned = new ArrayList<>();
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom)!=null) {
			sexTypesBanned.addAll(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(dom));
		}
		if(Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)!=null) {
			for(SexType st : Main.sex.getInitialSexManager().getSexTypesBannedMap().get(sub)) {
				sexTypesBanned.add(st.getReversedSexType());
			}
		}
		if(preference!=null) {
			if(domBanned.contains(preference.getPerformingSexArea())
					|| subBanned.contains(preference.getTargetedSexArea())
					|| !dom.isAbleToAccessCoverableArea(preference.getPerformingSexArea().getRelatedCoverableArea(dom), true)
					|| !sub.isAbleToAccessCoverableArea(preference.getTargetedSexArea().getRelatedCoverableArea(sub), true)) {
				preference = null;
			}
		}
		if(preference==null && dom.hasPenis() && !domBanned.contains(SexAreaPenetration.PENIS) && dom.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
			if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
				
			} else if(Main.game.isAnalContentEnabled() && !subBanned.contains(SexAreaOrifice.ANUS) && sub.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			}
		}
		if(preference==null && dom.hasVagina() && !domBanned.contains(SexAreaOrifice.VAGINA) && dom.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
				
			} else if(sub.hasTail() && !subBanned.contains(SexAreaPenetration.TAIL) && sub.isTailSuitableForPenetration()){
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TAIL);
				
			} else {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
			}
		}
		if(preference==null && dom.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !domBanned.contains(SexAreaOrifice.MOUTH)) {
			if(sub.hasPenis() && !subBanned.contains(SexAreaPenetration.PENIS) && sub.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
				
			} else if(sub.hasVagina() && !subBanned.contains(SexAreaOrifice.VAGINA) && sub.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
				
			} else if(sub.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && !subBanned.contains(SexAreaOrifice.MOUTH)) {
				preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
			}
		}
		
		if(sexTypesBanned.contains(preference)) {
			preference = null;
		}
		
		if(preference==null) {
			preference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH); // At least give them something...
		}
		
		return preference;
	}
	
	private static boolean preventCreampie(SexType sexType, GameCharacter dom, GameCharacter sub) {
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))
				&& ((Main.sex.isConsensual() && sub.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(dom)!=OrgasmBehaviour.CREAMPIE)
						|| Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(dom)==OrgasmBehaviour.PULL_OUT)) {
			return true;
		}
		if(sexType.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))
				&& ((dom.getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative() && Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(sub)!=OrgasmBehaviour.CREAMPIE)
						|| Main.sex.getInitialSexManager().getCharacterOrgasmBehaviour(sub)==OrgasmBehaviour.PULL_OUT)) {
			return true;
		}
		return false;
	}
	
	private static String generateQuickSexDescription() {

		StringBuilder sb = new StringBuilder();
		
		HashMap<GameCharacter, GameCharacter> targetedCharacters = new HashMap<>();
		List<GameCharacter> availableSubs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		boolean allDomsAssigned = false;
		boolean allSubsAssigned = false;
		List<GameCharacter> domsNotSatisfied = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		
		// All characters in sex should know of each others' parts:
		for(GameCharacter character : Main.sex.getAllParticipants()) {
			for(GameCharacter partner : Main.sex.getAllParticipants()) {
				if(!character.equals(partner)) {
					partner.setAllAreasKnownByCharacter(character, true);
					character.setAllAreasKnownByCharacter(partner, true);
				}
			}
		}
		
		while(!allSubsAssigned) {
			for(GameCharacter dom : domsNotSatisfied) {
				GameCharacter target = dom.isPlayer()?Main.sex.getTargetedPartner(dom):Main.sex.getInitialSexManager().getPreferredSexTarget((NPC) dom);
				if(target==null || (dom.isPlayer() && allDomsAssigned && Main.sex.isConsensual())) { // If second time through loop, and equal control, give player another target if available
					if(availableSubs.isEmpty()) { // If run out of subs, re-populate sub list.
						availableSubs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
					}
					float topWeight = -10_000;
					for(GameCharacter sub : availableSubs) {
						float weight = dom.isAttractedTo(sub)?dom.getAffection(sub):-1_000;
						if(weight>topWeight) {
							topWeight = weight;
							target = sub;
						}
					}
				}
				availableSubs.remove(target);
				if(availableSubs.isEmpty()) {
					allSubsAssigned = true;
				}
				targetedCharacters.put(dom, target);
				if(allDomsAssigned && allSubsAssigned) { // If this is the second+ time going through the loop, break as soon as all subs are accounted for
					break;
				}
			}
			allDomsAssigned = true;
			
			// Apply all generic sex effects:
			for(Entry<GameCharacter, GameCharacter> entry : targetedCharacters.entrySet()) {
				GameCharacter dom = entry.getKey();
				GameCharacter sub = entry.getValue();
				sb.append(UtilText.parse(dom, sub,
						"<p style='text-align:center;'>"
//							+ "<b>[style.boldSexDom([npc.Name])] dominantly "+(Main.sex.isConsensual()?"having sex with":"fucking")+" [style.boldSexSub([npc2.name])]</b>"
							+ "<b><b style='color:"+dom.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"
								+ "以支配的姿态"+(Main.sex.isConsensual()?"与":"强奸了")
								+"<b style='color:"+sub.getFemininity().getColour().toWebHexString()+";'>[npc2.name]</b>"+(Main.sex.isConsensual()?"做爱":"")+"</b>"
						+ "</p>"));
				
				boolean preventCreampie = false;
				
				// Foreplay:
				SexType preference;
				if(Main.sex.isInForeplay(dom)) {
					// Self-equip clothing:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						}
					}
					// Equip clothing on partner:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						}
					}
					preference = getForeplayPreference(dom, sub);
					preventCreampie = preventCreampie(preference, dom, sub);
					sb.append("<p style='margin:0; padding:0; text-align:center;'>");
					sb.append("[style.boldPurpleLight(前戏)] ([style.colourSexDom("+Util.capitaliseSentence(preference.getPerformingSexArea().getName(dom, true))+")]-[style.colourSexSub("+preference.getTargetedSexArea().getName(sub, true)+")]): ");
					sb.append(dom.calculateGenericSexEffects(true, false, sub, preference, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null)));
					sb.append("</p>");
				}
				
				// Main sex:
				preference = getMainSexPreference(dom, sub);
				preventCreampie = preventCreampie(preference, dom, sub);
				// If equal sex control, dom should satisfy subs:
				int orgamsNeeded = !Main.sex.isConsensual()
						?(dom.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(dom))
						:Math.max((sub.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(sub)), (dom.getOrgasmsBeforeSatisfied()-Main.sex.getNumberOfOrgasms(dom)));
				for(int i=0; i<orgamsNeeded; i++) {
					// Regenerate cum by 5 minutes' worth of cum, so that there's cum for the next orgasm:
					// Moved before orgasm so the first quick sex orgasm isn't dry
					dom.incrementPenisStoredCum((5*60) * dom.getCumRegenerationPerSecond());
					sub.incrementPenisStoredCum((5*60) * sub.getCumRegenerationPerSecond());

					// Self-equip clothing:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToSelfEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_SELF_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToSelfEquip(dom, true);
						}
					}
					// Equip clothing on partner:
					if(dom instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(dom)) {
							sub.equipClothingFromInventory(clothingValue.getKey(), true, dom, dom);
							clothingValue = ((NPC)dom).getSexClothingToEquip(sub, true);
						}
					}
					if(sub instanceof NPC) {
						Value<AbstractClothing, String> clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						while(clothingValue!=null && SexActionUtility.PARTNER_EQUIP_CLOTHING.isQuickSexRequirementsMet(sub)) {
							dom.equipClothingFromInventory(clothingValue.getKey(), true, sub, sub);
							clothingValue = ((NPC)sub).getSexClothingToEquip(dom, true);
						}
					}
					sb.append("<p style='margin:0; padding:0; text-align:center;'>");
					sb.append("[style.boldPurple(性爱)]([style.colourSexDom("+Util.capitaliseSentence(preference.getPerformingSexArea().getName(dom, true))+")]-[style.colourSexSub("+preference.getTargetedSexArea().getName(sub, true)+")]): ");
					sb.append("</p>");
					sb.append(dom.calculateGenericSexEffects(true, true, sub, preference, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null))); // This increments orgasms
//					if(sub.hasPenisIgnoreDildo() && !dom.hasFetish(Fetish.FETISH_DENIAL) && Main.sex.getSexPace(sub)!=SexPace.SUB_RESISTING) {
//						sb.append(sub.calculateGenericSexEffects(true, true, dom, new SexType(SexAreaPenetration.PENIS, SexAreaPenetration.FINGER), GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED, (preventCreampie?GenericSexFlag.PREVENT_CREAMPIE:null))); // This increments orgasms
//						//TODO
//					}
					
					if(orgamsNeeded>1) {
						dom.generateSexChoices(false, sub);
						preference = getMainSexPreference(dom, sub);
						preventCreampie = preventCreampie(preference, dom, sub);
					}
				}
			}
			
			for(GameCharacter dom : new ArrayList<>(domsNotSatisfied)) {
				if(Main.sex.getNumberOfOrgasms(dom)>=dom.getOrgasmsBeforeSatisfied() || !dom.isAbleToOrgasm()) {
					domsNotSatisfied.remove(dom);
				}
			}
			
			if(!Main.sex.isConsensual()) { // If the doms don't care about satisfying all the subs, treat all subs assigned as being true
				if(domsNotSatisfied.isEmpty()) {
					allSubsAssigned = true;
				}
			} else {
				if(domsNotSatisfied.isEmpty() && !allSubsAssigned) {
					domsNotSatisfied = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
				}
			}
		}
		
		// Append description of what clothing was equipped during sex:
		StringBuilder equippedClothingSB = new StringBuilder();
		for(Entry<GameCharacter, Map<GameCharacter, List<AbstractClothing>>> equippedMapEntry : Main.sex.getClothingEquippedDuringSex().entrySet()) {
			if(equippedMapEntry.getValue().isEmpty()) {
				continue;
			}
			if(equippedClothingSB.length()>0) {
				equippedClothingSB.append("<br/>");
			}
			GameCharacter equipper = equippedMapEntry.getKey();
			equippedClothingSB.append(UtilText.parse(equipper, "<b><span style='color:"+(equipper.getFemininity().getColour().toWebHexString())+"'>[npc.Name]</span>在性爱过程中装备了以下衣物:</b>"));
			for(Entry<GameCharacter, List<AbstractClothing>> characterEntry : equippedMapEntry.getValue().entrySet()) {
				GameCharacter target = characterEntry.getKey();
				for(AbstractClothing clothing : characterEntry.getValue()) {
					equippedClothingSB.append(UtilText.parse(target, "<br/>"+clothing.getDisplayName(true)+"于<span style='color:"+(target.getFemininity().getColour().toWebHexString())+"'>[npc.name]</span>身上"));
				}
			}
		}
		if(equippedClothingSB.length()>0) {
			equippedClothingSB.insert(0, "<p style='text-align:center;'>");
			equippedClothingSB.append("</p>");
		}
		sb.append(equippedClothingSB.toString());
		
		return sb.toString();
	}

	private static String eggLayingTargetDescription(SexAreaPenetration penetratingArea, SexAreaInterface orifice, GameCharacter characterOrgasming, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		if(!orifice.isOrifice()) {
			System.err.println("ERROR: eggLayingTargetDescription()");
			new Exception().printStackTrace();
			return "";
		}
		
		SexAreaOrifice orificeTargeted = (SexAreaOrifice)orifice;
		
		boolean condomBreaks = characterOrgasming.isWearingCondom();
		int eggCount = characterOrgasming.getPregnantLitter().getTotalLitterCount();
		String penetrationAreaText = "[npc.cock]";
		String penetrationAreaPlusText = "[npc.cock+]";
		
		if(penetratingArea==SexAreaPenetration.CLIT) {
			penetrationAreaText = "[npc.clit]";
			penetrationAreaPlusText = "[npc.clit+]";
			
		} else if(penetratingArea==SexAreaPenetration.TAIL) {
			penetrationAreaText = "[npc.tail]";
			penetrationAreaPlusText = "[npc.tail+]";
		}
		
		String hipGrindText = "";
		String selfTargetText = "[npc2.namePos]";
		if(characterOrgasming==target) {
			selfTargetText = "[npc2.her]自己";
		}
		switch(orificeTargeted) {
			case ARMPITS:
			case ASS:
			case BREAST:
			case BREAST_CROTCH:
			case SPINNERET:
			case THIGHS:
			case URETHRA_PENIS:
			case URETHRA_VAGINA:
				break;
			case ANUS:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "将[npc.tail+]送入了"+selfTargetText+"的[npc2.assCloaca]";
				} else {
					hipGrindText = "将[npc.hips]塞进了"+selfTargetText+"的[npc2.assCloaca]";
				}
				break;
			case MOUTH:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "将[npc.tail+]深入"+selfTargetText+"的喉咙";
				} else {
					hipGrindText = "将[npc.hips]塞入了"+selfTargetText+"的嘴巴";
				}
				break;
			case NIPPLE:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "将[npc.tail+]插入了"+selfTargetText+"[npc2.nipple+(true)]";
				} else {
					hipGrindText = "将[npc.hips]塞进了"+selfTargetText+"的[npc2.breasts]";
				}
				break;
			case NIPPLE_CROTCH:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "将[npc.tail+]插入了"+selfTargetText+"[npc2.nippleCrotch+(true)]";
				} else {
					hipGrindText = "将[npc.hips]塞进了"+selfTargetText+"的[npc2.crotchBreasts]";
				}
				break;
			case VAGINA:
				if(penetratingArea==SexAreaPenetration.TAIL) {
					hipGrindText = "将[npc.tail+]送入了"+selfTargetText+"[npc2.pussy+]";
				} else {
					hipGrindText = "将[npc.hips]压向了"+selfTargetText+"的下体";
				}
				break;
		}

		if(characterOrgasming==target) {
			sb.append("[npc.name]想要将卵产在");
			if(eggCount!=1) {
				sb.append("");
			}
			switch(orificeTargeted) {
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case SPINNERET:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
				case ANUS:
					sb.append("[npc2.her]自己的屁股里，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.asshole]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
					sb.append("[npc.she]急切地"+hipGrindText+"，做好产卵准备后，兴奋得连连[npc.moansVerb]。");
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]肚子的深处，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]继续将"+penetrationAreaPlusText+"深深埋在[npc2.her]自己[npc2.asshole+]内，"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的肚子打造成了孵卵的温床。");
					}
					sb.append("</br>");
					sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.her]鼓起的小腹，感叹道，");
					sb.append("[npc.speech(舒服多了！)]");
					break;
				case MOUTH:
					sb.append("[npc2.her]自己的肚子里，于是竭尽所能，将"+penetrationAreaPlusText+"推入喉咙的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
					sb.append("[npc.she]急切地"+hipGrindText+"，做好产卵准备后，兴奋得连连[npc.moansVerb]。");
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]肚子的深处，[npc2.she]不禁惊讶得发出了模糊的[npc2.moan]声。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]继续将"+penetrationAreaPlusText+"深深埋在[npc2.her]自己的喉咙内，"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的肚子打造成了孵卵的温床。");
					}
					sb.append("</br>");
					sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.her]鼓起的小腹，感叹道，");
					sb.append("[npc.speech(舒服多了！)]");
					break;
				case NIPPLE:
					sb.append("[npc2.her]自己的胸部，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.nipple(true)]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
					sb.append("[npc.she]急切地"+hipGrindText+"，做好产卵准备后，兴奋得连连[npc.moansVerb]。");
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"
							+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]足以插入的乳房内，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]便拔了出来，又立刻插入了另外的[npc2.nipple(true)]里。"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的乳房打造成了孵卵的温床。");
					}
					sb.append("</br>");
					sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.her]鼓起的[npc2.breasts]，感叹道，");
					sb.append("[npc.speech(舒服多了！)]");
					break;
				case NIPPLE_CROTCH:
					sb.append("[npc2.her]自己的[npc2.crotchBoobs]，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.nippleCrotch(true)]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
					sb.append("[npc.she]急切地"+hipGrindText+"，做好产卵准备后，兴奋得连连[npc.moansVerb]。");
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"
							+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]足以插入的[npc2.crotchBoobs]内，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]便拔了出来，又立刻插入了另外的[npc2.nippleCrotch(true)]里。"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的[npc2.crotchBoobs]打造成了孵卵的温床。");
					}
					sb.append("</br>");
					sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.her]鼓起的[npc2.crotchBoobs]，感叹道，");
					sb.append("[npc.speech(舒服多了！)]");
					break;
				case VAGINA:
					sb.append("[npc2.her]自己的子宫里，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.pussy]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
					sb.append("[npc.she]急切地"+hipGrindText+"，做好产卵准备后，兴奋得连连[npc.moansVerb]。");
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]子宫的深处，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]继续将"+penetrationAreaPlusText+"深深埋在[npc2.her]自己[npc2.pussy+]内，"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的子宫打造成了孵卵的温床。");
					}
					sb.append("</br>");
					sb.append("伴随着最后一声[npc.moan]的消散，[npc.name]从其中抽出，宠爱地抚摸着[npc2.her]鼓起的小腹，感叹道，");
					sb.append("[npc.speech(舒服多了！)]");
					break;
			}
			
		} else {
			sb.append("[npc.name]想要将卵产在");
			if(eggCount!=1) {
				sb.append("");
			}
			switch(orificeTargeted) {
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case SPINNERET:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
				case ANUS:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"顶入[npc2.her]的[npc2.asshole]足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]粗暴地"+hipGrindText+"，嘴角露出一抹哂笑，");
							if(eggCount==1) {
								sb.append("[npc.speech(准备好接下我的蛋吧，[npc2.bitch]！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满，[npc2.bitch]！)]");
							}
							break;
						default:
							sb.append("[npc2.name]体内，将"+penetrationAreaPlusText+"推入[npc.her]的[npc2.asshole]足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]急切地"+hipGrindText+"，兴奋地高喊出来，");
							if(eggCount==1) {
								sb.append("[npc.speech(我要把蛋产在你身体里了！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满！)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]肚子的深处，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]继续将"+penetrationAreaPlusText+"深深埋在[npc2.namePos][npc2.asshole+]内，"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的肚子打造成了孵卵的温床。");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]粗鲁地从其中抽出，高高在上一般揉搓着[npc2.her]鼓起的小腹，低声道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子，[npc2.bitch]！)]");
							break;
						default:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.namePos]鼓起的小腹，感叹道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子！)]");
							break;
					}
					break;
				case MOUTH:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"顶入[npc2.her]喉咙的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]粗暴地"+hipGrindText+"，嘴角露出一抹哂笑，");
							if(eggCount==1) {
								sb.append("[npc.speech(准备好接下我的蛋吧，[npc2.bitch]！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满，[npc2.bitch]！)]");
							}
							break;
						default:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.her]喉咙的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]急切地"+hipGrindText+"，兴奋地高喊出来，");
							if(eggCount==1) {
								sb.append("[npc.speech(我要把蛋产在你身体里了！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满！)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]肚子的深处，[npc2.she]不禁惊讶得发出了模糊的[npc2.moan]声。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]继续将"+penetrationAreaPlusText+"深深埋在[npc2.namePos]的喉咙内，"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的肚子打造成了孵卵的温床。");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]粗鲁地从其中抽出，高高在上一般揉搓着[npc2.her]鼓起的小腹，低声道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子，[npc2.bitch]！)]");
							break;
						default:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.namePos]鼓起的小腹，感叹道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子！)]");
							break;
					}
					break;
				case NIPPLE:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"顶入[npc2.her][npc2.nipple(true)]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]粗暴地"+hipGrindText+"，嘴角露出一抹哂笑，");
							if(eggCount==1) {
								sb.append("[npc.speech(准备好接下我的蛋吧，[npc2.bitch]！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满，[npc2.bitch]！)]");
							}
							break;
						default:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.her][npc2.nipple(true)]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]急切地"+hipGrindText+"，兴奋地高喊出来，");
							if(eggCount==1) {
								sb.append("[npc.speech(我要把蛋产在你身体里了！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满！)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"
							+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]足以插入的乳房内，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]便拔了出来，又立刻插入了[npc2.namePos]另外的[npc2.nipple(true)]里。"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.namePos]的乳房打造成了孵卵的温床。");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]粗鲁地从其中抽出，高高在上似的揉搓着[npc2.namePos]鼓起的[npc2.breasts]，低声道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子，[npc2.bitch]！)]");
							break;
						default:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.namePos]鼓起的[npc2.breasts]，感叹道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子！)]");
							break;
					}
					break;
				case NIPPLE_CROTCH:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"顶入[npc2.her][npc2.nippleCrotch(true)]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]粗暴地"+hipGrindText+"，嘴角露出一抹哂笑，");
							if(eggCount==1) {
								sb.append("[npc.speech(准备好接下我的蛋吧，[npc2.bitch]！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满，[npc2.bitch]！)]");
							}
							break;
						default:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"推入[npc2.her][npc2.nippleCrotch(true)]的足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]急切地"+hipGrindText+"，兴奋地高喊出来，");
							if(eggCount==1) {
								sb.append("[npc.speech(我要把蛋产在你身体里了！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满！)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"
							+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]足以插入的[npc2.crotchBoobs]内，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]便拔了出来，又立刻插入了[npc2.namePos]另外的[npc2.nippleCrotch(true)]里。"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.namePos]的[npc2.crotchBoobs]打造成了孵卵的温床。");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]粗鲁地从其中抽出，高高在上一般揉搓着[npc2.her]鼓起的[npc2.crotchBoobs]，低声道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子，[npc2.bitch]！)]");
							break;
						default:
							sb.append("当最后一声[npc.moan]消散后，[npc.name]从其中抽出，宠爱地抚摸着[npc2.namePos]鼓起的[npc2.crotchBoobs]，感叹道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子！)]");
							break;
					}
					break;
				case VAGINA:
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("[npc2.name]体内，于是竭尽所能，将"+penetrationAreaPlusText+"顶入[npc2.her]的[npc2.pussy]足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]粗暴地"+hipGrindText+"，嘴角露出一抹哂笑，");
							if(eggCount==1) {
								sb.append("[npc.speech(准备好接下我的蛋吧，[npc2.bitch]！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满，[npc2.bitch]！)]");
							}
							break;
						default:
							sb.append("[npc2.name]体内，将"+penetrationAreaPlusText+"推入[npc.her]的[npc2.pussy]足够深处，却禁不住爆发出一阵[npc.a_moan+]。");
							sb.append("[npc.she]急切地"+hipGrindText+"，兴奋地高喊出来，");
							if(eggCount==1) {
								sb.append("[npc.speech(我要把蛋产在你身体里了！)]");
							} else {
								sb.append("[npc.speech(看我用蛋把你给塞满！)]");
							}
							break;
					}
					sb.append("</br>");
					sb.append("随后[npc2.name]确切无疑地感觉到一枚圆滚滚的卵沿着[npc.namePos]的"+penetrationAreaText+"被送了进来，"
							+ "当卵从末端挤出"+(condomBreaks?"，同时还撑破了[npc.namePos]的避孕套，":"，")+"安安稳稳地安置在了[npc2.her]子宫的深处，[npc2.she]不禁惊讶得[npc2.moan]了起来。");
					if(eggCount>1) {
						sb.append("卵还没有排空，[npc.name]继续将"+penetrationAreaPlusText+"深深埋在[npc2.namePos][npc2.pussy+]内，"
								+ "在一连串满足的[npc.moans]声后，便将[npc2.her]的子宫打造成了孵卵的温床。");
					}
					sb.append("</br>");
					switch(Main.sex.getSexPace(characterOrgasming)) {
						case DOM_ROUGH:
							sb.append("伴随着最后一声[npc.moan]的消散，[npc.name]粗鲁的从其中抽出，高高在上一般揉搓着[npc2.namePos]鼓起的小腹，感叹道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子，[npc2.bitch]！)]");
							break;
						default:
							sb.append("伴随着最后一声[npc.moan]的消散，[npc.name]从其中抽出，宠爱地抚摸着[npc2.namePos]鼓起的小腹，感叹道，");
							sb.append("[npc.speech(别忘了照顾好我的孩子！)]");
							break;
					}
					break;
			}
		}
		
		return UtilText.parse(characterOrgasming, target, sb.toString());
	}
	
	private static GameCharacter getCharacterToBeEgged(GameCharacter performingCharacter, GameCharacter targetedCharacter, SexAreaPenetration penetratingArea, SexAreaInterface targetedOrifice) {
		Set<GameCharacter> ongoingCharacters = Main.sex.getOngoingCharactersUsingAreas(performingCharacter, penetratingArea, targetedOrifice);
		if(ongoingCharacters.isEmpty()) {
			return null;
		}
		GameCharacter characterPenetrated = ongoingCharacters.iterator().next();
		
		List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(performingCharacter, penetratingArea);
		if(charactersPenetrated.contains(targetedCharacter)) {
			characterPenetrated = targetedCharacter;
		}
		
		return characterPenetrated;
	}
	
	public static final SexAction PLAYER_SKIP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.isCharacterImmobilised(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.UNIQUE_MAX; // So that this action is available with the 'Cocooned!' action.
//			}
//			return super.getPriority();
//		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_ORANGE;
		}
		@Override
		public String getActionTitle() {
			return "快速性爱";
		}
		@Override
		public String getActionDescription() {
			return "跳过该性爱场景，但仍会根据对方的喜好[style.boldSex(应用所有适用影响)]，就像该场景已经发生过一样。"
					+ "在场景结束前，会显示该性爱场景的总结。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isAbleToSkipSexScene()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String applyEndEffects(){
			quickSexDescription = "";
			return "";
		}
		@Override
		public String getDescription() {
			if(quickSexDescription.isEmpty()) {
				quickSexDescription = generateQuickSexDescription();
			}
			return quickSexDescription;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction GENERIC_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					|| (!Main.sex.isDom(Main.sex.getCharacterPerformingAction()) && !Main.sex.isConsensual());
		}
		
		@Override
		public String getActionTitle() {
			return "抗拒";
		}

		@Override
		public String getActionDescription() {
			return "抗拒和[npc2.name]做爱。";
		}

		@Override
		public String getDescription() {
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BACK_TO_WALL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]对着[npc2.name]拳打脚踢扇巴掌，拼命想挣脱，"
								+ "但完全是白费力气，[npc2.she]轻而易举地把[npc.herHim]顶到了墙上。",
						
						"[npc.name][npc.sob+]着，无力地挣扎，想摆脱[npc2.name]的控制，"
								+ "但[npc2.her]的力量比[npc.herHim]大太多了，轻而易举地把[npc.herHim]推回了墙上。",
						
						"[npc.name]并拼命挣扎反抗[npc2.name]，乞求[npc2.herHim]放过[npc.herHim]。"
								+ "[npc2.she]不由分说地把[npc.herHim]推回墙边，[npc.she][npc.sobbing]。");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]一边[npc.sob+]，一边从[npc2.name]身边爬走，"
								+ "但完全是白费力气，[npc2.name]一把抓住[npc.her][npc.hips]，把[npc.herHim]的[npc.ass]拉回[npc2.her]胯下。",
						
						"[npc.name]试图从[npc2.name]身边逃走，像狗一样手脚并用地爬。[npc2.name]不顾[npc.name]的挣扎和[npc.sob+]，抓住[npc.her][npc.hips]，把[npc.herHim]又拖了回来。",
						
						"[npc.name]发了疯一样想从[npc2.name]身边爬走，并乞求[npc2.name]放过[npc.herHim]，"
								+ "[npc2.name]一把抓住[npc.her][npc.hips]，把[npc.herHim]又拖了回来。[npc.name]发出了绝望的[npc.sobbing]。");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.FACE_TO_WALL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]对着[npc2.name]拳打脚踢扇巴掌，拼命想挣脱，"
								+ "但完全是白费力气，[npc2.name]轻而易举地把[npc.herHim]按在了墙上。",
						
						"[npc.name][npc.sob+]着，无力地挣扎，想摆脱[npc2.name]的控制，"
								+ "但[npc2.her]的力量比[npc.herHim]强太多了，轻而易举地就把[npc.herHim]摁回墙上。",
						
						"[npc.name]并拼命挣扎反抗[npc2.name]，乞求[npc2.herHim]放过[npc.herHim]。"
								+ "[npc2.she]不由分说地把[npc.herHim]顶到墙上，[npc.she][npc.sobbing]。");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]试着把[npc2.namePos]胯部从[npc.her][npc.face]上挪走，"
								+ "但完全是白费力气，[npc2.name]抓着[npc.her]的脑袋，强行扭回[npc2.her]胯下。",
						
						"[npc.name][npc.sob+]着，弱弱地挣扎，想把脸从[npc2.name]胯下抽走，"
								+ "但[npc2.name]紧紧钳着[npc.her]的头不放，一下就把[npc.herHim]脑袋摆正。",
								
						"[npc.name]并拼命挣扎反抗[npc2.name]，乞求[npc2.herHim]放过[npc.herHim]。"
								+ "[npc2.name]无视了绝望的[npc.sobbing]，不依不饶地把[npc.her]的[npc.face]掰回[npc2.her]的胯下。");
				
			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LYING_DOWN)) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]拼命想从[npc2.name]身下挣脱，试图把[npc2.herHim]从[npc.herHim]身上推开，"
								+ "但完全是白费力气，[npc2.name]轻而易举地把[npc.herHim]摁死在地上。",
						
						"[npc.name][npc.sob+]着，无力地挣扎，想从[npc2.name]身下爬出来，"
								+ "但[npc2.name]将身体紧紧扣在[npc.hers]身上，不让[npc.herHim]逃脱。",
						
						"[npc.name]并拼命挣扎反抗[npc2.name]，乞求[npc2.herHim]放过[npc.herHim]。"
								+ "然而[npc2.she]无视了痛苦的[npc.sobbing]，用身子将[npc.herHim]紧紧压着。");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]对着[npc2.name]拳打脚踢扇巴掌，拼命想挣脱，"
								+ "但被[npc2.name]轻松制服，徒劳无功。",
								
						"[npc.name][npc.sob+]着，无力地扭动，想摆脱[npc2.name]的控制。",
						
						"[npc.Name]拼命挣扎着反抗[npc2.name]，乞求[npc2.name]放[npc.herHim]走。[npc2.name]轻而易举地把[npc.Name]摁在原地，[npc.Name]不禁绝望地[npc.sobbing]。");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
		}
	};
	
	public static final SexAction PLAYER_SELF_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "长出鸡巴(自己)";
		}

		@Override
		public String getActionDescription() {
			return "用你的转化能力长出一条鸡巴。<b>性爱结束后，你会自动变走长出来的鸡巴。</b>";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().isAbleToSelfTransform()
					&& !Main.game.getPlayer().hasPenis()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {//TODO resisting variations
			if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
				return "你决定使用转化能力给自己变个粗大的恶魔鸡巴，接着面露出笑容，[npc.moanVerb]道，"
						+ "[npc.speech(你会喜欢这个的！)]";
			} else if (Main.game.getPlayer().isYouko()){
				return "你决定用转化能力给自己一根有肥大锁结的狐狸肉棒，你笑了笑，转而[npc.moanVerb]，"
						+ "[npc.speech(你会喜欢这个的！)]";
			} else {
				return "你决定使用史莱姆转化能力，给自己长一根粗大的史莱姆肉棒，你边笑边[npc.moanVerb]，"
						+ "[npc.speech(你一定会喜欢这个的！)]";
			}
		}

		@Override
		public String applyEffectsString() {
			Main.sex.getCharactersGrewCock().add(Main.game.getPlayer());
			
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
				sb.append(Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON, Main.game.getPlayer().getPreviousPenisType()!=PenisType.DEMON_COMMON));
			} else if (Main.game.getPlayer().isYouko()){
				sb.append(Main.game.getPlayer().setPenisType(PenisType.FOX_MORPH, Main.game.getPlayer().getPreviousPenisType()!=PenisType.FOX_MORPH));
			} else {
				AbstractPenisType penisType = RacialBody.valueOfRace(Main.game.getPlayer().getBody().getFleshSubspecies().getRace()).getPenisType();
				if(Main.game.getPlayer().getPreviousPenisType()!=null) {
					penisType = Main.game.getPlayer().getPreviousPenisType();
				}
				sb.append(Main.game.getPlayer().setPenisType(penisType, Main.game.getPlayer().getPreviousPenisType()!=penisType));
			}
			
			Main.game.getPlayer().fillCumToMaxStorage();
			
			if(Main.game.getPlayer().getPreviousPenisType()==null) {
				int size = Main.sex.getCharacterPerformingAction().getLegConfiguration().isLargeGenitals()?40:20;
				if(Main.game.getPlayer().getPenisRawSizeValue() < size) {
					sb.append(Main.game.getPlayer().setPenisSize(size));
				}
				if(Main.game.getPlayer().getPenisRawCumStorageValue() < Main.sex.getCharacterPerformingAction().getFleshSubspecies().getRace().getRacialBody().getCumProduction()) {
					sb.append(Main.game.getPlayer().setPenisCumStorage(Main.sex.getCharacterPerformingAction().getFleshSubspecies().getRace().getRacialBody().getCumProduction()));
				}
			}
			
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character instanceof NPC) {
					for(GameCharacter target : Main.sex.getAllParticipants()) {
						if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
							((NPC)character).generateSexChoices(false, target, null);
						}
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction PLAYER_GET_PARTNER_TO_GROW_PENIS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "长出鸡巴";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				return "让[npc2.name]使用恶魔自我转化能力，给自己长一根恶魔肉棒。";
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				return "让[npc2.name]使用与生俱来的自我转化能力，给自己长一根狐狸肉棒。";
			} else {
				return "让[npc2.name]使用史莱姆身体自我转化能力，给自己长一根史莱姆肉棒。";
			}
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isMasturbation()
					&& !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					//TODO make the NPC refuse with a reason before blocking this action
//					&& (Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterTargetedForSexAction(this), Main.game.getPlayer())
//							|| !Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative())
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToSelfTransform()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON
					|| Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				return "对[npc2.name]咧嘴笑着，你戏谑地说，[npc.speech(不如你用你的变形能力长个又粗又大的恶魔鸡巴，这样我们就能玩更多Play了！)]"
						+ "<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.name]咯咯地笑了笑，当你往下看[npc2.her]裸露的腹股沟时，发现一个巨大的肿块正在[npc2.her][npc2.skin]下形成。"
									+ "你还没来得及改变主意，它很快就长成了一个肥硕的恶魔阴茎，当你盯着它时，你能看到许多的小突起，沿着棒棒长出来，"
									+ "尖端渗出一小滴先走液"
							:"[npc2.name]咯咯地笑着，当你看向[npc2.her]的腹股沟时，你会看到一个庞大的肿块正在[npc2.her]"
									+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"下形成。"
									+ "你还没来得及改变主意，[npc2.she]就发出[npc2.a_moan+]，你发现[npc2.her]的衣服被巨大恶魔肉棒顶起个包。");
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				return "对[npc2.name]咧嘴笑着，你戏谑地说，[npc.speech(不如你用你的变形能力长个又粗又大的狐狸肉棒，这样我们就能玩更多Play了！)]"
						+ "<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
						?"[npc2.name]咯咯地笑了笑，你往下看[npc2.her]裸露的腹股沟，发现一个巨大的肿块正在[npc2.her][npc2.skin]下形成。"
						+ "你还没来得及改变主意，它很快就长成了一根狐狸肉棒，当你盯着它时，你能看到它整根抽动着，"
						+ "尖端渗出一小滴先走液"
						:"[npc2.name]咯咯地笑着，当你看向[npc2.her]的腹股沟时，你会看到一个庞大的肿块正在[npc2.her]"
						+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"下形成。"
						+ "你还没来得及改变主意，[npc2.she]就发出[npc2.a_moan+]，你发现[npc2.her]的衣服被巨大狐狸肉棒顶起个包。");
			} else {
				return "对[npc2.name]咧嘴笑着，你戏谑地说，[npc.speech(不如你用你的变形能力长个又粗又大的史莱姆阴茎，这样我们就能玩更多Play了！)]"
						+ "<br/><br/>"
						+(Main.sex.getCharacterTargetedForSexAction(this).isCoverableAreaExposed(CoverableArea.PENIS)
							?"[npc2.name]咯咯地笑了笑，当你往下看[npc2.her]裸露的腹股沟时，发现一个巨大的肿块正在[npc2.her][npc2.skin]下形成。"
									+ "你还没来得及改变主意，它很快就长成了一个肥大的史莱姆阴茎，当你盯着它时，你能看到它的长度悸动着，一滴先走液从它尖端渗出并滴下。"
							:"[npc2.name]咯咯地笑着，当你看向[npc2.her]的腹股沟时，你会看到一个庞大的肿块正在[npc2.her]"
									+Main.sex.getCharacterTargetedForSexAction(this).getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"下形成。"
									+ "你还没来得及改变主意，[npc2.she]就发出[npc2.a_moan+]，你发现[npc2.her]的衣服被巨大史莱姆肉棒顶起个包。");
			}
		}
		
		@Override
		public String applyEffectsString() {
			Main.sex.getCharactersGrewCock().add(Main.sex.getCharacterTargetedForSexAction(this));
			
			StringBuilder sb = new StringBuilder();
			boolean discoveredPenisColour = Main.sex.getCharacterTargetedForSexAction(this).isBodyCoveringTypesDiscovered(BodyCoveringType.PENIS);
			if(Main.sex.getCharacterTargetedForSexAction(this).getSubspeciesOverrideRace()==Race.DEMON || Main.sex.getCharacterTargetedForSexAction(this).isElemental()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.DEMON_COMMON, Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=PenisType.DEMON_COMMON));
				if(!discoveredPenisColour) {
					Main.sex.getCharacterTargetedForSexAction(this).setSkinCovering(BodyCoveringType.PENIS, Main.sex.getCharacterTargetedForSexAction(this).getCovering(BodyCoveringType.DEMON_COMMON).getPrimaryColour(), false);
				}
				
			} else if(Main.sex.getCharacterTargetedForSexAction(this).isYouko()) {
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(PenisType.FOX_MORPH, Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=PenisType.FOX_MORPH));
				if(!discoveredPenisColour) {
					Main.sex.getCharacterTargetedForSexAction(this).setSkinCovering(BodyCoveringType.PENIS, PresetColour.SKIN_RED, false);
				}
				
			} else {
				AbstractPenisType penisType = RacialBody.valueOfRace(Main.sex.getCharacterTargetedForSexAction(this).getBody().getFleshSubspecies().getRace()).getPenisType();
				if(Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=null) {
					penisType = Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType();
				}
				sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisType(penisType, Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()!=penisType));
			}
			

			if(Main.sex.getCharacterTargetedForSexAction(this).getPreviousPenisType()==null) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getPenisRawCumStorageValue() < 150) {
					Main.sex.getCharacterTargetedForSexAction(this).setPenisCumStorage(150);
				}
				Main.sex.getCharacterTargetedForSexAction(this).fillCumToMaxStorage();
				if(Main.sex.getCharacterTargetedForSexAction(this).hasVagina()) {
					Main.sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.ZERO_VESTIGIAL);
				} else if(Main.sex.getCharacterTargetedForSexAction(this).getTesticleSize().getValue() < TesticleSize.THREE_LARGE.getValue()){
					Main.sex.getCharacterTargetedForSexAction(this).setTesticleSize(TesticleSize.THREE_LARGE);
				}
				if(Main.sex.getCharacterTargetedForSexAction(this).getPenisGirth().getValue() < PenetrationGirth.THREE_AVERAGE.getValue()) {
					sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisGirth(PenetrationGirth.THREE_AVERAGE));
				}
				int size = Main.sex.getCharacterTargetedForSexAction(this).getLegConfiguration().isLargeGenitals()?40:20;
				if(Main.sex.getCharacterTargetedForSexAction(this).getPenisRawSizeValue() < size) {
					sb.append(Main.sex.getCharacterTargetedForSexAction(this).setPenisSize(size));
				}
			}
			
			
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				if(character instanceof NPC) {
					for(GameCharacter target : Main.sex.getAllParticipants()) {
						if(!target.equals(character) && Main.sex.getSexPositionSlot(target)!=SexSlotGeneric.MISC_WATCHING) {
							((NPC)character).generateSexChoices(false, target, null);
						}
					}
				}
			}
			
			return sb.toString();
		}
	};

	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public Colour getHighlightColour() {
			return PresetColour.PSYCHOACTIVE;
		}
		@Override
		public String getActionTitle() {
			return "冷静的建议";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.Name]现在陷入了幻觉之中，你可以利用这一点，催眠暗示[npc2.she]讨厌和你做爱的感觉。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return 
//					!Main.sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					Main.sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.PSYCHOACTIVE)
					&& !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || (Main.sex.getCharacterTargetedForSexAction(this).getLust()>25 && Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>"
					+ "尝试着利用[npc2.nameIsFull]的头脑受到强烈的致幻性物质影响这一事实，[npc.Name]俯身面向[npc2.herHim]并[npc.moansVerb]，"
						+ "[npc.speech(你真的不喜欢和我做爱，是吗？)]"
					+ "</p>"
					+ "<p>"
						+ "[npc2.Name]情不自禁地同意了[npc.sheIs]的话，并吞吞吐吐地回答,"
						+ "[npc2.speech(是啊……我……我真的不知道为什么我正在和你做爱……)]"
					+ "</p>"
					+ "<p>"
						+(!Main.sex.getCharacterPerformingAction().isPlayer()?"被[npc.her]的非自愿性癖所驱使，":"")+"[npc.name]又深入一分，继续道，"
						+ "[npc.speech(你宁愿我现在不操你，不是么？)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))) {
					sb.append("当催眠的暗示进入[npc2.namePos]的头脑时，[npc2.she]失望地叹了口气，"
							+ "[npc2.speech(这并不是很有趣……)]");
					
				} else {
					if(LustLevel.getLustLevelFromValue(Main.sex.getCharacterTargetedForSexAction(this).getLust()-50).getSexPaceSubmissive()==SexPace.SUB_RESISTING) {
						sb.append("催眠的暗示进入[npc2.namePos]的头脑，[npc2.she]发出迷乱的哀叫，"
								+ "[npc2.speech(等等，怎——什么情况？求你了，快停下！从我里面出去！)]");
					} else {
						sb.append("当催眠的暗示进入[npc2.namePos]的头脑时，[npc2.she]失望地叹了口气，"
								+ "[npc2.speech(这并不是很有趣……)]");
					}
				}
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterTargetedForSexAction(this).incrementLust(-50, false);
		}
	};
	
	public static final SexAction HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public Colour getHighlightColour() {
			return PresetColour.PSYCHOACTIVE;
		}
		@Override
		public String getActionTitle() {
			return "下流的建议";
		}
		@Override
		public String getActionDescription() {
			return "[npc2.Name]现在陷入了幻觉之中，你可以利用这一点，催眠暗示[npc2.she]爱上和你做爱的感觉。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return 
//					!Main.sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					Main.sex.getCharacterTargetedForSexAction(this).hasStatusEffect(StatusEffect.PSYCHOACTIVE)
					&& !Main.sex.getCharacterTargetedForSexAction(this).isAsleep()
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || (Main.sex.getCharacterTargetedForSexAction(this).getLust()<75 && !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_DOM)));
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("尝试着利用[npc2.nameIsFull]的头脑受到致幻性物质的影响后易于催眠暗示这一事实，[npc.Name]俯身面向[npc2.herHim]并[npc.moanVerb]，"
						+ "[npc.speech(你喜欢和我做爱，不是吗？)]"
					+ "</p>");
			
			if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>"
							+ "当[npc.Name]说这句话时，你突然感到一股稀里糊涂的温暖笼罩着你的头脑，当你回答时，你迷糊地意识到你的[npc2.eyes]变得呆滞起来，"
							+ "[npc2.speech(嗯……我……我真的好喜欢和你做爱……)]"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "[npc2.she]回答时眼神有些呆滞，"
						+ "[npc2.speech(是啊……我……我真的好爱和你做爱……)]"
					+ "</p>");
			}
			
			sb.append( "<p>"
						+ "[npc.Name]进一步暗示道，"
						+ "[npc.speech(你喜欢求我操你，难道不是吗？)]"
					+ "</p>");

			if(Main.sex.getCharacterTargetedForSexAction(this).isPlayer()) {
				sb.append("<p>"
							+ "你的神智完全沉溺于催眠中。你发现自己满脑子只剩下被[npc.Name]操这件事。你渴欲且[npc2.moansVerb]，"
							+ "[npc2.speech(好……好的！请，请操我！我<i>想要</i>你操我！)]"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "在那催眠暗示深入到[npc2.namePos]的神智之后，[npc2.she]听起来更加地欲求不满，[npc2.moansVerb]，"
						+ "[npc2.speech(好……好的，[npc.Name]！请，操我！我<i>希望</i>你来操死我！)]"
					+ "</p>");
			}
			
			sb.append( "<p>"
						+ "[npc.speech(真是个好[npc2.girl]，)][npc.Name]说，很高兴听到[npc2.namePos]的想法已经被[npc.hers]扭曲成[npc.she]想要的样子了。[npc.speech(我将赐予你任何你想要的！)]"
					+ "</p>");
			
			return sb.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharacterTargetedForSexAction(this).incrementLust(50, false);
		}
	};
	
	public static final SexAction GENERIC_DENY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "制止";
		}

		@Override
		public String getActionDescription() {
			return "强迫[npc2.name]保持完全静止，控制住，让他们没那么性奋。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isMasturbation()
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterPerformingAction()) // can't deny when hidden
					&& !Main.sex.getInitialSexManager().isHidden(Main.sex.getCharacterTargetedForSexAction(this))) { // can't deny hidden characters
				if(Main.sex.getCharacterPerformingAction().isPlayer()) {
					return true;
					
				} else {
					return !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this)) // Doms will not deny other doms.
							&& Main.sex.getCharacterTargetedForSexAction(this).getArousal()>=50 // They will not deny if less than 50 arousal.
							&& Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_DENIAL).isPositive()
							&& !Main.sex.getLastUsedSexAction(Main.sex.getCharacterPerformingAction()).equals(this); // Do not deny twice in a row
				}
			}
			return false;
		}

		@Override
		public String getDescription() {//TODO BDSM gear. penetrations.
			UtilText.nodeContentSB.setLength(0);
			
			boolean alreadyOrgasmed = Main.sex.getNumberOfOrgasms(Main.sex.getCharacterTargetedForSexAction(this))>0;
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					if(!Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
					UtilText.nodeContentSB.append(
						UtilText.returnStringAtRandom(
							"温柔而坚定地拽住[npc2.namePos][npc2.arms]，[npc.Name]静静地抱住[npc2.herHim]，一边轻声[npc.moaning]，一边等待[npc2.herHim]冷静下来 ，",
							"[npc.Name]牢牢抓住[npc2.namePos]的[npc2.arms]，把住[npc2.herHim]，发出[npc.a_moan+]，强行让[npc2.herHim]冷静下来，",
							"[npc.Name]轻柔地[npc.moaning]，牢牢抓住[npc2.namePos]的[npc2.arms]，调戏着[npc2.name]，等待[npc2.namePos]平静下来，"));
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
									"[npc.speech(这才是乖[npc2.girl]……我们现在都不想让你"+(alreadyOrgasmed?"再高潮一次":"就这样高潮")+"，对吧？)]",
									"[npc.speech(现在该做个乖[npc2.girl]了。平静一会。你也不想很快就和我"+(alreadyOrgasmed?"再高潮一次":"达到高潮")+"吧，对不对？)]",
									"[npc.speech(乖乖[npc2.girl]……只需要一小会，平静一下。我们现在都不想你"+(alreadyOrgasmed?"就这样再高潮一次":"太快高潮")+"呢，是不是？)]"));
					} else {
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
									"[npc.Name]从[npc2.name]身边退开，不再继续玩弄[npc2.herHim]，[npc.Name]轻柔地[npc.moaning]着，等待[npc2.herHim]平静下来。",
									"[npc.Name]中止了与[npc2.name]的肉体接触，不再继续玩弄[npc2.herHim]，[npc.Name]发出一声[npc.a_moan+]，等待着[npc2.herHim]冷静下来。",
									"[npc.Name]暂停了一会，不再继续玩弄[npc2.name]，[npc.Name]轻柔地[npc.verbMoans]着，等待[npc2.herHim]平静下来。"));
					}
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"坚定地拽住[npc2.namePos][npc2.arms]，[npc.Name]静静地抱住[npc2.herHim]，一边发出[npc.moaning+]，一边等待[npc2.herHim]冷静下来 ，",
								"[npc.Name]牢牢抓住[npc2.namePos]的[npc2.arms]，把住[npc2.herHim]，发出[npc.a_moan+]，强行让[npc2.herHim]冷静下来，",
								"[npc.Name]发出[npc.Moaning+]，牢牢抓住[npc2.namePos]的[npc2.arms]，调戏着[npc2.name]，等待[npc2.namePos]平静下来，"));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(这才是乖[npc2.girl]！我们现在都不想让你"+(alreadyOrgasmed?"再高潮一次":"就这样高潮")+"，对吧？！)]",
								"[npc.speech(现在该做个乖[npc2.girl]了。平静一会。你也不想很快就和我"+(alreadyOrgasmed?"再高潮一次":"达到高潮")+"吧，对不对？)]",
								"[npc.speech(乖乖[npc2.girl]！只需要一小会，平静一下。我们现在都不想你"+(alreadyOrgasmed?"就这样再高潮一次":"太快高潮")+"呢，是不是？！)]"));
						break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"粗鲁地紧拽住[npc2.namePos][npc2.arms]，[npc.Name]强制[npc2.herHim]保持不动，一边威胁地咆哮，一边等待[npc2.herHim]冷静下来 ，",
								"[npc.Name]粗暴地抓住[npc2.namePos]的[npc2.arms]，强迫[npc2.herHim]原地不动，还发出威胁的咆哮让[npc2.herHim]平静下来，",
								"[npc.Name]气势汹汹地咆哮着，牢牢抓住[npc2.namePos]的[npc2.arms]，威胁[npc.her]的对象，等[npc2.herHim]平静下来，"));

						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
								"[npc.speech(别乱动，你个笨[npc2.bitch]！你可不能和我"+(alreadyOrgasmed?"再高潮一次":"就这么高潮")+"，懂了吗？！)]",
								"[npc.speech(别乱动，[npc2.bitch]，能不能平静点！你不会以为自己"+(alreadyOrgasmed?"还能这么快和我高潮":"能这么快就和我高潮")+"吧！)]",
								"[npc.speech(就这样，[npc2.bitch]！别动，平静下来！我们现在都不想你"+(alreadyOrgasmed?"再这么高潮":"这么快高潮")+"，对吧？)]"));
						break;
				default:
					break;
			}
			if(!Main.sex.getCharacterTargetedForSexAction(this).isAsleep()) {
				UtilText.nodeContentSB.append("<br/><br/>");
				boolean nameKnown = Main.sex.getCharacterPerformingAction().isPlayerKnowsName();
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_EAGER:
						UtilText.nodeContentSB.append(
							UtilText.returnStringAtRandom(
									"[npc2.speechNoExtraEffects("+(nameKnown?"不行，[npc.name]，":"不要……")+")][npc2.name]回答，努力抑制声音里的兴奋，[npc2.speechNoExtraEffects(我会忍住的……)]",
									"[npc2.speechNoExtraEffects("+(nameKnown?"不行，[npc.name]，":"不要……")+")][npc2.name][npc2.moansVerb]，声音中强烈的快感失去控制，冲撞而出，[npc2.speechNoExtraEffects(我会尽量憋回去的……)]",
									"[npc2.speechNoExtraEffects("+(nameKnown?"不行，[npc.name]，":"不要……")+")][npc2.name]回答，然后努力扼住狂乱的[npc2.moan]，[npc2.speechNoExtraEffects(~嗯唔！~ 我会努力憋住的！)]"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
										"[npc2.speechNoExtraEffects("+(nameKnown?"不要，[npc.name]……":"不行……")+")][npc2.name]回答，[npc2.speechNoExtraEffects(我会努力忍住的……)]",
										"[npc2.speechNoExtraEffects("+(nameKnown?"不要，[npc.name]……":"不行……")+")][npc2.name][npc2.moansVerb]，[npc2.speechNoExtraEffects(我会努力憋住的……)]",
										"[npc2.speechNoExtraEffects("+(nameKnown?"不要，[npc.name]……":"不行……")+")][npc2.name]回答，[npc2.speechNoExtraEffects(我会努力憋住的！)]"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(
								UtilText.returnStringAtRandom(
										"[npc2.speechNoExtraEffects(只要你快走！)][npc2.name]悲伤地[npc2.sobsVerb]，[npc2.speechNoExtraEffects(我讨厌这样！)]",
										"[npc2.speechNoExtraEffects(放开我！)][npc2.name]狂乱地[npc2.sobsVerb]，努力挣脱[npc.namePos]的控制，[npc2.speechNoExtraEffects(快停下！求求你快走！)]",
										"[npc2.speechNoExtraEffects(为什么你就是不放我走呢？！)][npc2.name][npc2.sobsVerb]，随即试图虚弱无力地拽开[npc2.name]，[npc2.speechNoExtraEffects(我不想这样！)]"));
						break;
					default:
						break;
				}
			}
			UtilText.nodeContentSB.append("<br/><br/>");
			UtilText.nodeContentSB.append("[npc2.nameHasFull]一旦明显平静下来，[npc.name]就放松控制，然后继续之前的动作……");
			
			return UtilText.nodeContentSB.toString();
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
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止插入";
		}

		@Override
		public String getActionDescription() {
			return "停止所有[npc2.name]参与的现行插入动作。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening()
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Main.sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.name]从[npc2.name][npc2.asshole+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.name]从[npc2.name][npc2.asshole+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case ASS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]停止使用[npc2.name][npc2.ass+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]停止使用[npc2.name][npc2.ass+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case ARMPITS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]停止操[npc2.name][npc2.armpit+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]停止操[npc2.name][npc2.armpit+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case BREAST:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]停止玩弄[npc2.name][npc2.breasts+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]停止玩弄[npc2.name][npc2.breasts+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case BREAST_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]停止玩弄[npc2.name][npc2.crotchBoobs+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]停止玩弄[npc2.name][npc2.crotchBoobs+]后，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case MOUTH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name]嘴里拔出来，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name]嘴里拔出来，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case NIPPLE:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name][npc2.nipple+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name][npc2.nipple+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case NIPPLE_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name][npc2.crotchNipple+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name][npc2.crotchNipple+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case URETHRA_PENIS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name][npc2.penisUrethra+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name][npc2.penisUrethra+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case URETHRA_VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name][npc2.vaginaUrethra+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name][npc2.vaginaUrethra+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name][npc2.pussy+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name][npc2.pussy+]里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case THIGHS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name]腿缝间拔出来，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name]腿缝间拔出来，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
						case SPINNERET:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append(UtilText.parse(character, Main.sex.getCharacterTargetedForSexAction(this),
										"<br/>[npc.Name]从[npc2.name]丝囊里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							if (Main.sex.getCharacterOngoingSexArea(character, ot).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
								UtilText.nodeContentSB.append(UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), character,
										"<br/>[npc.Name]从[npc2.name]丝囊里拔出去了，[npc2.name]发出[npc2.a_moan+]。"));
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), character);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止插入(自己)";
		}

		@Override
		public String getActionDescription() {
			return "停止所有你参与的现行插入动作。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterEngagedInOngoingAction(Main.game.getPlayer())
					&& Main.sex.getAllParticipants(false).size()>2
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			for(GameCharacter character : Main.sex.getAllParticipants()) {
				for(SexAreaOrifice ot : SexAreaOrifice.values()) {
					switch(ot) {
						case ANUS:
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.asshole+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(character)) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name][npc2.asshole+]里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							break;
						case ASS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止使用[npc2.name][npc2.ass+]，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.ass+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case ARMPITS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你不再操[npc2.name][npc2.armpit+]后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.armpits+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case BREAST:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你不再玩弄[npc2.name][npc2.breasts+]后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.breasts+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case BREAST_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你不再玩弄[npc2.name][npc2.crotchBoobs+]后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.crotchBoobs+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case MOUTH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name]嘴里拔出来，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从自己嘴里拔出来后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case NIPPLE:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name][npc2.nipple+]里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.nipple+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case NIPPLE_CROTCH:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name][npc2.crotchNipple+]里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.crotchNipple+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case URETHRA_PENIS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name][npc2.penisUrethra+]里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.penisUrethra+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case URETHRA_VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name][npc2.vaginaUrethra+]里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.vaginaUrethra+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case VAGINA:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name][npc2.pussy+]里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己[npc.pussy+]后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case THIGHS:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name]腿缝间拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止玩弄自己的大腿后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
						case SPINNERET:
							if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你从[npc2.name]的丝囊里拔出来后，[npc2.her]发出[npc2.a_moan+]。");
							}
							if (Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append("<br/>你停止刺激自己的丝囊后，[npc.lips+]间飘出[npc.a_moan+]。");
							}
							break;
					}
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Main.sex.getAllParticipants()) {
				Main.sex.stopAllOngoingActions(Main.game.getPlayer(), character);
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "禁止自我行为";
		}
		@Override
		public String getActionDescription() {
			return "禁止[npc2.name]所有自插行为。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCharacterAllowedToUseSelfActions(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc2.Name]发出失望的[npc.moan]，因为你强行阻止了[npc2.herHim]继续刺激自己[npc2.pussy+]。");
				}
			}
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("因为你终止了[npc2.namePos]刺激自己的[npc2.asshole]，[npc2.she]发出悲惨的呜叫。");
				}
			}
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.name]冲着你噘了噘嘴，因为你强行阻止了[npc2.herHim]继续刺激自己[npc2.nipples+]。");
				}
			}
			
			if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name]发出了失望的[npc.moan]，因为你强行阻止[npc2.herHim]使用自己的嘴。");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0) {
				UtilText.nodeContentSB.append("<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("[npc.speech(我可不想看见你尝试放开自己，)]你冲着[npc2.name][npc.moanVerb]。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]不能再使用自插行为了。</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.setCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "允许自慰";
		}
		@Override
		public String getActionDescription() {
			return "允许[npc2.name]所有自插行为。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCharacterAllowedToUseSelfActions(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(你可以随心意触碰自己，)]你对着[npc2.name][npc.moanVerb]。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]现在能使用任意自插行为了。</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCharacterAllowedToUseSelfActions(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};

	public static final SexAction PLAYER_FORBID_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "严格限制动作";
		}
		@Override
		public String getActionDescription() {
			return "限制[npc2.namePos]的控制等级，阻止[npc2.herHim]所有非自插的插入行为。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& !Main.sex.isDom(target)
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(target).getValue()>=SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(没得到允许，你什么都不许做，)]你命令[npc2.name]说。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]的控制等级被限制了，不能做非自插的插入行为。</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.setForcedSexControl(target, SexControl.ONGOING_ONLY);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CONTROL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "减弱动作限制";
		}
		@Override
		public String getActionDescription() {
			return "取消[npc2.namePos]的控制等级限制，允许[npc2.herHim]做非自插的插入行为。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& !Main.sex.isDom(target)
					&& !Main.sex.isMasturbation()
					&& Main.sex.getSexControl(target).getValue()<SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS.getValue()
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(你可以随心做事了，)]你对[npc2.name]说，让[npc2.she]按喜好去做。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]的控制等级不受限了，可以按意愿做非自插的插入行为。</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.setForcedSexControl(target, SexControl.FULL);
			for(GameCharacter participant : Main.sex.getAllParticipants()) {
				if(!participant.equals(target) && Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "禁止姿势切换";
		}
		@Override
		public String getActionDescription() {
			return "禁止[npc2.name]使用姿势切换动作。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& performer.isPlayer()
					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(target);
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("[npc.speech(我不想看见你试图换位置，)]你冲着[npc2.name][npc.moanVerb]。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]将不能再使用姿势切换动作。</i>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
			
			Main.sex.addCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_POSITIONING_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "允许姿势切换";
		}
		@Override
		public String getActionDescription() {
			return "允许[npc2.name]使用姿势切换动作。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCharacterForbiddenByOthersFromPositioning(target)
					&& performer.isPlayer()
					&& Main.sex.getInitialSexManager().isPositionChangingAllowed(target);
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(如果你想，可以换成任何你觉得舒服的姿势，)]你冲着[npc2.name][npc.moanVerb]。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]现在能使用姿势切换动作了。</i>";
		}
		@Override
		public void applyEffects() {
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			Main.sex.removeCharacterForbiddenByOthersFromPositioning(Main.sex.getCharacterTargetedForSexAction(this));
			for(GameCharacter participant : Main.sex.getAllParticipants()) {
				if(!participant.equals(target) && Main.sex.getSexPositionSlot(participant)!=SexSlotGeneric.MISC_WATCHING) {
					((NPC)target).generateSexChoices(false, participant, null);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "禁止着装他人";
		}
		@Override
		public String getActionDescription() {
			return "禁止[npc2.name]管理你的衣物。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCanRemoveOthersClothing(target, null)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(你<i>敢</i>碰我的衣服试试！)]你冲着[npc2.name]咆哮。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]不会再尝试脱下或者替换你的衣物。</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "允许着装他人";
		}
		@Override
		public String getActionDescription() {
			return "允许[npc2.name]脱下并替换你的衣物。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCanRemoveOthersClothing(target, null)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(你帮我脱些衣服吧？)]你[npc.moan]道。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]能够管理你的衣物了。</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveOthersClothing(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "禁止自我着装";
		}
		@Override
		public String getActionDescription() {
			return "禁止[npc2.name]管理自己的衣物。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& Main.sex.isCanRemoveSelfClothing(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(你<i>敢</i>碰自己的衣服试试！)]你冲着[npc2.name]咆哮。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]不会再尝试移除或替换自己的衣物。</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "允许自我着装";
		}
		@Override
		public String getActionDescription() {
			return "允许[npc2.name]脱下并替换自己的衣物。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			
			return !target.isAsleep()
					&& (Main.sex.getSexControl(performer)==SexControl.FULL || (Main.sex.isCharacterImmobilised(performer) && Main.sex.isDom(performer) && !Main.sex.isSexControlForced(performer)))
					&& (Main.sex.getSexControl(target)!=SexControl.FULL || !Main.sex.isDom(target))
					&& !Main.sex.isMasturbation()
					&& !Main.sex.isCanRemoveSelfClothing(target)
					&& performer.isPlayer();
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
		@Override
		public String getDescription() {
			return "[npc.speech(不如你脱些衣服吧？)]你[npc.moan]道。<br/><br/>"
					+ "<i style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name]现在能管理[npc2.her]的衣物了。</i>";
		}
		@Override
		public void applyEffects() {
			Main.sex.setCanRemoveSelfClothing(Main.sex.getCharacterTargetedForSexAction(this), true);
		}
	};
	
	
	public static final SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止对象动作";
		}

		@Override
		public String getActionDescription() {
			return "停止[npc2.name]所有自插行为。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isMasturbation()
					&& Main.sex.isCharacterSelfOngoingActionHappening(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc2.Name]发出失望的[npc.moan]，因为你强行阻止了[npc2.herHim]继续刺激自己[npc2.pussy+]。");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("因为你终止了[npc2.namePos]刺激自己的[npc2.asshole]，[npc2.she]发出悲惨的呜叫。");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.name]冲着你噘了噘嘴，因为你强行阻止了[npc2.herHim]继续刺激自己[npc2.nipples+]。");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name]发出了失望的[npc.moan]，因为你强行阻止[npc2.herHim]使用自己的嘴。");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止玩家动作";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterSelfOngoingActionHappening(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isAnyNonSelfOngoingActionHappening()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc.Name]发出愤怒的咆哮，强迫[npc2.name]停止刺激[npc2.her][npc2.pussy+]。");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.name]终止了[npc2.namePos]刺激自己的[npc2.asshole]，还冲着[npc2.herHim]威胁地咆哮。");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name]冲着[npc2.name]皱眉，强迫[npc2.herHim]停止刺激[npc2.nipples+]。");
				}
			}
			
			if (Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Main.sex.getCharacterOngoingSexArea(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name]用[npc.tongue]发出不赞同的哒哒声，强迫[npc2.name]停止使用[npc2.her]的嘴。");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Main.sex.stopAllOngoingActions(Main.sex.getCharacterTargetedForSexAction(this), Main.sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	/**
	 * Special end action for submissive NPCs who end up resisting, and who also have the power to stop sex.
	 */
	public static final SexAction PARTNER_STOP_SEX_NOT_HAVING_FUN = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "结束性爱";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& (!Main.sex.getCharacterPerformingAction().isSlave() || !Main.sex.getAllParticipants().contains(Main.sex.getCharacterPerformingAction().getOwner()))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())==SexPace.SUB_RESISTING
					&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_NON_CON_SUB);
			
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "[npc.Name]恼火地叹了口气，挣开了[npc2.namePos]的爪子，"
					+ "[npc.speechNoExtraEffects(呃啊……我现在真的没感觉到好吗？)]";
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return Main.sex.isMasturbation()
					?"停止自慰"
					:"停止性爱";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isCharacterWantingToStopSex(Main.sex.getCharacterPerformingAction())
					&& (!Main.sex.getAllParticipants(false).contains(Main.game.getPlayer()) || !Main.sex.isReadyToOrgasm(Main.game.getPlayer()))
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).getGenericEndSexDescription(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "停止观看";
			}
			return Main.sex.isMasturbation()
					?"停止自慰"
					:"停止性爱";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				return "你离开了，不再观看面前上演的性爱场景。"
						+ "<br/>由于该场景已经正式发生，仍会对所有参与者[style.boldSex(施加所有应施加的效果)]。";
			}
			return Main.sex.isMasturbation()
					?"结束自慰。"
					:"停止与[npc2.name]做爱。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getInitialSexManager().isPlayerAbleToStopSex()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getDescription() {
			return Main.sex.isSpectator(Main.game.getPlayer())
					?"你看够表演了，转身离开，停止观看面前上演的性爱场景……"
					:(Main.sex.isMasturbation()
						?"你已经满足了，停止了自慰……"
						:"你感觉足够了，结束了性爱……");
		}
		@Override
		public SexParticipantType getParticipantType() {
			return Main.sex.isMasturbation() || Main.sex.isSpectator(Main.game.getPlayer())
					?SexParticipantType.SELF
					:SexParticipantType.NORMAL;
		}
		@Override
		public boolean endsSex() {
			return true;
		}
		@Override
		public String applyEndEffects(){
			 // Generate effects when ending sex as hidden spectator, but do not assign it to the 'quickSexDescription' variable, as that's only used for display in PLAYER_QUICK_SEX
			if(Main.sex.isSpectator(Main.game.getPlayer()) && Main.sex.getInitialSexManager().isHidden(Main.game.getPlayer())) {
				generateQuickSexDescription();
			}
			return "";
		}
	};

	public static final SexAction ROPE_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.ROPE);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(被缚！)]";
		}
		@Override
		public String getActionDescription() {
			return "绳索绑住了你的身体，你不能动了！";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.ROPE)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]努力扭动起来。但[npc.her]被绳索牢牢绑住身体，只得仰倒在地，一阵眩晕。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction CHAINS_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.CHAINS);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(被缚！)]";
		}
		@Override
		public String getActionDescription() {
			return "锁链绑住了你的身体，你不能动了！";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.CHAINS)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]努力扭动起来。但[npc.her]被链子牢牢绑住身体，只得仰倒在地，一阵眩晕。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction STOCKS_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.STOCKS);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(被缚！)]";
		}
		@Override
		public String getActionDescription() {
			return "[npc.nameIsFull]被锁在一套颈手枷中，无法做出任何行动！";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.STOCKS)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]努力扭动起来，但[npc.sheIs]被颈手枷牢牢锁住，完全动弹不得。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Spinneret:
	
	public static final SexAction SPINNERET_SPIN_CONDOM_SELF = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "纺织避孕套(自己)";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊，将你的[npc.penis]裹在避孕套般的网罩中。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| ((NPC) Main.sex.getCharacterPerformingAction()).isWantingToEquipCondom(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())))
					&& Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterPerformingAction(), InventorySlot.PENIS, null)
					&& Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterPerformingAction().equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
	};
	
	public static final SexAction SPINNERET_SPIN_CONDOM_PARTNER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "纺织避孕套";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊，将[npc2.namePos]的[npc2.penis]裹在避孕套般的网罩中。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer()
						|| ((NPC) Main.sex.getCharacterPerformingAction()).isWantingToEquipCondomOnPartner(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction())))
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.PENIS, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.PENIS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
	};
	
	public static final SexAction SPINNERET_SPIN_SEAL_VAGINA = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "纺织小穴封印";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊，将[npc2.namePos]的[npc2.pussy]用一层厚网罩住。";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.VAGINA, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.VAGINA)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_vagina"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_ANUS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "纺织屁股封印";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊，将[npc2.namePos]的[npc2.asshole]用一层厚网罩住。";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.ANUS, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.ANUS)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_anus"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_NIPPLES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "纺织乳头封印";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊，将[npc2.namePos]的[npc2.nipples]用一层厚网罩住。";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.NIPPLE, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.NIPPLE)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_nipples"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_SPIN_SEAL_MOUTH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public String getActionTitle() {
			return "纺织嘴部封印";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊，将[npc2.namePos]的[npc2.mouth]用一层厚网罩住。";
		}
		@Override
		public boolean isBaseRequirementsMet() {//TODO add behaviour for NPCs too
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), null)
					&& Main.sex.isClothingEquipAvailable(Main.sex.getCharacterTargetedForSexAction(this), InventorySlot.MOUTH, null)
					&& Main.sex.getCharacterTargetedForSexAction(this).getClothingInSlot(InventorySlot.MOUTH)==null;
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			sb.append("<p>");
			sb.append(Main.sex.getCharacterTargetedForSexAction(this).equipClothingFromNowhere(
					Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_webbing_seal_mouth"), false), true, Main.sex.getCharacterPerformingAction()));
			sb.append("</p>");
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Cocooning:
	
	public static final SexAction SPINNERET_COCOON_PARTNER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.SPINNERET, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "茧刑[npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "使用你的丝囊把[npc2.name]裹成茧。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "[npc.Name]不想让[npc2.name]自己动，便将丝囊对准[npc2.herHim]，然后开始喷射出一缕缕连续不断的粘稠网状物。"
					+ "[npc2.namePos]还没明白过来发生了什么，[npc2.arms]和[npc2.legs]就全被强力束缚住了，"
						+ "只不过几秒钟，[npc.Name]就把[npc2.herHim]全包到了茧里。"
					+ "[npc.Name]对自己的成品得意地坏笑着，准备好好利用[style.boldBad([npc2.name]现在不能动)]的现状。";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.COCOON, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction SPINNERET_COCOON_PARTNER_REMOVE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "移除蛛网";
		}
		@Override
		public String getActionDescription() {
			return "撕开裹着[npc2.name]的茧。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COCOON)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "[npc.Name]决定束缚够了[npc2.name]，便撕开了裹着[npc2.her]的茧。"
					+ "不过片刻，[npc.Name]就成功毁掉了所有网，结果是，[style.boldGood([npc2.name]又能自由行动了)]。";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.COCOON);
		}
	};

	public static final SexAction COCOONED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.COCOON);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(被茧缚！)]";
		}
		@Override
		public String getActionDescription() {
			return "厚厚的蜘蛛网茧阻止了你的行动！";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.COCOON)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]努力扭动起来。但[npc.her]被茧牢牢缚住身体，只得仰倒在地，一阵眩晕。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Tentacles:
	
	public static final SexAction TENTACLES_RESTRICT_PARTNER = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TENTACLE, null)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "触手限制";
		}
		@Override
		public String getActionDescription() {
			return "用你的触手抓住[npc2.name]，阻止[npc2.herHim]移动。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "[npc.Name]不想让[npc2.name]自己动，便用[npc.tentacles]抓住[npc2.herHim]，将其牢牢定在原地。"
					+ "[npc2.name]咯咯笑，尝试着与紧缚斗争了几下，但徒劳无获，"
						+ "[npc.name]准备好好利用[style.boldBad([npc2.name]现在不能动)]的现状。";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.TENTACLE_RESTRICTION, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TENTACLES_RELEASE_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "释放触手";
		}
		@Override
		public String getActionDescription() {
			return "放开对[npc2.name]的束缚，让[npc2.herHim]能再次自由行动。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "[npc.Name]觉得[npc2.name]受够了束缚，便准备松开[npc.tentacles]的束握。"
					+ "[npc.name]完全放开了对[npc2.namePos]的触手束缚，得意地坏笑着，愉悦地欣赏着[npc2.herHim]脸上解脱的表情。"
						+ "结果是，[style.boldGood([npc2.name]现在能自由行动了)]。";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.TENTACLE_RESTRICTION);
		}
	};

	public static final SexAction TENTACLE_SQUEEZE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "触手挤压";
		}
		@Override
		public String getActionDescription() {
			return "趁[npc2.name]完全被你的触手束缚，又挤又捆[npc2.her]的身体。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]趁[npc2.name]完全被触手束缚，又箍弄又限制[npc2.her]的身体，"
							+ "让[npc2.herHim]发出悲哀的、被窒息的喘气声。",
					"[npc.Name]想让[npc2.name]知道，[npc2.sheIs]完全受自己的摆布，便用触手牢牢箍紧[npc2.her]的身体，"
							+ "引得[npc2.herHim]喘息逐渐微弱下来，最后只能迷乱地发出些呼气的杂音。",
					"[npc.name]处于完全的掌控地位，用触手牢牢箍住[npc2.namePos]，"
							+ "从狂乱迷醉的喘息挣扎中取乐，欣赏着自己引导而出的、"+(targetPlayer?"你的窘态。":"[npc.her]对象的窘态。"),
					"[npc.Name]相当享受自己的支配地位，缓慢增加着触手捆[npc2.namePos]的紧度，直到"+(targetPlayer?"你":"[npc.her]的对象")
						+"什么都不能做，只能微弱挣扎着，拼命喘着气。",
					"[npc.Name]用触手牢牢箍住[npc2.namePos]，愉悦地挤压着"+(targetPlayer?"你":"[npc.her]对象")
						+"肺里的空气，[npc.she]忍不住发出支配的[npc.moan]，感受着[npc2.name]寻求自由的、轻微的颤动挣扎。"));

			sb.append(UtilText.returnStringAtRandom(
					"直到[npc2.nameIsFull]变得虚弱无力，[npc.Name]最终才放松下来，松开触手，"
							+ "[npc.she]允许[npc2.herHim]回复呼吸，并且意识回来了一些。",
					"[npc.Name]目前还不想让[npc2.name]失去意识，便在最后一刻把触手放松下来，"
							+ "允许[npc2.name]吸了一大口气，在晕倒边缘缓和过来。",
					"[npc.Name]这样限制了一会[npc2.name]，感觉已经压够了，便最后又紧紧地箍了一下，"
							+ "[npc.she]放松了触手，让[npc2.name]能够再次自由呼吸。",
					"一直等到[npc2.nameIsFull]意识模糊、快晕过去了，[npc.Name]才发出被取悦的[npc.moan]，"
							+ "然后放松触手，[npc2.name]又能把自己的肺灌满氧气。",
					"一直等到[npc2.nameIsFull]快晕过去了，[npc.name]才发出被取悦的[npc.moan]，然后放松触手，允许[npc2.name]深吸一口气，从窒息中缓和出来。"));
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SADIST, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TENTACLE_MASSAGE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "触手按摩";
		}
		@Override
		public String getActionDescription() {
			return "趁[npc2.name]完全被你的触手束缚，又揉又捏[npc2.her]的身体。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]趁[npc2.name]完全被触手束缚，又箍弄又揉按[npc2.her]的身体。",
					"[npc.Name]想让[npc2.name]知道，[npc2.sheIs]完全受自己的摆布，便用触手箍紧[npc2.her]的身体，开始按摩。",
					"[npc.Name]处于支配地位，用触手牢牢箍住[npc2.namePos]，还挤握按摩着"+(targetPlayer?"你的":"[npc.her]对象的")+"身体。",
					"[npc.Name]相当享受自己的支配地位，完全触手捆住[npc2.namePos]的身体，又挤又揉。",
					"[npc.Name]结实又牢固地触手捆住[npc2.namePos]的身体，愉悦地挤弄按摩着[npc2.herHim]。"));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						"虽然完全不可能，但[npc2.name]还是继续在[npc.namePos]掌控下挣扎着，[npc2.she]意识到这不过是徒劳无用，不禁发出苦闷的哭号。",
						"[npc2.Name]疯狂地哭喊着作为回应，失败了，失败了，又失败了，无法挣开[npc.namePos]的环抱。",
						"[npc2.name]拒绝接受自己的命运，努力挣脱[npc.namePos]紧窄的禁锢，但[npc2.her]的努力被证明是徒劳的。"));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.name]发出[npc2.a_moan+]，放松地享受着[npc.namePos][npc.tentacles+]滑过身体，又揉按着身体的触感。",
						"[npc2.Name]发出[npc2.a_moan+]回应着，显而易见，[npc2.sheIs]正享受着被[npc.namePos][npc.tentacles+]环住的感觉。",
						"[npc2.name]放松下来，享受着被[npc.namePos][npc.tentacles+]环绕的感觉，发出[npc2.a_moan+]，鼓励[npc.herHim]继续按摩。"));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction TENTACLE_BOUND = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.TENTACLE_RESTRICTION);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(被触手束缚！)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TENTACLE_RESTRICTION);
			return UtilText.parse(applier, "[npc.NameIsFull]用[npc.her]触手固定住你，你不能行动了！");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.TENTACLE_RESTRICTION)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TENTACLE_RESTRICTION);
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), applier,
					"[npc.Name]努力扭动起来，但[npc2.namePos]的触手太过有力，[npc.she]尽全力也只可悲地蠕动了几下。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	// Witch's seal:

	public static final SexAction WITCH_SEAL_CAST = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.SPELL_SCHOOL_ARCANE;
		}
		@Override
		public String getActionTitle() {
			return "释放“"+Spell.WITCH_SEAL.getName()+"”";
		}
		@Override
		public String getActionDescription() {
			return "对[npc2.name]施放法术“"+Spell.WITCH_SEAL.getName()+"”，固定[npc2.herHim]，完全阻止[npc2.herHim]行动。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer() // Only allow player to use this
					&& Main.sex.getCharacterPerformingAction().hasSpell(Spell.WITCH_SEAL, true)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "[npc.Name]不想让[npc2.name]自己动，便准备对[npc2.herHim]施放法术“"+Spell.WITCH_SEAL.getName()+"”。"
					+ "[npc.Name]将奥术之力集中在扫把上，召唤出一道强大的封印，把[npc2.name]控制在原地！"
						+ "[npc.Name]成功施放了法术，准备好好利用[style.boldBad([npc2.name]现在不能动)]的现状。";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.WITCH_SEAL, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction WITCH_SEALED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.WITCH_SEAL);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(封印！)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.WITCH_SEAL);
			return UtilText.parse(applier, "[npc.name]施放了魔女封锁术，你不能动了！");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.WITCH_SEAL)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			if(Main.sex.getInitialSexManager() instanceof SMAltarMissionarySealed) {
				return UtilText.returnStringAtRandom(
						"[npc.Name]努力扭动起来。但魔女封锁术太过强力，[npc.she]只得仰在祭坛上，一阵眩晕。",
						"五角星形的紫色光芒如同实质般压住了[npc.namePos]的身体，阻止了[npc.she]的挣扎，这证明魔女封锁术还在发挥束缚作用。",
						"[npc.Name]努力在祭坛上坐起来，但受制于魔女封锁术的束缚效果，[npc.sheIs]只能蠕来蠕去。",
						"[npc.Name]挣扎扭动时，看到周围魔女封锁术柔和的紫色光芒。",
						"[npc.speech(~嗯唔！~)][npc.name]呻吟着，徒劳地挣扎抵抗着魔女封锁术。",
						"[npc.speech(~啊哈啊！~)][npc.name]呜咽着，在祭坛上蠕动着，却被魔女封锁术锁在原地。");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name]努力扭动起来，但魔女封锁术太过强大，[npc.she]摔倒在地，一阵眩晕。",
						"五角星形的紫色光芒如同实质般压住了[npc.namePos]的身体，阻止了[npc.she]的挣扎，这证明魔女封锁术还在发挥束缚作用。",
						"[npc.Name]努力移动，但受制于魔女封锁术的束缚效果，[npc.sheIs]只能蠕来蠕去。",
						"[npc.Name]挣扎扭动时，看到周围魔女封锁术柔和的紫色光芒。",
						"[npc.speech(~嗯唔！~)][npc.name]呻吟着，徒劳地挣扎抵抗着魔女封锁术。",
						"[npc.speech(~啊哈啊！~)][npc.name]呜咽着，蠕动着，却被魔女封锁术锁在原地。");
			}
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction WITCH_SEAL_BREAK = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.SPELL_SCHOOL_ARCANE;
		}
		@Override
		public String getActionTitle() {
			return "驱散“"+Spell.WITCH_SEAL.getName()+"”";
		}
		@Override
		public String getActionDescription() {
			return "取消你对[npc2.name]施放的法术“"+Spell.WITCH_SEAL.getName()+"”，让[npc2.herHim]能再次自由行动。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.WITCH_SEAL)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "[npc.Name]觉得束缚够了[npc2.name]，便准备驱散法术“"+Spell.WITCH_SEAL.getName()+"”的效果。"
					+ "[npc.Name]深吸一口气，专注于控制奥术力量，打破了法术，结果是，[style.boldGood([npc2.name]现在又能自由行动了)]。";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.WITCH_SEAL);
		}
	};
	
	// Tentacles:
	
	public static final SexAction TAIL_CONSTRICTION_RESTRICT_PARTNER = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "收缩";
		}
		@Override
		public String getActionDescription() {
			return "用你的长尾巴卷住[npc2.name]，限制[npc2.herHim]，阻止[npc2.herHim]行动。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			if(Main.sex.getCharactersImmobilised().containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getCharactersImmobilised().get(ImmobilisationType.TAIL_CONSTRICTION).containsKey(Main.sex.getCharacterPerformingAction())) {
				return false; // If performing character is engaged in ongoing long-tail constriction, return false (as can only restrict one at a time).
			}
			return Main.sex.getCharacterPerformingAction().getLegConfiguration()==LegConfiguration.TAIL_LONG
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER))
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "[npc.Name]不想让[npc2.name]自己动，便用长尾巴抓住[npc2.herHim]，将其牢牢定在原地。"
					+ "[npc2.name]咯咯笑，尝试着与收缩的环缚斗争了几下，但徒劳无获，"
						+ "[npc.name]准备好好利用[style.boldBad([npc2.name]现在不能动)]的现状。";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.TAIL_CONSTRICTION, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_CONSTRICTION_RELEASE_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "解开束缚";
		}
		@Override
		public String getActionDescription() {
			return "放开对[npc2.name]的束缚，让[npc2.herHim]能再次自由行动。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "[npc.Name]觉得[npc2.name]受够了束缚，便准备松开长尾巴的环箍。"
					+ "[npc.name]完全放开了对[npc2.namePos]的束缚，得意地坏笑着，愉悦地欣赏着[npc2.herHim]脸上解脱的表情。"
						+ "结果是，[style.boldGood([npc2.name]现在能自由行动了)]。";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.TAIL_CONSTRICTION);
		}
	};

	public static final SexAction TAIL_SQUEEZE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "尾巴挤压";
		}
		@Override
		public String getActionDescription() {
			return "趁[npc2.name]完全被你的尾巴束缚，又挤又捆[npc2.her]的身体。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]趁[npc2.name]完全被尾巴束缚，又箍弄又限制[npc2.her]的身体，"
							+ "让[npc2.herHim]发出悲哀的、被窒息的喘气声。",
					"[npc.Name]想让[npc2.name]知道，[npc2.sheIs]完全受自己的摆布，便用尾巴牢牢箍紧[npc2.her]的身体，"
							+ "引得[npc2.herHim]喘息逐渐微弱下来，最后只能迷乱地发出些呼气的杂音。",
					"[npc.name]处于完全的掌控地位，用尾巴牢牢箍住[npc2.namePos]，"
							+ "从狂乱迷醉的喘息挣扎中取乐，欣赏着自己引导而出的、"+(targetPlayer?"你的窘态。":"[npc.her]对象的窘态。"),
					"[npc.Name]相当享受自己的支配地位，缓慢增加着尾捆[npc2.namePos]的紧度，直到"+(targetPlayer?"你":"[npc.her]的对象")
						+"什么都不能做，只能微弱挣扎着，拼命喘着气。",
					"[npc.Name]用尾巴牢牢箍住[npc2.namePos]，愉悦地挤压着"+(targetPlayer?"你":"[npc.her]对象")
						+"肺里的空气，[npc.she]忍不住发出支配的[npc.moan]，感受着[npc2.name]寻求自由的、轻微的颤动挣扎。"));
			
			sb.append(UtilText.returnStringAtRandom(
					"直到[npc2.nameIsFull]变得虚弱无力，[npc.Name]最终才放松下来，松开尾巴，"
							+ "[npc.she]允许[npc2.herHim]回复呼吸，并且意识回来了一些。",
					"[npc.Name]目前还不想让[npc2.name]失去意识，便在最后一刻把尾巴放松下来，"
							+ "允许[npc2.name]吸了一大口气，在晕倒边缘缓和过来。",
					"[npc.Name]这样限制了一会[npc2.name]，感觉已经压够了，便最后又紧紧地箍了一下，"
							+ "便放松了尾巴，让[npc2.name]能再次自由地呼吸。",
					"一直等到[npc2.nameIsFull]意识模糊、快晕过去了，[npc.Name]才发出被取悦的[npc.moan]，"
							+ "然后放松尾巴，[npc2.name]又能把自己的肺灌满氧气。",
					"一直等到[npc2.nameIsFull]快晕过去了，[npc.name]才发出被取悦的[npc.moan]，然后放松尾巴，允许[npc2.name]深吸一口气，从窒息中缓和出来。"));
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_SADIST, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASOCHIST, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_MASSAGE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public String getActionTitle() {
			return "尾巴按摩";
		}
		@Override
		public String getActionDescription() {
			return "趁[npc2.name]完全被你的尾巴束缚，又揉又捏[npc2.her]的身体。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			boolean targetPlayer = Main.sex.getCharacterTargetedForSexAction(this).isPlayer();
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]趁[npc2.name]完全被尾巴束缚，又箍弄又揉按[npc2.her]的身体。",
					"[npc.Name]想让[npc2.name]知道，[npc2.sheIs]完全受自己的摆布，便用尾巴箍紧[npc2.her]的身体，开始按摩。",
					"[npc.Name]处于支配地位，用尾巴牢牢箍住[npc2.namePos]，还挤握按摩着"+(targetPlayer?"你的":"[npc.her]对象的")+"身体。",
					"[npc.Name]相当享受自己的支配地位，完全尾捆住[npc2.namePos]的身体，又挤又揉。",
					"[npc.Name]结实又牢固地尾捆住[npc2.namePos]的身体，愉悦地挤弄按摩着[npc2.herHim]。"));
			
			if(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))==SexPace.SUB_RESISTING) {
				sb.append(UtilText.returnStringAtRandom(
						"虽然完全不可能，但[npc2.name]还是继续在[npc.namePos]掌控下挣扎着，[npc2.she]意识到这不过是徒劳无用，不禁发出苦闷的哭号。",
						"[npc2.Name]疯狂地哭喊着作为回应，失败了，失败了，又失败了，无法挣开[npc.namePos]的环抱。",
						"[npc2.name]拒绝接受自己的命运，努力挣脱[npc.namePos]紧窄的禁锢，但[npc2.her]的努力被证明是徒劳的。"));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.name]发出[npc2.a_moan+]，放松地享受着[npc.namePos]的尾巴滑过身体，又揉按着身体的触感。",
						"[npc2.Name]发出[npc2.a_moan+]回应着，显而易见，[npc2.sheIs]正享受着被[npc.namePos]的尾巴环住的感觉。",
						"[npc2.name]放松下来，享受着被[npc.namePos]的尾巴环绕的感觉，发出[npc2.a_moan+]，鼓励[npc.herHim]继续按摩。"));
			}
			
			return sb.toString();
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction TAIL_CONSTRICTED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.TAIL_CONSTRICTION);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(束缚！)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TAIL_CONSTRICTION);
			return UtilText.parse(applier, "[npc.Name]用[npc.her]的长尾巴固定住你，你不能行动了！");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.TAIL_CONSTRICTION)
					&& !Main.sex.isCharacterInanimateFromImmobilisation(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.TAIL_CONSTRICTION);
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), applier,
					"[npc.Name]努力扭动起来。但[npc2.namePos]的限制太过强大，[npc.she]的所有努力只不过是一些可悲的蠕动。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	

	// Command:

	public static final SexAction COMMAND_IMMOBILE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().isPlayer()) {
				return super.getPriority();
			}
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_BLUE_STEEL;
		}
		@Override
		public String getActionTitle() {
			return "变成性爱玩具";
		}
		@Override
		public String getActionDescription() {
			return "命令[npc2.name]保持完全静止，这样你就能把[npc2.herHim]当成一个无法活动且沉默的性爱玩具操干了。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_BONDAGE_APPLIER)) // Only allow player or bondage fetishists to use this
					&& Main.sex.getCharacterTargetedForSexAction(this).isDoll()
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue()
					&& !Main.sex.isCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public String getDescription() {
			return "[npc.name]想让[npc2.name]扮演一个无生命的性爱玩具，便命令[npc2.herHim]停止活动。"
						+ "[npc2.name]顺从地执行了[npc.namePos]的命令，静止在原地。"
						+ "[npc.name]对自己笑了笑，准备好好利用[style.boldBad([npc2.name]现在完全无法移动)]的现状。";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterImmobilised(ImmobilisationType.COMMAND, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this));
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_APPLIER);
			} else if(character.equals(Main.sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};
	
	public static final SexAction COMMAND_IMMOBILE_ACTION = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.COMMAND);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(变成性爱玩具！)]";
		}
		@Override
		public String getActionDescription() {
			GameCharacter applier = Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).get(ImmobilisationType.COMMAND);
			return UtilText.parse(applier, "[npc.name]命令你保持静止，你顺从地不再移动了！");
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.COMMAND)
					&& !Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.SLEEP);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]顺从地保持完全静止，就像是一个无生命的性爱玩偶。",
					"被这样命令之后，[npc.name]保持着完全的静止。",
					"[npc.name]保持着完全的静止，允许自己被当做一个毫无价值的性爱玩具来使用。",
					"[npc.Name]保持着完全的静止，对[npc.herHim]做任何事情[npc.herHim]都不再作出反应。",
					"[npc.name]是一个顺从的性爱玩偶，[npc.she]听从命令，保持着完全的静止。",
					"[npc.Name]表现得像个完美的无生命性爱玩偶，继续一动不动。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_BONDAGE_VICTIM);
			}
			return null;
		}
	};

	public static final SexAction COMMAND_IMMOBILE_RELEASE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "允许活动";
		}
		@Override
		public String getActionDescription() {
			return "告诉[npc2.name]不需要再保持静止了，允许[npc2.herHim]自由活动和交流。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.COMMAND)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "[npc.name]受够了[npc2.namePos]无法移动的状态，于是告诉[npc2.herHim]可以再次自由活动了。"
					+ "[npc2.name]带着诱人的目光发出了饥渴的呻吟声，这意味着[style.boldGood([npc2.name]现在又能自由行动了)]。";
		}
		@Override
		public void applyEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterTargetedForSexAction(this), ImmobilisationType.COMMAND);
		}
	};
	
	// Sleeping:
	
	public static final SexAction SLEEPING_ACTION = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.SLEEP);
		}
		@Override
		public String getActionTitle() {
			return "[style.boldBad(睡眠中……)]";
		}
		@Override
		public String getActionDescription() {
			return "你保持着熟睡……";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getImmobilisationTypes(Main.sex.getCharacterPerformingAction()).containsKey(ImmobilisationType.SLEEP);
		}
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]仍在沉睡中，没有表现出即将醒来的迹象。",
					"尽管偶尔会发出"+(Main.sex.getCharacterPerformingAction().isFeminine()?"呻吟":"哼唧")+"声，但[npc.name]依旧保持着熟睡。",
					"[npc.name]保持着熟睡，完全没有察觉自己正在被操。",
					"[npc.Name]继续沉睡着，无论对[npc.herHim]做什么，几乎都没反应。",
					"[npc.Name]简直是个超级重度沉睡者，边挨操边继续沉睡着。",
					"[npc.Name]毫无醒来的迹象，接着在睡梦中被操。");
		}
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
			return null;
		}
	};

	public static final SexAction SLEEPING_WAKE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.BASE_GREEN_LIME;
		}
		@Override
		public String getActionTitle() {
			return "叫醒[npc2.herHim]";
		}
		@Override
		public String getActionDescription() {
			return "摇晃[npc2.name]直到[npc2.she]醒来。";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isPlayer()
					&& Main.sex.getImmobilisationTypes(Main.sex.getCharacterTargetedForSexAction(this)).containsKey(ImmobilisationType.SLEEP)
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction()).getValue()>=SexControl.FULL.getValue();
		}
		@Override
		public String getDescription() {
			return "[npc.name]操够了睡着的[npc2.name]，便粗暴地摇晃[npc2.herHim]，想把[npc2.herHim]叫醒。"
					+ "[npc2.name]不情愿地睁开眼睛，惊声大叫着[style.boldGood(醒来)]。";
		}
		@Override
		public void applyEffects() {
			Main.sex.addCharacterWoken(Main.sex.getCharacterTargetedForSexAction(this));
		}
	};

	public static final SexAction SLEEPING_WOKEN_UP = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}
		@Override
		public boolean isOverrideAvailableDuringResisting() {
			return true;
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return types.contains(ImmobilisationType.SLEEP);
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.GENERIC_BAD;
		}
		@Override
		public String getActionTitle() {
			return "醒来";
		}
		@Override
		public String getActionDescription() {
			return "你醒来发现自己正在被操！";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().isAsleep()
					&& Main.sex.getCharactersWoken().contains(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			// Woken by oral:
			if(Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH).stream().anyMatch(penetration->penetration.isPenetration() && ((SexAreaPenetration)penetration).isTakesVirginity())) {
				// Pen name
				SexAreaPenetration pen = Main.sex.getFirstOngoingSexAreaPenetration(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH);
				GameCharacter characterPenetrating = Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH, pen).iterator().next();
				sb.append(UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrating,
						"虽然[npc.sheIs]是深度睡眠者，但被[npc2.namePos]的"+pen.getName(characterPenetrating)+"深深插进喉咙，窒息感把[npc.Name]弄醒了。"));
				
			} else {
				GameCharacter dom = Main.sex.getDominantParticipants(false).keySet().iterator().next();
				sb.append(UtilText.parse(Main.sex.getCharacterPerformingAction(), dom,
						"虽然[npc.sheIs]是深度睡眠者，但[npc2.namePos]的行动足够有力，将[npc.herHim]从沉睡中唤醒了。"));
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case SUB_EAGER:
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						sb.append("[npc.Name]睁开眼睛，看看发生了什么，而后兴奋[npc.moan]，继续开心地挨操。");
					} else {
						sb.append("[npc.Name]睁开眼睛，看看发生了什么，而后兴奋[npc.moan]，继续开心地说，[npc.speech(太棒了！操我吧！)]");
					}
					break;
				case SUB_RESISTING:
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						sb.append("[npc.Name]睁开眼睛，看看发生了什么，而后羞愤呼气，急切抵抗起来。");
					} else {
						sb.append("[npc.Name]睁开眼睛，看看发生了什么，而后羞愤呼气，恐惧地"+(Main.sex.getCharacterPerformingAction().isFeminine()?"尖叫":"大喊")+"着，");
						sb.append("[npc.speechNoExtraEffects(干你娘的？！不要啊！从我里面出去！)]");
					}
					break;
				case SUB_NORMAL:
				case DOM_GENTLE:
				case DOM_NORMAL:
				case DOM_ROUGH:
				default:
					if(Main.sex.getCharacterPerformingAction().isMute()) {
						sb.append("[npc.Name]睁开眼睛，看看发生了什么，而后[npc.moan]，迅速顺服地挨操。");
					} else {
						sb.append("[npc.Name]睁开眼睛，看看发生了什么，而后发出[npc.moan]，叫道，[npc.speech(太棒了！操我吧！)]");
					}
					break;
			}
			
			return sb.toString();
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.removeCharacterImmobilised(Main.sex.getCharacterPerformingAction(), ImmobilisationType.SLEEP);
			Main.sex.removeCharacterWoken(Main.sex.getCharacterPerformingAction());
			Main.sex.getCharacterPerformingAction().wakeUp();
			
			// If not attracted to person fucking them, immediately set to resisting:
			if(!Main.sex.getCharacterPerformingAction().isDoll()
					&& Main.game.isNonConEnabled()
					&& !Main.sex.getCharacterPerformingAction().isAttractedTo(Main.game.getPlayer())
					&& !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
				Main.game.getTextEndStringBuilder().append(Main.sex.getCharacterPerformingAction().incrementAffection(Main.game.getPlayer(), -100));
				Main.sex.getCharacterPerformingAction().setLust(0);
			}
			
			return "";
		}
	};
	
	// Ovipositor actions:

//	public static final SexAction OVIPOSITOR_PENIS_EGG_LAYING_BLOCKED = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FIVE_EXTREME,
//			ArousalIncrease.FIVE_EXTREME,
//			CorruptionLevel.ZERO_PURE,
//			null,
//			SexParticipantType.NORMAL) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS);
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
//					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
//				return false;
//			}
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			return !Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()
//					&& !OVIPOSITOR_PENIS_EGG_LAYING.isBaseRequirementsMet();
//		}
//		@Override
//		public String getActionTitle() {
//			return "Lay eggs";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"[npc.Name] cannot be impregnated, so you cannot lay eggs in [npc.herHim]!");
//			}
//
//			if(Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"Epona will consider egg-laying to be cheating, and so you're not able to do this!");
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			
//			if(!areaContacted.isOrifice()) {
//				switch((SexAreaPenetration)areaContacted) {
//					case FINGER:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a handjob from [npc.name]!");
//					case FOOT:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a [npc.footjob] from [npc.name]!");
//					case TAIL:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a tailjob from [npc.name]!");
//					case TENTACLE:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a tentaclejob from [npc.name]!");
//					case CLIT:
//					case PENIS:
//					case TONGUE:
//						break;
//				}
//			} else {
//				return UtilText.parse(getCharacterToBeEgged(),
//						(((SexAreaOrifice) areaContacted).isInternalOrifice()
//							?"You cannot lay eggs in [npc.namePos] "
//							:"You cannot lay eggs while fucking [npc.namePos] ")
//						+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//			}
//			return UtilText.parse(getCharacterToBeEgged(),
//					"You cannot lay eggs while fucking [npc.namePos] "+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//		}
//		@Override
//		public String getDescription() {
//			return "";
//		}
//		@Override
//		public Response toResponse() {
//			if(!isBaseRequirementsMet()) {
//				return null;
//			}
//			return convertToNullResponse();
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SEX;
//		}
//	};
	
	public static final SexAction OVIPOSITOR_PENIS_EGG_LAYING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.PENIS, getAreaToBeEgged());
		}
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor penis, an egg-laying vagina, and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().hasPenisIgnoreDildo()
					|| !Main.sex.getCharacterPerformingAction().hasPenisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
//					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).isEmpty()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(!areaContacted.isOrifice()) {
				return false;
			}
			
			boolean isPenetratingSuitableOrifice  = false;
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case NIPPLE:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
						break;
					case NIPPLE_CROTCH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
						break;
					case ANUS:
					case MOUTH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						break;
					case SPINNERET:
						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						return false;
					case VAGINA:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
						break;
				}
			}
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			try {
				switch((SexAreaOrifice)getAreaToBeEgged()) {
					case ANUS:
						return "产卵(肛门)";
					case MOUTH:
						return "产卵(腹部)";
					case NIPPLE: case NIPPLE_CROTCH:
						return "产卵(乳房)";
					case VAGINA:
						return "产卵(子宫)";
					case SPINNERET:
						return "产卵(丝囊)";
					case ARMPITS:
					case BREAST:
					case ASS:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return "";
				}
			} catch(Exception ex) {
				System.err.println("OVIPOSITOR_PENIS_EGG_LAYING getActionTitle() error - Area not found!");
			}
			return "产卵";
		}
		@Override
		public String getActionDescription() {
			String returnString = "决定在[npc2.name]体内产卵。";
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "将鸡巴尽可能深地插入[npc2.namePos]的[npc2.asshole]，用产卵器在[npc2.her]的肚子里产卵。";
					break;
				case MOUTH:
					returnString = "将鸡巴尽可能深地插入[npc2.namePos]的喉咙，用产卵器在[npc2.her]的肚子里产卵。";
					break;
				case NIPPLE:
					returnString = "将鸡巴尽可能深地插入[npc2.namePos][npc2.nipple+]，用产卵器在[npc2.her]的[npc2.breasts]里产卵。";
					break;
				case NIPPLE_CROTCH:
					returnString = "将鸡巴尽可能深地插入[npc2.namePos][npc2.crotchNipple+]，用产卵器在[npc2.her]的[npc2.crotchBoobs]里产卵。";
					break;
				case VAGINA:
					returnString = "将鸡巴尽可能深地插入[npc2.namePos][npc2.pussy+]，用产卵器在[npc2.her]的子宫里产卵。";
					break;
				case SPINNERET:
					returnString = "将鸡巴尽可能深地插入[npc2.namePos]的[npc2.spinneret]，用产卵器在[npc2.her]体内产卵。";
					break;
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.PENIS, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {//TODO test appending
				sb.append(UtilText.parse(Main.sex.getCharacterPerformingAction(),
						"<p style='text-align:center;'>[style.boldTerrible([npc.NamePos]的避孕套在产卵时破了！)]</p>"));
				Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS).setSealed(false);
				Main.sex.getCharacterPerformingAction().unequipClothingIntoVoid(Main.sex.getCharacterPerformingAction().getClothingInSlot(InventorySlot.PENIS), true, Main.sex.getCharacterPerformingAction());
			}
			
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
				areaEgged = "stomach";
			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
				areaEgged = "womb";
			}
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在"+areaEgged+"内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			
			return sb.toString();
		}
		@Override
		public String applyEndEffects(){
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

//	public static final SexAction OVIPOSITOR_CLIT_EGG_LAYING_BLOCKED = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FIVE_EXTREME,
//			ArousalIncrease.FIVE_EXTREME,
//			CorruptionLevel.ZERO_PURE,
//			null,
//			SexParticipantType.NORMAL) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT);
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			if(Main.sex.getCharacterPerformingAction().getVaginaClitorisSize()==ClitorisSize.ZERO_AVERAGE
//					|| !Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isPregnant()) {
//				return false;
//			}
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			return !Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty() && !OVIPOSITOR_CLIT_EGG_LAYING.isBaseRequirementsMet();
//		}
//		@Override
//		public String getActionTitle() {
//			return "Lay eggs (clit)";
//		}
//		@Override
//		public String getActionDescription() {
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"[npc.Name] cannot be impregnated, so you cannot lay eggs in [npc.herHim]!");
//			}
//
//			if(Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return UtilText.parse(getCharacterToBeEgged(),
//						"Epona will consider egg-laying to be cheating, and so you're not able to do this!");
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			
//			if(!areaContacted.isOrifice()) {
//				switch((SexAreaPenetration)areaContacted) {
//					case FINGER:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while being fingered by [npc.name]!");
//					case FOOT:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while receiving a [npc.footjob] from [npc.name]!");
//					case TAIL:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while being tail-fucked by [npc.name]!");
//					case TENTACLE:
//						return UtilText.parse(getCharacterToBeEgged(),
//								"You cannot lay eggs while being tentacle-fucked by [npc.name]!");
//					case CLIT:
//					case PENIS:
//					case TONGUE:
//						break;
//				}
//			} else {
//				return UtilText.parse(getCharacterToBeEgged(),
//						(((SexAreaOrifice) areaContacted).isInternalOrifice()
//							?"You cannot lay eggs in [npc.namePos] "
//							:"You cannot lay eggs while fucking [npc.namePos] ")
//						+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//			}
//			return UtilText.parse(getCharacterToBeEgged(),
//					"You cannot lay eggs while fucking [npc.namePos] "+areaContacted.getName(getCharacterToBeEgged(), true)+"!");
//		}
//		@Override
//		public String getDescription() {
//			return "";
//		}
//		@Override
//		public Response toResponse() {
//			if(!isBaseRequirementsMet()) {
//				return null;
//			}
//			return convertToNullResponse();
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SEX;
//		}
//	};
	
	public static final SexAction OVIPOSITOR_CLIT_EGG_LAYING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.CLIT, getAreaToBeEgged());
		}
		private SexAreaInterface getAreaToBeEgged() {
			return Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).get(0);
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT, getCharacterToBeEgged()).get(0);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor clit, an egg-laying vagina, and for the eggs to be fertilised
			if(Main.sex.getCharacterPerformingAction().getVaginaClitorisSize()==ClitorisSize.ZERO_AVERAGE
					|| !Main.sex.getCharacterPerformingAction().hasClitorisModifier(PenetrationModifier.OVIPOSITOR)
//					|| !Main.sex.getCharacterPerformingAction().hasVagina()
//					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			
			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.CLIT).isEmpty()) {
				return false;
			}
			
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			
			SexAreaInterface areaContacted = getAreaToBeEgged();
			if(!areaContacted.isOrifice()) {
				return false;
			}
			
			boolean isPenetratingSuitableOrifice  = false;
			if(areaContacted.isOrifice()) {
				switch((SexAreaOrifice)areaContacted) {
					case ARMPITS:
					case ASS:
					case THIGHS:
					case BREAST:
					case BREAST_CROTCH:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return false;
					case NIPPLE:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
						break;
					case NIPPLE_CROTCH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
						break;
					case ANUS:
					case MOUTH:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						break;
					case SPINNERET:
						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
						return false;
					case VAGINA:
						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
						break;
				}
			}
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			try {
				switch((SexAreaOrifice)getAreaToBeEgged()) {
					case ANUS:
						return "产卵(阴蒂-肛门)";
					case MOUTH:
						return "产卵(阴蒂-腹部)";
					case NIPPLE: case NIPPLE_CROTCH:
						return "产卵(阴蒂-乳房)";
					case VAGINA:
						return "产卵(阴蒂-子宫)";
					case SPINNERET:
						return "产卵(阴蒂-丝囊)";
					case ARMPITS:
					case BREAST:
					case ASS:
					case BREAST_CROTCH:
					case THIGHS:
					case URETHRA_PENIS:
					case URETHRA_VAGINA:
						return "";
				}
			} catch(Exception ex) {
				System.err.println("OVIPOSITOR_CLIT_EGG_LAYING getActionTitle() error - Area not found!");
			}
			return "产卵(阴蒂)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "决定在[npc2.name]体内产卵。";
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			SexAreaInterface areaContacted = getAreaToBeEgged();
			switch((SexAreaOrifice)areaContacted) {
				case ANUS:
					returnString = "将阴蒂尽可能深地插入[npc2.namePos]的[npc2.asshole]，用产卵器在[npc2.her]肚子里产卵。";
					break;
				case MOUTH:
					returnString = "将阴蒂尽可能深地插入[npc2.namePos]的喉咙，用产卵器在[npc2.her]肚子里产卵。";
					break;
				case NIPPLE:
					returnString = "将阴蒂尽可能深地插入[npc2.namePos][npc2.nipple+]，用产卵器在[npc2.her]的[npc2.breasts]里产卵。";
					break;
				case NIPPLE_CROTCH:
					returnString = "将阴蒂尽可能深地插入[npc2.namePos][npc2.crotchNipple+]，用产卵器在[npc2.her]的[npc2.crotchBoobs]里产卵。";
					break;
				case VAGINA:
					returnString = "将阴蒂尽可能深地插入[npc2.namePos][npc2.pussy+]，用产卵器在[npc2.her]的子宫里产卵。";
					break;
				case SPINNERET:
					returnString = "将阴蒂尽可能深地插入[npc2.namePos]的[npc2.spinneret]，用产卵器在[npc2.herHim]体内产卵。";
					break;
				case ARMPITS:
				case ASS:
				case BREAST:
				case BREAST_CROTCH:
				case THIGHS:
				case URETHRA_PENIS:
				case URETHRA_VAGINA:
					break;
			}
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.CLIT, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
				areaEgged = "stomach";
			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
				areaEgged = "womb";
			}
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在"+areaEgged+"内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};
	
	// Need individual actions for tail egg laying, as it's possible for a character with multiple tails to be penetrating multiple orifices at once
	
	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_VAGINA = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.VAGINA;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null && !getCharacterToBeEgged().isPregnant();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾巴-子宫)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"将尾巴尽可能深地插入[npc2.namePos][npc2.pussy+]，用产卵器在[npc2.her]的子宫里产卵。");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在[npc.her]的子宫内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_ANUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.ANUS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾巴-肛门)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"将尾巴尽可能深地插入[npc2.namePos]的[npc2.asshole]，然后使用产卵能力将卵产在[npc2.her]的腹部中。");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在肚子里产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.MOUTH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾口)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"将尾巴尽可能深地插入[npc2.namePos]的喉咙，用产卵器在[npc2.her]肚子里产卵。");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在肚子里产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_NIPPLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾乳)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"将尾巴尽可能深地插入[npc2.namePos][npc2.nipple+]，用产卵器在[npc2.her]的[npc2.breasts]里产卵。");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在[npc.her][npc.breasts+]内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_NIPPLE_CROTCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.NORMAL) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE_CROTCH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaOrifice areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter(areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾胯乳)";
		}
		@Override
		public String getActionDescription() {
			GameCharacter characterPenetrated = getCharacterToBeEgged();
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated,
					"将尾巴尽可能深地插入[npc2.namePos][npc2.crotchNipple+]，用产卵器在[npc2.her]的[npc2.crotchBoobs]里产卵。");
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在[npc.her][npc.crotchBoobs+]内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
	};
	
//	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FOUR_HIGH,
//			ArousalIncrease.THREE_NORMAL,
//			CorruptionLevel.TWO_HORNY,
//			null,
//			SexParticipantType.NORMAL) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			// To lay eggs, the orgasming character requires an ovipositor tail, an egg-laying vagina, and for the eggs to be fertilised
//			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
////					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
//				return false;
//			}
//			
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL).isEmpty()) {
//				return false;
//			}
//			
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return false;
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			if(!areaContacted.isOrifice()) {
//				return false;
//			}
//			
//			boolean isPenetratingSuitableOrifice  = false;
//			if(areaContacted.isOrifice()) {
//				switch((SexAreaOrifice)areaContacted) {
//					case ARMPITS:
//					case ASS:
//					case THIGHS:
//					case BREAST:
//					case BREAST_CROTCH:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return false;
//					case NIPPLE:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
//						break;
//					case NIPPLE_CROTCH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
//						break;
//					case ANUS:
//					case MOUTH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						break;
//					case SPINNERET:
//						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
////						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						return false;
//					case VAGINA:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
//						break;
//				}
//			}
//			if(!isPenetratingSuitableOrifice) {
//				return false;
//			}
//			
//			return true;
//		}
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.LOW;
//			}
//			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
//				return SexActionPriority.HIGH;
//			}
//			return SexActionPriority.NORMAL;
//		}
//		@Override
//		public String getActionTitle() {
//			try {
//				switch((SexAreaOrifice)getAreaToBeEgged()) {
//					case ANUS:
//						return "Lay eggs (tail-anal)";
//					case MOUTH:
//						return "Lay eggs (tail-stomach)";
//					case NIPPLE: case NIPPLE_CROTCH:
//						return "Lay eggs (tail-breasts)";
//					case VAGINA:
//						return "Lay eggs (tail-womb)";
//					case SPINNERET:
//						return "Lay eggs (tail-spinneret)";
//					case ARMPITS:
//					case BREAST:
//					case ASS:
//					case BREAST_CROTCH:
//					case THIGHS:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return "";
//				}
//			} catch(Exception ex) {
//				System.err.println("OVIPOSITOR_TAIL_EGG_LAYING getActionTitle() error - Area not found!");
//			}
//			return "Lay eggs (tail)";
//		}
//		@Override
//		public String getActionDescription() {
//			String returnString = "Decide to lay your eggs in [npc2.name].";
//			GameCharacter characterPenetrated = getCharacterToBeEgged();
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			switch((SexAreaOrifice)areaContacted) {
//				case ANUS:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.asshole], before using its ovipositor ability to lay your eggs in [npc2.her] stomach.";
//					break;
//				case MOUTH:
//					returnString = "Ram your tail as deep as possible down [npc2.namePos] throat, before using its ovipositor ability to lay your eggs in [npc2.her] stomach.";
//					break;
//				case NIPPLE:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.nipple+], before using its ovipositor ability to lay your eggs in [npc2.her] [npc2.breasts].";
//					break;
//				case NIPPLE_CROTCH:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.crotchNipple+], before using its ovipositor ability to lay your eggs in [npc2.her] [npc2.crotchBoobs].";
//					break;
//				case VAGINA:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.pussy+], before using its ovipositor ability to lay your eggs in [npc2.her] womb.";
//					break;
//				case SPINNERET:
//					returnString = "Ram your tail as deep as possible into [npc2.namePos] [npc2.spinneret], before using its ovipositor ability to lay your eggs inside of [npc2.herHim].";
//					break;
//				case ARMPITS:
//				case ASS:
//				case BREAST:
//				case BREAST_CROTCH:
//				case THIGHS:
//				case URETHRA_PENIS:
//				case URETHRA_VAGINA:
//					break;
//			}
//			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
//		}
//		@Override
//		public String getDescription() {
//			return eggLayingTargetDescription(SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
//		}
//		@Override
//		public String applyPreParsingEffects() {
//			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
//			return "";
//		}
//		@Override
//		public void applyEffects() {
//			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
//			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
//			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
//				areaEgged = "stomach";
//			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
//				areaEgged = "womb";
//			}
//			Main.game.getTextEndStringBuilder().append(
//					"<p style='text-align:center;'>"
//							+ UtilText.parse(getCharacterToBeEgged(),
//									"[style.italicsYellowLight([npc.Name] [npc.has] had "+Util.intToString(eggCount)+" egg"+(eggCount>1?"s":"")+" implanted in [npc.her] "+areaEgged+"!)]")
//					+ "</p>");
//			
//			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
//		}
//		@Override
//		public String applyEndEffects() {
//			Main.sex.setCharacterLayingEggs(null);
//			return "";
//		}
//		@Override
//		public List<AbstractFetish> getFetishes(GameCharacter character) {
//			if(character.equals(Main.sex.getCharacterPerformingAction())) {
//				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
//			} else {
//				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
//			}
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SEX;
//		}
//	};
	
	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_VAGINA = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.VAGINA;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾巴-子宫)(自己)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "将尾巴尽可能深地插入自己[npc.pussy+]，用产卵器在子宫里产卵。";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在[npc.her]的子宫内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_ANUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.ANUS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾肛)(自己)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "将尾巴尽可能深地插入自己的[npc.asshole]，用产卵器在肚子里产卵。";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在肚子里产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_MOUTH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.MOUTH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾口)(自己)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "将尾巴尽可能深地插入自己的喉咙，用产卵器在肚子里产卵。";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在肚子里产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_NIPPLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾乳)(自己)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "将尾巴尽可能深地插入自己[npc.nipple+]，用产卵器在[npc.breasts]里产卵。";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在[npc.her][npc.breasts+]内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};

	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF_NIPPLE_CROTCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			null,
			SexParticipantType.SELF) {
		private GameCharacter getCharacterToBeEgged() {
			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
		}
		private SexAreaOrifice getAreaToBeEgged() {
			return SexAreaOrifice.NIPPLE_CROTCH;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			// To lay eggs, the orgasming character requires an ovipositor tail and for the eggs to be fertilised
			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
				return false;
			}
			if(getCharacterToBeEgged()==null) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
				return false;
			}
			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
				return false;
			}
			SexAreaInterface areaContacted = getAreaToBeEgged();
			boolean isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
			if(!isPenetratingSuitableOrifice) {
				return false;
			}
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.LOW;
			}
			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			return "产卵(尾胯乳)(自己)";
		}
		@Override
		public String getActionDescription() {
			String returnString = "将尾巴尽可能深地插入自己[npc.crotchNipple+]，用产卵器在[npc.crotchBoobs]里产卵。";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), returnString);
		}
		@Override
		public String getDescription() {
			return eggLayingTargetDescription(SexAreaPenetration.TAIL, getAreaToBeEgged(), Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
			return "";
		}
		@Override
		public String applyEffectsString() {
			StringBuilder sb = new StringBuilder();
			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
			sb.append(
					"<p style='text-align:center;'>"
							+ UtilText.parse(getCharacterToBeEgged(),
									"[style.italicsYellowLight([npc.Name]在[npc.her][npc.crotchBoobs+]内产下了"+Util.intToString(eggCount)+"枚卵！)]")
					+ "</p>");
			
			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
			return sb.toString();
		}
		@Override
		public String applyEndEffects() {
			Main.sex.setCharacterLayingEggs(null);
			return "";
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.equals(Main.sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			}
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SELF;
		}
	};
	
//	public static final SexAction OVIPOSITOR_TAIL_EGG_LAYING_SELF = new SexAction(
//			SexActionType.ONGOING,
//			ArousalIncrease.FOUR_HIGH,
//			ArousalIncrease.THREE_NORMAL,
//			CorruptionLevel.TWO_HORNY,
//			null,
//			SexParticipantType.SELF) {
//		private GameCharacter getCharacterToBeEgged() {
//			return GenericActions.getCharacterToBeEgged(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), SexAreaPenetration.TAIL, getAreaToBeEgged());
//		}
//		private SexAreaInterface getAreaToBeEgged() {
//			return Main.sex.getOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL, getCharacterToBeEgged()).get(0);
//		}
//		@Override
//		public boolean isBaseRequirementsMet() {
//			// To lay eggs, the orgasming character requires an ovipositor tail, an egg-laying vagina, and for the eggs to be fertilised
//			if(!Main.sex.getCharacterPerformingAction().getTailType().isOvipositor()
////					|| !Main.sex.getCharacterPerformingAction().hasVagina()
////					|| !Main.sex.getCharacterPerformingAction().isVaginaEggLayer()
//					|| !Main.sex.getCharacterPerformingAction().isVisiblyPregnant()) {
//				return false;
//			}
//			
//			if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.TAIL).isEmpty()) {
//				return false;
//			}
//			
//			if(!getCharacterToBeEgged().isAbleToBeEgged()) {
//				return false;
//			}
//			if(!getCharacterToBeEgged().isAbleToBeImpregnated()
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
//					|| Main.sex.getCharacterPerformingAction().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY)) {
//				return false;
//			}
//			
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			if(!areaContacted.isOrifice()) {
//				return false;
//			}
//			
//			boolean isPenetratingSuitableOrifice  = false;
//			if(areaContacted.isOrifice()) {
//				switch((SexAreaOrifice)areaContacted) {
//					case ARMPITS:
//					case ASS:
//					case THIGHS:
//					case BREAST:
//					case BREAST_CROTCH:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return false;
//					case NIPPLE:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastAbleToIncubateEggs();
//						break;
//					case NIPPLE_CROTCH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && getCharacterToBeEgged().isBreastCrotchAbleToIncubateEggs();
//						break;
//					case ANUS:
//					case MOUTH:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						break;
//					case SPINNERET:
//						// Spinneret transformation restrictions are too complex to handle, so just prevent ability to lay eggs in it.
////						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null;
//						return false;
//					case VAGINA:
//						isPenetratingSuitableOrifice = getCharacterToBeEgged().getIncubationLitter((SexAreaOrifice) areaContacted)==null && !getCharacterToBeEgged().isPregnant();
//						break;
//				}
//			}
//			if(!isPenetratingSuitableOrifice) {
//				return false;
//			}
//			
//			return true;
//		}
//		@Override
//		public SexActionPriority getPriority() {
//			if(Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_IMPREGNATION).isNegative() || Main.sex.isInForeplay(Main.sex.getCharacterPerformingAction())) {
//				return SexActionPriority.LOW;
//			}
//			if(Math.random()<0.66f || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_IMPREGNATION)) {
//				return SexActionPriority.HIGH;
//			}
//			return SexActionPriority.NORMAL;
//		}
//		@Override
//		public String getActionTitle() {
//			try {
//				switch((SexAreaOrifice)getAreaToBeEgged()) {
//					case ANUS:
//						return "Lay eggs (self tail-anal)";
//					case MOUTH:
//						return "Lay eggs (self tail-stomach)";
//					case NIPPLE: case NIPPLE_CROTCH:
//						return "Lay eggs (tail-breasts)";
//					case VAGINA:
//						return "Lay eggs (self tail-womb)";
//					case SPINNERET:
//						return "Lay eggs (self tail-spinneret)";
//					case ARMPITS:
//					case BREAST:
//					case ASS:
//					case BREAST_CROTCH:
//					case THIGHS:
//					case URETHRA_PENIS:
//					case URETHRA_VAGINA:
//						return "";
//				}
//			} catch(Exception ex) {
//				System.err.println("OVIPOSITOR_TAIL_EGG_LAYING_SELF getActionTitle() error - Area not found!");
//			}
//			return "Lay eggs (self tail)";
//		}
//		@Override
//		public String getActionDescription() {
//			String returnString = "Decide to lay your eggs in yourself.";
//			GameCharacter characterPenetrated = getCharacterToBeEgged();
//			SexAreaInterface areaContacted = getAreaToBeEgged();
//			switch((SexAreaOrifice)areaContacted) {
//				case ANUS:
//					returnString = "Ram your tail as deep as possible into your own [npc2.asshole], before using its ovipositor ability to lay your eggs in your stomach.";
//					break;
//				case MOUTH:
//					returnString = "Ram your tail as deep as possible down your own throat, before using its ovipositor ability to lay your eggs in your stomach.";
//					break;
//				case NIPPLE:
//					returnString = "Ram your tail as deep as possible into your own [npc2.nipple+], before using its ovipositor ability to lay your eggs in your [npc2.breasts].";
//					break;
//				case NIPPLE_CROTCH:
//					returnString = "Ram your tail as deep as possible into your own [npc2.crotchNipple+], before using its ovipositor ability to lay your eggs in your [npc2.crotchBoobs].";
//					break;
//				case VAGINA:
//					returnString = "Ram your tail as deep as possible into your own [npc2.pussy+], before using its ovipositor ability to lay your eggs in your womb.";
//					break;
//				case SPINNERET:
//					returnString = "Ram your tail as deep as possible into your own [npc2.spinneret], before using its ovipositor ability to lay your eggs inside of yourself.";
//					break;
//				case ARMPITS:
//				case ASS:
//				case BREAST:
//				case BREAST_CROTCH:
//				case THIGHS:
//				case URETHRA_PENIS:
//				case URETHRA_VAGINA:
//					break;
//			}
//			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
//		}
//		@Override
//		public String getDescription() {
//			return eggLayingTargetDescription(SexAreaPenetration.TAIL, Main.sex.getCharacterPerformingAction(), getCharacterToBeEgged());
//		}
//		@Override
//		public String applyPreParsingEffects() {
//			Main.sex.setCharacterLayingEggs(Main.sex.getCharacterPerformingAction());
//			return "";
//		}
//		@Override
//		public void applyEffects() {
//			int eggCount = Main.sex.getCharacterPerformingAction().getPregnantLitter().getTotalLitterCount();
//			String areaEgged = getAreaToBeEgged().getName(getCharacterToBeEgged(), true);
//			if(getAreaToBeEgged()==SexAreaOrifice.ANUS || getAreaToBeEgged()==SexAreaOrifice.MOUTH) {
//				areaEgged = "stomach";
//			} else if(getAreaToBeEgged()==SexAreaOrifice.VAGINA) {
//				areaEgged = "womb";
//			}
//			Main.game.getTextEndStringBuilder().append(
//					"<p style='text-align:center;'>"
//							+ UtilText.parse(getCharacterToBeEgged(),
//									"[style.italicsYellowLight([npc.Name] [npc.has] had "+Util.intToString(eggCount)+" egg"+(eggCount>1?"s":"")+" implanted in [npc.her] "+areaEgged+"!)]")
//					+ "</p>");
//			
//			Main.sex.getCharacterPerformingAction().implantPregnantLitter(getCharacterToBeEgged(), (SexAreaOrifice) getAreaToBeEgged());
//		}
//		@Override
//		public String applyEndEffects() {
//			Main.sex.setCharacterLayingEggs(null);
//			return "";
//		}
//		@Override
//		public List<AbstractFetish> getFetishes(GameCharacter character) {
//			if(character.equals(Main.sex.getCharacterPerformingAction())) {
//				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
//			} else {
//				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
//			}
//		}
//		@Override
//		public SexActionCategory getCategory() {
//			return SexActionCategory.SELF;
//		}
//	};
}
