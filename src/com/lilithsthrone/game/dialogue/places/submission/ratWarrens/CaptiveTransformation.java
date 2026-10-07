package com.lilithsthrone.game.dialogue.places.submission.ratWarrens;

import java.util.LinkedHashMap;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.FluidRegeneration;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.5.5
 * @version 0.3.9
 * @author Innoxia
 */
public enum CaptiveTransformation {
	
	MASCULINE_FETISH(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			map.put("首先，你会开始爱上被转化嘞感觉；之后你会求我来转化你！",
					target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			
			map.put("接下来，你会因为自己是个没用嘞顺从挤奶工，私处被展示给任何进入这个房间嘞人而真正性奋起来！",
					target.removeFetish(Fetish.FETISH_DOMINANT, true)
					+ target.addFetish(Fetish.FETISH_SUBMISSIVE, true)
					+ target.addFetish(Fetish.FETISH_MASOCHIST, true)
					+ target.addFetish(Fetish.FETISH_EXHIBITIONIST, true));
			
			map.put(target.hasPenisIgnoreDildo()
						?"你最喜欢嘞就是整天对着挤奶机榨汁！"
						:"一旦我让你长出漂亮嘞大屌，你就会喜欢整天对着挤奶机射精了！",
					target.addFetish(Fetish.FETISH_PENIS_GIVING, true)
					+ target.addFetish(Fetish.FETISH_CUM_STUD, true));

			map.put("当然，做我嘞产奶工，最重要的是爱上我嘞大肥屌！之后你就会求着被我灌满臭精了！",
					target.addFetish(Fetish.FETISH_SIZE_QUEEN, true)
					+ target.addFetish(Fetish.FETISH_PENIS_RECEIVING, true)
					+ target.addFetish(Fetish.FETISH_CUM_ADDICT, true));
			
			if(Main.game.isAnalContentEnabled()) {
				map.put("我们不能忘了你嘞屁股，对吧？我嘞挤奶工全都是下流嘞屁穴荡妇，你也不例外！",
						target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			}

			map.put("最后，该让你爱上口交了！我马上就来测试这个……",
					target.addFetish(Fetish.FETISH_ORAL_GIVING, true));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 1);
			
			return map;
		}
	},
	
	MASCULINE_FEMININITY(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a sissy:
			boolean sissy = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
			
			if(target.hasBreasts()) {
				map.put("你再也不需要你那对大奶子了！",
						(sissy
							?target.setBreastSize(CupSize.TRAINING_A.getMeasurement())
							:target.setBreastSize(0))
						+ (sissy
							?""
							:target.setNippleSize(NippleSize.ONE_SMALL.getValue())
								+ target.setAreolaeSize(AreolaeSize.ONE_SMALL.getValue())));
			}
			
			if(target.hasBreastsCrotch()) {
				map.put("我们不需要你那些悬垂嘞腹乳！",
						target.setBreastCrotchType(BreastType.NONE));
			}
			
			if(sissy) {
				map.put("我要你嘞样子能让别人猜不出你是男是女！哈！",
						target.setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity())
						+ ((!target.isFaceBaldnessNatural() && target.getHairRawLengthValue()<HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								?target.incrementHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								:""));
				
				map.put("让我给你一些少女嘞大乳头吧！",
						target.incrementNippleSize(3)
						+ target.incrementAreolaeSize(1));

				map.put("让我给你一个少女嘞圆润大屁股吧！",
						target.setHipSize(HipSize.THREE_GIRLY)
						+ target.setAssSize(AssSize.FOUR_LARGE));
				
				if(target.getLipSizeValue()<LipSize.TWO_FULL.getValue()) {
					map.put("现在让你嘞嘴唇丰满一点吧！",
							target.setLipSize(LipSize.TWO_FULL));
				}
				
			} else {
				map.put("像你这样嘞产精工，应该看起来像个男人！",
						target.setFemininity(5)
						+ target.setHipSize(HipSize.TWO_NARROW)
						+ target.setAssSize(AssSize.TWO_SMALL)
						+ (!target.isFaceBaldnessNatural()
							?target.setHairLength(HairLength.TWO_SHORT)
							:"")
						+ target.setLipSize(LipSize.ONE_AVERAGE));
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 2);
			return map;
		}
	},
	
	MASCULINE_GENITALS(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a sissy:
			boolean sissy = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
			
			if(target.hasVagina()) {
				map.put("既然你要变成产精工，你就不再需要你嘞屄了！",
						target.setVaginaType(VaginaType.NONE));
			}

			if(!target.hasPenisIgnoreDildo()) {
				if(sissy) {
					map.put("你会长出一个伪娘嘞小鸡巴！让这个小东西流出精液，你会觉得很尴尬的！嘿嘿嘿！",
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType())
							+ target.setPenisSize(PenisLength.ONE_TINY.getMedianValue())
							+ (target.getPenisRawGirthValue()>PenetrationGirth.TWO_NARROW.getValue()
									?target.setPenisGirth(PenetrationGirth.TWO_NARROW)
									:""));
					
				} else {
					map.put("你会长出一个很棒嘞肥屌！你嘞精液会给我们带来一大笔钱！嘿嘿嘿！",
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType())
							+ target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_GIRTHY)
									:""));
				}
			} else {
				if(sissy) {
					map.put("你嘞鸡巴会又小又精致，让你觉得自己是个可悲嘞小伪娘！",
							target.setPenisSize(PenisLength.ONE_TINY.getMedianValue())
							+(target.getPenisRawGirthValue()>PenetrationGirth.TWO_NARROW.getValue()
									?target.setPenisGirth(PenetrationGirth.TWO_NARROW)
									:""));
					
				} else {
					map.put("你嘞鸡巴得再大点，不然榨精嘞时候吸奶管可能会滑下来！",
							target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_GIRTHY)
									:""));
				}
			}
			
			if(target.isInternalTesticles()) {
				map.put(sissy
							?"我想看到你嘞小蛋蛋在机器榨精时紧缩嘞样子！"
							:"我们想看你嘞蛋蛋在机器榨精时摇晃嘞样子！",
						target.setInternalTesticles(false));
			}
			
			if(sissy) {
				if(target.getTesticleSize().getValue()>TesticleSize.ONE_TINY.getValue()) {
					map.put("你嘞蛋蛋要变小很多，我们要让你觉得展示蛋蛋很丢人！",
							target.setTesticleSize(TesticleSize.ONE_TINY));
				}
				
			} else {
				if(target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()) {
					map.put("你嘞蛋蛋还得再大点，才能把精液榨出来！",
							target.setTesticleSize(TesticleSize.FOUR_HUGE));
				}
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY)) {
					map.put("让我们给你一个诱人嘞肉感屁眼吧，让任何来拜访产奶工嘞人都忍不住去干它！",
							target.addAssOrificeModifier(OrificeModifier.PUFFY));
				}
				if(target.getAssWetness().getValue()<Wetness.FIVE_SLOPPY.getValue()) {
					map.put("我们要把你嘞屁眼弄得湿湿的，这样俺嘞肥屌就能滑进去了！",
							target.setAssWetness(Wetness.FIVE_SLOPPY));
				}
				
				OrificeDepth depth = Main.game.getPlayer().getBody().getAss().getAnus().getOrificeAnus().getMinimumDepthForSizeUncomfortable(Main.game.getPlayer(), Main.game.getNpc(Murk.class).getPenisRawSizeValue());
				int increment = depth.getValue() - Main.game.getPlayer().getAssDepth().getValue();
				if(increment>0) {
					map.put("如果不能把俺嘞肥屌捅进你嘞屁眼里，那就没意思了！",
							target.incrementAssDepth(increment));
				}
				
				if(Main.game.isGapeContentEnabled()) {
					map.put("我喜欢我嘞产奶工一开始<i>都是</i>紧致嘞洞，然后我花时间把它们都变成我嘞形状！我会很享受把你嘞小屁股变成一个毁掉嘞操弄洞！",
							target.setAssCapacity(1, true)
							+ target.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue())
							+ target.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
				}
				
				if(!target.hasLegs() && target.getGenitalArrangement()!=GenitalArrangement.CLOACA_BEHIND) {
					map.put("你以为你嘞屁眼长在[pc.leg]前面，我就不能从后面干你了吗？哈，那就再想想吧！"
								+ "你嘞屁眼会转化到你嘞后向泄殖腔里，所以从现在起你要一直被人从后面操了！嘿嘿嘿！",
							target.setGenitalArrangement(GenitalArrangement.CLOACA_BEHIND));
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 3);
			
			return map;
		}
	},
	
	MASCULINE_FLUIDS(false) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a sissy:
			boolean sissy = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
			
			map.put(sissy
						?"我们需要你尽可能多地把精液注入你嘞小蛋蛋里！"
						:"我们需要你把大量宝贵嘞精液储存在你那肥大嘞蛋蛋里！",
					target.incrementPenisCumStorage(500));

			map.put("产精工最重要嘞一点是，你能像这样快速地榨出更多精液！",
					target.incrementPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
			
			if(!sissy) {
				if(target.getTesticleSize().getValue()<TesticleSize.SIX_GIGANTIC.getValue()) {
					map.put("想要榨出那么多精液，我想你需要更大嘞蛋蛋了！",
							target.setTesticleSize(TesticleSize.SIX_GIGANTIC));
				}
			}
			
			FluidFlavour flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());
			
			map.put("要卖出味道普通嘞液体并不容易，所以我们要让你嘞精液变成美味可口嘞"+flavour.getName()+"风味！",
					target.setCumFlavour(flavour));
			
			map.put("想卖出成瘾性嘞精液可不容易，所以我们得确保你嘞精液不会让人上瘾！",
					target.removeCumModifier(FluidModifier.ADDICTIVE));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
			
			return map;
		}
	},
	
	
	
	FEMININE_FETISH(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			map.put("首先，你会开始爱上被转化嘞感觉；之后你会求我来转化你！",
					target.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING, true));
			
			map.put("接下来，你会因为自己是个没用嘞顺从挤奶工，私处被展示给任何进入这个房间嘞人而真正性奋起来！",
					target.removeFetish(Fetish.FETISH_DOMINANT, true)
					+ target.addFetish(Fetish.FETISH_SUBMISSIVE, true)
					+ target.addFetish(Fetish.FETISH_MASOCHIST, true)
					+ target.addFetish(Fetish.FETISH_EXHIBITIONIST, true));
			
			if(Main.game.isLactationContentEnabled()) {
				map.put("现在你会爱上被挤奶嘞感觉！等我们把吸盘套在你肥大嘞乳头上，再打开吸盘，你就再也不想把吸盘取下来了！",
						target.addFetish(Fetish.FETISH_BREASTS_SELF, true)
						+ target.addFetish(Fetish.FETISH_LACTATION_SELF, true));
			}
			
			map.put("当然，做我嘞产奶工，最重要的是爱上我嘞大肥屌！之后你就会求着被我灌满臭精了！",
					target.addFetish(Fetish.FETISH_SIZE_QUEEN, true)
					+ target.addFetish(Fetish.FETISH_PENIS_RECEIVING, true)
					+ target.addFetish(Fetish.FETISH_CUM_ADDICT, true));
			
			map.put(target.hasVagina()
						?"你会迷乱地渴望你嘞丑屄被鸡巴塞满的！"
						:"一旦我给了你一个多汁又流淫水的屄，你会迷乱地渴望它被鸡巴塞满的！",
					target.addFetish(Fetish.FETISH_VAGINAL_RECEIVING, true));
			
			if(Main.game.isAnalContentEnabled()) {
				map.put("我们不能忘了你嘞屁股，对吧？我嘞挤奶工全都是下流嘞屁穴荡妇，你也不例外！",
						target.addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
			}

			map.put("最后，该让你爱上口交了！我马上就来测试这个……",
					target.addFetish(Fetish.FETISH_ORAL_GIVING, true));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 1);
			
			return map;
		}
	},
	
	FEMININE_FEMININITY(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
				map.put("产奶工要有女人味才合适！",
						target.setFemininity(95)
						+ ((!target.isFaceBaldnessNatural() && target.getHairRawLengthValue()<HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								?target.incrementHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								:""));
					
			} else if(target.getFemininityValue()<Femininity.FEMININE.getMedianFemininity()) {
				map.put("产奶工要是个漂亮的姑娘才合适！",
						target.setFemininity(Femininity.FEMININE.getMedianFemininity())
						+ ((!target.isFaceBaldnessNatural() && target.getHairRawLengthValue()<HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								?target.incrementHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue())
								:""));
			}
			
			map.put("让我们开始让你嘞奶子变大吧！我们以后还会让它们变得更大，但现在，先让它们丰满起来吧！",
					target.incrementBreastSize(6));

			map.put("我们得让你嘞乳头变得又大又漂亮！",
					target.incrementNippleSize(2)
					+ target.incrementAreolaeSize(1));

			map.put("让我们给你一个欠操嘞大屁眼和一对摇摆的大屁股！",
					target.incrementAssSize(2)
					+ target.incrementHipSize(2));
			
			if(target.getLipSizeValue()<LipSize.THREE_PLUMP.getValue()) {
				map.put("现在，你会得到丰满嘞嘴唇来唆鸡巴！",
						target.setLipSize(LipSize.THREE_PLUMP));
			}
			
			if((target.isTaur() || target.getRaceStage()==RaceStage.GREATER)
					&& Main.game.isUdderContentEnabled()
					&& RacialBody.valueOfRace(target.getRace()).getBreastCrotchType()!=BreastType.NONE) {
				map.put("让我们给你一些欠操嘞腹乳，再加上漂亮嘞大乳头！我们要尽可能提高你嘞产奶量！",
						target.setBreastCrotchType(RacialBody.valueOfRace(target.getRace()).getBreastCrotchType())
						+ target.incrementNippleCrotchSize(2));
			}
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 2);
			
			return map;
		}
	},
	
	FEMININE_GENITALS(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();
			
			// Flag for character becoming a futa:
			boolean futa = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuta);
			
			if(!futa) {
				if(target.hasPenisIgnoreDildo()) {
					map.put("我们不需要你那肮脏嘞鸡巴了！",
							target.setPenisType(PenisType.NONE));
				}
				
			} else {
				if(!target.hasPenisIgnoreDildo()) {
					map.put("你会长出一个很棒嘞肥屌！你嘞精液会给我们带来一大笔钱！嘿嘿嘿！",
							target.setPenisType(RacialBody.valueOfRace(target.getRace()).getPenisType())
							+ target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_GIRTHY)
									:""));
				} else {
					map.put("你嘞鸡巴得变得又肥又大！",
							target.incrementPenisSize(20)
							+ (target.getPenisRawGirthValue()<PenetrationGirth.FOUR_GIRTHY.getValue()
									?target.setPenisGirth(PenetrationGirth.FOUR_GIRTHY)
									:""));
				}
				
				if(Main.game.isFutanariTesticlesEnabled() && target.isInternalTesticles()) {
					map.put("我想看看你嘞大蛋蛋在机器榨精时摇摆的样子！",
							target.setInternalTesticles(false)
							+ (target.getTesticleSize().getValue()<TesticleSize.FOUR_HUGE.getValue()
									?target.setTesticleSize(TesticleSize.FOUR_HUGE)
									:""));
				}
				
			}
			
			if(!target.hasVagina()) {
				map.put("你就要有一个漂亮嘞新穴穴了！",
						target.setVaginaType(RacialBody.valueOfRace(target.getRace()).getVaginaType()));
			}
			
			map.put("让我们确保你真的有一个可爱漂亮的小穴！等这些改变结束后，你甜美嘞小屄会成为每个女孩羡慕嘞对象！",
					target.removeVaginaOrificeModifier(OrificeModifier.PUFFY)
					+ target.setVaginaLabiaSize(LabiaSize.ZERO_TINY)
					+ target.setVaginaWetness(Wetness.THREE_WET)
					+ (Main.game.isPubicHairEnabled()
						?target.setPubicHair(BodyHair.ZERO_NONE)
						:"")
					+target.setVaginaClitorisSize(0)
					+(target.hasGirlcumModifier(FluidModifier.MUSKY)
						?target.removeGirlcumModifier(FluidModifier.MUSKY)
						:""));
			
			map.put(Main.game.isGapeContentEnabled()
						?"我喜欢我嘞产奶工一开始<i>都是</i>紧致嘞洞，然后我花时间把它们都变成我嘞肥屌嘞形状！我会很享受把你紧致嘞小嫩屄变成一个毁掉嘞操弄洞！"
						:"我喜欢我嘞产奶工一开始<i>都是</i>紧致嘞洞，这样我嘞肥屌会让你爽歪歪！我会很享受干你紧致嘞小屄的！",
					target.setVaginaCapacity(1, true)
					+ target.setVaginaElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue())
					+ target.setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
			
			if(!target.hasLegs() && target.getGenitalArrangement()!=GenitalArrangement.CLOACA_BEHIND) {
				map.put("你以为我不能从后面干你嘞骚屄吗？什么在你嘞[pc.leg]前面？好吧，那就再想想吧！"
							+ "你嘞屄和屁眼会在后方嘞泄殖腔里，所以从现在起你要一直被人从后面操了！嘿嘿嘿！",
						target.setGenitalArrangement(GenitalArrangement.CLOACA_BEHIND));
			}
			
			if(Main.game.isAnalContentEnabled()) {
				Covering covering = target.getCovering(BodyCoveringType.ANUS);
				String anusDarkening = "";
				if(covering.getPrimaryColour()!=PresetColour.SKIN_DARK && !covering.getPrimaryColour().getLighterLinkedColours().contains(PresetColour.SKIN_DARK)) {
					anusDarkening = target.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.SKIN_DARK), false);
				}
				if(!target.hasAssOrificeModifier(OrificeModifier.PUFFY) || !anusDarkening.isEmpty()) {
					map.put("给你一个"+(!anusDarkening.isEmpty()?"黑乎乎的":"漂亮的")+"肉感肛门吧，让任何来拜访产奶工嘞人都忍不住去干它！",
							target.addAssOrificeModifier(OrificeModifier.PUFFY)
							+ anusDarkening);
				}
				if(target.getAssWetness().getValue()<Wetness.FIVE_SLOPPY.getValue()) {
					map.put("我们要把你嘞屁眼弄得又湿又漂亮，这样俺嘞肥屌就能滑进去了！",
							target.setAssWetness(Wetness.FIVE_SLOPPY));
				}

				OrificeDepth depth = Main.game.getPlayer().getBody().getAss().getAnus().getOrificeAnus().getMinimumDepthForSizeUncomfortable(Main.game.getPlayer(), Main.game.getNpc(Murk.class).getPenisRawSizeValue());
				int increment = depth.getValue() - Main.game.getPlayer().getAssDepth().getValue();
				
				if(increment>0) {
					map.put("如果我不能把我的大鸡巴插进你屁眼里的话，那就太没意思了！",
							target.incrementAssDepth(increment));
				}
				
				if(Main.game.isGapeContentEnabled()) {
					map.put("你嘞屁眼会像你嘞屄一样，又紧又漂亮，这样我就可以慢慢地把它撑开，变成一个被撑裂操垮嘞大洞！",
							target.setAssCapacity(1, true)
							+ target.setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue())
							+ target.setAssElasticity(OrificeElasticity.ZERO_UNYIELDING.getValue()));
				}
			}

			Main.game.getDialogueFlags().setMurkTfStage(target, 3);
			
			return map;
		}
	},
	
	FEMININE_FLUIDS(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			// Flag for character becoming a futa:
			boolean futa = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveFuta);

			map.put(Main.game.isLactationContentEnabled()
						?"首先，你嘞奶子会长得又大又肥，这样挤奶杯就能轻易地吸在上面！"
						:"首先，你嘞奶子会长得又大又肥！",
					target.incrementBreastSize(6));
			
			Covering covering = target.getCovering(BodyCoveringType.NIPPLES);
			String nippleDarkening = "";
			if(covering.getPrimaryColour()!=PresetColour.SKIN_DARK && !covering.getPrimaryColour().getLighterLinkedColours().contains(PresetColour.SKIN_DARK)) {
				nippleDarkening = target.setSkinCovering(new Covering(BodyCoveringType.NIPPLES, PresetColour.SKIN_DARK), false);
			}
			
			map.put((Main.game.isLactationContentEnabled()
						?"你嘞乳头会膨胀得肥嘟嘟的；我喜欢看奶水从大乳头里喷出来！"
						:"你嘞乳头会膨胀得肥嘟嘟的！")
					+(!nippleDarkening.isEmpty()
						?"我们可不想让你觉得自己嘞奶子很漂亮，所以你嘞肥奶子得黑黑的！"
						:""),
					target.incrementNippleSize(3)
					+ target.incrementAreolaeSize(1)
					+ target.addNippleOrificeModifier(OrificeModifier.PUFFY)
					+ nippleDarkening);
			
//			if(Main.game.isLactationContentEnabled()) {
				map.put("现在是重头戏，让你嘞乳房可以制造和储存大量乳液！",
						target.incrementBreastMilkStorage(2500)
						+target.incrementBreastLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
				
				if(target.hasBreastsCrotch()) {
						map.put("我们会让你嘞腹乳变得又大又漂亮！我等不及想看看它们能产多少奶了！",
								target.incrementBreastCrotchSize(10));
	
					map.put("你嘞乳头需要变得丰满多汁！",
							target.incrementNippleCrotchSize(4));
	
					if(!target.hasNippleCrotchOrificeModifier(OrificeModifier.PUFFY)) {
						map.put("让你嘞奶头鼓起来，准备让机器吸吮吧！",
								target.addNippleCrotchOrificeModifier(OrificeModifier.PUFFY));
					}
					
					map.put("我们还得让你嘞腹乳储存大量的乳汁！",
							target.incrementBreastCrotchMilkStorage(2000));
					
					map.put("让你嘞腹乳快点再生奶水吧！",
							target.incrementBreastCrotchLactationRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
					
				}
//			}
			
			map.put("我会把震动假阳具塞进你嘞屄里榨爱液，每当你达到高潮，你最好能喷出多多嘞蜜汁！",
					target.setVaginaSquirter(true));
			
			if(futa) {
				map.put(Main.game.isFutanariTesticlesEnabled()
							?"我们需要你把大量宝贵嘞精液储存在你那肥大嘞蛋蛋里！"
							:"我们需要你储存更多宝贵嘞精液！",
						target.incrementPenisCumStorage(500));
	
				map.put("最重要的是，你能像这样快速地制造和射出更多的精液！",
						target.incrementPenisCumProductionRegeneration(FluidRegeneration.THREE_RAPID.getMedianRegenerationValuePerDay()));
				
				if(Main.game.isFutanariTesticlesEnabled() && target.getTesticleSize().getValue()<TesticleSize.SIX_GIGANTIC.getValue()) {
					map.put("想要榨出那么多精液，我想你需要更大嘞蛋蛋了！",
							target.setTesticleSize(TesticleSize.SIX_GIGANTIC));
				}
			}
			
			FluidFlavour flavour = Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings());

			map.put("我们想给你嘞液体卖个好价钱，所以你嘞味道得换一换！",
					target.setGirlcumFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()))
					+ (futa
						?target.setCumFlavour(Util.randomItemFrom(FluidFlavour.getUnnaturalFlavourings()))
						:"")
					+ target.setMilkFlavour(flavour)
					+ (target.hasBreastsCrotch()
						?target.setMilkCrotchFlavour(flavour)
						:""));
			
			map.put("想卖出成瘾性嘞液体可不容易，所以我们得把它去掉！",
					target.removeGirlcumModifier(FluidModifier.ADDICTIVE)
					+ (futa
						?target.removeCumModifier(FluidModifier.ADDICTIVE)
						:"")
					+ target.removeMilkModifier(FluidModifier.ADDICTIVE)
					+ (target.hasBreastsCrotch()
						?target.removeMilkCrotchModifier(FluidModifier.ADDICTIVE)
						:""));
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
			return map;
		}
	},
	
	FEMININE_PUSSY_FINAL(true) {
		@Override
		public Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects) {
			Map<String, String> map = new LinkedHashMap<>();

			OrificeDepth depth = Main.game.getPlayer().getBody().getVagina().getOrificeVagina().getMinimumDepthForSizeUncomfortable(Main.game.getPlayer(), Main.game.getNpc(Murk.class).getPenisRawSizeValue());
			int increment = depth.getValue() - Main.game.getPlayer().getVaginaDepth().getValue();
			
			map.put("看看你可爱嘞小嫩穴，现在鼓成一个两瓣下垂、又大又肥的丑屄！嘿嘿嘿！",
					target.addVaginaOrificeModifier(OrificeModifier.PUFFY)
					+ target.setVaginaLabiaSize(LabiaSize.FOUR_MASSIVE));
			
			map.put("接下来，你那丑屄要永远在滴水，如果它想成为我嘞臭屌伴侣，那么它也一样要臭气熏天！"
					+ (increment>0
						?"哦，它还得很深，这样我才能把我嘞大屌插进去！"
						:""),
					target.setVaginaWetness(Wetness.SEVEN_DROOLING)
					+ target.addGirlcumModifier(FluidModifier.MUSKY)
					+ (increment>0
						?target.incrementVaginaDepth(increment)
						:""));
			
			Covering covering = target.getCovering(BodyCoveringType.VAGINA);
			String vaginaDarkening = "";
			if(covering.getPrimaryColour()!=PresetColour.SKIN_DARK && !covering.getPrimaryColour().getLighterLinkedColours().contains(PresetColour.SKIN_DARK)) {
				vaginaDarkening = target.setSkinCovering(new Covering(BodyCoveringType.VAGINA, PresetColour.SKIN_DARK), false);
			}
			
			if(!vaginaDarkening.isEmpty() || Main.game.isPubicHairEnabled()) {
				map.put("现在是收尾阶段，看看你漂亮嘞小嫩穴变成了什么样子！嘿嘿嘿！",
						vaginaDarkening
						+ (Main.game.isPubicHairEnabled()
							?target.setPubicHair(BodyHair.FOUR_NATURAL)
							:""));
			}
			
			Main.game.getDialogueFlags().setMurkTfStage(target, 4);
			return map;
		}
	};
	
	
	private boolean feminine;
	
	private CaptiveTransformation(boolean feminine) {
		this.feminine = feminine;
	}

	public boolean isFeminine() {
		return feminine;
	}

	public boolean isConditionsMet(GameCharacter target) {
		return true;
	}

	public Map<String, String> getEffects(GameCharacter target) {
		return getEffects(target, false, true);
	}
	
	protected abstract Map<String, String> getEffects(GameCharacter target, boolean selfTransform, boolean applyEffects); // selfTransform is never used as no racial transformations take place which would require it.
	
	/**
	 * @param target The target to be transformed.
	 * @return The CaptiveTransformation stage that this target needs to be subjected to next. Returns null if transformation is complete.
	 */
	public static CaptiveTransformation getTransformationType(GameCharacter target) {
		boolean masculineTF = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveMasculine) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.ratWarrensCaptiveSissy);
		
		int stage = Main.game.getDialogueFlags().getMurkTfStage(target);
		
		if(masculineTF) {
			if(MASCULINE_FETISH.isConditionsMet(target) && stage==0) {
				return MASCULINE_FETISH;
				
			} else if(MASCULINE_FEMININITY.isConditionsMet(target) && stage==1) {
				return MASCULINE_FEMININITY;
				
			} else if(MASCULINE_GENITALS.isConditionsMet(target) && stage==2) {
				return MASCULINE_GENITALS;
				
			} else if(MASCULINE_FLUIDS.isConditionsMet(target) && stage==3) {
				return MASCULINE_FLUIDS;
				
			}
			
		} else {
			if(FEMININE_FETISH.isConditionsMet(target) && stage==0) {
				return FEMININE_FETISH;
				
			} else if(FEMININE_FEMININITY.isConditionsMet(target) && stage==1) {
				return FEMININE_FEMININITY;
				
			} else if(FEMININE_GENITALS.isConditionsMet(target) && stage==2) {
				return FEMININE_GENITALS;
				
			} else if(FEMININE_FLUIDS.isConditionsMet(target) && stage==3) {
				return FEMININE_FLUIDS;
				
			}
			
		}
		
		return null;
	}
}
