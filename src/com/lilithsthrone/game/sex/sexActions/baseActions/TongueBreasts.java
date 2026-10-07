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
 * @since 0.1.88
 * @version 0.3.1
 * @author Innoxia
 */
public class TongueBreasts {
	
	public static final SexAction KISS_BREAST = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).hasBreasts()) {
				return "亲吻乳房";
			} else {
				return "亲吻胸部";
			}
		}

		@Override
		public String getActionDescription() {
			return "在[npc2.namePos]裸露的[npc2.breasts]留下一连串的亲吻。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]慢慢俯向[npc2.namePos]的胸部，"
									+ "将[npc.her][npc.lips+]按在[npc2.her][npc2.breastSkin+]上，在[npc2.her][npc2.breast+]上留下一连串轻吻。",

							"将[npc.her][npc.lips+]温柔地按在[npc2.namePos]胸前，"
									+ "[npc.name]在[npc2.her]裸露[npc2.breasts+]上留下一串深情的亲吻。",

							"[npc.Name]温柔地亲吻[npc2.namePos]裸露的[npc2.breasts]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she][npc.lips+]抵着[npc2.her][npc2.skin+]"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地俯到[npc2.namePos]的胸上，"
									+ "贪婪地将[npc.her][npc.lips+]抵在[npc2.her][npc2.breastSkin+]上，开始在[npc2.her][npc2.breasts+]上留下一连串热情的吻。",

							"贪婪地将[npc.her][npc.lips+]按在[npc2.namePos]的胸口上，"
									+ "[npc.name]在[npc2.her]裸露[npc2.breasts+]上留下一串热情的亲吻。",

							"[npc.Name]热情地亲吻[npc2.namePos]裸露的[npc2.breasts]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she]拼命地[npc.lips+]抵着[npc2.her][npc2.skin+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]贪婪地俯向[npc2.namePos]的胸部，"
									+ "粗暴地将[npc.her][npc.lips+]按在[npc2.her][npc2.breastSkin+]上，在[npc2.her][npc2.breast+]上留下一连串重重地吻。",

							"贪婪地将[npc.her][npc.lips+]按在[npc2.namePos]的胸口上，"
									+ "[npc.name]在[npc2.her]裸露[npc2.breasts+]上留下一串粗暴的亲吻。",

							"[npc.Name]粗暴地亲吻[npc2.namePos]裸露的[npc2.breasts]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she]用力地用[npc.lips+]抵着[npc2.her][npc2.skin+]"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地俯到[npc2.namePos]的胸上，"
									+ "贪婪地将[npc.her][npc.lips+]抵在[npc2.her][npc2.breastSkin+]上，开始在[npc2.her][npc2.breasts+]上留下一连串热情的吻。",

							"贪婪地将[npc.her][npc.lips+]按在[npc2.namePos]的胸口上，"
									+ "[npc.name]在[npc2.her]裸露[npc2.breasts+]上留下一串热情的亲吻。",

							"[npc.Name]热情地亲吻[npc2.namePos]裸露的[npc2.breasts]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she]拼命地[npc.lips+]抵着[npc2.her][npc2.skin+]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]俯向[npc2.namePos]的胸部，"
									+ "将[npc.her][npc.lips+]按在[npc2.her][npc2.breastSkin+]上，在[npc2.her][npc2.breast+]上留下一连串吻。",

							"将[npc.her][npc.lips+]按在[npc2.namePos]的胸口上，"
									+ "[npc.name]在[npc2.her]裸露[npc2.breasts+]上留下一串亲吻。",

							"[npc.Name]亲吻[npc2.namePos]裸露的[npc2.breasts]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she][npc.lips+]抵着[npc2.her][npc2.skin+]"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出轻柔的[npc2.moan]作为回应，温柔地把[npc.namePos]的[npc.face]拉向自己的胸部，高声呼喊让对方继续做下去。",
	
								"[npc2.she]推出[npc2.her]的胸部，[npc2.namePos]的[npc2.lips]间飘出一声轻柔的[npc2.moan]。",
	
								"轻轻地将[npc2.her]的胸部向[npc.namePos]的[npc.face]推，[npc2.name]发出轻轻的[npc2.moan]，[npc2.she]恳求[npc.herHim]继续。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，急切地把[npc.namePos]的[npc.face]拉向[npc2.her]的胸部，高声呼喊着让[npc.name]继续。",
	
								"[npc2.she]热情地按着[npc2.her]的胸部，[npc2.namePos][npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]急切地将胸部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，粗暴地把[npc.namePos]的[npc.face]拉向[npc2.her]的胸部，高声命令[npc.name]继续。",
	
								"[npc2.she]粗暴地推出[npc2.her]的胸部，[npc2.namePos][npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]有力地将胸部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，急切地把[npc.namePos]的[npc.face]拉向[npc2.her]的胸部，高声呼喊着让[npc.name]继续。",
	
								"[npc2.she]热情地按着[npc2.her]的胸部，[npc2.namePos][npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]急切地将胸部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，把[npc.namePos]的[npc.face]拉向[npc2.her]的胸部，高声呼喊着让[npc.name]继续。",
	
								"[npc2.she]推出[npc2.her]的胸部，[npc2.namePos][npc2.lips+]间飘出一声[npc.A_moan+]。",
	
								"将[npc2.her]的胸部向[npc.namePos]的[npc.face]推，[npc2.name]发出了[npc2.a_moan+]，[npc2.she]恳求[npc.herHim]继续。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]拼命地试图将[npc2.her]的[npc2.breasts]拉离[npc.namePos]的[npc.face]，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，恳求[npc.name]就这样放过[npc2.herHim]。",
	
								"[npc2.her]泪流满面，[npc2.name]拼命抵抗着[npc.Name]，"
										+ "哭得更大声，[npc2.she]尝试从[npc.her]冷淡的[npc.lips]移开[npc2.her]的[npc2.breasts]。",
	
								"悲痛地啜泣着，泪水从[npc2.namePos][npc2.eyes]里流出，"
										+ "[npc2.name]祈求[npc.name]放过自己，[npc2.she]疯狂的尝试移开[npc2.her]的[npc2.breasts]。"));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
