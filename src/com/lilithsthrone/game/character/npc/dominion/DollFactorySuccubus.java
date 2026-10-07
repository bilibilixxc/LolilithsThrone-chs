package com.lilithsthrone.game.character.npc.dominion;

import com.lilithsthrone.LolificationProject.Experiments.CharacterLoader;
import com.lilithsthrone.LolificationProject.Experiments.XMLLoader;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.*;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.*;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayDemonDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.time.Month;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Most of the methods relating to this class are found in Saellatrix's class.
 * This is because those methods needed to be accessed in a non-static way before an instance of this class has been created.
 * 
 * @since 0.4.9.12
 * @version 0.4.9.12
 * @author Innoxia
 */
public class DollFactorySuccubus extends NPC {
	
	public DollFactorySuccubus() {
		this(false);
	}
	
	public DollFactorySuccubus(boolean isImported) {
		super(isImported, null, "洛维耶纳马尔图拉尼",
				"",
				Util.random.nextInt(50)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(28),
				25,
				Gender.getGenderFromUserPreferences(true, false), Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(false, 10), 
				WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL,
				false);

		if(!isImported) {
//			this.setLocation(Main.game.getPlayer(), true);
			
			setLevel(Util.random.nextInt(11) + 20);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(false);
//			this.setGenericName("punished succubus");
			
//			// Set starting perks based on the character's race
//			initPerkTreeAndBackgroundPerks();
//			this.setStartingCombatMoves();
//			loadImages();
//			
//			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
	}
	
	@Override
	public void resetDefaultMoves() {
		this.clearEquippedMoves();
		this.equipMove("BASIC_STRIKE");
		this.equipMove("BASIC_TEASE");
		this.equipAllSpellMoves();
		this.equipAllSpecialMoves();
	}

	@Override
	public void setStartingPersona(boolean setPersonality, boolean setFetishes, boolean setOrientation, boolean setHistory, boolean setSpells) {
		if(setPersonality) {
			this.clearPersonalityTraits();
			
			this.setPersonalityTraits(
					PersonalityTrait.LEWD,
					PersonalityTrait.SELFISH);
		}
		
		if(setFetishes) {
			this.clearFetishes();
			this.clearFetishDesires();
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_MASOCHIST);
		}
		
		if(setOrientation) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		}

		if(setHistory) {
			this.setHistory(Occupation.NPC_SLAVE);
		}
		
		if(setSpells) {
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3);
			
			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3);

			this.addSpell(Spell.ARCANE_CLOUD);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_2);
			this.addSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_3);
		}
	}
	
	@Override
	public void setStartingBody(boolean setPersona) {
		if(setPersona) {
			setStartingPersona();
		}

		Main.game.getCharacterUtils().randomiseBody(this, true);
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.resetInventory(true);
		
		this.addTattoo(InventorySlot.TORSO_OVER,
				new Tattoo(
					"innoxia_symbol_pentagram",
					PresetColour.CLOTHING_GOLD,
					PresetColour.CLOTHING_GOLD,
					PresetColour.CLOTHING_GOLD,
					false,
					new TattooWriting(
							"赛拉特里克斯的财产",
							PresetColour.CLOTHING_GOLD,
							false),
					new TattooCounter(
							TattooCounterType.VALUE_AS_SLAVE,
							TattooCountType.NUMBERS,
							PresetColour.CLOTHING_GOLD,
							false,
							0)));
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		return UtilText.parse(this,
				"在恶魔政治游戏中，她被自己的姐姐赛拉特里克斯击败，彻底败下阵来，这个魅魔现在不过是个奴隶。"
				+ "为了利用她，赛拉特里克斯命令她监督在自己工厂工作的玩偶。");
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
	public Set<Relationship> getRelationshipsTo(GameCharacter character, Relationship... excludedRelationships) {
		if(character instanceof Saellatrix || character instanceof DollFactorySuccubus) {
			Set<Relationship> result = new LinkedHashSet<>();
			result.add(Relationship.HalfSibling);
			return result;
		}
		return super.getRelationshipsTo(character, excludedRelationships);
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AlleywayDemonDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", AlleywayDemonDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	// combat behaviour is tease and spells
	@Override
	public CombatBehaviour getCombatBehaviour() {
		boolean spellsAvailable = false;
		if(Main.game.isInCombat()) {
			for(GameCharacter character : Main.combat.getAllCombatants(true)) {
				if(!getWeightedSpellsAvailable(character).isEmpty()) {
					spellsAvailable = true;
					break;
				}
			}
		}
		if(spellsAvailable && Util.random.nextFloat()<0.6f) {
			return CombatBehaviour.SPELLS;
		}
		return CombatBehaviour.SEDUCE;
	}
	
	// Sex:

	@Override
	public int getUniqueSexPartnerCount() {
		return super.getUniqueSexPartnerCount() + 50 + (this.getLevel() * 3) + (int)(this.getAgeValue() * 0.8f) + (this.getAppearsAsAgeValue() * 2);
	}
	
	@Override
	public Value<AbstractItem, String> getSexItemToUse(GameCharacter partner) {
		if(this.equals(partner) && this.hasFetish(Fetish.FETISH_PENIS_GIVING)) { // Self-using for cock succubus
			if(this.isAbleToAccessCoverableArea(CoverableArea.MOUTH, false)) {
				if(!this.hasStatusEffect(StatusEffect.CUM_FULL)
						&& this.hasItemType(ItemType.REJUVENATION_POTION)
						&& Main.sex.getNumberOfOrgasms(this)<3) {
					return new Value<>(Main.game.getItemGen().generateItem(ItemType.REJUVENATION_POTION),
							Main.sex.isMasturbation()
							?UtilText.parse(this,
										Main.sex.getNumberOfOrgasms(this)==1
											?"那魅魔拿出一个装着紫色液体的玻璃瓶，拔掉瓶塞后朝身后一丢，将整瓶两三口便饮尽。"
												+ "活力药剂立刻生效，她巨大的蛋蛋明显胀得更大了，她愉悦地呻吟起来，"
												+ "[npc.speechNoEffects(~哦哦！~我操！好舒服！~唔嗯！~我真是个婊子……我好想再射一次！)]"
											:"那魅魔又拿出一个装满了紫色液体的玻璃瓶，拔掉瓶塞后朝身后一丢，将整瓶两三口便饮尽。"
												+ "与方才一样，活力药剂立刻生效，她巨大的蛋蛋明显胀得更大了，她激动地呻吟道，"
												+ "[npc.speechNoEffects(~啊啊！~妈的！我还想来一次……~唔嗯！~主人……我就是只坏狗狗……我好想射出来！)]")
							:UtilText.parse(this,
									Main.sex.getNumberOfOrgasms(this)==1
									?"那魅魔拿出一个装着紫色液体的玻璃瓶，拔掉瓶塞后朝身后一丢，将整瓶两三口便饮尽。"
										+ "活力药剂立刻生效，她巨大的蛋蛋明显胀得更大了，她愉悦地呻吟起来，"
										+ "[npc.speechNoEffects(~哦哦！~我操！一下子就满了！~唔嗯！~我准备好向你倾泻弹药了，婊子！)]"
									:"那魅魔又拿出一个装满了紫色液体的玻璃瓶，拔掉瓶塞后朝身后一丢，将整瓶两三口便饮尽。"
										+ "与方才一样，活力药剂立刻生效，她巨大的蛋蛋明显胀得更大了，她激动地呻吟道，"
										+ "[npc.speechNoEffects(~啊啊！~妈的！又给你上了一梭子，骚货！~唔嗯！~我要把你给塞得满满的……)]"));
				}
			}
		}
		return super.getSexItemToUse(partner);
	}
}
