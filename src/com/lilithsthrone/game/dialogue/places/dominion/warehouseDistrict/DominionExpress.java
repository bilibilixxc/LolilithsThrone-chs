package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisLength;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.DominionExpressCentaur;
import com.lilithsthrone.game.character.npc.dominion.Natalya;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.PossibleItemEffect;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpress;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public class DominionExpress {
	
	private static SexAreaInterface sleepSexAreaWanted = null;
	private static int slavePointsReward = 1;
	private static GameCharacter activeSlave;

	private static List<MuleReward> muleRewards;
	static {
		muleRewards = new ArrayList<>();
		// Basic mule: Add horse-morph penis/ass, feminine++, boobs++:
		muleRewards.add(
				new MuleReward("基础[style.Mule]", "消去你的小穴，获得马化形的鸡巴和屁股、巨乳以及女性化的身材！", 5) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BASIC_FILLY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().setVaginaType(VaginaType.NONE));
						sb.append(Main.game.getPlayer().setAssType(AssType.HORSE_MORPH));
						sb.append(Main.game.getPlayer().setPenisType(PenisType.EQUINE));
						if(Main.game.getPlayer().getFemininityValue()<75) {
							sb.append(Main.game.getPlayer().setFemininity(75));
						}
						if(Main.game.getPlayer().getBreastSize().getMeasurement()<CupSize.D.getMeasurement()) {
							sb.append(Main.game.getPlayer().setBreastSize(CupSize.D));
							if(Main.game.getPlayer().getNippleSize().getValue()<NippleSize.TWO_BIG.getValue()) {
								Main.game.getPlayer().setNippleSize(NippleSize.TWO_BIG);
							}
							if(Main.game.getPlayer().getAreolaeSize().getValue()<AreolaeSize.TWO_BIG.getValue()) {
								Main.game.getPlayer().setAreolaeSize(AreolaeSize.TWO_BIG);
							}
						}
						return sb.toString();
					}
				});
		// Taur: Makes lower body taur:
		muleRewards.add(
				new MuleReward("半人马化", "成为半人马，获得你梦寐以求的兽态下半身！", 5) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						if(Main.game.getPlayer().getLegConfiguration()==LegConfiguration.QUADRUPEDAL) {
							return new Value<>(false, "你已经是半兽身人了，所以该奖励没有作用！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_TAUR"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						if(Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
							if(Main.game.getPlayer().getLegType()!=LegType.DEMON_HORSE_HOOFED) {
								sb.append(Main.game.getPlayer().setLegType(LegType.DEMON_HORSE_HOOFED));
							}
							sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.QUADRUPEDAL, true));
						} else {
							if(Main.game.getPlayer().getLegType()!=LegType.HORSE_MORPH) {
								sb.append(Main.game.getPlayer().setLegType(LegType.HORSE_MORPH));
							}
							sb.append(Main.game.getPlayer().setLegConfiguration(LegConfiguration.QUADRUPEDAL, true));
						}
						return sb.toString();
					}
				});
		// Ass-pussy: Lubrication, puffy, depth, muscles.
		muleRewards.add(
				new MuleReward("屁穴", "将你的肛门转化成完美的性爱用腔穴！", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_ASS_PUSSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementAssWetness(3));
						sb.append(Main.game.getPlayer().incrementAssDepth(3));
						sb.append(Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().addAssOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
						return sb.toString();
					}
				});
		// Throat-pussy: Bigger lips, wetter throat, depth, muscles.
		muleRewards.add(
				new MuleReward("喉穴", "从此以后，吮吸马屌的感觉就再也不一样了！", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_THROAT_PUSSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementFaceWetness(3));
						sb.append(Main.game.getPlayer().incrementFaceDepth(3));
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.MUSCLE_CONTROL));
						return sb.toString();
					}
				});
		// Ass-licker: Bigger lips, wetter tongue, longer tongue.
		muleRewards.add(
				new MuleReward("舔肛者", "大而丰满的嘴唇和长而有力的舌头，向给那些马屁股应得的宠爱，就得需要这些！", 15) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_ASS_LICKER"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementFaceWetness(3));
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						sb.append(Main.game.getPlayer().incrementTongueLength(10));
						sb.append(Main.game.getPlayer().addTongueModifier(TongueModifier.STRONG));
						return sb.toString();
					}
				});
		// Hung: Penis size+, more cum, bigger balls.
		muleRewards.add(
				new MuleReward("晃荡马屌", "你喜欢被上的时候有一根粗大的阴茎在身下来回摆动！", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							return new Value<>(false, "你需要拥有阴茎，才能获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_HUNG"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementPenisSize(10));
						if(Main.game.getPlayer().getPenisGirth().getValue()<PenetrationGirth.FIVE_THICK.getValue()) {
							sb.append(Main.game.getPlayer().incrementPenisGirth(1));
						}
						if(Main.game.getPlayer().getTesticleSize().getValue()<TesticleSize.FIVE_MASSIVE.getValue()) {
							sb.append(Main.game.getPlayer().incrementTesticleSize(1));
						}
						sb.append(Main.game.getPlayer().incrementPenisCumStorage(50));
						return sb.toString();
					}
				});
		// Sissy mule: Penis size-, smaller balls.
		muleRewards.add(
				new MuleReward("娘炮[style.mule]", "两腿间夹着一根小得可怜的鸡巴，向你的伴侣展示你是个多么顺从的娘炮[style.mule]！", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
							return new Value<>(false, "你需要拥有阴茎，才能获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_SISSY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						if(Main.game.getPlayer().getPenisRawSizeValue()>PenisLength.ONE_TINY.getMedianValue()) {
							if(Main.game.getPlayer().getPenisRawSizeValue()>PenisLength.ONE_TINY.getMedianValue()+10) {
								sb.append(Main.game.getPlayer().incrementPenisSize(-10));
							} else {
								sb.append(Main.game.getPlayer().setPenisSize(PenisLength.ONE_TINY.getMedianValue()));
							}
						}
						if(Main.game.getPlayer().getPenisGirth().getValue()>PenetrationGirth.THREE_AVERAGE.getValue()) {
							sb.append(Main.game.getPlayer().incrementPenisGirth(-1));
						}
						if(Main.game.getPlayer().getTesticleSize().getValue()>TesticleSize.ONE_TINY.getValue()) {
							sb.append(Main.game.getPlayer().incrementTesticleSize(-1));
						}
						return sb.toString();
					}
				});
		// Top-heavy: Breast size+, puffy nipples, nipple size+
		muleRewards.add(
				new MuleReward("上身沉甸", "增大你的胸部，让那些半人马奴隶有东西可看(也可以捏)！", 25) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_TOP_HEAVY"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_END"));
						sb.append(Main.game.getPlayer().incrementBreastSize(3));
						sb.append(Main.game.getPlayer().incrementNippleSize(1));
						sb.append(Main.game.getPlayer().incrementAreolaeSize(1));
						sb.append(Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY));
						return sb.toString();
					}
				});
		// Subby slut: submissive, oral giving, anal giving, penis receiving, anal receiving
		muleRewards.add(
				new MuleReward("温驯荡妇", "来帮你牢牢记住自己是一个多么下流的[style.mule]小婊子吧！", 50) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_SUBBY"));
						sb.append(Main.game.getPlayer().removeFetish(Fetish.FETISH_DOMINANT, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_PENIS_RECEIVING, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						return sb.toString();
					}
				});
		// Bimbo: Add bimbo fetish, big lips, bleach-blonde hair, breast size+
		muleRewards.add(
				new MuleReward("无脑大胸", "除了屈服于马屌和马屁股之外，还需要去想别的事情，那可太讨厌了，不是吗？", 50) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							return new Value<>(false, "你需要能够触碰你的口腔，来获得这个奖励！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(this.getName(), true);
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), false);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BIMBO"));
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_GENERIC_MENTAL_END"));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_BIMBO, true));
						sb.append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE, true));
						
						if(Main.game.getPlayer().getFemininityValue()<95) {
							sb.append(Main.game.getPlayer().setFemininity(95));
						}
						
						sb.append(Main.game.getPlayer().incrementLipSize(3));
						sb.append(Main.game.getPlayer().addFaceOrificeModifier(OrificeModifier.PUFFY));
						
						sb.append(Main.game.getPlayer().incrementBreastSize(6));
						sb.append(Main.game.getPlayer().incrementNippleSize(1));
						sb.append(Main.game.getPlayer().incrementAreolaeSize(1));
						sb.append(Main.game.getPlayer().addNippleOrificeModifier(OrificeModifier.PUFFY));

						sb.append(Main.game.getPlayer().setHairCovering(
								new Covering(Main.game.getPlayer().getHairType().getBodyCoveringType(Main.game.getPlayer()), CoveringPattern.HIGHLIGHTS, PresetColour.COVERING_BLONDE, false, PresetColour.COVERING_BLEACH_BLONDE, false), false));
						
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_TRANSFORMATION_BIMBO_END"));
						Main.game.getPlayer().addStatusEffect(StatusEffect.LOLLIPOP_SUCKING, 60*20);
						
						return sb.toString();
					}
				});
		
		muleRewards.add(
				new MuleReward("银质项圈", "获得这个闪闪发亮的升级！有了这个之后，你允许为主人娜塔莉亚提供性服务，每天一次！", 100) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(getPlayerCollar().getColour(0)!=PresetColour.CLOTHING_BRONZE) {
							return new Value<>(false, "你已经把项圈升级成银色了！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), true);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_SILVER_COLLAR"));
						getPlayerCollar().setColour(0, PresetColour.CLOTHING_SILVER);
						return sb.toString();
					}
				});
		
		muleRewards.add(
				new MuleReward("金质项圈", "主人娜塔莉亚答应任何黄金级的[style.mule]都可以支配她，但说实话，你根本不可能买得起这个！", 1000) {
					@Override
					public Value<Boolean, String> isAvailable() {
						if(getPlayerCollar().getColour(0)==PresetColour.CLOTHING_GOLD) {
							return new Value<>(false, "你已经把项圈升级成金色了！");
						}
						if(getPlayerCollar().getColour(0)!=PresetColour.CLOTHING_SILVER) {
							return new Value<>(false, "你要先把项圈升级成银色，才能再升级成金色！");
						}
						return new Value<>(true, "");
					}
					@Override
					public String applyEffect() {
						UtilText.addSpecialParsingString(Util.intToString(this.getCost()), true);
						StringBuilder sb = new StringBuilder(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_REWARD_GOLD_COLLAR"));
						getPlayerCollar().setColour(0, PresetColour.CLOTHING_GOLD);
						return sb.toString();
					}
				});
	}
	
	
	private static AbstractClothing getPlayerCollar() {
		return Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
	}
	
	private static boolean wearingMuleCollar() {
		return getPlayerCollar()!=null && getPlayerCollar().getClothingType().getId().equals("innoxia_neck_filly_choker");
	}
	
	private static AbstractClothing generateCollar() {
		return Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_neck_filly_choker"), false);
	}
	
	private static boolean isPlayerBodyCorrect() {
		return Main.game.getPlayer().isFeminine()
				&& Main.game.getPlayer().getBreastSize().getMeasurement()>=CupSize.D.getMeasurement()
				&& Main.game.getPlayer().hasPenis()
				&& !Main.game.getPlayer().hasVagina();
	}
	
	private static String applyTransformation() {
		StringBuilder sb =  new StringBuilder();
		
		TransformativePotion potion = Main.game.getNpc(Natalya.class).generateTransformativePotion(Main.game.getPlayer());

		if(Main.game.getPlayer().isAbleToHaveRaceTransformed()) {
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.COVERING_BLACK), false);
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.COVERING_BLACK), false);
		}
		
		for(PossibleItemEffect pe : potion.getEffects()) {
			sb.append(pe.getEffect().applyEffect(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), 1));
		}
		
		return sb.toString();
	}
	
	public static GameCharacter spawnSlave(boolean feminine) {
		return spawnSlave(feminine, null);
	}
	
	public static GameCharacter spawnSlave(boolean feminine, Colour collarColour) {
		slavePointsReward = 2+Util.random.nextInt(4); // Slaves give the player 2-5 points to service them
		
		NPC npc;
		if(collarColour==null) {
			collarColour = PresetColour.CLOTHING_GOLD;
			double rnd = Math.random();
			if(rnd<0.8f) {
				collarColour = PresetColour.CLOTHING_BRONZE;
			} else if(rnd<0.95f) {
				collarColour = PresetColour.CLOTHING_SILVER;
			}
		}
		if(feminine) {
			npc = new DominionExpressCentaur(Gender.F_P_B_SHEMALE, collarColour);
		} else {
			npc = new DominionExpressCentaur(Gender.M_P_MALE, collarColour);
		}
		npc.setLocation(Main.game.getPlayer(), false);
		
		npc.setLastTimeOrgasmedSeconds(Main.game.getSecondsPassed()-(25*60*60));
		npc.removeCumModifier(FluidModifier.ADDICTIVE);
		npc.removeCumModifier(FluidModifier.HALLUCINOGENIC);
		
		try {
			Main.game.addNPC(npc, false);
			Main.game.setActiveNPC(npc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.getNpc(Natalya.class).addSlave(npc);
		
		return npc;
	}
	
	public static void applySadistSlave(GameCharacter slave) {
		slave.addFetish(Fetish.FETISH_SADIST);
		
		slave.removePersonalityTraits(PersonalityCategory.SPEECH);
		
		slave.setName(new NameTriplet("雷霆"));
		slave.setPlayerKnowsName(true);

		slave.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_OLIVE), true);
		slave.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, PresetColour.COVERING_BROWN_DARK), true);
		slave.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		slave.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		
		slave.setSkinCovering(new Covering(BodyCoveringType.EYE_HUMAN, PresetColour.EYE_HAZEL), false);
		slave.setSkinCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_HAZEL), false);
		
		if(slave.isFeminine()) {
			slave.setHairLength(HairLength.FOUR_MID_BACK);
			slave.setHairStyle(HairStyle.BRAIDED);
		} else {
			slave.setHairLength(HairLength.ONE_VERY_SHORT);
			slave.setHairStyle(HairStyle.NONE);
		}
		
		slave.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.COVERING_BLACK), false);
		slave.setSkinCovering(new Covering(BodyCoveringType.ANUS, PresetColour.COVERING_BLACK), false);
		
		slave.setHeight(235);
		
		Main.game.getDialogueFlags().setSadistNatalyaSlave(slave.getId());
	}
	
	private static List<GameCharacter> getSavedSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION_EXPRESS).getCell(PlaceType.DOMINION_EXPRESS_STABLES)));
		list.removeIf((npc)->!(npc instanceof DominionExpressCentaur));
		return list;
	}
	
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> list = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		list.removeIf((npc)->!(npc instanceof DominionExpressCentaur));
		return list;
	}

	private static GameCharacter getSadistSlave() {
		try {
			return Main.game.getNPCById(Main.game.getDialogueFlags().getSadistNatalyaSlave());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static void banishSlave(GameCharacter slave, boolean delete) {
		if(delete) {
			Main.game.banishNPC((NPC) slave);
			
		} else {
			slave.setHomeLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_STABLES);
			slave.returnToHome();
		}
		activeSlave = null;
	}
	
	public static final DialogueNode INITIAL_ENTRANCE = new DialogueNode("", "", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
				return ENTRANCE.getResponse(responseTab, index);
				
			} else {
				if(index==0) {
					return new Response("离开", "告诉接待员你走错了路，转身返回仓库区域。", Warehouses.WAREHOUSE_DISTRICT) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
						}
					};
					
				} else if(index==1) {
					return new Response("展示卡片", "给接待员看看你从娜塔莉亚手里得到的卡片。", INITIAL_ENTRANCE_CARD_SHOWN);
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode INITIAL_ENTRANCE_CARD_SHOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "INITIAL_ENTRANCE_CARD_SHOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ENTRANCE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ENTRANCE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "ENTRANCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "转身返回仓库区域。", Warehouses.WAREHOUSE_DISTRICT) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode CORRIDOR = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(wearingMuleCollar()) {
				if(index==1) {
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("吸引注意力", "因为你不能使用你的嘴，你没法服务任何半人马奴隶，没办法吸引他们的注意力……", null);
						
					} else {
						return new ResponseEffectsOnly(
								"寻找同伴",
								"你展示着脖子上的[style.mule]项圈，你知道自己能轻易地吸引过路的半人马奴隶的注意……"){
								@Override
								public int getSecondsPassed() {
									return 5*60;
								}
								@Override
								public void effects() {
									DialogueNode dn = Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getDialogue(true, true);
									Main.game.setContent(new Response("", "", dn));
								}
							};
					}
				}
			}
			return null;
		}
	};

	public static final DialogueNode STORAGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STORAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return CORRIDOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode FILLY_STATION = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("机器", "靠近机器看看能不能和它互动……", FILLY_STATION_MACHINE);
				
			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==FILLY_STATION_POSTER) {
					return new Response("海报", "你已经在读信息很多的海报了……", null);
				}
				return new Response("海报", "奥术机器旁边的墙上贴着一张信息很多的海报，读读它。", FILLY_STATION_POSTER);
			}
			return null;
		}
	};

	public static final DialogueNode FILLY_STATION_POSTER = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_POSTER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return FILLY_STATION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode FILLY_STATION_MACHINE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE"));
			
			if(!wearingMuleCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE_REJECTED"));
				
			} else {
				UtilText.addSpecialParsingString(String.valueOf(Main.game.getDialogueFlags().getNatalyaPoints()), true);
				Colour collarColour = getPlayerCollar().getColour(0);
				UtilText.addSpecialParsingString("<span style='color:"+collarColour.toWebHexString()+";'>"+Util.capitaliseSentence(collarColour.getName())+"</span>", false);
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FILLY_STATION_MACHINE_ACCEPTED"));
				
				sb.append("<div class='container-full-width'>");
					for(MuleReward fr : muleRewards) {
						int points = Main.game.getDialogueFlags().getNatalyaPoints();
						Value<Boolean, String> available = fr.isAvailable();
						sb.append("<div class='container-full-width inner' style='margin-bottom:4px;'>");
							sb.append((available.getKey()?"[style.boldPink(":"[style.boldBad(")+fr.getName()+":)] ");
							sb.append((points<fr.getCost()?"[style.colourBad(":"[style.colourPinkLight(")+fr.getCost()+")][style.colourPinkLight([style.Mule]点数)]<br/>");
							sb.append(fr.getDescription());
							if(!available.getKey()) {
								sb.append("<br/>[style.italicsBad("+available.getValue()+")]");
							}
						sb.append("</div>");
					}
				sb.append("</div>");
			}
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("退开", "从机械旁边退开……", FILLY_STATION);
			}
			if(wearingMuleCollar()) {
				List<Response> responses = new ArrayList<>();
				for(MuleReward fr : muleRewards) {
					int points = Main.game.getDialogueFlags().getNatalyaPoints();
					Value<Boolean, String> available = fr.isAvailable();
					if(!available.getKey()) {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsBad("+available.getValue()+")]", null));
						
					} else if(points<fr.getCost()) {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsBad(你"+(points==0?"没有":"只有"+points)+"[style.mule]点数，付不起"+fr.getCost()+"！)]", null));
						
					} else {
						responses.add(new Response(fr.getName()+" ("+fr.getCost()+")", fr.getDescription()+"<br/>[style.italicsPinkLight(你拥有"+points+"[style.mule]点数，这将花费你"+fr.getCost()+"！)]", FILLY_STATION_REWARD) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(fr.applyEffect());
								Main.game.getTextStartStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-fr.getCost()));
							}
						});
					}
				}
				if(index-1<responses.size()) {
					return responses.get(index-1);
				}
			}
			return null;
		}
	};

	public static final DialogueNode FILLY_STATION_REWARD = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续与[style.mule]奖品站互动。", FILLY_STATION_MACHINE);
			}
			return null;
		}
	};
	
	
	public static final DialogueNode STABLES = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES"));
			if(!wearingMuleCollar()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_NO_COLLAR"));
			} else if(!isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_WRONG_BODY"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTER"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
					if(!wearingMuleCollar()) {
						return new Response("进入", "你没有佩戴[style.mule]项圈，不能见娜塔莉亚小姐！", null);
					} else if(!isPlayerBodyCorrect()) {
						return new Response("进入", "你没有丰满的[style.shemale]，不能和娜塔莉亚小姐见面！", null);
					}
					return new Response("进入", "进入马厩，寻找半人马奴隶提供服务……", STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_ENTERED"));
						}
					};
					
				} else {
					return new Response("进入", "因为你不是奴隶或合格的[style.mule]，不能进入马厩……", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLES_INTERIOR = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "马厩";
			} else if(index==1) {
				return "最爱";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "离开马厩，回到主仓库……", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLES_EXIT"));
					}
				};
				
			}
			if(responseTab==0) {
				if(index==1) {
					if(getSavedSlaves().size()>=10) {
						return new Response("半人马", "你喜爱的奴隶数量已达到最大值(10)……", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("半人马", "你无法触碰你的嘴，所以不能服务任何奴隶……", null);
					}
					return new Response("半人马", "寻找一位半人马提供服务……", STABLE_SEX) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.MASCULINE;
						}
						@Override
						public void effects() {
							activeSlave = spawnSlave(false);
							activeSlave.setPlayerKnowsName(true);
							UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", activeSlave));
						}
					};
					
				} else if(index==2) {
					if(getSavedSlaves().size()>=10) {
						return new Response("半人马", "喜爱你的奴隶已达到最大值(10)……", null);
					}
					if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("半人马",  "你无法控制你的嘴，因此也无法服务任何奴隶……", null);
					}
					return new Response("女半人马", "寻找一位女半人马提供服务……", STABLE_SEX) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.FEMININE;
						}
						@Override
						public void effects() {
							activeSlave = spawnSlave(true);
							activeSlave.setPlayerKnowsName(true);
							UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER", activeSlave));
						}
					};
					
				} else if(index==5) {
					if(Main.game.getCurrentDialogueNode()==STABLE_SHOWER) {
						return new Response("洗澡", "你刚刚才洗过澡！", null);
					}
					return new Response("洗澡",
								"你可以在在马场的淋浴设施里清洗干净。"
									+ "<br/>[style.italicsGood(从所有腔穴中清理<b>最多"+Units.fluid(500)+"</b>液体。)]"
									+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
								STABLE_SHOWER);
				}
				
			} else if(responseTab==1) {
				List<Response> responses = new ArrayList<>();
				for(GameCharacter slave : getSavedSlaves()) {
					if(!getSlaves().contains(slave)) {
						responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "[npc.Name]目前出差在外，无法与你做爱……"), null));
						
					} else {
						responses.add(new Response(UtilText.parse(slave, "[npc.Name]"), UtilText.parse(slave, "找到[npc.namePos]的摊位，和[npc.herHim]做爱……"), STABLE_SEX) {
							@Override
							public void effects() {
								activeSlave = slave;
								UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);
								if(activeSlave.equals(getSadistSlave())) {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER_SADIST", activeSlave));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_FIND_PARTNER_REPEAT", activeSlave));
								}
							}
						});
					}
				}
				for(int i=0; i<responses.size(); i++) {
					if(index-1==i) {
						return responses.get(i);
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STABLE_SEX = new DialogueNode("", "", true) {
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
				return new ResponseSex(
						"吮吸鸡巴",
						UtilText.parse(activeSlave, "跪下来嗦[npc.namePos]的鸡巴。"),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(activeSlave, new HashMap<>());
								map.get(activeSlave).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(activeSlave).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ORAL")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), activeSlave, PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"吻肛",
						UtilText.parse(activeSlave, "跪向[npc.name]，给[npc.herHim]舔肛。"),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_ANILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), activeSlave, TongueAnus.ANILINGUS_START, false, true));
					}
				};
				
			} else if(index==3) {
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
					return new Response("顶入", UtilText.parse(activeSlave, "你无法控制肛门，没法让[npc.name]进入……"), null);
				}
				return new ResponseSex(
						"被骑",
						UtilText.parse(activeSlave, "把你[pc.asshole+]露给[npc.name]看，让[npc.herHim]进入你。"),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(activeSlave, new HashMap<>());
								map.get(activeSlave).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(activeSlave).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						AFTER_STABLE_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SEX_MOUNTED")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX = new DialogueNode("结束", "奴隶和你做完了……", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(activeSlave, "[npc.Name]和你做够了……");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(slavePointsReward));
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX", activeSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开 ([style.colourMinorGood(Favourite)])",
						UtilText.parse(activeSlave, "你给[npc.namePos]的摊位做了标记，以便你回马场时再次拜访[npc.herHim]。"
								+ "<br/>[style.colourMinorGood(你可以再次拜访[npc.Name]了！)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, false);
					}
				};
				
			} else if(index==2) {
				if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
					return new Response("离开(忘记)", UtilText.parse(activeSlave, "你无法忘记[npc.name]……"), null);
				}
				return new Response("离开 ([style.colourMinorBad(Forget)])",
						UtilText.parse(activeSlave, "你离开[npc.her]的摊位，转身回马场，不打算去拜访[npc.Name]。"
								+ "<br/>[style.colourMinorBad(你无法再拜访[npc.name]了！)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, true);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getHourOfDay()>4 && Main.game.getHourOfDay()<22) {
					return new Response("睡觉", UtilText.parse(activeSlave, "你只能在[style.time(22)]到[style.time(4)]之间与奴隶一起睡觉。"), null);
					
				} else {
					return new Response("睡觉", UtilText.parse(activeSlave, "接受[npc.namePos]的过夜邀请。"), AFTER_STABLE_SEX_SLEEP);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_STABLE_SEX_SLEEP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			List<SexAreaInterface> list = Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				list.remove(SexAreaOrifice.MOUTH);
				list.remove(SexAreaPenetration.TONGUE);
			}
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				list.remove(SexAreaOrifice.ANUS);
			}
			if(list.isEmpty() || Math.random()<0.1f) {
				sleepSexAreaWanted = null;
			} else {
				sleepSexAreaWanted = Util.randomItemFrom(list);
			}
			if(sleepSexAreaWanted==null) {
				Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
			}
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(6*60)*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP", activeSlave));
			if(sleepSexAreaWanted==null) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_NO_SEX", activeSlave));
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_BLOWJOB", activeSlave));
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_ANILINGUS", activeSlave));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_MOUNTED", activeSlave));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(sleepSexAreaWanted==null) {
				if(index==1) {
					return new Response("离开 ([style.colourMinorGood(Favourite)])",
							UtilText.parse(activeSlave, "你给[npc.namePos]的摊位做了标记，以便你回马场时再次拜访[npc.herHim]。"
									+ "<br/>[style.colourMinorGood(你可以再次拜访[npc.Name]了！)]"),
							STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", activeSlave));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
							banishSlave(activeSlave, false);
						}
					};
					
				} else if(index==2) {
					if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
						return new Response("离开(忘记)", UtilText.parse(activeSlave, "你无法忘记[npc.name]……"), null);
					}
					return new Response("离开 ([style.colourMinorBad(Forget)])",
							UtilText.parse(activeSlave, "你离开[npc.her]的摊位，转身回马场，不打算去拜访[npc.Name]。"
									+ "<br/>[style.colourMinorBad(你无法再拜访[npc.name]了！)]"),
							STABLES_INTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_LEAVE", activeSlave));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
							banishSlave(activeSlave, true);
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaOrifice.MOUTH) {
				if(index==1) {
					return new ResponseSex(
							"吮吸鸡巴",
							UtilText.parse(activeSlave, "你别无选择，只能嗦[npc.namePos]粗壮的马屌。"),
							true,
							false,
							new SMDominionExpress(SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_BLOWJOB", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
						}
					};
				}
				
			} else if(sleepSexAreaWanted==SexAreaPenetration.TONGUE) {
				if(index==1) {
					return new ResponseSex(
							"吻肛",
							UtilText.parse(activeSlave, "你别无选择，只能给[npc.Name]舔肛。"),
							true,
							false,
							new SMDominionExpress(SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotLyingDown.FACE_SITTING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.ANUS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_ANILINGUS", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
						}
					};
				}
				
			} else {
				if(index==1) {
					return new ResponseSex(
							"挨操",
							UtilText.parse(activeSlave, "你别无选择，只能让[npc.Name]操你的屁股。"),
							true,
							false,
							new SMDominionExpress(SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(new Value<>(activeSlave, SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
									Util.newHashMapOfValues(new Value<>(activeSlave, new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
									Util.newHashMapOfValues(
											new Value<>(activeSlave, Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))),
							null,
							null,
							AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_START_MOUNTED", activeSlave)) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(activeSlave, Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX = new DialogueNode("结束", "奴隶和你做完了……", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(activeSlave, "[npc.Name]和你做够了……");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(2));
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getForeplayPreference(activeSlave, Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_BLOWJOB", activeSlave));
				
			} else if(Main.sex.getForeplayPreference(activeSlave, Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_ANILINGUS", activeSlave));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_MOUNTED", activeSlave));
			}
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_FINISH", activeSlave));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开 ([style.colourMinorGood(Favourite)])",
						UtilText.parse(activeSlave, "你给[npc.namePos]的摊位做了标记，以便你回马场时再次拜访[npc.herHim]。"
								+ "<br/>[style.colourMinorGood(你可以再次拜访[npc.Name]了！)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FAVOURITE_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, false);
					}
				};
				
			} else if(index==2) {
				if(activeSlave.equals(getSadistSlave())) { // Cannot remove sadist slave:
					return new Response("离开(忘记)", UtilText.parse(activeSlave, "你无法忘记[npc.name]……"), null);
				}
				return new Response("离开 ([style.colourMinorBad(Forget)])",
						UtilText.parse(activeSlave, "你离开[npc.her]的摊位，转身回马场，不打算去拜访[npc.Name]。"
								+ "<br/>[style.colourMinorBad(你无法再拜访[npc.name]了！)]"),
						STABLES_INTERIOR) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "AFTER_STABLE_SEX_SLEEP_AFTER_WAKEUP_SEX_LEAVE", activeSlave));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "FORGET_SLAVE_ENDING", activeSlave));
						banishSlave(activeSlave, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode STABLE_SHOWER = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().washAllOrifices(false));
			Main.game.getPlayer().calculateStatusEffects(0);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().cleanAllClothing(false, true));
			Main.game.getPlayer().cleanAllDirtySlots(true);
		}
		@Override
		public int getSecondsPassed() {
			return 10 * 60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "STABLE_SHOWER");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return STABLES_INTERIOR.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STABLES_INTERIOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(!Main.game.isAnalContentEnabled() && !Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					return new Response("进入",
							"你不想和娜塔莉亚有任何瓜葛……"
									+ "<br/>[style.italicsMinorBad(娜塔莉亚的场景包含肛门内容，若关闭“肛门内容”则相关文本也会被移除。)]",
							null);
				}
				
				return new Response("进入",
						"敲敲娜塔莉亚办公室的门，走进去。"
						+ (!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)
							?"<br/>[style.italicsQuestRomance(这会开启娜塔莉亚的浪漫任务！)]"
							:""),
						OFFICE_STABLE_ENTRY) {
					@Override
					public Colour getHighlightColour() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
							return PresetColour.QUEST_RELATIONSHIP;
						}
						return super.getHighlightColour();
					}
				};
			}
			return null;
		}
	};
	
	private static boolean isOfficeEntryDenied() {
		return (Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA) && (!isPlayerBodyCorrect() || !wearingMuleCollar()))
				|| Main.game.getPlayer().isPregnant()
				|| Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)
				|| Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaBusy);
	}
	
	public static final DialogueNode OFFICE_STABLE_ENTRY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(!isOfficeEntryDenied()) {
				if(!Main.game.getPlayer().hasQuest(QuestLine.ROMANCE_NATALYA)) {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.ROMANCE_NATALYA));
				}
				if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
					Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD);
					Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD);
					Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD_STAMPED), false);
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(isOfficeEntryDenied()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_DENIED");
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY"));

			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW"));
				
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_REPEAT"));
				if(!isPlayerBodyCorrect()) { // If entering after interview has started, that means the player passed & signed the contact, and as such requires Natalya's preferred body:
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WRONG_BODY"));
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_2"));
					
				} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
					sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRAINING_3", getSadistSlave()));
					
				} else { // Qualified mule:
					if(getPlayerCollar().getColour(0)!=Main.game.getDialogueFlags().getNatalyaCollarColour()) {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_COLLAR_UPGRADED"));
						
					} else {
						sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_WITH_COLLAR"));
					}
				}
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isOfficeEntryDenied()) {
				if(index==1) {
					return new Response("离开", "你现在的状态不允许你去见娜塔莉亚，除了离开也没别的办法了……", OFFICE_STABLE_EXIT_NO_CONTENT) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_DENIED_ENTRY"));
						}
					};
				}
				
			} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_1_INTERVIEW_START) {
				if(index==1) {
					return new Response("面试", "接受娜塔莉亚的面试。", OFFICE_STABLE_INTERVIEW_1) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewOffered, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_2_CONTRACT_SIGNED));
						}
					};
					
				} else if(index==2) {
					return new Response("拒绝", "拒绝被娜塔莉亚面试。<br/>[style.italicsTerrible(这会导致娜塔莉亚的浪漫任务失败！)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_TERRIBLE;
						}
						@Override
						public void effects() {
							Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
							Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
							Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_0"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
						}
					};
				}
				
			} else {
				if(!isPlayerBodyCorrect()) {
					if(index==1) {
						return new Response("喝下药水", "喝下药水转化为[style.a_shemale]。", OFFICE_STABLE_ENTRY_TRANSFORMED){
							@Override
							public Colour getHighlightColour() {
								return PresetColour.TRANSFORMATION_GENERIC;
							}
							@Override
							public void effects() {
								String transformationText = applyTransformation();
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START"));
								Main.game.getTextStartStringBuilder().append(transformationText);
								
								if(Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(-25));
								}
							}
						};
					}
					
				} else {
					if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
						if(index==1) {
							return new Response("跪下", "按主人娜塔莉亚说的，跪在她的旁边……", OFFICE_STABLE_TRAINING_2);
						}
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_5_TRAINING_3) {
						if(index==1) {
							return new Response("跪下", "按主人娜塔莉亚说的，跪在她的旁边……", OFFICE_STABLE_TRAINING_3);
						}
							
					} else { // Qualified mule:
						AbstractClothing collar = getPlayerCollar();
						
						if(index==0) {
							return new Response("离开", "告诉娜塔莉亚，你要回去履行你的职责，然后离开……", OFFICE_STABLE_EXIT_NO_CONTENT) {
								@Override
								public void effects() {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_FILLY_LEAVE"));
									Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
								}
							};
							
						// Silver:
						} else if(index==1) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("吮吸肉棒", "你今天已经给娜塔莉亚口交过了，她没时间再陪你……", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("吮吸肉棒",
										"你的[style.mule]段位太低！<br/>[style.italics(要求至少有“白银”段位，而你只有“"+collar.getColour(0).getName()+"”……)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return new Response("吮吸肉棒", "你的嘴此刻被限制，不能吸娜塔莉亚的肉棒！", null);
								
							} else {
								return new Response("吮吸肉棒", "你告诉娜塔莉亚你想用[style.mule]段位奖励换取吮吸她肉棒的机会。", OFFICE_STABLE_FILLY_GIVE_BLOWJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==2) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("舔肛", "你今天已经给娜塔莉亚口交过了，她没时间再陪你……", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("提供舔肛",
										"你的[style.mule]段位太低！<br/>[style.italics(要求至少有“白银”段位，而你只有“"+collar.getColour(0).getName()+"”……)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
								return new Response("舔肛", "你的嘴此刻被限制，不能舔娜塔莉亚的肛门！", null);
								
							} else {
								return new Response("舔肛", "你告诉娜塔莉亚你想用[style.mule]段位奖励换取舔她肛门的机会。", OFFICE_STABLE_FILLY_GIVE_RIMJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==3) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsSub)) {
								return new Response("顶入", "你今天已经和娜塔莉亚做过了，她没时间再陪你……", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_SILVER && collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("顶入",
										"你的[style.mule]段位太低！<br/>[style.italics(要求至少有“白银”段位，而你只有“"+collar.getColour(0).getName()+"”……)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
								return new Response("顶入", "你无法控制肛门，没法让娜塔莉亚进入你！", null);
								
							} else {
								return new Response("被上", "你告诉娜塔莉亚你想用[style.mule]段位奖励换取和她做爱的机会。", OFFICE_STABLE_FILLY_GET_MOUNTED) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsSub, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						// Gold:
						} else if(index==6) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("口交", "你今天已经和娜塔莉亚口交过了，她可能明天才愿意再这么干……", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("被口交",
										"你的[style.mule]段位太低！<br/>[style.italics(要求至少有“黄金”段位，而你只有“"+collar.getColour(0).getName()+"”……)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return new Response("口交", "由于无法使用阴茎，娜塔莉亚不能唆你的鸡巴。", null);
								
							} else {
								return new Response("口交", "要求娜塔莉亚跪下来唆你的鸡巴。", OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==7) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("舔肛", "你今天已经和娜塔莉亚这么做过了，她可能明天才愿意再这么干……", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("舔肛",
										"你的[style.mule]段位太低！<br/>[style.italics(要求至少有“黄金”段位，而你只有“"+collar.getColour(0).getName()+"”……)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
								return new Response("舔肛", "你控制不了肛门，没法让娜塔莉亚给你舔肛！", null);
								
							} else {
								return new Response("舔肛", "要求娜塔莉亚跪下来给你舔肛。", OFFICE_STABLE_FILLY_RECEIVE_RIMJOB) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
							
						} else if(index==8) {
							if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.natalyaDailySexAsDom)) {
								return new Response("顶入她", "你今天已经和娜塔莉亚这么做过了，她可能明天才愿意再这么干……", null);
								
							} else if(collar.getColour(0)!=PresetColour.CLOTHING_GOLD) {
								return new Response("顶入她",
										"你的[style.mule]段位太低！<br/>[style.italics(要求至少有“黄金”段位，而你只有“"+collar.getColour(0).getName()+"”……)]",
										null);
								
							} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
								return new Response("顶入她", "你控制不了你的阴茎，无法进入娜塔莉亚！", null);
								
							} else {
								return new Response("顶入她", "你要求娜塔莉亚露出肛门让你进去操她。", OFFICE_STABLE_FILLY_MOUNT_HER) {
									@Override
									public boolean isSexHighlight() {
										return true;
									}
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaDailySexAsDom, true);
										Main.game.getDialogueFlags().setNatalyaCollarColour(getPlayerCollar().getColour(0));
									}
								};
							}
						}
						
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_ENTRY_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().getQuest(QuestLine.ROMANCE_NATALYA)==Quest.ROMANCE_NATALYA_4_TRAINING_2) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START_TRAINING_2_END");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_TRANSFORMED_START_TRAINING_3_END");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_1");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("脱衣服并跪下", "听从娜塔莉亚的命令，脱光衣服跪在她面前。", OFFICE_STABLE_INTERVIEW_2) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝", "拒绝跪在娜塔莉亚前面<br/>[style.italicsTerrible(这会导致娜塔莉亚的浪漫任务失败！)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_1"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_2 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAnyEquippedClothingSealed()) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_STRIP_SEALED"));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_STRIP"));
			}
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_2_END"));
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
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
				return new Response("掀起裙子", "按照娜塔莉亚的命令，掀起她的裙子……", OFFICE_STABLE_INTERVIEW_3) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).displaceClothingForAccess(CoverableArea.ANUS, null);
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝", "拒绝撩起娜塔莉亚的裙子。<br/>[style.italicsTerrible(这将使娜塔莉亚的浪漫任务失败！)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_2"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_3 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_3");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"不情愿地舔",
						"犹豫着亲吻娜塔莉亚的屁眼，开始嗦她的牛子。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_RELUCTANT_KISS")
						+ UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex(
						"急切地舔",
						"急切地舔娜塔莉亚的屁眼，然后开始嗦她的牛子。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_INTERVIEW_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_EAGER_KISS")
						+ UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
					}
				};
				
			} else if(index==3) {
				return new Response("拒绝", "拒绝亲吻娜塔莉亚的菊花。<br/>[style.italicsTerrible(这将使娜塔莉亚的浪漫任务失败！)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().removeItemByType(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.getProperties().addItemDiscovered(ItemType.NATALYA_BUSINESS_CARD_STAMPED);
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_INTERVIEW);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_FAIL_3"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_FULL_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_EXIT_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode OFFICE_STABLE_INTERVIEW_AFTER_SEX = new DialogueNode("结束", "娜塔莉亚很满意你的表现。", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_INTERVIEW_AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("清洁肉棒", "在娜塔莉亚准备你的合同时，清理她的肉棒。", OFFICE_STABLE_CONTRACT_OFFERED) {
					@Override
					public void effects() {
						Main.game.getNpc(Natalya.class).replaceAllClothing();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_OFFERED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED"));
			if(isPlayerBodyCorrect()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_NO_TF"));
			} else {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_TF"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("签字", "按照娜塔莉亚说的，在合同上签字。", OFFICE_STABLE_CONTRACT_SIGNED) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_3_TRAINING_1));
					}
				};
				
			} else if(index==2) {
				return new Response("拒绝", "拒绝在合同上签字。<br/>[style.italicsTerrible(这会导致娜塔莉亚的浪漫任务失败！)]", OFFICE_STABLE_FULL_EXIT_NO_CONTENT){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_TERRIBLE;
					}
					@Override
					public void effects() {
						Main.game.getPlayer().setQuestFailed(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_FAILED_CONTRACT);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_OFFERED_REFUSED"));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_ENTRY_INTERVIEW_FAIL_END"));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), -100));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_CONTRACT_SIGNED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(isPlayerBodyCorrect()) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_NO_TF");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isPlayerBodyCorrect()) {
				return OFFICE_STABLE_TRANSFORMED.getResponse(responseTab, index);
				
			} else {
				if(index==1) {
					return new Response("喝下药水", "喝下药水转化为主人娜塔莉亚的[style.shemale][style.mules]。", OFFICE_STABLE_TRANSFORMED){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.TRANSFORMATION_GENERIC;
						}
						@Override
						public void effects() {
							String transformationText = applyTransformation();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_CONTRACT_SIGNED_TRANSFORMED"));
							Main.game.getTextStartStringBuilder().append(transformationText);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRANSFORMED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRANSFORMED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("半人马", "告诉娜塔莉亚你更想服务半人马。", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.MASCULINE;
					}
					@Override
					public void effects() {
						applySadistSlave(spawnSlave(false, PresetColour.CLOTHING_BRONZE));
					}
				};
				
			} else if(index==2) {
				return new Response("女半人马", "告诉娜塔莉亚你更想服务女半人马。", OFFICE_STABLE_TRAINING_1) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.FEMININE;
					}
					@Override
					public void effects() {
						applySadistSlave(spawnSlave(true, PresetColour.CLOTHING_BRONZE));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1 = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("不情愿地吻", UtilText.parse(getSadistSlave(), "犹豫地亲吻[npc.namePos]地肛门。"), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_RELUCTANT_KISS", getSadistSlave()));
						if(Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isNegative()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.TWO_NEUTRAL, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("急切地吻", UtilText.parse(getSadistSlave(), "急切的亲吻[npc.namePos]地肛门。"), OFFICE_STABLE_TRAINING_1_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_EAGER_KISS", getSadistSlave()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ANAL_GIVING, FetishDesire.THREE_LIKE, true));
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_1_SEX = new DialogueNode("", "", true, true) {
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
				return new ResponseSex(
						"舔鸡巴",
						UtilText.parse(getSadistSlave(), "按照主人娜塔莉亚地指令，下去舔[npc.namePos]兽态地马屌。"),
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_1_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_SEX_SUCK_COCK")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), getSadistSlave(), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_PENIS_RECEIVING, FetishDesire.THREE_LIKE, true));
						}
						if(!Main.game.getPlayer().getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setFetishDesire(Fetish.FETISH_ORAL_GIVING, FetishDesire.THREE_LIKE, true));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_1_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlave(getSadistSlave(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_4_TRAINING_2));
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getSadistSlave(), "[npc.Name]和你做够了，把鸡巴从你受虐地喉咙里拔出。");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("服从", UtilText.parse(getSadistSlave(), "告诉主人娜塔莉亚，你是一匹淫荡的[style.mule]，喜欢马尻和马屌，然后跟着[npc.name]离开办公室……"), OFFICE_STABLE_EXIT_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_1_AFTER_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2"));
			if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_LIFT_SKIRT"));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isAbleToWearMakeup()) {
				if(index==1) {
					return new Response("哑光深蓝", "选择亚光深蓝口红。", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_DARK_BLUE"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.MATTE, PresetColour.COVERING_BLUE_DARK, false, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
					
				} else if(index==2) {
					return new Response("闪亮金色", "选择闪亮的金色口红。", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_SPARKLY_GOLD"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.SPARKLY, PresetColour.COVERING_GOLD, false, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
					
				} else if(index==3) {
					return new Response("明亮粉色", "选择亮粉色的口红。", OFFICE_STABLE_TRAINING_2_MAKEUP) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_LIPSTICK_GLOWING_PINK"));
							Main.game.getPlayer().setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, CoveringPattern.NONE, CoveringModifier.GLOSSY, PresetColour.COVERING_PINK, true, PresetColour.COVERING_NONE, false));
							Main.game.getPlayer().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
						}
					};
				}
				return null;
				
			} else {
				return OFFICE_STABLE_TRAINING_2_MAKEUP.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_LIFT_SKIRT"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("不情愿的吻", "犹豫地亲上娜塔莉亚的肛门。", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_RELUCTANT_KISSES", getSadistSlave()));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						}
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ORAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("渴求的吻", "饥渴难耐地亲吻娜塔莉亚的肛门。", OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_EAGER_KISSES", getSadistSlave()));
						Main.game.getNpc(Natalya.class).addLipstickMarking(Main.game.getPlayer(), InventorySlot.ANUS, Main.game.getPlayer().getLipstick());
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_GIVING, true));
						}
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ORAL_GIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING, true));
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
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
				return new ResponseSex(
						"渴望吻肛",
						"开始用舌头给娜塔莉亚吻肛。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_2_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_MAKEUP_ANILINGUS_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_2_AFTER_SEX = new DialogueNode("结束", "娜塔莉亚主人对你的表现很满意……", true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getColourDescriptor(Main.game.getPlayer(), false, false), true);
			UtilText.addSpecialParsingString(Main.game.getPlayer().getLipstick().getPrimaryColourDescriptor(false), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.ROMANCE_NATALYA_5_TRAINING_3));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "娜塔莉亚今天没时间招待你了，你得明天再来继续训练。", OFFICE_STABLE_EXIT_NO_CONTENT){
					@Override
					public void effects() {
						Main.game.getPlayer().equipAllClothingFromHoldingInventory();
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_2_AFTER_SEX_LEAVE"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3 = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response(UtilText.parse(getSadistSlave(), "[npc.Name]"),
						UtilText.parse(getSadistSlave(), "娜塔莉亚主人忙着应付[npc.name]，你在她操你屁眼的时候可以给[npc.herHim]吻肛。"),
						OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY) {
					@Override
					public void effects() {
						getSadistSlave().setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("不情愿地乞求", "吞吞吐吐地告诉娜塔莉亚你想让她发情。", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY", getSadistSlave()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY_END", getSadistSlave()));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						}
					}
				};
				
			} else if(index==2) {
				return new Response("急切地乞求", "急切地乞求主人娜塔莉亚操你。", OFFICE_STABLE_TRAINING_3_FUCKED) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_EAGERLY_OBEY", getSadistSlave()));
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_SLAVE_ENTRY_OBEY_END", getSadistSlave()));
						Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Natalya.class).incrementAffection(Main.game.getPlayer(), 5));
						if(!Main.game.getPlayer().hasFetish(Fetish.FETISH_ANAL_RECEIVING)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_ANAL_RECEIVING, true));
						}
					}
				};
				
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_TRAINING_3_FUCKED = new DialogueNode("", "", true, true) {
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
				return new ResponseSex(
						"向后推",
						UtilText.parse(getSadistSlave(), "向后推，让娜塔莉亚的肉棒插入你，同时为[npc.Name]提供吻肛……"),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND),
										new Value<>(getSadistSlave(), SexSlotAllFours.IN_FRONT_ANAL)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
										new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH, CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.PRECUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_FUCKED_START", getSadistSlave())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getSadistSlave(), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX = new DialogueNode("结束", "娜塔莉亚主人爽够了，把肉棒从你被操透的屁眼里抽了出来……", true) {
		@Override
		public void applyPreParsingEffects() {
			getSadistSlave().fillCumToMaxStorage();
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"第二轮",
						UtilText.parse(getSadistSlave(), "向[npc.Name]展示你的屁穴，让[npc.she]能骑着你操你的屁穴……"),
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(getSadistSlave(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(getSadistSlave(), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								
								map.put(getSadistSlave(), new HashMap<>());
								map.get(getSadistSlave()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(getSadistSlave()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA, LubricationType.PRECUM, LubricationType.CUM));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_ROUND_TWO", getSadistSlave())) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(getSadistSlave(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND = new DialogueNode("结束", "", true) {
		@Override
		public void applyPreParsingEffects() {
			banishSlave(getSadistSlave(), false);
		}
		@Override
		public String getDescription() {
			return UtilText.parse(getSadistSlave(), "你的屁穴被精液灌得满满当当，[npc.name]后退一步，把鸡巴从你的屁眼里滑了出来。");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_AFTER_SEX_SECOND", getSadistSlave());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(index==1) {
					return new Response("[style.Mule]项圈", "让娜塔莉亚把新颈圈系在你的脖子上……", OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().equipClothingFromNowhere(generateCollar(), true, Main.game.getNpc(Natalya.class)));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(10));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.ROMANCE_NATALYA, Quest.SIDE_UTIL_COMPLETE));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString("", true);
			if(Main.game.getPlayer().getHoldingClothing().values().stream().anyMatch(c->c.getClothingType().getId().equals("innoxia_neck_ambers_bitch_collar"))) {
				UtilText.addSpecialParsingString(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_AMBER_SPECIAL"), true);
			}
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "按娜塔莉亚说的，离开办公室……", OFFICE_STABLE_TRAINING_3_EXIT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_TRAINING_3_CHOKER_EQUIPPED_LEAVE"));
						Main.game.getPlayer().setNearestLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_CORRIDOR, false);
						if(Main.game.getPlayer().getHoldingClothing().containsKey(InventorySlot.NECK)) {
							Main.game.getPlayer().addClothing(Main.game.getPlayer().getHoldingClothing().get(InventorySlot.NECK), false);
						}
						Main.game.getPlayer().equipAllClothingFromHoldingInventory(Util.newArrayListOfValues(InventorySlot.NECK));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_TRAINING_3_EXIT = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
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
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GIVE_BLOWJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"亲吻肛门",
						"亲吻娜塔莉亚的肛门，开始嗦她的牛子。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GIVE_RIMJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_RIMJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"吻肛",
						"亲吻娜塔莉亚的肛门，用舌头给她吻肛。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GIVE_RIMJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_GET_MOUNTED = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GET_MOUNTED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"亲吻肛门",
						"亲吻娜塔莉亚的肛门，让她骑在你身上。",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
								if(!character.isPlayer()) {
									return OrgasmBehaviour.CREAMPIE;
								}
								return super.getCharacterOrgasmBehaviour(character);
							}
							@Override
							public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
								Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();

								map.put(Main.game.getPlayer(), new HashMap<>());
								map.get(Main.game.getPlayer()).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getPlayer()).get(SexAreaOrifice.ANUS).put(Main.game.getNpc(Natalya.class), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								map.put(Main.game.getNpc(Natalya.class), new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).put(SexAreaOrifice.ANUS, new HashMap<>());
								map.get(Main.game.getNpc(Natalya.class)).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
								
								return map;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_GET_MOUNTED_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Natalya.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"支配她",
						"把你的鸡巴冲入她的喉咙，支配主人娜塔莉亚。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.PERFORMING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_BLOWJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisMouth.BLOWJOB_START, false, true));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode OFFICE_STABLE_FILLY_RECEIVE_RIMJOB = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_RIMJOB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"支配她",
						"屁股向后一顶，用你[pc.asshole+]捂住她的脸，支配主人娜塔莉亚。",
						true,
						false,
						new SMDominionExpress(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.MOUTH)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_RECEIVE_RIMJOB_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), TongueAnus.RECEIVING_ANILINGUS_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_MOUNT_HER = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"支配她",
						"支配者娜塔莉亚主人用她的[natalya.asshole+]深深含住你[pc.cock+]。",
						true,
						false,
						new SMDominionExpress(SexPosition.ALL_FOURS,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), SexSlotAllFours.ALL_FOURS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Natalya.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(Natalya.class), Util.newArrayListOfValues(CoverableArea.ANUS)),
										new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.PENIS)))) {
							@Override
							public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
								return true;
							}
						},
						null,
						null,
						OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_MOUNT_HER_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Natalya.class), PenisAnus.PENIS_FUCKING_START, false, true));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB = new DialogueNode("结束", "娜塔莉亚主人很满意，你的快乐也来到尽头……", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_RIMJOB");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_SUB_MOUNTED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM = new DialogueNode("完成", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_BLOWJOB");
				
			} else if(Main.sex.getForeplayPreference(Main.game.getNpc(Natalya.class), Main.game.getPlayer()).equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_RIMJOB");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/dominionExpress", "OFFICE_STABLE_FILLY_AFTER_SEX_AS_DOM_MOUNTED");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE_STABLE_ENTRY.getResponse(responseTab, index);
		}
	};
	
}
