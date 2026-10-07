package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.4
 * @version 0.4.10.7
 * @author Innoxia
 */
public class SadisticActions {
	
	
	// ASS ACTIONS:
	
	
	/**
	 * A non-sadistic version of 'slap ass'.
	 */
	public static final SexAction SPANK_ASS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return SLAP_ASS.isBaseRequirementsMet()
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST);
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getActionTitle() {
			return "拍[npc2.herHim]的屁股";
		}
		@Override
		public String getActionDescription() {
			return "开玩笑地拍打[npc2.namePos][npc2.ass+]。";
		}
		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.pussy+]中，又对着那[npc2.ass+]饶有兴趣地揉搓起来，"
							+ "接着就在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				} else {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.pussy+]中，又饶有趣味地揪住[npc2.her][npc2.tail+]根，朝上提了起来，"
									+ "让那[npc2.ass+]抬得高高抬起，接着在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.pussy+]里来回抽插，嘴里还不断[npc.moansVerb]着说，要让[npc.sheIs]明白自己的地位，"
							+ "紧接着行动起来，颇有情趣地在[npc2.her]光溜溜的屁股上拍了一下。";
				} else {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.pussy+]里来回抽插，同时一只[npc.hand]抓住了[npc2.namePos][npc2.tail+]根，"
										+ "颇有情趣地把[npc2.her][npc2.ass+]高高抬起，接着就在[npc2.her]光溜溜的屁股蛋上结结实实地拍了一巴掌。";
				}

				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name]将[npc.cock+]深深埋入[npc2.namePos]的[npc2.pussy]中，同时一只[npc.hand]稳稳地把住[npc2.herHim]，"
									+ "另一只则结结实实地拍打在[npc2.her]露出的屁股蛋上。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]一面挺身抽插着[npc2.name][npc2.pussy+]，一面玩笑似的拍打着[npc2.ass+]，惹得[npc2.her]不断地发出[npc2.a_moan+]。",
							isTargetedCharacterInanimate()?null
								:"[npc.name]继续向着[npc2.namePos][npc2.pussy+]内顶撞，同时伸出[npc.hand]来，玩笑似地拍打着[npc2.her][npc2.ass+]，"
									+ "每来一下都会让[npc2.herHim]扭动着身子，细声呻吟一番作为回应。");
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.asshole+]中，又对着那[npc2.ass+]饶有兴趣地揉搓起来，"
							+ "接着就在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				} else {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.asshole+]中，又饶有趣味地揪住[npc2.her][npc2.tail+]根，朝上提了起来，"
									+ "让那[npc2.ass+]抬得高高抬起，接着在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.asshole+]里来回抽插，嘴里还不断[npc.moansVerb]着说，要让[npc.sheIs]明白自己的地位，"
							+ "紧接着行动起来，颇有情趣地在[npc2.her]光溜溜的屁股上拍了一下。";
				} else {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.asshole+]里来回抽插，同时一只[npc.hand]抓住了[npc2.namePos][npc2.tail+]根，"
										+ "颇有情趣地把[npc2.her][npc2.ass+]高高抬起，接着就在[npc2.her]光溜溜的屁股蛋上结结实实地拍了一巴掌。";
				}
				
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name]将[npc.cock+]深深埋入[npc2.namePos]的[npc2.asshole]中，同时一只[npc.hand]稳稳地把住[npc2.herHim]，"
								+ "另一只则结结实实地拍打在[npc2.her]露出的屁股蛋上。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]一面挺身抽插着[npc2.name][npc2.asshole+]，一面玩笑似地拍打着[npc2.ass+]，惹得[npc2.her]不断地发出[npc2.a_moan+]。",
							isTargetedCharacterInanimate()?null
								:"[npc.name]继续向着[npc2.namePos][npc2.asshole+]内顶撞，同时伸出[npc.hand]来，玩笑似地拍打着[npc2.her][npc2.ass+]，"
									+ "每来一下都会让[npc2.herHim]扭动着身子，细声呻吟一番作为回应。");
				
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.Name]发出[npc.a_moan+]，用一只[npc.hand(true)]紧紧抓住[npc2.namePos]的腰肢，"
										+ "另一只则结结实实地拍打在[npc2.her][npc2.ass+]上。";
				} else {
					tailSpecial1 = "[npc.Name]发出[npc.a_moan+]，饶有趣味地揪住[npc2.her][npc2.tail+]根，朝上提了起来，"
								+ "让那[npc2.ass+]抬得高高抬起，接着在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name]一只[npc.hand]用力紧抓住[npc2.namePos]的腰身，"
							+ "，对着[npc2.her]光溜溜的屁股情趣地拍打起来。";
				} else {
					tailSpecial2 = isTargetedCharacterInanimate()?null
									:"[npc.Name]忽然攥住[npc2.namePos][npc2.tail+]的尾巴根，吓得[npc2.herHim]惊叫一声，随后就抓着朝上提了起来，"
											+ "强行让[npc2.herHim]把[npc2.ass+]高高抬起在空中，接着[npc.name]就在[npc2.her]光溜溜的屁股蛋上结结实实地拍了一巴掌。";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name]一只[npc.hand]稳稳地把住[npc2.herHim]，另一只则结结实实地拍打在[npc2.her]露出的屁股蛋上。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]玩笑似的拍打着[npc2.ass+]，惹得[npc2.name]不断地发出[npc2.a_moan+]。",
							isTargetedCharacterInanimate()?null
								:"[npc.name]伸出[npc.hand]来，玩笑似地拍打着[npc2.her][npc2.ass+]，每来一下都会让[npc2.herHim]扭动着身子，细声呻吟一番作为回应。");
			}
		}
	};
	
	public static final SexAction SLAP_ASS = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean assFinger = false;
			boolean assFingerReversed = false;
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.LYING_DOWN)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.BACK_TO_WALL)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.OVER_DESK_BACK)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SITTING)
					|| Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.SITTING_IN_LAP)) {
				return false;
			}
			try {
				assFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(target))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.ASS);
			} catch(Exception ex) {
				// No available finger-ass actions, so can't reach ass
			}
			try {
				assFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(target)).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.ASS).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-ass actions, so can't reach ass
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(target)
					&& (assFinger || assFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		
		@Override
		public String getActionTitle() {
			return "扇屁股";
		}

		@Override
		public String getActionDescription() {
			return "扇打[npc2.namePos][npc2.ass+]。";
		}

		@Override
		public String getDescription() {
			String tailSpecial1 = "", tailSpecial2 = "";
			
			if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.pussy+]中，又对着那[npc2.ass+]狠力揉搓起来，"
							+ "接着就在[npc2.her]光溜溜的屁股蛋痛打了好几巴掌。";
				} else {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.pussy+]中，又用力攥住[npc2.her][npc2.tail+]根，朝上一揪，"
									+ "让[npc2.her][npc2.ass+]不免高高抬起，接着用力在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.pussy+]里来回抽插，嘴里还不断低吼着，要让[npc.sheIs]明白自己的地位，"
							+ "紧接着行动起来，毫不留情地在[npc2.her]光溜溜的屁股上狠拍了几下。";
				} else {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.pussy+]里来回抽插，同时一只[npc.hand]抓住了[npc2.namePos][npc2.tail+]根，"
										+ "粗暴地把[npc2.her][npc2.ass+]高高揪起来，毫不留情地往[npc2.her]光溜溜地屁股上痛打了一巴掌。";
				}
				
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name]将[npc.cock+]深深埋入[npc2.namePos]的[npc2.pussy]中，同时一只[npc.hand]稳稳地把住[npc2.herHim]，"
								+ "另一只则结结实实地痛打在[npc2.her]露出的屁股蛋上。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]一面挺身用力抽插着[npc2.name][npc2.pussy+]，一面玩拍打着[npc2.ass+]，惹得[npc2.her]不断地发出[npc2.a_moan+]。",
							isTargetedCharacterInanimate()?null
								:"[npc.name]继续向着[npc2.namePos][npc2.pussy+]内顶撞，同时伸出[npc.hand]来，狠力拍打着[npc2.her][npc2.ass+]，"
									+ "愉悦的低哼声从口中漏出，连环的击打让[npc2.herHim]扭动着身子，细声呻吟地回应着。");
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.asshole+]中，又对着那[npc2.ass+]狠力揉搓起来，"
							+ "接着就在[npc2.her]光溜溜的屁股蛋痛打了好几巴掌。";
				} else {
					tailSpecial1 = "[npc.Name]将[npc.cock+]深埋入[npc2.namePos][npc2.asshole+]中，又用力攥住[npc2.her][npc2.tail+]根，朝上一揪，"
										+ "让[npc2.her][npc2.ass+]不免高高抬起，接着用力在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.asshole+]里来回抽插，嘴里还不断低吼着，要让[npc.sheIs]明白自己的地位，"
							+ "紧接着行动起来，毫不留情地在[npc2.her]光溜溜的屁股上狠拍了几下。";
				} else {
					tailSpecial2 = "[npc.Name]继续在[npc2.her][npc2.asshole+]里来回抽插，同时一只[npc.hand]抓住了[npc2.namePos][npc2.tail+]根，"
										+ "粗暴地把[npc2.her][npc2.ass+]高高揪起来，毫不留情地往[npc2.her]光溜溜地屁股上痛打了一巴掌。";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							"[npc.Name]将[npc.cock+]深深埋入[npc2.namePos]的[npc2.asshole]中，同时一只[npc.hand]稳稳地把住[npc2.herHim]，"
								+ "另一只则结结实实地痛打在[npc2.her]露出的屁股蛋上。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]一面挺身用力抽插着[npc2.name][npc2.asshole+]，一面玩拍打着[npc2.ass+]，惹得[npc2.her]不断地发出[npc2.a_moan+]。",
							isTargetedCharacterInanimate()?null
								:"[npc.name]继续向着[npc2.namePos][npc2.asshole+]内顶撞，同时伸出[npc.hand]来，狠力拍打着[npc2.her][npc2.ass+]，"
									+ "愉悦的低哼声从口中漏出，连环的击打让[npc2.herHim]扭动着身子，细声呻吟地回应着。");
			
			} else {
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial1 = "[npc.Name]在[npc2.namePos][npc2.ear+]边低吼着，一只[npc.hand(true)]紧紧抓住[npc2.namePos]的腰肢，"
										+ "另一只则结结实实地痛打在[npc2.her]露出的屁股蛋上。";
				} else {
					tailSpecial1 = "[npc.Name]在[npc2.namePos][npc2.ear+]边低吼着，又用力攥住[npc2.her][npc2.tail+]根，朝上一揪，"
								+ "让[npc2.her][npc2.ass+]不免高高抬起，接着用力在[npc2.her]光溜溜的屁股蛋上连连拍了几个巴掌。";
				}
				if(!Main.sex.getCharacterTargetedForSexAction(this).hasTail()) {
					tailSpecial2 = "[npc.Name]一只[npc.hand]用力紧抓住[npc2.namePos]的腰身，"
							+ "，对着[npc2.her]光溜溜的屁股毫不留情的痛打起来。";
				} else {
					tailSpecial2 = isTargetedCharacterInanimate()?null
							:"[npc.Name]忽然攥住[npc2.namePos][npc2.tail+]的尾巴根，吓得[npc2.herHim]惊叫一声，随后就揪着一把提了起来，"
										+ "强行让[npc2.herHim]把[npc2.ass+]高高抬起在空中，接着[npc.name]便在[npc2.her]光溜溜的屁股蛋上狠狠地打了几巴掌。";
				}
			
				return UtilText.returnStringAtRandom(
							tailSpecial1,
							tailSpecial2,
							isTargetedCharacterInanimate()?null
								:"[npc.Name]用力拍打着[npc2.ass+]，惹得[npc2.name]不断地发出[npc2.a_moan+]，"
									+ "[npc.Name]注意到自己目睹着[npc2.herHim]在痛打下扭动身躯，不断哀嚎的样子时，不禁露出了喜悦的笑容。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]开始狠力拍击着[npc2.Name][npc2.ass+]，让[npc2.her]忍不住哀嚎出来，"
									+ "[npc.Name]立刻注意到自己目睹着[npc2.herHim]在狠击下扭动身躯，不断痛哭的样子时，不禁露出了喜悦的笑容。",
							isTargetedCharacterInanimate()?null
								:"[npc.Name]从嗓子里发出深沉的吼声，迫不及待地想要将[npc2.name]羞辱一番，于是凶狠地拍打起[npc2.her][npc2.ass+]，"
									+ "当看到[npc2.she]在狠击下扭动身躯，不断哀嚎，露出顺从的样子，[npc.name]不禁露出一抹窃笑。");
			}
		}
	};
	
	
	// FACE ACTIONS:
	
	
	public static final SexAction SLAP_FACE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "扇耳光";
		}

		@Override
		public String getActionDescription() {
			return "扇[npc2.namePos]的耳光，令其屈服。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"为了让[npc2.name]摆正位置，[npc.name]举起[npc.hand]便横扫过去，在[npc2.her]的脸上狠狠地打了一巴掌。",
					"[npc.Name]抬起[npc.hand]便挥扫过去，向着[npc2.namePos]的脸送上了一击响亮的耳光，来让[npc2.her]摆正位置。",
					"[npc.Name]为了提醒[npc2.name]谁才是主导者，抬起[npc.hand]便挥舞了过去，在[npc2.her]的脸上狠狠地打了一巴掌。"));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"肉体相接之际，[npc2.name]顿时发出一声情欲的高喊，也让[npc.name]明白了[npc2.sheIs]受到此等虐待，反而在受虐中感受到了快感。",
							"一阵浪叫脱口而出，任谁也能明白[npc2.name]受到如此有辱人格的行为反而会格外兴奋。",
							"[npc2.name]口中冒出的不是痛叫，反而是饥渴的呻吟，也让[npc.name]明白[npc2.sheIs]是个受虐狂，受到如此虐待反而觉得兴奋。"));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								"肉体相接后，[npc2.namePos]口中顿时冒出一声抗拒的尖叫，"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?"正中了你的下怀。"
												:"[npc.namePos]嘴角微扬，表示这正是[npc.she]期待的行为。"),
								"痛苦的尖叫脱口而出，这正中了[npc.name]的下怀，带着阴险的笑容，注视着"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?"你[npc2.eyes+]中溢出泪水。"
												:"[npc.her]那顺服婊子[npc2.eyes+]中溢出泪水。"),
								"[npc2.namePos]惊呼一声，泪水一下子溢了出来，[npc.name]也得知此番虐待的效果正中了[npc.her]的下怀。"));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								"肉体相接后，[npc2.namePos]口中顿时冒出一声抗拒的喊叫，"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?"正中了你的下怀。"
												:"[npc.namePos]嘴角微扬，表示这正是[npc.she]期待的行为。"),
								"痛苦的喊叫脱口而出，这正中了[npc.name]的下怀，[npc.she]带着阴险的笑容，注视着"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?"你[npc2.eyes+]中震惊的神情。"
												:"[npc.her]那顺服婊子[npc2.eyes+]中震惊的神情。"),
								"[npc2.Name]立刻就发出了一声尖叫，[npc.name]于是得知此番虐待的效果正中了[npc.her]的下怀"));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SPIT_FACE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			null,
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			Map<InventorySlot, List<AbstractClothing>> concealedMap = Main.sex.getCharacterTargetedForSexAction(this).getInventorySlotsConcealed(Main.sex.getCharacterPerformingAction());
			if(concealedMap.containsKey(InventorySlot.MOUTH) && concealedMap.containsKey(InventorySlot.EYES)) {
				return false; // If mouth and eyes are concealed, treat face as being concealed and so unavailable
			}
			
			boolean mouthTongue = false;
			boolean mouthTongueReversed = false;
			try {
				mouthTongue = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.TONGUE).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthTongueReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.TONGUE);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			return SexAreaOrifice.MOUTH.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthTongue || mouthTongueReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		
		@Override
		public String getActionTitle() {
			return "在脸上吐口水";
		}

		@Override
		public String getActionDescription() {
			return "在[npc2.namePos]的脸上吐口水，将[npc2.herHim]贬低得一文不值。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]打算让[npc2.name]知道自己不过是个一文不值的婊子，便稍稍伏下身子，在[npc2.her]的脸颊上吐了一口唾沫。",
					"[npc.Name]倾身向着[npc2.name]，撅起[npc.lips+]，一口唾沫直接吐到了[npc2.her]脸上。",
					"[npc.Name]为了让[npc2.name]记起自己有多么低贱，身体微微前倾，撅起[npc.lips+]朝着[npc2.her]脸上吐了一口口水。"));

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"那滩唾沫溅到脸颊上时，[npc2.namePos]却发出一声响亮的[npc2.moan]，让[npc.name]明白[npc2.sheIs]受到此等侮辱，反而感受到了快感。",
							"一阵浪叫脱口而出，任谁也能明白[npc2.name]受到如此有辱人格的行为反而会格外兴奋。",
							"[npc2.name]并没有恶心得叫出来，反而饥渴地[npc2.moan]着，也让[npc.name]明白[npc2.sheIs]是个受虐狂，受到如此侮辱反而觉得兴奋。"));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								"那滩唾沫砸在脸颊上时，[npc2.name]一下子恶心得叫了出来，"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?"正中了你的下怀。"
												:"[npc.namePos]嘴角微扬，表示这正是[npc.she]期待的行为。"),
								"[npc2.name]恐惧地拒绝起来，而这却正是[npc.name]期待的结果，[npc.she]不禁露出一抹笑意，注视着"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?"你[npc2.eyes+]中溢出泪水。"
												:"[npc.her]那顺服婊子[npc2.eyes+]中溢出泪水。"),
								"[npc2.namePos]惊呼一声，泪水一下子溢了出来，[npc.name]也得知此番虐待的效果正中了[npc.her]的下怀。"));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								"那滩唾沫砸在脸颊上时，[npc2.name]一下子恶心得叫了出来，"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?"正中了你的下怀。"
												:"[npc.namePos]嘴角微扬，表示这正是[npc.she]期待的行为。"),
								"[npc2.name]恐惧地连连拒绝，而这却正是[npc.name]期待的结果，[npc.she]不禁露出一抹笑意，注视着"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?"你[npc2.eyes+]中震惊的神情。"
												:"[npc.her]那顺服婊子[npc2.eyes+]中震惊的神情。"),
								"[npc2.Name]立刻就发出了一声尖叫，[npc.name]于是得知此番虐待的效果正中了[npc.her]的下怀"));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction CHOKE = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			boolean mouthFinger = false;
			boolean mouthFingerReversed = false;
			try {
				mouthFinger = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.MOUTH);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}
			try {
				mouthFingerReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.MOUTH).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available finger-mouth actions, so can't reach face
			}

			return SexAreaPenetration.FINGER.isFree(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (mouthFinger || mouthFingerReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
		}
		
		@Override
		public String getActionTitle() {
			return "窒息";
		}

		@Override
		public String getActionDescription() {
			return "掐住[npc2.namePos]的脖子，让[npc2.herHim]窒息。";
		}
		
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterPerformingAction())) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name]依旧不停的将[npc.cock+]轰入[npc2.namePos][npc2.pussy+]，同时又掐住了[npc2.her]的脖子，用力让[npc2.herHim]窒息起来。",
						"[npc.Name]一把攥住了[npc2.namePos]的脖子，同时依旧暴力地向着[npc2.her][npc2.pussy+]内顶撞，再用力狠狠地掐起来，让[npc2.herHim]逐渐窒息。",
						"[npc.Name]先是将[npc.cock+]深深顶进[npc2.namePos][npc2.pussy+]中，又掐住了[npc2.her]的脖子，"
								+ "逐渐用力让[npc2.herHim]窒息，最后又继续粗暴地抽插起来。"));
				
			} else if(Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterPerformingAction())) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name]仍不断将[npc.cock+]在[npc2.namePos][npc2.asshole+]里猛烈抽送着，又伸出[npc.hand]掐住了[npc2.her]的脖子，逐渐让[npc2.herHim]窒息。",
						"[npc.Name]一把攥住了[npc2.namePos]的脖子，同时依旧暴力地向着[npc2.her][npc2.asshole+]内顶撞，再用力狠狠地掐起来，让[npc2.herHim]逐渐窒息。",
						"[npc.Name]先是将[npc.cock+]深深顶进[npc2.namePos][npc2.asshole+]中，又掐住了[npc2.her]的脖子，"
								+ "逐渐用力让[npc2.herHim]窒息，最后又继续粗暴地抽插起来。"));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name]的脸上不禁浮现出一抹施虐的狠笑，伸出[npc.hand]掐住了[npc2.name]的脖子，接着逐渐用力，让[npc2.herHim]窒息起来。",
						"[npc.Name]攥住了[npc2.namePos]的脖子，一阵凶狠的笑声从口中传出，[npc.she]随后狠狠地掐了起来，让[npc2.herHim]逐渐窒息。",
						"伴随着一声残忍的笑声，[npc.name]掐住了[npc2.name]的脖子，越掐越兴奋，让[npc2.herHim]窒息了。"));
			}

			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.name]发出“咕噜咕噜”声，明显淫靡地回应着动作，[npc.Name]明白[npc2.name]很享受被这般虐待。",
							"[npc2.name]发出的、下流且咕噜噜的[npc2.moans]足够让人意识到[npc2.sheIs]是个受虐狂，正因遭受差劲的对待而性起。",
							"[npc2.name]发出的急促喘息声与几声淫靡的[npc2.moans]混合在一起，[npc.Name]知道[npc2.sheIs]能以这种方式从受虐中获得快感。"));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]口中传出一阵“咕噜咕噜”的水声，"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?"正中了你的下怀。"
												:"[npc.namePos]嘴角微扬，表示这正是[npc.she]期待的行为。"),
								"[npc2.name]哽咽的哭号正是[npc.Name]想要的，[npc.she]看到"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?"泪水从你[npc2.eyes+]中溢出，便情不自禁地笑起来。"
												:"泪水[npc.her]那顺服婊子[npc2.eyes+]中溢出，便情不自禁地笑起来。"),
								"[npc2.namePos]大口大口地喘着气，泪水一下子溢了出来，[npc.name]也得知此番虐待的效果正中了[npc.her]的下怀。"));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]口中传出一阵“咕噜咕噜”的水声，"
										+ (Main.sex.getCharacterPerformingAction().isPlayer()
												?"正中了你的下怀。"
												:"[npc.namePos]嘴角微扬，表示这正是[npc.she]期待的行为。"),
								"[npc2.name]哽咽的哭号正是[npc.Name]想要的，[npc.she]看到"
										+ (Main.sex.getCharacterTargetedForSexAction(this).isPlayer()
												?"你[npc2.eyes+]中震惊的神情，不禁笑起来。"
												:"顺服婊子[npc2.eyes+]中震惊的神情，不禁笑起来。"),
								"[npc2.Name]立刻嘶哑地喘息起来，流露出[npc.Name]的辱骂达到了[npc.she]想要的效果。"));	
					}
				}
			}
			
			return sb.toString();
		}
	};
	
	
	// PUSSY ACTIONS:
	
	
	public static final SexAction SLAP_PUSSY = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean fingerPussy = false;
			boolean fingerPussyReversed = false;
			try {
				fingerPussy = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaOrifice.VAGINA);
			} catch(Exception ex) {
				// No available finger-pussy actions, so unavailable
			}
			try {
				fingerPussyReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaOrifice.VAGINA).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available pussy-finger actions, so unavailable
			}
			return SexAreaOrifice.VAGINA.isFree(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (fingerPussy || fingerPussyReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		@Override
		public String getActionTitle() {
			return "扇小穴";
		}
		@Override
		public String getActionDescription() {
			return "对着[npc2.namePos]的小穴一阵扇打。";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.Name]用[npc.fingers+]挑逗着[npc2.namePos][npc2.pussy+]，然后突然对着[npc2.her]的女性器来了一连串猛烈而痛苦的扇打。",
					"[npc.Name]用[npc.hand]探向[npc2.namePos]的下体，开始粗暴地扇打[npc2.her][npc2.pussy+]。",
					"[npc.Name]探向[npc2.namePos]的下体，激烈地揉弄了[npc2.herHim]一段时间，然后突然对着[npc2.her][npc2.pussy+]来了一连串猛烈的扇打。"));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"肉体相接之际，[npc2.name]顿时发出一声情欲的高喊，也让[npc.name]明白了[npc2.her]的[npc2.pussy]受到此等虐待，反而在扇打中感受到了快感。",
							"一阵浪叫脱口而出，任谁也能明白[npc2.namePos]的[npc2.pussy]受到如此虐待反而会让[npc2.sheIs]格外兴奋。",
							"[npc2.name]口中冒出的不是痛叫，反而是饥渴的呻吟，也让[npc.name]明白[npc2.sheIs]是个受虐狂，[npc2.pussy]受到扇打反而觉得兴奋。"));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]的口中发出一声抗拒的尖叫，[npc2.she]不适地扭动呼喊着，但[npc2.her]的[npc2.pussy]依然被继续扇打。",
								"[npc2.Name]在令人疼痛的打击下来回扭动着，发出一阵痛苦的尖叫，[npc2.her]的[npc2.pussy]正遭受着[npc.namePos]的虐待。",
								"[npc2.name]惊呼一声，泪水顿时从[npc2.eyes]中溢出，[npc2.she]挣扎着乞求[npc.name]停止扇打[npc2.her]的[npc2.pussy]。"));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]的口中发出一声抗拒的低吼，[npc2.she]不适地扭动呜咽着，但[npc2.her]的[npc2.pussy]依然被继续扇打。",
								"[npc2.Name]在令人疼痛的打击下来回扭动着，发出一阵痛苦的呜咽，[npc2.her]的[npc2.pussy]正遭受着[npc.namePos]的虐待。",
								"[npc2.name]惊呼一声，[npc2.she]挣扎喊叫着，要求[npc.name]放过自己的[npc2.pussy]。"));
					}
				}
			}
			
			return sb.toString();
		}
	};

	public static final SexAction PINCH_CLIT = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.CLIT)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean fingerPussy = false;
			boolean fingerPussyReversed = false;
			try {
				fingerPussy = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.CLIT);
			} catch(Exception ex) {
				// No available finger-pussy actions, so unavailable
			}
			try {
				fingerPussyReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.CLIT).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available pussy-finger actions, so unavailable
			}
			return SexAreaPenetration.CLIT.isFree(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (fingerPussy || fingerPussyReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		@Override
		public String getActionTitle() {
			return "捏阴蒂";
		}
		@Override
		public String getActionDescription() {
			return "残暴地拧捏[npc2.namePos][npc.clit+]。";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"[npc.NamePos][npc.fingers+]对准了[npc2.namePos][npc2.pussy+]，然后毫无预兆地开始残忍地拧捏弹弄[npc2.her][npc2.clit+]。",
					"[npc.Name]支配地将[npc.hand]按向[npc2.namePos]的下体，突然开始使劲拧捏[npc2.her][npc2.clit+]，然后快速地连续用力弹了几下。",
					"[npc.Name]探向[npc2.namePos]的下体，对着[npc2.her][npc2.clit+]粗暴地弹了几下，然后残忍地拧捏拉扯着它。"));
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"[npc2.namePos]口中爆发出一声淫荡的[npc2.moan]，也让[npc.name]明白了[npc2.her]的[npc2.clit]受到此等虐待，反而在拧捏中感受到了快感。",
							"一阵[npc2.moan]脱口而出，任谁也能明白[npc2.namePos]的[npc2.clit]受到如此虐待反而会让[npc2.sheIs]格外兴奋。",
							"[npc2.name]口中冒出的不是痛叫，反而是饥渴的呻吟，很明显[npc2.sheIs]是个受虐狂，[npc2.clit]受到虐待反而觉得兴奋。"));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]的口中爆发出抗拒的哭号，[npc2.she]痛苦地扭动呼喊着，但[npc2.her]的[[npc2.clit]依然被粗暴虐待。",
								"[npc2.Name]在令人疼痛的虐待下来回扭动着，发出一阵抗拒的尖叫，[npc2.her]的[npc2.clit]正遭受着[npc.namePos]的虐待。",
								"[npc2.name]惊呼一声，泪水顿时从[npc2.eyes]中溢出，[npc2.she]挣扎着乞求[npc.name]停止惩罚[npc2.her]的[npc2.clit]。"));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]的口中发出一声抗拒的低吼，[npc2.she]不适地扭动呜咽着，但[npc2.her]的[npc2.clit]依然被粗暴虐待。",
								"[npc2.Name]在令人疼痛的虐待下来回扭动着，发出一阵抗拒的呜咽，[npc2.her]的[npc2.clit]正遭受着[npc.namePos]的虐待。",
								"[npc2.name]惊呼一声，[npc2.she]挣扎喊叫着，要求[npc.name]放过自己的[npc2.clit]。"));
					}
				}
			}
			
			return sb.toString();
		}
	};

	
	// COCK ACTIONS:
	
	
	public static final SexAction SLAP_COCK = new SexAction(
			SexActionType.REQUIRES_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public boolean isSadisticAction() {
			return true;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			boolean fingerPenis = false;
			boolean fingerPenisReversed = false;
			try {
				fingerPenis = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)))
						.getInteractions().get(SexAreaPenetration.FINGER).contains(SexAreaPenetration.PENIS);
			} catch(Exception ex) {
				// No available finger-penis actions, so unavailable
			}
			try {
				fingerPenisReversed = Main.sex.getPosition().getSlotTargets().get(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))).get(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()))
						.getInteractions().get(SexAreaPenetration.PENIS).contains(SexAreaPenetration.FINGER);
			} catch(Exception ex) {
				// No available penis-finger actions, so unavailable
			}
			return SexAreaPenetration.PENIS.isFree(Main.sex.getCharacterTargetedForSexAction(this))
					&& !Main.sex.isDom(Main.sex.getCharacterTargetedForSexAction(this))
					&& (fingerPenis || fingerPenisReversed)
					&& (Main.sex.getCharacterPerformingAction().isPlayer() || Main.sex.getCharacterPerformingAction().hasFetish(Fetish.FETISH_SADIST));
			
		}
		@Override
		public String getActionTitle() {
			return "扇鸡巴";
		}
		@Override
		public String getActionDescription() {
			return "惩罚[npc2.namePos]的鸡巴，对它一阵扇打。";
		}
		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.SEX;
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			if(Main.sex.getCharacterTargetedForSexAction(this).isInternalTesticles()) {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name]用[npc.fingers+]挑逗着[npc2.namePos][npc2.cock+]，然后突然对着它来了一连串猛烈而痛苦的扇打。",
						"[npc.Name]用[npc.hand]探向[npc2.namePos]的下体，开始粗暴地扇打[npc2.her][npc2.cock+]。",
						"[npc.Name]探向[npc2.namePos]的下体，激烈地揉弄了[npc2.her][npc2.cock+]一段时间，然后突然对着它来了一连串猛烈的扇打。"));
				
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"[npc.Name]用[npc.fingers+]挑逗着[npc2.namePos][npc2.balls+]，然后突然对着[npc2.her][npc2.cock+]来了一连串猛烈而痛苦的扇打。",
						"[npc.Name]用[npc.hand]探向[npc2.namePos]的下体，开始粗暴地扇打[npc2.her][npc2.cock+]。",
						"[npc.Name]探向[npc2.namePos]的下体，激烈地揉弄了[npc2.her][npc2.balls+]一段时间，然后突然对着[npc2.her][npc2.cock+]来了一连串猛烈的扇打。"));
			}
			
			if(!isTargetedCharacterInanimate()) {
				if(Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_MASOCHIST).isPositive() && Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING) {
					sb.append(UtilText.returnStringAtRandom(
							"肉体相接之际，[npc2.name]顿时发出一声情欲的高喊，也让[npc.name]明白了[npc2.her]的[npc2.cock]受到此等虐待，反而在扇打中感受到了快感。",
							"一阵浪叫脱口而出，任谁也能明白[npc2.namePos]的[npc2.cock]受到如此虐待反而会让[npc2.sheIs]格外兴奋。",
							"[npc2.name]口中冒出的不是痛叫，反而是饥渴的呻吟，也让[npc.name]明白[npc2.sheIs]是个受虐狂，[npc2.cock]受到扇打反而觉得兴奋。"));
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isFeminine()) {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]的口中发出一声抗拒的尖叫，[npc2.she]不适地扭动呼喊着，但[npc2.her]的[npc2.cock]依然被继续扇打。",
								"[npc2.Name]在令人疼痛的打击下来回扭动着，发出一阵痛苦的尖叫，[npc2.her]的[npc2.cock]正遭受着[npc.namePos]的虐待。",
								"[npc2.name]惊呼一声，泪水顿时从[npc2.eyes]中溢出，[npc2.she]挣扎着乞求[npc.name]停止扇打[npc2.her]的[npc2.cock]。"));
						
					} else {
						sb.append(UtilText.returnStringAtRandom(
								"[npc2.namePos]的口中发出一声抗拒的低吼，[npc2.she]不适地扭动呜咽着，但[npc2.her]的[npc2.cock]依然被继续扇打。",
								"[npc2.Name]在令人疼痛的打击下来回扭动着，发出一阵痛苦的呜咽，[npc2.her]的[npc2.cock]正遭受着[npc.namePos]的虐待。",
								"[npc2.name]惊呼一声，[npc2.she]挣扎喊叫着，要求[npc.name]放过自己的[npc2.cock]。"));
					}
				}
			}
			
			return sb.toString();
		}
	};
}
