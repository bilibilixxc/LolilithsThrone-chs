package com.lilithsthrone.game.dialogue.story;

import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.LolificationProject.ModCommon.Conversions;
import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.LolificationProject.Overrides.CharCreationMenu;
import com.lilithsthrone.LolificationProject.Overrides.MenuOverrides;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.character.npc.misc.PrologueFemale;
import com.lilithsthrone.game.character.npc.misc.PrologueMale;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.quests.QuestType;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

import java.io.File;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CharacterCreation {

	public static final int TIME_TO_NAME = 120;
	public static final int TIME_TO_APPEARANCE = 60;
	public static final int TIME_TO_CLOTHING = 30;
	public static final int TIME_TO_BACKGROUND = 150;
	public static final int TIME_TO_JOB = 150;
	public static final int TIME_TO_SEX_EXPERIENCE = 150;
	public static final int TIME_TO_FINAL_CHECK = 150;

	public static SpellSchool getStartingTomeSpellSchool() {
		if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 1) {
			return SpellSchool.EARTH;
		} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
			return SpellSchool.AIR;
		} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 3) {
			return SpellSchool.WATER;
		}
		return SpellSchool.FIRE;
	}
	
	public static SpellSchool getStartingDemonstoneSpellSchool() {
		if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
			return SpellSchool.EARTH;
		} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 3) {
			return SpellSchool.AIR;
		} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 0) {
			return SpellSchool.WATER;
		}
		return SpellSchool.FIRE;
	}

	public static final DialogueNode CHARACTER_CREATION_START = new DialogueNode("免责声明", "", true) {

		@Override
		public String getContent() {
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("同意", "你保证自己到了查看色情内容的合法年龄，表示同意接触可能包含色情、血腥、暴力、成人或其他令人不适的内容或图片。", ALPHA_MESSAGE);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode ALPHA_MESSAGE = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "版本" + Main.VERSION_NUMBER + " |<b style='color:" + PresetColour.BASE_YELLOW_LIGHT.toWebHexString() + ";'>"+Main.VERSION_DESCRIPTION+"</b>";
		}
		
		@Override
		public String getContent() {
			return Main.getPatchNotes();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("开始", "继续进行角色创建。", CHOOSE_APPEARANCE){
					@Override
					public void effects() {
						Main.game.clearTextStartStringBuilder();
						Main.game.clearTextEndStringBuilder();
						Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
						Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
						Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
						Main.game.getPlayer().calculateStatusEffects(0);
						Main.game.getPlayer().setHealthPercentage(1);
						Main.game.getPlayer().setManaPercentage(1);
						getDressed();
						resetBodyAppearance();
						
						Main.game.setRenderAttributesSection(true);
						Main.game.getPlayer().setName(new NameTriplet("某人", "某人", "某人"));
						Main.game.getPlayer().setSurname("");
						BodyChanging.setTarget(Main.game.getPlayer());
					}
				};
				
			} else if (index == 2) {
				return new Response("开始(导入)", "从旧版本导入角色并开始新游戏。", IMPORT_CHOOSE) {
					@Override
					public void effects() {
						Main.game.getPlayerCell().resetInventory();
					}
				};
			}
			return null;
		};
	};

	public static void resetBodyAppearance() {
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_LIGHT), true);
		Main.game.getNpc(Lilaya.class).setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_BROWN), true);
		Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN), true);
		Main.game.getPlayer().setBreastShape(BreastShape.ROUND);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.TWO_AVERAGE.getValue());
		
		Main.game.getPlayer().setFacialHair(BodyHair.ZERO_NONE);
		resetFemininityAppearance();
		
		CharacterModificationUtils.resetCoveringsToBeApplied();
	}
	
	public static void resetFemininityAppearance() {
		switch(Main.game.getPlayer().getFemininity()) {
			case MASCULINE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPenisSize(PenisLength.TWO_AVERAGE.getMedianValue()+3);
                Main.game.getPlayer().setBreastSize(CupSize.FLAT);
				break;
			case MASCULINE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setAssHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				Main.game.getPlayer().setPenisSize(PenisLength.TWO_AVERAGE);
                if(Main.game.getPlayer().hasVagina()) {
                    Main.game.getPlayer().setBreastSize(CupSize.TRAINING_AA);
                } else {
                    Main.game.getPlayer().setBreastSize(CupSize.FLAT);
                }
				break;
			case ANDROGYNOUS:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.FOUR_NATURAL);
				if(Main.game.getPlayer().hasPenis()) {
					Main.game.getPlayer().setPenisSize(PenisLength.ONE_TINY);
				}
				if(Main.game.getPlayer().hasVagina()) {
					Main.game.getPlayer().setBreastSize(CupSize.A);
				}
				break;
			case FEMININE:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.TWO_MANICURED);
				Main.game.getPlayer().setPubicHair(BodyHair.THREE_TRIMMED);
				Main.game.getPlayer().setBreastSize(CupSize.C);
				break;
			case FEMININE_STRONG:
				Main.game.getPlayer().setUnderarmHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setAssHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setPubicHair(BodyHair.ZERO_NONE);
				Main.game.getPlayer().setBreastSize(CupSize.DD);
				break;
		}
	}
	
	public static void setGenderFemale() {
		Femininity fem = Femininity.FEMININE;
		switch(BodyChanging.getTarget().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case MASCULINE:
				fem = Femininity.FEMININE;
				break;
			case MASCULINE_STRONG:
				fem = Femininity.FEMININE_STRONG;
				break;
			default:
				break;
		}
		BodyChanging.getTarget().setBody(Gender.F_V_B_FEMALE, RacialBody.HUMAN, RaceStage.HUMAN, false);
		BodyChanging.getTarget().setFemininity(fem.getMedianFemininity());
		
		if(BodyChanging.getTarget().isPlayer()) {
			getDressed();
			CharacterCreation.resetBodyAppearance();
		}
	}
	
	public static void setGenderMale() {
		Femininity fem = Femininity.MASCULINE;
		switch(BodyChanging.getTarget().getFemininity()) {
			case ANDROGYNOUS:
				fem = Femininity.ANDROGYNOUS;
				break;
			case FEMININE:
				fem = Femininity.MASCULINE;
				break;
			case FEMININE_STRONG:
				fem = Femininity.MASCULINE_STRONG;
				break;
			default:
				break;
		}
		BodyChanging.getTarget().setBody(Gender.M_P_MALE, RacialBody.HUMAN, RaceStage.HUMAN, false);
		BodyChanging.getTarget().setFemininity(fem.getMedianFemininity());

		if(BodyChanging.getTarget().isPlayer()) {
			getDressed();
			CharacterCreation.resetBodyAppearance();
		}
	}
	
	public static void equipPiercings() {
		Colour colour1 = PresetColour.CLOTHING_BLACK_STEEL;
		Colour colour2 = PresetColour.CLOTHING_STEEL;

		if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE_STRONG) {
			colour1 = PresetColour.CLOTHING_PLATINUM;
			colour2 = PresetColour.CLOTHING_GOLD;
		} else if(Main.game.getPlayer().isFeminine()) {
			colour1 = PresetColour.CLOTHING_SILVER;
			colour2 = PresetColour.CLOTHING_SILVER;
		}
		
		Map<InventorySlot, AbstractClothing> pendingPiercings = new HashMap<>();
		
		// Ear piercings:
		if(Main.game.getPlayer().isPiercedEar()) {
			if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE_STRONG) {
				pendingPiercings.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_chain_dangle", colour1, false));
			} else if(Main.game.getPlayer().getFemininity()==Femininity.FEMININE) {
				pendingPiercings.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", colour1, false));
			} else {
				pendingPiercings.put(InventorySlot.PIERCING_EAR, Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ball_studs", colour1, false));
			}
		}
		
		// Lip piercings:
		if(Main.game.getPlayer().isPiercedLip()) {
			pendingPiercings.put(InventorySlot.PIERCING_LIP, Main.game.getItemGen().generateClothing("innoxia_piercing_lip_double_ring", colour1, false));
		}
		
		// Navel piercings:
		if(Main.game.getPlayer().isPiercedNavel()) {
			if(Main.game.getPlayer().isFeminine()) {
				pendingPiercings.put(InventorySlot.PIERCING_STOMACH, Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", colour2, false));
			} else {
				pendingPiercings.put(InventorySlot.PIERCING_STOMACH, Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", colour2, false));
			}
		}

		// Nipples piercings:
		if(Main.game.getPlayer().isPiercedNipple()) {
			pendingPiercings.put(InventorySlot.PIERCING_NIPPLE, Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell_pair", colour2, false));
		}

		// Nose piercings:
		if(Main.game.getPlayer().isPiercedNose()) {
			if(Main.game.getPlayer().isFeminine()) {
				pendingPiercings.put(InventorySlot.PIERCING_NOSE, Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ring", colour1, false));
			} else {
				pendingPiercings.put(InventorySlot.PIERCING_NOSE, Main.game.getItemGen().generateClothing("innoxia_piercing_nose_ball_stud", colour1, false));
			}
		}

		// Penis piercings:
		if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().isPiercedPenis()) {
			pendingPiercings.put(InventorySlot.PIERCING_PENIS, Main.game.getItemGen().generateClothing("innoxia_piercing_penis_ring", colour2, false));
		}

		// Tongue piercings:
		if(Main.game.getPlayer().isPiercedTongue()) {
			pendingPiercings.put(InventorySlot.PIERCING_TONGUE, Main.game.getItemGen().generateClothing("innoxia_piercing_basic_barbell", colour1, false));
		}

		// Vagina piercings:
		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isPiercedVagina()) {
			pendingPiercings.put(InventorySlot.PIERCING_VAGINA, Main.game.getItemGen().generateClothing("innoxia_piercing_ringed_barbell", colour2, false));
		}
		
		for(InventorySlot slot : InventorySlot.getPiercingSlots()) {
			AbstractClothing clothingCurrentlyInSlot = Main.game.getPlayer().getClothingInSlot(slot);
			
			if(pendingPiercings.get(slot)!=null){
				if(clothingCurrentlyInSlot==null || clothingCurrentlyInSlot.getClothingType()!=pendingPiercings.get(slot).getClothingType()) {
					Main.game.getPlayer().equipClothingFromNowhere(pendingPiercings.get(slot), slot, true, Main.game.getPlayer());
				}
				
			} else if(clothingCurrentlyInSlot!=null){
				Main.game.getPlayer().unequipClothingIntoVoid(slot, true, Main.game.getPlayer());
			}
		}
	}
	
	public static void getDressed() {
		getDressed(Main.game.getPlayer(), true);
	}
	
	public static void getDressed(GameCharacter character, boolean spawnClothingOnFloor) {
		character.resetInventory(false);
		Main.game.getPlayerCell().resetInventory();
		
		equipPiercings();
		
		switch(character.getFemininity()) {
			case MASCULINE_STRONG:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_RED, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_suit_jacket", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_GOLD, false), true, character);

				break;
				
			case MASCULINE:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_mens_smart_shoes", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_SILVER, false), true, character);

				break;
				
			case ANDROGYNOUS:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, character);
				if(character.getBreastRawSizeValue()!=0) {
					character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false), true, character);
				} else {
					Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing("innoxia_chest_croptop_bra", PresetColour.CLOTHING_WHITE, false));
				}
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_socks", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_RED, false), true, character);
				
				break;
				
			case FEMININE:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_panties", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_skater_dress", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_trainer_socks", PresetColour.CLOTHING_WHITE, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_PINK_LIGHT, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_SILVER, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER, false), true, character);

				break;
				
			case FEMININE_STRONG:
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_thong", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_chest_plunge_bra", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_slip_dress", PresetColour.CLOTHING_RED_BURGUNDY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED_BURGUNDY, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_WOMENS_WATCH, PresetColour.CLOTHING_BLACK, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_finger_ring", PresetColour.CLOTHING_GOLD, false), true, character);
				character.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_heart_necklace", PresetColour.CLOTHING_GOLD, false), true, character);

				break;
			default:
				break;
		}
		if(spawnClothingOnFloor) {
			spawnClothingInArea();
		}
		
		if(character.isPlayer()
				&& ((character.getName(false).equals("James") || character.getName(false).equals("Jane") || character.getName(false).equals("Tracy")) && character.getSurname().equals("Bond"))) {
			character.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon(WeaponType.getWeaponTypeFromId("innoxia_western_kkp_western_kkp")));
		}
	}
	
	private static void generateClothingOnFloor(String clothingType, Colour colour) {
		generateClothingOnFloor(ClothingType.getClothingTypeFromId(clothingType), colour, null, null);
	}
	
	private static void generateClothingOnFloor(AbstractClothingType clothingType, Colour colour) {
		generateClothingOnFloor(clothingType, colour, null, null);
	}
	
	private static void generateClothingOnFloor(AbstractClothingType clothingType, Colour colour, Colour colour2, Colour colour3) {
		for(AbstractClothing clothing : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
			if(clothing.getClothingType()==clothingType) {
				return;
			}
		}
		Main.game.getPlayerCell().getInventory().addClothing(Main.game.getItemGen().generateClothing(clothingType, colour, colour2, colour3, false));
	}
	
	private static void spawnClothingInArea() {
//		switch(Main.game.getPlayer().getFemininity()) {
//			case MASCULINE:
//			case MASCULINE_STRONG:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_foot_trainers"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLUE_GREY, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_work_boots", PresetColour.CLOTHING_TAN);
				generateClothingOnFloor("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_sock_socks", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_groin_boxers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_eye_aviators", PresetColour.CLOTHING_BLACK_STEEL);
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);
				generateClothingOnFloor("innoxia_hand_gloves", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_head_cap", PresetColour.CLOTHING_BLUE);
				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torsoOver_ribbed_jumper", PresetColour.CLOTHING_GREY);
				generateClothingOnFloor("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_neck_tie", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_torsoOver_suit_jacket", PresetColour.CLOTHING_BLACK);
//				break;
				
//			case ANDROGYNOUS:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_foot_trainers"), PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_PURPLE_DARK, PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_heels", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_groin_thong", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_groin_briefs", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_RED);

				generateClothingOnFloor("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_leg_cargo_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_yoga_pants", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_GREY);

				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_RED);
				
				generateClothingOnFloor("innoxia_head_cap", PresetColour.CLOTHING_BLUE);
				
				generateClothingOnFloor("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_torso_tshirt", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_blouse", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_cami_straps", PresetColour.CLOTHING_GREEN);
				
				generateClothingOnFloor("innoxia_torsoOver_hoodie", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_finger_ring", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_neck_heart_necklace", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_wrist_bangle", PresetColour.CLOTHING_SILVER);
				generateClothingOnFloor("innoxia_ankle_anklet", PresetColour.CLOTHING_SILVER);
				
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);
//				break;
				
//			case FEMININE:
//			case FEMININE_STRONG:
				generateClothingOnFloor("bloom_wasp609_rainCoat_rain_coat", PresetColour.CLOTHING_PURPLE_DARK);
				generateClothingOnFloor("innoxia_torsoOver_womens_winter_coat", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_sock_stockings", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.HIPS_SUSPENDER_BELT, PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_groin_panties", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_groin_thong", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_groin_lacy_panties", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_groin_vstring", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_chest_lacy_plunge_bra", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_chest_fullcup_bra", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_sock_pantyhose", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_sock_kneehigh_socks", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_sock_thighhigh_socks", PresetColour.CLOTHING_WHITE);

				generateClothingOnFloor("innoxia_eye_aviators", PresetColour.CLOTHING_ROSE_GOLD);
				generateClothingOnFloor("innoxia_eye_glasses", PresetColour.CLOTHING_BLACK_STEEL);

				generateClothingOnFloor("innoxia_foot_ankle_boots", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_foot_low_top_skater_shoes", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_foot_thigh_high_boots", PresetColour.CLOTHING_TAN);
				generateClothingOnFloor("innoxia_foot_stiletto_heels", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_foot_heels", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor("innoxia_hand_elbow_length_gloves", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_head_headband", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_head_headband_bow"), PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK);

				generateClothingOnFloor("innoxia_leg_hotpants", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_leg_mini_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_skirt", PresetColour.CLOTHING_PINK);
				generateClothingOnFloor("innoxia_leg_yoga_pants", PresetColour.CLOTHING_PINK_LIGHT);
				generateClothingOnFloor("innoxia_leg_pencil_skirt", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_leg_tight_jeans", PresetColour.CLOTHING_BLUE_NAVY);
				generateClothingOnFloor("innoxia_leg_jeans", PresetColour.CLOTHING_BLUE_GREY);
				generateClothingOnFloor("innoxia_leg_distressed_jeans", PresetColour.CLOTHING_BLUE_GREY);
				
				generateClothingOnFloor("innoxia_neck_scarf", PresetColour.CLOTHING_RED);
				
				generateClothingOnFloor("innoxia_stomach_underbust_corset", PresetColour.CLOTHING_BLACK);

				generateClothingOnFloor(ClothingType.getClothingTypeFromId("innoxia_torso_feminine_short_sleeve_shirt"), PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_blouse", PresetColour.CLOTHING_BLUE_LIGHT);
				generateClothingOnFloor("innoxia_torso_cami_straps", PresetColour.CLOTHING_GREEN);
				generateClothingOnFloor("innoxia_torso_long_sleeve_dress", PresetColour.CLOTHING_BLACK);
				generateClothingOnFloor("innoxia_torso_short_croptop", PresetColour.CLOTHING_PINK);
				generateClothingOnFloor("innoxia_torso_virgin_killer_sweater", PresetColour.CLOTHING_WHITE);
				generateClothingOnFloor("innoxia_torso_slip_dress", PresetColour.CLOTHING_RED);
				generateClothingOnFloor("innoxia_torso_skater_dress", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_torsoOver_open_front_cardigan", PresetColour.CLOTHING_BLACK);
				
				generateClothingOnFloor("innoxia_wrist_bangle", PresetColour.CLOTHING_GOLD);
				generateClothingOnFloor("innoxia_ankle_anklet", PresetColour.CLOTHING_GOLD);
//				break;
//		}
	}
	
	public static final DialogueNode CHOOSE_APPEARANCE = new DialogueNode("一场夜生活", "", true) {
		
		@Override
		public String getHeaderContent() {
			Main.game.setRenderAttributesSection(true);
			if (Library.timeTestAppearance) {
				Library.timeTestAppearance = false;
				Logger.logTime("Time Until Choose Appearance", System.nanoTime() - Library.T_T);
			}
			return "<p>"
						+ "你搭着出租车来到了大英博物馆门口。"
						+ "你此次来到伦敦，就是为了参加莉莉姨妈新艺术展的开幕晚会。<br>不过——你已经迟到将近五分钟了。"
							+ "你急急忙忙把小费塞给司机，冲出车门，祈祷着她的演讲还没有开始。"
					+ "</p>"
					+ "<p>"
						+ "你奔入博物馆的大门，四周的路灯明暗交替，让附近染上一片暗淡的橙黄色光芒。"
						+ "只花了一小会儿，你就来到了博物馆的正厅门前。但让人糟心的是，你看到那里排起了一条队伍，人们都排着队等待入场。"
						+ "你别无选择，只能排进队伍等待入场。等待期间，你短暂地瞥了一眼博物馆外面立着的现代风格巨大玻璃窗，看见自己在玻璃中的模糊倒影……"
					+ "</p>"
					+ "<br/>"
					
					+ CharacterModificationUtils.getStartDateDiv()
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getGenderChoiceDiv()
						
						+ CharacterModificationUtils.getFemininityChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "你将被看作是<span style='color:"+Main.game.getPlayer().getGender().getColour().toWebHexString()+";'>"
								+ Main.game.getPlayer().getGender().getName()+"</span>。<br/>"
							+ "<i>你可以在选项菜单中修改所有性别称呼</i>"
						+ "</div>"

						+ CharacterModificationUtils.getBirthdayChoiceDiv()
						
						+ CharacterModificationUtils.getOrientationChoiceDiv()

						+ CharacterModificationUtils.getPersonalityChoiceDiv(false)
						
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "等待轮到你，祈祷晚会还没开始。", CHOOSE_NAME) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_NAME;
					}
					@Override
					public void effects() {
//						getDressed();
					}
				};
			} else if (index == 2) {
				return new Response("Start (Import)", "Import a character from a previous version to use on game start.", IMPORT_CHOOSE) {
					@Override
					public void effects() {
						Main.game.getPlayerCell().resetInventory();
					}
				};
			}
//			else if (index == 0) {
//				return new Response("Back", "Return to the main menu.", OptionsDialogue.MENU);
//			}
			return null;
		}
	};
	
	public static final DialogueNode CHOOSE_NAME = new DialogueNode("一场夜生活", "", true) {

		boolean unsuitableName = false, unsuitableSurname = false;
		
		@Override
		public String getHeaderContent() {
			return "<p>"
						+ "[npcMale.speech("+(Main.game.getPlayer().isFeminine()?"小姐":"先生")+"，)]"
						+ "门卫的叫声打断了你的思考——排在你前面的人都已经入场了，"
						+ "[npcMale.speech(你有邀请函吗？)]"
					+ "</p>"
					+ "<p>"
						+ "你回过神来，把注意力从玻璃窗上移开，微笑着走了过去。"
						+ "[pc.speech(是的，我把它揣在这儿了……呃……稍等……)]"
					+ "</p>"
					+ "<p>"
						+ "你伸进"+(Main.game.getPlayer().isFeminine()?"手提包":"口袋")+"里摸索起来，发现里面并没有邀请函，心跳顿时加速。"
						+ "<br>[pc.speech(不不不！我一定是把邀请函落在出租车里了！)]"
					+ "</p>"
					+ "<p>"
						+ "[npcMale.speech(好吧，别担心，)]"
						+ "门卫附和着，"
						+ "[npcMale.speech(如果你说一下名字，我可以帮你看看你在不在参会名单上。)]"
					+ "</p>"
					+ "<p>"
						+ "你舒了一口气，告诉门卫你的名字是……"
					+ "</p>"
					+"<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<div style='position:relative; display:inline-block; padding-bottom:0; margin 0 auto; vertical-align:middle; width:100%; text-align:center;'>"
							+ "<i>"
								+ "你的名字可以设置三种差分：男性名，中性名和女性名。"
								+ "你的名字会与你身体的女性化程度自动关联。"
							+ "</i>"
							+ "<br/>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:25px; line-height:25px; width:100px;'>名：</p>"
							+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
									+ "<input type='text' id='nameMasculineInput' style=' color:"+PresetColour.MASCULINE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getMasculine())+ "'>"
									
							+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
								+ "<input type='text' id='nameAndrogynousInput' style=' color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getAndrogynous())+ "'>"
								
							+ "</form style='display:inline-block; padding:0; margin:0; text-align:center;'>"
								+ "<input type='text' id='nameFeminineInput' style=' color:"+PresetColour.FEMININE.toWebHexString()+";' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getNameTriplet().getFeminine())+ "'>"
							
							+ "<br/>"
							+ "<p style='display:inline-block; padding:0; margin:0; height:25px; line-height:25px; width:100px;'>姓：</p>"
							+ "<form style='display:inline-block; padding:0; margin:0; text-align:center;'><input type='text' id='surnameInput' value='"+ UtilText.parseForHTMLDisplay(Main.game.getPlayer().getSurname())+ "'></form>"
						+ "</div>"
						+ "<br/>"
						+ "<i>你的姓名长度必须在 2 到 32 个字符之间。不能使用方括号或句号。(姓氏可留空。)</i>"
						+ (unsuitableName ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>无效的名字</b></p>" : "")
						+ (unsuitableSurname ? "<p style='text-align:center;padding-top:0;'><b style=' color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>无效的姓氏</b></p>" : "")
					+ "</div>"
					
					+ "<p id='hiddenFieldName' style='display:none;'></p>"
					+ "<p id='hiddenFieldSurname' style='display:none;'></p>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("继续", "使用这个姓名进入角色创建界面。"){
					@Override
					public int getSecondsPassed() {
						if (unsuitableName || unsuitableSurname)  {
							return super.getSecondsPassed();
						}
						return TIME_TO_APPEARANCE;
					}
					@Override
					public void effects() {
						List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
						List<String> namesList = new ArrayList<>();
						for(String s : fieldsList) {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32
										|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+")) {
									unsuitableName = true;
								} else {
									unsuitableName = false;
									namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							}
						}
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 32
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+"))) {
								unsuitableSurname = true;
							} else {
								unsuitableSurname = false;
							}
						}
						if (unsuitableName || unsuitableSurname)  {
							Main.game.setContent(new Response("" ,"", CHOOSE_NAME));
							
						} else {
							Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());

							Main.game.getPlayerCell().resetInventory();
							Main.game.getPlayer().moveToAdjacentMatchingCellType(false, PlaceType.MUSEUM_LOBBY);
							Main.game.setContent(new Response("" ,"", CHOOSE_ADVANCED_APPEARANCE));
							getDressed();
						}
					}
				};
				
			} else if (index == 2) {
				return new Response("随机名", "根据性别随机生成名字。", CHOOSE_NAME){
					@Override
					public void effects() {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldSurname').innerHTML=document.getElementById('surnameInput').value;");
						if(Main.mainController.getWebEngine().getDocument()!=null) {
							if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length()>=1
									&& (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().length() > 16
											|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent().matches("[^\\[\\]\\.]+")))
								unsuitableSurname = true;
							else {
								unsuitableSurname = false;
							}
						}
						if(!unsuitableSurname) {
							Main.game.getPlayer().setSurname(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldSurname").getTextContent());
						}

						Main.game.getPlayer().setName(Name.getRandomTriplet(Subspecies.HUMAN));
					}
				};
				
			} else if (index == 3) {
				return new Response("随机姓", "随机生成姓氏。", CHOOSE_NAME){
					@Override
					public void effects() {
						List<String> fieldsList = Util.newArrayListOfValues("nameMasculineInput", "nameAndrogynousInput", "nameFeminineInput");
						List<String> namesList = new ArrayList<>();
						for(String s : fieldsList) {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('"+s+"').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 1
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 16
										|| !Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().matches("[^\\[\\]\\.]+"))
									unsuitableName = true;
								else {
									unsuitableName = false;
									namesList.add(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
								}
							}
						}
						if(!unsuitableName) {
							Main.game.getPlayer().setName(new NameTriplet(namesList.get(0), namesList.get(1), namesList.get(2)));
						}
						
						Main.game.getPlayer().setSurname(Name.getSurname(Main.game.getPlayer()));
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "回到性别选择界面。", CHOOSE_APPEARANCE) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_NAME;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE = new DialogueNode("博物馆内", "", true) {
		
		@Override
		public String getHeaderContent() {
			Main.game.setPlayer(Conversions.applyAgeEffects(Main.game.getPlayer()));
			return "<p>"
						+ "[pc.speech(我叫"+(Main.game.getPlayer().getSurname().length()!=0?"[pc.surname]，[pc.name]·[pc.surname]":"[pc.name]")+"。)]"
						+ "你焦躁地回答道，那名男子闻言开始浏览起手上的参会名单来。"
					+ "</p>"
					+ "<p>"
						+ "看到他的手指划过你的名字时，你才终于松了口气。他微笑着让到一旁，做出了请进的手势。"
						+ "[npcMale.speech(祝您有个愉快的夜晚，"+(Main.game.getPlayer().getSurname().length()!=0
								?"[pc.surname]"+(Main.game.getPlayer().isFeminine()?"小姐":"先生")
								:(Main.game.getPlayer().isFeminine()?"小姐":"先生"))+"。)]"
					+ "</p>"
					+ "<p>"
						+ "你匆匆回了一个礼，就急忙穿过门廊，来到了博物馆巨大的中央大厅。"
						+ "夹层的阳台上悬挂着巨大的横幅，横幅上配着硕大的黑体字标语“阿卡德帝国展览：开幕晚会”。"
						+ "在大礼堂的另一端有一个大舞台，观众们大都已经聚拢到了那边。不过看起来主持还没有上台，这让你不禁松了一口气。"
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(呼……好歹是赶上了……)]"
					+ "</p>"
					+ "<p>"
						+ "莉莉似乎也跟你一样迟到了，看来她的开场演讲也得往后推推了。于是你决定在附近找块镜子，趁着还没开场再整理整理着装……"
					+ "</p>"
					+ "<br/>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>外表</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>"
					+ "<br/>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>你可以调整下面的选项来修改外观。</i>"
					+ "</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
			/*if (index == 1) {
				return new Response("Continue",
						"Your clothes are a little messy after rushing here. Tidy yourself up before proceeding to the main stage.",
						InventoryDialogue.INVENTORY_MENU) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_CLOTHING;
					}
					@Override
					public void effects() {
						equipPiercings();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
					}
				};
				
			} else if (index == 2) {
				return new Response("Core", "Enter the customisation menu for all of your body's core aspects.", CHOOSE_ADVANCED_APPEARANCE_CORE);
				
			} else if (index == 3) {
				return new Response("Face", "Enter the customisation menu for aspects related to your face.", CHOOSE_ADVANCED_APPEARANCE_FACE);
				
			} else if (index == 4) {
				return new Response("Hair", "Enter the customisation menu for your hair.", CHOOSE_ADVANCED_APPEARANCE_HAIR);
				
			} else if (index == 5) {
				return new Response("Breasts", "Enter the customisation menu for your breasts.", CHOOSE_ADVANCED_APPEARANCE_BREASTS);
				
			} else if (index == 6) {
				return new Response("Ass & Hips", "Enter the customisation menu for aspects related to your ass, hips, and anus.", CHOOSE_ADVANCED_APPEARANCE_ASS);
				
			} else if (index == 7) {
				return new Response((Main.game.getPlayer().hasPenis()?"Penis":"Vagina"), "Enter the customisation menu for aspects related to your "+(Main.game.getPlayer().hasPenis()?"penis":"vagina")+".", CHOOSE_ADVANCED_APPEARANCE_GENITALS);
				
			}  else if (index == 8) {
				return new Response("Makeup", "Enter the customisation menu for makeup.", CHOOSE_ADVANCED_APPEARANCE_COSMETICS);
				
			} else if (index == 9) {
				return new Response("Piercings", "Enter the customisation menu for body piercings.", CHOOSE_ADVANCED_APPEARANCE_PIERCINGS);
				
			} else if (index == 10) {
				return new Response("Tattoos", "Enter the customisation menu for tattoos.", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);
				
			} else if (index == 11) {
				return new Response("Extra hair", "Enter the customisation menu for facial, pubic, and body hair.", CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR);
				
			} else if (index == 0) {
				return new Response("Back", "Confirm your choices and return to the content preferences menu.", CHOOSE_NAME) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_APPEARANCE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE, false);
					}
				};
				
			} else {
				return null;
			}*/
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_CORE = new DialogueNode("核心身体设置", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getHeightChoiceDiv(true)
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.HUMAN, BodyCoveringType.HUMAN, "皮肤颜色", "覆盖你躯体的皮肤颜色。", true, false, false)
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getBodySizeChoiceDiv()
						
						+ CharacterModificationUtils.getMuscleChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
							+ "你的肌肉量和体型会影响你的外观:<br/>"
							+ "<b style='color:"+Main.game.getPlayer().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false))+"</b>"
						+ "</div>"
					
					+"</div>";
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_FACE = new DialogueNode("面容", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"

					+ CharacterModificationUtils.getLipSizeDiv()
					
					+ CharacterModificationUtils.getLipPuffynessDiv()

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Main.game.getPlayer().getEyeType().getRace(), BodyCoveringType.EYE_HUMAN, "虹膜颜色", "你虹膜的颜色", true, false, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回外观菜单确定你的选择。", CHOOSE_ADVANCED_APPEARANCE);
				
			}
			else return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_HAIR = new DialogueNode("发型", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"

					+ CharacterModificationUtils.getKatesDivHairLengths(false, "头发长度", "选择你头发的长度。")
					
					+ CharacterModificationUtils.getKatesDivHairStyles(false, "发型", "选择你的发型，短发可能无法选择某些特殊发型。")

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Main.game.getPlayer().getHairType().getRace(), BodyCoveringType.HAIR_HUMAN, "发色", "你头发的颜色。", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_BREASTS = new DialogueNode("胸部外观", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getBreastSizeDiv()
					
					+ CharacterModificationUtils.getBreastShapeDiv()
					
					+ CharacterModificationUtils.getNippleSizeDiv()
					
					+ CharacterModificationUtils.getAreolaeSizeDiv()
					
					+ CharacterModificationUtils.getNipplePuffynessDiv()
					
					+ CharacterModificationUtils.getSelfTransformLactationDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_ASS = new DialogueNode("臀部外观", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"
						
					+ CharacterModificationUtils.getAssSizeDiv()
					
					+ CharacterModificationUtils.getHipSizeDiv()
					
					+ CharacterModificationUtils.getBleachedAnusDiv();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_GENITALS = new DialogueNode("生殖器外观", "", true) {
		
		@Override
		public String getLabel() {
			if(Main.game.getPlayer().hasPenis()) {
				return "阴茎外观";
			} else {
				return "阴部外观";
			}
		}
		
		@Override
		public String getHeaderContent() {
			if(Main.game.getPlayer().hasPenis()) {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>所有选项都会影响游戏的后续发展。</i>"
						+ "</div>"
							
							+ CharacterModificationUtils.getPenisSizeDiv()
							
							+ CharacterModificationUtils.getTesticleSizeDiv()
							
							+ CharacterModificationUtils.getSelfTransformCumProductionDiv();
				
			} else {
				return "<div class='container-full-width' style='text-align:center;'>"
							+ "<i>所有选项都会影响游戏的后续发展。</i>"
						+ "</div>"
	
							+ CharacterModificationUtils.getVaginaCapacityDiv()
							
							+ CharacterModificationUtils.getLabiaSizeDiv()
							
							+ CharacterModificationUtils.getClitorisSizeDiv();
				
			}
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_PIERCINGS = new DialogueNode("穿孔", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"
						
					+CharacterModificationUtils.getKatesDivPiercings(true);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_TATTOOS = new DialogueNode("纹身", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>在游戏后期，你可以选择附魔纹身和发光纹身，不过目前你暂时只能选择普通纹身。</i>"
					+ "</div>"
					+CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getLabel() {
			return "增加纹身："+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName());
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("应用", "你需要选择纹身类型，添加一些文字或计数指示才能形成完整的纹身！", null);
					
				} else {
					return new Response("应用", 
							UtilText.parse(BodyChanging.getTarget(), "添加这个纹身。"), CHOOSE_ADVANCED_APPEARANCE_TATTOOS) {
						@Override
						public void effects() {
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("保存/加载", "保存/加载纹身预设。", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("返回", "取消纹身并回到选择菜单。", CHOOSE_ADVANCED_APPEARANCE_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_COSMETICS = new DialogueNode("化妆品", "", true) {
		
		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>所有选项都会影响游戏的后续发展。</i>"
					+ "</div>"
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "腮红", "腮红(也叫胭脂)被用来粉饰脸颊，以显得更加年轻或凸显颧骨。", true, false)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "口红", "口红被用来为嘴唇提供色彩、质地或保护。", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "眼线", "眼线用于眼廓周围，有助于修饰眼型或突出不同的特征。", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "眼影", "眼影用来让使用者的眼睛更加凸显迷人。", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "指甲油", "指甲油用于给[pc.hands]添加色彩或提供保护。", true, false)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚趾甲油", "脚趾甲油用于给[pc.feet]添加色彩或提供保护。", true, false);
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static final DialogueNode CHOOSE_ADVANCED_APPEARANCE_BODY_HAIR = new DialogueNode("体毛", "", true) {
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
												+ "<i>所有选项都会影响游戏的后续发展。</i>"
											+ "</div>");
			
			if(Main.game.isPubicHairEnabled() || Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false, Race.NONE, Main.game.getPlayer().getBodyHairCoveringType(), "体毛", "覆盖头部以外任何地方的毛发。", false, false, false));
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"体毛", "覆盖头部以外任何地方的毛发。", "所有体毛附加选项都将不可用。你不会看见任何关于体毛的内容。"));
				
				return UtilText.nodeContentSB.toString();
			}
			
			if(Main.game.isFacialHairEnabled()) {
				if (Main.game.isFemaleFacialHairEnabled()) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "胡须", "面部的体毛。"));
				} else {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "胡须", "面部的体毛。女性化角色无法长出面部毛发。"));
				}
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"胡须", "面部的体毛。女性化角色无法长出面部毛发。", "胡须选项目前不可用，你将不会看见任何胡须相关的内容。"));
			}
			
			if(Main.game.isPubicHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "阴毛", "你下体附近的体毛，在你的生殖器和腹股沟附近。"));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"阴毛", "你下体附近的体毛，在你的生殖器和腹股沟附近。", "阴毛选项目前不可用，你将不会看见任何阴毛相关内容。"));
			}
			
			if(Main.game.isBodyHairEnabled()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getKatesDivUnderarmHair(false, "腋毛", "腋窝处的体毛。"));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"腋毛", "腋窝处的体毛。", "腋毛选项目前不可用，你将不会看见任何腋毛相关的内容。"));
			}
			
			if(Main.game.isAssHairEnabled()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "肛毛", "肛门附近的体毛。"));
				
			} else {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivGenericBodyHairDisabled(
						"肛毛", "你肛门附近的体毛。", "肛毛选项目前不可用，你将不会看见任何肛毛相关的内容。"));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return CharCreationMenu.getBodyOptions(index);
		}
	};
	
	public static String getCheckingClothingDescription() {
		StringBuilder sb = new StringBuilder();

		File dir = new File("res/");
		if(!dir.exists()) {
			sb.append("<p style='text-align:center;'>"
						+ "[style.italicsBad(游戏无法读取 'res' 文件夹，因此将缺失重要的服装贴图！在继续游戏之前，请查阅 README.txt 中的 'MISSING FOLDERS' 部分！)]"
					+ "</p>");
		}
		
		sb.append("<div class='container-full-width' style='background:transparent;'>"
					+ "<p>"
						+ "主舞台上还是没动静，看来离开场还有几分钟时间，你决定再打扮一下自己。"
						+ "毕竟，这是莉莉的盛大晚宴，你想让她看看你为了打扮花费了多少功夫。"
					+ "</p>"
					+ "<p>"
						+ "你走得更近，更清楚地凝视着镜中的自己，你注意到你今晚看起来很"+(Main.game.getPlayer().isFeminine()?"火辣":"英俊")+"……"
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(我为什么突然这么饥渴？)]"
					+ "</p>"
					+ "<div class='container-full-width' style='text-align:center;'>"
						+ "<i>选择你穿什么去博物馆。</i><br/>"
						+ "<i>你需要穿上鞋子，还有遮盖生殖器和胸部的衣物，才能继续进行。</i>"
					+ "</div>"
				+ "</div>");
		
		return sb.toString();
	}
	
	public static void moveNPCIntoPlayerTile() {
		if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasVagina())) {
			Main.game.getNpc(PrologueMale.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			
		} else {
			Main.game.getNpc(PrologueFemale.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
			Main.game.getNpc(PrologueFemale.class).addStatusEffect(StatusEffect.PROMISCUITY_PILL_PROLOGUE, 60*60*24*3); // 3 days
		}
	}
	
	public static void moveNPCOutOfPlayerTile() {
		Main.game.getNpc(PrologueMale.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
		Main.game.getNpc(PrologueFemale.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
	}
	
	public static boolean femalePrologueNPC() {
		return Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC || (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && Main.game.getPlayer().hasPenis());
	}
	
	public static final DialogueNode CHOOSE_BACKGROUND = new DialogueNode("博物馆中", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "打理好外表后，你转身离开镜子，向主舞台走去。"
						+ "你每踏出一步，便莫名其妙地觉得自己愈加兴奋，走到一半，已经没入了熙熙攘攘的人群中间，"
							+(Main.game.getPlayer().hasPenis()
									?"你拼尽全力避免自己在大庭广众下搭起帐篷。"
									:"你感觉小穴已经由于欲望被润湿了。")
					+ "</p>"
					+ "<p>"
						+ "你立刻躲藏到附近的立柱后面，你摇摇头，试图摆脱那些开始渗入你脑海的淫念。"
						+ "你背靠着冰冷的石柱，深呼吸着，这时一个声音忽然打断了你的思绪，");
			
			if(!femalePrologueNPC()) {
				UtilText.nodeContentSB.append("[prologueMale.speech(你也是从人群里逃出来休息的吗！？)]"
						+ "</p>"
						+ "<p>"
							+ "你转过身，只见一位身材高挑的英俊男性，比你大不过一两岁，脸上挂着你从未见过的迷人微笑。"
							+ "在你还没有反应过来的时候，你便对着他充满男人味、肌肉分明的身体上下打量了一遍又一遍，要不是尽力憋住，不然可能就呻吟了出来。"
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(要专心，[pc.name]，专心！)]你一边想着，一边摆出自然的表情，对着面前的陌生人也微笑了一下。"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(其实，)]你说道，[pc.speech(我才刚到。我还以为自己迟到了，没想到还没开始。)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(啊，你大概错过了刚才的通知，)]他回复道，[prologueMale.speech(开幕演讲被推迟了半个小时。"
								+ "我本想在人群里逛一逛，但我也不是历史学家，他们的对话都太无聊了……)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(哈哈，)]"
							+ "你笑了笑，但还是竭尽全力没有去想他赤身裸体的样子，"
							+ "[pc.speech(我可<i>太明白</i>你的意思了。我的姨妈就是马上要去做开幕演讲的那位，我每次见到她博物馆的朋友都完全搭上话。"
									+ "当然，除了那个亚瑟。他跟我们年纪更近些，而且为人随和，很愿意跟别人聊天。)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueMale.speech(哈！你认识亚瑟吗？我就是他邀请来的。我们是老相识了，)]"
							+ "那个男人看上去很开心，他的微笑让你心跳加速。"
							+ "[prologueMale.speech(对了，我叫[prologueMale.name]，很高兴见到你……？)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(我也是，)]你握了握他伸出的手，脑海里却不断闪过他的大手会如何强硬有力。[pc.speech(我叫[pc.Name]。)]"
						+ "</p>"
						+ "<p>"
							+ "你和[prologueMale.name]继续聊着天，等待演讲开始。"
							+ "没过多久，话题便转向了工作，你得知他是航空公司的一名飞行员，工作地点在市郊的机场。"
							+ "接着他问及了你的工作，你便跟他又聊了一会儿……"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append("[prologueFemale.speech(你也是从人群里逃出来休息的吗！？)]"
						+ "</p>"
						+ "<p>"
							+ "你转过身，只见一位美貌的女性，年龄与你相仿，脸上挂着你从未见过的动人微笑。"
							+ "在你还没有反应过来的时候，你便对着她充满女人味、前凸后翘的身体上下打量了一遍又一遍，要不是尽力憋住，不然可能就发出了饥渴的低吼。"
						+ "</p>"
						+ "<p>"
							+ "[pc.thought(要专心，[pc.name]，专心！)]你一边想着，一边摆出自然的表情，对着面前的陌生人也微笑了一下。"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(其实，)]你说道，[pc.speech(我才刚到。我还以为自己迟到了，没想到还没开始。)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(啊，你大概错过了刚才的通知，)]她回复道，[prologueFemale.speech(开幕演讲被推迟了半个小时。"
								+ "我本想在人群里逛一逛，但我也不是历史学家，他们的对话都太无聊了……)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(哈哈，)]"
							+ "你笑了笑，但还是竭尽全力没有去想她赤身裸体的样子，"
							+ "[pc.speech(我可<i>太明白</i>你的意思了。我的姨妈就是马上要去做开幕演讲的那位，我每次见到她博物馆的朋友都完全搭上话。"
									+ "当然，除了那个亚瑟。他跟我们年纪更近些，而且为人随和，很愿意跟别人聊天。)]"
						+ "</p>"
						+ "<p>"
							+ "[prologueFemale.speech(哈！你认识亚瑟吗？我就是他邀请来的。我们是老相识了，)]"
							+ "那个女人看上去很开心，她的微笑让你心跳加速。"
							+ "[prologueFemale.speech(对了，我叫[prologueFemale.Name]，很高兴见到你……？)]"
						+ "</p>"
						+ "<p>"
							+ "[pc.speech(我也是，)]你回应道，握住她伸出的手，同时尽量不去想她的皮肤多么柔软和娇嫩。[pc.speech(我是[pc.Name]。)]"
						+ "</p>"
						+ "<p>"
							+ "你和[prologueFemale.Name]继续聊着天，等待演讲开始。"
							+ "没过多久，话题转向了工作，你发现她正在接受医生培训，在这座城市的大学学习。"
							+ "接着她问及了你的工作，你便跟他又聊了一会儿……"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("返回", "返回衣物选择。") {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_BACKGROUND;
					}
					@Override
					public void effects() {
						moveNPCOutOfPlayerTile();
						InventoryDialogue.setBuyback(false);
						InventoryDialogue.setInventoryNPC(null);
						InventoryDialogue.setNPCInventoryInteraction(InventoryInteraction.CHARACTER_CREATION);
						Main.game.setContent(new Response("", "", InventoryDialogue.INVENTORY_MENU));
					}
				};
				
			} else if (index == 1) {
				return new Response("选择职业", "进入职业选择界面。", BACKGROUND_SELECTION_MENU) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_JOB;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACKGROUND_SELECTION_MENU = new DialogueNode("博物馆中", "-", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("<div class='container-full-width'>"
									+ "<h6 style='text-align:center'>职业选择</h6>"
									+ "<p style='text-align:center'>点击你期望的职业旁的图标，然后点击“继续”。</p>"
								+ "</div>");

			UtilText.nodeContentSB.append("<div class='container-full-width'>");
			for(Occupation history : Occupation.getAvailableHistories(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width'>"
							+"<div class='container-full-width' style='margin:0;padding:0;'>"
								+ "<h6 style='color:"+history.getAssociatedPerk().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(history.getName(Main.game.getPlayer()))+"</h6>"
							+ "</div>"
							+"<div class='container-full-width' style='margin:0 8px; width: calc(10% - 16px);'>"
								+ "<div id='OCCUPATION_" + history + "' class='fetish-icon full"
									+ (Main.game.getPlayer().getHistory()==history
										? " owned' style='border:2px solid " + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
										: " unlocked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>")
									+ "<div class='fetish-icon-content'>"+history.getAssociatedPerk().getSVGString(Main.game.getPlayer())+"</div>"
								+ "</div>"
							+ "</div>"
							+"<div class='container-full-width' style='margin:0 8px; width: calc(90% - 16px);'>"
								+ "<p>"
									+ history.getDescription(Main.game.getPlayer())
								+ "</p>"
							+ "</div>"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "回到上一界面。", CHOOSE_BACKGROUND) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_JOB;
					}
				};
				
			} else if (index == 1) {
				if(Main.game.getPlayer().getHistory().getAssociatedPerk()==null) {
					return new Response("继续", "你需要先选择一个职业！", null);
				} else {
					return new Response("继续", femalePrologueNPC()?"告诉[prologueFemale.name]你现在靠什么谋生。":"告诉[prologueMale.name]你现在靠什么谋生。", CHOOSE_SEX_EXPERIENCE) {
						@Override
						public int getSecondsPassed() {
							return TIME_TO_SEX_EXPERIENCE;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().getVirginityLossMap().replaceAll((k, v) ->
								(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.GYNEPHILIC
									|| (Main.game.getPlayer().getSexualOrientation()==SexualOrientation.AMBIPHILIC && !Main.game.getPlayer().isFeminine()))
									?new SimpleEntry<>("", "your girlfriend")
									:new SimpleEntry<>("", "your boyfriend"));
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNode CHOOSE_SEX_EXPERIENCE = new DialogueNode("开始", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
            switch (Main.game.getPlayer().getHistory()) {
                case ATHLETE:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是一名职业运动员，)]"
                                    + "你解释道，"
                                    + "[pc.speech(我大多数事件都在训练和参加比赛。)]");
                    break;
                case BUTLER:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我在城里一个很有影响力的家族做管家，)]"
                                    + "你解释道，"
                                    + "[pc.speech(不过今天请了个假，才来参加莉莉的演讲。)]");
                    break;
                case CHEF:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(这边拐个弯就到的餐厅，我在那里做主厨，)]"
                                    + "你解释道，"
                                    + "[pc.speech(不过今天请了个假，才来参加莉莉的演讲。)]");
                    break;
                case CONSTRUCTION_WORKER:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是个建筑工人，)]"
                                    + "你解释道，"
                                    + "[pc.speech(我正在负责市郊一个大工程。)]");
                    break;
                case MAID:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我在城里一个很有影响力的家族做女仆长，)]"
                                    + "你解释道，"
                                    + "[pc.speech(不过今天请了个假，才来参加莉莉的演讲。)]");
                    break;
                case MUSICIAN:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是城市交响乐团的一员，)]"
                                    + "你解释道，"
                                    + "[pc.speech(我也提供私人音乐辅导。)]");
                    break;
                case BAND:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I'm a member of a band,)]"
                                    + "你解释道，"
                                    + " [pc.speech(and I also enjoy art.)]");
                    break;
                case OFFICE_WORKER:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我在市中心的一家公司坐办公室，)]"
                                    + "你解释道，"
                                    + "[pc.speech(通常都是做管理和文书工作。)]");
                    break;
                case OJISAN:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I work in one of the corporate offices in the centre of the city,)]"
                                    + "你解释道，"
                                    + " [pc.speech(I am here looking for something.)]");
                    break;
                case STUDENT_COUNCIL:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I am in the student council of my school,)]"
                                    + "你解释道，"
                                    + " [pc.speech(mostly doing admin and paper work.)]");
                    break;
                case SOLDIER:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我在军队服役，)]"
                                    + "你解释道，"
                                    + "[pc.speech(这周剩下的时间我都在休假，然后就得回军营了。)]");
                    break;
                case JROTC:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I'm in the JROTC,)]"
                                    + "你解释道，"
                                    + " [pc.speech(I'm on vacation for the rest of the week, and then it's back to school and training for me.)]");
                    break;
                case GSTUDENT:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I'm a student at the city grade school,)]"
                                    + "你解释道，"
                                    + " [pc.speech(I am here to look at all the cool art.)]");
                    break;
                case MSTUDENT:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I'm a student at the city middle school,)]"
                                    + "你解释道，"
                                    + " [pc.speech(I really enjoy art so I am here to take a look at all the cool pieces.)]");
                    break;
                case HSTUDENT:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I'm a student at the city high school,)]"
                                    + " you explain,"
                                    + " [pc.speech(I am an art enthusist and may plan on going to art school once I graduate.)]");
                    break;
                case STUDENT:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是大学的学生，)]"
                                    + " you explain,"
                                    + "[pc.speech(不过还没决定好专业。)]");
                    break;
                case TEACHER:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是当地中学的一名老师，)]"
                                    + " you explain,"
                                    + "[pc.speech(但由于是半学期，我这周可以休息一下。)]");
                    break;
                case UNEMPLOYED:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我还在找工作，)]"
                                    + " you explain,"
                                    + "[pc.speech(我其实想过要不要申请这座博物馆的工作。)]");
                    break;
                case NO_JOB_YET:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I've never had a job,)]"
                                    + " you explain,");
                    break;
                case AVERAGE_JOE:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(I do whatever it takes to pay the bills,)]"
                                    + " you explain,");
                    break;
                case WRITER:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是一名职业作家，)]" // I write erotic novels……
                                    + " you explain,"
                                    + "[pc.speech(我正在等待出版商给我上一部小说的回复。)]");
                    break;
                case ARISTOCRAT:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我不需要担心工作，)]"
                                    + " you explain,"
                                    + "[pc.speech(我的家族资产就足以提供我需要的收入，所以我整天都在旅游和享受生活。)]");
                    break;
                case TOURIST:
                    UtilText.nodeContentSB.append(
                            "[pc.speech(我是来这里度假的，)]"
                                    + " you explain,"
                                    + "[pc.speech(既然到了英国，我就不想再考虑工作了。)]");
                    break;
                default:
                    break;
            }
			UtilText.nodeContentSB.append("</p>");
			
			if(femalePrologueNPC()) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "你们两个继续聊着天，先是聊工作，接着又是些琐事；聊着聊着，你意识到自己越来越兴奋了。"
							+ "更奇怪的是，你注意到[prologueFemale.namePos]的脸颊也开始潮红，还在时不时地趁着你没注意的时候饥渴地偷瞄你的身体。"
						+ "</p>"
						+ "<p>"
							+ "她忽然开始公开谈论她的性生活，这终于证明了她和你同样兴奋。"
							+ "一开始你对她奔放的态度有些吃惊，但随着她越说越多，你意识到自己跟这位陌生人谈论起性的时候，也没有那样尴尬了。"
						+ "</p>"
						+ "<p>"
							+ "因而，跟[prologueFemale.name]谈了没有十分钟，你便开始事无巨细地向她讲述起自己的性经历……"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "你们两个继续聊着天，先是聊工作，接着又是些琐事；聊着聊着，你意识到自己越来越兴奋了。"
							+ "更奇怪的是，你注意到[prologueMale.namePos]的脸颊也开始潮红，还在时不时地趁着你没注意的时候饥渴地偷瞄你的身体。"
						+ "</p>"
						+ "<p>"
							+ "他忽然开始公开谈论他的性生活，这终于证明了他和你同样兴奋。"
							+ "一开始你对他奔放的态度有些吃惊，但随着他越说越多，你意识到自己跟这位陌生人谈论起性的时候，也没有那样尴尬了。"
						+ "</p>"
						+ "<p>"
							+ "因而，跟[prologueMale.Name]谈了没有十分钟，你便开始事无巨细地向她讲述起自己的性经历……"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='text-align:center;'>"
							+ "<i>性经历越丰富，堕落就越高。(你可以在左侧的角色栏中看到自己的堕落值，包括其他属性)"
							+ "<br/>"
							+ "对性癖选择“<span style='color:"+FetishDesire.FOUR_LOVE.getColour().toWebHexString()+";'>"+FetishDesire.FOUR_LOVE.getName()+"</span>”"
								+ "等级会使你在游戏开始时获得该性癖，而其他等级只会决定你对于那项性癖的态度。</i>"
						+ "</div>"
						+CharacterModificationUtils.getSexualExperienceDiv()
						+CharacterModificationUtils.getFetishChoiceDiv());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "对于性经历满意后，就可以前往角色创建的最后阶段了。", FINAL_CHECK) {
					@Override
					public int getSecondsPassed() {
						return TIME_TO_FINAL_CHECK;
					}
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasPenis()) {
							for(SexAreaOrifice ot : SexAreaOrifice.values()) {
								SexType st = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, ot);
								Main.game.getPlayer().resetVirginityLoss(st);
								st = new SexType(SexParticipantType.SELF, SexAreaPenetration.PENIS, ot);
								Main.game.getPlayer().resetVirginityLoss(st);
							}
							Main.game.getPlayer().setPenisVirgin(true);
							
						}
						if(!Main.game.getPlayer().hasVagina()) {
							for(SexAreaPenetration pt : SexAreaPenetration.values()) {
								SexType st = new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pt);
								Main.game.getPlayer().resetVirginityLoss(st);
								st = new SexType(SexParticipantType.SELF, SexAreaOrifice.VAGINA, pt);
								Main.game.getPlayer().resetVirginityLoss(st);
							}
							Main.game.getPlayer().setVaginaVirgin(true);
						}
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回背景选择。", BACKGROUND_SELECTION_MENU) {
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_SEX_EXPERIENCE;
					}
				};
				
			} else {
				return null;
			}
		}
	};

	private static void applyGameStart() {
		CharacterModificationUtils.resetImpossibeSexExperience();
		
		Main.getProperties().addRaceDiscovered(Subspecies.HUMAN);
		Main.game.getPlayer().setGenderIdentity(Main.game.getPlayer().getGender());
		
		Main.game.getNpc(Lilaya.class).setSkinCovering(new Covering(BodyCoveringType.HUMAN, Main.game.getPlayer().getCovering(BodyCoveringType.HUMAN).getPrimaryColour()), true);

		Main.game.getNpc(Lilaya.class).setBirthday(LocalDateTime.of(Main.game.getPlayer().getBirthday().getYear()-20, Main.game.getNpc(Lilaya.class).getBirthMonth(), Main.game.getNpc(Lilaya.class).getDayOfBirth(), 12, 0));
		
		Main.game.clearTextStartStringBuilder();
		Main.game.clearTextEndStringBuilder();

		Main.game.setWeatherInSeconds(Weather.MAGIC_STORM, 5*60*60);
		
		Main.game.getPlayerCell().resetInventory();

		Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("innoxia_quest_clothing_keys"), false);
	}

	private static void applySkipPrologueStart(boolean imported) {
		Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Lilaya.class));
		Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Rose.class));
		
		Main.getProperties().addRaceDiscovered(Main.game.getNpc(Lilaya.class).getSubspecies());
		Main.getProperties().addRaceDiscovered(Main.game.getNpc(Rose.class).getSubspecies());
		
		Main.game.applyStartingDateChange();
		if(!imported) {
			Main.game.getPlayer().setAgeAppearanceDifference(-Game.TIME_SKIP_YEARS);
		}

		Main.game.getPlayer().addSpecialPerk(Perk.SPECIAL_PLAYER);
		
		moveNPCOutOfPlayerTile();
	}
	
	public static final DialogueNode FINAL_CHECK = new DialogueNode("开始", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='text-align:center;'>"
					+ "<i>对于外表满意后，点击“开始游戏”来开始！<br/>"
					+ "[style.colourGood(这就是角色创建的最后阶段，对于所有选择满意后就可以继续了！)]</i>"
				+ "</div>"
				+ "<br/>"
				+ "<div class='container-full-width'>"
					+ "<h5 style='text-align:center;'>最终外表</h5>"
					+ Main.game.getPlayer().getBodyDescription()
				+ "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("开始游戏", "使用这个角色从游戏的最开头开始，在博物馆中寻找亚瑟。", PrologueDialogue.INTRO){
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("跳过序章", "开始游戏并跳过序章。<br/><br/><i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>初次游玩不推荐！</i>"){
					@Override
					public int getSecondsPassed() {
						return 60*60;
					}
					@Override
					public void effects() {
						
						Main.game.setRenderMap(true);
						
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));
						
						Main.game.getPlayer().incrementMoney(5000);

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						Spell startingSpell = Spell.FIREBALL;
						switch(getStartingTomeSpellSchool()) {
							case AIR:
								startingSpell = Spell.POISON_VAPOURS;
								break;
							case EARTH:
								startingSpell = Spell.SLAM;
								break;
							case FIRE:
							case ARCANE:
								startingSpell = Spell.FIREBALL;
								break;
							case WATER:
								startingSpell = Spell.ICE_SHARD;
								break;
						}
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(startingSpell));
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						applyGameStart();
						applySkipPrologueStart(false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);

						Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
						Main.game.getPlayer().setLustNoText(Main.game.getPlayer().getRestingLust());
						
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回背景选择。", CHOOSE_SEX_EXPERIENCE){
					@Override
					public int getSecondsPassed() {
						return -TIME_TO_FINAL_CHECK;
					}
					@Override
					public void effects() {
						// Remove attribute gain sentences in the start game screen:
						Main.game.clearTextEndStringBuilder();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	
	private static StringBuilder importSB;
	public static final DialogueNode IMPORT_CHOOSE = new DialogueNode("导入", "", true) {
		
		@Override
		public String getContent(){
			importSB = new StringBuilder();

			importSB.append("<p style='text-align:center;'>"
					+ "这些角色是从'data/characters'文件夹中读取的。"
					+ "如果你想导入一个过去版本的角色，请依照以下步骤：<br/><br/>"
					+ "<b>1.</b>打开旧版游戏，导出你之前的角色(菜单=>选项=>导出)。<br/>"
					+ "<b>2.</b>复制导出的.xml文件(在老版本的<i>data/characters</i>文件夹)。<br/>"
					+ "<b>3.</b>黏贴进入当前版本的<i>data/characters</i>文件夹。<br/>"
					+ "<b>4.</b>点击“刷新”，你之前的角色文件将会出现在列表中！<br/><br/>"
//					+ "(If it doesn't work, please let me know as a comment on my blog, and I'll get it fixed ASAP!)"
					+ "</p>"
					+ "<p>"
					+ "<table align='center'>"
					+ "<tr>"
					+ "<th></th>"
					+ "<th>名称</th>"
					+ "<th></th>"
					+ "</tr>");
			
			int i=1;
			for(File f : Main.getCharactersForImport()){
				importSB.append(getImportRow(i, f.getName()));
				i++;
			}

			importSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");

			return importSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("刷新", "刷新此页面。", IMPORT_CHOOSE);
				
			} else if (index == 0) {
				return new Response("Back", "Return to main menu.", MenuOverrides.MENU);
				
			} else {
				return null;
			}
		}
	};
	private static String getImportRow(int i, String name) {
		String baseName = Util.getFileName(name);
		String identifier = Util.getFileIdentifier(name);
		
		return "<tr>"
				+ "<td>"
					+ i+"."
				+ "</td>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='IMPORT_CHARACTER_" + identifier + "' style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>读取</div>"
				+ "</td>"
				+ "</tr>";
	}

	private static boolean resetImportedCharacter = false;

	public static final DialogueNode START_GAME_WITH_IMPORT = new DialogueNode("开始游戏", "", true) {
		
		@Override
		public String getLabel() {
			return "导入的角色";
		}
		
		@Override
		public String getContent() {
			return "<p>"
						+ "<b>TODO:</b>今后我会尝试对导入角色支持全套的角色创建流程！"
					+ "</p>"
					+ "<br/>"
					+"<details>"
						+ "<summary class='quest-title' style='color:" + QuestType.MAIN.getColour().toWebHexString() + ";'>导入日志</summary>"
						+ Main.game.getCharacterUtils().getCharacterImportLog()
					+ "</details>"
					+ "<div class='container-full-width'>"
						+ "<h5 style='text-align:center;'>外观</h5>"
						+ Main.game.getPlayer().getBodyDescription()
					+ "</div>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("开始", "使用这个角色从游戏的最开头开始。", INTRO_2_FROM_IMPORT){
					@Override
					public void effects() {
						if(resetImportedCharacter){
							resetPlayerCharacter();
						}
						Main.game.getPlayer().resetAllQuests();
						Main.game.getPlayer().getCharactersEncountered().clear();
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						applyGameStart();
					}
				};
				
			} else if (index == 2) {
				return new ResponseEffectsOnly("跳过序章", "开始游戏并跳过序章。<br/><br/><i style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>初次游玩不推荐！</i>"){
					@Override
					public void effects() {
						Main.game.setRenderMap(true);
						if(resetImportedCharacter){
							resetPlayerCharacter();
						}
						Main.game.getPlayer().incrementMoney(5000);

						Main.game.getPlayer().resetAllQuests();
						Main.game.getPlayer().getCharactersEncountered().clear();
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.MAIN));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_A_LILAYAS_TESTS));

						DamageType damageType = DamageType.FIRE;
						switch(CharacterCreation.getStartingDemonstoneSpellSchool()) {
							case AIR:
								damageType = DamageType.POISON;
								break;
							case EARTH:
								damageType = DamageType.PHYSICAL;
								break;
							case ARCANE:
							case FIRE:
								damageType = DamageType.FIRE;
								break;
							case WATER:
								damageType = DamageType.ICE;
								break;
						}
						if(Main.game.getPlayer().getMainWeapon(0)==null) {
							Main.game.getPlayer().equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType));
						} else {
							Main.game.getPlayer().addWeapon(Main.game.getItemGen().generateWeapon("innoxia_crystal_rare", damageType), false);
						}
						
						AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.FIREBALL));
						if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 1) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.SLAM));
						} else if(Main.game.getPlayer().getBirthMonth().getValue() % 4 == 2) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.POISON_VAPOURS));
						} else if(Main.game.getPlayer().getBirthMonth().getValue()  % 4 == 3) {
							spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
						}
						Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
						
						applyGameStart();
						applySkipPrologueStart(true);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER);
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
					}
				};

			} else if (index == 5) {
				return new ResponseEffectsOnly(resetImportedCharacter
						?"重置角色：<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>开启</span>"
						:"重置角色：<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>关闭</span>",
						"重置经验和火币为0，并清空物品栏，除了装备的衣物和武器。" +
								"法术和法术天赋同样也会被去除。"){
					@Override
					public void effects(){
						resetImportedCharacter = !resetImportedCharacter;
					}
				};


			}
			// Throws error when going back and then resuming
//			else if (index == 0) {
//				return new Response("Back", "Return to new game screen.", OptionsDialogue.MENU);
//			}
			else {
				return null;
			}
		}
	};

	private static void resetPlayerCharacter(){
		PlayerCharacter player = Main.game.getPlayer();
		player.clearNonEquippedInventory(true);
		player.setEssenceCount(0);
		player.incrementExperience(player.getExperienceNeededForNextLevel(player.getLevel()), false);
		player.setLevel(1);
		player.resetSpells();
		player.resetPerksMap(false);
	}

	public static final DialogueNode INTRO_2_FROM_IMPORT = new DialogueNode("博物馆中", "", true) {

		@Override
		public int getSecondsPassed() {
			return 60*10;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("misc/prologue", "INTRO_2");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return PrologueDialogue.INTRO_2.getResponse(responseTab, index);
		}
	};
	
}
