package com.lilithsthrone.game.sex.sexActions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PositioningMenu;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.3.2
 * @author Innoxia
 */
public class SexActionUtility {

	// GENERIC:
	
	public static final SexAction PLAYER_NONE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "什么都不做";
		}

		@Override
		public String getActionDescription() {
			return "一动不动。";
		}
		
		@Override
		public String getDescription() {
			if(Main.sex.isMasturbation()) {
				return "你定在原地，一动不动……";
			}
			
			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotGeneric.MISC_WATCHING) {
				List<GameCharacter> characters = new ArrayList<>(Main.sex.getAllParticipants());
				characters.remove(Main.sex.getCharacterPerformingAction());
				if(characters.size()>=2) {
					return UtilText.parse(characters,
							UtilText.returnStringAtRandom(
							"你保持原位，看着[npc.name]和[npc2.name]在你面前做爱。",
							"你几乎一动不动，看着[npc.name]和[npc2.name]在你面前交欢",
							"你继续观望[npc.name]和[npc2.name]，自己则什么都不做。"));
				}
			}
			
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							"你保持原位，轻轻地压向[npc.name]，但没有对[npc.herHim]做些什么。",
							"你几乎一动不动，压上了[npc.name]的身躯，等待着[npc.herHim]的下一步动作。",
							"你轻轻地压上[npc.name]的身躯，期待着[npc.herHim]作出下一步动作。");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							"你保持原位，压向[npc.name]，但没有对[npc.herHim]做些什么。",
							"你几乎一动不动，压上了[npc.name]的身躯，等待着[npc.herHim]的下一步动作。",
							"你压上[npc.name]的身躯，期待着[npc.herHim]作出下一步动作。");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							"你保持原位，磨蹭着[npc.name]的身体，但没有对[npc.herHim]做些什么。",
							"你几乎一动不动，磨蹭着[npc.name]的身躯，等待着[npc.herHim]的下一步动作。",
							"你磨蹭着[npc.name]的身躯，期待着[npc.herHim]作出下一步动作。");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							"你保持原位，压向[npc.name]，但没有对[npc.herHim]做些什么。",
							"你几乎一动不动，压上了[npc.name]的身躯，等待着[npc.herHim]的下一步动作。",
							"你压上[npc.name]的身躯，期待着[npc.herHim]作出下一步动作。");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							"你保持原位，压向[npc.name]，但没有对[npc.herHim]做些什么。",
							"你几乎一动不动，压上了[npc.name]的身躯，等待着[npc.herHim]的下一步动作。",
							"你压上[npc.name]的身躯，期待着[npc.herHim]作出下一步动作。");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							"你继续挣扎抵抗着[npc.name]，拒绝对[npc.herHim]做出任何事。",
							"你挣扎抽泣着想要挣脱[npc.namePos]的掌控，恐惧着[npc.her]的下一步行动。",
							"你试着将[npc.name]推开，痛苦地啜泣挣扎着，拒绝服从于[npc.herHim]。");
			}

			return "你保持原位，静观[npc.name]的下一步动作。";
		}
	};
	
	public static final SexAction PLAYER_CALM_DOWN = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.NEGATIVE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "平静下来";
		}

		@Override
		public String getActionDescription() {
			return "集中精力让自己平静下来，从而降低你的快感。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"你花了点时间把注意力从[npc.name]身上移开，在这个过程中让自己平静下来。",
							"你闭上眼睛，深呼吸，让自己平静下来，也降低了你的快感度。",
							"深吸一口气，集中精力让自己稍微平静下来。");
				case DOM_NORMAL:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"你花了点时间把注意力从[npc.name]身上移开，在这个过程中让自己平静下来。",
							"你闭上眼睛，深呼吸，让自己平静下来，也降低了你的快感度。",
							"深吸一口气，集中精力让自己稍微平静下来。");
				case DOM_ROUGH:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"你花了点时间把注意力从[npc.name]身上移开，在这个过程中让自己平静下来。",
							"你闭上眼睛，深呼吸，让自己平静下来，也降低了你的快感度。",
							"深吸一口气，集中精力让自己稍微平静下来。");
				case SUB_EAGER:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"你花了点时间把注意力从[npc.name]身上移开，在这个过程中让自己平静下来。",
							"你闭上眼睛，深呼吸，让自己平静下来，也降低了你的快感度。",
							"深吸一口气，集中精力让自己稍微平静下来。");
				case SUB_NORMAL:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"你花了点时间把注意力从[npc.name]身上移开，在这个过程中让自己平静下来。",
							"你闭上眼睛，深呼吸，让自己平静下来，也降低了你的快感度。",
							"深吸一口气，集中精力让自己稍微平静下来。");
				case SUB_RESISTING:
					return UtilText.returnStringAtRandom(
							Main.sex.isMasturbation()?"":"你仍在无力地挣扎着抵抗[npc.name]，试图让自己稍微平静下来，提醒着自己这一切很快就会结束。",
							"你紧闭双眼，努力深呼吸，假装这一切都没有发生，试图让自己平静下来。",
							Main.sex.isMasturbation()?"":"你深吸一口气，努力让自己平静下来，然后继续挣扎抵抗[npc.Name]。");
				default:
					return Main.sex.isMasturbation()?"":"你试着把注意力从正在和自己做爱的[npc.race]身上移开。这样做可以让自己稍微平静下来，降低快感度。";
			}
		}
	};
	
	public static final SexAction PARTNER_NONE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "什么都不做";
		}

		@Override
		public String getActionDescription() {
			return "决定什么都不做。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]什么都没做。";
		}
	};
	
	public static final SexAction PARTNER_ORGASM_SKIP = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public String getDescription() {
			return "[npc.Name]发出了[npc.a_moan+]。";
		}
	};

	public static final SexAction PLAYER_USE_ITEM = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "使用物品";
		}
		@Override
		public String getActionDescription() {
			return "看看有哪些物品能用。";
		}
		@Override
		public String getDescription() {
			return Main.sex.getUsingItemText();
		}
	};
	
	public static final SexAction PARTNER_SELF_EQUIP_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private Value<AbstractClothing, String> getSexClothingBeingUsed() {
			return getSexClothingBeingUsed(Main.sex.getCharacterPerformingAction());
		}
		private Value<AbstractClothing, String> getSexClothingBeingUsed(GameCharacter performer) {
			return ((NPC)performer).getSexClothingToSelfEquip(Main.sex.getClothingSelfEquipInformation().getValue().getKey(), false);
		}
		@Override
		public boolean isQuickSexRequirementsMet(GameCharacter performer) {
			return !performer.isPlayer()
					&& Main.sex.isCanRemoveSelfClothing(performer);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexClothingBeingUsed()!=null
					&& Main.sex.isCanRemoveSelfClothing(Main.sex.getCharacterPerformingAction());
		}
		@Override
		public String getActionTitle() {
			if(getSexClothingBeingUsed()!=null) {
				return "装备"+getSexClothingBeingUsed().getKey().getName();
			}
			return "装备服装";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexClothingBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString() {
			return "<p>"
						+ Main.sex.getCharacterPerformingAction().equipClothingFromInventory(getSexClothingBeingUsed().getKey(), true, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction())
					+ "</p>";
		}
	};

	public static final SexAction PARTNER_EQUIP_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private Value<AbstractClothing, String> getSexClothingBeingUsed() {
			return getSexClothingBeingUsed(Main.sex.getCharacterPerformingAction());
		}
		private Value<AbstractClothing, String> getSexClothingBeingUsed(GameCharacter performer) {
			return ((NPC) performer).getSexClothingToEquip(Main.sex.getClothingEquipInformation().getValue().getKey(), false);
		}
		@Override
		public boolean isQuickSexRequirementsMet(GameCharacter performer) {
			return !performer.isPlayer()
					&& Main.sex.isCanRemoveOthersClothing(performer, null);
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexClothingBeingUsed()!=null
					&& Main.sex.isCanRemoveOthersClothing(Main.sex.getCharacterPerformingAction(), getSexClothingBeingUsed().getKey());
		}
		@Override
		public String getActionTitle() {
			if(getSexClothingBeingUsed()!=null) {
				return "装备"+getSexClothingBeingUsed().getKey().getName();
			}
			return "装备服装";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexClothingBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString() {
			return "<p>"
						+ Main.sex.getClothingEquipInformation().getValue().getKey().equipClothingFromInventory(getSexClothingBeingUsed().getKey(), true, Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction())
					+ "</p>";
		}
	};
	
	public static final SexAction PARTNER_USE_ITEM = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private Value<AbstractItem, String> getSexItemBeingUsed() {
			return ((NPC) Main.sex.getCharacterPerformingAction()).getSexItemToUse(Main.sex.getItemUseInformation().getValue().getKey());
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterPerformingAction().isPlayer()
					&& getSexItemBeingUsed()!=null;
		}
		@Override
		public String getActionTitle() {
			if(getSexItemBeingUsed()!=null) {
				return "使用"+getSexItemBeingUsed().getKey().getName();
			}
			return "使用物品";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return getSexItemBeingUsed().getValue();
		}
		@Override
		public String applyEffectsString(){
			GameCharacter target = Main.sex.getItemUseInformation().getValue().getKey();
			
			if(target.equals(Main.sex.getCharacterPerformingAction())) { // If self-use, their use description forms part of the getSexItemBeingUsed() description.
				Value<AbstractItem, String> itemBeingUsed = getSexItemBeingUsed();
				Main.sex.addItemUseDenial(Main.sex.getCharacterPerformingAction(), target, itemBeingUsed.getKey().getItemType()); // Don't use the same item more than once in a scene
				return Main.sex.getCharacterPerformingAction().useItem(itemBeingUsed.getKey(), target, false, true); // Append only effects
			}
			
			// If using on NPC, the target is responsible for accepting or not:
			if(!target.isPlayer()) {
				Value<Boolean, String> result = ((NPC)target).getItemUseEffects(getSexItemBeingUsed().getKey(), Main.sex.getCharacterPerformingAction(), Main.sex.getCharacterPerformingAction(), target);
				
				if(!result.getKey()) { // Make sure that this character is tracked as having refused this item (so that it can be checked and not offered again in the NPC.getSexItemToUse() method).
					Main.sex.addItemUseDenial(Main.sex.getCharacterPerformingAction(), target, getSexItemBeingUsed().getKey().getItemType());
				}
				
				return result.getValue();
			}
			
			if(Main.sex.isForcingItemUse(Main.sex.getCharacterPerformingAction(), target)) { // If forced to use item, the use description forms part of the getSexItemBeingUsed() description.
				Main.sex.getCharacterPerformingAction().useItem(getSexItemBeingUsed().getKey(), target, false, true); // Append only effects
			}
			// If using on player, and not forced, the player handles refusing or not in their own SexAction, so return nothing.
			return "";
		}
	};
	
	public static final SexAction PLAYER_ACCEPT_ITEM_FROM_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getItemUseInformation()!=null;
		}
		@Override
		public String getActionTitle() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "接受"+item.getName(false);
		}
		@Override
		public String getActionDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return Util.capitaliseSentence(item.getUseName()+UtilText.parse(Main.sex.getItemUseInformation().getKey(), "[npc.name]给你的")+item.getName(false) +"。");
		}
		@Override
		public String getDescription() {
			return "";
		}
		@Override
		public String applyEffectsString() {
			AbstractItem item = Main.sex.getItemUseInformation().getValue().getValue();
			return Main.sex.getItemUseInformation().getKey().useItem(item, Main.game.getPlayer(), false, false); // Append full use + effects
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
	};

	public static final SexAction PLAYER_REFUSE_ITEM_FROM_PARTNER = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getItemUseInformation()!=null;
		}
		@Override
		public String getActionTitle() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "拒绝"+item.getName(false);
		}
		@Override
		public String getActionDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return "拒绝使用"+item.getUseName()+UtilText.parse(Main.sex.getItemUseInformation().getKey(), "[npc.name]给你的")+item.getName(false)+"。";
		}
		@Override
		public String getDescription() {
			AbstractItemType item = Main.sex.getItemUseInformation().getValue().getValue().getItemType();
			return UtilText.parse(Main.sex.getItemUseInformation().getKey(),
					"你拒绝接受[npc.name]给你的"+item.getName(false)+"。"
					+ "[npc.she]发了声失望的呜咽，把"+(item.isPlural()?"它们":"它")+"放回了[npc.her]的物品栏……");
		}
		@Override
		public void applyEffects() {
			// Make sure that this character is tracked as having refused this item (so that it can be checked and not offered again in the NPC.getSexItemToUse() method):
			Main.sex.addItemUseDenial(Main.sex.getItemUseInformation().getKey(), Main.game.getPlayer(), Main.sex.getItemUseInformation().getValue().getValue().getItemType());
		}
		@Override
		public boolean isAvailableDuringImmobilisation(Collection<ImmobilisationType> types) {
			return true;
		}
	};
	
	public static final SexAction CLOTHING_REMOVAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "管理服装";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return Main.sex.getUnequipClothingText();
		}
	};
	
	public static final SexAction CLOTHING_DYE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "管理服装";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return Main.sex.getDyeClothingText();
		}
	};
	
	public static final SexAction POSITION_SELECTION = new SexAction(
			SexActionType.POSITIONING_MENU,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ZERO_NONE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "调整姿势";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			try {
				return PositioningMenu.positioningSB.toString();
			} catch(Exception ex) {
				System.err.println("POSITION_SELECTION: positioningSB does not exist!");
				return "";
			}
		}
		
		@Override
		public void applyEffects() {
			PositioningMenu.setNewSexManager();
			Main.sex.setSexStarted(true);
		}
	};
}
