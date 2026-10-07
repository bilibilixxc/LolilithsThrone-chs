package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Helena;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.character.npc.misc.BasicSlave;
import com.lilithsthrone.game.character.npc.misc.GenericMaleNPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryEncyclopediaUnlock;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.dominion.SMScarlettShopOral;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.83
 * @version 0.3.9.1
 * @author Innoxia
 */
public class ScarlettsShop {
	
	private static void spawnDeliveryNpcs() {
		Main.game.getNpc(Natalya.class).setLocation(Main.game.getPlayer(), false);
		
		String[] names = new String[] {"顺从的半人马", "忠诚的半人马"};
		for(int i=0; i<2; i++) {
			NPC npc = new GenericSexualPartner(Gender.M_P_MALE, WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getCell(PlaceType.GENERIC_HOLDING_CELL).getLocation(), false);
			npc.setBody(Gender.M_P_MALE, Subspecies.CENTAUR, Main.game.getCharacterUtils().getRaceStageFromPreferences(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(Subspecies.CENTAUR), Gender.M_P_MALE, Subspecies.CENTAUR),false);
			
			npc.unequipAllClothing(npc, true, true);
			npc.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_bdsm_metal_collar"), PresetColour.CLOTHING_GOLD, false), true, npc);
			
			npc.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			npc.setBodySize(BodySize.FOUR_HUGE.getMedianValue());
			npc.setHeight(230+Util.random.nextInt(11));
			
			npc.setTesticleSize(TesticleSize.FIVE_MASSIVE);
			npc.setPenisCumStorage(750);
			npc.setPenisCumExpulsion(80);
			
			npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			npc.clearFetishes();
			npc.clearFetishDesires();
			
			npc.addFetish(Fetish.FETISH_ANAL_GIVING);
			
			npc.setFetishDesire(Fetish.FETISH_CUM_STUD, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE);
			npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
			
			npc.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.ZERO_HATE);
			npc.setFetishDesire(Fetish.FETISH_IMPREGNATION, FetishDesire.ZERO_HATE);
			
			npc.setAssVirgin(false);
			npc.setPenisVirgin(false);
			npc.setFaceVirgin(false);
			
			npc.setGenericName(names[i]);
			
			npc.setLocation(Main.game.getPlayer(), false);
			try {
				Main.game.addNPC(npc, false);
				Main.game.setActiveNPC(npc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static List<GameCharacter> getDeliveryNpcs() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->npc.isUnique());
		return list;
	}
	
	private static int getScarlettPrice() {
		return (int) Units.roundTo(Main.game.getNpc(Scarlett.class).getValueAsSlave(false) * 2, 1000);
	}
	
	private static SexManagerInterface getScarlettSleepoverSexManager(AbstractSexPosition position, SexSlot scarlettSlot, SexSlot playerSlot, SexType scarlettPreference, Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				return SexControl.ONGOING_ONLY; // So Scarlett doesn't start anything else.
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return true;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return false;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return scarlettPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getPlayer(), new HashMap<>());
					map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
					map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Scarlett.class), Util.newHashSetOfValues(LubricationType.SALIVA));
					return map;
				}
				return super.getStartingWetAreas();
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettSleepoverSex)) { // If this is the morning oral scene, Scarlett stops after cumming.
					return Main.sex.isSatisfiedFromOrgasms(partner, true);
				}
				return Main.sex.isSatisfiedFromOrgasms(partner, true) && (Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true) || Main.sex.getNumberOfOrgasms(partner)>=3);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {// pull out if Scarlett receiving blowjob
						return OrgasmBehaviour.PULL_OUT;
					}
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
				if(!character.isPlayer()) {
					if(scarlettPreference.getPerformingSexArea()==SexAreaPenetration.PENIS && scarlettPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {// pull out onto face if Scarlett receiving blowjob
						return OrgasmCumTarget.FACE;
					}
				}
				return null;
			}
		};
	}
	
	private static SexManagerInterface getScarlettCafeSexManager(AbstractSexPosition position, SexSlot scarlettSlot, SexSlot playerSlot, SexType scarlettPreference, Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		return new SexManagerDefault(
				false,
				position,
				Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Scarlett.class), scarlettSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
			@Override
			public boolean isPublicSex() {
				return false;
			}
			@Override
			public SexControl getSexControl(GameCharacter character) {
				return SexControl.ONGOING_ONLY; // So Scarlett doesn't start anything else.
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
				return clothingToEquip.isCondom();
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character){
				return false;
			}
			@Override
			public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
				return false;
			}
			@Override
			public boolean isPositionChangingAllowed(GameCharacter character) {
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return exposeAtStartOfSexMap;
			}
			@Override
			public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
				return new ArrayList<>();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return scarlettPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return character.getMainSexPreference(targetedCharacter);
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				return Main.sex.isSatisfiedFromOrgasms(partner, true);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
		};
	}

	private static GameCharacter getSlaveForCustomisation() {
		if(Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters().isEmpty()) {
			return null;
		}
		return Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters().get(0);
	}
	
	private static int getSlaveValue(boolean asSlime) {
		if(getSlaveForCustomisation()==null) {
			return 0;
		}
		
		int value = 25_000;
		for(Entry<AbstractRace, Integer> entry : getSlaveForCustomisation().getBody().getRaceWeightMap().entrySet()) { // Add value for non-human parts:
			if(entry.getKey()!=Race.HUMAN) {
				value += Math.min(5_000, 1_000*entry.getValue());
			}
		}
		
		if(asSlime) {
			value += 5_000;
		}
		return value;
	}
	
	public static boolean isSlaveCustomisationMenu() {
		return Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP
				|| Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS;
	}
	
	private static void generateStartingSlave(Gender gender) {
		NPC slave = new BasicSlave(gender, false);
		
		try {
			Main.game.addNPC(slave, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.getNpc(Helena.class).addSlave(slave);
		
		BodyChanging.setTarget(slave);
		
		SuccubisSecrets.initCoveringsMap(slave);
	}
	
	private static boolean isMule() {
		return Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) || Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA) || Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD);
	}
	
	public static final DialogueNode SCARLETTS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.isExtendedWorkTime()) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR_CLOSED");
			}
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_EXTERIOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.isExtendedWorkTime()) {
					return new Response("进入", "斯嘉丽商店目前关门，早上六点营业。你得在六点之后过来。", null);
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_HELENA) {
					return new Response("进入", "你需要找到海伦娜，才能再次进入斯嘉丽的商店。", null);
					
				} else {
					return new Response("进入", "进入商店。", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETTS_SHOP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP_INTRO");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETTS_SHOP");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
					return new Response("问及亚瑟", "询问斯嘉丽有没有一个叫亚瑟的奴隶卖。", SCARLETT_IS_A_BITCH);

				} else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("离开", "退出商店。", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_BITCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("同意", "同意帮助斯嘉丽。", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_HELENA));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SCARLETT_IS_A_SUPER_BITCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "SCARLETT_IS_A_SUPER_BITCH");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_EXTERIOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_HELENA_RETURNS");
				
			} else if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_ROMANCE_FAILED");
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTING_CLOSED"));
				}
				
			} else if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR)
					&& Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_8_FINISH)) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaGoneHome)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_GONE_HOME"));
					
				} else if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_PAINTED_CLOSED"));
				}
				
			} else {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
					if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_OPEN"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_FINISHED_CLOSED"));
					}
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR"));
				}
				
				if(Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_OPEN"));
					
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_EXTERIOR_CLOSED"));
				}
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_HELENA)) {
				return null;
			}
			
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("进入", "进入商店。", HELENAS_SHOP) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getNpc(Scarlett.class).resetInventory(true);
							AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", PresetColour.CLOTHING_BLACK_STEEL, false);
							collar.setSealed(true);
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(collar, true, Main.game.getNpc(Helena.class));
							Main.game.getNpc(Scarlett.class).equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK, false), true, Main.game.getNpc(Helena.class));
						}
					};
					
				} else if(!Main.game.getNonCompanionCharactersPresent().contains(Main.game.getNpc(Helena.class))) {
					return new Response("进入",
							"海伦娜的商店现在还没开门，早上九点营业。你得之后再过来。",
							null);
				}
				return new Response("进入",
						"进入商店。",
						Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)
							?ROMANCE_SHOP_CORE
							:HELENAS_SHOP);

			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP = new DialogueNode("", "", true) {

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_INTRODUCTION");
					
			} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_OFFER_SCARLETT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP", Main.game.getNpc(Helena.class).getSlavesOwnedAsCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0 && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_F_SCARLETTS_FATE)) {
				return new Response("离开", "跟海伦娜道别，离开她的商店。", HELENAS_SHOP_EXTERIOR);
				
			} else if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("提议购买", "提议从海伦娜那里买下斯嘉丽。", HELENAS_SHOP_SCARLETT_FOR_SALE) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY));
							if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.punishedByHelena)) {
								Main.game.getDialogueFlags().scarlettPrice = 10000;
							}
						}
					};
					
				} else if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_G_SLAVERY) {
					if(!Main.game.getPlayer().isHasSlaverLicense()) {
						return new Response("购买斯嘉丽(" + UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "span")+")",
								"你需要先从奴隶管理局获得贩奴许可才能购买斯嘉丽！", null);
						
					} else if(Main.game.getPlayer().getMoney() < Main.game.getDialogueFlags().scarlettPrice) {
						return new Response("购买斯嘉丽(" +UtilText.formatAsMoneyUncoloured(Main.game.getDialogueFlags().scarlettPrice, "span")+")", "你的钱不够购买斯嘉丽。", null);
						
					} else {
						return new Response("购买斯嘉丽("+UtilText.formatAsMoney(Main.game.getDialogueFlags().scarlettPrice, "span")+")",
								"以"+Main.game.getDialogueFlags().scarlettPrice+"火币的价格购买斯嘉丽。", HELENAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getDialogueFlags().scarlettPrice));
								
								AbstractClothing ballgag = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.MOUTH);
								if (ballgag != null) {
									ballgag.setSealed(false);
									Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(ballgag, true, Main.game.getNpc(Helena.class));
								}
								
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getNpc(Helena.class), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
								Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
								Main.game.getPlayer().addSlave(Main.game.getNpc(Scarlett.class));
							}
						};
					}
					
				} else {
					return new Response("奴隶管理", "进入奴隶管理界面。", HELENAS_SHOP) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(HELENAS_SHOP, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_G_SLAVERY)) {
				if(index==2) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_HELENA)) {
						return new Response("生意",
								"询问海伦娜问什么还会选择留在这里自己经营生意。"
										+ "<br/>[style.italicsQuestRomance(这将会开启海伦娜的浪漫任务！)]",
								ROMANCE_BUSINESS) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.QUEST_RELATIONSHIP;
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_FOR_SALE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_FOR_SALE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP.getResponse(0, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getDialogueFlags().scarlettPrice), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("让她冷静", "安抚斯嘉丽，让她冷静下来。", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_GENTLE"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if (index == 2) {
				return new Response("大声呵斥", "呵斥斯嘉丽，让她清楚她已经是你的财产了。", HELENAS_SHOP_SCARLETT_PURCHASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SHOUT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(5));
					}
				};
				
			} else if (index == 3) {
				return new Response("打她耳光", "打斯嘉丽一耳光，让她清楚她已经是你的财产了。", HELENAS_SHOP_SCARLETT_PURCHASED,
						Util.newArrayListOfValues(Fetish.FETISH_SADIST),
						CorruptionLevel.FOUR_LUSTFUL,
						null, null, null) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED_SLAP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE));
						((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -15));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementObedience(10));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_PURCHASED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_SCARLETT_PURCHASED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("留下她", "你决定将斯嘉丽留作你的奴隶。", HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER) {
					@Override
					public void effects() {
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};

			} else if (index == 2) {
				return new Response("释放她", "你决定赐予斯嘉丽自由。", HELENAS_SHOP_BUYING_SCARLETT_FREE_HER) {
					@Override
					public void effects() {
						
						AbstractClothing collar = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.NECK);
						if(collar!=null) {
							collar.setSealed(false);
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(collar, true, Main.game.getNpc(Helena.class));
						}
						
						((Scarlett) Main.game.getNpc(Scarlett.class)).equipClothing(Util.newArrayListOfValues(EquipClothingSetting.REPLACE_CLOTHING, EquipClothingSetting.REMOVE_SEALS, EquipClothingSetting.ADD_ACCESSORIES));
						
						Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
						Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).setAffection(Main.game.getPlayer(), 20));
						Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_KEEP_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "退出商店。", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP_BUYING_SCARLETT_FREE_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "退出商店。", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Helena romance quest:
	
	public static final DialogueNode ROMANCE_SHOP_CORE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				// If Scarlett has a saved inventory, then give items to player:
				CharacterInventory scarlettSavedInventory = Main.game.getSavedInventories().get(Main.game.getNpc(Scarlett.class).getId());
				if(scarlettSavedInventory!=null) {
					for(AbstractWeapon weapon : scarlettSavedInventory.getMainWeaponArray()) {
						if(weapon!=null) {
							Main.game.getPlayer().addWeapon(weapon, 1, false, true);
						}
					}
					for(AbstractWeapon weapon : scarlettSavedInventory.getOffhandWeaponArray()) {
						if(weapon!=null) {
							Main.game.getPlayer().addWeapon(weapon, 1, false, true);
						}
					}
					for(AbstractClothing clothing : scarlettSavedInventory.getClothingCurrentlyEquipped()) {
						Main.game.getPlayer().addClothing(clothing, 1, false, true);
					}
					for(Entry<AbstractWeapon, Integer> entry : scarlettSavedInventory.getAllWeaponsInInventory().entrySet()) {
						Main.game.getPlayer().addWeapon(entry.getKey(), entry.getValue(), false, true);
					}
					for(Entry<AbstractClothing, Integer> entry : scarlettSavedInventory.getAllClothingInInventory().entrySet()) {
						Main.game.getPlayer().addClothing(entry.getKey(), entry.getValue(), false, true);
					}
					for(Entry<AbstractItem, Integer> entry : scarlettSavedInventory.getAllItemsInInventory().entrySet()) {
						Main.game.getPlayer().addItem(entry.getKey(), entry.getValue(), false, true);
					}
					Main.game.getPlayer().incrementMoney(scarlettSavedInventory.getMoney());
				}
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/scarlettsShop", "HELENAS_SHOP")
						+ UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_1");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2"));
				if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
						|| Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_PAINT"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_2_NO_PAINT"));
				}
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					|| Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_EXTERIOR");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_3_PAINT_SIGN");
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT_OWNED_PRESENT"); // Helena demands you sell Scarlett to her
						
					} else {
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT_OWNED_NOT_PRESENT"); // Helena demands you fetch Scarlett
					}
					
				} else {
					if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_SCARLETT"); // Helena tells you to come back tomorrow/Monday
						
					} else {
						if(Main.game.getNpc(Scarlett.class).isSlave()) {
							return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_NO_SCARLETT_SLAVE"); // Helena tells you to come back once Scarlett has been returned
							
						} else {
							return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_4_NO_SCARLETT"); // Helena tells you to come back once Scarlett is here
						}
					}
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5"));
				CharacterInventory scarlettSavedInventory = Main.game.getSavedInventories().get(Main.game.getNpc(Scarlett.class).getId());
				if(scarlettSavedInventory!=null) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_ITEMS_RESTORED"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_NO_ITEMS"));
				}
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_5_END"));
				
				return sb.toString();
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_6_ADVERTISING) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SHOP_CORE_6"); // Helena asks you if you've put up the posters yet
			}
			
			
			// Romance quest completed:
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE"));
			
			if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY && Main.game.getHourOfDay()>=17) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_DATE"));
				
			} else if(getSlaveForCustomisation()!=null) {
				int daysToGo = 7-(Main.game.getDayNumber()-Main.game.getDialogueFlags().helenaSlaveOrderDay);
				if(daysToGo>0) {
					UtilText.addSpecialParsingString(Util.intToString(daysToGo), true);
					LocalDateTime timeReady = Main.game.getDateNow().plusDays(daysToGo);
					UtilText.addSpecialParsingString(timeReady.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE), false);
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_CUSTOM_SLAVE_PROGRESS"));
				} else {
					sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END_CUSTOM_SLAVE_READY"));
				}
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "ROMANCE_SHOP_CORE_END"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_HELENA)) {
				if(index==0) {
					return new Response("离开", "跟海伦娜道别之后退出商店。", HELENAS_SHOP_EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getNpc(Helena.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getNpc(Scarlett.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							if(((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaSlutSeen, true);
							}
						}
					};
					
				} else if(index==1) {
					if(getSlaveForCustomisation()!=null) {
						int daysToGo = 7-(Main.game.getDayNumber()-Main.game.getDialogueFlags().helenaSlaveOrderDay);
						if(daysToGo>0) {
							return new Response("收下奴隶",
									"你自定义的奴隶还在训练和转化过程中，需要"+Util.intToString(daysToGo)+"天才能收到。",
									null);
							
						} else {
							return new Response("收下奴隶", "告诉海伦娜你准备好收下你自定义的奴隶了。", HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY) {
								@Override
								public void effects() {
									NPC slave = (NPC) getSlaveForCustomisation();
									slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, true);
								}
							};
						}
						
					} else {
						return new Response("自定义奴隶", "询问海伦娜自定义奴隶的服务。", HELENAS_SHOP_CUSTOM_SLAVE_START);
					}
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopTalkedTo)) {
						return new Response("对话", "你今天已经跟海伦娜谈过话了……", null);
					}
					return new Response("对话", "询问海伦娜生意如何。", HELENAS_SHOP_TALK) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopTalkedTo, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 2));
						}
					};
					
				} else if(index==3 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopFucked)) {
						return new Response("去里屋", "你今天已经去里屋跟海伦娜做过了，她没时间再来一次……", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
						return new Response("里屋", "你不能使用嘴巴或生殖器，所以无法和海伦娜做爱……", null);
					}
					return new Response("里屋", "接受海伦娜的建议，跟她一起去里屋。<br/>[style.italicsSex(这会让你跟她做爱……)]", HELENAS_SHOP_BACK_ROOM) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopFucked, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				}
				//TODO
//				else if(index==3 && ((Helena) Main.game.getNpc(Helena.class)).isSlutty()) {
//					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettThreesome)) {
//						return new Response("Threesome", "Helena will be unwilling to have a threesome until after you've convinced her to do it in her bedroom.", null);
//					}
//					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopFucked)) {
//						return new Response("Threesome", "You've already had sex with Helena in the back room today, and she doesn't have time to do it again...", null);
//					}
//					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
//							&& (!Main.game.getPlayer().hasPenis() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true))
//							&& (!Main.game.getPlayer().hasVagina() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))) {
//						return new Response("Threesome", "As you are unable to gain access to your mouth or genitals, you can't have sex with Helena...", null);
//					}
//					return new Response("Threesome",
//							"Ask Helena if she'd like to have a threesome with you and Scarlett in the back room...",
//							HELENAS_SHOP_BACK_ROOM_THREESOME) {
//						@Override
//						public boolean isSexHighlight() {
//							return true;
//						}
//						@Override
//						public void effects() {
//							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopFucked, true);
//							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
//						}
//					};
//					
//				}
				else if(index==5) {
					if(Main.game.getDayOfWeek()!=DayOfWeek.FRIDAY || Main.game.getHourOfDay()<17) {
						return new Response("约会", "你只能在星期五[units.time(17)]之后邀请海伦娜外出约会。", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("约会", "由于涉及晚餐，你[style.colourBad(需要能够使用嘴巴)]才能邀请海伦娜外出约会！", null);
					}
					return new Response("约会", "叫海伦娜出去约会。", HelenaHotel.DATE_START);
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafe)) {
						return new Response("斯嘉丽", "由于她已经跟你吃过午饭了，海伦娜不允许斯嘉丽再浪费工作时间跟你闲聊……", null);
					} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCounterOral)) {
						return new Response("斯嘉丽", "由于你今天已经跟斯嘉丽度过一段时间了，海伦娜不允许斯嘉丽再浪费工作时间跟你闲聊……", null);
					}
					return new Response("斯嘉丽", "询问海伦娜你能不能跟斯嘉丽待一会儿。", HELENAS_SHOP_SCARLETT);
				}
				return null;
			}
			
			if (index==0
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN
					&& Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)!=Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				return new Response("离开", "与海伦娜告别，离开她的商店。", HELENAS_SHOP_EXTERIOR);
			}
			
			if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)
					&& !Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
				if(index == 1) {
					return new Response("奴隶管理", "进入奴隶管理界面。", ROMANCE_SHOP_CORE) {
						@Override
						public boolean isTradeHighlight() {
							return true;
						}
						@Override
						public DialogueNode getNextDialogue() {
							CompanionManagement.initManagement(null, 0, null);
							return OccupantManagementDialogue.getSlaveryManagementDialogue(ROMANCE_SHOP_CORE, Main.game.getNpc(Helena.class));
						}
					};
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_1_OFFER_HELP) {
				if(index==2) {
					return new Response("提供帮助", "告诉海伦娜，你愿意帮她的忙推进这场生意。", ROMANCE_OFFER_HELP);
				}
			}
			
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA)==Quest.ROMANCE_HELENA_2_PURCHASE_PAINT) {
				if(index==1) {
					if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN_PREMIUM)) {
						return new Response("油漆", "给海伦娜看看那罐“紫星”金色油漆，正是她想要的那款。", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_PREMIUM"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN_PREMIUM);
							}
						};
						
					} else if(Main.game.getPlayer().hasItemType(ItemType.PAINT_CAN)) {
						return new Response("油漆", "给海伦娜看看那罐“铜星”金色油漆，希望她没注意到这跟她要求的牌子并不一样……", ROMANCE_PAINT) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaCheapPaint, true);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT_STANDARD"));
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR));
								Main.game.getPlayer().removeItemByType(ItemType.PAINT_CAN);
							}
						};
						
					} else {
						return new Response("油漆", "你还没有买来海伦娜要的金色油漆……", null);
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_A_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("刮除油漆", "听从海伦娜的安排，把门面上已经要剥落的旧油漆刮下来。", ROMANCE_PAINTING_A) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR));
						}
					};
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("粉刷门面", "听从海伦娜的安排，用全新的一层白漆粉刷门面。", ROMANCE_PAINTING_B);
				}
			}
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR) {
				if(index==1) {
					return new Response("拿来油漆", "听从海伦娜的指导，把金色油漆拿到外面。", ROMANCE_PAINTING_C);
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN) {
				if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
					if(!Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
						if(index==1) {
							return new Response("离开", "按海伦娜说的做，离开她的商店。", SlaverAlleyDialogue.ALLEYWAY) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								}
							};
						}
						
					} else {
						if(index==1) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 1); // Sell
							
						} else if(index==2) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 2); // Give
							
						} else if(index==5) {
							return ROMANCE_PAINTING_C_FINISHED_LETTER.getResponse(0, 5); // Refuse
						}
					}
					
				} else {
					if(index==1) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) { // Returning after recovering Scarlett from nest or antiques shop:
							return new Response("同意",
									Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
										?"告诉海伦娜你周一再来。"
										:"告诉海伦娜你明天再来。",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
								}
							};
							
						} else { // Returning before Scarlett is recovered:
							return new Response("离开", "按海伦娜说的做，离开她的商店。", SlaverAlleyDialogue.ALLEYWAY) {
								@Override
								public void effects() {
									Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
								}
							};
						}
					}
				}
			}

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_HELENA) == Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER) {
				if(index==1) {
					return new Response("跟随",
							"按照海伦娜的要求，跟她进到里屋。",
							ROMANCE_5_POTIONS);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("表达感谢", "或许她正等着你感谢她呢？", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_THANK_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
				
			} else if(index==2) {
				return new Response("催促她", "让她继续讲。", ROMANCE_BUSINESS_FOLLOWUP) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_PROMPT_HER"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_HELENA));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_BUSINESS_FOLLOWUP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_BUSINESS_FOLLOWUP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(0, index);
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=10_000) {
					return new Response("支付("+UtilText.formatAsMoney(10_000, "span")+")", "付给海伦娜要求的一万火币。", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-10_000));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getNpc(Helena.class).incrementMoney(10_000);
						}
					};
					
				} else if(Main.game.getPlayer().getMoney()>0) {
					return new Response("支付("+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")+")", "告诉海伦娜你没有一万火币那么多钱，所以没法付给她钱。", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(Util.intToString(Main.game.getPlayer().getMoney()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAY_REDUCED"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-Main.game.getPlayer().getMoney()));
							Main.game.getNpc(Helena.class).incrementMoney(Main.game.getPlayer().getMoney());
						}
					};
					
				} else {
					return new Response("无法支付", "告诉海伦娜你的火币完全不够用，所以没法付给她钱。", ROMANCE_OFFER_HELP_PAYMENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_CANNOT_PAY"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
						}
					};
				}
				
			} else if(index==2) {
				return new Response("拒绝", "告诉海伦娜她太无理取闹了；应该<i>她</i>付给<i>你</i>钱才是！", ROMANCE_OFFER_HELP_PAYMENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_REFUSE_TO_PAY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_PAYMENT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_PAYMENT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("补给品", "听从海伦娜的命令，取回补给品。", ROMANCE_OFFER_HELP_FETCH_SUPPLIES);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_FETCH_SUPPLIES = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_FETCH_SUPPLIES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "等海伦娜找到她要找的东西。", ROMANCE_OFFER_HELP_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_2_PURCHASE_PAINT));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_OFFER_HELP_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN_PREMIUM.getValue()), true);
			UtilText.addSpecialParsingString(Util.intToString(ItemType.PAINT_CAN.getValue()), false);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_OFFER_HELP_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_A = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
							?"你必须等到周一才能继续工作……"
							:"你必须等到明天才能继续工作……",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_B = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			spawnDeliveryNpcs();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 60*60;
			} else {
				return 4*60*60;
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_2", getDeliveryNpcs());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						isMule()
							?"向娜塔莉亚问好"
							:"介绍",
						isMule()
							?"向主人问好，告诉他你是来取一批家具的，肯定就在马车后面。"
							:"向这个[natalya.race]说自己就是[natalya.she]要找的人，然后拿走那批家具，肯定就在马车后面。",
						ROMANCE_PAINTING_FURNITURE_DELIVERY) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setPlayerKnowsName(true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "完全不帮忙，站在旁边等半人马完成工作。", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_NO_HELP"));
					}
				};
				
			} else if(index==2) {
				return new Response("提供帮助", "询问娜塔莉亚有什么忙你可以帮上的。", ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT_HELP"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_NEXT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMule()) {
				if(index==1) {
					return new Response("跟随",
							"跟着主人娜塔莉亚进到小巷子里，看看她想让你做什么。",
							ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
						@Override
						public void effects() {
							Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("留在原地", "在马车旁等候，等娜塔莉亚回来。", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
						@Override
						public int getSecondsPassed() {
							return 15*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_WAIT"));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isAnalContentEnabled()) {
						return new Response("跟随",
								"你预感到跟着娜塔莉亚去小巷子里，可能会面临你不想见到的事情……"
										+ "<br/>[style.italicsMinorBad(娜塔莉亚的场景包含肛门内容，若关闭“肛门内容”则相关文本也会被移除。)]",
								null);
					}
					return new Response("跟随",
							"跟着娜塔莉亚去小巷子里，看看她有什么打算。"
									+ "<br/>[style.italicsSex(你预感到可能会看到十分下流的场面……)]",
							ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW) {
						@Override
						public void effects() {
							Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.PENIS, null);
							((Natalya)Main.game.getNpc(Natalya.class)).insertDildo();
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : getDeliveryNpcs()) {
				Main.game.banishNPC((NPC) npc);
			}
			Main.game.getNpc(Natalya.class).returnToHome();
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_C_EXTERIOR_DECORATOR));
			if(!Main.game.getPlayer().isQuestFailed(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
					&& !Main.game.getPlayer().hasItemType(ItemType.NATALYA_BUSINESS_CARD)) {
				Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false));
			}
		}
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
				return new Response("上锁",
						"给商店上锁，准备离开。",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_END_LOCK_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"继续往奴隶巷走。",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转头从娜塔莉亚身旁离开，留娜塔莉亚自救。", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 15*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_LEAVE"));
						Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
				
			} else if(index==2) {
				return new Response("屈服",
						"听从这个强硬的[natalya.race]的命令，喊了她一句“主人”后，便跪在她面前，甘愿接受她为你安排的任何任务……",
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.playerSubmittedToNatalya, true);
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("开始撸管", "听从主人娜塔莉亚的命令，开始撸动她粗壮的马屌。",
						true,
						false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL))) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_ONLY;
								}
								return super.getSexControl(character);
							}
							@Override
							public boolean isAbleToSkipSexScene() {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing) {
								return false;
							}
							@Override
							public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
								return new ArrayList<>();
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return getForeplayPreference(character, targetedCharacter);
								}
								return character.getMainSexPreference(targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<SexAreaInterface>> getAreasBannedMap() {
								Map<GameCharacter, List<SexAreaInterface>> map = new HashMap<>();
								map.put(Main.game.getPlayer(), Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaOrifice.NIPPLE, SexAreaOrifice.BREAST));
								map.put(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(SexAreaOrifice.ANUS, SexAreaPenetration.FOOT));
								return map;
							}
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								return OrgasmBehaviour.PULL_OUT;
							}
							@Override
							public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
								if(!character.isPlayer()) {
									if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playerReceivedNatalyaFacial)) {
										return OrgasmCumTarget.FACE;
									} else {
										return OrgasmCumTarget.FLOOR;
									}
								}
								return super.getCharacterPullOutOrgasmCumTarget(character, target);
							}
						},
						null,
						null,
						ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_START_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), FingerPenis.COCK_MASTURBATING_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX = new DialogueNode("结束", "娜塔莉亚主人暂时和你做够了。", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "跟着主人娜塔莉亚，去看看那两只半人马把家具都拆出来了没有。", ROMANCE_PAINTING_FURNITURE_DELIVERY_END) {
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_FURNITURE_DELIVERY_FOLLOW_SUBMIT_POST_SEX_FOLLOW"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("粉刷标志", "在海伦娜近距离的监督下，用买来的金色油漆给入口处“海伦娜精品店”几个字粉刷。", ROMANCE_PAINTING_C_PAINT_SIGN);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_PAINT_SIGN = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getPlayer().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)) {
				return 10*60;
			}
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_PAINT_SIGN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "跟随海伦娜回到商店。", ROMANCE_PAINTING_C_FINISHED) {
					@Override
					public void effects() {
						// If Scarlett has been sold but not 'banished' yet, 'banish' her now:
						if(Main.game.getNpc(Scarlett.class).isSlave() && !Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
							Main.game.getNpc(Scarlett.class).getOwner().removeSlave(Main.game.getNpc(Scarlett.class));
							Main.game.banishNPC(Main.game.getNpc(Scarlett.class));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("沏茶",
						"按海伦娜说的做，给你们两个沏一壶茶。",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_TEA"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝",
						"拒绝给海伦娜沏茶，反而直白地问她还有什么事情要让你做。",
						ROMANCE_PAINTING_C_FINISHED_SCARLETT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_NO_TEA"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_SCARLETT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("阅读", "阅读海伦娜递给你的信。", ROMANCE_PAINTING_C_FINISHED_LETTER) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_4_SCARLETTS_RETURN));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_PAINTING_C_FINISHED_LETTER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_PAINTING_C_FINISHED_LETTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Scarlett.class).isSlave() && Main.game.getNpc(Scarlett.class).getOwner().isPlayer()) {
				if(!Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
					if(index==1) {
						return new Response("同意", "同意去把斯嘉丽带来到这。", ROMANCE_SCARLETT_OWNED_FETCH);
					}
				} else {
					if(index==1) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
							return new Response("出售斯嘉丽("+UtilText.formatAsMoney(getScarlettPrice(), "span", PresetColour.GENERIC_GOOD)+")",
									"再把斯嘉丽以"+UtilText.formatAsMoney(getScarlettPrice())+"的价格卖给海伦娜。",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
									
									Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
									Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
									Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD"));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_REACTION"));
									if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || !Main.game.getNpc(Scarlett.class).isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NEEDING_TF"));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NO_TF_NEEDED"));
									}
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(getScarlettPrice()));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
									
									Main.game.addSavedInventory(Main.game.getNpc(Scarlett.class));
									int essences = Main.game.getNpc(Scarlett.class).getEssenceCount();
									Main.game.getNpc(Scarlett.class).setInventory(new CharacterInventory(false, 0));
									Main.game.getNpc(Scarlett.class).setEssenceCount(essences);
								}
							};
							
						} else {
							return new Response("出售斯嘉丽("+UtilText.formatAsMoneyUncoloured(getScarlettPrice(), "span")+")",
									"你需要先把斯嘉丽作为队友带到这里，才能把[scarlett.herHim]卖给海伦娜！",
									null);
						}
						
					} else if(index==2) {
						if(Main.game.getCharactersPresent().contains(Main.game.getNpc(Scarlett.class))) {
							return new Response("交出斯嘉丽",
									"将斯嘉丽归还给海伦娜，并且不接受她付给你的"+UtilText.formatAsMoney(getScarlettPrice())+"。",
									ROMANCE_SCARLETT_DELIVERED_EMPTY) {
								@Override
								public void effects() {
									UtilText.addSpecialParsingString(Util.intToString(getScarlettPrice()), true);
									
									Main.game.getPlayer().removeCompanion(Main.game.getNpc(Scarlett.class));
									Main.game.getPlayer().removeSlave(Main.game.getNpc(Scarlett.class));
									Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
									
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_GIVEN_AWAY"));
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_SOLD_REACTION"));
									if(Main.game.getNpc(Scarlett.class).getRace()!=Race.HARPY || !Main.game.getNpc(Scarlett.class).isFeminine()) {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NEEDING_TF"));
									} else {
										Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_NO_TF_NEEDED"));
									}
									Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_DELIVERED_END"));
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 15));
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_5_SCARLETT_TRAINER));
									
									Main.game.addSavedInventory(Main.game.getNpc(Scarlett.class));
									int essences = Main.game.getNpc(Scarlett.class).getEssenceCount();
									Main.game.getNpc(Scarlett.class).setInventory(new CharacterInventory(false, 0));
									Main.game.getNpc(Scarlett.class).setEssenceCount(essences);
								}
							};
							
						} else {
							return new Response("交出斯嘉丽", "你需要先把斯嘉丽带到这里，才能把[scarlett.herHim]交给海伦娜！", null);
						}
					}
				}
				
				if(index==5) {
					return new Response("拒绝",
								"拒绝把斯嘉丽卖给海伦娜。"
									+ "<br/>[style.italicsBad(这将会导致任务失败，没有你的协助，海伦娜只得关停店铺，回到哈比之巢！)]",
								ROMANCE_QUEST_FAILURE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setAffection(Main.game.getPlayer(), -100));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_FAILED));
							Main.game.getNpc(Helena.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
							if(!Main.game.getPlayer().hasCompanion(Main.game.getNpc(Scarlett.class))) {
								Main.game.getNpc(Scarlett.class).returnToHome();
								Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'><i>你命令[scarlett.name]回到[scarlett.her]的房间……</i></p>");
							}
						}
					};
				}
				return null;
			}
			
			if(index==1) {
				return new Response("继续",
						"继续往奴隶巷走。",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						if(Main.game.getNpc(Scarlett.class).getHomeWorldLocation()!=WorldType.HARPY_NEST) {
							Main.game.getNpc(GenericMaleNPC.class).addSlave(Main.game.getNpc(Scarlett.class));
							Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ANTIQUES);
						}
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			
			return null;
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_OWNED_FETCH = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_OWNED_FETCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"你既然保证会把斯嘉丽带回来，最好还是赶紧去吧……",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_QUEST_FAILURE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_SCARLETT_REFUSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"由于海伦娜已经放弃了这家店铺，你无事可做，只得离开……",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_SCARLETT_DELIVERED_EMPTY = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			Main.game.getNpc(Scarlett.class).setHomeLocation(WorldType.HELENAS_APARTMENT, PlaceType.HELENA_APARTMENT_SCARLETT_BEDROOM);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
			
			if(Main.game.getNpc(Helena.class).getAffection(Main.game.getNpc(Scarlett.class))<0) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), 0);
			}
			
			((Scarlett)Main.game.getNpc(Scarlett.class)).resetName();
			
			((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();
		}
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
				return new Response("离开",
						"现在海伦娜和斯嘉丽都离开了，你无事可做，只得离开……",
						SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_5_POTIONS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getSavedInventories().remove(Main.game.getNpc(Scarlett.class).getId()); // Removed Scarlett saved inventory, as it was restored to the player in the preceding scene
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("保持原样", "告诉海伦娜最好还是不要转化斯嘉丽。", ROMANCE_5_POTIONS_NEXT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS_SCARLETT_NO_TF"));
					}
				};
				
			} else if(index==2) {
				return new Response("转化她", "告诉海伦娜把斯嘉丽转化成个女性哈比是最适合她的。", ROMANCE_5_POTIONS_NEXT) {
					@Override
					public void effects() {
						((Scarlett)Main.game.getNpc(Scarlett.class)).applyFeminisation();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_5_POTIONS_SCARLETT_FEMALE"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_5_POTIONS_NEXT = new DialogueNode("", "", true, true) {
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
				return new Response("跟随", "跟随海伦娜进到商店里。", ROMANCE_ADVERTISING_POSTERS);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("称赞", "称赞海伦娜海报上她的美貌。", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_COMPLIMENT"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("质疑", "同意斯嘉丽说的，质疑这些海报是否真的能给生意打广告。", ROMANCE_ADVERTISING_POSTERS_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_QUESTION"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_ADVERTISING_POSTERS_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.ROLLED_UP_POSTERS), false));
			if(Main.getProperties().addItemDiscovered(ItemType.ROLLED_UP_POSTERS)) {
				Main.game.addEvent(new EventLogEntryEncyclopediaUnlock(ItemType.ROLLED_UP_POSTERS.getName(false), ItemType.ROLLED_UP_POSTERS.getRarity().getColour()), true);
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_6_ADVERTISING));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(100));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_ADVERTISING_POSTERS_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开",
						"从海伦娜那里接受命令后，你动身前往奴隶巷，准备张贴海报。",
						SlaverAlleyDialogue.GATEWAY_POSTER_PERMISSION) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE);
					}
				};
//				return new Response("Leave",
//						"Having received your orders from Helena, you're ready to set out into Slaver Alley and put up these posters.",
//						SlaverAlleyDialogue.ALLEYWAY) {
//					@Override
//					public void effects() {
//						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
//					}
//				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_RETURN_AFTER_POSTERS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0; // 0 as otherwise the time in the dialogue doesn't match up
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_RETURN_AFTER_POSTERS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随",
						"跟随海伦娜穿过商店，弄清楚她想让你和斯嘉丽在明天之前完成什么事情。",
						ROMANCE_7_FOLLOW) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_FOLLOW"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_7_GRAND_OPENING_PREPARATION));
						Main.game.getNpc(Helena.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaGoneHome, true);
						Main.game.getPlayer().removeAllCompanions(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_FOLLOW = new DialogueNode("", "", true, true) {
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
				return new Response("开始工作",
						"除了开始干活也别无选择了……",
						ROMANCE_7_START);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("耐心请求",
						"耐着性子请求斯嘉丽帮你一起打扫商店。",
						ROMANCE_7_WORKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_ASK_NICELY"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
					}
				};
				
			} else if(index==2) {
				return new Response("责骂她",
						"责骂斯嘉丽只会捣乱，命令她来帮忙。",
						ROMANCE_7_WORKING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_SCOLDING"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), -5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_WORKING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("布置装饰",
						"开始布置装饰。",
						ROMANCE_7_DECORATIONS) {
					@Override
					public void effects() {
						AbstractClothing dress = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.TORSO_UNDER);
						if(dress!=null) {
							Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(dress, true, Main.game.getNpc(Scarlett.class));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_7_DECORATIONS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(1 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_DECORATIONS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("拒绝",
						"拒绝斯嘉丽递来的威士忌。",
						ROMANCE_7_WORKING_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_NO_DRINK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAlcoholLevel(0.3f));
					}
				};
				
			} else if(index==2) {
				return new Response("喝",
						"喝下斯嘉丽给你的威士忌。",
						ROMANCE_7_WORKING_FINISHED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_DRINK"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAlcoholLevel(0.3f));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementAlcoholLevel(0.3f));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_WORKING_FINISHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
				AbstractClothing bra = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.CHEST);
				if(bra!=null) {
					Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(bra, true, Main.game.getNpc(Scarlett.class));
				}
				AbstractClothing underwear = Main.game.getNpc(Scarlett.class).getClothingInSlot(InventorySlot.GROIN);
				if(underwear!=null) {
					Main.game.getNpc(Scarlett.class).unequipClothingIntoVoid(underwear, true, Main.game.getNpc(Scarlett.class));
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("睡觉", "在沙发上睡着。", ROMANCE_7_MORNING);
				}
				
			} else {
				if(Main.game.getNpc(Scarlett.class).hasVagina()) {
					if(index==1) {
						return new Response("拒绝", "告诉斯嘉丽，你没兴趣侍奉她的小穴。", ROMANCE_7_SEX_DECLINED);
						
					} else if(index==2) {
						if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							return new ResponseSex(
									"骑乘",
									"按照斯嘉丽的要求，让她骑上你的肉棒。",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_RIDDEN_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							};
							
						} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex(
									"舔阴",
									"按照斯嘉丽的要求，给他舔阴。",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_CUNNILINGUS_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							};
							
						} else {
							return new ResponseSex(
									"指交",
									"按照斯嘉丽的要求，给她指交。",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_FINGERING_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERED_START, false, true));
								}
							};
						}
					}
					
				} else {
					 if(index==1) {
						return new Response("拒绝", "告诉斯嘉丽你没兴趣侍奉她的肉棒。", ROMANCE_7_SEX_DECLINED);
						
					} else if(index==2) {
						if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							return new ResponseSex(
									"献上屁股",
									"按照斯嘉丽的要求，将屁股献上供她使用。",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS, CoverableArea.VAGINA)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_ANAL_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> initialActions = new ArrayList<>();
									
									initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
									
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true));
										
									} else if(Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
										initialActions.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true));
										
									}
									
									return initialActions;
								}
							};
							
						} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new ResponseSex(
									"吮吸鸡巴",
									"按照斯嘉丽的要求，含住她的鸡巴。",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_ORAL_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
								}
							};
							
						} else {
							return new ResponseSex(
									"提供手淫",
									"按照斯嘉丽的要求，帮她手淫。",
									true,
									false,
									getScarlettSleepoverSexManager(SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									ROMANCE_7_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_WORKING_FINISHED_HANDJOB_START")) {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 10));
								}
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
								}
							};
						}
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_AFTER_SEX = new DialogueNode("结束", "斯嘉丽对你的表现很满意。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("睡觉", "在沙发上睡着。", ROMANCE_7_MORNING) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaScarlettSleepoverSex, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_SEX_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_SEX_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("睡觉", "在沙发上睡着。", ROMANCE_7_MORNING);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8 * 60) * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaScarlettSleepoverSex) && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("叫醒她", "晃醒斯嘉丽。", ROMANCE_7_MORNING_TIDY_UP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_WAKE_SCARLETT_NO_SEX"));
						}
					};
					
				} else if(index==2) {
					if(Main.game.getNpc(Scarlett.class).hasVagina()) {
						return new ResponseSex(
								"早安舔阴",
								"按照斯嘉丽昨晚告诉你的，舔她的下面来叫醒她。",
								true,
								false,
								getScarlettSleepoverSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								ROMANCE_7_MORNING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_CUNNILINGUS_START")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 20));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
							}
						};
						
					} else {
						return new ResponseSex(
								"早安口交",
								"按照斯嘉丽昨晚告诉你的，给她口交来叫醒她。",
								true,
								false,
								getScarlettSleepoverSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								ROMANCE_7_MORNING_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_BLOWJOB_START")) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 20));
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						};
					}
					
				}
				
			} else {
				if(index==1) {
					return new Response("叫醒她", "晃醒斯嘉丽。", ROMANCE_7_MORNING_TIDY_UP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_WAKE_SCARLETT"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING_AFTER_SEX = new DialogueNode("结束", "斯嘉丽达到了高潮，已经做够了。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("收拾干净", "趁海伦娜还没来，收拾干净里屋。", ROMANCE_7_MORNING_TIDY_UP);
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_7_MORNING_TIDY_UP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Scarlett.class).equipClothing();
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getHourOfDay()<9) {
				return (Main.game.getMinutesUntilTimeInMinutes(9 * 60) * 60) + 10*60;
			}
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MORNING_TIDY_UP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("准备饮料", "跟斯嘉丽一起给客人准备一些饮料。", ROMANCE_7_MAKING_DRINKS);
			}
			return null;
		}
	};
	
	public static final DialogueNode ROMANCE_7_MAKING_DRINKS = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDayOfWeek()==DayOfWeek.FRIDAY) {
				return (Main.game.getMinutesUntilTimeInMinutes(21 * 60) * 60) + 10*60;
			}
			return (Main.game.getMinutesUntilTimeInMinutes(17 * 60) * 60) + 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_7_MAKING_DRINKS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("闭上眼睛", "听从海伦娜的要求，闭上眼睛。", ROMANCE_END_KISSED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_CLOSE_EYES"));
					}
				};
				
			} else if(index==2) {
				return new Response("偷瞄", "假装闭上眼睛，但是偷偷瞄一眼海伦娜有什么打算。", ROMANCE_END_KISSED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED_PEEK"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ROMANCE_END_KISSED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_HELENA, Quest.SIDE_UTIL_COMPLETE));
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).incrementAffection(Main.game.getPlayer(), 25));
			
			if(Main.game.getNpc(Helena.class).getAffection(Main.game.getNpc(Scarlett.class))<50) {
				Main.game.getNpc(Helena.class).setAffection(Main.game.getNpc(Scarlett.class), 50);
			}
			Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_COMPLETED"));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenaRomance", "ROMANCE_END_KISSED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续进入奴隶巷……", SlaverAlleyDialogue.ALLEYWAY) {
					@Override
					public void effects() {
						Main.game.getNpc(Helena.class).setHomeLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
						Main.game.getPlayer().setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()-1));
					}
				};
			}
			return null;
		}
	};
	
	
	// Dialogue for when romance quest is completed:
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_START = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("反悔", "决定还是不从海伦娜那里定制奴隶了。", HELENAS_SHOP_CUSTOM_SLAVE_DECLINED);
				
			} else if(index==1) {
				return new Response("[style.colourFeminine(女性模板)]", "开始设计自定义奴隶，使用人类女性为出发点。", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public void effects() {
						generateStartingSlave(Gender.F_V_B_FEMALE);
					}
				};
				
			} else if(index==2) {
				return new Response("[style.colourMasculine(男性模板)]", "开始设计自定义奴隶，使用人类男性为出发点。", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public void effects() {
						generateStartingSlave(Gender.M_P_MALE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DECLINED = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DECLINED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY"));
			
			sb.append(
					"<div class='container-full-width' style='padding:8px;'>"
						+ "<div style='width:22%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "名字"
						+ "</div>"
						+ "<div style='width:22%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "姓氏"
						+ "</div>"
						+ "<div style='width:24%; float:left; font-weight:bold; margin:0 6% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(getSlaveForCustomisation(), "[npc.she]对你的称呼")
						+ "</div>"
						
						+ "<form style='float:left; width:22%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
							
						+ "<form style='float:left; width:22%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
						
						+ "<form style='float:left; width:24%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(getSlaveForCustomisation().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+getSlaveForCustomisation().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
					+ "</div>");
			
			sb.append("<div class='cosmetics-container' style='background:transparent;'>"
						+ CharacterModificationUtils.getAgeChoiceDiv()
						+ CharacterModificationUtils.getOrientationChoiceDiv()
						+ CharacterModificationUtils.getPersonalityChoiceDiv(true)
						+ CharacterModificationUtils.getObedienceChoiceDiv()
						+ CharacterModificationUtils.getAffectionChoiceDiv()
						+ CharacterModificationUtils.getFetishChoiceDiv()
					+"</div>");
			
			sb.append("<p id='hiddenFieldName' style='display:none;'></p>");
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("取消", "决定还是不从海伦娜那里定制奴隶了。", ROMANCE_SHOP_CORE) {
					@Override
					public void effects() {
						Main.game.banishNPC((NPC) getSlaveForCustomisation());
					}
				};
				
			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					return new Response("个性", "你正在定制奴隶的个性！", null);
				}
				return new Response("个性", "定制奴隶的个性。", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE) {
					return new Response("身体", "你正在定制奴隶身体的核心部分！", null);
				}
				return new Response("身体", "定制奴隶身体的核心部分。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==3) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES) {
					return new Response("眼睛", "你正在定制奴隶的眼睛了！", null);
				}
				return new Response("眼部", "定制奴隶的眼睛。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==4) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR) {
					return new Response("毛发", "你正在定制奴隶的毛发！", null);
				}
				return new Response("毛发", "定制奴隶的毛发。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==5) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD) {
					return new Response("头部", "你正在定制奴隶的头部和面部！", null);
				}
				return new Response("头部", "定制奴隶的头部和面部。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==6) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS) {
					return new Response("屁股", "你正在定制奴隶的臀部和屁股！", null);
				}
				return new Response("屁股", "定制奴隶的臀部和屁股。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==7) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS) {
					return new Response("胸部", "你正在定制奴隶的胸部！", null);
				}
				return new Response("胸部", "定制奴隶的胸部。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA) {
					return new Response("阴道", "你正在定制奴隶的阴道！", null);
				}
				return new Response("阴道", "定制奴隶的阴道。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==9) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS) {
					return new Response("阴茎", "你正在定制奴隶的阴茎！", null);
				}
				return new Response("阴茎", "定制奴隶的阴茎。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==10) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET) {
					return new Response("丝囊", "你正在定制奴隶的丝囊！", null);
				}
				if(!BodyChanging.getTarget().hasSpinneret()) {
					return new Response("丝囊",
							"你的奴隶没有丝囊！<br/><i>丝囊通过特定的尾巴类型或腿部类型获得。</i>",
							null);
				}
				return new Response("丝囊", "定制奴隶的丝囊。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==11) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH) {
					return new Response("胯乳", "你正在定制奴隶的胯乳！", null);
				}
				
				return new Response(
						BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳",
						UtilText.parse(BodyChanging.getTarget(), "定制奴隶的[npc.crotchBoobs]。"),
						HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH);
				
			} else if(index==12) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP) {
					return new Response("妆容", "你正在定制奴隶的妆容！", null);
				}
				return new Response("妆容", "定制奴隶的妆容。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==13) {
				if(Main.game.getCurrentDialogueNode()==HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS) {
					return new Response("穿孔", "你正在定制奴隶的穿孔！", null);
				}
				return new Response("穿孔", "定制奴隶的穿孔。", HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS) {
					@Override
					public int getSecondsPassed() {
						return 10;
					}
				};
				
			} else if(index==14) {
				return new Response("[style.colourMinorGood(确认订单)]",
						"告诉海伦娜你填好了订单表格，看看价格如何……",
						HELENAS_SHOP_CUSTOM_SLAVE_FINISH) {
					@Override
					public void effects() {
						BodyChanging.getTarget().setAllAreasKnownByCharacter(Main.game.getPlayer(), true);
					}
				};
				
			} 
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_CORE"));
			sb.append(BodyChanging.BODY_CHANGING_CORE.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_EYES"));
			sb.append(BodyChanging.BODY_CHANGING_EYES.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_HAIR"));
			sb.append(BodyChanging.BODY_CHANGING_HAIR.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_HEAD"));
			sb.append(BodyChanging.BODY_CHANGING_HEAD.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_ASS"));
			sb.append(BodyChanging.BODY_CHANGING_ASS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS"));
			sb.append(BodyChanging.BODY_CHANGING_BREASTS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_VAGINA"));
			sb.append(BodyChanging.BODY_CHANGING_VAGINA.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_PENIS"));
			sb.append(BodyChanging.BODY_CHANGING_PENIS.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_BREASTS_CROTCH"));
			sb.append(BodyChanging.BODY_CHANGING_BREASTS_CROTCH.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_SPINNERET"));
			sb.append(BodyChanging.BODY_CHANGING_SPINNERET.getHeaderContent());
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_MAKEUP"));

			sb.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "腮红", "腮红(也叫胭脂)被用来粉饰脸颊，以显得更加年轻或凸显颧骨。", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "口红", "口红被用来为嘴唇提供色彩、质地或保护。", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "眼线", "眼线用于眼廓周围，有助于修饰眼型或突出不同的特征。", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "眼影", "眼影用来让使用者的眼睛更加凸显迷人。", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "指甲油", "指甲油用于某人的手添加色彩或提供保护。", true, true)
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚趾甲油", "脚趾甲油用于给某人的脚添加色彩或提供保护。", true, true));
					
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS = new DialogueNode("定制奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 0;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_BODY_PIERCINGS"));
			sb.append(CharacterModificationUtils.getKatesDivPiercings(true));
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_FINISH = new DialogueNode("命令奴隶", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(false)), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_FINISH"));
			
			sb.append("<div class='container-full-width' style='text-align:center;'>"
							+ "<i>"
								+ "性经历越丰富，堕落就越高。"
							+ "</i>"
						+ "</div>"
						+CharacterModificationUtils.getSexualExperienceDiv());
			
			sb.append("<div class='container-full-width'>"
						+ UtilText.parse(BodyChanging.getTarget(), "<p style='text-align:center;'><b>[npc.NamePos]的外表</b></p>")
						+ BodyChanging.getTarget().getBodyDescription()
					+ "</div>");
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "返回并且做些改变……", HELENAS_SHOP_CUSTOM_SLAVE_PERSONALITY);
				
			} else if(index==1) {
				if(Main.game.getPlayer().getMoney()<getSlaveValue(false)) {
					return new Response("下单("+UtilText.formatAsMoneyUncoloured(getSlaveValue(false), "span")+")",
							"你付不起下单的钱，你只有"+Util.intToString(Main.game.getPlayer().getMoney())+"火币。",
							null);
				}
				return new Response("下单("+UtilText.formatAsMoney(getSlaveValue(false), "span")+")",
						"告诉海伦娜你想用"+Util.intToString(getSlaveValue(false))+"火币下单这个奴隶。",
						HELENAS_SHOP_CUSTOM_SLAVE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(false)), true);
						UtilText.addSpecialParsingString(Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getSlaveValue(false)));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<getSlaveValue(true)) {
					return new Response("史莱姆特征("+UtilText.formatAsMoneyUncoloured(getSlaveValue(true), "span")+")",
							"你付不起史莱姆特征的钱，你只有"+Util.intToString(Main.game.getPlayer().getMoney())+"火币。",
							null);
				}
				return new Response("史莱姆特征("+UtilText.formatAsMoney(getSlaveValue(true), "span")+")",
						"告诉海伦娜你想用"+Util.intToString(getSlaveValue(true))+"火币下单“史莱姆特征”的奴隶。",
						HELENAS_SHOP_CUSTOM_SLAVE_ORDER) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(Util.intToString(getSlaveValue(true)), true);
						UtilText.addSpecialParsingString(Main.game.getDateNow().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE), false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_SLIME"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_ORDER_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getSlaveValue(true)));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_ORDER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().helenaSlaveOrderDay = Main.game.getDayNumber();
			CharacterModificationUtils.resetImpossibeSexExperience(); // If no vagina/penis, reset experience.
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
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			NPC slave = (NPC) getSlaveForCustomisation();
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			NPC slave = (NPC) getSlaveForCustomisation();
			if(index==1) {
				return new Response("回应", UtilText.parse(slave, "积极回应[npc.namePos]的问题。"), HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END", slave));
						Main.game.getPlayer().addSlave(slave);
						slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_CUSTOM_SLAVE_DELIVERY_END = new DialogueNode("", "", true, true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			AbstractClothing underwear = Main.game.getNpc(Helena.class).getClothingInSlot(InventorySlot.GROIN);
			if(underwear!=null) {
				Main.game.getNpc(Helena.class).unequipClothingIntoVoid(underwear, true, Main.game.getNpc(Helena.class));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				responses.add(new Response("舔阴", "由于无法使用嘴巴，所以你不能给海伦娜舔阴！", null));
			} else {
				responses.add(new ResponseSex(
						"舔阴",
						"在海伦娜面前跪下，开始舔阴。",
						true, true,
						HelenaHotel.getHelenaSexManager(false,
								SexPosition.AGAINST_WALL, SexSlotAgainstWall.BACK_TO_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_PERFORM_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
					}
				});
			}

			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("吻肛", "由于无法使用嘴巴，你无法给海伦娜吻肛！", null));
				} else {
					responses.add(new ResponseSex(
							"吻肛",
							"询问海伦娜你是否能给她吻肛。",
							true, true,
							HelenaHotel.getHelenaSexManager(false,
									SexPosition.AGAINST_WALL, SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_PERFORM_ANILINGUS")) {
						@Override
						public void effects() {
							if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
							}
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
						}
					});
				}
			}
			
			if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) { // If Helena is dominant, she wants to be on the receiving end of oral
				if(Main.game.getPlayer().hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("接受口交", "由于无法使用阴茎，海伦娜不能舔你的鸡巴。", null));
					} else {
						responses.add(new ResponseSex(
								"接受口交",
								"让海伦娜舔你的鸡巴。",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.AGAINST_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL
											:SexSlotAgainstWall.PERFORMING_ORAL_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotAgainstWall.BACK_TO_WALL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_BLOWJOB_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_BLOWJOB_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE));
								}
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
						});
					}
				}
	
				if(Main.game.getPlayer().hasVagina()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						responses.add(new Response("被舔阴", "由于无法使用小穴，海伦娜无法给你舔阴。", null));
					} else {
						responses.add(new ResponseSex(
								"被舔阴",
								"让海伦娜给你舔阴。",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										Main.game.getPlayer().isTaur()
											?SexPosition.STANDING
											:SexPosition.AGAINST_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.PERFORMING_ORAL_BEHIND
											:SexSlotAgainstWall.PERFORMING_ORAL_WALL,
										Main.game.getPlayer().isTaur()
											?SexSlotStanding.STANDING_DOMINANT
											:SexSlotAgainstWall.BACK_TO_WALL,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								(Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_CUNNILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RECEIVE_CUNNILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
							}
						});
					}
				}
			}
			
			// Penetrative sex:
			
			if(Main.game.getPlayer().hasPenis()) {
				if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
					if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
						responses.add(new Response("骑乘", "海伦娜不愿意就这样失去处女！", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("骑乘", "由于无法使用阴茎，你不能操海伦娜。", null));
						
					} else {
						responses.add(new ResponseSex(
								"骑乘",
								"让海伦娜骑上你的肉棒。",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RIDDEN")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
							}
						});
					}
					if(Main.game.isAnalContentEnabled()) {
						if(Main.game.getNpc(Helena.class).isAssVirgin()) {
							responses.add(new Response("骑乘(肛交)", "海伦娜不愿意就这样失去肛门贞操！", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("骑乘(肛交)", "由于无法使用阴茎，你不能操海伦娜的屁股。", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							responses.add(new Response("骑乘(肛交)", "海伦娜想让你操她屁股之前先给那里润滑一下，但由于无法使用嘴巴，所以你不能这么做……", null));
							
						} else {
							responses.add(new ResponseSex(
									"骑乘(肛交)",
									"让海伦娜用后面骑上你的肉棒。<br/>[style.italicsSex(她会让你先用唾液润滑后再让你插入……)]",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_RIDDEN_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
								}
							});
						}
					}
					
				} else { // Submissive:
					if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
						responses.add(new Response("操她", "海伦娜不愿意就这样失去处女！", null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						responses.add(new Response("操她", "由于无法使用阴茎，你不能操海伦娜。", null));
						
					} else {
						responses.add(new ResponseSex(
								"操她",
								"让海伦娜趴在附近的桌子上，开始干她的小穴。",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										SexPosition.OVER_DESK, Main.game.getNpc(Helena.class).isVisiblyPregnant()?SexSlotDesk.OVER_DESK_ON_BACK:SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_FUCK_HER")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
							}
						});
					}
					if(Main.game.isAnalContentEnabled()) {
						if(Main.game.getNpc(Helena.class).isAssVirgin()) {
							responses.add(new Response("操她(肛交)", "海伦娜不愿意就这样失去肛门贞操！", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("操她(肛交)", "由于无法使用阴茎，你不能操海伦娜的屁股。", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							responses.add(new Response("操她(肛交)", "海伦娜想让你操她屁股之前先给那里润滑一下，但由于无法使用嘴巴，所以你不能这么做……", null));
							
						} else {
							responses.add(new ResponseSex(
									"操她(肛交)",
									"让海伦娜趴在附近的桌子上，开始用她的后面。<br/>[style.italicsSex(她会让你先用唾液润滑后再让你插入……)]",
									true, true,
									HelenaHotel.getHelenaSexManager(false,
											SexPosition.OVER_DESK, Main.game.getNpc(Helena.class).isVisiblyPregnant()?SexSlotDesk.OVER_DESK_ON_BACK:SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.BETWEEN_LEGS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_FUCK_HER_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_AFTER_SEX = new DialogueNode("结束", "海伦娜好了，需要回去工作。", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	//TODO
	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_THREESOME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "专注海伦娜";
			} else if(index==1) {
				return "专注斯嘉丽";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			if(responseTab==0) { // Helena focus:
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					responses.add(new Response("舔阴", "由于无法使用嘴巴，所以你不能给海伦娜舔阴！", null));
				} else {
					responses.add(new ResponseSex(
							"舔阴",
							"让海伦娜躺下，这样你就可以低下头趴在腿间给她舔阴，同时让斯嘉丽坐在她的脸上了。",
							true, true,
							HelenaHotel.getHelenaSexManager(false,
									true,
									true,
									SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.MISSIONARY_ORAL,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
									Main.game.getNpc(Scarlett.class).hasVagina()
										?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
										:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
									null,
									Main.game.getNpc(Scarlett.class).hasVagina()
										?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
										:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
												:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_PERFORM_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							List<InitialSexActionInformation> list = new ArrayList<>();
							list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
							} else {
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
							}
							return list;
						}
					});
				}

				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("吻肛", "由于无法使用嘴巴，你无法给海伦娜吻肛！", null));
					} else {
						responses.add(new ResponseSex(
								"吻肛",
								"让海伦娜坐在你的脸上，这样你就能给她吻肛，同时让她用嘴巴侍奉斯嘉丽了。",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										true,
										false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.BESIDE, SexSlotLyingDown.LYING_DOWN,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										Main.game.getNpc(Scarlett.class).hasVagina()
											?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
											:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										null,
										Main.game.getNpc(Scarlett.class).hasVagina()
											?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
											:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
													:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_PERFORM_ANILINGUS")
								+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))==0
									?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_ANILINGUS_FIRST_TIME")
									:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_ANILINGUS_EXPERIENCED"))) {
							@Override
							public void effects() {
								if(!Main.game.getNpc(Helena.class).getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
									Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).setFetishDesire(Fetish.FETISH_ANAL_RECEIVING, FetishDesire.THREE_LIKE));
								}
							}
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueAnus.ANILINGUS_START, false, true));
								if(Main.game.getNpc(Scarlett.class).hasVagina()) {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START, false, true));
								} else {
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
								}
								return list;
							}
						});
					}
				}
				
				if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) { // If Helena is dominant, she wants to be on the receiving end of oral
					if(Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("接受口交", "由于无法使用阴茎，海伦娜不能舔你的鸡巴。", null));
						} else {
							if(Main.game.getNpc(Scarlett.class).hasPenis()
									&& (!Main.game.getNpc(Helena.class).isAssVirgin() || !Main.game.getNpc(Helena.class).isVaginaVirgin())) {
								if(!Main.game.getNpc(Helena.class).isAssVirgin()) {
									responses.add(new ResponseSex(
											"接受口交",
											"让海伦娜给你口交，同时让斯嘉丽在后面干她的屁股。",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB_SCARLETT_ANAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
									
								} else {
									responses.add(new ResponseSex(
											"接受口交",
											"让海伦娜给你口交，同时让斯嘉丽从后面插入她。",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB_SCARLETT_VAGINAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
								}
								
							} else {
								responses.add(new ResponseSex(
										"接受口交",
										"让海伦娜给你口交，同时也让她坐在斯嘉丽的脸上。",
										true, true,
										HelenaHotel.getHelenaSexManager(true,
												false,
												true,
												SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.BESIDE,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
												null,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
														new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_BLOWJOB")
										+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))==0
											?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_BLOWJOB_FIRST_TIME")
											:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "THREESOME_BLOWJOB_EXPERIENCED"))) {
									@Override
									public void effects() {
										if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
										}
									}
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										return list;
									}
								});
							}
						}
					}
		
					if(Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							responses.add(new Response("被舔阴", "由于无法使用小穴，海伦娜无法给你舔阴。", null));
						} else {
							if(Main.game.getNpc(Scarlett.class).hasPenis()
									&& (!Main.game.getNpc(Helena.class).isAssVirgin() || !Main.game.getNpc(Helena.class).isVaginaVirgin())) {
								if(!Main.game.getNpc(Helena.class).isAssVirgin()) {
									responses.add(new ResponseSex(
											"被舔阴",
											"让海伦娜舔你的下面，同时让斯嘉丽在后面干她的屁股。",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS_SCARLETT_ANAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
									
								} else {
									responses.add(new ResponseSex(
											"被舔阴",
											"让海伦娜舔你的下面，同时让斯嘉丽从后面插入她。",
											true, true,
											HelenaHotel.getHelenaSexManager(false,
													true,
													true,
													SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.IN_FRONT,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
													new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
													null,
													new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
													Util.newHashMapOfValues(
															new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
															new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
															new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
											null,
											null,
											HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
											UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS_SCARLETT_VAGINAL")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											List<InitialSexActionInformation> list = new ArrayList<>();
											list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
											return list;
										}
									});
								}
								
							} else {
								responses.add(new ResponseSex(
										"被舔阴",
										"坐在海伦娜的脸上，这样她就能舔你的下面，同时让斯嘉丽低下头趴在腿间给她舔阴了。",
										true, true,
										HelenaHotel.getHelenaSexManager(false,
												true,
												true,
												SexPosition.LYING_DOWN, SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.FACE_SITTING,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
												null,
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.VAGINA)),
														new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RECEIVE_CUNNILINGUS")
										+ (Main.game.getNpc(Helena.class).getTotalSexCount(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))==0
											?UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "GIVING_CUNNILINGUS_FIRST_TIME")
											:UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "GIVING_CUNNILINGUS_EXPERIENCED"))) {
									@Override
									public void effects() {
										if(!Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_ORAL_GIVING)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Helena.class).addFetish(Fetish.FETISH_ORAL_GIVING));
										}
									}
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true));
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										return list;
									}
								});
							}
						}
					}
				}
				
				// Penetrative sex:
				
				if(Main.game.getPlayer().hasPenis()) {
					if(Main.game.getNpc(Helena.class).hasFetish(Fetish.FETISH_DOMINANT)) {
						if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
							responses.add(new Response("骑乘", "海伦娜不愿意就这样失去处女！", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("骑乘", "由于无法使用阴茎，你不能操海伦娜。", null));
							
						} else {
							responses.add(new ResponseSex(
									"骑乘",
									"让海伦娜骑上你的肉棒，斯嘉丽坐在你的脸上。",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
												:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													Main.game.getNpc(Scarlett.class).hasVagina()
														?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH))
														:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									if(Main.game.getNpc(Scarlett.class).hasVagina()) {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									} else {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
									}
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getNpc(Helena.class).isAssVirgin()) {
								responses.add(new Response("骑乘(肛交)", "海伦娜不愿意就这样失去肛门贞操！", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								responses.add(new Response("骑乘(肛交)", "由于无法使用阴茎，你不能操海伦娜的屁股。", null));
								
							} else {
								responses.add(new ResponseSex(
										"骑乘(肛交)",
										"让海伦娜用后面骑上你的肉棒，斯嘉丽坐在你的脸上。",
										true, true,
										HelenaHotel.getHelenaSexManager(true,
												true,
												false,
												SexPosition.LYING_DOWN, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LYING_DOWN,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
													:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)),
														Main.game.getNpc(Scarlett.class).hasVagina()
															?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH))
															:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_RIDDEN_ANAL")) {
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), PenisAnus.USING_PENIS_START, false, true));
										if(Main.game.getNpc(Scarlett.class).hasVagina()) {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										} else {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
										}
										list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
										return list;
									}
								});
							}
						}
						
					} else { // Submissive:
						if(Main.game.getNpc(Helena.class).isVaginaVirgin()) {
							responses.add(new Response("操她", "海伦娜不愿意就这样失去处女！", null));
							
						} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("操她", "由于无法使用阴茎，你不能操海伦娜。", null));
							
						} else {
							responses.add(new ResponseSex(
									"操她",
									"让海伦娜四肢着地趴下，从身后插入她，同时让她用嘴巴侍奉斯嘉丽。",
									true, true,
									HelenaHotel.getHelenaSexManager(false,
											true,
											true,
											SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
												:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
											null,
											Main.game.getNpc(Scarlett.class).hasVagina()
												?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
												:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													Main.game.getNpc(Scarlett.class).hasVagina()
														?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
														:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_FUCK_HER")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisVagina.PENIS_FUCKING_START, false, true));
									if(Main.game.getNpc(Scarlett.class).hasVagina()) {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									} else {
										list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
									}
									return list;
								}
							});
						}
						if(Main.game.isAnalContentEnabled()) {
							if(Main.game.getNpc(Helena.class).isAssVirgin()) {
								responses.add(new Response("操她(肛交)", "海伦娜不愿意就这样失去肛门贞操！", null));
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								responses.add(new Response("操她(肛交)", "由于无法使用阴茎，你不能操海伦娜的屁股。", null));
								
							} else {
								responses.add(new ResponseSex(
										"操她(肛交)",
										"让海伦娜四肢着地趴下，从身后插入她的后穴，同时让她用嘴巴侍奉斯嘉丽。",
										true, true,
										HelenaHotel.getHelenaSexManager(false,
												true,
												true,
												SexPosition.ALL_FOURS, SexSlotAllFours.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND,
												new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)
													:new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
												null,
												Main.game.getNpc(Scarlett.class).hasVagina()
													?new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)
													:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
												Util.newHashMapOfValues(
														new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)),
														Main.game.getNpc(Scarlett.class).hasVagina()
															?new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA))
															:new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
														new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))),
										null,
										null,
										HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
										UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_FUCK_HER_ANAL")) { // Player anilingus first
									@Override
									public List<InitialSexActionInformation> getInitialSexActions() {
										List<InitialSexActionInformation> list = new ArrayList<>();
										list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisAnus.PENIS_FUCKING_START, false, true));
										if(Main.game.getNpc(Scarlett.class).hasVagina()) {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
										} else {
											list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), PenisMouth.BLOWJOB_START, false, true));
										}
										return list;
									}
								});
							}
						}
					}
				}
				
			} else if(responseTab==1) { // Scarlett focus:
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("口交", "由于无法使用嘴巴，你不能给斯嘉丽口交！", null));
					} else {
						responses.add(new ResponseSex(
								"口交",
								"跟海伦娜一起，跪下来给斯嘉丽口交。",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										true,
										false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL_TWO,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_DOUBLE_BLOWJOB")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								return list;
							}
						});
					}
					
				} else {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("舔阴", "由于无法使用嘴巴，你不能给斯嘉丽舔阴！", null));
					} else {
						responses.add(new ResponseSex(
								"舔阴",
								"跟海伦娜一起，跪下来侍奉斯嘉丽的小穴。",
								true, true,
								HelenaHotel.getHelenaSexManager(false,
										true,
										false,
										SexPosition.SITTING, SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING, SexSlotSitting.PERFORMING_ORAL_TWO,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_DOUBLE_CUNNILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueVagina.CUNNILINGUS_START_ADDITIONAL, false, true));
								return list;
							}
						});
					}
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						responses.add(new Response("吻肛", "由于无法使用嘴巴，你无法给斯嘉丽吻肛！", null));
					} else {
						responses.add(new ResponseSex(
								"吻肛",
								"让斯嘉丽坐在你的脸上，这样你就能给她吻肛，同时让她用嘴巴侍奉海伦娜了。",
								true, true,
								HelenaHotel.getHelenaSexManager(true,
										true,
										false,
										SexPosition.LYING_DOWN, SexSlotLyingDown.BESIDE, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LYING_DOWN,
										null,
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA)),
												new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
												new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
								null,
								null,
								HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
								UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								List<InitialSexActionInformation> list = new ArrayList<>();
								list.add(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Scarlett.class), TongueAnus.ANILINGUS_START, false, true));
								list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getNpc(Helena.class), TongueVagina.CUNNILINGUS_START, false, true));
								return list;
							}
						});
					}
				}
				
				// Penetrative sex:

				if(Main.game.getPlayer().hasPenis()) {
					if(Main.game.getNpc(Scarlett.class).hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("骑乘", "由于无法使用阴茎，你不能操斯嘉丽。", null));
							
						} else {
							responses.add(new ResponseSex(
									"骑乘",
									"让斯嘉丽骑上你的肉棒，海伦娜坐在你的脸上。",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}

				if(Main.game.getNpc(Scarlett.class).hasVagina()) {
					if(Main.game.getPlayer().hasPenis()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							responses.add(new Response("骑乘", "由于无法使用阴茎，你不能操斯嘉丽。", null));
							
						} else {
							responses.add(new ResponseSex(
									"骑乘",
									"让斯嘉丽骑上你的肉棒，海伦娜坐在你的脸上。",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.LYING_DOWN, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.LYING_DOWN,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_RIDDEN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}

				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					if(Main.game.getPlayer().hasVagina()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							responses.add(new Response("挨操", "由于无法使用小穴，斯嘉丽不能插入你。", null));
							
						} else {
							responses.add(new ResponseSex(
									"挨操",
									"让斯嘉丽插入你的秘缝，同时你给海伦娜舔阴。",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_FUCKED")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
					if(Main.game.isAnalContentEnabled()) {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							responses.add(new Response("挨操(肛交)", "由于无法使用肛门，斯嘉丽不能插入你。", null));
							
						} else {
							responses.add(new ResponseSex(
									"挨操(肛交)",
									"让斯嘉丽操你的屁股，同时你给海伦娜舔阴。",
									true, true,
									HelenaHotel.getHelenaSexManager(true,
											true,
											false,
											SexPosition.ALL_FOURS, SexSlotAllFours.IN_FRONT, SexSlotAllFours.BEHIND, SexSlotAllFours.ALL_FOURS,
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH),
											new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
											new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getNpc(Helena.class), Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.MOUTH)),
													new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.MOUTH)),
													new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.MOUTH)))),
									null,
									null,
									HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME,
									UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_THREESOME_SCARLETT_FUCKED_ANAL")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									List<InitialSexActionInformation> list = new ArrayList<>();
									list.add(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
									list.add(new InitialSexActionInformation(Main.game.getNpc(Helena.class), Main.game.getNpc(Scarlett.class), TongueMouth.KISS_START, false, true));
									return list;
								}
							});
						}
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME = new DialogueNode("结束", "海伦娜做完了，需要回去工作。", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Helena.class).cleanAllClothing(true, false);
			Main.game.getNpc(Helena.class).cleanAllDirtySlots(true);
			Main.game.getNpc(Scarlett.class).cleanAllClothing(true, false);
			Main.game.getNpc(Scarlett.class).cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_BACK_ROOM_AFTER_SEX_THREESOME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	
	// Scarlett:
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "告诉斯嘉丽她可以回去工作了。", HELENAS_SHOP_SCARLETT_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_END"));
					}
				};
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettTalkedTo)) {
					return new Response("对话", "你今天已经跟斯嘉丽谈过话了……", null);
				}
				return new Response("对话", "询问斯嘉丽最近过得如何。", HELENAS_SHOP_SCARLETT_TALK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettTalkedTo, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCounterOral)) {
					return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"口交":"舔阴",
							"你今天已经用嘴巴侍奉过斯嘉丽了……",
							null);
				}
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"口交":"舔阴",
							"斯嘉丽并没有被你吸引，所以她不愿意让你给她"+(Main.game.getNpc(Scarlett.class).hasPenis()?"来一回迅速的口交":"来一回迅速的舔阴")+"。",
							null);
				}
				return new Response(Main.game.getNpc(Scarlett.class).hasPenis()?"口交":"舔阴",
						"跪在商店的柜台下面，给斯嘉丽"+(Main.game.getNpc(Scarlett.class).hasPenis()?"来一回迅速的口交":"来一回迅速的舔阴")+"。",
						HELENAS_SHOP_SCARLETT_COUNTER_ORAL) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCounterOral, true);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==5) {
				if(!Main.game.getNpc(Scarlett.class).isAttractedTo(Main.game.getPlayer())) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"小餐馆"
								:"午餐休息",
							"斯嘉丽只愿意跟能吸引她的人一起共进午餐，由于你不够女性化，她不愿意跟你消磨时光……",
							null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafe)) {
					return new Response("小餐馆", "你今天已经跟斯嘉丽去过小餐馆了……", null);
					
				} else if(Main.game.getHourOfDay()<11) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"小餐馆"
								:"午餐休息",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"现在去午餐为时尚早，她不能跟你去小餐厅。[units.time(11)]到[units.time(15)]之间再来。"
								:"斯嘉丽现在去午餐为时尚早。[units.time(11)]到[units.time(15)]之间再来。",
							null);
					
				} else if(Main.game.getHourOfDay()>15) {
					return new Response(
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"小餐馆"
								:"午餐休息",
							Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
								?"斯嘉丽已经用过午餐了，所以不能跟你去小餐厅。另找一天的[units.time(11)]到[units.time(15)]之间再来。"
								:"斯嘉丽已经用过午餐了，所以再跟你一起。另找一天的[units.time(11)]到[units.time(15)]之间再来。",
							null);
					
				}
				
				return new Response(
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
							?"小餐馆"
							:"午餐休息",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed)
							?"询问斯嘉丽愿不愿意跟你再去小餐厅用午餐。"
							:"询问斯嘉丽愿不愿意跟你去小餐厅用午餐。",
						HELENAS_SHOP_SCARLETT_CAFE);
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_TALK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_COUNTER_ORAL = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"吮吸鸡巴",
							"听斯嘉丽的，含住她的鸡巴……",
							true,
							false,
							new SMScarlettShopOral(),
							null,
							null,
							HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
					
				} else {
					return new ResponseSex(
							"给她舔阴",
							"听斯嘉丽的，舔她的下面……",
							true,
							false,
							new SMScarlettShopOral(),
							null,
							null,
							HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_COUNTER_ORAL_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL = new DialogueNode("结束", "斯嘉丽很满足，从柜台退开……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_AFTER_COUNTER_ORAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_END = new DialogueNode("", "", true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(((Scarlett)Main.game.getNpc(Scarlett.class)).isLikesPlayer()) {
				if(index==1) {
					return new Response("小餐馆", "让斯嘉丽带你去她想去的那家小餐馆。", HELENAS_SHOP_SCARLETT_CAFE_ARRIVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_CORE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()<150) {
						return new Response("支付("+UtilText.formatAsMoneyUncoloured(150, "span")+")", "你的钱不够付斯嘉丽的午餐……", null);
					}
					return new Response("付钱("+UtilText.formatAsMoney(150, "span")+")", "告诉斯嘉丽这一顿你请。", HELENAS_SHOP_SCARLETT_CAFE_ARRIVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_PAY"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_CORE"));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-150));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else if(index==2) {
					return new Response("拒绝", "拒绝给斯嘉丽的午餐付钱。", HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY);
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_REFUSE_TO_PAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_ARRIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_CAFE, false);
			Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCafe, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettCafeRevealed, true);
		}
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
				if(Main.game.getPlayer().getMoney()<150) {
					return new Response("点单("+UtilText.formatAsMoneyUncoloured(150, "span")+")", "你的钱不够点一份午餐……", null);
				}
				return new Response("点单("+UtilText.formatAsMoney(150, "span")+")", "点一份跟斯嘉丽相同样式的三明治。", HELENAS_SHOP_SCARLETT_CAFE_EATING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_ARRIVE_EATING_LUNCH"));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-150));
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("不点单", "什么也不点，只是坐着让斯嘉丽跟你边吃边聊。", HELENAS_SHOP_SCARLETT_CAFE_EATING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_ARRIVE_NO_LUNCH"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_EATING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_DISCUSSION");
				
			} else if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_DISCUSSION_REPEAT");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMATION_APPLIED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
				return HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("劝止",
							"劝斯嘉丽暂时不要喝下药水。<br/>[style.italics(如果你改变主意，可以随时让她喝下。)]",
							HELENAS_SHOP_SCARLETT_CAFE_END) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_NO_TRANSFORMATION"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
						}
					};
					
				} else if(index==2) {
					return new Response("喝",
							"支持斯嘉丽喝下药水。<br/>"
							+ (Main.game.getNpc(Scarlett.class).hasVagina()
								?"[style.italicsTfSex(这会让斯嘉丽的胸部增大一个罩杯(变成"+CupSize.getCupSizeFromInt(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+1)+"罩杯)，"
										+ "并且让她显得更加女性化！)]"
								:"[style.italicsTfSex(这将会增加斯嘉丽的精液产量，同时也会让她的肉棒加粗，并且增长至[style.sizes(20)]！)]"),
							HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED"));
							
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								Main.game.getNpc(Scarlett.class).setFemininity(90);
								Main.game.getNpc(Scarlett.class).setBreastSize(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+1);
								Main.game.getNpc(Scarlett.class).setVaginaWetness(Wetness.FIVE_SLOPPY);
								
							} else {
								Main.game.getNpc(Scarlett.class).setPenisSize(20);
								Main.game.getNpc(Scarlett.class).setPenisGirth(PenetrationGirth.THREE_AVERAGE);
								Main.game.getNpc(Scarlett.class).setPenisCumStorage(50);
							}

							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_CORE"));
							
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied, true);
						}
					};
					
				} else if(index==3) {
					if(Main.game.getPlayer().getEssenceCount()<10) {
						return new Response("增强(10精华)", "你至少需要十点奥术精华才能增强转化药剂的效果！", null);
					}
					return new Response("增强([style.colourArcane(10精华)])",
							"使用十点奥术精华增强斯嘉丽那个药水的效果。<br/>"
							+ (Main.game.getNpc(Scarlett.class).hasVagina()
								?"[style.italicsTfSex(这会让斯嘉丽的胸部增大两个罩杯(变成"+CupSize.getCupSizeFromInt(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+2)+"罩杯)，"
										+ "并且让她显得格外女性化！)]"
								:"[style.italicsTfSex(这将会增加斯嘉丽的精液产量，同时也会让她的肉棒加粗，并且增长至[style.sizes(30)]！)]"),
							HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_EXTRA"));
							
							if(Main.game.getNpc(Scarlett.class).hasVagina()) {
								Main.game.getNpc(Scarlett.class).setFemininity(95);
								Main.game.getNpc(Scarlett.class).setBreastSize(Main.game.getNpc(Scarlett.class).getBreastSize().getMeasurement()+2);
								Main.game.getNpc(Scarlett.class).setVaginaWetness(Wetness.FIVE_SLOPPY);
								
							} else {
								Main.game.getNpc(Scarlett.class).setPenisSize(30);
								Main.game.getNpc(Scarlett.class).setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
								Main.game.getNpc(Scarlett.class).setPenisCumStorage(150);
							}

							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_CORE"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementEssenceCount(-10, false));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationDiscussed, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED = new DialogueNode("", "", true, true) {
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
				return new Response("拒绝", "告诉斯嘉丽你不想跟她做什么淫乱的行为。", HELENAS_SHOP_SCARLETT_CAFE_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_EATING_TRANSFORMED_NO_SEX"));
					}
				};
				
			} else if(index==2) {
				if(Main.game.getNpc(Scarlett.class).hasPenis()) {
					return new ResponseSex(
							"手淫",
							"坐到斯嘉丽身旁，给她手淫……",
							true,
							false,
							getScarlettCafeSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO,
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.PENIS)))),
							null,
							null,
							HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_MASTURBATION_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
					
				} else {
					return new ResponseSex(
							"指交",
							"坐到斯嘉丽身旁，给她指交……",
							true,
							false,
							getScarlettCafeSexManager(SexPosition.SITTING, SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO,
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Scarlett.class), Util.newArrayListOfValues(CoverableArea.VAGINA)))),
							null,
							null,
							HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX,
							UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_MASTURBATION_START")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Scarlett.class), Main.game.getPlayer(), FingerVagina.FINGERED_START, false, true));
						}
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Scarlett.class).incrementAffection(Main.game.getPlayer(), 5));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX = new DialogueNode("结束", "斯嘉丽满足了，让你从她身边走开……", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return HELENAS_SHOP_SCARLETT_CAFE_END.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_END = new DialogueNode("", "", true, true) {
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
				return new Response("返回", "伴随斯嘉丽一起返回海伦娜精品店。", HELENAS_SHOP_SCARLETT_CAFE_RETURNED) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false);
						Main.game.getNpc(Scarlett.class).setLocation(Main.game.getPlayer(), false);
						Main.game.getNpc(Helena.class).setLocation(Main.game.getPlayer(), false); // Just in case time passed closing time during sex.
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/slaverAlley/helenasBoutique", "HELENAS_SHOP_SCARLETT_CAFE_RETURNED"));
						if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationApplied)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.helenaShopScarlettExtraTransformationHelenaReacted, true);
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode HELENAS_SHOP_SCARLETT_CAFE_RETURNED = new DialogueNode("", "", true) {
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
			return ROMANCE_SHOP_CORE.getResponse(responseTab, index);
		}
	};
	
	
}
