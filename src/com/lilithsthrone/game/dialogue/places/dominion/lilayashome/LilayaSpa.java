package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Arthur;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.dominion.Rose;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMShower;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.3.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class LilayaSpa {
	
	public static final String SPA_CONSTRUCTTION_TIMER_ID = "spa_construction_timer";
	
	private static Cell cellInstallation = null;
	
    private static List<GameCharacter> slavesWashing;
    private static List<GameCharacter> slavesSex;
	private static List<GameCharacter> bathingStripped;
    private static List<GameCharacter> slavesForMassage;

	private static GameCharacter drinksCharacter = null;
	private static GameCharacter massageSlave = null;
	private static GameCharacter guest = null;
	private static boolean playerStripped = false;
	
	private static boolean massageSlaveSex = false;
	
    private static List<AbstractItemType> getDrinks() {
    	return Util.newArrayListOfValues(
    			ItemType.getItemTypeFromId("innoxia_race_human_vanilla_water"),
    			ItemType.getItemTypeFromId("innoxia_race_bat_fruit_bats_juice_box"),
    			ItemType.getItemTypeFromId("innoxia_race_dog_canine_crush"),
    			ItemType.getItemTypeFromId("innoxia_race_horse_equine_cider"),
    			ItemType.getItemTypeFromId("innoxia_race_cat_felines_fancy"),
    			ItemType.getItemTypeFromId("innoxia_race_fox_vulpines_vineyard"),
    			ItemType.getItemTypeFromId("innoxia_race_rat_black_rats_rum"),
    			ItemType.getItemTypeFromId("innoxia_race_wolf_wolf_whiskey"));
    }
    
    public static void setCellInstallation(Cell cellInstallation) {
    	LilayaSpa.cellInstallation = cellInstallation;
    }
    
	private static List<GameCharacter> getSlaves() {
		List<GameCharacter> charactersPresent = new ArrayList<>(Main.game.getNonCompanionCharactersPresent());
		charactersPresent.removeIf(character -> !character.isSlave());
		return charactersPresent;
	}
	
	public static void initGuestAtSpa(GameCharacter guest) {
		LilayaSpa.guest = guest;
		drinksCharacter = null;
	}
	
	public static boolean isGuestAbleToEquipSwimwear(GameCharacter guest) {
		if(guest.isFeminine()) {
			AbstractClothing swimsuit = Main.game.getItemGen().generateClothing("innoxia_chest_swimsuit", PresetColour.CLOTHING_PINK, false);
			AbstractClothing bikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_bikini", PresetColour.CLOTHING_PINK, false); // innoxia_chest_micro_bikini
			AbstractClothing bikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_bikini", PresetColour.CLOTHING_PINK, false); // innoxia_groin_micro_bikini
			
			return guest.isAbleToEquip(swimsuit, true, guest) || (guest.isAbleToEquip(bikiniTop, true, guest) && guest.isAbleToEquip(bikiniBottom, true, guest));
			
		} else {
			AbstractClothing swimShorts = Main.game.getItemGen().generateClothing("innoxia_groin_swim_shorts", PresetColour.CLOTHING_BLUE, false);
			return guest.isAbleToEquip(swimShorts, true, guest);
		}
	}
	
	private static boolean isGuestPresent() {
		return guest!=null;
	}
	
	// Reception slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> receptionGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "看到你进入了接待区后，[npc.Name]向你行礼，然后开始询问，"
						+ "#ELSE"
							+ "[npc.Name]看到你进入了接待区后，[npc.she]向你鞠躬然后开始询问，"
						+ "#ENDIF",
						"给了你一个标志性的微笑后，[npc.Name]询问道，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]身体向前倾过柜台用最诱人的声音向你询问，"
						+ "#ELSE"
							+ "[npc.Name]注视着你的[pc.eyes]然后用最诱人的声音向你询问，"
						+ "#ENDIF",
						"[npc.Name]给你一个诱人的微笑然后询问，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]轻咬[npc.her]的嘴唇发出一声不合适的娇喘然后询问，"
						+ "#ELSE"
							+ "[npc.Name]在你进来后色咪咪的看着你，然后直接了当地询问，"
						+ "#ENDIF",
						"[npc.Name]给你一个淫荡的笑容然后询问，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]看到你进入接待区后向你行屈膝礼然后询问，"
						+ "#ELSE"
							+ "[npc.Name]看到你进入接待区后向你微笑然后询问，"
						+ "#ENDIF",
						"[npc.Name]对你微微一笑然后问道，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]看到你进入接待区后小声发出高兴的尖叫然后急切地询问道，"
						+ "#ELSE"
							+ "[npc.Name]看到你进入接待区后高兴的对你微笑然后询问道，"
						+ "#ENDIF",
						"[npc.Name]给你一个充满爱意的微笑然后急切的询问道，")));

	private static Map<SlavePermissionSetting, List<String>> receptionGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]看到你进入接待区后向你行礼然后在一旁恭敬的等待着看你是否会向[npc.herHim]问些什么。"
						+ "#ELSE"
							+ "[npc.Name]看到你进入接待区后向你鞠躬然后在一旁恭敬的等待着看你是否会向[npc.herHim]问些什么。"
						+ "#ENDIF",
						"[npc.Name]给你一个微笑然后顺从等待着，看[npc.she]能否帮到你什么。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]身体向前倾过柜台然后对你露出最诱人的微笑，等待着看你是否会问些什么。"
						+ "#ELSE"
							+ "[npc.Name]注视着你的[pc.eyes]露出诱人的微笑，等待着你的询问。"
						+ "#ENDIF",
						"[npc.name]向你露出一个诱人的微笑，等着看[npc.she]是否能帮上忙。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]轻咬嘴唇发出一声不合时宜的淫荡的轻喘，等待着看你会问些什么。"
						+ "#ELSE"
							+ "当你进门时，[npc.Name]色眯眯地冲你笑，然后等着看你是否会问[npc.herHim]什么。"
						+ "#ENDIF",
						"[npc.name]向你露出淫荡的笑容，等着看[npc.she]是否能帮你什么忙。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "当[npc.she]看到你进入接待区时，[npc.Name]会弯腰行礼，然后恭敬地等待你是否会向[npc.herHim]询问什么。"
						+ "#ELSE"
							+ "当[npc.Name]看到你进入接待区时，[npc.she]微笑着，然后尽职地等待着，看你是否会向[npc.herHim]询问什么。"
						+ "#ENDIF",
						"[npc.Name]对你微微一笑然后等待着想知道[npc.she]可以帮你做些什么。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "当[npc.she]看到你进入接待区时，[npc.Name]高兴地叫了一声，然后就恭恭敬敬地等着看你是否会问[npc.herHim]什么。"
						+ "#ELSE"
							+ "当[npc.Name]看到你进入接待区时，[npc.she]开心地笑了，然后就恭恭敬敬地等着看你是否会问[npc.herHim]什么。"
						+ "#ENDIF",
						"[npc.Name]对你露出充满爱意的微笑，在一旁开心地等待着，想知道[npc.she]有什么可以帮你的。")));

	private static Map<SlavePermissionSetting, List<String>> receptionSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"我能帮到你什么吗，[pc.name]？",
						"有什么需要我帮忙的吗，[pc.name]？",
						"你想在进入水疗中心前洗个澡吗，[pc.name]？")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"你需要一些<i>私人的</i>帮助吗？",
						"有什么我可以帮你的<i>事情</i>吗，[pc.name]？",
						"我今天能为你<i>做些什么</i>，[pc.name]？")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))"
							+ "不如你把我压在桌子人然后把我操晕？"
						+ "#ELSE"
							+ "你想好好干一炮对吧？"
						+ "#ENDIF",
						"来吧，操我吧！",
						"来做吧，[pc.name]！")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"有什么我可以帮你的吗，[pc.name]？",
						"我可以为你做些什么，[pc.name]？",
						"有什么我能帮忙的吗，[pc.name]？")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"嗨，[pc.name]，我能为你做些什么吗？！我很高兴能再次见到你！",
						"我可以为你做些什么，[pc.name]？！",
						"嗨，[pc.name]，今天我能为你做些什么？！")));
	
	private static List<String> receptionRudeGreetings = Util.newArrayListOfValues(
			"[npc.name]怒视着你，不服气地绷着脸，",
			"[npc.name][npc.eyes+]中充满怨恨，怒视着你，厉声说，",
			"[npc.name]显然对被迫在水疗中心工作很不开心，生气地怒吼，");

	private static List<String> receptionRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.name]怒视着你，不服气地绷着脸，[npc.sheHasFull]没有兴趣帮你做任何事情……",
			"[npc.name][npc.eyes+]中充满怨恨，怒视着你，发出叛逆的咆哮。",
			"[npc.name]显然对被迫在水疗中心工作很不开心，朝你生气地怒吼，[npc.arms]交叉在胸前，拒绝为你提供任何帮助。");
	
	private static List<String> receptionRudeSpeech = Util.newArrayListOfValues(
			"你他妈现在想干嘛？<i>[pc.name]</i>？",
			"你他妈快点走吧。",
			"不如你转身然后滚蛋？");
	
	
	// Shower slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> showerGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"把肥皂泡沫放在[npc.her]的[npc.hands]上，[npc.Name]一边给你搓背，一边说道，",
						"[npc.Name]一边非常专业的给你的身上擦肥皂，一边说道，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]身子倾斜着把[npc.her][npc.breasts+]贴着你的背，挑逗道，"
						+ "#ELSE"
							+ "[npc.Name]靠过来，把[npc.herself]压在你的背上，挑逗着你，"
						+ "#ENDIF",
						"#IF(npc.isFeminine())"
							+ "[npc.Name]挑逗地用[npc.her]沾满肥皂的[npc.fingers]抚摸你的身体，"
						+ "#ELSE"
							+ "[npc.name]挑逗地用[npc.her]沾满肥皂的[npc.hands]抚摸你的身体，"
						+ "#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.name]毫无廉耻地在你背上涂抹[npc.her]沾满肥皂的[npc.breasts]，冒犯地问道，"
						+ "#ELSE"
							+ "[npc.name]毫无廉耻地将[npc.her]沾满肥皂的身体贴在你的背上，冒犯地问道，"
						+ "#ENDIF",
						"当[npc.she]用[npc.her]沾满肥皂[npc.hands]在你的[pc.skin]上揉搓时，[npc.name]指着你的身体问，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"把肥皂泡沫放在[npc.her]的[npc.hands]上，[npc.Name]一边给你搓背，一边说道，",
						"[npc.name]尽职尽责地从事[npc.her]的工作，在你身上擦抹肥皂，说，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]爱怜地用[npc.her]沾满肥皂的手抚摸你的身体，然后靠在你身上喘息，"
						+ "#ELSE"
							+ "[npc.Name]爱怜地用[npc.her]沾满肥皂的手抚摸你的身体，然后靠在你身上说，"
						+ "#ENDIF",
						"当[npc.she]在你身上擦肥皂时，[npc.name]高兴地对[npc.herself]哼着歌，")));

	private static Map<SlavePermissionSetting, List<String>> showerGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"[npc.name]在[npc.her][npc.hands]上涂满肥皂，开始以专业的方式为你搓背。",
						"[npc.name]以专业的方式开始在你身上涂抹肥皂，帮助你清洁身体。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]靠过来，诱惑地将[npc.her][npc.breasts+]压在你背上，[npc.she]帮你清洁。"
						+ "#ELSE"
							+ "[npc.Name]靠过来，诱惑地将自己压在你背上，[npc.she]帮你清洁。"
						+ "#ENDIF",
						"#IF(npc.isFeminine())"
							+ "[npc.name]在[npc.she]帮你清洗身体时，诱惑地用沾满肥皂的[npc.fingers]挑逗你的身体，[npc.Name]对[npc.herself]傻笑。"
						+ "#ELSE"
							+ "[npc.name]用沾满肥皂的手在你身上摸来摸去，[npc.she]一边帮你清洗，一边对[npc.herself]傻笑。"
						+ "#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.name]毫无廉耻地用涂满肥皂的[npc.breasts]在你的背上摩擦，伸手在你的腹股沟上狠狠地摸索，发出饥渴的[npc.moan]。"
						+ "#ELSE"
							+ "[npc.name]毫无廉耻地将[npc.her]涂满肥皂的身体贴在你的背上，伸手在你的腹股沟上狠狠地摸索，发出饥渴的[npc.moan]。"
						+ "#ENDIF",
						"[npc.name]用[npc.her]沾满肥皂的[npc.hands]在你的[pc.skin]上揉搓清洁，并发出饥渴的[npc.moan]。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"[npc.name]在[npc.her][npc.hands]上抹满肥皂，尽职尽责地开始帮你洗背。",
						"[npc.name]尽职尽责地开始[npc.her]的工作，帮你擦拭身体。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.name]爱怜地用[npc.her]沾满肥皂手在你身上摸来摸去，然后靠在你身上，发出一声幸福的叹息。"
						+ "#ELSE"
							+ "[npc.name]爱怜地用[npc.her]沾满肥皂手在你身上摸来摸去，然后靠在你身上，发出一声幸福的叹息。"
						+ "#ENDIF",
						"[npc.name]开心地在你身上涂满肥皂后，靠在你身上，发出满足的喘息。")));

	private static Map<SlavePermissionSetting, List<String>> showerSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"我很快会把你清洗干净的，[pc.name]。",
						"这只需要一会，[pc.name]。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"你知道我们在这里能做的不仅仅是清洁……",
						"也许我们可以在这里多待一会……")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))"
							+ "来吧，[pc.name]，把我推到墙上，好好操我一顿！"
						+ "#ELSE"
							+ "来吧，[pc.name]，让我们来做吧！"
						+ "#ENDIF",
						"来吧，操我吧！")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"做完这个，你会变得干干净净的，[pc.name]。",
						"我马上就会把你洗干净，[pc.name]。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"我希望你喜欢这个，[pc.name]……",
						"你喜欢这样吗，[pc.name]？")));
	
	private static List<String> showerRudeGreetings = Util.newArrayListOfValues(
			"[npc.Name]不情愿地帮你清洁，愤怒地皱起眉头，",
			"[npc.Name]漫不经心地帮你清洗身体，怒视着你咆哮，",
			"[npc.Name]充满怨恨地帮你清洁，愤怒地讥讽，");

	private static List<String> showerRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.Name]不情愿地帮你清洁，愤怒地皱起眉头……",
			"[npc.Name]漫不经心地帮你清洗身体，一直怒视着你……",
			"[npc.Name]充满怨恨地帮你清洁，怒视着你……");
	
	private static List<String> showerRudeSpeech = Util.newArrayListOfValues(
			"你他妈的快点，我好赶紧离开这里……",
			"我非常讨厌这样……",
			"我他妈为什么要这么做？");
	
	
	// Bathing slave dialogues:
	
	private static Map<SlavePermissionSetting, List<String>> bathingGreetings = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"[npc.Name]缓缓没入水池的热水中，叹了口气，",
						"[npc.she]进入池塘，发出长长的呻吟，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]轻咬[npc.lip]，[npc.she]给你一个诱人的眼神，调侃着你，"
						+ "#ELSE"
							+ "[npc.Name]给你一个诱人的调笑，调侃着你，"
						+ "#ENDIF",
						"#IF(npc.isFeminine())"
							+ "[npc.name]挑逗地用[npc.her]湿润的[npc.fingers]抚摸你的身体，"
						+ "#ELSE"
							+ "[npc.name]挑逗地用[npc.her]湿润的[npc.hands]抚摸你的身体，"
						+ "#ENDIF")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"[npc.name]向你猛泼池水，冒犯地问道，",
						"[npc.name]抚动摸索着你的身体，[npc.she]指着你的身体问，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"[npc.Name]缓缓没入水池的热水中，叹了口气，",
						"[npc.she]进入池塘，发出长长的呻吟，")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"[npc.Name]缓缓没入水池的热水中，脸颊微红，叹了口气，",
						"当[npc.she]滑进热水时，[npc.name]高兴地对[npc.herself]哼着歌，")));

	private static Map<SlavePermissionSetting, List<String>> bathingGreetingsMute = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"[npc.Name]缓缓没入水池的热水中，发出一声轻叹，享受放松的机会。",
						"[npc.name]进入池塘，发出轻不可闻的呻吟，她闭上[npc.eyes]享受热水浴。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"#IF(npc.isFeminine())"
							+ "[npc.Name]走近你坐的位置，[npc.she]轻咬[npc.her][npc.lip]，给你一个诱人的眼神。"
						+ "#ELSE"
							+ "[npc.Name]走近你坐的位置，暧昧地与你调笑。"
						+ "#ENDIF",
						"[npc.Name]挑逗地用[npc.her]湿润的[npc.hands]抚摸自己的身体，暗示地向你眨眼。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"[npc.name]朝你猛泼池水，粗鲁地示意你去操[npc.herHim]……",
						"[npc.name]抚动摸索着你的身体，发出饥渴的呻吟。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
							"[npc.Name]缓缓没入水池的热水中，发出一声轻叹，享受放松的机会。",
							"[npc.name]进入池塘，发出轻不可闻的呻吟，她闭上[npc.eyes]享受热水浴。")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"[npc.Name]缓缓没入水池的热水中，向你微笑，脸颊微红。",
						"[npc.name]滑进热水，[npc.she]高兴地对[npc.herself]哼着歌，对你露出充满爱意的微笑。")));

	private static Map<SlavePermissionSetting, List<String>> bathingSpeech = Util.newHashMapOfValues(
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL,
					Util.newArrayListOfValues(
						"如果你需要什么请告诉我，[pc.name]……",
						"这是最让人兴奋的……")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE,
					Util.newArrayListOfValues(
						"你知道我们现在除了放松还可以做一些别的事……",
						"或许我们有办法让这更刺激？")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_SLUTTY,
					Util.newArrayListOfValues(
						"#IF(npc.hasFetish(FETISH_SUBMISSIVE))"
							+ "来吧，[pc.name]，好好操我一顿！"
						+ "#ELSE"
							+"来做吧，[pc.name]！"
						+ "#ENDIF",
						"嘿，我们在水池里做爱怎么样？听起来不错对吧？")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_STANDARD,
					Util.newArrayListOfValues(
						"这感觉太棒了……",
						"谢谢你让我能加入你们，[pc.name]……")),
			new Value<>(
					SlavePermissionSetting.BEHAVIOUR_WHOLESOME,
					Util.newArrayListOfValues(
						"这感觉真好…… 非常感谢你让我和你一起，[pc.name]……",
						"谢谢你和我分享，[pc.name]……")));
	
	private static List<String> bathingRudeGreetings = Util.newArrayListOfValues(
			"[npc.Name]不情愿地和你进入水池，愤怒地皱起眉头，",
			"[npc.Name]溅落到水池的热水中，绷着脸怒视着你，",
			"[npc.Name]充满怨恨地和你进入水池，愤怒地讥讽，");

	private static List<String> bathingRudeGreetingsMute = Util.newArrayListOfValues(
			"[npc.Name]不情愿地和你进入水池，愤怒地皱起眉头……",
			"[npc.Name]溅落到水池的热水中，绷着脸怒视着你……",
			"[npc.Name]充满怨恨地和你进入水池，怒视着你……");
	
	private static List<String> bathingRudeSpeech = Util.newArrayListOfValues(
			"水疗不错，但如果你不在的话会更好……",
			"赶紧滚吧，这样我又可以一个人享受了……",
			"赶紧从这里滚开……");
	
	
	private static String getReceptionSlavesDescription(List<GameCharacter> slavesReception) {
		if(slavesReception.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesReception.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesReception) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append("被你安排在你的水疗中心做接待员的奴隶， "+Util.stringsToStringList(names, false)+", 正站在柜台前。");
			} else {
				sb.append("被你安排在你的水疗中心做接待员的奴隶们， "+Util.stringsToStringList(names, false)+",正站在柜台前。");
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesReception.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(receptionGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(receptionGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(receptionSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+"[npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesReception);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(receptionRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(receptionRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(receptionRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+"[npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	private static String getShowerSlavesDescription(List<GameCharacter> slavesWashing) {
		if(slavesWashing.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing, "奉命协助你清洗自己，你的奴隶， "+Util.stringsToStringList(names, false)+"，[npc.steps]进入淋浴间，准备开始为你清洗……"));
			} else {
				sb.append(UtilText.parse(slavesWashing, "奉命协助你清洁自己，你的奴隶们，"+Util.stringsToStringList(names, false)+"，[npc.step]进淋浴间，准备开始为你清洗……"));
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(showerGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(showerGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(showerSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+"[npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesWashing);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(showerRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(showerRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(showerRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+"[npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}

	private static String getSpaSlavesDescription(List<GameCharacter> slavesPresent) {
		if(slavesPresent.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		boolean soloSlave = slavesPresent.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesPresent) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append("[style.boldMinorGood(在场的奴隶)]<b>:</b>"
						+ "<br/>");
				sb.append(UtilText.parse(slavesPresent, "你的奴隶坐在泳池边的躺椅上，"+Util.stringsToStringList(names, false)+"，正在等待你向[npc.herHim]下达命令。"));
				if(slavesPresent.get(0).hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)) {
					sb.append(UtilText.parse(slavesPresent,
							"</p>"
							+ "<p>[style.boldAqua(沐浴)]<b>:</b>"
							+ "<br/>按照你的指示"+Util.stringsToStringList(names, false)+"会和你一起在水疗中心沐浴，如果你进入某个水池，[npc.she]也会陪着你。"));
				}
				if(slavesPresent.get(0).hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE)) {
					sb.append(UtilText.parse(slavesPresent,
							"</p>"
							+ "<p>[style.boldTan(按摩)]<b>:</b>"
							+ "<br/>"+Util.stringsToStringList(names, false)+"已经接受了一些如何进行按摩的培训，"
									+ "所以，如果你想让[npc.herHim]给你来一次，你只需要躺在其中一张躺椅上，然后把[npc.herHim]叫过来。"));
				}
				
			} else {
				sb.append("[style.boldMinorGood(在场的奴隶)]<b>:</b>"
						+ "<br/>");
				sb.append(UtilText.parse(slavesPresent, "坐在泳池边的躺椅上，你的奴隶们， "+Util.stringsToStringList(names, false)+"，他们正在等待你的命令。"));
				List<String> bathingNames = new ArrayList<>();
				GameCharacter bathingSlave = null;
				for(GameCharacter npc : slavesPresent) {
					if(npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)) {
						bathingSlave = npc;
						bathingNames.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
					}
				}
				if(!bathingNames.isEmpty()) {
					sb.append("</p>"
							+ "<p>[style.boldAqua(沐浴)]<b>:</b>"
							+ "<br/>");
					if(bathingNames.size()==1) {
						sb.append(UtilText.parse(bathingSlave,
								"由于你已指示[npc.herHim]每次在水疗中心沐浴时都与你一起，因此你可以确定 "+Util.stringsToStringList(bathingNames, false)+"如果你进入其中一个水池，它将伴你左右。"));
					} else {
						sb.append("由于你已嘱咐他们每次在水疗中心沐浴时都要和你一起，因此你可以确信 "+Util.stringsToStringList(bathingNames, false)+"将会在你进入其中一个水池的时候伴你左右。");
					}
				}
				List<String> massageNames = new ArrayList<>();
				GameCharacter massageSlave = null;
				for(GameCharacter npc : slavesPresent) {
					if(npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE)) {
						massageNames.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
					}
				}
				if(!massageNames.isEmpty()) {
					sb.append("</p>"
							+ "<p>[style.boldTan(按摩)]<b>:</b>"
							+ "<br/>");
					if(massageNames.size()==1) {
						sb.append(UtilText.parse(massageSlave,
								Util.stringsToStringList(bathingNames, false)+"已经接受了一些如何进行按摩的培训，"
										+ "所以，如果你想让[npc.herHim]给你来一次，你只需要躺在其中一张躺椅上，然后把[npc.herHim]叫过来。"));
					} else {
						sb.append(Util.stringsToStringList(bathingNames, false)+"接受过如何进行按摩的培训，"
										+ "所以，如果你想让这些奴隶给你来一次，你只需要躺在其中一张躺椅上，然后叫他们过来。");
					}
				}
			}
		sb.append("</p>");
		
		return sb.toString();
	}
	
	private static String getBathingSlavesDescription(List<GameCharacter> slavesWashing) {
		if(slavesWashing.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing, "每当你沐浴时，你的奴隶都会奉命与你一同沐浴，"+Util.stringsToStringList(names, false)+"，[npc.steps]走进泳池，沉入你身旁的温水中……"));
			} else {
				sb.append(UtilText.parse(slavesWashing, "每当你沐浴时，你的奴隶们都会奉命加入你的行列，"+Util.stringsToStringList(names, false)+"，[npc.step]进入水池，沉入温暖的水中……"));
			}
		sb.append("</p>");
		
		// Slave greetings:
		Map<SlavePermissionSetting, List<String>> greetings = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> greetingsMute = new HashMap<>();
		Map<SlavePermissionSetting, List<String>> speech = new HashMap<>();
		
		List<GameCharacter> nice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		for(GameCharacter npc : nice) {
			SlavePermissionSetting behaviour = npc.getSlavePermissionSettings().get(SlavePermission.BEHAVIOUR).iterator().next();
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(greetings.get(behaviour)==null || greetings.get(behaviour).isEmpty()) {
				greetings.put(behaviour, new ArrayList<>(bathingGreetings.get(behaviour)));
			}
			if(mute) {
				if(greetingsMute.get(behaviour)==null || greetingsMute.get(behaviour).isEmpty()) {
					greetingsMute.put(behaviour, new ArrayList<>(bathingGreetingsMute.get(behaviour)));
				}
			}
			if(speech.get(behaviour)==null || speech.get(behaviour).isEmpty()) {
				speech.put(behaviour, new ArrayList<>(bathingSpeech.get(behaviour)));
			}
			String greetingText;
			sb.append("<p>");
				// Append random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(greetingsMute.get(behaviour));
					greetingsMute.get(behaviour).remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(greetings.get(behaviour));
					greetings.get(behaviour).remove(greetingText);
					String speechText = Util.randomItemFrom(speech.get(behaviour));
					speech.get(behaviour).remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+"[npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		
		// Slave rude greetings:
		List<String> rudeGreetings = new ArrayList<>();
		List<String> rudeGreetingsMute = new ArrayList<>();
		List<String> rudeSpeech = new ArrayList<>();
		
		List<GameCharacter> rude = new ArrayList<>(slavesWashing);
		rude.removeAll(nice);
		for(GameCharacter npc : rude) {
			boolean mute = npc.isMute() || npc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
			// Populate greeting and speech maps if empty:
			if(rudeGreetings.isEmpty()) {
				rudeGreetings.addAll(bathingRudeGreetings);
			}
			if(mute) {
				if(rudeGreetingsMute.isEmpty()) {
					rudeGreetingsMute.addAll(bathingRudeGreetingsMute);
				}
			}
			if(rudeSpeech.isEmpty()) {
				rudeSpeech.addAll(bathingRudeSpeech);
			}
			
			String greetingText;
			sb.append("<p>");
				// Get random text and remove from maps so that it's not used for the next npcs:
				if(mute) {
					greetingText = Util.randomItemFrom(rudeGreetingsMute);
					rudeGreetingsMute.remove(greetingText);
					sb.append(UtilText.parse(npc, greetingText));
					
				} else {
					greetingText = Util.randomItemFrom(rudeGreetings);
					rudeGreetings.remove(greetingText);
					String speechText = Util.randomItemFrom(rudeSpeech);
					rudeSpeech.remove(speechText);
					sb.append(UtilText.parse(npc, greetingText+"[npc.speech("+speechText+")]"));
				}
			sb.append("</p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode SPA_INSTALLATION = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("承诺", "承诺扩建这个房间，将其改造成私人水疗中心。", SPA_INSTALLATION_COMMIT) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation;
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX(), cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("改变想法", "告诉莉莱雅你改变主意了……", SPA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA.getInstallCost());
						cellInstallation.addPlaceUpgrade(PlaceUpgrade.LILAYA_EMPTY_ROOM);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_INSTALLATION_COMMIT = new DialogueNode("水疗中心", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
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
				return new Response("继续", "跟着莉莱雅走到走廊……", SPA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_INSTALLATION_COMMIT_LEAVE"));
						Main.game.getTextStartStringBuilder().append(PlaceType.LILAYA_HOME_CORRIDOR.getDialogue(false).getContent());
						if(Main.game.getActiveWorld().getWorldType()==WorldType.LILAYAS_HOUSE_GROUND_FLOOR) { // To cover for if the player is upgrading via Office's occupancy ledger
							Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
						}
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_INSTALLATION_END = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SPA_CONSTRUCTION = new DialogueNode("建筑工地", "", false) {
		@Override
		public void applyPreParsingEffects() {
			// This is a backup check to finish construction if somehow the building site has not been converted to the spa:
			if(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID) > 7) {
				LilayaHomeGeneric.dailyUpdate();
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			int daysLeft = 7 - (int)(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID));
			UtilText.addSpecialParsingString(Util.intToString(daysLeft)+""+(daysLeft==1?"天":"天"), true);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CONSTRUCTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SPA_RECEPTION = new DialogueNode("水疗中心", "", false) {
		@Override
		public void applyPreParsingEffects() {
			drinksCharacter = null;
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)==null) {
				int daysLeft = 7 - (int)(Main.game.getDayNumber() - Main.game.getDialogueFlags().getSavedLong(SPA_CONSTRUCTTION_TIMER_ID));
				UtilText.addSpecialParsingString(Util.intToString(daysLeft)+""+(daysLeft==1?"天":"天"), true);
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CONSTRUCTION");
				
			} else {
				StringBuilder sb = new StringBuilder();
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION", getSlaves()));
				sb.append(getReceptionSlavesDescription(getSlaves()));
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_END"));
				sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
				return sb.toString();
			}
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<GameCharacter> slavesAssignedToRoom = getSlaves();
			
			if(index==0) {
				return null;
				
			} else if(index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("管理房间", "进入该房间的管理界面。", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("管理房间", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if(index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("人员管理", "进入奴隶和友人住客的管理界面。", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("管理人员", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if(index==3) {
				if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_SPA)!=null) {
					return new Response("洗澡",
							"在你进入水疗中心之前请用淋浴器快速清洁自己。"
								+ "<br/>[style.italicsGood(从所有腔穴中清理<b>最多"+Units.fluid(500)+"</b>液体。)]"
								+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
							SPA_RECEPTION_SHOWER){
						@Override
						public void effects() {
							slavesWashing = getSlaves().stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA_RECEPTIONIST, SlaveJobSetting.SPA_SHOWERING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, true, null, 240+30);
							}

							Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>你把衣服扔在更衣室，洗澡的时候顺便洗下衣服……</i></p>");
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(false, false, null, 240+30));
						}
					};
				}
				return null;
			}
			//TODO Inviting slaves, occupants, Arthur, Lilaya (who is always accompanied by Rose)
			// Leads into them taking showers and into pools
			//TODO make spa core scene travel disabled
			// If get Lilaya drunk, Rose makes her perform cunnilingus (Rose sits on edge of pool while Lilaya is in it)
				// Rose asks if you'd like to join her bitch
				// Leads into Rose fucking you and Lilaya with strapon
			
			int indexPresentStart = 4;
			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
				NPC character = (NPC) slavesAssignedToRoom.get(index-indexPresentStart);
				return LilayaHomeGeneric.interactWithNPC(character);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SPA_RECEPTION_SHOWER = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_SHOWER", getSlaves()));
			sb.append(getShowerSlavesDescription(slavesWashing));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_SHOWER_END", getSlaves()));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] showerSlots = new SexSlot[] {
					SexSlotStanding.STANDING_SUBMISSIVE,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND,
					SexSlotStanding.STANDING_SUBMISSIVE_TWO,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO};
			
			if(index==1) {
				return new Response("结束", "你洗完澡，回到更衣室。", AFTER_SHOWER_FINISHED);
				
			} else if(index==2) {
				if(slavesWashing.isEmpty()) {
					return new Response("做爱", "你没让奴隶跟到水疗中心里给你洗澡，所以没人跟你做爱……", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size(); i++) {
					slaveSlots.put(slavesWashing.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("做爱",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "和[npc.name]边洗澡边做支配型性爱。")
							:"和你的奴隶在浴室里做爱。",
						true, false,
						new SMShower(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								slaveSlots),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "START_SHOWER_SEX_AS_DOM", slavesWashing)) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
					}
				};
				
			} else if(index==3) {
				if(slavesWashing.isEmpty()) {
					return new Response("服从型性爱", "你没让奴隶跟到水疗中心里给你洗澡，所以没人跟你做服从型性爱……", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("服从型性爱",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "[npc.name]没被你吸引，不想跟你做支配型性爱……")
								:UtilText.parse(slavesWashing, "[npc2.name]没被你吸引，不想跟你做支配型性爱……"),
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), showerSlots[i]);
				}
				return new ResponseSex("服从性爱",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "[npc.name]会边洗澡边强势操你。")
										:"你的奴隶会边洗澡边强势操你。",
						true, true,
						new SMShower(SexPosition.STANDING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT))),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "START_SHOWER_SEX_AS_SUB", attractedSlaves)) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_SHOWER_SEX = new DialogueNode("完成", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getDescription() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			if(sexSlaves.size()==1) {
				return UtilText.parse(sexSlaves, "[npc.Name]玩得很开心，[npc.her]提醒你还有其他事没做完……");
			} else {
				return "你的奴隶都玩尽兴了，提醒你还另有事情要做……";
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			UtilText.addSpecialParsingString(String.valueOf(sexSlaves.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_SHOWER_SEX", sexSlaves));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_RECEPTION.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_RECEPTION.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_SHOWER_FINISHED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_SHOWER_FINISHED", slavesWashing));
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_RECEPTION_CLOTHING_CLEAN"));
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_RECEPTION.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_RECEPTION.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPA_CORE = new DialogueNode("", "", false) {
		@Override
		public boolean isTravelDisabled() {
			return isGuestPresent();
		}
		@Override
		public void applyPreParsingEffects() {
			try {
				Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA).get(0).getInventory().cleanAllClothing(true);
			} catch(Exception ex) {
				System.err.println("Clothes cleaning in SPA_CORE failed!");
			}
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			UtilText.addSpecialParsingString(String.valueOf(getSlaves().isEmpty()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE"));
			
			sb.append(getSpaSlavesDescription(getSlaves()));
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_SPA_BAR).isEmpty()) {
				return null;
			}
			if(index==0) {
				return "水池";
			} else if(index==1) {
				return "酒吧";
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isGuestPresent() && index==0) {
				return new Response("离开",
						UtilText.parse(guest, "告诉[npc.name]你们俩现在必须离开水疗中心。"),
						SPA_GUEST_END);
			}
			
			if(responseTab==1) {
				if(index==0) {
					return null;
				}
				GameCharacter target = 
						drinksCharacter==null || (!getSlaves().contains(drinksCharacter) && !isGuestPresent())
							?Main.game.getPlayer()
							:drinksCharacter;
							
				if(index==11) {
					return new ResponseEffectsOnly(
							UtilText.parse(target, "目标:<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"切换目标角色接受饮料。") {
						@Override
						public void effects() {
							List<GameCharacter> characters = Util.newArrayListOfValues(Main.game.getPlayer());
							if(isGuestPresent()) {
								characters.add(guest);
							} else {
								characters.addAll(getSlaves());
							}
							for(int i=0; i<characters.size();i++) {
								if(characters.get(i).equals(target)) {
									if(i==characters.size()-1) {
										drinksCharacter = characters.get(0);
									} else {
										drinksCharacter = characters.get(i+1);
										break;
									}
								}
							}
							Main.game.updateResponses();
						}
					};
				}
				if(index-1<getDrinks().size()) {
					AbstractItem drink = Main.game.getItemGen().generateItem(getDrinks().get(index-1));
					return new Response(drink.getName(false, false),
							target.isPlayer()
								?"在吧台点一杯"+drink.getName(false, false)+"。"
								:UtilText.parse(target, "在吧台给[npc.name]点一杯"+drink.getName(false, false)+"。"),
							SPA_CORE_BAR_DRINK) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(drink.getName(false, false), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BAR_DRINK", target));
							if(target instanceof Arthur || target instanceof Rose) { // Arthur and Rose do not get drunk (Lilaya does...)
								if(!drink.getItemTags().contains(ItemTag.ALCOHOLIC)) {
									Main.game.getTextStartStringBuilder().append(drink.applyEffect(Main.game.getPlayer(), target));
								}
								
							} else {
								Main.game.getTextStartStringBuilder().append(drink.applyEffect(Main.game.getPlayer(), target));
							}
						}
					};
				}
				
				return null;
			}
			
			if(isGuestPresent()) {
				if(index==1) {
					return new Response("使用水池",
							UtilText.parse(guest, "你与[npc.name]滑进水池放松了一会……")
								+ "<br/>[style.italicsExcellent(这样可以清洗<b>所有</b>从你腔穴流出的液体。)]",
							SPA_GUEST_CORE_BATHING){
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(guest.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
				}
				if(index==2) {
					return new Response("按摩",
							UtilText.parse(guest, "你让[npc.name]躺在躺椅上，给[npc.herHim]按摩。"),
							SPA_GUEST_CORE_MASSAGE_GIVE);
				}
				if(index==3) {
					return new Response("接受按摩",
							UtilText.parse(guest,
									"你躺在躺椅上，让[npc.name]给你按摩。"),
							SPA_GUEST_CORE_MASSAGE_RECEIVE);
				}
				
			} else {
				List<GameCharacter> slaves = new ArrayList<>(getSlaves());
				if(index==1) {
					return new Response("使用水池",
							"滑进水池放松了一会……"
								+ "<br/>[style.italicsExcellent(清理所有腔穴中的<b>全部</b>液体。)]"
								+ (Main.game.getPlayer().hasCompanions()
									?"<br/>[style.italicsMinorGood(<b>会</b>为同伴清理。)]"
									:""),
							SPA_CORE_BATHING){
						@Override
						public void effects() {
							bathingStripped = new ArrayList<>();
							slavesWashing = slaves.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60);
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
					
				} else if(index==2) {
					return new Response("使用水池(脱光)",
							"脱光后滑进水池里放松了一会……"
								+ "<br/>[style.italicsExcellent(清理所有腔穴中的<b>全部</b>液体。)]"
								+ (Main.game.getPlayer().hasCompanions()
									?"<br/>[style.italicsMinorGood(<b>会</b>为同伴清理。)]"
									:""),
							SPA_CORE_BATHING){
						@Override
						public void effects() {
							bathingStripped = new ArrayList<>();
							bathingStripped.add(Main.game.getPlayer());
							Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, false);
							slavesWashing = slaves.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING)).collect(Collectors.toList());
							for(GameCharacter npc : slavesWashing) {
								npc.applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60);
							}
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SPA, 240+60));
						}
					};
					
				} else if(index==3) {
					if(!slaves.stream().anyMatch(npc -> npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE))) {
						return new Response("按摩",
								"没有奴隶能给你按摩！"
										+ "<br/><i>安排奴隶在你的私人水疗中心工作并给他们布置“"+SlaveJobSetting.SPA_MASSAGE.getName()+"”任务。</i>",
								null);
					}
					return new Response("按摩",
							"让奴隶为你按摩。<br/>[style.italics(进入奴隶挑选处挑选给你按摩的奴隶。)]",
							SPA_MASSAGE_SELECTION);
					
				} else if(index==4) {
//					return new Response("Relax", "Lie down on one of the loungers and just relax for a while...", null); //TODO Slave use player
					return null;
					
				} else if(index==5) { //TODO All slimes turn the pool into slime and massage you. Requires unique sex scene with special actions as they fuck you from everywhere at once
//					if(!slaves.stream().anyMatch(npc -> npc.getRace()==Race.SLIME && npc.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING))) {
//						return new Response("Slime soak", "TODO", null);
//					}
//					return new Response("Slime soak", "TODO", null);
					return null;
				}
				int indexPresentStart = 6;
				if(index>0 && index-indexPresentStart<getSlaves().size()) {
					NPC character = (NPC) getSlaves().get(index-indexPresentStart);
					return LilayaHomeGeneric.interactWithNPC(character);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode SPA_CORE_BAR_DRINK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "决定接下来做什么……", SPA_CORE);
			}
			return null;
		}
	};

	public static final DialogueNode SPA_CORE_BATHING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene, and populate slaves for sex:
			slavesSex = new ArrayList<>();
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
				if(slave.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_STRIP_TO_BATHE)) {
					bathingStripped.add(slave);
					slave.unequipAllClothingIntoHoldingInventory(slave, false, false);
				}
				if(slavesSex.size()<3) {
					slavesSex.add(slave);
				}
			}
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(bathingStripped.contains(Main.game.getPlayer())), true);
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING"));
			sb.append(getBathingSlavesDescription(slavesWashing));
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_END", slavesWashing));
			
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] bathingSlots = new SexSlot[] {
					SexSlotSitting.SITTING_IN_LAP,
					SexSlotSitting.SITTING_TWO,
					SexSlotSitting.SITTING_THREE};
			
		    List<GameCharacter> slavesSexNoNulls = new ArrayList<>(slavesSex);
		    slavesSexNoNulls.removeIf(s->s==null);
			
			if(index==1) {
				return new Response("结束", "冼完澡离开水池。", BATHING_FINISHED);
				
			} else if(index==2) {
				if(slavesSex.isEmpty()) {
					return new Response("做爱", "你没让奴隶跟到水疗中心里给你洗澡，所以没人跟你做爱……", null);
				}
				if(slavesSexNoNulls.isEmpty()) {
					return new Response("做爱", "你需要选择一位奴隶与你做爱……", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesSexNoNulls.size() && i<bathingSlots.length; i++) {
					slaveSlots.put(slavesSexNoNulls.get(i), bathingSlots[i]);
				}
//				UtilText.addSpecialParsingString(String.valueOf(slavesSexNoNulls.size()), true);
				List<GameCharacter> notAttractedList = new ArrayList<>();
				List<String> notAttractedNamesList = new ArrayList<>();
				
				for(GameCharacter slaveTarget : slavesSexNoNulls) {
					if(!slaveTarget.isAttractedTo(Main.game.getPlayer())) {
						notAttractedList.add(slaveTarget);
						notAttractedNamesList.add(UtilText.parse(slaveTarget, "[npc.name]"));
					}
				}
				
				if(!Main.game.isNonConEnabled() && !notAttractedList.isEmpty()) {
					return new Response("做爱",
							notAttractedList.size()==1
								?UtilText.parse(notAttractedList.get(0), "[npc.Name]没被你吸引，不想跟你做爱……")
								:Util.stringsToStringList(notAttractedNamesList, true)+" 他们没被你吸引，不想跟你做爱……",
							null);
				}
				return new ResponseSex(slavesSexNoNulls.stream().anyMatch(c->!c.isAttractedTo(Main.game.getPlayer()))?"强奸":"做爱",
						(slavesSexNoNulls.size()==1
							?UtilText.parse(slavesSexNoNulls, "和[npc.name]做支配型性爱。")
							:(slavesSexNoNulls.size()==2
								?UtilText.parse(slavesSexNoNulls, "[npc2.name]坐在你旁边时你正与[npc.name]做支配型性爱。")
								:UtilText.parse(slavesSexNoNulls, "[npc2.name]与[npc3.name]坐在你旁边时你与[npc.name]做支配型性爱。")))
						+(notAttractedList.isEmpty()
							?""
							:(notAttractedList.size()==1
								?UtilText.parse(notAttractedList.get(0), "<br/>[style.italicsBad([npc.Name]没被你吸引，会认为这是强奸……)]")
								:"<br/>[style.italicsBad("+Util.stringsToStringList(notAttractedNamesList, true)+"没被你吸引，会认为这是强奸……)]")),
						true, false,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								slaveSlots) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return bathingStripped.contains(character);
							}
						},
						null,
						null,
						BATHING_AFTER_SEX,
						"") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(slavesSexNoNulls.size()), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_SEX_DOM", slavesSexNoNulls));
					}
				};
				
			} else if(index==3) {
				if(slavesSex.isEmpty()) {
					return new Response("服从型性爱", "你没让奴隶跟到水疗中心里给你洗澡，没人跟你做服从型性爱……", null);
				}
				if(slavesSexNoNulls.isEmpty()) {
					return new Response("服从型性爱", "你需要选一个奴隶来跟你做爱……", null);
				}
				
				if(!slavesSexNoNulls.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("服从型性爱",
							slavesSexNoNulls.size()==1
								?UtilText.parse(slavesSexNoNulls, "由于你对[npc.name]来说没有吸引力，[npc.she]不愿意成为性爱中的支配者……")
								:"因为你的奴隶没有被你吸引，所以他们不愿意成为性爱中的支配者……",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesSexNoNulls.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size() && i<bathingSlots.length; i++) {
					slaveSlots.put(attractedSlaves.get(i), bathingSlots[i]);
				}
				return new ResponseSex("服从性爱",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "[npc.name]会在水池强势地操你。")
										:"让你的奴隶在水池里强势地操你。",
						true, true,
						new SMBath(SexPosition.SITTING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return bathingStripped.contains(character);
							}
						},
						null,
						null,
						BATHING_AFTER_SEX,
						"") {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_CORE_BATHING_SEX_SUB", attractedSlaves));
					}
				};
			}
			if(slavesSex.size()>0) {
				if(index==11) {
					GameCharacter target = slavesSex.get(0);
					return new ResponseEffectsOnly(
							target==null
								?"目标：[style.boldDisabled(无)]"
								:UtilText.parse(target, "目标:<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"循环切换选择性交的主要目标角色。") {
						@Override
						public void effects() {
							setNextSexTarget(0);
							Main.game.updateResponses();
						}
					};
					
				} else if(index==12 && slavesSex.size()>1) {
					GameCharacter target = slavesSex.get(1);
					return new ResponseEffectsOnly(
							target==null
								?"目标：[style.boldDisabled(无)]"
								:UtilText.parse(target, "目标:<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"循环切换选择性交次要目标角色。") {
						@Override
						public void effects() {
							setNextSexTarget(1);
							Main.game.updateResponses();
						}
					};
					
				} else if(index==13 && slavesSex.size()>2) {
					GameCharacter target = slavesSex.get(2);
					return new ResponseEffectsOnly(
							target==null
								?"目标：[style.boldDisabled(无)]"
								:UtilText.parse(target, "目标:<b style='color:"+target.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
							"循环切换选择第三位性交目标角色。") {
						@Override
						public void effects() {
							setNextSexTarget(2);
							Main.game.updateResponses();
						}
					};
				}
			}
			return null;
		}
	};
	
	private static void setNextSexTarget(int index) {
		GameCharacter current = slavesSex.get(index);
		if(slavesSex.get(index)==null) {
			slavesSex.remove(index);
			slavesSex.add(index, slavesWashing.get(0));
		} else {
			for(int i=0; i<slavesWashing.size();i++) {
				if(slavesWashing.get(i).equals(current)) {
					slavesSex.remove(index);
					if(i==slavesWashing.size()-1) {
						slavesSex.add(index, null);
					} else {
						slavesSex.add(index, slavesWashing.get(i+1));
					}
					break;
				}
			}
		}
		
		Set<GameCharacter> excludedCharacters = new HashSet<>();
		GameCharacter newTarget = slavesSex.get(index);
		if(newTarget!=null) {
			excludedCharacters.add(newTarget);
		}
		
		for(int i=0; i<slavesSex.size(); i++) {
			if(i!=index) {
				if(excludedCharacters.contains(slavesSex.get(i))) {
					slavesSex.remove(i);
					slavesSex.add(i, current);
				}
			}
		}
	}

	public static final DialogueNode BATHING_AFTER_SEX = new DialogueNode("完成", "", true) {
		@Override
		public void applyPreParsingEffects() {
//			for(GameCharacter character : bathingStripped) {
//				character.equipAllClothingFromHoldingInventory();
//			}
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getDescription() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			if(sexSlaves.size()==1) {
				return UtilText.parse(sexSlaves, "[npc.Name]玩得很开心，[npc.her]提醒你还有其他事没做完……");
			} else {
				return "你的奴隶都玩尽兴了，提醒你还另有事情要做……";
			}
		}
		@Override
		public String getContent() {
			List<GameCharacter> sexSlaves = new ArrayList<>(Main.sex.getAllParticipants());
			sexSlaves.removeIf(c -> c.isPlayer());
			UtilText.addSpecialParsingString(String.valueOf(sexSlaves.size()), true);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "BATHING_AFTER_SEX", sexSlaves);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE_BATHING.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE_BATHING.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode BATHING_FINISHED = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter character : bathingStripped) {
				character.equipAllClothingFromHoldingInventory();
			}
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
			sb.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "BATHING_FINISHED", slavesWashing));
			sb.append(SPA_CORE.getContent());
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SPA_MASSAGE_SELECTION = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			slavesForMassage = new ArrayList<>(getSlaves());
			slavesForMassage.removeIf(s -> !s.hasSlaveJobSetting(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE));
		}
		@Override
		public String getContent() {
			List<String> names = new ArrayList<>();
			for(GameCharacter npc : slavesForMassage) {
				names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
			}
			
			UtilText.addSpecialParsingString(String.valueOf(slavesForMassage.size()), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SELECTION", slavesForMassage);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "还是决定不按摩了……", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SELECTION_BACK"));
					}
				};
			}
			if(index-1<slavesForMassage.size()) {
				GameCharacter slave = slavesForMassage.get(index-1);
				return new Response(UtilText.parse(slave, "[npc.Name]"),
						UtilText.parse(slave,
								"躺在其中一张躺椅上，让[npc.name]为你按摩。"
								+ (slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())
									?(slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE)
										?"<br/>[style.italicsSex(由于[npc.name]有使用你进行性交的许可，而且[npc.she]目前正压抑着自己的性欲，所以[npc.she]肯定会在按摩过程中开始操你！)]"
										:"<br/>[style.italicsSex(由于[npc.name]允许与你进行性交，[npc.she]可能会在按摩过程中开始操你！)]")
									:"")),
						SPA_MASSAGE) {
					@Override
					public Colour getHighlightColour() {
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())) {
							return PresetColour.GENERIC_SEX;
						}
						return super.getHighlightColour();
					}
					@Override
					public void effects() {
						massageSlave = slave;
						if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER) && slave.isAttractedTo(Main.game.getPlayer())) {
							massageSlaveSex = slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE) || Math.random()<0.5f;
						} else {
							massageSlaveSex = false;
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			if(massageSlaveSex) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX", massageSlave);
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE", massageSlave);
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(massageSlaveSex) {
				if(index==1) {
					return new ResponseSex(
							"被操",
							UtilText.parse(massageSlave, "[npc.Name]把你按在躺椅上，准备开始操你……"),
							true,
							!(massageSlave.isWillingToRape(Main.game.getPlayer()) && massageSlave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)),
							new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(massageSlave, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_FUCKED", massageSlave));	
					
				} else if(index==2) {
					if(massageSlave.isWillingToRape(Main.game.getPlayer()) && massageSlave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)) {
						return new Response("拒绝",
								UtilText.parse(massageSlave, "由于你给了[npc.nameIsFull]强奸的许可，[npc.herHim]不会接受你的反对意见！"),
								null);
						
					} else {
						return new Response("拒绝",
								UtilText.parse(massageSlave, "你现在没心情做，于是坚决地让[npc.name]停下。"),
								REFUSE_SLAVE_SEX);
						
					}
				}
				
			} else {
				if(index==1) {
					return new Response("继续", "现在你已经享受了一次惬意的按摩，你在想接下来该做什么……", SPA_CORE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_FINISHED", massageSlave));
						}
					};
					
				} else if(index==2) {
					if(!Main.game.isNonConEnabled() && !massageSlave.isAttractedTo(Main.game.getPlayer())) {
						return new Response("做爱", UtilText.parse(massageSlave, "你没有对[npc.Name]产生吸引力，所以[npc.she]不愿意与你发生性关系……"), null);
					}
					return new ResponseSex(
							!massageSlave.isAttractedTo(Main.game.getPlayer())
								?"强奸"
								:"做爱",
							!massageSlave.isAttractedTo(Main.game.getPlayer())
								?UtilText.parse(massageSlave, "将[npc.name]推倒在躺椅上，然后强奸[npc.herHim]。")
								:UtilText.parse(massageSlave, "将[npc.name]推倒在躺椅上，与[npc.herHim]进行支配型性爱。"),
							true,
							false,
							new SexManagerDefault(massageSlave.isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), massageSlave.isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(massageSlave, massageSlave.isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX_AS_DOM", massageSlave));
					
				} else if(index==3) {
					if(!massageSlave.isAttractedTo(Main.game.getPlayer())) {
						return new Response("服从型性爱", UtilText.parse(massageSlave, "由于你对[npc.name]来说没有吸引力，所以[npc.she]不愿意在与你发生性关系时扮演主导角色……"), null);
					}
					return new ResponseSex(
							"服从型性爱",
							UtilText.parse(massageSlave, "让[npc.name]在躺椅上操你……"),
							true,
							false,
							new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(new Value<>(massageSlave, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
								},
							null,
							null,
							AFTER_MASSAGE_SEX,
							UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_MASSAGE_SEX_AS_SUB", massageSlave));
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_MASSAGE_SEX = new DialogueNode("完成", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(massageSlave, "在享受了[npc.her]的乐子之后，[npc.name]提醒你还有其他事情需要处理……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_MASSAGE_SEX", massageSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "现在你已经享受了一次意想不到的有趣按摩，你在想接下来该做什么……", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "AFTER_MASSAGE_SEX_FINISHED", massageSlave));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode REFUSE_SLAVE_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "REFUSE_SLAVE_SEX", massageSlave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "现在你已经让奴隶退下，你在想接下来该做什么……", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "REFUSE_SLAVE_SEX_FINISHED", massageSlave));
					}
				};
			}
			return null;
		}
	};
	
	
	// Guest content:
	
	public static final DialogueNode SPA_GUEST_INVITE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_INVITE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("前往水疗中心", UtilText.parse(guest, "陪同[npc.name]去水疗中心。"), SPA_GUEST_ARRIVE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_SPA);
						guest.setLocation(Main.game.getPlayer());
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_ARRIVE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), 5));
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {

			if(guest.isFeminine()) {
				AbstractClothing swimsuit = Main.game.getItemGen().generateClothing("innoxia_chest_swimsuit", PresetColour.CLOTHING_PINK, false);
				AbstractClothing bikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_bikini", PresetColour.CLOTHING_PINK_LIGHT, false);
				AbstractClothing bikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_bikini", PresetColour.CLOTHING_PINK_LIGHT, false);
				AbstractClothing microBikiniTop = Main.game.getItemGen().generateClothing("innoxia_chest_micro_bikini", PresetColour.CLOTHING_PINK_HOT, false);
				AbstractClothing microBikiniBottom = Main.game.getItemGen().generateClothing("innoxia_groin_micro_bikini", PresetColour.CLOTHING_PINK_HOT, false);
				
				if(index==1) {
					if(guest.isAbleToEquip(swimsuit, true, guest)) {
						return new Response("泳装", UtilText.parse(guest, "告诉[npc.name]穿上得体的泳装。"), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(swimsuit, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_SWIMSUIT", guest));
							}
						};
					} else {
						return new Response("泳装", UtilText.parse(guest, "[npc.Name]不能穿泳装，因为[npc.her]的某些封印衣物阻碍了[npc.herHim]穿泳装……"), null);
					}
				}
				if(index==2) {
					if(guest.isAbleToEquip(bikiniTop, true, guest) && guest.isAbleToEquip(bikiniBottom, true, guest)) {
						return new Response("比基尼", UtilText.parse(guest, "让[npc.name]穿上比基尼。"), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(bikiniTop, true, guest);
								guest.equipClothingFromNowhere(bikiniBottom, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_BIKINI", guest));
							}
						};
					} else {
						return new Response("比基尼", UtilText.parse(guest, "[npc.Name]不能穿比基尼，因为[npc.her]的某些封印衣物阻碍了[npc.herHim]穿比基尼……"), null);
					}
				}
				if(index==3) {
					if(guest.isAbleToEquip(microBikiniTop, true, guest) && guest.isAbleToEquip(microBikiniBottom, true, guest)) {
						return new Response(
								guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.colourBad(迷你比基尼)]"
									:"迷你比基尼",
								UtilText.parse(guest, "让[npc.name]穿上高度暴露的迷你比基尼。"
										+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
											?"[style.italicsBad(由于[npc.name]不喜欢“"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"”的性癖，[npc.she]不会喜欢这个！)]"
											:"")),
								SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(microBikiniTop, true, guest);
								guest.equipClothingFromNowhere(microBikiniBottom, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_MICRO_BIKINI", guest));
								if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
									Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
								}
							}
						};
					} else {
						return new Response("迷你比基尼", UtilText.parse(guest, "[npc.Name]不能穿迷你比基尼，因为[npc.her]的某些封印衣物阻碍了[npc.herHim]穿比基尼……"), null);
					}
				}
				if(index==4) {
					return new Response(
							guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
								?"[style.colourBad(裸体的)]"
								:"赤身裸体",
							UtilText.parse(guest, "让[npc.name]脱下[npc.her]的所有衣服，一丝不挂地进入水疗中心。"
								+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.italicsBad(由于[npc.name]不喜欢“"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"”的性癖，[npc.she]不会喜欢这个！)]"
									:"")),
							SPA_GUEST_PLAYER_CLOTHING) {
						@Override
						public void effects() {
							guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_NAKED", guest));
							if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
				}
				
			} else {
				AbstractClothing swimShorts = Main.game.getItemGen().generateClothing("innoxia_groin_swim_shorts", PresetColour.CLOTHING_BLUE, false);
				if(index==1) {
					if(guest.isAbleToEquip(swimShorts, true, guest)) {
						return new Response("游泳短裤", UtilText.parse(guest, "让[npc.name]穿上游泳短裤。"), SPA_GUEST_PLAYER_CLOTHING) {
							@Override
							public void effects() {
								guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
								guest.equipClothingFromNowhere(swimShorts, true, guest);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_SWIM_SHORTS", guest));
							}
						};
					} else {
						return new Response("游泳短裤", UtilText.parse(guest, "[npc.Name]不能穿游泳短裤，因为[npc.her]的某些封印衣物阻碍了[npc.herHim]穿游泳短裤……"), null);
					}
				}
				if(index==2) {
					return new Response(
							guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
								?"[style.colourBad(裸体的)]"
								:"赤身裸体",
							UtilText.parse(guest, "让[npc.name]脱下[npc.her]的所有衣服，一丝不挂地进入水疗中心。"
								+ (guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()
									?"[style.italicsBad(由于[npc.name]不喜欢“"+Fetish.FETISH_EXHIBITIONIST.getName(guest)+"”的性癖，[npc.she]不会喜欢这个！)]"
									:"")),
							SPA_GUEST_PLAYER_CLOTHING) {
						@Override
						public void effects() {
							guest.unequipAllClothingIntoHoldingInventory(guest, false, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_ARRIVE_NAKED", guest));
							if(guest.getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
								Main.game.getTextStartStringBuilder().append(guest.incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_PLAYER_CLOTHING = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("不脱衣服", "决定不脱光衣服，继续原样进入水疗中心。", SPA_GUEST_PLAYER_CLOTHING_REVEAL) {
					@Override
					public void effects() {
						playerStripped = false;
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_CONTINUE", guest));
					}
				};
			}
			if(index==2) {
				return new Response("脱光",
						"脱光衣服，一丝不挂地进入水疗中心。",
						SPA_GUEST_PLAYER_CLOTHING_REVEAL) {
					@Override
					public void effects() {
						playerStripped = true;
						Main.game.getPlayer().unequipAllClothingIntoHoldingInventory(Main.game.getPlayer(), false, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_NAKED", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_PLAYER_CLOTHING_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_REVEAL", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("泳池", UtilText.parse(guest, "引导[npc.name]进入水池。"), SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_PLAYER_CLOTHING_REVEAL_POOLS", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_CORE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE", guest);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return SPA_CORE.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SPA_CORE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SPA_GUEST_CORE_BATHING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("结束", "结束沐浴，走出泳池。", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_END", guest));
					}
				};
				
			} else if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("做爱",
							UtilText.parse(guest, "你对[npc.Name]来说没有吸引力，所以[npc.she]不愿意和你发生性关系……"),
							null);
				}
				return new ResponseSex("做爱",
						UtilText.parse(guest, "和[npc.name]做支配型性爱。"),
						true, false,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(guest, SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						SPA_GUEST_CORE_BATHING_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_SEX_START", guest));
				
			} else if(index==3) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("服从型性爱",
							UtilText.parse(guest, "你对[npc.Name]来说没有吸引力，所以[npc.she]不愿意和你发生性关系……"),
							null);
				}
				return new ResponseSex("服从性爱",
						UtilText.parse(guest, "让[npc.name]在泳池里操你。"),
						true, true,
						new SMBath(SexPosition.SITTING,
								Util.newHashMapOfValues(new Value<>(guest, SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_IN_LAP))) {
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return false;
							}
						},
						null,
						null,
						SPA_GUEST_CORE_BATHING_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_SUBMISSIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_BATHING_AFTER_SEX = new DialogueNode("完成", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SPA, 240+30);
		}
		@Override
		public String getDescription() {
			return UtilText.parse(guest, "[npc.her]玩得很开心，[npc.name]问你是否想做点别的……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_AFTER_SEX", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "结束沐浴，走出泳池。", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_BATHING_AFTER_SEX_END", guest));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_GIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", UtilText.parse(guest, "现在，你已经为[npc.name]做了一次很好的放松按摩，你在想接下来该做什么……"), SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE_END", guest));
					}
				};
				
			} else if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response(UtilText.parse(guest, "操[npc.herHim]"),
							UtilText.parse(guest, "由于你对[npc.name]来说没有吸引力，所以[npc.she]不愿意与你发生性关系……"),
							null);
				}
				return new ResponseSex(
						UtilText.parse(guest, "操[npc.herHim]"),
						UtilText.parse(guest, "把[npc.name]想要的东西给[npc.she]……"),
						true,
						false,
						new SexManagerDefault(guest.isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), guest.isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(guest, guest.isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public String getDeskName() {
								return "按摩台";
							}
						},
						null,
						null,
						SPA_GUEST_CORE_MASSAGE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_GIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_RECEIVE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+30)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "现在你已经享受了一次惬意的按摩，你在想接下来该做什么……", SPA_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE_END", massageSlave));
					}
				};
				
			}
			if(index==2) {
				if(!guest.isAttractedTo(Main.game.getPlayer())) {
					return new Response("被操",
							UtilText.parse(guest, "由于你对[npc.name]来说没有吸引力，所以[npc.she]不愿意与你发生性关系……"),
							null);
				}
				return new ResponseSex(
						"被操",
						UtilText.parse(guest, "告诉[npc.name][npc.she]可以操你……"),
						true,
						false,
						new SexManagerDefault(Main.game.getPlayer().isTaur()?SexPosition.ALL_FOURS:SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(guest, Main.game.getPlayer().isTaur()?SexSlotAllFours.BEHIND:SexSlotLyingDown.MISSIONARY)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotAllFours.ALL_FOURS:SexSlotLyingDown.LYING_DOWN))) {
							@Override
							public String getDeskName() {
								return "按摩台";
							}
						},
						null,
						null,
						SPA_GUEST_CORE_MASSAGE_AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_RECEIVE_SEX_START", guest));
			}
			return null;
		}
	};

	public static final DialogueNode SPA_GUEST_CORE_MASSAGE_AFTER_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(guest, "[npc.her]玩得很开心，[npc.name]问你是否想做点别的……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_AFTER_SEX", guest);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "现在，你已经享受了一次意想不到的有趣按摩，你在想接下来该做什么……", SPA_GUEST_CORE) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_CORE_MASSAGE_AFTER_SEX_FINISHED", guest));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SPA_GUEST_END = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			guest.equipAllClothingFromHoldingInventory();
			guest.returnToHome();
			if(playerStripped) {
				Main.game.getPlayer().equipAllClothingFromHoldingInventory();
			}
			Main.game.getPlayer().setNearestLocation(PlaceType.LILAYA_HOME_CORRIDOR);

			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_GUEST_END", guest));
			
			LilayaSpa.guest = null;
			drinksCharacter = null;
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "现在你已经享受了一次意想不到的有趣按摩，你在想接下来该做什么……", PlaceType.LILAYA_HOME_CORRIDOR.getDialogue(false));
			}
			return null;
		}
	};
	
	
	
	
	//TODO
	// Expansion:
	

	public static final DialogueNode SPA_SAUNA_INSTALLATION = new DialogueNode("", "", true) { //TODO
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("承诺", "承诺扩建水疗中心，并增加桑拿区域。", SPA_SAUNA_INSTALLATION_END) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation; //TODO
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()+1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("改变想法", "告诉莉莱雅你改变主意了……", SPA_SAUNA_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA_SAUNA.getInstallCost());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_SAUNA_INSTALLATION_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
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
			return null;
		}
	};
	
	public static final DialogueNode SPA_SAUNA = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(Lilaya.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getNpc(Rose.class).setLocation(Main.game.getPlayer(), false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_SAUNA", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("桑拿", "在桑拿房里泡一会儿。", null);
			}
			//TODO slave interaction
			
			return null;
		}
	};
	

	public static final DialogueNode SPA_POOL_INSTALLATION = new DialogueNode("", "", true) { //TODO
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("承诺", "承诺扩建水疗中心，并增加桑拿区域。", SPA_POOL_INSTALLATION_END) {
					@Override
					public void effects() {
						int size = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).WORLD_WIDTH;
						Cell cell = cellInstallation; //TODO
						if(cell.getLocation().getY()>=size-2) { // North
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else if(cell.getLocation().getX()>=size-2) { // East
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()+1, cell.getLocation().getY()-1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						} else { // West
							Cell poolCell = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(new Vector2i(cell.getLocation().getX()-1, cell.getLocation().getY()-1));
							poolCell.getPlace().setPlaceType(PlaceType.LILAYA_HOME_UNDER_CONSTRUCTION);
							
						}
						Main.game.getDialogueFlags().setSavedLong(SPA_CONSTRUCTTION_TIMER_ID, Main.game.getDayNumber());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION_COMMIT"));
					}
				};
				
			} else if(index==2) {
				return new Response("改变想法", "告诉莉莱雅你改变想法了……", SPA_POOL_INSTALLATION_END) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(PlaceUpgrade.LILAYA_SPA_POOL.getInstallCost());
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL_INSTALLATION_CHANGE_MIND"));
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode SPA_POOL_INSTALLATION_END = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_CORRIDOR, false);
			Main.game.getNpc(Lilaya.class).returnToHome();
			Main.game.getNpc(Rose.class).returnToHome();
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
			return null;
		}
	};
	
	public static final DialogueNode SPA_POOL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/spa", "SPA_POOL", getSlaves()); //TODO
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("游泳", "去游泳。", null);
			}
			//TODO slave interaction
			
			return null;
		}
	};
	
}
