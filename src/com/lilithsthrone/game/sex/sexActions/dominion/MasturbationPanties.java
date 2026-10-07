package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.OrgasmCumTarget;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.8
 * @version 0.2.8
 * @author Innoxia
 */
public class MasturbationPanties {
	
	public static final SexAction PLAYER_STROKE_VAGINA_PANTIES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES;
		}
		
		@Override
		public String getActionTitle() {
			return "内裤蹭穴";
		}

		@Override
		public String getActionDescription() {
			return "用莉莱雅的内裤磨蹭你的小穴。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你将莉莱雅面料柔软的内裤按在了[pc.legs+]之间，紧贴着[pc.pussy]上下揉动起来，口中呼出一声长叹。",
					"你把莉莱雅的内裤按在自己[pc.pussy+]上，闭上[pc.eyes]，幻想着你的恶魔[lilaya.relation(pc)]，正也用蜜穴隔着柔软的面料跟你摩擦着。",
					"你用莉莱雅的内裤蹭过自己[pc.pussy+]，脑中却浮现出其主人穿着的样子，那个仍在实验室工作的恶魔[lilaya.relation(pc)]，不禁发出一声[pc.a_moan+]。",
					"你饥渴地用莉莱雅的内裤擦过自己的[pc.pussy]，布料和阴唇按压在一起的感觉让你呻吟起来。");
		}
		
		@Override
		public String applyEffectsString() {
			if(!LilayasRoom.lilayasPanties.isDirty() && Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return "<p style='text-align:center'>"
							+ "[style.italicsDirty(被按在你潮湿的小穴上摩擦过后，莉莱雅的内裤很快便被弄脏了。)]"
						+ "</p>";
				
			} else {
				return "";
			}
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_INCEST);
			} else {
				return null;
			}
		}
		
	};
	
	public static final SexAction PLAYER_STROKE_PENIS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES;
		}
		
		@Override
		public String getActionTitle() {
			return "内裤揉棒";
		}

		@Override
		public String getActionDescription() {
			return "用莉莱雅的内裤磨蹭你的[pc.cock]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你用莉莱雅的内裤包住自己[pc.cock+]，上下撸动起来，脑内浮现出你的恶魔[lilaya.relation(pc)]，正用蜜穴隔着柔软的面料摩擦着，忍不住发出了[pc.moaning+]。",
					"你把莉莱雅的内裤盖在了自己[pc.cock+]的[pc.cockHead+]上，接着便撸动起来，口中也冒出了[pc.a_moan+]。",
					"你握住自己[pc.cock+]，用莉莱雅的内裤裹在肉竿上，便闭上[pc.eyes]自慰起来，脑中已是你恶魔[lilaya.relation(pc)]的湿穴。",
					"你用莉莱雅的内裤包着自己[pc.cock+]揉搓着，脑中却浮现出其主人穿着的样子，那个仍在实验室工作的恶魔[lilaya.relation(pc)]，不禁发出一声[pc.a_moan+]。");
		}

		@Override
		public String applyEffectsString() {
			if(!LilayasRoom.lilayasPanties.isDirty() && Main.sex.hasLubricationTypeFromAnyone(Main.game.getPlayer(), SexAreaPenetration.PENIS)) {
				LilayasRoom.lilayasPanties.setDirty(null, true);
				return "<p style='text-align:center'>"
							+ "[style.italicsDirty(被按在你的肉棒上摩擦过后，莉莱雅的内裤很快便被弄脏了。)]"
						+ "</p>";
				
			} else {
				return "";
			}
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_INCEST);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_STROKE_MOUND = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES
					&& !Main.game.getPlayer().hasPenis()
					&& !Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public String getActionTitle() {
			return "内裤蹭丘";
		}

		@Override
		public String getActionDescription() {
			return "用莉莱雅的内裤磨蹭你的无性别下体。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你把莉莱雅的内裤伸到股间，在自己玩偶一样光滑的下体上磨蹭起来，脑内浮现出你的恶魔[lilaya.relation(pc)]，正用蜜穴隔着柔软的面料摩擦着，忍不住发出了[pc.moaning+]。",
					"你用莉莱雅的内裤揉弄着自己股间玩偶般敏感的平丘，脑中却浮现出其主人穿着的样子，那个仍在实验室工作的恶魔[lilaya.relation(pc)]，嘴里呜咽起来。",
					"你把莉莱雅的内裤按在自己敏感的无性下体上，便闭上[pc.eyes]自慰起来，脑中已是你恶魔[lilaya.relation(pc)]的湿穴。",
					"你饥渴地用莉莱雅的内裤擦过自己玩偶般的下体，布料和你敏感的[pc.skin]按压在一起的感觉让你呻吟起来。");
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_SNIFF_PANTIES = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, null)),
			SexParticipantType.SELF) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES;
		}

		@Override
		public String getActionTitle() {
			return "闻闻内裤";
		}

		@Override
		public String getActionDescription() {
			return "闻闻莉莱雅穿过的内裤。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你把莉莱雅的内裤送到[pc.face]旁，用鼻子压在了那柔软的布料上，深吸了一口气，感受着你恶魔[lilaya.relation(pc)]贴身衣物上微弱的雌性气味。",
					"你把莉莱雅的内裤按在脸上，贪婪地呼吸着这被你恶魔[lilaya.relation(pc)]使用过的底裤上，上头的轻微淫味。",
					"你用莉莱雅的内裤按在鼻子上，闭上[pc.eyes]呼吸着其上带有一丝香水味的雌性味道，脑中满是你恶魔[lilaya.relation(pc)]的蜜穴。",
					"你连忙把莉莱雅的内裤按在鼻子上，呼吸着布料上残留着的带有一丝香水味的女性气味，不自觉地喘了起来。");
		}
		
		@Override
		public List<AbstractFetish> getExtraFetishes(GameCharacter characterPerformingAction) {
			if(characterPerformingAction.isPlayer()) {
				return Util.newArrayListOfValues(
						Fetish.FETISH_INCEST,
						Fetish.FETISH_MASTURBATION);
			} else {
				return null;
			}
		}
	};
	
	public static final SexAction PLAYER_MASTURBATION_ORGASM_IN_PANTIES = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				return "射在内裤上";
			}
			return "专注于内裤";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasPenisIgnoreDildo()) {
				return "你已经临近顶峰，没法再抑制住高潮。直接在莉莱雅的内裤上射出来。";
			}
			return "你已经临近顶峰，没法再抑制住高潮。专注在莉莱雅的内裤上，贪婪地嗅着。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotMasturbation.KNEELING_PANTIES
					&& ((Main.game.getPlayer().hasPenisIgnoreDildo()
						&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
						&& SexAreaPenetration.PENIS.isFree(Main.game.getPlayer())
						&& !Main.game.getPlayer().isWearingCondom())
					|| (Main.game.getPlayer().hasVagina()
						&& SexAreaOrifice.VAGINA.isFree(Main.game.getPlayer())
						&& Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.VAGINA)));
		}

		@Override
		public String getDescription() {
			return GenericOrgasms.getGenericOrgasmDescription(this, Main.game.getPlayer(), OrgasmCumTarget.LILAYA_PANTIES);
		}
		
		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE) {
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			}
		}
	};
}
