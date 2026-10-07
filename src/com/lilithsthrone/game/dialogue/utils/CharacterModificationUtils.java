package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAntennaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHairType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
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
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.FootStructure;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
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
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Units.ValueType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static com.lilithsthrone.game.character.GameCharacter.MAXIMUM_AGE;
import static com.lilithsthrone.game.character.GameCharacter.MINIMUM_AGE;

/**
 * @since 0.1.7?
 * @version 0.3.9.1
 * @author Innoxia
 */
public class CharacterModificationUtils {

	private static StringBuilder contentSB = new StringBuilder();

	public static final int FLUID_INCREMENT_SMALL = 5;
	public static final int FLUID_INCREMENT_AVERAGE = 50;
	public static final int FLUID_INCREMENT_LARGE = 500;
	public static final int FLUID_INCREMENT_HUGE = 5000;

	public static final int FLUID_REGEN_INCREMENT_SMALL = 100;
	public static final int FLUID_REGEN_INCREMENT_AVERAGE = 1000;
	public static final int FLUID_REGEN_INCREMENT_LARGE = 10000;
	public static final int FLUID_REGEN_INCREMENT_HUGE = 100000;

	public static final int MAX_AGE_PLAYER = 50;
	public static final int MAX_AGE_NPC = 60;

	public static String getInformationDiv(String id, TooltipInformationEventListener information) {
		return getInformationDiv(id, information, false);
	}
	
	public static String getInformationDiv(String id, TooltipInformationEventListener information, boolean left) {
		Game.informationTooltips.put(id, information);
		return "<div class='title-button no-select' id='"+id+"' style='position:absolute; "+(left?"left:8px; right:auto;":"left:auto; right:8px;")+" top:8px; background:transparent; padding:0; margin:0;'>"
					+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
				+"</div>";
	}
	
	public static String getStartDateDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='cosmetics-inner-container full'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>起始日期</b></p>");
			contentSB.append("<p style='text-align:center;'>"
								+ "选择游戏开始时的月份。"
							+ "</p>");
	
			for(Month month : Month.values()) {
				if(Main.game.getStartingDate().getMonth() == month) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(month.getDisplayName(TextStyle.FULL, Locale.CHINESE))+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='STARTING_MONTH_"+month+"' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.GENERIC_MINOR_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(month.getDisplayName(TextStyle.FULL, Locale.CHINESE))+"</span>"
							+ "</div>");
				}
			}

		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	// Basics:
	
	public static String getGenderChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-half-width' style='text-align:center;'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>性别</b></p>");
		
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>"
								+ "你的性别决定了你开始游戏时所拥有的生殖器。"
							+ "</p>");
			}
			
			if(BodyChanging.getTarget().getGender().getGenderName().isHasVagina()) {
				contentSB.append(
						"<div id='CHOOSE_GENDER_MALE' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.MASCULINE.getShades()[0]+";'>男性</span>"
						+ "</div>"
						+ "<div class='cosmetics-button active'>"
							+ "[style.boldFeminine(女性)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "[style.colourMasculine(男性)]"
						+ "</div>"
						+ "<div id='CHOOSE_GENDER_FEMALE' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.FEMININE.getShades()[0]+";'>女性</span>"
						+ "</div>");
			}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getFemininityChoiceDiv() {
		contentSB.setLength(0);

		contentSB.append("<div class='container-half-width' style='text-align:center;'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>女性化程度</b></p>");
		
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>"
									+ "“女性化程度”控制你的外表有多接近女性或者男性"
								+ "</p>");
			}
			
//			if(BodyChanging.getTarget().getGender().getGenderName().isHasVagina()) {
//            if(BodyChanging.getTarget().getFemininity()==Femininity.ANDROGYNOUS) {
//                contentSB.append(
//                        "<div class='cosmetics-button active'>"
//                            + "[style.colourAndrogynous(Androgynous)]"
//                        + "</div>");
//            } else {
//                contentSB.append(
//                        "<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
//                            + "<span style='color:"+PresetColour.ANDROGYNOUS.getShades()[0]+";'>Androgynous</span>"
//                        + "</div>");
//            }
        if(BodyChanging.getTarget().getFemininity()==Femininity.FEMININE_STRONG) {
            contentSB.append(
                    "<div class='cosmetics-button active'>"
                        + "[style.colourFeminineStrong(极端女性化)]"
                    + "</div>");
        } else {
            contentSB.append(
                    "<div id='CHOOSE_FEM_FEMININE_STRONG' class='cosmetics-button'>"
                        + "<span style='color:"+PresetColour.FEMININE_PLUS.getShades()[0]+";'>极端女性化</span>"
                    + "</div>");
        }

        if(BodyChanging.getTarget().getFemininity()==Femininity.FEMININE) {
            contentSB.append(
                    "<div class='cosmetics-button active'>"
                            + "[style.colourFeminine(女性化)]"
                            + "</div>");
        } else {
            contentSB.append(
                    "<div id='CHOOSE_FEM_FEMININE' class='cosmetics-button'>"
                            + "<span style='color:"+PresetColour.FEMININE.getShades()[0]+";'>女性化</span>"
                            + "</div>");
        }
//			} else {
        if(BodyChanging.getTarget().getFemininity()==Femininity.ANDROGYNOUS) {
            contentSB.append(
                    "<div class='cosmetics-button active'>"
                        + "[style.colourAndrogynous(中性)]"
                    + "</div>");
        } else {
            contentSB.append(
                    "<div id='CHOOSE_FEM_ANDROGYNOUS' class='cosmetics-button'>"
                        + "<span style='color:"+PresetColour.ANDROGYNOUS.getShades()[0]+";'>中性</span>"
                    + "</div>");
        }
        if(BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE) {
            contentSB.append(
                    "<div class='cosmetics-button active'>"
                        + "[style.colourMasculine(男性化)]"
                    + "</div>");
        } else {
            contentSB.append(
                    "<div id='CHOOSE_FEM_MASCULINE' class='cosmetics-button'>"
                        + "<span style='color:"+PresetColour.MASCULINE.getShades()[0]+";'>男性化</span>"
                    + "</div>");
        }
        if(BodyChanging.getTarget().getFemininity()==Femininity.MASCULINE_STRONG) {
            contentSB.append(
                    "<div class='cosmetics-button active'>"
                        + "[style.colourMasculineStrong(极端男性化)]"
                    + "</div>");
        } else {
            contentSB.append(
                    "<div id='CHOOSE_FEM_MASCULINE_STRONG' class='cosmetics-button'>"
                        + "<span style='color:"+PresetColour.MASCULINE_PLUS.getShades()[0]+";'>极端男性化</span>"
                    + "</div>");
        }
//			}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getPersonalityChoiceDiv(boolean allowSpecials) {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width' style='text-align:center;'>");
		
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>个性</b></p>");
				
				if(BodyChanging.getTarget().isPlayer()) {
					contentSB.append("<p style='text-align:center;'>"
									+ "你的性格会在一些情况下产生少量影响。"
									+ "但不会在游戏中锁定任何选项，这些选项主要是为了满足角色扮演的需求。"
								+ "</p>");
				}
				
				for(PersonalityTrait trait : PersonalityTrait.values()) {
					if(allowSpecials || !trait.isSpecialRequirements()) {
						if(BodyChanging.getTarget().hasPersonalityTrait(trait)) {
							contentSB.append(
									"<div id='PERSONALITY_TRAIT_"+trait+"' class='cosmetics-button active'>"
										+ "<span style='color:"+trait.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(trait.getName())+"</span>"
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div id='PERSONALITY_TRAIT_"+trait+"' class='cosmetics-button'>"
											+ "[style.colourDisabled("+Util.capitaliseSentence(trait.getName())+")]"
									+ "</div>");
						}
					}
				}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getObedienceChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
		
			contentSB.append("<div class='cosmetics-inner-container full'>");
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>服从</b></p>");
				
				for(ObedienceLevel obedience : ObedienceLevel.values()) {
					if(BodyChanging.getTarget().getObedience()==obedience) {
						contentSB.append(
								"<div id='OBEDIENCE_LEVEL_"+obedience+"' class='cosmetics-button active'>"
									+ "<span style='color:"+obedience.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='OBEDIENCE_LEVEL_"+obedience+"' class='cosmetics-button'>"
										+ "[style.colourDisabled("+Util.capitaliseSentence(obedience.getName())+")]"
								+ "</div>");
					}
				}
			
			contentSB.append("</div>");
			
		contentSB.append("</div>");
		
		return contentSB.toString();
	}

	public static String getAffectionChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
		
			contentSB.append("<div class='cosmetics-inner-container full'>");
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>好感</b></p>");
				
				for(AffectionLevel affection : AffectionLevel.values()) {
					if(BodyChanging.getTarget().getAffectionLevel(Main.game.getPlayer())==affection) {
						contentSB.append(
								"<div id='AFFECTION_LEVEL_"+affection+"' class='cosmetics-button active'>"
									+ "<span style='color:"+affection.getColour().getShades()[2]+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='AFFECTION_LEVEL_"+affection+"' class='cosmetics-button'>"
										+ "[style.colourDisabled("+Util.capitaliseSentence(affection.getName())+")]"
								+ "</div>");
					}
				}
			
			contentSB.append("</div>");
			
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	
	public static void performAgeCheck() {
		int age = (int) ChronoUnit.YEARS.between(BodyChanging.getTarget().getBirthday(), Main.game.getDateNow());
		
		/*if(BodyChanging.getTarget().isPlayer() && age > CharacterModificationUtils.MAX_AGE_PLAYER) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(age-CharacterModificationUtils.MAX_AGE_PLAYER));
			
		} else if(!BodyChanging.getTarget().isPlayer() && age > (CharacterModificationUtils.MAX_AGE_NPC - MINIMUM_AGE)) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(age-(CharacterModificationUtils.MAX_AGE_NPC - MINIMUM_AGE)));
		}*/
		if(age > (MAXIMUM_AGE - MINIMUM_AGE)) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().plusYears(age-(MAXIMUM_AGE - MINIMUM_AGE)));
			int age2 = (int) ChronoUnit.YEARS.between(BodyChanging.getTarget().getBirthday(), Main.game.getDateNow());
			System.out.println("Age being increased : Was [" + age + "] now [" + age2 + "]");
		}
		
		if(age < MINIMUM_AGE) {
			BodyChanging.getTarget().setBirthday(BodyChanging.getTarget().getBirthday().minusYears(MINIMUM_AGE - age));
			int age2 = (int) ChronoUnit.YEARS.between(BodyChanging.getTarget().getBirthday(), Main.game.getDateNow());
			System.out.println("Age being lowered : Was [" + age + "] now [" + age2 + "]");
		}
		
		if(age < 0) {
			BodyChanging.getTarget().setBirthday(Main.game.getDateNow());
		}
	}
	
	public static String getBirthdayChoiceDiv() {
		contentSB.setLength(0);

		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>生日</b></p>");
			
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>"
									+ "你出生于"
										+Units.date(BodyChanging.getTarget().getBirthday(), Units.DateType.LONG)+"，因而你"+Util.intToString(BodyChanging.getTarget().getAgeValue())+"岁。"
								+ "</p>");
			}
			
			contentSB.append("<div class='container-full-width' style='margin:0;padding;0;width:100%;'>");
			
				contentSB.append("<div class='container-full-width' style='width:calc(33.3% - 16px);'>");
					contentSB.append(applyDateWrapper("日期", "BIRTH_DAY", "", "", String.valueOf(BodyChanging.getTarget().getBirthday().getDayOfMonth()), false, false));
				contentSB.append("</div>");

				contentSB.append("<div class='container-full-width' style='width:calc(33.3% - 16px);'>");
					contentSB.append(applyDateWrapper("月份", "BIRTH_MONTH", "", "", BodyChanging.getTarget().getBirthday().getMonth().getDisplayName(TextStyle.FULL, Locale.CHINESE), false, false));
				contentSB.append("</div>");

				contentSB.append("<div class='container-full-width' style='width:calc(33.3% - 16px);'>");
					contentSB.append(applyDateWrapper("年龄", "AGE", "", "",
							String.valueOf(BodyChanging.getTarget().getAgeValue()),
							BodyChanging.getTarget().getAgeValue()<=MINIMUM_AGE,
							BodyChanging.getTarget().getAgeValue()>=MAXIMUM_AGE));
				contentSB.append("</div>");
				
			contentSB.append("</div>");
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String applyDateWrapper(String title, String id, String measurement, String measurementPlural, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return "<p style='width:100%;  margin:0; padding:0; text-align:center;'>"
						+ "<b>"+title+"</b>"
					+ "</p>"
					+ "<div class='container-half-width' style='width:30%; margin:0; padding:0; text-align:center;'>"
						+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (decreaseDisabled?"[style.boldDisabled(--"+measurementPlural+")]":"[style.boldBad(--)]")
						+ "</div>"
						+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (decreaseDisabled?"[style.boldDisabled(-"+measurement+")]":"[style.boldMinorBad(-)]")
						+ "</div>"
					+ "</div>"
					+ "<div class='container-half-width' style='width:40%; margin:0; padding:0; text-align:center;'>"
						+ value
					+ "</div>"
					+ "<div class='container-half-width' style='width:30%; margin:0; padding:0; text-align:center;'>"
						+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (increaseDisabled?"[style.boldDisabled(+"+measurement+")]":"[style.boldMinorGood(+)]")
						+ "</div>"
						+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:0 1%;'>"
							+ (increaseDisabled?"[style.boldDisabled(++"+measurementPlural+")]":"[style.boldGood(++)]")
						+ "</div>"
					+ "</div>";
	}
	
	public static String getAgeChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-half-width'>");
			contentSB.append(applyDateWrapper("年龄", "AGE", "", "",
					String.valueOf(BodyChanging.getTarget().getAgeValue()),
					BodyChanging.getTarget().getAgeValue()<=MINIMUM_AGE,
					BodyChanging.getTarget().isPlayer()
						?BodyChanging.getTarget().getAgeValue()>=MAX_AGE_PLAYER
						:BodyChanging.getTarget().getAgeValue()>=MAX_AGE_NPC));
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getOrientationChoiceDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isPlayer()) {
			contentSB.append("<div class='container-full-width' style='text-align:center;'>");
		} else {
			contentSB.append("<div class='container-half-width' style='text-align:center;'>");
		}
			contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>性取向</b></p>");
			
			if(BodyChanging.getTarget().isPlayer()) {
				contentSB.append("<p style='text-align:center;'>"
								+"性取向是由你受到女性特质或男性特质的吸引程度所决定的。"
								+ "<br/><i>将鼠标悬停在角色状态效果栏中的取向图标上以查看效果。</i>"
							+ "</p>");
			}
			
			for(SexualOrientation orientation : SexualOrientation.values()) {
				if(BodyChanging.getTarget().getSexualOrientation() == orientation) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+orientation.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(orientation.getName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='SEXUAL_ORIENTATION_"+orientation+"' class='cosmetics-button'>"
								+ "<span style='color:"+orientation.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(orientation.getName())+"</span>"
							+ "</div>");
				}
			}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static String getFetishChoiceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<div class='container-full-width' style='padding:0;'>");
				contentSB.append("<p style='text-align:center; margin:0; padding:0;'><b>性癖</b></p>");
				
				int i=0;
				for(AbstractFetish fetish : Fetish.getAllFetishes()) {
					if((fetish.isAvailable(BodyChanging.getTarget()) || (fetish==Fetish.FETISH_PURE_VIRGIN && Main.game.getPlayer().hasVagina())) // Always allow virgin fetish so that players can start as broken virgin
							&& fetish.isContentEnabled()
							&& fetish.getFetishesForAutomaticUnlock().isEmpty()) {
						contentSB.append("<div class='container-full-width inner' style='width:100%; margin:0; padding:0; background:"+(i%2==0?PresetColour.BACKGROUND:PresetColour.BACKGROUND_ALT).toWebHexString()+";'>");
						
							contentSB.append("<div class='container-full-width inner' style='margin:0; padding:0 0 0 20px; width:25%; text-align:center;background:transparent;'>");
								contentSB.append("<span style='color:"+BodyChanging.getTarget().getFetishDesire(fetish).getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fetish.getName(BodyChanging.getTarget()))+"</span>");
							contentSB.append("</div>");
	
							contentSB.append(
									getInformationDiv(
											"FETISH_INFO_"+Fetish.getIdFromFetish(fetish),
											new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(fetish.getName(BodyChanging.getTarget())), fetish.getDescription(null)),
											true));
							
							contentSB.append("<div class='container-full-width inner' style='margin:0; padding:0; width:75%; text-align:center; background:transparent;'>");
							for(FetishDesire desire : FetishDesire.values()) {
								if(BodyChanging.getTarget().getFetishDesire(fetish)==desire) {
									contentSB.append("<div class='cosmetics-button active' style='width:18%; margin:1%; min-width:0;'>"
											+ "<span style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getName())+"</span></div>");
								} else {
									contentSB.append("<div id='FETISH_DESIRE_"+Fetish.getIdFromFetish(fetish)+desire+"' class='cosmetics-button' style='width:18%; margin:1%; min-width:0;'>"
											+ "<span style='color:"+desire.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(desire.getName())+"</span></div>");
								}
							}
							contentSB.append("</div>");
							
						contentSB.append("</div>");
					}
					i++;
				}
			
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	
	public static int maxSexExperience = 500;
	public static int[] normalSexExperienceValues = new int[] {0, 1, 25, 100, 250};
	public static float[] sexExperienceCorruption = new float[] {0, 0.5f, 1, 2.5f, 5};
	
	private static Colour[] sexColours = new Colour[] {PresetColour.GENERIC_EXCELLENT, PresetColour.BASE_PINK_LIGHT, PresetColour.BASE_PINK_SALMON, PresetColour.BASE_PINK, PresetColour.BASE_PINK_DEEP};
	public static String[] feminineNames = new String[] {"初探", "青涩", "熟练", "专家", "荡妇"};
	public static String[] masculineNames = new String[] {"初探", "青涩", "熟练", "专家", "种马"};
	
//	public static String[] virginityLossesGynephilic = new String[] {"your girlfriend", "", "some girl in your apartment", "some girl in a club's restroom"};
//	public static String[] virginityLossesAmbiphilic = new String[] {"your girlfriend in her apartment", "your girlfriend in your apartment", "some girl in her apartment", "some girl in your apartment", "some girl in a club's restroom",
//			"your boyfriend in his apartment", "your boyfriend in your apartment", "some guy in his apartment", "some guy in your apartment", "some guy in a club's restroom"};
//	public static String[] virginityLossesAndrophilic = new String[] {"your boyfriend in his apartment", "your boyfriend in your apartment", "some guy in his apartment", "some guy in your apartment", "some guy in a club's restroom"};
//	
	
	
	public static String getSexualExperienceDiv() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>"
							+ "<div class='container-full-width' style='text-align:center;'><b>性行为[style.colourSex(已执行)]</b></div>");
		
			contentSB.append(
							getSexExperienceEntry("HANDJOBS_GIVEN",
									"手淫",
									"你给别人撸管过",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS))
							
							+ getSexExperienceEntry("FINGERINGS_GIVEN",
									"指交",
									"你给别人指交过",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA))
							
							+ getSexExperienceEntry("BLOWJOBS_GIVEN",
									"口交",
									"你吃过别人的鸡巴",
									new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))
							
							+ getSexExperienceEntry("CUNNILINGUS_GIVEN",
									"舔阴",
									"你给别人舔过逼",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))

							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANILINGUS_GIVEN",
										"吻肛",
										"你舔过别人的肛门",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))
									:"")
			
							+ getSexExperienceEntry("VAGINAL_GIVEN",
									"阴道性交",
									"你操过别人的小穴",
									new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))
							
							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANAL_GIVEN",
										"肛交",
										"你操过别人的屁股",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))
									:""));
		contentSB.append("</div>");

		contentSB.append("<div class='container-full-width'>"
							+ "<div class='container-full-width' style='text-align:center;'><b>性行为[style.colourSexDom(接受)]</b></div>");
			contentSB.append(
							(BodyChanging.getTarget().hasPenis()
									?getSexExperienceEntry("HANDJOBS_TAKEN",
										"手淫",
										"别人给你撸管过",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER))
									:"")
							
							+ (BodyChanging.getTarget().hasVagina()
									?getSexExperienceEntry("FINGERINGS_TAKEN",
										"指交",
										"别人为你指交过",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER))
									:"")
							
							+ (BodyChanging.getTarget().hasPenis()
									?getSexExperienceEntry("BLOWJOBS_TAKEN",
										"口交",
										"有人吃过你的鸡巴",
										new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))
									:"")
							
							+ (BodyChanging.getTarget().hasVagina()
									?getSexExperienceEntry("CUNNILINGUS_TAKEN",
										"舔阴",
										"你的小穴被舔过",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE))
									:"")
							
							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANILINGUS_TAKEN",
										"吻肛",
										"你的肛门被别人舔过",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))
									:"")
							
							+ (BodyChanging.getTarget().hasVagina()
									?getSexExperienceEntry("VAGINAL_TAKEN",
										"阴道性交",
										"你的小穴被操过",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))
									:"")

							+ (Main.game.isAnalContentEnabled()
									?getSexExperienceEntry("ANAL_TAKEN",
										"肛交",
										"你的屁眼被操过",
										new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS))
									:""));
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	public static void resetImpossibeSexExperience() {
		if(!BodyChanging.getTarget().hasVagina()) {
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), 0);
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER), 0);
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE), 0);
		}
		if(!BodyChanging.getTarget().hasPenis()) {
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 0);
			CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER), 0);
		}
	}

	public static void incrementSexExperience(SexType type, int increment) {
		int count = BodyChanging.getTarget().getSexCount(null, type) + increment;
		setSexExperience(type, count);
	}
	
	public static void setSexExperience(SexType type, int count) {
		count = Math.min(maxSexExperience, Math.max(0, count));
		int index = getSexExperienceIndex(type);
		BodyChanging.getTarget().incrementAttribute(Attribute.MAJOR_CORRUPTION, -sexExperienceCorruption[index]);
		BodyChanging.getTarget().setSexCount(null, type, count);
		index = getSexExperienceIndex(type);
		BodyChanging.getTarget().incrementAttribute(Attribute.MAJOR_CORRUPTION, sexExperienceCorruption[index]);
		
		
		if(count!=0) {
			if(BodyChanging.getTarget().getSexualOrientation()==SexualOrientation.GYNEPHILIC
					|| (BodyChanging.getTarget().getSexualOrientation()==SexualOrientation.AMBIPHILIC && !BodyChanging.getTarget().isFeminine())) {
				BodyChanging.getTarget().setVirginityLoss(type, "", BodyChanging.getTarget().isPlayer()?"your girlfriend":"a stranger");
			} else {
				BodyChanging.getTarget().setVirginityLoss(type, "", BodyChanging.getTarget().isPlayer()?"your boyfriend":"a stranger");
			}
		} else {
			BodyChanging.getTarget().resetVirginityLoss(type);
		}
		
//		System.out.println(BodyChanging.getTarget().getNameIgnoresPlayerKnowledge()+": "+type+" | "+BodyChanging.getTarget().getVirginityLossDescription(type));
		
		if(type.getPerformingSexArea()==SexAreaPenetration.PENIS) {
			if(count==0 || type.getTargetedSexArea().isPenetration() || (type.getTargetedSexArea().isOrifice() && !((SexAreaOrifice)type.getTargetedSexArea()).isInternalOrifice())) {
				BodyChanging.getTarget().setPenisVirgin(true);
			} else {
				BodyChanging.getTarget().setPenisVirgin(false);
			}
		}
		
		if(type.getTargetedSexArea()==SexAreaPenetration.PENIS) {
			if(type.getPerformingSexArea().isOrifice()) {
				switch((SexAreaOrifice)type.getPerformingSexArea()) {
					case ANUS:
						if(count==0) {
							BodyChanging.getTarget().setAssVirgin(true);
						} else {
							BodyChanging.getTarget().setAssVirgin(false);
						}
						break;
					case ARMPITS:
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(count==0) {
							BodyChanging.getTarget().setFaceVirgin(true);
						} else {
							BodyChanging.getTarget().setFaceVirgin(false);
						}
						break;
					case NIPPLE:
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(count==0) {
							BodyChanging.getTarget().setVaginaVirgin(true);
						} else {
							BodyChanging.getTarget().setVaginaVirgin(false);
						}
						break;
					case SPINNERET:
						if(count==0) {
							BodyChanging.getTarget().setSpinneretVirgin(true);
						} else {
							BodyChanging.getTarget().setSpinneretVirgin(false);
						}
						break;
				}
			}
		}
	}
	
	private static int getSexExperienceIndex(SexType associatedSexType) {
		int index = 0;
		for(int i=normalSexExperienceValues.length-1; i>=0; i--) {
			if(BodyChanging.getTarget().getTotalSexCount(associatedSexType) >= normalSexExperienceValues[i]) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	private static String getSexExperienceEntry(String id, String title, String description, SexType associatedSexType) {
		int index = getSexExperienceIndex(associatedSexType);
		
		String[] names = BodyChanging.getTarget().isFeminine()?feminineNames:masculineNames;
		
		int sexCount = BodyChanging.getTarget().getTotalSexCount(associatedSexType);
		boolean decreaseDisabled = sexCount<=0;
		boolean increaseDisabled = sexCount>=maxSexExperience;
		int singleStep = 1;
		int minorStep = 10;
		int majorStep = 100;
		
		return "<div class='container-full-width inner'>"
					+ "<div class='container-full-width inner' style='width:25%;margin:0;padding:0; font-weight:bold; text-align:center;'>"
						+ title
					+ "</div>"
					+ "<div class='container-full-width inner' style='width:75%;margin:0;padding:0;text-align:center;'>"
						+ "<i>"+description+"的次数。</i>"
					+ "</div>"
					
					+ "<div class='container-full-width' style='width:100%;margin:0;padding:0; display:flex; align-items:baseline;'>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+majorStep+")]":"[style.boldBad(-"+majorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+minorStep+")]":"[style.boldBad(-"+minorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE_SMALL' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+singleStep+")]":"[style.boldBadMinor(-"+singleStep+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:8%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+"<span style='color:"+sexColours[index].toWebHexString()+";'>"+sexCount+"</span>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_INCREASE_SMALL' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+singleStep+")]":"[style.boldGoodMinor(+"+singleStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+minorStep+")]":"[style.boldGoodMinor(+"+minorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:30%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+majorStep+")]":"[style.boldGood(+"+majorStep+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width inner' style='width:40%; margin:0;padding:0;text-align:center;'>"
							+"<span style='color:"+sexColours[index].toWebHexString()+";'>"+names[index]+"</span>"
							+" ([style.colourCorruption(+"+sexExperienceCorruption[index]+"堕落)])"
						+ "</div>"
					+ "</div>"
				+ "</div>";
	}
	
	
	
	// Advanced:
	
	public static String applyWrapper(String title, String description, String id, String input, boolean halfWidth) {
		if(halfWidth) {
			return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:48%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
						+ "<p style='text-align:center; margin:0; padding:0;'>"
							+ "<b>"+title+"</b>"
						+"</p>"
						+ input
					+ "</div>";
			
		} else {
			return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:98%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
//						+"<div class='cosmetics-inner-container left'>"
//							+ "<p style='text-align:center; margin:0; padding:0;'>"
//								+ "<b>" +title+"</b>"
//							+"</p>"
//						+ "</div>"
//						+ "<div class='cosmetics-inner-container right'>"
//							+ input
//						+ "</div>"
						+ "<p style='text-align:center; margin:0; padding:0;'>"
							+ "<b>"+title+"</b>"
						+"</p>"
						+ input
					+ "</div>";
		}
	}

	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled) {
		return applyFullVariableWrapper(title, description, id, minorStep, majorStep, value, decreaseDisabled, increaseDisabled, null, false);
	}
	
	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled, boolean fullWidth) {
		return applyFullVariableWrapper(title, description, id, minorStep, majorStep, value, decreaseDisabled, increaseDisabled, null, fullWidth);
	}
	

	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled, String additionalDescription) {
		return applyFullVariableWrapper(title, description, id, minorStep, majorStep, value, decreaseDisabled, increaseDisabled, additionalDescription, false);
	}
	
	private static String applyFullVariableWrapper(String title, String description, String id, String minorStep, String majorStep, String value, boolean decreaseDisabled, boolean increaseDisabled, String additionalDescription, boolean fullWidth) {
			return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:"+(fullWidth?"98":"48")+"%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ "<p style='margin:0; padding:0;'>"
							+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
							+ "<b>"+title+"</b>"
						+"</p>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+majorStep+")]":"[style.boldBad(-"+majorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled(-"+minorStep+")]":"[style.boldBadMinor(-"+minorStep+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:48%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ value
						+ "</div>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+minorStep+")]":"[style.boldGoodMinor(+"+minorStep+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:48%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+majorStep+")]":"[style.boldGood(+"+majorStep+")]")
							+ "</div>"
						+ "</div>"
						+(additionalDescription!=null && !additionalDescription.isEmpty()
							?"<div class='container-full-width' style='width:98%; margin:1%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"+additionalDescription+"</div>"
							:"")
					+ "</div>";
	}


	private static String applyFullVariableWrapperSizes(String title, String description, String id, double value, boolean decreaseDisabled, boolean increaseDisabled, boolean fullWidth) {
		return applyFullVariableWrapper(title, description, id, Units.size(1), Units.size(5),
				Units.size(value, Units.ValueType.PRECISE, Units.UnitType.SHORT),decreaseDisabled, increaseDisabled, fullWidth);
	}
	
	private static String applyVariableWrapperFluids(String title, String description, String id, String value, boolean decreaseDisabled, boolean increaseDisabled, int incrementSmall, int incrementAverage, int incrementLarge, int incrementHuge) {
		return "<div class='cosmetics-inner-container' style='margin:1% 1%; width:48%; padding:1%; box-sizing:border-box; position:relative'>"
					+ "<p style='margin:0; padding:0;'>"
						+ getInformationDiv(id, new TooltipInformationEventListener().setInformation(title, description))
						+ "<b>"+title+"</b>"
					+"</p>"
					+ "<div class='container-full-width'>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_DECREASE_SMALL' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementSmall)+")]":"[style.boldBadMinor("+Units.fluid(-incrementSmall)+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementAverage)+")]":"[style.boldBadMinor("+Units.fluid(-incrementAverage)+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE_LARGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementLarge)+")]":"[style.boldBad("+Units.fluid(-incrementLarge)+")]")
							+ "</div>"
							+ "<div id='"+id+"_DECREASE_HUGE' class='normal-button"+(decreaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (decreaseDisabled?"[style.boldDisabled("+Units.fluid(-incrementHuge)+")]":"[style.boldBad("+Units.fluid(-incrementHuge)+")]")
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:48%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ value
						+ "</div>"
						+ "<div class='container-full-width' style='width:25%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='"+id+"_INCREASE_SMALL' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementSmall)+")]":"[style.boldGoodMinor(+"+Units.fluid(incrementSmall)+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementAverage)+")]":"[style.boldGoodMinor(+"+Units.fluid(incrementAverage)+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_LARGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementLarge)+")]":"[style.boldGood(+"+Units.fluid(incrementLarge)+")]")
							+ "</div>"
							+ "<div id='"+id+"_INCREASE_HUGE' class='normal-button"+(increaseDisabled?" disabled":"")+"' style='width:98%; margin:1%; padding:0;'>"
								+ (increaseDisabled?"[style.boldDisabled(+"+Units.fluid(incrementHuge)+")]":"[style.boldGood(+"+Units.fluid(incrementHuge)+")]")
							+ "</div>"
						+ "</div>"
					+ "</div>"
				+ "</div>";
	}
	

	public static String getAgeAppearanceChoiceDiv() {
		return applyFullVariableWrapper(
				"外表年龄",
				UtilText.parse(BodyChanging.getTarget(),
						"Change how old [npc.name] [npc.verb(appear)] to be. [npc.She] [npc.is] limited to looking as young as " + MINIMUM_AGE + ", or up to "
						+ Util.intToString(BodyChanging.getTarget().getAgeDifferenceUpperLimit())
						+ "岁。"
						+ "<br/><i>单纯只是外表改变，不会影响游戏内的选择。</i>"),
				"AGE_APPEARANCE",
				"1",
				"5",
				String.valueOf(BodyChanging.getTarget().getAppearsAsAgeValue()),
				BodyChanging.getTarget().getAppearsAsAgeValue()<=18,
				BodyChanging.getTarget().getAppearsAsAgeValue()>=(BodyChanging.getTarget().getAgeValue()+BodyChanging.getTarget().getAgeDifferenceUpperLimit()))
				
				+ applyWrapper("生日",
						UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos]的生日无法被改变，但可以通过转化[npc.her]的身体，使其显得比真实年龄更加年轻或年长。"),
						"BIRTHDAY",
						"<p style='text-align:center; margin:0; padding:0;'>"
							+ BodyChanging.getTarget().getBirthdayString()
								+ UtilText.parse(BodyChanging.getTarget(),
									"</br>设置[npc.namePos]的真实年龄：<b style='color:"+BodyChanging.getTarget().getAge().getColour().toWebHexString()+"'>"+BodyChanging.getTarget().getAgeValue()+"</b>")
						+"</p>",
						true);
	}
	
	public static String getHeightChoiceDiv(boolean fullWidth) {
		return applyFullVariableWrapperSizes("身高",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.name]的身高。"
						+ "<br/><i>这影响部分细微的描述，并且用以决定某个性交场景是否有“体型差”。</i>"
						+ (!Main.game.isInNewWorld()
							?"<br/>[style.italicsMinorBad(角色创建时身高最大为[units.sizes("+Height.getMaximumHeightForCharacterCreation()+")]"
									+ "在角色创建时设定，但后续可提升至[units.sizes("+Height.SEVEN_COLOSSAL.getMaximumValue()+")]。)]"
							:"")),
				"HEIGHT",
				BodyChanging.getTarget().getHeightValue(),
				BodyChanging.getTarget().getHeightValue()<=BodyChanging.getTarget().getMinimumHeight(),
				BodyChanging.getTarget().getHeightValue()
					>= (Main.game.isInNewWorld()
							?BodyChanging.getTarget().getMaximumHeight()
							:Height.getMaximumHeightForCharacterCreation()),
				fullWidth);
	}
	
	public static String getSelfTransformFemininityChoiceDiv() {
		return applyFullVariableWrapper(
				"女性化程度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的身体有多女性化或男性化。"
						+ "<br/><i>这影响对话上色，并且决定了某件衣服对于[npc.herHim]来说是否太过女性化或男性化，并且同样会被用来决定性吸引力(基于他人的去向)。</i>"),
				"FEMININITY",
				"1",
				"5",
				BodyChanging.getTarget().getFemininityValue()
					+"<br/><i style='color:"+BodyChanging.getTarget().getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getFemininity().getName(false))+"</i>",
				BodyChanging.getTarget().getFemininityValue()<=0,
				BodyChanging.getTarget().getFemininityValue()>=100);
	}
	
	public static String getSelfTransformBodyMaterialChoiceDiv(GameCharacter target) {
		contentSB.setLength(0);
		
		List<BodyMaterial> materials = new ArrayList<>();
		switch(target.getBodyMaterial()) {
			case AIR:
				materials.add(BodyMaterial.AIR);
				break;
			case ARCANE:
				materials.add(BodyMaterial.ARCANE);
				break;
			case FIRE:
				materials.add(BodyMaterial.FIRE);
				break;
			case FLESH:
				materials.add(BodyMaterial.FLESH);
				break;
			case SILICONE:
				materials.add(BodyMaterial.SILICONE);
				break;
			case ICE:
			case WATER:
				materials.add(BodyMaterial.WATER);
				materials.add(BodyMaterial.ICE);
				break;
			case SLIME:
				materials.add(BodyMaterial.SLIME);
				break;
			case RUBBER:
			case STONE:
				materials.add(BodyMaterial.STONE);
				materials.add(BodyMaterial.RUBBER);
				break;
		}
		
		for(BodyMaterial mat : materials) {
			if(BodyChanging.getTarget().getBodyMaterial() == mat) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+mat.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(mat.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BODY_MATERIAL_"+mat+"' class='cosmetics-button'>"
							+ "<span style='color:"+mat.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(mat.getName())+"</span>"
						+ "</div>");
			}
		}
		
		return applyWrapper("身体材质",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的身体材质。"
						+ "<br/><i>不同的身体材质类型支持不同的被动增益。将鼠标悬浮在该角色的种族状态效果上查看提示！</i>"),
				"BODY_MATERIAL",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformTailChoiceDiv(List<AbstractRace> availableRaces, boolean removeNone) {
		contentSB.setLength(0);
		
		for(AbstractTailType tail : TailType.getAllTailTypes()) {
			if((tail.getRace() !=null && availableRaces.contains(tail.getRace()))
					|| (!removeNone && tail==TailType.NONE)) {
				if(!BodyChanging.getTarget().getLegConfiguration().isAbleToGrowTail() && tail!=TailType.NONE) {
					continue;
				}
				if (!BodyChanging.getTarget().isYouko()) {
					if(BodyChanging.getTarget().getTailType()==TailType.FOX_MORPH_MAGIC && tail!=TailType.FOX_MORPH_MAGIC) {
						continue;
					}
					if(BodyChanging.getTarget().getTailType()!=TailType.FOX_MORPH_MAGIC && tail==TailType.FOX_MORPH_MAGIC) {
						continue;
					}
				}
				Colour c = PresetColour.TEXT_GREY;
				
				if(tail.getRace() != null) {
					c = tail.getRace().getColour();
				}

				boolean suitableForPenetration = (tail.getTags().contains(BodyPartTag.TAIL_SUITABLE_FOR_PENETRATION) || Main.game.isFurryTailPenetrationContentEnabled())
						&& !tail.getTags().contains(BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION);
				
				if(BodyChanging.getTarget().getTailType() == tail) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"
									+Util.capitaliseSentence(tail.getTransformName())+(suitableForPenetration?"*":"")
									+(tail.isPrehensile()?"&#8314;":"")
									+(tail.isOvipositor()?"&deg;":"")
									+(tail.isTussy()?"&macr;":"")
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TAIL_"+TailType.getIdFromTailType(tail)+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"
									+Util.capitaliseSentence(tail.getTransformName())+(suitableForPenetration?"*":"")
									+(tail.isPrehensile()?"&#8314;":"")
									+(tail.isOvipositor()?"&deg;":"")
									+(tail.isTussy()?"&macr;":"")
								+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("尾巴",
				UtilText.parse(BodyChanging.getTarget(),
						"<i>尾巴必须要适合插入(*)，并且灵活可控(⁺)，或至少有[npc.namePos]身高50%的长度，才能在性交中用于插入。"
						+ " Ovipositor-equipped tails (&deg;) can lay eggs in orifices during sex. Tussy-equipped tails (&macr;) can be used as a fuckable orifice.</i>"),
				"TAIL_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTailLengthDiv() {
		if(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG) {
			float percentageMinimum = BodyChanging.getTarget().isFeral()?Leg.LENGTH_PERCENTAGE_MIN_FERAL:Leg.LENGTH_PERCENTAGE_MIN;
			
			return applyFullVariableWrapper(Util.capitaliseSentence(LegConfiguration.TAIL_LONG.getName())+"长度",
					UtilText.parse(BodyChanging.getTarget(),
						"修改[npc.namePos][npc.tail]的长度。通过设置长度为[npc.namePos]身高的百分比确定，"
								+ "数值限制在"+Math.round(percentageMinimum*100)+"%到"+Math.round(Leg.LENGTH_PERCENTAGE_MAX*100)+"%之间。"),
					"TAIL_LENGTH",
					"5%",
					"25%",
					Math.round(BodyChanging.getTarget().getLegTailLengthAsPercentageOfHeight()*100)+"%"
						+ "<br/>"
						+ Units.size(BodyChanging.getTarget().getLegTailLength(false)),
					BodyChanging.getTarget().getLegTailLengthAsPercentageOfHeight()<=percentageMinimum,
					BodyChanging.getTarget().getLegTailLengthAsPercentageOfHeight()>=Leg.LENGTH_PERCENTAGE_MAX);
			
		} else {
			return applyFullVariableWrapper("尾巴长度",
					UtilText.parse(BodyChanging.getTarget(),
							BodyChanging.getTarget().hasTail()
								?"修改[npc.namePos][npc.tail]的长度。通过设置长度为[npc.namePos]身高的百分比确定，"
										+ "数值限制在"+Math.round(Tail.LENGTH_PERCENTAGE_MIN*100)+"%到"+Math.round(Tail.LENGTH_PERCENTAGE_MAX*100)+"%之间。"
								:"[npc.Name]没有尾巴，所以无法修改长度！"),
					"TAIL_LENGTH",
					"5%",
					"25%",
					Math.round(BodyChanging.getTarget().getTailLengthAsPercentageOfHeight()*100)+"%"
						+ "<br/>"
						+ Units.size(BodyChanging.getTarget().getTailLength(false))
						+ "<br/><i>根部直径："+Units.size(BodyChanging.getTarget().getTailDiameter(0))+"</i>"
						+ "<br/><i>顶端直径："+Units.size(BodyChanging.getTarget().getTailDiameter(BodyChanging.getTarget().getTailLength(false)))+"</i>",
					BodyChanging.getTarget().getTailLengthAsPercentageOfHeight()<=Tail.LENGTH_PERCENTAGE_MIN || !BodyChanging.getTarget().hasTail(),
					BodyChanging.getTarget().getTailLengthAsPercentageOfHeight()>=Tail.LENGTH_PERCENTAGE_MAX || !BodyChanging.getTarget().hasTail());
		}
	}
	
	public static String getSelfTransformTailCountDiv() {
		contentSB.setLength(0);

		if(!BodyChanging.getTarget().hasTail()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
						+ Util.capitaliseSentence("零")
					+ "</div>");
			
		} else {
			for(int i=1; i <= Tail.MAXIMUM_COUNT; i++) {
				if(BodyChanging.getTarget().getTailCount() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
							+ "</div>");
					
				} else {
					if(!BodyChanging.isDebugMenu() && (BodyChanging.getTarget().isYouko() && i > BodyChanging.getTarget().getMaxTailCount())) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
									+ Util.capitaliseSentence(Util.intToString(i))
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='TAIL_COUNT_"+i+"' class='cosmetics-button'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
								+ "</div>");
					}
				}
			}
		}
		
		return applyWrapper("尾巴数量",
				UtilText.parse(BodyChanging.getTarget(),
						!BodyChanging.getTarget().hasTail()
							?"由于[npc.name]没有尾巴，所以无法修改尾巴的数量！"
							:((BodyChanging.getTarget().isYouko()
								?"由于[npc.nameIsFull]是一只妖狐，[npc.she]能够修改拥有的尾巴数量！"
								:"修改[npc.name]拥有多少[npc.tails]。")
							+ "<br/><i>尾巴的数量会在检测某条尾巴在性交中是否适合插入时纳入考虑。</i>")),
				"TAIL_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTailGirthDiv() {
		contentSB.setLength(0);

		if(!BodyChanging.getTarget().hasTail()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
						+ Util.capitaliseSentence("N/A")
					+ "</div>");
			
		} else {
			for(PenetrationGirth girth : PenetrationGirth.values()) {
				if(BodyChanging.getTarget().getTailGirth() == girth) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTailType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TAIL_GIRTH_"+girth+"' class='cosmetics-button'>"
								+ "<span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTailType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("尾巴周长",
				UtilText.parse(BodyChanging.getTarget(),
						!BodyChanging.getTarget().hasTail()
							?("由于[npc.name]没有尾巴，所以无法修改尾巴周长！"
									+(BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG
										?"<br/>(<i>[npc.her]蛇尾的周长基于屁股大小。</i>)"
										:""))
							:("修改[npc.namePos]的[npc.tail]周长。"
									+ "<br/><i>尾巴周长影响插入时是否对于腔穴过大。</i>")),
				"TAIL_GIRTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformTentacleLengthDiv() {
		return applyFullVariableWrapper("触手长度",
				UtilText.parse(BodyChanging.getTarget(),
						BodyChanging.getTarget().hasTentacle()
							?"修改[npc.namePos][npc.tentacles]的长度。通过设置长度为[npc.namePos]身高的百分比确定，数值限制在100%至500%之内。"
							:"[npc.Name]没有触手，所以无法修改长度！"),
				"TENTACLE_LENGTH",
				"5%",
				"25%",
				Math.round(BodyChanging.getTarget().getTentacleLengthAsPercentageOfHeight()*100)+"%"
					+ "<br/>"
					+ Units.size(BodyChanging.getTarget().getTentacleLength(false)),
				BodyChanging.getTarget().getTentacleLengthAsPercentageOfHeight()<=Tentacle.LENGTH_PERCENTAGE_MIN || !BodyChanging.getTarget().hasTentacle(),
				BodyChanging.getTarget().getTentacleLengthAsPercentageOfHeight()>=Tentacle.LENGTH_PERCENTAGE_MAX || !BodyChanging.getTarget().hasTentacle());
	}

	public static String getSelfTransformTentacleGirthDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasTentacle()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
						+ Util.capitaliseSentence("N/A")
					+ "</div>");
			
		} else {
			for(PenetrationGirth girth : PenetrationGirth.values()) {
				if(BodyChanging.getTarget().getTentacleGirth() == girth) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTentacleType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TENTACLE_GIRTH_"+girth+"' class='cosmetics-button'>"
								+ "<span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getTentacleType().getGirthDescriptor(girth))+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("触手周长",
				UtilText.parse(BodyChanging.getTarget(),
						!BodyChanging.getTarget().hasTentacle()
							?"由于[npc.name]没有触手，所以无法修改触手周长！"
							:("修改[npc.namePos]的[npc.tentacle]周长。"
									+ "<br/><i>触手周长影响插入时是否对于腔穴过大。</i>")),
				"TENTACLE_GIRTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformWingSizeDiv() {
		GameCharacter target = BodyChanging.getTarget();
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasWings()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (WingSize wingSize : WingSize.values()) {
				if (target.getWingType().getMinimumSize().getValue()>wingSize.getValue()
						|| target.getWingType().getMaximumSize().getValue()<wingSize.getValue()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
									+Util.capitaliseSentence(wingSize.getName())
									+(wingSize.getValue()>=target.getLegConfiguration().getMinimumWingSizeForFlight(target.getBody()).getValue()?"*":"")
									+"</div>");
					
				} else {
					if (BodyChanging.getTarget().getWingSize() == wingSize) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
										+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(wingSize.getName())
										+(wingSize.getValue()>=target.getLegConfiguration().getMinimumWingSizeForFlight(target.getBody()).getValue()?"*":"")+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='WING_SIZE_"+wingSize+"' class='cosmetics-button'>"
										+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(wingSize.getName())
										+(wingSize.getValue()>=target.getLegConfiguration().getMinimumWingSizeForFlight(target.getBody()).getValue()?"*":"")+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("翅膀尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]翅膀的大小。"
						+ "<br/><i>翅膀大小影响[npc.namePos]的飞行能力。"
						+ "要求基于腿部配置，适合[npc.namePos]“[npc.legConfiguration]”的大小则会被标记星号(*)。部分翅膀类型不支持所有翅膀大小等级。</i>"),
				"WING_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformWingChoiceDiv(List<AbstractRace> availableRaces, boolean removeNone) {
		contentSB.setLength(0);
		
		List<AbstractWingType> sortedTypes = new ArrayList<>(WingType.getAllWingTypes());
		/*
		 * 'None' is first, then the rest of the 'no race' options.
		 * Then, the races are sorted alphabetically.
		 * Within a race (including 'no race'), options are sorted alphabetically.
		 */
		sortedTypes.sort(Comparator.comparingInt((AbstractWingType w) -> w == WingType.NONE ? 0 : 1)
				.thenComparingInt(w -> w.getRace() == Race.NONE ? 0 : 1)
				.thenComparing(w -> w.getRace().getName(false))
				.thenComparing(AbstractWingType::getTransformName));
		
		for(AbstractWingType wing : sortedTypes) {
			if((wing.getRace() !=null && availableRaces.contains(wing.getRace()))
					|| (!removeNone && wing==WingType.NONE)) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(wing.getRace() != null) {
					c = wing.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getWingType() == wing) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(wing.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='WING_"+WingType.getIdFromWingType(wing)+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(wing.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("翅膀",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的翅膀类型。"
						+ "<br/><i>支持飞行的翅膀类型(只要尺寸足够大)会用星号标记(*)。</i>"),
				"WING_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformHornChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);

		Set<AbstractHornType> types = new HashSet<>();
		for(AbstractRace race : availableRaces) {
			types.addAll(RacialBody.valueOfRace(race).getHornTypes(false));
		}
		
		
		List<AbstractHornType> sortedTypes = new ArrayList<>(types);
		/*
		 * 'None' is first, then the rest of the 'no race' options.
		 * Then, the races are sorted alphabetically.
		 * Within a race (including 'no race'), options are sorted alphabetically.
		 */
		sortedTypes.sort(Comparator.comparingInt((AbstractHornType h) -> h == HornType.NONE ? 0 : 1)
				.thenComparingInt(h -> h.getRace() == Race.NONE ? 0 : 1)
				.thenComparing(h -> h.getRace().getName(false))
				.thenComparing(AbstractHornType::getTransformName));
		
		for(AbstractHornType horn : sortedTypes) {
			if((horn.getRace()!=null && availableRaces.contains(horn.getRace()))
					|| horn.equals(HornType.NONE)) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(horn.getRace() != null) {
					c = horn.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getHornType().equals(horn)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(horn.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_"+HornType.getIdFromHornType(horn)+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(horn.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("角",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]角的类型。"
						+ "<br/><i>拥有足够长度的角能够当作部分性动作的把手，也会用于亚种识别。</i>"),
				"HORN_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHornSizeDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasHorns()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (HornLength hornLength : HornLength.values()) {
				if (HornLength.getLengthFromInt(BodyChanging.getTarget().getHornLengthValue()) == hornLength) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(hornLength.getDescriptor())+(hornLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_LENGTH_"+hornLength+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(hornLength.getDescriptor())+(hornLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("角长度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]角的长度。"
						+ "<br/><i>拥有足够长度的角能够当作部分性动作的把手(标记为星号*)，也会用于亚种识别。</i>"),
				"HORN_LENGTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHornCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasHorns()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Horn.MAXIMUM_ROWS; i++) {
				if (BodyChanging.getTarget().getHornRows() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_COUNT_"+i+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("成组角的数量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]有几组[npc.horns]。"
						+ "<br/><i>通常只是外表修改，但在亚种识别会纳入考量(如独角兽就有一组独角)。</i>"),
				"HORN_COUNT",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHornsPerRowCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasHorns()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Horn.MAXIMUM_HORNS_PER_ROW; i++) {
				if (BodyChanging.getTarget().getHornsPerRow() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='HORN_COUNT_PER_ROW_"+i+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}
		
		return applyWrapper("每组角的数量",
				UtilText.parse(BodyChanging.getTarget(), "修改一组[npc.horns]有几个。"
						+ "<br/><i>通常只是外表修改，但在亚种识别会纳入考量(如独角兽就有一组独角)。</i>"),
				"HORN_COUNT_ROWS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAntennaChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractAntennaType antenna : AntennaType.getAllAntennaTypes()) {
			if((antenna.getRace()!=null && availableRaces.contains(antenna.getRace())) || antenna==AntennaType.NONE) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(antenna.getRace() != null) {
					c = antenna.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getAntennaType() == antenna) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(antenna.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_"+AntennaType.getIdFromAntennaType(antenna)+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(antenna.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("触须",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的触须类型。"
						+ "<br/><i>仅用于亚种识别。</i>"),
				"ANTENNAE_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAntennaSizeDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasAntennae()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (HornLength antennaLength : HornLength.values()) {
				if (HornLength.getLengthFromInt(BodyChanging.getTarget().getAntennaLength()) == antennaLength) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(antennaLength.getDescriptor())+(antennaLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_LENGTH_"+antennaLength+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(antennaLength.getDescriptor())+(antennaLength.isSuitableAsHandles()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("触须长度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]触须的长度。"
						+ "<br/><i>拥有足够长度的触须能够当作部分性动作的把手(标记为星号*)，也会用于亚种识别。</i>"),
				"ANTENNA_LENGTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAntennaCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasAntennae()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Antenna.MAXIMUM_ROWS; i++) {
				if (BodyChanging.getTarget().getAntennaRows() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_COUNT_"+i+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("触须组数",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]有几组[npc.antennae]。"
						+ "<br/><i>通常只是外表修改，但在亚种识别会纳入考量(如独角兽就有一组单触须)。</i>"),
				"ANTENNA_COUNT",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAntennaePerRowCountDiv() {
		contentSB.setLength(0);
		
		if(!BodyChanging.getTarget().hasAntennae()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("N/A")
							+"</div>");
		} else {
			for (int i = 1; i<=Antenna.MAXIMUM_ANTENNAE_PER_ROW; i++) {
				if (BodyChanging.getTarget().getAntennaePerRow() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='ANTENNA_COUNT_PER_ROW_"+i+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}
		
		return applyWrapper("每组触须数量",
				UtilText.parse(BodyChanging.getTarget(), "修改一组[npc.antennae]有几个。"
						+ "<br/><i>通常只是外表修改，但在亚种识别会纳入考量(如独角兽就有一组单触须)。</i>"),
				"ANTENNA_COUNT_ROWS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformHairChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractHairType hair : HairType.getAllHairTypes()) {
			if((hair.getRace() !=null && availableRaces.contains(hair.getRace()))) {
				if(BodyChanging.getTarget().getHairType()==hair) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+hair.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hair.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='HAIR_"+HairType.getIdFromHairType(hair)+"' class='cosmetics-button'>"
								+ "<span style='color:"+hair.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hair.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("头发",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的头发类型。"
						+ "<br/><i>仅用于亚种识别。</i>"),
				"HAIR_TYPE",
				contentSB.toString(),
				false);
	}

	public static String getSelfTransformHairLengthDiv() {
		contentSB.setLength(0);
		
		for(HairLength hairLength : HairLength.values()) {
			if(BodyChanging.getTarget().getHairLength()==hairLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HAIR_LENGTH_"+hairLength+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("头发长度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.hair(true)]的长度。"
						+ "<br/><i>足够长的头发(标记星号*)可以在部分性动作中被拉扯。</i>"),
				"HAIR_LENGTH",
				contentSB.toString(),
				true);
	}

	public static String getNeckFluffDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isNeckFluff()) {
			contentSB.append(
					"<div id='NECK_FLUFF_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.TRANSFORMATION_SHRINK.getShades()[0]+";'>无颈部毛团</span>"
					+ "</div>"
					+"<div class='cosmetics-button active'>"
						+ "<span style='color:"+PresetColour.TRANSFORMATION_GROW.toWebHexString()+";'>有颈部毛团</span>"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SHRINK.toWebHexString()+";'>无颈部毛团</span>"
					+ "</div>"
					+"<div id='NECK_FLUFF_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.TRANSFORMATION_GROW.getShades()[0]+";'>有颈部毛团</span>"
					+ "</div>");
		}
		

		return applyWrapper("颈部毛团",
				UtilText.parse(BodyChanging.getTarget(), "设置[npc.name]是否在颈部和上胸部有额外的毛绒团。"
						+ "<br/><i>单纯只是外表转化。</i>"),
				"NECK_FLUFF",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfDivHairStyles(String title, String description) {
		contentSB.setLength(0);

		for (HairStyle hairStyle : HairStyle.values()) {
			if (BodyChanging.getTarget().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
						+ "</div>");
			} else {
				if(BodyChanging.getTarget().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + ")]"
							+ "</div>");
				}
			}
		}

		return applyWrapper("发型",
				description
					+ "<br/><i>“"+Util.capitaliseSentence(HairStyle.TWIN_TAILS.getName(BodyChanging.getTarget()))+"”和“"+Util.capitaliseSentence(HairStyle.TWIN_BRAIDS.getName(BodyChanging.getTarget()))+"”"
							+ "可以在部分性动作中作为把手使用。</i>",
				"HAIR_STYLE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformAssChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractAssType ass : AssType.getAllAssTypes()) {
			if((ass.getRace() !=null && availableRaces.contains(ass.getRace()))) {
				
				if(BodyChanging.getTarget().getAssType().equals(ass)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+ass.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ass.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ASS_"+AssType.getIdFromAssType(ass)+"' class='cosmetics-button'>"
								+ "<span style='color:"+ass.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ass.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("肛门",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的类型。"
						+ "<br/><i>肛门类型影响角色生成时肛门的修饰词，也会修改描述和用作亚种识别。</i>"),
				"ASSHOLE_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformBreastChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractBreastType breast : BreastType.getAllBreastTypes()) {
			if((breast.getRace() !=null && breast.getRace()!=Race.NONE && availableRaces.contains(breast.getRace()))) {
				if(BodyChanging.getTarget().getBreastType() == breast) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+breast.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_"+BreastType.getIdFromBreastType(breast)+"' class='cosmetics-button'>"
								+ "<span style='color:"+breast.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("乳头",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳头的类型。"
						+ "<br/><i>乳头类型影响角色生成时乳头的修饰词，同样也会影响乳房产生的乳汁类型。同时还会修改角色描述和用作亚种识别。</i>"),
				"NIPPLE_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformBreastCrotchChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractBreastType breast : BreastType.getAllBreastTypes()) {
			if((breast.getRace()==Race.NONE || availableRaces.contains(breast.getRace()))) {
				if(BodyChanging.getTarget().getBreastCrotchType() == breast) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+breast.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_CROTCH_"+BreastType.getIdFromBreastType(breast)+"' class='cosmetics-button'>"
								+ "<span style='color:"+breast.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(breast.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper(Util.capitaliseSentence(getCrotchBoobName(false))+"类型",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]" +getCrotchBoobName(true) + "上的乳头类型。"
						+ "<br/><i>胯乳乳头类型影响角色生成时乳头的修饰词，同样也会影响"+getCrotchBoobName(true)+"产生的乳汁类型。"
								+ "同时还会修改角色描述和用作亚种识别。</i>"),
				"CROTCH_NIPPLE_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformArmChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractArmType arm : ArmType.getAllArmTypes()) {
			if(arm.getRace() !=null && availableRaces.contains(arm.getRace())) {
				if(BodyChanging.getTarget().getArmType().equals(arm)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+arm.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ARM_"+ArmType.getIdFromArmType(arm)+"' class='cosmetics-button'>"
								+ "<span style='color:"+arm.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(arm.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("手臂",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的手臂类型。"
						+ "<br/><i>手臂类型只会修改角色描述和用于亚种识别。</i>"),
				"ARM_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformArmCountDiv() {
		contentSB.setLength(0);
		
		for(int i=1; i <= Arm.MAXIMUM_ROWS; i++) {
			if(BodyChanging.getTarget().getArmRows() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ARM_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("手臂对数",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]有多少对[npc.arms]。"
						+ "<br/><i>手臂对数会修改角色描述和用于亚种识别，同时也会决定是否拥有空闲的手臂用于性动作。</i>"),
				"ARM_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLegChoiceDiv(List<AbstractRace> availableRaces, boolean bypassRestrictions) {
		contentSB.setLength(0);
		
		for(AbstractLegType leg : LegType.getAllLegTypes()) {
			if((leg.isAvailableForSelfTransformMenu(BodyChanging.getTarget()) || bypassRestrictions) && leg.getRace()!=null && availableRaces.contains(leg.getRace())) {
				if(BodyChanging.getTarget().getLegType().equals(leg)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+leg.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='LEG_"+LegType.getIdFromLegType(leg)+"' class='cosmetics-button'>"
								+ "<span style='color:"+leg.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(leg.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("腿部",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的腿部类型。"
						+ "<br/><i>腿部类型会修改角色描述和用于亚种识别。不同的腿部类型有不同可用的腿部配置。</i>"),
				"LEG_TYPE",
				contentSB.toString(),
				true);
			
	}
	
	public static String getSelfTransformFootStructureChoiceDiv() {
		contentSB.setLength(0);
		
		for(FootStructure footStructure : FootStructure.values()) {
			if(BodyChanging.getTarget().getLegType().getFootType().getPermittedFootStructures(BodyChanging.getTarget().getLegConfiguration()).contains(footStructure)) {
				if(BodyChanging.getTarget().getFootStructure() == footStructure) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(footStructure.getName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='FOOT_STRUCTURE_"+footStructure+"' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(footStructure.getName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("脚部结构",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.feet]的结构。"
						+ "<br/><i>脚部类型修改了角色描述和性场面的描述，而且可能会限制穿着脚部相关的衣物。</i>"),
				"FOOT_STRUCTURE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformFaceChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractFaceType face : FaceType.getAllFaceTypes()) {
			if(face.getRace() !=null && availableRaces.contains(face.getRace())) {
				if(BodyChanging.getTarget().getFaceType() == face) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+face.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(face.getTransformName())+(face.getTags().contains(BodyPartTag.THERMAL_VISION)?"*":"")+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='FACE_"+FaceType.getIdFromFaceType(face)+"' class='cosmetics-button'>"
								+ "<span style='color:"+face.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(face.getTransformName())+(face.getTags().contains(BodyPartTag.THERMAL_VISION)?"*":"")+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("面部",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的面部类型。"
						+ "<br/><i>面部类型决定了嘴巴和舌头类型。"
						+ "还会决定亚种识别，并提供红外视觉能力(标记为星号*)。</i>"),
				"FACE_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformBodyChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractTorsoType skin : TorsoType.getAllTorsoTypes()) {
			if(availableRaces.contains(skin.getRace())) {
				if(BodyChanging.getTarget().getTorsoType() == skin) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+skin.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(skin.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='TORSO_"+TorsoType.getIdFromTorsoType(skin)+"' class='cosmetics-button'>"
								+ "<span style='color:"+skin.getRace().getColour().getShades()[0]+";'>"+Util.capitaliseSentence(skin.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("身体",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的身体类型。"
						+ "<br/><i>身体类型影响[npc.namePos]体表覆盖的材质，包括皮肤、皮毛、鳞片和羽毛。</i>"),
				"BODY_TYPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformGenitalArrangementChoiceDiv() {
		contentSB.setLength(0);
		
		for(GenitalArrangement arrangement : GenitalArrangement.values()) {
//			if(arrangement!=GenitalArrangement.CLOACA_BEHIND) {
				if(BodyChanging.getTarget().getLegConfiguration().getAvailableGenitalConfigurations().contains(arrangement) || BodyChanging.getTarget().getGenitalArrangement()==arrangement) {
					if(BodyChanging.getTarget().getGenitalArrangement() == arrangement) {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>"+Util.capitaliseSentence(arrangement.getName())+"</span>"
								+ "</div>");
						
					} else if(!Main.getProperties().hasValue(PropertyValue.bipedalCloaca) && BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.BIPEDAL && arrangement!=GenitalArrangement.NORMAL) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
									+ "<span style='color:"+PresetColour.BASE_GREY.getShades()[0]+";'>"+Util.capitaliseSentence(arrangement.getName())+"</span>"
								+ "</div>");
						
					} else {
						contentSB.append(
								"<div id='GENITAL_ARRANGEMENT_"+arrangement+"' class='cosmetics-button'>"
									+ "<span style='color:"+PresetColour.GENERIC_SEX.getShades()[0]+";'>"+Util.capitaliseSentence(arrangement.getName())+"</span>"
								+ "</div>");
					}
				}
//			}
		}

		return applyWrapper("生殖器排列",
				UtilText.parse(BodyChanging.getTarget(),
						"修改[npc.namePos]生殖器的配置。"
								+ "<br/><i>这决定了[npc.namePos]生殖器和肛门的位置。对于绝大多数腿部配置，都限制于'"+GenitalArrangement.NORMAL.getName()+"'。</i>"
						+ (!Main.getProperties().hasValue(PropertyValue.bipedalCloaca)
							?"[style.italicsDisabled(双足泄殖腔在内容选项中被禁用。)]"
							:"")),
				"GENITAL_ARRANGEMENT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLegConfigurationChoiceDiv() {
		contentSB.setLength(0);
		
		for(LegConfiguration legConfig : LegConfiguration.values()) {
			if(BodyChanging.getTarget().getLegType().isLegConfigurationAvailable(legConfig)) {
				if(BodyChanging.getTarget().getLegConfiguration() == legConfig) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.RACE_BESTIAL.toWebHexString()+";'>"+Util.capitaliseSentence(legConfig.getName())+(!legConfig.isAbleToGrowTail()?"*":"")+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='LEG_CONFIGURATION_"+legConfig+"' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.RACE_BESTIAL.getShades()[0]+";'>"+Util.capitaliseSentence(legConfig.getName())+(!legConfig.isAbleToGrowTail()?"*":"")+"</span>"
							+ "</div>");
				}
			}
		}
		
		return applyWrapper("腿部配置",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]下半身的样子。"
						+ "<br/><i>应用该修改会导致腰部以下的所有身体部位被转化！部分腿部配置(标记为星号*)会禁止[npc.namePos]长尾巴！</i>"),
				"LEG_CONFIGURATION",
				contentSB.toString(),
				true);
	}
	
	
	public static String getSelfTransformEarChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractEarType ear : EarType.getAllEarTypes()) {
			if(availableRaces.contains(ear.getRace())) {
				if(BodyChanging.getTarget().getEarType().equals(ear)) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+ear.getRace().getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(ear.getTransformName())
									+(ear.isAbleToBeUsedAsHandlesInSex()?"*":"")
									+(ear.getTags().contains(BodyPartTag.ECHO_LOCATION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='EAR_"+EarType.getIdFromEarType(ear)+"' class='cosmetics-button'>"
								+ "<span style='color:"+ear.getRace().getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(ear.getTransformName())
									+(ear.isAbleToBeUsedAsHandlesInSex()?"*":"")
									+(ear.getTags().contains(BodyPartTag.ECHO_LOCATION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("耳朵",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的耳朵类型。"
						+ "<br/><i>耳朵类型有助于亚种识别。部分足够长的可以在性交过程中被拉扯(*), ，或者提供回声定位(⁺;)。</i>"),
				"EAR_TYPE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformEyeChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractEyeType eye : EyeType.getAllEyeTypes()) {
			if(availableRaces.contains(eye.getRace())) {
				if(BodyChanging.getTarget().getEyeType() == eye) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+eye.getRace().getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(eye.getTransformName())
									+(eye.getTags().contains(BodyPartTag.NIGHT_VISION)?"*":"")
									+(eye.getTags().contains(BodyPartTag.EYE_PERFECT_VISION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='EYE_"+EyeType.getIdFromEyeType(eye)+"' class='cosmetics-button'>"
								+ "<span style='color:"+eye.getRace().getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(eye.getTransformName())
									+(eye.getTags().contains(BodyPartTag.NIGHT_VISION)?"*":"")
									+(eye.getTags().contains(BodyPartTag.EYE_PERFECT_VISION)?"&#8314;":"")
								+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("眼部",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的眼睛类型。"
						+ "<br/><i>眼睛类型决定了角色生成时虹膜和瞳孔形状，并拥有亚种识别，"
						+ " 且能够提供夜视能力(*)，或完美视力(⁺;).</i>"),
				"EYE_TYPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformIrisChoiceDiv() {
		contentSB.setLength(0);
		
		for(EyeShape eyeShape : EyeShape.values()) {
			if(BodyChanging.getTarget().getIrisShape() == eyeShape) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='IRIS_SHAPE_"+eyeShape+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("虹膜形状",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]虹膜的形状。"
						+ "<br/><i>单纯只是外表转化。</i>"),
				"IRIS_SHAPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformPupilChoiceDiv() {
		contentSB.setLength(0);
		
		for(EyeShape eyeShape : EyeShape.values()) {
			if(BodyChanging.getTarget().getPupilShape() == eyeShape) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PUPIL_SHAPE_"+eyeShape+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(eyeShape.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("瞳孔形状",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]瞳孔的形状。"
						+ "<br/><i>单纯只是外表转化。</i>"),
				"PUPIL_SHAPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformEyeCountDiv() {
		contentSB.setLength(0);
		
		for(int i=1; i <= Eye.MAXIMUM_PAIRS; i++) {
			if(BodyChanging.getTarget().getEyePairs() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='EYE_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("眼睛对数",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]有几对[npc.eyes]。"
						+ "<br/><i>单纯只是外表转化。</i>"),
				"EYE_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLipSizeDiv() {
		contentSB.setLength(0);
		
		for(LipSize lipSize : LipSize.values()) {
			if(BodyChanging.getTarget().getLipSize() == lipSize) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(lipSize.getName())+(lipSize.isImpedesSpeech()&&Main.game.isLipLispEnabled()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LIP_SIZE_"+lipSize+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(lipSize.getName())+(lipSize.isImpedesSpeech()&&Main.game.isLipLispEnabled()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("嘴唇尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]嘴唇的大小。"
						+ (Main.game.isLipLispEnabled()
								?"<br/><i>尽管绝大多数情况只是外表转化，但嘴唇足够大(用星号*标记)将会使[npc.name]说话口齿不清。</i>"
								:"<br/><i>由于“唇音不清”选项被关闭，这单纯只是外表转化。</i>")),
				"LIP_SIZE",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformThroatModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div  id='THROAT_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("嘴唇与喉咙修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]嘴唇与喉咙的修饰词。"
						+ "<br/><i>这些修饰词会影响提供口交时的描述。</i>"),
				"THROAT_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getFaceWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='THROAT_WETNESS_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("喉部湿润度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]喉咙的湿润度。"
							+ "<br/><i>影响性交中的快感，未润滑的腔穴会削弱快感的获取。</i>"
							+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
									?"<br/>[style.italicsWetness8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴润滑度永远不会低于'"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'！)]"
									:"")),
				"THROAT_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getFaceCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("喉部容量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]喉咙的容量。"
						+ "<br/><i>该部分影响[npc.namePos]喉咙能够接受的插入物的尺寸。如果对于插入物来说容量太小或太大，就会影响快感获取。</i>"),
				"THROAT_CAPACITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformThroatDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getFaceDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='THROAT_DEPTH_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("喉部深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]喉咙的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"THROAT_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getFaceElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("喉部弹性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]喉咙的弹性。"
						+ "<br/><i>该部分决定了[npc.namePos]的喉咙在被过于粗壮的插入物撑开后，能够多快地适应。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"标记为星号(*)的数值会增加喉咙的最大不适深度。":""))
						+"</i>",
				"THROAT_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformThroatPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getFacePlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='THROAT_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("喉部可塑性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]喉咙的可塑性。"
						+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"THROAT_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTongueModifiersDiv() {
		contentSB.setLength(0);
		
		for(TongueModifier tongueMod : TongueModifier.values()) {
			if(BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
				contentSB.append(
						"<div  id='TONGUE_MOD_"+tongueMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(tongueMod.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TONGUE_MOD_"+tongueMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(tongueMod.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("舌头修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]舌头的修饰词。"
						+ "<br/><i>舌头修饰词会影响提供口交时的描述。</i>"),
				"TONGUE_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTongueSizeDiv() {
		contentSB.setLength(0);
		
		for(TongueLength tongueLength : TongueLength.values()) {
			if(BodyChanging.getTarget().getTongueLength() == tongueLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"+Util.capitaliseSentence(tongueLength.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TONGUE_LENGTH_"+tongueLength+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>"+Util.capitaliseSentence(tongueLength.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("舌头长度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]舌头的长度。"
						+ "<br/><i>影响提供口交时的描述。</i>"),
				"TONGUE_LENGTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAssSizeDiv() {
		contentSB.setLength(0);
		
		for(AssSize as : AssSize.values()) {
			if(BodyChanging.getTarget().getAssSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ASS_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("屁股尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]屁股的尺寸。"
						+ "<br/><i>单纯只是外表修改。</i>"),
				"ASS_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformHipSizeDiv() {
		contentSB.setLength(0);
		
		for(HipSize hs : HipSize.values()) {
			if(BodyChanging.getTarget().getHipSize() == hs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+hs.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HIP_SIZE_"+hs+"' class='cosmetics-button'>"
							+ "<span style='color:"+hs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("臀部尺寸",
				UtilText.parse(BodyChanging.getTarget(),
						"修改[npc.namePos]臀部的大小。"
						+ (BodyChanging.getTarget().getLegConfiguration()==LegConfiguration.TAIL_LONG
								?"<br/>会影响[npc.her]蛇尾的周长。"
								:"<br/>若[npc.she]获得了蛇尾样的下半身，这也会影响其周长。")),
				"HIP_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getAssWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ANUS_WETNESS_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("肛门湿润度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的湿润度。"
						+ "<br/><i>影响性交中的快感，未润滑的腔穴会削弱快感的获取。</i>"
						+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
							?"<br/>[style.italicsWetness8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴润滑度永远不会低于'"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'！)]"
							:"")),
				"ASSHOLE_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getAssCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("肛门容量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的容量。"
						+ "<br/><i>该部分影响[npc.namePos]肛门能够接受的插入物的尺寸。如果对于插入物来说容量太小或太大，就会影响快感获取。</i>"),
				"ASSHOLE_CAPACITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformAnusDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getAssDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='ANUS_DEPTH_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("肛门深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"ANUS_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getAssElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
			}
		}

		return applyWrapper("肛门弹性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的弹性。"
						+ "<br/><i>该部分决定了[npc.namePos]的肛门在被过于粗壮的插入物撑开后，能够多快地适应。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" 标记为星号(*)的数值会增加肛门的最大不适深度。":""))
						+"</i>",
				"ASSHOLE_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getAssPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("肛门可塑性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的可塑性。"
						+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"ASSHOLE_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAnusModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div  id='ANUS_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ANUS_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("肛门修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]肛门的修饰词。"
						+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"ANUS_MOD",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformBreastSizeDiv() {
		return applyFullVariableWrapper("乳房尺寸",
				BodyChanging.getTarget().isPlayer()
					?"修改你乳房的尺寸。"
					:UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳房的尺寸。"),
				"BREAST_SIZE",
				"1",
				"5",
				BodyChanging.getTarget().getBreastRawSizeValue()
					+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastSize().getCupSizeName())+(BodyChanging.getTarget().getBreastSize()==CupSize.FLAT?"":"罩杯")+"</i>",
				BodyChanging.getTarget().getBreastRawSizeValue()<=0
					|| (BodyChanging.getTarget().getBreastRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE)!=null),
				BodyChanging.getTarget().getBreastRawSizeValue()>=CupSize.XXX_N.getMeasurement());
	}
	
	// This is to stop variable-length names from being generated every time a button is clicked (i.e. stop "crotch-tits", "crotch-breasts")
	private static String getCrotchBoobName(boolean plural) {
		if(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS) {
			return plural?"腹乳":"腹乳";
		} else {
			return plural?"胯乳":"胯乳";
		}
	}
	
	public static String getSelfTransformBreastCrotchSizeDiv() {
		return applyFullVariableWrapper(
				UtilText.parse(BodyChanging.getTarget(), Util.capitaliseSentence(getCrotchBoobName(false))+"尺寸"),
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]"+getCrotchBoobName(true)+"的尺寸。"),
				"BREAST_CROTCH_SIZE",
				"罩杯",
				"罩杯",
				Util.capitaliseSentence(BodyChanging.getTarget().getBreastCrotchSize().getCupSizeName())
					+"<br/>("+BodyChanging.getTarget().getBreastCrotchRawSizeValue()+")",
				BodyChanging.getTarget().getBreastCrotchRawSizeValue()<=0
						|| (BodyChanging.getTarget().getBreastCrotchRawSizeValue()<=CupSize.getMinimumCupSizeForEggIncubation().getMeasurement() && BodyChanging.getTarget().getIncubationLitter(SexAreaOrifice.NIPPLE_CROTCH)!=null),
				BodyChanging.getTarget().getBreastCrotchRawSizeValue()>=CupSize.XXX_N.getMeasurement());
	}
	
	public static String getSelfTransformBreastShapeDiv() {
		contentSB.setLength(0);
		
		for(BreastShape bs : BreastShape.values()) {
			if(!bs.isRestrictedToCrotchBoobs()) {
				if(BodyChanging.getTarget().getBreastShape() == bs) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_SHAPE_"+bs+"' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("乳房形状",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳房的形状。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"BREAST_SHAPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformBreastCrotchShapeDiv() {
		contentSB.setLength(0);
		
		for(BreastShape bs : BreastShape.values()) {
			if(BodyChanging.getTarget().getBreastCrotchShape() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_CROTCH_SHAPE_"+bs+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper(
				UtilText.parse(BodyChanging.getTarget(), Util.capitaliseSentence(getCrotchBoobName(false))+"形状"),
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]"+getCrotchBoobName(true)+"的形状。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"BREAST_CROTCH_SHAPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformBreastRowsDiv() {
		contentSB.setLength(0);
		
		if (Main.getProperties().multiBreasts == 0) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("一")
							+"</div>");
		} else {
			for (int i = 1; i<=Breast.MAXIMUM_BREAST_ROWS; i++) {
				if (BodyChanging.getTarget().getBreastRows() == i) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_COUNT_"+i+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳房对数",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]有几对乳房。"
						+ "<br/><i>通常只是外表修改，同时也会决定是否拥有空闲的乳头用于性动作。</i>"
						+(Main.getProperties().multiBreasts == 0
							?"<br/>[style.italicsBad(多对乳房在内容选项中被禁用！)]"
							:"")),
				"BREAST_ROWS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformBreastCrotchRowsDiv() {
		contentSB.setLength(0);
		
		int minimum = 1;
		if(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS) {
			minimum = 0;
		}
		
		for(int i=minimum; i <= BreastCrotch.MAXIMUM_BREAST_ROWS; i++) {
			String name = Util.capitaliseSentence(Util.intToString(i));
			if(i==0) {
				name = "(单个)";
			}
			if(BodyChanging.getTarget().getBreastCrotchRows()==i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+name+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_CROTCH_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+name+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper(
				UtilText.parse(BodyChanging.getTarget(), Util.capitaliseSentence(getCrotchBoobName(false))+"对"),
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]有几对"+getCrotchBoobName(false)+"。"
						+ "<br/><i>通常只是外表修改，同时也会决定是否拥有空闲的胯乳乳头用于性动作。</i>"),
				"BREAST_CROTCH_ROWS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCountDiv() {
		contentSB.setLength(0);

		int minimum = 1;
		
		for(int i=minimum; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			if(BodyChanging.getTarget().getNippleCountPerBreast() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("乳头数量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]每个乳房上的乳头数量。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"NIPPLE_COUNT",
				contentSB.toString(),true);
	}

	public static String getSelfTransformNippleCrotchCountDiv() {
		contentSB.setLength(0);

		int minimum = 1;
		if(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS && BodyChanging.getTarget().getBreastCrotchRows()==0) {
			minimum = 2;
		}
		
		for(int i=minimum; i <= BreastCrotch.MAXIMUM_NIPPLES_PER_BREAST; i++) {
			if(BodyChanging.getTarget().getNippleCrotchCountPerBreast() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_CROTCH_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("乳头数量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]每个"+getCrotchBoobName(false)+"上的乳头数量。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"NIPPLE_CROTCH_COUNT",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleSizeDiv() {
		contentSB.setLength(0);
		
		for(NippleSize ns : NippleSize.values()) {
			if(BodyChanging.getTarget().getNippleSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_SIZE_"+ns+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("乳头尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳头的尺寸。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"NIPPLE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchSizeDiv() {
		contentSB.setLength(0);
		
		for(NippleSize ns : NippleSize.values()) {
			if(BodyChanging.getTarget().getNippleCrotchSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_CROTCH_SIZE_"+ns+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("乳头尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.crotchNipples]的尺寸。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"NIPPLE_CROTCH_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAreolaeSizeDiv() {
		contentSB.setLength(0);
		
		for(AreolaeSize as : AreolaeSize.values()) {
			if(BodyChanging.getTarget().getAreolaeSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("乳晕尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳晕的尺寸。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"AREOLAE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformAreolaeCrotchSizeDiv() {
		contentSB.setLength(0);
		
		for(AreolaeSize as : AreolaeSize.values()) {
			if(BodyChanging.getTarget().getAreolaeCrotchSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_CROTCH_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("乳晕尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.crotchNipples]乳晕的尺寸。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述。</i>"),
				"CROTCH_AREOLAE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleShapeDiv() {
		contentSB.setLength(0);
		
		for(NippleShape ns : NippleShape.values()) {
			if(BodyChanging.isDebugMenu() || !ns.isAssociatedWithPenetrationContent() || Main.game.isNipplePenEnabled()) {
				if(BodyChanging.getTarget().getNippleShape() == ns) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");

				} else {
					contentSB.append(
							"<div id='NIPPLE_SHAPE_"+ns+"' class='cosmetics-button'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");
				}
			}
		}

		return applyWrapper("乳头形状",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳头的形状。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述和可用动作。</i>"),
				"NIPPLE_SHAPE",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCrotchShapeDiv() {
		contentSB.setLength(0);
		
		for(NippleShape ns : NippleShape.values()) {
			if(BodyChanging.isDebugMenu() || !ns.isAssociatedWithPenetrationContent() || Main.game.isNipplePenEnabled()) {
				if(BodyChanging.getTarget().getNippleCrotchShape() == ns) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");

				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_SHAPE_"+ns+"' class='cosmetics-button'>"
									+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
									+ "</div>");
				}
			}
		}

		return applyWrapper("胯乳乳头形状",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.crotchNipples]的形状。"
						+ "<br/><i>大多数情况下只是外表修改，但也会影响性交中的部分描述和可用动作。</i>"),
				"NIPPLE_CROTCH_SHAPE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getNippleCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CAPACITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头容量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳头的容量。"
						+ "<br/><i>任意非0的数值都会使得[npc.namePos]的乳头在性交中可以被插入(取决于内容选项中“乳头插入”偏好的设置。)</i>"),
				"NIPPLE_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getNippleCrotchCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_CAPACITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头容量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.crotchNipples]的容量。"
						+ "<br/><i>任意非0的数值都会使得[npc.namePos]的乳头在性交中可以被插入(取决于内容选项中“乳头插入”偏好的设置。)</i>"),
				"NIPPLE_CROTCH_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static int getLactationUpperLimit() {
		if (Main.game.isInNewWorld()) {
			return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
		} else {
			return 150;
		}
	}
	
	public static String getSelfTransformLactationDiv() {
		String title = "泌乳";
		String description = UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]乳房中可以容纳的最大乳汁量。"
				+"<br/><i>耗尽后，[npc.namePos]的乳房需要以一定速度恢复乳汁，基于其乳汁再生速度。</i>");
		String id = "MILK_PRODUCTION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastRawMilkStorageValue(), ValueType.PRECISE)
							+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastMilkStorage().getName())+"</i>",
					BodyChanging.getTarget().getBreastRawMilkStorageValue()<=0,
					BodyChanging.getTarget().getBreastRawMilkStorageValue()>=getLactationUpperLimit(),
					FLUID_INCREMENT_SMALL,
					FLUID_INCREMENT_AVERAGE,
					FLUID_INCREMENT_LARGE,
					FLUID_INCREMENT_HUGE);
		}
	}
	
	public static int getLactationCrotchUpperLimit() {
		return Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue();
	}
	
	public static String getSelfTransformLactationCrotchDiv() {
		String title = "泌乳";
		String description = UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name][npc.crotchBoobs]中可以容纳的最大乳汁量。"
				+"<br/><i>耗尽后，[npc.namePos]的[npc.crotchBoobs]需要以一定速度恢复乳汁，基于其乳汁再生速度。</i>");
		String id = "MILK_CROTCH_PRODUCTION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue(), ValueType.PRECISE)
							+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastCrotchMilkStorage().getName())+"</i>",
					BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()<=0,
					BodyChanging.getTarget().getBreastCrotchRawMilkStorageValue()>=getLactationCrotchUpperLimit(),
					FLUID_INCREMENT_SMALL,
					FLUID_INCREMENT_AVERAGE,
					FLUID_INCREMENT_LARGE,
					FLUID_INCREMENT_HUGE);
		}
	}
	
	public static String getSelfTransformLactationRegenerationDiv() {
		String title = "乳汁再生速度(每乳房)";
		String description = UtilText.parse(BodyChanging.getTarget(), "调整[npc.namePos]<b>每个</b>乳房产生乳汁的速度。[npc.sheHasFull]拥有越多乳房，每秒产生的乳汁就越多。"
				+"<br/><i>耗尽后，[npc.namePos]的乳房需要以该速度恢复乳汁至最大储量。</i>");
		String id = "MILK_REGENERATION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastRawLactationRegenerationValue(), ValueType.PRECISE)+"/天"
							+"<br/>("+Units.fluid(BodyChanging.getTarget().getLactationRegenerationPerSecond(false)*60, ValueType.PRECISE)+"/分钟)"
							+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastLactationRegeneration().getName())+"</i>",
					BodyChanging.getTarget().getBreastRawLactationRegenerationValue()<=0,
					BodyChanging.getTarget().getBreastRawLactationRegenerationValue()>=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(),
					FLUID_REGEN_INCREMENT_SMALL,
					FLUID_REGEN_INCREMENT_AVERAGE,
					FLUID_REGEN_INCREMENT_LARGE,
					FLUID_REGEN_INCREMENT_HUGE);
		}
	}

	public static String getSelfTransformLactationCrotchRegenerationDiv() {
		String title = "乳汁再生速度(每胯乳)";
		String description = UtilText.parse(BodyChanging.getTarget(), "调整[npc.namePos]<b>每个</b>[npc.crotchBoobs]产生乳汁的速度。[npc.sheHasFull]拥有越多[npc.crotchBoobs]，每秒产生的乳汁就越多。"
				+"<br/><i>耗尽后，[npc.namePos]的[npc.crotchBoobs]需要以该速度恢复乳汁至最大储量。</i>");
		String id = "MILK_CROTCH_REGENERATION";
		if (!Main.game.isLactationContentEnabled()) {
			return applyWrapper(title, description, id,
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>", true);
		} else {
			return applyVariableWrapperFluids(title,
					description,
					id,
					Units.fluid(BodyChanging.getTarget().getBreastCrotchRawLactationRegenerationValue(), ValueType.PRECISE)+"/天"
							+"<br/>("+Units.fluid(BodyChanging.getTarget().getCrotchLactationRegenerationPerSecond(false)*60, ValueType.PRECISE)+"/分钟)"
							+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getBreastCrotchLactationRegeneration().getName())+"</i>",
					BodyChanging.getTarget().getBreastCrotchRawLactationRegenerationValue()<=0,
					BodyChanging.getTarget().getBreastCrotchRawLactationRegenerationValue()>=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(),
					FLUID_REGEN_INCREMENT_SMALL,
					FLUID_REGEN_INCREMENT_AVERAGE,
					FLUID_REGEN_INCREMENT_LARGE,
					FLUID_REGEN_INCREMENT_HUGE);
		}
	}

	public static String getSelfTransformLactationFlavourDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>");
		} else {
			for (FluidFlavour flavour : FluidFlavour.values()) {
				if (BodyChanging.getTarget().getMilkFlavour().equals(flavour)) {
					contentSB.append(
							"<div id='MILK_FLAVOUR_"+flavour+"' class='cosmetics-button active'>"
									+"<span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_FLAVOUR_"+flavour+"' class='cosmetics-button'>"
									+"<span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳汁风味",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳汁的风味。"
							+ "<br/><i>会影响性交内外的描述。</i>"),
				"MILK_FLAVOUR",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformLactationModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>");
		} else {
			for (FluidModifier modifier : FluidModifier.values()) {
				if (BodyChanging.getTarget().hasMilkModifier(modifier)) {
					contentSB.append(
							"<div id='MILK_MODIFIER_"+modifier+"' class='cosmetics-button active'>"
									+"<span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_MODIFIER_"+modifier+"' class='cosmetics-button'>"
									+"<span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳汁修饰词",
				UtilText.parse(BodyChanging.getTarget(), "为[npc.namePos]的乳汁添加或移除修饰词。"
							+ "<br/><i>会影响性交内外的描述。在游戏内有特殊效果的修饰词将会被标记为星号*，如摄入后会获得状态效果等。</i>"),
				"MILK_MODIFIER",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformLactationCrotchFlavourDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>");
		} else {
			for (FluidFlavour flavour : FluidFlavour.values()) {
				if (BodyChanging.getTarget().getMilkCrotchFlavour().equals(flavour)) {
					contentSB.append(
							"<div id='MILK_CROTCH_FLAVOUR_"+flavour+"' class='cosmetics-button active'>"
									+"<span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_CROTCH_FLAVOUR_"+flavour+"' class='cosmetics-button'>"
									+"<span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳汁风味",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.crotchMilk]的风味。"
							+ "<br/><i>会影响性交内外的描述。</i>"),
				"MILK_CROTCH_FLAVOUR",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLactationCrotchModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isLactationContentEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用泌乳")
							+"</div>");
		} else {
			for (FluidModifier modifier : FluidModifier.values()) {
				if (BodyChanging.getTarget().hasMilkCrotchModifier(modifier)) {
					contentSB.append(
							"<div id='MILK_CROTCH_MODIFIER_"+modifier+"' class='cosmetics-button active'>"
									+"<span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='MILK_CROTCH_MODIFIER_"+modifier+"' class='cosmetics-button'>"
									+"<span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳汁修饰词",
				UtilText.parse(BodyChanging.getTarget(), "为[npc.namePos]的[npc.crotchMilk]添加或移除修饰词。"
							+ "<br/><i>会影响性交内外的描述。在游戏内有特殊效果的修饰词将会被标记为星号*，如摄入后会获得状态效果等。</i>"),
				"MILK_CROTCH_MODIFIER",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getNippleDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='NIPPLE_DEPTH_"+value+"' class='cosmetics-button'>"
										+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("乳头深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.nipples]的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"NIPPLE_DEPTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCrotchDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getNippleCrotchDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='NIPPLE_CROTCH_DEPTH_"+value+"' class='cosmetics-button'>"
										+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("乳头深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.crotchNipples]的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"NIPPLE_CROTCH_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getNippleElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_ELASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头弹性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳头的弹性。"
						+ "<br/><i>该部分决定了[npc.namePos]的乳头被过分扩张后的适应速度。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" 标有星号的数值会增加[npc.her]乳头的最大不适深度。":""))
						+"</i>",
				"NIPPLE_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getNippleCrotchElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_ELASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头弹性",
				UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos][npc.crotchNipples]的弹性等级。"
						+ "<br/><i>该部分决定了[npc.namePos]的[npc.crotchNipples]被过分扩张后的适应速度。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" 标记为星号(*)的数值会增加[npc.crotchNipples]的最大不适深度。":""))
						+"</i>",
				"NIPPLE_CROTCH_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNipplePlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getNipplePlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_PLASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头可塑性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的乳头可塑性"
						+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"NIPPLE_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformNippleCrotchPlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getNippleCrotchPlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_PLASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头可塑性",
				UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos][npc.crotchNipples]的可塑性。"
							+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"NIPPLE_CROTCH_PLASTICITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='NIPPLE_MOD_"+orificeMod+"' class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_MOD_"+orificeMod+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]乳头的修饰词"
							+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"NIPPLE_MODS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformNippleCrotchModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isNipplePenEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用乳头插入")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasNippleCrotchOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_MOD_"+orificeMod+"' class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='NIPPLE_CROTCH_MOD_"+orificeMod+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("乳头修饰词",
				UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos][npc.crotchNipples]的修饰词。"
							+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"NIPPLE_CROTCH_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaChoiceDiv(List<AbstractRace> availableRaces) {
		contentSB.setLength(0);
		
		for(AbstractVaginaType vagina : VaginaType.getAllVaginaTypes()) {
			if(((vagina.getRace() !=null && availableRaces.contains(vagina.getRace()))
					|| vagina==VaginaType.NONE)
					&& vagina!=VaginaType.ONAHOLE) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(vagina.getRace() != null) {
					c = vagina.getRace().getColour();
				}
				
				
				if(BodyChanging.getTarget().getVaginaType() == vagina) {
					contentSB.append(
							"<div id='VAGINA_"+VaginaType.getIdFromVaginaType(vagina)+"' class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"
									+Util.capitaliseSentence(vagina.getTransformName())+(vagina.isEggLayer()?"*":"")
								+"</span>"
							+ "</div>");
					
				} else {
					boolean cannotChoose = vagina==VaginaType.NONE
							&& (BodyChanging.getTarget().isPregnant() || BodyChanging.getTarget().hasStatusEffect(StatusEffect.PREGNANT_0) || BodyChanging.getTarget().hasIncubationLitter(SexAreaOrifice.VAGINA));
					contentSB.append(
							"<div id='VAGINA_"+VaginaType.getIdFromVaginaType(vagina)+"' class='cosmetics-button"+(cannotChoose?" disabled":"")+"'>"
								+ "<span style='color:"+(cannotChoose?PresetColour.TEXT_GREY.toWebHexString():c.getShades()[0])+";'>"
									+Util.capitaliseSentence(vagina.getTransformName())+(vagina.isEggLayer()?"*":"")
								+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("阴道",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的阴道类型。标记为星号*的类型默认能够产卵。"
//					+ "<br/><i>Vagina type affects default vagina attributes and descriptions.</i>"
					+ "<br/>[style.italicsMinorBad(有可能怀孕或正在孵卵时无法移除阴道！)]"),
				"VAGINA_TYPE",
				contentSB.toString(),
				false);
	}

	public static String getSelfTransformGirlcumFlavourDiv() {
		contentSB.setLength(0);
		
		for(FluidFlavour flavour : FluidFlavour.values()) {
			if(BodyChanging.getTarget().getGirlcumFlavour().equals(flavour)) {
				contentSB.append(
						"<div id='GIRLCUM_FLAVOUR_"+flavour+"' class='cosmetics-button active'>"
							+ "<span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='GIRLCUM_FLAVOUR_"+flavour+"' class='cosmetics-button'>"
							+ "<span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("爱液风味",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]爱液的风味。"
							+ "<br/><i>会影响性交内外的描述。</i>"),
				"GIRLCUM_FLAVOUR",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformGirlcumModifiersDiv() {
		contentSB.setLength(0);
		
		for(FluidModifier modifier : FluidModifier.values()) {
			if(BodyChanging.getTarget().hasGirlcumModifier(modifier)) {
				contentSB.append(
						"<div id='GIRLCUM_MODIFIER_"+modifier+"' class='cosmetics-button active'>"
							+ "<span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='GIRLCUM_MODIFIER_"+modifier+"' class='cosmetics-button'>"
							+ "<span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("爱液修饰词",
				UtilText.parse(BodyChanging.getTarget(), "为[npc.namePos]的爱液添加或移除修饰词"
							+ "<br/><i>会影响性交内外的描述。在游戏内有特殊效果的修饰词将会被标记为星号*，如摄入后会获得状态效果等。</i>"),
				"GIRLCUM_MODIFIER",
				contentSB.toString(),
				false);
	}
	
	public static String getSelfTransformVaginaCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getVaginaCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴道直径",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴道的直径。"
							+ "<br/><i>该部分影响[npc.namePos]阴道能够接受的插入物的尺寸。如果对于插入物来说直径太小或太大，就会影响快感获取。</i>"),
				"VAGINA_CAPACITY",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaSquirterDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isVaginaSquirter()) {
			contentSB.append(
					"<div id='VAGINA_SQUIRTER_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_WETNESS_ONE.getShades()[0]+";'>无潮吹</span>"
					+ "</div>"
					+"<div class='cosmetics-button active'>"
						+ "<span style='color:"+PresetColour.GENERIC_WETNESS_FIVE.toWebHexString()+";'>有潮吹</span>"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_WETNESS_ONE.toWebHexString()+";'>无潮吹</span>"
					+ "</div>"
					+"<div id='VAGINA_SQUIRTER_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_WETNESS_FIVE.getShades()[0]+";'>有潮吹</span>"
					+ "</div>");
		}
		

		return applyWrapper("潮吹",
				UtilText.parse(BodyChanging.getTarget(), "设置[npc.namePos]的阴道是否会在高潮时潮吹。"
						+ "<br/><i>若[npc.nameIsFull]可以潮吹，那么当高潮时，将会脏污潮吹溅射到的衣物。"
							+ "同样，如果有人正在舔弄阴道，也会喝下液体。</i>"),
				"VAGINA_SQUIRTER",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaHymenDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().hasHymen()) {
			contentSB.append(
					"<div id='VAGINA_HYMEN_OFF' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.getShades()[0]+";'>失去</span>"
					+ "</div>"
					+"<div class='cosmetics-button active'>"
						+ "<span style='color:"+PresetColour.GENERIC_MINOR_GOOD.toWebHexString()+";'>完好</span>"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>失去</span>"
					+ "</div>"
					+"<div id='VAGINA_HYMEN_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_MINOR_GOOD.getShades()[0]+";'>完好</span>"
					+ "</div>");
		}
		

		return applyWrapper("处女膜",
				UtilText.parse(BodyChanging.getTarget(), "设定[npc.namePos]的处女膜完好与否"),
				"VAGINA_HYMEN",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaEggLayerDiv() {
		contentSB.setLength(0);
		
		boolean disabled = BodyChanging.getTarget().isPregnant();
		
		if(BodyChanging.getTarget().isVaginaEggLayer()) {
			contentSB.append(
					"<div id='VAGINA_EGG_LAYER_OFF' class='cosmetics-button"+(disabled?" disabled":"")+"'>"
						+ "<span style='color:"+PresetColour.GENERIC_SEX.getShades()[0]+";'>胎生</span>"
					+ "</div>"
					+"<div class='cosmetics-button"+(disabled?" disabled":" active")+"'>"
						+ "<span style='color:"+PresetColour.EGG.toWebHexString()+";'>卵生</span>"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button"+(disabled?" disabled":" active")+"'>"
							+ "<span style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>胎生</span>"
					+ "</div>"
					+"<div id='VAGINA_EGG_LAYER_ON' class='cosmetics-button"+(disabled?" disabled":"")+"'>"
						+ "<span style='color:"+PresetColour.EGG.getShades()[0]+";'>卵生</span>"
					+ "</div>");
		}
		
		return applyWrapper("生育类型",
				UtilText.parse(BodyChanging.getTarget(), "设定[npc.namePos]是胎生还是卵生，[style.italicsMinorBad(怀孕时不可修改)]"
						+ "<br/><i>每次阴道类型修改时，改值会重置为阴道类型的默认生育类型。</i>"),
				"VAGINA_EGG_LAYER",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getVaginaWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_WETNESS_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("阴道湿润度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴道的湿润度"
							+ "<br/><i>影响性交中的快感，未润滑的腔穴会削弱快感的获取。</i>"
							+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
									?"<br/>[style.italicsWetness8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴润滑度永远不会低于'"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'！)]"
									:"")),
				"VAGINA_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getVaginaDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_DEPTH_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("阴道深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴道的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"VAGINA_DEPTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformVaginaElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getVaginaElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴道弹性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴道的弹性。"
						+ "<br/><i>该部分决定了[npc.namePos]的阴道被过分扩张后的适应速度。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" 标记为星号(*)的数值会增加阴道的最大不适深度。":""))
						+"</i>",
				"VAGINA_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getVaginaPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴道可塑性等级",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴道的可塑性。"
							+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"VAGINA_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformClitorisSizeDiv() {
		return applyFullVariableWrapper("阴蒂长度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴蒂的长度"
						+ "所有大于'<i style='color:"+ClitorisSize.ONE_BIG.getColour().toWebHexString()+";'>"
							+ Util.capitaliseSentence(ClitorisSize.ONE_BIG.getDescriptor())+"</i>'的尺寸都支持在性交中进行插入行为。"
									+ "(当尺寸大到足够作为假阴茎使用时，会用星号(*)标记)"),
				"CLITORIS_SIZE",
				Units.size(1),
				Units.size(5),
				Units.size(BodyChanging.getTarget().getVaginaRawClitorisSizeValue(), Units.ValueType.PRECISE, Units.UnitType.SHORT)
					+"<br/><i style='color:"+BodyChanging.getTarget().getVaginaClitorisSize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getVaginaClitorisSize().getDescriptor())
						+ (BodyChanging.getTarget().getVaginaClitorisSize().isPseudoPenisSize()?"*":"")
					+"</i>"
					+ "<br/><i>直径:"+Units.size(BodyChanging.getTarget().getClitorisDiameter())+"</i>",
				BodyChanging.getTarget().getVaginaRawClitorisSizeValue()<=0,
				BodyChanging.getTarget().getVaginaRawClitorisSizeValue()>=ClitorisSize.SEVEN_STALLION.getMaximumValue());
	}

	public static String getSelfTransformClitorisGirthDiv() {
		contentSB.setLength(0);
		
		for(PenetrationGirth girth : PenetrationGirth.values()) {
			if(BodyChanging.getTarget().getClitorisGirth() == girth) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_GIRTH_"+girth+"' class='cosmetics-button'>"
							+ "<span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴蒂周长",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴蒂的周长。"
						+ "<br/><i>阴蒂周长影响插入时是否对于腔穴过大。</i>"),
				"CLITORIS_GIRTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformLabiaSizeDiv() {
		contentSB.setLength(0);
		
		for(LabiaSize size : LabiaSize.values()) {
			if(BodyChanging.getTarget().getVaginaLabiaSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LABIA_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴唇尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴唇的尺寸。"
						+ "<br/><i>单纯只是外表修改，影响性交内外的描述。</i>"),
				"LABIA_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='VAGINA_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴道修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴道的修饰词。"
							+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"VAGINA_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getVaginaUrethraCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_CAPACITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道直径",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的直径。"
							+ "<br/><i>任意非0的数值都会使得[npc.namePos]的尿道在性交中可以被插入。(取决于内容选项中“尿道插入”偏好的设置)</i>"),
				"VAGINA_URETHRA_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getVaginaUrethraDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='VAGINA_URETHRA_DEPTH_"+value+"' class='cosmetics-button'>"
										+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("尿道深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"VAGINA_URETHRA_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getVaginaUrethraElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_ELASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道弹性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的弹性。"
						+ "<br/><i>该部分决定了[npc.namePos]的尿道被过分扩张后的适应速度。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"标记为星号(*)的数值会增加尿道的最大不适深度。":""))
						+"</i>",
				"VAGINA_URETHRA_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraPlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getVaginaUrethraPlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_PLASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道可塑性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的可塑性。"
							+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"VAGINA_URETHRA_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformVaginaUrethraModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasVaginaUrethraOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='VAGINA_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='VAGINA_URETHRA_MOD_"+orificeMod+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的修饰词。"
							+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"VAGINA_URETHRA_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformPenisChoiceDiv(List<AbstractRace> availableRaces, boolean halfWidth) {
		contentSB.setLength(0);
		
		for(AbstractPenisType penis : PenisType.getAllPenisTypes()) {
			if(((penis.getRace() !=null && availableRaces.contains(penis.getRace()))
					|| penis==PenisType.NONE)
					&& penis!=PenisType.DILDO) {
				
				Colour c = PresetColour.TEXT_GREY;
				
				if(penis.getRace() != null) {
					c = penis.getRace().getColour();
				}
				
				if(BodyChanging.getTarget().getPenisType() == penis) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+c.toWebHexString()+";'>"+Util.capitaliseSentence(penis.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='PENIS_"+PenisType.getIdFromPenisType(penis)+"' class='cosmetics-button'>"
								+ "<span style='color:"+c.getShades()[0]+";'>"+Util.capitaliseSentence(penis.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("阴茎",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的阴茎类型。"
						+ "<br/><i>阴茎类型影响着角色生成时的修饰词和其他属性，包括性交内外的描述。</i>"),
				"PENIS_TYPE",
				contentSB.toString(),
				halfWidth);
	}
	
	public static String getSelfTransformPenisSizeDiv() {
		return applyFullVariableWrapper("阴茎长度",
				(BodyChanging.getTarget().isPlayer()
						?"修改你阴茎的长度。"
						:UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴茎的长度。")),
				"PENIS_SIZE",
				Units.size(1),
				Units.size(5),
				Units.size(BodyChanging.getTarget().getPenisRawSizeValue(), Units.ValueType.PRECISE, Units.UnitType.SHORT)
					+"<br/><i style='color:"+BodyChanging.getTarget().getPenisSize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getPenisSize().getDescriptor())+"</i>"
					+ "<br/><i>直径:"+Units.size(BodyChanging.getTarget().getPenisDiameter())+"</i>",
				BodyChanging.getTarget().getPenisRawSizeValue()<=0,
				BodyChanging.getTarget().getPenisRawSizeValue()>=PenisLength.SEVEN_STALLION.getMaximumValue());
	}
	
	public static String getSelfTransformPenisGirthDiv() {
		contentSB.setLength(0);
		
		for(PenetrationGirth girth : PenetrationGirth.values()) {
			if(BodyChanging.getTarget().getPenisGirth() == girth) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+girth.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_GIRTH_"+girth+"' class='cosmetics-button'>"
							+ "<span style='color:"+girth.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(girth.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴茎周长",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴茎的周长。"
						+ "<br/><i>阴茎周长影响插入时是否对于腔穴过大。</i>"),
				"PENIS_GIRTH",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTesticleSizeDiv() {
		contentSB.setLength(0);
		
		for(TesticleSize size : TesticleSize.values()) {
			if(BodyChanging.getTarget().getTesticleSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴囊尺寸",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴囊的尺寸。"
						+ "<br/><i>阴囊尺寸只是外表转化，影响性交内外的描述。</i>"),
				"TESTICLE_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformTesticleCountDiv() {
		contentSB.setLength(0);
		
		for(int i=Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i+=2) {
			if(BodyChanging.getTarget().getTesticleCount() == i) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_COUNT_"+i+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(Util.intToString(i))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴囊数量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]阴囊的数量。"
							+ "<br/><i>阴囊数量只是外表转化，影响性交内外的描述。</i>"),
				"TESTICLE_COUNT",
				contentSB.toString(),
				true);
	}
	

	public static String getSelfTransformInternalTesticleDiv() {
		contentSB.setLength(0);
		
		if(BodyChanging.getTarget().isInternalTesticles()) {
			contentSB.append(
					"<div id='TESTICLES_INTERNAL_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>外露</span>"
					+ "</div>"
					+"<div class='cosmetics-button active'>"
						+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>体内</span>"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>外露</span>"
					+ "</div>"
					+"<div id='TESTICLES_INTERNAL_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>体内</span>"
					+ "</div>");
		}
		

		return applyWrapper("体内阴囊",
				UtilText.parse(BodyChanging.getTarget(), "设置[npc.namePos]的阴囊是否位于体内。"
						+ "<br/><i>影响性交中的部分描述和可用动作。(对于扶她角色，取决于内容选项中的“扶她阴囊”)</i>"),
				"INTERNAL_TESTICLES",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraCapacityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (Capacity value : Capacity.getCapacityListFromPreferences()) {
				if (BodyChanging.getTarget().getPenisCapacity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_CAPACITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道容量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的容量。"
							+ "<br/><i>意非0的数值都会使得[npc.namePos]的尿道在性交中可以被插入。(取决于内容选项中“尿道插入”偏好的设置)</i>"),
				"PENIS_URETHRA_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraDepthDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificeDepth value : OrificeDepth.values()) {
				if (BodyChanging.getTarget().getUrethraDepth() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					if (!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
						contentSB.append(
								"<div class='cosmetics-button disabled'>"
										+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
										+Util.capitaliseSentence(value.getDescriptor())
										+"</span>"
										+"</div>");
						
					} else {
						contentSB.append(
								"<div id='URETHRA_DEPTH_"+value+"' class='cosmetics-button'>"
										+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
										+"</div>");
					}
				}
			}
		}

		return applyWrapper("尿道深度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"URETHRA_DEPTH",
				contentSB.toString(),
				true);
	}
	
	public static int getCumUpperLimit() {
		if(Main.game.isInNewWorld()) {
			return CumProduction.SEVEN_MONSTROUS.getMaximumValue();
		} else {
			return 30;
		}
	}
	
	public static String getSelfTransformCumProductionDiv() {
		return applyVariableWrapperFluids("精液储量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的最大精液储量。"
						+ "<br/><i>耗尽后，[npc.namePos]的睾丸需要以一定速度恢复精液，基于其精液再生速度。</i>"),
				"CUM_PRODUCTION",
				Util.capitaliseSentence(BodyChanging.getTarget().getPenisCumStorage().getName())
					+"<br/>("+Units.fluid(BodyChanging.getTarget().getPenisRawCumStorageValue(), ValueType.PRECISE)+")",
				BodyChanging.getTarget().getPenisRawCumStorageValue()<=0,
				BodyChanging.getTarget().getPenisRawCumStorageValue()>=getCumUpperLimit(),
				FLUID_INCREMENT_SMALL,
				FLUID_INCREMENT_AVERAGE,
				FLUID_INCREMENT_LARGE,
				FLUID_INCREMENT_HUGE);
	}
	
	public static String getSelfTransformCumRegenerationDiv() {
		return applyVariableWrapperFluids("精液再生速度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.Name]产生精液的速度。"
						+ "<br/><i>耗尽后，[npc.namePos]的睾丸需要以该速度恢复精液至最大储量。</i>"),
				"CUM_REGENERATION",
				Units.fluid(BodyChanging.getTarget().getPenisRawCumProductionRegenerationValue(), ValueType.PRECISE)+"/天"
					+"<br/>("+Units.fluid(BodyChanging.getTarget().getCumRegenerationPerSecond()*60, ValueType.PRECISE)+"/分钟)"
					+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getPenisCumProductionRegeneration().getName())+"</i>",
				BodyChanging.getTarget().getPenisRawCumProductionRegenerationValue()<=0,
				BodyChanging.getTarget().getPenisRawCumProductionRegenerationValue()>=FluidRegeneration.FOUR_VERY_RAPID.getMaximumRegenerationValuePerDay(),
				FLUID_REGEN_INCREMENT_SMALL,
				FLUID_REGEN_INCREMENT_AVERAGE,
				FLUID_REGEN_INCREMENT_LARGE,
				FLUID_REGEN_INCREMENT_HUGE);
	}

	public static String getSelfTransformCumExplusionDiv() {
		CumProduction expelledValue = CumProduction.getCumProductionFromInt(BodyChanging.getTarget().getPenisMaximumOrgasmCumQuantity());
		int extraSlotsDirtied = expelledValue.getAdditionalSlotsDirtiedUponOrgasm();
		return applyFullVariableWrapper("射精量",
				UtilText.parse(BodyChanging.getTarget(), "修改每次高潮会射出多少[npc.namePos]储存的精液。"
						+ "<br/><i>若储存的精液量小于等于"+Units.fluid(Testicle.MINIMUM_VALUE_FOR_ALL_CUM_TO_BE_EXPELLED)+"，将会射出睾丸中所有剩余的精液。</i>"),
				"CUM_EXPULSION",
				"1",
				"10",
				BodyChanging.getTarget().getPenisRawCumExpulsionValue()+"%"
						+"<br/><i>"+Units.fluid(BodyChanging.getTarget().getPenisMaximumOrgasmCumQuantity())+" - <span style='color:"+expelledValue.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(expelledValue.getName())+"</span></i>",
//					+"<br/><i>"+Util.capitaliseSentence(BodyChanging.getTarget().getPenisCumExpulsion().getDescriptor())+"</i>",
				BodyChanging.getTarget().getPenisRawCumExpulsionValue()<=5 || !Main.getProperties().hasValue(PropertyValue.cumRegenerationContent),
				BodyChanging.getTarget().getPenisRawCumExpulsionValue()>=100 || !Main.getProperties().hasValue(PropertyValue.cumRegenerationContent),
				"<i>高潮时脏污额外<b style='color:"+expelledValue.getColour().toWebHexString()+";'>"+Util.intToString(extraSlotsDirtied)+"</b>个无装备栏位"+(extraSlotsDirtied==1?"":"")+"。</i>");
	}

	public static String getSelfTransformCumFlavourDiv() {
		contentSB.setLength(0);
		
		for(FluidFlavour flavour : FluidFlavour.values()) {
			if(BodyChanging.getTarget().getCumFlavour().equals(flavour)) {
				contentSB.append(
						"<div id='CUM_FLAVOUR_"+flavour+"' class='cosmetics-button active'>"
							+ "<span style='color:"+flavour.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CUM_FLAVOUR_"+flavour+"' class='cosmetics-button'>"
							+ "<span style='color:"+flavour.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(flavour.getName())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("精液风味",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]精液的风味。"
							+ "<br/><i>会影响性交内外的描述。</i>"),
				"CUM_FLAVOUR",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformCumModifiersDiv() {
		contentSB.setLength(0);
		
		for(FluidModifier modifier : FluidModifier.values()) {
			if(BodyChanging.getTarget().hasCumModifier(modifier)) {
				contentSB.append(
						"<div id='CUM_MODIFIER_"+modifier+"' class='cosmetics-button active'>"
							+ "<span style='color:"+modifier.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CUM_MODIFIER_"+modifier+"' class='cosmetics-button'>"
							+ "<span style='color:"+modifier.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(modifier.getName())+(modifier.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("精液修饰词",
				UtilText.parse(BodyChanging.getTarget(), "为[npc.namePos]的精液添加或移除修饰词。"
							+ "<br/><i>会影响性交内外的描述。在游戏内有特殊效果的修饰词将会被标记为星号*，如摄入后会获得状态效果等。</i>"),
				"CUM_MODIFIER",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraElasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificeElasticity value : OrificeElasticity.values()) {
				if (BodyChanging.getTarget().getUrethraElasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_ELASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
									+(value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
									+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道弹性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的弹性。"
						+ "<br/><i>该部分决定了[npc.namePos]的尿道被过分扩张后的适应速度。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"标记为星号(*)的数值会增加尿道的最大不适深度。":""))
						+"</i>",
				"PENIS_URETHRA_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraPlasticityDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificePlasticity value : OrificePlasticity.values()) {
				if (BodyChanging.getTarget().getUrethraPlasticity() == value) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
									+"<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_PLASTICITY_"+value+"' class='cosmetics-button'>"
									+"<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道可塑性",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的可塑性。"
							+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"PENIS_URETHRA_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformPenisModifiersDiv() {
		contentSB.setLength(0);
		
		for(PenetrationModifier penMod : PenetrationModifier.values()) {
			if(BodyChanging.getTarget().hasPenisModifier(penMod)) {
				contentSB.append(
						"<div id='PENIS_MOD_"+penMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_MOD_"+penMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴茎修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴茎的修饰词。"
							+ "<br/><i>阴茎修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"PENIS_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformUrethraModifiersDiv() {
		contentSB.setLength(0);
		
		if (!Main.game.isUrethraEnabled()) {
			contentSB.append(
					"<div class='cosmetics-button disabled'>"
							+Util.capitaliseSentence("禁用尿道插入")
							+"</div>");
		} else {
			for (OrificeModifier orificeMod : OrificeModifier.values()) {
				if (BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
					contentSB.append(
							"<div id='URETHRA_MOD_"+orificeMod+"' class='cosmetics-button active'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
					
				} else {
					contentSB.append(
							"<div id='URETHRA_MOD_"+orificeMod+"' class='cosmetics-button'>"
									+"<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
									+"</div>");
				}
			}
		}

		return applyWrapper("尿道修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]尿道的修饰词。"
							+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"PENIS_URETHRA_MODS",
				contentSB.toString(),
				true);
	}
	
	public static String getBodySizeChoiceDiv() {
		contentSB.setLength(0);
		
		for(BodySize bs : BodySize.values()) {
			if( BodyChanging.getTarget().getBodySize() == bs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+ BodyChanging.getTarget().getBodySize().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(bs.getName(false))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BODY_SIZE_"+bs+"' class='cosmetics-button'>"
							+ "<span style='color:"+bs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(bs.getName(false))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("体型",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的体型。"
						+ "<br/><i>决定了[npc.namePos]有多少脂肪，单纯只是外表转化。</i>"),
				"BODY_SIZE",
				contentSB.toString(),
				true);
	}
	
	public static String getMuscleChoiceDiv() {
		contentSB.setLength(0);
		
		for(Muscle muscle : Muscle.values()) {
			if( BodyChanging.getTarget().getMuscle() == muscle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+ BodyChanging.getTarget().getMuscle().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='MUSCLE_"+muscle+"' class='cosmetics-button'>"
							+ "<span style='color:"+muscle.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(muscle.getName(false))+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("肌肉量",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]的肌肉量。"+(!Main.game.isInNewWorld()?"并不会影响角色的体格属性。":"")
							+ "<br/><i>决定了[npc.namePos]拥有多少肌肉，单纯只是外表转化。</i>"),
				"MUSCLE",
				contentSB.toString(),
				true);
	}
	
	public static String getLipSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"嘴唇尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你嘴唇的大小。"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(LipSize ls : LipSize.values()) {
			if(!ls.isImpedesSpeech() || !Main.game.isLipLispEnabled()) {
				if(BodyChanging.getTarget().getLipSize() == ls) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+ls.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ls.getName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='LIP_SIZE_"+ls+"' class='cosmetics-button'>"
								+ "<span style='color:"+ls.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ls.getName())+"</span>"
							+ "</div>");
				}
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getLipPuffynessDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"肉唇"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你的嘴唇是否会更有肉感些。"
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().hasFaceOrificeModifier(OrificeModifier.PUFFY)) {
			contentSB.append(
					"<div id='LIP_PUFFY_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>普通</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(肉感)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(普通)]"
					+ "</div>"
					+ "<div id='LIP_PUFFY_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>肉感</span>"
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static CupSize[] getBreastSizesAvailable() {// TODO Slider
		return new CupSize[] {CupSize.FLAT, CupSize.TRAINING_AAA, CupSize.TRAINING_AA, CupSize.TRAINING_A, CupSize.AA, CupSize.A, CupSize.B, CupSize.C, CupSize.D, CupSize.DD, CupSize.E};
//		if(BodyChanging.getTarget().hasPenis()) {
//			return new CupSize[] {CupSize.FLAT, CupSize.TRAINING_AAA, CupSize.TRAINING_AA, CupSize.TRAINING_A};
//		} else {
//			return new CupSize[] {CupSize.AA, CupSize.A, CupSize.B, CupSize.C, CupSize.D, CupSize.DD, CupSize.E};
//		}
	}
	
	public static String getBreastSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"乳房尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你的乳房的大小，以罩杯表示"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		CupSize[] sizesAvailable = getBreastSizesAvailable();
		
		for(CupSize cs : sizesAvailable) {
			if(BodyChanging.getTarget().getBreastSize() == cs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(cs.getCupSizeName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='BREAST_SIZE_"+cs+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(cs.getCupSizeName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBreastShapeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"乳房形状"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你乳房的形状。"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(BreastShape bs : BreastShape.values()) {
			if(!bs.isRestrictedToCrotchBoobs()) {
				if(BodyChanging.getTarget().getBreastShape() == bs) {
					contentSB.append(
							"<div class='cosmetics-button active'>"
								+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='BREAST_SHAPE_"+bs+"' class='cosmetics-button'>"
								+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(bs.getTransformName())+"</span>"
							+ "</div>");
				}
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static NippleSize[] getNippleSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new NippleSize[] {NippleSize.ZERO_TINY, NippleSize.ONE_SMALL, NippleSize.TWO_BIG};
		} else {
			return new NippleSize[] {NippleSize.ZERO_TINY, NippleSize.ONE_SMALL, NippleSize.TWO_BIG, NippleSize.THREE_LARGE, NippleSize.FOUR_MASSIVE};
		}
	}
	
	public static String getNippleSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"乳头尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你的乳头的大小"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		NippleSize[] sizesAvailable = getNippleSizesAvailable();
		
		for(NippleSize ns : sizesAvailable) {
			if(BodyChanging.getTarget().getNippleSize() == ns) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+ns.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='NIPPLE_SIZE_"+ns+"' class='cosmetics-button'>"
							+ "<span style='color:"+ns.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(ns.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static AreolaeSize[] getAreolaeSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new AreolaeSize[] {AreolaeSize.ZERO_TINY, AreolaeSize.ONE_SMALL, AreolaeSize.TWO_BIG};
		} else {
			return new AreolaeSize[] {AreolaeSize.ZERO_TINY, AreolaeSize.ONE_SMALL, AreolaeSize.TWO_BIG, AreolaeSize.THREE_LARGE, AreolaeSize.FOUR_MASSIVE};
		}
	}
	
	public static String getAreolaeSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"乳晕尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你乳晕的大小"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		AreolaeSize[] sizesAvailable = getAreolaeSizesAvailable();
		
		for(AreolaeSize as : sizesAvailable) {
			if(BodyChanging.getTarget().getAreolaeSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='AREOLAE_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getNipplePuffynessDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"肉感乳头"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你的乳头是否会更有肉感。"
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().hasNippleOrificeModifier(OrificeModifier.PUFFY)) {
			contentSB.append(
					"<div id='NIPPLE_PUFFY_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>正常</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(肉感)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(正常)]"
					+ "</div>"
					+ "<div id='NIPPLE_PUFFY_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>肉感</span>"
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static AssSize[] getAssSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new AssSize[] {AssSize.ZERO_FLAT, AssSize.ONE_TINY, AssSize.TWO_SMALL, AssSize.THREE_NORMAL, AssSize.FOUR_LARGE};
		} else {
			return AssSize.values();
		}
	}
	
	public static String getAssSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"屁股尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你屁股的大小。"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		AssSize[] sizesAvailable = getAssSizesAvailable();
		
		for(AssSize as : sizesAvailable) {
			if(BodyChanging.getTarget().getAssSize() == as) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+as.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='ASS_SIZE_"+as+"' class='cosmetics-button'>"
							+ "<span style='color:"+as.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(as.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static HipSize[] getHipSizesAvailable() {
		if(BodyChanging.getTarget().hasPenis()) {
			return new HipSize[] {HipSize.ZERO_NO_HIPS, HipSize.ONE_VERY_NARROW, HipSize.TWO_NARROW, HipSize.THREE_GIRLY};
		} else {
			return HipSize.values();
		}
	}
	
	public static String getHipSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"臀部尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你臀部的大小。"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		HipSize[] sizesAvailable = getHipSizesAvailable();
		
		for(HipSize hs : sizesAvailable) {
			if(BodyChanging.getTarget().getHipSize() == hs) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+hs.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HIP_SIZE_"+hs+"' class='cosmetics-button'>"
							+ "<span style='color:"+hs.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(hs.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getBleachedAnusDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"已漂白的肛门"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你的肛门是否漂白至与其余皮肤同色。"
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>");
		
		if(BodyChanging.getTarget().isAssBleached()) {
			contentSB.append(
					"<div id='BLEACHING_OFF' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>正常</span>"
					+ "</div>"
					+ "<div class='cosmetics-button active'>"
						+ "[style.boldGood(已漂白)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div class='cosmetics-button active'>"
						+ "[style.boldGood(正常)]"
					+ "</div>"
					+ "<div id='BLEACHING_ON' class='cosmetics-button'>"
						+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>已漂白</span>"
					+ "</div>");
		}
		
		contentSB.append(
				"</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static int[] getPenisSizesAvailable() {
		return new int[] {3, 5, 8, 10, 12, 15, 18, 20};
	}
	
	public static String getPenisSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"阴茎长度"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你阴茎的长度"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		int[] sizesAvailable = getPenisSizesAvailable();
		
		for(int size : sizesAvailable) {
			if(BodyChanging.getTarget().getPenisRawSizeValue() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Units.size(size)+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='PENIS_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Units.size(size)+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static TesticleSize[] getTesticleSizesAvailable() {
		return new TesticleSize[] {TesticleSize.ZERO_VESTIGIAL, TesticleSize.ONE_TINY, TesticleSize.TWO_AVERAGE, TesticleSize.THREE_LARGE};
	}
	
	public static String getTesticleSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"阴囊尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你阴囊的大小。"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		TesticleSize[] sizesAvailable = getTesticleSizesAvailable();
		
		for(TesticleSize size : sizesAvailable) {
			if(BodyChanging.getTarget().getTesticleSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='TESTICLE_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getVaginaCapacityDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"阴道直径"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你阴道的直径，更大的直径意味着你可以更轻松的接受大号物体的插入，但如果太松，对于阴茎较小的伴侣就不会有太大的快感。"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getVaginaCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='VAGINA_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}

	public static String getSelfTransformClitorisModifiersDiv() {
		contentSB.setLength(0);
		
		for(PenetrationModifier penMod : PenetrationModifier.values()) {
			if(BodyChanging.getTarget().hasClitorisModifier(penMod)) {
				contentSB.append(
						"<div id='CLITORIS_MOD_"+penMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_MOD_"+penMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(penMod.getName())+(penMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("阴蒂修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]阴蒂的修饰词"
							+ "<br/><i>阴蒂修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"CLITORIS_MODS",
				contentSB.toString(),
				true);
	}
	
	public static ClitorisSize[] getClitorisSizesAvailable() {
		return new ClitorisSize[] {ClitorisSize.ZERO_AVERAGE, ClitorisSize.ONE_BIG};
	}
	
	public static String getClitorisSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"阴蒂长度"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你阴蒂的大小"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		ClitorisSize[] sizesAvailable = getClitorisSizesAvailable();
		
		for(ClitorisSize size : sizesAvailable) {
			if(BodyChanging.getTarget().getVaginaClitorisSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='CLITORIS_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getDescriptor())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getLabiaSizeDiv() {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+"<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+"阴唇尺寸"
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ "选择你阴唇的大小"
						+ "</p>"
						+ "</div>"
						+ "<div class='cosmetics-inner-container right'>");
		
		for(LabiaSize size : LabiaSize.values()) {
			if(BodyChanging.getTarget().getVaginaLabiaSize() == size) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+size.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='LABIA_SIZE_"+size+"' class='cosmetics-button'>"
							+ "<span style='color:"+size.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(size.getName())+"</span>"
						+ "</div>");
			}
		}
		
		contentSB.append("</div></div>");
		
		return contentSB.toString();
	}
	
	public static String getSelfTransformSpinneretModifiersDiv() {
		contentSB.setLength(0);
		
		for(OrificeModifier orificeMod : OrificeModifier.values()) {
			if(BodyChanging.getTarget().hasSpinneretOrificeModifier(orificeMod)) {
				contentSB.append(
						"<div id='SPINNERET_MOD_"+orificeMod+"' class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.toWebHexString()+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_MOD_"+orificeMod+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_SEXUAL.getShades()[0]+";'>"+Util.capitaliseSentence(orificeMod.getName())+(orificeMod.isSpecialEffects()?"*":"")+"</span>"
						+ "</div>");
			}
		}
		
		return applyWrapper("丝囊修饰词",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]丝囊的修饰词"
							+ "<br/><i>腔穴修饰词影响性交过程中的描述和部分动作。不止影响描述的修饰词将会被标记为星号*。</i>"),
				"SPINNERET_MODS",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformSpinneretCapacityDiv() {
		contentSB.setLength(0);
		
		for(Capacity value : Capacity.getCapacityListFromPreferences()) {
			if(BodyChanging.getTarget().getSpinneretCapacity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_CAPACITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("丝囊直径",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]丝囊的直径"
							+ "<br/><i>该部分影响[npc.namePos]丝囊能够接受的插入物的尺寸。如果对于插入物来说容量太小或太大，就会影响快感获取。</i>"),
				"SPINNERET_CAPACITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformSpinneretWetnessDiv() {
		contentSB.setLength(0);
		
		for(Wetness value : Wetness.values()) {
			if(BodyChanging.getTarget().getSpinneretWetness() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='SPINNERET_WETNESS_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("丝囊湿润度",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]丝囊的湿润度。"
							+ "<br/><i>影响性交中的快感，未润滑的腔穴会削弱快感的获取。</i>"
							+(BodyChanging.getTarget().getBodyMaterial().isOrificesAlwaysMaximumWetness()
									?"<br/>[style.italicsWetness8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴润滑度永远不会低于'"+Util.capitaliseSentence(Wetness.SEVEN_DROOLING.getDescriptor())+"'！)]"
									:"")),
				"SPINNERET_WETNESS",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformSpinneretDepthDiv() {
		contentSB.setLength(0);
		
		for(OrificeDepth value : OrificeDepth.values()) {
			if(BodyChanging.getTarget().getSpinneretDepth() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				if(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
									+Util.capitaliseSentence(value.getDescriptor())
								+"</span>"
							+ "</div>");
					
				} else {
					contentSB.append(
							"<div id='SPINNERET_DEPTH_"+value+"' class='cosmetics-button'>"
								+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
							+ "</div>");
				}
			}
		}

		return applyWrapper("丝囊深度",
				UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos]的丝囊深度。"
						+ "<br/><i>腔穴的深度决定了多长的物体可以被舒服地容纳入腔穴之中。</i>"
						+(!BodyChanging.getTarget().getBodyMaterial().isOrificesLimitedDepth()
								?"<br/>[style.italicsSize8(由于[npc.namePos]的身体完全由[npc.bodyMaterial]制成，[npc.her]的腔穴深度永远不会低于'"+Util.capitaliseSentence(OrificeDepth.SEVEN_FATHOMLESS.getDescriptor())+"'！)]"
								:"")),
				"SPINNERET_DEPTH",
				contentSB.toString(),
				true);
	}

	public static String getSelfTransformSpinneretElasticityDiv() {
		contentSB.setLength(0);
		
		for(OrificeElasticity value : OrificeElasticity.values()) {
			if(BodyChanging.getTarget().getSpinneretElasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_ELASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"
								+ Util.capitaliseSentence(value.getDescriptor())
								+ (value.isExtendingUncomfortableDepth() && Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?"*":"")
							+ "</span>"
						+ "</div>");
			}
		}

		return applyWrapper("丝囊弹性等级",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]丝囊的弹性等级。"
						+ "<br/><i>该部分决定了[npc.namePos]的尿道被过分扩张后的适应速度。"
						+ (Main.game.isPenetrationLimitationsEnabled() && Main.game.isElasticityAffectDepthEnabled()?" 标记为星号(*)的数值会增加丝囊的最大不适深度。":""))
						+"</i>",
				"SPINNERET_ELASTICITY",
				contentSB.toString(),
				true);
	}
	
	public static String getSelfTransformSpinneretPlasticityDiv() {
		contentSB.setLength(0);

		for(OrificePlasticity value : OrificePlasticity.values()) {
			if(BodyChanging.getTarget().getSpinneretPlasticity() == value) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+value.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='SPINNERET_PLASTICITY_"+value+"' class='cosmetics-button'>"
							+ "<span style='color:"+value.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(value.getDescriptor())+"</span>"
						+ "</div>");
			}
		}

		return applyWrapper("丝囊可塑性等级",
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos]丝囊的可塑性等级。"
							+ "<br/><i>一个腔穴的可塑性等级决定了这个腔穴在被扩张之后恢复的速度。</i>"),
				"SPINNERET_PLASTICITY",
				contentSB.toString(),
				true);
	}
	
	
	
	// ---------------------- Kate's Shop: ---------------------- //
	
	public static String getKatesDivHairLengths(boolean withCost, String title, String description) {
		contentSB.setLength(0);
		
		boolean noCost = !withCost;

		
		for(HairLength hairLength : HairLength.values()) {
			if(BodyChanging.getTarget().getHairLength()==hairLength) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+hairLength.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"")+"</span>"
						+ "</div>");
				
			} else {
				contentSB.append(
						"<div id='HAIR_LENGTH_"+hairLength+"' class='cosmetics-button'>"
								+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST || noCost
									? "<span style='color:"+hairLength.getColour().getShades()[0]+";'>" + Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"") + "</span>"
									: "[style.colourDisabled(" + Util.capitaliseSentence(hairLength.getDescriptor())+(hairLength.isSuitableForPulling()?"*":"") + ")]")
						+ "</div>");
			}
		}

		return applyWrapper("头发长度"
				+(noCost
					?""
					:""+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST
							? UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_LENGTH_COST, "b")
							: UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_LENGTH_COST, "b", PresetColour.GENERIC_BAD))),
				UtilText.parse(BodyChanging.getTarget(), "修改[npc.namePos][npc.hair(true)]的长度。"
						+ "<br/><i>足够长的头发(标记星号*)可以在部分性动作中被拉扯。</i>"),
				"HAIR_LENGTH",
				contentSB.toString(),
				false);
	}
	
	public static String getKatesDivHairStyles(boolean withCost, String title, String description) {
		contentSB.setLength(0);

		boolean noCost = !withCost;
		for (HairStyle hairStyle : HairStyle.values()) {
			if (BodyChanging.getTarget().getHairStyle() == hairStyle) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
						+ "</div>");
			} else {
				if(BodyChanging.getTarget().getHairRawLengthValue() >= hairStyle.getMinimumLengthRequired()) {
					contentSB.append(
							"<div id='HAIR_STYLE_"+hairStyle+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST || noCost
											? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + "</span>"
											: "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + ")]")
							+ "</div>");
				} else {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(hairStyle.getName(BodyChanging.getTarget())) + ")]"
							+ "</div>");
				}
			}
		}

		return applyWrapper("发型"
				+(noCost
						?""
						:""+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST
								? UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_STYLE_COST, "b")
								: UtilText.formatAsMoney(SuccubisSecrets.BASE_HAIR_STYLE_COST, "b", PresetColour.GENERIC_BAD))),
				UtilText.parse(BodyChanging.getTarget(), description)
					+ "<br/><i>“"+Util.capitaliseSentence(HairStyle.TWIN_TAILS.getName(BodyChanging.getTarget()))
						+"”和“"+Util.capitaliseSentence(HairStyle.TWIN_BRAIDS.getName(BodyChanging.getTarget()))+"”可以在部分性动作中作为把手使用。</i>",
				"HAIR_STYLE",
				contentSB.toString(),
				false);
		
	}
	
	public static String getKatesDivAssHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().isAssHairAvailable()
						?""
						:"<br/><i>基于[npc.namePos]的肛门类型，[npc.she]无法长出任何肛毛！</i>"),
				BodyChanging.getTarget().getAssHair(),
				"ASS_HAIR_",
				!BodyChanging.getTarget().isAssHairAvailable());
	}
	
	public static String getKatesDivUnderarmHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().isUnderarmHairAvailable()
						?""
						:"<br/><i>基于[npc.namePos]的手臂类型，[npc.she]无法长出任何腋毛！</i>"),
				BodyChanging.getTarget().getUnderarmHair(),
				"UNDERARM_HAIR_",
				!BodyChanging.getTarget().isUnderarmHairAvailable());
	}
	
	public static String getKatesDivFacialHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().isFeminine() && !Main.game.isFemaleFacialHairEnabled()
						?"<br/><i>由于[npc.nameIsFull]偏女性化，所以无法长出胡子！</i>"
						:(BodyChanging.getTarget().isUnderarmHairAvailable()
							?""
							:"<br/><i>基于[npc.namePos]的脸部类型，[npc.she]无法长出胡子！</i>")),
				BodyChanging.getTarget().getFacialHair(),
				"FACIAL_HAIR_",
				!BodyChanging.getTarget().isFacialHairAvailable() || (BodyChanging.getTarget().isFeminine() && !Main.game.isFemaleFacialHairEnabled()));
	}
	
	public static String getKatesDivPubicHair(boolean withCost, String title, String description) {
		return getKatesDivGenericBodyHair(withCost,
				title,
				description
					+(BodyChanging.getTarget().hasPenisIgnoreDildo() && !BodyChanging.getTarget().getPenisType().isPubicHairAllowed()
						?"<br/><i>基于[npc.namePos]的阴茎类型，[npc.she]无法长出阴毛！</i>"
						:(BodyChanging.getTarget().hasVagina() && !BodyChanging.getTarget().getVaginaType().isPubicHairAllowed()
							?"<br/><i>基于[npc.namePos]的阴道类型，[npc.she]无法长出阴毛！</i>"
							:(BodyChanging.getTarget().hasPenisIgnoreDildo() || BodyChanging.getTarget().hasVagina()
							?""
							:"<br/><i>因为[npc.she]缺失性器，[npc.name]无法长出阴毛！</i>"))),
				BodyChanging.getTarget().getPubicHair(),
				"PUBIC_HAIR_",
				!BodyChanging.getTarget().isPubicHairAvailable());
	}
	
	private static String getKatesDivGenericBodyHair(boolean withCost, String title, String description, BodyHair activeHair, String id, boolean blockAllButNoneOptions) {
		contentSB.setLength(0);

		boolean noCost = !withCost;
		for (BodyHair bodyHair : BodyHair.values()) {
			if (activeHair == bodyHair) {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>" + Util.capitaliseSentence(bodyHair.getName()) + "</span>"
						+ "</div>");
			} else {
				if(blockAllButNoneOptions) {
					contentSB.append(
							"<div class='cosmetics-button disabled'>"
								+ "[style.colourDisabled(" + Util.capitaliseSentence(bodyHair.getName()) + ")]"
							+ "</div>");
				} else {
					contentSB.append(
							"<div id='"+id+bodyHair+"' class='cosmetics-button'>"
									+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || noCost
										? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(bodyHair.getName()) + "</span>"
										: "[style.colourDisabled(" + Util.capitaliseSentence(bodyHair.getName()) + ")]")
							+ "</div>");
				}
			}
		}

		return applyWrapper(title
				+(noCost
						?""
						:""+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST
								? UtilText.formatAsMoney(SuccubisSecrets.BASE_BODY_HAIR_COST, "b")
								: UtilText.formatAsMoney(SuccubisSecrets.BASE_BODY_HAIR_COST, "b", PresetColour.GENERIC_BAD))),
				UtilText.parse(BodyChanging.getTarget(), description),
				id,
				contentSB.toString(),
				false);
	}
	
	public static String getKatesDivGenericBodyHairDisabled(String title, String description, String disabledDescription) {
		contentSB.setLength(0);
		
		contentSB.append(
				"<div class='container-full-width'>"
					+ "<div class='cosmetics-inner-container left'>"
						+ "<h5 style='text-align:center;'>"
							+title
						+"</h5>"
						+ "<p style='text-align:center;'>"
							+ description
						+ "</p>"
					+ "</div>"
					+ "<div class='cosmetics-inner-container right'>"
					+ "<p style='text-align:center; color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"
						+ disabledDescription
					+ "</p>"
				+ "</div>"
			+ "</div>");
		
		return contentSB.toString();
	}
	
	public static String getKatesDivAnalBleaching() {
		contentSB.setLength(0);

		boolean noCost = !Main.game.isInNewWorld();

		contentSB.append("<div class='container-full-width'>");

			contentSB.append(
						 "<div class='container-full-width' style='text-align:center; width:100%;padding:0;margin:0;'>"
							+ "<b>肛门漂白</b>"
							+ (noCost
								?""
								:""+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
									? UtilText.formatAsMoney(SuccubisSecrets.BASE_ANAL_BLEACHING_COST, "b")
									: UtilText.formatAsMoney(SuccubisSecrets.BASE_ANAL_BLEACHING_COST, "b", PresetColour.GENERIC_BAD)))
						+ "</div>");
						
			contentSB.append(
					getInformationDiv(
							"ANAL_BLEACHING",
							new TooltipInformationEventListener().setInformation("肛门漂白", "肛门漂白即减轻肛门附近皮肤颜色的过程，使其与周围的颜色相贴合。"),
							false));
			
			contentSB.append("<div class='container-full-width' style='text-align:center; width:100%;padding:0;margin:0;'>");
			if(BodyChanging.getTarget().isAssBleached()) {
				contentSB.append(
						"<div id='BLEACHING_OFF' class='cosmetics-button'>"
							+ "[style.colourDisabled(普通)]"
						+ "</div>"
						+ "<div class='cosmetics-button active'>"
							+ "[style.boldArcane(已漂白)]"
						+ "</div>");
			} else {
				contentSB.append(
						"<div class='cosmetics-button active'>"
							+ "普通"
						+ "</div>"
						+ "<div id='BLEACHING_ON' class='cosmetics-button'>"
							+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST
								?"<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>已漂白</span>"
								:"[style.colourDisabled(已漂白)]")
						+ "</div>");
			}
	
			contentSB.append("</div>");
			
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	//TODO reset on open
	private static Map<AbstractBodyCoveringType, Covering> coveringsToBeApplied = new HashMap<>();
	
	public static Map<AbstractBodyCoveringType, Covering> getCoveringsToBeApplied() {
		return coveringsToBeApplied;
	}
	
	public static void resetCoveringsToBeApplied() {
		coveringsToBeApplied = new HashMap<>();
	}

	public static String getKatesDivCoveringsNew(boolean withCost, AbstractRace race, AbstractBodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow) {
		return getKatesDivCoveringsNew(withCost, race, coveringType, title, description, withSecondary, withGlow, true);
	}
	
	public static String getKatesDivCoveringsNew(boolean withCost, AbstractRace race, AbstractBodyCoveringType coveringType, String title, String description, boolean withSecondary, boolean withGlow, boolean withDyeAndExtraPatterns) {
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.coveringChangeListenersRequired, true);
		
		boolean disabledButton = !coveringsToBeApplied.containsKey(coveringType) || coveringsToBeApplied.get(coveringType).equals(BodyChanging.getTarget().getCovering(coveringType));
		
		StringBuilder sb = new StringBuilder();

		Covering activeCovering = !coveringsToBeApplied.containsKey(coveringType)
										?BodyChanging.getTarget().getCovering(coveringType)
										:coveringsToBeApplied.get(coveringType);
		
		List<CoveringPattern> availablePatterns = new ArrayList<>(withDyeAndExtraPatterns
																	?coveringType.getAllPatterns().keySet()
																	:coveringType.getNaturalPatterns().keySet());
		
		String rainbow = "";
		
		List<Colour> availablePrimaryColours = new ArrayList<>(withDyeAndExtraPatterns
				?coveringType.getAllPrimaryColours()
				:coveringType.getNaturalColoursPrimary());
		Collections.sort(availablePrimaryColours, (c1, c2)->c2.isMetallic()?(c1.isMetallic()?0:-1):(c1.isMetallic()?1:0));
		
		List<Colour> availableSecondaryColours = new ArrayList<>(withDyeAndExtraPatterns
													?coveringType.getAllSecondaryColours()
													:coveringType.getNaturalColoursSecondary());
		Collections.sort(availableSecondaryColours, (c1, c2)->c2.isMetallic()?(c1.isMetallic()?0:-1):(c1.isMetallic()?1:0));
		
		String border = "border: 1px solid "+PresetColour.BACKGROUND.toWebHexString()+";";
		
		boolean secondaryDisabled = activeCovering.getPattern()==CoveringPattern.NONE
										|| activeCovering.getPattern()==CoveringPattern.EYE_IRISES
										|| activeCovering.getPattern()==CoveringPattern.EYE_PUPILS
										|| activeCovering.getPattern()==CoveringPattern.EYE_SCLERA
										|| !withSecondary;
		
		sb.append("<div class='container-full-width' style='text-align:center;'>");

			sb.append("<div class='container-full-width' style='padding:0; margin:0; text-align:center;'>");
				sb.append("<b>"+Util.capitaliseSentence(title)+"</b>");
				if(race!=Race.NONE) {
					sb.append(" -<b style='color:"+(race.getColour().toWebHexString())+"'>"+Util.capitaliseSentence(race.getName(BodyChanging.getTarget().getBody(), BodyChanging.getTarget().isFeral()))+"</b>");
				}
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='padding:0; margin:0; text-align:center;'>");
				sb.append(Util.capitaliseSentence(BodyChanging.getTarget().getCovering(coveringType).getFullDescription(BodyChanging.getTarget(), true)));
			sb.append("</div>");

			// Pattern and Modifiers section on left:
			sb.append("<div class='container-full-width' style='width:33.3%; padding:0; margin:0; text-align:center;'>");
			
				// Covering Pattern:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<p style='padding:0;margin:0;text-align:center;'>图案：</p>");
					for (CoveringPattern pattern : availablePatterns) {
						if (activeCovering.getPattern() == pattern) {
							sb.append(
									"<div class='cosmetics-button active'>"
										+ "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
									+ "</div>");
						} else {
							sb.append(
									"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PATTERN_"+pattern+"' class='cosmetics-button'>"
											+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
												? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getName()) + "</span>"
												: "[style.colourDisabled(" + Util.capitaliseSentence(pattern.getName()) + ")]")
									+ "</div>");
						}
					}
				sb.append("</div>");
				
				// Covering Modifiers:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<p style='padding:0;margin:0;text-align:center;'>修饰词:</p>");
					if(activeCovering.getType().getNaturalModifiers().size() + activeCovering.getType().getExtraModifiers().size()>1) {
//						sb.append("<div class='container-full-width'>");
						for(CoveringModifier mod : activeCovering.getType().getNaturalModifiers()) {
							if (activeCovering.getModifier() == mod) {
								sb.append(
										"<div class='cosmetics-button active'>"
											+ "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
										+ "</div>");
							} else {
								sb.append(
										"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_MODIFIER_"+mod+"' class='cosmetics-button'>"
												+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
													? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
													: "[style.colourDisabled(" + Util.capitaliseSentence(mod.getName()) + ")]")
										+ "</div>");
							}
						}
						for(CoveringModifier mod : activeCovering.getType().getExtraModifiers()) {
							if (activeCovering.getModifier() == mod) {
								sb.append(
										"<div class='cosmetics-button active'>"
											+ "<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
										+ "</div>");
							} else {
								sb.append(
										"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_MODIFIER_"+mod+"' class='cosmetics-button'>"
												+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType) || !withCost
													? "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(mod.getName()) + "</span>"
													: "[style.colourDisabled(" + Util.capitaliseSentence(mod.getName()) + ")]")
										+ "</div>");
							}
						}
//						sb.append("</div>");
						
					} else {
						sb.append("<p style='padding:0;margin:0;text-align:center;'>[style.italicsDisabled(无可用)]</p>");
					}
				sb.append("</div>");
				
			sb.append("</div>");

			// Primary and secondary colours:
			sb.append("<div class='container-full-width' style='width:66.6%; padding:0; margin:0; text-align:center;'>");
			
				// Primary:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; background:transparent;'>");
						sb.append("主要颜色");
						sb.append(" | <span style='color:"+activeCovering.getPrimaryColour().toWebHexString()+";"
										+(activeCovering.isPrimaryGlowing()
												?"text-shadow: 0px 0px 4px "+activeCovering.getPrimaryColour().getShades()[4]+";"
												:"")+"'>"
									+Util.capitaliseSentence(activeCovering.getPrimaryColour().getName())
								+"</span>");
					sb.append("</div>");
					for (Colour c : availablePrimaryColours) {
						if(c.getRainbowColours()!=null) {
							rainbow = c.getRainbowDiv(5);
						} else {
							rainbow = "";
						}
						sb.append("<div class='normal-button"+(activeCovering.getPrimaryColour()==c?" selected":"")+"' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PRIMARY_"+c.getId()+"'"
												+ " style='width:auto; margin-right:4px;"+(activeCovering.getPrimaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
											+ (c.isMetallic()
													?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:(c.getRainbowColours()!=null
														?"<div class='phone-item-colour' style='background: "+rainbow
														:"<div class='phone-item-colour' style='background"+(c==PresetColour.COVERING_CLEAR?"-image":"-color")+":" + (c.getCoveringIconColour()) + ";"))
												+(c==PresetColour.COVERING_NONE
													?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X"
													:"'>")
											+"</div>"
										+ "</div>");
					}
					
					if(activeCovering.getPrimaryColour()!=PresetColour.COVERING_NONE && withGlow) { // Glow:
						if(activeCovering.isPrimaryGlowing()) {
							sb.append(
									"<div class='normal-button active' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PRIMARY_GLOW_OFF' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
										+ "[style.boldArcane(奥术光芒)]"
									+ "</div>");
						} else {
							sb.append(
									"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_PRIMARY_GLOW_ON' class='normal-button' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
										+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>奥术光芒</span>"
									+ "</div>");
						}
					}
				sb.append("</div>");
				
				// Secondary:
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; background:transparent;'>");
						sb.append("次要颜色");
						if(!secondaryDisabled) {
							sb.append(" | <span style='color:"+activeCovering.getSecondaryColour().toWebHexString()+";"
											+(activeCovering.isSecondaryGlowing()
													?"text-shadow: 0px 0px 4px "+activeCovering.getSecondaryColour().getShades()[4]+";"
													:"")+"'>"
										+Util.capitaliseSentence(activeCovering.getSecondaryColour().getName())
									+"</span>");
						}
					sb.append("</div>");
					if(secondaryDisabled) {
						sb.append("<p style='padding:0;margin:0;text-align:center;'>[style.italicsDisabled(无可用)]</p>");
						
					} else {
						for (Colour c : availableSecondaryColours) {
							if(c.getRainbowColours()!=null) {
								rainbow = c.getRainbowDiv(5);
							} else {
								rainbow = "";
							}
							sb.append("<div class='normal-button"+(activeCovering.getSecondaryColour()==c?" selected":"")+"' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_SECONDARY_"+c.getId()+"'"
													+ " style='width:auto; margin-right:4px;"+(activeCovering.getSecondaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
												+ (c.isMetallic()
														?"<div class='phone-item-colour' style='background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
														:(c.getRainbowColours()!=null
															?"<div class='phone-item-colour' style='background: "+rainbow
															:"<div class='phone-item-colour' style='background"+(c==PresetColour.COVERING_CLEAR?"-image":"-color")+":" + (c.getCoveringIconColour()) + ";"))
													+(c==PresetColour.COVERING_NONE
														?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X"
														:"'>")
												+"</div>"
											+ "</div>");
						}
					}
					
					if(activeCovering.getSecondaryColour() != PresetColour.COVERING_NONE && withGlow && !secondaryDisabled) { // Glow:
						if(activeCovering.isSecondaryGlowing()) {
							sb.append(
									"<div class='normal-button active' id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_SECONDARY_GLOW_OFF' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
										+ "[style.boldArcane(奥术光芒)]"
									+ "</div>");
						} else {
							sb.append(
									"<div id='"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"_SECONDARY_GLOW_ON' class='normal-button' style='width:50%; margin:1% 25%; padding:0; text-align:center;'>"
										+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>奥术光芒</span>"
									+ "</div>");
						}
					}
				sb.append("</div>");
			
			sb.append("</div>");

			if(coveringType==BodyCoveringType.MAKEUP_LIPSTICK && Main.getProperties().hasValue(PropertyValue.lipstickMarkingContent)) {
				boolean heavyLipstick = BodyChanging.getTarget().isHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
				sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center; "+border+"'>");
					sb.append("<div class='container-full-width' style='width:60%; padding:0; margin:0; text-align:center;'>");
						sb.append(UtilText.parse(BodyChanging.getTarget(),
								"涂上浓重的口红后，[npc.name]可以在亲吻的部位留下唇印！"
								+ "<br/>[style.italics(在性交中使用过后，浓重的口红需要重涂。)]"));
					sb.append("</div>");
					sb.append("<div class='container-full-width' style='width:40%; padding:0; margin:0; text-align:center;'>");
						if(!heavyLipstick) {
							sb.append(
									"<div class='cosmetics-button active'>"
										+ "<span style='color:" + PresetColour.BASE_PINK_LIGHT.toWebHexString() + ";'>普通</span>"
									+ "</div>");
						} else {
							sb.append(
									"<div id='MAKEUP_LIPSTICK_HEAVY_OFF' class='cosmetics-button'>"
										+ "<span style='color:"+PresetColour.BASE_PINK_LIGHT.getShades()[0]+";'>普通</span>"
									+ "</div>");
						}
						if(heavyLipstick) {
							sb.append(
									"<div class='cosmetics-button active'>"
										+ "<span style='color:" + PresetColour.BASE_PINK_DEEP.toWebHexString() + ";'>浓重</span>"
									+ "</div>");
						} else {
							sb.append(
									"<div id='MAKEUP_LIPSTICK_HEAVY_ON' class='cosmetics-button'>"
										+ "<span style='color:"+PresetColour.BASE_PINK_DEEP.getShades()[0]+";'>浓重</span>"
									+ "</div>");
						}
					sb.append("</div>");
				sb.append("</div>");
			}
			
			// Reset/Apply changes buttons:
			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0; text-align:center;'>");
				sb.append("<div class='container-full-width' style='width:50%; padding:0; margin:0; text-align:center;'>");
					if(disabledButton) {
						sb.append("<div class='normal-button disabled' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;'>"
									+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>重置修改</span>"
								+ "</div>");
					} else {
						sb.append("<div class='normal-button' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;' id='RESET_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"'>"
										+ "[style.colourMinorBad(重置修改)]"
									+ "</div>");
					}
				sb.append("</div>");
				sb.append("<div class='container-full-width' style='width:50%; padding:0; margin:0; text-align:center;'>");
					if(disabledButton) {
						sb.append("<div class='normal-button disabled' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;'>"
									+"<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>应用修改"
										+ (withCost
											?" ("+UtilText.formatAsMoneyUncoloured(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "span")+")"
											:"")
									+"</span>"
								+ "</div>");
					} else {
						sb.append("<div class='normal-button' style='width:80%; margin:2% auto; padding:0; text-align:center; bottom:0;' id='APPLY_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(coveringType)+"'>"
									+ "[style.colourMinorGood(应用修改)]"
									+ (withCost
										?" ("
											+(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(coveringType)
												? UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "span")
												: UtilText.formatAsMoney(SuccubisSecrets.getBodyCoveringTypeCost(coveringType), "span", PresetColour.GENERIC_BAD))+")"
										:"")
									+ "</div>");
					}
				sb.append("</div>");
			sb.append("</div>");
			
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String getKatesDivPiercings(boolean noCost) {
		contentSB.setLength(0);
		
		boolean isPierced = false;
		boolean canPierce = true;
		
		String title="";
		String  description="";
		int i=0;
		
		for(PiercingType piercingType : PiercingType.values()) {
			title = Util.capitaliseSentence(piercingType.getName())+"穿孔";
			description = piercingType.getDescription();
			
			switch(piercingType) {
				case EAR:
					isPierced = BodyChanging.getTarget().isPiercedEar();
					break;
				case LIP:
					isPierced = BodyChanging.getTarget().isPiercedLip();
					break;
				case NAVEL:
					isPierced = BodyChanging.getTarget().isPiercedNavel();
					break;
				case NOSE:
					isPierced = BodyChanging.getTarget().isPiercedNose();
					break;
				case TONGUE:
					isPierced = BodyChanging.getTarget().isPiercedTongue();
					break;
					
				case NIPPLE:
					isPierced = BodyChanging.getTarget().isPiercedNipple();
					break;
				case PENIS:
					canPierce = BodyChanging.getTarget().hasPenisIgnoreDildo();
					isPierced = BodyChanging.getTarget().isPiercedPenis();
					break;
				case VAGINA:
					canPierce = BodyChanging.getTarget().hasVagina();
					isPierced = BodyChanging.getTarget().isPiercedVagina();
					break;
			}

			contentSB.append("<div class='container-full-width' style='padding:4px;margin:0;width:100%;background:"+(i%2==0?PresetColour.BACKGROUND_ALT:PresetColour.BACKGROUND).toWebHexString()+";'>");
				
				if(noCost) {
					contentSB.append("<div class='container-half-width' style='padding:0;margin:0;width:50%;background:transparent;text-align:center;'>");
						if(canPierce) {
							contentSB.append(title);
						} else {
							contentSB.append("[style.colourDisabled("+title+")]");
						}
					contentSB.append("</div>");
					
				} else {
					contentSB.append("<div class='container-half-width' style='padding:0;margin:0 5% 0 0;width:35%;background:transparent;text-align:right;'>");
						if(canPierce) {
							contentSB.append(title);
						} else {
							contentSB.append("[style.colourDisabled("+title+")]");
						}
					contentSB.append("</div>");
					contentSB.append("<div class='container-half-width' style='padding:0;margin:0;width:10%;background:transparent;text-align:left;'>");
						if(Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(piercingType)) {
							contentSB.append(UtilText.formatAsMoney(SuccubisSecrets.getPiercingCost(piercingType), "b"));
						} else {
							contentSB.append(UtilText.formatAsMoney(SuccubisSecrets.getPiercingCost(piercingType), "b", PresetColour.GENERIC_BAD));
						}
					contentSB.append("</div>");
				}
				
				contentSB.append(
						getInformationDiv(
								"PIERCING_INFO_"+piercingType,
								new TooltipInformationEventListener().setInformation(title, description),
								true));

				contentSB.append("<div class='container-half-width' style='padding:0;margin:0;width:50%;background:transparent;'>");
					
					if(isPierced) {
						contentSB.append(
								"<div id='"+piercingType+"_PIERCE_REMOVE' class='cosmetics-button'>"
									+ "[style.colourDisabled(未穿孔)]"
								+ "</div>");
						
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "[style.boldArcane(已穿孔)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div class='cosmetics-button active'>"
									+ "未穿孔"
								+ "</div>");
						
						if(canPierce) {
							contentSB.append(
									"<div id='"+piercingType+"_PIERCE' class='cosmetics-button'>"
										+ (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(piercingType) || noCost
											?"<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>已穿孔</span>"
											:"[style.colourDisabled(已穿孔)]")
									+ "</div>");
							
						} else {
							contentSB.append(
									"<div class='cosmetics-button disabled'>"
										+ "[style.colourDisabled(已穿孔)]"
									+ "</div>");
						}
					}
				contentSB.append("</div>");
			
			contentSB.append("</div>");
			
			i++;
		}
		
		
		return contentSB.toString();
	}

	public static String getKatesDivTattoos() {
		contentSB.setLength(0);
		
		contentSB.append("<div class='container-full-width'>");
//				+ "<h5 style='width:100%; text-align:center;'>Main Areas</h5>");
		
		for(InventorySlot invSlot : RenderingEngine.mainInventorySlots) {
			contentSB.append(getTattooDiv(invSlot));
		}
		
//		contentSB.append("</div>");
//		
//		contentSB.append("<div class='container-full-width'>"
//				+ "<h5 style='width:100%; text-align:center;'>Extra Areas</h5>");
		
		for(InventorySlot invSlot : RenderingEngine.secondaryInventorySlots) {
			contentSB.append(getTattooDiv(invSlot));
		}
		
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getTattooDiv(InventorySlot invSlot) {
		Tattoo tattooInSlot = BodyChanging.getTarget().getTattooInSlot(invSlot);
		boolean disabled = false;
		
		switch(invSlot) {
			case HORNS:
				if(BodyChanging.getTarget().getHornType().equals(HornType.NONE)) {
					disabled = true;
				}
				break;
			case PENIS:
				if(!BodyChanging.getTarget().hasPenisIgnoreDildo()) {
					disabled = true;
				}
				break;
			case TAIL:
				if(BodyChanging.getTarget().getTailType()==TailType.NONE) {
					disabled = true;
				}
				break;
			case VAGINA:
				if(!BodyChanging.getTarget().hasVagina()) {
					disabled = true;
				}
				break;
			case WINGS:
				if(BodyChanging.getTarget().getWingType()==WingType.NONE) {
					disabled = true;
				}
				break;
			default:
				break;
		}
		
		return "<div class='container-half-width inner' style='width:23%;margin:1%;'>"
				+ "<div class='container-full-width inner' style='width:100%; margin:0; text-align:center;'>"+Util.capitaliseSentence(invSlot.getTattooSlotName())+"</div>"
				+(disabled
					?"<div class='inventory-item-slot disabled' style='width:48%;margin:0 1%'></div>"
					:"<div class='modifier-icon' style='width:48%;margin:0 1%'>"
						+ (tattooInSlot==null
							?"<div class='modifier-icon-content'></div>"
							:"<div class='modifier-icon-content' style='background-color:"+tattooInSlot.getRarity().getBackgroundColour().toWebHexString()+";'>"+tattooInSlot.getSVGImage(BodyChanging.getTarget())+"</div>")
						+ "<div class='overlay no-pointer' id='TATTOO_INFO_"+invSlot.toString()+"'></div>"
					+ "</div>")
				
				+ "<div class='container-half-width inner' style='width:48%;margin:0 1%;padding:0;'>"
					+ "<div style='float:left; width:98%; margin:0 1%; padding:0;'>"
						+ "<div class='normal-button"+(disabled?" disabled":"")+"' "+(!disabled?"id='TATTOO_ADD_REMOVE_"+invSlot.toString()+"'":"")+" style='width:100%;'>"
							+(tattooInSlot==null
								?"添加"
								:(SuccubisSecrets.invSlotTattooToRemove==invSlot || !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?"[style.colourBad(去除)]":"去除"))
						+"</div>"
					+ "</div>"
					+ (Main.game.isInNewWorld()
							?"<div style='float:left; width:98%; margin:0 1%; padding:0;'>"
									+ "<div class='normal-button"+(disabled || tattooInSlot==null?" disabled":"")+"' "+(!disabled && tattooInSlot!=null?"id='TATTOO_MODIFY_"+invSlot.toString()+"'":"")+" style='width:100%;'>自定义</div>"
								+ "</div>"
							:"")
					+ (Main.game.isInNewWorld()
						?"<div style='float:left; width:98%; margin:0 1%; padding:0;'>"
								+ "<div class='normal-button"+(disabled || tattooInSlot==null?" disabled":"")+"' "+(!disabled && tattooInSlot!=null?"id='TATTOO_ENCHANT_"+invSlot.toString()+"'":"")+" style='width:100%;'>附魔</div>"
							+ "</div>"
						:"")
				+ "</div>"
			+ "</div>";
	}
	
	public static InventorySlot tattooInventorySlot = null;
	public static Tattoo tattoo = null;
	public static boolean retroactiveApplicationPreferZeroStart = false;
	
	public static void resetTattooVariables(InventorySlot slot) {
		tattooInventorySlot = slot;
		
		retroactiveApplicationPreferZeroStart = false;
		
		tattoo = new Tattoo(
				"innoxia_symbol_tribal",
				PresetColour.CLOTHING_GREY,
				null,
				null,
				false,
				new TattooWriting(
						"",
						PresetColour.BASE_GREY,
						false),
				new TattooCounter(
						TattooCounterType.NONE,
						TattooCountType.NUMBERS,
						PresetColour.BASE_GREY,
						false,
						0));
	}
	
	public static void resetTattooColours() {
		if(!tattoo.getType().getAvailablePrimaryColours().contains(tattoo.getPrimaryColour())) {
			tattoo.setPrimaryColour(tattoo.getType().getAvailablePrimaryColours().get(0));
		}
		
		if(!tattoo.getType().getAvailableSecondaryColours().contains(tattoo.getSecondaryColour())) {
			if(tattoo.getType().getAvailableSecondaryColours().isEmpty()) {
				tattoo.setSecondaryColour(null);
			} else {
				tattoo.setSecondaryColour(tattoo.getType().getAvailableSecondaryColours().get(0));
			}
		}

		if(!tattoo.getType().getAvailableTertiaryColours().contains(tattoo.getTertiaryColour())) {
			if(tattoo.getType().getAvailableTertiaryColours().isEmpty()) {
				tattoo.setTertiaryColour(null);
			} else {
				tattoo.setTertiaryColour(tattoo.getType().getAvailableTertiaryColours().get(0));
			}
		}
		
		tattoo.setGlowing(false);
	}
	
	public static String getKatesDivTattoosAdd() {
		contentSB.setLength(0);
		
		// Type:
	
		contentSB.append("<div class='container-full-width'>");
			contentSB.append("<div class='container-full-width' style='width:75%; margin:0; position:relative; text-align:center;'>");
				contentSB.append("<h5 style='width:100%; text-align:center;'>选择类型</h5>");
		
				for(AbstractTattooType type : TattooType.getConditionalTattooTypes(BodyChanging.getTarget())) {
					if(type.getSlotAvailability().contains(tattooInventorySlot)) {
						contentSB.append("<div style='width:23%; margin:1%; padding:0; display:inline-block;'>"
											+ "<div class='normal-button"+(tattoo.getType()==type?" selected":"")+"' id='TATTOO_TYPE_"+type.getId()+"'"
													+ " style='width:100%; margin:0; color:"+(tattoo.getType()==type?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</div>"
										+ "</div>");
						
					} else {
						contentSB.append("<div style='width:23%; margin:1%; padding:0; display:inline-block;'>"
								+ "<div class='normal-button disabled' id='TATTOO_TYPE_"+type.getId()+"'"
										+ " style='width:100%; margin:0;'>"+Util.capitaliseSentence(type.getName())+"</div>"
							+ "</div>");
					}
				}
				contentSB.append("</div>"
						+ "<div class='container-full-width' style='width:25%; margin:0;'>");

//				background-color:"+Main.game.getPlayer().getCovering(Main.game.getPlayer().getTorsoCovering()).getPrimaryColour().toWebHexString()+";
				
				contentSB.append("<div class='modifier-icon' style='float:left; width:100%; margin:0; text-align:center;'>"
									+ "<div class='modifier-icon-content'>"+tattoo.getSVGImage(BodyChanging.getTarget())+"</div>"
									+ "<div class='overlay no-pointer' id='NEW_TATTOO_INFO'></div>"
								+ "</div>");
			
			contentSB.append("</div>");
		contentSB.append("</div>");
	
		// Colours:

		contentSB.append("<div class='container-full-width'>"
				+ "<h5 style='width:100%; text-align:center;'>选择颜色</h5>");
			
			// Primary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
				for (Colour c : tattoo.getType().getAvailablePrimaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getPrimaryColour()==c?" selected":"")+"' id='TATTOO_COLOUR_PRIMARY_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getPrimaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			contentSB.append("</div>");

			// Secondary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
			if(tattoo.getType().getAvailableSecondaryColours().isEmpty()) {
				contentSB.append(
						"<p style='text-align:center;'>[style.italicsDisabled(无可用的二级颜色……)]</p>");
				
			} else {
				for (Colour c : tattoo.getType().getAvailableSecondaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getSecondaryColour()==c?" selected":"")+"' id='TATTOO_COLOUR_SECONDARY_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getSecondaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("</div>");

			// Tertiary:
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
			if(tattoo.getType().getAvailableTertiaryColours().isEmpty()) {
				contentSB.append(
						"<p style='text-align:center;'>[style.italicsDisabled(无可用的三级颜色……)]</p>");
				
			} else {
				for (Colour c : tattoo.getType().getAvailableTertiaryColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getTertiaryColour()==c?" selected":"")+"' id='TATTOO_COLOUR_TERTIARY_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getTertiaryColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
			}
			contentSB.append("</div>");
			
			if(Main.game.isInNewWorld()) {
				contentSB.append("<div class='container-full-width'>");
					if(tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))) {
						contentSB.append(
								"<div class='normal-button disabled' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "光芒"
								+ "</div>");
						
					} else if(tattoo.isGlowing()) {
						contentSB.append(
								"<div class='normal-button active' id='TATTOO_GLOW' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "[style.boldArcane(奥术光芒)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_GLOW' class='normal-button' style='width:20%; margin:2% 40%; padding:0; text-align:center;'>"
									+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>奥术光芒</span>"
								+ "</div>");
					}
				contentSB.append("</div>");
			}

		contentSB.append("</div>");
	
		// Writing:

		contentSB.append("<div class='container-full-width'>"
				+ "<h5 style='width:100%; text-align:center;'>选择文字</h5>");
		
			contentSB.append("<div class='container-full-width' style='width:66.6%; margin:0; position:relative; text-align:center;'>"
					+"<form style='padding:0; margin:0 0 4px 0; text-align:center;'>"
						+ "<input type='text' id='tattoo_name' value='" +UtilText.parseForHTMLDisplay(tattoo.getWriting().getText())+"' style='padding:0;margin:0;width:80%;'>"// "+(tattoo.getWriting().getStyle()==TattooWritingStyle.NONE?"disabled":"")+">"
					+ "</form>"
					+ "<p style='width:100%; text-align:center;'>文字风格</p>");
			for(TattooWritingStyle style : TattooWritingStyle.values()) {
				contentSB.append("<div style='width:18%; margin:0 1%; padding:0; display:inline-block;'>"
									+ "<div class='normal-button"+(tattoo.getWriting().getStyles().contains(style)?" selected":"")+"' id='TATTOO_WRITING_STYLE_"+style.toString()+"'"
											+ " style='width:100%; margin:0; color:"+(tattoo.getWriting().getStyles().contains(style)?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"+Util.capitaliseSentence(style.getName())+"</div>"
								+ "</div>");
			}
			contentSB.append("<div class='container-full-width'>"
					+ "结果:"+tattoo.getFormattedWritingOutput()
					+ "</div>");
			contentSB.append("</div>");
			
			contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
				for (Colour c : TattooWriting.getAvailableColours()) {
					contentSB.append("<div class='normal-button"+(tattoo.getWriting().getColour()==c?" selected":"")+"' id='TATTOO_WRITING_COLOUR_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px;"+(tattoo.getWriting().getColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
										+ "<div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
									+ "</div>");
				}
				if(Main.game.isInNewWorld()) {
					contentSB.append("<br/>");
					if(tattoo.getWriting().isGlow()) {
						contentSB.append(
								"<div class='normal-button selected' id='TATTOO_WRITING_GLOW' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "[style.boldArcane(奥术光芒)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_WRITING_GLOW' class='normal-button' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>奥术光芒</span>"
								+ "</div>");
					}
				}
			contentSB.append("</div>");
		contentSB.append("</div>");

		// Counter:
		if(Main.game.isInNewWorld()) {
			contentSB.append("<div class='container-full-width'>");

			contentSB.append("<div class='container-full-width inner' style='margin:0; padding:0; width:100%; text-align:center; background:transparent;'>");
				contentSB.append("<h5 style='width:100%; text-align:center;'>选择计数指示</h5>");
			contentSB.append("</div>");
			
			contentSB.append(getInformationDiv(
					"TATTOO_COUNTER_INFO",
					new TooltipInformationEventListener().setInformation(
							"纹身计数指示",
							"已附魔的纹身计数指示能够在对应计数类型的数值增加时自动更新数字。"
								+ "绝大部分计数类型可选择从0开始重新计数，或是追溯过往的所有经历继续计数。"
								+ "然而，另外一部分只能显示当前的数值，这部分计数类型已经用星号*标记。"),
					false));
			
				contentSB.append("<div class='container-full-width' style='width:66.6%; margin:0;'>");
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center; margin-top:0; padding-top:0;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>计数类型</p>");
						for(TattooCounterType counterType : TattooCounterType.getTattooCounterTypesWithContentFiltersApplied()) {
							contentSB.append("<div style='width:48%; margin:1%; padding:0; display:inline-block;'>"
												+ "<div class='normal-button"+(tattoo.getCounter().getType()==counterType?" selected":"")+"' id='TATTOO_COUNTER_TYPE_"+counterType.toString()+"'"
														+ " style='width:100%; margin:0; color:"+(tattoo.getCounter().getType()==counterType?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"
													+Util.capitaliseSentence(counterType.getName())
													+ (counterType.isRetroactiveApplicationAvailable()?"":" *")
												+"</div>"
											+ "</div>");
						}
					contentSB.append("</div>");
				contentSB.append("</div>");
				
				contentSB.append("<div class='container-full-width' style='width:33.3%; margin:0;'>");
					for (Colour c : TattooCounter.getAvailableColours()) {
						contentSB.append("<div class='normal-button"+(tattoo.getCounter().getColour()==c?" selected":"")+"' id='TATTOO_COUNTER_COLOUR_"+c.getId()+"'"
												+ " style='width:auto; margin-right:4px;"+(tattoo.getCounter().getColour()==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
											+ "<div class='phone-item-colour' style='background-color:" + c.getCoveringIconColour() + ";"+(c==PresetColour.COVERING_NONE?" color:"+PresetColour.BASE_RED.toWebHexString()+";'>X":"'>")+"</div>"
										+ "</div>");
					}
					contentSB.append("<br/>");
					if(tattoo.getCounter().isGlow()) {
						contentSB.append(
								"<div class='normal-button selected' id='TATTOO_COUNTER_GLOW' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "[style.boldArcane(奥术光芒)]"
								+ "</div>");
					} else {
						contentSB.append(
								"<div id='TATTOO_COUNTER_GLOW' class='normal-button' style='width:50%; margin:2% 25%; padding:0; text-align:center;'>"
									+ "<span style='color:"+PresetColour.GENERIC_ARCANE.getShades()[0]+";'>奥术光芒</span>"
								+ "</div>");
					}
					
					boolean activeButtonZero = tattoo.getCounter().getType().getNonRetroactiveOffset(BodyChanging.getTarget())==0?retroactiveApplicationPreferZeroStart:tattoo.getCounter().isRetroactiveApplication();
					
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>初始计数</p>");

						if(tattoo.getCounter().getType().isRetroactiveApplicationAvailable()) {
							contentSB.append("<div style='width:98%; margin:1%; padding:0; display:inline-block;'>");
								contentSB.append("<div class='normal-button"+(activeButtonZero?" selected":"")+"' id='TATTOO_COUNT_RETROACTIVE_DISABLED'"
														+ " style='width:100%; margin:0; color:"+(activeButtonZero?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>");
									contentSB.append("从0开始");
								contentSB.append("</div>");
							contentSB.append("</div>");
							contentSB.append("<div style='width:98%; margin:1%; padding:0; display:inline-block;'>");
								contentSB.append("<div class='normal-button"+(!activeButtonZero?" selected":"")+"' id='TATTOO_COUNT_RETROACTIVE_ENABLED'"
														+ " style='width:100%; margin:0; color:"+(!activeButtonZero?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>");
									contentSB.append("追溯过往经历");
								contentSB.append("</div>");
							contentSB.append("</div>");
							
						} else {
							contentSB.append("<div class='container-full-width' style='margin:0; padding:0;'>");
								contentSB.append("[style.colourDisabled(<i>此计数类型总会显示当前数值，因此无法修改初始计数。</i>)]");
							contentSB.append("</div>");
						}
							
					contentSB.append("</div>");
					
					contentSB.append("<div class='container-full-width' style='position:relative; text-align:center;'>");
						contentSB.append("<p style='width:100%; text-align:center;'>计数标识风格</p>");
						for(TattooCountType countType : TattooCountType.values()) {
							contentSB.append("<div style='width:98%; margin:1%; padding:0; display:inline-block;'>"
												+ "<div class='normal-button"+(tattoo.getCounter().getCountType()==countType?" selected":"")+"' id='TATTOO_COUNT_TYPE_"+countType.toString()+"'"
														+ " style='width:100%; margin:0; color:"+(tattoo.getCounter().getCountType()==countType?PresetColour.GENERIC_GOOD:PresetColour.TEXT_HALF_GREY).toWebHexString()+";'>"
													+Util.capitaliseSentence(countType.getName())+"</div>"
											+ "</div>");
						}
					contentSB.append("</div>");
					
					contentSB.append("<div class='container-full-width'>");
						if(tattoo.getCounter().getType()==TattooCounterType.NONE) {
							contentSB.append("[style.colourDisabled(结果：<i>(此计数类型为“空”，该纹身将不会被添加计数指示。)</i>)]");
						} else {
							contentSB.append("结果："+tattoo.getFormattedCounterOutput(BodyChanging.getTarget()));
						}
					contentSB.append("</div>");
				contentSB.append("</div>");
				
			contentSB.append("</div>");
		}
		
		contentSB.append("<p id='hiddenPField' style='display:none;'></p>");
		
		return contentSB.toString();
	}
	
}
