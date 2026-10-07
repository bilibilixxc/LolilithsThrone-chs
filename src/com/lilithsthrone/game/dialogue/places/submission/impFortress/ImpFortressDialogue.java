package com.lilithsthrone.game.dialogue.places.submission.impFortress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.FortressAlphaLeader;
import com.lilithsthrone.game.character.npc.submission.FortressFemalesLeader;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.9
 * @author Innoxia
 */
public class ImpFortressDialogue {

	public static final String FORTRESS_ALPHA_CLEAR_TIMER_ID = "fortress_alpha_clear";
	public static final String FORTRESS_FEMALES_CLEAR_TIMER_ID = "fortress_females_clear";
	public static final String FORTRESS_MALES_CLEAR_TIMER_ID = "fortress_males_clear";

	private static boolean isAlphaFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA;
	}

	private static boolean isFemalesFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_FEMALES;
	}

	private static boolean isMalesFortress() {
		return Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES;
	}

	public static boolean isDarkSirenDefeated() {
		return Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL);
	}

	private static boolean isOralAvailable() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}

	private static boolean isOralAvailableCompanion() {
		return getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	private static AbstractPlaceType getSubmissionFortress() {
		if(isAlphaFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA;
		} else if(isFemalesFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES;
		} else if(isMalesFortress()) {
			return PlaceType.SUBMISSION_IMP_FORTRESS_MALES;
		}
		return null;
	}

	public static boolean isGuardsDefeated() {
		return getImpGuards(Main.game.getPlayer().getWorldLocation()).isEmpty();
	}
	
	private static boolean isGuardsPacified() {
		if(isPacified()) {
			return true;
		}
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsPacified);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesGuardsPacified);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesGuardsPacified);
		}
	}
	
	private static void setGuardsPacified() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesGuardsPacified, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsPacified, true);
		}
	}

	private static boolean isGuardsKnowPlayerDemon() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaGuardsKnowPlayerDemon);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesGuardsKnowPlayerDemon);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesGuardsKnowPlayerDemon);
		}
	}
	
	private static void setGuardsKnowPlayerDemon() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsKnowPlayerDemon, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesGuardsKnowPlayerDemon, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsKnowPlayerDemon, true);
		}
	}
	
	private static boolean isGuardsPacifiedBySex() {
		return isGuardsPacified() && !getImpGuards().isEmpty() && Main.game.getPlayer().getTotalTimesHadSex(getImpGuards().get(0))>0;
	}
	
	private static boolean isPacified() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaPacified);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesPacified);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesPacified);
		}
	}
	
	private static void setPacified() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, true);
		}
	}
	
	private static boolean isDefeated() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaDefeated);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesDefeated);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesDefeated);
		}
	}
	
	private static void setBossEncountered() {
		if(isAlphaFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, true);
		} else if(isFemalesFortress()) {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesBossEncountered, true);
		} else {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesBossEncountered, true);
		}
	}
	
	private static boolean isBossEncountered() {
		if(isAlphaFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressAlphaBossEncountered);
		} else if(isFemalesFortress()) {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressFemalesBossEncountered);
		} else {
			return Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressMalesBossEncountered);
		}
	}

	private static void clearBossGuards() {
		clearBossGuards(Main.game.getPlayer().getWorldLocation());
	}
	
	private static void clearBossGuards(AbstractWorldType fortress) {
		for(GameCharacter character : getImpBossGroup(fortress, true)) {
			if(!character.equals(getBoss(fortress))) {
				Main.game.banishNPC(character.getId());
			}
		}
	}
	
	private static void clearFortress() {
		clearFortress(Main.game.getPlayer().getWorldLocation());
	}
	
	public static void clearFortress(AbstractWorldType fortress) {
		
		banishImpGuards(fortress);
		
		clearBossGuards(fortress);
		
		((NPC) getBoss(fortress)).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));
		
		if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
			getBoss(fortress).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
			
		} else {
			getBoss(fortress).setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_DEMON_KEEP);
		}
		
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			Main.game.getDialogueFlags().setSavedLong(FORTRESS_ALPHA_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
					character.returnToHome();
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			Main.game.getDialogueFlags().setSavedLong(FORTRESS_FEMALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
					character.returnToHome();
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			Main.game.getDialogueFlags().setSavedLong(FORTRESS_MALES_CLEAR_TIMER_ID, Main.game.getMinutesPassed());
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
	
			// Move NPCs out of hiding:
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL)) {
				if(character.getHomeLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
					character.returnToHome();
				}
			}
		}
	}
	
	public static void resetFortress(AbstractWorldType fortress) {
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			// Make sure everything is reset:
			clearFortress(fortress);
			resetGuards(fortress);
			
			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, false);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, false);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaDefeated, true);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaPacified, true);
			}
			
			List<GameCharacter> impGroup = new ArrayList<>();
			try {
				// Boss guards:
				
				impGroup = new ArrayList<>();
				List<String> impAdjectives = new ArrayList<>();
				
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
//				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setGenericName("阿尔法小恶魔弓箭手");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
					impCharacter.clearNonEquippedInventory(true);
					impCharacter.setMoney(50+Util.random.nextInt(100));
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getNpc(FortressAlphaLeader.class).setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP);
			Main.game.getNpc(FortressAlphaLeader.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));

			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
				// Move NPCs into hiding:
				Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
				for(int i=0; i< cells.length;i++) {
					for(int j=0; j< cells[i].length;j++) {
						Cell cell = cells[j][i];
						if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
							for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
								if(!Main.game.getPlayer().getCompanions().contains(character)) {
									character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
									character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
								}
							}
						}
					}
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			// Make sure everything is reset:
			clearFortress(fortress);
			resetGuards(fortress);

			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, false);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, false);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesDefeated, true);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesPacified, true);
			}
			
			List<GameCharacter> impGroup = new ArrayList<>();
			try {
				// Boss guards:
				
				impGroup = new ArrayList<>();
				List<String> impAdjectives = new ArrayList<>();
				
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
//				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setGenericName("阿尔法小恶魔弓箭手");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
					impCharacter.clearNonEquippedInventory(true);
					impCharacter.setMoney(50+Util.random.nextInt(100));
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getNpc(FortressFemalesLeader.class).setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP);
			Main.game.getNpc(FortressFemalesLeader.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));

			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
				// Move NPCs into hiding:
				Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
				for(int i=0; i< cells.length;i++) {
					for(int j=0; j< cells[i].length;j++) {
						Cell cell = cells[j][i];
						if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
							for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
								if(!Main.game.getPlayer().getCompanions().contains(character)) {
									character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
									character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
								}
							}
						}
					}
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			// Make sure everything is reset:
			clearFortress(fortress);
			resetGuards(fortress);

			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, false);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, false);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesDefeated, true);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesPacified, true);
			}
			
			List<GameCharacter> impGroup = new ArrayList<>();
			try {
				// Boss guards:
				
				impGroup = new ArrayList<>();
				List<String> impAdjectives = new ArrayList<>();
				
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
					impCharacter.clearNonEquippedInventory(true);
					impCharacter.setMoney(50+Util.random.nextInt(100));
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Move boss back to fortress:
			Main.game.getNpc(FortressMalesLeader.class).setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP);
			Main.game.getNpc(FortressMalesLeader.class).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.ADD_WEAPONS, EquipClothingSetting.ADD_ACCESSORIES));

			if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_B_SIRENS_CALL)) {
				// Move NPCs into hiding:
				Cell[][] cells = Main.game.getWorlds().get(WorldType.SUBMISSION).getCellGrid();
				for(int i=0; i< cells.length;i++) {
					for(int j=0; j< cells[i].length;j++) {
						Cell cell = cells[j][i];
						if(cell.getPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
							for(GameCharacter character : Main.game.getCharactersPresent(cell)) {
								if(!Main.game.getPlayer().getCompanions().contains(character)) {
									character.setHomeLocation(WorldType.SUBMISSION, character.getLocation());
									character.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static List<GameCharacter> getImpBossGroup(boolean includeBoss) {
		return getImpBossGroup(Main.game.getPlayer().getWorldLocation(), includeBoss);
	}
	
	public static List<GameCharacter> getImpBossGroup(AbstractWorldType fortress, boolean includeBoss) {
		List<GameCharacter> bossGroup = new ArrayList<>();
		
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_KEEP)) {
				if((character instanceof ImpAttacker || (includeBoss?character instanceof FortressAlphaLeader:false)) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_KEEP)) {
				if((character instanceof ImpAttacker || (includeBoss?character instanceof FortressFemalesLeader:false)) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_KEEP)) {
				if((character instanceof ImpAttacker || (includeBoss?character instanceof FortressMalesLeader:false)) && character.getPartyLeader()==null && !character.isSlave()) {
					bossGroup.add(character);
				}
			}
		}
		
		bossGroup.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return bossGroup;
	}

	private static List<GameCharacter> getImpGuards() {
		return getImpGuards(Main.game.getPlayer().getWorldLocation());
	}
		
	public static List<GameCharacter> getImpGuards(AbstractWorldType fortress) {
		
		List<GameCharacter> impGuards = new ArrayList<>();

		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE)) {
				if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
					impGuards.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE)) {
				if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
					impGuards.add(character);
				}
			}
			
		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			for(GameCharacter character : Main.game.getCharactersPresent(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE)) {
				if(character instanceof ImpAttacker && character.getPartyLeader()==null && !character.isSlave()) {
					impGuards.add(character);
				}
			}
		}
		
		impGuards.sort((imp1, imp2) -> imp2.getLevel()-imp1.getLevel());
		return impGuards;
	}

	private static GameCharacter getBoss() {
		return getBoss(Main.game.getPlayer().getWorldLocation());
	}
	
	public static GameCharacter getBoss(AbstractWorldType fortress) {
		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			return Main.game.getNpc(FortressAlphaLeader.class);

		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			return Main.game.getNpc(FortressFemalesLeader.class);

		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			return Main.game.getNpc(FortressMalesLeader.class);
		}
		
		return null;
	}

	private static ImpAttacker getImpGuardLeader() {
		return getImpGuardLeader(Main.game.getPlayer().getWorldLocation());
	}
	
	public static ImpAttacker getImpGuardLeader(AbstractWorldType fortress) {
		return (ImpAttacker) getImpGuards(fortress).get(0);
	}

	private static void banishImpGuards() {
		banishImpGuards(Main.game.getPlayer().getWorldLocation());
	}

	public static void banishImpGuards(AbstractWorldType fortress) {
		for(GameCharacter imp : getImpGuards(fortress)) {
			if(!imp.isSlave() && imp.getPartyLeader()==null) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	public static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static List<GameCharacter> getPartyForSex() {
		if(isCompanionDialogue()) {
			return Util.newArrayListOfValues(Main.game.getPlayer(), getMainCompanion());
		} else {
			return Util.newArrayListOfValues(Main.game.getPlayer());
		}
	}
	
	public static List<GameCharacter> getAllCharacters() {
		// There's a reason I can't just add all from getCharactersPresent(), but I forgot. Maybe it was because the Elemental companion gets added?
		List<GameCharacter> allCharacters = new ArrayList<>();
		
		if(isCompanionDialogue()) {
			allCharacters.add(getMainCompanion());
		}
		
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_ENTRANCE)) {
			allCharacters.addAll(getImpGuards(Main.game.getPlayer().getWorldLocation()));
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_KEEP)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_KEEP)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_KEEP)) {
			allCharacters.add(getBoss());
			allCharacters.addAll(getImpBossGroup(Main.game.getPlayer().getWorldLocation(), false));
		}
		
		return allCharacters;
	}
	
	public static void resetGuards(AbstractWorldType fortress) {
		List<String> impAdjectives = new ArrayList<>();
		List<GameCharacter> impGroup = new ArrayList<>();

		if(fortress==WorldType.IMP_FORTRESS_ALPHA) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				imp.setGenericName("阿尔法小恶魔首领");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_P_V_B_FUTANARI, false);
				imp.setGenericName("阿尔法小恶魔弓箭手");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_shortbow", Util.randomItemFrom(new DamageType[] {DamageType.POISON, DamageType.FIRE})));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_ALPHA, PlaceType.FORTRESS_ALPHA_ENTRANCE, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsPacified, false);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaGuardsKnowPlayerDemon, false);
			}

		} else if(fortress==WorldType.IMP_FORTRESS_FEMALES) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
				imp.setGenericName("阿尔法小恶魔首领");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.F_V_B_FEMALE, false);
				impAdjectives.add(Main.game.getCharacterUtils().setGenericName(imp, impAdjectives));
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_FEMALES, PlaceType.FORTRESS_FEMALES_ENTRANCE, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesGuardsPacified, false);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesGuardsKnowPlayerDemon, false);
			}

		} else if(fortress==WorldType.IMP_FORTRESS_MALES) {
			try {
				ImpAttacker imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				imp.setGenericName("阿尔法小恶魔首领");
				imp.setLevel(12+Util.random.nextInt(3)); // 12-14
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				imp = new ImpAttacker(Subspecies.IMP_ALPHA, Gender.M_P_MALE, false);
				imp.setGenericName("阿尔法小恶魔格斗家");
				imp.setLevel(8+Util.random.nextInt(3)); // 8-10
				Main.game.addNPC(imp, false);
				imp.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_pipe_pipe")));
				imp.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_crudeShield_crude_shield")));
				impGroup.add(imp);
				
				for(GameCharacter impCharacter : impGroup) {
					impCharacter.setLocation(WorldType.IMP_FORTRESS_MALES, PlaceType.FORTRESS_MALES_ENTRANCE, true);
					((NPC)impCharacter).equipClothing(EquipClothingSetting.getAllClothingSettings());
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!isPacified()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsPacified, false);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesGuardsKnowPlayerDemon, false);
			}
		}
	}
	
	public static String getDialogueEncounterId() {
		StringBuilder idSB = new StringBuilder();
		
		if(isAlphaFortress()) {
			// Alpha imp group encounter:
			idSB.append("Alpha");
			
		} else if(isFemalesFortress()) {
			// Female imps encounter:
			idSB.append("Females");
			
		} else {
			// Male imps encounter:
			idSB.append("Males");
		}
		
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		return idSB.toString();
	}
	
	public static String getGuardsDialogueEncounterId() {
		StringBuilder idSB = new StringBuilder();
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		return idSB.toString();
	}
	
	private static List<GameCharacter> getKeepDominantSpectators(SexManagerInterface manager) {
		List<GameCharacter> spectators = new ArrayList<>();
		if(manager.getDominants().containsKey(Main.game.getPlayer())) {
			for(GameCharacter character : getPartyForSex()) {
				if(!manager.getDominants().containsKey(character)) {
					spectators.add(character);
				}
			}
			
		} else {
			for(GameCharacter character : getImpBossGroup(true)) {
				if(!manager.getDominants().containsKey(character)) {
					spectators.add(character);
				}
			}
		}
		return spectators;
	}
	
	private static List<GameCharacter> getKeepSubmissiveSpectators(SexManagerInterface manager) {
		List<GameCharacter> spectators = new ArrayList<>();
		if(manager.getSubmissives().containsKey(Main.game.getPlayer())) {
			for(GameCharacter character : getPartyForSex()) {
				if(!manager.getSubmissives().containsKey(character)) {
					spectators.add(character);
				}
			}
			
		} else {
			for(GameCharacter character : getImpBossGroup(true)) {
				if(!manager.getSubmissives().containsKey(character)) {
					spectators.add(character);
				}
			}
		}
		return spectators;
	}
	
	public static boolean isAlphaBossWantingOral(GameCharacter character) {
		return character.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}

	private static SexManagerInterface getAlphaSexManager(GameCharacter sub1, ResponseTag... tags) {
		return getAlphaSexManager(sub1, null, tags);
	}
	
	private static SexManagerInterface getAlphaSexManager(GameCharacter sub1, GameCharacter sub2, ResponseTag... tags) {
		HashMap<GameCharacter, SexSlot> doms = new HashMap<>();
		HashMap<GameCharacter, SexSlot> subs = new HashMap<>();
		
		boolean doggy = true;
		
		if(sub2!=null) {
			if(isAlphaBossWantingOral(sub1)) {
				if(isAlphaBossWantingOral(sub2)) {
					doggy = false;
					subs = Util.newHashMapOfValues(
							new Value<>(sub1, SexSlotStanding.PERFORMING_ORAL),
							new Value<>(sub2, SexSlotStanding.PERFORMING_ORAL_TWO));
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotStanding.STANDING_DOMINANT));
					
				} else {
					subs = Util.newHashMapOfValues(
							new Value<>(sub1, SexSlotAllFours.ALL_FOURS),
							new Value<>(sub2, SexSlotAllFours.ALL_FOURS_TWO));
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotAllFours.IN_FRONT),
							new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND),
							new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.IN_FRONT_TWO),
							new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.BEHIND_TWO));
				}
					
			} else if(isAlphaBossWantingOral(sub2)) {
				doms = Util.newHashMapOfValues(
						new Value<>(getBoss(), SexSlotAllFours.IN_FRONT_TWO),
						new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND_TWO),
						new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.IN_FRONT),
						new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.BEHIND));
				
			} else {
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND),
						new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.BEHIND_TWO),
						new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.HUMPING));
			}
			
		} else {
			if(isAlphaBossWantingOral(sub1)) {
				doggy = false;
				subs = Util.newHashMapOfValues(new Value<>(sub1, SexSlotStanding.PERFORMING_ORAL));
				doms = Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotStanding.STANDING_DOMINANT));
					
			} else {
				subs = Util.newHashMapOfValues(new Value<>(sub1, SexSlotAllFours.ALL_FOURS));
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotAllFours.BEHIND),
						new Value<>(getImpBossGroup(false).get(1), SexSlotAllFours.BEHIND_TWO),
						new Value<>(getImpBossGroup(false).get(2), SexSlotAllFours.HUMPING));
			}
		}
		
		if(doggy) {
			return new SMAllFours(doms, subs) {
				@Override
				public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
					if(getDominants().containsKey(getBoss())) {
						Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
						map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS));
						return map;
					}
					return super.exposeAtStartOfSexMap();
				}
				@Override
				public SexPace getStartingSexPaceModifier(GameCharacter character) {
					if(character.isPlayer()) {
						for(ResponseTag tag : tags) {
							if(tag!=null) {
								switch(tag) {
									case START_PACE_PLAYER_DOM_GENTLE:
										return SexPace.DOM_GENTLE;
									case START_PACE_PLAYER_DOM_ROUGH:
										return SexPace.DOM_ROUGH;
									case START_PACE_PLAYER_SUB_RESISTING:
										return SexPace.SUB_RESISTING;
									case START_PACE_PLAYER_SUB_EAGER:
										return SexPace.SUB_EAGER;
									case PREFER_ORAL:
									case PREFER_MISSIONARY:
									case PREFER_DOGGY:
									case PREFER_COW_GIRL:
									case DISABLE_POSITIONING:
										break;
								}
							}
						}
					}
					return null;
				}
			};
			
		} else {
			return new SMStanding(doms, subs) {
				@Override
				public boolean isPositionChangingAllowed(GameCharacter character) {
					return false;
				}
				@Override
				public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
					if(getDominants().containsKey(getBoss())) {
						Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
						map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS));
						return map;
					}
					return super.exposeAtStartOfSexMap();
				}
				@Override
				public SexPace getStartingSexPaceModifier(GameCharacter character) {
					if(character.isPlayer()) {
						for(ResponseTag tag : tags) {
							if(tag!=null) {
								switch(tag) {
									case START_PACE_PLAYER_DOM_GENTLE:
										return SexPace.DOM_GENTLE;
									case START_PACE_PLAYER_DOM_ROUGH:
										return SexPace.DOM_ROUGH;
									case START_PACE_PLAYER_SUB_RESISTING:
										return SexPace.SUB_RESISTING;
									case START_PACE_PLAYER_SUB_EAGER:
										return SexPace.SUB_EAGER;
									case PREFER_ORAL:
									case PREFER_MISSIONARY:
									case PREFER_DOGGY:
									case PREFER_COW_GIRL:
									case DISABLE_POSITIONING:
										break;
								}
							}
						}
					}
					return null;
				}
			};
		}
	}
	
	
	
	public static boolean isMaleBossWantingToBreed(GameCharacter character) {
		return !character.isVisiblyPregnant() && character.hasVagina() && character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}

	private static SexManagerInterface getMalesSexManager(GameCharacter sub1, ResponseTag... tags) {
		return getMalesSexManager(sub1, null, tags);
	}
	
	private static SexManagerInterface getMalesSexManager(GameCharacter sub1, GameCharacter sub2, ResponseTag... tags) {
		HashMap<GameCharacter, SexSlot> doms = new HashMap<>();
		HashMap<GameCharacter, SexSlot> subs = new HashMap<>();
		
		if(sub2!=null) {
			subs = Util.newHashMapOfValues(
					new Value<>(sub1, SexSlotLyingDown.LYING_DOWN),
					new Value<>(sub2, SexSlotLyingDown.LYING_DOWN_TWO));
			
			if(isMaleBossWantingToBreed(sub1)) {
				if(isMaleBossWantingToBreed(sub2)) {
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY));
				} else {
					doms = Util.newHashMapOfValues(
							new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
							new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY_TWO),
							new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
							new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO));
				}
				
			} else if(isMaleBossWantingToBreed(sub2)) {
				doms = Util.newHashMapOfValues(
						new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY_TWO),
						new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY),
						new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
						new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO));
					
			} else {
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY),
						new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
						new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING));
			}
			
		} else {
			subs = Util.newHashMapOfValues(new Value<>(sub1, SexSlotLyingDown.LYING_DOWN));
			
			if(isMaleBossWantingToBreed(sub1)) {
				doms = Util.newHashMapOfValues(
						new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY));
				
			} else {
				doms = Util.newHashMapOfValues(
						new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.MISSIONARY),
						new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
						new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO));
			}
		}
		
		return new SMLyingDown(doms, subs) {
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					for(ResponseTag tag : tags) {
						if(tag!=null) {
							switch(tag) {
								case START_PACE_PLAYER_DOM_GENTLE:
									return SexPace.DOM_GENTLE;
								case START_PACE_PLAYER_DOM_ROUGH:
									return SexPace.DOM_ROUGH;
								case START_PACE_PLAYER_SUB_RESISTING:
									return SexPace.SUB_RESISTING;
								case START_PACE_PLAYER_SUB_EAGER:
									return SexPace.SUB_EAGER;
								case PREFER_ORAL:
								case PREFER_MISSIONARY:
								case PREFER_DOGGY:
								case PREFER_COW_GIRL:
								case DISABLE_POSITIONING:
									break;
							}
						}
					}
				}
				return null;
			}
		};
	}
	
	
	
	// Dialogues:
	
	public static final DialogueNode ENTRANCE = new DialogueNode("大门", "", false) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().getRace()==Race.DEMON) {
				setGuardsKnowPlayerDemon();
			}
		}
		@Override
		public boolean isTravelDisabled() {
			return !isGuardsPacified() && !isGuardsDefeated();
		}
		
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isGuardsDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DESERTED", getAllCharacters()));
				if(!isDefeated()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DESERTED_GUARD_RETURN_WARNING", getAllCharacters()));
				}
				
			} else if(isGuardsPacifiedBySex()) {
				if(Main.game.getPlayer().getRace()==Race.DEMON) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED_BY_SEX_DEMON", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED_BY_SEX", getAllCharacters()));
				}
				
			} else if(isGuardsPacified()) {
				if(Main.game.getPlayer().getRace()==Race.DEMON || isGuardsKnowPlayerDemon()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED_DEMON", getAllCharacters()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_PACIFIED", getAllCharacters()));
				}
				
			} else if(Main.game.getPlayer().getRace()==Race.DEMON) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DEMON", getAllCharacters()));
				
			} else if(Main.game.getPlayer().isElementalSummoned()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL",
						Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE", getAllCharacters()));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isGuardsPacified() || isGuardsDefeated()) {
				if (index == 1) {
					return new Response("离开", "回到隧道里去。", getSubmissionFortress().getDialogue(false)) {
						@Override
						public void effects() {
							if(isGuardsDefeated() && !isDefeated()) {
								resetGuards(Main.game.getPlayer().getWorldLocation());
							}
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "LEAVE_FORTRESS", getAllCharacters()));
						}
					};
	
				} else if(!isGuardsDefeated()) {
					if(index==2) {
						if(!isOralAvailable()) {
							return new Response(isCompanionDialogue()?"口交(单人)":"口交", "因为你不能使用你的嘴，所以你没法给小恶魔口交！", null);
						}
						return new ResponseSex(isCompanionDialogue()?"提供口交(单人)":"提供口交",
								isGuardsPacifiedBySex()
									?"同意小恶魔的嘲弄，并再次为他们口交。"
									:"主动为小恶魔口交。",
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
								GUARDS_AFTER_ORAL_FOR_ENTRY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_PACIFIED", getAllCharacters()),
								ResponseTag.PREFER_ORAL,
								ResponseTag.DISABLE_POSITIONING) {
							@Override
							public void effects() {
								setGuardsPacified();
							}
						};
						
					} else if(index==3 && !isGuardsDefeated() && isCompanionDialogue()) {
						if(!isOralAvailable()) {
							return new Response("口交(双人)", "因为你不能使用你的嘴，你不能给小恶魔口交！", null);
						}
						if(!isOralAvailableCompanion()) {
							return new Response("提供口交(双人)", UtilText.parse(getMainCompanion(), "因为[npc.nameIsFull]不能使用[npc.her]的嘴，[npc.she]没法给小恶魔口交！"), null);
						}
						if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
							return new Response("提供口交(双人)",
									UtilText.parse(getMainCompanion(), "[npc.Name]对为小恶魔口交不感兴趣，而且[npc.sheIs]不是奴隶，你不能强迫[npc.herHim]这么做……"), null);
							
						} else {
							return new ResponseSex("提供口交(双人)",
									isGuardsPacifiedBySex()
										?UtilText.parse(getMainCompanion(), "同意小恶魔的嘲讽，并告诉他们你和[npc.name]愿意再次为他们口交。")
										:UtilText.parse(getMainCompanion(), "告诉小恶魔，你和[npc.name]都想为他们口交。"),
									true,
									false,
									getImpGuards(),
									getPartyForSex(),
									null,
									null,
									GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
									UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_PACIFIED_WITH_COMPANION", getAllCharacters()),
									ResponseTag.PREFER_ORAL,
									ResponseTag.DISABLE_POSITIONING) {
								@Override
								public void effects() {
									setGuardsPacified();
								}
							};
						}
						
					} else if(isCompanionDialogue()?index==4:index==3) {
						return new ResponseCombat("袭击", "改变不想与小恶魔战斗的想法，你决定给他们一个教训！", getImpGuardLeader(), getImpGuards(), null);
					}
				}
				return null;
				
			} else {
				if (index == 1) {
					if(Main.game.getPlayer().getTrueRace()==Race.DEMON) {
						return new Response("指令",
								Main.game.getPlayer().getRace()==Race.DEMON
									?"面对恶魔，小恶魔们显得异常紧张。利用这一点，命令它们靠边站。"
									:"向小恶魔们展示你真正的恶魔形态，命令他们靠边站。",
								ENTRANCE_DEMONIC_COMMAND) {
							@Override
							public void effects() {
								setGuardsPacified();
								setGuardsKnowPlayerDemon();
							}
						};
						
					} else {
						return new Response("指令",
								"如果你是恶魔，那么你可能可以恐吓这些小恶魔，但你不是。所以你只能和他们战斗了……",
								null);
					}
	
				} else if(index==2) {
					if(Main.game.getPlayer().isElementalSummoned()) {
						return new Response("元素",
								UtilText.parse("让小恶魔们注意到，你的力量强大到足以召唤出一个元素与你在一起，以此来吓唬他们……"), ENTRANCE_ELEMENTAL) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_INTIMIDATE",
										Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
								setGuardsPacified();
							}
						};
						
					} else if((Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_AIR) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_AIR.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_ARCANE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_ARCANE.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_FIRE) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_FIRE.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_EARTH) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_EARTH.getModifiedCost(Main.game.getPlayer()))
							|| (Main.game.getPlayer().hasSpell(Spell.ELEMENTAL_WATER) && Main.game.getPlayer().getMana()>=Spell.ELEMENTAL_WATER.getModifiedCost(Main.game.getPlayer()))) {
						return new Response("元素体", UtilText.parse(getBoss(), "在小恶魔面前召唤出你的元素体，威慑他们。"), ENTRANCE_ELEMENTAL) {
							@Override
							public void effects() {
								List<Spell> elementalSpells = Util.newArrayListOfValues(Spell.ELEMENTAL_AIR, Spell.ELEMENTAL_ARCANE, Spell.ELEMENTAL_FIRE, Spell.ELEMENTAL_EARTH, Spell.ELEMENTAL_WATER);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_SUMMON",
										Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
								for(Spell spell : elementalSpells) {
									if(Main.game.getPlayer().hasSpell(spell) && Main.game.getPlayer().getMana()>=spell.getModifiedCost(Main.game.getPlayer())) {
										Main.game.getTextStartStringBuilder().append(spell.applyEffect(Main.game.getPlayer(), Main.game.getPlayer(), true, false));
										break;
									}
								}
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_SUMMON_END",
										Util.mergeLists(Util.newArrayListOfValues(Main.game.getPlayer().getElemental()), getImpGuards())));
								setGuardsPacified();
							}
						};
						
					} else {
						return new Response("元素体", UtilText.parse(getBoss(), "如果你想威慑[npc.Name]，那么你需要有一只已召唤的元素体，或者知道法术并且有足够的灵气来施放……"), null);
					}
					
				} else if(index==3) {
					return new ResponseCombat("袭击", "自卫，攻击小恶魔！", getImpGuardLeader(), getImpGuards(), null);
					
				} else if(index==4) {
					if(!isOralAvailable()) {
						return new Response(isCompanionDialogue()?"口交(单人)":"口交", "因为你不能使用你的嘴，所以你没法给小恶魔口交！", null);
					}
					return new ResponseSex(isCompanionDialogue()?"提供口交(单人)":"提供口交",
							"主动为小恶魔口交，以换取他们让你进入要塞。",
							true,
							false,
							getImpGuards(),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
							GUARDS_AFTER_ORAL_FOR_ENTRY,
							UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL", getAllCharacters()),
							ResponseTag.PREFER_ORAL,
							ResponseTag.DISABLE_POSITIONING) {
						@Override
						public void effects() {
							setGuardsPacified();
						}
					};
					
				} else if(index==5 && isCompanionDialogue()) {
					if(!isOralAvailable()) {
						return new Response("口交(双人)", "因为你不能使用你的嘴，你不能给小恶魔口交！", null);
					}
					if(!isOralAvailableCompanion()) {
						return new Response("提供口交(双人)", UtilText.parse(getMainCompanion(), "因为[npc.nameIsFull]不能使用[npc.her]的嘴，[npc.she]没法给小恶魔口交！"), null);
					}
					if(!getMainCompanion().isAttractedTo(getImpGuardLeader()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response("提供口交(双人)",
								UtilText.parse(getMainCompanion(), "[npc.Name]对为小恶魔口交不感兴趣，而且[npc.sheIs]不是奴隶，你不能强迫[npc.herHim]这么做……"), null);
						
					} else {
						return new ResponseSex("提供口交(双人)",
								UtilText.parse(getMainCompanion(), "告诉小恶魔们，你和[npc.name]将为他们口交，以换取让你们两个进入要塞。"),
								true,
								false,
								getImpGuards(),
								getPartyForSex(),
								null,
								null,
								GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_GIVE_ORAL_WITH_COMPANION", getAllCharacters()),
								ResponseTag.PREFER_ORAL,
								ResponseTag.DISABLE_POSITIONING) {
							@Override
							public void effects() {
								setGuardsPacified();
							}
						};
					}
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode GUARDS_AFTER_ORAL_FOR_ENTRY = new DialogueNode("结束", "。", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_ORAL_FOR_ENTRY", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION = new DialogueNode("结束", "。", false) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_ORAL_FOR_ENTRY_WITH_COMPANION", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE_DEMONIC_COMMAND = new DialogueNode("", "。", false, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_DEMONIC_COMMAND", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};

	
	public static final DialogueNode ENTRANCE_ELEMENTAL = new DialogueNode("主楼", "", true, true) {
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
			if(index==1) {
				return new Response("继续", "现在，这些小恶魔已经被威慑住，你可以继续前往要塞。", Main.game.getDefaultDialogue()) {
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "ENTRANCE_ELEMENTAL_CONTINUE", getAllCharacters()));
						if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA) {
							Main.game.getPlayer().setNearestLocation(PlaceType.FORTRESS_ALPHA_COURTYARD);
						} else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES) {
							Main.game.getPlayer().setNearestLocation(PlaceType.FORTRESS_MALES_COURTYARD);
						} else {
							Main.game.getPlayer().setNearestLocation(PlaceType.FORTRESS_FEMALES_COURTYARD);
						}
					}
					@Override
					public DialogueNode getNextDialogue() {
						if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA) {
							return PlaceType.FORTRESS_ALPHA_COURTYARD.getDialogue(false);
						} else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES) {
							return PlaceType.FORTRESS_MALES_COURTYARD.getDialogue(false);
						} else {
							return PlaceType.FORTRESS_FEMALES_COURTYARD.getDialogue(false);
						}
					}
				};
			}
			return null;
		}
	};

	
	public static final DialogueNode GUARDS_AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "。", true) {

		@Override
		public String getDescription() {
			return "你已击败这些小恶魔！";
		}

		@Override
		public String getContent() {
			if(getImpGuards().isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY_ALL_ENSLAVED", getAllCharacters());
			}
			return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGuards().isEmpty()) {
				if(index==0) {
					return "互动";
					
				} else if(index==1) {
					return "物品栏";
					
				} else if(index==2) {
					return "转化";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGuards().isEmpty()) {
				if(index==1) {
					return new Response("继续", "既然你已经奴役了小恶魔守卫，那就没什么可做的了，只能继续进入要塞……", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("吓跑", "让小恶魔们赶紧滚开这里。", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("做爱",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								getPartyForSex(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("做爱(温柔)",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								getPartyForSex(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("做爱(粗暴)",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								getPartyForSex(),
								getImpGuards(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("顺从",
								"你不太确定现在该做什么……也许最好让小恶魔来决定接下来做什么……",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGuards(),
								getPartyForSex(),
								null,
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {

				if(responseTab == 0) {
					if (index == 1) {
						return new Response("吓跑", "让小恶魔们赶紧滚开……", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								banishImpGuards();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("单人做爱",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("单人做爱(温柔)",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("单人做爱(粗暴)",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGuards(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("单人屈从",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你臣服于小恶魔，让他们与你发生支配型性爱。"),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGuards(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								GUARDS_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群交",
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群交"),
									UtilText.parse(companion, "与小恶魔进行支配型性爱，并让[npc.Name]也加入其中找找乐子。"),
									true,
									false,
									getPartyForSex(),
									getImpGuards(),
									null,
									null,
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群体屈从",
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群体屈从"),
									UtilText.parse(companion, "让[npc.name]和你一起臣服于小恶魔，让他们和你俩发生支配型性爱。"),
									true,
									false,
									getImpGuards(),
									getPartyForSex(),
									null,
									null,
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "给[npc.name]"),
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
									UtilText.parse(companion, "告诉[npc.Name]让[npc.she]和小恶魔玩玩，你在旁边看着。"),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGuards(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpGuardLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, "你看出[npc.name]一点也不想跟小恶魔做爱，你也没法逼[npc.herHim]去做……"),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, "将[npc.Name]交给小恶魔，然后看着他们与[npc.herHim]发生性关系。"),
									true,
									false,
									getImpGuards(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									GUARDS_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpGuardLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGuards().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGuards().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			}
		}
	};

	public static final DialogueNode GUARDS_AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "。", true) {

		@Override
		public int getSecondsPassed() {
			return Main.game.isNonConEnabled()?1:15*60;
		}
		
		@Override
		public String getDescription() {
			return "你已被小恶魔们击败！";
		}

		@Override
		public String getContent() {
			if(Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_THROWN_OUT", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new ResponseSex("做爱",
							"让小恶魔把你移到合适的位置……",
							false,
							false,
							getImpGuards(),
							getPartyForSex(),
							null,
							null,
							GUARDS_AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							"在这群小恶魔跟你摆好姿势的时候，表现得十分饥渴……",
							false,
							false,
							getImpGuards(),
							getPartyForSex(),
							null,
							null,
							GUARDS_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							"在这群小恶魔跟你摆好姿势的时候，奋力抵抗……",
							false,
							false,
							getImpGuards(),
							getPartyForSex(),
							null,
							null,
							GUARDS_AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
				}
				
			} else {
				if (index == 1) {
					return new Response("扔出去", "小恶魔们把你扔了出来，回到了他们的要塞……", getSubmissionFortress().getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
						}
					};
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARDS_AFTER_SEX_VICTORY = new DialogueNode("退开", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，于是退到一旁，让这群小恶魔恢复过来后，自行散去了。";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "GUARDS_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return GUARDS_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImpGuards();
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARDS_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARDS_AFTER_SEX_DEFEAT = new DialogueNode("瘫软", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "你在[npc.namePos]的支配下精疲力竭，需要休息一会儿。";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortressImpGuards"+getGuardsDialogueEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", getSubmissionFortress().getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode COURTYARD = new DialogueNode("庭院", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "COURTYARD", getAllCharacters()));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPartyAbleToFly()) {
					return new Response("飞行",
							(isDefeated()
									|| isPacified())
								?"因为你只需从正门平和地离开，所以没有必要这样做，除非你只是想炫耀一下……"
								:"飞越要塞的围墙，避免与门口的小恶魔守卫发生冲突。",
							getSubmissionFortress().getDialogue(false)) {
						@Override
						public void effects() {
							if(isGuardsDefeated() && !isDefeated()) {
								resetGuards(Main.game.getPlayer().getWorldLocation());
							}
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "IMP_FORTRESS_FLY_EXIT"));
							Main.game.getPlayer().setLocation(WorldType.SUBMISSION, getSubmissionFortress());
						}
					};
				} else {
					return new Response("飞行", "你或你队伍中的一员无法飞行，你不能飞越围墙进入要塞！", null);
				}	
			}
			return null;
		}
	};

	public static final DialogueNode KEEP = new DialogueNode("主楼", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			if(isPacified() && Main.game.getCharactersPresent().contains(getBoss())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED", getAllCharacters()));
			} else if(isDefeated()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_DEFEATED", getAllCharacters()));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isDefeated() && !Main.game.getCharactersPresent().contains(getBoss())) {
					return new Response("进入", "城堡空无一人，里面没有什么值钱的东西……", null);
				} else {
					return new Response("进入", "推开主楼的门走进去。", KEEP_ENTRY) {
						@Override
						public void effects() {
							getBoss().setPlayerKnowsName(true);
						}
					};
				}
			}
			return null;
		}
	};

	private static AbstractWeapon getSuitableWeaponForCutting() {
		AbstractWeapon suitableWeapon = null;
		
		for(AbstractWeapon weapon : Main.game.getPlayer().getMainWeaponArray()) {
			if(weapon!=null
					&& weapon.getWeaponType().getItemTags().contains(ItemTag.WEAPON_BLADE)
					&& Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, weapon)
						>Attack.getMaximumDamage(Main.game.getNpc(FortressMalesLeader.class), null, Attack.MAIN, Main.game.getNpc(FortressMalesLeader.class).getMainWeapon(0))) {
				suitableWeapon = weapon;
				break;
			}
		}
		if(suitableWeapon==null) {
			for(AbstractWeapon weapon : Main.game.getPlayer().getOffhandWeaponArray()) {
				if(weapon!=null
						&& weapon.getWeaponType().getItemTags().contains(ItemTag.WEAPON_BLADE)
						&& Attack.getMaximumDamage(Main.game.getPlayer(), null, Attack.MAIN, weapon)
							>Attack.getMaximumDamage(Main.game.getNpc(FortressMalesLeader.class), null, Attack.MAIN, Main.game.getNpc(FortressMalesLeader.class).getMainWeapon(0))) {
					suitableWeapon = weapon;
					break;
				}
			}
		}
		
		return suitableWeapon;
	}
	
	public static final DialogueNode KEEP_ENTRY = new DialogueNode("主楼", "。", true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isPacified()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED", getAllCharacters()));
				if(isAlphaFortress()) {
					if(isCompanionDialogue()) {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							if(isAlphaBossWantingOral(getMainCompanion())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_ORAL", getAllCharacters()));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_ORAL_COMPANION_IMPS", getAllCharacters()));
							}
						} else if(isAlphaBossWantingOral(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS_COMPANION_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_IMPS", getAllCharacters()));
						}
					} else {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS", getAllCharacters()));
						}
					}
					
				} else if(isMalesFortress()) {
					if(isCompanionDialogue()) {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							if(isMaleBossWantingToBreed(getMainCompanion())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_BREEDING", getAllCharacters()));
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BREEDING_COMPANION_IMPS", getAllCharacters()));
							}
						} else if(isMaleBossWantingToBreed(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS_COMPANION_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BOTH_IMPS", getAllCharacters()));
						}
					} else {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_PACIFIED_DEMAND_IMPS", getAllCharacters()));
						}
					}
				}

				
			} else if(isBossEncountered()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_RETURN", getAllCharacters()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isPacified()) {
				if(index==1) {
					return new Response("离开", UtilText.parse(getBoss(), "告诉[npc.name]你只是来看看[npc.herHim]，然后迅速离开。"), KEEP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_LEAVE", getAllCharacters()));
						}
					};
					
				} else if(index==2) {
					String title = "做爱";
					
					if(isAlphaFortress()) {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							title = isCompanionDialogue()?"吮吸鸡巴 (单人)":"吮吸鸡巴";
						} else {
							title = isCompanionDialogue()?"小恶魔性交 (单人)":"小恶魔性交";
						}
						
					} else if(isFemalesFortress()) {
						title = isCompanionDialogue()?"玩物(单人)":"玩物";
						
					} else if(isMalesFortress()) {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							title = isCompanionDialogue()?"繁殖 (单人)":"繁殖";
						} else {
							title = isCompanionDialogue()?"小恶魔性交 (单人)":"小恶魔性交";
						}
					}
					
					if(isAlphaFortress()) {
						SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
						return new ResponseSex(title,
								UtilText.parse(getBoss(), "听从[npc.name]的命令，再次臣服于[npc.herHim]……"),
								true,
								false,
								manager,
								getKeepDominantSpectators(manager),
								getKeepSubmissiveSpectators(manager),
								KEEP_AFTER_SEX_PACIFIED,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_PACIFIED_REPEAT_SEX":"KEEP_PACIFIED_REPEAT_SEX_IMPS", getAllCharacters()));
						
					} else if(isMalesFortress()) {
						SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
						return new ResponseSex(title,
								UtilText.parse(getBoss(), "听从[npc.name]的命令，再次臣服于[npc.herHim]……"),
								true,
								false,
								manager,
								getKeepDominantSpectators(manager),
								getKeepSubmissiveSpectators(manager),
								KEEP_AFTER_SEX_PACIFIED,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_PACIFIED_REPEAT_SEX":"KEEP_PACIFIED_REPEAT_SEX_IMPS", getAllCharacters()));
					}
					return new ResponseSex(title,
							UtilText.parse(getBoss(), "按[npc.name]的命令做，准备再次和[npc.herHim]做服从型性爱……"),
							true,
							false,
							new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
										new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
										new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
										new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							Main.game.getPlayer().getCompanions(),
							KEEP_AFTER_SEX_PACIFIED,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_REPEAT_SEX", getAllCharacters())){
						@Override
						public void effects() {
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
						}
					};
					
				} else if(index==3 && isCompanionDialogue()) {
					String title = "做爱";
					
					if(isAlphaFortress()) {
						if(isAlphaBossWantingOral(Main.game.getPlayer())) {
							title = "吮吸肉棒(双人)";
						} else {
							title = "与小恶魔做爱(双人)";
						}
						
					} else if(isFemalesFortress()) {
						title = "玩物(双人)";
						
					} else if(isMalesFortress()) {
						if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
							title = "开始被授种(双人)";
						} else {
							title = "与小恶魔做爱(双人)";
						}
					}
					
					
					if(!getMainCompanion().isAttractedTo(getBoss()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(title,
								UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name]没兴趣与[npc2.name]做爱，并且[npc.sheIs]不是奴隶，你也不能逼[npc.herHim]这样做……"), null);
						
					} else {
						if(isAlphaFortress()) {
							SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
							return new ResponseSex(title,
									UtilText.parse(getBoss(), getMainCompanion(), "按[npc.Name]的命令做，让[npc2.name]和你一起屈服于[npc.herHim]……"),
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_PACIFIED,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isAlphaBossWantingOral(Main.game.getPlayer())
											?isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_ORAL"
												:"KEEP_PACIFIED_REPEAT_SEX_ORAL_COMPANION_IMPS"
											:isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_IMPS_COMPANION_ORAL"
												:"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_IMPS",
										getAllCharacters()));
							
						} else if(isMalesFortress()) {
							SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
							return new ResponseSex(title,
									UtilText.parse(getBoss(), getMainCompanion(), "按[npc.Name]的命令做，让[npc2.name]和你一起屈服于[npc.herHim]……"),
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_PACIFIED,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isMaleBossWantingToBreed(Main.game.getPlayer())
											?isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_BREEDING"
												:"KEEP_PACIFIED_REPEAT_SEX_BREEDING_COMPANION_IMPS"
											:isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_PACIFIED_REPEAT_SEX_IMPS_COMPANION_BREEDING"
												:"KEEP_PACIFIED_REPEAT_SEX_DOUBLE_IMPS",
										getAllCharacters()));
						}
						return new ResponseSex(title,
								UtilText.parse(getMainCompanion(), getBoss(), "按[npc2.name]的命令做，你和[npc.Name]都准备再次与[npc2.herHim]做服从型性爱……"),
								true,
								false,
								new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
												new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO))),
								null,
								null,
								KEEP_AFTER_SEX_PACIFIED,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_PACIFIED_REPEAT_SEX_WITH_COMPANION", getAllCharacters())){
							@Override
							public void effects() {
								((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else {
				boolean darkSirenActionAvailable = !isDarkSirenDefeated()
						&& (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impCitadelEncountered) || Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_A_INTO_THE_DEPTHS));

				if (index == 1) {
					if(isAlphaFortress()) {
						if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.MARTIAL_ARTIST)) {
							return new Response(Util.capitaliseSentence(Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())),
									UtilText.parse(getBoss(),
											"抓住稍纵即逝的机会，激惹[npc.Name]揍你，"
													+ "靠你作为<b style='color:"+PresetColour.TRAIT.toWebHexString()+";'>"+Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())+"的技巧</b>"
															+ "在[npc.her]的小恶魔跟班面前羞辱[npc.herHim]。"),
									KEEP_ALPHA_BRAWLER) {
								@Override
								public void effects() {
									try {
										getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.TORSO_OVER), true, getBoss());
									} catch(Exception ex) {
									}
									if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_KEY", getAllCharacters()));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY), false));
									} else if(!isDarkSirenDefeated()) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_DEFEATED", getAllCharacters()));
									} else {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_DEFEATED_DS_DEALT_WITH", getAllCharacters()));
									}
									clearBossGuards();
									setBossEncountered();
								}
							};
							
						} else {
							return new Response(Util.capitaliseSentence(Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())),
									UtilText.parse(getBoss(),
											"你没有足够的实力在[npc.her]的帮派面前羞辱[npc.Name]……</br>"
													+ "(需要“"+Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())+"”天赋。)"),
									null);
						}
						
					} else if(isFemalesFortress()) {
						if(Main.game.getPlayer().hasTraitActivated(Perk.NYMPHOMANIAC)) {
							return new Response("真正的女色鬼",
									UtilText.parse(getBoss(),
											"抓住稍纵即逝的机会，削弱[npc.namePos]的权威，借机描述[npc.her]的帮派多么有趣"
													+ "<b style='color:"+PresetColour.TRAIT.toWebHexString()+";'>色鬼</b>活动于屈城区的隧道中。"),
									KEEP_FEMALES_NYMPHO) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO", getAllCharacters()));
									if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_KEY", getAllCharacters()));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3), false));
									} else if(!isDarkSirenDefeated()) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_DEFEATED", getAllCharacters()));
									} else {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_DEFEATED_DS_DEALT_WITH", getAllCharacters()));
									}
									clearBossGuards();
									setBossEncountered();
								}
							};
							
						} else {
							return new Response("真正的女色鬼",
									UtilText.parse(getBoss(), "你没有[npc.name]那么性饥渴，所以不能在[npc.her]的小恶魔面前削弱[npc.her]的权威……</br>(需要“性瘾成狂”天赋。)"),
									null);
						}
						
					} else {
						if(getSuitableWeaponForCutting()!=null) {
							return new Response("试斩",
									UtilText.parse(getBoss(),
											"抓住稍纵即逝的机会，在[npc.Name]背后的大竹竿上“试斩”，以此在[npc.her]的小恶魔跟班面前耍威风。"),
									KEEP_MALES_TAMESHIGIRI_MAIN) {
								@Override
								public void effects() {
									setBossEncountered();
									if(!Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2) && !Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_KEY", getAllCharacters()));
										Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2), false));
									} else if(!isDarkSirenDefeated()) {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_DEFEATED", getAllCharacters()));
									} else {
										Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_DEFEATED_DS_DEALT_WITH", getAllCharacters()));
									}
								}
							};
							
						} else {
							return new Response("试斩",
									UtilText.parse(getBoss(), "你认为只凭你的武器无法匹敌[npc.namePos]的威力……</br>"
											+ "(需要你装备了最大伤害大于<b>"
												+Attack.getMaximumDamage(Main.game.getNpc(FortressMalesLeader.class), null, Attack.MAIN, Main.game.getNpc(FortressMalesLeader.class).getMainWeapon(0))+"</b>的带刃武器。)"),
									null);
						}
					}
					
				} else if (index == 2 && darkSirenActionAvailable) {

					if((isAlphaFortress() && Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY))
							|| (isMalesFortress() && Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_2))
							|| (isFemalesFortress() && Main.game.getPlayer().hasItemType(ItemType.IMP_FORTRESS_ARCANE_KEY_3))) {
						return new Response("暗夜塞壬",
								UtilText.parse(getBoss(), "你已经从[npc.name]那拿到钥匙了！"),
								null);
					}
					return new Response("暗夜塞壬",
							UtilText.parse(getBoss(), "告诉[npc.Name]你想见“暗夜塞壬”，并询问[npc.herHim]如何才能获得他们的青睐。"),
							KEEP_AUDIENCE) {
						@Override
						public void effects() {
							setBossEncountered();
						}
					};

				} else if(darkSirenActionAvailable?index==3:index==2) {
					return new ResponseCombat("袭击", UtilText.parse(getBoss(), "自卫，攻击[npc.Name]和[npc.her]的爪牙！"),
							(NPC) getBoss(),
							getImpBossGroup(true), null) {
						@Override
						public void effects() {
							setBossEncountered();
						}
					};

				} else if(darkSirenActionAvailable?index==4:index==3) {
					if(isAlphaFortress()) {
						SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
						return new ResponseSex("投降",
								isCompanionDialogue()
									?UtilText.parse(getMainCompanion(), getBoss(), "你与[npc.name]一起向[npc2.name]投降，允许[npc2.herHim]和[npc2.her]的小恶魔随意拿你们取乐。")
									:UtilText.parse(getBoss(), "以你的身体向[npc.Name]和[npc.her]的小恶魔投降，以此不战斗就能离开。"),
								true,
								false,
								manager,
								getKeepDominantSpectators(manager),
								getKeepSubmissiveSpectators(manager),
								KEEP_AFTER_SEX_DEFEAT,
								isCompanionDialogue()
									?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())
												?isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_ENTRY_OFFER_SEX_DOUBLE_ORAL"
													:"KEEP_ENTRY_OFFER_SEX_ORAL_COMPANION_IMPS"
												:isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_ENTRY_OFFER_SEX_IMPS_COMPANION_ORAL"
													:"KEEP_ENTRY_OFFER_SEX_DOUBLE_IMPS",
											getAllCharacters())
									:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())
												?"KEEP_ENTRY_OFFER_SEX"
												:"KEEP_ENTRY_OFFER_SEX_IMPS",
											getAllCharacters())){
							@Override
							public void effects() {
								setBossEncountered();
							}
						};
						
					} else if(isMalesFortress()) {
						SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
						return new ResponseSex("投降",
										isCompanionDialogue()
											?UtilText.parse(getMainCompanion(), getBoss(), "你与[npc.name]一起向[npc2.name]投降，允许[npc2.herHim]和[npc2.her]的小恶魔随意拿你们取乐。")
											:UtilText.parse(getBoss(), "以你的身体向[npc.Name]和[npc.her]的小恶魔投降，以此不战斗就能离开。"),
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_DEFEAT,
										isCompanionDialogue()
											?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
													isMaleBossWantingToBreed(Main.game.getPlayer())
														?isMaleBossWantingToBreed(getMainCompanion())
															?"KEEP_ENTRY_OFFER_SEX_DOUBLE_BREEDING"
															:"KEEP_ENTRY_OFFER_SEX_BREEDING_COMPANION_IMPS"
														:isMaleBossWantingToBreed(getMainCompanion())
															?"KEEP_ENTRY_OFFER_SEX_IMPS_COMPANION_BREEDING"
															:"KEEP_ENTRY_OFFER_SEX_DOUBLE_IMPS",
													getAllCharacters())
											:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
													isMaleBossWantingToBreed(Main.game.getPlayer())
														?"KEEP_ENTRY_OFFER_SEX"
														:"KEEP_ENTRY_OFFER_SEX_IMPS",
													getAllCharacters())){
							@Override
							public void effects() {
								setBossEncountered();
							}
						};
					}
					return new ResponseSex("投降",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), "你与[npc.name]一起向[npc2.name]投降，允许[npc2.herHim]和[npc2.her]的小恶魔随意拿你们取乐。")
								:UtilText.parse(getBoss(), "以你的身体向[npc.Name]和[npc.her]的小恶魔投降，以此不战斗就能离开。"),
							true,
							false,
							isCompanionDialogue()
								?new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
												new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO)))
								:new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
							null,
							null,
							KEEP_AFTER_SEX_DEFEAT,
							isCompanionDialogue()
								?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_OFFER_SEX_WITH_COMPANION", getAllCharacters())
								:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ENTRY_OFFER_SEX", getAllCharacters())) {
						@Override
						public void effects() {
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
								
							setBossEncountered();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode KEEP_ALPHA_BRAWLER = new DialogueNode("主楼", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER", getAllCharacters()));
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("吓跑",
						UtilText.parse(getBoss(), "让[npc.Name]逃跑，冲[npc.herHim]大喊快逃离这个地方。"),
						KEEP_ALPHA_BRAWLER_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
					
			} else if(index==2 && Main.game.isNonConEnabled()) {
				return new ResponseSex(isCompanionDialogue()?"强暴(单人)":"强暴",
						UtilText.parse(getBoss(), "按倒[npc.Name]，强行把自己压在[npc.herHim]身上。"),
						false,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(getSubmissives().containsKey(getBoss())) {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.ASS, CoverableArea.VAGINA));
									return map;
								}
								return super.exposeAtStartOfSexMap();
							}
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.equals(getBoss())) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						Main.game.getPlayer().getCompanions(),
						null,
						KEEP_AFTER_SEX_ALPHA_FORCED,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SEX", getAllCharacters())) {
					@Override
					public void effects() {
						try {
							getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.CHEST), true, getBoss());
						} catch(Exception ex) {
						}
						Main.game.getPlayer().incrementKarma(-50);
					}
				};
				
			} else if(index==3 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isWillingToRape() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("强奸(同伴)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name]对强奸[npc2.name]不感兴趣，并且[npc.sheIs]不是奴隶，你也不能强迫[npc.herHim]这样做……"), null);
				}
				return new ResponseSex("强暴(同伴)",
						UtilText.parse(getBoss(), getMainCompanion(), "按倒[npc.Name]，告诉[npc2.name]可以对[npc.herHim]为所欲为。"),
						false,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(getSubmissives().containsKey(getBoss())) {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.ASS, CoverableArea.VAGINA));
									return map;
								}
								return super.exposeAtStartOfSexMap();
							}
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.equals(getBoss())) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						KEEP_AFTER_SEX_ALPHA_FORCED,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SEX_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						try {
							getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.CHEST), true, getBoss());
						} catch(Exception ex) {
						}
						Main.game.getPlayer().incrementKarma(-50);
					}
				};
				
			} else if(index==4 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isWillingToRape() && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("强暴(双人)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name]对强奸[npc2.name]不感兴趣，并且[npc.sheIs]不是奴隶，你也不能强迫[npc.herHim]这样做……"), null);
				}
				return new ResponseSex("强奸(双人)",
						UtilText.parse(getBoss(), getMainCompanion(), "按倒[npc.Name]，强迫[npc.herHim]与你和[npc2.name]做爱。"),
						false,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND),
										new Value<>(getMainCompanion(), SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(getSubmissives().containsKey(getBoss())) {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									map.put(Main.game.getNpc(FortressAlphaLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.ASS, CoverableArea.VAGINA));
									return map;
								}
								return super.exposeAtStartOfSexMap();
							}
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.equals(getBoss())) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						null,
						KEEP_AFTER_SEX_ALPHA_FORCED,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_ALPHA_BRAWLER_SEX_WITH_BOTH", getAllCharacters())) {
					@Override
					public void effects() {
						try {
							getBoss().unequipClothingOntoFloor(getBoss().getClothingInSlot(InventorySlot.CHEST), true, getBoss());
						} catch(Exception ex) {
						}
						Main.game.getPlayer().incrementKarma(-50);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_ALPHA_BRAWLER_SCARED_OFF = new DialogueNode("主楼", "。", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_ALPHA_FORCED = new DialogueNode("结束", "", true, true) {

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "你已经和[npc.name]爽够了。");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_ALPHA_FORCED", getAllCharacters()));
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("吓跑",
						UtilText.parse(getBoss(), "让[npc.Name]逃跑，冲[npc.herHim]大喊快逃离这个地方。"),
						KEEP_AFTER_SEX_ALPHA_FORCED_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_ALPHA_FORCED_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_AFTER_SEX_ALPHA_FORCED_SCARED_OFF = new DialogueNode("主楼", "。", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_FEMALES_NYMPHO = new DialogueNode("主楼", "。", true, true) {

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
				return new Response("吓跑",
						UtilText.parse(getBoss(), "让[npc.Name]逃跑，冲[npc.herHim]大喊快逃离这个地方。"),
						KEEP_FEMALES_NYMPHO_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
					
			} else if(index==2 && Main.game.isNonConEnabled()) {
				return new ResponseSex(isCompanionDialogue()?"做爱 (单人)":"做爱",
						UtilText.parse(getBoss(), "按[npc.Name]说的做，和[npc.herHim]做爱。"),
						false,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotLyingDown.LYING_DOWN))),
						Main.game.getPlayer().getCompanions(),
						null,
						KEEP_AFTER_SEX_FEMALES_NYMPHO,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SEX", getAllCharacters()));
				
			} else if(index==3 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isAttractedTo(getBoss()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("做爱(同伴)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name]没兴趣与[npc2.name]做爱，并且[npc.sheIs]不是奴隶，你也不能逼[npc.herHim]这样做……"), null);
				}
				return new ResponseSex("做爱(同伴)",
						UtilText.parse(getBoss(), getMainCompanion(), "告诉[npc2.name]，[npc2.she]能和[npc.Name]做爱，随后退到一边旁观。"),
						false,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotLyingDown.LYING_DOWN))),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						KEEP_AFTER_SEX_FEMALES_NYMPHO,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SEX_WITH_COMPANION", getAllCharacters()));
				
			} else if(index==4 && isCompanionDialogue() && Main.game.isNonConEnabled()) {
				if(!((NPC) getMainCompanion()).isAttractedTo(getBoss()) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response("做爱(双人)", 
							UtilText.parse(getMainCompanion(), getBoss(), "[npc.Name]没兴趣与[npc2.name]做爱，并且[npc.sheIs]不是奴隶，你也不能逼[npc.herHim]这样做……"), null);
				}
				return new ResponseSex("做爱(双人)",
						UtilText.parse(getBoss(), getMainCompanion(), "按[npc.Name]说的做，让[npc2.name]加入你，一起和[npc.herHim]做爱。"),
						false,
						false,
						new SMLyingDown(
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY),
										new Value<>(getMainCompanion(), SexSlotLyingDown.FACE_SITTING)),
								Util.newHashMapOfValues(new Value<>(getBoss(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						KEEP_AFTER_SEX_FEMALES_NYMPHO,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_FEMALES_NYMPHO_SEX_WITH_BOTH", getAllCharacters()));
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_FEMALES_NYMPHO_SCARED_OFF = new DialogueNode("主楼", "。", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_FEMALES_NYMPHO = new DialogueNode("结束", "", true, true) {

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "你已经和[npc.name]爽够了。");
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_FEMALES_NYMPHO", getAllCharacters()));
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("吓跑",
						UtilText.parse(getBoss(), "让[npc.Name]逃跑，冲[npc.herHim]大喊快逃离这个地方。"),
						KEEP_AFTER_SEX_FEMALES_NYMPHO_SCARED_OFF) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_FEMALES_NYMPHO_SCARED_OFF", getAllCharacters()));
						clearFortress();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode KEEP_AFTER_SEX_FEMALES_NYMPHO_SCARED_OFF = new DialogueNode("主楼", "。", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_MALES_TAMESHIGIRI_MAIN = new DialogueNode("主楼", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 5*60;
		}

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(getSuitableWeaponForCutting().getName(), true);
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_MAIN", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("让他离开",
						UtilText.parse(getBoss(), "允许[npc.Name]和[npc.her]的小恶魔不战而逃。"),
						KEEP_MALES_TAMESHIGIRI_ALLOW_TO_LEAVE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_MALES_TAMESHIGIRI_ALLOW_TO_LEAVE", getAllCharacters()));
						clearFortress();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_MALES_TAMESHIGIRI_ALLOW_TO_LEAVE = new DialogueNode("主楼", "。", false, true) {

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
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AUDIENCE = new DialogueNode("主楼", "。", true, true) {

		@Override
		public int getSecondsPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE", getAllCharacters()));
			
			// Demands are based on preference availability:
			if(isAlphaFortress()) {
				if(isCompanionDialogue()) {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						if(isAlphaBossWantingOral(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_ORAL_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isAlphaBossWantingOral(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS_COMPANION_ORAL", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_ORAL", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS", getAllCharacters()));
					}
				}
				
			} else if(isMalesFortress()) {
				if(isCompanionDialogue()) {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						if(isMaleBossWantingToBreed(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_BREEDING_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isMaleBossWantingToBreed(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS_COMPANION_BREEDING", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_BREEDING", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_IMPS", getAllCharacters()));
					}
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("拒绝", UtilText.parse(getBoss(), "你还没准备做到那种地步！这样告诉[npc.Name]，准备自卫！"),
						(NPC) getBoss(),
						getImpBossGroup(true), null);
				
			} else if(index==2) {
				if(isAlphaFortress()) {
					SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
					return new ResponseSex(isCompanionDialogue()?"同意(单人)":"同意",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), "决定听从[npc2.name]的命令，让[npc.Name]站在一旁，保持旁观，而后向[npc2.name]屈服……")
								:UtilText.parse(getBoss(), "决定听从[npc.name]的命令，向[npc.herHim]屈服……"),
							true,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_AUDIENCE,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_AUDIENCE_SEX":"KEEP_AUDIENCE_SEX_IMPS", getAllCharacters())) {
						@Override
						public void effects() {
							setPacified();
						}
					};
					
				} else if(isMalesFortress()) {
					SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
					return new ResponseSex(isCompanionDialogue()?"同意(单人)":"同意",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), getBoss(), "决定听从[npc2.name]的命令，让[npc.Name]站在一旁，保持旁观，而后向[npc2.name]屈服……")
								:UtilText.parse(getBoss(), "决定听从[npc.name]的命令，向[npc.herHim]屈服……"),
							true,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_AUDIENCE,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_AUDIENCE_SEX":"KEEP_AUDIENCE_SEX_IMPS", getAllCharacters())) {
						@Override
						public void effects() {
							setPacified();
						}
					};
				}
				return new ResponseSex(isCompanionDialogue()?"同意(单人)":"同意",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), getBoss(), "决定听从[npc2.name]的命令，让[npc.Name]站在一旁，保持旁观，而后向[npc2.name]屈服……")
							:UtilText.parse(getBoss(), "决定听从[npc.name]的命令，向[npc.herHim]屈服……"),
						true,
						false,
						new SMLyingDown(
							Util.newHashMapOfValues(
									new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
									new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
									new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
									new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						Main.game.getPlayer().getCompanions(),
						KEEP_AFTER_SEX_AUDIENCE,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_SEX", getAllCharacters())) {
						@Override
						public void effects() {
							setPacified();
							((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
						}
					};
					
			} else if(index==3 && isCompanionDialogue()) {
				if(isAlphaFortress()) {
					SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
					return new ResponseSex("同意(双人)",
							UtilText.parse(getMainCompanion(), getBoss(), "听从[npc2.name]的命令，和[npc.Name]一起向[npc2.herHim]屈服……"),
							true,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_AUDIENCE,
							UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
								isAlphaBossWantingOral(Main.game.getPlayer())
									?isAlphaBossWantingOral(getMainCompanion())
										?"KEEP_AUDIENCE_SEX_DOUBLE_ORAL"
										:"KEEP_AUDIENCE_SEX_ORAL_COMPANION_IMPS"
									:isAlphaBossWantingOral(getMainCompanion())
										?"KEEP_AUDIENCE_SEX_IMPS_COMPANION_ORAL"
										:"KEEP_AUDIENCE_SEX_DOUBLE_IMPS",
								getAllCharacters())){
						@Override
						public void effects() {
							setPacified();
						}
					};
					
				} else if(isMalesFortress()) {
					SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
					return new ResponseSex("同意(双人)",
									UtilText.parse(getMainCompanion(), getBoss(), "听从[npc2.name]的命令，和[npc.Name]一起向[npc2.herHim]屈服……"),
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_AUDIENCE,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isMaleBossWantingToBreed(Main.game.getPlayer())
											?isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_AUDIENCE_SEX_DOUBLE_BREEDING"
												:"KEEP_AUDIENCE_SEX_BREEDING_COMPANION_IMPS"
											:isMaleBossWantingToBreed(getMainCompanion())
												?"KEEP_AUDIENCE_SEX_IMPS_COMPANION_BREEDING"
												:"KEEP_AUDIENCE_SEX_DOUBLE_IMPS",
										getAllCharacters())){
						@Override
						public void effects() {
							setPacified();
						}
					};
				}
				return new ResponseSex("同意(双人)",
						UtilText.parse(getMainCompanion(), getBoss(), "听从[npc2.name]的命令，和[npc.Name]一起向[npc2.herHim]屈服……"),
						true,
						false,
						new SMLyingDown(
							Util.newHashMapOfValues(
									new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
									new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
									new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
									new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
									new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO))),
						null,
						Main.game.getPlayer().getCompanions(),
						KEEP_AFTER_SEX_AUDIENCE,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AUDIENCE_SEX_WITH_COMPANION", getAllCharacters())) {
					@Override
					public void effects() {
						setPacified();
						((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_AUDIENCE = new DialogueNode("结束", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "[npc.Name]似乎对你顺服的表现很满意。");
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_AUDIENCE_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_AUDIENCE", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("拿走钥匙", UtilText.parse(getBoss(), "从[npc.name]那拿走钥匙，离开主楼。"), KEEP_AFTER_SEX_AUDIENCE_KEY) {
					@Override
					public void effects() {
						AbstractItemType keyType = ItemType.IMP_FORTRESS_ARCANE_KEY;
						if(isMalesFortress()) {
							keyType = ItemType.IMP_FORTRESS_ARCANE_KEY_2;
						} else if(isFemalesFortress()) {
							keyType = ItemType.IMP_FORTRESS_ARCANE_KEY_3;
						}
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(keyType), false));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_AUDIENCE_KEY = new DialogueNode("退开", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_AUDIENCE_KEY", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KEEP_AFTER_COMBAT_VICTORY = new DialogueNode("主楼", "。", true) {

		@Override
		public String getDescription() {
			return UtilText.parse(getBoss(), "你打败了[npc.Name]和[npc.her]的小恶魔！");
		}

		@Override
		public String getContent() {
			return ""; // Set in the leader's endCombat() method.
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "互动";
				
			} else if(index==1) {
				return "物品栏";
				
			} else if(index==2) {
				return "转化";
				
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("吓退", UtilText.parse(getBoss(), "告诉[npc.Name]和[npc.her]的帮派，在你改变主意之前，赶紧滚远……"), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("做爱",
								UtilText.parse(getBoss(), "[npc.Name]和[npc.her]的小恶魔帮派被打败了，你可以肆意与他们做爱。"),
								true,
								false,
								getPartyForSex(),
								getImpBossGroup(true),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("做爱(温柔)",
								UtilText.parse(getBoss(), "[npc.Name]和[npc.her]的小恶魔帮派被打败了，你可以肆意与他们做爱。"),
								true,
								false,
								getPartyForSex(),
								getImpBossGroup(true),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("做爱(粗暴)",
								UtilText.parse(getBoss(), "[npc.Name]和[npc.her]的小恶魔帮派被打败了，你可以肆意与他们做爱。"),
								true,
								false,
								getPartyForSex(),
								getImpBossGroup(true),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						if(isAlphaFortress()) {
							SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
							return new ResponseSex("顺从",
									UtilText.parse(getBoss(), "你对[npc.Name]和[npc.her]的小恶魔感到抱歉，决定让他们用你的身体爽一爽，随后再把他们赶出要塞……"),
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null,
									Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
									null,
									null,
									null,
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
							
						} else if(isMalesFortress()) {
								SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
								return new ResponseSex("顺从",
										UtilText.parse(getBoss(), "你对[npc.Name]和[npc.her]的小恶魔感到抱歉，决定让他们用你的身体爽一爽，随后再把他们赶出要塞……"),
										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
										null,
										Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
										null,
										null,
										null,
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										null,
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
								
							}
						return new ResponseSex("顺从",
								UtilText.parse(getBoss(), "你对[npc.Name]和[npc.her]的小恶魔感到抱歉，决定让他们用你的身体爽一爽，随后再把他们赶出要塞……"),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters())){
							@Override
							public void effects() {
								((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
							}
						};
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("吓跑",
								UtilText.parse(getMainCompanion(), getBoss(), "告诉[npc2.name]和[npc2.her]的帮派，在你和[npc.Name]改变主意之前从这滚出去……"), Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_VICTORY_SCARE_OFF", getAllCharacters()));
								clearFortress();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("单人做爱",
								UtilText.parse(getMainCompanion(), getBoss(), "让[npc.name]站在一边，看你与[npc2.name]还有[npc2.her]的小恶魔帮派做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(true),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX", getAllCharacters()));
						
					} else if (index == 3) {
						return new ResponseSex("单人做爱(温柔)",
								UtilText.parse(getMainCompanion(), getBoss(), "让[npc.name]站在一边，看你与[npc2.name]还有[npc2.her]的小恶魔帮派做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(true),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("单人做爱(粗暴)",
								UtilText.parse(getMainCompanion(), getBoss(), "让[npc.name]站在一边，看你与[npc2.name]还有[npc2.her]的小恶魔帮派做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpBossGroup(true),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								KEEP_AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						if(isAlphaFortress()) {
							SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer());
							return new ResponseSex("单人屈从",
									UtilText.parse(getMainCompanion(), getBoss(),
											"你让[npc.name]站在一边，允许[npc2.name]和[npc2.her]的帮派用你的身体爽一爽，随后将他们流放出要塞……"),
									Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
									null,
									Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
									null,
									null,
									null,
									true,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
							
						} else if(isMalesFortress()) {
								SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer());
								return new ResponseSex("单人屈从",
										UtilText.parse(getMainCompanion(), getBoss(),
												"你让[npc.name]站在一边，允许[npc2.name]和[npc2.her]的帮派用你的身体爽一爽，随后将他们流放出要塞……"),
										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
										null,
										Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
										null,
										null,
										null,
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())?"KEEP_COMBAT_VICTORY_SEX_SUBMIT":"KEEP_COMBAT_VICTORY_SEX_SUBMIT_IMPS", getAllCharacters()));
								
							}
						return new ResponseSex("单人屈从",
								UtilText.parse(getMainCompanion(), getBoss(),
										"你让[npc.name]站在一边，允许[npc2.name]和[npc2.her]的帮派用你的身体爽一爽，随后将他们流放出要塞……"),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters())){
							@Override
							public void effects() {
								((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
							}
						};
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群交",
									UtilText.parse(companion, getBoss(),
											"[npc.Name]与[npc2.name]和[npc2.her]的小恶魔做爱不感兴趣，并且[npc.sheIs]不是奴隶，你也不能强迫[npc2.name]这样做……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群交"),
									UtilText.parse(companion, getBoss(), "与[npc2.name]和[npc2.her]的小恶魔做支配型性爱，让[npc.Name]也加入爽一爽。"),
									true,
									false,
									getPartyForSex(),
									getImpBossGroup(true),
									null,
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX", getAllCharacters()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群体屈从",
									UtilText.parse(companion, getBoss(), "[npc.Name]不想和[npc2.name]还有[npc2.her]的小恶魔做爱，并且[npc.sheIs]不是奴隶，你无法逼[npc.herHim]去做……"), null);
							
						} else {
							if(isAlphaFortress()) {
								SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion());
								return new ResponseSex("群体屈从",
										UtilText.parse(getMainCompanion(), getBoss(), "让[npc.name]和你一同屈服于[npc2.name]和[npc2.her]的小恶魔们，允许他们和你们俩发生支配型性爱。"),
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_APOLOGY", getAllCharacters())
										+ UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(Main.game.getPlayer())
												?isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_ORAL"
													:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_ORAL_COMPANION_IMPS"
												:isAlphaBossWantingOral(getMainCompanion())
													?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_IMPS_COMPANION_ORAL"
													:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_IMPS",
											getAllCharacters()));
								
							} else if(isMalesFortress()) {
									SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion());
									return new ResponseSex("群体屈从",
											UtilText.parse(getMainCompanion(), getBoss(), "让[npc.name]和你一同屈服于[npc2.name]和[npc2.her]的小恶魔们，允许他们和你们俩发生支配型性爱。"),
											true,
											false,
											manager,
											getKeepDominantSpectators(manager),
											getKeepSubmissiveSpectators(manager),
											KEEP_AFTER_SEX_VICTORY,
											UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_APOLOGY", getAllCharacters())
											+ UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())
													?isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_BREEDING"
														:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_BREEDING_COMPANION_IMPS"
													:isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_IMPS_COMPANION_BREEDING"
														:"KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION_DOUBLE_IMPS",
												getAllCharacters()));
								}
							return new ResponseSex("群体屈从",
									UtilText.parse(companion, getBoss(), "让[npc.name]加入你，一起向[npc2.name]以及[npc2.her]的小恶魔屈服，任由他们和你们俩做支配型性爱。"),
									true,
									false,
									new SMLyingDown(
										Util.newHashMapOfValues(
												new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
												new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
												new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
												new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
												new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO))),
									null,
									null,
									KEEP_AFTER_SEX_VICTORY,
									UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getAllCharacters())){
								@Override
								public void effects() {
									((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
								}
							};
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "给[npc.name]"),
									UtilText.parse(companion, getBoss(), "[npc.Name]不想和[npc2.name]还有[npc2.her]的小恶魔做爱，并且[npc.sheIs]不是奴隶，你无法逼[npc.herHim]去做……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
									UtilText.parse(companion, getBoss(), "告诉[npc.name]，[npc.she]可以跟[npc2.name]还有[npc2.her]的小恶魔们找找乐子，你在一旁看着。"),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpBossGroup(true),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_GIVE_TO_COMPANION", getAllCharacters()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getBoss()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, getBoss(),
											"你看出[npc.Name]对与[npc2.name]和[npc2.her]的小恶魔做爱不感兴趣，你也不能强迫[npc.herHim]这么做……"),
									null);
							
						} else { 
							if(isAlphaFortress()) {
								SexManagerInterface manager = getAlphaSexManager(getMainCompanion());
								return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
										UtilText.parse(companion, getBoss(), "把[npc.name]交给[npc2.name]和[npc2.her]的小恶魔，旁观他们与[npc.herHim]做爱，随后将他们赶出要塞。"),
										true,
										false,
										manager,
										getKeepDominantSpectators(manager),
										getKeepSubmissiveSpectators(manager),
										KEEP_AFTER_SEX_VICTORY,
										UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
											isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_COMBAT_VICTORY_OFFER_COMPANION"
												:"KEEP_COMBAT_VICTORY_OFFER_COMPANION_IMPS",
											getAllCharacters()));
								
							} else if(isMalesFortress()) {
									SexManagerInterface manager = getMalesSexManager(getMainCompanion());
									return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
											UtilText.parse(companion, getBoss(), "把[npc.name]交给[npc2.name]和[npc2.her]的小恶魔，旁观他们与[npc.herHim]做爱，随后将他们赶出要塞。"),
											true,
											false,
											manager,
											getKeepDominantSpectators(manager),
											getKeepSubmissiveSpectators(manager),
											KEEP_AFTER_SEX_VICTORY,
											UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(getMainCompanion())
													?"KEEP_COMBAT_VICTORY_OFFER_COMPANION"
													:"KEEP_COMBAT_VICTORY_OFFER_COMPANION_IMPS",
												getAllCharacters()));
								}
							return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, getBoss(), "把[npc.name]交给[npc2.name]和[npc2.her]的小恶魔，旁观他们与[npc.herHim]做爱，随后将他们赶出要塞。"),
									true,
									false,
									new SMLyingDown(
											Util.newHashMapOfValues(
													new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
													new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
													new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
													new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
											Util.newHashMapOfValues(
													new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN))),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									KEEP_AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_COMBAT_VICTORY_OFFER_COMPANION", getAllCharacters())) {
								@Override
								public void effects() {
									((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
										
									if(!companion.isAttractedTo(getBoss()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpBossGroup(true).size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpBossGroup(true).get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			}
		}
	};

	public static final DialogueNode KEEP_AFTER_COMBAT_DEFEAT = new DialogueNode("主楼", "。", true) {
		
		@Override
		public String getDescription() {
			return "你被[npc2.name]和[npc2.her]的小恶魔打败了！";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(isAlphaFortress()) {
				if(isCompanionDialogue()) {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						if(isAlphaBossWantingOral(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_ORAL", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_ORAL_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isAlphaBossWantingOral(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS_COMPANION_ORAL", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isAlphaBossWantingOral(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_ORAL", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS", getAllCharacters()));
					}
				}
				
			} else if(isMalesFortress()) {
				if(isCompanionDialogue()) {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						if(isMaleBossWantingToBreed(getMainCompanion())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_BREEDING", getAllCharacters()));
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_BREEDING_COMPANION_IMPS", getAllCharacters()));
						}
					} else if(isMaleBossWantingToBreed(getMainCompanion())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS_COMPANION_BREEDING", getAllCharacters()));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_DOUBLE_IMPS", getAllCharacters()));
					}
					
				} else {
					if(isMaleBossWantingToBreed(Main.game.getPlayer())) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_BREEDING", getAllCharacters()));
					}  else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_IMPS", getAllCharacters()));
					}
				}
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 || index == 2 || (index == 3 && Main.game.isNonConEnabled())) {
				
				String title = "做爱";
				String appendPace = "";
				String description = UtilText.parse(getBoss(), "让[npc.name]和[npc.her]的小恶魔与你做爱。");
				ResponseTag tag = null;
				if(index==2) {
					title = "做爱(渴求)";
					appendPace = "_EAGER";
					description = UtilText.parse(getBoss(), "饥渴地鼓励[npc.name]和[npc.her]的小恶魔与你做爱。");
					tag = ResponseTag.START_PACE_PLAYER_SUB_EAGER;
				}
				if(index==3) {
					title = "抵抗做爱";
					appendPace = "_RESIST";
					description = UtilText.parse(getBoss(), "挣扎反抗[npc.Name]和[npc.her]的小恶魔，尽力抵抗和他们做爱。");
					tag = ResponseTag.START_PACE_PLAYER_SUB_RESISTING;
				}
				
				if(isAlphaFortress()) {
					SexManagerInterface manager = getAlphaSexManager(Main.game.getPlayer(), getMainCompanion(), tag);
					return new ResponseSex(title,
							description,
							false,
							false,
							manager,
							getKeepDominantSpectators(manager),
							getKeepSubmissiveSpectators(manager),
							KEEP_AFTER_SEX_DEFEAT,
							(isCompanionDialogue()
								?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isAlphaBossWantingOral(Main.game.getPlayer())
											?isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_ORAL"+appendPace
												:"KEEP_AFTER_COMBAT_DEFEAT_SEX_ORAL_COMPANION_IMPS"+appendPace
											:isAlphaBossWantingOral(getMainCompanion())
												?"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS_COMPANION_ORAL"+appendPace
												:"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_IMPS"+appendPace,
										getAllCharacters())
								:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
										isAlphaBossWantingOral(Main.game.getPlayer())
											?"KEEP_AFTER_COMBAT_DEFEAT_SEX"+appendPace
											:"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS"+appendPace,
										getAllCharacters()))
							);
					
				} else if(isMalesFortress()) {
					SexManagerInterface manager = getMalesSexManager(Main.game.getPlayer(), getMainCompanion(), tag);
					return new ResponseSex(title,
									description,
									false,
									false,
									manager,
									getKeepDominantSpectators(manager),
									getKeepSubmissiveSpectators(manager),
									KEEP_AFTER_SEX_DEFEAT,
									isCompanionDialogue()
										?UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())
													?isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_BREEDING"+appendPace
														:"KEEP_AFTER_COMBAT_DEFEAT_SEX_BREEDING_COMPANION_IMPS"+appendPace
													:isMaleBossWantingToBreed(getMainCompanion())
														?"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS_COMPANION_BREEDING"+appendPace
														:"KEEP_AFTER_COMBAT_DEFEAT_SEX_DOUBLE_IMPS"+appendPace,
												getAllCharacters())
										:UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(),
												isMaleBossWantingToBreed(Main.game.getPlayer())
													?"KEEP_AFTER_COMBAT_DEFEAT_SEX"+appendPace
													:"KEEP_AFTER_COMBAT_DEFEAT_SEX_IMPS"+appendPace,
												getAllCharacters()));
				}
				return new ResponseSex(title,
						description,
						false,
						false,
						isCompanionDialogue()
							?new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.MISSIONARY_TWO),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.FACE_SITTING_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(getMainCompanion(), SexSlotLyingDown.LYING_DOWN_TWO)))
							:new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(getBoss(), SexSlotLyingDown.MISSIONARY),
											new Value<>(getImpBossGroup(false).get(0), SexSlotLyingDown.FACE_SITTING),
											new Value<>(getImpBossGroup(false).get(1), SexSlotLyingDown.BESIDE),
											new Value<>(getImpBossGroup(false).get(2), SexSlotLyingDown.BESIDE_TWO)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						KEEP_AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_COMBAT_DEFEAT_SEX"+appendPace, getAllCharacters())){
					@Override
					public void effects() {
						((FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class)).equipStrapon();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_PACIFIED = new DialogueNode("结束", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "[npc.Name]似乎对你的表现很满意，命令你从[npc.herHim]身边离开。");
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_PACIFIED_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_PACIFIED", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", UtilText.parse(getBoss(), "按[npc.name]说的做，离开主楼。"), KEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_VICTORY = new DialogueNode("退开", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "你现在爽够了，可以离开，把[npc.Name]和[npc.her]小恶魔赶出要塞，任由他们流亡在屈城区隧道里。");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_VICTORY", getAllCharacters());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return KEEP_AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("吓跑", UtilText.parse(getBoss(), "告诉[npc.Name]和[npc.her]的小恶魔从这滚开，别再回来了。"), KEEP_AFTER_SEX_VICTORY_SCARE_OFF) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/fortress"+getDialogueEncounterId(), "KEEP_AFTER_SEX_VICTORY_SCARE_OFF", getAllCharacters()));
							clearFortress();
						}
					};
				}
				
			} else if(responseTab==1) {
				return KEEP_AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_VICTORY_SCARE_OFF = new DialogueNode("", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode KEEP_AFTER_SEX_DEFEAT = new DialogueNode("瘫软", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return UtilText.parse(getBoss(), "你完全被[npc.namePos]的支配调教玩坏了，需要一会时间恢复。");
		}

		@Override
		public String getContent() {
			return ""; // Set in leader's endSex() method.
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(isAlphaFortress() || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA)) {
							List<ItemEffect> effects = Util.newArrayListOfValues(
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_ORAL_GIVING, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_DOMINANT, TFPotency.DRAIN, 0));
							
							FortressAlphaLeader boss = (FortressAlphaLeader) Main.game.getNpc(FortressAlphaLeader.class);
							
							if((boss).isAbleToEquipGag(Main.game.getPlayer())) {
								AbstractClothing ringGag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_GOLD, effects);
								ringGag.setName(UtilText.parse(boss,"[npc.NamePos]“嗦牛者”口枷"));
								Main.game.getPlayer().equipClothingFromNowhere(ringGag, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强迫你穿:<br/>"
										+Main.game.getPlayer().getClothingInSlot(ringGag.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
							if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())
									&& (boss).isAbleToEquipGag(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing ringGag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ringgag", PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_BROWN_DARK, PresetColour.CLOTHING_BLACK_STEEL, effects);
								ringGag.setName(UtilText.parse(boss,"[npc.NamePos]“嗦牛者”口枷"));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(ringGag, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强行"
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+"穿着：<br/>"
											+ImpFortressDialogue.getMainCompanion().getClothingInSlot(ringGag.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
						} else if(isFemalesFortress() || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES)) {
							List<ItemEffect> effects = Util.newArrayListOfValues(
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_MASTURBATION, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_EXHIBITIONIST, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_ASS, TFPotency.BOOST, 0));

							FortressFemalesLeader boss = (FortressFemalesLeader) Main.game.getNpc(FortressFemalesLeader.class);
							
							if(boss.isAbleToEquipButtPlug(Main.game.getPlayer())) {
								AbstractClothing buttPlug = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"),
										PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PINK_LIGHT, effects);
								buttPlug.setName(UtilText.parse(boss,"[npc.NamePos]“公用玩具”肛塞"));
								Main.game.getPlayer().equipClothingFromNowhere(buttPlug, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强迫你穿:<br/>"
										+Main.game.getPlayer().getClothingInSlot(buttPlug.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
							if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())
									&& boss.isAbleToEquipButtPlug(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing buttPlug = Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_buttPlugs_butt_plug_heart"),
										PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PERIWINKLE, PresetColour.CLOTHING_PERIWINKLE, effects);
								buttPlug.setName(UtilText.parse(boss,"[npc.NamePos]“公用玩具”肛塞"));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(buttPlug, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强行"
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+"穿着：<br/>"
										+ImpFortressDialogue.getMainCompanion().getClothingInSlot(buttPlug.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
						} else if(isMalesFortress() || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_FORTRESS_MALES)) {
							List<ItemEffect> effects = Util.newArrayListOfValues(
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_DRAIN, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_PREGNANCY, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, HipSize.FIVE_VERY_WIDE.getValue()),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0),
									new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));

							FortressMalesLeader boss = (FortressMalesLeader) Main.game.getNpc(FortressMalesLeader.class);
							
							if(boss.isAbleToEquipThong(Main.game.getPlayer())) {
								AbstractClothing thong = Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_RED_DARK, effects);
								thong.setName(UtilText.parse(boss,"[npc.NamePos]“繁育者”开裆丁字裤"));
								Main.game.getPlayer().equipClothingFromNowhere(thong, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强迫你穿:<br/>"
										+Main.game.getPlayer().getClothingInSlot(thong.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							if(boss.isAbleToEquipDildo(Main.game.getPlayer())) {
								AbstractClothing dildo = Main.game.getItemGen().generateClothing("innoxia_vagina_insertable_dildo", PresetColour.CLOTHING_BLACK,
										Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0)));
								Main.game.getPlayer().equipClothingFromNowhere(dildo, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强迫你穿:<br/>"
										+Main.game.getPlayer().getClothingInSlot(dildo.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							
							if(ImpFortressDialogue.getMainCompanion()!=null && Main.sex.getAllParticipants().contains(ImpFortressDialogue.getMainCompanion())
									&& boss.isAbleToEquipThong(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing thong = Main.game.getItemGen().generateClothing("innoxia_groin_crotchless_thong", PresetColour.CLOTHING_PINK_LIGHT, effects);
								thong.setName(UtilText.parse(boss,"[npc.NamePos]“繁育者”开裆丁字裤"));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(thong, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强行"
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+"穿着：<br/>"
										+ImpFortressDialogue.getMainCompanion().getClothingInSlot(thong.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
							if(ImpFortressDialogue.getMainCompanion()!=null && boss.isAbleToEquipDildo(ImpFortressDialogue.getMainCompanion())) {
								AbstractClothing dildo = Main.game.getItemGen().generateClothing("innoxia_vagina_insertable_dildo", PresetColour.CLOTHING_WHITE,
										Util.newArrayListOfValues(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0)));
								ImpFortressDialogue.getMainCompanion().equipClothingFromNowhere(dildo, true, boss);
								Main.game.getTextStartStringBuilder().append("<p style='text-align:center;'>"+UtilText.parse(boss,"[npc.Name]")+"强行"
										+UtilText.parse(ImpFortressDialogue.getMainCompanion(), "[npc.name]")+"穿着：<br/>"
										+ImpFortressDialogue.getMainCompanion().getClothingInSlot(dildo.getClothingType().getEquipSlots().get(0)).getDisplayName(true)+ "</p>");
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
