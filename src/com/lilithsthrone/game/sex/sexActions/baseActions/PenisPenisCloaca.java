package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.4.8.5
 * @version 0.4.8.5
 * @author Sightglass
 */
public class PenisPenisCloaca {
	private static boolean hasCloaca(GameCharacter gc) {
		return gc.getGenitalArrangement() == GenitalArrangement.CLOACA ||  gc.getGenitalArrangement() == GenitalArrangement.CLOACA_BEHIND;
	}

	// Innoxia: I removed the start/stop actions from this class as the PenisPenis class handles penis-to-penis actions as "frotting", and so by treating them as separate ongoing actions they clash with one another.
	/*
	public static final SexAction SLIT_PENETRATION_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
					    
		@Override
		public String getActionTitle() {
			return "Start slit penetration";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}

		@Override
		public String getActionDescription() {
			return "Start sliding [npc.her] [npc.cock] past [npc2.namePos] [npc2.cock] and into [npc2.her] genital slit.";
		}

		@Override
		public String getDescription() {
			
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
				case DOM_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							"Reaching down, [npc.name] [npc.verb(adjust)] [npc.her] [npc.cock] to line it up with [npc2.hers], before slipping past it, thrusting gently into [npc.her] genital slit.",
							"Carefully adjusting [npc.her] [npc.hips+], [npc.name] [npc.verb(press)] [npc.her] [npc.cock] along the base of [npc2.her] [npc2.cock], slipping the tip into [npc2.her] slit.",
							"With [npc.a_moan+], [npc.name] slowly [npc.verb(press)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to tease [npc.her] [npc.cock] against the opening of [npc2.her] cloaca."));
					break;
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"With a rough buck of [npc.her] [npc.hips+], [npc.name] forcefully [npc.verb(grind)] [npc.her] [npc.cock] against [npc2.namePos] genital slit, .",
							"With a growl, [npc.name] roughly [npc.verb(pull)] [npc2.namePos] crotch into place around [npc.her] [npc.legs], lining up [npc.her] [npc.cock] against [npc2.hers] and starting to thrust them against each other.",
							"With [npc.a_moan+], [npc.name] roughly [npc.verb(grind)] [npc.her] groin in between [npc2.namePos] [npc2.legs], before starting to forcefully grind [npc.her] [npc.cock] up and down over [npc2.hers]."));
					break;
			}
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_EAGER:
					sb.append(UtilText.returnStringAtRandom(
							" Happily bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and readily [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.her] body as [npc.name] [npc.verb(thrust)] into [npc2.her] slit.",
							" Responding with a happy buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to frantically drive [npc.her] [npc.cock] deeper into [npc2.her] cloaca."));
					break;
				case SUB_NORMAL:
					sb.append(UtilText.returnStringAtRandom(
							" Bucking [npc2.her] own [npc2.hips] in response,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(mirror)] the movements of [npc.name] and [npc2.verb(rub)] [npc2.her] [npc2.cock] against [npc.her] body as [npc.name] [npc.verb(thrust)] into [npc2.her] slit.",
							" Responding with a buck of [npc2.her] own [npc2.hips], [npc2.name] [npc2.verb(start)] mirroring [npc.namePos] movements, helping to drive [npc.her] [npc.cock] deeper into [npc2.her] cloaca."));
					break;
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							" Desperately trying to pull away,"
									+ " [npc2.name] [npc2.verb(let)] out [npc2.a_moan+] as [npc2.she] [npc2.verb(plead)] for [npc.name] to get away from [npc2.herHim] and pull out.",
							" Responding by frantically recoiling from [npc.namePos] unwanted advance, [npc2.name] [npc2.verb(start)] pleading to be left alone, all the while trying to pull [npc2.her] [npc2.cock] away from [npc.nameHers]."));
					break;
			}
			
			return sb.toString();
		}
		
	};
	*/
	
	private static String getTargetedCharacterResponse(SexAction action) {
		StringBuilder sb = new StringBuilder();
		switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
			case SUB_EAGER:
			case DOM_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]热情地回礼，在[npc.nameHers]身上来回磨蹭自己的[npc2.cock]，一声[npc2.a_moan+]不禁漏出。",
						"[npc2.name]急切地将[npc2.hips]前推，用自己的[npc2.cock]来回磨蹭着[npc.nameHers]的，一声[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
						"[npc2.name]愉悦地[npc2.moaning]着，饥渴地挺起[npc2.hips+]，顺着[npc.namePos]的动作，用自己的[npc2.cock]磨蹭着[npc.nameHers]的。"));
				break;
			case SUB_RESISTING:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.name]拼命想要逃离[npc.namePos]的[npc.cock]，但没能成功，只得无力地恳求[npc.name]放过[npc2.him]。",
						"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试将[npc.name]推离自己的下体。",
						"[npc2.name]悲痛地[npc2.Sobbing]着，泪水从[npc2.face]上流下，"
								+ "[npc2.she]无力地反抗着，哭着哀求[npc.name]远离[npc2.her]的下体。"));
				break;
			case SUB_NORMAL:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]回礼，并用自己的[npc2.cock]在对方的上面来回磨蹭，一声[npc2.a_moan+]不禁漏出。",
						"[npc2.name]挺起腰身，用[npc2.cock]来回磨蹭着对方的，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
						"[npc2.name]愉悦地[npc2.Moaning]着，挺起[npc2.hips+]，顺着[npc.namePos]的动作，用自己[npc2.cock+]磨蹭着[npc.her]的。"));
				break;
			case DOM_GENTLE:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]也温柔地回礼，用[npc2.cock]在[npc.nameHers]的上面来回磨蹭，同时发出[npc2.a_moan+]。",
						"[npc2.name]缓缓挺进腰身，用[npc2.cock]温柔地磨蹭[npc.nameHers]的[npc.cock]，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间泄出。",
						"[npc2.name]愉悦地[npc2.Moaning]着，缓缓挺出[npc2.hips+]，借着[npc.name]的动作将自己的[npc2.cock]紧贴在[npc.nameHers]的那上面。"));
				break;
			case DOM_ROUGH:
				sb.append(UtilText.returnStringAtRandom(
						"[npc2.Name]贪婪地回应着，一边发出[npc2.a_moan+]，一边粗暴地用自己的[npc2.cock]在[npc.nameHers]的那上面来回磨蹭。",
						"[npc2.name]粗暴地将[npc2.hips]前推，激烈地用[npc2.cock]来回磨蹭[npc.nameHers]的那个，一阵[npc2.A_moan+]从[npc2.namePos][npc2.lips+]间爆发而出。",
						"[npc2.name]愉悦地[npc2.moaning]着，激烈地挺出[npc2.hips+]，借助[npc.namePos]的动作粗暴地用[npc2.cock]磨蹭着[npc.nameHers]那里。"));
				break;
		}
		return sb.toString();
	}
	
	public static final SexAction SLIT_PENETRATION_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		@Override
		public String getActionTitle() {
			return "穴缝插入(温柔)";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return hasCloaca(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}
		@Override
		public String getActionDescription() {
			return "温柔地将[npc.cock]插入[npc2.namePos]的生殖裂。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.returnStringAtRandom(
					"[npc.name]温柔地将[npc.cock+]推进[npc2.namePos]的生殖裂中，每次摆腰都会漏出一小声[npc.moan]。",
					"[npc.Name]温柔地将[npc.cock+]滑入[npc2.namePos]泄殖腔，发出一连串轻柔的[npc.moans]。",
					"[npc.Name]用[npc.cock+]摩擦着[npc2.namePos][npc2.cock+]，而在插入[npc2.namePos]泄殖腔时，不禁漏出一小声[npc.moan]。"));

			sb.append(getTargetedCharacterResponse(this));
					
			return sb.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		@Override
		public String getActionTitle() {
			return "穴缝插入";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}
		@Override
		public String getActionDescription() {
			return "将你的[npc.cock]插入[npc2.namePos]的生殖裂。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"伴随着一声[npc.a_moan+]，[npc.name]急切地摆动着腰身，连忙将[npc.cock+]插入了[npc2.name]的生殖裂中。",
					"[npc.Name]饥渴地将[npc.cock+]抵在[npc2.namePos]泄殖腔上磨蹭，发出一连串淫荡的[npc.moans]。",
					"[npc.name]饥渴地用[npc.cock+]摩擦着[npc2.namePos][npc2.cock+]，而在[npc.she]插入[npc2.namePos]紧致的生殖裂时，发出了一声[npc.a_moan+]。"));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	public static final SexAction SLIT_PENETRATION_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		@Override
		public String getActionTitle() {
			return "穴缝插入(粗暴)";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
					&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}
		@Override
		public String getActionDescription() {
			return "粗暴地将[npc.cock+]挤进容纳着[npc2.namePos][npc2.cock+]的生殖裂。";
		}
		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.returnStringAtRandom(
					"伴随着一阵[npc.a_moan+]，[npc.name]用力挺着腰身，粗暴地将[npc.cock+]插入[npc2.namePos]的生殖裂中。",
					"[npc.Name]粗暴地将[npc.cock+]插入[npc2.namePos]泄殖腔，发出一连串淫荡的[npc.moans]。",
					"[npc.name]粗暴地用[npc.cock+]磨蹭着[npc2.namePos][npc2.cock+]，发出一阵[npc.a_moan+]，接着用力插入了[npc2.namePos]紧致的生殖裂。."));

			sb.append(getTargetedCharacterResponse(this));
			
			return sb.toString();
		}
	};
	
	// Innoxia: I removed the start/stop actions from this class as the PenisPenis class handles penis-to-penis actions as "frotting", and so by treating them as separate ongoing actions they clash with one another.
	/*
	public static final SexAction SLIT_PENETRATION_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop slit penetration";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (hasCloaca(Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction()))
				&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom());
		}
		
		@Override
		public String getActionDescription() {
			return "Pull your [npc.cock+] out of [npc2.namePos] genital slit.";
		}

		@Override
		public String getDescription() {
			
			StringBuilder sb = new StringBuilder();
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					sb.append(UtilText.returnStringAtRandom(
							"With one last rough thrust, [npc.name] [npc.verb(pull)] [npc.her] groin out of [npc2.namePos] genital slit.",
							"Roughly grinding [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, pulling out of [npc2.namePos] cloaca."));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							"With one last buck of [npc.her] [npc.hips], [npc.name] [npc.verb(pull)] [npc.her] groin out from [npc2.namePos] genital slit.",
							"Rubbing [npc.her] [npc.cock+] over [npc2.namePos] [npc2.cock] one last time, [npc.name] then [npc.verb(pull)] back, pulling out of [npc2.namePos] cloaca."));
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case SUB_RESISTING:
					sb.append(UtilText.returnStringAtRandom(
							" Although happy to have [npc2.her] slit released, [npc2.name] [npc2.verb(continue)] crying and weakly struggling against [npc.name] as [npc2.she] [npc2.verb(plead)] for [npc.herHim] to leave [npc2.herHim] alone.",
							" With [npc2.a_sob+], [npc2.name] [npc2.verb(continue)] to struggle and protest, tears streaming down [npc2.her] [npc2.face] as [npc2.she] [npc2.verb(pull)] [npc2.her] groin away from [npc.name]."));
					break;
				default:
					sb.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)] out [npc2.a_moan+] as [npc.name] [npc.verb(pull)] back, signalling [npc2.her] desire for more attention.",
							" [npc2.A_moan+] escapes from between [npc2.namePos] [npc2.lips+], betraying [npc2.her] desperate desire for more of [npc.namePos] attention."));
					break;
			}
			
			return sb.toString();
		}
	};
	*/
	
	// Innoxia: I removed the orgasm from this class as the PenisPenis class handles the penis-to-penis orgasm action, and so by treating them as separate orgasm actions they clash with one another.
	/*
	public static final SexAction SLIT_PENETRATION_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		private GameCharacter getCharacterToBeCreampied() {
			GameCharacter characterPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).get(0);
			
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter lockingCharacter = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				if(Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS).contains(lockingCharacter)) {
					characterPenetrated = lockingCharacter;
				}
				
			} else { // If not locked, can choose who to cum inside:
				List<GameCharacter> charactersPenetrated = Main.sex.getCharactersHavingOngoingActionWith(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS);
				if(charactersPenetrated.contains(Main.sex.getCharacterTargetedForSexAction(this))) {
					characterPenetrated = Main.sex.getCharacterTargetedForSexAction(this);
				}
			}
			
			return characterPenetrated;
		}
		private SexAreaInterface getAreaToBeCreampied() {
			return SexAreaPenetration.PENIS;
		}
		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			if(!performer.hasPenisIgnoreDildo()) {
				return false;
			}
			
			List<GameCharacter> ongoingPenis = Main.sex.getCharactersHavingOngoingActionWith(performer, SexAreaPenetration.PENIS);
			if(ongoingPenis.isEmpty()) {
				return false;
			}
			GameCharacter target = ongoingPenis.get(0);
			
			boolean dicksTouching = Main.sex.getOngoingSexAreas(performer, SexAreaPenetration.PENIS, target).contains(SexAreaPenetration.PENIS);;
			if (!dicksTouching || !hasCloaca(target)) {
				return false;
			}
			
			// Will not use if obeying pull out requests:
			if((Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)!=OrgasmBehaviour.CREAMPIE
					&& !performer.isPlayer()
					&& !Main.sex.getCreampieLockedBy().containsKey(performer) // Only allow this action to be blocked if no forced creampie.
					&& Main.sex.getRequestedPulloutWeighting(performer)>0)
				|| Main.sex.getSexManager().getCharacterOrgasmBehaviour(performer)==OrgasmBehaviour.PULL_OUT) {
				return false;
			}
			
			return true;
		}
		@Override
		public SexActionPriority getPriority() {
			boolean knotRequestObeyed = false;
			for(GameCharacter knotRequester : Main.sex.getCharactersRequestingKnot()) {
				if(Main.sex.isCharacterObeyingTarget(Main.sex.getCharacterPerformingAction(), knotRequester)) {
					knotRequestObeyed = true; // If there is a knot requester who they're listening to, give priority to knotting
					break;
				}
			}
			if(Main.sex.getSexManager().getCharacterOrgasmBehaviour(Main.sex.getCharacterPerformingAction())==OrgasmBehaviour.CREAMPIE) {
				return SexActionPriority.UNIQUE_MAX;
			}
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				return SexActionPriority.UNIQUE_MAX;
			}
			
			if(Math.random()<0.66f
					|| Main.sex.getCharacterPerformingAction().getFetishDesire(Fetish.FETISH_CUM_STUD).isPositive()
					|| Main.sex.getRequestedPulloutWeighting(Main.sex.getCharacterPerformingAction())<0
				&& !knotRequestObeyed) {
				return SexActionPriority.HIGH;
			}
			return SexActionPriority.NORMAL;
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return "Slit creampie! (forced)";
					
				} else if(bodypart == Arm.class) {
					return "Hug-locked slit creampie!";
					
				} else if(bodypart == Leg.class) {
					return "Leg-locked  slit creampie!";
					
				} else if(bodypart == Tail.class) {
					return "Tail-locked  slit creampie!";
					
				} else if(bodypart == Wing.class) {
					return "Wing-locked  slit creampie!";
					
				} else if(bodypart == Tentacle.class) {
					return "Tentacle-locked slit creampie!";
				}
			}
			return "Slit creampie!";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCreampieLockedBy().containsKey(Main.sex.getCharacterPerformingAction())) {
				GameCharacter character = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getKey();
				Class<? extends BodyPartInterface> bodypart = Main.sex.getCreampieLockedBy().get(Main.sex.getCharacterPerformingAction()).getValue();
				if(bodypart == Torso.class) {
					return UtilText.parse(character,
							"[npc.NameIsFull] using [npc.her] advantageous position to force you to grind your penis into [npc.her] cloaca as your orgasm! As you're on the very brink of orgasm, you have no time to try and push [npc.herHim] away!");
					
				} else if(bodypart == Arm.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.arms+] around your lower body, thereby forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Leg.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.legs+] around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tail.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] "+(character.getTailCount()>1?"[npc.tails+]":"[npc.tail]")+" around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Wing.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.wingSize] [npc.wings] around your body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
					
				} else if(bodypart == Tentacle.class) {
					return UtilText.parse(character,
							"[npc.NameHasFull] tightly wrapped [npc.her] [npc.tentacles+] around your lower body, forcing you to grind your penis into [npc.her] cloaca as you orgasm!"
							+ " As you're on the very brink of orgasm, you have no time to try and disentangle yourself from [npc.her] clutches!");
				}
			}
			
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			String returnString = "You've reached your climax, and can't hold back your orgasm any longer. Cum inside [npc2.name], grinding your penis into [npc.her] cloaca.";
			return UtilText.parse(Main.sex.getCharacterPerformingAction(), characterPenetrated, returnString);
		}

		@Override
		public String getDescription() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, false).getDescription();
		}
		
		@Override
		public void applyEffects() {
			Main.sex.setCreampieLockedBy(Main.sex.getCharacterPerformingAction(), null); // Need this before effects, as effects can set locking (such as in Lyssieth's demon TF scenes)
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEffects();
		}
		
		@Override
		public String applyEndEffects(){
			Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).applyEndEffects();
			return "";
		}
		
		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			GameCharacter characterPenetrated = getCharacterToBeCreampied();
			SexAreaInterface areaContacted = getAreaToBeCreampied();
			
			if(cumTarget.equals(characterPenetrated)) {
				return Util.newArrayListOfValues(areaContacted);
				
			} else {
				return null;
			}
		}

		@Override
		public List<CoverableArea> getAreasCummedOn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(cumProvider.equals(Main.sex.getCharacterPerformingAction())
					&& ((cumTarget.equals(Main.sex.getTargetedPartner(cumProvider)) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumTarget).isEmpty())
						|| (cumTarget.equals(cumProvider) && !Main.sex.getOngoingSexAreas(cumProvider, SexAreaPenetration.PENIS, cumProvider).isEmpty()))) {

				return Util.newArrayListOfValues(
						CoverableArea.PENIS,
						CoverableArea.VAGINA);
			}
			return null; 
		}
		
		@Override
		public boolean endsSex() {
			return Main.sex.getCharacterPerformingAction().getSexActionOrgasmOverride(this, OrgasmCumTarget.GROIN, true).isEndsSex();
		}
	};
	*/
	
	public static final SexAction MUTUAL_SLIT_PENETRATION = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "互相穴缝插入";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.namePos][npc2.cock+]滑入你的生殖裂，同时你也插进[npc2.hers]的。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getTargetedPartner(Main.sex.getCharacterPerformingAction());
			return hasCloaca(performer)
					&& hasCloaca(target)
					&& performer.hasPenisIgnoreDildo()
					&& target.hasPenisIgnoreDildo()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()).isDom();
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			String start = UtilText.returnStringAtRandom(
					"[npc.name]让[npc2.namePos][npc2.cock]的尖端滑入了包裹着自己[npc.cock+]的穴缝中，在缝里打着圈，挑逗着里面敏感的皮肤，不禁发出一阵[npc.a_moan+]。",
					"[npc.Name]发出[npc.a_moan+]，将手伸向[npc2.namePos][npc2.cock+]。"
							+ "[npc.name]将其扶到自己[npc.cock+]旁边，咧嘴一笑，随后就让对方滑入了自己的生殖裂中，使得[npc2.name]发出一阵[npc2.a_moan+]。",
					"[npc.Name]愉悦地笑了起来，扶着[npc2.namePos]的[npc2.cock]对准自己泄殖腔的入口，然后将其滑入。",
					"[npc.Name]将[npc2.namePos]的[npc2.cock]滑入自己的生殖裂中，并用缓慢的圈状动作挑逗着，不禁发出了一阵[npc.a_moan+]。");
			String middle1 = UtilText.returnStringAtRandom(
				"[npc.Name]缓缓将[npc2.namePos]的[npc2.cock]深入自己的穴缝，同时将自己[npc.cock+]也顶入[npc2.nameHers]的生殖裂。",
				"[npc.name]并没有让[npc2.namePos]的[npc2.cock]深深插进来，反而将自己[npc.cock+]推进了[npc2.namePos]的生殖裂中。",
				"当[npc2.namePos]的[npc2.cock]插入对方泄殖腔时，[npc.name]也将自己的肉棒顶入了[npc2.namePos]的生殖裂。"
			);
			String middle2 = UtilText.returnStringAtRandom(
				"[npc2.NameIsFull]被双重的刺激所淹没：[npc2.cock]被包裹在[npc.namePos]紧致的穴缝中，而[npc.her][npc.cock+]也进入了自己的生殖裂。"
						+ "[npc2.name]只能感受着这种令人麻木的快感，颤抖并[npc2.moan]着。",
				"[npc.namePos]泄殖腔的内壁极其敏感，当[npc2.namePos]的[npc2.cock]回应着穴缝的紧密包裹时，[npc.name]也能感受到每一次颤动和抽搐。"
						+ "[npc.Her]的[npc.cock]进入[npc2.namePos]的穴缝之后，跟对方[npc2.cock+]抵在一起，两根以相似的方式抽动颤抖着。",
				(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))!=SexPace.SUB_RESISTING
					?"[npc2.name]发出一声充满欲望的[npc2.moan]，贴近了[npc.name]，温柔地顶送着，还想继续将[npc2.cock+]送入对方的穴缝中，而在此过程中[npc.name]也进到了[npc2.hers]更深处。"
					:"")
			);
			String end = UtilText.returnStringAtRandom(
				"在最后几次抽插后，[npc.name]小心翼翼地退出，结束了双方的泄殖腔交合。"
						+ "[npc2.namePos]顽皮地将[npc2.cock]拍打在对方"
						+(performer.isVisiblyPregnant()
								?"孕肚上，"
								:"肚子上，")
						+"[npc.name]则继续跟[npc2.herHim]互相摩擦起来。",
				"[npc.name]叹了口气，又抽插几下后便向后退开，让[npc2.namePos][npc2.cock+]从自己的泄殖腔中滑出，然后继续与[npc2.herHim]磨蹭。"
			);
			return String.join("",start, middle1, middle2,end);
		}
	};
}
