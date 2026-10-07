package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.7
 * @version 0.3.3.10
 * @author Innoxia
 */
public class SALilayaSpecials {
	
	private static boolean isAmazonsSecretActive(GameCharacter targetedCharacter) {
		GameCharacter performingCharacter = Main.sex.getCharacterPerformingAction();
		if(targetedCharacter.hasStatusEffect("innoxia_amazons_secret")) {
			Set<GameCharacter> charactersContactingVagina = new HashSet<>(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaOrifice.VAGINA, SexAreaOrifice.VAGINA));
			charactersContactingVagina.addAll(Main.sex.getOngoingCharactersUsingAreas(performingCharacter, SexAreaPenetration.CLIT, SexAreaPenetration.CLIT));

			return !charactersContactingVagina.isEmpty();
		}
		
		return false;
	}
	
	// Demand pull out
	public static final SexAction PARTNER_DEMAND_PULL_OUT = new SexAction(
			SexActionType.SPEECH_WITH_ALTERNATIVE,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				return "提醒退开";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return "检查避孕套";
			}
			return "提醒外射";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return (isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
						|| (Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
								&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()))
					&& Main.sex.getCharacterTargetedForSexAction(this).getArousal() >= ArousalLevel.FOUR_PASSIONATE.getMinimumValue()
					&& !Main.sex.getCharactersRequestingPullout().keySet().contains(Main.sex.getCharacterPerformingAction())
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "[npc.name]发出迷乱的呻吟和淫荡的喊叫，尝试把[npc2.namePos]从[npc.her]的[npc.pussy]边推开，"
							+ "显然是不想让[npc2.herHim]以这种姿势高潮，原因就是喝下的那瓶“亚马逊人的秘术”带来的效果。";
					
				} else {
					return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
							+"[npc.speech(趁着还没高潮赶紧拔出来！我知道你喝了‘亚马逊人的秘术’，我可<b>不想</b>怀孕！)]";
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "[npc.name]发出迷乱的呻吟和淫荡的喊叫，尝试把[npc2.namePos]从[npc.her]的[npc.pussy]边推开，"
							+ "显然是不想让[npc2.herHim]射在里面。";
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(避孕套没问题吧？我可<b>不想</b>怀孕！)]";
					}
					return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
							+"[npc.speech(记住要拔出去！我可<b>不想</b>怀孕！)]";
				}
			}
				
		}
		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	public static final SexAction PARTNER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
					&& !isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "准备";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[npc2.name]马上就要高潮了。做好准备。";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					return "[npc.Name]发出着轻声[npc.moan]，催动着氛围，准备迎接[npc2.name]高潮的到来。";
				case DOM_NORMAL:
					return "[npc.Name]发出一声[npc.a_moan+]，催动着情趣，准备迎接[npc2.name]高潮的到来。";
				case DOM_ROUGH:
					return "[npc.Name]发出一声[npc.a_moan+]，催动着情趣，准备迎接[npc2.name]高潮的到来。";
				case SUB_EAGER:
					return "[npc.Name]发出一声[npc.a_moan+]，催动着情趣，准备迎接[npc2.name]高潮的到来。";
				case SUB_NORMAL:
					return "[npc.Name]发出一声[npc.a_moan+]，催动着情趣，准备迎接[npc2.name]高潮的到来。";
				case SUB_RESISTING:
					return "[npc.Name]发出一声[npc.a_moan+]，努力想趁着[npc2.name]还没高潮，赶紧从身边退开。";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ASK_FOR_PULL_OUT = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				return "要求退开";
			}
			if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
				return "复查避孕套";
			}
			return "要求外射";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))
					|| (Main.sex.getOngoingCharactersUsingAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS).contains(Main.sex.getCharacterTargetedForSexAction(this))
							&& Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()))
					&& !Main.game.getNpc(Lilaya.class).isVisiblyPregnant()
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this))) {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "[npc.name]发出迷乱的呻吟和淫荡的喊叫，尝试把[npc2.namePos]从[npc.her]的[npc.pussy]边推开，"
							+ "显然是不想让[npc2.herHim]以这种姿势高潮，原因就是喝下的那瓶“亚马逊人的秘术”带来的效果。";
					
				} else {
					return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
							+"[npc.speech(等，等一下！你就这样去了的话，我可能会怀孕的！)]";
				}
				
			} else {
				if(Main.sex.getCharacterPerformingAction().isSpeechMuffled()) {
					return "[npc.name]发出迷乱的呻吟和淫荡的喊叫，尝试把[npc2.namePos]从[npc.her]的[npc.pussy]边推开，"
							+ "显然是不想让[npc2.herHim]射在里面。";
					
				} else {
					if(Main.sex.getCharacterTargetedForSexAction(this).isWearingCondom()) {
						return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
								+"[npc.speech(避孕套，希望没有破掉！我可<b>不想</b>怀孕！)]";
					}
					return "在止不住的呻吟和淫荡的叫喊中，[npc.name]总算组织出一段语言，向着[npc2.name]喊道，"
							+ "[npc.speech(拔出来！我<b>不想</b>怀孕！)]";
				}
			}
		}

		@Override
		public void applyEffects() {
			Main.sex.getCharactersRequestingPullout().put(Main.sex.getCharacterPerformingAction(), null);
		}
	};
	
	// Furious stop sex
	/**
	 * This should no longer ever be seen, as it was replaced by a catch for the player's orgasm override. I left it here just in case...
	 */
	public static final SexAction PARTNER_FURIOUS_STOP_SEX = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this)) || !Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return "怀孕了？！";
			}
			return "内射了？！";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getNpc(Lilaya.class).hasStatusEffect(StatusEffect.PREGNANT_0)
					&& Main.game.getNpc(Lilaya.class).getFetishDesire(Fetish.FETISH_PREGNANCY).isNegative()
					&& Main.sex.getCharacterPerformingAction().equals(Main.game.getNpc(Lilaya.class));
		}

		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			if(isAmazonsSecretActive(Main.sex.getCharacterTargetedForSexAction(this)) || !Main.sex.getCharacterTargetedForSexAction(this).hasPenisIgnoreDildo()) {
				return "[npc.Name]感到一股暖流在她[lilaya.pussy+]深处扩散开来，一直进入了子宫之中。她一把推开了[npc2.name]，怒喝道，"
						+ "[npc.speechNoEffects(搞什么鬼？！我不是让你退开了吗！)]";
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.lilayaCondomBroke)) {
				return "[npc.Name]感觉到[npc2.namePos]的[npc2.cum]漏进了她[lilaya.pussy+]之中，忽然变了个人似的推开了[npc2.herHim]，哭喊起来，"
						+"[npc.speechNoEffects(避孕套破了！怎么可能！操！我会怀上的！)]";
			}
			return "[npc.Name]感到[npc2.namePos]的[npc2.cum]流进了她[lilaya.pussy+]之中，一把推开了[npc2.herHim]，怒喝道，"
					+ "[npc.speechNoEffects(搞什么鬼？！我告诉你要射在外面的！)]";
		}

		@Override
		public void applyEffects() {
		}

		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
