package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.CultistDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.88
 * @version 0.3.4
 * @author Innoxia
 */
public class Cultist extends NPC {

	private boolean requestedAnal = false;
//	private boolean sealedSex = false;

	public Cultist() {
		this(false);
	}
	
	public Cultist(boolean isImported) {
		super(isImported, null, null,
				"",
				Util.random.nextInt(30)+30, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				15,
				Gender.getGenderFromUserPreferences(true, true),
				Subspecies.DEMON,
				RaceStage.GREATER,
				new CharacterInventory(false, 10),
				WorldType.DOMINION,
				PlaceType.DOMINION_BACK_ALLEYS,
				false);
		
		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
			// BODY RANDOMISATION:
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
			Main.game.getCharacterUtils().addFetishes(this);
			if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
				this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
			}
			if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ZERO_HATE) {
				this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.TWO_NEUTRAL);
			}
			
			Main.game.getCharacterUtils().randomiseBody(this, true);

			this.setHistory(Occupation.NPC_CULTIST);
			
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(true);
			setDescription("“莉莉丝异教”的高阶成员，正常人都看得出来这是个强大的恶魔。"
					+ "但你可不是“正常人”，你靠近她之后，几乎能用身体感受到其奥术灵气的力量，正在与你的灵气纠缠在一起……");
			
			// Set random inventory & weapons:
			resetInventory(true);
			inventory.setMoney(100);
			
			// CLOTHING:
			
			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			initHealthAndManaToMax();
			
			setStartingCombatMoves();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(15);
			this.setHistory(Occupation.NPC_CULTIST);
			this.resetPerksMap(true);
		}
		setStartingCombatMoves();
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_PENIS_GIVING)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.3.6")) {
			this.setLevel(15);
			this.setHistory(Occupation.NPC_CULTIST);
			this.resetPerksMap(true);
		}
		setStartingCombatMoves();
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(
						Perk.LUSTPYRE,
						Perk.FETISH_SEEDER,
						Perk.ARCANE_COMBATANT),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 3),
						new Value<>(PerkCategory.ARCANE, 5)));
	}
	
	@Override
	public void setStartingCombatMoves() {
		this.clearEquippedMoves();
		this.equipMove("strike");
		this.equipMove("tease");
		this.equipMove("avert");
		this.equipMove("block");
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
	public void equipClothing(List<EquipClothingSetting> settings) {
		List<Colour> colours = new ArrayList<>();
		colours.add(PresetColour.CLOTHING_ORANGE);
		colours.add(PresetColour.CLOTHING_BLACK);
		colours.add(PresetColour.CLOTHING_PURPLE);
		colours.add(PresetColour.CLOTHING_PURPLE_LIGHT);
		Colour underwearColour = colours.get(Util.random.nextInt(colours.size()));

		colours.clear();
		colours.add(PresetColour.CLOTHING_WHITE);
		colours.add(PresetColour.CLOTHING_BLACK);
		Colour witchColour = colours.get(Util.random.nextInt(colours.size()));
		
		
		List<AbstractClothingType> clothingChoices = new ArrayList<>();
		
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_panties"));
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_groin_crotchless_thong"));
		equipClothingFromNowhere(Main.game.getItemGen().generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), underwearColour, false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_chest_lacy_plunge_bra"));
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_chest_plunge_bra"));
		equipClothingFromNowhere(Main.game.getItemGen().generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), underwearColour, false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.getClothingTypeFromId("innoxia_sock_thighhigh_socks"));
		equipClothingFromNowhere(Main.game.getItemGen().generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), witchColour, false), true, this);

		equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_dress", witchColour, false), true, this);
		if(Math.random()<0.5) {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat", witchColour, PresetColour.CLOTHING_GOLD, witchColour, false), true, this);
		} else {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_hat_wide", witchColour, PresetColour.CLOTHING_GOLD, witchColour, false), true, this);
		}
		if(Math.random()>0.5f) {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots", witchColour, false), true, this);
		} else {
			equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_witch_witch_boots_thigh_high", witchColour, false), true, this);
		}
		
		if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
			this.equipMainWeaponFromNowhere(Main.game.getItemGen().generateWeapon("innoxia_cleaning_witch_broom"));
		}
		
		// Makeup:
		colours = Util.newArrayListOfValues(
				PresetColour.COVERING_NONE,
				PresetColour.COVERING_ORANGE,
				PresetColour.COVERING_PURPLE,
				PresetColour.COVERING_BLACK);
		
		Colour colourForCoordination = colours.get(Util.random.nextInt(colours.size()));
		Colour colourForNails = colours.get(Util.random.nextInt(colours.size()));
		
		setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, colourForCoordination));
		setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, colourForCoordination));
		setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, colourForCoordination));
		
		setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, colourForNails));
		setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, colourForNails));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	public boolean isRequestedAnal() {
		return requestedAnal;
	}

	public void setRequestedAnal(boolean requestedAnal) {
		this.requestedAnal = requestedAnal;
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
		return CultistDialogue.ENCOUNTER_START;
	}

	// Combat:
	
	@Override
	public String getLostVirginityDescriptor() {
		return "于她的小教堂中";
	}

	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.getItemType().equals(ItemType.getItemTypeFromId("innoxia_pills_sterility"))) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					Main.game.getPlayer().useItem(item, target, false);
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"你拿出一片“[#ITEM_innoxia_pills_sterility.getName(false)]”给[npc2.name]，告诉[npc2.herHim]吃下去之后就不用担心意外怀孕了。"
									+ "[npc2.She]气呼呼地哼了一声，但又无法拒绝，只得不情愿地照做了，"
									+ "[npc2.speech(这简直是对莉莉丝本人的侮辱……)]")
							+ "</p>");
				} else {
					itemOwner.removeItemByType(ItemType.getItemTypeFromId("innoxia_pills_sterility"));
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"你拿出一片“[#ITEM_innoxia_pills_sterility.getName(false)]”给[npc2.name]，让[npc2.herHim]吃下去，之后就不用担心意外怀孕了。"
									+ "[npc2.she]气呼呼地哼了一声，一把拍掉了你手上的药片，"
									+ "[npc2.speech(你真是大胆！莉莉丝要求其追随者子种精壮！)]")
							+ "</p>");
				}
					
			} else if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				Main.game.getPlayer().useItem(item, target, false);
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"你拿出"+item.getName(false, false)+"给[npc2.name]，告诉[npc2.herHim]吃下去。"
									+ "[npc2.She]乐得大叫起来，连忙吃下了那小小的"+item.getColour(0).getName()+"药片，"
									+ "[npc2.speech(太感谢了！让生育力旺盛可以说是敬奉莉莉丝最好的一种办法！)]")
							+ "</p>");
					
				} else {
					return new Value<>(true,
							"<p>"
								+ UtilText.parse(user, target,
									"你拿出"+item.getName(false, false)+"给[npc2.name]，让[npc2.herHim]吃下去。"
									+ "[npc2.She]乐得大叫起来，连忙吃下了那小小的"+item.getColour(0).getName()+"药片，"
									+ "[npc2.speech(多好的玩具！让生育力旺盛可以说是敬奉莉莉丝最好的一种办法！)]")
							+ "</p>");
				}
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
	public String getSpellDescription() {
		return "<p>"
				+UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name]以一种迷人的方式挥舞着扫帚，随后向你捅过来，同时释放了一道魔法！",
							"[npc.Name]将扫帚放到两腿之间，随后一挺腰，一段格外下流的呻吟便从口中发出，同时法术也释放了出来！",
							"[npc.Name]连续五次将扫帚刺向半空中，随后发出一声渴望的呻吟，释放了法术！"))
			+"</p>";
	}
	
	public String getSeductionDescription(Attack attackType, boolean isHit) {
		return "<p>"
				+UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name]露出一副积欲已久的表情，当你们四目相对时，你听见一声格外淫荡的呻吟，在脑内回响，[npc.thought(~啊啊啊！~你让我都湿透了！)]",
							"[npc.Name]嘟起嘴，用无辜的大眼睛紧盯着你看，你忽然感到意识深处响起回荡的呻吟声，[npc.thought(~姆嗯！~来干我啊！~啊啊！~我下面都湿好了，就等你来！)]",
							"[npc.Name]无辜地向你嘟起了嘴，接着便远远地送上了一记湿吻。随后她挺直身子，你却感到一对湿润的嘴唇的感觉鬼使神差地贴在了脸颊上。"))
				+"</p>";
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", CultistDialogue.ENCOUNTER_CHAPEL_COMBAT_VICTORY);
		} else {
			return new Response ("", "", CultistDialogue.ENCOUNTER_CHAPEL_COMBAT_LOSS);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
	@Override
	public boolean getSexBehaviourDeniesRequests(GameCharacter requestingCharacter, SexType sexTypeRequest) {
		return true;
	}

	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(Main.sex.isInForeplay(this)) {
			return slot==SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS || slot==SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS;
		} else {
			return slot==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS || slot==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS;
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS || Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS) {
			if(requestedAnal || !target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
			} else if(target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
			}
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS || Main.sex.getSexPositionSlot(this)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS) {
			if(requestedAnal || !target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
			} else if(target.hasVagina()) {
				return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
			}
		} else {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex() && !target.isAsleep()) {
			if((Main.sex.isDom(Main.game.getPlayer()) || Main.sex.isSubHasEqualControl()) && !target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return UtilText.parse(target,
						"<p>"
							+ "你递给[npc.name]一个避孕套，必须要[npc.herHim]戴上。"
							+ "[npc.she]迅速撕开了铝箔包装，卷着将避孕套完整地戴在了[npc.cock+]上，随后向你呜咽道，"
							+ "[npc.speech(这简直是对莉莉丝的侮辱……)]"
						+ "</p>");
			} else if (!target.isPlayer()){
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom(InventorySlot.PENIS)) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name]将[npc.her]的丝囊对准了[npc2.namePos]的[npc2.cock]，但当[npc2.name]意识到[npc.sheIs]的意图后，立刻将其拍开，低声吼叫着，"
							+ "[npc2.speech(我可不想这么玩！收下我的种子，你肯定会喜欢的！)]");
				}
				return UtilText.parse(target,
						"<p>"
							+ "你拿出一个避孕套想交给[npc.name]，但[npc.she]却只轻笑一声，便把那铝箔包装袋抢了过来，撕成了两半，"
							+ "[npc.speech(我可不想这么玩！收下我的种子，你肯定会喜欢的！)]"
						+ "</p>");
			}
		}
		return null;
	}
	
	//TODO UNique virginity loss/dirty talk needed. Was previously using the same as DominionSuccubusAttacker, which didn't fit the situation.
	
}
