package com.lilithsthrone.game.character.npc.submission;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.submission.TunnelImpsDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.12
 * @author Innoxia
 */
public class ImpAttacker extends NPC {

	public ImpAttacker() {
		this(Subspecies.IMP, Gender.getGenderFromUserPreferences(false, false), false);
	}
	
	public ImpAttacker(boolean isImported) {
		this(Subspecies.IMP, Gender.F_V_B_FEMALE, isImported);
	}
	
	public ImpAttacker(AbstractSubspecies subspecies, Gender gender) {
		this(subspecies, gender, false);
	}
	
	public ImpAttacker(AbstractSubspecies subspecies, Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, subspecies, RaceStage.GREATER,
				new CharacterInventory(false, 10), WorldType.SUBMISSION, PlaceType.SUBMISSION_TUNNELS, false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// Set random level from 5 to 8:
			setLevel(5 + Util.random.nextInt(4));
			
			setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"小恶魔，尤其是这样的，对性爱以外的所有事情都不感兴趣，并且会袭击帮派成员以外的所有人来获取他们想要的东西……"));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
	
			// Clothing is equipped in the Encounter class, when the imps are spawned.
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		
		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setHomeLocation();
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		setStartingCombatMoves();
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3")) {
			this.setHomeLocation();
		}

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
			this.addPersonalityTrait(PersonalityTrait.SLOVENLY);
		}
		setStartingCombatMoves();
	}
	
	@Override
	public void setStartingCombatMoves() {
		this.clearEquippedMoves();
		if(this.isFeminine()) {
			if(this.hasPenis()) {
				this.equipBasicCombatMoves();
				
			} else {
				this.equipMove("tease");
				this.equipMove("avert");
				this.equipMove("block");
			}
		} else {
			this.equipMove("strike");
			this.equipMove("twin-strike");
			this.equipMove("block");
		}
		this.equipAllKnownMoves();
		this.equipAllSpellMoves();
	}
	
	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
		}
		
		if(setFetishes) {
		}
		
		if(setOrientation) {
		}

		if(setHistory) {
		}
		
		if(setSpells) {
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) { //TODO gang tattoos?
		this.incrementMoney((long) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);

		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave() && this.isDoll()) {
			return super.getDescription();
		}
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos]在屈城区的隧道游荡和骚扰无辜过路人的日子已经结束。[npc.sheIs]因触犯法律成为了奴隶，不过是[npc.her]主人的财产而已。"));
		} else {
			return (UtilText.parse(this, description));
		}
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return TunnelImpsDialogue.IMP_ATTACK;
	}

	@Override
	public boolean isAbleToBeEnslaved() {
		return this.getWorldLocation()!=WorldType.IMP_FORTRESS_ALPHA
				&& this.getWorldLocation()!=WorldType.IMP_FORTRESS_DEMON
				&& this.getWorldLocation()!=WorldType.IMP_FORTRESS_FEMALES
				&& this.getWorldLocation()!=WorldType.IMP_FORTRESS_MALES;
	}
	
	// Combat:
	
	@Override
	public int getEscapeChance() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			return 0;
		}
		return super.getEscapeChance();
	}
	
	@Override
	public void applyEscapeCombatEffects() {
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_DEMON_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_MALES);
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_ENTRANCE)) {
			Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES);
			
		} else {
			TunnelImpsDialogue.banishImpGroup();
		}
	};
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_ALPHA_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_FEMALES_ENTRANCE)
				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_MALES_ENTRANCE)) {
			if (victory) {
				return new Response("", "", ImpFortressDialogue.GUARDS_AFTER_COMBAT_VICTORY);
			} else {
				return new Response("", "", ImpFortressDialogue.GUARDS_AFTER_COMBAT_DEFEAT);
			}
			
		} else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
			if (victory) {
				if(applyEffects) {
					Main.game.getDialogueFlags().impCitadelImpWave++;
				}
				if(Main.game.getDialogueFlags().impCitadelImpWave>5) {
					return new Response("", "", ImpCitadelDialogue.IMP_FIGHT_AFTER_COMBAT_VICTORY) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(
									UtilText.parseFromXMLFile("places/submission/impCitadel"+ImpCitadelDialogue.getDialogueEncounterId(), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ATTRIBUTE_BOOST", ImpCitadelDialogue.getAllCharacters()));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addSpecialPerk(Perk.IMP_SLAYER));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressDemonImpsDefeated, true);
							if(ImpCitadelDialogue.isCompanionDialogue()) {
								for(GameCharacter companion : Main.game.getPlayer().getParty()) {
									Main.game.getTextEndStringBuilder().append(companion.addSpecialPerk(Perk.IMP_SLAYER));
								}
							}
						}
					};
				} else {
					return new Response("", "", ImpCitadelDialogue.IMP_CHALLENGE_CONTINUE) {
						@Override
						public void effects() {
							ImpCitadelDialogue.spawnImps();
						}
					};
				}
				
			} else {
				return new Response("", "", ImpCitadelDialogue.IMP_FIGHT_AFTER_COMBAT_DEFEAT) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.FORTRESS_LAB)) {
							if(Main.game.isNonConEnabled() && (Main.game.getPlayer().isFeminine() || (ImpCitadelDialogue.isCompanionDialogue() && ImpCitadelDialogue.getMainCompanion().isFeminine()))) {
								ImpCitadelDialogue.getArcanist().displaceClothingForAccess(CoverableArea.VAGINA, null);
								ImpCitadelDialogue.getArcanist().setLustNoText(50);
							}
						}
						ImpCitadelDialogue.getArcanist().setLocation(WorldType.IMP_FORTRESS_DEMON, PlaceType.FORTRESS_LAB);
					}
				};
			}
			
		} else {
			if (victory) {
				TransformativePotion effects = TunnelImpsDialogue.getImpLeader().generateTransformativePotion(Main.game.getPlayer());
				if(effects!=null) {
					AbstractItem potion = EnchantingUtils.craftItem(
						Main.game.getItemGen().generateItem(effects.getItemType()),
						effects.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
					potion.setName("小恶魔灵药");
					TunnelImpsDialogue.getImpGroup().get(1).addItem(potion, false);
				}
				if(!Main.game.getPlayer().getNonElementalCompanions().isEmpty()) {
					TransformativePotion effects2 = TunnelImpsDialogue.getImpLeader().generateTransformativePotion(Main.game.getPlayer().getMainCompanion());
					if(effects2!=null) {
						AbstractItem potion2 = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(effects2.getItemType()),
							effects2.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						potion2.setName("小恶魔灵药");
						TunnelImpsDialogue.getImpGroup().get(1).addItem(potion2, false);
					}
				}
				
				return new Response("", "", TunnelImpsDialogue.AFTER_COMBAT_VICTORY);
				
			} else {
				return new Response("", "", TunnelImpsDialogue.AFTER_COMBAT_DEFEAT);
			}
		}
	}
	
	// TF potion:
	
	@Override
	public TransformativePotion generateTransformativePotion(GameCharacter target) {
		AbstractItemType itemType = target.getSubspecies().getTransformativeItem(target);
        if(itemType==null || itemType.equals(ItemType.getItemTypeFromId("innoxia_race_slime_slime_quencher"))) {
            itemType = ItemType.getItemTypeFromId("innoxia_race_human_bread_roll");
        }
		
		List<PossibleItemEffect> effects = new ArrayList<>();
//		int numberOfTransformations = (2+Util.random.nextInt(4)) * (target.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)?2:1);
		
		if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				effects.addAll(getFeminineEffects(target, itemType));
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"来给尼一个漂亮的新穴穴吧！"));
			}
			if(target.getVaginaWetness().getValue()<Wetness.SIX_SOPPING_WET.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"你的小穴需要被弄湿，准备好挨操！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"尼需要一根鸡巴来满足我们！"));
			}
			if(target.getPenisRawSizeValue()<PenisLength.THREE_LARGE.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"你的鸡巴该比现在更大！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawCumStorageValue()<100) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"尼要设得到处都是！"));
			}
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY) {
				effects.addAll(getMasculineEffects(target, itemType));
			} else {
				effects.addAll(getFeminineEffects(target, itemType));
			}

			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"尼需要一根鸡巴来满足我们！"));
			}
			if(target.getPenisRawSizeValue()<PenisLength.THREE_LARGE.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"你的鸡巴该比现在更大！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"让你的鸡巴更大更粗些吧！"));
			}
			if(target.getPenisRawCumStorageValue()<250) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"准备好当我们的精液容器了吗？"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"来给尼一个漂亮的新穴穴吧！"));
			}
			if(target.getVaginaWetness().getValue()<Wetness.SIX_SOPPING_WET.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"你的小穴需要被弄湿，准备好挨操！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.MASCULINE_HEAVY) {
				effects.addAll(getMasculineEffects(target, itemType));
			}
			
			// Add penis:
			if(!target.hasPenisIgnoreDildo()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"尼需要一根鸡巴来满足我们！"));
			}
			if(target.getPenisRawSizeValue()<PenisLength.THREE_LARGE.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"你的鸡巴该比现在更大！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
					"让你的鸡巴更大更粗些吧！"));
			}

			if(target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					"你的蛋蛋马上要变大了！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MINOR_BOOST, 1),
					""));
			}
			if(target.getPenisRawCumStorageValue()<250) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"准备好当我们的精液容器了吗？"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
			
			// Add long tongue (for cunnilingus):
			if(target.getTongueLengthValue()<TongueLength.TWO_VERY_LONG.getMedianValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.BOOST, 1),
					"尼的舌头最好是呦长呦漂亮，这样你在舔俺们的时候就能给俺们来点特别的！"));
			}
			
		} else if(target.getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_MALES)) {
			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE
					|| Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				effects.addAll(getFeminineEffects(target, itemType));
			}
			
			// Add wet vagina:
			if(!target.hasVagina()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
					"让我们给你一个屄操吧！"));
			}
			if(target.getVaginaWetness().getValue()<Wetness.SIX_SOPPING_WET.getValue()) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					"你的小穴需要被弄湿，准备好挨操！"));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, TFPotency.MAJOR_BOOST, 1),
					""));
			}
		}

		if(effects.isEmpty()) {
			return null;
		}
		
		return new TransformativePotion(itemType, effects);
	}
	
	private static List<PossibleItemEffect> getMasculineEffects(GameCharacter target, AbstractItemType itemType) {
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		for(int i=target.getFemininityValue(); i>Femininity.MASCULINE.getMinimumFemininity(); i-=15) { // Turn masculine:
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1),
				"你该比现在更有男子气概！"));
		}
		if(target.getMuscleValue()<Muscle.THREE_MUSCULAR.getMaximumValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
				"我要你更强壮！"));
		}
		if(target.getBodySizeValue()<BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_BOOST, 1),
				"你想要变得更大！"));
		}
		if(target.getHeightValue()<183) { // 6'
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"就是这样；变得更大更长吧！"));
		}
		for(int i=target.getBreastSize().getMeasurement(); i>0; i-=3) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"把你的奶头去掉吧！"));
		}
		if(target.getHipSize().getValue()>HipSize.TWO_NARROW.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
				"该让屁股收窄点嘛！"));
		}
		if(target.getAssSize().getValue()>AssSize.THREE_NORMAL.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"你的屁股太肥了！"));
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i>HairLength.TWO_SHORT.getMaximumValue(); i-=15) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
					"你该做一头更男人的发型！"));
			}
		}
		for(int i=target.getLipSizeValue(); i>LipSize.TWO_FULL.getValue(); i-=2) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1),
				"把你这张只会吸鸡巴的嘴弄小一点！"));
		}
		
		return effects;
	}
	
	private static List<PossibleItemEffect> getFeminineEffects(GameCharacter target, AbstractItemType itemType) {
		List<PossibleItemEffect> effects = new ArrayList<>();
		
		for(int i=target.getFemininityValue(); i<Femininity.FEMININE.getMinimumFemininity(); i+=15) { // Turn feminine:
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1),
				"做完这个，你会看起来更少女！"));
		}
		if(target.getMuscleValue()>Muscle.THREE_MUSCULAR.getMedianValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 1),
				"尼身上有男人味的肌肉该消事了！"));
		}
		if(target.getBodySizeValue()>BodySize.TWO_AVERAGE.getMinimumValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.MAJOR_DRAIN, 1),
				"尼应盖更苗条点！"));
		}
		if(target.getHeightValue()>167) { // 5'6"
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1),
				"尼太高了，不合俺口味！"));
		}
		for(int i=target.getBreastSize().getMeasurement(); i<CupSize.E.getMeasurement(); i+=3) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"把尼的大奶子给俺玩玩噻！"));
		}
		if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_BOOST, 1),
				"尼的屁股会长到赞赞的肥呦，能载俺小恶魔很多崽呢。俺会把你操怀嘞！"));
		}
		if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
				"尼该长个更肥的大屁股！"));
		}
		if(target.getHairRawLengthValue()>0) { // If bald, leave bald.
			for(int i=target.getHairRawLengthValue(); i<HairLength.THREE_SHOULDER_LENGTH.getMaximumValue(); i+=15) {
				effects.add(new PossibleItemEffect(
					new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, 1),
					"尼该留耿长的头发！"));
			}
		}
		for(int i=target.getLipSizeValue(); i<LipSize.TWO_FULL.getValue(); i+=2) {
			effects.add(new PossibleItemEffect(
				new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, TFPotency.BOOST, 1),
				"该给你多汁的小穴了!"));
		}
		
		return effects;
	}
}
