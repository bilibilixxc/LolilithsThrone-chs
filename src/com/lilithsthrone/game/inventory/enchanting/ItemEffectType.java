package com.lilithsthrone.game.inventory.enchanting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
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
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.misc.GenericAndrogynousNPC;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.OffspringMapDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.7
 * @version 0.3.9
 * @author Innoxia
 */
public class ItemEffectType {
	
	public static AbstractItemEffectType TESTING = new AbstractItemEffectType(Util.newArrayListOfValues(
			"测试物品"),
		PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.incrementMuscle(-25)
					+ target.incrementBodySize(25)
					+ target.setUnderarmHair(BodyHair.SIX_BUSHY);
		}
	};
	
	public static AbstractItemEffectType DYE_BRUSH = new AbstractItemEffectType(Util.newArrayListOfValues(
				"重新为一件衣物上色。"),
			PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "你使用了染色刷。"
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType REFORGE_HAMMER = new AbstractItemEffectType(Util.newArrayListOfValues(
				"修改武器的伤害类型。"),
			PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p>"
					+ "你使用了重铸锤。"
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType USED_CONDOM_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"提供一份存储的精液。"),
			PresetColour.GENERIC_SEX) {
		@Override
		public boolean isBreakOutOfInventory() {
			return true;
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledCondom OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
		}
	};

	public static AbstractItemEffectType FILLED_MOO_MILKER_DRINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"奶香味的饮品。"),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return ""; // THIS EFFECT IS NOT USED, AS AbstractFilledBreastPump OVERRIDES THE USUAL AbstractItem's applyEffects() METHOD!!!
			// Then why is it here? :thinking:
		}
	};
	
	
	public static AbstractItemEffectType ORIENTATION_CHANGE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"将性取向设置为女性恋。",
			Attribute.MAJOR_CORRUPTION.getFormattedValue(50)),
			PresetColour.FEMININE_PLUS) {
		
		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return Util.newArrayListOfValues(
					TFModifier.REMOVAL,
					TFModifier.ORIENTATION_GYNEPHILIC,
					TFModifier.ORIENTATION_AMBIPHILIC,
					TFModifier.ORIENTATION_ANDROPHILIC,
					TFModifier.PERSONALITY_TRAIT_SPEECH_LISP,
					TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER,
					TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return Util.newArrayListOfValues(
					TFModifier.ARCANE_BOOST);
		}

		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP
					|| primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER
					|| primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
				return Util.newArrayListOfValues(
						TFPotency.MINOR_DRAIN,
						TFPotency.MINOR_BOOST);
			}
			return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			descriptions.clear();
			
			if(primaryModifier!=null && primaryModifier!=TFModifier.NONE) {
				if(primaryModifier==TFModifier.REMOVAL) {
					descriptions.add("没有效果。");
					
				} else if(primaryModifier==TFModifier.ORIENTATION_GYNEPHILIC) {
					descriptions.add("将性取向设置为[style.colourFeminineStrong(女性恋)]");
					descriptions.add(Attribute.MAJOR_CORRUPTION.getFormattedValue(5));
					
				} else if(primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC) {
					descriptions.add("将性取向设置为[style.colourAndrogynous(双性恋)]");
					descriptions.add(Attribute.MAJOR_CORRUPTION.getFormattedValue(5));
					
				} else if(primaryModifier==TFModifier.ORIENTATION_ANDROPHILIC) {
					descriptions.add("将性取向设置为[style.colourMasculineStrong(男性恋)]");
					descriptions.add(Attribute.MAJOR_CORRUPTION.getFormattedValue(5));
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP) {
					if(potency==TFPotency.MINOR_DRAIN) {
						descriptions.add("移除<b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_LISP.getColour().toWebHexString()+";'>口齿不清</b>");
					} else {
						descriptions.add("添加<b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_LISP.getColour().toWebHexString()+";'>口齿不清</b>");
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER) {
					if(potency==TFPotency.MINOR_DRAIN) {
						descriptions.add("移除<b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER.getColour().toWebHexString()+";'>口吃</b>");
					} else {
						descriptions.add("添加<b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER.getColour().toWebHexString()+";'>口吃</b>");
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
					if(potency==TFPotency.MINOR_DRAIN) {
						descriptions.add("移除<b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY.getColour().toWebHexString()+";'>发音模糊</b>");
					} else {
						descriptions.add("添加<b style='color:"+TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY.getColour().toWebHexString()+";'>发音模糊</b>");
					}
				}
				
			} else {
				descriptions.add("可附魔");
			}
			
			return descriptions;
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			
			if(primaryModifier!=null && primaryModifier!=TFModifier.NONE && primaryModifier!=TFModifier.REMOVAL) {
				target.incrementAttribute(Attribute.MAJOR_CORRUPTION, 5);
				
				if(target.isDoll()
						&& (primaryModifier==TFModifier.ORIENTATION_GYNEPHILIC
							|| primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC
							|| primaryModifier==TFModifier.ORIENTATION_ANDROPHILIC)) {
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ "[style.colourDisabled(作为性爱玩偶，[npc.name]永远只能是双性恋……)]"
								+ "</p>");
				}
				
				if(primaryModifier==TFModifier.ORIENTATION_GYNEPHILIC) {
					boolean alreadyGynephilic = target.getSexualOrientation()==SexualOrientation.GYNEPHILIC;
					target.setSexualOrientation(SexualOrientation.GYNEPHILIC);
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ (alreadyGynephilic
											?"[style.colourDisabled([npc.NameIsFull]已经是女性恋了，所以无事发生……)]"
											:"[npc.nameIsFull]现在是[style.colourFemininePlus(女性恋)]了！")
								+ "</p>");
					
				} else if(primaryModifier==TFModifier.ORIENTATION_AMBIPHILIC) {
					boolean alreadyAmbiphilic = target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
					target.setSexualOrientation(SexualOrientation.AMBIPHILIC);
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ (alreadyAmbiphilic
											?"[style.colourDisabled([npc.NameIsFull]已经是双性恋了，无事发生……)]"
											:"[npc.NameIsFull]现在是[style.colourAndrogynous(双性恋)]了！")
								+ "</p>");
					
					
				} else if(primaryModifier==TFModifier.ORIENTATION_ANDROPHILIC) {
					boolean alreadyAndrophilic = target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
					target.setSexualOrientation(SexualOrientation.ANDROPHILIC);
					return UtilText.parse(target,
							"<p style='text-align:center;'>"
									+ (alreadyAndrophilic
											?"[style.colourDisabled([npc.NameIsFull]已经是男性恋了，无事发生……)]"
											:"[npc.NameIsFull]现在是[style.colourMasculinePlus(男性恋)]了！")
								+ "</p>");
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_LISP) {
					if(potency==TFPotency.MINOR_DRAIN) {
						return target.removePersonalityTrait(PersonalityTrait.LISP);
					} else {
						return target.addPersonalityTrait(PersonalityTrait.LISP);
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_STUTTER) {
					if(potency==TFPotency.MINOR_DRAIN) {
						return target.removePersonalityTrait(PersonalityTrait.STUTTER);
					} else {
						return target.addPersonalityTrait(PersonalityTrait.STUTTER);
					}
					
				} else if(primaryModifier==TFModifier.PERSONALITY_TRAIT_SPEECH_SLOVENLY) {
					if(potency==TFPotency.MINOR_DRAIN) {
						return target.removePersonalityTrait(PersonalityTrait.SLOVENLY);
					} else {
						return target.addPersonalityTrait(PersonalityTrait.SLOVENLY);
					}
				}
				
			} 
			
			return "<p>"
						+ "无事发生，因为催眠怀表的附魔已经解除。"
						+ "你在使用前需要先进行附魔。"
					+ "</p>";
		}
	};
	
//	public static AbstractItemEffectType VIXENS_VIRILITY = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.FERTILITY.getFormattedValue(50)+" for 24 hours",
//			Attribute.VIRILITY.getFormattedValue(50)+" for 24 hours",
//			"[style.boldBad(Removes status effect:)]",
//			"<i>'"+StatusEffect.PROMISCUITY_PILL.getName(null)+"'</i>"),
//			PresetColour.GENERIC_SEX) {
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
//			target.addStatusEffect(StatusEffect.VIXENS_VIRILITY, 60*24*60);
//			return UtilText.parse(target,
//					"<p style='margin-bottom:0; padding-bottom:0;'>"
//						+ "The little purple pill easily slides down [npc.her] throat, and within moments [npc.she] [npc.verb(feel)] "
//						+ ( target.hasVagina()
//								? "a soothing, warm glow spreading out from [npc.her] ovaries into [npc.her] lower torso."
//									+ " [npc.Her] mind fogs over with an overwhelming desire to feel potent sperm spurting deep into [npc.her] "+(target.isVisiblyPregnant()?"pussy":"womb")
//									+", and before [npc.she] can stop it, a horny whimper escapes from between [npc.her] [npc.lips]."
//									+ (target.hasPenisIgnoreDildo()
//											?" At the same time, [npc.her] manhood begins to throb with need, and [npc.she] [npc.verb(feel)] "
//											:"") 
//								:"")
//						+ (target.hasPenisIgnoreDildo()
//								? "an overpowering desire to sink deep into a fertile female's cunt and fill her with [npc.cum+]."
//								: "")
//						+ (!target.hasPenisIgnoreDildo() && !target.hasVagina()
//								?"a desperate heat in [npc.her] genderless mound."
//								:"")
//					+"</p>"
//					+ "<p style='text-align:center; margin-top:0; padding-top:0;'>"
//						+ "[style.colourPink([npc.Name] [npc.is] now experiencing <i>'"+StatusEffect.VIXENS_VIRILITY.getName(target)+"'</i> for the next 24 hours!)]"
//					+ "</p>");
//		}
//	};
//	
//	public static AbstractItemEffectType PROMISCUITY_PILL = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.FERTILITY.getFormattedValue(-100)+" for 24 hours",
//			Attribute.VIRILITY.getFormattedValue(-100)+" for 24 hours",
//			"[style.boldBad(Removes status effect:)]",
//			"<i>'"+StatusEffect.VIXENS_VIRILITY.getName(null)+"'</i>"),
//			PresetColour.GENERIC_SEX) {
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.removeStatusEffect(StatusEffect.VIXENS_VIRILITY);
//			target.addStatusEffect(StatusEffect.PROMISCUITY_PILL, 60*24*60);
//			return UtilText.parse(target,
//					"<p>"
//						+ "The little blue pill easily slides down [npc.namePos] throat, and after only a few moments [npc.she] [npc.verb(feel)] a cool throbbing sensation taking root deep within [npc.her] loins."
//					+ "</p>"
//					+ "<p style='text-align:center; margin-top:0; padding-top:0;'>"
//						+ "[style.colourBlueLight([npc.Name] [npc.is] now experiencing <i>'"+StatusEffect.PROMISCUITY_PILL.getName(target)+"'</i> for the next 24 hours!)]"
//					+ "</p>");
//		}
//	};
	

	public static AbstractItemEffectType MOO_MILKER = new AbstractItemEffectType(Util.newArrayListOfValues(
			"为胸部挤乳。"),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			int milkPumped = (int) Math.min(target.getBreastRawStoredMilkValue(), ItemType.getMooMilkerMaxMilk());
			target.incrementBreastStoredMilk(-milkPumped);
			if(target.isPlayer()) {
				return "<p>"
							+ "只花了一点时间，杯子里就装满了"+ Units.fluid(milkPumped, Units.UnitType.LONG)+" 你的[pc.milk]。"
						+ "</p>"
						+ user.addItem(Main.game.getItemGen().generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourShades().get(0), target, target.getMilk(), milkPumped), false, true);
			
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "只花了一点时间，杯子里就装满了"+Units.fluid(milkPumped, Units.UnitType.LONG)+"[npc.her]的[npc.milk]。"
						+ "</p>"
						+ user.addItem(Main.game.getItemGen().generateFilledBreastPump(ItemType.MOO_MILKER_EMPTY.getColourShades().get(0), target, target.getMilk(), milkPumped), false, true));
			}
		}
	};
	

	public static AbstractItemEffectType PREGNANCY_TEST = new AbstractItemEffectType(Util.newArrayListOfValues(
			"显示妊娠信息。"),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.performImpregnationCheck(false);
			if(target.isPregnant()) {
				List <GameCharacter> fathers = new ArrayList<>();
				for(PregnancyPossibility pp : target.getPotentialPartnersAsMother()) {
					if(pp.getFather()!=null) {
						fathers.add(pp.getFather());
					}
				}
				for(GameCharacter father : fathers) {
					father.getPotentialPartnersAsFather().removeIf((pp) -> pp.getMotherId().equals(target.getId()) && target.getPregnantLitter().getFather() != (father));
				}
				
				target.getPotentialPartnersAsMother().removeIf((pp) -> !pp.getFatherId().equals(target.getPregnantLitter().getFatherId()));
				
				GameCharacter father = target.getPregnantLitter().getFather();
				String unknownFatherName = "未知！";
				if(father==null) {
					try {
						OffspringSeed offspring0 = target.getPregnantLitter().getOffspringSeed().iterator().next();
						if(!offspring0.getFatherName().equals("???")) {
							unknownFatherName = offspring0.getFatherName();
						}
					} catch(Exception ex) {
					}
				}
				
				return "<p style='text-align:center;'>"
						+ "电子屏幕上显示出两条平行红线，旁边闪烁着粉色文字："
						+ "<br/><b>“[style.italicsArcane(怀孕！)]”</b>"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "除了瞬间确认了怀孕，还提供了一些额外信息:<br/>"
						+ "<i>"
						+ "父亲："+(father!=null
										?father.getNameIgnoresPlayerKnowledge()+" ("+Util.capitaliseSentence(target.getPregnantLitter().getFatherRace().getName(father.getBody()))+")"
										:unknownFatherName+" ("+Util.capitaliseSentence(target.getPregnantLitter().getFatherRace().getName(Main.game.getNpc(GenericAndrogynousNPC.class).getBody()))+")")+"<br/>"
						+ "产仔数量：" +target.getPregnantLitter().getTotalLitterCount()+"<br/>"
						+ "[style.colourFeminine(女儿)]：" +(target.getPregnantLitter().getDaughtersFromFather()+target.getPregnantLitter().getDaughtersFromMother())+"<br/>"
						+ "[style.colourMasculine(儿子)]：" +(target.getPregnantLitter().getSonsFromFather()+target.getPregnantLitter().getSonsFromMother())+"<br/>"
						+ "</i>"
					+ "</p>";
				
			} else {
				return "<p style='text-align:center;'>"
					+ "子屏幕上显示出一条红线，旁边写着黑色的文字："
					+ "<br/><b>“<i>未怀孕。</i>”</b>"
				+ "</p>";
			}
		}
	};
	
	
	public static AbstractItemEffectType MOTHERS_MILK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"加速孕期和孵化。"),
			PresetColour.GENERIC_SEX) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			boolean effectsObserved = false;
			
			sb.append("<p>");
			sb.append("[npc.Name]急切地拿起这醇厚的液体大口喝了起来，醇香的味道让[npc.herHim]很快就将整瓶一饮而尽。");

			Map<SexAreaOrifice, List<AbstractStatusEffect>> incubationEffectMap = Util.newHashMapOfValues(
					new Value<>(SexAreaOrifice.VAGINA, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_WOMB_1, StatusEffect.INCUBATING_EGGS_WOMB_2, StatusEffect.INCUBATING_EGGS_WOMB_3)),
					new Value<>(SexAreaOrifice.ANUS, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_STOMACH_1, StatusEffect.INCUBATING_EGGS_STOMACH_2, StatusEffect.INCUBATING_EGGS_STOMACH_3)),
					new Value<>(SexAreaOrifice.NIPPLE, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_1, StatusEffect.INCUBATING_EGGS_NIPPLES_2, StatusEffect.INCUBATING_EGGS_NIPPLES_3)),
					new Value<>(SexAreaOrifice.NIPPLE_CROTCH, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_1, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_2, StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)),
					new Value<>(SexAreaOrifice.SPINNERET, Util.newArrayListOfValues(StatusEffect.INCUBATING_EGGS_SPINNERET_1, StatusEffect.INCUBATING_EGGS_SPINNERET_2, StatusEffect.INCUBATING_EGGS_SPINNERET_3)));
			
			for(Entry<SexAreaOrifice, List<AbstractStatusEffect>> entry : incubationEffectMap.entrySet()) {
				Litter litter = target.getIncubationLitter(entry.getKey());
				if(litter!=null) {
					boolean advanced = false;
					if(target.hasStatusEffect(entry.getValue().get(0))) {
						target.removeStatusEffect(entry.getValue().get(0));
						advanced = true;
					} else if(target.hasStatusEffect(entry.getValue().get(1))){
						target.removeStatusEffect(entry.getValue().get(1));
						advanced = true;
					}
					if(advanced) {
						effectsObserved = true;
						int eggs = litter.getTotalLitterCount();
						String areaName = entry.getKey().getName(target, true);
						if(entry.getKey()==SexAreaOrifice.VAGINA) {
							areaName = "子宫";
						} else if(entry.getKey()==SexAreaOrifice.ANUS) {
							areaName = "肚子";
						}
						sb.append("</br>");
						if(eggs>1) {
							sb.append("在[npc.namePos]的<b>"+areaName+"</b>中孵化的卵迅速成熟，变得格外沉重，[npc.herHim]意识到");
						} else {
							sb.append("在[npc.namePos]的<b>"+areaName+"</b>中孵化的卵迅速成熟，变得格外沉重，[npc.herHim]意识到");
						}
						if(target.hasStatusEffect(entry.getValue().get(1))) {
							sb.append("[style.colourYellowLight(孵化进入了下一阶段)]！");
						} else {
							sb.append("已经[style.colourYellowLight(准备好产卵了)]！");
						}
					}
				}
			}
			
			if(target.isVisiblyPregnant()) {
				if(!target.hasStatusEffect(StatusEffect.PREGNANT_3)) {
					effectsObserved = true;
					if(target.hasStatusEffect(StatusEffect.PREGNANT_1)) {
						target.removeStatusEffect(StatusEffect.PREGNANT_1);
						
					} else if(target.hasStatusEffect(StatusEffect.PREGNANT_2)) {
						target.removeStatusEffect(StatusEffect.PREGNANT_2);
					}
					sb.append("</br>");
					sb.append("顿时，[npc.her]的小腹飞快地鼓起，一股能量流淌过身体，[npc.she]不禁发出一声深沉的[npc.moan]。"
								+ "没过多一会儿，药效便结束了，[npc.name]露出了欣慰的微笑，"
									+ "孕肚长大也就说明[npc.herHim]进入了孕期的下一阶段……");
				}
				
			} else if(target.hasStatusEffect(StatusEffect.PREGNANT_0)) {
				effectsObserved = true;
				target.removeStatusEffect(StatusEffect.PREGNANT_0);
				
				if(target.isPregnant()) {
					sb.append("</br>");
					sb.append("一股抚慰的暖流迅速在[npc.her]的下腹部流淌，[npc.she]不受控制地喘息起来，"
									+ "[npc.her]的小腹瞬间鼓起，完全已经是[style.boldMinorGood(孕肚的样子)]！");
					
				} else {
					sb.append("</br>");
					sb.append("尽管一股舒缓的温暖弥漫在[npc.her]的小腹，但[npc.her]的腹部没有任何怀孕的迹象。"
								+ "最终看来[npc.sheIs][style.boldMinorBad(没有怀孕)]……");
				}
			}
			
			if(!effectsObserved) {
				sb.append("除了为[npc.her]解渴以外，这种液体对[npc.Name]没有任何效果……");
			}
			
			sb.append("</p>");
			
			return UtilText.parse(target, sb.toString());
		}
	};
	
	public static AbstractItemEffectType REJUVENATION_POTION = new AbstractItemEffectType(
			null,
			PresetColour.BASE_PURPLE) {
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effects = new ArrayList<>();
			
			effects.add("[style.colourExcellent(立即恢复)][style.colourSex(被撑开的腔穴)]");
			
			if(Main.game.isLactationContentEnabled()) {
				effects.add("[style.colourGood(重新充满)][style.colourMilk(乳汁储量)]");
			}
			if(Main.game.isCumRegenerationEnabled()) {
				effects.add("[style.colourGood(重新充满)][style.colourCum(精液储量)]");
			}
			
			return effects;
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			List<String> areasTightened = new ArrayList<>();
			
			if (target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				areasTightened.add("pussy");
				target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				areasTightened.add("asshole");
				target.setAssStretchedCapacity(target.getAssRawCapacityValue());
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				areasTightened.add("nipples");
				target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
			}
			if (target.hasBreastsCrotch()
					&& target.getNippleCrotchRawCapacityValue()!=target.getNippleCrotchStretchedCapacity()){
				areasTightened.add("crotch-nipples");
				target.setNippleCrotchStretchedCapacity(target.getNippleCrotchRawCapacityValue());
			}
			if (target.hasPenis() && target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				areasTightened.add("penile urethra");
				target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
			}
			if (target.hasVagina() && target.getVaginaUrethraRawCapacityValue()!=target.getVaginaUrethraStretchedCapacity()){
				areasTightened.add("vaginal urethra");
				target.setVaginaUrethraStretchedCapacity(target.getVaginaUrethraRawCapacityValue());
			}
			
			if(!areasTightened.isEmpty()) {
				sb.append(UtilText.parse(target,
							"<br/>[npc.Her]被撑开的[style.colourSex("+Util.stringsToStringList(areasTightened, false)+")]"
							+ (areasTightened.size()>1
								?"[style.colourGood(重新收紧了)]！"
								:"[style.colourGood(重新收紧了)]！")));
			}
			
			if(Main.game.isLactationContentEnabled()) {
				if(target.getBreastRawMilkStorageValue()>0 && target.getBreastRawStoredMilkValue()<target.getBreastRawMilkStorageValue()) {
					target.setBreastStoredMilk(target.getBreastRawMilkStorageValue());
					sb.append(UtilText.parse(target, "<br/>[npc.NamePos][npc.breasts+][style.colourGood(充满了)][npc.milk]！"));
				}
				if(target.hasBreastsCrotch() && target.getBreastCrotchRawMilkStorageValue()>0 && target.getBreastCrotchRawStoredMilkValue()<target.getBreastCrotchRawMilkStorageValue()) {
					target.setBreastCrotchStoredMilk(target.getBreastCrotchRawMilkStorageValue());
					sb.append(UtilText.parse(target, "<br/>[npc.NamePos][npc.crotchBoobs+][style.colourGood(充满了)][npc.crotchMilk]！"));
				}
			}

			if(Main.game.isCumRegenerationEnabled()) {
				if(target.hasPenis() && target.getPenisRawCumStorageValue()>0 && target.getPenisRawStoredCumValue()<target.getPenisRawCumStorageValue()) {
					target.setPenisStoredCum(target.getPenisRawCumStorageValue());
					sb.append(UtilText.parse(target, "<br/>[npc.NamePos][npc.balls+][style.colourGood(充满了)][npc.cum]！"));
				}
			}
			
			if(sb.length()==0) {
				sb.append(UtilText.parse(target,
							"<br/>除了感觉到很放松，并没有什么别的事情发生……"));
			}
			
			return "<p style='text-align:center;'>"
						+"[npc.Name]感受到一阵凉爽抚慰的感觉冲刷身体而过，发出一声长叹。"
						+ "<i>"
							+ sb.toString()
						+"</i>"
					+ "</p>";
		}
	};
	
	public static AbstractItemEffectType CIGARETTE_PACK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"提供20支斯塔尔香烟。"),
			PresetColour.BASE_PURPLE) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.addItem(Main.game.getItemGen().generateItem(ItemType.CIGARETTE), 20, false, target.isPlayer());
		}
	};
	
	public static AbstractItemEffectType CIGARETTE = new AbstractItemEffectType(null,
			PresetColour.BASE_PURPLE) {
		@Override
		public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
			return Util.newHashMapOfValues(new Value<>(StatusEffect.SMOKING, 60*5));
		}
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
			return list;
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return UtilText.parse(target,
					"<p>"
						+ "[npc.Name]立刻感觉到了添加在斯塔尔香烟中的灵气增幅补剂的增益作用。"
						+ "当[npc.her]将烟雾吸入肺中时，无论如何，还是感觉这有些不太健康……"
					+ "</p>");
		}
	};

	public static AbstractItemEffectType MAKEUP_SET = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourPink(打开化妆界面)]",
			"[style.colourSex(性交后自动重涂浓重的口红)]"),
			PresetColour.BASE_PURPLE) {
		@Override
		public boolean isBreakOutOfInventory() {
			return true;
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			BodyChanging.setTarget(target);
			Main.game.setContent(new Response(
					"",
					"",
					MiscDialogue.getMakeupDialogue(false,
							(BodyChanging.getTarget().isPlayer()
								?"你打开了奥术化妆套装，准备开始化妆……"
								:"你打开了奥术化妆套装，准备开始给[npc.name]化妆……"))));
			return "";
		}
	};

	public static AbstractItemEffectType DOLL_CONSOLE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourPink(打开玩偶定制界面)]"),
			PresetColour.BASE_PURPLE) {
		@Override
		public boolean isBreakOutOfInventory() {
			return true;
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			BodyChanging.setTarget(target);
			Main.game.setContent(new Response(
					"",
					"",
					BodyChanging.BODY_CHANGING_CORE
					));
			return "";
		}
	};
	
	// Ingredients and potions:
	
	public static AbstractItemEffectType MYSTERY_KINK = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.italicsSex(+50)][style.italicsLust(性欲)]",
			"在性爱中[style.italicsSex(+50)][style.italicsArousal(快感)]"
			//"[style.colourFetish(Random fetish addition or removal)]"
			),
			PresetColour.FETISH) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();
			sb.append(target.incrementLust(50, false));
			if(Main.game.isInSex()) {
				target.incrementArousal(50);
				sb.append("<p style='text-align:center;'>"
							+ UtilText.parse(target, "[npc.Name][style.colourSex(获得了)][style.boldSex(50快感)]！")
						+ "</p>");
			}
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType ADDICTION_REMOVAL = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourMinorGood(移除)][style.colourExcellent(所有)][style.colourBad(成瘾状态)]",
			"[style.colourMinorGood(移除)][style.colourAlcohol(醉酒程度)]",
			"[style.colourMinorGood(移除)][style.colourPsychoactive(致幻状态)]"),
			PresetColour.BASE_GOLD) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			boolean hadAddictions = !target.getAddictions().isEmpty();
			target.clearAddictions();
			
			boolean drunk = target.getIntoxicationPercentage()>0;
			target.setAlcoholLevel(0);
			
			boolean psychoactive = target.removeStatusEffect(StatusEffect.PSYCHOACTIVE);
			
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p style='text-align:center;'>");
				sb.append("[npc.Name]感觉到一股幸福的宁静感涌上心头……");
				if(hadAddictions) {
					sb.append("<br/><i>[npc.SheIsFull]不再对任何物质上瘾！</i>");
				}
				if(drunk) {
					sb.append("<br/><i>仍在[npc.her]体内的酒精瞬间被代谢了！</i>");
				}
				if(psychoactive) {
					sb.append("<br/><i>[npc.she]正在经历的幻觉结束了！</i>");
				}
				if(!hadAddictions && !drunk && !psychoactive) {
					sb.append("<br/>[style.italicsDisabled(除了感到一股愉悦感，无事发生……)]");
				}
			sb.append("</p>");
			
			return UtilText.parse(target, sb.toString());
		}
	};
	
	public static AbstractItemEffectType MUSHROOMS = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourTfGeneric(令粘液和腔穴内部发光)]"),
			PresetColour.ATTRIBUTE_CORRUPTION) {
		@Override
		public Map<AbstractStatusEffect, Integer> getAppliedStatusEffects() {
			return Util.newHashMapOfValues(new Value<>(StatusEffect.PSYCHOACTIVE, 6*60*60));
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();

			if(target.isDoll()) {
				return "<p style='text-align:center;'>"
							+ UtilText.parse(target, "[style.colourDisabled(由于[npc.nameIsFull]是个性爱玩偶，[npc.sheIs]完全不会被蘑菇影响……)]")
						+"</p>";
			}
			
			sb.append("<p style='text-align:center;'>");
				if(target.getBodyMaterial()==BodyMaterial.SLIME) {
					if(target.isPlayer()) {
						sb.append("你粘液构成的躯体开始[style.boldTfGeneric(发光了)]！");
					} else {
						sb.append(UtilText.parse(target, "[npc.NamePos]粘液构成的躯体开始[style.boldTfGeneric(发光了)]！"));
					}
					
					for(AbstractBodyCoveringType bct : BodyCoveringType.getAllSlimeTypes()) {
						target.getCovering(bct).setPrimaryGlowing(true);
						target.getCovering(bct).setSecondaryGlowing(true);
					}
					sb.append("<br/>");
				}
				if(target.isPlayer()) {
					sb.append("你所有腔穴的内部开始[style.boldTfGeneric(发光了)]！");
				} else {
					sb.append(UtilText.parse(target, "[npc.namePos]所有腔穴的内部开始[style.boldTfGeneric(发光了)]！"));
				}

				target.getCovering(BodyCoveringType.MOUTH).setSecondaryGlowing(true);
				target.getCovering(BodyCoveringType.ANUS).setSecondaryGlowing(true);
				target.getCovering(BodyCoveringType.VAGINA).setSecondaryGlowing(true);
				target.getCovering(BodyCoveringType.PENIS).setSecondaryGlowing(true);
				target.getCovering(BodyCoveringType.NIPPLES).setSecondaryGlowing(true);
				
			sb.append("</p>");

			sb.append("<p>");
				if(target.isPlayer()) {
					sb.append("五颜六色的星点在你的视线中若隐若现，你觉得头晕目眩，产生了[style.boldPsychoactive(幻觉)]！");
				} else {
					sb.append(UtilText.parse(target, "无颜六色的星点在[npc.namePos]的视线中若隐若现，[npc.she]觉得头晕目眩，产生了[style.boldPsychoactive(幻觉)]！"));
				}
			sb.append("</p>");
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType EGGPLANT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourGood(回复)]5%[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]",
			"[style.colourGood(回复)]5%[style.colourAura(灵气)]"),
			PresetColour.ATTRIBUTE_CORRUPTION) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/20);
			target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)/20);
			
			if(target.isPlayer()) {
				return "<p style='text-align:center;'>"
							+"挺美味的。"
						+"</p>";
			} else {
				return "";
			}
		}
	};
	
	public static AbstractItemEffectType EGGPLANT_POTION = new AbstractItemEffectType(null,
			PresetColour.BASE_PURPLE) {

		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return Util.newArrayListOfValues(TFModifier.TF_PENIS);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return getRacialSecondaryModifiers(Race.HUMAN, primaryModifier);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getRacialPotencyModifiers(Race.HUMAN, primaryModifier, secondaryModifier);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return Util.newArrayListOfValues(getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).getDescription());
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return getRacialEffect(Race.HUMAN, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
		}
	};
	
	public static AbstractItemEffectType ADDICTION_REMOVAL_REFINEMENT = new AbstractItemEffectType(null,
			PresetColour.BASE_BLUE_LIGHT) {

		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return Util.newArrayListOfValues(
					TFModifier.CORRUPTION,
					TFModifier.TF_MOD_HYMEN);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier==TFModifier.CORRUPTION) {
				return Util.newArrayListOfValues(
						TFPotency.MINOR_BOOST,
						TFPotency.BOOST,
						TFPotency.MAJOR_BOOST);
			} else {
				return Util.newArrayListOfValues(
						TFPotency.MINOR_DRAIN,
						TFPotency.MINOR_BOOST);
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			if(primaryModifier==TFModifier.CORRUPTION) {
				switch(potency) {
					case MINOR_BOOST:
						return Util.newArrayListOfValues("[style.colourMinorGood(-5)][style.colourCorruption(堕落)]");
					case BOOST:
						return Util.newArrayListOfValues("[style.colourGood(-10)][style.colourCorruption(堕落)]");
					case MAJOR_BOOST:
						return Util.newArrayListOfValues("[style.colourExcellent(-15)][style.colourCorruption(堕落)]");
					case MINOR_DRAIN:
					case DRAIN:
					case MAJOR_DRAIN:
					case SPECIAL:
						break;
				}
				return Util.newArrayListOfValues("");
				
			} else {
				if(potency.isNegative()) {
					return Util.newArrayListOfValues("[style.colourMinorBad(移除)][style.colourSex(处女膜)]");
				} else {
					return Util.newArrayListOfValues("[style.colourMinorGood(恢复)][style.colourSex(处女膜)]");
				}
			}
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(primaryModifier==TFModifier.CORRUPTION) {
				String purifyingDescription =
						"<p style='text-align:center;'>"
								+ "乍一看，似乎什么都没有发生，"
								+ "但[npc.name]咽下口中最后几滴液体后，瞬间一阵净化的能量便汹涌而来。"
							+ "随着一股股淡蓝色的微弱闪光在[npc.her]身体各处微微浮现，能量最终汇集到了头部，"
								+ "药水瞬间就开始生效，净化了[npc.her]的思想并且平复了性欲……"
						+"</p>";
				switch(potency) {
					case MINOR_BOOST:
						return purifyingDescription
								+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -5);
					case BOOST:
						return purifyingDescription
							+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -10);
					case MAJOR_BOOST:
						return purifyingDescription
							+ target.incrementAttribute(Attribute.MAJOR_CORRUPTION, -15);
					case MINOR_DRAIN:
					case DRAIN:
					case MAJOR_DRAIN:
					case SPECIAL:
						break;
				}
				return "";
				
			} else {
				if(potency.isNegative()) {
					return target.setHymen(false);
				} else {
					return target.setHymen(true);
				}
			}
		}
	};
	
	public static AbstractItemEffectType GIFT_CHOCOLATES = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourGood(恢复)]30%[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]"),
			PresetColour.ATTRIBUTE_HEALTH) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.incrementHealth((target.getAttributeValue(Attribute.HEALTH_MAXIMUM)/100)*30);
			
			if(target.isPlayer()) {
				return "<p style='text-align:center;'>"
						+"特别美味，你只用了一会儿就吃完了一整盒。"
						+"</p>";
			} else {
				return "";
			}
		}
	};
	
	public static AbstractItemEffectType GIFT_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
			Attribute.DAMAGE_LUST.getFormattedValue(5)+"到“药剂效果”"),
			PresetColour.ATTRIBUTE_LUST) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "<p style='text-align:center;'>"
					+(target.isPlayer()
						?"你现在闻上去味道好多了……"
						:UtilText.parse(target, "[npc.Name]闻上去味道好多了……"))
					+ "<br/>"
					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 5)
					+"</p>";
		}
	};
	
	
	public static AbstractItemEffectType PRESENT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"内含随机物品。"),
			PresetColour.GENERIC_EXCELLENT) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			List<AbstractItemType> items = new ArrayList<>();
			items.add(ItemType.getItemTypeFromId("innoxia_race_reindeer_rudolphs_egg_nog"));
			items.add(ItemType.getItemTypeFromId("innoxia_race_none_mince_pie"));
			items.add(ItemType.getItemTypeFromId("innoxia_race_reindeer_sugar_cookie"));
			
			Map<AbstractClothingType, Integer> clothingMap = new HashMap<>();
			// Common clothing (55%):
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_head_antler_headband"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_elemental_snowflake_necklace"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_elemental_piercing_ear_snowflakes"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_elemental_piercing_nose_snowflake"), 11);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_torsoOver_christmas_jumper"), 11);
			
			// Uncommon clothing (44%):
			clothingMap.put(ClothingType.JOLNIR_BOOTS, 4);
			clothingMap.put(ClothingType.JOLNIR_BOOTS_FEMININE, 4);
			clothingMap.put(ClothingType.JOLNIR_COAT, 4);
			clothingMap.put(ClothingType.JOLNIR_DRESS, 4);
			clothingMap.put(ClothingType.JOLNIR_HAT, 4);
			
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_kimono"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_geta"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_kanzashi"), 4);

			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_mens_kimono"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_mens_geta"), 4);
			clothingMap.put(ClothingType.getClothingTypeFromId("innoxia_japanese_haori"), 4);
			
			// 50% chance for consumable, 50% for clothing:
			if(Math.random()<0.5f) {
				AbstractItemType itemType = items.get(Util.random.nextInt(items.size()));
				
				return "<p>"
							+ "其中的礼物是:<b>"+itemType.getDisplayName(true)+"</b>！"
						+ "</p>"
						+ user.addItem(Main.game.getItemGen().generateItem(itemType), false);
				
			} else {
				AbstractClothingType clothingType = Util.getRandomObjectFromWeightedMap(clothingMap);
				AbstractClothing clothing = Main.game.getItemGen().generateClothing(clothingType, false);
				
				if(!Main.game.getPlayerCell().getInventory().isInventoryFull()) {
					Main.game.getPlayerCell().getInventory().addClothing(clothing);
					return "<p>"
								+ "其中的礼物是:<b>"+clothing.getDisplayName(true)+"</b>！"
							+ "</p>"
							+ user.addClothing(clothing, true);
					
				} else {
					return "<p>"
								+ "其中的礼物是:<b>"+clothing.getDisplayName(true)+"</b>！"
							+ "</p>"
							+ user.addClothing(clothing, false);
				}
			}
		}
	};
	
	// Racial:
	
	public static AbstractItemEffectType RACE_INNOXIAS_GIFT = new AbstractItemEffectType(Util.newArrayListOfValues(
			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+"到“药剂效果”",
			Attribute.MAJOR_ARCANE.getFormattedValue(2)+"到“药剂效果”",
			Attribute.MAJOR_CORRUPTION.getFormattedValue(5)+"到“药剂效果”"),
			PresetColour.RACE_HUMAN) {
		
		@Override
		public String getPotionDescriptor() {
			return "恶魔";
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "你感觉到这个物品只是用来作为测试用途的……"
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2)
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2)
					+ "<br/>"
					+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 5);
		}
	};
	
	/**
	 * This is just to provide a hard-coded hook to the biojuice canister's effects, for use in NPC.generateTransformativePotion()
	 */
	public static AbstractItemEffectType RACE_SLIME_TF_UTIL_EFFECT = new AbstractItemEffectType(Util.newArrayListOfValues(
			"将使用者转化为一个史莱姆！"),
			PresetColour.RACE_HUMAN) {
		@Override
		public String getPotionDescriptor() {
			return "史莱姆";
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return Main.game.getItemGen().generateItem(ItemType.getItemTypeFromId("innoxia_race_slime_biojuice_canister")).applyEffect(user, target);
		}
	};
	
//	public static AbstractItemEffectType RACE_BREAD_ROLL = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(1)+" to 'potion effects'"),
//			PresetColour.RACE_HUMAN) {
//		@Override
//		public String getPotionDescriptor() {
//			return "human";
//		}
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot healthier...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 1);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_CANINE_CRUNCH = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_DOG_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "canine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot more energetic...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_FOX_PIE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_FOX_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "vulpine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot more energetic...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_BURGER = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_RAT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "rat";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot more energetic...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_CARROT_CAKE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.FERTILITY.getFormattedValue(15)+" to 'potion effects'",
//			Attribute.VIRILITY.getFormattedValue(15)+" to 'potion effects'",
//			Attribute.RESISTANCE_LUST.getFormattedValue(-25)+" to 'potion effects'"),
//			PresetColour.RACE_RABBIT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "lagomorphic";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel like [npc.she] needs to breed...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.FERTILITY, 15)
//					+ target.addPotionEffect(Attribute.VIRILITY, 15)
//					+ target.addPotionEffect(Attribute.RESISTANCE_LUST, -25);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_KITTYS_REWARD = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_ARCANE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_CAT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "feline";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel [npc.her] arcane power increasing...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 3);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_ROUND_NUTS = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_ARCANE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_SQUIRREL_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "sciuridine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel [npc.her] arcane power increasing...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_FRUIT_SALAD = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_ARCANE.getFormattedValue(2)+" to 'potion effects'"),
//			PresetColour.RACE_BAT_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "chiropterine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel [npc.her] arcane power increasing...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_ARCANE, 2);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_SUGAR_CARROT_CUBE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_HORSE_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "equine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_SUGAR_COOKIE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_REINDEER_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "reindeer";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
//	
//	public static AbstractItemEffectType RACE_ALLIGATORS_GUMBO = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_ALLIGATOR_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "alligator";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
//	
//	public static AbstractItemEffectType RACE_BUBBLE_CREAM = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(3)+" to 'potion effects'"),
//			PresetColour.RACE_COW_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "bovine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 3);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_MEAT_AND_MARROW = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_PHYSIQUE.getFormattedValue(5)+" to 'potion effects'"),
//			PresetColour.RACE_WOLF_MORPH) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "lupine";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel a lot stronger...")
//					+ "</p>"
//					+ target.addPotionEffect(Attribute.MAJOR_PHYSIQUE, 5);
//		}
//	};
	
//	public static AbstractItemEffectType RACE_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.DAMAGE_LUST.getFormattedValue(10)+" to 'potion effects'",
//			"[style.boldSex(+3)] [style.boldFeminine(femininity)]"),
//			PresetColour.RACE_HARPY) {
//		@Override
//		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
//			List<String> list = super.getEffectsDescription(primaryModifier, secondaryModifier, potency, limit, user, target);
//			AbstractStatusEffect se = StatusEffect.LOLLIPOP_SUCKING;
//			list.add("Applies <i style='color:"+se.getColour().toWebHexString()+";'>'"+Util.capitaliseSentence(se.getName(target))+"'</i>:");
//			for(Entry<AbstractAttribute, Float> entry : se.getAttributeModifiers(target).entrySet()) {
//				list.add("<i>"+entry.getKey().getFormattedValue(entry.getValue())+"</i>");
//			}
//			return list;
//		}
//		@Override
//		public String getPotionDescriptor() {
//			return "harpy";
//		}
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			target.addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
//			return "<p style='text-align:center;'>"
//						+ UtilText.parse(target, "[npc.Name] [npc.verb(start)] to feel more feminine...")
//					+ "</p>"
//					+ target.incrementFemininity(3)
//					+ "<br/>"
//					+ target.addPotionEffect(Attribute.DAMAGE_LUST, 10);
//		}
//	};
//	
//	public static AbstractItemEffectType RACE_BIOJUICE = new AbstractItemEffectType(Util.newArrayListOfValues(
//			Attribute.MAJOR_CORRUPTION.getFormattedValue(25),
//			Attribute.MAJOR_CORRUPTION.getFormattedValue(50)+" to 'potion effects'",
//			"[style.boldSlime(Transforms body into slime!)]"),
//			PresetColour.RACE_SLIME) {
//
//		@Override
//		public String getPotionDescriptor() {
//			return "slime";
//		}
//		
//		@Override
//		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			if(target.getBody().getBodyMaterial()==BodyMaterial.SLIME) {
//				return "<p style='text-align:center;'>"
//							+ UtilText.parse(target, "[style.colourDisabled([npc.NameIsFull] already a slime, so nothing happens...)]")
//						+ "</p>";
//				
//			} else {
//				return target.incrementAttribute(Attribute.MAJOR_CORRUPTION, 25)
//						+ "<br/>"
//						+ target.addPotionEffect(Attribute.MAJOR_CORRUPTION, 50)
//						+ "<br/>"
//						+ target.setBodyMaterial(BodyMaterial.SLIME);
//			}
//		}
//	};
	
	// Essences:
	
	public static AbstractItemEffectType BOTTLED_ESSENCE_ARCANE = new AbstractItemEffectType(Util.newArrayListOfValues(
			"[style.colourGood(+1)][style.colourArcane(奥术精华)]"),
			PresetColour.GENERIC_ARCANE) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return target.incrementEssenceCount(1, false);
		}
	};
	

	// Specials:
	
	public static AbstractItemEffectType BIMBO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>无脑大胸</b><b style='color:"+PresetColour.RACE_HARPY.toWebHexString()+";'>哈比</b><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>转化</b>",
			"施加[style.colourSex(“舔棒棒糖”)]状态效果"),
			PresetColour.RACE_HARPY) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
			
			if(target.isDoll()) {
				return "<p>"
							+ UtilText.parse(target, "由于[npc.sheIsFull]是个性爱玩偶，棒棒糖的转化效果对[npc.name]无效……")
						+ "</p>";
			}
			
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ UtilText.parse(target, "棒棒糖的转化效似乎起效了，[npc.name]开始觉得头昏脑沉……")
					+ "</p>");
			
			if(!target.hasFetish(Fetish.FETISH_BIMBO)) {
				target.addFetish(Fetish.FETISH_BIMBO);
				if(target.isPlayer()) {
					sb.append("<br/>"
							+ "<p>"
								+ "一阵傻笑从[pc.mouth]中发出，你忽然意识到自己满脑子都是，怎么说呢，没什么营养还超级下流的东西了！"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>你获得了无脑大胸性癖！</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "一阵傻笑从[npc.namePos]的[pc.mouth]中发出，[npc.she]忽然意识到自己满脑子都是，怎么说呢，没什么营养还超级下流的东西了！"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>[npc.Name]获得了无脑大胸性癖！</b>"
							+ "</p>"));
				}
			}
			
			
			// Non-racial changes
			if(target.getFemininityValue()<95) {
				sb.append("<br/>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.DD.getMeasurement()) {
				sb.append("<br/>" + target.setBreastSize(CupSize.DD.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
				sb.append("<br/>" + target.setAssSize(AssSize.FOUR_LARGE.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
				sb.append("<br/>" + target.setHipSize(HipSize.FOUR_WOMANLY.getValue()));
			}
			
			if(!target.isAbleToHaveRaceTransformed()) {
				sb.append("<br/>");
				sb.append(UtilText.parse(target, "由于[npc.nameIsFull]免疫种族转化，棒棒糖没有产生额外效果！"));
				
			} else {
				if(target.getHairType()!=HairType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true));
				}
				if(target.getTorsoType()!=TorsoType.HARPY) {
					sb.append("<br/>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_BLEACH_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), true));
				}
				
				// Harpy TFs:
				if(target.getFaceType()!=FaceType.HUMAN)
					sb.append("<br/>" + target.setFaceType(FaceType.HUMAN));
				if(target.getEarType()!=EarType.HARPY)
					sb.append("<br/>" + target.setEarType(EarType.HARPY));
				if(target.getEyeType()!=EyeType.HARPY)
					sb.append("<br/>" + target.setEyeType(EyeType.HARPY));
				if(target.getHairType()!=HairType.HARPY)
					sb.append("<br/>" + target.setHairType(HairType.HARPY));
				if(target.getTorsoType()!=TorsoType.HUMAN)
					sb.append("<br/>" + target.setTorsoType(TorsoType.HUMAN));
	
				if(target.getWingType()!=WingType.NONE)
					sb.append("<br/>" + target.setWingType(WingType.NONE));
				if(target.getHornType()!=HornType.NONE)
					sb.append("<br/>" + target.setHornType(HornType.NONE));
	
				if(target.getArmType()!=ArmType.HARPY)
					sb.append("<br/>" + target.setArmType(ArmType.HARPY));
				if(target.getLegType()!=LegType.HARPY)
					sb.append("<br/>" + target.setLegType(LegType.HARPY));
				if(target.getTailType()!=TailType.HARPY)
					sb.append("<br/>" + target.setTailType(TailType.HARPY));
	
				if(target.getBreastType()!=BreastType.HARPY)
					sb.append("<br/>" + target.setBreastType(BreastType.HARPY));
				if(target.getAssType()!=AssType.HARPY)
					sb.append("<br/>" + target.setAssType(AssType.HARPY));
	
				if(target.hasPenisIgnoreDildo()) {
					sb.append("<br/>" + target.setPenisType(PenisType.HARPY));
	
					if(target.getPenisRawCumStorageValue()<CumProduction.TWO_SMALL_AMOUNT.getMedianValue()) {
						sb.append("<br/>" + target.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
						target.fillCumToMaxStorage();
					}
				}
				if(target.hasVagina()) {
					sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
				}
			}
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType NYMPHO_LOLLIPOP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>女色鬼</b><b style='color:"+PresetColour.RACE_HARPY.toWebHexString()+";'>哈比</b><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>转化</b>",
			"施加[style.colourSex(“舔棒棒糖”)]状态效果"),
			PresetColour.RACE_HARPY) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			target.addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);

			if(target.isDoll()) {
				return "<p>"
							+ UtilText.parse(target, "由于[npc.sheIsFull]是个性爱玩偶，棒棒糖的转化效果对[npc.name]无效……")
						+ "</p>";
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ UtilText.parse(target, "棒棒糖的转化效似乎起效了，[npc.name]开始觉得头昏脑沉……")
					+ "</p>");
			
			if(!target.hasTrait(Perk.NYMPHOMANIAC, false)) {
				target.addPerk(Perk.NYMPHOMANIAC);
				if(target.isPlayer()) {
					sb.append("<br/>"
							+ "<p>"
								+ "一阵难以抗拒的呻吟从[pc.mouth]中发出，你忽然意识到自己满脑子都只有做爱、做爱、做爱！"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>你获得了性瘾成狂天赋！</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "一阵难以抗拒的呻吟从[npc.namePos]的[pc.mouth]中发出，[npc.she]忽然意识到自己满脑子都只有做爱、做爱、做爱！"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>[npc.Name]获得了性瘾成狂天赋！</b>"
							+ "</p>"));
				}
			}
			
			// Non-racial changes
			if(target.getFemininityValue()<95) {
				sb.append("<br/>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
				sb.append("<br/>" + target.setBreastSize(CupSize.C.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
				sb.append("<br/>" + target.setAssSize(AssSize.THREE_NORMAL.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
				sb.append("<br/>" + target.setHipSize(HipSize.THREE_GIRLY.getValue()));
			}

			if(!target.isAbleToHaveRaceTransformed()) {
				sb.append("<br/>");
				sb.append(UtilText.parse(target, "由于[npc.nameIsFull]免疫种族转化，棒棒糖没有产生额外效果！"));
				
			} else {
				if(target.getHairType()!=HairType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false), true));
				}
				if(target.getTorsoType()!=TorsoType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_PINK, false, PresetColour.COVERING_PINK, false), true));
				}
				
				// Harpy TFs:
				if(target.getFaceType()!=FaceType.HUMAN)
					sb.append("<br/>" + target.setFaceType(FaceType.HUMAN));
				if(target.getEarType()!=EarType.HARPY)
					sb.append("<br/>" + target.setEarType(EarType.HARPY));
				if(target.getEyeType()!=EyeType.HARPY)
					sb.append("<br/>" + target.setEyeType(EyeType.HARPY));
				if(target.getHairType()!=HairType.HARPY)
					sb.append("<br/>" + target.setHairType(HairType.HARPY));
				if(target.getTorsoType()!=TorsoType.HUMAN)
					sb.append("<br/>" + target.setTorsoType(TorsoType.HUMAN));
	
				if(target.getWingType()!=WingType.NONE)
					sb.append("<br/>" + target.setWingType(WingType.NONE));
				if(target.getHornType()!=HornType.NONE)
					sb.append("<br/>" + target.setHornType(HornType.NONE));
	
				if(target.getArmType()!=ArmType.HARPY)
					sb.append("<br/>" + target.setArmType(ArmType.HARPY));
				if(target.getLegType()!=LegType.HARPY)
					sb.append("<br/>" + target.setLegType(LegType.HARPY));
				if(target.getTailType()!=TailType.HARPY)
					sb.append("<br/>" + target.setTailType(TailType.HARPY));
	
				if(target.getBreastType()!=BreastType.HARPY)
					sb.append("<br/>" + target.setBreastType(BreastType.HARPY));
				if(target.getAssType()!=AssType.HARPY)
					sb.append("<br/>" + target.setAssType(AssType.HARPY));
				if(target.getAssWetness().getValue()<Wetness.TWO_MOIST.getValue())
					sb.append("<br/>" + target.setAssWetness(Wetness.TWO_MOIST.getValue()));
					
	
				if(target.hasPenisIgnoreDildo()) {
					sb.append("<br/>" + target.setPenisType(PenisType.HARPY));
	
					if(target.getPenisRawCumStorageValue()<CumProduction.THREE_AVERAGE.getMedianValue()) {
						sb.append("<br/>" + target.setPenisCumStorage(CumProduction.THREE_AVERAGE.getMedianValue()));
						target.fillCumToMaxStorage();
					}
				}
				if(target.hasVagina()) {
					sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
	
					if(target.getVaginaWetness().getValue()<Wetness.FOUR_SLIMY.getValue())
						sb.append("<br/>" + target.setVaginaWetness(Wetness.FOUR_SLIMY.getValue()));
				}
			}
			
			return sb.toString();
		}
	};
	
	public static AbstractItemEffectType DOMINANT_PERFUME = new AbstractItemEffectType(Util.newArrayListOfValues(
			"<b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>支配欲</b><b style='color:"+PresetColour.RACE_HARPY.toWebHexString()+";'>哈比</b><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>转化</b>"),
			PresetColour.RACE_HARPY) {
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			StringBuilder sb = new StringBuilder();

			if(target.isDoll()) {
				return "<p>"
							+ UtilText.parse(target, "由于[npc.sheIsFull]是个性爱玩偶，香水的转化效果对[npc.name]无效……")
						+ "</p>";
			}
			
			sb.append("<p>"
						+ UtilText.parse(target, "香水的转化效似乎起效了，[npc.name]开始觉得头昏脑沉……")
					+ "</p>");
			
			if(!target.hasFetish(Fetish.FETISH_DOMINANT)) {
				target.addFetish(Fetish.FETISH_DOMINANT);
				if(target.isPlayer()) {
					sb.append("<br/>"
							+ "<p>"
								+ "一阵低沉的呻吟从[pc.mouth]中发出，你忽然意识到自己满脑子都是要如何征服你遇见的下一个人！"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>你获得了支配者性癖！</b>"
							+ "</p>");
				} else {
					sb.append(UtilText.parse(target, "<br/>"
							+ "<p>"
								+ "一阵低沉的呻吟从[npc.namePos]的[pc.mouth]中发出，[npc.she]忽然意识到自己满脑子都是要如何征服[npc.she]遇见的下一个人！"
								+ "<br/><b style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>[npc.Name]获得了支配者性癖！</b>"
							+ "</p>"));
				}
			}
			
			// Non-racial changes
			if(target.getFemininityValue()<95) {
				sb.append("<br/>" + target.setFemininity(95));
			}
			if(target.getBreastSize().getMeasurement()<CupSize.C.getMeasurement()) {
				sb.append("<br/>" + target.setBreastSize(CupSize.C.getMeasurement()));
			}
			if(target.getAssSize().getValue()<AssSize.THREE_NORMAL.getValue()) {
				sb.append("<br/>" + target.setAssSize(AssSize.THREE_NORMAL.getValue()));
			}
			if(target.getHipSize().getValue()<HipSize.THREE_GIRLY.getValue()) {
				sb.append("<br/>" + target.setHipSize(HipSize.THREE_GIRLY.getValue()));
			}

			if(!target.isAbleToHaveRaceTransformed()) {
				sb.append("<br/>");
				sb.append(UtilText.parse(target, "由于[npc.nameIsFull]免疫种族转化，香水没有产生额外效果！"));
				
			} else {
				if(target.getHairType()!=HairType.HARPY) {
					sb.append("<br/>" + target.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, CoveringPattern.NONE, PresetColour.COVERING_BLACK, false, PresetColour.COVERING_BLACK, false), true));
				}
				if(target.getTorsoType()!=TorsoType.HARPY) {
					sb.append("<br/>" + target.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, CoveringPattern.NONE, PresetColour.COVERING_RED, false, PresetColour.COVERING_RED, false), true));
				}
				
				// Harpy TFs:
				if(target.getFaceType()!=FaceType.HUMAN)
					sb.append("<br/>" + target.setFaceType(FaceType.HUMAN));
				if(target.getEarType()!=EarType.HARPY)
					sb.append("<br/>" + target.setEarType(EarType.HARPY));
				if(target.getEyeType()!=EyeType.HARPY)
					sb.append("<br/>" + target.setEyeType(EyeType.HARPY));
				if(target.getHairType()!=HairType.HARPY)
					sb.append("<br/>" + target.setHairType(HairType.HARPY));
				if(target.getTorsoType()!=TorsoType.HUMAN)
					sb.append("<br/>" + target.setTorsoType(TorsoType.HUMAN));
	
				if(target.getWingType()!=WingType.NONE)
					sb.append("<br/>" + target.setWingType(WingType.NONE));
				if(target.getHornType()!=HornType.NONE)
					sb.append("<br/>" + target.setHornType(HornType.NONE));
	
				if(target.getArmType()!=ArmType.HARPY)
					sb.append("<br/>" + target.setArmType(ArmType.HARPY));
				if(target.getLegType()!=LegType.HARPY)
					sb.append("<br/>" + target.setLegType(LegType.HARPY));
				if(target.getTailType()!=TailType.HARPY)
					sb.append("<br/>" + target.setTailType(TailType.HARPY));
	
				if(target.getBreastType()!=BreastType.HARPY)
					sb.append("<br/>" + target.setBreastType(BreastType.HARPY));
				if(target.getAssType()!=AssType.HARPY)
					sb.append("<br/>" + target.setAssType(AssType.HARPY));
					
	
				if(target.hasPenisIgnoreDildo()) {
					sb.append("<br/>" + target.setPenisType(PenisType.HARPY));
	
					if(target.getPenisRawCumStorageValue()<CumProduction.TWO_SMALL_AMOUNT.getMedianValue()) {
						sb.append("<br/>" + target.setPenisCumStorage(CumProduction.TWO_SMALL_AMOUNT.getMedianValue()));
						target.fillCumToMaxStorage();
					}
				}
				if(target.hasVagina()) {
					sb.append("<br/>" + target.setVaginaType(VaginaType.HARPY));
				}
			}
			
			return sb.toString();
		}
	};
	
	
	// Enchantment effects:
	
	public static AbstractItemEffectType ATTRIBUTE_PHYSIQUE = new AbstractItemEffectType(null,
			PresetColour.ATTRIBUTE_PHYSIQUE) {

		@Override
		public String getPotionDescriptor() {
			return "绚丽的";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return TFModifier.getTFModStrengthList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(ResourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.HEALTH, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType ATTRIBUTE_ARCANE = new AbstractItemEffectType(null,
			PresetColour.ATTRIBUTE_ARCANE) {

		@Override
		public String getPotionDescriptor() {
			return "抚慰的";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return TFModifier.getTFModIntelligenceList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType ATTRIBUTE_SEXUAL = new AbstractItemEffectType(null,
			PresetColour.GENERIC_SEX) {

		@Override
		public String getPotionDescriptor() {
			return "情欲的";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return TFModifier.getTFModSexualList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.MANA, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType ATTRIBUTE_CORRUPTION = new AbstractItemEffectType(null,
			PresetColour.ATTRIBUTE_CORRUPTION) {

		@Override
		public String getPotionDescriptor() {
			return "粘稠的";
		}
		
		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return TFModifier.getTFModCorruptionList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			return TFModifier.getTFAttributeList();
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(secondaryModifier != TFModifier.NONE) {
				return TFPotency.getAllPotencies();
			} else {
				return new ArrayList<>();
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			return genericAttributeEffectDescription(ResourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit);
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return genericAttributeEffect(ResourceRestoration.ALL, primaryModifier, secondaryModifier, potency, limit, user, target);
		}
	};
	
	public static AbstractItemEffectType FETISH_ENHANCEMENT = new AbstractItemEffectType(null,
			PresetColour.FETISH) {

		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return Util.newArrayListOfValues(
					TFModifier.NONE,
					TFModifier.TF_MOD_FETISH_BODY_PART,
					TFModifier.TF_MOD_FETISH_BEHAVIOUR);
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			List<TFModifier> list = new ArrayList<>();
			list.add(TFModifier.NONE);
			
			if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				list.addAll(TFModifier.getTFBehaviouralFetishList());
			}
			if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART){
				list.addAll(TFModifier.getTFBodyPartFetishList());
			}
			return list;
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			if(primaryModifier==TFModifier.NONE) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
			}
			return Util.newArrayListOfValues(
					TFPotency.BOOST,
					TFPotency.MINOR_BOOST,
					TFPotency.MINOR_DRAIN,
					TFPotency.DRAIN);
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {

			if(primaryModifier==TFModifier.NONE) {
				return Util.newArrayListOfValues("增加或移除一个[style.colourFetish(随机性癖)]。");
			}
			
			String descriptor = "";
			
			if(primaryModifier==TFModifier.TF_MOD_FETISH_BODY_PART) {
				descriptor = "身体部位相关";
			} else if(primaryModifier==TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				descriptor = "行为相关";
			}
			
			if(potency==TFPotency.BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("增加一个[style.colourFetish(随机"+descriptor+"性癖)]。");
				} else {
					return Util.newArrayListOfValues("增加[style.colourFetish("+secondaryModifier.getName()+"性癖)]。");
				}
				
			} else if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("提高对于[style.colourFetish(随机"+descriptor+"性癖)]的[style.colourLust(渴望值)]。");
				} else {
					return Util.newArrayListOfValues("提高对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]。");
				}
				
			} else if(potency==TFPotency.MINOR_DRAIN) {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("降低对于[style.colourFetish(随机"+descriptor+"性癖)]的[style.colourLust(渴望值)](前提是尚未拥有该性癖)。");
				} else {
					return Util.newArrayListOfValues("降低对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)](前提是尚未拥有该性癖)。");
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					return Util.newArrayListOfValues("移除[style.colourFetish(随机"+descriptor+"性癖)]。");
				} else {
					return Util.newArrayListOfValues("移除[style.colourFetish("+secondaryModifier.getName()+"性癖)]。");
				}
			}
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			// Completely random:
			if(primaryModifier==TFModifier.NONE) {
				List<AbstractFetish> fetishesToAdd = new ArrayList<>();
				List<AbstractFetish> fetishesToRemove = new ArrayList<>();
				for(AbstractFetish f : Fetish.getAllFetishes()) {
					if(!f.isContentEnabled()) {
						continue;
					}
					if(f.getFetishesForAutomaticUnlock().isEmpty()) {
						if(target.hasFetish(f)) {
							fetishesToRemove.add(f);
							
						} else if(f.isAvailable(target)) {
							fetishesToAdd.add(f);
						}
					}
				}
				
				if((Math.random()>0.33f && !fetishesToAdd.isEmpty()) || fetishesToRemove.isEmpty()) {
					AbstractFetish f = fetishesToAdd.get(Util.random.nextInt(fetishesToAdd.size()));
					return target.addFetish(f);
					
				} else {
					AbstractFetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
					return target.removeFetish(f);
				}
			}
			
			// Based on body part or behaviour fetishes:
			
			List<AbstractFetish> availableFetishes = new ArrayList<>();
			
			if(primaryModifier==TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				for(TFModifier mod : TFModifier.getTFBehaviouralFetishList()) {
					if(mod.getFetish()!=null) {
						availableFetishes.add(mod.getFetish());
					}
				}
			} 
			if(primaryModifier==TFModifier.TF_MOD_FETISH_BODY_PART) {
				for(TFModifier mod : TFModifier.getTFBodyPartFetishList()) {
					if(mod.getFetish()!=null) {
						availableFetishes.add(mod.getFetish());
					}
				}
			}
			
			if(potency==TFPotency.BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToAdd = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToAdd.add(f);
							}
						}
					}
					
					if(!fetishesToAdd.isEmpty()) {
						AbstractFetish f = fetishesToAdd.get(Util.random.nextInt(fetishesToAdd.size()));
						return target.addFetish(f);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(无事发生……)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					
					return target.addFetish(fetish);
				}
				
			} else if(potency==TFPotency.MINOR_BOOST) {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToBoost = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToBoost.add(f);
							}
						}
					}
					
					if(!fetishesToBoost.isEmpty()) {
						AbstractFetish f = fetishesToBoost.get(Util.random.nextInt(fetishesToBoost.size()));
						FetishDesire newDesire = target.getFetishDesire(f).getNextDesire();
						
						return target.setFetishDesire(f, newDesire);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(无事发生……)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					FetishDesire newDesire = target.getFetishDesire(fetish).getNextDesire();
					
					return target.setFetishDesire(fetish, newDesire);
				}
				
			} else if(potency==TFPotency.MINOR_DRAIN) {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToDrain = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty() && !target.hasFetish(f)) {
							if(f.isAvailable(target)) {
								fetishesToDrain.add(f);
							}
						}
					}
					
					if(!fetishesToDrain.isEmpty()) {
						AbstractFetish f = fetishesToDrain.get(Util.random.nextInt(fetishesToDrain.size()));
						FetishDesire newDesire = target.getFetishDesire(f).getPreviousDesire();
						
						return target.setFetishDesire(f, newDesire);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(无事发生……)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					FetishDesire newDesire = target.getFetishDesire(fetish).getPreviousDesire();
					
					return target.setFetishDesire(fetish, newDesire);
				}
				
			} else {
				if(secondaryModifier == TFModifier.NONE) {
					List<AbstractFetish> fetishesToRemove = new ArrayList<>();
					for(AbstractFetish f : availableFetishes) {
						if(f.getFetishesForAutomaticUnlock().isEmpty()) {
							if(target.hasFetish(f)) {
								fetishesToRemove.add(f);
							}
						}
					}
					
					if(!fetishesToRemove.isEmpty()) {
						AbstractFetish f = fetishesToRemove.get(Util.random.nextInt(fetishesToRemove.size()));
						return target.removeFetish(f);
						
					} else {
						return "<p>"
									+"[style.colourDisabled(无事发生……)]"
								+"</p>";
					}
					
				} else {
					AbstractFetish fetish = secondaryModifier.getFetish();
					
					return target.removeFetish(fetish);
				}
			}
		}
	};
	
	// RACIAL:
	
	public static AbstractItemEffectType DEBUG_DEMON_POTION_EFFECT = new AbstractItemEffectType(null,
			PresetColour.RACE_DEMON) {
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsDescription = new ArrayList<>();
			
			effectsDescription.add("[style.colourBad(无法影响特殊角色)]");
			
			effectsDescription.add("将非恶魔[style.colourTfGeneric(转化)]为[style.colourDemon(半恶魔)]");

			effectsDescription.add("将半恶魔[style.colourTfGeneric(转化)]为[style.colourDemon(恶魔)]");
			
			return effectsDescription;
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
//			if(target.isPlayer()) {
//				return "<p style='text-align:center'>[style.italicsDisabled(This item does not work on you...)]</p>";
//			}
			if(!target.isPlayer() && target.isUnique() && (!target.isSlave() || target.getOwner().isPlayer())) {
				return "<p style='text-align:center'>[style.italicsDisabled(该物品对于非奴隶的特殊角色无效……)]</p>";
			}
			
			AbstractSubspecies sub = target.getBody().getFleshSubspecies();
			if(sub.getRace()!=Race.DEMON) {
				target.setBody(Main.game.getCharacterUtils().generateHalfDemonBody(target, target.getGender(), sub, true), false);
				return UtilText.parse(target, "<p style='text-align:center; color:"+PresetColour.RACE_DEMON.toWebHexString()+";'><i>[npc.Name]现在是[npc.a_race]了！</i></p>");
			} else {
				target.setBody(target.getGender(), Subspecies.DEMON, RaceStage.GREATER, false);
				target.setSubspeciesOverride(Subspecies.DEMON);
				return UtilText.parse(target, "<p style='text-align:center; color:"+PresetColour.RACE_DEMON.toWebHexString()+";'><i>[npc.Name]现在是[npc.a_race]了！</i></p>");
			}
		}
	};
	
	// CLOTHING:
	
	public static AbstractItemEffectType CLOTHING = new AbstractItemEffectType(null,
			PresetColour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			List<TFModifier> mods = new ArrayList<>(TFModifier.getClothingPrimaryList());
			if(targetItem instanceof AbstractClothing) {
				 //If this clothing is a 'sex toy' or groin/nipple clothing, then allow vibration and orgasm denial enchantments:
				if(((AbstractClothing)targetItem).getItemTags().contains(ItemTag.ENABLE_SEX_EQUIP)
						|| !Collections.disjoint(
								((AbstractClothing)targetItem).getClothingType().getEquipSlots(),
								Util.newArrayListOfValues(
										InventorySlot.GROIN,
										InventorySlot.VAGINA,
										InventorySlot.PENIS,
										InventorySlot.ANUS,
										InventorySlot.NIPPLE,
										InventorySlot.CHEST,
										InventorySlot.PIERCING_NIPPLE,
										InventorySlot.PIERCING_PENIS,
										InventorySlot.PIERCING_VAGINA))) {
					mods.add(2, TFModifier.CLOTHING_SEXUAL);
				}
			}
			return mods;
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getClothingAttributeList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				return TFModifier.getClothingMajorAttributeList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				return TFModifier.getTFBehaviouralFetishList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return TFModifier.getTFBodyPartFetishList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_SPECIAL) {
				List<TFModifier> mods = Util.newArrayListOfValues(TFModifier.CLOTHING_SEALING, TFModifier.CLOTHING_SERVITUDE);
				
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					mods.add(TFModifier.CLOTHING_ENSLAVEMENT);
				}
				return mods;
				
			} else if(primaryModifier == TFModifier.CLOTHING_SEXUAL) {
				return Util.newArrayListOfValues(
						TFModifier.CLOTHING_VIBRATION,
						TFModifier.CLOTHING_ORGASM_PREVENTION);
				
			} else if(primaryModifier == TFModifier.CLOTHING_CONDOM) {
				return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
				
			} else if(primaryModifier == TFModifier.CLOTHING_CREAMPIE_RETENTION) {
				return TFModifier.getClothingCreampieRetentionList();
				
			} else {
				return getClothingTFSecondaryModifiers(primaryModifier);
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			 if(secondaryModifier == TFModifier.CLOTHING_SEALING) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.MINOR_DRAIN, TFPotency.DRAIN, TFPotency.MAJOR_DRAIN);
				
			} else if(secondaryModifier == TFModifier.CLOTHING_VIBRATION
						|| secondaryModifier == TFModifier.REMOVAL
						|| secondaryModifier == TFModifier.TF_TYPE_1) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.BOOST, TFPotency.MAJOR_BOOST);
				
			} else if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
						|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
						|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
						|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
						|| primaryModifier == TFModifier.CLOTHING_CONDOM
						|| !getClothingTFSecondaryModifiers(primaryModifier).isEmpty()) {
				return TFPotency.getAllPotencies();
				
			} else {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();
			
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) { //This is overridden in a couple of places, such as in InventoryTooltipEventListener
				effectsList.add(secondaryModifier.getAssociatedAttribute().getFormattedValue(potency.getClothingBonusValue()));
				
			} else if(secondaryModifier == TFModifier.CLOTHING_SEALING) {
				if(potency==TFPotency.SPECIAL) {
					effectsList.add("[style.colourCrimson(封印于穿戴者)]<b>([style.colourTerrible(无法解封！)])</b>");
					
				} else if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.colourCrimson(封印于穿戴者)]<b>(解封:[style.colourArcane(" + ItemEffect.SEALED_COST_MINOR_DRAIN + ")])</b>");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.colourCrimson(封印于穿戴者)]<b>(解封:[style.colourArcane(" + ItemEffect.SEALED_COST_DRAIN + ")])</b>");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("[style.colourCrimson(封印于穿戴者)]<b>(解封:[style.colourArcane(" + ItemEffect.SEALED_COST_MAJOR_DRAIN + ")])</b>");
					
				} else {
					effectsList.add("[style.colourCrimson(封印于穿戴者)]<b>(解封:[style.colourArcane(" + ItemEffect.SEALED_COST_MINOR_BOOST + ")])</b>");
				}
				
			} else if(secondaryModifier == TFModifier.CLOTHING_SERVITUDE) {
				effectsList.add("[style.colourBad(阻止)][style.colourTfGeneric(自我转化)]");
				effectsList.add("[style.colourBad(阻止)][style.colourArcane(移除封印)]");
				
			} else if(secondaryModifier == TFModifier.CLOTHING_ENSLAVEMENT) {
				effectsList.add("[style.colourCrimson(奴役穿戴者)]");
				
			} else if(secondaryModifier == TFModifier.CLOTHING_ORGASM_PREVENTION) {
				effectsList.add("[style.colourCrimson(阻止穿戴者高潮)]");
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.colourExcellent(给予)][style.colourFetish("+secondaryModifier.getName()+"性癖)]");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.colourGood(+2)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.colourMinorGood(+1)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("<b style='color:"+FetishDesire.ZERO_HATE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(FetishDesire.ZERO_HATE.getNameAsVerb())+"</b>[style.colourFetish("+secondaryModifier.getName()+"性癖)]");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.colourBad(-2)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
					
				} else if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.colourMinorBad(-1)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
				}
				
			} else if(primaryModifier == TFModifier.CLOTHING_CONDOM) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.colourExcellent(无限化)]安全射精量。");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.colourGood("+Units.fluid(CumProduction.SIX_EXTREME.getMaximumValue())+")]安全射精量。");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.colourMinorGood("+Units.fluid(CumProduction.FIVE_HUGE.getMaximumValue())+")]安全射精量。");
					
				} else if(potency==TFPotency.MAJOR_DRAIN
						|| potency==TFPotency.DRAIN
						|| potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.colourTerrible(只要被破坏)]就会损坏！");
				}
				
			} else if(secondaryModifier == TFModifier.CLOTHING_VIBRATION) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.colourSex(+20)][style.colourLust(日常性欲)]");
					effectsList.add("[style.colourSex(性交时)][style.colourSex(+2)][style.colourArousal(快感/回合)] ");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.colourSex(+10)][style.colourLust(日常性欲)]");
					effectsList.add("[style.colourSex(性交时)][style.colourSex(+1)][style.colourArousal(快感/回合)]");
					
				} else {
					effectsList.add("[style.colourSex(+5)][style.colourLust(日常性欲)]");
					effectsList.add("[style.colourSex(性交时)][style.colourSex(+0.5)][style.colourArousal(快感/回合)]");
				}
				
			} else if(primaryModifier == TFModifier.CLOTHING_CREAMPIE_RETENTION) {
				String area = "";
				switch(secondaryModifier) {
					case TF_FACE:
						area = "腹部";
						break;
					case TF_ASS:
						area = "屁股";
						break;
					case TF_VAGINA:
						area = "小穴";
						break;
					case TF_VAGINA_URETHRA:
						area = "阴道尿道";
						break;
					case TF_PENIS_URETHRA:
						area = "阴茎尿道";
						break;
					case TF_BREASTS:
						area = "胸部";
						break;
					case TF_BREASTS_CROTCH:
						area = target!=null && target.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳";
						break;
					case TF_SPINNERET:
						area = "丝囊";
						break;
					default:
						break;
				}
				effectsList.add("[style.colourExcellent(保持)]"+area+"的内射状态");
				
			} else {
				return getClothingTFDescriptions(primaryModifier, secondaryModifier, potency, limit, user, target);
			}
			
			return effectsList;
		}
		
		@Override
		public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getClothingTFLimits(primaryModifier, secondaryModifier);
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(target.isDoll()) {
				return ""; // Dolls cannot be transformed via standard clothing effects
			}
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| secondaryModifier == TFModifier.CLOTHING_ENSLAVEMENT
					|| secondaryModifier == TFModifier.CLOTHING_SERVITUDE
					|| secondaryModifier == TFModifier.CLOTHING_SEALING
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
					|| primaryModifier == TFModifier.CLOTHING_CONDOM) {
				return "";
			}
			return applyClothingTF(primaryModifier, secondaryModifier, potency, limit, user, target, timer);
		}
	};
	
	public static AbstractItemEffectType TATTOO = new AbstractItemEffectType(null,
			PresetColour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return TFModifier.getTattooPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getClothingAttributeList();
				
			}else if(primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				return TFModifier.getClothingMajorAttributeList();
				
			}  else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR) {
				return TFModifier.getTFBehaviouralFetishList();
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return TFModifier.getTFBodyPartFetishList();
				
			} else if(primaryModifier == TFModifier.CLOTHING_CREAMPIE_RETENTION) {
				return TFModifier.getClothingCreampieRetentionList();
				
			} else {
				return getClothingTFSecondaryModifiers(primaryModifier);
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			 if(secondaryModifier == TFModifier.CLOTHING_VIBRATION
					|| secondaryModifier == TFModifier.REMOVAL
					|| secondaryModifier == TFModifier.TF_TYPE_1) {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST, TFPotency.BOOST, TFPotency.MAJOR_BOOST);
				
			} else if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART
					|| !getClothingTFSecondaryModifiers(primaryModifier).isEmpty()) {
				return TFPotency.getAllPotencies();
				
			} else {
				return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
			}
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();
			
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) { //This is overridden in a couple of places, such as in InventoryTooltipEventListener
				effectsList.add(secondaryModifier.getAssociatedAttribute().getFormattedValue(potency.getClothingBonusValue()));
				
			} else if(primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				if(potency==TFPotency.MAJOR_BOOST) {
					effectsList.add("[style.colourExcellent(给予)][style.colourFetish("+secondaryModifier.getName()+"性癖)]");
					
				} else if(potency==TFPotency.BOOST) {
					effectsList.add("[style.colourGood(提高)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
					
				} else if(potency==TFPotency.MINOR_BOOST) {
					effectsList.add("[style.colourMinorGood(略微提高)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
					
				} else if(potency==TFPotency.MAJOR_DRAIN) {
					effectsList.add("<b style='color:"+FetishDesire.ZERO_HATE.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(FetishDesire.ZERO_HATE.getNameAsVerb())+"</b>[style.colourFetish("+secondaryModifier.getName()+"性癖)]");
					
				} else if(potency==TFPotency.DRAIN) {
					effectsList.add("[style.colourBad(降低)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
					
				} else if(potency==TFPotency.MINOR_DRAIN) {
					effectsList.add("[style.colourMinorBad(略微降低)]对于[style.colourFetish("+secondaryModifier.getName()+"性癖)]的[style.colourLust(渴望值)]");
				}
				
			} else if(primaryModifier == TFModifier.CLOTHING_CREAMPIE_RETENTION) {
				String area = "";
				switch(secondaryModifier) {
					case TF_FACE:
						area = "腹部";
						break;
					case TF_ASS:
						area = "屁股";
						break;
					case TF_VAGINA:
						area = "小穴";
						break;
					case TF_VAGINA_URETHRA:
						area = "阴道尿道";
						break;
					case TF_PENIS_URETHRA:
						area = "阴茎尿道";
						break;
					case TF_BREASTS:
						area = "胸部";
						break;
					case TF_BREASTS_CROTCH:
						area = target.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳";
						break;
					case TF_SPINNERET:
						area = "丝囊";
						break;
					default:
						break;
				}
				effectsList.add("[style.colourExcellent(保持)]"+area+"的内射状态");
				
			} else {
				return getClothingTFDescriptions(primaryModifier, secondaryModifier, potency, limit, user, target);
			}
			
			return effectsList;
		}
		
		@Override
		public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return getClothingTFLimits(primaryModifier, secondaryModifier);
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			if(target.isDoll()) {
				return ""; // Dolls cannot be transformed via standard clothing effects
			}
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE
					|| primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BEHAVIOUR
					|| primaryModifier == TFModifier.TF_MOD_FETISH_BODY_PART) {
				return "";
			}
			return applyClothingTF(primaryModifier, secondaryModifier, potency, limit, user, target, timer);
		}
	};
	

	public static AbstractItemEffectType WEAPON = new AbstractItemEffectType(null,
			PresetColour.RARITY_RARE) {

		@Override
		public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
			return TFModifier.getWeaponPrimaryList();
		}

		@Override
		public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE) {
				return TFModifier.getWeaponAttributeList();
			} else {
				return TFModifier.getWeaponMajorAttributeList();
			}
		}
		
		@Override
		public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
			return TFPotency.getAllPotencies();
		}
		
		@Override
		public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
			List<String> effectsList = new ArrayList<>();

			if(primaryModifier == TFModifier.CLOTHING_ATTRIBUTE || primaryModifier == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				effectsList.add(secondaryModifier.getAssociatedAttribute().getFormattedValue(potency.getClothingBonusValue()));
				
			} else {
				effectsList.add("[style.colourBad(未辨认效果:)]"+primaryModifier.getName());
			}
			
			return effectsList;
		}
		
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			return "";
		}
	};
	
	public static AbstractItemEffectType OFFSPRING_MAP = new AbstractItemEffectType(Util.newArrayListOfValues(
			"启用后代发现功能。"),
			PresetColour.RARITY_LEGENDARY) {
		@Override
		public boolean isBreakOutOfInventory() {
			return true;
		}
		@Override
		public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
			Main.game.setContent(new Response("", "", OffspringMapDialogue.OFFSPRING_CHOICE));
			return "";
		}
	};

	public static AbstractItemEffectType getRacialEffectType(AbstractRace race) {
		return racialEffectTypes.get(race);
	}
	
	public static Map<AbstractItemEffectType, String> itemEffectTypeToIdMap = new HashMap<>();
	public static Map<String, AbstractItemEffectType> idToItemEffectTypeMap = new HashMap<>();
	public static List<AbstractItemEffectType> allEffectTypes = new ArrayList<>();
	public static Map<AbstractRace, AbstractItemEffectType> racialEffectTypes = new HashMap<>();
	
	public static void addAbstractItemEffectToIds(String id, AbstractItemEffectType itemEffectType) {
		allEffectTypes.add(itemEffectType);
		
		itemEffectTypeToIdMap.put(itemEffectType, id);
		idToItemEffectTypeMap.put(id, itemEffectType);
	}
	
	public static AbstractItemEffectType getItemEffectTypeFromId(String id) {
		if(id.startsWith("RACE_")) {
			return getRacialEffectType(Race.getRaceFromId(id.substring(5)));
		}
		id = Util.getClosestStringMatch(id, idToItemEffectTypeMap.keySet());
		return idToItemEffectTypeMap.get(id);
	}
	
	public static String getIdFromItemEffectType(AbstractItemEffectType itemEffectType) {
		return itemEffectTypeToIdMap.get(itemEffectType);
	}
	
	// set in ItemType
	public static AbstractItemEffectType getBookEffectFromSubspecies(AbstractSubspecies subspecies) {
		String id = Util.getClosestStringMatch("BOOK_READ_"+Subspecies.getIdFromSubspecies(subspecies), idToItemEffectTypeMap.keySet());
		return idToItemEffectTypeMap.get(id);
	}
	
	public static List<AbstractItemEffectType> getAllEffectTypes() {
		return allEffectTypes;
	}
	
	static {
		Field[] fields = ItemEffectType.class.getFields();
		for(Field f : fields){
			if (AbstractItemEffectType.class.isAssignableFrom(f.getType())) {
				AbstractItemEffectType iet;
				try {
					iet = ((AbstractItemEffectType) f.get(null));
					
					allEffectTypes.add(iet);
					
					itemEffectTypeToIdMap.put(iet, f.getName());
					idToItemEffectTypeMap.put(f.getName(), iet);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		for(AbstractRace race : Race.getAllRaces()) {
			if(race==Race.SLIME) { // Special case for slimes:
				racialEffectTypes.put(
						race,
						new AbstractItemEffectType(null,
								race.getColour()) {
							@Override
							public AbstractRace getAssociatedRace() {
								return race;
							}
							@Override
							public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
								return Util.newArrayListOfValues(TFModifier.TF_MATERIAL_FLESH);
							}
							@Override
							public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
								return Util.newArrayListOfValues(TFModifier.ARCANE_BOOST);
							}
							@Override
							public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
								return Util.newArrayListOfValues(TFPotency.MINOR_BOOST);
							}
							@Override
							public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
								return Util.newArrayListOfValues("将目标的身体材质修改为血肉。");
							}
							@Override
							public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
								return target.isElemental()
										? "<p style='margin-bottom:0; padding-bottom:0;'>" +
												"[style.colourDisabled([npc.NameIsFull]是元素体，所以无事发生……)]" +
											"</p>"
										: target.setBodyMaterial(BodyMaterial.FLESH);
							}
						});
				
			} else {
				racialEffectTypes.put(
						race,
						new AbstractItemEffectType(null,
								race.getColour()) {
							@Override
							public AbstractRace getAssociatedRace() {
								return race;
							}
//							@Override
//							public int getLimits(TFModifier primaryModifier, TFModifier secondaryModifier) {
//								return getClothingTFLimits(primaryModifier, secondaryModifier);
//							}
							@Override
							public List<TFModifier> getPrimaryModifiers(AbstractCoreItem targetItem) {
								return TFModifier.getTFRacialBodyPartsList();
							}
							@Override
							public List<TFModifier> getSecondaryModifiers(AbstractCoreItem targetItem, TFModifier primaryModifier) {
								return getRacialSecondaryModifiers(race, primaryModifier);
							}
							@Override
							public List<TFPotency> getPotencyModifiers(TFModifier primaryModifier, TFModifier secondaryModifier) {
								return getRacialPotencyModifiers(race, primaryModifier, secondaryModifier);
							}
							@Override
							public List<String> getEffectsDescription(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target) {
								return Util.newArrayListOfValues(getRacialEffect(race, primaryModifier, secondaryModifier, potency, user, target).getDescription());
							}
							@Override
							public String itemEffectOverride(TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit, GameCharacter user, GameCharacter target, ItemEffectTimer timer) {
								return getRacialEffect(race, primaryModifier, secondaryModifier, potency, user, target).applyEffect();
							}
						});
			}
		}
		
		for(Entry<AbstractRace, AbstractItemEffectType> entry : racialEffectTypes.entrySet()) {
			allEffectTypes.add(entry.getValue());
			
			String id = "RACE_"+Race.getIdFromRace(entry.getKey());
			itemEffectTypeToIdMap.put(entry.getValue(), id);
			idToItemEffectTypeMap.put(id, entry.getValue());
		}
	}
	
}
