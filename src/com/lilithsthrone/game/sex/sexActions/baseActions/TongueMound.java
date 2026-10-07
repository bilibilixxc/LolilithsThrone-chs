package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class TongueMound {
	
	private static String getDescriptionFinisher(SexAction action, int variation) {
		if(!action.isTargetedCharacterInanimate()) {
			if(variation==1) {
				return "，[npc2.name]在[npc.herHim]身下发出一阵[npc2.moan+]。";
			}
			return "，[npc2.name]在[npc.herHim]身下发出一阵[npc2.moan+]。";
		}
		return "。";
	}
	
	public static final SexAction MOUND_SNOG = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return "亲吻耻丘(粗暴)";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地亲吻着[npc2.namePos]无性别的小丘。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
						"[npc.Name]急切地用[npc.her][npc.lips+]摩擦着[npc2.namePos]玩偶般空无一物的下体，"
								+ "激烈地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0),

						"将头埋入[npc2.namePos]胯间，[npc.Name]粗暴地用嘴唇摩擦着[npc2.her]无性别的小丘，"
								+ "激烈地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 1),

						"[npc.Name]将脸压在[npc2.namePos]的腹股沟上，开始用[npc.lips+]磨蹭着[npc2.her]无性别的小丘，"
								+ "粗暴地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "亲吻耻丘";
		}

		@Override
		public String getActionDescription() {
			return "热情地亲吻舔舐[npc2.namePos]无性别的小丘。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.her][npc.lips+]压向[npc2.namePos]玩偶般空无一物的下体，"
							+ "热情地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0),

					"[npc.Name]将头埋入[npc2.namePos]胯间，嘴激情四射地贴住无性别的小丘，"
							+ "激烈地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 1),

					"[npc.Name]将脸压在[npc2.namePos]的腹股沟上，开始用[npc.lips+]磨蹭着[npc2.her]无性别的小丘，"
							+ "急切地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction GENTLE_MOUND_KISSING = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return "亲吻耻丘";
		}

		@Override
		public String getActionDescription() {
			return "温柔地亲吻舔舐[npc2.namePos]无性别的小丘。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]温柔地将[npc.her][npc.lips+]压向[npc2.namePos]玩偶般空无一物的下体，"
							+ "在[npc2.her]敏感的小丘上亲下轻柔的吻"+getDescriptionFinisher(this, 0),

					"[npc.Name]将头埋入[npc2.namePos]胯间，嘴温柔地贴住无性别的小丘，"
							+ "激烈地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 1),

					"[npc.Name]将脸压在[npc2.namePos]的腹股沟上，开始温柔地用[npc.lips+]磨蹭着[npc2.her]无性别的小丘，"
							+ "温柔地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_SNOG_SUB_EAGER = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return "深吻耻丘";
		}

		@Override
		public String getActionDescription() {
			return "热情地亲吻着[npc2.namePos]无性别的小丘。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]急切地将[npc.her][npc.lips+]压向[npc2.namePos]玩偶般空无一物的下体，"
							+ "热情地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0),

					"[npc.Name]将头埋入[npc2.namePos]胯间，嘴激情四射地贴住无性别的小丘，"
							+ "激烈地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 1),

					"[npc.Name]将脸压在[npc2.namePos]的腹股沟上，开始用[npc.lips+]磨蹭着[npc2.her]无性别的小丘，"
							+ "急切地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0));
		}
	};
	
	public static final SexAction MOUND_KISSING_SUB_NORMAL = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "亲吻耻丘";
		}
	
		@Override
		public String getActionDescription() {
			return "温柔地亲吻舔舐[npc2.namePos]玩偶般空无一物的下体。";
		}
	
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getCharacterTargetedForSexAction(this).hasVagina()
					&& !Main.sex.getCharacterTargetedForSexAction(this).hasPenis();
		}
	
		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"[npc.Name]将[npc.her][npc.lips+]压向[npc2.namePos]玩偶般空无一物的下体，"
							+ "对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0),

					"[npc.Name]将头埋入[npc2.namePos]胯间，嘴贴住无性别的小丘，"
							+ "激烈地对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 1),

					"[npc.Name]将脸压在[npc2.namePos]的腹股沟上，开始用[npc.lips+]磨蹭着[npc2.her]无性别的小丘，"
							+ "对着[npc2.her]敏感的小丘亲吻舔舐"+getDescriptionFinisher(this, 0));
		}
	};
}
