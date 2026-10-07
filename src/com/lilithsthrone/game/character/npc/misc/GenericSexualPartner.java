package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * This NPC doesn't spawn with addictive fluids so as to prevent issues with randomly getting their partners addicted to fluids.
 * 
 * @since 0.2.2
 * @version 0.4.8.4
 * @author Innoxia
 */
public class GenericSexualPartner extends NPC {

	public GenericSexualPartner() {
		this(Gender.getGenderFromUserPreferences(false, false), WorldType.EMPTY, new Vector2i(0, 0), false);
	}
	
	public GenericSexualPartner(boolean isImported) {
		this(Gender.F_V_B_FEMALE, WorldType.EMPTY, new Vector2i(0, 0), isImported);
	}

	public GenericSexualPartner(Gender gender, AbstractWorldType worldLocation, Vector2i location, boolean isImported) {
		this(gender, worldLocation, location, isImported, null);
	}

	public GenericSexualPartner(Gender gender, AbstractWorldType worldLocation, AbstractPlaceType placeType, boolean isImported, Predicate<AbstractSubspecies> subspeciesRemovalFilter) {
		this(gender, worldLocation, Main.game.getWorlds().get(worldLocation).getCell(placeType).getLocation(), isImported, subspeciesRemovalFilter);
	}
	
	public GenericSexualPartner(Gender gender, AbstractWorldType worldLocation, Vector2i location, boolean isImported, Predicate<AbstractSubspecies> subspeciesRemovalFilter) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3,
				null, null, null,
				new CharacterInventory(false, 10),
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
			this.setLocation(worldLocation, location, false);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:
			
			Map<AbstractSubspecies, Integer> availableRaces = new HashMap<>();
			List<AbstractSubspecies> availableSubspecies = new ArrayList<>();
			availableSubspecies.addAll(Subspecies.getAllSubspecies());
			
			if(subspeciesRemovalFilter!=null) {
				availableSubspecies.removeIf(subspeciesRemovalFilter);
			}
			
			for(AbstractSubspecies s : availableSubspecies) {
				if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
					continue;
				}
				if(s==Subspecies.REINDEER_MORPH
						&& Main.game.getSeason()==Season.WINTER
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					AbstractSubspecies.addToSubspeciesMap(50, gender, s, availableRaces);
				}
				
				if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
					AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).get(s).getChanceMultiplier()), gender, s, availableRaces);
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces, true, subspeciesRemovalFilter==null);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getSubspecies()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this, "是[npc.a_race]。"));
			
			// PERSONALITY & BACKGROUND:
			
			Main.game.getCharacterUtils().setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			Main.game.getCharacterUtils().addFetishes(this);
			
			// BODY RANDOMISATION:
			
			Main.game.getCharacterUtils().randomiseBody(this, true);
			// Do not allow addictive fluids:
			this.removeMilkCrotchModifier(FluidModifier.ADDICTIVE);
			this.removeMilkModifier(FluidModifier.ADDICTIVE);
			this.removeCumModifier(FluidModifier.ADDICTIVE);
			this.removeGirlcumModifier(FluidModifier.ADDICTIVE);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setName(new NameTriplet("某男性", "某女性", "某女性"));
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);

		this.setName(new NameTriplet("unknown male", "unknown female", "unknown female"));
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
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
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
		return null;
	}
	
	private boolean playerRequested = false;
	
	@Override
	public void generateSexChoices(boolean resetPositioningBan, GameCharacter target, List<SexType> request) {
		if(this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) && Main.sex.getTurn()>1) {
			playerRequested = true;
		}
		
		super.generateSexChoices(resetPositioningBan, target, request);
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.isHappyToBeInSlot(position, slot, target);
			
		} else {
			if(Main.sex.isInForeplay(this) || this.hasFetish(Fetish.FETISH_ORAL_GIVING) || !target.hasPenis()) {
				return slot==SexSlotUnique.GLORY_HOLE_KNEELING;
			} else {
				return slot==SexSlotUnique.GLORY_HOLE_FUCKED;
			}
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.getForeplayPreference(target);
		}
		
		if(target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
		} else if(target.hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
		} else {
			return super.getForeplayPreference(target);
		}
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.getMainSexPreference(target);
		}
		
		if(this.hasFetish(Fetish.FETISH_ORAL_GIVING)) {
			return getForeplayPreference(target);
		}
		
		if(this.hasVagina() && target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		} else if(target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
		}

		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer()
				|| penetrating != SexAreaPenetration.PENIS
				|| penetrated != SexAreaOrifice.VAGINA
				|| (!penetratingCharacter.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY) && !penetratingCharacter.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY))) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().hasHymen()) {
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("[npc.namePos][npc.cock+]顶入你[pc.pussy+]，你不由自主地发出一声渴求，颤抖的哀号。"
								+ "你非常痴迷于做一个纯洁的处女，你不知道自己着了什么魔，报名参加了怀孕轮盘赌，"
									+ "[npc.namePos][npc.cock+]夺走了你宝贵的童贞，你没时间反思自己贫弱的选择。"
								+ "你脑中只剩下被你素不相识的人撕裂处女膜所带来的痛苦。");
				} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
					sb.append("[npc.namePos][npc.cock+]顶入你[pc.pussy+]，撕裂处女膜并夺取你的童贞，你不由自主地发出一声受虐狂般的淫叫。"
							+ "在你素不相识的人撕裂你的处女膜带来的剧痛彻底淹没你之前，你在一片强烈的欢愉中难以抑制地尖叫和呻吟。");
				} else {
					sb.append("[npc.namePos][npc.cock+]顶入你[pc.pussy+]，夺取你的童贞，你不由自主地发出一声狂乱，颤抖的哀号。"
							+ "你素不相识的人撕裂处女膜所带来的剧痛压倒了你，你在桌上扭动着，尽力忍受这可怕的经历。");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "你的哀号化为颤抖的哭泣，你听到墙的另一边的[npc.race]惊讶的喊声，"
						+ "[npc.speechNoExtraEffects(我靠！这骚婊子是个处！)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("墙那边的房间里突然充满了嘲笑和猥亵的话语，你意识到你失去了你珍贵而"
								+ "纯洁的贞操，给了一个想搞大你肚子的陌生人。");
				} else {
					sb.append("你无法掩饰自己的痛苦，窗外的房间充满嘲笑和淫秽的言辞，你意识到那些试图让你怀孕的陌生人夺走了你的童贞。");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[pc.thought(怀孕……我的第一次……不可能……)]你心里想着。[npc.Name]抽出又再次插入你，你试着抑制你的呜咽。"
					+ "</p>"
					+ "<p>"
						+ "[npc.she]用[npc.cock+]填满了你初破的小穴，墙另一边发出的嘲笑声刺痛着你的耳膜。"
						+ "[npc.speechNoExtraEffects(居然选择在‘怀孕轮盘赌’中失去童贞！真是个骚婊子！哈哈！很高兴我永远不会告诉我们的孩子他们是怎么来的！)]"
					+ "</p>");
			
		} else {
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("[npc.namePos][npc.cock+]顶入你[pc.pussy+]，你不由自主地发出一声痛苦的哀号。"
								+ "你非常痴迷于做一个纯洁的处女，你不知道自己着了什么魔，报名参加了怀孕轮盘赌，"
									+ "[npc.namePos][npc.cock+]夺走了你宝贵的童贞，你没时间反思自己贫弱的选择。"
								+ "你脑子里现在只剩下一件事，那就是你被一个素未谋面的家伙给破处了，[npc.she]继续抽插着，你从口中溢出绝望的呼喊。");
				} else {
					sb.append("[npc.namePos][npc.cock+]插入了你[pc.pussy+]，将贞操夺去，但你却禁不住发出了一声极度淫荡的[pc.moan]。"
							+ "这个你素未谋面的人给了你一种前所未有的感觉，你沉浸在强烈的狂喜之中，不停尖叫和[pc.moan]。");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "由于你已经失去了处女膜，只有被插入时的强烈反应，才能表明你仍是处女，"
							+ "但这足以让墙的另一边的[npc.race]明白了，[npc.she]发出惊讶地喊了出来，"
						+ "[npc.speechNoExtraEffects(我靠！这骚婊子是个处！)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("墙那边的房间里突然充满了嘲笑和猥亵的话语，你意识到你失去了你珍贵而"
								+ "纯洁的贞操，给了一个想搞大你肚子的陌生人。");
				} else {
					sb.append("墙那边的房间里突然充满了嘲弄的笑声和猥亵的话语，你意识到你把贞洁交给了一个想搞大你肚子的陌生人，你不禁小声呜咽了起来。");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[pc.thought(怀孕……我的第一次……不可能……)]你心里想着，同时不由自主地发出一声[pc.moan]。这时[npc.name]再次抽身插了进来。"
					+ "</p>"
					+ "<p>"
						+ "[npc.she]用[npc.cock+]填满了你初破的小穴，墙另一边发出的嘲笑声刺痛着你的耳膜。"
						+ "[npc.speechNoExtraEffects(居然选择在‘怀孕轮盘赌’中失去童贞！真是个骚婊子！哈哈！很高兴我永远不会告诉我们的孩子他们是怎么来的！)]"
					+ "</p>");
		}
		
		return sb.toString();
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return UtilText.parse(penetratingCharacter,
				"<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>失格处女</b>"
				+ "</p>"
				+ "<p>"
					+ "[npc.race]继续撞击你的小穴，你突然意识到发生了什么，这对你来说是一记重锤。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("我，我就这样失贞了？！<br/>"
							+ "像……像<b>这样</b>？！")
				+ "</p>"
				+ "<p>"
					+ "你不知道哪个更糟糕；是失去了这样看重的第一次，还是自己反而乐在其中。"
					+ "你[pc.labia+]淫靡地包裹着那"
					+ (penetrating==SexAreaPenetration.PENIS
						?"又热又粗的[npc.cock]"
						:penetrating.getName(penetratingCharacter))
					+ "在体内进进出出时，你渐渐相信了，这就大概是你唯一做得好的事情。"
				+ "</p>"
				+ "<p style='text-align:center;'>"
				+ UtilText.parsePlayerThought("如果我不再是处女，那我就是个骚货了……<br/>"
						+ "我只是个万人骑的荡妇……<br/>"
						+ "我打赌这场[npc.race]结束后不久我就会被其他人操了……")
				+ "</p>"
				+ "<p>"
					+ "你隐约感觉到在[npc.Name]知道自己夺走了你的童贞时，[npc.she]在胜利的喜悦中[npc.moaning]。"
					+ "随着一声渴求的[pc.moan]，"
					+ (Main.game.getPlayer().hasLegs()
						?"你张开双腿并"
						:"你")
					+ "只能接受现实，听之任之。现在的你什么都不是，只是个"
					+ "<b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>失格处女</b>……"
				+ "</p>");
	}
	
}