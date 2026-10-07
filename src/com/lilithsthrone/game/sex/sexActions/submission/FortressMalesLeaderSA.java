package com.lilithsthrone.game.sex.sexActions.submission;

import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.submission.FortressMalesLeader;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.11
 * @version 0.3.4
 * @author Innoxia
 */
public class FortressMalesLeaderSA {
	
	public static boolean isBothTargetsUsed() {
		try {
			return Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0
					&& Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		} catch(Exception ex) {
			return true;
		}
	}
	
	public static GameCharacter getBreedingTarget() {
		return Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).isEmpty()
				?null
				:Main.sex.getCharactersHavingOngoingActionWith(Main.game.getNpc(FortressMalesLeader.class), SexAreaPenetration.PENIS).get(0);
	}
	
	private static GameCharacter getOtherTarget() {
		try {
			GameCharacter otherTarget = null;
			if(getBreedingTarget()==null) {
				if(Main.sex.getSexTypeCount(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0) {
					otherTarget = Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO);
				} else {
					otherTarget = Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN);
				}
				
			} else {
				otherTarget = Main.sex.getSexPositionSlot(getBreedingTarget())==SexSlotLyingDown.LYING_DOWN
						?Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN_TWO)
						:Main.sex.getCharacterInPosition(SexSlotLyingDown.LYING_DOWN);
			}
			
			if(!otherTarget.hasVagina() || !otherTarget.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				return null;
			}
			return otherTarget;
			
		} catch(Exception ex) {
			return null;
		}
	}
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮";
		}

		@Override
		public String getActionDescription() {
			return "跟[npc2.name]说你还没爽够呢！";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.Name]抓着[npc2.namePos][npc2.hips+]向前挺身，将[npc.her][npc.cock+]深深插入[npc2.her]充满精液的阴道。"
					+ "[npc.she]感觉到[npc2.namePos][npc2.labia+]撞击[npc.her]鸡巴根部，[npc.she]发出深深的呻吟，低吼着，"
					+ "[npc.speechNoEffects(让我再给你灌一次！必须搞大你的肚子！)]</br>"
					+ "[npc.Name]将[npc.cock+]插入[npc2.namePos]的[npc2.pussy]中，拿出一个装满淡蓝色液体的小瓶子。"
					+ "[npc.she]用牙齿拔出瓶塞，迅速喝下药水，然后把空瓶扔到一边。</br>"
					+ "[npc.Name]将[npc.hips]后撤，再次有节奏地操起[npc2.name]，"
						+ "[npc.her]每一次插入都伴随着淫荡的挤压声，充满[npc2.namePos]小穴的精液在[npc.her]粗大的阴茎周围流出。</br>"
					+ "[npc2.name]很快就意识到刚才[npc.Name]喝下的药水是做什么用的了，因为每次[npc.race]的蛋蛋拍打[npc2.her][npc2.assSkin]时，都会明显感觉越来越重。"
					+ "为了证实[npc2.her]猜测，[npc.name]咕哝了一声，[npc.speech(操，我的蛋蛋又涨满了！你的子宫会被我的种子填满的！)]";
		}

		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_ONGOING_SWITCH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮(换位)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "选择操[npc.name]。");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					"[npc.Name]抓住[npc2.namePos][npc2.hips+]，向后一拉，把[npc.cock+]从[npc2.her]塞满精液的小穴里滑了出来。"
					+(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?"然后，[npc.she]迅速脱掉挡住[npc3.namePos][npc3.pussy]的衣服，开始在[npc3.her][npc3.labia+]之间上下摩擦她的龟头。"
							:"然后，[npc.she]迅速将[npc.cock+]对准[npc3.namePos]的[npc3.pussy]，将龟头在[npc3.her][npc3.labia+]之间上下摩擦。")
					+"[npc.she]突然向前挺动[npc.her][npc.hips]，将阴茎深深插入[npc3.namePos][npc2.pussy+]中，在插入[npc3.herHim]时发出低沉的咆哮声，"
					+ "[npc.speechNoEffects(别以为我原谅你了！)]</br></br>"
					+ "[npc.Name]将阴茎插入[npc3.namePos][npc3.pussy]，然后拿出一个装满淡蓝色液体的小瓶子。"
					+ "[npc.she]用[npc.her]牙齿拔出瓶塞，迅速喝下药水，然后把空瓶扔到一边。</br></br>"
					+ "[npc.Name]将[npc.hips]后撤，再次有节奏地操起[npc3.name]，"
						+ "由于[npc2.namePos]小穴里的精液和汁液润滑了[npc.her]粗大的阴茎，[npc.her]每次都能轻松地顶进抽出。</br>"
					+ "[npc3.name]很快就意识到刚才[npc.Name]喝下的药水是做什么用的了，因为每次[npc.race]的蛋蛋拍打[npc3.her][npc3.assSkin]时，都会明显感觉越来越重。"
					+ "为了证实她的猜测，[npc.name]咕哝了一声[npc.speech(操，我的蛋蛋又涨满了！你的子宫会被我的种子填满的！)]");
		}
		
		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
			GameCharacter otherTarget = getOtherTarget();
			
			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮";
		}

		@Override
		public String getActionDescription() {
			return "跟[npc2.name]说你还没爽够呢！";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()==null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
						|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return "[npc.name]抓住[npc2.namePos][npc2.hips+]，将[npc.cock+]顶到[npc2.her][npc2.pussy+]上，然后向前一挺，将[npc.cock+]深深插入[npc2.her]塞满精液的小穴。"
					+ "[npc.she]感觉到[npc2.namePos][npc2.labia+]撞击[npc.her]鸡巴根部，[npc.she]发出深深的呻吟，低吼着，"
					+ "[npc.speechNoEffects(让我再灌满你一次！我要确保你真的怀孕了！)]</br>"
					+ "[npc.Name]将[npc.cock+]插入[npc2.namePos]的[npc2.pussy]中，拿出一个装满淡蓝色液体的小瓶子。"
					+ "[npc.she]用牙齿拔出瓶塞，迅速喝下药水，然后把空瓶扔到一边。</br>"
					+ "[npc.Name]将[npc.her][npc.臀部]后撤，然后开始再次有节奏地操[npc2.name]，"
						+ "[npc.her]每一次插入都伴随着淫荡的挤压声，充满[npc2.namePos]小穴的精液在[npc.her]粗大的阴茎周围流出。</br>"
					+ "[npc2.name]很快就意识到刚才[npc.Name]喝下的药水是做什么用的了，因为每次[npc.race]的蛋蛋拍打[npc2.her][npc2.assSkin]时，都会明显感觉越来越重。"
					+ "为了证实[npc2.her]的猜测，[npc.name]咕哝了一声，[npc.speech(操，我的蛋蛋又涨满了！你的子宫会被我的种子填满的！)]";
		}

		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction PARTNER_ROUND_TWO_START_SWITCH = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "第二轮(换位)";
		}

		@Override
		public String getActionDescription() {
			return UtilText.parse(getOtherTarget(), "选择操[npc.name]。");
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return getOtherTarget()!=null
					&& (Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.ORGASM
							|| Main.sex.getLastUsedSexAction(Main.game.getNpc(FortressMalesLeader.class)).getActionType()==SexActionType.PREPARE_FOR_PARTNER_ORGASM)
					&& !isBothTargetsUsed()
					&& Main.sex.getNumberOfOrgasms(Main.sex.getCharacterPerformingAction())==1
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(FortressMalesLeader.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			return UtilText.parse(Util.newArrayListOfValues(Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterTargetedForSexAction(this), getOtherTarget()),
					(getOtherTarget().isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
							?" 然后，[npc.she]快速移除[npc3.namePos][npc3.pussy]上碍事的衣物，开始在[npc3.her][npc3.labia+]上上下蹭动[npc.her]的龟头。"
							:"接着，[npc.she]迅速将[npc.her][npc.cock+]对准[npc3.namePos][npc3.pussy]，用龟头在[npc3.her][npc3.labia+]间上下蹭动。")
					+"[npc.she]突然向前挺动[npc.hips]，将阴茎深深插入[npc3.namePos][npc2.pussy+]中，在插入[npc3.herHim]时发出低沉的咆哮声，"
					+ "[npc.speechNoEffects(不要以为我原谅你了！)]</br></br>"
					+ "[npc.Name]将[npc.cock+]插入[npc3.namePos]的[npc3.pussy]，然后拿出一个装满淡蓝色液体的小瓶子。"
					+ "[npc.she]用牙齿拔出瓶塞，迅速喝下药水，然后把空瓶扔到一边。</br></br>"
					+ "[npc.Name]将[npc.hips]后撤，再次有节奏地操起[npc3.name]，"
						+ "[npc.her]每次用力，都能轻松地在[npc3.her][npc3.pussy+]中抽插，多亏了[npc2.namePos]小穴溢出的爱液，润滑了[npc.her]粗大的鸡巴。</br>"
					+ "[npc3.name]很快就意识到刚才[npc.Name]喝下的药水是做什么用的了，因为每次[npc.race]的蛋蛋拍打[npc3.her][npc3.assSkin]时，都会明显感觉越来越重。"
					+ "为了证实她的猜测，[npc.name]咕哝了一声[npc.speech(操，我的蛋蛋又涨满了！你的子宫会被我的种子填满的！)]");
		}
		
		@Override
		public void applyEffects(){
			Main.game.getNpc(FortressMalesLeader.class).fillCumToMaxStorage();
			GameCharacter otherTarget = getOtherTarget();

			Map<AbstractClothing, DisplacementType> clothingTouched = otherTarget.displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
			
			Main.sex.stopAllOngoingActions(otherTarget, SexAreaOrifice.VAGINA, otherTarget, false);
			
			Main.sex.stopOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					getBreedingTarget(),
					SexAreaOrifice.VAGINA);

			Main.sex.stopOngoingAction(
					otherTarget,
					SexAreaPenetration.TONGUE,
					Main.sex.getCharacterPerformingAction(),
					SexAreaOrifice.VAGINA);
					
			Main.sex.applyOngoingAction(
					Main.sex.getCharacterPerformingAction(),
					SexAreaPenetration.PENIS,
					otherTarget,
					SexAreaOrifice.VAGINA,
					true);
		}
	};


	
}
