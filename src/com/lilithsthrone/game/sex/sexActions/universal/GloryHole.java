package com.lilithsthrone.game.sex.sexActions.universal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.dominion.gloryHole.SMGloryHole;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.PositioningData;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class GloryHole {
	
	private static void applyChangeSlotEffects() {
		Map<GameCharacter, SexSlot> dominants = new HashMap<>();
		Map<GameCharacter, SexSlot> submissives = new HashMap<>();

		List<GameCharacter> doms = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
		List<GameCharacter> subs = new ArrayList<>(Main.sex.getSubmissiveParticipants(false).keySet());
		
		if(Main.sex.isDom(Main.game.getPlayer())) {
			dominants.put(Main.game.getPlayer(), Main.sex.getPositionRequest().getPartnerSlots().get(0));
			if(Main.sex.getDominantParticipants(false).size()==2) {
				doms.remove(Main.game.getPlayer());
				dominants.put(doms.get(0), Main.sex.getPositionRequest().getPartnerSlots().get(1));
			}
			submissives.put(subs.get(0), Main.sex.getPositionRequest().getPerformerSlots().get(0));
			
		} else {
			submissives.put(Main.game.getPlayer(), Main.sex.getPositionRequest().getPartnerSlots().get(0));
			if(Main.sex.getDominantParticipants(false).size()==2) {
				subs.remove(Main.game.getPlayer());
				submissives.put(subs.get(0), Main.sex.getPositionRequest().getPartnerSlots().get(1));
			}
			dominants.put(doms.get(0), Main.sex.getPositionRequest().getPerformerSlots().get(0));
		}
		
		Main.sex.setSexManager(new SexManagerDefault(
				Main.sex.getPositionRequest().getPosition(),
				dominants,
				submissives){
		});
	}

	public static final SexAction POSITION_DOUBLE_ORAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
							|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| ((NPC) Main.sex.getCharacterPerformingAction()).isHappyToBeInSlot(SexPosition.GLORY_HOLE, SexSlotUnique.GLORY_HOLE_KNEELING, Main.game.getPlayer()));
		}
		
		@Override
		public String getActionTitle() {
			return "跪下";
		}

		@Override
		public String getActionDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				return "跪在地上，准备用你的嘴为寻欢洞服务。";
			} else {
				return "跪在地上，准备用嘴为寻欢洞服务。";
			}
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				return "[npc.name]拉开与寻欢洞的距离，后退了一步并跪在了地上。"
						+ "[npc.she]来回看着暴露的生殖器，发出一声[npc.a_moan+]并准备用[npc.her]的嘴口交。";
				
			} else {
				return "[npc.name]拉开与寻欢洞的距离，后退了一步并跪在寻欢洞前的地上。"
						+ "[npc.she]看着暴露的生殖器，露出了饥渴的笑容。[npc.she]发出了一声[npc.a_moan+]并前倾身体，准备使用[npc.her]的嘴。";
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));

				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE,
						Util.newHashMapOfValues(
								new Value<>(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING))));
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_KNEELING))));
			}
		}
	};
	
	public static final SexAction POSITION_ANAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaOrifice.ANUS));
		}
		
		@Override
		public String getActionTitle() {
			return "呈现屁眼";
		}

		@Override
		public String getActionDescription() {
			return "你把[npc.ass+]顶到[npc2.namePos]的寻欢洞口，准备让[npc2.herHim]使用你[npc.asshole+]。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "[npc.name]站起来朝[npc2.name]走去，然后弯下腰，将[npc.her][npc.ass+]抵住寻欢洞。"
							+ "[npc.she]身体前倾，摆好位置，让[npc.her]的嘴与隔间对面的洞口只有[unit.sizes]的距离，"
								+ "然后[npc.she]发出[npc.a_moan+]，准备让[npc.her][npc.asshole+]和嘴同时被使用。";
					
				} else {
					return "[npc.name]拉开与寻欢洞的距离，调整位置，弯下腰，将[npc.her][npc.ass+]抵住[npc2.namePos]的寻欢洞。"
							+ "[npc.she]身体前倾，摆好位置，让[npc.her]的嘴与隔间对面的洞口只有[unit.sizes]的距离，"
								+ "然后[npc.she]发出[npc.a_moan+]，准备让[npc.her][npc.asshole+]和嘴同时被使用。";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "[npc.name]站起来朝[npc2.name]走去，然后弯下腰，将[npc.her][npc.ass+]抵住寻欢洞。"
							+ "[npc.she]发出[npc.a_moan+]，背靠着墙，准备让[npc.her][npc.asshole+]被使用。";
					
				} else {
					return "[npc.name]从寻欢洞中抽身出来，重新摆好[npc.herself]的姿势并弯下腰，将[npc.her][npc.asshole+]抵住寻欢洞。"
							+ "[npc.she]发出[npc.a_moan+]，背靠着墙，准备让[npc.her][npc.asshole+]被使用。";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(
								new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED))));
				
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED))));
			}
		}
	};

	public static final SexAction POSITION_VAGINAL = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasVagina()
					&& Main.sex.isPositionChangingAllowed(Main.sex.getCharacterPerformingAction())
					&& (Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					&& Main.sex.getCharacterPerformingAction().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& (Main.sex.getCharacterPerformingAction().isPlayer()
							|| (!Main.sex.getCharacterTargetedForSexAction(this).getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isNegative()
									&& ((NPC) Main.sex.getCharacterPerformingAction()).getCurrentSexPreference(Main.sex.getCharacterTargetedForSexAction(this)).getPerformingSexArea()==SexAreaOrifice.VAGINA));
		}
		
		@Override
		public String getActionTitle() {
			return "露出小穴";
		}

		@Override
		public String getActionDescription() {
			return "你将[npc.ass+]抵住[npc2.namePos]的寻欢洞，准备让[npc2.herHim]使用你[npc.pussy+]。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "[npc.name]站起来朝[npc2.name]走去，然后弯下腰，将[npc.her][npc.ass+]抵住寻欢洞。"
							+ "[npc.she]身体前倾，摆好位置，让[npc.her]的嘴与隔间对面的洞口只有[unit.sizes]的距离，"
								+ "然后[npc.she]发出一阵[npc.a_moan+]，准备让[npc.her][npc.pussy+]和嘴同时被使用。";
					
				} else {
					return "[npc.name]拉开与寻欢洞的距离，调整位置，弯下腰，将[npc.her][npc.ass+]抵住[npc2.namePos]的寻欢洞。"
							+ "[npc.she]身体前倾，摆好位置，让[npc.her]的嘴与隔间对面的洞口只有[unit.sizes]的距离，"
								+ "然后[npc.she]发出一阵[npc.a_moan+]，准备让[npc.her][npc.pussy+]和嘴同时被使用。";
				}
				
			} else {
				if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction())==SexSlotUnique.GLORY_HOLE_KNEELING) {
					return "[npc.name]站起来朝[npc2.name]走去，然后弯下腰，将[npc.her][npc.ass+]抵住寻欢洞。"
							+ "[npc.she]发出一阵[npc.a_moan+]，背靠着墙，准备让[npc.her][npc.pussy+]被使用。";
					
				} else {
					return "[npc.name]从寻欢洞中抽身出来，重新摆好的姿势，弯下腰，将[npc.her][npc.asshole+]抵住寻欢洞。"
							+ "[npc.she]发出一阵[npc.a_moan+]，背靠着墙，准备让[npc.her][npc.pussy+]被使用。";
				}
			}
		}

		@Override
		public void applyEffects() {
			if(Main.sex.getTotalParticipantCount(false)==3) {
				List<GameCharacter> participants = new ArrayList<>(Main.sex.getAllParticipants());
				participants.remove(Main.sex.getCharacterPerformingAction());
				GameCharacter characterFucked = Main.sex.getCharacterPerformingAction();
				participants.remove(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()));
				GameCharacter characterFucking = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
				
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(
								new Value<>(characterFucking, SexSlotUnique.GLORY_HOLE_FUCKING),
								new Value<>(participants.get(0), SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)),
						Util.newHashMapOfValues(
								new Value<>(characterFucked, SexSlotUnique.GLORY_HOLE_FUCKED))));
				
			} else {
				Main.sex.setSexManager(new SMGloryHole(
						SexPosition.GLORY_HOLE_SEX,
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterTargetedForSexAction(this), SexSlotUnique.GLORY_HOLE_FUCKING)),
						Util.newHashMapOfValues(new Value<>(Main.sex.getCharacterPerformingAction(), SexSlotUnique.GLORY_HOLE_FUCKED))));
			}
		}
	};
	
	public static final SexAction POSITION_VAGINAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE_SEX,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKED),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
		}
		@Override
		public String getActionTitle() {
			return "操小穴(请求)";
		}
		@Override
		public String getActionDescription() {
			return "你让[npc2.name]把[npc2.her]的小穴对准寻欢洞，这样你就能操它了。";
		}
		@Override
		public String getDescription() {
			return "你想操[npc2.namePos]的小穴，于是大声叫喊，让[npc2.herHim]听到：[npc.speech(把你的小穴对准洞口！我想操你！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_RECEIVING);
			}
		}
	};

	public static final SexAction POSITION_ANAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE_SEX,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_FUCKING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_KNEELING
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_FUCKED)
					&& Main.game.isAnalContentEnabled()
					&& Main.sex.getCharacterTargetedForSexAction(this).isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& Main.sex.getCharacterPerformingAction().isPlayer()
					&& !Main.sex.getRequestsBlocked(Main.game.getPlayer()).contains(new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
		}
		@Override
		public String getActionTitle() {
			return "操屁股(请求)";
		}
		@Override
		public String getActionDescription() {
			return "你让[npc2.name]把[npc2.her]的屁股对准寻欢洞，这样你就能操它了";
		}
		@Override
		public String getDescription() {
			return "你想操[npc2.namePos]的屁股，就大声叫喊，让[npc2.herHim]听到：[npc.speech(把屁股对准洞口！我想操你的屁股！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			}
		}
	};

	public static final SexAction POSITION_ORAL_REQUEST = new SexAction(
			SexActionType.POSITIONING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		
		private PositioningData data = new PositioningData(
				SexPosition.GLORY_HOLE,
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_KNEELING),
				Util.newArrayListOfValues(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_FUCKED
						|| Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this))==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public String getActionTitle() {
			return "口交(请求)";
		}
		@Override
		public String getActionDescription() {
			return "你要求[npc2.name]跪在寻欢洞前并让[npc2.she]口交。";
		}
		@Override
		public String getDescription() {
			return "你想让[npc2.name]口交，于是大声叫喊，让[npc2.herHim]听到：[npc.speech(跪下，让我用你的嘴！)]";
		}
		@Override
		public void applyEffects() {
			Main.sex.setPositionRequest(data);
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(Main.sex.getCharacterPerformingAction().equals(character)) {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_GIVING);
				
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
			}
		}
	};

	public static final SexAction PARTNER_POSITION_RESPONSE = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getPositionRequest()!=null
					&& !Main.sex.getCharacterPerformingAction().isPlayer();
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
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
			if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
				if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
					return "[npc.Name]站起来并向后移动，将[npc.her][npc.pussy+]抵住寻欢洞并[npc.moansVerb]，"
							+ "[npc.speech(来吧！操我的小穴！)]";
					
				} else {
					return "[npc.name]停留在[npc.her]当前的位置，生气地叫喊道："
							+ "[npc.speech(我才不会让你操我的小穴！差不多得了！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
				if(!Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
					return "[npc.name]站起来并向后移动，以便将[npc.her][npc.asshole+]抵住寻欢洞并[npc.moansVerb]，"
							+ "[npc.speech(来吧，干我的屁股！)]";
					
				} else {
					return "[npc.name]停留在[npc.her]当前的位置，生气地叫喊道："
							+ "[npc.speech(我才不会让你操我的小穴！差不多得了！)]";
				}
				
			} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_KNEELING) {
				return "[npc.name]跪倒在地并[npc.moansVerb]，"
							+ (Main.game.getPlayer().hasPenis()
								?"[npc.speech(让我嗦你鸡巴！)]"
								:"[npc.speech(让我口！)]");
			}
			
			return "";
		}

		@Override
		public void applyEffects() {
			if((Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()
					|| (Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED && !Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()))
					|| Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_KNEELING) {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, null)));
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, null)));
					
				} else {
					((NPC)Main.sex.getCharacterPerformingAction()).generateSexChoices(true, Main.game.getPlayer(), Util.newArrayListOfValues(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, null)));
				}
				applyChangeSlotEffects();
				
			} else {
				if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_FUCKED) {
					Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.VAGINA));
					
				} else if(Main.sex.getPositionRequest().getPerformerSlots().get(0)==SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED) {
					Main.sex.addRequestsBlocked(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, null, SexAreaOrifice.ANUS));
				}
			}
			
			Main.sex.setPositionRequest(null);
		}
	};
}
