package com.lilithsthrone.LolificationProject.Lexi.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.79
 * @version 0.4.8.5
 * @author Lexi
 */
public class FrenchKiss {

	public static final SexAction FRENCH_KISS_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isOrificeNonSelfOngoingAction(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.MOUTH)
					&& Main.sex.getCharacterPerformingAction().getTongueLengthValue() > 2;
		}

		@Override
		public String getActionTitle() {
			return "开始法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "将你的[npc.lips]贴上[npc2.namePos]的嘴，将[npc.her]的[npc.tongue+]滑入[npc2.her]的[npc2.mouth]。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.COWGIRL_REVERSE)
					|| Main.sex.getSexPositionSlot(Main.sex.getCharacterPerformingAction()).hasTag(SexSlotTag.SITTING_IN_LAP)) {

				if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(sink)]坐到[npc2.namePos]的[npc2.penis]上，当[npc.her]的[npc.asshole+]紧箍住[npc2.her]的[npc2.penisGirth] [npc2.penis+]时，[npc.she]发出一声[npc.a_moan+]。"
									+ " 随着[npc.her]的[npc.moan]声渐弱，[npc.name] [npc.verb(lean)]下身子，双手[npc.verb(grab)]住[npc2.namePos]的头，"
									+ " 然后将[npc.her]的[npc.lips+]贴上[npc2.hers]，[npc.verb(slither)]着把[npc.her]的[npc.tongue+]滑入[npc.her]的嘴里。",

							"带着一声[npc.a_moan+]，[npc.name] [npc.verb(slide)]坐到[npc2.namePos]的[npc2.penis+]上，随即俯身将自己压向[npc2.her]的[npc2.breasts]。"
									+ " 呼吸着[npc2.namePos]的[npc2.scent+]，[npc.name]把[npc.her]的[npc.lips+]贴上[npc2.hers]，开始[npc.verb(start)]激情地向[npc2.her]嘴中探入[npc.her]的舌头，"
									+ "在双舌交缠时品尝着[npc2.her]的唾液。",

							"[npc.Name] [npc.verb(let)]着让[npc2.namePos]的[npc2.penis+]在[npc.her]的[npc.asshole+]里滑得更深，[npc.she]同时[npc.verb(lean)]下身子，把[npc2.herHim]拉入一个绝望的舌吻。",

							"俯身向下，[npc.name] [npc.verb(let)]出一声[npc.a_moan+]，[npc.she]把[npc2.namePos]的[npc2.penis+]深埋进[npc.her]的[npc.asshole+]，"
									+ " 然后俯身将自己的[npc.lips+]贴上[npc2.hers]。"
									+ " 打算让这远超一场热烈的亲吻，[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips+]，将[npc2.her]的舌头挤到一旁，随后探入[npc2.her]的喉咙深处，使其鼓胀起来"
									+ " 而[npc.her]的[npc.tongue+]在[npc2.her]的喉咙里扭动着。"));

				} else if (Main.sex.getAllOngoingSexAreas(Main.sex.getCharacterPerformingAction(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(sink)]坐到[npc2.namePos]的[npc2.penis]上，当[npc.her]的[npc.vagina+]紧箍住[npc2.her]的[npc2.penisGirth] [npc2.penis+]时，[npc.she]发出一声[npc.a_moan+]。"
									+ " 随着[npc.her]的[npc.moan]声渐弱，[npc.name] [npc.verb(lean)]下身子，双手[npc.verb(grab)]住[npc2.namePos]的头，"
									+ " 然后将[npc.her]的[npc.lips+]贴上[npc2.hers]，[npc.verb(slither)]着把[npc.her]的[npc.tongue+]滑入[npc.her]的嘴里。",

							"带着一声[npc.a_moan+]，[npc.name] [npc.verb(slide)]坐到[npc2.namePos]的[npc2.penis+]上，随即俯身将自己压向[npc2.her]的[npc2.breasts]。"
									+ " 呼吸着[npc2.namePos]的[npc2.scent+]，[npc.name]把[npc.her]的[npc.lips+]贴上[npc2.hers]，开始[npc.verb(start)]激情地向[npc2.her]嘴中探入[npc.her]的舌头，"
									+ "在双舌交缠时品尝着[npc2.her]的唾液。",

							"[npc.Name] [npc.verb(let)]着让[npc2.namePos]的[npc2.penis+]在[npc.her]的[npc.vagina+]里滑得更深，[npc.she]同时[npc.verb(lean)]下身子，把[npc2.herHim]拉入一个绝望的舌吻。",

							"俯身向下，[npc.name] [npc.verb(let)]出一声[npc.a_moan+]，[npc.she]把[npc2.namePos]的[npc2.penis+]深埋进[npc.her]的[npc.vagina+]，"
									+ " 然后俯身将自己的[npc.lips+]贴上[npc2.hers]。"
									+ " 打算让这远超一场热烈的亲吻，[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips+]，将[npc2.her]的舌头挤到一旁，随后探入[npc2.her]的喉咙深处，使其鼓胀起来"
									+ " 而[npc.her]的[npc.tongue+]在[npc2.her]的喉咙里扭动着。"));
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(lean)]下身子，将[npc.her]的[npc.lips+]贴上[npc2.namePos]的嘴，同时[npc.she]激情地将[npc.her]的[npc.tongue+]探入[npc2.her]的嘴里，"
									+ " 在双舌交缠时品尝着[npc2.her]的唾液。",

							"带着一丝微笑，[npc.name]俯身埋入[npc2.namePos]的胸前，用[npc.her]的[npc.tongue+]在它们上面舔过，留下一道湿漉漉的痕迹，直到抵达[npc2.her]的嘴唇。"
								+ "舔着[npc2.her]的嘴唇并让它们湿润淋漓，[npc.she]将[npc.her]的[npc.tongue+]滑入[npc2.her]的嘴里，自由地扭动着，品尝着[npc2.her]口中的甜蜜滋味。",

							"俯身向下，[npc.name] [npc.verb(let)]出一声[npc.a_moan+]，[npc.she]将[npc.her]的[npc.lips+]贴上[npc2.namePos]，开始[npc.verb(start)]热切地亲吻[npc2.her]，每一下心跳间，[npc.her]的[npc.tongue+]都更深地探索[npc2.her]口中的更多地方。 "
								+ "渴望更多，[npc.she]急切地把[npc.her]的[npc.tongue+]越推越深，直到[npc.she]触及[npc2.her]口腔的后端，开始[npc.verb(start)]滑入[npc2.her]的喉咙，一道小小的隆起在[npc2.her]的喉咙里浮现。"));
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(push)]将[npc2.her]的[npc2.tongue]推入[npc.namePos]的嘴里，"
											+ " 把[npc2.her]的[npc2.lips+]贴上[npc.hers]，[npc2.she]热切地[npc2.verb(return)]着[npc.namePos]表达的爱意。",

									" 随着一声[npc2.a_moan]，[npc2.name] [npc2.verb(lean)]向[npc.name]，"
											+ " 把[npc2.her]的[npc2.moans]闷进[npc.her]的嘴里，[npc2.she]急切地用[npc2.her]的[npc2.tongue]穿过[npc.her]的[npc.lips+]用力探入。",

									" [npc2.Moaning]出喜悦的呻吟，[npc2.name] [npc2.verb(lean)]向[npc.name]，"
											+ " 把[npc2.her]的[npc2.lips+]贴上[npc.nameHers]，[npc2.she]开心地将[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)]着想要抽身避开，[npc2.sobbing]着在不适中扭动，因为[npc.name] [npc.verb(force)]把[npc.her]的[npc.tongue+]强行塞入[npc2.herHim]的嘴里。",

									" [npc2.NamePos]的[npc2.sob+]被闷进[npc.namePos]的嘴里，[npc2.she] [npc2.verb(try)]着想抽身避开，在不适中扭动，因为[npc.name] [npc.verb(force)]把[npc.her]的[npc.tongue+]强行塞入[npc2.herHim]的嘴里。",

									" 随着一声[npc2.a_sob+]，[npc2.name] [npc2.verb(try)]着，徒劳无功地，想从[npc.name]身边抽身避开，"
											+ " 在不适中抗议扭动着，因为[npc.name] [npc.verb(force)]把[npc.her]的[npc.tongue]穿过[npc2.her]不情愿的[npc2.lips]探进[npc2.her]的喉咙深处，泪水在[npc2.her]的眼中泛起。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name]开心地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]深探入[npc.namePos]的嘴里，"
											+ " 急切地将[npc2.her]的[npc2.lips+]压回[npc.hers]上，[npc2.she]带着喜悦[npc2.moaning]，贪婪地[npc2.verb(return)]着她的爱意。",

									" 带着一声急切的[npc2.moan]，[npc2.name]绝望地[npc2.verb(grind)]压回[npc.name]身上，"
											+ " 把[npc2.her]的[npc2.moans]闷进[npc.her]的嘴里，[npc2.she]贪婪地用[npc2.her]的[npc2.tongue]穿过[npc.her]的[npc.lips+]用力探入。",

									" [npc2.Moaning]出喜悦的呻吟，[npc2.name]急切地把[npc2.her]的[npc2.lips+]用力贴上[npc.nameHers]，[npc2.she]开心地将[npc2.her]的[npc2.tongue]推入[npc.her]的嘴里。"));
							break;
					}
				}

			} else if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {// Face-to-wall penetration descriptions: TODO

				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伸手托住[npc2.namePos]的下巴，[npc.name] [npc.verb(tilt)]把[npc2.her]的头侧向一边，[npc.she]同时[npc.verb(lean)]前倾，"
										+ " 将[npc.her]的[npc.lips+]贴上[npc2.hers]，[npc.she]把[npc2.herHim]拉入一个轻柔的吻，"
										+ " 温柔地用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，让[npc2.her]品尝[npc.her]的唾液。",

								"[npc.Name] [npc.verb(lean)]前倾，贴上[npc2.namePos]的后背，呼吸着[npc2.her]的[npc2.scent+]。"
										+ " 举起手，[npc.she] [npc.verb(turn)]把[npc2.namePos]的头转向一边，温柔地[npc.verb(press)]将[npc.her]的[npc.lips+]贴上[npc2.hers]，"
										+ " 随即[npc.verb(ease)]将它们分开，为[npc.her]的[npc.tongue+]让出空间，轻柔地[npc.verb(stroke)]抚过[npc2.her]脸颊内侧，使其鼓胀起来。",

								"[npc.Name] [npc.verb(lean)]贴上[npc2.namePos]的后背，把[npc2.her]的头微微转向一侧，随即轻柔地把[npc2.herHim]拉入一个温柔的深吻，"
										+"双舌激烈交缠，在润湿的牵绊中缠绕。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伸手粗暴地抓住[npc2.namePos]的下巴，[npc.name] [npc.verb(yank)]猛地将[npc2.her]的头甩向一侧，"
										+ " 随即把[npc.her]的[npc.lips+]碾在[npc2.hers]上，[npc.she]将[npc2.herHim]拉入一个强势的深吻。"
										+ " 贪婪地用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，让[npc2.her]品尝[npc.her]的唾液。",


								"[npc.Name] [npc.verb(lean)]前倾，贴上[npc2.namePos]的后背，呼吸着[npc2.her]的[npc2.scent+]。"
										+ " 举起手，[npc.she]粗暴地[npc.verb(yank)]把[npc2.namePos]的头甩向一侧，随即用力[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.hers]。"
										+ " 随后[npc.verb(shove)]用舌头将它们挤开，为[npc.her]的[npc.tongue+]让出空间，用力[npc.verb(push)]把[npc2.her]的[npc2.tongue]抵在[npc2.her]的脸颊上，使其鼓胀起来。",


								"[npc.Name] [npc.verb(lean)]贴上[npc2.namePos]的后背，猛地将[npc2.her]的头甩向一侧，随即粗暴地[npc.verb(force)]将[npc.her]的[npc.tongue+]探入[npc2.her]的喉咙深处，"
										+ " 因这突如其来的侵入而鼓胀起来。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伸手托住[npc2.her]的下巴，[npc.name] [npc.verb(lean)]前倾，"
										+ " 急切地把[npc.her]的[npc.lips+]贴上[npc2.hers]，[npc.she]顺势将[npc2.herHim]拉入一个热烈的深吻，"
										+ " 双舌交缠。",

								"[npc.Name] [npc.verb(lean)]前倾，贴上[npc2.namePos]的后背，呼吸着[npc2.her]的[npc2.scent+]。"
										+ " 举起手，[npc.she] [npc.verb(turn)]把[npc2.namePos]的头转向一边，充满激情地[npc.verb(press)]将[npc.her]的[npc.lips+]贴上[npc2.hers]。"
										+ " 随即[npc.verb(slide)]用舌头将它们分开，为[npc.her]的[npc.tongue+]让出空间，有力地[npc.verb(stroke)]抚过[npc2.her]的脸颊内侧，使其鼓胀起来。",

								"[npc.Name] [npc.verb(lean)]贴上[npc2.namePos]的后背，把[npc2.her]的头微微转向一侧，随即急切地把[npc2.herHim]拉入一个热烈的深吻。"
										+"双舌激烈交缠，在混乱的交错中缠绕。"));
						break;
				}
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(push)]将[npc2.her]的[npc2.tongue]推入[npc.name]的嘴里，"
											+ " 把[npc2.her]的[npc2.lips+]用力贴上[npc.hers]，[npc2.she]热切地[npc2.verb(return)]着她的爱意。",

									" 随着一声[npc2.a_moan]，[npc2.name] [npc2.verb(lean)]靠回[npc.name]身上，"
											+ " 把[npc2.her]的[npc2.moans]闷进[npc.her]的嘴里，[npc2.she]急切地用[npc2.her]的[npc2.tongue]穿过[npc.her]的[npc.lips+]用力探入。",

									" [npc2.Moaning]出喜悦的呻吟，[npc2.name] [npc2.verb(lean)]靠回[npc.name]身上，"
											+ " 把[npc2.her]的[npc2.lips+]贴上[npc.hers]，[npc2.she]开心地将[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)]着想要抽身避开，[npc2.sobbing]着在不适中扭动，因为[npc.name] [npc.verb(force)]把[npc.her]的[npc.tongue+]强行塞入[npc2.herHim]的嘴里。",

									" [npc2.NamePos]的[npc2.sob+]被闷进[npc.namePos]的嘴里，[npc2.she] [npc2.verb(try)]着想抽身避开，在不适中扭动，因为[npc.name] [npc.verb(force)]把[npc.her]的[npc.tongue+]强行塞入[npc2.herHim]的嘴里。",

									" 随着一声[npc2.a_sob+]，[npc2.name] [npc2.verb(try)]着，徒劳无功地，想从[npc.name]身边抽身避开，"
											+ " 在不适中抗议扭动着，因为[npc.name] [npc.verb(force)]把[npc.her]的[npc.tongue]穿过[npc2.her]不情愿的[npc2.lips]探进[npc2.her]的喉咙深处，泪水在[npc2.her]的眼中泛起。"));
							break;
						default:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name]开心地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]深探入[npc.namePos]的嘴里，"
											+ " 急切地将[npc2.her]的[npc2.lips+]压回[npc.hers]上，[npc2.she]带着喜悦[npc2.moaning]，贪婪地[npc2.verb(return)]着她的爱意。",

									" 带着一声急切的[npc2.moan]，[npc2.name]绝望地[npc2.verb(grind)]压回[npc.name]身上，"
											+ " 把[npc2.her]的[npc2.moans]闷进[npc.her]的嘴里，[npc2.she]贪婪地用[npc2.her]的[npc2.tongue]穿过[npc.her]的[npc.lips+]用力探入。",

									" [npc2.Moaning]出喜悦的呻吟，[npc2.name]急切地把[npc2.her]的[npc2.lips+]用力贴上[npc.nameHers]，[npc2.she]开心地将[npc2.her]的[npc2.tongue]推入[npc.her]的嘴里。"));
							break;
					}
				}

			} else { // Default penetration descriptions:

				switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"凝视着[npc2.namePos]的[npc2.eyes]，[npc.name] [npc.verb(lean)]前倾，将[npc.her]的[npc.lips+]贴上[npc2.hers]，[npc.she]把[npc2.herHim]拉入一个轻柔的吻。"
										+ " 温柔地用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，让[npc2.her]品尝[npc.her]的唾液。",

								"[npc.Name] [npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she]温柔地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.hers]。"
										+ " 随即[npc.verb(ease)]将它们分开，为[npc.her]的[npc.tongue+]让出空间，轻柔地[npc.verb(stroke)]抚过[npc2.her]的脸颊内侧，使其鼓胀起来。",

								"[npc.Name] [npc.verb(lean)]靠在[npc2.namePos]的[npc2.breasts+]上，把[npc.her]的头微微侧向一边，随即轻柔地把[npc2.herHim]拉入一个深情的深吻。"));
						break;
					case DOM_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"凝视着[npc2.namePos]的[npc2.eyes]，[npc.name] [npc.verb(lean)]前倾，将[npc.her]的[npc.lips+]贴上[npc2.hers]，[npc.she]把[npc2.herHim]拉入一个热烈的深吻。"
										+ " 双舌交缠。",

								"[npc.Name] [npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she]急切地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.hers]。"
										+ " 随即[npc.verb(slide)]用舌头将它们分开，为[npc.her]的[npc.tongue+]让出空间，有力地[npc.verb(stroke)]抚过[npc2.her]的脸颊内侧，使其鼓胀起来。",

								"[npc.Name] [npc.verb(lean)]靠在[npc2.namePos]的[npc2.breasts+]上，把[npc.her]的头微微侧向一边，随即急切地把[npc2.herHim]拉入一个热烈的深吻。"
										+"双舌激烈交缠，在混乱的交错中缠绕。"));

						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"凝视着[npc2.namePos]的[npc2.eyes]，[npc.name] [npc.verb(lean)]前倾，粗暴地把[npc.her]的[npc.lips+]碾在[npc2.hers]上，[npc.she]把[npc2.herHim]拉入一个强势的深吻。"
										+ " 贪婪地用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，让[npc2.her]品尝[npc.her]的唾液。",

								"[npc.Name] [npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she]粗暴地把[npc.her]的[npc.lips+]碾在[npc2.hers]上。"
										+ " 随即[npc.verb(shove)]用舌头将它们挤开，为[npc.her]的[npc.tongue+]让出空间，用力[npc.verb(push)]把[npc2.her]的[npc2.tongue]抵在[npc2.her]的脸颊上，使其鼓胀起来。",

								"[npc.Name] [npc.verb(lean)]靠在[npc2.namePos]的[npc2.breasts+]上，把[npc.her]的头侧向一边，随即粗暴地把[npc2.herHim]拉到一起，粗暴地亲吻，[npc.verb(force)]将[npc.her]的[npc.tongue+]探入[npc2.her]的喉咙深处，"
										+ " 因这突如其来的侵入而鼓胀起来。"));
						break;
					case SUB_EAGER:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"凝视着[npc2.namePos]的[npc2.eyes]，[npc.name] [npc.verb(lean)]前倾，急切地把[npc.her]的[npc.lips+]贴上[npc2.hers]，献上热烈的一吻。"
										+ " 热切地将[npc.her]的[npc.tongue+]滑入[npc2.her]美味的嘴里。",

								"[npc.Name] [npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she]急切地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.hers]，兴奋地[npc.verb(push)]把[npc.her]的[npc.tongue+]探入[npc2.her]的嘴里。",

								"[npc.Name] [npc.verb(lean)]靠在[npc2.namePos]的[npc2.breasts+]上，把[npc.her]的头微微侧向一边，随即在[npc2.her]的[npc2.lips+]上献上热烈的一吻，"
										+ " 绝望地[npc.verb(suck)]吮着[npc2.her]的[npc2.tongue]，[npc.verb(desire)]渴望在[npc.her]的嘴里感受它的触感。"));
						break;
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"凝视着[npc2.namePos]的[npc2.eyes]，[npc.name] [npc.verb(lean)]前倾，先把[npc.her]的[npc.lips+]贴上[npc2.hers]献上热烈的一吻，"
										+ "开心地[npc.verb(slide)]把[npc.her]的[npc.tongue+]滑入[npc2.her]的嘴里。",

								"[npc.Name] [npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she]开心地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.hers]。"
										+ " [npc.she]同时[npc.verb(slide)]把[npc.her]的[npc.tongue]滑入[npc2.her]的嘴里。",


								"[npc.Name] [npc.verb(lean)]靠在[npc2.namePos]的[npc2.breasts+]上，把[npc.her]的头微微侧向一边，随即在[npc2.her]的[npc2.lips+]上落下一个湿润的吻。"
										+ " 随后[npc.verb(push)]把[npc.her]的[npc.tongue+]推入[npc2.name]的嘴里。"));
						break;
					default:
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name]慢慢地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]推入[npc.namePos]的嘴里，"
											+ " 抬起手温柔地爱抚[npc.her]的[npc.face]，[npc2.she]开心地[npc2.verb(return)]着她的爱意。",

									" 带着赞许的低吟，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
											+ " 用[npc2.her]的[npc2.lips+]把[npc.her]的[npc.moans]声堵住，[npc2.she]温柔地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]推入[npc.her]的嘴里。",

									" [npc2.Moaning]出赞许的呻吟，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
											+ " 把[npc2.her]的[npc2.lips+]轻柔地贴上[npc.hers]，[npc2.she]慢慢地[npc2.verb(slide)]把[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
							break;
						case DOM_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(push)]把[npc2.her]的[npc2.tongue]推入[npc.namePos]的嘴里，"
											+ " 贪婪地把[npc2.her]的[npc2.lips+]迎上去与[npc.hers]相接，[npc2.she]急切地[npc2.verb(return)]着她的爱意。",

									" 带着赞许的[npc2.moan]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
											+ " 用[npc2.her]的[npc2.lips+]把[npc.her]的[npc.moans]声堵住，[npc2.she]急切地[npc2.verb(thrust)]把[npc2.her]的[npc2.tongue]探入[npc.her]的嘴里。",

									" [npc2.Moaning]出赞许的呻吟，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
											+ " 把[npc2.her]的[npc2.lips+]用力贴上[npc.hers]，[npc2.she]急切地[npc2.verb(slide)]把[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(force)]把[npc2.her]的[npc2.tongue]深深探入[npc.namePos]的嘴里，"
											+ " 粗暴地把[npc2.her]的[npc2.lips+]压在[npc.hers]上，[npc2.she]贪婪地[npc2.verb(return)]着她的爱意。",

									" 带着赞许的[npc2.moan]，[npc2.name]用力地[npc2.verb(grind)]贴向[npc.herHim]，"
											+ " 用[npc2.her]的[npc2.lips+]把[npc.her]的[npc.moans]声堵住，[npc2.she]粗暴地[npc2.verb(thrust)]把[npc2.her]的[npc2.tongue]深深探入[npc.her]的嘴里。",

									" [npc2.Moaning]出赞许的呻吟，[npc2.name] [npc2.verb(grind)]把[npc2.herself]贴向[npc.name]，"
											+ " 用力地把[npc2.her]的[npc2.lips+]压在[npc.hers]上，[npc2.she]粗暴地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]深深推入[npc.her]的嘴里。"));
							break;
						case SUB_EAGER:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name]开心地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]深深探入[npc.namePos]的嘴里，"
											+ " 急切地把[npc2.her]的[npc2.lips+]贴上[npc.hers]，欣喜地[npc2.moaning]着，[npc2.she]贪婪地[npc2.verb(return)]着她的爱意。",

									" 带着急切的[npc2.moan]，[npc2.name]绝望地[npc2.verb(grind)]贴向[npc.name]，"
											+ " 把[npc2.her]的[npc2.moans]声堵在[npc.her]的嘴里，[npc2.she]贪婪地[npc2.verb(thrust)]把[npc2.her]的[npc2.tongue]探过[npc.her]的[npc.lips+]。",

									" [npc2.Moaning]出欣喜的呻吟，[npc2.name]绝望地[npc2.verb(grind)]把[npc2.herself]贴向[npc.name]，"
											+ " 急切地把[npc2.her]的[npc2.lips+]用力贴上[npc.hers]，[npc2.she]开心地[npc2.verb(push)]把[npc2.her]的[npc2.tongue]推入[npc.her]的嘴里。"));
							break;
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(push)]把[npc2.her]的[npc2.tongue]推入[npc.namePos]的嘴里，"
											+ " 把[npc2.her]的[npc2.lips+]贴上[npc.hers]，[npc2.she]急切地[npc2.verb(return)]着她的爱意。",

									" 带着[npc2.a_moan]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
											+ " 把[npc2.her]的[npc2.moans]声堵在[npc.her]的嘴里，[npc2.she]急切地[npc2.verb(thrust)]把[npc2.her]的[npc2.tongue]探过[npc.her]的[npc.lips+]。",

									" [npc2.Moaning]出欣喜的呻吟，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
											+ " 把[npc2.her]的[npc2.lips+]贴上[npc.hers]，[npc2.she]开心地[npc2.verb(slide)]把[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									" [npc2.Name] [npc2.verb(try)]想要挣脱，[npc2.sobbing]着，不舒服地扭动身体，因为[npc.name] [npc.verb(force)]强行把[npc.herself]压在[npc2.herHim]身上。",

									" [npc2.NamePos]的[npc2.sob+]声被闷在[npc.namePos]的嘴里，[npc2.she] [npc2.verb(try)]想要挣脱，[npc.name] [npc.verb(force)]强行把[npc.herself]压在[npc2.herHim]身上时，她因不适而扭动。",

									" 伴随着[npc2.a_sob+]，[npc2.she]徒劳地[npc2.verb(try)]想从[npc.name]身边挣脱，"
											+ " 抗议着并因不适扭动，因为[npc.name] [npc.verb(force)]强行把[npc.her]的[npc.tongue]探过[npc2.her]不情愿的[npc2.lips]。"));
							break;
						default:
							break;
					}
				}

			}

			//TODO describe tongue modifiers

			return UtilText.nodeContentSB.toString();
		}
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
					return(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出一声急切的[npc2.moan]作为回应，"
									+ " 热情地把[npc2.herself]贴上[npc.name]，把[npc2.her]的[npc2.tongue]探入[npc.her]的嘴里，[npc2.she]急切地[npc2.verb(return)]着她的爱意。",

							" 带着欣喜的[npc2.moan]，[npc2.name]急切地[npc2.verb(lean)]靠向[npc.name]，把[npc2.her]的[npc2.tongue]探过[npc.her]的[npc.lips]，[npc2.she]绝望地pull把[npc.herHim]拉入一个疯狂的吻。",

							" 欣喜地发出[npc2.a_moan]，[npc2.name]press把[npc2.herself]贴上[npc.name]，"
									+ " 热情地回应[npc.her]的吻，[npc2.she] [npc2.verb(grind)]把[npc2.her]的[npc2.lips+]碾在[npc.her]的嘴上，同时把[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
				case SUB_RESISTING:
					return(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出[npc2.a_sob+]作为回应，使出浑身力气想[npc2.verb(push)]把[npc.name]推开，[npc2.she]因不适而[npc2.verb(writhe)]扭动。",

							" 伴随着[npc2.a_sob+]，[npc2.name] [npc2.verb(try)]想[npc2.verb(push)]把[npc.name]推开，徒劳地挣扎，[npc.she] [npc.verb(continue)]继续温柔地把[npc.her]的[npc.tongue+]钻过[npc2.her]不情愿的[npc2.lips]。",

							" [npc2.Name]绝望地[npc2.verb(try)]想要挣脱，挣扎着用力推拒，因为[npc.name] [npc.verb(continue)]继续侵犯[npc2.herHim]，"
									+ " 每当[npc.her]不受欢迎的[npc.tongue+]侵入时，都从[npc2.her]的[npc2.lips]之间挤出压抑的[npc2.sob]。"));
				case DOM_GENTLE:
					return(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出一声轻柔的[npc2.moan]作为回应，花了一点时间，在[npc.namePos]的[npc.lips+]上落下一连串轻柔的吻，然后轻轻地把[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。",

							" 带着轻柔的[npc2.moan]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，温柔地把[npc2.her]的[npc2.tongue]探过[npc.her]的[npc.lips+]，[npc2.she] [npc2.verb(return)]着他的爱意。",

							" 发出温柔的[npc2.moan]，[npc2.name] [npc2.verb(press)]把[npc2.herself]贴上[npc.name]，"
									+ " 把[npc2.her]的[npc2.moans]声闷在[npc.her]的[npc.lips+]上，[npc2.she]慢慢地把[npc2.her]的[npc2.tongue]滑入[npc.her]的嘴里。"));
				case DOM_ROUGH:
					return(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出一声低吼，粗暴地把[npc2.her]的[npc2.lips]碾在[npc.her]的嘴上，[npc2.she]猛烈地[npc2.verb(thrust)]把[npc2.her]的舌头深深探入[npc.her]的喉咙。",

							" 带着威胁性的低吼，[npc2.name]猛烈地[npc2.verb(grind)]贴向[npc.name]，"
									+ " 只一心想着[npc2.her]自己的快感，[npc2.she]贪婪地[npc2.verb(thrust)]把[npc2.her]的[npc2.tongue]深深探入[npc.her]的喉咙。",

							" [npc2.Name]对着[npc.namePos]的爱意露出得意的笑，随即发出一声狂暴的低吼，[npc2.she]用力地[npc2.verb(grind)]把[npc2.her]的[npc2.lips]碾在[npc.hers]上，"
									+ " 在[npc.her]的嘴里[npc2.moaning]着，同时贪婪地用舌头[npc2.verb(fuck)]抽插着[npc.her]的喉咙。"));
				default:
					return(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出[npc2.a_moan]作为回应，"
									+ " 把[npc2.herself]贴上[npc.name]，开心地把[npc2.her]的[npc2.tongue]推入[npc.her]的嘴里，[npc2.she]同时回应着他的爱意。",

							" 带着[npc2.a_moan]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，把[npc2.her]的[npc2.tongue]探过[npc.her]的[npc.lips]，[npc2.she] [npc2.verb(pull)]把[npc.herHim]拉入一个急切的吻。",

							" 发出[npc2.a_moan]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，回应[npc.her]的吻，[npc2.she]press把[npc2.her]的[npc2.lips+]贴上[npc.her]的嘴，"
									+ " [npc2.her]的[npc2.tongue] [npc2.verb(wiggle)]扭动着钻入[npc.her]的嘴里。"));
			}
		}
		return "";
	}

	public static final SexAction FRENCH_KISS_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {

		@Override
		public String getActionTitle() {
			return "温柔法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "温柔地与[npc2.name]法式舌吻。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]仍靠在[npc.namePos]背上，[npc.name]温柔地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即温柔地[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name]温柔地[npc.verb(lean)]靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，[npc.she] [npc.verb(glide)]把[npc.her]的[npc.tongue+]掠过[npc2.her]的[npc2.lips+]，"
							+ " 将它们润滑，随后[npc.verb(ease)]用[npc.her]的[npc.tongue+]将它们分开，温柔地[npc.verb(slide)]贴着[npc2.her]的脸颊内侧抚扫，使其鼓胀起来。",

						"[npc.Name]慢慢地[npc.verb(press)]贴向[npc2.namePos]的背部，[npc.she] [npc.verb(lean)]越过[npc2.her]的肩膀轻柔地与他法式舌吻时，轻轻把[npc2.herHim]按在墙上，"
							+ " 小心翼翼地把[npc.her]的[npc.tongue+]引去缠绕[npc2.hers]，深情地[npc.verb(squeeze)]紧裹着它。"));

			} else {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]温柔地把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即温柔地[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name]温柔地[npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she] [npc.verb(suck)]轻轻地吮吸着[npc2.her]的[npc2.tongue+]。",

						"[npc.Name]温柔地[npc.verb(press)]贴在[npc2.namePos]的[npc2.breasts+]上，[npc.she]轻柔地[npc.verb(push)]把[npc.her]的[npc.tongue+]探过[npc2.her]的[npc2.lips+]时，把[npc.her]的头稍微侧向一边。"));

			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction FRENCH_KISS_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return "与[npc2.herHim]法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "继续与[npc2.name]法式舌吻。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]仍靠在[npc.namePos]背上，[npc.name]急切地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即热烈地[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name]急切地[npc.verb(lean)]靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，[npc.she] [npc.verb(glide)]把[npc.her]的[npc.tongue+]掠过[npc2.her]的[npc2.lips+]，"
								+ " 将它们润滑，随后[npc.verb(ease)]用[npc.her]的[npc.tongue+]将它们分开，急切地[npc.verb(slide)]贴着[npc2.her]的脸颊内侧抚扫，使其鼓胀起来。",

						"[npc.Name]急切地[npc.verb(press)]贴向[npc2.namePos]的背部，把[npc2.herHim]按在墙上，[npc.she] [npc.verb(lean)]越过[npc2.her]的肩膀热烈地与[npc2.her]的[npc2.lips+]法式舌吻。"
								+ " 急切地把[npc.her]的[npc.tongue+]引去缠绕[npc2.hers]，用力地[npc.verb(squeeze)]紧裹着它。"));

			} else {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]急切地把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即温柔地[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name]急切地[npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she] [npc.verb(suck)]轻轻地吮吸着[npc2.her]的[npc2.tongue+]。",

						"[npc.Name]急切地[npc.verb(press)]贴在[npc2.namePos]的[npc2.breasts+]上，[npc.she]热烈地[npc.verb(push)]把[npc.her]的[npc.tongue+]探过[npc2.her]的[npc2.lips+]时，把[npc.her]的头稍微侧向一边。"));
			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction FRENCH_KISS_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return "粗暴法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地与[npc2.name]法式舌吻。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"粗暴地碾向[npc2.namePos]的背部，[npc.name]用力地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即贪婪地把[npc.her]的[npc.tongue]深深探入[npc2.her]的喉咙。",

						"[npc.Name]粗暴地[npc.verb(press)]贴向[npc2.namePos]的背部，呼吸着[npc2.her]的[npc2.scent]，随即[npc.verb(shove)]用[npc.her]的[npc.tongue+]撑开[npc2.her]的[npc2.lips]，[npc.verb(taste)]品尝[npc2.her]的唾液。",

						"[npc.Name]粗暴地[npc.verb(press)]贴向[npc2.namePos]的背部，"
								+ " [npc.she] [npc.verb(lean)]越过[npc2.her]的肩膀把[npc.her]的[npc.tongue+]深深探入[npc2.her]的喉咙时，用力地把[npc2.herHim]按在墙上。"));

			} else {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"用力地把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，[npc.name]贪婪地[npc.verb(thrust)]把[npc.her]的[npc.tongue]深深探入[npc2.her]的喉咙。",

						"[npc.Name]粗暴地[npc.verb(grind)]碾向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she]贪婪地用舌头[npc.verb(fuck)]抽插着[npc2.her]的嘴。",

						"[npc.Name]粗暴地[npc.verb(press)]贴在[npc2.namePos]的[npc2.breasts+]上，随即把[npc.her]的[npc.tongue]深深探入[npc2.her]的喉咙。"));

			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));

			return UtilText.nodeContentSB.toString();
		}

	};

	public static final SexAction FRENCH_KISS_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return "与[npc2.herHim]法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "继续与[npc2.name]法式舌吻。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]仍靠在[npc.namePos]背上，[npc.name] [npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name] [npc.verb(lean)]靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，[npc.she] [npc.verb(plant)]在[npc2.her]的[npc2.lips+]上落下一连串热烈的吻。"
								+ " 将它们润滑，随后[npc.verb(ease)]用[npc.her]的[npc.tongue+]将它们分开，[npc.verb(slide)]贴着[npc2.her]的脸颊内侧抚扫，使其鼓胀起来。",


						"[npc.Name] [npc.verb(press)]贴向[npc2.namePos]的背部，把[npc2.herHim]按在墙上，[npc.she] [npc.verb(lean)]越过[npc2.her]的肩膀热烈地与[npc2.her]的[npc2.lips+]法式舌吻。"
								+ " 把[npc.her]的[npc.tongue+]引去缠绕[npc2.hers]，[npc.verb(squeeze)]用力地紧裹着它。"));


			} else {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即[npc.name]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，[npc.verb(taste)]品尝[npc2.her]的唾液。",

						"[npc.Name] [npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she] [npc.verb(suck)]轻轻地吮吸着[npc2.her]的[npc2.tongue+]。",

						"[npc.Name] [npc.verb(press)]贴在[npc2.namePos]的[npc2.breasts+]上，[npc.she]热烈地[npc.verb(push)]把[npc.her]的[npc.tongue+]探过[npc2.her]的[npc2.lips+]时，把[npc.her]的头稍微侧向一边。"));

			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction FRENCH_KISS_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return "热切法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "热切地与[npc2.name]法式舌吻。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getSexPositionSlot(Main.sex.getCharacterTargetedForSexAction(this)).hasTag(SexSlotTag.FACE_TO_WALL)) {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc2.Name]仍靠在[npc.namePos]背上，[npc.name]急切地[npc.verb(press)]把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即热烈地[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name]急切地[npc.verb(lean)]靠在[npc2.namePos]的背上，呼吸着[npc2.her]的[npc2.scent]，[npc.she] [npc.verb(plant)]在[npc2.her]的[npc2.lips+]上落下一连串热烈的吻。"
								+ " 将它们润滑，随后[npc.verb(ease)]用[npc.her]的[npc.tongue+]将它们分开，急切地[npc.verb(slide)]贴着[npc2.her]的脸颊内侧抚扫，使其鼓胀起来。",


						"[npc.Name]急切地[npc.verb(press)]贴向[npc2.namePos]的背部，把[npc2.herHim]按在墙上，[npc.she] [npc.verb(lean)]越过[npc2.her]的肩膀热烈地与[npc2.her]的[npc2.lips+]法式舌吻。"
								+ " 急切地把[npc.her]的[npc.tongue+]引去缠绕[npc2.hers]，[npc.verb(squeeze)]用力地紧裹着它。"));

			} else {

				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"[npc.name]急切地把[npc.her]的[npc.lips+]贴上[npc2.nameHers]，随即温柔地[npc.verb(taste)]用[npc.her]的[npc.tongue+]分开[npc2.her]的[npc2.lips]，品尝[npc2.her]的唾液。",

						"[npc.Name]急切地[npc.verb(lean)]靠向[npc2.name]，呼吸着[npc2.her]的[npc2.scent+]，[npc.she] [npc.verb(suck)]轻轻地吮吸着[npc2.her]的[npc2.tongue+]。",

						"[npc.Name]急切地[npc.verb(press)]贴在[npc2.namePos]的[npc2.breasts+]上，[npc.she]热烈地[npc.verb(push)]把[npc.her]的[npc.tongue+]探过[npc2.her]的[npc2.lips+]时，把[npc.her]的头稍微侧向一边。"));

			}

			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction FRENCH_KISS_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {

		@Override
		public String getActionTitle() {
			return "抗拒法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "抗拒[npc2.namePos]的法式舌吻。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
					"[npc.name]感到泪水在[npc.her]的眼中涌起，[npc.name] [npc.verb(let)]发出了[npc.a_sob+]，想要推拒[npc2.name]，以回应[npc2.her]不受欢迎的舌头侵入。",

					"面对轮番[npc2.namePos]的[npc2.scent+]淹没[npc.her]的感官，[npc.name] [npc.verb(let)]发出一声压抑的[npc.sob]，"
							+ " 随即绝望地想把[npc2.herHim]从[npc.herHim]身上推开，徒劳地企图阻止[npc2.her]对她嘴的持续侵犯。",

					"[npc.Name]绝望地[npc.verb(try)]想把[npc2.name]推开，[npc.sobbing]着慌张地呻吟，因为[npc2.she] [npc2.verb(continue)]继续[npc2.verb(shove)]把[npc2.her]的[npc2.tongue+]塞进[npc.herHim]的嘴里。"));

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出一声安抚的[npc2.moan]作为回应，无视[npc.namePos]的抗议，[npc2.she]试着通过继续温柔地[npc.verb(slide)]把[npc.her]的[npc.tongue+]滑过[npc.lips+]来安抚[npc.herHim]。",

							" 带着轻柔的[npc2.moan]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
									+ " 无视[npc.her]的[npc.sobs]，[npc2.she]温柔但坚定地把[npc2.her]的[npc2.tongue]探过[npc.her]不情愿的[npc.lips]，进入[npc.her]的嘴里。",

							" 发出一声安抚的[npc2.moan]，[npc2.name] [npc2.verb(press)]把[npc2.herself]贴上[npc.name]，"
									+ " 把[npc.her]的[npc.sobs]声闷在[npc2.her]的[npc2.lips+]上，[npc2.she] [npc2.verb(continue)]继续与他法式舌吻。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]对[npc.her]的抗议发出一声愤怒的低吼，"
									+ " 粗暴地把[npc2.her]的[npc2.lips]碾在[npc.her]的嘴上，随即猛烈地把[npc2.her]的舌头深深探入[npc.her]的喉咙。",

							" 带着狂暴的低吼，[npc2.name]猛烈地把[npc2.herself]碾向[npc.name]，无视[npc.her]的[npc.sobs]，[npc2.she] [npc2.verb(thrust)]把[npc2.her]的[npc2.tongue]深深探入[npc.her]的喉咙。",

							" [npc2.Name]无视[npc.her]的抗议，伴随着一声粗暴的低吼，[npc2.she] [npc2.verb(continue)]继续狂暴地操着[npc.her]不情愿的喉咙。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							" [npc2.Name] [npc2.verb(let)]发出一声[npc2.a_moan]作为回应，无视[npc.namePos]的抗议，[npc2.she] [npc2.verb(continue)]继续在[npc.her]的[npc.lips+]上落下热烈的吻。",

							" 伴随着[npc2.a_moan+]，[npc2.name] [npc2.verb(lean)]靠向[npc.name]，"
									+ " 无视[npc.her]的[npc.sobs]，[npc2.she]坚定地把[npc2.her]的[npc2.tongue]探过[npc.her]不情愿的[npc.lips]，进入[npc.her]的嘴里。",

							" 发出[npc2.a_moan+]，[npc2.name] [npc2.verb(press)]把[npc2.herself]贴上[npc.name]，继续热烈地亲吻[npc.herHim]。"));
					break;
			}

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction FRENCH_KISS_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().getTongueLengthValue() > 2;
		}

		@Override
		public String getActionTitle() {
			return "停止法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "从[npc2.namePos]身边退开，停止与[npc2.herHim]法式舌吻。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]退开，粗鲁地把[npc2.name]从[npc.herHim]身边推开，[npc.she]同时把[npc.her]的[npc.tongue+]从[npc.her]的喉咙里抽出。",

							"[npc.Name]突然而粗鲁地[npc.verb(push)]把[npc2.name]从[npc.herHim]身边推开，[npc.verb(yank)]猛地从[npc2.her]的嘴里抽出[npc.her]的[npc.tongue+]。",

							"[npc.Name] [npc.verb(pull)]从[npc2.name]身边退开，随即粗鲁地把[npc2.herHim]从[npc.herHim]身边推开，结束了[npc.her]的法式舌吻。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"凝视着[npc2.namePos]的[npc2.eyes]，[npc.name] [npc.verb(grin)]咧嘴一笑，[npc.she] [npc.verb(pull)]退开，一缕口水仍连接着[npc.her]的[npc.tongue+]和[npc2.her]的嘴，你在此时结束[npc.her]的法式舌吻。",

							"[npc.Name]突然[npc.verb(pull)]退开，结束了[npc.her]的法式舌吻。",

							"[npc.Name] [npc.verb(pull)]从[npc2.name]身边退开，[npc.she]结束[npc.her]的法式舌吻时，把[npc.her]的[npc.tongue+]从[npc2.her]的嘴里缓缓抽出。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(breathe)]松了一口气，随即继续[npc2.sob]着在[npc.name]怀中挣扎，[npc2.she] [npc2.verb(try)]想挣脱[npc.her]的掌握。",

								" 伴随着[npc2.a_sob+]，[npc2.name]徒劳地[npc2.verb(try)]想[npc2.verb(push)]把[npc.name]推得更远，[npc.she] [npc.verb(hold)]牢牢按住[npc2.herHim]时，她一边抗议一边因不适而扭动。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								" [npc2.Name] [npc2.verb(let)]在[npc.name] [npc.verb(pull)]退开时发出一声小小的呜咽，显示出[npc2.her]渴望更多关注的迫切需求。",

								" 一声绝望的呜咽从[npc2.namePos]的[npc2.lips]之间逸出，暴露了[npc2.her]对[npc.namePos]更多关注的渴望。"));
						break;
				}
			}

			return UtilText.nodeContentSB.toString();
		}
	};

	public static final SexAction EXTREME_FRENCH_KISS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {

		@Override
		public String getActionTitle() {
			return "极致法式舌吻";
		}

		@Override
		public String getActionDescription() {
			return "将[npc.her]的[npc.tongue+]尽可能地推入[npc.her]的喉咙深处。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())!=SexPace.SUB_RESISTING
					&&Main.sex.getCharacterPerformingAction().getTongueLengthValue() >= TongueLength.THREE_EXTREMELY_LONG.getMedianValue();
		}

		@Override
		public String getDescription() {
			GameCharacter performer = Main.sex.getCharacterPerformingAction();
			GameCharacter target = Main.sex.getCharacterTargetedForSexAction(this);

			return UtilText.parse(performer, target,
					UtilText.returnStringAtRandom(
							"[npc.Name]发出一声急切的[npc.moan]，决定把[npc.her]的[npc.tongue+]越来越深地探入[npc2.her]的嘴里，直到[npc.she]抵达[npc2.her]喉咙的深处，但长度还绰绰有余，"
										+ " [npc.she]决定不停在这里，继续将[npc.her]的[npc.tongue+]向[npc2.her]体内更深之处探去，口水开始从[npc2.her]被填满的嘴边溢出。"
										+ " 当[npc2.she]因持续的窒息感开始挣扎时，[npc.she]用[npc.her]的[npc.hands]轻轻托住[npc2.her]的头，安抚[npc2.herHim]，将她牢牢按住，[npc.her]的[npc.tongue+]仍继续向[npc2.herHim]体内更深处探去。"
										+ " 尽可能长久地享受这一刻，[npc.she]继续在[npc2.her]的喉咙里扭动着[npc.her]的[npc.tongueSize] [npc.tongue+]，使其夸张地鼓胀起来，正合[npc.her]的心意。"
										+ " 在这番亲密深吻又过了片刻后，[npc.she]开始慢慢抽出[npc.her]的[npc.tongueSize] [npc.tongue+]，缓缓从[npc2.her]的喉咙里滑出，随后完全退出[npc2.her]的嘴，收回到[npc.her]自己口中。"
										+ " 给[npc2.herHim]片刻喘息的机会，[npc2.she]大口喘着气，[npc.she]俏皮地舔了舔[npc2.her]的嘴唇，然后继续[npc.her]的法式舌吻。"));

		}
	};

}