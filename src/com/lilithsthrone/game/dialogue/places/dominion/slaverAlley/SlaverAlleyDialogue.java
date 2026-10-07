package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Finch;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Sean;
import com.lilithsthrone.game.character.npc.dominion.SlaveInStocks;
import com.lilithsthrone.game.character.npc.misc.GenericFemaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.misc.SlaveForSale;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.DominionPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public class SlaverAlleyDialogue {

	private static NPC biddingNPC = null;
	private static int biddingPrice = 0;
	private static int biddingRounds = 0;
	private static int biddingRoundsTotal = 1;
	private static boolean playerBidLeader = false;
	private static SlaveAuctionBidder currentRivalBidder = null;
	
	private static NPC stocksSlaveTargeted = null;

	private static List<GameCharacter> randomSexPartners = null;
	
	public static void dailyReset() {
		for(String finchSlaveId : new ArrayList<>(Main.game.getNpc(Finch.class).getSlavesOwned())) {
			if(Main.game.isCharacterExisting(finchSlaveId)) {
				Main.game.banishNPC(finchSlaveId);
			}
		}
		Main.game.getNpc(Finch.class).removeAllSlaves();
		
		// Female stall:
		Gender[] genders = new Gender[] {Gender.F_V_B_FEMALE, Gender.F_V_B_FEMALE, Gender.F_P_V_B_FUTANARI};
		for (Gender gender : genders) {
			NPC slave = new SlaveForSale(gender, false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_FEMALES, true);
			slave.resetInventory(true);
			slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_GOLD, false), true, Main.game.getNpc(Finch.class));
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsFemale(slave);
		}

		// Male stall:
		genders = new Gender[] {Gender.M_P_MALE, Gender.M_P_MALE, Gender.M_P_MALE};
		for (Gender gender : genders) {
			NPC slave = new SlaveForSale(gender, false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_MALES, true);
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsMale(slave);
		}

		// Anal stall:
		for (int i=0; i<3; i++) {
			NPC slave = new SlaveForSale(Gender.getGenderFromUserPreferences(false, false), false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_ANAL, true);
			if (i==0) {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug"), false), true, Main.game.getNpc(Finch.class));
			} else if(i==1) {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_jewel"), false), true, Main.game.getNpc(Finch.class));
			} else {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"), false), true, Main.game.getNpc(Finch.class));
			}
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsAnal(slave);
			Main.game.getPlayer().setKnowsCharacterArea(CoverableArea.ANUS, slave, true);
		}

		// Vaginal stall:
		for (int i=0; i<3; i++) {
			NPC slave = new SlaveForSale(Gender.getGenderFromUserPreferences(true, false), false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_VAGINAL, true);
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsVaginal(slave);
		}

		// Oral stall:
		for (int i=0; i<3; i++) {
			NPC slave = new SlaveForSale(Gender.getGenderFromUserPreferences(false, false), false);
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_STALL_ORAL, true);
			if (Math.random()<0.5f) {
				slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", false), true, Main.game.getNpc(Finch.class));
			}
			Main.game.getNpc(Finch.class).addSlave(slave);
			
			applySlaveEffectsOral(slave);
		}
	}
	
	private static void applySlaveEffectsFemale(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_SUBMISSIVE);
		slave.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		slave.addFetish(Fetish.FETISH_ORAL_GIVING);
		slave.removePersonalityTraits(PersonalityCategory.SPEECH);
		slave.removePersonalityTrait(PersonalityTrait.SHY);
		if (Math.random() < 0.5f) {
			slave.addPersonalityTrait(PersonalityTrait.LEWD);
		}
		slave.setObedience(100);
	}
	
	private static void applySlaveEffectsMale(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_DOMINANT);
		slave.addFetish(Fetish.FETISH_CUM_STUD);
		slave.removePersonalityTrait(PersonalityTrait.COWARDLY);
		if (Math.random() < 0.5f) {
			slave.addPersonalityTrait(PersonalityTrait.BRAVE);
		}
		slave.setObedience(75);
	}
	
	private static void applySlaveEffectsAnal(GameCharacter slave) {
		slave.setAssWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
		slave.setAssBleached(true);
		slave.setAssCapacity(Util.random.nextInt((int) Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue(false)), true);
		slave.setAssVirgin(false);
		
		slave.addFetish(Fetish.FETISH_ANAL_GIVING);
		slave.addFetish(Fetish.FETISH_ANAL_RECEIVING);
		slave.setObedience(75);
	}

	private static void applySlaveEffectsVaginal(GameCharacter slave) {
		slave.setVaginaWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
		slave.setVaginaCapacity(Util.random.nextInt((int) Capacity.ONE_EXTREMELY_TIGHT.getMaximumValue(false)), true);
		slave.setVaginaVirgin(true);
		
		slave.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		slave.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		slave.setObedience(75);
	}
	
	private static void applySlaveEffectsOral(GameCharacter slave) {
		slave.setFaceWetness(Util.randomItemFrom(Util.newArrayListOfValues(Wetness.FOUR_SLIMY, Wetness.FIVE_SLOPPY, Wetness.SIX_SOPPING_WET, Wetness.SEVEN_DROOLING)).getValue());
		slave.setFaceCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue(), true);
		slave.setFaceElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		slave.setLipSize(LipSize.FOUR_HUGE.getValue());
		slave.setFaceVirgin(false);

		slave.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		slave.addFetish(Fetish.FETISH_ORAL_GIVING);
		slave.setObedience(75);
	}
	
	private static boolean slavesInStocksPresent() {
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if((npc instanceof SlaveInStocks) && !Main.game.getPlayer().getCompanions().contains(npc)) {
				return true;
			}
		}
		return false;
	}
	
	private static void banishSlavesInStocks() {
		List<NPC> npcsToBanish = new ArrayList<>();
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if((npc instanceof SlaveInStocks) && !Main.game.getPlayer().getCompanions().contains(npc)) {
				npcsToBanish.add(npc);
			}
		}
		for(NPC npc : npcsToBanish) {
			Main.game.banishNPC(npc);
		}
	}
	
	
	public static void stocksUpdate() {
		float chanceToBeUsed = (12 - Main.game.getHourOfDay()%12)/12f;
		for(NPC npc : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))) {
			if((npc instanceof SlaveInStocks) && !Main.game.getPlayer().getCompanions().contains(npc)) {
				if (Math.random()<chanceToBeUsed) {
					if (!Main.game.getCharactersPresent().contains(npc)) {
						Gender gender = Gender.getGenderFromUserPreferences(false, true);
						
						Map<AbstractSubspecies, Integer> availableRaces = AbstractSubspecies.getGenericSexPartnerSubspeciesMap(gender);
						
						AbstractSubspecies subspecies = Subspecies.HUMAN;
						AbstractSubspecies halfDemonSubspecies = null;
						if (!availableRaces.isEmpty()) {
							subspecies = Util.getRandomObjectFromWeightedMap(availableRaces);
						}
						if (Math.random()<Main.getProperties().halfDemonSpawnRate) {
							halfDemonSubspecies = subspecies;
							subspecies = Subspecies.HALF_DEMON;
						}
						
						if (npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL)) {
							npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
						}
						if (npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL)) {
							npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
						}
						if (npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL) && npc.hasVagina()) {
							npc.calculateGenericSexEffects(false, true, null, subspecies, halfDemonSubspecies, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.NO_DESCRIPTION_NEEDED);
						}
					}
				}
			}
		}
	}
	
	public static void stocksReset(){
		banishSlavesInStocks();
		
		for(int i=0; i<4; i++) {
			SlaveInStocks slave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
			try {
				Main.game.addNPC(slave, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(Math.random()>0.5f) {
				Main.game.getNpc(GenericFemaleNPC.class).addSlave(slave);
			} else {
				Main.game.getNpc(GenericMaleNPC.class).addSlave(slave);	
			}
			slave.initSlavePermissions();
		}
		
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleySlavesFreed, false);
	}
	
	public static void setupBidding(NPC slaveToBidOn) {
		biddingNPC = slaveToBidOn;
		biddingPrice = (int) (biddingNPC.getValueAsSlave(true)*0.5f);
		biddingRoundsTotal = Util.random.nextInt(3)+1;
		biddingRounds = 0;
		playerBidLeader = false;
		currentRivalBidder = SlaveAuctionBidder.generateNewSlaveAuctionBidder(biddingNPC);
	}
	
	private static void increaseBid() {
		biddingRounds++;
		if(biddingRounds!=biddingRoundsTotal) {
			biddingPrice = (int) (biddingPrice * (1+(0.8f*Math.random())));
			playerBidLeader = false;
		}
	}
	
	private static String getImportRow(String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_SLAVE_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>导出</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static List<GameCharacter> generateRandomStocksPartners(GameCharacter target, boolean forceTwoPartners) {
		List<GameCharacter> partners = new ArrayList<>();

		Gender g = Gender.getGenderFromUserPreferences(false, false);
		if(!g.getGenderName().isHasPenis() && !g.getGenderName().isHasVagina()) {
			if(g.isFeminine()) {
				g = Gender.getGenderFromUserPreferences(true, false);
			} else {
				g = Gender.getGenderFromUserPreferences(false, true);
			}
		}
		NPC partner = new GenericSexualPartner(g, WorldType.SLAVER_ALLEY, Main.game.getPlayer().getLocation(), false);
		setupPartner(partner);
		try {
			Main.game.addNPC(partner, false);
			partners.add(partner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(Math.random()<0.25f || forceTwoPartners) { // Second partner:
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTwoPartners, true);
			partner = new GenericSexualPartner(Gender.getGenderFromUserPreferences(false, true), WorldType.SLAVER_ALLEY, Main.game.getPlayer().getLocation(), false);
			setupPartner(partner);
			try {
				Main.game.addNPC(partner, false);
				partners.add(0, partner); // Add at index 0, as this one will always be the one fucking from behind (as they have a penis).
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTwoPartners, false);
		}
		
		partners.get(0).removeFetish(Fetish.FETISH_ORAL_RECEIVING);
		partners.get(0).removeFetish(Fetish.FETISH_VAGINAL_GIVING);
		partners.get(0).removeFetish(Fetish.FETISH_ANAL_GIVING);
		List<AbstractFetish> fetishes = Util.newArrayListOfValues(
				target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
				&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
					?Fetish.FETISH_ORAL_RECEIVING
					:null,
				target.hasVagina()
				&& target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				&& partners.get(0).hasPenis()
					?Fetish.FETISH_VAGINAL_GIVING
					:null,
				Main.game.isAnalContentEnabled()
				&& target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
				&& partners.get(0).hasPenis()
					?Fetish.FETISH_ANAL_GIVING
					:null);
		if(!fetishes.isEmpty()) {
			partners.get(0).addFetish(Util.randomItemFrom(fetishes));
		}
		
		return partners;
	}
	
	private static void setupPartner(GameCharacter partner) {
		partner.setFetishDesire(Fetish.FETISH_EXHIBITIONIST, FetishDesire.THREE_LIKE);
		if(partner.hasPenis()) {
			partner.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			partner.setPenisVirgin(false);
		}
		if(partner.hasVagina()) {
			partner.setFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING, FetishDesire.THREE_LIKE);
			partner.setVaginaVirgin(false);
		}
		partner.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
		partner.setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE);
		partner.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
		partner.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	}

	private static void banishRandomSexPartners() {
		if(randomSexPartners!=null) {
			for(GameCharacter npc : randomSexPartners) {
				Main.game.banishNPC((NPC) npc);
			}
		}
	}
	
	private static void updateSeanPregnancyReactions() {
		Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Sean.class), true);
		if(isCompanionDialogue()) {
			getMainCompanion().setCharacterReactedToPregnancy(Main.game.getNpc(Sean.class), true);
		}
	}
	
	private static boolean isSeanOfferingDeal(GameCharacter target) {
		return target.isFeminine()
				&& target.hasVagina()
				&& target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				&& !target.isPregnant()
				&& !target.hasIncubationLitter(SexAreaOrifice.VAGINA);
	}
	
	private static void applyLockedUpEffects(boolean includeCompanion, boolean willingCompanion) {
		if(isCompanionDialogue() && includeCompanion && (willingCompanion || Main.game.isInvoluntaryNTREnabled())) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionInStocks, true);
			
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal,
					getMainCompanion().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()
						&& getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))
						&& isSeanOfferingDeal(getMainCompanion()));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
				Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), getMainCompanion(), false);
			}
			
			getMainCompanion().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
			
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionInStocks, false);
		}
		
		Main.game.getPlayer().setCaptive(true);
		Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
	}
	
	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static SexManagerInterface getRandomPartnerSexManager() {
		boolean twoPartners = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners);
		return new SexManagerDefault(
				SexPosition.STOCKS,
				!twoPartners
					?Util.newHashMapOfValues(new Value<>(randomSexPartners.get(0), randomSexPartners.get(0).hasFetish(Fetish.FETISH_ORAL_RECEIVING)?SexSlotStocks.RECEIVING_ORAL:SexSlotStocks.BEHIND_STOCKS))
					:Util.newHashMapOfValues(
								new Value<>(randomSexPartners.get(0), SexSlotStocks.BEHIND_STOCKS),
								new Value<>(randomSexPartners.get(1), SexSlotStocks.RECEIVING_ORAL)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
			@Override
			public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
				return Util.newHashMapOfValues(
						new Value<>(ImmobilisationType.STOCKS,
								Util.newHashMapOfValues(
										new Value<>(randomSexPartners.get(0),
												Util.newHashSetOfValues(Main.game.getPlayer())))));
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				return getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!twoPartners) {
					if(!character.isPlayer()) {
						if(character.hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
							if(character.hasPenis()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
							}
						} else if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
						} else if(character.hasFetish(Fetish.FETISH_VAGINAL_GIVING)) {
							return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
						} else {
							if(character.hasPenis()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
							}
						}
					}
				}
				return super.getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer()) {
					return SexControl.NONE;
				}
				return super.getSexControl(character);
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
				if(character.isPlayer()) {
					return false;
				}
				return super.isAbleToRemoveOthersClothing(character, clothing);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return !equippingCharacter.isPlayer();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return !character.isPlayer();
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				if(twoPartners) {
					return Util.newHashMapOfValues(
							new Value<>(randomSexPartners.get(0), Util.newArrayListOfValues(CoverableArea.PENIS)),
							new Value<>(randomSexPartners.get(1), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)));
				} else {
					return Util.newHashMapOfValues(
							new Value<>(randomSexPartners.get(0), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA)));
				}
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
		};
	}
	
	private static void generateCompanionSexParsingDescriptions() {
		SexType sexType;
		
		if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
			GameCharacter secondCharacter = randomSexPartners.get(1);
			if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(secondCharacter.hasPenis()) {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
				} else {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
				}
				
			} else {
				if(secondCharacter.hasPenis()) {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
				} else {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
				}
			}
			
			UtilText.addSpecialParsingString(
					secondCharacter.calculateGenericSexEffects(true, true, getMainCompanion(), sexType, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED),
					true);
		}
		
		GameCharacter character = randomSexPartners.get(0);
		if(character.hasFetish(Fetish.FETISH_ORAL_RECEIVING) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
			if(character.hasPenis()) {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
			} else {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
			}
			
		} else if(character.hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			
		} else if(character.hasFetish(Fetish.FETISH_VAGINAL_GIVING)) {
			sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			
		} else {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ASS);
				
			} else {
				if(character.hasPenis()) {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
				} else {
					sexType = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
				}
			}
		}
		
		UtilText.addSpecialParsingString(
				character.calculateGenericSexEffects(true, true, getMainCompanion(), sexType, GenericSexFlag.EXTENDED_DESCRIPTION_NEEDED),
				!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners));
	}
	
	public static final DialogueNode OUTSIDE = new DialogueNode("奴隶巷", "-", false) {
		
		@Override
		public int getSecondsPassed() {
			return DominionPlaces.TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "OUTSIDE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("奴隶巷", "经过大门进入奴隶巷。", PlaceType.SLAVER_ALLEY_ENTRANCE.getDialogue(false)){
					@Override
					public void effects() {
						// If Sean is not introduced and the stocks slaves are not present, then this must be the first time the player has entered slaver alley, in which case the slaves need to be initialised:
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.seanIntroduced) && !slavesInStocksPresent()) {
							dailyReset();
							stocksReset();
						}
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY = new DialogueNode("大门", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY"));

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_6_ADVERTISING) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTERS"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "退回到御城区的巷道里。", PlaceType.DOMINION_SLAVER_ALLEY.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_SLAVER_ALLEY, false);
					}
				};
				
			} else if(index==2 && Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_6_ADVERTISING) {
				return new Response("海报", "询问守卫是否能把海伦娜给你的海报贴上去。", GATEWAY_POSTER_PERMISSION);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION = new DialogueNode("大门", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
//				if(Main.game.getPlayer().getMoney()>=100) {
					return new Response("支付("+UtilText.formatAsMoney(100, "span")+")", "付上守卫要求的100火币。", GATEWAY_POSTER_PERMISSION_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_PAID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_END"));
							Main.game.getPlayer().removeItemByType(ItemType.ROLLED_UP_POSTERS);
							Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-100));
						}
					};
					
//				} else {
//					return new Response("Pay ("+UtilText.formatAsMoneyUncoloured(100, "span")+")", "You cannot afford to pay the guards the hundred flames they're asking for!", null);
//				}

			} else if(index==2) {
				if(Main.game.getPlayer().hasBreasts()) {
					if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)) {
						if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
							return new Response("被摸一把",
									"让守卫摸一把你露在外面的胸部，换取张贴海报的许可。",
									GATEWAY_POSTER_PERMISSION_GROPED,
									Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF, Fetish.FETISH_EXHIBITIONIST),
									CorruptionLevel.THREE_DIRTY,
									null,
									null,
									null) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_GROPED"));
									Main.game.getPlayer().incrementLust(15, false);
								}
							};
							
						} else {
							return new Response("秀出胸部",
									"将胸部完全展露在守卫面前，换取张贴海报的许可。",
									GATEWAY_POSTER_PERMISSION_GROPED,
									Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF, Fetish.FETISH_EXHIBITIONIST),
									CorruptionLevel.THREE_DIRTY,
									null,
									null,
									null) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_FLASH_BREASTS"));
									Main.game.getPlayer().incrementLust(5, false);
								}
							};
						}
						
					} else {
						return new Response("秀出胸部", "你无法将胸部完全展露出来。所以不能向守卫秀出胸部，以换取张贴海报的许可……", null);
					}
				}
				
			}
//			else if(index==0) {
//				return new Response("Leave", "Tell the guards that you'll back with the money later...", GATEWAY) {
//					@Override
//					public void effects() {
//						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_LEAVE"));
//					}
//				};
//			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION_GROPED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("海报", "获得守卫的许可后，可以张贴海报了。", GATEWAY_POSTER_PERMISSION_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_END"));
						Main.game.getPlayer().removeItemByType(ItemType.ROLLED_UP_POSTERS);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION_END = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("斯嘉丽", "搞明白斯嘉丽想干什么。", GATEWAY_POSTER_PERMISSION_END_RETURN);
			}
			return null;
		}
	};
	
	public static final DialogueNode GATEWAY_POSTER_PERMISSION_END_RETURN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "GATEWAY_POSTER_PERMISSION_END_RETURN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("返回", "跟着斯嘉丽返回海伦娜精品店。", ScarlettsShop.ROMANCE_RETURN_AFTER_POSTERS) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ALLEYWAY = new DialogueNode("小巷", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "ALLEYWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode DESERTED_ALLEYWAY = new DialogueNode("空无一人的小巷", "", false) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "DESERTED_ALLEYWAY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_FEMALE = new DialogueNode("女士风味", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_FEMALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("奴隶管理", "你还没有贩奴许可，无法购买或卖出奴隶……", null);
				}
				return new Response("奴隶管理", "进入奴隶管理界面", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_MALE = new DialogueNode("铁 & 钢", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_MALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("奴隶管理", "你还没有贩奴许可，无法购买或卖出奴隶……", null);
				}
				return new Response("奴隶管理", "进入奴隶管理界面", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_ANAL = new DialogueNode("请走后门", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_ANAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("奴隶管理", "你还没有贩奴许可，无法购买或卖出奴隶……", null);
				}
				return new Response("奴隶管理", "进入奴隶管理界面", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_VAGINAL = new DialogueNode("白百合", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_VAGINAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("奴隶管理", "你还没有贩奴许可，无法购买或卖出奴隶……", null);
				}
				return new Response("奴隶管理", "进入奴隶管理界面", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_ORAL = new DialogueNode("声音万岁(Viva Voce)", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_ORAL");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("奴隶管理", "你还没有贩奴许可，无法购买或卖出奴隶……", null);
				}
				return new Response("奴隶管理", "进入奴隶管理界面", MARKET_STALL_FEMALE) {
					@Override
					public boolean isTradeHighlight() {
						return true;
					}
					@Override
					public DialogueNode getNextDialogue() {
						CompanionManagement.initManagement(null, 0, null);
						return OccupantManagementDialogue.getSlaveryManagementDialogue(null, Main.game.getNpc(Finch.class));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_STATUE = new DialogueNode("堕天使雕像", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE"));
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.statueTruthRevealed)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE_TRUTH"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_STATUE_IGNORANCE"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_EXCLUSIVE = new DialogueNode("奴隶租赁商店", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_EXCLUSIVE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_BULK = new DialogueNode("财阀交易所", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_BULK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MARKET_STALL_CAFE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "进入咖啡店，找一张桌子坐下。", MARKET_STALL_CAFE_INTERIOR);
			}
			return null;
		}
	};

	private static Map<Integer, AbstractItemType> getCafeItems() {
		return Util.newHashMapOfValues(
				new Value<>(1, ItemType.getItemTypeFromId("innoxia_race_human_vanilla_water")),
				new Value<>(2, ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_juice_box")),
				new Value<>(3, ItemType.getItemTypeFromId("innoxia_race_rabbit_bunny_juice")),
				new Value<>(4, ItemType.getItemTypeFromId("innoxia_race_squirrel_squirrel_java")),
				new Value<>(6, ItemType.getItemTypeFromId("innoxia_race_rabbit_bunny_carrot_cake")),
				new Value<>(7, ItemType.getItemTypeFromId("innoxia_race_rat_brown_rats_burger")),
				new Value<>(8, ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_salad")));
	}
	
	public static final DialogueNode MARKET_STALL_CAFE_INTERIOR = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNonCompanionCharactersPresent().isEmpty()) {
				NPC slave = new SlaveForSale(
						Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2 || Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3
							?Gender.M_P_MALE
							:Gender.F_V_B_FEMALE,
						false,
						false);
				try {
					Main.game.addNPC(slave, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				slave.setLocation(Main.game.getPlayer(), true);
				slave.removePersonalityTrait(PersonalityTrait.MUTE);
				slave.removePersonalityTrait(PersonalityTrait.STUTTER);
				slave.removePersonalityTrait(PersonalityTrait.SLOVENLY);
				slave.setSexualOrientation(SexualOrientation.AMBIPHILIC);
				for(AbstractFetish fetish : Fetish.allFetishes) {
					if(slave.getFetishDesire(fetish).isNegative()) {
						slave.setFetishDesire(fetish, FetishDesire.TWO_NEUTRAL); // Remove all negative fetishes to make sure they don't start hating sex scenes
					}
				}
				Main.game.getNpc(Finch.class).addSlave(slave);
				
				if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE) { //Oral:
					applySlaveEffectsOral(slave);
					slave.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.METALLIC, PresetColour.COVERING_GOLD, false, PresetColour.COVERING_GOLD, false));
					slave.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					slave.addPersonalityTrait(PersonalityTrait.SHY);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_PURPLE_DARK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_PURPLE_DARK, false), true, slave);
					
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_dress", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_headpiece", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_sleeves", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_stockings", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_maid_heels", PresetColour.CLOTHING_BLUE_LIGHT, false), true, slave);
					
					slave.setPiercedEar(true);
					slave.setPiercedTongue(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", PresetColour.CLOTHING_GOLD, false), true, slave);
					slave.setPiercedLip(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", PresetColour.CLOTHING_GOLD, false), true, slave);
					
				} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2) { //Masculine:
					applySlaveEffectsMale(slave);
					if(slave.getPenisSize().getMedianValue()<PenisLength.FOUR_HUGE.getMedianValue()) {
						slave.setPenisSize(PenisLength.FOUR_HUGE);
					}
					slave.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
					slave.addPersonalityTrait(PersonalityTrait.CONFIDENT);
					slave.addFetish(Fetish.FETISH_IMPREGNATION);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_work_boots", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_crotchless_chaps", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_NAVY, false), true, slave);
					
				} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3) { //Anal:
					applySlaveEffectsAnal(slave);
					slave.addFetish(Fetish.FETISH_SUBMISSIVE);
					slave.removePersonalityTrait(PersonalityTrait.CONFIDENT);
					
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, slave);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_waistcoat_shirt", PresetColour.CLOTHING_GREY, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_jacket", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_trousers", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_gloves", PresetColour.CLOTHING_WHITE, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_butler_butler_shoes", PresetColour.CLOTHING_BLACK, false), true, slave);

					slave.addStatusEffect(StatusEffect.CHASTITY_4, -1);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_chastity_cage", PresetColour.CLOTHING_PINK_LIGHT, false), true, slave);
					
				} else { //Feminine:
					applySlaveEffectsFemale(slave);
					if(slave.getBreastSize().getMeasurement()<CupSize.F.getMeasurement()) {
						slave.setBreastSize(CupSize.F);
					}
					slave.addPersonalityTrait(PersonalityTrait.CONFIDENT);
					slave.addPersonalityTrait(PersonalityTrait.LEWD);
					slave.setVaginaSquirter(true);

					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_virgin_killer_sweater", PresetColour.CLOTHING_RED_BURGUNDY, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hand_elbow_length_gloves", PresetColour.CLOTHING_BLACK, false), true, slave);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_platform_boots", PresetColour.CLOTHING_RED_BURGUNDY, false), true, slave);
					
					slave.setPiercedEar(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_hoops", PresetColour.CLOTHING_PLATINUM, false), true, slave);
					slave.setPiercedNose(true);
					slave.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", PresetColour.CLOTHING_PLATINUM, false), true, slave);
				}
			}
			NPC slave = Main.game.getNonCompanionCharactersPresent().get(0);
			Main.game.setActiveNPC(slave);
			if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE) { //Oral:
				slave.addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
			}
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE_INTERIOR"));
			if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE) { //Oral:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe1Visited, true);
			} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2) { //Masculine:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe2Visited, true);
			} else if(Main.game.getPlayer().getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3) { //Anal:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe3Visited, true);
			} else { // Feminine:
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCafe4Visited, true);
			}
			if(slave.isVisiblyPregnant()) {
				slave.setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			}
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(slave, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().hasCompanions()) {
				if(index==0) {
					return "自己";
				} else if(index==1) {
					return "[com.Name]";
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "离开小餐厅，退回奴隶巷。", MARKET_STALL_CAFE);
			}
			
			if(getCafeItems().containsKey(index)) {
				AbstractItemType itemType = getCafeItems().get(index);
				int itemValue = (int) (itemType.getValue()*1.8f);
				if(responseTab==0) {
					if(Main.game.getPlayer().getMoney()<itemValue) {
						return new Response(itemType.getName(false)+" ("+UtilText.formatAsMoneyUncoloured(itemValue, "span")+")",
								"你的钱不够点"+itemType.getDeterminer()+itemType.getName(false)+"……",
								null);
						
					} else {
						return new Response(itemType.getName(false)+" ("+UtilText.formatAsMoney(itemValue, "span")+")",
										Main.game.getPlayer().hasCompanions()
											?"给自己点"+itemType.getDeterminer()+itemType.getName(false)+"。"
											:"点"+itemType.getDeterminer()+itemType.getName(false)+"。",
											MARKET_STALL_CAFE_INTERIOR_NO_CONTENT) {
									@Override
									public void effects() {
										UtilText.addSpecialParsingString(itemType.getDeterminer(), true);
										UtilText.addSpecialParsingString(itemType.getName(false), false);
										UtilText.addSpecialParsingString(Util.intToString(itemValue), false);
										UtilText.addSpecialParsingString(itemType.getUseName(), false);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE_INTERIOR_ORDER"));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().useItem(Main.game.getItemGen().generateItem(itemType), Main.game.getPlayer(), false, true));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-itemValue));
									}
								};
					}
					
				} else if(responseTab==1) {
					if(Main.game.getPlayer().getMoney()<itemValue) {
						return new Response(itemType.getName(false), "你的钱不够点"+itemType.getDeterminer()+itemType.getName(false)+"……", null);
						
					} else {
						return new Response(itemType.getName(false),
										"给[com.name]点"+itemType.getDeterminer()+itemType.getName(false)+"。",
										MARKET_STALL_CAFE_INTERIOR_NO_CONTENT) {
									@Override
									public void effects() {
										UtilText.addSpecialParsingString(itemType.getDeterminer(), true);
										UtilText.addSpecialParsingString(itemType.getName(false), false);
										UtilText.addSpecialParsingString(Util.intToString(itemValue), false);
										UtilText.addSpecialParsingString(UtilText.parse("[com.verb("+itemType.getUseName()+")]"), false);
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "MARKET_STALL_CAFE_INTERIOR_ORDER_COMPANION"));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().getMainCompanion().useItem(Main.game.getItemGen().generateItem(itemType), Main.game.getPlayer().getMainCompanion(), false, true));
										Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-itemValue));
									}
								};
					}
				}
			}
			
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_slaver_alley_cafe_interior").getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MARKET_STALL_CAFE_INTERIOR_NO_CONTENT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MARKET_STALL_CAFE_INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode BOUNTY_HUNTERS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/bountyHunterLodge", "EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "进入展览四处逛逛……", BountyHunterLodge.ENTRANCE_INITITAL) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.BOUNTY_HUNTER_LODGE, PlaceType.BOUNTY_HUNTER_LODGE_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AUCTION_BLOCK = new DialogueNode("拍卖台", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_BLOCK");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("靠近", "靠近拍卖台。", AUCTION_BLOCK_LIST);
				} else {
					return new Response("靠近", "你没有贩奴许可，无法参加奴隶拍卖。", null);
				}
			}
			return null;
		}
	};
	
	
	public static final DialogueNode AUCTION_BLOCK_LIST = new DialogueNode("拍卖台", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_BLOCK_LIST_START"));
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
						+ "<b>即将开始的公开拍卖</b>"
						+ "<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
							+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "奴隶"
							+ "</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>服从</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>价值</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>起拍价</b>"
							+"</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "竞拍"
							+ "</div>"
						+ "</div>");
			
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			Collections.sort(charactersPresent, (e1, e2) -> e1.getName(true).compareTo(e2.getName(true)));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
								+ "<b>没有即将开始的拍卖……</b>"
						+ "</div>");
				
			} else {
				int i=0;
				for(NPC slave : charactersPresent){
					boolean alternateBackground = i%2==0;
					
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"'")+"'>"
								+ "<div style='width:40%; float:left; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName(true)+"</b>- "
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span> "
									+ "<span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"
										+Util.capitaliseSentence((slave.isFeminine()?slave.getSubspecies().getSingularFemaleName(slave.getBody()):slave.getSubspecies().getSingularMaleName(slave.getBody())))
									+"</span>"
								+ "</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getObedience().getColour().toWebHexString()+";'>"+slave.getObedienceValue()+ "</b>"
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney(slave.getValueAsSlave(true), "span")
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney((int)(slave.getValueAsSlave(true)*0.5f), "span")
								+"</div>"
								+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
									+ "<div id='"+slave.getId()+"_BID' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBid()+"</div></div>"
								+ "</div>"
							+ "</div>"
							));
					i++;
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("导入", "查看导入角色的界面。", AUCTION_IMPORT);
				
			} else if(index==0) {
				return new Response("返回", "走出拍卖台。", AUCTION_BLOCK);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUCTION_IMPORT = new DialogueNode("拍卖台", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_START"));
			saveLoadSB.append(
					"<p>"
						+ "<table align='center'>");
			
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index==0) {
				return new Response("返回", "回到上一界面。", AUCTION_BLOCK_LIST);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AUCTION_BIDDING = new DialogueNode("拍卖台", "", true) {
		
		@Override
		public boolean isContinuesDialogue() {
			return biddingRounds!=0;
		}
		
		@Override
		public String getContent() {
			if(biddingRounds==0) {
				UtilText.addSpecialParsingString(currentRivalBidder.getName(true), true);
				UtilText.addSpecialParsingString(UtilText.parseNPCSpeech(currentRivalBidder.getRandomBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG)), false);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice, "span"), false);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice+100, "span"), false);

				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_BIDDING_START", biddingNPC);
				
			} if(biddingRounds==biddingRoundsTotal) {
				if(playerBidLeader) {
					UtilText.addSpecialParsingString(currentRivalBidder.getName(false), true);
					UtilText.addSpecialParsingString(UtilText.parseNPCSpeech(currentRivalBidder.getRandomFailedBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG)), false);
					UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice, "span"), false);
					
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_VICTORY", biddingNPC);
					
				} else {
					UtilText.addSpecialParsingString(currentRivalBidder.getName(false), true);
					UtilText.addSpecialParsingString(UtilText.parseNPCSpeech(currentRivalBidder.getRandomSuccessfulBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG)), false);

					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_DEFEAT", biddingNPC);
				}
				
			} else {
				UtilText.addSpecialParsingString(currentRivalBidder.getName(false), true);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice, "span"), false);
				UtilText.addSpecialParsingString(UtilText.formatAsMoney(biddingPrice+100, "span"), false);
				
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AUCTION_IMPORT_CONTINUE", biddingNPC);
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(biddingRounds==biddingRoundsTotal) {
				if(index==1) {
					if(playerBidLeader) {
						return new Response("继续", UtilText.parse(biddingNPC, "你赢得了竞拍！[npc.Name]现在位于奴隶管理局等待取走。"), AUCTION_BLOCK) {
							@Override
							public void effects() {
							}
						};
					} else {
						return new Response(UtilText.parse(biddingNPC, "[npc.Name]被卖出"), "你没能赢得这次拍卖，但下次还有机会，对吧？", AUCTION_BLOCK) {
							@Override
							public void effects() {
								Main.game.getNpc(Finch.class).removeSlave(biddingNPC);
								Main.game.banishNPC(biddingNPC);
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=biddingPrice+100) {
						return new Response("叫价"+UtilText.formatAsMoney(biddingPrice+100, "span"), UtilText.parse(biddingNPC, "给[npc.Name]叫价"+(biddingPrice+100)+"火币。"), AUCTION_BIDDING) {
							@Override
							public void effects() {
								biddingPrice += 100;
								playerBidLeader = true;
								increaseBid();
								if(biddingRounds==biddingRoundsTotal) {
									Main.game.getPlayer().addSlave(biddingNPC);
									biddingNPC.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
									Main.game.getPlayer().incrementMoney(-biddingPrice);
								}
							}
						};
					} else {
						return new Response("叫价"+UtilText.formatAsMoneyUncoloured(biddingPrice+100, "span"), "你负担不起"+(biddingPrice+100)+"火币的价格，只能让这名奴隶归他人所有了。", null);
					}
					
				} else if(index==2) {
					return new Response("停止竞拍", UtilText.parse(biddingNPC, "停止竞拍，会让其他人买下[npc.name]。"), AUCTION_BIDDING) {
						@Override
						public void effects() {
							playerBidLeader = false;
							biddingRounds=biddingRoundsTotal;
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS = new DialogueNode("公开颈手枷", "", false) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleySlavesFreed)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_FREED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS"));

				List<String> sexAvailability = new ArrayList<>();

				List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
				charactersPresent.removeIf((npc) -> npc instanceof Sean);
				for(NPC npc : charactersPresent) {
					UtilText.nodeContentSB.append(UtilText.parse(npc, 
							"<p>"
								+ "[npc.Name]，" + (npc.getOwner().isPlayer()?"[style.boldArcane(你的奴隶)]，":"")
								+ "<span style='color:"+npc.getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span>"
										+ "<span style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</span>，标记为"));
					
					sexAvailability.clear();
					if(npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL)) {
						sexAvailability.add("<b style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>口穴</b>");
					}
					if(npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL)) {
						sexAvailability.add("<b style='color:"+PresetColour.BASE_PINK.toWebHexString()+";'>阴道</b>");
					}
					if(npc.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL)) {
						sexAvailability.add("<b style='color:"+PresetColour.BASE_PINK_DEEP.toWebHexString()+";'>肛门</b>");
					}
					
					if(!sexAvailability.isEmpty()) {
						UtilText.nodeContentSB.append(
								Util.stringsToStringList(sexAvailability, false)
								+"随意使用。</p>");
					} else {
						UtilText.nodeContentSB.append(
								"[style.boldBad(不准插入)]。</p>");
					}
				}
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_ENFORCER"));

			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleySlavesFreed)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_FREED_ENDING"));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "单人";
				
			} else if(index==1) {
				return "三人行";
			}
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> charactersPresent = Main.game.getNonCompanionCharactersPresent();
			charactersPresent.removeIf((npc) -> npc instanceof Sean);
			GameCharacter companion = getMainCompanion();

			if((responseTab==0 || isCompanionDialogue()) && index==1) {
				return new Response("[sean.Name]",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyComplained)
							?"你看到[sean.name]在看管这个区域，或许应该再找[sean.herHim]聊一聊……"
							:"似乎有一位执法者在看管这个区域，或许应该找他聊一聊……",
							PUBLIC_STOCKS_SEAN);
			}
			
			if(index>1) {
				if(responseTab==0) {
					if(index-1 <= charactersPresent.size()) {
						GameCharacter slave = charactersPresent.get(index-2);
						boolean ownedByPlayer = slave.isSlave() && slave.getOwner().isPlayer();
						return new ResponseSex(
								UtilText.parse(slave,"使用[npc.name]"),
								UtilText.parse(slave, "到[npc.name]的旁边，开始在大庭广众之下操[npc.herHim]……"),
								false,
								false,
								new SMStocks(
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS)),
										Util.newHashMapOfValues(new Value<>(slave, SexSlotStocks.LOCKED_IN_STOCKS))) {
									@Override
									public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
										return Util.newHashMapOfValues(
												new Value<>(ImmobilisationType.STOCKS,
														Util.newHashMapOfValues(
																new Value<>(Main.game.getPlayer(),
																		Util.newHashSetOfValues(slave)))));
									}
								},
								Util.newArrayListOfValues(companion),
								null,
								AFTER_STOCKS_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEX_SOLO", slave, companion)) {
							@Override
							public void effects() {
								stocksSlaveTargeted = ((NPC) slave);
							}
						};
					}
					
				} else if(isCompanionDialogue()) {
					if(index-1 <= charactersPresent.size()) {
						GameCharacter slave = charactersPresent.get(index-2);
						boolean ownedByPlayer = slave.isSlave() && slave.getOwner().isPlayer();
						
						if(companion.isAbleToRefuseSexAsCompanion()) {
							if(!companion.isAttractedTo(Main.game.getPlayer())) {
								return new Response(UtilText.parse(slave, "使用[npc.name]"),
										UtilText.parse(companion, "你感觉得到[npc.name]完全没兴趣跟你做爱，而且[npc.she]不是你的奴隶，你也无法强迫……"),
										null);
							}
							if(!companion.isAttractedTo(slave)) {
								return new Response(UtilText.parse(slave, "使用[npc.name]"),
										UtilText.parse(slave, companion, "你感觉的到[npc2.name]完全没兴趣跟[npc.name]做爱，而且[npc2.she]不是你的奴隶，你也无法强迫……"),
										null);
							}
							if(companion.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								return new Response(UtilText.parse(slave, "使用[npc.name]"),
										UtilText.parse(companion, "[npc.Name]对“"+Fetish.FETISH_EXHIBITIONIST.getName(companion)+"”性癖有些反感，所以不愿意在大庭广众下做爱。"
												+ "而且[npc.she]不是你的奴隶，你也无法强迫……"),
										null);
							}
						}
						return new ResponseSex(
								UtilText.parse(slave, "使用[npc.name]"),
								UtilText.parse(slave, companion, "到[npc.name]的旁边，让[npc2.name]跟你一起在大庭广众之下操[npc.herHim]……"),
								false,
								false,
								new SMStocks(
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_VAGINAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ANAL),
										ownedByPlayer || slave.hasSlaveJobSetting(SlaveJob.PUBLIC_STOCKS, SlaveJobSetting.SEX_ORAL),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotStocks.BEHIND_STOCKS),
												new Value<>(companion, SexSlotStocks.RECEIVING_ORAL)),
										Util.newHashMapOfValues(new Value<>(slave, SexSlotStocks.LOCKED_IN_STOCKS))) {
									@Override
									public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
										return Util.newHashMapOfValues(
												new Value<>(ImmobilisationType.STOCKS,
														Util.newHashMapOfValues(
																new Value<>(Main.game.getPlayer(),
																		Util.newHashSetOfValues(slave)))));
									}
								},
								null,
								null,
								AFTER_STOCKS_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEX_THREESOME", slave, companion)) {
							@Override
							public void effects() {
								stocksSlaveTargeted = ((NPC) slave);
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_STOCKS_SEX = new DialogueNode("公开颈手枷", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(stocksSlaveTargeted, "跟[npc.name]结束后，你退了下来，准备继续你的旅程。");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_STOCKS_SEX", stocksSlaveTargeted);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程。", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						stocksSlaveTargeted = null;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_SEAN = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Sean.class).setGenericName(UtilText.parse("警员[sean.surname]"));
			Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Sean.class));
			
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getCurrentDialogueNode()==PUBLIC_STOCKS_TALK) {
					return new Response("对话", "你已经跟[sean.name]谈过话了。", null);
				}
				return new Response("交谈",
						"询问[sean.name]他为什么在看管这片区域，最近有没有发生什么有趣的事情。",
						PUBLIC_STOCKS_TALK) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_TALK"));
						
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanIntroduced, true);
						if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTalkedBraxReveal, true);
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTalked, true);
						updateSeanPregnancyReactions();
					}
				};
				
			} if(index==2) {
				if(Main.game.getCurrentDialogueNode()==PUBLIC_STOCKS_TALK_FREED_SLAVES) {
					return new Response("放走的奴隶", "你已经问过[sean.name]那些被放走的奴隶的现状了。", null);
				}
				if(!slavesInStocksPresent()) {
					return new Response("放走的奴隶",
							"询问[sean.name]放走颈手枷里的奴隶他会不会惹上麻烦。",
							PUBLIC_STOCKS_TALK_FREED_SLAVES) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_TALK_FREED_SLAVES"));
							
							if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTalkedFreedSlaves, true);
							updateSeanPregnancyReactions();
						}
					};
				}
				return new Response("抱怨",
						"你不喜欢奴隶被公开使用。向[sean.name]抱怨……",
						PUBLIC_STOCKS_COMPLAIN) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanIntroduced, true);
						if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
						}
						updateSeanPregnancyReactions();
					}
				};
				
			} else if(index==3 && Main.game.getNpc(Sean.class).getTotalTimesHadSex(Main.game.getPlayer())>0) {
				if(!Main.game.getNpc(Sean.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response("诱惑", "[sean.Name]并没有被你吸引，诱惑他只会白费功夫。", null);
				}
				return new Response("诱惑",
						"玩味地挑逗[sean.name]，问他还想不想跟你爽一爽……",
						PUBLIC_STOCKS_SEAN_SEDUCE) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						updateSeanPregnancyReactions();
					}
				};
				
				
			} else if(index==0) {
				return new Response("退开", "你决定不向[Sean.name]询问任何事情，转而退开。", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanIntroduced, true);
						if(isCompanionDialogue() && getMainCompanion().equals(Main.game.getNpc(Brax.class))) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.seanSeenBrax, true);
						}
						updateSeanPregnancyReactions();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PUBLIC_STOCKS_SEAN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_TALK_FREED_SLAVES = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PUBLIC_STOCKS_SEAN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_SEAN_SEDUCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
//				if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
//					return new Response(
//							isCompanionDialogue()
//								?"Alleyway (solo)"
//								:"Alleyway",
//							"You have not yet discovered the hidden alleyway, so cannot suggest going there to [sean.name]!"
//									+ "<br/>[style.italics(You will discover the hidden alleyway if you accept his challenge (you are not forced to fight him when choosing that option).)]",
//							null);
//				}
				return new ResponseSex(
						isCompanionDialogue()
							?"小巷(单人)"
							:"小巷",
						"告诉[sean.name]你想去隐蔽的小巷，跟他在那里做爱……",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.TWO_HORNY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Sean.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMainCompanion()),
								null),
						AFTER_SEAN_SEDUCE_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_ALLEYWAY")) {
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
							Cell c = Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getX()+2,
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getY());
							c.getPlace().setPlaceType(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						}
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasVagina()) {
					return new Response("颈手枷配种", "你没有阴道，所以没法在颈手枷里让[sean.name]给你配种。", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("颈手枷配种", "[sean.Name]无法使用你的阴道，所以也就没法在颈手枷里让他给你配种。", null);
				}
				if(Main.game.getPlayer().isPregnant()) {
					return new Response("颈手枷配种", "你已经怀孕了，所以没法在颈手枷里让[sean.name]给你配种。", null);
				}
				if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("颈手枷配种", "你的子宫里装满了卵，所以没法在颈手枷里让[sean.name]给你配种。", null);
				}
				return new ResponseSex("颈手枷配种",
						"询问[sean.name]他能不能把你锁在颈手枷里，在大庭广众下给你配种。",
						Util.newArrayListOfValues(Fetish.FETISH_EXHIBITIONIST, Fetish.FETISH_PREGNANCY, Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.FOUR_LUSTFUL,
						null,
						null,
						null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								return Util.newHashMapOfValues(
										new Value<>(ImmobilisationType.STOCKS,
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Sean.class),
																Util.newHashSetOfValues(Main.game.getPlayer())))));
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_SEAN_SEDUCE_STOCKS_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_BREEDING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
						Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal, false);
						Main.game.getPlayer().setCaptive(true);
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
					}
				};
				
			} else if(index==6 && isCompanionDialogue()) {
//				if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
//					return new Response(
//							"Alleyway (threesome",
//							"You have not yet discovered the hidden alleyway, so cannot suggest going there to [sean.name]!"
//									+ "<br/>[style.italics(You will discover the hidden alleyway if you accept his challenge (you are not forced to fight him when choosing that option).)]",
//							null);
//				}
				if(!Main.game.getNpc(Sean.class).isAttractedTo(getMainCompanion())) {
					return new Response("小巷(三人行)", "[sean.Name]并没有被[com.name]吸引，所以不愿意跟[com.herHim]三人行。", null);
				}
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
						return new Response("小巷(三人行)", "[com.Name]并没有被你吸引，所以不愿意跟你和[sean.name]三人行。", null);
					}
					if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))) {
						return new Response("Alleyway (threesome)", "[com.Name]并没有被[sean.name]吸引，所以不愿意跟他和你三人行。", null);
					}
//				}
				return new ResponseSex(
						"小巷(三人行)",
						"告诉[sean.name]你和[com.name]想去隐蔽的小巷，跟他在那里做爱……",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.TWO_HORNY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Sean.class)),
								Util.newArrayListOfValues(Main.game.getPlayer(), getMainCompanion()),
								null,
								null),
						AFTER_SEAN_SEDUCE_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_ALLEYWAY_THREESOME")){
					@Override
					public void effects() {
						if(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY)==null) {
							Cell c = Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getX()+2,
									Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getY());
							c.getPlace().setPlaceType(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						}
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
					}
				};
				
			} else if(index==7 && isCompanionDialogue()) {
				if(!Main.game.getPlayer().hasVagina()) {
					return new Response("双人配种", "你没有阴道，所以没法在颈手枷里让[sean.name]给你配种。", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("双人配种", "[sean.Name]无法使用你的阴道，所以也就没法在颈手枷里让他给你配种。", null);
				}
				if(Main.game.getPlayer().isPregnant()) {
					return new Response("双人配种", "你已经怀孕了，所以没法在颈手枷里让[sean.name]给你配种。", null);
				}
				if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("双人配种", "你的子宫里装满了卵，所以没法在颈手枷里让[sean.name]给你配种。", null);
				}
				if(!getMainCompanion().hasVagina()) {
					return new Response("双人配种", "[com.Name]没有阴道，所以没法在颈手枷里让[sean.name]给[com.her]配种。", null);
				}
				if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
					return new Response("双人配种", "[sean.Name]无法使用[com.namePos]的阴道，所以也就没法在颈手枷里让他给[com.her]配种。", null);
				}
				if(getMainCompanion().isPregnant()) {
					return new Response("双人配种", "[com.Name]已经怀孕了，所以没法在颈手枷里让[sean.name]给[com.her]配种。", null);
				}
				if(getMainCompanion().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
					return new Response("双人配种", "[com.NamePos]的子宫里装满了卵，所以没法在颈手枷里让[sean.name]给[com.her]配种。", null);
				}
				if(!Main.game.getNpc(Sean.class).isAttractedTo(getMainCompanion())) {
					return new Response("双人配种", "[sean.Name]并没有被[com.name]吸引，所以不愿意给[com.her]配种。", null);
				}
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
						return new Response("双人配种", "[com.Name]并没有被你吸引，所以不愿意参与到配种中。", null);
					}
					if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))) {
						return new Response("双人配种", "[com.Name]并没有被[sean.name]吸引，所以不愿意参与到配种中。", null);
					}
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
						return new Response("双人配种",
								"由于[com.name]对于“"+Fetish.FETISH_EXHIBITIONIST.getName(getMainCompanion())+"”性癖心存反感，所以不会同意。"
										+ "[sean.Name]也不想强迫[com.herHim]做不愿意做的事情。",
								null);
					}
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_PREGNANCY).isPositive()) {
						return new Response("双人配种",
								"由于[com.name]对于“"+Fetish.FETISH_PREGNANCY.getName(getMainCompanion())+"”性癖心存反感，所以不会同意。"
										+ "[sean.Name]也不想强迫[com.herHim]做不愿意做的事情。",
								null);
					}
//				}
				return new ResponseSex("双人配种",
						"询问[sean.name]他能不能把你和[com.name]都锁在颈手枷里，在大庭广众下给你们配种。",
						Util.newArrayListOfValues(Fetish.FETISH_EXHIBITIONIST, Fetish.FETISH_PREGNANCY, Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.FOUR_LUSTFUL,
						null,
						null,
						null,
						true,
						false,
						new SexManagerDefault(
								SexPosition.STOCKS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
							@Override
							public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
								return Util.newHashMapOfValues(
										new Value<>(ImmobilisationType.STOCKS,
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Sean.class),
																Util.newHashSetOfValues(Main.game.getPlayer())))));
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.NONE;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								if(character.isPlayer()) {
									return false;
								}
								return super.isAbleToRemoveOthersClothing(character, clothing);
							}
							@Override
							public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
								return !equippingCharacter.isPlayer();
							}
							@Override
							public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
								return !character.isPlayer();
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.CREAMPIE;
							}
						},
						null,
						null,
						AFTER_SEAN_SEDUCE_STOCKS_BREEDING,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_BREEDING_THREESOME")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
						Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
						Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), getMainCompanion(), false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal, true);
						Main.game.getPlayer().setCaptive(true);
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
						getMainCompanion().unequipAllClothingIntoHoldingInventory(Main.game.getNpc(Sean.class), false, false);
					}
				};
				
			} else if(index==0) {
				return new Response("反悔", "决定还是不跟[sean.name]做爱了……", PUBLIC_STOCKS_SEAN_SEDUCE_BACK_OUT) {
					@Override
					public void effects() {
						if(Main.game.getNpc(Sean.class).getAffection(Main.game.getPlayer())>AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Sean.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_SEAN_SEDUCE_BACK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_SEAN_SEDUCE_BACK_OUT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PUBLIC_STOCKS_SEAN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_SEAN_SEDUCE_ALLEYWAY_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
				return "你、[com.name]和[sean.name]结束了性爱……";
			}
			return "你和[sean.name]结束了性爱……";
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_ALLEYWAY_THREESOME_SEX");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_ALLEYWAY_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"返回奴隶巷。",
						PlaceType.SLAVER_ALLEY_PATH.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_ALLEYWAY_SEX_FINISHED"));
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PATH, false);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyVisitedHiddenAlleyway, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEAN_SEDUCE_STOCKS_BREEDING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				return 10*60;
			} else {
				return 2*60;
			}
		}
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Sean.class).calculateGenericSexEffects(
								true, true, getMainCompanion(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.LIMITED_DESCRIPTION_NEEDED),
						true);
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_THREESOME");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
					return new Response("等待", "[sean.Name]把你和[com.name]锁在颈手枷上又过了一段时间。", AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED) {
						@Override
						public void effects() {
							Main.game.getPlayer().performImpregnationCheck(true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
							getMainCompanion().performImpregnationCheck(true);
							getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
							updateSeanPregnancyReactions();
						}
					};
					
				} else {
					return new Response("等待", "[sean.Name]把你锁在颈手枷上又过了一段时间。", AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED) {
						@Override
						public void effects() {
							Main.game.getPlayer().performImpregnationCheck(true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
							updateSeanPregnancyReactions();
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_THREESOME_FINISHED");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
					return new Response("解放", "[sean.Name]上前把你和[com.name]从颈手枷里解放出来。", PUBLIC_STOCKS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_THREESOME_FINISHED_END"));
							Main.game.getPlayer().setCaptive(false);
							Main.game.getPlayer().equipAllClothingFromHoldingInventory();
							getMainCompanion().equipAllClothingFromHoldingInventory();
						}
					};
				} else {
					return new Response("解放", "[sean.Name]上前把你从颈手枷中解放出来。", PUBLIC_STOCKS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_SEDUCE_STOCKS_BREEDING_FINISHED_END"));
							Main.game.getPlayer().setCaptive(false);
							Main.game.getPlayer().equipAllClothingFromHoldingInventory();
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
								getMainCompanion().equipAllClothingFromHoldingInventory();
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyComplained)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("退开", "按着[sean.name]说的做，退开。", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_STEP_BACK"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
					}
				};
				
			} else if(index==2) {
				return new Response("坚持己见", "无视[sean.namePos]的命令，仍然要求释放奴隶。", PUBLIC_STOCKS_COMPLAIN_PERSIST);
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_PERSIST = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyComplained)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_PERSIST_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_PERSIST");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝", "决定不接受[sean.namePos]的挑战，然后退开。", PUBLIC_STOCKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_PERSIST_STEP_BACK"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
					}
				};
				
			} else if(index==2) {
				return new Response("挑战", "接受[sean.namePos]的挑战。", PUBLIC_STOCKS_COMPLAIN_CHALLENGE) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Cell c = Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(
								Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getX()+2,
								Main.game.getWorlds().get(WorldType.SLAVER_ALLEY).getCell(PlaceType.SLAVER_ALLEY_ENTRANCE).getLocation().getY());
						
						c.getPlace().setPlaceType(PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);

						NPC sean = Main.game.getNpc(Sean.class);
						sean.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_DESERTED_ALLEYWAY);
						// Sean takes his stabproof vest, utility belt, and beret off:
						sean.unequipClothingIntoInventory(sean.getClothingInSlot(InventorySlot.TORSO_OVER), true, sean);
						sean.unequipClothingIntoInventory(sean.getClothingInSlot(InventorySlot.HIPS), true, sean);
						sean.unequipClothingIntoInventory(sean.getClothingInSlot(InventorySlot.HEAD), true, sean);

						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyVisitedHiddenAlleyway)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_REPEAT"));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE"));
						}
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyVisitedHiddenAlleyway, true);
						
						sean.setPlayerKnowsName(true);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getMoney()<5000) {
					return new Response("贿赂("+UtilText.formatAsMoneyUncoloured(5000, "span")+")",
							"你的钱不够贿赂[sean.name]……<br/>[style.italicsBad(需要5000火币！)]",
							null);
				}
				return new Response("贿赂("+UtilText.formatAsMoney(5000, "span")+")",
						"贿赂[sean.name]，让他在你释放奴隶的时候假装看向别处。",
						PUBLIC_STOCKS_COMPLAIN_BRIBE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyBribed)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_BRIBE_REPEAT"));
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_BRIBE"));

						Main.game.getNpc(Sean.class).setPlayerKnowsName(true);
						
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-5000));
					}
				};
				
			} else if(index==4) {
				return new Response(
						isCompanionDialogue()
							?"代替(单人)"
							:"代替",
						isCompanionDialogue()
							?"按照[sean.name]的建议，取代其中一名奴隶，让[com.name]等在旁边看着……"
							:"按照[sean.name]的建议，取代其中一名奴隶。",
						PUBLIC_STOCKS_LOCKED_UP) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
 					@Override
					public void effects() {
 						SlaveInStocks tempSlave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTookPlace)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE_REPEAT", tempSlave));
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE", tempSlave));

						Main.game.getNpc(Sean.class).setPlayerKnowsName(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						
						applyLockedUpEffects(false, false);
					}
				};
				
			} else if(index==5 && isCompanionDialogue()) {
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
						return new Response("代替(双飞)",
								"由于[com.name]对于“"+Fetish.FETISH_EXHIBITIONIST.getName(getMainCompanion())+"”性癖心存反感，所以不会同意。"
										+ "[sean.Name]也不想强迫[com.herHim]做不愿意做的事情。",
								null);
					}
					if(!getMainCompanion().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
						return new Response("代替(双飞)",
								"由于[com.name]对于“"+Fetish.FETISH_PREGNANCY.getName(getMainCompanion())+"”性癖心存反感，所以不会同意。"
										+ "[sean.Name]也不想强迫[com.herHim]做不愿意做的事情。",
								null);
					}
//				}
				return new Response("代替(双飞)",
						"按照[sean.name]的建议，让[com.name]跟你一起取代其中的两名奴隶。",
						PUBLIC_STOCKS_LOCKED_UP) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
 					@Override
					public void effects() {
 						SlaveInStocks tempSlave = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
 						SlaveInStocks tempSlave2 = new SlaveInStocks(Gender.getGenderFromUserPreferences(false, false));
 						
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTookPlace)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE_BOTH_REPEAT", tempSlave, tempSlave2));
						} else {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_TAKE_PLACE_BOTH", tempSlave, tempSlave2));
						}
						
						Main.game.getNpc(Sean.class).setPlayerKnowsName(true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyComplained, true);
						
						applyLockedUpEffects(true, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_CHALLENGE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗", "跟[sean.name]开打！", Main.game.getNpc(Sean.class));
				
			} else if(index==0) {
				return new Response("反悔", "决定还是不跟[sean.name]战斗了……", 
						PlaceType.SLAVER_ALLEY_PATH.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_BACK_OUT"));
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PATH, false);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
						if(Main.game.getNpc(Sean.class).getAffection(Main.game.getPlayer())>AffectionLevel.NEGATIVE_ONE_ANNOYED.getMedianValue()) {
							Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Sean.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("释放奴隶", "回到广场，解开颈手枷的锁。", PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY_FREE_SLAVES"));
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("诱惑",
							"[sean.Name]并没有被你吸引，所以你无法诱惑他。而你强行凑上去可能是个很糟糕的主意，你会立刻成为悬赏的罪犯。",
							null);
				}
				return new ResponseSex(
						"诱惑",
						"告诉[sean.name]你真的只想和他独处一段时间……"
								+ "<br/>[style.italicsMinorBad(跟[sean.name]做爱后你就没有足够的时间释放奴隶了。)]",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Sean.class)),
								Util.newArrayListOfValues(getMainCompanion()),
								null),
						AFTER_SEAN_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY_SEX"));
				
			} else if(index==3 && isCompanionDialogue()) {
				if(!Main.game.getPlayer().isFeminine()) {
					return new Response("诱惑(三人行)",
							"[sean.Name]并没有被你吸引，所以你无法诱惑他。而你强行凑上去可能是个很糟糕的主意，你会立刻成为悬赏的罪犯。",
							null);
				}
				if(!getMainCompanion().isFeminine()) {
					return new Response("诱惑(三人行)",
							"[sean.Name]并没有被[com.name]吸引，所以你不能诱惑他加入三人行。"
									+ "而你们强行凑上去可能是个很糟糕的主意，你和[com.name]都会立刻成为悬赏的罪犯。",
							null);
				}
//				if(getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					if(!getMainCompanion().isAttractedTo(Main.game.getPlayer())) {
						return new Response("诱惑(三人行)",
								"你看得出来[com.name]一点也不想和你做爱……",
								null);
					}
					if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Sean.class))) {
						return new Response("诱惑(三人行)",
								"你看得出来[com.name]一点也不想和[sean.name]做爱……",
								null);
					}
//				}
				return new ResponseSex(
						"诱惑(三人行)",
						"告诉[sean.name]你真的只想和[com.name]独处一段时间……"
								+ "<br/>[style.italicsMinorBad(跟[sean.name]和[com.name]三人行后你就没有足够的时间释放奴隶了。)]",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										getMainCompanion()),
								Util.newArrayListOfValues(
										Main.game.getNpc(Sean.class)),
								null,
								null),
						AFTER_SEAN_ALLEYWAY_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_VICTORY_SEX_BOTH"));
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEAN_ALLEYWAY_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
				return "你、[com.name]和[sean.name]结束了性爱……";
			}
			return "你和[sean.name]结束了性爱……";
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_ALLEYWAY_SEX_THREESOME");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_ALLEYWAY_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"返回奴隶巷",
						PlaceType.SLAVER_ALLEY_PATH.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "AFTER_SEAN_ALLEYWAY_SEX_FINISHED"));
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PATH, false);
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
						Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_CHALLENGE_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
			Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
			Main.game.getNpc(Sean.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_CHALLENGE_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被锁住",
						isCompanionDialogue()
							?"你和[com.name]被锁在颈手枷上了……"
							:"你被锁在颈手枷上了……",
						PUBLIC_STOCKS_LOCKED_UP) {
					@Override
					public void effects() {
						applyLockedUpEffects(true, false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_BRIBE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("释放奴隶", "回到广场，解开颈手枷的锁。", PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES) {
					@Override
					public void effects() {
						Main.game.getNpc(Sean.class).setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_CAFE, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyBribed, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlavesInStocks();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("退开",
						"听从守卫的话，退到一旁，让他们调查发生了什么事情。",
						PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES_END) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleySlavesFreed, true);
						Main.game.getDialogueFlags().setSavedLong("slaver_alley_slaves_freed_time", Main.game.getSecondsPassed());
						Main.game.getNpc(Sean.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES_END = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_COMPLAIN_FREE_SLAVES_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getPlayerCell().getDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			if(isSeanOfferingDeal(Main.game.getPlayer())) {
				if(isCompanionDialogue() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks) && isSeanOfferingDeal(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_DEAL_BOTH");
					
				} else {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_DEAL");
				}
				
			} else {
				if(isCompanionDialogue() && isSeanOfferingDeal(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_DEAL_COMPANION");
					
				} else {
					return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP");
				}
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isSeanOfferingDeal(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("接受",
							"吞下[sean.name]给你的"+ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false)+"，也相当于接受了他的保护，而他则会尝试让你受孕作为交换……",
							PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_ACCEPTED_DEAL"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyAcceptedDeal, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTookPlace, true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
							Main.game.getNpc(Sean.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
						}
					};
					
				} else if(index==2) {
					return new Response("拒绝",
							"拒绝吞下"+ItemType.getItemTypeFromId("innoxia_pills_fertility").getName(false)+"，但同意自己被公众使用。",
							PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_REFUSED_DEAL"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_APPROACH", randomSexPartners));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyAcceptedDeal, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTookPlace, true);
						}
					};
					
				}
				
			} else {
				if(index==1) {
					return new Response("继续",
							"你只能在此静候，看看有没有哪个人来使用你……",
							PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX) {
						@Override
						public int getSecondsPassed() {
							return 10*60;
						}
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_APPROACH", randomSexPartners));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyAcceptedDeal, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.slaverAlleyTookPlace, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				if(index==1) {
					return new ResponseSex("开始接受“配种”",
							"[sean.Name]站到你的身后，准备给你配种。",
							true,
							false,
							new SexManagerDefault(
									SexPosition.STOCKS,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
								@Override
								public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
									return Util.newHashMapOfValues(
											new Value<>(ImmobilisationType.STOCKS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Sean.class),
																	Util.newHashSetOfValues(Main.game.getPlayer())))));
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.NONE;
									}
									return super.getSexControl(character);
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
									if(character.isPlayer()) {
										return false;
									}
									return super.isAbleToRemoveOthersClothing(character, clothing);
								}
								@Override
								public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
									return !equippingCharacter.isPlayer();
								}
								@Override
								public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
									return !character.isPlayer();
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
								@Override
								public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
									return new ArrayList<>();
								}
								@Override
								public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
									return OrgasmBehaviour.CREAMPIE;
								}
							},
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FIRST_SEX_START_BREEDING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex("被使用",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
								?"这两个陌生人准备来使用你了……"
								:"有个陌生人准备来使用你了……",
							false,
							false,
							getRandomPartnerSexManager(),
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX", randomSexPartners));
				
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return "[sean.name]让你[pc.pussy+]里溢满了精液，完事之后就退了下去。";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				return "陌生人舒服过后就退了下去……";
				
			} else {
				return UtilText.parse(randomSexPartners.get(0), "路人操够了[npc.her]，走开了……");
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX_BREEDING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX_RANDOMS", randomSexPartners);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
						return new Response("轮到[com.NamePos]了",
								"[sean.Name]准备好跟[com.name]开干了……",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_FIRST_SEX) {
							@Override
							public void effects() {
								UtilText.addSpecialParsingString(
										Main.game.getNpc(Sean.class).calculateGenericSexEffects(
												true, true, getMainCompanion(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.LIMITED_DESCRIPTION_NEEDED),
										true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_FIRST_SEX_COMPANION_BREEDING"));
							}
						};
						
					} else {
						return new Response("轮到[com.NamePos]了",
								"一个路人走到[com.name]身边，开始操[com.herHim]……",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_FIRST_SEX) {
							@Override
							public void effects() {
								banishRandomSexPartners();
								randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(getMainCompanion(), false);
								generateCompanionSexParsingDescriptions();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX_COMPANION", randomSexPartners));
							}
						};
					}
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
						return new Response("等待",
								"你没什么事好做，只能干等着[sean.name]再来操你……",
								PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_BREEDING"));
							}
						};
						
					} else {
						return new Response("等待",
								"你没什么事好做，只能等着看看有没有哪个人来使用你……",
								PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
							@Override
							public void effects() {
								banishRandomSexPartners();
								randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_RANDOMS", randomSexPartners));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_COMPANION_FIRST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
					return new Response("等待",
							"你没什么事好做，只能干等着[sean.name]再来操你……",
							PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_BREEDING"));
						}
					};
					
				} else {
					return new Response("等待",
							"你没什么事好做，只能等着看看有没有哪个人来使用你……",
							PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX) {
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_RANDOMS", randomSexPartners));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				if(index==1) {
					return new ResponseSex("再次被配种",
							"[sean.Name]站到你的身后，准备给你配种。",
							true,
							false,
							new SexManagerDefault(
									SexPosition.STOCKS,
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Sean.class), SexSlotStocks.BEHIND_STOCKS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStocks.LOCKED_IN_STOCKS))) {
								@Override
								public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
									return Util.newHashMapOfValues(
											new Value<>(ImmobilisationType.STOCKS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Sean.class),
																	Util.newHashSetOfValues(Main.game.getPlayer())))));
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.NONE;
									}
									return super.getSexControl(character);
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
									if(character.isPlayer()) {
										return false;
									}
									return super.isAbleToRemoveOthersClothing(character, clothing);
								}
								@Override
								public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
									return !equippingCharacter.isPlayer();
								}
								@Override
								public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
									return !character.isPlayer();
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Sean.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
								@Override
								public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
									return new ArrayList<>();
								}
								@Override
								public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
									return OrgasmBehaviour.CREAMPIE;
								}
							},
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_SECOND_SEX_START_BREEDING")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getNpc(Sean.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex("再次被使用",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
								?"这两个陌生人准备来使用你了……"
								:"有个陌生人准备来使用你了……",
							false,
							false,
							getRandomPartnerSexManager(),
							null,
							null,
							PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX", randomSexPartners));
				
				}
			}
			return null;
		}
	};

	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return "[sean.name]让你[pc.pussy+]里溢满了精液，完事之后就退了下去。";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				return "陌生人舒服过后就退了下去……";
				
			} else {
				return UtilText.parse(randomSexPartners.get(0), "路人操够了[npc.her]，走开了……");
			}
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX_BREEDING");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX_RANDOMS", randomSexPartners);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
						return new Response("轮到[com.NamePos]了",
								"[sean.Name]到了[com.name]身边，准备开始操[com.herHim]……",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_SECOND_SEX) {
							@Override
							public void effects() {
								Main.game.getNpc(Sean.class).calculateGenericSexEffects(
										true, true, getMainCompanion(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.LIMITED_DESCRIPTION_NEEDED);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_AFTER_SECOND_SEX_COMPANION_BREEDING"));
							}
						};
						
					} else {
						return new Response("轮到[com.NamePos]了",
								"两个陌生人来到[com.name]身边，准备开始操[com.herHim]……",
								PUBLIC_STOCKS_LOCKED_UP_COMPANION_SECOND_SEX) {
							@Override
							public void effects() {
								banishRandomSexPartners();
								randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(getMainCompanion(), false);
								generateCompanionSexParsingDescriptions();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX_COMPANION", randomSexPartners));
							}
						};
					}
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
						return new Response("等待",
								"你没什么事好做，只能干等着[sean.name]回来把你从颈手枷解下……",
								PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
							@Override
							public void effects() {
								Main.game.getPlayer().performImpregnationCheck(true);
								Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_BREEDING"));
								updateSeanPregnancyReactions();
							}
						};
						
					} else {
						return new Response("等待",
								"你没什么事好做，只能干等着[sean.name]回来把你从颈手枷解下……",
								PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED"));
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_COMPANION_SECOND_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
					return new Response("等待",
							"你没什么事好做，只能干等着[sean.name]回来把你从颈手枷解下……",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
						@Override
						public void effects() {
							Main.game.getPlayer().performImpregnationCheck(true);
							Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);

							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
								getMainCompanion().performImpregnationCheck(true);
								getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
							}
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_BREEDING_WITH_COMPANION"));
							updateSeanPregnancyReactions();
						}
					};
					
				} else {
					return new Response("等待",
							"你没什么事好做，只能干等着[sean.name]回来把你从颈手枷解下……",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
						@Override
						public void effects() {
							banishRandomSexPartners();
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
								getMainCompanion().performImpregnationCheck(true);
								getMainCompanion().clearFluidsStored(SexAreaOrifice.VAGINA);
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_WITH_COMPANION"));
							updateSeanPregnancyReactions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"你已经自由了，可以继续你的路程……",
						PUBLIC_STOCKS) {
					@Override
					public void effects() {
						banishRandomSexPartners();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)) {
							getMainCompanion().equipAllClothingFromHoldingInventory();
						}
						if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_END"));
						}
					}
				};
			}
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				if(index==2) {
					return new Response("更多",
							"告诉[sean.name]你还想在颈手枷里待久一些……",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT,
							Util.newArrayListOfValues(
									Fetish.FETISH_EXHIBITIONIST,
									Fetish.FETISH_SUBMISSIVE,
									Fetish.FETISH_MASOCHIST),
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(Main.game.getPlayer(), false);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("被使用",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)
							?"这两个陌生人准备来使用你了……"
							:"有个陌生人准备来使用你了……",
						false,
						false,
						getRandomPartnerSexManager(),
						null,
						null,
						PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_RANDOMS_START_SEX", randomSexPartners));
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getDescription() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyAcceptedDeal)) {
				return "[sean.name]让你[pc.pussy+]里溢满了精液，完事之后就退了下去。";
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyTwoPartners)) {
				return "陌生人舒服过后就退了下去……";
				
			} else {
				return UtilText.parse(randomSexPartners.get(0), "路人操够了[npc.her]，走开了……");
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX", randomSexPartners);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionInStocks)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.slaverAlleyCompanionAcceptedDeal)) {
				if(index==1) {
					return new Response("轮到[com.NamePos]了",
							"一个路人走到[com.name]身边，开始操[com.herHim]……",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_COMPANION_SEX) {
						@Override
						public void effects() {
							banishRandomSexPartners();
							randomSexPartners = SlaverAlleyDialogue.generateRandomStocksPartners(getMainCompanion(), false);
							generateCompanionSexParsingDescriptions();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_AFTER_SEX_START_SEX_COMPANION", randomSexPartners));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("等待",
							"你没什么事好做，只能干等着[sean.name]回来把你从颈手枷解下……",
							PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PUBLIC_STOCKS_LOCKED_UP_FINISHED_REPEAT_COMPANION_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待",
						"你没什么事好做，只能干等着[sean.name]回来把你从颈手枷解下……",
						PUBLIC_STOCKS_LOCKED_UP_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/genericDialogue", "PUBLIC_STOCKS_LOCKED_UP_FINISHED"));
					}
				};
			}
			return null;
		}
	};
}
