package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Axel;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.npc.submission.Vengar;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePoker;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DicePokerTable;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.managers.submission.SMVengarDominantSex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.5
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RatWarrensDialogue {
	
	private static NPC gambler;
	private static final int PERSUASION_PAYMENT = 10_000;
	
	public static void init() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
		
		// Spawn guard rats if missing:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.M_P_MALE);
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				Main.game.getCharacterUtils().setGenericName(rat, "二把手", null);
				rat.unequipOffhandWeaponIntoVoid(0, false);
				rat.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.POISON, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GREEN_DRAB, PresetColour.CLOTHING_GUNMETAL)));
				rat.incrementEssenceCount(8, false);
				
				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(9);
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE, true);
				Main.game.getCharacterUtils().setGenericName(rat, "帮众", null);
				rat.unequipOffhandWeaponIntoVoid(0, false);
				rat.equipOffhandWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_bow_pistol_crossbow", DamageType.PHYSICAL, Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_KHAKI, PresetColour.CLOTHING_STEEL)));
				rat.incrementEssenceCount(3, false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Spawn humans:
		if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR) && Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM).isEmpty()) {
			spawnMilkers();
		}
		
		// Spawn bar-tender:
		if(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN).isEmpty()) {
			try {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				if(rat.hasVagina()) {
					rat.setVaginaSquirter(true);
				}
				if(!rat.hasPenis() && !rat.hasVagina()) {
					rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, true));
				}
				Main.game.addNPC(rat, false);
				rat.setLevel(4);
				rat.addFetish(Fetish.FETISH_ORAL_RECEIVING);
				if(rat.hasPenis()) {
					rat.addFetish(Fetish.FETISH_PENIS_GIVING);
				}
				if(rat.hasVagina()) {
					rat.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
				}
				rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
				Main.game.getCharacterUtils().setGenericName(rat, "酒保", null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void exit() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
	}
	
	public static void applyCombatDefeatFlagsReset() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedLeft, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedCentre, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensClearedRight, false);
	}
	
	public static String applyConflictQuestEnd() {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
		return Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END);
	}
	
	/**
	 * @return A list with the player's main companion in index 0, with gang members present being in slots after that.
	 */
	public static List<GameCharacter> getGuards(boolean includeCompanion) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof RatGangMember));
		Collections.sort(guards, (a, b)->b.getLevel()-a.getLevel());
		if(Main.game.getPlayer().hasCompanions() && includeCompanion) {
			guards.add(0, Main.game.getPlayer().getMainCompanion());
		}
		return guards;
	}
	
	public static List<GameCharacter> getMilkers() {
		List<GameCharacter> milkers = new ArrayList<>();
		for(GameCharacter milker : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM))) {
			if(milker instanceof RatWarrensCaptive && !Main.game.getPlayer().getCompanions().contains(milker)) {
				milkers.add(milker);
			}
		}
		return milkers;
	}
	
	private static void banishGuards(boolean includeLeaderGuards) {
		for(GameCharacter guard : getGuards(false)) {
			if(includeLeaderGuards || guard.getLevel()<9) {
				Main.game.banishNPC((NPC) guard);
				
			} else {
				guard.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
			}
		}
	}
	
	public static void banishTwoGuards() {
		Main.game.banishNPC((NPC) getGuards(false).get(getGuards(false).size()-1));
		Main.game.banishNPC((NPC) getGuards(false).get(getGuards(false).size()-1));
	}
	
	public static void applyRatWarrensRaid() {
		Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
		Main.game.getPlayer().setNearestLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);

		((Shadow)Main.game.getNpc(Shadow.class)).moveToBountyHunterLodge();
		((Silence)Main.game.getNpc(Silence.class)).moveToBountyHunterLodge();
		
		Main.game.getNpc(Shadow.class).removeItemByType(ItemType.RESONANCE_STONE);
		
		Main.game.getNpc(Axel.class).addSlave(Main.game.getNpc(Vengar.class));
		Main.game.getNpc(Vengar.class).unequipAllClothingIntoVoid(true, true);
		Main.game.getNpc(Vengar.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Axel.class));
		
		Main.game.getNpc(Axel.class).addSlave(Main.game.getNpc(Murk.class));
		Main.game.getNpc(Murk.class).unequipAllClothingIntoVoid(true, true);
		Main.game.getNpc(Murk.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false), true, Main.game.getNpc(Axel.class));
		
		Main.game.getNpc(Vengar.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);
		Main.game.getNpc(Murk.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE, true);
		Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -100);
		Main.game.getNpc(Murk.class).setAffection(Main.game.getPlayer(), -100);
		
		Main.game.getPlayer().removeItemByType(ItemType.RESONANCE_STONE);

		List<NPC> ratGuards = new ArrayList<>(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE));
		ratGuards.addAll(Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN));
		for(NPC ratGuard : ratGuards) {
			if(ratGuard instanceof RatGangMember && !Main.game.getPlayer().getCompanions().contains(ratGuard)) {
				Main.game.banishNPC(ratGuard);
			}
		}
	}
	
	public static void spawnMilkers() {
		try {
			String[] adjectives = new String[] {"已洗脑", "顺从", "服从", "温驯"};
			for(int i=0;i<4;i++) {
				NPC human = new RatWarrensCaptive(Gender.F_V_B_FEMALE);
				Main.game.addNPC(human, false);
				human.setGenericName(adjectives[i]+"产奶工");
				human.setAffection(Main.game.getNpc(Murk.class), 100);
				Main.game.getNpc(Murk.class).calculateGenericSexEffects(true, true, human, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), GenericSexFlag.NO_DESCRIPTION_NEEDED);
				Main.game.getNpc(Murk.class).fillCumToMaxStorage();
				human.clearFluidsStored(SexAreaOrifice.VAGINA);
				human.calculateStatusEffects(1);
				AbstractItem milk = Main.game.getItemGen().generateItem(ItemType.MOTHERS_MILK);
				human.useItem(milk, human, false);
				if(human.isPregnant()) {
					if(Math.random()<0.75f) {
						human.useItem(milk, human, false);
					}
					if(Math.random()<0.5f) {
						human.useItem(milk, human, false);
					}
					if(Math.random()<0.25f) {
						human.useItem(milk, human, false);
					}
				}
				human.addMuskMarkerCharacter(Main.game.getNpc(Murk.class));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void banishMilkers() {
		for(GameCharacter milker : getMilkers()) {
			Main.game.banishNPC((NPC) milker);
		}
	}
	
	
	private static void spawnGuards(boolean withLeader, int totalRatsToSpawn) {
		try {
			List<String> adjectives = new ArrayList<>();
			if(withLeader) {
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(10);
				rat.setLocation(Main.game.getPlayer(), true);
				rat.addSpecialPerk(Perk.SPECIAL_HEALTH_FANATIC);
				rat.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
				rat.setGenericName(Util.randomItemFrom(Util.newArrayListOfValues("大块头的", "强壮的", "肌肉发达的"))+"二把手");

				rat = new RatGangMember(Gender.getGenderFromUserPreferences(false, false));
				Main.game.addNPC(rat, false);
				rat.setLevel(9);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, "跟班", adjectives));
				
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight, false);
				
			} else {
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
				for(NPC rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN)) {
					rat.setLocation(Main.game.getPlayer(), false);
				}
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight, true);
			}
			for(int i=0;i<(withLeader?totalRatsToSpawn-2:totalRatsToSpawn);i++) {
				String[] names = new String[] {"恶棍", "匪徒", "帮派成员", "黑手党成员"};
				NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(i==1, i==2));
				Main.game.addNPC(rat, false);
				rat.setLevel(8-i);
				rat.setLocation(Main.game.getPlayer(), true);
				adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), adjectives));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static NPC generateGambler(Femininity femininity) {
		NPC gambler = new RatGangMember(Gender.getGenderFromUserPreferences(femininity));
		if(Math.random()<0.5f) { // 50% chance for them to be a cheater
			List<Dice> dice = new ArrayList<>();
			int weightedFace = Util.random.nextInt(6)+1;
			for(int i=0; i<5; i++) {
				dice.add(new Dice(Util.newHashMapOfValues(new Value<>(DiceFace.getFaceFromInt(weightedFace), 5f))));
			}
			gambler.setDice(dice);
		}
		try {
			Main.game.addNPC(gambler, false);
			Main.game.getCharacterUtils().setGenericName(gambler, "赌徒", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
		return gambler;
	}

	private static GameCharacter getGuardLeader() {
		if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Murk.class))) {
			return Main.game.getNpc(Murk.class);
		}
		return getGuards(false).get(0);
	}
	
	private static GameCharacter getMainCompanion() {
		if(Main.game.getPlayer().hasCompanions()) {
			return Main.game.getPlayer().getMainCompanion();
		}
		return null;
	}
	
	private static boolean isCompanionDialogue() {
		return getMainCompanion()!=null;
	}
	
	private static String applyCaptivity(GameCharacter character, GameCharacter equipper, Colour collarColour) {
		Main.game.addSavedInventory(character);
		
		int essences = character.getEssenceCount();
		character.setInventory(new CharacterInventory(false, 0));
		character.setEssenceCount(essences);

		Main.game.getPlayer().setCaptive(true);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
		
		return "<p style='text-align:center;'>"+RatWarrensCaptiveDialogue.equipCollar(character, equipper, collarColour)+"</p>";
	}
	
	private static int getRumPrice() {
		return ItemType.getItemTypeFromId("innoxia_race_rat_black_rats_rum").getValue()/2;
	}

	private static boolean isMouthAccess(GameCharacter target) {
		return target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	private static boolean isAssAccess(GameCharacter target) {
		return Main.game.isAnalContentEnabled() && target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true);
	}

	private static boolean isVaginaAccess(GameCharacter target) {
		return target.hasVagina() && target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}
	
	private static String getCooperationWarning() {
		return "<br/>[style.italicsSideQuest(如果选择此选项，游戏路线将被锁定为无暴力探索。)]";
	}
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY = new DialogueNode("入口", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_WHORE", getGuards(true));
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_REPEAT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INITIAL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) { // They want sex again:
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntry)) { // They let you in again:
				if(index==1) {
					return new Response("机密", "告诉老鼠们你有私人事务，如果他们阻止你入场，文加会生气。", ENTRANCE_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_REPEAT_ACCESSED", getGuards(true)));
						}
					};
				}
			}
			
			if(index==1) {
				return new Response("阿克塞尔", "告诉帮派成员，你是来和文加讨论阿克塞尔的账单的。", ENTRANCE_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntry, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_AXEL", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new Response("妓女", "告诉帮派成员你是被雇来取悦文加的妓女。", RAT_WARREN_INITIAL_ENTRY_WHORE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensEntryWhore, true);
					}
				};
				
			} else if(index==3) {
				return new Response("挑战", "告诉帮派成员你是来找茬的。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false, 1);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode RAT_WARREN_INITIAL_ENTRY_WHORE = new DialogueNode("入口", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("推辞", "告诉帮派成员，如果文加没第一个操到你，他就会生气。", ENTRANCE_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_NO_SEX", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"做爱 (单人)"
							:"做爱",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "告诉帮派成员你很乐意让他们看看你的能耐，但那个[npc.name]打算为了文加保留[npc.herself]……")
							:"告诉帮派成员你很乐意让他们看看你的能耐……",
						Util.newArrayListOfValues(
								Fetish.FETISH_SUBMISSIVE),
						null,
						CorruptionLevel.THREE_DIRTY,
						null,
						null,
						null,
						true,
						true,
						new SMGeneric(
								getGuards(false),
								Util.newArrayListOfValues(
										Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(
										getMainCompanion())) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						AFTER_ENTRANCE_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_SOLO", getGuards(true)));
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "双飞"),
							UtilText.parse(getGuards(true), "你可以看出，[npc.name]对与[npc2.name]或[npc3.name]发生性关系一点兴趣都没有，你也不能强迫[npc.herHim]这样做……"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "做爱(双人)"),
							UtilText.parse(getGuards(true), "告诉帮派成员，你和[npc.name]都很乐意向他们展示你的能力。"),
							Util.newArrayListOfValues(
									Fetish.FETISH_SUBMISSIVE),
							null,
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null,
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companion),
									null,
									null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_BOTH", getGuards(true)));
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getGuards(true), "你可以看出[npc2.name]对与[npc.name]发生性关系一点兴趣都没有，你也不能强迫[npc2.herHim]这样做……"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getMainCompanion(), "告诉帮派成员，你很乐意让[npc.name]向他们展示[npc.sheIs]的能力，但你要把自己留给文加……"),
							Util.newArrayListOfValues(
									Fetish.FETISH_VOYEURIST),
							null,
							CorruptionLevel.THREE_DIRTY,
							null,
							null,
							null,
							true,
							true,
							new SMGeneric(
									getGuards(false),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
							AFTER_ENTRANCE_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "RAT_WARREN_INITIAL_ENTRY_WHORE_COMPANION", getGuards(true)));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_ENTRANCE_SEX = new DialogueNode("结束", "帮派成员做完了……", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(isCompanionDialogue()) {
				if(Main.sex.getSubmissiveSpectators().contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_COMPANION_WATCHING", getGuards(true));
				} else if(Main.sex.getSubmissiveSpectators().contains(Main.game.getPlayer())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_PLAYER_WATCHING", getGuards(true));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_BOTH", getGuards(true));
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_ENTRANCE_SEX_SOLO", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE_FIGHT = new DialogueNode("", "", true) {
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
				return new ResponseCombat("战斗",
						"抵御这群老鼠的袭击！",
						(NPC)getGuardLeader(),
						getGuards(false),
						new HashMap<>());
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {
		@Override
		public void applyPreParsingEffects() {
//			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
//					|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT
//					|| Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT) {
//				Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
//			}
//			banishTwoGuards();
		}
		
		@Override
		public String getContent() {
			if(getGuards(false).size()==1) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ONE_REMAINING", getGuards(false));
				
			} else if(getGuards(false).isEmpty()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ALL");
				
			} else if(getGuards(false).size()<4) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ENSLAVED_ONE", getGuards(false));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY", getGuards(true));
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getGuards(false).isEmpty()) {
				if(index==0) {
					return "互动";
					
				} else if(index==1) {
					return "物品栏";
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getGuards(false).isEmpty()) {
				if (index == 1) {
					return new Response("继续", "你把那些胆敢对你动手的家伙都奴役了，在这里已经无事可做，只好继续旅程……", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ALL_ENSLAVED"));
						}
					};
				}
				return null;
			}
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("吓退", "告诉那群帮派成员最好趁着还能逃跑赶紧离开……", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(false)));
							banishGuards(true);
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							isCompanionDialogue()
								?"单人做爱"
								:"做爱",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "告诉[npc.name]站到一旁，看着你和这群帮派成员做爱。")
								:"和帮派成员做爱。",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX", getGuards(false)));
					
				} else if (index == 3) {
					return new ResponseSex(
							isCompanionDialogue()
								?"单人做爱(温柔)"
								:"做爱(温柔)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "告诉[npc.name]站到一旁，看着你和这群帮派成员做爱。")
								:"和帮派成员做爱。",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GENTLE", getGuards(false)),
							ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
					
				} else if (index == 4) {
					return new ResponseSex(
							isCompanionDialogue()
								?"单人做爱(粗暴)"
								:"做爱(粗暴)",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "告诉[npc.name]站到一旁，看着你和这群帮派成员做爱。")
								:"和帮派成员做爱。",
							true,
							false,
							Util.newArrayListOfValues(Main.game.getPlayer()),
							getGuards(false),
							Util.newArrayListOfValues(getMainCompanion()),
							null,
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_ROUGH", getGuards(false)),
							ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
					
				} else if (index == 5) {
					return new ResponseSex(
							isCompanionDialogue()
								?"单人屈从"
								:"顺从",
							isCompanionDialogue()
								?UtilText.parse(getMainCompanion(), "告诉[npc.name]站到一旁，看着你向这群帮派成员屈服，让他们主导你。")
								:"告诉那群帮派成员可以随意摆布你……",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null,
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null,
							true,
							false,
							getGuards(false),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							Util.newArrayListOfValues(getMainCompanion()),
							GUARD_COMBAT_VICTORY_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_SUBMIT", getGuards(false)));
					
				} else if (index == 6 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response("群交",
								UtilText.parse(companion, "[npc.Name]没兴趣根这些帮派成员做爱，而且[npc.sheIs]不是奴隶，你也不能强迫[npc.herHim]……"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "群交"),
								UtilText.parse(companion, "跟这些帮派成员来一场支配型性爱，并且让[npc.name]也一同加入。"),
								true,
								false,
								Main.game.getPlayer().getParty(),
								getGuards(false),
								null,
								null,
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GROUP", getGuards(false)));
					}
					
				} else if (index == 7 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response("群体屈从",
								UtilText.parse(companion, "[npc.Name]没兴趣根这些帮派成员做爱，而且[npc.sheIs]不是奴隶，你也不能强迫[npc.herHim]……"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "群体屈从"),
								UtilText.parse(companion, "让[npc.name]跟你一起向这些帮派成员屈服，任他们支配你们两个。"),
								true,
								false,
								getGuards(false),
								Main.game.getPlayer().getParty(),
								null,
								null,
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GROUP_SUBMIT", getGuards(false)));
					}
					
				} else if (index == 8 && isCompanionDialogue()) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "给[npc.name]"),
								UtilText.parse(companion, "[npc.Name]没兴趣根这些帮派成员做爱，而且[npc.sheIs]不是奴隶，你也不能强迫[npc.herHim]……"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
								UtilText.parse(companion, "告诉[npc.name]说[npc.she]可以跟这些帮派成员爽一爽，你在旁边看着。"),
								false,
								false,
								Util.newArrayListOfValues(getMainCompanion()),
								getGuards(false),
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_GIVE_TO_COMPANION", getGuards(false)));
					}
					
				} else if (index == 9 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedToGroup(getGuards(false)) && ((companion.isAbleToRefuseSexAsCompanion()) || !Main.game.isNonConEnabled())) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(companion, "你看得出来[npc.name]并没有兴趣跟这群帮派成员做爱，而且你也不能强迫[npc.herHim]……"),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(companion, "将帮派成员都交给[npc.name]，他们做爱的时候你在一旁观看。"),
								true,
								false,
								getGuards(false),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								GUARD_COMBAT_VICTORY_AFTER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SEX_OFFER_COMPANION", getGuards(false))) {
							@Override
							public void effects() {
								if(!companion.isAttractedToGroup(getGuards(false)) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
					}
					
				} else {
					return null;
				}
				
			} else if(responseTab == 1) {
				for(int i=1; i<=getGuards(false).size(); i++) {
					if(index==i) {
						NPC guard = (NPC) getGuards(false).get(i-1);
						return new ResponseEffectsOnly(UtilText.parse(guard, "[npc.Name]"),
								UtilText.parse(guard, "你已经击败了[npc.name]，要扒衣服还是抢东西都随你的便了……")) {
							@Override
							public void effects() {
								Main.mainController.openInventory(guard, InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_VICTORY_AFTER_SEX = new DialogueNode("退开", "你已经享受够了，便退开来，思考起该对帮派成员做些什么……", true) {

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
				if(isCompanionDialogue() && Main.sex.getAllParticipants(false).contains(getMainCompanion())) {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_BOTH", getGuards(true));
				} else {
					return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_SOLO", getGuards(true));
				}
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_AFTER_SEX_COMPANION", getGuards(true));
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return GUARD_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(getGuards(false).isEmpty()) {
				if (index == 1) {
					return new Response("继续", "你把那些胆敢对你动手的家伙都奴役了，在这里已经无事可做，只好继续旅程……", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_ALL_ENSLAVED"));
						}
					};
				}
				return null;
			}
			if(responseTab==0) {
				if (index == 1) {
					return new Response("吓退", "把帮派成员吓退，继续前进。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishGuards(true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_VICTORY_SCARE_OFF", getGuards(true)));
						}
					};
				}
				
			} else if(responseTab==1) {
				return GUARD_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {
		@Override
		public String getContent() {
			if(!Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_NO_NON_CON", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("被扔出去", "帮派成员一起毫不留情地把你扔回了屈城区隧道里。", PlaceType.SUBMISSION_RAT_WARREN.getDialogue(false)) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_THROWN_OUT", getGuards(true)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, false);
							applyCombatDefeatFlagsReset();
							List<GameCharacter> guards = getGuards(false);
							for(GameCharacter npc : guards) {
								Main.game.banishNPC((NPC) npc);
							}
							exit();
						}
					};
				}
				
			} else {
				if(index == 1) {
					if(isCompanionDialogue()) {
						return new Response("保护[com.name]",
								"动用最后一丝力量拖住这些老鼠，让[com.name]逃走。"
										+ "<br/>[style.italicsMinorGood([com.Name]将会平安无事地回到家。)]",
								GUARD_COMBAT_DEFEAT_STOCKS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_COMPANION_ESCAPE", getGuards(true)));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS", getGuards(true)));
								
								GameCharacter companion = getMainCompanion();
								Main.game.getPlayer().removeCompanion(companion);
								companion.returnToHome();
								
								List<GameCharacter> guards = getGuards(false);
								int count=0;
								for(GameCharacter npc : guards) {
									count++;
									if(count>1) {
										Main.game.banishNPC((NPC) npc);
										continue;
									}
									npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								}
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
							}
						};
						
					} else {
						return new Response("被拖走",
								UtilText.parse(getGuards(false), "你已经无力抵抗，只能任由这群帮派成员把你拖进这匪窝深处……"),
								GUARD_COMBAT_DEFEAT_STOCKS) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS", getGuards(true)));
								List<GameCharacter> guards = getGuards(false);
								int count=0;
								for(GameCharacter npc : guards) {
									count++;
									if(count>1) {
										Main.game.banishNPC((NPC) npc);
										continue;
									}
									npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								}
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	private static SexManagerInterface getStocksManager(List<GameCharacter> guards, SexPace pace) {
		return new SexManagerDefault(
				SexPosition.MILKING_STALL,
				Util.newHashMapOfValues(
						new Value<>(guards.get(0), SexSlotMilkingStall.RECEIVING_ORAL),
						isCompanionDialogue()
							?new Value<>(guards.get(1), SexSlotMilkingStall.RECEIVING_ORAL_TWO)
							:null),
				Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL),
						isCompanionDialogue()
							?new Value<>(getMainCompanion(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO)
							:null)) {
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(guards.contains(character)) {
					if(character.hasPenis()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
						
					} else if(character.hasVagina()) {
						return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
					}
				}
				return character.getForeplayPreference(targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				return character.getForeplayPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
				for(GameCharacter guard : guards) {
					map.put(guard, Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA));
				}
				return map;
			}
			@Override
			public void assignNPCTarget(GameCharacter targeter) {
				if(isCompanionDialogue()) {
					if(targeter.equals(guards.get(0))) {
						Main.sex.setTargetedPartner(targeter, Main.game.getPlayer());
						
					} else if(isCompanionDialogue() && targeter.equals(guards.get(1))) {
						Main.sex.setTargetedPartner(targeter, getMainCompanion());
						
					} else {
						super.assignNPCTarget(targeter);
					}
					
				} else {
					super.assignNPCTarget(targeter);
				}
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				if(character.isPlayer() || character.equals(getMainCompanion())) {
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
				if(character.isPlayer() || character.equals(getMainCompanion())) {
					return false;
				}
				return super.isAbleToRemoveOthersClothing(character, clothing);
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return !equippingCharacter.isPlayer() && !equippingCharacter.equals(getMainCompanion());
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return !character.isPlayer() && !character.equals(getMainCompanion());
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					return pace;
				}
				return super.getStartingSexPaceModifier(character);
			}
		};
	}
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS = new DialogueNode("", "", true) {
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
			if (index == 1) {
				return new Response("被剥光",
						UtilText.parse(getGuards(false), "默克和[npc.name]上来把你扒了个精光……"),
						GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED) {
					@Override
					public void effects() {
						applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Murk.class), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("新家",
						UtilText.parse(getGuards(false), "默克和[npc.name]把你带去了邻接的房间，送去了“新家”……"),
						GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END) {
					@Override
					public void effects() {
						List<GameCharacter> parsingCharacters = new ArrayList<>(getGuards(true));
						parsingCharacters.addAll(getMilkers());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END", parsingCharacters));
						
						List<GameCharacter> guards = getGuards(true);
						for(GameCharacter npc : guards) {
							npc.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						}
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
					}
				};
				
			}
			return null;
		}
	};

	private static List<InitialSexActionInformation> getInitialCapturedStartSexActions(List<GameCharacter> guards) {
		List<InitialSexActionInformation> list = new ArrayList<>();
		
		if(guards.get(0).hasPenis()) {
			list.add(new InitialSexActionInformation(guards.get(0), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
			
		} else if(guards.get(0).hasVagina()) {
			list.add(new InitialSexActionInformation(guards.get(0), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
		}

		if(isCompanionDialogue()) {
			if(guards.get(1).hasPenis()) {
				list.add(new InitialSexActionInformation(guards.get(1), getMainCompanion(), PenisMouth.BLOWJOB_START, false, true));
				
			} else if(guards.get(1).hasVagina()) {
				list.add(new InitialSexActionInformation(guards.get(1), getMainCompanion(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
			}
		}
		
		return list;
	}
	
	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_STRIPPED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> guards = getGuards(false);
			
			if (index == 1) {
				return new ResponseSex("口交",
						UtilText.parse(getGuards(false), "就如他们期待的那样，顺从地给[npc.name]口交起来。"),
						false,
						false,
						getStocksManager(guards, null),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("饥渴地口交",
						UtilText.parse(getGuards(false), "顺从地给[npc.name]口交，展现出你多么地心甘情愿。"),
						false,
						false,
						getStocksManager(guards, SexPace.SUB_EAGER),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX_EAGER", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("抵抗口交",
						UtilText.parse(getGuards(false), "竭尽全力避免给[npc.name]口交。"),
						false,
						false,
						getStocksManager(guards, SexPace.SUB_RESISTING),
						null,
						null,
						GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_SEX_RESIST", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = getInitialCapturedStartSexActions(guards);
						if(!list.isEmpty()) {
							return list;
						}
						return super.getInitialSexActions();
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return UtilText.parse(getGuards(false).get(0), "[npc.Name]已经跟你玩够了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "GUARD_COMBAT_DEFEAT_STOCKS_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("默克", "默克再次出现，告诉了你接下来还有什么在等着你……", RatWarrensCaptiveDialogue.CAPTIVE_DAY_0) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0", getGuards(false)));
						banishGuards(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode ENTRANCE_NO_CONTENT = new DialogueNode("入口", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("入口", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
					return new Response("离开", "这群老鼠封住了门，阻止了你逃离！现在如果想离开，就必须直面文加……", null);
				}
				return new Response("离开", "告诉帮派成员你要离开了，于是回到了屈城区的隧道里。", SubmissionGenericPlaces.RAT_WARREN) {
					@Override
					public void effects() {
						exit();
					}
				};
				
			} else if(index==2 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==3 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==4 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre) && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntryWhore)) {
				return RAT_WARREN_INITIAL_ENTRY_WHORE.getResponse(responseTab, index);
				
			} else if(index==6
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
				return new Response("挑战", "告诉帮派成员你是来找茬的。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(false, 1);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "ENTRANCE_INTITIAL_ENTRY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("曲折通道", "", false) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					spawnGuards(false, 1);
					
				} else {
					spawnGuards(true, 4);
				}
				
			} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
					spawnGuards(false, 1);
					
				} else {
					spawnGuards(true, 4);
				}
			}
		}
		@Override
		public boolean isTravelDisabled() {
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))
				|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))) {
				return true;
			}
			return false;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CHECKPOINT_FIGHT", getGuards(true)));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
					
				} else if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CHECKPOINT_FIGHT", getGuards(true)));
						
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
					
				} else {
					if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_LEFT && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
							|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CORRIDOR_RIGHT && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight))
							|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre)) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_CLEARED", getGuards(true)));
					}
				}
				
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR_HOSTILE_WARNING", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "CORRIDOR", getGuards(true)));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_LEFT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))
				|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_CHECKPOINT_RIGHT
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)!=Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedCentre))) {
				if(index==1) {
					return new ResponseCombat("战斗",
							"不打一架就无法摆脱这群帮派成员，你必须准备好自卫了！",
							(NPC)getGuardLeader(),
							getGuards(false),
							new HashMap<>());
				}
			}
			return null;
		}
	};

	public static final DialogueNode DORMITORY = new DialogueNode("宿舍", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY", getGuards(true)));
			
			if((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
					|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight))) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_OCCUPIED", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_CLEARED", getGuards(true)));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& ((Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_LEFT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft))
							|| (Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.RAT_WARRENS_DORMITORY_RIGHT && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedRight)))) {
				return new Response("挑战", "告诉帮派成员你是来找茬的。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						spawnGuards(true, 4);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DORMITORY_FIGHT", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DICE_DEN = new DialogueNode("骰子赌场", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_ENTRY", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensLootedDiceDen)) {
						return new Response("搜刮吧台", "你已经把吧台上值钱的东西都拿走了……", null);
						
					} else {
						return new Response("搜刮吧台", "看一眼吧台后面，有没有值得一拿的东西……", DICE_DEN_LOOT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensLootedDiceDen, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(1500));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum"), 5, false, true));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_wolf_wolf_whiskey"), 2, false, true));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_race_cat_felines_fancy"), 1, false, true));
							}
						};
					}
				}
				
			} else {
				DicePokerTable table = DicePokerTable.COPPER;
				int buyIn = table.getInitialBet()+table.getRaiseAmount();
				
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("[style.colourMasculine(鼠男)]("+UtilText.formatAsMoney(buyIn, "span")+")",
								"跟其中一个鼠男打骰子扑克。买入金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。") {
							@Override
							public void effects() {
								gambler = generateGambler(Femininity.MASCULINE);
								gambler.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN, true);
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_POST_GAMBLING, "places/submission/ratWarrens/dicePoker")));
							}
						};
						
					} else {
						return new Response("鼠男("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"买入金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。所以你钱不够跟这个鼠男赌！",
								null);
					}
					
				} else if(index==2) {
					if(Main.game.getPlayer().getMoney()>=buyIn) {
						return new ResponseEffectsOnly("[style.colourFeminine(鼠女)]("+UtilText.formatAsMoney(buyIn, "span")+")",
								"跟其中一个鼠女打骰子扑克。买入金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
									+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。") {
							@Override
							public void effects() {
								gambler = generateGambler(Femininity.FEMININE);
								Main.game.setContent(new Response("", "", DicePoker.initDicePoker(gambler, table, DICE_DEN_POST_GAMBLING, "places/submission/ratWarrens/dicePoker")));
							}
						};
						
					} else {
						return new Response("鼠女 ("+UtilText.formatAsMoneyUncoloured(buyIn, "span")+")",
								"买入金额为"+UtilText.formatAsMoney(table.getInitialBet(), "span")
								+"，但想要加注则还需要"+UtilText.formatAsMoney(table.getRaiseAmount(), "span")+"。所以你钱不够跟这个鼠女赌！",
								null);
					}
					
				} else if(index==3) {
					return new Response("规则", UtilText.parse(getGuards(false).get(0), "询问[npc.name]骰子扑克的规则。"), DICE_DEN_RULES);

				} else if(index==4) {
					int price = getRumPrice();
					if(!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						return new Response("朗姆酒("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你的嘴巴被封住了，喝不了朗姆酒……", null);
					}
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("朗姆酒("+UtilText.formatAsMoneyUncoloured(price, "span")+")", "你想买一杯朗姆酒，但没有那么多钱付给吧台后那老鼠……", null);
					}
					return new Response("朗姆酒("+UtilText.formatAsMoney(price, "span")+")", "向吧台后面那老鼠买一杯朗姆酒。", DICE_DEN_RUM) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_DRINK", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_OFFER", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(
									Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(Main.game.getPlayer(), Main.game.getPlayer())
									+ Main.game.getPlayer().incrementMoney(-price));
						}
					};
					
				} else if(index==5 && isCompanionDialogue()) {
					int price = getRumPrice();
					if(!getMainCompanion().isCoverableAreaExposed(CoverableArea.MOUTH)) {
						return new Response("朗姆酒("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoneyUncoloured(price, "span")+")",
								UtilText.parse(getMainCompanion(), "[npc.Name]的嘴巴被堵住了，喝不了朗姆酒……"),
								null);
					}
					if(Main.game.getPlayer().getMoney()<price) {
						return new Response("朗姆酒("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoneyUncoloured(price, "span")+")",
								UtilText.parse(getMainCompanion(), "你想买一杯朗姆酒给[npc.name]，但没有那么多钱付给吧台后那老鼠……"),
								null);
					}
					return new Response("朗姆酒("+UtilText.parse(getMainCompanion(), "[npc.name]")+") ("+UtilText.formatAsMoney(price, "span")+")",
							UtilText.parse(getMainCompanion(), "向吧台后面那老鼠买一杯朗姆酒给[npc.Name]。"),
							DICE_DEN_RUM) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_DRINK_COMPANION", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_OFFER", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(
									Main.game.getItemGen().generateItem("innoxia_race_rat_black_rats_rum").applyEffect(getMainCompanion(), getMainCompanion())
									+ Main.game.getPlayer().incrementMoney(-price));
						}
					};
					
				} else if(index==6
						&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("挑战", "告诉帮派成员你是来找茬的。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]", ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(false, 1); // Set to 1 as the bar-tender makes the 2nd, and the entrance guards make 3rd and 4th
							for(GameCharacter rat : Main.game.getCharactersPresent(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE)) {
								rat.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_DICE_DEN);
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_CHALLENGE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DICE_DEN_LOOT = new DialogueNode("骰子赌场", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_LOOT", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_RULES = new DialogueNode("骰子赌场", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RULES", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("规则", UtilText.parse(getGuards(false).get(0), "你已经问过[npc.name]骰子扑克的规则了。"), DICE_DEN_RULES);
			}
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DICE_DEN_POST_GAMBLING = new DialogueNode("骰子赌场", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.banishNPC(gambler);
			gambler = null;
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_POST_GAMBLING", getGuards(true)));
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DICE_DEN_RUM = new DialogueNode("骰子赌场", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC bartender = (NPC) getGuards(false).get(0);
			if(index==1) {
				return new Response("拒绝", UtilText.parse(bartender, "拒绝[npc.namePos]的提议……"), DICE_DEN);
				
			} else if(index==2) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("接受", UtilText.parse(bartender, "你无法使用自己的嘴巴，所以不能用嘴巴侍奉[npc.name]，来拿回火币……"), null);
				}
				return new ResponseSex(
						"接受",
						UtilText.parse(bartender, "收下"+getRumPrice()+"火币，用嘴巴侍奉[npc.name]……"),
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(bartender, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(!Main.sex.isDom(character)) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return Main.sex.getNumberOfOrgasms(bartender)>=bartender.getOrgasmsBeforeSatisfied();
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return false;
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								if(bartender.hasPenis()) {
									return Util.newHashMapOfValues(
											new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
								} else {
									return Util.newHashMapOfValues(
											new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.VAGINA)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)));
								}
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									if(character.hasPenis()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								return getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}	
						},
						null,
						Util.newArrayListOfValues(getMainCompanion()),
						AFTER_DICE_DEN_ORAL,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_ORAL", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(bartender.hasPenis()) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, Main.game.getPlayer(), PenisMouth.BLOWJOB_START, true, true));
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
						}
					}
				};
				
			} else if (index==3 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("献出[com.name]", UtilText.parse(companion, bartender, "[npc.name]无法使用自己的嘴巴，所以不能用嘴巴侍奉[npc2.name]，来拿回火币……"), null);
				}
				if(!companion.isAttractedToGroup(getGuards(false)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(companion, bartender, "你看得出来[npc.name]不愿意用嘴巴侍奉[npc2.name]，也无法强迫[npc.herHim]……"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, bartender, "收下"+getRumPrice()+"火币，让[npc.Name]用嘴巴侍奉[npc2.name]……"),
									true,
									false,
									new SMStanding(
											Util.newHashMapOfValues(new Value<>(bartender, SexSlotStanding.STANDING_DOMINANT)),
											Util.newHashMapOfValues(new Value<>(companion, SexSlotStanding.PERFORMING_ORAL))) {
										@Override
										public boolean isPublicSex() {
											return false;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(!Main.sex.isDom(character)) {
												return SexControl.ONGOING_ONLY;
											}
											return super.getSexControl(character);
										}
										@Override
										public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
											return false;
										}
										@Override
										public boolean isPositionChangingAllowed(GameCharacter character) {
											return false;
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return Main.sex.getNumberOfOrgasms(bartender)>=bartender.getOrgasmsBeforeSatisfied();
										}
										@Override
										public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
											return false;
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											if(bartender.hasPenis()) {
												return Util.newHashMapOfValues(
														new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.PENIS)),
														new Value<>(companion, Util.newArrayListOfValues(CoverableArea.MOUTH)));
											} else {
												return Util.newHashMapOfValues(
														new Value<>(bartender, Util.newArrayListOfValues(CoverableArea.VAGINA)),
														new Value<>(companion, Util.newArrayListOfValues(CoverableArea.MOUTH)));
											}
										}
										@Override
										public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(!character.equals(companion)) {
												if(character.hasPenis()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											}
											return super.getForeplayPreference(character, targetedCharacter);
										}
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											return getForeplayPreference(character, targetedCharacter);
										}
										@Override
										public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
											return new ArrayList<>();
										}	
									},
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_DICE_DEN_ORAL,
									UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "DICE_DEN_RUM_ORAL_COMPANION", getGuards(true))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									if(bartender.hasPenis()) {
										return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, companion, PenisMouth.BLOWJOB_START, true, true));
									} else {
										return Util.newArrayListOfValues(new InitialSexActionInformation(bartender, companion, TongueVagina.RECEIVING_CUNNILINGUS_START, true, true));
									}
								}
							};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DICE_DEN_ORAL = new DialogueNode("结束", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(getRumPrice()));
		}
		@Override
		public String getDescription() {
			return UtilText.parse((NPC) getGuards(false).get(0), "[npc.Name]做够了……");
		}
		@Override
		public String getContent() {
			if(Main.sex.getSubmissiveParticipants(false).containsKey(getMainCompanion())) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_DICE_DEN_ORAL_COMPANION", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_DICE_DEN_ORAL", getGuards(true));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DICE_DEN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_STORAGE = new DialogueNode("入口", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM).isTravelledTo()) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
			}
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isCaptive()) {
				return 0; // So that the player can't advance days by repeatedly moving back and forth.
			}
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_QUEST_COMPLETE");
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CLEARED", getGuards(true));
			}
			if(Main.game.getPlayer().isCaptive()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CAPTIVE", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			if(Main.game.getPlayer().isCaptive()) {
				if(index==1) {
					return new Response("退开", "锁链限制着你的行动，你并不能离开太远……", RatWarrensCaptiveDialogue.CAPTIVE_NIGHT) {
						@Override
						public int getSecondsPassed() {
							return 0; // So that the player can't advance days by repeatedly moving back and forth.
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM, false);
						}
					};
				}
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(index==1) {
					return new Response("退开", "按默克说的做，离开。", CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
						}
					};
					
				} else if(index==2) {
					if(Main.game.getHourOfDay()<14 || Main.game.getHourOfDay()>=22) {
						return new Response("挤奶工("+UtilText.formatAsMoneyUncoloured(500, "span")+")",
								"默克只有在[style.time(14)]到[style.time(22)]之间才会出租产奶工，如果你想找他们，必须在这段时间内再来。",
								null);
					}
					return new Response("挤奶工("+UtilText.formatAsMoney(500, "span")+")", "付给默克500火币，跟这些“挤奶工”接触。", MILKING_ROOM) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-500));
							Main.game.getNpc(Murk.class).incrementMoney(500);
							for(GameCharacter milker : getMilkers()) {
								((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
							}
						}
					};
					
				} else if(index==6
						&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("挑战", "告诉默克你是来这里打架的。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]", ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkIntroduced, true);
							spawnGuards(true, 4);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_STORAGE_CHALLENGE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode MILKING_ROOM = new DialogueNode("挤奶室", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_QUEST_COMPLETE");
			}
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM", getMilkers());
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CLEARED", getMilkers());
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			if(isCompanionDialogue()) {
				switch(index) {
					case 0:
						return "你";
					case 1:
						return UtilText.parse(getMainCompanion(), "[npc.Name]");
					case 2:
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
							return "双人";
						}
						break;
				}
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_VENGAR)) {
				return null;
			}
			List<GameCharacter> milkers = getMilkers();
			if(responseTab==0) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						return new ResponseSex(
								"使用"+milker.getName(true),
								UtilText.parse(milker, "选择和[npc.name]做爱……"),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
										return false;
									}
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Main.game.getPlayer().getParty(),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
						
					} else {
						return new ResponseSex(
								"使用"+milker.getName(true),
								UtilText.parse(milker, "你已经击败了默克和一同前来的帮派成员，现在没人能阻止你跟[npc.name]做爱了……"),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Main.game.getPlayer().getParty(),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
					}
				}
				
			} else if(responseTab==1) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					
					if(!getMainCompanion().isAttractedTo(milker) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker,
										"你看得出来[npc.name]没兴趣跟[npc2.name]做爱，也不能强迫[npc.herHim]……"),
								null);
					}
					
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
						return new ResponseSex(
								UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker, "让[npc.name]去干[npc2.name]，而你和默克在一旁观看……"),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(getMainCompanion(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
										return false;
									}
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_COMPANION", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
						
					} else {
						return new ResponseSex(
								UtilText.parse(milker, "[npc.Name]"),
								UtilText.parse(getMainCompanion(), milker, "你已经击败了默克和一同前来的帮派成员，现在没人能阻止你命令[npc.Name]跟[npc2.name]做爱了……"),
								true,
								false,
								new SMMilkingStall(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
										Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
									@Override
									public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
										return false;
									}
									@Override
									public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
										if(character instanceof RatWarrensCaptive) {
											return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										} else {
											return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
										}
									}
								},
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								AFTER_MILKER_SEX,
								UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_COMPANION_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
							}
						};
					}
				}
				
			} else if(responseTab==2) {
				if(index>=1 && index<=4) {
					GameCharacter milker = milkers.get(index-1);
					
					if(!getMainCompanion().isAttractedTo(milker) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(milker, "双人([npc.Name])"),
								UtilText.parse(getMainCompanion(), milker,
										"你看得出来[npc.name]没兴趣跟[npc2.name]做爱，也不能强迫[npc.herHim]……"),
								null);
					}
					
					return new ResponseSex(
							UtilText.parse(milker, "双人([npc.Name])"),
							UtilText.parse(getMainCompanion(), milker, "你已经击败了默克和一同前来的帮派成员，现在没人能阻止你和[npc.Name]跟[npc2.name]做爱了……"),
							true,
							false,
							new SMMilkingStall(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.BEHIND_MILKING_STALL),
											new Value<>(getMainCompanion(), SexSlotMilkingStall.RECEIVING_ORAL)),
									Util.newHashMapOfValues(new Value<>(milker, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))) {
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isSlotAvailable(GameCharacter character, SexSlot slot) {
									if(character instanceof RatWarrensCaptive) {
										return slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
									} else {
										return !slot.hasTag(SexSlotTag.LOCKED_IN_STOCKS);
									}
								}
							},
							null,
							null,
							AFTER_MILKER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_SEX_BOTH_AFTER_CLEARED", Util.newArrayListOfValues(isCompanionDialogue()?getMainCompanion():null, milker))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						}
					};
				}
			}
			
			if(index==5) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensMilkersFreeAttempt)) {
						return new Response(
								"释放俘虏",
								"你已经尝试过解救这些产奶工，但却发现他们都不愿意跟你逃走……",
								null);
					}
					return new Response(
							"释放俘虏",
							"你已经击败该区域的帮派成员，现在没人能阻止你释放这些被俘虏的人类了……",
							MILKING_ROOM_FREE_ATTEMPT) {
						@Override
						public void effects() {
							 Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensMilkersFreeAttempt, true);
						}
					};
					
				} else {
					return new Response(
							"产奶工",
							"询问默克他是怎么得到这些“产奶工”的……",
							MILKING_ROOM_BACKGROUND) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensMilkersBackground, true);
						}
					};
				}
				
			} else if(index==6
					&& !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return new Response("与默克战斗", "告诉默克你是来这里打架的。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]", ENTRANCE_FIGHT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_LEFT, false);
						spawnGuards(true, 4);
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE_CORE", getGuards(true)));
					}
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
				
			} else if(index==0 && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return new Response("离开", "决定还是不跟产奶工做爱，直接离开……<br/>[style.italicsBad(交出去的钱不会再还回来！)]", MILKING_ROOM_BACKED_OUT) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
						for(GameCharacter milker : getMilkers()) {
							((RatWarrensCaptive)milker).applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MILKING_ROOM_BACKED_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_BACK_OUT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_STORAGE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode AFTER_MILKER_SEX = new DialogueNode("结束", "退开。你已经爽够了，考虑之后去做什么……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSeenMilkers, true);
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
			Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			List<GameCharacter> characters = new ArrayList<>();
			if(isCompanionDialogue()) {
				characters.add(getMainCompanion());
			}
			characters.add(Main.sex.getSubmissiveParticipants(false).keySet().iterator().next());
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX", characters);
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX_CLEARED", characters);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensClearedLeft)) {
				if(index==6 && !Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION)) {
					return new Response("与默克战斗",
							"告诉默克这些女的现在都归你，给他来点教训。<br/>[style.italicsBad(毫无疑问这会引来一大帮难搞的帮派成员来支援！)]",
							ENTRANCE_FIGHT) {
						@Override
						public void effects() {
							spawnGuards(true, 4);
							Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "AFTER_MILKER_SEX_CHALLENGE", getGuards(true)));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_CHALLENGE_CORE", getGuards(true)));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
				}
				return MILKING_STORAGE.getResponse(responseTab, index);
			} else {
				return MILKING_ROOM.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode MILKING_ROOM_BACKGROUND = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_BACKGROUND", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response(
						"产奶工",
						"你已经问过默克是怎么得到这些“产奶工”的了！",
						null);
			}
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MILKING_ROOM_FREE_ATTEMPT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MILKING_ROOM_FREE_ATTEMPT", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MILKING_ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode VENGARS_HALL = new DialogueNode("文加的大厅", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("接近",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)
							?"接近文加，准备跟他战斗。"
							:(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)
								?"接近文加，准备跟他谈话。"
								:"接近文加，向他自我介绍。"),
						VENGARS_HALL_APPROACH) {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
							if(!Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT)) {
								if(isCompanionDialogue()) {
									if(!getMainCompanion().hasTraitActivated(Perk.OBSERVANT)) {
										Main.game.getTextStartStringBuilder().append(getMainCompanion().incrementHealth(-Main.game.getPlayer().getHealth()*0.75f));
									}
									
								} else {
									Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementHealth(-Main.game.getPlayer().getHealth()/2));
								}
							}
						}
					}
				};
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)) {
				if(index==0) {
					return new Response("离开", "决定不接近文加，反而是转头离开大厅。", PlaceType.RAT_WARRENS_CORRIDOR_RIGHT.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						}
					};
				}
				
			} else {
				if(index==2 && Main.game.getPlayer().hasItemType(ItemType.RESONANCE_STONE)) {
					return new Response("回声石", "使用回声石，向SWORD执法者传递信号，开始突袭。", VENGARS_HALL_RESONANCE_STONE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensUsedResonanceStone, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			// Repeat encounters (after initial quest is resolved):
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.vengarIntroduced)) {
				if(index==1) {
					return new Response("卧室",
							"跟文加一起去到他私人的寝室……",
							VENGARS_BEDROOM) {
						@Override
						public boolean isSexHighlight() {
							return true;	
						}
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
							Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
							}
						}
					};
					
				} else if(index==2 && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.axelSissified)) {
					if(!Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine()
							&& Main.game.getPlayer().isFeminine()
							&& !Main.game.isVoluntaryNTREnabled()) {
						return new Response("莱克萨", "莱克萨并没有被你吸引，所以你不能跟文加一起去和她做爱……", null);
					}
					return new Response("莱克萨", "跟文加一起去见莱克萨，做个不速之客……", LEXA_VISIT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							Main.game.getNpc(Vengar.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_OFFICE, false);
							for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
								character.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
							}
						}
					};
					
				} else if(index==0) {
					return new Response("离开", "告诉文加你现在没什么需求，但是过后你可能还会回来。", PlaceType.RAT_WARRENS_CORRIDOR_RIGHT.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						}
					};
				}
				
			} else {
				if(index==1) {
					// Fight
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response(
								"准备",
								"准备自卫！",
								VENGARS_HALL_FIGHT_NO_CONTENT) {
							@Override
							public void effects() {
								boolean surprised = !isCompanionDialogue() && !Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT);
								boolean companionSurprised = isCompanionDialogue() && !Main.game.getPlayer().hasTraitActivated(Perk.OBSERVANT) && !getMainCompanion().hasTraitActivated(Perk.OBSERVANT);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								if(surprised) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT_SURPRISED", getGuards(true)));
								} else if(companionSurprised) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT_SURPRISED_COMPANION", getGuards(true)));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_FIGHT", getGuards(true)));
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
							}
							@Override
							public boolean isCombatHighlight() {
								return true;
							}
						};
					}
					return new Response("挑战", "告诉文加你是来这里给他和他的帮派带来毁灭的。", VENGARS_HALL_FIGHT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensHostile, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_CHALLENGE", getGuards(true)));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("说服", "文加将你视作敌人，无论你再怎么巧舌如簧他也不会多听一句……", null);
					}
					return new Response("说服", "尝试说服文加，不要再从阿克塞尔那里勒索保护费……", VENGARS_HALL_APPROACH_PERSUADE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarPersuaded, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("威胁", "你没有可能吓住文加，你需要先解决他的保镖！", null);
					}
					if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)
							|| Main.game.getPlayer().getRace()==Race.DEMON
							|| Main.game.getPlayer().getLevel()>=35) {
						return new Response("威胁",
								"威胁文加，他会在没有保镖保护的情况下直接跟你战斗。"
										+ "<br/>[style.italicsMinorGood("
											+(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)
													?"由于你中二病的行为"
													:(Main.game.getPlayer().getRace()==Race.DEMON
														?"由于你恶魔的身份"
														:"由于你已经超过35级"))
											+"而解锁。)]",
								VENGARS_HALL_APPROACH_THREATEN) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarThreatened, true);
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -75));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
							}
						};
					}
					return new Response("威胁",
							"你没法威胁文加，让他不再继续勒索阿克塞尔。"
							+ "<br/>[style.italicsMinorBad(你需要达到文加等级的两倍、身为恶魔，或者开启了“"+Perk.CHUUNI.getName(Main.game.getPlayer())+"”特性，才能成功吓住他。)]",
							null);
					
				} else if(index==4) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensHostile)) {
						return new Response("诱惑", "文加将你视作敌人，无论你如何诱惑他也不会多瞧一眼……", null);
					}
					if(Main.game.getPlayer().hasTraitActivated(Perk.CONVINCING_REQUESTS)
							|| Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)
							|| Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_LUST)>=75) {
						return new Response("诱惑",
								"诱惑文加，尝试借此劝说他停止从阿克塞尔那里勒索保护费。"
										+ "<br/>[style.italicsMinorGood(由于开启了“"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"”特性而解锁。)]"
										+getCooperationWarning(),
								VENGARS_HALL_APPROACH_SEDUCE) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.vengarSeduced, true);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_PRIVATE_BEDCHAMBERS, false);
								for(GameCharacter character : Main.game.getPlayer().getCompanions()) {
									character.setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
							}
						};
					}
					return new Response("诱惑",
							"你的诱惑技巧不精，只靠这样无法劝说文加不再从阿克塞尔那里勒索保护费。"
									+ "<br/>[style.italicsMinorBad(需要开启“"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"”特性，"
											+ "至少拥有75性欲伤害加成，或者“"+Spell.TELEPATHIC_COMMUNICATION.getName()+"”法术获得了“"+SpellUpgrade.TELEPATHIC_COMMUNICATION_3.getName()+"”升级。)]",
							null);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_FIGHT_NO_CONTENT = new DialogueNode("", "", true, true) {
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
				return new ResponseCombat("战斗",
						"跟文加的鼠女保镖战斗。",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Shadow.class), Main.game.getNpc(Silence.class)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Shadow.class), ""),
								new Value<>(Main.game.getNpc(Silence.class), "")));
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(PERSUASION_PAYMENT), true);
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<PERSUASION_PAYMENT) {
					return new Response("支付("+UtilText.formatAsMoneyUncoloured(PERSUASION_PAYMENT, "span")+")", "你没有"+UtilText.formatAsMoney(PERSUASION_PAYMENT, "span")+"，满足不了文加的要求……", null);
				}
				return new Response("支付("+UtilText.formatAsMoney(PERSUASION_PAYMENT, "span")+")",
						"同意付给文加"+UtilText.formatAsMoney(PERSUASION_PAYMENT, "span")+"以表臣服……"
						+getCooperationWarning(),
						VENGARS_HALL_APPROACH_PERSUADE_PAY) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-PERSUASION_PAYMENT));
						Main.game.getNpc(Vengar.class).incrementMoney(PERSUASION_PAYMENT);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
					}
				};
				
			} else if(index==2) {
				return new Response("下跪",
						"在文加面前下跪以表臣服……",
						VENGARS_HALL_APPROACH_PERSUADE_KNEEL) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3) {
				return new Response("拒绝", "拒不交钱。文加不会给你好脸色的……", VENGARS_HALL_FIGHT_NO_CONTENT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_REFUSE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -25));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_PAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(Util.intToString(PERSUASION_PAYMENT), true);
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_PAY", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("同意", "既然已经决定用非暴力的方法，同意交出阿克塞尔，向他表示臣服，这似乎是唯一的选择。", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_END_AND_LEAVE", getGuards(true)));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_KNEEL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("屈服",
						"按照文加的要求，充当他的脚凳。"
								+getCooperationWarning(),
						VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝", "你拒绝如文加的要求那般轻贱自己。他不会给你好脸色的……", VENGARS_HALL_FIGHT_NO_CONTENT) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL_REFUSE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Vengar.class).setAffection(Main.game.getPlayer(), -25));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_CONFLICT));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_KNEEL_SUBMIT_START", getGuards(true)));
			
			if(isAssAccess(Main.game.getPlayer()) || isVaginaAccess(Main.game.getPlayer())) {
				if(isCompanionDialogue()) {
					if(Main.game.isVoluntaryNTREnabled() && (isAssAccess(getMainCompanion()) || isVaginaAccess(getMainCompanion()))) {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH", getGuards(true)));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_COMPANION_WATCHING", getGuards(true)));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_SOLO", getGuards(true)));
				}
				
			} else {
				if(isCompanionDialogue()) {
					if(isAssAccess(getMainCompanion()) || isVaginaAccess(getMainCompanion())) {
						if(Main.game.isVoluntaryNTREnabled()) {
							sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_PLAYER_WATCHING", getGuards(true)));
						} else {
							sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
						}
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX", getGuards(true)));
				}
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isAssAccess(Main.game.getPlayer()) && !isVaginaAccess(Main.game.getPlayer())) {
				if(isCompanionDialogue()) {
					if(!isAssAccess(getMainCompanion()) && !isVaginaAccess(getMainCompanion())) {
						if(index==1) {
							return new Response("继续", "文加不能强上你们，所以只是说出他要让你做的事情便满足了。", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
								}
							};
						}
						return null;
						
					} else {
						if(!Main.game.isVoluntaryNTREnabled()) {
							if(index==1) {
								return new Response("继续", "文加不能强上你，所以只是说出他要让你做的事情便满足了。", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
									}
								};
							}
							return null;
							
						} else {
							if(index==1) {
								return new Response("起立",
										UtilText.parse(getMainCompanion(), "不同意让文加在大庭广众之下操[npc.name]，提醒他你已经按他要求的做了。"),
										VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
									@Override
									public void effects() {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_REFUSE_WATCH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH", getGuards(true)));
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
									}
								};
								
							} else if(index==2) {
								if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Vengar.class)) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
									return new Response("同意",
											UtilText.parse(getMainCompanion(), "你看得出来[npc.name]没兴趣跟文加做爱，也没法强迫[npc.herHim]……"),
											null);
								}
								return new ResponseSex(
										"同意",
										UtilText.parse(getMainCompanion(), "让文加操[npc.name]，你和其他人都在一旁观看。"),
										true,
										false,
										new SMVengarDominantSex(
												SexPosition.ALL_FOURS,
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
												Util.newHashMapOfValues(
														new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS))),
										Util.newArrayListOfValues(
												Main.game.getNpc(Shadow.class),
												Main.game.getNpc(Silence.class)),
										Util.newArrayListOfValues(
												Main.game.getPlayer()),
										VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
										UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(getMainCompanion())?"VENGARS_HALL_SUB_SEX_WATCH":"VENGARS_HALL_SUB_SEX_WATCH_ANAL", getGuards(true))) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										if(isVaginaAccess(getMainCompanion())) {
											return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
											
										} else if(isAssAccess(getMainCompanion())) {
											return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
										}
										return super.getInitialSexActions();
									}
								};
							}
						}
						return null;
					}
					
				} else {
					if(index==1) {
						return new Response("继续", "文加不能强上你，所以只是说出他要让你做的事情便满足了。", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_NO_SEX_FINISH"));
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK"));
							}
						};
					}
					return null;
				}
			}
			
			if(index==1) {
				return new Response("起立",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "不同意文加在大庭广众下操你或者操[npc.name]，提醒他你已经按他要求的做过很多了。")
							:"不同意文加在大庭广众之下操你，提醒他你已经按他要求的做了。",
						VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_REFUSED", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						isCompanionDialogue()
							?"展示自己(单人)"
							:"展示自己",
						isCompanionDialogue()
							?UtilText.parse(getMainCompanion(), "让[npc.name]站到一旁，上前之后抬起屁股对准文加，将自己的下体展露无遗，等待着他在大庭广众之下操你。")
							:"上前之后抬起屁股对准文加，将自己的下体展露无遗，等待着他在大庭广众之下操你。",
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								getMainCompanion()),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(Main.game.getPlayer())?"VENGARS_HALL_SUB_SEX_SOLO_START":"VENGARS_HALL_SUB_SEX_SOLO_START_ANAL", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(isVaginaAccess(Main.game.getPlayer())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(Main.game.getPlayer())) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						return super.getInitialSexActions();
					}
				};
				
			} else if (index == 3 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!getMainCompanion().isAttractedTo(Main.game.getNpc(Vengar.class)) && getMainCompanion().isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "展示自己"),
							UtilText.parse(getMainCompanion(), "你看得出来[npc.name]没兴趣跟文加做爱，也没法强迫[npc.herHim]……"),
							null);
					
				} else {
					return new Response(
							UtilText.parse(companion, "展示自己"),
							UtilText.parse(getMainCompanion(), "让[npc.name]跟你一起，上前之后抬起屁股对准文加，将自己的下体展露无遗，等待着他在大庭广众之下操你们。"),
							VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
				}
				
			} else if (index == 4 && isCompanionDialogue() && Main.game.isVoluntaryNTREnabled()) {
				GameCharacter companion = getMainCompanion();

				if(!companion.isAttractedTo(Main.game.getNpc(Vengar.class)) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(companion, "你看得出来[npc.name]没兴趣跟文加做爱，也没法强迫[npc.herHim]……"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
							UtilText.parse(getMainCompanion(), "告诉文加虽然你自己对这个注意没什么兴趣，但很乐意看着他在大庭广众之下操[npc.name]……"),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS))),
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
									Main.game.getPlayer()),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", isVaginaAccess(getMainCompanion())?"VENGARS_HALL_SUB_SEX_WATCH":"VENGARS_HALL_SUB_SEX_WATCH_ANAL", getGuards(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(isVaginaAccess(getMainCompanion())) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_SEX_DOUBLE_CHOICE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("首先挨操",
						UtilText.parse(getMainCompanion(),
							"用你的[pc.hips]顶住文加，示意他先操你。"
								+ (!isMouthAccess(Main.game.getPlayer())
									?"因为你无法使用自己的嘴巴，不能给[npc.name]口交，所以[npc.she]只能眼睁睁看着文加操你，静候着轮到[npc.her]……"	
									:(isVaginaAccess(getMainCompanion())
										?"你又得到了一个任务，文加操你的时候你要给[npc.name]舔阴，这样就能在跟你做完之后，立刻为文加的肉棒准备好。"
										:(isAssAccess(getMainCompanion())
											?"你又得到了一个任务，文加操你的时候你要给[npc.name]吻肛，这样就能在跟你做完之后，立刻为文加的肉棒准备好。"
											:"文加操你的时候，[npc.Name]必须在一旁看着，静候着轮到[npc.her]……")))),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
										!isMouthAccess(Main.game.getPlayer()) || (!isVaginaAccess(getMainCompanion()) && !isAssAccess(getMainCompanion()))
											?null
											:new Value<>(getMainCompanion(),
												isVaginaAccess(getMainCompanion())
													?SexSlotAllFours.IN_FRONT
													:SexSlotAllFours.IN_FRONT_ANAL))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								!isMouthAccess(Main.game.getPlayer())
									?getMainCompanion()
									:isVaginaAccess(getMainCompanion())
										?null
										:(isAssAccess(getMainCompanion())
											?null
											:getMainCompanion())),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_PLAYER_FIRST", getGuards(true))) {
					
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						if(isVaginaAccess(Main.game.getPlayer())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(Main.game.getPlayer())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}

						if(isMouthAccess(Main.game.getPlayer())) {
							if(isVaginaAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueAnus.ANILINGUS_START, false, true));
							}
						}
						
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("第二个挨操",
						UtilText.parse(getMainCompanion(), 
							!isMouthAccess(getMainCompanion())
								?"你稍稍离开了文加一点，意思是想让他先操你的同伴。"
										+ "因为[npc.name]无法使用自己的嘴巴，不能给你口交，所以你只能眼睁睁地看着文加操[npc.herHim]，静候着轮到你……"
								:isVaginaAccess(Main.game.getPlayer())
									?"让[npc.name]给你舔阴，意思是想让文加先操你的同伴。"
									:(isAssAccess(getMainCompanion())
										?"让[npc.name]爬到你身后给你舔肛，意思是想让文加先操你的同伴。"
										:"你稍稍离开了文加一点，意思是想让他先操你的同伴。")),
						true,
						false,
						new SMVengarDominantSex(
								SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
										!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
											?null
											:new Value<>(Main.game.getPlayer(),
												isVaginaAccess(Main.game.getPlayer())
													?SexSlotAllFours.IN_FRONT
													:SexSlotAllFours.IN_FRONT_ANAL))),
						Util.newArrayListOfValues(
								Main.game.getNpc(Shadow.class),
								Main.game.getNpc(Silence.class)),
						Util.newArrayListOfValues(
								!isMouthAccess(getMainCompanion())
									?Main.game.getPlayer()
									:isVaginaAccess(Main.game.getPlayer())
										?null
										:(isAssAccess(Main.game.getPlayer())
											?null
											:Main.game.getPlayer())),
						VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_COMPANION_FIRST", getGuards(true))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						List<InitialSexActionInformation> list = new ArrayList<>();
						
						if(isVaginaAccess(getMainCompanion())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(isAssAccess(getMainCompanion())) {
							list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
						
						if(isMouthAccess(getMainCompanion())) {
							if(isVaginaAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
								
							} else if(isAssAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
							}
						}
						
						if(!list.isEmpty()) {
							return list;
						}
						
						return super.getInitialSexActions();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE = new DialogueNode("结束", "文加满足了，结束了做爱……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE_PLAYER_NEXT", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_DOUBLE_COMPANION_NEXT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> previousWetAreas = new HashMap<>(Main.sex.getAllWetAreas()); // Starting lube from saliva
			
			if(index==1) {
				if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
					return new ResponseSex("挨操",
							UtilText.parse(getMainCompanion(),
								!isMouthAccess(Main.game.getPlayer())
									?"你抬起屁股，准备让文加操。"
									:isVaginaAccess(getMainCompanion())
										?"你开始舔食从[npc.namePos]小穴中流出的精液，准备下一个挨文加操。"
										:(isAssAccess(getMainCompanion())
												?"你开始舔食从[npc.namePos]屁穴中流出的精液，准备下一个挨文加操。"
												:"你抬起屁股，准备让文加操。")),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS),
											!isMouthAccess(Main.game.getPlayer()) || (!isVaginaAccess(getMainCompanion()) && !isAssAccess(getMainCompanion()))
												?null
												:new Value<>(getMainCompanion(),
													isVaginaAccess(getMainCompanion())
														?SexSlotAllFours.IN_FRONT
														:SexSlotAllFours.IN_FRONT_ANAL))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									return previousWetAreas;
								}
							},
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
								!isMouthAccess(Main.game.getPlayer())
									?getMainCompanion()
									:isVaginaAccess(getMainCompanion())
										?null
										:(isAssAccess(getMainCompanion())
											?null
											:getMainCompanion())),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_PLAYER_SECOND_START", getGuards(true))) {
						
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							if(isVaginaAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(Main.game.getPlayer())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							}

							if(isMouthAccess(Main.game.getPlayer())) {
								if(isVaginaAccess(getMainCompanion())) {
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueVagina.CUNNILINGUS_START, false, true));
									
								} else if(isAssAccess(getMainCompanion())) {
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), getMainCompanion(), TongueAnus.ANILINGUS_START, false, true));
								}
							}
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
					
				} else {
					return new ResponseSex(
							!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
								?UtilText.parse(getMainCompanion(), "轮到[npc.NamePos]")
								:"用嘴巴清洁",
							UtilText.parse(getMainCompanion(),
								!isMouthAccess(getMainCompanion())
									?"眼看着文加接下来要操[npc.name]……"
									:isVaginaAccess(Main.game.getPlayer())
										?"让[npc.name]舔食你小穴里流出的精液，文加接下来准备操[npc.herHim]。"
										:(isAssAccess(Main.game.getPlayer())
											?"让[npc.name]爬到你身后，舔食你屁穴中流出的精液，文加接下来准备操[npc.herHim]。"
											:"眼看着文加接下来要操[npc.name]……")),
							true,
							false,
							new SMVengarDominantSex(
									SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Vengar.class), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(
											new Value<>(getMainCompanion(), SexSlotAllFours.ALL_FOURS),
											!isMouthAccess(getMainCompanion()) || (!isVaginaAccess(Main.game.getPlayer()) && !isAssAccess(Main.game.getPlayer()))
												?null
												:new Value<>(Main.game.getPlayer(),
													isVaginaAccess(Main.game.getPlayer())
														?SexSlotAllFours.IN_FRONT
														:SexSlotAllFours.IN_FRONT_ANAL))),
							Util.newArrayListOfValues(
									Main.game.getNpc(Shadow.class),
									Main.game.getNpc(Silence.class)),
							Util.newArrayListOfValues(
								!isMouthAccess(getMainCompanion())
									?Main.game.getPlayer()
									:isVaginaAccess(Main.game.getPlayer())
										?null
										:(isAssAccess(Main.game.getPlayer())
											?null
											:Main.game.getPlayer())),
							VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_SUB_SEX_BOTH_COMPANION_SECOND_START", getGuards(true))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							
							if(isVaginaAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(isAssAccess(getMainCompanion())) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), getMainCompanion(), PenisAnus.PENIS_FUCKING_START, false, true));
							}
							
							if(isMouthAccess(getMainCompanion())) {
								if(isVaginaAccess(Main.game.getPlayer())) {
									list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
									
								} else if(isAssAccess(Main.game.getPlayer())) {
									list.add(new InitialSexActionInformation(getMainCompanion(), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true));
								}
							}
							
							if(!list.isEmpty()) {
								return list;
							}
							
							return super.getInitialSexActions();
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX = new DialogueNode("结束", "文加满足了，结束了性爱……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getSexPositionSlot(Main.game.getPlayer())!=SexSlotAllFours.ALL_FOURS) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_COMPANION", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("起身", "站起来等候文加接下来的命令……", VENGARS_HALL_APPROACH_PERSUADE_FINISH) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_AFTER_SEX_FINISH", getGuards(true)));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_PERSUADE_FINISH = new DialogueNode("", "", true, true) {
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
				return new Response("同意", "既然已经决定用非暴力的方法，同意交出阿克塞尔，向他表示臣服，这似乎是唯一的选择。", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_PERSUADE_END_AND_LEAVE", getGuards(true)));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().hasTraitActivated(Perk.CHUUNI)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_CHUUNI", getGuards(true)));
				
			} else if(Main.game.getPlayer().getRace()==Race.DEMON) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_DEMON", getGuards(true)));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN", getGuards(true)));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("战斗",
						"抵御文加的攻击！",
						Main.game.getNpc(Vengar.class));
				
			} else if(index==2) {
				if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.MARTIAL_ARTIST)
						|| Main.game.getPlayer().getAttributeValue(Attribute.DAMAGE_UNARMED)>=75
						|| Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					return new Response("击昏",
							"趁着文加匆忙，将其一击击昏。"
									+ "<br/>[style.italicsMinorGood("
									+(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)
										?"拥有“"+SpellUpgrade.SLAM_3.getName()+"”升级的“"+Spell.SLAM.getName()+"”而解锁。"
										:(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.MARTIAL_ARTIST)
											?"拥有“"+Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())+"”特性而解锁。"
											:"拥有超过75徒手攻击加成而解锁。"))
									+ ")]",
							VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT);
					
				} else {
					return new Response("击昏",
							"你无法抓住文加匆忙的机会……"
									+ "<br/>[style.italicsMinorBad(需要“"+Perk.MARTIAL_ARTIST.getName(Main.game.getPlayer())+"特性，"
											+ "超过75徒手伤害加成，或者拥有“"+SpellUpgrade.SLAM_3.getName()+"”升级的“"+Spell.SLAM.getName()+"”。)]",
							null);
				}
				
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SLAM", getGuards(true)));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT", getGuards(true)));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_END", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("自卫", "保卫自己免受鼠女的攻击！", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("准备好", "无论接下来发生什么，都要做好准备！", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID);
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "你别无选择，只能等待前线巡逻队现身……", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE) {
					@Override
					public void effects() {
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_SWORD_RAID_CLAIRE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "你别无选择，只能照克莱尔的说法，跟她离开鼠窟……", SWORD_RAID_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SWORD_RAID_EXIT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			applyRatWarrensRaid();
		}
		@Override
		public int getSecondsPassed() {
			return 6*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "SWORD_RAID_EXIT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			if(Main.game.getPlayer().hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_TELEPATHY", getGuards(true)));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE", getGuards(true)));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_END", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("只挑逗",
						"调戏文加，保证解决阿克塞尔的事情后就会跟他做。",
						VENGARS_HALL_APPROACH_SEDUCE_NO_SEX);
				
			} else if(index==2) {
				return new ResponseSex(
						"顺从",
						"告诉文加他可以对你为所欲为……",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_SEX_SUBMISSIVE", getGuards(true)));
				
			} else if(index==3) {
				return new ResponseSex(
						"支配",
						"告诉文加他应该向你屈服……",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								null,
								null),
						VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_SEX_DOMINANT", getGuards(true)));	
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE_NO_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_NO_SEX", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("同意", "既然已经决定用非暴力的方法，同意交出阿克塞尔，向他表示臣服，这似乎是唯一的选择。", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_NO_SEX_LEAVE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX = new DialogueNode("结束", "文加很满足，结束了性爱……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX", getGuards(true)));
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_QUITTING_TALK", getGuards(true)));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("同意", "既然已经决定用非暴力的方法，同意交出阿克塞尔，向他表示臣服，这似乎是唯一的选择。", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_APPROACH_SEDUCE_AFTER_SEX_LEAVE", getGuards(true)));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_COOPERATION));
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode VENGARS_HALL_RESONANCE_STONE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_HALL_RESONANCE_STONE", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("自卫", "保卫自己免受鼠女的攻击！", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
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
			return CORRIDOR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_SHADOW_DEFEATED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Spell.ELEMENTAL_AIR.applyEffect(Main.game.getNpc(Silence.class), Main.game.getNpc(Silence.class), true, false);
			((Silence)Main.game.getNpc(Silence.class)).initElemental();
			Main.game.getNpc(Silence.class).setHealthPercentage(1);
			Main.game.getNpc(Silence.class).setManaPercentage(1);
			Main.game.getNpc(Silence.class).addStatusEffect(StatusEffect.SPECIAL_SILENCE_TRANCE, 8*60*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_SHADOW_DEFEATED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("默",
						"看来你必须把默和刚刚召唤出来的元素体击败！",
						Main.game.getNpc(Silence.class),
						Util.newArrayListOfValues(Main.game.getNpc(Silence.class), Main.game.getNpc(Silence.class).getElemental()),
						null);
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_SILENCE_DEFEATED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setHealthPercentage(1);
			Main.game.getNpc(Shadow.class).setManaPercentage(1);
			Main.game.getNpc(Shadow.class).addStatusEffect(StatusEffect.SPECIAL_SHADOW_BESERK, 8*60*60);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_SILENCE_DEFEATED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("影",
						"看来你必须击败盛怒中的影！",
						Main.game.getNpc(Shadow.class),
						Util.newArrayListOfValues(Main.game.getNpc(Shadow.class)),
						null);
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_VICTORY", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("文加",
						"你已经解决了文加的保镖，现在该直面文加了！",
						Main.game.getNpc(Vengar.class));
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_NO_NON_CON", getGuards(true));
			}
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					return new Response("自卫", "保护自己免受鼠女的攻击！", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS); //TODO test
				}
				
			} else {
				if(index==1) { //TODO
					if(isCompanionDialogue()) {
						return new Response("保护[com.name]",
								"动用最后一丝力量拖住这些老鼠，让[com.name]逃走。"
										+ "<br/>[style.italicsMinorGood([com.Name]将会平安无事地回到家。)]",
								BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_COMPANION_ESCAPE"));
								
								GameCharacter companion = getMainCompanion();
								Main.game.getPlayer().removeCompanion(companion);
								companion.returnToHome();

								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
							}
						};
						
					} else {
						return new Response("被拖走",
								"你无力抵抗，只能任由文加把你拖进这匪窝深处……",
								BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF) {
							@Override
							public void effects() {
								Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
								
							}
						};
					}
				}
				
				
				// Lead-in to Vengar sex slave content, which was set aside in v0.3.9 for later inclusion.
//				if(index==1) {
//					if(isCompanionDialogue()) {
//						return new Response(UtilText.parse(getMainCompanion(), "Save [npc.name]"),
//								UtilText.parse(getMainCompanion(), "Use the last of your energy to hold off the rats long enough for [npc.name] to escape."
//										+ "<br/>[style.italicsMinorGood(Unlocked by having the 'Involuntary NTR' content setting turned off."
//											+ " [npc.Name] will safely return home, but the rest of the loss route will proceed as normal.)]"),
//								VengarCaptiveDialogue.FINAL_COMBAT_DEFEAT_STRIPPED) {
//							@Override
//							public void effects() {
//								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_COMPANION_ESCAPE", getGuards(true)));
//								
//								GameCharacter companion = getMainCompanion();
//								Main.game.getPlayer().removeCompanion(companion);
//								companion.returnToHome();
//
//								Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
//								applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), PresetColour.CLOTHING_PINK_HOT);
//							}
//						};
//						
//					} else {
//						return new Response("Stripped", "Shadow unceremoniously strips you in front of everyone in the hall...", VengarCaptiveDialogue.FINAL_COMBAT_DEFEAT_STRIPPED) {
//							@Override
//							public void effects() {
//								Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_STORAGE);
//								applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Shadow.class), PresetColour.CLOTHING_PINK_HOT);
//							}
//						};
//					}
//				}
			}
			return null;
		}
	};
	
	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_DRAGGED_OFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("被剥光",
						"默克上来把你扒了个精光……",
						BODYGUARDS_COMBAT_DEFEAT_STRIPPED) {
					@Override
					public void effects() {
						applyCaptivity(Main.game.getPlayer(), Main.game.getNpc(Murk.class), PresetColour.CLOTHING_PINK_LIGHT);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_STRIPPED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("新家",
						"默克和文加把你带去了邻接的房间，送去了“新家”……",
						BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END) {
					@Override
					public void effects() {
						Main.game.getNpc(Murk.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("口交",
						"就如他们期待的那样，顺从地给文加口交起来。",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), null),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("饥渴地口交",
						"顺从地给文加口交，展现出你多么地心甘情愿。",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), SexPace.SUB_EAGER),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("抗拒口交",
						"竭尽全力避免给文加口交。",
						false,
						false,
						getStocksManager(Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)), SexPace.SUB_RESISTING),
						null,
						null,
						BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_STRIPPED_END_SEX_RESIST")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Vengar.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getDescription(){
			return "文加爽过之后，从你身边推开，准备离开……";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "BODYGUARDS_COMBAT_DEFEAT_AFTER_MILKING_ROOM_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("默克", "默克再次出现，告诉了你接下来还有什么在等着你……", RatWarrensCaptiveDialogue.CAPTIVE_DAY_0) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0"));
						banishGuards(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensEntranceGuardsFight));
						Main.game.getNpc(Vengar.class).returnToHome();
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode VENGAR_COMBAT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_COMBAT_VICTORY", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("自卫", "保卫自己免受鼠女的攻击！", VENGARS_HALL_APPROACH_THREATEN_KNOCK_OUT_CHAOS);
			}
			return null;
		}
	};

	public static final DialogueNode VENGAR_COMBAT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGAR_COMBAT_DEFEAT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BODYGUARDS_COMBAT_DEFEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode VENGARS_BEDROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"顺从",
						"告诉文加他可以对你为所欲为……",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						VENGARS_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_SEX_SUBMISSIVE", getGuards(true)));
				
			} else if(index==2) {
				return new ResponseSex(
						"支配",
						"告诉文加他应该向你屈服……",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								null,
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						VENGARS_BEDROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_SEX_DOMINANT", getGuards(true)));	
			}
			return null;
		}
	};

	public static final DialogueNode VENGARS_BEDROOM_AFTER_SEX = new DialogueNode("结束", "文加很满足，结束了性爱……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开之前，告诉文加你不久之后还会再来找他的。", VENGARS_HALL_CORRIDOR_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "VENGARS_BEDROOM_AFTER_SEX_LEAVE"));
						Main.game.getPlayer().setNearestLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_CORRIDOR_RIGHT, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LEXA_VISIT = new DialogueNode("赌场", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.getNpc(Axel.class).getSexualOrientation().isAttractedToFeminine() && Main.game.getPlayer().isFeminine()) {
					return new Response("三人行", "[axel.Name]只会被男性化角色吸引，所以不愿意跟你做爱……", null);
				}
				return new ResponseSex(
						"三人行",
						"跟文加一起支配[axel.name]……",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(
										Main.game.getPlayer(),
										Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(
										Main.game.getNpc(Axel.class)),
								null,
								null){
							@Override
							public boolean isPlayerAbleToStopSex() {
								return false;
							}
						},
						LEXA_VISIT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_THREESOME", getGuards(true)));
				
			} else if(index==2) {
				return new ResponseSex(
						"旁观",
						"旁观文加跟[axel.name]做爱……",
						true,
						true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Vengar.class)),
								Util.newArrayListOfValues(Main.game.getNpc(Axel.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null) {
								@Override
								public boolean isPlayerAbleToStopSex() {
									return false;
								}
							},
						LEXA_VISIT_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_WATCH", getGuards(true)));
				
			} else if(index==3) {
				return new Response("离开", "决定不跟文加和[axel.name]做什么淫行，让他们两个自己搞去吧……", PlaceType.SUBMISSION_GAMBLING_DEN.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_LEAVE", getGuards(true)));
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_GAMBLING_DEN, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
						Main.game.getNpc(Vengar.class).calculateGenericSexEffects(true, true, Main.game.getNpc(Axel.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode LEXA_VISIT_AFTER_SEX = new DialogueNode("结束", "文加很满足，结束了性爱……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "LEXA_VISIT_AFTER_SEX", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "[axel.name]回到了赌场的主入口，留下了你一个。", PlaceType.GAMBLING_DEN_ENTRANCE.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
						Main.game.getNpc(Vengar.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_VENGARS_HALL, false);
						Main.game.getNpc(Axel.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, false);
					}
				};
			}
			return null;
		}
	};
	
	// After captivity enforcer raid:
	
	public static final DialogueNode POST_CAPTIVITY_SWORD_RAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyRatWarrensRaid();
			Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_ENFORCERS));
			Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
		}
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_CAPTIVITY_SWORD_RAID", getGuards(true));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	
//	public static final DialogueNode POST_DEFEAT_SWORD_RAID = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 2*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_DEFEAT_SWORD_RAID", getGuards(true));
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(index==1) {
//				return new Response("Wait", "Wait with Silence for Shadow to return.", POST_DEFEAT_SWORD_RAID_FINISH) {
//					@Override
//					public void effects() {
//						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer(), false);
//						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_VENGAR, Quest.VENGAR_TWO_ENFORCERS));
//					}
//				};
//			}
//			return null;
//		}
//	};
//	
//	public static final DialogueNode POST_DEFEAT_SWORD_RAID_FINISH = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 10*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "POST_CAPTIVITY_SWORD_RAID_FINISH", getGuards(true));
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if(index==1) {
//				return new Response("Follow", "You don't have much choice but to follow Claire out of the Rat Warrens...", SWORD_RAID_EXIT) {
//					@Override
//					public void effects() {
//						Main.game.getTextEndStringBuilder().append(applyConflictQuestEnd());
//					}
//				};
//			}
//			return null;
//		}
//	};
}
