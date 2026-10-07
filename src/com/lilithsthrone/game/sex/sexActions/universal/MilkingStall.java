package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.8
 * @version 0.3.9
 * @author Innoxia
 */
public class MilkingStall {

	public static final SexAction ATTACH_PUMPS = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		private List<InventorySlot> getPumpSlots() {
			List<InventorySlot> list = new ArrayList<>();
			GameCharacter milker = Main.sex.getCharacterTargetedForSexAction(this);
			
			if(MilkingRoom.getActualMilkPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.NIPPLE, null)) {
				if(milker.getClothingInSlot(InventorySlot.NIPPLE)==null) {
					list.add(InventorySlot.NIPPLE);
				}
			}
			if(MilkingRoom.getActualCrotchMilkPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_MILK_CROTCH)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.STOMACH, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.STOMACH, null)) {
				if(milker.getClothingInSlot(InventorySlot.STOMACH)==null) {
					list.add(InventorySlot.STOMACH);
				}
			}
			if(MilkingRoom.getActualCumPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_CUM)
					&& milker.isAbleToAccessCoverableArea(CoverableArea.PENIS, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.PENIS, null)) {
				if(milker.getClothingInSlot(InventorySlot.PENIS)==null) {
					list.add(InventorySlot.PENIS);
				}
			}
			if(MilkingRoom.getActualGirlcumPerHour(milker)>0
					&& milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_GIRLCUM)
					&& (!milker.hasHymen() || milker.hasSlaveJobSetting(SlaveJob.MILKING, SlaveJobSetting.MILKING_TEAR_HYMEN))
					&& milker.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false)
					&& Main.sex.isClothingEquipAvailable(milker, InventorySlot.VAGINA, null)) {
				if(milker.getClothingInSlot(InventorySlot.VAGINA)==null) {
					list.add(InventorySlot.VAGINA);
				}
			}
			
			return list;
		}
		@Override
		public Colour getHighlightColour() {
			return PresetColour.MILK;
		}
		@Override
		public boolean isDisplayedAsUnavailable() {
			return true;
		}
		@Override
		public String getActionTitle() {
			return "连接挤奶器";
		}
		@Override
		public String getActionDescription() {
			List<InventorySlot> pumps = getPumpSlots();
			if(pumps.isEmpty()) {
				//"[npc2.Name] doesn't have any accessible areas for milking pumps to be attached to!"
				return "<i>[style.italicsBad(需求：)]其中一个栏位处于可用状态：乳头、阴茎、阴道和腹部(有腹乳时)。"
						+ "此外，[npc2.namePos]的挤奶工作权限允许在该装备栏中配备奶泵。</i>";
			}
			return "将挤奶机的奶泵连接到[npc2.namePos]的身上"+Util.inventorySlotsToStringList(pumps)+".";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			List<InventorySlot> pumps = getPumpSlots();
			return !pumps.isEmpty()
					&& Main.sex.getCharacterPerformingAction().isPlayer() // Limit to the player as otherwise it gets incredibly annoying
					&& Main.sex.getSexControl(Main.sex.getCharacterPerformingAction())==SexControl.FULL
					&& (!Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS) || Main.sex.isMasturbation())
					&& Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.LOCKED_IN_STOCKS);
		}

		@Override
		public String getDescription() {
			List<InventorySlot> pumps = getPumpSlots();
			
			if(pumps.size()>1 || pumps.contains(InventorySlot.NIPPLE) || pumps.contains(InventorySlot.STOMACH)) {
				return "决定将[npc2.name]连接上挤奶机，[npc.name]绕到摊位的一侧并且拿起一些尚未使用的奶泵……";
				
			} else {
				return "决定将[npc2.name]连接上挤奶机，[npc.name]绕到摊位的一侧并且拿起一个尚未使用的奶泵……";
			}
		}

		@Override
		public String applyEffectsString(){
			List<InventorySlot> pumps = getPumpSlots();
			GameCharacter equipper = Main.sex.getCharacterPerformingAction();
			GameCharacter milker = Main.sex.getCharacterTargetedForSexAction(this);
			StringBuilder sb = new StringBuilder();
			
			for(InventorySlot slot : pumps) {
				sb.append("<p style='text-align:center;padding:0;margin:0;'><i>");
					switch(slot) {
						case NIPPLE:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.NIPPLE, true, equipper));
							break;
						case STOMACH:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_breast_pumps"), false), InventorySlot.STOMACH, true, equipper));
							break;
						case PENIS:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_penis_pump"), false), true, equipper));
							break;
						case VAGINA:
							sb.append(milker.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.getClothingTypeFromId("innoxia_milking_vagina_pump"), false), true, equipper));
							break;
						default:
							break;
					}
				sb.append("</i></p>");
			}
			
			return sb.toString();
		}
	};
	
	public static final SexAction SWITCH_TO_BEHIND = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.BEHIND_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "移动到后面";
		}

		@Override
		public String getActionDescription() {
			return "移动到[npc2.name]身后，准备好插入[npc2.herHim]。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]想趁着[npc2.name]被锁在颈手枷的时候干[npc2.her]，"
						+ "便[npc.step]向[npc2.herHim]的身后，将下体紧紧贴在了[npc2.her][npc2.ass+]。"
					+ "[npc.she]牢牢地抓住[npc2.her][npc2.hips+]，[npc.moanVerb]道，"
					+ "[npc.speech(乖乖的，在我操你的时候不要乱动！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.BEHIND_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

	public static final SexAction SWITCH_TO_BENEATH = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.ALL_FOURS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction())
					&& Main.sex.getTotalParticipantCount(false)==2;
		}
		
		@Override
		public String getActionTitle() {
			return "四肢跪地";
		}

		@Override
		public String getActionDescription() {
			return "在[npc2.name]的身下四肢跪地，将你[npc.ass+]抵住[npc2.her]的下体，准备好被[npc2.herHim]操了。";
		}

		@Override
		public String getDescription() {
			return "[npc.Name]想被[npc2.name]插入，于是四肢跪地，趴在[npc2.her]的颈手枷之下。"
					+ "挪动身子摆到了个舒服的姿势，抬起[npc.hips+]，将[npc.ass+]抵在了[npc2.her]的下体上。"
					+ "[npc.Name]带着激动的[npc.moan]高喊道，"
					+ "[npc.speech(你赚到了哦！我正准备让你干我呢！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.BENEATH_MILKING_STALL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
		}
	};
	
	public static final SexAction SWITCH_TO_GIVING_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "提供口交";
		}

		@Override
		public String getActionDescription() {
			return "跪在[npc2.name]后面并且为[npc2.herHim]口交 。";
		}

		@Override
		public String getDescription() {
			return "[npc.name]决定给[npc2.name]口交，便跪在[npc2.herHim]后面。"
					+ "[npc.her]把嘴巴贴向[npc2.her]的下体，[npc.moansVerb]道，"
					+ "[npc.speech(你会喜欢这个的！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.PERFORMING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};
	
	public static final SexAction SWITCH_TO_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.RECEIVING_ORAL_STOCKS)
					&& !Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& Main.sex.isDom(Main.sex.getCharacterPerformingAction());
		}
		
		@Override
		public String getActionTitle() {
			return "挪到前面";
		}

		@Override
		public String getActionDescription() {
			return "决定使用[npc2.namePos]的嘴。";
		}

		@Override
		public String getDescription() {
			return "[npc.she]决定使用他的嘴，[npc.name]#IF(npc.isPlayer())[npc.step]#ELSE[npc.steps]#ENDIF返回，然后在[npc2.face]前面晃来晃去。"
					+ "[npc.her]把下体贴向[npc2.her]的嘴巴，[npc.moansVerb]"
					+ "[npc.speech(你会喜欢这个的！)]";
		}

		@Override
		public void applyEffects() {
			Main.sex.setSexManager(new SMMilkingStall(
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotMilkingStall.RECEIVING_ORAL)),
					Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotMilkingStall.LOCKED_IN_MILKING_STALL))));
			
		}
	};

}
