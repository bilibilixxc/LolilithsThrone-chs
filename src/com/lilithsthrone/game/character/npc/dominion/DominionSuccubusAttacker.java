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
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayDemonDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.3.7.4
 * @author Innoxia
 */
public class DominionSuccubusAttacker extends NPC {
	
	public DominionSuccubusAttacker() {
		this(false);
	}
	
	public DominionSuccubusAttacker(boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(50)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5, Gender.getGenderFromUserPreferences(Femininity.FEMININE), Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(false, 10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
//			if(!Gender.getGenderFromUserPreferences(false, false).isFeminine()) {
//				this.setBody(Gender.M_P_MALE, Subspecies.DEMON, RaceStage.GREATER, true);
//				this.setGenderIdentity(Gender.M_P_MALE);
//			}
			
			Gender gender = Gender.getGenderFromUserPreferences(false, false);
			this.setBody(gender, Subspecies.DEMON, RaceStage.GREATER, true);
			this.setGenderIdentity(gender);
			
			
			Main.game.getCharacterUtils().randomiseBody(this, true);

			Main.game.getCharacterUtils().setHistoryAndPersonality(this, false);
			this.setHistory(Occupation.NPC_MUGGER); // All demon alleyway attackers are muggers
			
			addFetish(Fetish.FETISH_DEFLOWERING);
			addFetish(Fetish.FETISH_DOMINANT);
			Main.game.getCharacterUtils().addFetishes(this);

			this.removePersonalityTrait(PersonalityTrait.PRUDE);

			setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			setLevel(Util.random.nextInt(5) + 8);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(false);
			
			// Set random inventory & weapons:
			resetInventory(true);
			inventory.setMoney(50);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			// CLOTHING:
			
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);

			if(hasFetish(Fetish.FETISH_CUM_ADDICT) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);

			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();
			
			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
		}
	}

	@Override
	public void loadFromXML(XMLLoader loader, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, loader, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceAbsolute(18+Util.random.nextInt(10));
		}
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
		if(isSlave()) {
			return UtilText.parse(this,
					"[npc.name]终于屈服于其汹涌的性欲，便在御城区的巷子里潜行，寻找无辜的过路者强暴。"
					+ "很不幸，[npc.she]上了执法者的通缉单，还遇上了你，最终受到奴役，为你所有了。");
		}
		return UtilText.parse(this,
				"虽然所有恶魔都有极强的性欲，但有些恶魔的性欲比其他恶魔更甚。"
				+ "这其中又有许多能够控制住其渴望，但另外的，例如这个[npc.race]，脑内除了如何征服下一个受害者，已经别无他物。");
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
		return AlleywayDemonDialogue.DEMON_ATTACK;
	}

	@Override
	public boolean isAffectionHighEnoughToInviteHome() {
		if(this.isRelatedTo(Main.game.getPlayer())) {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue();
		} else {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
		}
	}

	// Combat:

	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		if(!this.isSlave() && this.getMainWeapon(0)==null) {
			if(this.isFeminine()) {
				return UtilText.parse(this, target,
						UtilText.returnStringAtRandom(
								"[npc.Name]见到[npc2.nameIs]想要反抗，顿时感觉有些恼火，纵身一跃朝着[npc2.her]的脸上打去。",
								"[npc.Name]怒而咋舌，朝着[npc2.name]的脸上就扇了过去。",
								"[npc.Name]发出一声沮丧的呜咽，一脚踢向[npc2.namePos]的小腿。"));
			} else {
				return UtilText.parse(this, target,
						UtilText.returnStringAtRandom(
								"[npc.Name]见到[npc2.nameIs]想要反抗，顿时感觉有些恼火，纵身一跃朝着[npc2.her]的[npc2.arm]上打去。",
								"[npc.Name]一声怒喝，飞身向前，一拳正中[npc2.name]的胸口！",
								"[npc.Name]懊恼地喝了一声，朝着[npc2.namePos]的小腿就踢了过去。"));
			}
		}
		return super.getMainAttackDescription(armRow, target, isHit, critical);
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AlleywayDemonDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", AlleywayDemonDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex() && !target.isAsleep()) {
			if((Main.sex.isDom(Main.game.getPlayer()) || Main.sex.isSubHasEqualControl()) && !target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return UtilText.parse(equipper, target,
						"<p>"
							+ "[npc.Name]递给[npc2.name]一个避孕套，必须要[npc2.herHim]戴上。"
							+ "[npc2.she]迅速撕开了铝箔包装，卷着将避孕套完整地戴在了[npc2.cock+]上，随后呜咽道，"
							+ "[npc2.speech(一定要这样吗？不戴着可舒服多了……)]"
						+ "</p>");
				
			} else if (!target.isPlayer()) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name]将[npc.her]的丝囊对准了[npc2.namePos]的[npc2.cock]，但当[npc2.name]意识到[npc.sheIs]的意图后，立刻将其拍开，冷笑道，"
							+ "[npc2.speech(哈！我可没这个打算！)]");
				}
				return UtilText.parse(equipper, target,
						"<p>"
							+ "[npc.Name]拿出一个避孕套想交给[npc2.name]，但[npc2.she]却只轻笑一声，便把那铝箔包装袋抢了过来，撕成了两半。"
							+ "[npc2.she]嘲笑着[npc.namePos]让[npc2.herHim]套上套那异想天开的想法，冷冷地说道，"
							+ "[npc2.speech(哈！我可没这个打算！)]"
						+ "</p>");
			}
		}
		return null;
	}
	
	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer() || penetrating!=SexAreaPenetration.PENIS || penetrated!=SexAreaOrifice.VAGINA || !penetratingCharacter.equals(this) || this.isSlave()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().hasHymen()) {
			sb.append("<p>"
							+ "那个高高在上的[npc.race]将[npc.her][npc.cock+]深深塞进了你[pc.pussy+]，你顿时感到眼冒金星，一阵痛苦的尖叫从唇间挤了出来。"
							+ "由于你还是个处女，这突然的暴力插入带来的疼痛是前所未有的，"
								+ "你的尖叫化作了颤抖的哀嚎，躯体也因初次插入带来的剧痛而扭来扭去。"
						+ "</p>"
						+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)
								?"<p>"
									+ "由于你是个极端的受虐狂，你意识到自己痛苦的哭喊掺杂了欢愉而淫荡的呻吟。"
									+ "股间的剧痛反而带来纯粹的愉悦，你专注于这份痛感，在狂喜之中尖叫呻吟着。"
								+ "</p>"
								:"")
						+"<p>"
							+ "你低头一瞥，只见一小股鲜血从你被刺入的小穴中淌出来，你明白自己的处女膜已经完全被撕裂了。"
							+ "你绝望地哀叹着，却听到那[npc.race]对着你的反应欣喜地笑了起来。"
						+ "</p>"
						+"<p>"
							+ "你抬头看向[npc.herHim]，那眼中竟满是疯狂。"
							+ "[npc.she]将[npc.cock+]又送入你[pc.pussy+]的更深处，另一阵剧痛从股间传来。"
							+ "[npc.she]俯身靠近你，将你拖入了一场湿吻，随后又是一次猛力的突刺，你感觉得到[npc.her]的肉棒根部已经紧紧挤压在了你[pc.labia+]上。"
						+ "</p>"
						+ "<p>"
							+ "[npc.she]依然将[npc.cock]整根塞进着你的[pc.pussy]，与你从深吻中分开，举起[npc.hand]又捂住了你的嘴巴，哭喊声变得含混不清。"
							+ "[npc.she]饶有兴致地轻咬下唇，因为夺取了你的处女而大为兴奋，朝着你嘲讽起来。"
						+ "</p>"
						+ "<p>"
							+ "[npc.speechNoEffects(~姆嗯！~是不是感觉很爽？！噢……我猜你应该是为了某个人特意留下的吧？)]"
							+ "[npc.She]停下来发出一段尖锐的嘲笑声，接着又说道，"
							+ "[npc.speechNoEffects(哎呀，看样子你是专门为我留的了！唉，真是可惜，因为你不过是我用来发泄的玩具罢了。)]"
						+ "</p>"
						+ "<p>"
							+ "你反对地哼唧了几声，但那[npc.race]却只是把那只[npc.hand]捂得更紧了，继续说着，"
							+ "[npc.speechNoEffects(又怎么了？你还想让我再来一次吗？！)]"
						+ "</p>"
						+ "<p>"
							+ "那[npc.race]狂笑着，突然一缩腰身，将[npc.her][npc.cock+]整根都从你[pc.pussy+]中抽了出来。"
							+ "[npc.she]再次调整好位置，紧接着又用力向前，第二次刺入你的体内。"
							+ "尽管依然十分难受，喊叫声不免得又一次在[npc.her]的掌心中回响，但至少没有第一次插入时那般痛苦了，"
								+ "你感受到[npc.her]颤动的肉棒再次填满了你的身体，绝望的呻吟脱口而出。"
						+ "</p>"
						+ "<p>"
							+ "那[npc.race]欣赏着你的反应，格外愉悦，将身体凑近过来，继续调戏道，"
							+ "[npc.speechNoEffects(~哦哦！~你是我这恶魔大屌的小骚货了吗？你感觉到了吗，那<i>深深</i>刺入体内，夺取了你珍贵的处女的玩意？！)]"
						+ "</p>"
						+ "<p>"
							+ "[npc.she]继续压低着身子，随后在你的[pc.pussy]之中快速抽插起来，同时把[npc.hand]从你的嘴巴上移了开来，你却发现自己的口中竟不断地发出淫荡的呻吟。"
							+ "[npc.she]转而把住了你的腰部，口中的嘲讽一刻不断，重复地说着，你将会永远记住这一刻——意识到自己只不过是粗大的恶魔鸡巴的俘虏罢了。"
							+ "股间的疼痛逐渐只剩下微微的隐痛，口中的呻吟却一声接着一声，你有些担心起来，那[npc.race]说的可能没错……"
						+ "</p>");
			
		} else {
			sb.append(
					"<p>"
						+ "那个高高在上的[npc.race]将[npc.her][npc.cock+]深深塞进了你[pc.pussy+]，你脱口而出一声绝望的哀嚎。"
						+ "由于你还是个处女，这突然的暴力插入带来的疼痛是前所未有的，哀嚎逐渐化作颤抖的哭喊，这种从未体验过的感受淹没了你。"
					+ "</p>"
					+"<p>"
						+ "由于处女膜此前已经破裂，插入并没有那么痛，但令你意想不到的是，[npc.name]仍旧知道[npc.sheHas]夺取了你的处女。"
						+ "[npc.name]的眼中满是疯狂，[npc.she]暴力地向你的下体刺去，将[npc.cock+]一直插到入了你[pc.pussy+]，直到不能再深。"
						+ "[npc.Name]让[npc.her]颤抖的恶魔肉棒保持在你[pc.pussy+]深处，饶有兴致地舔了舔[npc.lips]，向你嘲讽起来。"
					+ "</p>"
					+ "<p>"
						+ "[npc.speechNoEffects(~姆嗯！~让恶魔的大屌塞满小穴的感觉是不是很爽？！~哦哦！~我猜你的第一次是不是给某个特别的人保留的？)]"
						+ "[npc.she]问过后就停了下来，发出一段尖锐的嘲笑声。[npc.she]显然是因为夺取了你的处女而大为兴奋，急忙继续道，"
						+ "[npc.speechNoEffects(看样子到头来是让我占了先！唉，真是可惜，因为我只不过是随便操你一顿罢了。)]"
					+ "</p>"
					+ "<p>"
						+ "你感受到[npc.namePos][npc.cock+]在你的蜜穴中颤动着，按捺不住发出一声[pc.a_moan+]，这反而让[npc.herHim]又奸笑起来，恶心地问道，"
						+ "[npc.speechNoEffects(又怎么了？你还想让我再来一次吗？！)]"
					+ "</p>"
					+ "<p>"
						+ "那[npc.race]来不及听你的恢复，便猛地一抽身，让[npc.cock+]从你[pc.pussy+]中完全抽离了出来。"
						+ "[npc.she]再次调整好位置，紧接着又用力向前，第二次刺入你的体内，你感受到[npc.her]温热的肉棒再次填满了你的身体，绝望的[pc.moan]脱口而出。"
						+ "[npc.Name]欣赏着你的反应，格外愉悦，将那恶魔肉棒压得更深，继续调戏道，"
						+ "[npc.speechNoEffects(~哦哦！~你是我这恶魔大屌的小骚货了吗？你喜欢这感觉吗？~姆嗯！~我的鸡巴<i>深深</i>刺入体内，夺取了你珍贵的处女的感觉？！)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.she]稍稍收了收腰，随后在你[pc.pussy+]中快速抽插起来，听到你发出一连串下流的[pc.moans]后，脸上布满了愉悦的笑意。"
						+ "[npc.her]的阴茎继续在你的细缝中进出着，[npc.name]口中的嘲讽一刻不断，重复地说着，你将会永远记住这一刻——意识到自己只不过是粗大的恶魔鸡巴的俘虏罢了。"
						+ "[npc.she]说着说着，你也意识到自己口中的呻吟却一声接着一声，便有些担心起来，那[npc.race]说的可能没错……"
					+ "</p>");
		}
		
		return UtilText.parse(this, sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
						+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>失格处女</b>"
					+ "</p>"
					+ "<p>"
						+ "那[npc.race]在你的股间不断抽送着，刚刚发生的一切，犹如一记重锤砸在你的心上。"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ UtilText.parsePlayerThought("我，我就这样失贞了？！<br/>"
								+ "给了……<b>这家伙</b>？！")
					+ "</p>"
					+ "<p>"
						+ "你不知道哪个更糟糕；是失去了这样看重的第一次，还是自己反而乐在其中。"
						+ "你那阴唇被那滚烫粗壮的恶魔鸡巴淫靡地撑开着，你不免有些认同那[npc.race]的话了。"
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("如果我不再是处女，那我就是个骚货了……<br/>"
							+ "只是个被恶魔肉棒征服的骚货……<br/>"
							+ "她说得对，我只不过是她这种人随便打一炮的家伙罢了……")
					+ "</p>"
					+ "<p>"
						+ "你隐约感觉到那[npc.race]似乎不再那么频繁地嘲弄你，[npc.she]将精力逐渐集中到了性交上。"
						+ "你迫不及待地呻吟着，自己也主动晃动起腰身，接受了这个事实，你不过只是个"
						+ "<b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>失格处女</b>……"
					+ "</p>";
	}
	
	// Dirty talk:
	
	@Override
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		if(this.isSlave()) {
			return super.getDirtyTalkNoPenetration(target, isPlayerDom);
		}
		
		List<String> speech = new ArrayList<>();
		
		if(isPlayerDom && Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING){
			speech.add("来吧，操我吧！");
			speech.add("来吧！怎么磨蹭这么久？！");
			speech.add("操我吧！");
			speech.add("开始吧！快来！");
		} else if(!isPlayerDom) {
			speech.add("我要把你变成被恶魔肉棒征服的荡妇！");
			speech.add("你被恶魔操过吗？");
			speech.add("在结束之前，你就会求着要我的精液！");
			speech.add("你就要变成一个乖乖小婊子了！");
		} else {
			return super.getDirtyTalkNoPenetration(target, isPlayerDom);
		}
		
		String returnedLine = speech.get(Util.random.nextInt(speech.size()));
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}
}
