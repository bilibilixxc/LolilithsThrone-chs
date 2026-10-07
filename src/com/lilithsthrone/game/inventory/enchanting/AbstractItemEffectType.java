package com.lilithsthrone.game.inventory.enchanting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TorsoType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeShape;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.AbstractPerk;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.EffectBenefit;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.4
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractItemEffectType {
	
	private List<String> effectsDescriptions;
	private Colour colour;
	
	public AbstractItemEffectType(
			List<String> effectsDescriptions,
			Colour colour) {
		this.effectsDescriptions = effectsDescriptions;
		this.colour = colour;
	}
	
	public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		if(effectsDescriptions==null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(effectsDescriptions);
	}
	
	public Colour getColour() {
		return colour;
	}
	
	/**
	 * @return Usually null, but if this ItemEffectType has an associated Race, this is how to access it.
	 */
	public AbstractRace getAssociatedRace() {
		return null;
	}
	
	public abstract String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer);

	public final String applyEffect(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
		StringBuilder sb = new StringBuilder();
		sb.append(itemEffectOverride(primaryModifier, secondaryModifier, potency, limit, user, target, timer));
		
		for(Entry<AbstractStatusEffect, Integer> entry : getAppliedStatusEffects().entrySet()) {
			AbstractStatusEffect se = entry.getKey();
			int time = entry.getValue();
			boolean added = target.addStatusEffect(se, time);
			if(!added) {
				continue;
			}
			String timeDesc = time+"回合";
			if(!se.isCombatEffect()) {
				int timeMinutes = (time/60);
				if(timeMinutes > 3*60) {
					timeDesc = timeMinutes/60+"小时";
				} else {
					timeDesc = timeMinutes+"分钟";
				}
			}
			sb.append(UtilText.parse(target,
					"<p style='text-align:center; padding-top:0; margin-top:0;'>"
					+ "[npc.NameIsFull]现在"
					+(se.getBeneficialStatus()==EffectBenefit.DETRIMENTAL?"遭到了[style.italicsBad(":(se.getBeneficialStatus()==EffectBenefit.BENEFICIAL?"获得了[style.italicsGood(":"得到了"))
					+se.getName(target)
					+(se.getBeneficialStatus()==EffectBenefit.NEUTRAL?"":")]")
					+ "的影响，持续"+timeDesc+"！"
					+ "</p>"));
		}
		
		return sb.toString();
	}
	
	public String getPotionDescriptor() {
		return "";
	}
	
	/**
	 * <b>This disables use in sex or combat automatically.</b>
	 * @return true if the use of this item should exit inventory management. i.e. If it's meant to set the content to a specific scene.
	 */
	public boolean isBreakOutOfInventory() {
		return false;
	}
	
	/**
	 * @return A Map of status effects to be applied to the target, mapped to how long that status effect should be applied for, <b>in seconds</b>.
	 */
	public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
		return new HashMap<>();
	}
	
	public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
		return new ArrayList<>();
	}
	
	public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
		return new ArrayList<>();
	}
	
	public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return new ArrayList<>();
	}
	
	public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return 0;
	}

	public int getSmallLimitChange(TFModifier primaryModifier, TFModifier secondaryModifier) {
		if (secondaryModifier == TFModifier.TF_MOD_WETNESS
				&& (primaryModifier == TFModifier.TF_BREASTS
						|| primaryModifier == TFModifier.TF_BREASTS_CROTCH
						|| primaryModifier == TFModifier.TF_PENIS)) {
			// Increase small change for fluids
			return 10;
		}
		return 1;
	}

	public int getLargeLimitChange(TFModifier primaryModifier, TFModifier secondaryModifier) {
		if (secondaryModifier == TFModifier.TF_MOD_WETNESS
				&& (primaryModifier == TFModifier.TF_BREASTS
						|| primaryModifier == TFModifier.TF_BREASTS_CROTCH
						|| primaryModifier == TFModifier.TF_PENIS)) {
			// Decrease large change for fluids
			return 500;
		}
		return Math.max(5, getMaximumLimit(primaryModifier, secondaryModifier)/10);
	}

	public int getMaximumLimit(TFModifier primaryModifier, TFModifier secondaryModifier) {
		return getLimits(primaryModifier, secondaryModifier);
	}
	
	public static String getBookEffect(GameCharacter reader, AbstractSubspecies mainSubspecies, List<AbstractSubspecies> additionalUnlockSubspecies, boolean withDescription) {
		List<AbstractSubspecies> subsPlusMain = new ArrayList<>();
		subsPlusMain.add(mainSubspecies);
		if(additionalUnlockSubspecies!=null) {
			subsPlusMain.addAll(additionalUnlockSubspecies);
		}
		
		String descriptionToReturn = "";
		AbstractPerk perk = Perk.getSubspeciesRelatedPerk(mainSubspecies);
		if(!reader.isPlayer() || ((PlayerCharacter) reader).addRaceDiscoveredFromBook(mainSubspecies) || !reader.hasPerkAnywhereInTree(perk)) {
			descriptionToReturn = (withDescription
						?("<p style='text-align:center; font-size:110%;margin-bottom:0;padding-bottom:0;'><b>"+mainSubspecies.getBookName()+"</b></p>"
							+ (mainSubspecies.getBookAuthor().isEmpty()?"":"<p style='text-align:center;margin-top:0;padding-top:0;'><b><i>作者为"+mainSubspecies.getBookAuthor()+"</i></b></p>")
							+ mainSubspecies.getBasicDescription(null)
							+ mainSubspecies.getAdvancedDescription(null))
						:"")
					+reader.addSpecialPerk(perk);
			
		} else {
			descriptionToReturn = "<p style='text-align:center; font-size:110%;margin-bottom:0;padding-bottom:0;'><b>"+mainSubspecies.getBookName()+"</b></p>"
					+ (mainSubspecies.getBookAuthor().isEmpty()?"":"<p style='text-align:center;margin-top:0;padding-top:0;'><b><i>作者为"+mainSubspecies.getBookAuthor()+"</i></b></p>")
					+ mainSubspecies.getBasicDescription(null)
					+ mainSubspecies.getAdvancedDescription(null)
					+"<p style='text-align:center; color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
						+ "重读此书不会有更多收获……"
					+ "</p>";
		}
		
		for(AbstractSubspecies subspecies : subsPlusMain) {
			Main.getProperties().addRaceDiscovered(subspecies);
			if(Main.getProperties().addAdvancedRaceKnowledge(subspecies) && ItemType.getLoreBook(subspecies)!=null) {
				Main.game.addEvent(new EventLogEntryBookAddedToLibrary(ItemType.getLoreBook(subspecies)), true);
			}
		}
		
		return descriptionToReturn;
	}
	
	protected static List<TFModifier> getClothingTFSecondaryModifiers(TFModifier primaryModifier) {
		switch(primaryModifier) {
			case TF_ASS:
				List<TFModifier> assMods = Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// ass size
						TFModifier.TF_MOD_SIZE_SECONDARY,// hip size
						Main.game.isAssHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null);
				if(Main.game.isAnalContentEnabled()) {
					assMods.add(TFModifier.TF_MOD_CAPACITY);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						assMods.add(TFModifier.TF_MOD_DEPTH);
					}
					assMods.add(TFModifier.TF_MOD_ELASTICITY);
					assMods.add(TFModifier.TF_MOD_PLASTICITY);
					assMods.add(TFModifier.TF_MOD_WETNESS);
					assMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					assMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					assMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					assMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return assMods;
			case TF_ARMS:
				return Util.newArrayListOfValues(
						Main.game.isBodyHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null);
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// breast size
						TFModifier.TF_MOD_SIZE_SECONDARY,// nipple size
						TFModifier.TF_MOD_SIZE_TERTIARY,// areolae size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						Main.game.isLactationContentEnabled()
							?TFModifier.TF_MOD_WETNESS
							:null,
						Main.game.isLactationContentEnabled()
							?TFModifier.TF_MOD_REGENERATION
							:null,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED
						);
			case TF_CORE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// height
						TFModifier.TF_MOD_SIZE_SECONDARY,// muscle mass
						TFModifier.TF_MOD_SIZE_TERTIARY,// body size
						TFModifier.TF_MOD_FEMININITY,
						Main.game.isPubicHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);
			case TF_FACE:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE, // lip size
						TFModifier.TF_MOD_SIZE_SECONDARY, // tongue size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,

						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED,
						
						TFModifier.TF_MOD_TONGUE_RIBBED,
						TFModifier.TF_MOD_TONGUE_TENTACLED,
						TFModifier.TF_MOD_TONGUE_BIFURCATED,
						TFModifier.TF_MOD_TONGUE_WIDE,
						TFModifier.TF_MOD_TONGUE_FLAT,
						TFModifier.TF_MOD_TONGUE_STRONG,
						
						Main.game.isFacialHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);
			case TF_HAIR:
				return Util.newArrayListOfValues(
						TFModifier.TF_MOD_SIZE,// hair length
						TFModifier.TF_MOD_SIZE_SECONDARY// neck floof
						);
			case TF_PENIS:
				List<TFModifier> penisMods = Util.newArrayListOfValues(
						TFModifier.TF_TYPE_1,
						TFModifier.REMOVAL,
						TFModifier.TF_MOD_SIZE,
						TFModifier.TF_MOD_SIZE_SECONDARY,
						TFModifier.TF_MOD_SIZE_TERTIARY);
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_CAPACITY);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						penisMods.add(TFModifier.TF_MOD_DEPTH);
					}
					penisMods.add(TFModifier.TF_MOD_ELASTICITY);
					penisMods.add(TFModifier.TF_MOD_PLASTICITY);
				}
				penisMods.add(TFModifier.TF_MOD_WETNESS);
				penisMods.add(TFModifier.TF_MOD_CUM_EXPULSION);
				penisMods.add(TFModifier.TF_MOD_REGENERATION);
				if(Main.game.isPubicHairEnabled()) {
					penisMods.add(TFModifier.TF_MOD_BODY_HAIR);
				}

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					penisMods.add(TFModifier.TF_MOD_ORIFICE_PUFFY);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_RIBBED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED);
					penisMods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED);
				}
				return penisMods;
				
			case TF_VAGINA:
				List<TFModifier> mods = Util.newArrayListOfValues(
						TFModifier.TF_TYPE_1,
						TFModifier.REMOVAL,
						TFModifier.TF_MOD_SIZE,// clit size
						TFModifier.TF_MOD_SIZE_SECONDARY,// labia size
						TFModifier.TF_MOD_CAPACITY,
						Main.game.isPenetrationLimitationsEnabled()
							?TFModifier.TF_MOD_DEPTH
							:null,
						TFModifier.TF_MOD_ELASTICITY,
						TFModifier.TF_MOD_PLASTICITY,
						TFModifier.TF_MOD_WETNESS,
						TFModifier.TF_MOD_ORIFICE_PUFFY,
						TFModifier.TF_MOD_ORIFICE_RIBBED,
						TFModifier.TF_MOD_ORIFICE_MUSCLED,
						TFModifier.TF_MOD_ORIFICE_TENTACLED,
						Main.game.isPubicHairEnabled()
							?TFModifier.TF_MOD_BODY_HAIR
							:null
						);

				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					mods.add(TFModifier.TF_MOD_CAPACITY_2);
					if(Main.game.isPenetrationLimitationsEnabled()) {
						mods.add(TFModifier.TF_MOD_DEPTH_2);
					}
					mods.add(TFModifier.TF_MOD_ELASTICITY_2);
					mods.add(TFModifier.TF_MOD_PLASTICITY_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_PUFFY_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_RIBBED_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_MUSCLED_2);
					mods.add(TFModifier.TF_MOD_ORIFICE_TENTACLED_2);
				}
				return mods;
			default:
				break;
		}
		return new ArrayList<>();
	}
	
	protected static int getClothingTFLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {

		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				return (int) Capacity.SEVEN_GAPING.getMaximumValue(false);
			case TF_MOD_DEPTH:
				return OrificeDepth.SEVEN_FATHOMLESS.getValue();
			case TF_MOD_ELASTICITY:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_CAPACITY_2:
				return (int) Capacity.SEVEN_GAPING.getMaximumValue(false);
			case TF_MOD_DEPTH_2:
				return OrificeDepth.SEVEN_FATHOMLESS.getValue();
			case TF_MOD_ELASTICITY_2:
				return OrificeElasticity.SEVEN_ELASTIC.getValue();
			case TF_MOD_PLASTICITY_2:
				return OrificePlasticity.SEVEN_MOULDABLE.getValue();
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
					&& primaryModifier!=TFModifier.TF_BREASTS
					&& primaryModifier!=TFModifier.TF_BREASTS_CROTCH) {
					return Wetness.SEVEN_DROOLING.getValue();
				}
				break;
			case TF_MOD_ORIFICE_PUFFY:
			case TF_MOD_ORIFICE_RIBBED:
			case TF_MOD_ORIFICE_MUSCLED:
			case TF_MOD_ORIFICE_TENTACLED:
				return 0;
			default:
				break;
		}
		
		switch(primaryModifier) {
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return AssSize.SEVEN_GIGANTIC.getValue();
					case TF_MOD_SIZE_SECONDARY:
						return HipSize.SEVEN_ABSURDLY_WIDE.getValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return CupSize.getMaximumCupSize().getMeasurement();
					case TF_MOD_SIZE_SECONDARY:
						return NippleSize.FOUR_MASSIVE.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return  AreolaeSize.FOUR_MASSIVE.getValue();
					case TF_MOD_WETNESS:
						return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return Height.SEVEN_COLOSSAL.getMaximumValue() - Height.ZERO_TINY.getMinimumValue();
					case TF_MOD_SIZE_SECONDARY:
						return Muscle.FOUR_RIPPED.getMaximumValue();
					case TF_MOD_SIZE_TERTIARY:
						return BodySize.FOUR_HUGE.getMaximumValue();
					case TF_MOD_FEMININITY:
						return Femininity.FEMININE_STRONG.getMaximumFemininity();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return LipSize.FOUR_HUGE.getValue();
					case TF_MOD_SIZE_SECONDARY:
						return TongueLength.FOUR_ABSURDLY_LONG.getMaximumValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return HairLength.SEVEN_TO_FLOOR.getMaximumValue();
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return PenisLength.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return PenetrationGirth.SEVEN_FAT.getValue();
					case TF_MOD_SIZE_TERTIARY:
						return TesticleSize.SEVEN_ABSURD.getValue();
					case TF_MOD_WETNESS:
						return CumProduction.SEVEN_MONSTROUS.getMaximumValue();
					case TF_MOD_CUM_EXPULSION:
						return FluidExpulsion.FOUR_HUGE.getMaximumValue();
					case TF_MOD_REGENERATION:
						return FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						return ClitorisSize.SEVEN_STALLION.getMaximumValue();
					case TF_MOD_SIZE_SECONDARY:
						return LabiaSize.FOUR_MASSIVE.getValue();
					case TF_MOD_BODY_HAIR:
						return BodyHair.SEVEN_WILD.getValue();
					default:
						break;
				}
				break;
			default:
				break;
		}
		return 0;
	}
	
	protected static List<String> getClothingTFDescriptions(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		List<String> descriptions = new ArrayList<>();
		
		String orificeName = "";

		if(primaryModifier==TFModifier.TF_PENIS) {
			orificeName = "尿道";
		} else if(primaryModifier==TFModifier.TF_VAGINA) {
			orificeName = "阴道";
		} else if(primaryModifier==TFModifier.TF_BREASTS) {
			orificeName = "乳头";
		} else if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
			orificeName = "乳头(胯部)";
		} else if(primaryModifier==TFModifier.TF_ASS) {
			orificeName = "肛门";
		} else if(primaryModifier==TFModifier.TF_FACE) {
			orificeName = "喉咙";
		}
		
		switch(secondaryModifier) {
			case TF_MOD_CAPACITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"承受力", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_DEPTH:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"深度", OrificeDepth.getDepthFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_ELASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"弹性", OrificeElasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"可塑性", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_CAPACITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"尿道容量", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
				break;
			case TF_MOD_DEPTH_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"尿道深度", OrificeDepth.getDepthFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_ELASTICITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"尿道弹性等级", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_PLASTICITY_2:
				descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"尿道可塑性", OrificePlasticity.getElasticityFromInt(limit).getDescriptor()));
				break;
			case TF_MOD_WETNESS:
				if(primaryModifier!=TFModifier.TF_PENIS
						&& primaryModifier!=TFModifier.TF_BREASTS
						&& primaryModifier!=TFModifier.TF_BREASTS_CROTCH) {
					descriptions.add(getClothingTFChangeDescriptionEntry(potency, orificeName+"湿润度", Wetness.valueOf(limit).getDescriptor()));
				}
				break;
			default:
				break;
		}
		
		switch(primaryModifier) {
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "屁股尺寸", AssSize.getAssSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "臀部尺寸", HipSize.getHipSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "肛门充满肉感", "肛门肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "肛门长出螺纹",  "肛门的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "肛门长出发达的肌肉", "肛门失去发达的肌肉"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "肛门长出触手", "肛门的触手消失"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "屁股多毛", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "腋下多毛", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						CupSize cupSize = CupSize.getCupSizeFromInt(limit);
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "罩杯尺寸", cupSize.getCupSizeName()+(cupSize==CupSize.FLAT?"":"罩杯")));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "乳头尺寸", NippleSize.getNippleSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "乳晕尺寸", AreolaeSize.getAreolaeSizeFromInt(limit).getName()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "泌乳", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "乳汁再生", "每天" + Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "乳头充满肉感", "乳头肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "乳头内部生长出螺纹",  "乳头的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "乳头内部生长出肌肉", "乳头内部的肌肉消失"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "乳头内长出触手", "乳头里的触手消失"));
						break;
					default:
						break;
				}
				break;
			case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "罩杯尺寸(胯部)", CupSize.getCupSizeFromInt(limit).getCupSizeName()+"罩杯"));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "乳头尺寸(胯部)", NippleSize.getNippleSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "乳晕尺寸(胯部)", AreolaeSize.getAreolaeSizeFromInt(limit).getName()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "泌乳(胯部)", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "乳汁再生(胯部)", "每天" + Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "乳头充满肉感(胯部)", "乳头肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "胯部乳头内部生长出螺纹",  "乳头的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "胯部乳头内部生长出肌肉", "乳头内部的肌肉消失"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "胯部乳头内长出触手", "乳头里的触手消失"));
						break;
					default:
						break;
				}
				break;
			case TF_CORE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "身高", Units.size(Height.ZERO_TINY.getMinimumValue() + limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "肌肉尺寸", String.valueOf(limit)));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "身体尺寸", String.valueOf(limit)));
						break;
					case TF_MOD_FEMININITY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "女性化", String.valueOf(limit)));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴部多毛", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "嘴唇尺寸", LipSize.getLipSizeFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "舌头长度", Units.size(limit)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "嘴唇充满肉感", "嘴唇肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "喉咙内生长出螺纹",  "喉咙的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "喉咙内生长出肌肉", "多余的肌肉消失"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "喉咙内长出触手", "喉咙内的触手消失"));
						break;
					case TF_MOD_TONGUE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "舌头长出螺纹",  "舌头的螺纹消失"));
						break;
					case TF_MOD_TONGUE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "舌头长出触手", "舌头的触手消失"));
						break;
					case TF_MOD_TONGUE_BIFURCATED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "舌头分叉", "舌头分叉消失"));
						break;
					case TF_MOD_TONGUE_WIDE:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "舌头变宽大", "舌头恢复"));
						break;
					case TF_MOD_TONGUE_FLAT:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "舌头变平", "舌头恢复"));
						break;
					case TF_MOD_TONGUE_STRONG:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "舌头变得强壮", "舌头恢复"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "胡子长度", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "头发长度", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("每周长出颈部毛团。");
								break;
							case BOOST:
								descriptions.add("每天长出颈部毛团。");
								break;
							case MAJOR_BOOST:
								descriptions.add("每小时长出颈部毛团。");
								break;
							case MINOR_DRAIN:
								descriptions.add("一周后，颈部毛团消失");
								break;
							case DRAIN:
								descriptions.add("一天后，颈部毛团消失");
								break;
							case MAJOR_DRAIN:
								descriptions.add("一小时后，颈部毛团消失");
								break;
							default:
								break;
						}
						break;
					default:
						break;
				}
				break;
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("一周后，长出阴茎。");
								break;
							case BOOST:
								descriptions.add("一天后，长出阴茎。");
								break;
							case MAJOR_BOOST:
								descriptions.add("一小时后，长出阴茎。");
								break;
							default:
								break;
						}
						break;
					case REMOVAL:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("一周后，阴茎消失。");
								break;
							case BOOST:
								descriptions.add("一天后，阴茎消失。");
								break;
							case MAJOR_BOOST:
								descriptions.add("一小时后，阴茎消失。");
								break;
							default:
								break;
						}
						break;
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴茎长度", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴茎周长", PenetrationGirth.getGirthFromInt(limit).getName()));
						break;
					case TF_MOD_SIZE_TERTIARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "睾丸尺寸", TesticleSize.getTesticleSizeFromInt(limit).getDescriptor()));
						break;
					case TF_MOD_WETNESS:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "精液储量", Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_CUM_EXPULSION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "精液排出", limit+"%"));
						break;
					case TF_MOD_REGENERATION:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "精液再生", "每天" + Units.fluid(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道充满肉感", "尿道肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道内部生长出螺纹",  "尿道的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道内生长出肌肉", "尿道多余的肌肉消失"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道内长出触手", "尿道内的触手消失"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴部多毛", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_TYPE_1:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("一周后，长出阴道。");
								break;
							case BOOST:
								descriptions.add("一天后，长出阴道。");
								break;
							case MAJOR_BOOST:
								descriptions.add("一小时后，长出阴道。");
								break;
							default:
								break;
						}
						break;
					case REMOVAL:
						switch(potency) {
							case MINOR_BOOST:
								descriptions.add("一周后，阴道消失。");
								break;
							case BOOST:
								descriptions.add("一天后，阴道消失。");
								break;
							case MAJOR_BOOST:
								descriptions.add("一小时后，阴道消失。");
								break;
							default:
								break;
						}
						break;
					case TF_MOD_SIZE:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴蒂尺寸", Units.size(limit, Units.ValueType.PRECISE, Units.UnitType.SHORT)));
						break;
					case TF_MOD_SIZE_SECONDARY:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴唇尺寸", LabiaSize.getLabiaSizeFromInt(limit).getName()));
						break;
					case TF_MOD_ORIFICE_PUFFY:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "阴道充满肉感", "阴道肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "阴道内生长出螺纹", "阴道的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "阴道内生长出肌肉", "多余的肌肉消失"));
						break;
					case TF_MOD_ORIFICE_TENTACLED:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "阴道内长出触手", "阴道内的触手消失"));
						break;
					case TF_MOD_ORIFICE_PUFFY_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道充满肉感", "尿道肉感减弱"));
						break;
					case TF_MOD_ORIFICE_RIBBED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道内部生长出螺纹",  "尿道的螺纹消失"));
						break;
					case TF_MOD_ORIFICE_MUSCLED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道内生长出肌肉", "尿道多余的肌肉消失"));
						break;
					case TF_MOD_ORIFICE_TENTACLED_2:
						descriptions.add(getClothingOrificeTFChangeDescriptionEntry(potency, "尿道内长出触手", "尿道内的触手消失"));
						break;
					case TF_MOD_BODY_HAIR:
						descriptions.add(getClothingTFChangeDescriptionEntry(potency, "阴部多毛", BodyHair.getBodyHairFromValue(limit).getName()));
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
		
		return descriptions;
	}
	
	private static String getClothingOrificeTFChangeDescriptionEntry(TFPotency potency, String changeAdd, String changeRemove) {
		switch(potency) {
			case MINOR_BOOST:
				return ("一周后，让"+changeAdd+"。");
			case BOOST:
				return ("一天后，让"+changeAdd+"。");
			case SPECIAL:
			case MAJOR_BOOST:
				return ("一小时后，让"+changeAdd+"。");
			case MINOR_DRAIN:
				return ("一周后，让"+changeRemove+"。");
			case DRAIN:
				return ("一天后，让"+changeRemove+"。");
			case MAJOR_DRAIN:
				return ("一小时后，让"+changeRemove+"。");
		}
		return "";
	}
	
	private static String getClothingTFChangeDescriptionEntry(TFPotency potency, String subject, String limit) {
		switch(potency) {
			case MINOR_BOOST:
				return ("每周增加"+subject+"。(极限:"+limit+")");
			case BOOST:
				return ("每天增加"+subject+"。(极限:"+limit+")");
			case SPECIAL:
			case MAJOR_BOOST:
				return ("每小时增加"+subject+"。(极限:"+limit+")");
			case MINOR_DRAIN:
				return ("每周降低"+subject+"。(极限:"+limit+")");
			case DRAIN:
				return ("每天降低"+subject+"。(极限:"+limit+")");
			case MAJOR_DRAIN:
				return ("每小时降低"+subject+"。(极限:"+limit+")");
		}
		return "";
	}
	
	protected static String applyClothingTF(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
		int depthIncrement = (potency.isNegative()?-1:1);
		int capacityIncrement = (potency.isNegative()?-2:2);
		int elasticityIncrement = (potency.isNegative()?-1:1);
		int plasticityIncrement = (potency.isNegative()?-1:1);
		int wetnessIncrement = (potency.isNegative()?-1:1);
		int assSizeIncrement = (potency.isNegative()?-1:1);
		int hipSizeIncrement = (potency.isNegative()?-1:1);

		int breastSizeIncrement = (potency.isNegative()?-1:1);
		int nippleSizeIncrement = (potency.isNegative()?-1:1);
		int areolaeSizeIncrement = (potency.isNegative()?-1:1);
		int lactationIncrement = (potency.isNegative()?-50:50);

		int fluidRegenerationIncrement = (potency.isNegative()?-250:250);
		
		int heightIncrement = (potency.isNegative()?-1:1);
		int muscleIncrement = (potency.isNegative()?-1:1);
		int bodySizeIncrement = (potency.isNegative()?-1:1);
		int femininityIncrement = (potency.isNegative()?-1:1);

		int lipSizeIncrement = (potency.isNegative()?-1:1);

		int hairLengthIncrement = (potency.isNegative()?-1:1);

		int penisSizeIncrement = (potency.isNegative()?-1:1);
		int testicleSizeIncrement = (potency.isNegative()?-1:1);
		int cumStorageIncrement = (potency.isNegative()?-5:5);
		int cumExpulsionIncrement = (potency.isNegative()?-5:5);

		int clitorisSizeIncrement = (potency.isNegative()?-1:1);
		int labiaSizeIncrement = (potency.isNegative()?-1:1);

		int bodyHairIncrement = (potency.isNegative()?-1:1);
		
		int TFCount = 0;
		int secondsRequired = 60*60;
		
		switch(potency) {
			case MINOR_BOOST:
				secondsRequired = 7 * 24 * 60 * 60;
				break;
			case BOOST:
				secondsRequired = 24 * 60 * 60;
				break;
			case SPECIAL:
			case MAJOR_BOOST:
				secondsRequired = 60 * 60;
				break;
			case MINOR_DRAIN:
				secondsRequired = 7 * 24 * 60 * 60;
				break;
			case DRAIN:
				secondsRequired = 24 * 60 * 60;
				break;
			case MAJOR_DRAIN:
				secondsRequired = 60 * 60;
				break;
		}
		
		TFCount = timer.getSecondsPassed()/secondsRequired;
		if(TFCount>=1) {
			timer.setSecondsPassed(timer.getSecondsPassed()%secondsRequired);
		}
//		System.out.println(timer.getTimePassed() + ", " + minutesRequired + ": " +TFCount);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<TFCount; i++) {
			switch(primaryModifier) {
				case TF_ASS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(assSizeIncrement, target.getAssSize().getValue(), limit)) {
								sb.append(target.incrementAssSize(assSizeIncrement));
							} else if(isSetToLimit(assSizeIncrement, target.getAssSize().getValue(), limit)) {
								sb.append(target.setAssSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.incrementHipSize(assSizeIncrement));
							} else if(isSetToLimit(hipSizeIncrement, target.getHipSize().getValue(), limit)) {
								sb.append(target.setHipSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.incrementAssCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getAssRawCapacityValue(), limit)) {
								sb.append(target.setAssCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getAssDepth().getValue(), limit)) {
								sb.append(target.incrementAssDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getAssDepth().getValue(), limit)) {
								sb.append(target.setAssDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.incrementAssElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getAssElasticity().getValue(), limit)) {
								sb.append(target.setAssElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.incrementAssPlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getAssPlasticity().getValue(), limit)) {
								sb.append(target.setAssPlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.incrementAssWetness(wetnessIncrement));
							} else if(isSetToLimit(wetnessIncrement, target.getAssWetness().getValue(), limit)) {
								sb.append(target.setAssWetness(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasAssOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addAssOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasAssOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeAssOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getAssHair().getValue(), limit)) {
								sb.append(target.incrementAssHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getAssHair().getValue(), limit)) {
								sb.append(target.setAssHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_ARMS:
					switch(secondaryModifier) {
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getUnderarmHair().getValue(), limit)) {
								sb.append(target.incrementUnderarmHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getUnderarmHair().getValue(), limit)) {
								sb.append(target.setUnderarmHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_BREASTS:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(breastSizeIncrement, target.getBreastRawSizeValue(), limit)) {
								sb.append(target.incrementBreastSize(breastSizeIncrement));
							} else if(isSetToLimit(breastSizeIncrement, target.getBreastRawSizeValue(), limit)) {
								sb.append(target.setBreastSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(nippleSizeIncrement));
							} else if(isSetToLimit(nippleSizeIncrement, target.getNippleSize().getValue(), limit)) {
								sb.append(target.incrementNippleSize(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(areolaeSizeIncrement));
							} else if(isSetToLimit(areolaeSizeIncrement, target.getAreolaeSize().getValue(), limit)) {
								sb.append(target.incrementAreolaeSize(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.incrementNippleCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getNippleRawCapacityValue(), limit)) {
								sb.append(target.setNippleCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getNippleDepth().getValue(), limit)) {
								sb.append(target.incrementNippleDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getNippleDepth().getValue(), limit)) {
								sb.append(target.setNippleDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.incrementNippleElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getNippleElasticity().getValue(), limit)) {
								sb.append(target.setNippleElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.incrementNipplePlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getNipplePlasticity().getValue(), limit)) {
								sb.append(target.setNipplePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(lactationIncrement, target.getBreastRawMilkStorageValue(), limit)) {
								sb.append(target.incrementBreastMilkStorage(lactationIncrement));
							} else if(isSetToLimit(lactationIncrement, target.getBreastRawMilkStorageValue(), limit)) {
								sb.append(target.setBreastMilkStorage(limit));
							}
							break;
						case TF_MOD_REGENERATION:
							if(isWithinLimits(fluidRegenerationIncrement, target.getBreastRawLactationRegenerationValue(), limit)) {
								sb.append(target.incrementBreastLactationRegeneration(fluidRegenerationIncrement));
							} else if(isSetToLimit(fluidRegenerationIncrement, target.getBreastRawLactationRegenerationValue(), limit)) {
								sb.append(target.setBreastLactationRegeneration(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasNippleOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addNippleOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasNippleOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeNippleOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						default:
							break;
					}
					break;
				case TF_BREASTS_CROTCH:
					if(target.hasBreastsCrotch()) {
						switch(secondaryModifier) {
							case TF_MOD_SIZE:
								if(isWithinLimits(breastSizeIncrement, target.getBreastCrotchRawSizeValue(), limit)) {
									sb.append(target.incrementBreastCrotchSize(breastSizeIncrement));
								} else if(isSetToLimit(breastSizeIncrement, target.getBreastCrotchRawSizeValue(), limit)) {
									sb.append(target.setBreastCrotchSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(nippleSizeIncrement, target.getNippleCrotchSize().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchSize(nippleSizeIncrement));
								} else if(isSetToLimit(nippleSizeIncrement, target.getNippleCrotchSize().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchSize(limit));
								}
								break;
							case TF_MOD_SIZE_TERTIARY:
								if(isWithinLimits(areolaeSizeIncrement, target.getAreolaeCrotchSize().getValue(), limit)) {
									sb.append(target.incrementAreolaeCrotchSize(areolaeSizeIncrement));
								} else if(isSetToLimit(areolaeSizeIncrement, target.getAreolaeCrotchSize().getValue(), limit)) {
									sb.append(target.incrementAreolaeCrotchSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getNippleCrotchRawCapacityValue(), limit)) {
									sb.append(target.incrementNippleCrotchCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getNippleCrotchRawCapacityValue(), limit)) {
									sb.append(target.setNippleCrotchCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getNippleCrotchDepth().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getNippleCrotchDepth().getValue(), limit)) {
									sb.append(target.setNippleCrotchDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getNippleCrotchElasticity().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getNippleCrotchElasticity().getValue(), limit)) {
									sb.append(target.setNippleCrotchElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getNippleCrotchPlasticity().getValue(), limit)) {
									sb.append(target.incrementNippleCrotchPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getNippleCrotchPlasticity().getValue(), limit)) {
									sb.append(target.setNippleCrotchPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(lactationIncrement, target.getBreastCrotchRawMilkStorageValue(), limit)) {
									sb.append(target.incrementBreastCrotchMilkStorage(lactationIncrement));
								} else if(isSetToLimit(lactationIncrement, target.getBreastCrotchRawMilkStorageValue(), limit)) {
									sb.append(target.setBreastCrotchMilkStorage(limit));
								}
								break;
							case TF_MOD_REGENERATION:
								if(isWithinLimits(fluidRegenerationIncrement, target.getBreastCrotchRawLactationRegenerationValue(), limit)) {
									sb.append(target.incrementBreastCrotchLactationRegeneration(fluidRegenerationIncrement));
								} else if(isSetToLimit(fluidRegenerationIncrement, target.getBreastCrotchRawLactationRegenerationValue(), limit)) {
									sb.append(target.setBreastCrotchLactationRegeneration(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addNippleCrotchOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasNippleCrotchOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeNippleCrotchOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							default:
								break;
						}
					}
					break;
				case TF_CORE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.incrementHeight(heightIncrement, false));
							} else if(isSetToLimit(heightIncrement, target.getHeightValue()-Height.ZERO_TINY.getMinimumValue(), limit)) {
								sb.append(target.setHeight(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.incrementMuscle(muscleIncrement));
							} else if(isSetToLimit(muscleIncrement, target.getMuscleValue(), limit)) {
								sb.append(target.setMuscle(limit));
							}
							break;
						case TF_MOD_SIZE_TERTIARY:
							if(isWithinLimits(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.incrementBodySize(bodySizeIncrement));
							} else if(isSetToLimit(bodySizeIncrement, target.getBodySizeValue(), limit)) {
								sb.append(target.setBodySize(limit));
							}
							break;
						case TF_MOD_FEMININITY:
							if(isWithinLimits(femininityIncrement, target.getFemininityValue(), limit)) {
								sb.append(target.incrementFemininity(femininityIncrement));
							} else if(isSetToLimit(femininityIncrement, target.getFemininityValue(), limit)) {
								sb.append(target.setFemininity(limit));
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
								sb.append(target.incrementPubicHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
								sb.append(target.setPubicHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_FACE:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(lipSizeIncrement, target.getLipSizeValue(), limit)) {
								sb.append(target.incrementLipSize(lipSizeIncrement));
							} else if(isSetToLimit(lipSizeIncrement, target.getLipSizeValue(), limit)) {
								sb.append(target.setLipSize(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(isWithinLimits(lipSizeIncrement, target.getTongueLengthValue(), limit)) {
								sb.append(target.incrementTongueLength(lipSizeIncrement));
							} else if(isSetToLimit(lipSizeIncrement, target.getTongueLengthValue(), limit)) {
								sb.append(target.setTongueLength(limit));
							}
							break;
						case TF_MOD_CAPACITY:
							if(isWithinLimits(capacityIncrement, target.getFaceRawCapacityValue(), limit)) {
								sb.append(target.incrementFaceCapacity(capacityIncrement, true));
							} else if(isSetToLimit(capacityIncrement, target.getFaceRawCapacityValue(), limit)) {
								sb.append(target.setFaceCapacity(limit, true));
							}
							break;
						case TF_MOD_DEPTH:
							if(isWithinLimits(depthIncrement, target.getFaceDepth().getValue(), limit)) {
								sb.append(target.incrementFaceDepth(depthIncrement));
							} else if(isSetToLimit(depthIncrement, target.getFaceDepth().getValue(), limit)) {
								sb.append(target.setFaceDepth(limit));
							}
							break;
						case TF_MOD_ELASTICITY:
							if(isWithinLimits(elasticityIncrement, target.getFaceElasticity().getValue(), limit)) {
								sb.append(target.incrementFaceElasticity(elasticityIncrement));
							} else if(isSetToLimit(elasticityIncrement, target.getFaceElasticity().getValue(), limit)) {
								sb.append(target.setFaceElasticity(limit));
							}
							break;
						case TF_MOD_PLASTICITY:
							if(isWithinLimits(plasticityIncrement, target.getFacePlasticity().getValue(), limit)) {
								sb.append(target.incrementFacePlasticity(plasticityIncrement));
							} else if(isSetToLimit(plasticityIncrement, target.getFacePlasticity().getValue(), limit)) {
								sb.append(target.setFacePlasticity(limit));
							}
							break;
						case TF_MOD_WETNESS:
							if(isWithinLimits(wetnessIncrement, target.getFaceWetness().getValue(), limit)) {
								sb.append(target.incrementFaceWetness(wetnessIncrement));
							} else if(isSetToLimit(wetnessIncrement, target.getFaceWetness().getValue(), limit)) {
								sb.append(target.setFaceWetness(limit));
							}
							break;
						case TF_MOD_ORIFICE_PUFFY:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.PUFFY));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.PUFFY));
								}
							}
							break;
						case TF_MOD_ORIFICE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.RIBBED));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.RIBBED)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_ORIFICE_MUSCLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
								}
							}
							break;
						case TF_MOD_ORIFICE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasFaceOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.addFaceOrificeModifier(OrificeModifier.TENTACLED));
								}
							} else {
								if(target.hasFaceOrificeModifier(OrificeModifier.TENTACLED)) {
									sb.append(target.removeFaceOrificeModifier(OrificeModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_TONGUE_RIBBED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.RIBBED)) {
									sb.append(target.addTongueModifier(TongueModifier.RIBBED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.RIBBED)) {
									sb.append(target.removeTongueModifier(TongueModifier.RIBBED));
								}
							}
							break;
						case TF_MOD_TONGUE_TENTACLED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.TENTACLED)) {
									sb.append(target.addTongueModifier(TongueModifier.TENTACLED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.TENTACLED)) {
									sb.append(target.removeTongueModifier(TongueModifier.TENTACLED));
								}
							}
							break;
						case TF_MOD_TONGUE_BIFURCATED:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.BIFURCATED)) {
									sb.append(target.addTongueModifier(TongueModifier.BIFURCATED));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.BIFURCATED)) {
									sb.append(target.removeTongueModifier(TongueModifier.BIFURCATED));
								}
							}
							break;
						case TF_MOD_TONGUE_WIDE:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.WIDE)) {
									sb.append(target.addTongueModifier(TongueModifier.WIDE));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.WIDE)) {
									sb.append(target.removeTongueModifier(TongueModifier.WIDE));
								}
							}
							break;
						case TF_MOD_TONGUE_FLAT:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.FLAT)) {
									sb.append(target.addTongueModifier(TongueModifier.FLAT));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.FLAT)) {
									sb.append(target.removeTongueModifier(TongueModifier.FLAT));
								}
							}
							break;
						case TF_MOD_TONGUE_STRONG:
							if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
								if(!target.hasTongueModifier(TongueModifier.STRONG)) {
									sb.append(target.addTongueModifier(TongueModifier.STRONG));
								}
							} else {
								if(target.hasTongueModifier(TongueModifier.STRONG)) {
									sb.append(target.removeTongueModifier(TongueModifier.STRONG));
								}
							}
							break;
						case TF_MOD_BODY_HAIR:
							if(isWithinLimits(bodyHairIncrement, target.getFacialHair().getValue(), limit)) {
								sb.append(target.incrementFacialHair(bodyHairIncrement));
							} else if(isSetToLimit(bodyHairIncrement, target.getFacialHair().getValue(), limit)) {
								sb.append(target.setFacialHair(limit));
							}
							break;
						default:
							break;
					}
					break;
				case TF_HAIR:
					switch(secondaryModifier) {
						case TF_MOD_SIZE:
							if(isWithinLimits(hairLengthIncrement, target.getHairRawLengthValue(), limit)) {
								sb.append(target.incrementHairLength(hairLengthIncrement));
							} else if(isSetToLimit(hairLengthIncrement, target.getHairRawLengthValue(), limit)) {
								sb.append(target.setHairLength(limit));
							}
							break;
						case TF_MOD_SIZE_SECONDARY:
							if(potency.isNegative()) {
								if(target.isNeckFluff()) {
									sb.append(target.setNeckFluff(false));
								}
							} else {
								if(!target.isNeckFluff()) {
									sb.append(target.setNeckFluff(true));
								}
							}
							break;
						default:
							break;
					}
					break;
				case TF_PENIS:
					if(target.hasPenisIgnoreDildo()) {
						switch(secondaryModifier) {
							case REMOVAL:
								sb.append(target.setPenisType(PenisType.NONE));
								break;
							case TF_MOD_SIZE:
								if(isWithinLimits(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.incrementPenisSize(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisRawSizeValue(), limit)) {
									sb.append(target.setPenisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(penisSizeIncrement, target.getPenisGirth().getValue(), limit)) {
									sb.append(target.incrementPenisGirth(penisSizeIncrement));
								} else if(isSetToLimit(penisSizeIncrement, target.getPenisGirth().getValue(), limit)) {
									sb.append(target.setPenisGirth(limit));
								}
								break;
							case TF_MOD_SIZE_TERTIARY:
								if(isWithinLimits(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
									sb.append(target.incrementTesticleSize(testicleSizeIncrement));
								} else if(isSetToLimit(testicleSizeIncrement, target.getTesticleSize().getValue(), limit)) {
									sb.append(target.setTesticleSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
									sb.append(target.incrementPenisCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getPenisRawCapacityValue(), limit)) {
									sb.append(target.setPenisCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getUrethraDepth().getValue(), limit)) {
									sb.append(target.incrementUrethraDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getUrethraDepth().getValue(), limit)) {
									sb.append(target.setUrethraDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
									sb.append(target.incrementUrethraElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getUrethraElasticity().getValue(), limit)) {
									sb.append(target.setUrethraElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
									sb.append(target.incrementUrethraPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getUrethraPlasticity().getValue(), limit)) {
									sb.append(target.setUrethraPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(cumStorageIncrement, target.getPenisRawCumStorageValue(), limit)) {
									sb.append(target.incrementPenisCumStorage(cumStorageIncrement));
								} else if(isSetToLimit(cumStorageIncrement, target.getPenisRawCumStorageValue(), limit)) {
									sb.append(target.setPenisCumStorage(limit));
								}
								break;
							case TF_MOD_REGENERATION:
								if(isWithinLimits(fluidRegenerationIncrement, target.getPenisRawCumProductionRegenerationValue(), limit)) {
									sb.append(target.incrementPenisCumProductionRegeneration(fluidRegenerationIncrement));
								} else if(isSetToLimit(fluidRegenerationIncrement, target.getPenisRawCumProductionRegenerationValue(), limit)) {
									sb.append(target.setPenisCumProductionRegeneration(limit));
								}
								break;
							case TF_MOD_CUM_EXPULSION:
								if(isWithinLimits(cumExpulsionIncrement, target.getPenisRawCumExpulsionValue(), limit)) {
									sb.append(target.incrementPenisCumExpulsion(cumExpulsionIncrement));
								} else if(isSetToLimit(cumExpulsionIncrement, target.getPenisRawCumExpulsionValue(), limit)) {
									sb.append(target.setPenisCumExpulsion(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_BODY_HAIR:
								if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.incrementPubicHair(bodyHairIncrement));
								} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.setPubicHair(limit));
								}
								break;
							default:
								break;
						}
						
					} else { // Do not have penis:
						switch(secondaryModifier) {
							case TF_TYPE_1:
								if(target.getSubspeciesOverride()!=null) {
									sb.append(target.setPenisType(PenisType.getPenisTypes(target.getSubspeciesOverrideRace()).get(0)));
								} else {
									sb.append(target.setPenisType(PenisType.getPenisTypes(target.getRace()).get(0)));
								}
								break;
							default:
								break;
						}
					}
					break;
				case TF_VAGINA:
					if(target.hasVagina()) {
						switch(secondaryModifier) {
							case REMOVAL:
								sb.append(target.setVaginaType(VaginaType.NONE));
								break;
							case TF_MOD_SIZE:
								if(isWithinLimits(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
									sb.append(target.incrementVaginaClitorisSize(clitorisSizeIncrement));
								} else if(isSetToLimit(clitorisSizeIncrement, target.getVaginaRawClitorisSizeValue(), limit)) {
									sb.append(target.setVaginaClitorisSize(limit));
								}
								break;
							case TF_MOD_SIZE_SECONDARY:
								if(isWithinLimits(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
									sb.append(target.incrementVaginaLabiaSize(labiaSizeIncrement));
								} else if(isSetToLimit(labiaSizeIncrement, target.getVaginaRawLabiaSizeValue(), limit)) {
									sb.append(target.setVaginaLabiaSize(limit));
								}
								break;
							case TF_MOD_CAPACITY:
								if(isWithinLimits(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
									sb.append(target.incrementVaginaCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getVaginaRawCapacityValue(), limit)) {
									sb.append(target.setVaginaCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH:
								if(isWithinLimits(depthIncrement, target.getVaginaDepth().getValue(), limit)) {
									sb.append(target.incrementVaginaDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getVaginaDepth().getValue(), limit)) {
									sb.append(target.setVaginaDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY:
								if(isWithinLimits(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getVaginaElasticity().getValue(), limit)) {
									sb.append(target.setVaginaElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY:
								if(isWithinLimits(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getVaginaPlasticity().getValue(), limit)) {
									sb.append(target.setVaginaPlasticity(limit));
								}
								break;
							case TF_MOD_WETNESS:
								if(isWithinLimits(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
									sb.append(target.incrementVaginaWetness(wetnessIncrement));
								} else if(isSetToLimit(wetnessIncrement, target.getVaginaWetness().getValue(), limit)) {
									sb.append(target.setVaginaWetness(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addVaginaOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasVaginaOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_CAPACITY_2:
								if(isWithinLimits(capacityIncrement, target.getVaginaUrethraRawCapacityValue(), limit)) {
									sb.append(target.incrementVaginaUrethraCapacity(capacityIncrement, true));
								} else if(isSetToLimit(capacityIncrement, target.getVaginaUrethraRawCapacityValue(), limit)) {
									sb.append(target.setVaginaUrethraCapacity(limit, true));
								}
								break;
							case TF_MOD_DEPTH_2:
								if(isWithinLimits(depthIncrement, target.getVaginaUrethraDepth().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraDepth(depthIncrement));
								} else if(isSetToLimit(depthIncrement, target.getVaginaUrethraDepth().getValue(), limit)) {
									sb.append(target.setVaginaUrethraDepth(limit));
								}
								break;
							case TF_MOD_ELASTICITY_2:
								if(isWithinLimits(elasticityIncrement, target.getVaginaUrethraElasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraElasticity(elasticityIncrement));
								} else if(isSetToLimit(elasticityIncrement, target.getVaginaUrethraElasticity().getValue(), limit)) {
									sb.append(target.setVaginaUrethraElasticity(limit));
								}
								break;
							case TF_MOD_PLASTICITY_2:
								if(isWithinLimits(plasticityIncrement, target.getVaginaUrethraPlasticity().getValue(), limit)) {
									sb.append(target.incrementVaginaUrethraPlasticity(plasticityIncrement));
								} else if(isSetToLimit(plasticityIncrement, target.getVaginaUrethraPlasticity().getValue(), limit)) {
									sb.append(target.setVaginaUrethraPlasticity(limit));
								}
								break;
							case TF_MOD_ORIFICE_PUFFY_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.PUFFY)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY));
									}
								}
								break;
							case TF_MOD_ORIFICE_RIBBED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.RIBBED)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED));
									}
								}
								break;
							case TF_MOD_ORIFICE_MUSCLED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
									}
								}
								break;
							case TF_MOD_ORIFICE_TENTACLED_2:
								if(potency == TFPotency.MINOR_BOOST || potency == TFPotency.BOOST || potency == TFPotency.MAJOR_BOOST) {
									if(!target.hasVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								} else {
									if(target.hasVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED)) {
										sb.append(target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED));
									}
								}
								break;
							case TF_MOD_BODY_HAIR:
								if(isWithinLimits(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.incrementPubicHair(bodyHairIncrement));
								} else if(isSetToLimit(bodyHairIncrement, target.getPubicHair().getValue(), limit)) {
									sb.append(target.setPubicHair(limit));
								}
								break;
							default:
								break;
						}
						
					} else { // Does not have vagina:
						switch(secondaryModifier) {
							case TF_TYPE_1:
								if(target.getSubspeciesOverride()!=null) {
									sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getSubspeciesOverrideRace()).get(0)));
								} else {
									sb.append(target.setVaginaType(VaginaType.getVaginaTypes(target.getRace()).get(0)));
								}
								break;
							default:
								break;
						}
					}
					break;
				default:
					break;
			}
		}
		
		return sb.toString();
	}
	
	private static boolean isWithinLimits(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(increment + currentValue < limit) {
				return false;
			}
		} else if(increment + currentValue > limit) {
			return false;
		}
		return true;
	}
	
	private static boolean isSetToLimit(int increment, float currentValue, int limit) {
		if(increment<0) {
			if(currentValue <= limit) {
				return false;
			}
		} else if(currentValue >= limit) {
			return false;
		}
		return true;
	}
	
	protected static List<String> descriptions = new ArrayList<>();
	protected static List<String> genericAttributeEffectDescription(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		descriptions.clear();
		
		switch(secondaryModifier) {
			default:
				switch(potency) {
					case MAJOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-15, "b")+"到“药剂效果”");
							}
						}
						break;
					case DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-10, "b")+"到“药剂效果”");
							}
						}
						break;
					case MINOR_DRAIN:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsDrain(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(-5, "b")+"到“药剂效果”");
							}
						}
						break;
					case MINOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(20, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(5, "b")+"到“药剂效果”");
							}
						}
						break;
					case BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(40, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(10, "b")+"到“药剂效果”");
							}
						}
						break;
					case SPECIAL:
					case MAJOR_BOOST:
						if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
							addResourceDescriptionsRestore(60, restorationType);
						} else {
							if(primaryModifier.getAssociatedAttribute()!=null) {
								descriptions.add(primaryModifier.getAssociatedAttribute().getFormattedValue(15, "b")+"到“药剂效果”");
							}
						}
						break;
				}
				break;
		}
		
		return descriptions;
	}
	
	private static void addResourceDescriptionsRestore(int value, ResourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldGood(回复)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
				break;
			case MANA:
					descriptions.add("[style.boldGood(回复)] "+value+"% [style.boldAura(灵气)]");
				break;
			case ALL:
					descriptions.add("[style.boldGood(回复)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
					descriptions.add("[style.boldGood(回复)] "+value+"% [style.boldAura(灵气)]");
				break;
		}
	}
	private static void addResourceDescriptionsDrain(int value, ResourceRestoration restorationType) {
		switch(restorationType){
			case HEALTH:
					descriptions.add("[style.boldBad(吸取)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
				break;
			case MANA:
					descriptions.add("[style.boldBad(吸取)] "+value+"% [style.boldAura(灵气)]");
				break;
			case ALL:
					descriptions.add("[style.boldBad(吸取)] "+value+"% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]");
					descriptions.add("[style.boldBad(吸取)] "+value+"% [style.boldAura(灵气)]");
				break;
		}
	}
	
	private static String applyRestoration(GameCharacter target, ResourceRestoration restorationType, float percentage) {
		switch(restorationType) {
			case ALL:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
			case HEALTH:
				target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*percentage);
				break;
			case MANA:
				target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*percentage);
				break;
		}
		
		if(percentage > 0) {
			if(target.isPlayer()) {
				return "一股舒缓的温暖感传遍你的全身，伴随着深深满足的叹息，你发现自己比刚刚一会儿前感觉好多了。";
			} else {
				return UtilText.parse(target, "[npc.Name]发出一声舒适的叹息，并且你注意到[npc.sheIs]突然看起来比[npc.she]刚才健康了很多。");
			}
		} else {
			if(target.isPlayer()) {
				return "一股病态的温暖感弥漫在你全身，伴随着虚弱的咳嗽，你发现自己比刚刚一会儿前感觉更虚弱了。";
			} else {
				return UtilText.parse(target, "[npc.Name]发出虚弱的咳嗽声，你注意到[npc.she]突然看起来比刚刚一会儿前虚弱了一些。");
			}
		}
	}
	
	protected static String genericAttributeEffect(ResourceRestoration restorationType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
			switch(potency) {
				case MAJOR_DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.6f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "一道令人恶心的奥术能量洗刷了[npc.name]……")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -15));
						}
					}
					break;
				case DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.4f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "一道令人恶心的奥术能量洗刷了[npc.name]……")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -10));
						}
					}
					break;
				case MINOR_DRAIN:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, -0.2f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "一道令人恶心的奥术能量洗刷了[npc.name]……")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), -5));
						}
					}
					break;
				case MINOR_BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.2f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "一道让人宁静的奥术能量洗刷了[npc.name]……")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 5));
						}
					}
					break;
				case BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.4f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "一道让人宁静的奥术能量洗刷了[npc.name]……")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 10));
						}
					}
					break;
				case SPECIAL:
				case MAJOR_BOOST:
					if(primaryModifier==null || primaryModifier==TFModifier.NONE) {
						sb.append(applyRestoration(target, restorationType, 0.6f));
					} else {
						if(primaryModifier.getAssociatedAttribute()!=null) {
							sb.append(UtilText.parse(target, "一道让人宁静的奥术能量洗刷了[npc.name]……")
									+ "<br/>"
									+ target.addPotionEffect(primaryModifier.getAssociatedAttribute(), 15));
						}
					}
					break;
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	// Caching:
	private static Map<AbstractRace, Map<TFModifier, LinkedHashMap<TFModifier, List<TFPotency>>>> racialPrimaryModSecondaryModPotencyGrid = new HashMap<>();
	
	protected static List<TFModifier> getRacialSecondaryModifiers(AbstractRace race, TFModifier primaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.containsKey(race) && racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).keySet());
		}
	}
	
	protected static List<TFPotency> getRacialPotencyModifiers(AbstractRace race, TFModifier primaryModifier, TFModifier secondaryModifier) {
		if(racialPrimaryModSecondaryModPotencyGrid.get(race).containsKey(primaryModifier)) {
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		} else {
			populateGrid(race, primaryModifier);
			return new ArrayList<>(racialPrimaryModSecondaryModPotencyGrid.get(race).get(primaryModifier).get(secondaryModifier));
		}
	}
	
	private static void populateGrid(AbstractRace race, TFModifier primaryModifier) {
		LinkedHashMap<TFModifier, List<TFPotency>> secondaryModPotencyMap = new LinkedHashMap<>();
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< RacialBody.valueOfRace(race).getAntennaTypes(true).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, TFPotency.getAllPotencies());
				break;
			
			case TF_ARMS:
				for(int i=0; i< ArmType.getArmTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				if(Main.game.isBodyHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_ASS:
				for(int i=0; i< AssType.getAssTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				if(Main.game.isAssHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				if(Main.game.isAnalContentEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_BREASTS: case TF_BREASTS_CROTCH:
				for(int i=0; i< BreastType.getBreastTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				if(primaryModifier==TFModifier.TF_BREASTS_CROTCH) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_UDDERS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_ROUND, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_POINTY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_PERKY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_SIDESET, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_WIDE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_BREAST_SHAPE_NARROW, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_NORMAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_INVERTED, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_VAGINA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_NIPPLE_LIPS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_AREOLAE_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));

				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				}
				if(Main.game.isLactationContentEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				if(Main.game.isNipplePenEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_CORE:
				secondaryModPotencyMap.put(TFModifier.TF_TYPE_1, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FEMININITY, TFPotency.getAllPotencies());
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST, TFPotency.BOOST));
				break;
				
			case TF_EARS:
				for(int i=0; i< EarType.getEarTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_EYES:
				for(int i=0; i< EyeType.getEyeTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_VERTICAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HORIZONTAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_IRIS_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_CIRCLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_VERTICAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HORIZONTAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_HEART, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_EYE_PUPIL_STAR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			case TF_FACE:
				for(int i=0; i< FaceType.getFaceTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				if(Main.game.isPenetrationLimitationsEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_BIFURCATED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_WIDE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_FLAT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_TONGUE_STRONG, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isFacialHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_HAIR:
				for(int i=0; i< HairType.getHairTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				break;
				
			case TF_HORNS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
//				for(int i=0; i< RacialBody.valueOfRace(race).getHornTypes(true).size();i++) {
				for(int i=0; i< HornType.getHornTypes(race, false).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT_SECONDARY, TFPotency.getAllPotencies());
				break;
				
			case TF_LEGS:
				for(int i=0; i< LegType.getLegTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.BIPEDAL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAUR, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.TAIL_LONG))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.TAIL))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_TAIL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.ARACHNID))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_ARACHNID, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.CEPHALOPOD))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.isLegConfigurationAvailable(LegConfiguration.AVIAN))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_LEG_CONFIG_AVIAN, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.PLANTIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_PLANTIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.DIGITIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_DIGITIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.getFootType().getPermittedFootStructures(null).contains(FootStructure.UNGULIGRADE))) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_FOOT_STRUCTURE_UNGULIGRADE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				if(LegType.getLegTypes(race).stream().anyMatch(lt->lt.hasSpinneret())) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_PENIS:
				for(int i=0; i< PenisType.getPenisTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BLUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_OVIPOSITOR, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				if(Main.game.isCumRegenerationEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_REGENERATION, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CUM_EXPULSION, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_INTERNAL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_SKIN:
				for(int i=0; i< TorsoType.getTorsoTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				break;
				
			case TF_TAIL:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< TailType.getTailTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_COUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

				if(RacialBody.valueOfRace(race).getTailType().stream().anyMatch(tt->tt.hasSpinneret())) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));

					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				}
				break;
				
			case TF_TENTACLE:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				break;
				
			case TF_VAGINA:
				for(int i=0; i< VaginaType.getVaginaTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE_TERTIARY, TFPotency.getAllPotencies());

				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_SQUIRTER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_VAGINA_EGG_LAYER, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY, TFPotency.getAllPotencies());
				if(Main.game.isPenetrationLimitationsEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH, TFPotency.getAllPotencies());
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY, TFPotency.getAllPotencies());
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());

				if(Main.game.isPubicHairEnabled()) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_BODY_HAIR, TFPotency.getAllPotencies());
				}
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				if(Main.getProperties().hasValue(PropertyValue.urethralContent)) {
					secondaryModPotencyMap.put(TFModifier.TF_MOD_CAPACITY_2, TFPotency.getAllPotencies());
					if(Main.game.isPenetrationLimitationsEnabled()) {
						secondaryModPotencyMap.put(TFModifier.TF_MOD_DEPTH_2, TFPotency.getAllPotencies());
					}
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ELASTICITY_2, TFPotency.getAllPotencies());
					secondaryModPotencyMap.put(TFModifier.TF_MOD_PLASTICITY_2, TFPotency.getAllPotencies());
	
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_PUFFY_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_RIBBED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_MUSCLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
					secondaryModPotencyMap.put(TFModifier.TF_MOD_ORIFICE_TENTACLED_2, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				}

				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BARBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_FLARED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_BLUNT, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_KNOTTED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_PREHENSILE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_RIBBED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_SHEATHED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TAPERED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_TENTACLED, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_VEINY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_PENIS_OVIPOSITOR, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				
				break;
				
			case TF_WINGS:
				secondaryModPotencyMap.put(TFModifier.REMOVAL, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				for(int i=0; i< WingType.getWingTypes(race).size();i++) {
					secondaryModPotencyMap.put(TFModifier.valueOf("TF_TYPE_"+(i+1)), Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				}
				secondaryModPotencyMap.put(TFModifier.TF_MOD_SIZE, TFPotency.getAllPotencies());
				break;
				
			case TF_CUM: case TF_MILK: case TF_MILK_CROTCH: case TF_GIRLCUM:
				secondaryModPotencyMap.put(TFModifier.TF_MOD_WETNESS, TFPotency.getAllPotencies());
				
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ADDICTIVE, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ALCOHOLIC, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_ALCOHOLIC_WEAK, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_BUBBLING, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_HALLUCINOGENIC, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MINERAL_OIL, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_MUSKY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_SLIMY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_STICKY, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLUID_VISCOUS, Util.newArrayListOfValues(TFPotency.MINOR_DRAIN, TFPotency.MINOR_BOOST));
				

				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GIRLCUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MILK, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_FLAVOURLESS, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BEER, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHOCOLATE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_HONEY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MINT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_PINEAPPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BUBBLEGUM, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_STRAWBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CHERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_VANILLA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_COFFEE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_TEA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MAPLE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_CINNAMON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_LEMON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_ORANGE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_GRAPE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_MELON, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_COCONUT, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BLUEBERRY, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				secondaryModPotencyMap.put(TFModifier.TF_MOD_FLAVOUR_BANANA, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
				
			default:
				secondaryModPotencyMap.put(TFModifier.NONE, Util.newArrayListOfValues(TFPotency.MINOR_BOOST));
				break;
		}
		
		racialPrimaryModSecondaryModPotencyGrid.put(race, Util.newHashMapOfValues(new Value<>(primaryModifier, secondaryModPotencyMap)));
	}
	
	// And in the comments these words appear: 'My name is Innoxia, creator of smut: Look on my methods, ye Modders, and despair!'
	// Contributor's comment: OH GOD WHY
	// Innoxia's comment: Because you must suffer!
	
	private static int smallChangeMajorDrain = -3;
	private static int smallChangeDrain = -2;
	private static int smallChangeMinorDrain = -1;
	private static int smallChangeMinorBoost = 1;
	private static int smallChangeBoost = 2;
	private static int smallChangeMajorBoost = 3;
	
	private static int mediumChangeMajorDrain = -15;
	private static int mediumChangeDrain = -5;
	private static int mediumChangeMinorDrain = -1;
	private static int mediumChangeMinorBoost = 1;
	private static int mediumChangeBoost = 5;
	private static int mediumChangeMajorBoost = 15;
	
	private static int largeChangeMajorDrain = -50;
	private static int largeChangeDrain = -15;
	private static int largeChangeMinorDrain = -5;
	private static int largeChangeMinorBoost = 5;
	private static int largeChangeBoost = 15;
	private static int largeChangeMajorBoost = 50;

	private static int hugeChangeMajorDrain = -500;
	private static int hugeChangeDrain = -100;
	private static int hugeChangeMinorDrain = -25;
	private static int hugeChangeMinorBoost = 25;
	private static int hugeChangeBoost = 100;
	private static int hugeChangeMajorBoost = 500;
	
	private static int singleDrain = -1;
	private static int singleBoost = 1;
	
	private static int modifierTypeToInt(TFModifier modifier) {
		List<TFModifier> modifierList = Util.newArrayListOfValues(
				TFModifier.TF_TYPE_1,
				TFModifier.TF_TYPE_2,
				TFModifier.TF_TYPE_3,
				TFModifier.TF_TYPE_4,
				TFModifier.TF_TYPE_5,
				TFModifier.TF_TYPE_6,
				TFModifier.TF_TYPE_7,
				TFModifier.TF_TYPE_8,
				TFModifier.TF_TYPE_9,
				TFModifier.TF_TYPE_10);
		
		if(modifierList.contains(modifier)) {
			return modifierList.indexOf(modifier);
		}
		return 0;
	}
	
	protected static RacialEffectUtil getRacialEffect(AbstractRace race, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, GameCharacter user, GameCharacter target) {
		
		boolean revealTransformedPart = user!=null && target!=null && !user.equals(target);
		
		switch(primaryModifier) {
			case TF_ANTENNA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]触须长度(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]触须长度(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]触须长度(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]触须长度(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]触须长度(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]触须长度(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAntennaLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除额外的一对触须。") { @Override public String applyEffect() { return target.incrementAntennaRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一对额外的触须。") { @Override public String applyEffect() {
									List<AbstractAntennaType> antennaTypesSuitableForTransformation = RacialBody.valueOfRace(race).getAntennaTypes(true);
									if(target.getAntennaType().equals(AntennaType.NONE) && !antennaTypesSuitableForTransformation.isEmpty()) {
										return target.setAntennaType(antennaTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementAntennaRows(singleBoost);
									} } };
						}
						
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]每列触须(" + smallChangeMajorDrain + "列触须)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]每列触须(" + smallChangeDrain + "列触须)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]每列触须(" + smallChangeMinorDrain + "列触须)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]每列触须(+" + smallChangeMinorBoost + "列触须)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]每列触须(+" + smallChangeBoost + "列触须)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]每列触须(+" + smallChangeMajorBoost + "列触须)") { @Override public String applyEffect() { return target.incrementAntennaePerRow(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("移除触须") { @Override public String applyEffect() { return target.setAntennaType(AntennaType.NONE); } };
	
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = modifierTypeToInt(secondaryModifier);
						return getAntennaTypeRacialEffectUtil(race, target, index);
							
					default:
						List<AbstractAntennaType> antennaTypes = RacialBody.valueOfRace(race).getAntennaTypes(true);
						AbstractAntennaType antennaType = antennaTypes.isEmpty()?AntennaType.NONE:Util.randomItemFrom(antennaTypes);
						return new RacialEffectUtil(antennaType.equals(AntennaType.NONE)?"移除触须。":Util.capitaliseSentence(race.getName(false))+"触须转化。") {
							@Override public String applyEffect() { return target.setAntennaType(antennaType); } };
				}
			
			case TF_ARMS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(ArmType.getArmTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(ArmType.getArmTypes(race).get(index).getTransformName())+"手臂转化。") {
							@Override public String applyEffect() { return target.setArmType(ArmType.getArmTypes(race).get(index)); } };
	
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一对额外的手臂") { @Override public String applyEffect() { return target.incrementArmRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一对额外的手臂。") { @Override public String applyEffect() { return target.incrementArmRows(singleBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("移除巨量的腋毛。(" + smallChangeMajorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("移除大量的腋毛。(" + smallChangeDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一些腋毛。(" + smallChangeMinorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一些腋毛。(+" + smallChangeMinorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("增加大量的腋毛。(+" + smallChangeBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("增加巨量的腋毛。(+" + smallChangeMajorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementUnderarmHair(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"手臂转化。") { @Override public String applyEffect() { return target.setArmType(RacialBody.valueOfRace(race).getArmType()); } };
				}
				
			case TF_ASS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(AssType.getAssTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(AssType.getAssTypes(race).get(index).getTransformName())+"屁股转化。") {
							@Override public String applyEffect() {
								String tfText = target.setAssType(AssType.getAssTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.ASS, target, true);
									user.setKnowsCharacterArea(CoverableArea.ANUS, target, true);
								}
								return tfText;
							} };
	
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]屁股尺寸(" + smallChangeMajorDrain + "屁股尺寸)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]屁股尺寸(" + smallChangeDrain + "屁股尺寸)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]屁股尺寸(" + smallChangeMinorDrain + "屁股尺寸)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]屁股尺寸(+" + smallChangeMinorBoost + "屁股尺寸)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]屁股尺寸(+" + smallChangeBoost + "屁股尺寸)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]屁股尺寸(+" + smallChangeMajorBoost + "屁股尺寸)") { @Override public String applyEffect() { return target.incrementAssSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]臀部尺寸(" + smallChangeMajorDrain + "臀部尺寸)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]臀部尺寸(" + smallChangeDrain + "臀部尺寸)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]臀部尺寸(" + smallChangeMinorDrain + "臀部尺寸)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]臀部尺寸(+" + smallChangeMinorBoost + "臀部尺寸)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]臀部尺寸(+" + smallChangeBoost + "臀部尺寸)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]臀部尺寸(+" + smallChangeMajorBoost + "臀部尺寸)") { @Override public String applyEffect() { return target.incrementHipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("移除巨量的肛毛。(" + smallChangeMajorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("移除大量的肛毛。(" + smallChangeDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一些肛毛。(" + smallChangeMinorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一些肛毛。(+" + smallChangeMinorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("增加大量的肛毛。(+" + smallChangeBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("增加巨量的肛毛(+" + smallChangeMajorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementAssHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]肛门容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]肛门容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]肛门容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]肛门容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]肛门容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]肛门容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementAssCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]肛门深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]肛门深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]肛门深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]肛门深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]肛门深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]肛门深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementAssDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]肛门弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]肛门弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]肛门弹性等级(" + smallChangeMinorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]肛门弹性等级(+" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]肛门弹性等级(+" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]肛门弹性等级(+" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementAssElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]肛门可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]肛门可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]肛门可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]肛门可塑性等级(+" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]肛门可塑性等级(+" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]肛门可塑性等级(+" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementAssPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]肛门润滑程度(" + smallChangeMajorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]肛门润滑程度(" + smallChangeDrain + "湿度)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]肛门润滑程度(" + smallChangeMinorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]肛门润滑程度(+" + smallChangeMinorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]肛门润滑程度(+" + smallChangeBoost + "湿度)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]肛门润滑程度(+" + smallChangeMajorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementAssWetness(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("去除肛门边缘的额外肉感。") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使肛门边缘更加肉感。") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除肛门内部的螺纹。") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在肛门内部添加螺纹。") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("去除肛门多余的内部肌肉。") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为肛门增加额外的内部肌肉。") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除肛门内部触手。") { @Override public String applyEffect() { return target.removeAssOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在肛门内部添加触手。") { @Override public String applyEffect() { return target.addAssOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"屁股转化。") { @Override public String applyEffect() {
							String tfText = target.setAssType(RacialBody.valueOfRace(race).getAssType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.ASS, target, true);
								user.setKnowsCharacterArea(CoverableArea.ANUS, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_BREASTS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(BreastType.getBreastTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(index).getTransformName())+"乳房转化。") {
							@Override public String applyEffect() {
								String tfText = target.setBreastType(BreastType.getBreastTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.BREASTS, target, true);
									user.setKnowsCharacterArea(CoverableArea.NIPPLES, target, true);
								}
								return tfText;
							} };
	
					case REMOVAL:
						return new RacialEffectUtil("将乳房完全变平。") {
							@Override public String applyEffect() { return target.setBreastSize(0); } };
							
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一对多余的乳房。") { @Override public String applyEffect() { return target.incrementBreastRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一对额外的乳房。") { @Override public String applyEffect() { return target.incrementBreastRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("从每个乳房上移除一个多余的乳头。") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("向每个乳房上增加一个额外的乳头。") { @Override public String applyEffect() { return target.incrementNippleCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳房尺寸(" + smallChangeMajorDrain + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳房尺寸(" + smallChangeDrain + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳房尺寸(" + smallChangeMinorDrain + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳房尺寸(+" + smallChangeMinorBoost + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳房尺寸(+" + smallChangeBoost + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳房尺寸(+" + smallChangeMajorBoost + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头尺寸(" + smallChangeMajorDrain + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头尺寸(" + smallChangeDrain + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头尺寸(" + smallChangeMinorDrain + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头尺寸(+" + smallChangeMinorBoost + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头尺寸(+" + smallChangeBoost + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头尺寸(+" + smallChangeMajorBoost + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleSize(smallChangeMajorBoost); } };
						}

					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("使胸部形状变圆") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("使胸部形状变得挺拔。") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("使胸部形状变尖。") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("使胸部变得向两侧分开。") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("使胸部形状变宽。") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("使胸部形状变窄。") { @Override public String applyEffect() { return target.setBreastShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("使胸部变成正常形状。") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_INVERTED:
						return new RacialEffectUtil("将乳头变成正常的形状，但是是内凹进去的。") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.INVERTED); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("将乳头变成乳穴。") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("将乳头变成一对嘴唇。") { @Override public String applyEffect() { return target.setNippleShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳晕尺寸(" + smallChangeMajorDrain + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳晕尺寸(" + smallChangeDrain + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳晕尺寸(" + smallChangeMinorDrain + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳晕尺寸(+" + smallChangeMinorBoost + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳晕尺寸(+" + smallChangeBoost + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳晕尺寸(+" + smallChangeMajorBoost + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("使乳晕变成正常的形状。") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("使乳晕的形状变为心形。") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("使乳晕变成星星形状。") { @Override public String applyEffect() { return target.setAreolaeShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementNippleDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头弹性等级(" + smallChangeMinorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头弹性等级(+" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头弹性等级(+" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头弹性等级(+" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头可塑性等级(+" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头可塑性等级(+" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头可塑性等级(+" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNipplePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳汁储量(" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳汁储量(" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳汁储量(" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳汁储量(+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳汁储量(+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳汁储量(+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastLactationRegeneration(hugeChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头的额外肉感。") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使乳头更加肉感。") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头内部的螺纹。") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在乳头内部添加螺纹。") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头内的额外肌肉。") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使乳头内部生长肌肉。") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头内部触手。") { @Override public String applyEffect() { return target.removeNippleOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在乳头内部添加触手。") { @Override public String applyEffect() { return target.addNippleOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"胸部转化。") { @Override public String applyEffect() {
							String tfText = target.setBreastType(RacialBody.valueOfRace(race).getBreastType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.BREASTS, target, true);
								user.setKnowsCharacterArea(CoverableArea.NIPPLES, target, true);
							}
							return tfText;
						} };
				}

			case TF_BREASTS_CROTCH:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(BreastType.getBreastTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(BreastType.getBreastTypes(race).get(index).getTransformName())+"胯乳转化。") { @Override public String applyEffect() {
							String tfText = target.setBreastCrotchType(BreastType.getBreastTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.BREASTS_CROTCH, target, true);
									user.setKnowsCharacterArea(CoverableArea.NIPPLES_CROTCH, target, true);
								}
								return tfText;
							} };
	
					case REMOVAL:
						return new RacialEffectUtil("移除胯乳。") {
							@Override public String applyEffect() {
								String tfText = target.setBreastCrotchType(BreastType.NONE);
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.BREASTS_CROTCH, target, true);
									user.setKnowsCharacterArea(CoverableArea.NIPPLES_CROTCH, target, true);
								}
								return tfText;
							} };
							
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一对多余的胯乳。") { @Override public String applyEffect() { return target.incrementBreastCrotchRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一对额外的胯乳。") { @Override public String applyEffect() { return target.incrementBreastCrotchRows(singleBoost); } };
						}
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("从每个胯乳上移除一个多余的乳头。") { @Override public String applyEffect() {
									return target.incrementNippleCrotchCountPerBreast(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为每个胯乳上增加一个额外的乳头。") { @Override public String applyEffect() {
									return target.incrementNippleCrotchCountPerBreast(singleBoost); } };
						}
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]胯乳尺寸(" + smallChangeMajorDrain + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]胯乳尺寸(" + smallChangeDrain + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]胯乳尺寸(" + smallChangeMinorDrain + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]胯乳尺寸(" + smallChangeMinorBoost + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]胯乳尺寸(" + smallChangeBoost + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]胯乳尺寸(" + smallChangeMajorBoost + "乳房尺寸)") { @Override public String applyEffect() { return target.incrementBreastCrotchSize(smallChangeMajorBoost); } };
						}
						

					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头尺寸(" + smallChangeMajorDrain + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头尺寸(" + smallChangeDrain + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头尺寸(" + smallChangeMinorDrain + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头尺寸(" + smallChangeMinorBoost + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头尺寸(" + smallChangeBoost + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头尺寸(" + smallChangeMajorBoost + "乳头尺寸)") { @Override public String applyEffect() { return target.incrementNippleCrotchSize(smallChangeMajorBoost); } };
						}

					case TF_MOD_BREAST_SHAPE_UDDERS:
						return new RacialEffectUtil("将胯乳变为腹乳。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.UDDERS); } };
					case TF_MOD_BREAST_SHAPE_ROUND:
						return new RacialEffectUtil("将胯乳的形状变圆。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.ROUND); } };
					case TF_MOD_BREAST_SHAPE_PERKY:
						return new RacialEffectUtil("使胯乳形状变得挺拔。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.PERKY); } };
					case TF_MOD_BREAST_SHAPE_POINTY:
						return new RacialEffectUtil("使胯乳形状变尖。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.POINTY); } };
					case TF_MOD_BREAST_SHAPE_SIDESET:
						return new RacialEffectUtil("使胯乳变得向两侧分开。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.SIDE_SET); } };
					case TF_MOD_BREAST_SHAPE_WIDE:
						return new RacialEffectUtil("使胯乳形状变宽。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.WIDE); } };
					case TF_MOD_BREAST_SHAPE_NARROW:
						return new RacialEffectUtil("使胯乳形状变窄。") { @Override public String applyEffect() { return target.setBreastCrotchShape(BreastShape.NARROW); } };
						
					case TF_MOD_NIPPLE_NORMAL:
						return new RacialEffectUtil("使乳头变成正常形状。") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.NORMAL); } };
					case TF_MOD_NIPPLE_INVERTED:
						return new RacialEffectUtil("将乳头变成正常的形状，但是是内凹进去的。") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.INVERTED); } };
					case TF_MOD_NIPPLE_VAGINA:
						return new RacialEffectUtil("将乳头变成乳穴。") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.VAGINA); } };
					case TF_MOD_NIPPLE_LIPS:
						return new RacialEffectUtil("将乳头变成一对嘴唇。") { @Override public String applyEffect() { return target.setNippleCrotchShape(NippleShape.LIPS); } };
						
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳晕尺寸(" + smallChangeMajorDrain + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳晕尺寸(" + smallChangeDrain + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳晕尺寸(" + smallChangeMinorDrain + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳晕尺寸(" + smallChangeMinorBoost + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳晕尺寸(" + smallChangeBoost + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳晕尺寸(" + smallChangeMajorBoost + "乳晕尺寸)") { @Override public String applyEffect() { return target.incrementAreolaeCrotchSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_AREOLAE_CIRCLE:
						return new RacialEffectUtil("使乳晕变成正常的形状。") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.NORMAL); } };
					case TF_MOD_AREOLAE_HEART:
						return new RacialEffectUtil("使乳晕的形状变为心形。") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.HEART); } };
					case TF_MOD_AREOLAE_STAR:
						return new RacialEffectUtil("使乳晕变成星星形状。") { @Override public String applyEffect() { return target.setAreolaeCrotchShape(AreolaeShape.STAR); } };
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头容量(" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头容量(" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头容量(" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementNippleCrotchCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementNippleCrotchDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头弹性等级(" + smallChangeMinorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头弹性等级(" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头弹性等级(" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头弹性等级(" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳头可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳头可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳头可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳头可塑性等级(" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳头可塑性等级(" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳头可塑性等级(" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementNippleCrotchPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳汁储量(" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳汁储量(" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳汁储量(" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳汁储量(" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳汁储量(" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳汁储量(" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳汁再生速率") { @Override public String applyEffect() { return target.incrementBreastCrotchLactationRegeneration(hugeChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头的额外肉感。") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使乳头更加肉感。") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头内部的螺纹。") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在乳头内部添加螺纹。") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头内的额外肌肉。") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使乳头内部生长肌肉。") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除乳头内部触手。") { @Override public String applyEffect() { return target.removeNippleCrotchOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在乳头内部添加触手。") { @Override public String applyEffect() { return target.addNippleCrotchOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"胯乳转化。") { @Override public String applyEffect() {
							String tfText = target.setBreastCrotchType(RacialBody.valueOfRace(race).getBreastType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.BREASTS_CROTCH, target, true);
								user.setKnowsCharacterArea(CoverableArea.NIPPLES_CROTCH, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_CORE: 
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]身高(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorDrain, false); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]身高(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeDrain, false); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]身高(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]身高(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMinorBoost, false); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]身高(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeBoost, false); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]身高(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHeight(mediumChangeMajorBoost, false); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]肌肉质量(" + mediumChangeMajorDrain + "肌肉)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]肌肉质量(" + mediumChangeDrain + "肌肉)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]肌肉质量(" + mediumChangeMinorDrain + "肌肉)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]肌肉质量(+" + mediumChangeMinorBoost + "肌肉)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]肌肉质量(+" + mediumChangeBoost + "肌肉)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]肌肉质量(+" + mediumChangeMajorBoost + "肌肉)") { @Override public String applyEffect() { return target.incrementMuscle(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]身体尺寸(" + mediumChangeMajorDrain + "身体尺寸)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]身体尺寸(" + mediumChangeDrain + "身体尺寸)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]身体尺寸(" + mediumChangeMinorDrain + "身体尺寸)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]身体尺寸(+" + mediumChangeMinorBoost + "身体尺寸)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]身体尺寸(+" + mediumChangeBoost + "身体尺寸)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]身体尺寸(+" + mediumChangeMajorBoost + "身体尺寸)") { @Override public String applyEffect() { return target.incrementBodySize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_FEMININITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]女性化程度(" + mediumChangeMajorDrain + "女性化程度)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]女性化程度(" + mediumChangeDrain + "女性化程度)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]女性化程度(" + mediumChangeMinorDrain + "女性化程度)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]女性化程度(+" + mediumChangeMinorBoost + "女性化程度)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]女性化程度(+" + mediumChangeBoost + "女性化程度)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]女性化程度(+" + mediumChangeMajorBoost + "女性化程度)") { @Override public String applyEffect() { return target.incrementFemininity(mediumChangeMajorBoost); } };
						}
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("移除巨量的阴毛。(" + smallChangeMajorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("移除大量的阴毛。(" + smallChangeDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一些阴毛。(" + smallChangeMinorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一些阴毛。(+" + smallChangeMinorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("增加大量的阴毛。(+" + smallChangeBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("增加巨量的阴毛(+" + smallChangeMajorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除泄殖腔，使得生殖器和肛门外露。") { @Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.NORMAL); } };
							case BOOST:
								return new RacialEffectUtil("将生殖器和肛门转化为泄殖腔并合并在肛门的位置。") {
									@Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA_BEHIND); }
								};
							case MINOR_BOOST:
								default:
								return new RacialEffectUtil("将生殖器和肛门转化为泄殖腔并合并在生殖器的位置。") {
									@Override public String applyEffect() { return target.setGenitalArrangement(GenitalArrangement.CLOACA); }
								};
						}
						
					default:
						return new RacialEffectUtil("随机"+race.getName(false)+"转化") {
							@Override
							public String applyEffect() {
								List<TFModifier> availableModifiers = new ArrayList<>();
								
								// Only add TFModifiers which will do something:
								for(TFModifier tfMod : TFModifier.getTFRacialBodyPartsList()) {
									boolean add = false;
									switch(tfMod) {
										case TF_ANTENNA:
											add = target.getAntennaType() != race.getRacialBody().getAntennaTypes(false).get(0);
											break;
										case TF_ARMS:
											add = target.getArmType() != race.getRacialBody().getArmType();
											break;
										case TF_ASS:
											add = target.getAssType() != race.getRacialBody().getAssType();
											break;
										case TF_BREASTS:
											add = target.getBreastType() != race.getRacialBody().getBreastType();
											break;
										case TF_BREASTS_CROTCH:
											add = target.hasBreastsCrotch() && target.getBreastCrotchType() != race.getRacialBody().getBreastCrotchType();
											break;
										case TF_CORE:
											break;
										case TF_EARS:
											add = target.getEarType() != race.getRacialBody().getEarType();
											break;
										case TF_EYES:
											add = target.getEyeType() != race.getRacialBody().getEyeType();
											break;
										case TF_FACE:
											add = target.getFaceType() != race.getRacialBody().getFaceType();
											break;
										case TF_HAIR:
											add = target.getHairType() != race.getRacialBody().getHairType();
											break;
										case TF_HORNS:
											add = target.getHornType() != race.getRacialBody().getHornTypes(false).get(0);
											break;
										case TF_LEGS:
											add = target.getLegType() != race.getRacialBody().getLegType();
											break;
										case TF_PENIS:
											add = target.hasPenisIgnoreDildo() && target.getPenisType() != race.getRacialBody().getPenisType();
											break;
										case TF_SKIN:
											add = target.getTorsoType() != race.getRacialBody().getTorsoType();
											break;
										case TF_TAIL:
											add = target.getTailType() != race.getRacialBody().getTailType().get(0);
											break;
										case TF_TENTACLE:
											break;
										case TF_VAGINA:
											add = target.hasVagina() && target.getVaginaType() != race.getRacialBody().getVaginaType();
											break;
										case TF_WINGS:
											add = target.getWingType() != race.getRacialBody().getWingTypes().get(0);
											break;
										default:
											break;
									}
									if(add) {
										availableModifiers.add(tfMod);
									}
								}
								
								if(availableModifiers.isEmpty()) {
									return UtilText.parse(target, "<p style='text-align:center'>[style.italicsDisabled([npc.NameHasFull]没有可用的随机"+race.getName(true)+"转化，所以什么都没发生……)]</p>");
								}
								
								
								TFModifier mod = availableModifiers.get(Util.random.nextInt(availableModifiers.size()));
								
								// If race does not have antenna, horns, tail, wings, or crotch-boobs, make sure that the TF is to remove:
								if((mod==TFModifier.TF_ANTENNA && race.getRacialBody().getAntennaTypes(false).size()==1 && race.getRacialBody().getAntennaTypes(false).contains(AntennaType.NONE))
										|| (mod==TFModifier.TF_HORNS && race.getRacialBody().getHornTypes(false).size()==1 && race.getRacialBody().getHornTypes(false).contains(HornType.NONE))
										|| (mod==TFModifier.TF_TAIL && race.getRacialBody().getTailType().size()==1 && race.getRacialBody().getTailType().contains(TailType.NONE))
										|| (mod==TFModifier.TF_WINGS && race.getRacialBody().getWingTypes().size()==1 && race.getRacialBody().getWingTypes().contains(WingType.NONE))
										|| ((mod==TFModifier.TF_BREASTS_CROTCH && race.getRacialBody().getBreastCrotchType()==BreastType.NONE))) {
									return getRacialEffect(race, mod, TFModifier.REMOVAL, potency, user, target).applyEffect();
								}
								
								return getRacialEffect(race, mod, secondaryModifier, potency, user, target).applyEffect();
							}
						};
				}
				
			case TF_EARS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(EarType.getEarTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(EarType.getEarTypes(race).get(index).getTransformName())+"耳朵转化。") {
							@Override public String applyEffect() { return target.setEarType(EarType.getEarTypes(race).get(index)); } };
	
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"耳朵转化。") { @Override public String applyEffect() { return target.setEarType(RacialBody.valueOfRace(race).getEarType()); } };
				}
				
			case TF_EYES:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(EyeType.getEyeTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(EyeType.getEyeTypes(race).get(index).getTransformName())+"眼部转化。") {
							@Override public String applyEffect() { return target.setEyeType(EyeType.getEyeTypes(race).get(index)); } };
						
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一对多余的眼。") { @Override public String applyEffect() { return target.incrementEyePairs(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("额外增加一对眼。") { @Override public String applyEffect() { return target.incrementEyePairs(singleBoost); } };
						}

					case TF_MOD_EYE_IRIS_CIRCLE:
						return new RacialEffectUtil("使虹膜呈圆形。") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_IRIS_VERTICAL:
						return new RacialEffectUtil("使虹膜空洞呈竖长条形。") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_IRIS_HORIZONTAL:
						return new RacialEffectUtil("使虹膜空洞呈横长条形。") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_IRIS_HEART:
						return new RacialEffectUtil("使虹膜呈心形。") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.HEART); } };
					case TF_MOD_EYE_IRIS_STAR:
						return new RacialEffectUtil("使虹膜呈星形。") { @Override public String applyEffect() { return target.setIrisShape(EyeShape.STAR); } };
						
					case TF_MOD_EYE_PUPIL_CIRCLE:
						return new RacialEffectUtil("使瞳孔呈圆形。") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.ROUND); } };
					case TF_MOD_EYE_PUPIL_VERTICAL:
						return new RacialEffectUtil("使瞳孔呈竖长条形。") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.VERTICAL); } };
					case TF_MOD_EYE_PUPIL_HORIZONTAL:
						return new RacialEffectUtil("使瞳孔呈横长条形。") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HORIZONTAL); } };
					case TF_MOD_EYE_PUPIL_HEART:
						return new RacialEffectUtil("使瞳孔呈心形。") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.HEART); } };
					case TF_MOD_EYE_PUPIL_STAR:
						return new RacialEffectUtil("使瞳孔呈星形。") { @Override public String applyEffect() { return target.setPupilShape(EyeShape.STAR); } };
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"眼部转化。") { @Override public String applyEffect() { return target.setEyeType(RacialBody.valueOfRace(race).getEyeType()); } };
				}
				
			case TF_FACE:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(FaceType.getFaceTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(FaceType.getFaceTypes(race).get(index).getTransformName())+"脸部转化。") {
							@Override public String applyEffect() { return target.setFaceType(FaceType.getFaceTypes(race).get(index)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]嘴唇尺寸(" + smallChangeMajorDrain + "嘴唇尺寸)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]嘴唇尺寸(" + smallChangeDrain + "嘴唇尺寸)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]嘴唇尺寸(" + smallChangeMinorDrain + "嘴唇尺寸)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]嘴唇尺寸(+" + smallChangeMinorBoost + "嘴唇尺寸)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]嘴唇尺寸(+" + smallChangeBoost + "嘴唇尺寸)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]嘴唇尺寸(+" + smallChangeMajorBoost + "嘴唇尺寸)") { @Override public String applyEffect() { return target.incrementLipSize(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除嘴唇的额外肉感。") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使嘴唇更加肉感。") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除喉咙内部的螺纹。") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在喉咙内部添加螺纹。") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除喉咙内的额外肌肉") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使喉咙上生长出肌肉。") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除喉咙内的触手") { @Override public String applyEffect() { return target.removeFaceOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在喉咙内添加触手") { @Override public String applyEffect() { return target.addFaceOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]喉咙容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]喉咙容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]喉咙容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]喉咙容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]喉咙容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]喉咙容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementFaceCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]喉咙深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]喉咙深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]喉咙深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]喉咙深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]喉咙深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]喉咙深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementFaceDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]喉咙弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]喉咙弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]喉咙弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]喉咙弹性等级(+" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]喉咙弹性等级(+" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]喉咙弹性等级(+" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementFaceElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]喉咙可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]喉咙可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]喉咙可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]喉咙可塑性等级(+" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]喉咙可塑性等级(+" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]喉咙可塑性等级(+" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementFacePlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]唾液分泌(" + smallChangeMajorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]唾液分泌(" + smallChangeDrain + "湿度)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]唾液分泌(" + smallChangeMinorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]唾液分泌(+" + smallChangeMinorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]唾液分泌(+" + smallChangeBoost + "湿度)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]唾液分泌(+" + smallChangeMajorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementFaceWetness(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]舌头长度(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]舌头长度(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]舌头长度(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]舌头长度(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]舌头长度(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]舌头长度(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementTongueLength(mediumChangeMajorBoost); } };
						}
					case TF_MOD_TONGUE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除舌头的螺纹。") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为舌头添加螺纹。") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.RIBBED); } };
						}
					case TF_MOD_TONGUE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除舌头的触手。") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为舌头添加触手。") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.TENTACLED); } };
						}
					case TF_MOD_TONGUE_BIFURCATED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除舌头分叉。") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.BIFURCATED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为舌头添加分叉。") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.BIFURCATED); } };
						}
					case TF_MOD_TONGUE_WIDE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("使舌头恢复宽度。") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.WIDE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("拓宽舌头。") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.WIDE); } };
						}
					case TF_MOD_TONGUE_FLAT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("使舌头恢复平整。") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.FLAT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使舌头变平。") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.FLAT); } };
						}
					case TF_MOD_TONGUE_STRONG:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除舌头的额外力量。") { @Override public String applyEffect() { return target.removeTongueModifier(TongueModifier.STRONG); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使舌头更加强壮。") { @Override public String applyEffect() { return target.addTongueModifier(TongueModifier.STRONG); } };
						}
						
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("移除巨量的脸部毛发。(" + smallChangeMajorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("移除大量的脸部毛发。(" + smallChangeDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除部分脸部毛发。(" + smallChangeMinorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一些脸部毛发(+" + smallChangeMinorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("增加大量的脸部毛发。(+" + smallChangeBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("增加巨量的脸部毛发(+" + smallChangeMajorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementFacialHair(smallChangeMajorBoost); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"脸部转化。") { @Override public String applyEffect() { return target.setFaceType(RacialBody.valueOfRace(race).getFaceType()); } };
				}
				
			case TF_HAIR:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(HairType.getHairTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(HairType.getHairTypes(race).get(index).getTransformName())+"头发转化。") {
							@Override public String applyEffect() { return target.setHairType(HairType.getHairTypes(race).get(index)); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]头发长度(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]头发长度(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]头发长度(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]头发长度(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]头发长度(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]头发长度(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHairLength(mediumChangeMajorBoost); } };
						}

					case TF_MOD_SIZE_SECONDARY:
						if(potency.isNegative()) {
							return new RacialEffectUtil("移除颈部绒毛。") { @Override public String applyEffect() { return target.setNeckFluff(false); } };
						} else {
							return new RacialEffectUtil("添加颈部绒毛。") { @Override public String applyEffect() { return target.setNeckFluff(true); } };
						}
						
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"头发转化。") { @Override public String applyEffect() { return target.setHairType(RacialBody.valueOfRace(race).getHairType()); } };
				}
				
			case TF_HORNS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("移除角。") { @Override public String applyEffect() { return target.setHornType(HornType.NONE); } };
						
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = modifierTypeToInt(secondaryModifier);
						List<AbstractHornType> hornTypes = HornType.getHornTypes(race, false);
						AbstractHornType selectedHornType = index >= hornTypes.size() ? HornType.NONE : hornTypes.get(index);
						return new RacialEffectUtil(selectedHornType.equals(HornType.NONE)?"移除角。":"长出"+selectedHornType.getTransformName()+"的角。") {
							@Override public String applyEffect() { return target.setHornType(selectedHornType); } };
						
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]角长度(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]角长度(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]角长度(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]角长度(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]角长度(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]角长度(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementHornLength(mediumChangeMajorBoost); } };
						}
				
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一对多余的角。") { @Override public String applyEffect() { return target.incrementHornRows(singleDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一对额外的角。") { @Override public String applyEffect() {
									List<AbstractHornType> hornTypesSuitableForTransformation = RacialBody.valueOfRace(race).getHornTypes(true);
									if(target.getHornType().equals(HornType.NONE) && !hornTypesSuitableForTransformation.isEmpty()) {
										return target.setHornType(hornTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementHornRows(singleBoost);
									} } };
						}
						
					case TF_MOD_COUNT_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]每行的角(" + smallChangeMajorDrain + "每行的角)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]每行的角(" + smallChangeDrain + "每行的角)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]每行的角(" + smallChangeMinorDrain + "每行的角)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]每行的角(+" + smallChangeMinorBoost + "每行的角)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]每行的角(+" + smallChangeBoost + "每行的角)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]每行角(+" + smallChangeMajorBoost + "每行角)") { @Override public String applyEffect() { return target.incrementHornsPerRow(smallChangeMajorBoost); } };
						}
						
					default:
						List<AbstractHornType> defaultHornTypes = RacialBody.valueOfRace(race).getHornTypes(true);
						AbstractHornType hornType = defaultHornTypes.isEmpty()?HornType.NONE:Util.randomItemFrom(defaultHornTypes);
						return new RacialEffectUtil(hornType.equals(HornType.NONE)?"移除角。":Util.capitaliseSentence(race.getName(false))+"角转化。") {
							@Override public String applyEffect() { return target.setHornType(hornType); } };
				}
				
			case TF_LEGS:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(LegType.getLegTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(LegType.getLegTypes(race).get(index).getTransformName())+"腿部转化。") {
							@Override public String applyEffect() { return target.setLegType(LegType.getLegTypes(race).get(index)); } };

					case TF_MOD_LEG_CONFIG_BIPEDAL:
						return new RacialEffectUtil("将下半身变成一对双足行走的腿部。") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.BIPEDAL).applyLegConfigurationTransformation(target, LegConfiguration.BIPEDAL, true, true); } };

					case TF_MOD_LEG_CONFIG_TAUR:
						return new RacialEffectUtil("将下半身变成一对四足行走的腿部。") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.QUADRUPEDAL).applyLegConfigurationTransformation(target, LegConfiguration.QUADRUPEDAL, true, true); } };

					case TF_MOD_LEG_CONFIG_TAIL_LONG:
						return new RacialEffectUtil("将下半身变成长蛇一样的尾巴。") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.TAIL_LONG).applyLegConfigurationTransformation(target, LegConfiguration.TAIL_LONG, true, true); } };

					case TF_MOD_LEG_CONFIG_TAIL:
						return new RacialEffectUtil("将下半身变成鱼一样的尾巴") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.TAIL).applyLegConfigurationTransformation(target, LegConfiguration.TAIL, true, true); } };

					case TF_MOD_LEG_CONFIG_ARACHNID:
						return new RacialEffectUtil("将下半身变成八条腿的蛛形纲动物。") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.ARACHNID).applyLegConfigurationTransformation(target, LegConfiguration.ARACHNID, true, true); } };

					case TF_MOD_LEG_CONFIG_CEPHALOPOD:
						return new RacialEffectUtil("将下半身变成八触手头足类动物的形状。") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.CEPHALOPOD).applyLegConfigurationTransformation(target, LegConfiguration.CEPHALOPOD, true, true); } };

					case TF_MOD_LEG_CONFIG_AVIAN:
						return new RacialEffectUtil("将下半身变成野鸟的形状。") {
							@Override public String applyEffect() { return RacialBody.valueOfRace(race).getLegType(LegConfiguration.AVIAN).applyLegConfigurationTransformation(target, LegConfiguration.AVIAN, true, true); } };
							

					case TF_MOD_FOOT_STRUCTURE_PLANTIGRADE:
						return new RacialEffectUtil("将脚部结构转变为跖行。") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.PLANTIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_DIGITIGRADE:
						return new RacialEffectUtil("将脚部结构转换为趾行。") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.DIGITIGRADE); } };

					case TF_MOD_FOOT_STRUCTURE_UNGULIGRADE:
						return new RacialEffectUtil("将脚部结构改造成蹄行。") {
							@Override public String applyEffect() { return target.setFootStructure(FootStructure.UNGULIGRADE); } };

					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]蛇尾长度(" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]蛇尾长度(" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]蛇尾长度(" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]蛇尾长度(+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]蛇尾长度(+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]蛇尾长度(+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementLegTailLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊的额外肉感。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使丝囊更加肉感。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊内部的螺纹。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为丝囊内部添加螺纹。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊内部的额外肌肉。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为丝囊内部增加额外肌肉") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊内部的触手") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为丝囊内部添加触手。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊弹性等级(" + smallChangeMinorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊弹性等级(+" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊弹性等级(+" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊弹性等级(+" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊可塑性等级(+" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊可塑性等级(+" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊可塑性等级(+" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊湿度(" + smallChangeMajorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊湿度(" + smallChangeDrain + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊湿度(" + smallChangeMinorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊湿度(+" + smallChangeMinorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊湿度(+" + smallChangeBoost + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊湿度(+" + smallChangeMajorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorBoost); } };
						}
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"腿部转化。") { @Override public String applyEffect() { return target.setLegType(RacialBody.valueOfRace(race).getLegType()); } };
				}
				
			case TF_PENIS:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴茎长度(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴茎长度(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴茎长度(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴茎长度(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴茎长度(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴茎长度(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴茎周长(" + smallChangeMajorDrain + "周长)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴茎周长(" + smallChangeDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴茎周长(" + smallChangeMinorDrain + "周长)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴茎周长(+" + smallChangeMinorBoost + "周长)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴茎周长(+" + smallChangeBoost + "周长)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴茎周长(+" + smallChangeMajorBoost + "周长)") { @Override public String applyEffect() { return target.incrementPenisGirth(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("移除阴茎。") { @Override public String applyEffect() {
								String tfText = target.setPenisType(PenisType.NONE);
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.PENIS, target, true);
									user.setKnowsCharacterArea(CoverableArea.TESTICLES, target, true);
								}
								return tfText;
							} };

					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("移除巨量的阴毛。(" + smallChangeMajorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("移除大量的阴毛。(" + smallChangeDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一些阴毛。(" + smallChangeMinorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一些阴毛。(" + smallChangeMinorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("增加大量的阴毛。(" + smallChangeBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("增加巨量的阴毛。(" + smallChangeMajorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎上的倒刺。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎添加倒刺。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的冠状龟头。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎添加冠状龟头。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.FLARED); } };
						}
					case TF_MOD_PENIS_BLUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的钝头。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.BLUNT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使阴茎头部变为圆头。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.BLUNT); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎根部的结。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在阴茎的根部添加结。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的抓握能力。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎增加抓握能力。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎螺纹。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎添加螺纹。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的护套。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎增加护套。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的尖头。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎增加尖头。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的触手。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎添加触手。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎鼓起的脉络。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎增加鼓起的脉络。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.VEINY); } };
						}
					case TF_MOD_PENIS_OVIPOSITOR:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴茎的产卵管功能。") { @Override public String applyEffect() { return target.removePenisModifier(PenetrationModifier.OVIPOSITOR); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴茎增加产卵管功能。") { @Override public String applyEffect() { return target.addPenisModifier(PenetrationModifier.OVIPOSITOR); } };
						}

					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]睾丸尺寸(" + smallChangeMajorDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]睾丸尺寸(" + smallChangeDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]睾丸尺寸(" + smallChangeMinorDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]睾丸尺寸(+" + smallChangeMinorBoost + "尺寸)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]睾丸尺寸(+" + smallChangeBoost + "尺寸)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]睾丸尺寸(+" + smallChangeMajorBoost + "尺寸)") { @Override public String applyEffect() { return target.incrementTesticleSize(smallChangeMajorBoost); } };
						}
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一对额外的睾丸。") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一对额外的睾丸。") { @Override public String applyEffect() { return target.incrementTesticleCount(smallChangeBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]精液储量(" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorDrain);
									target.fillCumToMaxStorage();
									return s;} };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]精液储量(" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]精液储量(" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorDrain);
									target.fillCumToMaxStorage();
									return s; } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]精液储量(+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMinorBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]精液储量(+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeBoost);
									target.fillCumToMaxStorage();
									return s; } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]精液储量(+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() {
									String s = target.incrementPenisCumStorage(largeChangeMajorBoost);
									target.fillCumToMaxStorage();
									return s; } };
						}
					case TF_MOD_REGENERATION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]精液再生速率") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]精液再生速率") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]精液再生速率") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]精液再生速率") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]精液再生速率") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]精液再生速率") { @Override public String applyEffect() { return target.incrementPenisCumProductionRegeneration(hugeChangeMajorBoost); } };
						}
					case TF_MOD_CUM_EXPULSION:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]高潮射精量(" + mediumChangeMajorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]高潮射精量(" + mediumChangeDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]高潮射精量(" + mediumChangeMinorDrain + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]高潮射精量(+" + mediumChangeMinorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]高潮射精量(+" + mediumChangeBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]高潮射精量(+" + mediumChangeMajorBoost + "%)") { @Override public String applyEffect() { return target.incrementPenisCumExpulsion(mediumChangeMajorBoost); } };
						}
					case TF_MOD_INTERNAL:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("令阴囊位于体外。") { @Override public String applyEffect() { return target.setInternalTesticles(false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("令阴囊位于体内。") { @Override public String applyEffect() { return target.setInternalTesticles(true); } };
						}
						
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道容量 (" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementUrethraDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道弹性(" + smallChangeMajorDrain + "弹性)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道弹性(" + smallChangeDrain + "弹性)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道弹性(" + smallChangeMinorDrain + "弹性)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道弹性(+" + smallChangeMinorBoost + "弹性)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道弹性(+" + smallChangeBoost + "弹性)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道弹性(+" + smallChangeMajorBoost + "弹性)") { @Override public String applyEffect() { return target.incrementUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道塑性(" + smallChangeMajorDrain + "塑性)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道塑性(" + smallChangeDrain + "塑性)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道塑性(" + smallChangeMinorDrain + "塑性)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道塑性(+" + smallChangeMinorBoost + "塑性)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道塑性(+" + smallChangeBoost + "塑性)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道塑性(+" + smallChangeMajorBoost + "塑性)") { @Override public String applyEffect() { return target.incrementUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道的额外肉感。") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使尿道更具肉感。") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道内部的螺纹。") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在尿道内部添加螺纹。") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道内的额外肌肉。") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使尿道内部生长肌肉。") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道内部触手。") { @Override public String applyEffect() { return target.removeUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在尿道内部添加触手。") { @Override public String applyEffect() { return target.addUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(PenisType.getPenisTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								PenisType.getPenisTypes(race).get(index)==PenisType.NONE
									?"移除阴茎。"
									:Util.capitaliseSentence(PenisType.getPenisTypes(race).get(index).getTransformName())+"阴茎转化。") { @Override public String applyEffect() {
								String tfText = target.setPenisType(PenisType.getPenisTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.PENIS, target, true);
									user.setKnowsCharacterArea(CoverableArea.TESTICLES, target, true);
								}
								return tfText;
							} };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"阴茎转化。") { @Override public String applyEffect() {
							String tfText = target.setPenisType(RacialBody.valueOfRace(race).getPenisType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.PENIS, target, true);
								user.setKnowsCharacterArea(CoverableArea.TESTICLES, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_SKIN:
				switch(secondaryModifier) {
					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(TorsoType.getTorsoTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(Util.capitaliseSentence(TorsoType.getTorsoTypes(race).get(index).getTransformName())+"躯体转化。") {
							@Override public String applyEffect() { return target.setTorsoType(TorsoType.getTorsoTypes(race).get(index)); } };
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"躯体转化。") { @Override public String applyEffect() { return target.setTorsoType(RacialBody.valueOfRace(race).getTorsoType()); } };
				}
				
			case TF_TAIL:
				switch(secondaryModifier) {
					case TF_MOD_COUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一条额外的尾巴。") { @Override public String applyEffect() { return target.incrementTailCount(singleDrain, false); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("添加一条额外的尾巴。") { @Override public String applyEffect() {
									List<AbstractTailType> tailTypesSuitableForTransformation = TailType.getTailTypesSuitableForTransformation(RacialBody.valueOfRace(race).getTailType());
									if(target.getTailType()==TailType.NONE && !tailTypesSuitableForTransformation.isEmpty()) {
										return target.setTailType(tailTypesSuitableForTransformation.get(0));
									} else {
										return target.incrementTailCount(singleBoost, false);
									} } };
						}

					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尾巴长度(" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尾巴长度(" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尾巴长度(" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尾巴长度(+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尾巴长度(+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尾巴长度(+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTailLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尾巴周长(" + smallChangeMajorDrain + "周长)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尾巴周长(" + smallChangeDrain + "周长)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尾巴周长(" + smallChangeMinorDrain + "周长)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尾巴周长(+" + smallChangeMinorBoost + "周长)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尾巴周长(+" + smallChangeBoost + "周长)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尾巴周长(+" + smallChangeMajorBoost + "周长)") { @Override public String applyEffect() { return target.incrementTailGirth(smallChangeMajorBoost); } };
						}
						
					case REMOVAL:
						return new RacialEffectUtil("移除尾巴。") { @Override public String applyEffect() { return target.setTailType(TailType.NONE); } };

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(TailType.getTailTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								TailType.getTailTypes(race).get(index)==TailType.NONE
									?"移除尾巴。"
									:Util.capitaliseSentence(TailType.getTailTypes(race).get(index).getTransformName())+"尾巴转化。") {
							@Override public String applyEffect() { return target.setTailType(TailType.getTailTypes(race).get(index)); } };

					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊的额外肉感。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使丝囊更加肉感。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊内部的螺纹。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为丝囊内部添加螺纹。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊内部的额外肌肉。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为丝囊内部增加额外肌肉。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除丝囊内部的触手。") { @Override public String applyEffect() { return target.removeSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为丝囊内部添加触手。") { @Override public String applyEffect() { return target.addSpinneretOrificeModifier(OrificeModifier.TENTACLED); } };
						}
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementSpinneretCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementSpinneretDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊弹性等级(" + smallChangeMinorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊弹性等级(+" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊弹性等级(+" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊弹性等级(+" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementSpinneretElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊可塑性等级(+" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊可塑性等级(+" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊可塑性等级(+" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementSpinneretPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]丝囊湿度(" + smallChangeMajorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]丝囊湿度(" + smallChangeDrain + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]丝囊湿度(" + smallChangeMinorDrain + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]丝囊湿度(+" + smallChangeMinorBoost + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]丝囊湿度(+" + smallChangeBoost + "湿度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]丝囊湿润度(+" + smallChangeMajorBoost + "湿润度)") { @Override public String applyEffect() { return target.incrementSpinneretWetness(smallChangeMajorBoost); } };
						}
							
					default:
						AbstractTailType tailType = RacialBody.valueOfRace(race).getRandomTailType(false);
						return new RacialEffectUtil(tailType==TailType.NONE?"移除尾巴。":Util.capitaliseSentence(race.getName(false))+"尾巴转化。") {
							@Override public String applyEffect() { return target.setTailType(tailType); } };
				}
				
			case TF_TENTACLE:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]触手长度(" + mediumChangeMajorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMajorDrain/100f); }
									};
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]触手长度(" + mediumChangeDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeDrain/100f); }
									};
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]触手长度(" + mediumChangeMinorDrain + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMinorDrain/100f); }
									};
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]触手长度(+" + mediumChangeMinorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMinorBoost/100f); }
									};
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]触手长度(+" + mediumChangeBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeBoost/100f); }
									};
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]触手长度(+" + mediumChangeMajorBoost + "%)") {
										@Override public String applyEffect() { return target.incrementTentacleLengthAsPercentageOfHeight(mediumChangeMajorBoost/100f); }
									};
						}
						
					default://case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]触手周长(" + smallChangeMajorDrain + "周长)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]触手周长(" + smallChangeDrain + "周长)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]触手周长(" + smallChangeMinorDrain + "周长)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]触手周长(+" + smallChangeMinorBoost + "周长)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]触手周长(+" + smallChangeBoost + "周长)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]触手周长(+" + smallChangeMajorBoost + "周长)") { @Override public String applyEffect() { return target.incrementTentacleGirth(smallChangeMajorBoost); } };
						}
				}
				
			case TF_VAGINA:
				switch(secondaryModifier) {
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴蒂长度(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴蒂长度(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴蒂长度(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴蒂长度(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴蒂长度(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴蒂长度(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaClitorisSize(mediumChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_SECONDARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴蒂周长(" + smallChangeMajorDrain + "周长)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴蒂周长(" + smallChangeDrain + "周长)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴蒂周长(" + smallChangeMinorDrain + "周长)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴蒂周长(+" + smallChangeMinorBoost + "周长)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴蒂周长(+" + smallChangeBoost + "周长)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴蒂周长(+" + smallChangeMajorBoost + "周长)") { @Override public String applyEffect() { return target.incrementClitorisGirth(smallChangeMajorBoost); } };
						}
					case TF_MOD_SIZE_TERTIARY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴唇尺寸(" + smallChangeMajorDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴唇尺寸(" + smallChangeDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴唇尺寸(" + smallChangeMinorDrain + "尺寸)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴唇尺寸(+" + smallChangeMinorBoost + "尺寸)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴唇尺寸(+" + smallChangeBoost + "尺寸)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴唇尺寸(+" + smallChangeMajorBoost + "尺寸)") { @Override public String applyEffect() { return target.incrementVaginaLabiaSize(smallChangeMajorBoost); } };
						}
					case REMOVAL:
							return new RacialEffectUtil("移除阴道。") { @Override public String applyEffect() {
								String tfText = target.setVaginaType(VaginaType.NONE);
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.VAGINA, target, true);
								}
								return tfText;
							} };
							
					case TF_MOD_BODY_HAIR:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("移除巨量的阴毛。(" + smallChangeMajorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("移除大量的阴毛。(" + smallChangeDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除一些阴毛。(" + smallChangeMinorDrain + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加一些阴毛。(" + smallChangeMinorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("增加大量阴毛。(" + smallChangeBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("增加巨量阴毛。(" + smallChangeMajorBoost + "毛发量)") { @Override public String applyEffect() { return target.incrementPubicHair(smallChangeMajorBoost); } };
						}

					case TF_MOD_PENIS_BARBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂上的倒刺。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.BARBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂添加倒刺。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.BARBED); } };
						}
					case TF_MOD_PENIS_FLARED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂的冠状头部。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.FLARED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂添加冠状头部。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.FLARED); } };
						}
					case TF_MOD_PENIS_BLUNT:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂的圆头。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.BLUNT); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使阴蒂的顶端变为圆头。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.BLUNT); } };
						}
					case TF_MOD_PENIS_KNOTTED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂根部的结。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.KNOTTED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在阴蒂的根部添加结。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.KNOTTED); } };
						}
					case TF_MOD_PENIS_PREHENSILE:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("使阴蒂不再灵活可控。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.PREHENSILE); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使阴蒂变得灵活可控。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.PREHENSILE); } };
						}
					case TF_MOD_PENIS_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂的螺纹。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂添加螺纹。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.RIBBED); } };
						}
					case TF_MOD_PENIS_SHEATHED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除额外的阴蒂包皮护套。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.SHEATHED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("增加额外的阴蒂包皮护套。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.SHEATHED); } };
						}
					case TF_MOD_PENIS_TAPERED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂的尖头。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.TAPERED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂增加尖头。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.TAPERED); } };
						}
					case TF_MOD_PENIS_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂的触手。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂添加触手。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.TENTACLED); } };
						}
					case TF_MOD_PENIS_VEINY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂鼓起的脉络。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.VEINY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂添加鼓起的脉络。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.VEINY); } };
						}
					case TF_MOD_PENIS_OVIPOSITOR:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴蒂的产卵管功能。") { @Override public String applyEffect() { return target.removeClitorisModifier(PenetrationModifier.OVIPOSITOR); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴蒂增加产卵管功能。") { @Override public String applyEffect() { return target.addClitorisModifier(PenetrationModifier.OVIPOSITOR); } };
						}
							
					case TF_MOD_CAPACITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴道容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴道容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴道容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴道容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴道容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴道容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴道深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴道深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴道深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴道深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴道深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴道深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementVaginaDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴道弹性等级(" + smallChangeMajorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴道弹性等级(" + smallChangeDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴道弹性等级(" + smallChangeMinorDrain + "弹性等级)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴道弹性等级(+" + smallChangeMinorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴道弹性等级(+" + smallChangeBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴道弹性等级(+" + smallChangeMajorBoost + "弹性等级)") { @Override public String applyEffect() { return target.incrementVaginaElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴道可塑性等级(" + smallChangeMajorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴道可塑性等级(" + smallChangeDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴道可塑性等级(" + smallChangeMinorDrain + "可塑性等级)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴道可塑性等级(+" + smallChangeMinorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴道可塑性等级(+" + smallChangeBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴道可塑性等级(+" + smallChangeMajorBoost + "可塑性等级)") { @Override public String applyEffect() { return target.incrementVaginaPlasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_WETNESS:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴道润滑程度(" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴道润滑程度(" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴道润滑程度(" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴道润滑程度(+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴道润滑程度(+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴道润滑程度(+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}

					case TF_MOD_VAGINA_SQUIRTER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("使阴道在高潮时潮吹。") { @Override public String applyEffect() { return target.setVaginaSquirter(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("使阴道在高潮时不再潮吹。") { @Override public String applyEffect() { return target.setVaginaSquirter(false); } };
						}

					case TF_MOD_VAGINA_EGG_LAYER:
						switch(potency) {
							case MINOR_BOOST:
								return new RacialEffectUtil("使阴道产卵。") { @Override public String applyEffect() { return target.setVaginaEggLayer(true); } };
							case MINOR_DRAIN: default:
								return new RacialEffectUtil("使阴道不再产卵生殖。") { @Override public String applyEffect() { return target.setVaginaEggLayer(false); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴唇的额外肉感。") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使阴唇更加肉感。") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴道内部的螺纹。") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴道内部添加螺纹。") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除了阴道的额外肌肉。") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使阴道内生长额外肌肉。") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除阴道内部触手。") { @Override public String applyEffect() { return target.removeVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为阴道内部添加触手。") { @Override public String applyEffect() { return target.addVaginaOrificeModifier(OrificeModifier.TENTACLED); } };
						}
						
					// Urethral stuff:
					case TF_MOD_CAPACITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道容量(" + Units.size(mediumChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorDrain, true); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道容量(" + Units.size(mediumChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeDrain, true); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道容量(" + Units.size(mediumChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorDrain, true); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道容量(+" + Units.size(mediumChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMinorBoost, true); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道容量(+" + Units.size(mediumChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeBoost, true); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道容量(+" + Units.size(mediumChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaUrethraCapacity(mediumChangeMajorBoost, true); } };
						}
					case TF_MOD_DEPTH_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道深度(" + smallChangeMajorDrain + "深度)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道深度(" + smallChangeDrain + "深度)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道深度(" + smallChangeMinorDrain + "深度)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道深度(+" + smallChangeMinorBoost + "深度)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道深度(+" + smallChangeBoost + "深度)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道深度(+" + smallChangeMajorBoost + "深度)") { @Override public String applyEffect() { return target.incrementVaginaUrethraDepth(smallChangeMajorBoost); } };
						}
					case TF_MOD_ELASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道弹性(" + smallChangeMajorDrain + "弹性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道弹性(" + smallChangeDrain + "弹性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道弹性(" + smallChangeMinorDrain + "弹性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道弹性(+" + smallChangeMinorBoost + "弹性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道弹性(+" + smallChangeBoost + "弹性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道弹性(+" + smallChangeMajorBoost + "弹性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraElasticity(smallChangeMajorBoost); } };
						}
					case TF_MOD_PLASTICITY_2:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]尿道塑性(" + smallChangeMajorDrain + "塑性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]尿道塑性(" + smallChangeDrain + "塑性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]尿道塑性(" + smallChangeMinorDrain + "塑性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]尿道塑性(+" + smallChangeMinorBoost + "塑性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]尿道塑性(+" + smallChangeBoost + "塑性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]尿道塑性(+" + smallChangeMajorBoost + "塑性)") { @Override public String applyEffect() { return target.incrementVaginaUrethraPlasticity(smallChangeMajorBoost); } };
						}
						
					case TF_MOD_ORIFICE_PUFFY_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道的额外肉感。") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使尿道更加肉感。") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.PUFFY); } };
						}
					case TF_MOD_ORIFICE_RIBBED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道内部的螺纹。") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("为尿道内部添加螺纹。") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.RIBBED); } };
						}
					case TF_MOD_ORIFICE_MUSCLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道内的额外肌肉。") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("使尿道内部生长肌肉。") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.MUSCLE_CONTROL); } };
						}
					case TF_MOD_ORIFICE_TENTACLED_2:
						switch(potency) {
							case MINOR_DRAIN:
								return new RacialEffectUtil("移除尿道内部触手。") { @Override public String applyEffect() { return target.removeVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("在尿道内部添加触手。") { @Override public String applyEffect() { return target.addVaginaUrethraOrificeModifier(OrificeModifier.TENTACLED); } };
						}

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(VaginaType.getVaginaTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(
								VaginaType.getVaginaTypes(race).get(index)==VaginaType.NONE
									?"移除阴茎。"
									:Util.capitaliseSentence(VaginaType.getVaginaTypes(race).get(index).getTransformName())+"阴道转化。") { @Override public String applyEffect() {
								String tfText = target.setVaginaType(VaginaType.getVaginaTypes(race).get(index));
								if(revealTransformedPart) {
									user.setKnowsCharacterArea(CoverableArea.VAGINA, target, true);
								}
								return tfText;
							} };
							
					default:
						return new RacialEffectUtil(Util.capitaliseSentence(race.getName(false))+"阴道转化。") { @Override public String applyEffect() {
							String tfText = target.setVaginaType(RacialBody.valueOfRace(race).getVaginaType());
							if(revealTransformedPart) {
								user.setKnowsCharacterArea(CoverableArea.VAGINA, target, true);
							}
							return tfText;
						} };
				}
				
			case TF_WINGS:
				switch(secondaryModifier) {
					case REMOVAL:
						return new RacialEffectUtil("移除翅膀。") { @Override public String applyEffect() { return target.setWingType(WingType.NONE); } };

					case TF_TYPE_1: case TF_TYPE_2: case TF_TYPE_3: case TF_TYPE_4: case TF_TYPE_5: case TF_TYPE_6: case TF_TYPE_7: case TF_TYPE_8: case TF_TYPE_9: case TF_TYPE_10:
						int index = Math.min(WingType.getWingTypes(race).size()-1, modifierTypeToInt(secondaryModifier));
						return new RacialEffectUtil(WingType.getWingTypes(race).get(index)==WingType.NONE?"移除翅膀。":Util.capitaliseSentence(WingType.getWingTypes(race).get(index).getTransformName())+"翅膀转化。") {
							@Override public String applyEffect() { return target.setWingType(WingType.getWingTypes(race).get(index)); } };
							
					case TF_MOD_SIZE:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]翅膀尺寸(" + smallChangeMajorDrain + "翅膀尺寸)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]翅膀尺寸(" + smallChangeDrain + "翅膀尺寸)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]翅膀尺寸(" + smallChangeMinorDrain + "翅膀尺寸)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorDrain); } };
							case MINOR_BOOST: default:
								return new RacialEffectUtil("[style.colourMinorGood(++)]翅膀尺寸(+" + smallChangeMinorBoost + "翅膀尺寸)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]翅膀尺寸(+" + smallChangeBoost + "翅膀尺寸)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeBoost); } };
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]翅膀尺寸(+" + smallChangeMajorBoost + "翅膀尺寸)") { @Override public String applyEffect() { return target.incrementWingSize(smallChangeMajorBoost); } };
						}
					default:
						AbstractWingType wingType = RacialBody.valueOfRace(race).getRandomWingType(false);
						return new RacialEffectUtil(wingType==WingType.NONE?"移除翅膀。":Util.capitaliseSentence(race.getName(false))+"翅膀转化。") {
							@Override public String applyEffect() { return target.setWingType(wingType); } };
				}
				
			case TF_CUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("使精液尝起来像啤酒。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("使精液尝起来像巧克力。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("使精液尝起来像精液。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("使精液尝起来像爱液。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("使精液尝起来像牛奶。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("使精液失去味道。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("使精液尝起来像蜂蜜。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("使精液尝起来像薄荷。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("使精液尝起来像樱桃。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("使精液尝起来像菠萝。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("使精液尝起来像泡泡糖。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("使精液尝起来像草莓。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("使精液尝起来像香草味") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("使精液尝起来像咖啡味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("使精液尝起来像茶味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("使精液尝起来像枫糖浆。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("使精液尝起来像肉桂味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("使精液尝起来像柠檬味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("使精液尝起来像橘子味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("使精液尝起来像葡萄味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("使精液尝起来像西瓜味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("使精液尝起来像椰子味") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("使精液尝起来像蓝莓味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("使精液尝起来像香蕉味。") { @Override public String applyEffect() { return target.setCumFlavour(FluidFlavour.BANANA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除来自精液的成瘾性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("为精液添加成瘾性。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液的高醉酒性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("使精液变成高度酒精精液。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液的酒精特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("使精液具有酒精性。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("为精液添加躁动特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("使精液变得充满气泡。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液的精神影响特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("为精液增加精神影响特性。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("去除精液的矿物油特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("为精液增加矿物油特性。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液味道浓重的特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("使精液散发出浓厚的味道。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液的粘滑特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("使精液变得粘滑。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液的粘黏特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("使精液变得粘黏。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除精液的粘稠特性。") { @Override public String applyEffect() { return target.removeCumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("使精液变得粘稠。") { @Override public String applyEffect() { return target.addCumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]精液储量(" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]精液储量(" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]精液储量(" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)]精液储量(+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]精液储量(+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]精液储量(+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementPenisCumStorage(largeChangeMajorBoost); } };
						}
				}
				break;
				
			case TF_MILK:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("使乳汁尝起来像啤酒。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("使乳汁尝起来像巧克力。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("使乳汁尝起来像精液。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("使乳汁尝起来像爱液。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("使乳汁尝起来像奶。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("使乳汁没有味道。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("使乳汁尝起来像蜂蜜。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("使乳汁尝起来像薄荷。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("使乳汁尝起来像樱桃。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("使乳汁尝起来像菠萝。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("使乳汁尝起来像泡泡糖。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("使乳汁尝起来像草莓。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("使乳汁尝起来像香草味。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("使乳汁尝起来像咖啡。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("使乳汁尝起来像茶。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("使乳汁尝起来像枫糖浆。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("使乳汁尝起来像肉桂味。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("使乳汁尝起来像柠檬。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("使乳汁尝起来像橘子。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("使乳汁尝起来像葡萄。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("使乳汁尝起来像西瓜。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("使乳汁尝起来像椰子。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("使乳汁尝起来像蓝莓。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("使乳汁尝起来像香蕉。") { @Override public String applyEffect() { return target.setMilkFlavour(FluidFlavour.BANANA); } };
						
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的成瘾性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("为乳汁添加成瘾性。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的高醉酒性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("使乳汁变成高度酒精乳汁。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的酒精特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("使乳汁具有酒精特性。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的躁动特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("使乳汁变得充满气泡。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的精神影响特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("为乳汁增加精神影响特性。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("去除乳汁的矿物油特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("为乳汁增加矿物油特性。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁味道浓重的特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("移除乳汁味道浓厚的特性。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的粘滑特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("使乳汁变得粘滑。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的粘黏特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("使乳汁变得粘黏。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除乳汁的粘稠特性。") { @Override public String applyEffect() { return target.removeMilkModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("使乳汁变得粘稠。") { @Override public String applyEffect() { return target.addMilkModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]乳汁储量(" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]乳汁储量(" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]乳汁储量(" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)]乳汁储量(+" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]乳汁储量(+" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]乳汁储量(+" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastMilkStorage(largeChangeMajorBoost); } };
						}
				}
				break;

			case TF_MILK_CROTCH:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("使腹乳乳汁尝起来像啤酒。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("使腹乳乳汁尝起来像巧克力。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("使腹乳乳汁尝起来像精液。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("使腹乳乳汁尝起来像爱液。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("使腹乳乳汁尝起来像奶。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("使腹乳乳汁没有味道。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("使腹乳乳汁尝起来像蜂蜜。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("使腹乳乳汁尝起来像薄荷。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("使腹乳乳汁尝起来像樱桃。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("使腹乳乳汁尝起来像菠萝。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("使腹乳乳汁尝起来像泡泡糖。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("使腹乳乳汁尝起来像草莓。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("使腹乳乳汁尝起来像香草味。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("使腹乳乳汁尝起来像咖啡。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("使腹乳乳汁尝起来像茶。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("使腹乳乳汁尝起来像枫糖浆。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("使腹乳乳汁尝起来像肉桂味。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("使腹乳乳汁尝起来像柠檬。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("使腹乳尝起来像橘子。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("使腹乳乳汁尝起来像葡萄。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("使腹乳乳汁尝起来像蜜瓜。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("使腹乳乳汁尝起来像椰子。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("使腹乳乳汁尝起来像蓝莓。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("使腹乳乳汁尝起来像香蕉。") { @Override public String applyEffect() { return target.setMilkCrotchFlavour(FluidFlavour.BANANA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的成瘾性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("为腹乳乳汁添加成瘾性。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的高醉酒性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("使腹乳乳汁变成高度酒精乳汁。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的酒精特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("使乳汁具有酒精特性。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的躁动特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("使腹乳乳汁变得充满气泡。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的精神影响特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("为腹乳乳汁增加精神影响特性。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("去除腹乳乳汁的矿物油特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("为腹乳乳汁增加矿物油特性。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁味道浓重的特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("移除腹乳乳汁味道浓厚的特性。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的粘滑特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("使腹乳乳汁变得粘滑。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的粘黏特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("使腹乳乳汁变得粘黏。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除腹乳乳汁的粘稠特性。") { @Override public String applyEffect() { return target.removeMilkCrotchModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("使腹乳乳汁变得粘稠。") { @Override public String applyEffect() { return target.addMilkCrotchModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]腹乳乳汁储量(" + Units.fluid(largeChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]腹乳乳汁储量(" + Units.fluid(largeChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]腹乳乳汁储量(" + Units.fluid(largeChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)]腹乳乳汁储量(" + Units.fluid(largeChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]腹乳乳汁储量(" + Units.fluid(largeChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]腹乳乳汁储量(" + Units.fluid(largeChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementBreastCrotchMilkStorage(largeChangeMajorBoost); } };
						}
				}
				break;
					
			case TF_GIRLCUM:
				switch(secondaryModifier) {
					case TF_MOD_FLAVOUR_BEER:
						return new RacialEffectUtil("使爱液尝起来像啤酒。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BEER); } };
					case TF_MOD_FLAVOUR_CHOCOLATE:
						return new RacialEffectUtil("使爱液尝起来像巧克力。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHOCOLATE); } };
					case TF_MOD_FLAVOUR_CUM:
						return new RacialEffectUtil("使爱液尝起来像精液。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CUM); } };
					case TF_MOD_FLAVOUR_GIRLCUM:
						return new RacialEffectUtil("使爱液尝起来像爱液。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GIRL_CUM); } };
					case TF_MOD_FLAVOUR_MILK:
						return new RacialEffectUtil("使爱液尝起来像牛奶。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MILK); } };
					case TF_MOD_FLAVOUR_FLAVOURLESS:
						return new RacialEffectUtil("使爱液没有味道。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.FLAVOURLESS); } };
					case TF_MOD_FLAVOUR_HONEY:
						return new RacialEffectUtil("使爱液尝起来像蜂蜜。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.HONEY); } };
					case TF_MOD_FLAVOUR_MINT:
						return new RacialEffectUtil("使爱液尝起来像薄荷。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MINT); } };
					case TF_MOD_FLAVOUR_CHERRY:
						return new RacialEffectUtil("使爱液尝起来像樱桃。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CHERRY); } };
					case TF_MOD_FLAVOUR_PINEAPPLE:
						return new RacialEffectUtil("使爱液尝起来像菠萝。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.PINEAPPLE); } };
					case TF_MOD_FLAVOUR_BUBBLEGUM:
						return new RacialEffectUtil("使爱液尝起来像泡泡糖。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BUBBLEGUM); } };
					case TF_MOD_FLAVOUR_STRAWBERRY:
						return new RacialEffectUtil("使爱液尝起来像草莓。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.STRAWBERRY); } };
					case TF_MOD_FLAVOUR_VANILLA:
						return new RacialEffectUtil("使爱液尝起来像香草味。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.VANILLA); } };
					case TF_MOD_FLAVOUR_COFFEE:
						return new RacialEffectUtil("使爱液尝起来像咖啡。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.COFFEE); } };
					case TF_MOD_FLAVOUR_TEA:
						return new RacialEffectUtil("使爱液尝起来像茶。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.TEA); } };
					case TF_MOD_FLAVOUR_MAPLE:
						return new RacialEffectUtil("使爱液尝起来像枫糖浆。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MAPLE); } };
					case TF_MOD_FLAVOUR_CINNAMON:
						return new RacialEffectUtil("使爱液尝起来像肉桂味。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.CINNAMON); } };
					case TF_MOD_FLAVOUR_LEMON:
						return new RacialEffectUtil("使爱液尝起来像柠檬。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.LEMON); } };
					case TF_MOD_FLAVOUR_ORANGE:
						return new RacialEffectUtil("使爱液尝起来像橘子。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.ORANGE); } };
					case TF_MOD_FLAVOUR_GRAPE:
						return new RacialEffectUtil("使爱液尝起来像葡萄。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.GRAPE); } };
					case TF_MOD_FLAVOUR_MELON:
						return new RacialEffectUtil("使爱液尝起来像西瓜。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.MELON); } };
					case TF_MOD_FLAVOUR_COCONUT:
						return new RacialEffectUtil("使爱液尝起来像椰子。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.COCONUT); } };
					case TF_MOD_FLAVOUR_BLUEBERRY:
						return new RacialEffectUtil("使爱液尝起来像蓝莓。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BLUEBERRY); } };
					case TF_MOD_FLAVOUR_BANANA:
						return new RacialEffectUtil("使爱液尝起来像香蕉。") { @Override public String applyEffect() { return target.setGirlcumFlavour(FluidFlavour.BANANA); } };
	
					case TF_MOD_FLUID_ADDICTIVE:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的成瘾性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ADDICTIVE); } };
						} else {
							return new RacialEffectUtil("为爱液添加成瘾性。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ADDICTIVE); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的高醉酒性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						} else {
							return new RacialEffectUtil("使爱液变成高度酒精精液。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ALCOHOLIC); } };
						}
					case TF_MOD_FLUID_ALCOHOLIC_WEAK:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的酒精特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						} else {
							return new RacialEffectUtil("使爱液具有酒精性。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.ALCOHOLIC_WEAK); } };
						}
					case TF_MOD_FLUID_BUBBLING:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的躁动特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.BUBBLING); } };
						} else {
							return new RacialEffectUtil("使爱液变得充满气泡。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.BUBBLING); } };
						}
					case TF_MOD_FLUID_HALLUCINOGENIC:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的精神影响特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						} else {
							return new RacialEffectUtil("为爱液增加精神影响特性。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.HALLUCINOGENIC); } };
						}
					case TF_MOD_FLUID_MINERAL_OIL:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("去除爱液的矿物油特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MINERAL_OIL); } };
						} else {
							return new RacialEffectUtil("为爱液增加矿物油特性。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MINERAL_OIL); } };
						}
					case TF_MOD_FLUID_MUSKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液味道浓重的特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.MUSKY); } };
						} else {
							return new RacialEffectUtil("移除爱液味道浓厚的特性。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.MUSKY); } };
						}
					case TF_MOD_FLUID_SLIMY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的粘滑特性。Removes slimy effect from girlcum.") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.SLIMY); } };
						} else {
							return new RacialEffectUtil("使爱液变得粘滑。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.SLIMY); } };
						}
					case TF_MOD_FLUID_STICKY:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的粘黏特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.STICKY); } };
						} else {
							return new RacialEffectUtil("使爱液变得粘黏。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.STICKY); } };
						}
					case TF_MOD_FLUID_VISCOUS:
						if(potency == TFPotency.MINOR_DRAIN) {
							return new RacialEffectUtil("移除爱液的粘稠特性。") { @Override public String applyEffect() { return target.removeGirlcumModifier(FluidModifier.VISCOUS); } };
						} else {
							return new RacialEffectUtil("使爱液变得粘稠。") { @Override public String applyEffect() { return target.addGirlcumModifier(FluidModifier.VISCOUS); } };
						}
						
					default:
						switch(potency) {
							case MAJOR_DRAIN:
								return new RacialEffectUtil("[style.colourTerrible(--)]阴道润滑(" + Units.fluid(smallChangeMajorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorDrain); } };
							case DRAIN:
								return new RacialEffectUtil("[style.colourBad(--)]阴道润滑(" + Units.fluid(smallChangeDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeDrain); } };
							case MINOR_DRAIN:
								return new RacialEffectUtil("[style.colourMinorBad(--)]阴道润滑(" + Units.fluid(smallChangeMinorDrain) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorDrain); } };
							case MINOR_BOOST:
								return new RacialEffectUtil("[style.colourMinorGood(++)]阴道润滑(+" + Units.fluid(smallChangeMinorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMinorBoost); } };
							case BOOST:
								return new RacialEffectUtil("[style.colourGood(++)]阴道润滑(+" + Units.fluid(smallChangeBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeBoost); } };
							case SPECIAL:
							case MAJOR_BOOST:
								return new RacialEffectUtil("[style.colourExcellent(++)]阴道润滑(+" + Units.fluid(smallChangeMajorBoost) + ")") { @Override public String applyEffect() { return target.incrementVaginaWetness(smallChangeMajorBoost); } };
						}
				}
				break;
			default:
				break;
		}

		return new RacialEffectUtil("随机非种族转化") {
			@Override
			public String applyEffect() {
				TFModifier mod = TFModifier.NONE, modSecondary = TFModifier.NONE;

				while (mod == TFModifier.NONE || modSecondary == TFModifier.NONE) {
					mod = TFModifier.getTFRacialBodyPartsList().get(Util.random.nextInt(TFModifier.getTFRacialBodyPartsList().size()));
					modSecondary = getRacialSecondaryModifiers(race, mod).get(Util.random.nextInt(getRacialSecondaryModifiers(race, mod).size()));
				}

				TFPotency pot = getRacialPotencyModifiers(race, mod, modSecondary).get(Util.random.nextInt(getRacialPotencyModifiers(race, mod, modSecondary).size()));

				return getRacialEffect(race, mod, modSecondary, pot, user, target).applyEffect();
			}
		};
	}

	private static RacialEffectUtil getAntennaTypeRacialEffectUtil(AbstractRace race, GameCharacter target, int index) {
		List<AbstractAntennaType> antennaTypes = RacialBody.valueOfRace(race).getAntennaTypes(true);
		AbstractAntennaType selectedAntennaType = index >= antennaTypes.size() ? AntennaType.NONE : antennaTypes.get(index);
		
		return new RacialEffectUtil("长出"+selectedAntennaType.getTransformName()+"触须。") {
			@Override public String applyEffect() { return target.setAntennaType(selectedAntennaType); } };
	}

}
