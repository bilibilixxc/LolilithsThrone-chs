package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.npc.submission.RatGangMember;
import com.lilithsthrone.game.character.npc.submission.RatWarrensCaptive;
import com.lilithsthrone.game.character.npc.submission.Shadow;
import com.lilithsthrone.game.character.npc.submission.Silence;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.GenericSexFlag;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Dialogue for when the player is taken captive by the rats in the Rat Warrens.
 * 
 * @since 0.3.5.5
 * @version 0.3.9
 * @author Innoxia
 */
public class RatWarrensCaptiveDialogue {
	
	private static boolean playerGrewVagina;
	public static int murkOrgasmsRequired = 1;
	
	private static List<GameCharacter> getCharacters(boolean includeMilkers) {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> npc.isUnique() || (!includeMilkers && (npc instanceof RatWarrensCaptive)));
		Collections.sort(guards, (a, b)->a.getLevel()-b.getLevel());
		return guards;
	}
	
	private static List<GameCharacter> getMilkers() {
		List<GameCharacter> milkers = new ArrayList<>();
		for(GameCharacter milker : Main.game.getCharactersPresent(Main.game.getWorlds().get(WorldType.RAT_WARRENS).getCell(PlaceType.RAT_WARRENS_MILKING_ROOM))) {
			if(milker instanceof RatWarrensCaptive) {
				milkers.add(milker);
			}
		}
		return milkers;
	}
	
	private static void spawnRat(boolean vaginaNeeded, boolean penisNeeded) {
		List<String> adjectives = new ArrayList<>();
		try {
			String[] names = new String[] {"恶棍", "匪徒", "帮派成员", "黑手党成员"};
			NPC rat = new RatGangMember(Gender.getGenderFromUserPreferences(vaginaNeeded, penisNeeded));
			Main.game.addNPC(rat, false);
			rat.setLevel(4+Util.random.nextInt(5));
			rat.setLocation(Main.game.getPlayer(), true);
			adjectives.add(Main.game.getCharacterUtils().setGenericName(rat, Util.randomItemFrom(names), adjectives));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void banishRats() {
		for(GameCharacter rat : getCharacters(false)) {
			Main.game.banishNPC((NPC) rat);
		}
	}
	
	private static GameCharacter getMurk() {
		return Main.game.getNpc(Murk.class);
	}

	public static String equipCollar(GameCharacter character, GameCharacter equipper, Colour collarColour) {
		AbstractClothing collar = Main.game.getItemGen().generateClothing("innoxia_bdsm_metal_collar", collarColour, PresetColour.CLOTHING_STEEL, PresetColour.CLOTHING_GUNMETAL, false);
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_ENSLAVEMENT, TFPotency.MINOR_BOOST, 0));
		collar.removeEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MINOR_BOOST, 0));
		collar.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SPECIAL, TFModifier.CLOTHING_SEALING, TFPotency.MAJOR_DRAIN, 0));
		return character.equipClothingFromNowhere(collar, true, equipper);
	}
	
	private static ResponseSex getPlayerOwnerEscapeSexResponse(boolean lyingDown, DialogueNode node, String nodePathHandjob, String nodePathOral, String nodePathSex) {
		AbstractSexPosition position;
		Value<SexSlot, SexType> murkSexInfo;
		SexSlot playerSlot;
		String sexIntroTextPath;
		String responseTitle;
		String responseDescription;
		int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
		
		if(lyingDown) {
			if(stage==0) {
				responseTitle = "手交";
				responseDescription = "照默克说的，给他打手枪……";
				sexIntroTextPath = nodePathHandjob;
				position = SexPosition.LYING_DOWN;
				murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER));
				playerSlot = SexSlotLyingDown.BESIDE;
				
			} else if(stage>=4) {
				sexIntroTextPath = nodePathSex;
				responseTitle = "骑上鸡巴";
				responseDescription = "听主人的话，骑上他的鸡巴……";
				if(Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs()) {
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.COWGIRL_REVERSE;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.COWGIRL_REVERSE;
					}
					
				} else {
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.COWGIRL;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.COWGIRL;
					}
				}
				
			} else {
				responseTitle = "亲吻肉棒";
				responseDescription = "照默克说的做，亲吻他的鸡巴……";
				sexIntroTextPath = nodePathOral;
				position = SexPosition.LYING_DOWN;
				murkSexInfo = new Value<>(SexSlotLyingDown.LYING_DOWN, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
				playerSlot = SexSlotLyingDown.MISSIONARY_ORAL;
			}
			
		} else {
			if(stage==0) {
				responseTitle = "跪下";
				responseDescription = "照默克说的，跪在他面前，给他打飞机……";
				sexIntroTextPath = nodePathHandjob;
				position = SexPosition.STANDING;
				murkSexInfo = new Value<>(SexSlotStanding.STANDING_DOMINANT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER));
				playerSlot = SexSlotStanding.PERFORMING_ORAL;
				
			} else if(stage>=4) {
				sexIntroTextPath = nodePathSex;
				if(Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs()) {
					responseTitle = "骑背";
					responseDescription = "按照主人的吩咐，向他展示自己，让他好好地“骑”你……";
					position = SexPosition.ALL_FOURS;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotAllFours.HUMPING, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotAllFours.ALL_FOURS;
						
					} else {
						murkSexInfo = new Value<>(SexSlotAllFours.HUMPING, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotAllFours.ALL_FOURS;
					}
					
				} else {
					responseTitle = "躺下";
					responseDescription = "按主人说的做，躺在他面前，让他狠狠操你……";
					position = SexPosition.LYING_DOWN;
					if(Main.game.getPlayer().hasVagina()) {
						murkSexInfo = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
						playerSlot = SexSlotLyingDown.LYING_DOWN;
						
					} else {
						murkSexInfo = new Value<>(SexSlotLyingDown.MATING_PRESS, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
						playerSlot = SexSlotLyingDown.LYING_DOWN;
					}
				}
				
			} else {
				responseTitle = "趴下";
				responseDescription = "照默克说的做，趴在他面前，给他口交……";
				sexIntroTextPath = nodePathOral;
				position = SexPosition.ALL_FOURS;
				murkSexInfo = new Value<>(SexSlotAllFours.IN_FRONT, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
				playerSlot = SexSlotAllFours.ALL_FOURS;
			}
		}
		
		return new ResponseSex(
				responseTitle,
				responseDescription,
				true,
				false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(new Value<>(getMurk(), murkSexInfo.getKey())),
						Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), playerSlot))) {
					@Override
					public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
						return false;
					}
					@Override
					public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
						if(!character.isPlayer()) {
							if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH || murkSexInfo.getValue().getTargetedSexArea()==SexAreaPenetration.FINGER) {
								return OrgasmBehaviour.PULL_OUT;
							}
							return OrgasmBehaviour.CREAMPIE;
						}
						return super.getCharacterOrgasmBehaviour(character);
					}
					@Override
					public OrgasmCumTarget getCharacterPullOutOrgasmCumTarget(GameCharacter character, GameCharacter target) {
						if(!character.isPlayer()) {
							return OrgasmCumTarget.FACE;
						}
						return null;
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
					public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
						if(!performer.isPlayer()
								&& (murkSexInfo.getValue().getPerformingSexArea()==SexAreaPenetration.PENIS || murkSexInfo.getValue().getPerformingSexArea()==SexAreaOrifice.VAGINA)) {
							return Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA);
						}
						return new ArrayList<>();
					}
					@Override
					public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getForeplayPreference(character, targetedCharacter);
						}
						return murkSexInfo.getValue();
					}
					@Override
					public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
						if(character.isPlayer()) {
							return super.getMainSexPreference(character, targetedCharacter);
						}
						return murkSexInfo.getValue();
					}
				},
				null,
				null,
				node,
				UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", sexIntroTextPath, getCharacters(false))) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.ANUS) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					
				} else if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));

				} else if(murkSexInfo.getValue().getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					
				} else {
					return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
					
				}
			}
		};
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms) {
		return getPlayerMurkMilkingStallSM(murkSlot, murkSexPreference, murkOrgasms, null);
	}

	private static SexManagerDefault getPlayerMurkMilkingStallSM(SexSlot murkSlot, SexType murkSexPreference, int murkOrgasms, SexPace startingSexPace) {
		return getBasicSexManager(SexPosition.MILKING_STALL,
				Util.newHashMapOfValues(new Value<>(getMurk(), murkSlot)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
				murkSexPreference, murkOrgasms, startingSexPace, false);
	}
	
	private static SexManagerDefault getBasicSexManager(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			SexType murkSexPreference,
			int murkOrgasms,
			SexPace startingSexPace,
			boolean spitOnAsshole) {
		if(murkOrgasms>0) {
			murkOrgasmsRequired = murkOrgasms;
		}
		return new SexManagerDefault(position, dominants, submissives) {
			@Override
			public boolean isAppendStartingExposedDescriptions(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isPartnerWantingToStopSex(GameCharacter partner) {
				if(murkOrgasms>0 && partner.equals(getMurk())) {
					return Main.sex.getNumberOfOrgasms(partner)>=murkOrgasms;
				}
				return super.isPartnerWantingToStopSex(partner);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				if(!character.isPlayer()) {
					return OrgasmBehaviour.CREAMPIE;
				}
				return super.getCharacterOrgasmBehaviour(character);
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
				return false;
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
				for(GameCharacter dom : dominants.keySet()) {
					map.put(dom, Util.newArrayListOfValues(CoverableArea.PENIS));
				}
				return map;
			}
			@Override
			public boolean isAbleToRemoveSelfClothing(GameCharacter character) {
				return false;
			}
			@Override
			public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip) {
				return false;
			}
			@Override
			public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
				if(spitOnAsshole) {
					Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
					map.put(Main.game.getPlayer(), new HashMap<>());
					map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, Util.newHashMapOfValues(new Value<>(getMurk(), Util.newHashSetOfValues(LubricationType.SALIVA))));
					return map;
				}
				return super.getStartingWetAreas();
			}
			@Override
			public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return murkSexPreference;
				}
				return super.getForeplayPreference(character, targetedCharacter);
			}
			@Override
			public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
				if(!character.isPlayer()) {
					return getForeplayPreference(character, targetedCharacter);
				}
				return super.getMainSexPreference(character, targetedCharacter);
			}
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer() && startingSexPace!=null) {
					return startingSexPace;
				}
				return super.getStartingSexPaceModifier(character);
			}
		};
	}
	
	private static void applyWakingEffects() {
		getMurk().setLocation(Main.game.getPlayer(), false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveWashed, false);
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
		for(GameCharacter milker : getMilkers()) {
			((RatWarrensCaptive)milker).applyMilkingEquipment(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
		}
	}
	
	private static void applyPlayerMilkingPumps(boolean equip, List<InventorySlot> slots) {
		GameCharacter player = Main.game.getPlayer();
		if(equip) {
			// It really doesn't make any narrative sense for the game's lactation content setting to limit the entire purpose of Murk's milkers. Instead of preventing milking, the lactation content setting just limits descriptions of it.
			if(slots.contains(InventorySlot.NIPPLE) && player.hasBreasts()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, player);
			}
			if(slots.contains(InventorySlot.STOMACH) && player.hasBreastsCrotch()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, player);
			}
			if(slots.contains(InventorySlot.PENIS) && player.hasPenis()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, player);
			}
			if(slots.contains(InventorySlot.VAGINA) && player.hasVagina()) {
				player.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, player);
			}
			
		} else {
			for(AbstractClothing c : new ArrayList<>(player.getClothingCurrentlyEquipped())) {
				if(c.isMilkingEquipment() && slots.contains(c.getSlotEquippedTo())) {
					player.unequipClothingIntoVoid(c, true, player);
				}
			}
		}
	}
	
	public static void restoreInventories() {
		int essences = Main.game.getPlayer().getEssenceCount();
		Main.game.getPlayer().setInventory(Main.game.getSavedInventories().get(Main.game.getPlayer().getId()));
		Main.game.getPlayer().setEssenceCount(essences);
	}
	
	public static boolean isTransformationFinished() {
		CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
		return playerTf==null;
	}
	
	private static DialogueNode getSleepNode() {
		if(!isTransformationFinished()) {
			int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
			if(stage==0) {
				return CAPTIVE_DAY_1_WAKE;
			}
			if(stage==1) {
				return CAPTIVE_DAY_2_WAKE;
			}
			if(stage==3) {
				return CAPTIVE_DAY_3_WAKE;
			}
		}
		return BAD_END;
	}
	
	private static boolean isMasculineTransform() {
		return Main.getProperties().getForcedTFTendency().isMasculine()
				&& Main.game.isAnalContentEnabled();
	}

	private static String getObedienceResponseDescription(float increment) {
		String descriptor = "";
		if(increment<-10) {
			descriptor = "[style.italicsMinorGood(大幅减少)]";
		} else if(increment<5) {
			descriptor = "[style.italicsBad(减少)]";
		} else if(increment<0) {
			descriptor = "[style.italicsMinorBad(略微减少)]";
		} else if(increment<=5) {
			descriptor = "[style.italicsMinorGood(略微增加)]";
		} else if(increment<=10) {
			descriptor = "[style.italicsGood(增加)]";
		} else {
			descriptor = "[style.italicsExcellent(大幅增加)]";
		}
		return "<br/>"
				+ "<i>你的[style.boldObedience(顺从)]将会"+descriptor+"！</i>";
	}
	
	private static String incrementPlayerObedience(int increment) {
		Main.game.getPlayer().incrementObedience(increment);
		
		int obedience = (int) Units.round(Main.game.getPlayer().getObedienceValue(), 0);
		if(obedience<0) {
			Main.game.getPlayer().setObedience(0);
			obedience = 0;
		}
		ObedienceLevel level = Main.game.getPlayer().getObedience();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p style='text-align:center;'>");
			if(increment!=0) {
				sb.append("你"+(increment>0?"[style.boldGrow(获得了)]":"[style.boldShrink(失去了)]")+"<b>"+Math.abs(increment)+"</b>[style.boldObedience(服从)]！<br/>");
			}
			sb.append("你表现得<span style='color:"+level.getColour().toWebHexString()+";'>"+level.getName()+"</span>"
					+ "(<span style='color:"+level.getColour().toWebHexString()+";'>"+obedience+"</span>/100)，");
			switch(level) {
				case ZERO_FREE_WILLED:
				case POSITIVE_ONE_AGREEABLE:
				case POSITIVE_TWO_OBEDIENT:
					sb.append("[style.colourMinorGood(可以拒绝或者服从默克的任何命令)]！");
					sb.append("<br/><i>达到"+ObedienceLevel.POSITIVE_THREE_DISCIPLINED.getMinimumValue()+"/100后，你便无法拒绝默克的命令！</i>");
					break;
				case POSITIVE_THREE_DISCIPLINED:
				case POSITIVE_FOUR_DUTIFUL:
					sb.append("[style.colourMinorBad(无法让自己拒绝默克的命令)]！");
					sb.append("<br/><i>达到"+ObedienceLevel.POSITIVE_FIVE_SUBSERVIENT.getMinimumValue()+"/100后，你将会任劳任怨地取悦默克！</i>");
					break;
				case POSITIVE_FIVE_SUBSERVIENT:
					if(obedience>=100) {
						sb.append("[style.colourBadEnd(你已然接受了成为默克产奶工的宿命)]！");
					} else {
						sb.append("[style.colourBad(会任劳任怨地取悦默克)]！");
						sb.append("<br/><i>到达100/100后，你将会接受成为默克挤奶器的宿命！</i>");
					}
					break;
				default:
					break;
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static boolean isPlayerObeyingOrders(boolean extremeSubCheck) {
		return Main.game.getPlayer().getObedience().isGreaterThan(extremeSubCheck?ObedienceLevel.POSITIVE_FOUR_DUTIFUL:ObedienceLevel.POSITIVE_TWO_OBEDIENT);
	}
	
	
	// --------- START OF DAY 0 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_0 = new DialogueNode("", "", true) {
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
			if(index==1) {
				return new Response("保持安静",
						"你保持沉默，希望默克会放过你。"
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_QUIET"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
				
			} else if(index==2) {
				return new Response("感谢他",
						"你向默克说了声谢谢。"
								+getObedienceResponseDescription(10),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_THANK_HIM"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
				
			} else if(index==3) {
				return new Response("侮辱他",
						"你让默克滚蛋。"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_0_THANKS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_INSULT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-5));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_THANKS_END"));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_0_THANKS = new DialogueNode("", "", true, true) {
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
			if(Main.game.getPlayer().isPregnant()) {
				if(index==1) {
					return new Response("等待", "等默克回来……", CAPTIVE_GIVE_BIRTH) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							boolean eggs = !Main.game.getPlayer().getIncubatingLitters().isEmpty();
							if(eggs) {
								for(SexAreaOrifice orifice : new ArrayList<>(Main.game.getPlayer().getIncubatingLitters().keySet())) {
									Main.game.getPlayer().endIncubationPregnancy(orifice, true);
								}
							}
							Main.game.getPlayer().setMana(0);
							
							if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
								Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
								Main.game.getPlayer().incrementVaginaCapacity(
										(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
										false);
							}
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY);
							}
							if(eggs && !Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION);
							}
						}
					};
				}
				
			} else if(!Main.game.getPlayer().getIncubatingLitters().isEmpty()) {
				if(index==1) {
					return new Response("等待", "等默克回来……", CAPTIVE_LAY_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
							Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
							
							Main.game.getPlayer().endPregnancy(true);
							for(SexAreaOrifice orifice : new ArrayList<>(Main.game.getPlayer().getIncubatingLitters().keySet())) {
								Main.game.getPlayer().endIncubationPregnancy(orifice, true);
							}
							Main.game.getPlayer().setMana(0);
							
							if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) { // If birthing side quest is not complete, remove it, as otherwise completion (referencing Lily) doesn't make any sense.
								Main.game.getPlayer().removeQuest(QuestLine.SIDE_FIRST_TIME_INCUBATION);
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("睡觉",
							"你感觉整个人精疲力竭，渐渐进入梦乡……",
							CAPTIVE_NIGHT) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_0_SLEEP_START"));
						}
					};
				}
			}
			return null;
		}
	};
	
	// --------- END OF DAY 0 --------- //

	
	// --------- START OF DAY 1 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_1_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			if(getMilkers().get(3).isPregnant()) {
				getMilkers().get(3).endPregnancy(true); // End milker 4's pregnancy as she's the one to lie on top of the player at the end of day 2 (which seems wrong if she's pregnant)
			}
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_PREP"));
			if(isMasculineTransform()) {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE_MASCULINE"));
			} else {
				sb.append( UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_WAKE"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isMasculineTransform()) {
				if(index==1) {
					return new Response("躲开",
							"远离[murk.namePos]的鸡巴，他会将你转化为[style.colourMasculineStrong(男性)]产精工。",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.MASCULINE_PLUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveMasculine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_MASCULINE"));
						}
					};
					
				} else if(index==2) {
					return new Response("靠上去",
							"抬起屁股压住[murk.namePos]的鸡巴，他会将你转化为[style.colourAndrogynous(中性)]的娘炮产精工。",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.ANDROGYNOUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveSissy, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_SISSY"));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("躲开",
							"远离[murk.namePos]的手，他会将你转化为[style.colourFeminineStrong(女性)]产奶工。",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE_PLUS;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFeminine, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FEMALE"));
						}
					};
					
				} else if(index==2) {
					return new Response("蹭手",
							"在默克的手上蹭来蹭去，表示你更愿意被转化为[style.colourFeminine(扶她)]产奶工。",
							CAPTIVE_DAY_1_TF_CHOICE) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE;
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveFuta, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_TF_CHOICE_TF_FUTA"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_TF_CHOICE = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(!Main.game.getPlayer().getTattoos().isEmpty()) {
				Main.game.getPlayer().clearTattoos();
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END_CLEAR_TATTOOS"));
			}
			Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END"));
		}
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
			if(index==1) {
				return new Response("等待", "你别无选择，只能在颈手枷中等着……", CAPTIVE_DAY_1_MORNING) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(1);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(11*60 + (Util.random.nextInt(45)))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("打招呼",
						"向默克的肉棒打招呼，希望他能不再继续虐待你，就此离开。"
							+getObedienceResponseDescription(5),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_ENDURE", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getPlayer().addMuskMarkerCharacter(getMurk());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_MUSK_APPLIED", getMilkers()));
					}
				};
				
			} else if(index==2) {
				return new Response("称赞",
						"向默克的肉棒打招呼，向那根巨物和强而有力的气味发出溢美之词。"
							+getObedienceResponseDescription(10),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_COMPLIMENT", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getPlayer().addMuskMarkerCharacter(getMurk());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_MUSK_APPLIED", getMilkers()));
					}
				};
				
			} else if(index==3) {
				return new Response("抵抗",
						"拒绝跟默克玩这种愚蠢的游戏，远离他的肉棒。"
							+getObedienceResponseDescription(-10),
							CAPTIVE_DAY_1_MORNING_WAIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_RESIST", getMilkers()));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
						Main.game.getPlayer().addMuskMarkerCharacter(getMurk());
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_MORNING_MUSK_APPLIED", getMilkers()));
					}
				};
				
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_MORNING_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
						"你没有选择只能等默克回来……",
						CAPTIVE_DAY_1_LUNCH);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进食",
						"照默克说的，把粥吃了……"
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_EAT"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝",
						"拒绝吃下这份恶心的稀粥……"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_1_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_LUNCH_REFUSE"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			Main.game.getPlayer().applyFoodConsumed(15);
		}
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
			if(index==1) {
				return new Response("等待", "你别无选择，只能在颈手枷中等着……", CAPTIVE_DAY_1_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_1_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON"));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("张嘴",
						"你按照默克说的，吞下了转化药水……"
								+getObedienceResponseDescription(10),
						CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON_SWALLOW"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				return new Response("抵抗",
						"你紧闭着嘴，尽量不让自己喝下转化药水！"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_1_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTERNOON_RESIST"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("恢复", "转化终于步入尾声，你可以休息了……", CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"张嘴",
						"你情不自禁地含住了默克脉动的阴茎，但你还不至于要承认自己爱上了这根肉棒……"
								+getObedienceResponseDescription(5),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkCaptiveBlowjob, true);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"向肉棒示爱",
						"陪默克玩扮演，说自己已经爱上了他的肉棒，而这就是你们的初吻。"
								+getObedienceResponseDescription(15),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_1_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_AFTER_TRANSFORMATION_MURK_COCK_BLOWJOB_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkCaptiveBlowjob, true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_1_END = new DialogueNode("结束", "默克在你肚子里灌满了温热且气味浓重的精液，已经做够了……", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_1_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被锁住", "你又一次被锁链锁住了……", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 1 (Max. obedience = 60) --------- //
	
	
	
	
	// --------- START OF DAY 2 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_2_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("服从",
						"上到挤奶台上，让默克将你绑紧……"
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_OBEY"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("抵抗", "你的服从太高，已经不会想拒绝默克的命令了！", null);
				}
				return new Response("抵抗",
						"努力抵抗，不被束缚在挤奶台上……"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_WAKE_RESIST"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
			if(index==1) {
				return new Response("等待", "你别无选择，只能在颈手枷中等着……", CAPTIVE_DAY_2_MORNING) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(2);
						milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_MORNING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(10*60 + Util.random.nextInt(30))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(
						"亲吻",
						"按照默克的命令，亲吻他沾满了精液，气味浓重的肉棒。"
							+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new Response(
						"亲吻(渴求)",
						"一边跟默克说自己已经爱上了他的鸡巴，一边亲吻。"
							+getObedienceResponseDescription(10),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS_EAGER"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==3) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("拒绝", "你的服从太高，已经无法让自己违抗默克了……", null);
				}
				return new Response(
						"拒绝",
						"拒绝亲吻默克臭气熏天的鸡巴。"
							+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_MORNING_KISS_REFUSE"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_POTION = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("急切地乞求",
						"你发现自己竟然迫不及待地想被转化了，求着想要尝尝默克那瓶药水的味道……"
								+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("不情愿地乞求", "你的服从太高了，只能急切地乞求默克将转化药水喂给你！", null);
				}
				return new Response("不情愿地乞求",
						"你发现自己竟然迫不及待地想被转化了，但不至于表现得太过热情……",
						CAPTIVE_DAY_2_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_POTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isPlayerObeyingOrders(true)) {
					return new Response("舔肉棒", "你已经足够顺从，脑中只剩下了向默克的肉棒屈服一个想法！", null);
				}
				return new ResponseSex(
						"舔肉棒",
						"虽然你并不像承认自己爱上了默克的鸡巴，但你却忍不住想尝尝那个味道……"
							+getObedienceResponseDescription(5),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1),
						null,
						null,
						CAPTIVE_DAY_2_AFTER_BLOWJOB,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION_BLOWJOB")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"屈服于肉棒",
						"告诉默克你已经无可救药地爱上了他美味的肉棒，你情愿那东西在你的喉咙里肆虐！"
							+getObedienceResponseDescription(10),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.RECEIVING_ORAL, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1, SexPace.SUB_EAGER),
						null,
						null,
						CAPTIVE_DAY_2_AFTER_BLOWJOB,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_POTION_BLOWJOB_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTER_BLOWJOB = new DialogueNode("结束", "默克操够了你的喉咙……", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTER_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "你别无选择，只能在颈手枷中等着……", CAPTIVE_DAY_2_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(13*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进食",
						"照默克说的，把粥吃了……"
							+getObedienceResponseDescription(5),
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("拒绝", "你的服从太高，已经不会想拒绝吃下这份稀粥了！", null);
				}
				return new Response("拒绝",
						"拒绝吃下这份恶心的稀粥……"
							+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_REFUSE"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_2_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			Main.game.getPlayer().applyFoodConsumed(15);
		}
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
			if(index==1) {
				return new Response("等待", "你别无选择，只能在颈手枷中等着……", CAPTIVE_DAY_2_AFTERNOON) {
					@Override
					public void effects() {
						GameCharacter milker = getMilkers().get(0);
						if(Main.game.isAnalContentEnabled()) {
							milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						} else {
							milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON", getMilkers()));
			Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
			playerGrewVagina = false;
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(17*60)*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("急切地乞求",
						"你发现自己竟然迫不及待地想被转化了，求着想要尝尝默克那瓶药水的味道……"
								+getObedienceResponseDescription(5),
								CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						boolean vagina = Main.game.getPlayer().hasVagina();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
						if(!vagina && Main.game.getPlayer().hasVagina()) {
							playerGrewVagina = true;
						}
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("不情愿地乞求", "你的服从太高了，只能急切地乞求默克将转化药水喂给你！", null);
				}
				return new Response("不情愿地乞求",
						"你发现自己竟然迫不及待地想被转化了，但不至于表现得太过热情……",
						CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION) {
					@Override
					public void effects() {
						boolean vagina = Main.game.getPlayer().hasVagina();
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_POTION_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
						if(!vagina && Main.game.getPlayer().hasVagina()) {
							playerGrewVagina = true;
						}
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被抚摸", "默克用了好一会儿，摸遍了你全新的身体……", CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(playerGrewVagina), true);
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_AFTERNOON_AFTER_TRANSFORMATION_GROPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("训练", "服从训练来到了最后一步……", CAPTIVE_DAY_2_EVENING_TRAINING_START);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING_START = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(21*60 + Util.random.nextInt(30))*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("乞求",
						"连忙请求默克来干你，而不是别的产奶工。"
								+getObedienceResponseDescription(10),
								CAPTIVE_DAY_2_EVENING_TRAINING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("保持沉默", "你的服从太高了，无法无视默克的要求，满脑子只有乞求他来操你！", null);
				}
				return new Response("保持沉默",
						"尽管欲望几乎难以抵挡，但你还是拒绝向默克发出乞求，让他来操你。"
								+getObedienceResponseDescription(-10),
								CAPTIVE_DAY_2_EVENING_TRAINING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_START_REFUSE"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-5));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_EVENING_TRAINING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed()+(60*30));
			GameCharacter milker = getMilkers().get(3);
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("屈服",
						"同意默克的说法，你就是个顺从的产奶用的贱货，正需要他粗大的肉棒蹂躏。"
							+getObedienceResponseDescription(10),
						CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_BEG"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("抵抗", "你的服从太高，没法拒绝默克的命令！", null);
				}
				return new Response("抵抗",
						"你尽力克制自己向默克屈服的冲动，只是保持沉默。"
								+getObedienceResponseDescription(-10),
						CAPTIVE_DAY_2_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_EVENING_TRAINING_REFUSE"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_2_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			GameCharacter milker = getMilkers().get(3);
			getMurk().fillCumToMaxStorage();
			if(Main.game.isAnalContentEnabled()) {
				milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			} else {
				milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			}
			if(Main.game.getPlayer().hasPenis()) {
				Main.game.getPlayer().addDirtySlot(InventorySlot.PENIS);
			}
			if(Main.game.getPlayer().hasVagina()) {
				Main.game.getPlayer().addDirtySlot(InventorySlot.VAGINA);
			}
			Main.game.getPlayer().addDirtySlot(InventorySlot.ANUS);
			Main.game.getPlayer().addDirtySlot(InventorySlot.LEG);
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_2_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被锁住", "你又一次被锁链锁住了……", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 2 (max obedience = 100) --------- //
	
	
	
	
	// --------- START OF DAY 3 --------- //
	
	
	public static final DialogueNode CAPTIVE_DAY_3_WAKE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			applyWakingEffects();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("服从",
						"上到挤奶台上，让默克将你绑紧……"
							+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_OBEY"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("抵抗", "你的服从太高，已经不会想拒绝默克的命令了！", null);
				}
				return new Response("抵抗",
						"努力抵抗，不被束缚在挤奶台上……"
								+ getObedienceResponseDescription(-10),
						CAPTIVE_DAY_3_LOCKED_IN_STALL) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_WAKE_RESIST"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(-10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
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
			if(index==1) {
				return new Response("等待", "等待默克回来，将最后的转化药水给你……", CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			CaptiveTransformation playerTf = CaptiveTransformation.getTransformationType(Main.game.getPlayer());
			
			if(index==1) {
				return new Response("亲吻(渴求)",
						"你发现自己竟然迫不及待地想被转化了，饥渴地亲吻着默克的肉棒，说着自己已经爱上了这巨物……"
								+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT_EAGER"));
						Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(5));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
				
			} else if(index==2) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("不情愿地亲吻", "你的服从太高了，只能急切地乞求默克将转化药水喂给你！", null);
				}
				return new Response("不情愿地亲吻",
						"你发现自己竟然迫不及待地想被转化了，但不至于表现得太过热情……",
						CAPTIVE_DAY_3_AFTER_POTION) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LOCKED_IN_STALL_WAIT_RELUCTANT"));
						Map<String, String> effects = playerTf.getEffects(Main.game.getPlayer());
						for(Entry<String, String> entry : effects.entrySet()) {
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
									+ "</p>"
									+ entry.getValue());
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_POTION = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被榨取", "默克过来把你绑到了挤奶机上……", CAPTIVE_DAY_3_MILKING);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_MILKING = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.STOMACH, InventorySlot.PENIS)); // No vagina as Murk is going to fuck it first
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasVagina()) {
				if(index==1) {
					return new Response("喝下",
							"你已经欲火中烧，满脑子只想着要把转化药水一饮而尽……"
								+ getObedienceResponseDescription(10),
							CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_DRINK"));
							Main.game.getTextStartStringBuilder().append(incrementPlayerObedience(10));
							Map<String, String> effects = CaptiveTransformation.FEMININE_PUSSY_FINAL.getEffects(Main.game.getPlayer());
							for(Entry<String, String> entry : effects.entrySet()) {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+ UtilText.parse(getMurk(), "[npc.speech("+entry.getKey()+")]")
										+ "</p>"
										+ entry.getValue());
							}
						}
					};
				}
				
			} else {
				return CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF.getResponse(responseTab, index);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_VAGINA_FINAL_TF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"如坠爱河",
						"向默克承认，你已经完全爱上了他的肉棒，乞求他来插入你！"
							+ getObedienceResponseDescription(25),
						true,
						false,
						getPlayerMurkMilkingStallSM(SexSlotMilkingStall.HUMPING,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS), 2),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_FIRST_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_MILKING_FIRST_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getMurk(), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
					}
					@Override
					public void postSexInitEffects() {
						Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_FIRST_SEX = new DialogueNode("结束", "默克用他粗大且气味浓重的鸡巴夺走了你的口穴童贞……", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().hasVagina()) {
				Main.game.getPlayer().clearFluidsStored(SexAreaOrifice.VAGINA);
				applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_FIRST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被榨取", "默克让你继续被机器榨取着，走开检查其他产奶工去了……", CAPTIVE_DAY_3_AFTER_SEX_MILKED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SEX_MILKED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SEX_MILKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"完全顺从",
						Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
							?"告诉默克就是个顺从的产奶用的母狗，正需要他赶紧插进来！"
							:"告诉默克你就是个低贱的挤奶用的屁穴骚货，你爱死他的肉棒了！"
								+ getObedienceResponseDescription(25),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getMurk(), SexSlotMilkingStall.HUMPING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								2,
								null,
								true),
						null,
						null,
						CAPTIVE_DAY_3_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SEX_MILKED_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
						applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
					}
					@Override
					public void postSexInitEffects() {
						if(Main.game.getPlayer().hasVagina() && !Main.game.isAnalContentEnabled()) {
							Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTER_SECOND_SEX = new DialogueNode("结束", "默克用他粗大且气味浓重的鸡巴给了你“第二次约会”，现在结束了……", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTER_SECOND_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("午餐时间", "默克让你继续被机器榨着，自己准备去吃午饭了……", CAPTIVE_DAY_3_LUNCH);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_LUNCH = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(Math.max(Main.game.getHourOfDay()+1, 13)*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进食",
						"照默克说的，把粥吃了……"
								+ getObedienceResponseDescription(5),
						CAPTIVE_DAY_3_LUNCH_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_EAT"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_LUNCH_END"));
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(5));
					}
				};
				
			} else if(index==2) { // Should be impossible...
				return new Response("拒绝", "你的服从太高，已经不会想拒绝吃下这份稀粥了！", null);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_LUNCH_END = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			GameCharacter milker = Util.randomItemFrom(getMilkers());
			milker.calculateGenericSexEffects(false, true, getMurk(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), GenericSexFlag.FORCE_CREAMPIE);
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
			getMurk().returnToHome();
			Main.game.getPlayer().applyFoodConsumed(15);
		}
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
			if(index==1) {
				return new Response("被榨取", "你享受着被榨取的感觉，等着默克回来……", CAPTIVE_DAY_3_AFTERNOON);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			spawnRat(false, true);
			getCharacters(false).get(0).setGenericName("帮派成员");
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return 60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"乞求肉棒",
						UtilText.parse(getCharacters(false), "乞求[npc.namePos]的鼠屌来插到你的体内。")
								+ getObedienceResponseDescription(10),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SEX", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(
								getCharacters(false).get(0), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharacters(false), "[npc.Name]觉得付的钱已经够本，于是跟你结束了做爱……");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SEX", getCharacters(false)));
			banishRats();
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
			if(index==1) {
				return new Response("被榨取", "你享受着被榨取的感觉，等着默克回来……", CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			spawnRat(false, true);
			getCharacters(false).get(0).setGenericName("帮派成员");
			getCharacters(false).get(0).addFetish(Fetish.FETISH_SADIST);
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(19*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"再次挨操",
						UtilText.parse(getCharacters(false), "[npc.Name]似乎并不管什么前戏，直接上来就是干……")
								+ getObedienceResponseDescription(10),
						true,
						false,
						getBasicSexManager(SexPosition.MILKING_STALL,
								Util.newHashMapOfValues(new Value<>(getCharacters(false).get(0), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_SECOND_SEX_START", getCharacters(false))) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getCharacters(false).get(0), Main.game.getPlayer(), Main.game.getPlayer().hasVagina()?PenisVagina.PENIS_FUCKING_START:PenisAnus.PENIS_FUCKING_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getCharacters(false), "[npc.Name]操你操够了，于是结束了这场性交……");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_AFTER_SECOND_SEX", getCharacters(false)));
			banishRats();
			getMurk().returnToHome();
			applyPlayerMilkingPumps(true, Util.newArrayListOfValues(InventorySlot.VAGINA));
		}
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
			if(index==1) {
				return new Response("被挤奶", "边享受被挤奶的感觉，边等默克回来……", CAPTIVE_DAY_3_AFTERNOON_END);
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
			applyPlayerMilkingPumps(false, Util.newArrayListOfValues(InventorySlot.VAGINA, InventorySlot.NIPPLE, InventorySlot.STOMACH, InventorySlot.PENIS));
			Main.game.getPlayer().addDirtySlot(InventorySlot.MOUTH);
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(Math.max(22, Main.game.getHourOfDay()+1)*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("屈服", "跪在默克面前，求着他干你。", CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT);
				
			} else if(index==2) {
				return new Response("逃跑", "你完全顺从的大脑已经不允许你产生一丝逃跑的想法……", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				boolean allFours = Main.game.getPlayer().isTaur() || !Main.game.getPlayer().hasLegs();
				return new ResponseSex(
						allFours
							?"展示自己"
							:"分开双腿",
						(allFours
							?"将自己的小穴展示给主人，告诉他你的真爱就是他的肉棒！"
							:"对着主人分开双腿，告诉他你的真爱就是他的肉棒！")
							+ getObedienceResponseDescription(25),
						true,
						false,
						getBasicSexManager(
								allFours
									?SexPosition.ALL_FOURS
									:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(getMurk(),
										allFours
											?SexSlotAllFours.HUMPING
											:(Main.game.getPlayer().isVisiblyPregnant()
												?SexSlotLyingDown.MISSIONARY
												:SexSlotLyingDown.MATING_PRESS))),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(),
										allFours
											?SexSlotAllFours.ALL_FOURS
											:SexSlotLyingDown.LYING_DOWN)),
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, Main.game.getPlayer().hasVagina()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS),
								0,
								null,
								false),
						null,
						null,
						CAPTIVE_DAY_3_END,
						UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_AFTERNOON_END_SUBMIT_SEX")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(getMurk(),
										Main.game.getPlayer(),
										Main.game.getPlayer().hasVagina()
											?PenisVagina.PENIS_FUCKING_START
											:PenisAnus.PENIS_FUCKING_START,
										false,
										true),
								allFours
									?null
									:new InitialSexActionInformation(getMurk(),
											Main.game.getPlayer(),
											TongueMouth.KISS_START,
											false,
											true));
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkMaster, true);
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(25));
					}
					@Override
					public void postSexInitEffects() {
						Main.sex.incrementNumberOfOrgasms(Main.game.getPlayer(), 1);
						if(Main.game.getPlayer().hasPenis()) {
							Main.game.getPlayer().applyOrgasmCumEffect();
							if(!allFours) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.CHEST);
							}
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_DAY_3_END = new DialogueNode("结束", "默克把你灌满浓精，爽够了……", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_DAY_3_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被锁住", "你又一次被锁链锁住了……", CAPTIVE_NIGHT);
			}
			return null;
		}
	};
	
	
	// --------- END OF DAY 3 --------- //
	
	
	
	
	// --------- NIGHT --------- //
	
	
	public static final DialogueNode CAPTIVE_NIGHT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
			for(GameCharacter milker : getMilkers()) {
				((RatWarrensCaptive)milker).applyMilkingEquipment(false, Util.newArrayListOfValues(InventorySlot.NIPPLE, InventorySlot.VAGINA));
			}
		}
		@Override
		public int getSecondsPassed() {
			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())==0) {
				return Main.game.getMinutesUntilTimeInMinutes(01*60)*60; // First night
			}
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			if(Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())==0
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription)) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "DAY_0_SLEEP"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT"));
			}
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_WARNING"));
			sb.append(incrementPlayerObedience(0));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				int stage = Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer());
				if(stage>=4) {
					if(Main.game.isBadEndsEnabled()) {
						return new Response("睡觉",
								"入睡……"
								+ "<br/>[style.boldBadEnd(坏结局：)]如果连续三晚都没能逃脱，你就会完全屈服于默克，接受成为他的产奶工的宿命！",
								CAPTIVE_NIGHT_SLEEP) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_BAD_END;
							}
						};
						
					} else {
						return new Response("睡觉", "入睡……", CAPTIVE_NIGHT_SLEEP_RESCUED);
					}
				}
				return new Response("睡觉", "入睡……", CAPTIVE_NIGHT_SLEEP);
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveWashed)) {
					return new Response("清洗", "你今晚已经给自己洗过身子了……", null);
				}
				return new Response("清洗", "用细小的水流给自己清洗身体。", CAPTIVE_NIGHT) {
					@Override
					public int getSecondsPassed() {
						return 10*60;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveWashed, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveInitialNightDescription, false);
						StringBuilder sb = new StringBuilder();
						GameCharacter player = Main.game.getPlayer();
						
						player.setHealth(player.getAttributeValue(Attribute.HEALTH_MAXIMUM));
						player.setMana(player.getAttributeValue(Attribute.MANA_MAXIMUM));
						
						sb.append(player.washAllOrifices(false));
						player.calculateStatusEffects(0);
						player.cleanAllDirtySlots(true);
						player.cleanAllClothing(false, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_WASH", getCharacters(false)));
						Main.game.getTextStartStringBuilder().append(sb.toString());
					}
				};
				
			} else if(index==3) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveCalledOut)) {
					return new Response("大喊", "你就算再叫默克也不会再注意你。你必须等到明天晚上……", null);
				}
				return new Response("大叫", "向默克大叫……", CAPTIVE_CALL_OUT);
				
			} else if(index==4) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("挣脱锁链", "你的服从太高，甚至不愿破坏默克的财产！", null);
				}
				if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)>=PhysiqueLevel.THREE_POWERFUL.getMinimumValue()) {
					return new Response("挣脱锁链", "用纯粹的击打来打破锁链……", CAPTIVE_BROKEN_FREE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("挣脱锁链", "你不够强壮，无法打破锁链……<br/>[style.italicsMinorBad(需要至少"+PhysiqueLevel.THREE_POWERFUL.getMinimumValue()+Attribute.MAJOR_PHYSIQUE.getName()+"……)]", null);
				
			} else if(index==5) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("挣脱锁链(法术)", "你的服从太高，甚至不愿破坏默克的财产！", null);
				}
				if(Main.game.getPlayer().hasSpell(Spell.FIREBALL)
						|| Main.game.getPlayer().hasSpell(Spell.ICE_SHARD)
						|| Main.game.getPlayer().hasSpell(Spell.SLAM)) {
					return new Response("挣脱锁链(法术)",
							"用一些时间将奥术之力传导到位，尝试着突破奴隶项圈上的附魔，然后施放法术打破颈手枷的枷锁。",
							CAPTIVE_BROKEN_FREE) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_BREAK_LOCK_SPELL", getCharacters(false)));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
						}
					};
				}
				return new Response("挣脱枷锁",
						"你不知道有什么合适的咒语来解除禁锢……"
								+ "<br/>[style.italicsMinorBad(需要学会以下法术其中之一: "+Spell.FIREBALL.getName()+"；"+Spell.ICE_SHARD.getName()+"；"+Spell.SLAM.getName()+"。)]",
						null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_NIGHT_SLEEP = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			if(Main.game.getHourOfDay()>0) {
				return Main.game.getMinutesUntilTimeInMinutes(Main.game.getHourOfDay()+1*60)*60;
			}
			return Main.game.getMinutesUntilTimeInMinutes(01*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_SLEEP", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("醒来", "次日早晨，你醒来了……", getSleepNode());
			}
			return null;
		}
	};
	
	
	
	// --------- MISCELLANEOUS DIALOGUES --------- //
	
	
	public static final DialogueNode BAD_END = new DialogueNode("[style.boldBadEnd(坏结局：默克的产奶工)]", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.setBadEnd("Murk's Milker");
			
			Main.game.getPlayer().setName(new NameTriplet("淫荡的产奶工"));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "BAD_END", getMilkers());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("结局……", "[style.italicsBadEnd(你的旅程就此结束，预言之线也被斩断。是恢复已保存的游戏来重塑命运的编织，还是继续留在你所创造的注定失败的世界中？)]", null);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH"));
			
			if(Main.game.getPlayer().isVaginaEggLayer()) {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END_EGGS"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_END"));
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isVaginaEggLayer()) {
					return new Response("保住那些蛋！", "保住你的蛋，别让老鼠抢走了！", CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						}
					};
				} else {
					return new Response("休息", "历经折磨后，你需要休息一会儿……", CAPTIVE_GIVE_BIRTH_FINISHED) {
						@Override
						public void effects() {
							Main.game.getNpc(Silence.class).returnToHome();
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 24*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_PROTECT_THE_EGGS", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("休息", "历经折磨后，你需要休息一会儿……", CAPTIVE_GIVE_BIRTH_FINISHED);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_GIVE_BIRTH_FINISHED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getNpc(Shadow.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_GIVE_BIRTH_INITIAL_FINISHED", getCharacters(false)));
			sb.append(CAPTIVE_NIGHT.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_NIGHT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_LAY_EGGS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_LAY_EGGS"));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("休息", "历经折磨后，你需要休息一会儿……", CAPTIVE_LAY_EGGS_FINISHED) {
					@Override
					public void effects() {
						Main.game.getNpc(Silence.class).returnToHome();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_LAY_EGGS_FINISHED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			Main.game.getNpc(Silence.class).returnToHome();
			Main.game.getNpc(Shadow.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_LAY_EGGS_INITIAL_FINISHED", getCharacters(false)));
			sb.append(CAPTIVE_NIGHT.getContent());
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_NIGHT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("袭击",
						"你现在已经摆脱了锁链，终于可以攻击默克了！",
						(NPC) getMurk(),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[murk.speech(你等着瞧！)]默克大吼一声，准备战斗。"))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("屈服",
						"你听从默克的指令，向他屈服……"
								+getObedienceResponseDescription(10),
						CAPTIVE_BROKEN_FREE_SUBMIT) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(incrementPlayerObedience(10));
					}
					@Override
					public boolean isSexHighlight() {
						return true;
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE_SUBMIT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE_SUBMIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_BROKEN_FREE_AFTER_SEX, "CAPTIVE_BROKEN_FREE_SUBMIT_HANDJOB", "CAPTIVE_BROKEN_FREE_SUBMIT_ORAL", "CAPTIVE_BROKEN_FREE_SUBMIT_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_BROKEN_FREE_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().returnToHome();
		}
		@Override
		public String getDescription() {
			return "默克跟你做完了……";
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_BROKEN_FREE_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("睡觉", "你为了在性事中取悦默克，已经精疲力竭，很快就陷入了沉睡……", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("睡觉",
						"你为了在性事中取悦默克，已经精疲力竭，很快就陷入了沉睡……",
						getSleepNode());
			}
			return null;
		}
	};

	public static final DialogueNode CAPTIVE_CALL_OUT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getMurk().setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(isPlayerObeyingOrders(false)) {
					return new Response("假装呛住", "你的服从太高，甚至不能对默克说谎！", null);
				}
				return new Response("假装呛住", "假装自己呛住了，想骗[murk.name]把你放开……", CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_CHOKE", getCharacters(false)));
					}
				};
				
			} else if(index==2) {
				if(!Main.game.getPlayer().hasTraitActivated(Perk.CONVINCING_REQUESTS) && !isPlayerObeyingOrders(false)) {
					return new Response("诱惑",
							UtilText.parse(getMurk(),
									"你的魅惑并不那么有力，无法欺骗[npc.name]摘下你的项圈……"
									+ "<br/>[style.italicsMinorBad(需要开启“"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"”特性。)]"),
							null);
				}
				return new Response("诱惑",
						isPlayerObeyingOrders(false)
							?"告诉默克你想跟他做爱想得睡不着觉……"
							:"告诉默克你想跟他做爱，借此骗他摘下你的项圈……"
								+ "<br/>[style.italicsMinorGood(由于开启了“"+Perk.CONVINCING_REQUESTS.getName(Main.game.getPlayer())+"”特性而解锁。)]",
						CAPTIVE_CALL_OUT_RELEASED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_CALL_OUT_SEDUCE", getCharacters(false)));
						getMurk().returnToHome();
						Main.game.getPlayer().setLocation(getMurk(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_CALL_OUT_RELEASED = new DialogueNode("", "", true) {
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
				if(isPlayerObeyingOrders(false)) {
					return new Response("战斗", "你的服从太高，甚至不想跟默克战斗！", null);
				}
				return new ResponseCombat("战斗",
						UtilText.parse(getMurk(), "你已经摆脱了项圈的控制，终于可以对[npc.name]大打出手了！"),
						null,
						(NPC) getMurk(),
						Util.newArrayListOfValues(getMurk()),
						Util.newHashMapOfValues(new Value<>(getMurk(), "[npc.speech(你，你等着瞧！)][npc.name]慌张地喊起来，挥着球棒冲了上来。"))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_RELEASED_AFTER_SEX, "CAPTIVE_CALL_OUT_RELEASED_HANDJOB", "CAPTIVE_CALL_OUT_RELEASED_ORAL", "CAPTIVE_CALL_OUT_RELEASED_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX = new DialogueNode("结束", "默克和你做完了……", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被锁住", "让默克把你锁回颈手枷上……", CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP);
				
			} else if(index==2) {
				return new Response("主动陪床", "主动提出跟默克一起睡，这可能会给你逃走的机会……", CAPTIVE_RELEASED_OFFER_COMPANY);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_AFTER_SEX_LOCKED_UP", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("睡觉", "入睡……", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("睡觉", "入睡……", getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("溜走", "利用这次机会溜走并尝试逃离……", CAPTIVE_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_SLIP_AWAY", getCharacters(false)));
						restoreInventories();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensCaptiveAttemptingEscape, true);
					}
				};
				
			} else if(index==2) {
				return new Response("留下", "和默克一起留下直到你被锁回挤奶台……", CAPTIVE_RELEASED_OFFER_COMPANY_STAY);
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY_STAY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 4*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_STAY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(true,
						CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX,
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_HANDJOB",
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_ORAL",
						"CAPTIVE_RELEASED_OFFER_COMPANY_STAY_SEX");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_RELEASED_OFFER_COMPANY_STAY_AFTER_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("睡觉", "入睡……", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("睡觉", "入睡……", getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPE_FIGHT_VICTORY = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_VICTORY", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("隧道", "转身穿过隧道并逃离鼠窟。", CAPTIVE_ESCAPING) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_VICTORY_ESCAPING", getCharacters(false)));
						restoreInventories();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPE_FIGHT_DEFEAT = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_ESCAPE_FIGHT_DEFEAT", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return getPlayerOwnerEscapeSexResponse(false, CAPTIVE_AFTER_DEFEAT_SEX, "DEFEAT_SEX_HANDJOB", "DEFEAT_SEX_ORAL", "DEFEAT_SEX_START");
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_AFTER_DEFEAT_SEX = new DialogueNode("结束", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_MILKING_ROOM);
			getMurk().returnToHome();
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getMurk(), "[npc.NameHasFull]和你做够了……");
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_AFTER_DEFEAT_SEX", getCharacters(false));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isBadEndsEnabled() && Main.game.getDialogueFlags().getMurkTfStage(Main.game.getPlayer())>=4) {
					return new Response("睡觉", "战斗和随后的性爱让你精疲力竭，你很快就陷入了沉睡……", CAPTIVE_NIGHT_SLEEP_RESCUED);
				}
				return new Response("睡觉",
						"战斗和随后的性爱让你精疲力竭，你很快就陷入了沉睡……",
						getSleepNode());
			}
			return null;
		}
	};
	
	public static final DialogueNode CAPTIVE_NIGHT_SLEEP_RESCUED = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 3*60*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "CAPTIVE_NIGHT_SLEEP_RESCUED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CAPTIVE_ESCAPING.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CAPTIVE_ESCAPING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Shadow.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Silence.class).setLocation(Main.game.getPlayer(), false);
		}
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
				return new Response("离开", "让影和默带你离开鼠窟。", ESCAPING) {
					@Override
					public void effects() {
						RatWarrensCaptiveDialogue.restoreInventories();
						Main.game.getPlayer().setCaptive(false);
						Main.game.getPlayer().setObedience(0);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.ratWarrensSilenceIntroduced, true);
						Main.game.getPlayer().setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
						Main.game.getNpc(Silence.class).setLocation(WorldType.RAT_WARRENS, PlaceType.RAT_WARRENS_ENTRANCE);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ESCAPING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/ratWarrens/captive", "ESCAPING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("跟随", "和影，默一起跟随亚当斯警员前往最近的执法者岗哨。", RatWarrensDialogue.POST_CAPTIVITY_SWORD_RAID) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Shadow.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
						Main.game.getNpc(Silence.class).setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_RAT_WARREN);
					}
				};
			}
			return null;
		}
	};
	
}
