package com.lilithsthrone.game.sex.positions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.dominion.CultistSexActions;
import com.lilithsthrone.game.sex.sexActions.dominion.PetMounting;
import com.lilithsthrone.game.sex.sexActions.dominion.PetOral;
import com.lilithsthrone.game.sex.sexActions.dominion.PixShower;
import com.lilithsthrone.game.sex.sexActions.dominion.RalphOral;
import com.lilithsthrone.game.sex.sexActions.dominion.RoseHandHolding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * 
 * @since 0.1.97
 * @version 0.3.4
 * @author Innoxia
 */
public class SexPositionUnique {

	public static final AbstractSexPosition PET_MOUNTING = new AbstractSexPosition("被骑乘",
			2,
			true,
			null, Util.newArrayListOfValues(PetMounting.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS), Main.sex.getCharacterInPosition(SexSlotUnique.PET_MOUNTING_HUMPING),
					"[npc.NameIs]四肢跪地，被[npc2.name]骑在身上，[npc2.she]正跃跃欲试地想插入进去，开始交尾。");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(SexSlotUnique.PET_MOUNTING_HUMPING, SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their tails or tentacles to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.PET_MOUNTING_ON_ALL_FOURS
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.PET_MOUNTING_HUMPING) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition PET_ORAL = new AbstractSexPosition("宠物口交",
			2,
			true,
			null, Util.newArrayListOfValues(PetOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotUnique.PET_ORAL_ON_ALL_FOURS), Main.sex.getCharacterInPosition(SexSlotUnique.PET_ORAL_COCKED_LEG),
					"[npc.NameIs]四肢跪地，被[npc2.namePos]的[npc2.leg]锁住了脖子，脸距离[npc2.namePos][npc2.cock+]仅有[unit.sizes]。");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.PET_ORAL_ON_ALL_FOURS, SexSlotUnique.PET_ORAL_COCKED_LEG));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition UNDER_DESK_RALPH = new AbstractSexPosition("桌下",
			2,
			false,
			null,
			Util.newArrayListOfValues(RalphOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "你跪在拉尔夫的柜台下，面部距离他的胯部仅有[unit.sizes]。";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOralRalph.getSexActionInteractions(SexSlotUnique.RALPH_SUB, SexSlotUnique.RALPH_DOM));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character taking oral can use their arms to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.RALPH_SUB
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.RALPH_DOM) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};

	public static final AbstractSexPosition OVER_DESK_RALPH = new AbstractSexPosition("桌上",
			2,
			false,
			null,
			Util.newArrayListOfValues(RalphOral.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "你弯腰趴在拉尔夫的柜台上，马男正站在你身后。";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOralRalph.getSexActionInteractions(SexSlotUnique.RALPH_SUB_SEX, SexSlotUnique.RALPH_DOM_SEX));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their legs to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.RALPH_SUB_SEX
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.RALPH_DOM_SEX) {
					return Util.newHashMapOfValues(
							new Value<>(Leg.class, genericGroinForceCreampieAreas));
				}
			return null;
		}
	};
	
	public static final AbstractSexPosition SHOWER_TIME_PIX = new AbstractSexPosition("淋浴间性爱",
			2,
			false,
			null, Util.newArrayListOfValues(PixShower.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "你面朝淋浴间的墙面，被压在此处，而背后则是皮克斯，在你耳边饥渴低吼着。";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.standingBehindCharacterFacingWall.getSexActionInteractions(SexSlotUnique.FACE_TO_WALL_FACING_TARGET_SHOWER_PIX, SexSlotUnique.FACE_TO_WALL_AGAINST_WALL_SHOWER_PIX));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition HANDS_ROSE = new AbstractSexPosition("握手",
			2,
			false,
			null, Util.newArrayListOfValues(RoseHandHolding.class, GenericOrgasms.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "你和那猫女仆萝丝面对着面，期待着对对方的手做些淫秽的举动。";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.handHolding.getSexActionInteractions(SexSlotUnique.HAND_SEX_DOM_ROSE, SexSlotUnique.HAND_SEX_SUB_ROSE));
			interactions.add(StandardSexActionInteractions.handHolding.getSexActionInteractions(SexSlotUnique.HAND_SEX_SUB_ROSE, SexSlotUnique.HAND_SEX_DOM_ROSE));
			return generateSlotTargetsMap(interactions);
		}
	};
	
	public static final AbstractSexPosition KNEELING_ORAL_CULTIST = new AbstractSexPosition("跪姿",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			return "你跪在[npc.name]的[npc.feet]边，[pc.face+]距离[npc.her]的下体仅有[unit.sizes]。";
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST, SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character taking oral can use their arms to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.KNEELING_PERFORMING_ORAL_CULTIST
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.KNEELING_RECEIVING_ORAL_CULTIST) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition MISSIONARY_ALTAR_CULTIST = new AbstractSexPosition("祭坛上传教士体位",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			// I spent several hours trying to figure out where UtilText.parse input NPCs were set.
			// I gave up and decided to do it in here so we *KNOW* which [npc] tags are doing what.
			// There is precedent for doing this in SexPosition.java.
			
			List<GameCharacter> characters = new ArrayList<>();
			boolean isKneeling = false;
			// 0 - Lying on altar
			// 1 - Standing OR kneeling, as determined by `kneeling`
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			if (Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS));
			} else if(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS));
				isKneeling = true;
			} else {
				return "MISSIONARY_ALTAR_CULTIST: 角色姿势不合法！";
			}

			// boolean playerIsOnTheAltar = characters.get(0).isPlayer();

			StringBuilder desc = new StringBuilder();
			desc.append("[npc.NameIs]平躺在教堂的祭坛上，");
			if (isKneeling) {
				desc.append("[npc2.nameIs]则跪在[npc.hisHer]的[npc.legs]间，准备以传教士体位用嘴巴跟[npc.himHer]好好玩乐一番。");
			} else {
				desc.append("[npc2.nameIs]站在[npc.hisHer]的[npc.legs]间，准备以传教士体位跟[npc.himHer]好好玩乐一番。");
			}
			return UtilText.parse(characters.get(0), characters.get(1), desc.toString());
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their legs, tails, or tentacles to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR
				&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				
			// The character on top can use their body weight to force a creampie:
			} else if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_LYING_ON_ALTAR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	
	public static final AbstractSexPosition MISSIONARY_ALTAR_SEALED_CULTIST = new AbstractSexPosition("祭坛上传教士体位",
			2,
			true,
			null, Util.newArrayListOfValues(CultistSexActions.class)) {
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {//TODO
			List<GameCharacter> characters = new ArrayList<>();
			boolean isKneeling = false;
			// 0 - Lying on altar
			// 1 - Standing OR kneeling, as determined by `kneeling`
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			if (Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS));
			} else if(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS));
				isKneeling = true;
			} else {
				return "MISSIONARY_ALTAR_SEALED_CULTIST: 角色姿势不合法！";
			}

			// boolean playerIsOnTheAltar = characters.get(0).isPlayer();

			StringBuilder desc = new StringBuilder();
			desc.append("[npc.NameIs]平躺在教堂的祭坛上，");
			if (isKneeling) {
				desc.append("[npc2.nameIs]则跪在[npc.hisHer]的[npc.legs]间，准备以传教士体位用嘴巴跟[npc.himHer]好好玩乐一番。");
			} else {
				desc.append("[npc2.nameIs]站在[npc.hisHer]的[npc.legs]间，准备以传教士体位跟[npc.himHer]好好玩乐一番。");
			}
			return UtilText.parse(characters.get(0), characters.get(1), desc.toString());
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS, SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR));
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if(Main.sex.getSexPositionSlot(performer) == SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
				if((action.getActionType()==SexActionType.ONGOING
						|| action.getActionType()==SexActionType.START_ONGOING
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION
						|| action.getActionType()==SexActionType.REQUIRES_EXPOSED
						|| action.getActionType()==SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED)) {
					return true;
				}
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character on top can use their body weight to force a creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotUnique.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
	};
	

}
