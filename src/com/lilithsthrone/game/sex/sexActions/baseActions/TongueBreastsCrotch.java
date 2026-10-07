package com.lilithsthrone.game.sex.sexActions.baseActions;

import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
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
 * @since 0.3.1
 * @version 0.3.1
 * @author Innoxia
 */
public class TongueBreastsCrotch {
	
	public static final SexAction KISS_BREAST_CROTCH = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchShape()==BreastShape.UDDERS) {
				return "亲吻腹乳";
			} else {
				return "亲吻胯乳";
			}
		}

		@Override
		public String getActionDescription() {
			return "在[npc2.namePos]裸露的[npc2.crotchBoobs]上留下一连串的亲吻。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch()
					&& Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]慢慢俯向[npc2.namePos]的下体，"
									+ "将[npc.her][npc.lips+]按在[npc2.her][npc2.breastSkin+]上，在[npc2.her][npc2.crotchBoobs+]上留下一连串轻吻。",

							"温柔地将[npc.her][npc.lips+]按在[npc2.namePos]下体，"
									+ "[npc.name]在[npc2.her]裸露[npc2.crotchBoobs+]上留下一串深情的亲吻。",

							"[npc.Name]温柔地亲吻[npc2.namePos]裸露的[npc2.crotchBoobs]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she][npc.lips+]抵着[npc2.her][npc2.crotchBoobsSkin+]"));
					break;
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地俯到[npc2.namePos]的下体上，"
									+ "贪婪地将[npc.her][npc.lips+]抵在[npc2.her][npc2.breastSkin+]上，开始在[npc2.her][npc2.crotchBoobs+]上留下一连串热情的吻。",

							"贪婪地将[npc.her][npc.lips+]按在[npc2.namePos]下体，"
									+ "[npc.name]在[npc2.her]裸露[npc2.crotchBoobs+]上留下一串热情的亲吻。",

							"[npc.Name]热情地亲吻[npc2.namePos]裸露的[npc2.crotchBoobs]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she]拼命地[npc.lips+]抵着[npc2.her][npc2.crotchBoobsSkin+]"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]贪婪地俯到[npc2.namePos]的下体上，"
									+ "粗暴地将[npc.her][npc.lips+]按在[npc2.her][npc2.breastSkin+]上，在[npc2.her][npc2.crotchBoobs+]上留下一连串重重地吻。",

							"贪婪地将[npc.her][npc.lips+]按在[npc2.namePos]下体，"
									+ "[npc.name]在[npc2.her]裸露[npc2.crotchBoobs+]上留下一串狂野的亲吻。",

							"[npc.Name]粗暴地亲吻[npc2.namePos]裸露的[npc2.crotchBoobs]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she]用力地用[npc.lips+]抵着[npc2.her][npc2.crotchBoobsSkin+]"));
					break;
				case SUB_EAGER:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地俯到[npc2.namePos]的下体上，"
									+ "贪婪地将[npc.her][npc.lips+]抵在[npc2.her][npc2.breastSkin+]上，开始在[npc2.her][npc2.crotchBoobs+]上留下一连串热情的吻。",

							"贪婪地将[npc.her][npc.lips+]按在[npc2.namePos]下体，"
									+ "[npc.name]在[npc2.her]裸露[npc2.crotchBoobs+]上留下一串热情的亲吻。",

							"[npc.Name]热情地亲吻[npc2.namePos]裸露的[npc2.crotchBoobs]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she]拼命地[npc.lips+]抵着[npc2.her][npc2.crotchBoobsSkin+]"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]俯到[npc2.namePos]的下体上，"
									+ "将[npc.her][npc.lips+]按在[npc2.her][npc2.breastSkin+]上，在[npc2.her][npc2.crotchBoobs+]上留下一连串轻吻。",

							"将[npc.her][npc.lips+]按在[npc2.namePos]下体，"
									+ "[npc.name]在[npc2.her]裸露[npc2.crotchBoobs+]上留下一串亲吻。",

							"[npc.Name]亲吻[npc2.namePos]裸露的[npc2.crotchBoobs]，"
									+"嗅着[npc2.her][npc2.scent+]，[npc.she][npc.lips+]抵着[npc2.her][npc2.crotchBoobsSkin+]"));
					break;
				default:
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]发出轻柔的[npc2.moan]作为回应，温柔地把[npc.namePos]的[npc.face]拉向自己的腹部，高声呼喊让对方继续做下去。",
	
								"[npc2.she]按住[npc2.her]的肚子，[npc2.namePos]的[npc2.lips]间飘出一声轻柔的[npc2.moan]。",
	
								"轻轻地将[npc2.her]的腹部向[npc.namePos]的[npc.face]推，[npc2.name]发出轻轻的[npc2.moan]，[npc2.she]恳求[npc.herHim]继续。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，急切地把[npc.namePos]的[npc.face]拉向[npc2.her]的腹部，高声呼喊着让[npc.name]继续。",
	
								"[npc2.namePos]热情地推搡着自己的腹部，那[npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]急切地将腹部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，粗暴地把[npc.namePos]的[npc.face]拉向[npc2.her]的腹部，高声命令[npc.name]继续。",
	
								"[npc2.she]粗鲁地推搡着[npc2.her]的腹部，[npc2.namePos][npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]有力地将腹部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，急切地把[npc.namePos]的[npc.face]拉向[npc2.her]的腹部，高声呼喊着让[npc.name]继续。",
	
								"[npc2.namePos]热情地推搡着自己的腹部，那[npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]急切地将腹部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"作为回应，[npc2.Name]发出一阵[npc2.a_moan+]，把[npc.namePos]的[npc.face]拉向[npc2.her]的腹部，高声呼喊着让[npc.name]继续。",
	
								"[npc2.she]推搡着[npc2.her]的腹部，[npc2.namePos][npc2.lips+]间飘出一声[npc2.moan]。",
	
								"[npc2.name]将腹部推向[npc.namePos]的[npc.face]，发出一阵[npc2.a_moan+]，[npc2.she]恳求[npc.name]继续。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]拼命地试图将[npc2.her]的[npc2.crotchBoobs]拉离[npc.namePos]的[npc.face]，"
										+ "[npc2.she]发出一阵[npc2.a_sob+]，恳求[npc.name]就这样放过[npc2.herHim]。",
	
								"[npc2.her]泪流满面，[npc2.name]拼命抵抗着[npc.Name]，"
										+ "[npc2.she][npc2.sobbing]得更大声，尝试从[npc.her]冷淡的[npc.lips]移开自己的[npc2.crotchBoobs]。",
	
								"悲痛地啜泣着，泪水从[npc2.namePos][npc2.eyes]里流出，"
										+ "[npc2.name]祈求[npc.name]放过自己，[npc2.she]疯狂的尝试移开[npc2.her]的[npc2.crotchBoobs]。"));
						break;
					default:
						break;
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
	};
}
