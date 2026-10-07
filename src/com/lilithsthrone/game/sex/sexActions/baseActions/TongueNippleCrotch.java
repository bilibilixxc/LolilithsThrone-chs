package com.lilithsthrone.game.sex.sexActions.baseActions;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
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
 * @since 0.3.1
 * @version 0.3.7.8
 * @author Innoxia
 */
public class TongueNippleCrotch {
	
	private static String getBaseSucklingActionName(GameCharacter crotchBoobCharacter) {
		switch(crotchBoobCharacter.getNippleCrotchShape()) {
			case LIPS:
				if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
					return "吮吸乳唇";
				} else {
					return "亲吻乳唇";
				}
			case INVERTED:
			case NORMAL:
				if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
					return "吮吸乳头";
				} else {
					return "亲吻乳头";
				}
			case VAGINA:
				if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
					return "吮吸乳头穴";
				} else {
					return "亲吻乳头穴";
				}
		}
		return "";
	}

	private static String getBaseBreastFeedingActionName(GameCharacter crotchBoobCharacter) {
		if(crotchBoobCharacter.getBreastCrotchRawStoredMilkValue()>0) {
			return UtilText.parse(crotchBoobCharacter, "哺乳([npc.crotchBoobs])");
		}
		return UtilText.parse(crotchBoobCharacter, "[npc.crotchNipples]被吮吸");
	}
	
	public static final SexAction SUCKLE_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterTargetedForSexAction(this).hasBreastsCrotch();
		}
		@Override
		public boolean isPhysicallyPossible() {
			return true; // Need this to override detection of whether nipples are penetrable or not.
		}
		@Override
		public String getActionTitle() {
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
						return "吮吸胯乳乳头";
					} else {
						return "亲吻胯乳乳头";
					}
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
						return "吮吸胯乳乳头";
					} else {
						return "亲吻胯乳乳头";
					}
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
						return "吮吸胯乳乳头穴";
					} else {
						return "亲吻胯乳乳头穴";
					}
			}
			return "";
		}
		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("将你的[npc.lips]压在[npc2.namePos][npc2.crotchBoob+]上，开始亲吻[npc2.her]唇状的乳头。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("将你的[npc.lips]压在[npc2.namePos][npc2.crotchBoob+]上，开始吮吸[npc2.her][npc2.crotchNipple+]。");
					break;
				case VAGINA:
					sb.append("将你的[npc.lips]压在[npc2.namePos][npc2.crotchBoob+]上，开始亲吻[npc2.her]穴状的乳头。");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?"因为[npc2.sheIsFull]正在[style.colourMinorGood(哺乳期)]，你可以在动作时喝到[npc2.her][npc2.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			Map<SexPace, List<String>> descriptors = Util.newHashMapOfValues(
					new Value<>(SexPace.DOM_GENTLE, Util.newArrayListOfValues("轻轻地", "温柔地")),
					new Value<>(SexPace.SUB_NORMAL, Util.newArrayListOfValues("渴求地", "贪婪地")),
					new Value<>(SexPace.SUB_EAGER, Util.newArrayListOfValues("渴求地", "贪婪地")),
					new Value<>(SexPace.DOM_NORMAL, Util.newArrayListOfValues("渴求地", "贪婪地")),
					new Value<>(SexPace.DOM_ROUGH, Util.newArrayListOfValues("粗暴地", "粗鲁地")));
			
			List<String> desList = descriptors.get(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()));
			int index = Util.random.nextInt(desList.size());
			String[] desc = new String[] {desList.get(index), desList.get((index+1)%desList.size())};
			
			String oneOfBoobsDescriptor = Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRows()==0?"":"其中一个";
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]"+desc[0]+"贴向[npc2.namePos][npc2.crotchBoobs+]，然后用[npc.lips+]抵住[npc2.her]的乳唇"+desc[1]+"亲昵起来。",
							Util.capitaliseSentence(desc[0])+"将[npc.lips+]压住[npc2.namePos]其中一个[npc2.crotchBoobs+]，[npc.name]开始"+desc[1]+"亲昵起乳唇来。"));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append("就像真嘴一样，[npc2.namePos]嘴唇般的乳头分开，露出了喉咙似的开口，让[npc.name]把舌头伸进[npc2.her]的[npc2.crotchBoob]。");
					}
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]"+desc[0]+"将[npc.lips+]压住"+oneOfBoobsDescriptor+"[npc2.namePos][npc2.crotchBoobs+]，然后开始"+desc[1]+"吮亲[npc2.her][npc2.crotchNipple+]。",
							Util.capitaliseSentence(desc[0])+"将[npc.lips+]压住[npc2.namePos][npc2.crotchBoob]，[npc.name]开始"+desc[1]+"吮亲[npc2.her][npc2.crotchNipple+]。"));

					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" [npc.Name]感觉[npc2.namePos]的乳头中间张开，露出了腔穴，让[npc.her]可以把舌头伸进[npc2.namePos]的[npc2.crotchBoob]。");
					}
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]"+desc[0]+"贴向[npc2.namePos][npc2.crotchBoobs+]，然后用[npc.lips+]抵住[npc2.her]其中一个小穴般的乳头"+desc[1]+"亲昵起来。",
							Util.capitaliseSentence(desc[0])+"用[npc.lips+]压住"+oneOfBoobsDescriptor+"[npc2.namePos]的[npc2.crotchBoobs]，[npc.name]开始"+desc[1]+"吮亲[npc2.her]小穴般的乳头。"));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append("就像真阴道一样，[npc2.namePos]小穴般的乳头中间形成了腔穴，让[npc.name]可以把舌头伸进[npc2.her]的[npc2.crotchBoob]。");
					}
					break;
			}
			
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) { // Milk:
				switch(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchStoredMilk()) {
					case ZERO_NONE:
						break;
					case ONE_TRICKLE:
					case TWO_SMALL_AMOUNT:
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"没过多久，[npc2.namePos][npc2.milk+]就滴入了[npc.name]口中，",
								"液体很快就滴入了[npc.NamePos]的口中，[npc.she]尝到了[npc2.namePos][npc2.milk+]的味道，"));
						break;
					case FOUR_LARGE_AMOUNT:
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"没过多久，[npc2.namePos][npc2.milk+]就流入了[npc.name]口中，",
								"液体很快就流入了[npc.NamePos]的口中，[npc.she]尝到了[npc2.namePos][npc2.milk+]的味道，"));
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"没过多久，[npc2.namePos][npc2.milk+]就涌入了[npc.name]口中，",
								"液体很快就涌入了[npc.NamePos]的口中，[npc.she]尝到了[npc2.namePos][npc2.milk+]的味道，"));
						break;
				}

				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]不禁发出一声轻叹，以回应这种深深的满足感。",
									"[npc2.name]温柔地将[npc.her]的头按向自己[npc2.crotchBoob+]，柔和地鼓励[npc.herHim]继续吮吸[npc2.crotchNipple+]。"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]不禁发出[npc2.a_moan+]，以回应这种深深的满足感。",
									"[npc2.name]愉悦地将[npc.her]的头按向自己[npc2.crotchBoob+]，欣然地鼓励[npc.herHim]继续吮吸[npc2.crotchNipple+]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]不禁发出[npc2.a_moan+]，以回应这种深深的满足感。",
									"[npc2.name]粗暴地将[npc.her]的头按向自己[npc2.crotchBoob+]，欣然地鼓励[npc.herHim]继续吮吸[npc2.crotchNipple+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]拼命地尝试推开[npc.name]，不由得发出[npc2.a_sob+]。",
									"[npc2.name]绝望地试图将[npc.namePos]的头推离自己[npc2.crotchBoob+]，[npc2.she]恳求[npc.name]放过自己。"));
							break;
					}
					
				} else {
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]吞下液体，发出了一阵[npc.a_moan]。",
							"[npc.name]将液体吞下，发出了一阵愉悦的[npc.moan]。"));
				}
				
			} else { // No milk:
				if(!isTargetedCharacterInanimate()) {
					switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
						case DOM_GENTLE:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]挺起胸膛，情不自禁地发出快乐的轻叹，以回应[npc2.crotchNipple]被吸吮的愉悦感觉。",
									"[npc2.name]温柔地将[npc.namePos]的头按向自己[npc2.crotchBoob+]，柔和地鼓励[npc.herHim]继续吮吸[npc2.crotchNipple+]。"));
							break;
						case DOM_NORMAL:
						case SUB_EAGER:
						case SUB_NORMAL:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]挺起胸膛，情不自禁地发出[npc2.a_moan+]，以回应[npc2.crotchNipple]被吸吮的愉悦感。",
									"[npc2.name]愉悦地将[npc.namePos]的头按向自己[npc2.crotchBoob+]，欣然鼓励[npc.herHim]继续吮吸[npc2.crotchNipple+]。"));
							break;
						case DOM_ROUGH:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.name]挺起胸膛，情不自禁地发出[npc2.a_moan+]，以回应[npc2.crotchNipple]被吸吮的愉悦感。",
									"[npc2.name]粗暴地将[npc.namePos]的头强行按向自己[npc2.crotchBoob+]，命令[npc.herHim]继续吮吸[npc2.crotchNipple+]。"));
							break;
						case SUB_RESISTING:
							UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
									"[npc2.Name]拼命尝试推开[npc.name]，不由得发出一阵[npc2.a_sob+]。",
									"[npc2.name]拼命地想要将[npc.namePos]的头推离自己[npc2.crotchBoob+]，不断哀求着[npc.name]放过自己。"));
							break;
					}
				}
			}
		
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0) {
				float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawMilkStorageValue()/5));
				
				if(suckleAmount>Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()) {
					suckleAmount = Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue();
				}
				
				String rs = Main.sex.getCharacterPerformingAction().ingestFluid(
							Main.sex.getCharacterTargetedForSexAction(this),
							Main.sex.getCharacterTargetedForSexAction(this).getMilkCrotch(),
							SexAreaOrifice.MOUTH,
							suckleAmount);
				Main.sex.getCharacterTargetedForSexAction(this).incrementBreastCrotchStoredMilk(-suckleAmount);
				return rs;
			}
			return "";
		}
	};

	private static String getTargetedCharacterResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			String actionDescription = "";
			switch(Main.sex.getCharacterTargetedForSexAction(action).getNippleCrotchShape()) {
				case LIPS:
					actionDescription = UtilText.returnStringAtRandom(
							"与[npc2.her]的乳唇亲热",
							"亲吻[npc2.her]的乳唇");
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(action).getBreastCrotchRawStoredMilkValue()>0) {
						actionDescription = UtilText.returnStringAtRandom(
								"吸[npc2.her][npc2.crotchNipple+]",
								"吮吸[npc2.her]乳头中的[npc2.milk]");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"吮吸[npc2.her][npc2.crotchNipple+]",
								"吮吸亲吻[npc2.her]的[npc2.crotchNipple]");
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(action).isBreastCrotchFuckableNipplePenetration()) {
						actionDescription = UtilText.returnStringAtRandom(
								"舔弄[npc2.her]的乳头小穴",
								"用舌头抽插[npc2.her]的乳穴");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"舔[npc2.her]小穴般的乳穴",
								"亲吻舔舐[npc2.her]的乳穴");
					}
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]饥渴地将[npc2.crotchBoob+]推向[npc.namePos][npc.lips+]，"
									+ "发出[npc2.a_moan+]，鼓励[npc.name]继续"+actionDescription+"。", 
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]饥渴地将[npc2.crotchBoob+]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续"+actionDescription+"。",
							"[npc2.name]愉悦地[npc2.moaning]着，迫不及待地用[npc2.crotchBoob+]磨蹭[npc.namePos]的[npc.face]，"
									+ "急切地乞求[npc.name]继续"+actionDescription+"。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.crotchBoobs]从[npc.namePos]那讨厌的[npc.tongue]下缩回，"
									+ "[npc2.she]无力地挣扎着，发出一阵[npc2.a_sob+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然继续"+actionDescription+"。",
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地试图远离[npc.namePos]的[npc.tongue]，"
									+ "[npc.name]不断"+actionDescription+"，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]温柔地将[npc2.crotchBoob+]推向[npc.namePos][npc.lips+]，"
									+ "[npc2.she]发出一声轻柔的[npc2.moan]，鼓励[npc.name]继续"+actionDescription+"。",
							" 一声轻柔的[npc2.moan]从[npc2.namePos][npc2.lips+]间飘离，"
									+ "[npc2.she]温柔地将[npc2.crotchBoob+]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续"+actionDescription+"。",
							"[npc2.name]愉悦地[npc2.moaning]着，轻柔地用[npc2.crotchBoob+]磨蹭[npc.namePos]的[npc.face]，"
									+ "急切地乞求[npc.name]继续"+actionDescription+"。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]粗暴地将[npc2.crotchBoob+]推向[npc.namePos][npc.lips+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，命令[npc.name]继续"+actionDescription+"。",
							"[npc2.namePos][npc2.lips+]间爆发出一阵[npc2.A_moan+]，"
									+ "[npc2.she]粗鲁地将[npc2.crotchBoob+]压向[npc.namePos]的[npc.face]，命令[npc.Name]继续"+actionDescription+"。",
							"[npc2.name]愉悦地[npc2.moaning]着，激烈地用[npc2.crotchBoob+]磨蹭[npc.namePos]的[npc.face]，"
									+ "积极地命令[npc.name]继续"+actionDescription+"。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.Name]将[npc2.crotchBoob+]推向[npc.namePos][npc.lips+]，"
									+ "[npc2.she]发出[npc2.a_moan+]，鼓励[npc.name]继续"+actionDescription+"。",
							"[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，"
									+ "[npc2.she]将[npc2.crotchBoob]压向[npc.namePos]的[npc.face]，乞求[npc.Name]继续"+actionDescription+"。",
							"[npc2.name]愉悦地[npc2.moaning]着，开始用[npc2.crotchBoob+]磨蹭[npc.namePos]的[npc.face]，"
									+ "乞求[npc.name]继续"+actionDescription+"。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction SUCKLE_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this))+"(温柔)";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("温柔地调情并亲吻[npc2.namePos]的口状乳唇。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("温柔地亲吻吸吮[npc2.namePos]的乳头");
					break;
				case VAGINA:
					sb.append("温柔地亲吻舔舐[npc2.namePos]小穴状的乳穴。");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?"因为[npc2.sheIsFull]正在[style.colourMinorGood(哺乳期)]，你可以在动作时喝到[npc2.her][npc2.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]乳唇，发出一声沉闷的[npc.moan]。",
								"[npc.Name]发出一声沉闷的[npc.moan]，集中于温柔地与[npc2.namePos]嘴一样的乳唇亲热。",
								"[npc.Name]温柔地用[npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳唇，开始缓慢地亲吻亲热。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻者[npc2.her]嘴一样的乳唇，发出一阵沉闷的[npc.moan]，喝下流出的[npc2.milk]。",
								"[npc.Name]发出一阵沉闷的[npc.moan]，专注于温柔地与[npc2.namePos]嘴一样的乳唇亲热，喝下过程中流出的[npc2.milk]。",
								"[npc.Name]用[npc.her][npc.tongue+]尖温柔地在[npc2.namePos][npc2.crotchBoob+]上滑动，对准[npc2.her]的乳唇，"
										+ "，开始与之亲热并喝下从中流出的[npc2.milk]。"));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.Name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，发出一阵沉闷的[npc.moan]。",
								"[npc.Name]发出一阵沉闷的[npc.moan]，专注于温柔地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]。",
								"[npc.Name]温柔地将[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，然后慢慢地舔舐亲吻。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，喝下流出的[npc2.milk]，发出一声沉闷的[npc.moan]。",
								"随着一声沉闷的[npc.moan]，[npc.Name]专注于温柔地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]，喝下过程中流出的[npc2.milk]。",
								"[npc.Name]温柔地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，对准了它们，"
										+ "吮吸溢出的[npc2.milk]。"));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于温柔地"
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"舔舐":"亲吻舔舐")+"[npc2.namePos]的乳穴。",
								"[npc.Name]温柔地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳穴，开始缓慢地亲吻舔舐。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"温柔地用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻着[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]，同时喝下从中流出的[npc2.milk]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于温柔地"+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"口":"亲吻舔舐")
									+"[npc2.namePos]的乳穴，吮吸着溢出的[npc2.milk]。",
								"[npc.Name]温柔地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳穴，"
										+ "开始舔舐并喝下从中流出的[npc2.milk]。"));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {

		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("调情并亲吻[npc2.namePos]的口状乳唇。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("亲吻吸吮[npc2.namePos]的乳头");
					break;
				case VAGINA:
					sb.append("亲吻舔舐[npc2.namePos]小穴状的乳穴。");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?"因为[npc2.sheIsFull]正在[style.colourMinorGood(哺乳期)]，你可以在动作时喝到[npc2.her][npc2.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]乳唇，发出一声沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地与[npc2.namePos]嘴一般的乳唇亲热。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖划过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳唇，开始急切地亲吻亲热。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻者[npc2.her]嘴一样的乳唇，发出一阵沉闷的[npc.moan]，喝下流出的[npc2.milk]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地与[npc2.namePos]嘴一样的乳唇亲热，喝下其中流出的[npc2.milk]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳唇，"
										+ "，开始与之亲热并喝下从中流出的[npc2.milk]。"));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.Name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her][npc2.crotchNipple+]，开始急切地舔舐亲吻。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，喝下流出的[npc2.milk]，发出一声沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]，喝下从中流出的[npc2.milk]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her][npc2.crotchNipple+]，"
										+ "吮吸溢出的[npc2.milk]。"));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地"
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"舔舐":"亲吻舔舐")+"[npc2.namePos]小穴般的乳穴。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳穴，开始急切地亲吻舔舐。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.Name]亲吻着[npc2.her]的乳穴，喝下其中流出的[npc2.milk]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地"+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"口交":"亲吻舔舐")
										+"[npc2.namePos]的乳穴，吮吸着溢出的[npc2.milk]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳穴，"
										+ "开始舔舐并喝下其中流出的[npc2.milk]。"));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {

		@Override
		public String getActionTitle() {
			return getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this))+"(粗暴)";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("粗暴地亲吻亲热[npc2.namePos]的乳唇。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("粗暴地亲吻吸吮[npc2.namePos]的乳头");
					break;
				case VAGINA:
					sb.append("粗暴地亲吻舔舐[npc2.namePos]小穴状的乳穴。");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?"因为[npc2.sheIsFull]正在[style.colourMinorGood(哺乳期)]，你可以在动作时喝到[npc2.her][npc2.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]野蛮地用[npc.lips+]迎上[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]嘴唇，发出一声沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于粗暴地亲热[npc2.namePos]的乳唇。",
								"[npc.Name]粗暴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳唇，开始激烈地亲吻亲热。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]野蛮地用[npc.lips+]迎上[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]的乳唇，喝下流出的[npc2.milk]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于粗暴地亲热[npc2.namePos]嘴一样的乳唇，喝下其中流出的[npc2.milk]。",
								"[npc.Name]粗暴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳唇，"
										+ "开始亲热并喝下流出的[npc2.milk]。"));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]野蛮地用[npc.lips+]迎上[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于粗暴地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]。",
								"[npc.Name]粗暴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her][npc2.crotchNipple+]，开始激烈地舔舐亲吻。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]野蛮地用[npc.lips+]迎上[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，喝下流出的[npc2.milk]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于粗暴地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]，喝下从中流出的[npc2.milk]。",
								"[npc.Name]粗暴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her][npc2.crotchNipple+]，"
										+ "吮吸溢出的[npc2.milk]。"));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]野蛮地用[npc.lips+]迎上[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于粗暴地"
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"舔舐":"亲吻舔舐")+"[npc2.namePos]小穴般的乳穴。",
								"[npc.Name]粗暴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳穴，开始激烈地亲吻舔舐。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]野蛮地用[npc.lips+]迎上[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.Name]亲吻着[npc2.her]的乳穴，喝下其中流出的[npc2.milk]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于粗暴地"
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"舔舐":"亲吻舔舐")
										+"[npc2.namePos]的乳穴，吮吸着溢出的[npc2.milk]。",
								"[npc.Name]粗暴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳穴，"
										+ "开始舔舐并喝下其中流出的[npc2.milk]。"));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_SUB_RESISTING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗"+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("努力远离[npc2.namePos]的口状乳唇。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("努力远离[npc2.namePos]的乳头。");
					break;
				case VAGINA:
					sb.append("努力远离[npc2.namePos]小穴状的乳穴。");
					break;
			}
			return sb.toString();
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.crotchNipple+]温柔地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.lips]按回了自己[npc2.crotchNipple+]，"
									+ "完全无视了[npc.her]的挣扎，温柔地磨蹭着[npc.herHim]。",
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.crotchBoobs+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边将[npc2.crotchNipple+]压向[npc.her][npc.lips+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.crotchNipple+]粗暴地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，粗暴地贴着[npc.herHim]。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]猛烈地将[npc.her]的[npc.lips]按回了自己[npc2.crotchNipple+]，"
									+ "完全无视了[npc.her]的挣扎，粗暴地磨蹭着[npc.herHim]。",
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.crotchBoobs+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边粗暴地将[npc2.crotchNipple+]压向[npc.her][npc.lips+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.name]试图把[npc.face]移开，但[npc2.name]继续将[npc2.crotchNipple+]急切地压在[npc.namePos][npc.lips+]上，"
									+ "[npc2.name]牢牢地将[npc.herHim]固定在原位，紧紧贴着[npc.herHim]。",
							"伴随着一声[npc.a_sob+]，[npc.Name]试着推开[npc2.name]，但[npc2.name]迅速地将[npc.her]的[npc.lips]按回了自己[npc2.crotchNipple+]，"
									+ "完全无视了[npc.her]的挣扎，急切地磨蹭着[npc.herHim]。",
							"泪水开始涌上[npc.namePos]的[npc.eyes]，伴随着一声[npc.a_sob+]，[npc.she]试图把嘴从[npc2.namePos][npc2.crotchBoobs+]边挪开，"
									+ "但[npc2.name]完全无视[npc.her]的抗议，一边快速地改变体位，一边急切地将[npc2.crotchNipple+]压向[npc.her][npc.lips+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction SUCKLE_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {

		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this)));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("调情并亲吻[npc2.namePos]的口状乳唇。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("亲吻吸吮[npc2.namePos]的乳头");
					break;
				case VAGINA:
					sb.append("亲吻舔舐[npc2.namePos]小穴状的乳穴。");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?"因为[npc2.sheIsFull]正在[style.colourMinorGood(哺乳期)]，你可以在动作时喝到[npc2.her][npc2.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]嘴一样的乳唇，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于亲热[npc2.namePos]嘴一样的乳唇。",
								"[npc.Name]用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳唇，开始急切地亲吻亲热。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]嘴一样的乳唇，喝下流出的[npc2.milk]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于亲热[npc2.namePos]嘴一样的乳唇，喝下从中流出的[npc2.milk]。",
								"[npc.Name]用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳唇，"
										+ "，开始与之亲热并喝下从中流出的[npc2.milk]。"));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.Name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，发出一阵沉闷的[npc.moan]。",
								"伴随着一阵低沉的[npc.moan]，[npc.name]专心于舔吻着[npc2.namePos][npc2.crotchNipple+]。",
								"[npc.Name]温柔地用[npc.tongue+]尖，在[npc2.namePos][npc2.crotchBoob+]上滑动着，饥渴地舔吻着[npc2.crotchNipple+]。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，喝下流出的[npc2.milk]，发出一声沉闷的[npc.moan]。",
								"[npc.Name]聚精会神地舔吻着[npc2.namePos][npc2.crotchNipple+]，吮吸着溢出的[npc2.milk]，喉间传来伴随着吞咽声的呜咽。",
								"[npc.Name]灵巧地运用着[npc.tongue+]，包裹玩弄着[npc2.namePos][npc2.crotchBoob+]，接着开始进攻[npc2.namePos][npc2.crotchNipple+]，"
										+ "吮吸溢出的[npc2.milk]。"));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]。",
								"伴随着一阵低沉的[npc.moan]，[npc.name]专心于"
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"舔舐":"亲吻舔舐")+"[npc2.namePos]小穴般的乳穴。",
								"[npc.Name]灵巧地运用着[npc.tongue+]，包裹玩弄着[npc2.namePos][npc2.crotchBoob+]，接着开始进攻乳穴，饥渴地舔吻了起来。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"用[npc.lips+]亲吻[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻着[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]，同时喝下从中流出的[npc2.milk]。",
								"[npc.Name]喉间传来模糊地呜咽声，开始集中进攻"+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"用舌头抽插":"舔吻")
									+"[npc2.namePos]的乳穴，吮吸着溢出的[npc2.milk]。",
								"[npc.Name]灵巧地运用着[npc.tongue+]，包裹玩弄着[npc2.namePos][npc2.crotchBoob+]，接着开始进攻乳穴，"
										+ "开始舔舐并喝下从中流出的[npc2.milk]。"));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {

		@Override
		public String getActionTitle() {
			return getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this))+"(渴求)";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("饥渴地调情并亲吻[npc2.namePos]的口状唇乳。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("急切地亲吮着[npc2.namePos]的奶头。");
					break;
				case VAGINA:
					sb.append("饥渴地亲吻舔舐[npc2.namePos]小穴状的乳穴。");
					break;
			}
			sb.append((Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()>0
						?"因为[npc2.sheIsFull]正在[style.colourMinorGood(哺乳期)]，你可以在动作时喝到[npc2.her][npc2.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]乳唇，发出一声沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地与[npc2.namePos]嘴一般的乳唇亲热。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖划过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳唇，开始急切地亲吻亲热。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻者[npc2.her]嘴一样的乳唇，发出一阵沉闷的[npc.moan]，喝下流出的[npc2.milk]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地与[npc2.namePos]嘴一样的乳唇亲热，喝下其中流出的[npc2.milk]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，对准[npc2.her]的乳唇，"
										+ "，开始与之亲热并喝下从中流出的[npc2.milk]。"));
					}
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.Name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her][npc2.crotchNipple+]，开始急切地舔舐亲吻。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]吮吸亲吻[npc2.her][npc2.crotchNipple+]，喝下流出的[npc2.milk]，发出一声沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地亲吻舔舐[npc2.namePos][npc2.crotchNipple+]，喝下从中流出的[npc2.milk]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her][npc2.crotchNipple+]，"
										+ "吮吸溢出的[npc2.milk]。"));
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(this).getBreastCrotchRawStoredMilkValue()==0) {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地"
										+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"舔舐":"亲吻舔舐")+"[npc2.namePos]小穴般的乳穴。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳穴，开始急切地亲吻舔舐。"));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.she]渴求地用[npc.lips+]抵住[npc2.namePos][npc2.crotchBoob+]，"
										+ "[npc.name]亲吻着[npc2.her]的乳穴，发出一阵沉闷的[npc.moan]，同时喝下从中流出的[npc2.milk]。",
								"随着一阵沉闷的[npc.moan]，[npc.Name]专注于贪婪地"+(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()?"口交":"亲吻舔舐")
									+"[npc2.namePos]的乳穴，吮吸着溢出的[npc2.milk]。",
								"[npc.Name]饥渴地用[npc.her][npc.tongue+]尖滑过[npc2.namePos][npc2.crotchBoob+]，瞄准[npc2.her]的乳穴，"
										+ "开始舔舐并喝下从中流出的[npc2.milk]。"));
					}
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return SUCKLE_START.applyEffectsString();
		}
	};
	
	public static final SexAction SUCKLE_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE_CROTCH)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止"+getBaseSucklingActionName(Main.sex.getCharacterTargetedForSexAction(this));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					sb.append("从[npc2.namePos]嘴般的乳唇旁移开。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("远离[npc2.namePos]的乳头。");
					break;
				case VAGINA:
					sb.append("从[npc2.namePos]小穴般的乳穴旁移开。");
					break;
			}
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"最后再粗暴地舔了一下，[npc.name]把[npc.face]从[npc2.namePos][npc2.crotchNipple+]旁移开。",
							"最后再粗鲁地亲了一下[npc2.namePos][npc2.crotchNipple+]，[npc.name]把[npc.face]从[npc2.crotchBoobs+]旁移开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"最后再舔了一次[npc2.namePos][npc2.crotchNipple+]，[npc.name]把[npc.face]移开。",
							"在[npc2.namePos][npc2.crotchNipple+]上留下了一个湿吻，[npc.Name]把脸从[npc2.crotchBoobs+]处移开。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.name]继续挣扎着，[npc2.sobbing]并不舒服地扭动，[npc2.she]意识到[npc.Name]还没有完全和[npc2.herHim]结束。",
								"[npc2.name]意识到[npc.she]还没有完全放过自己，继续挣扎[npc2.sobbing]，"
										+ "[npc2.she]恳求[npc.name]放过自己，泪水如小溪般从[npc2.her]的[npc2.face]上流下。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc.Name]将[npc.tongue+]从[npc2.namePos][npc2.nipples+]挪开，[npc2.name]不情愿地发出一声[npc2.a_moan+]。",
								"[npc.Name]不再舔弄[npc2.namePos][npc2.nipples+]，[npc2.namePos][npc2.lips+]间飘出一阵[npc2.A_moan+]，暴露了[npc2.she]渴望得到[npc.namePos]的更多关注。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	
	public static final SexAction BREASTFEED_START = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getCharacterPerformingAction().hasBreastsCrotch();
		}
		@Override
		public String getActionTitle() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) {
				return "哺乳([npc.crotchBoobs])";
			}
			return "开始吮吸[npc.crotchNipples]。";
		}
		@Override
		public String getActionDescription() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) {
				return "将[npc2.namePos]的脸推向你的[npc.crotchBoobs]，让[npc2.herHim]开始喝你的[npc.milk]。";
			}
			switch(Main.sex.getCharacterTargetedForSexAction(this).getNippleCrotchShape()) {
				case LIPS:
					return "将[npc2.namePos]的脸推向你的[npc.crotchBoobs]，让[npc2.herHim]开始亲吻你的乳唇。";
				case INVERTED:
				case NORMAL:
					return "将[npc2.namePos]的脸推向你的[npc.crotchBoobs]，让[npc2.herHim]开始亲吻吮吸你的乳头。";
				case VAGINA:
					return "将[npc2.namePos]的脸推向你的[npc.crotchBoobs]，让[npc2.herHim]开始亲吻吮吸你小穴状的乳穴。";
			}
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			

			UtilText.nodeContentSB.setLength(0);
			Map<SexPace, List<String>> descriptors = Util.newHashMapOfValues(
					new Value<>(SexPace.DOM_GENTLE, Util.newArrayListOfValues("轻轻地", "温柔地")),
					new Value<>(SexPace.SUB_NORMAL, Util.newArrayListOfValues("渴求地", "贪婪地")),
					new Value<>(SexPace.SUB_EAGER, Util.newArrayListOfValues("渴求地", "贪婪地")),
					new Value<>(SexPace.DOM_NORMAL, Util.newArrayListOfValues("渴求地", "贪婪地")),
					new Value<>(SexPace.DOM_ROUGH, Util.newArrayListOfValues("粗暴地", "粗鲁地")));
			
			List<String> desList = descriptors.get(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction()));
			int index = Util.random.nextInt(desList.size());
			String[] desc = new String[] {desList.get(index), desList.get((index+1)%desList.size())};

			String oneOfBoobsDescriptor = Main.sex.getCharacterPerformingAction().getBreastCrotchRows()==0?"":"其中一个";
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"在抓住[npc2.namePos]的头后，[npc.name]"+desc[0]+"引导[npc2.her][npc2.lips+]向上贴合到[npc.her]那[npc.crotchBoobs+]，"
									+ "随后"+desc[1]+"让[npc2.herHim]吸吮亲吻起[npc.her]的口状乳唇。",
							"在抓住[npc2.namePos]的头后，[npc.name]"+desc[0]+"将[npc2.herHim]拥入[npc.her]那[[npc.crotchBoobs+]当中，随后"+desc[1]+"让[npc2.herHim]吸吮亲吻起[npc.her]的口状乳唇。"));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append("就像真嘴一样，[npc.namePos]嘴唇般的乳头分开，露出了喉咙似的开口，让[npc2.name]把舌头伸进[npc.her]的[npc.crotchBoob]。");
					}
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的头，[npc.name]"+desc[0]+"将之埋入自己[npc.crotchBoobs+]之一，随后"+desc[1]+"让[npc2.herHim]吸吮亲吻自己[npc.crotchNipple+]。",
							"[npc.name]抓住[npc2.namePos]的头，"+desc[0]+"将[npc2.her]按向自己[npc.crotchBoobs+]当中，随后"+desc[1]+"让[npc2.herHim]吸吮亲吻起[npc.crotchNipple+]。"));

					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append(" [npc2.Name]感觉[npc.namePos]的乳头中间张开，露出了腔穴，让[npc2.her]把舌头伸进[npc.namePos]的[npc.crotchBoob]。");
					}
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]抓住[npc2.namePos]的头，[npc.name]"+desc[0]+"将之埋入自己[npc.crotchBoobs+]之一，随后"+desc[1]+"让[npc2.herHim]吸吮亲吻自己的乳穴。",
							"在抓住[npc2.namePos]的头后，[npc.name]"+desc[0]+"将[npc2.her]拥入[npc.her]那[npc.crotchBoobs+]当中，随后"+desc[1]+"让[npc2.herHim]吸吮亲吻起[npc.her]那小穴般的乳穴。"));
					
					if(Main.sex.getCharacterTargetedForSexAction(this).isBreastCrotchFuckableNipplePenetration()) {
						UtilText.nodeContentSB.append("就像真阴道一样，[npc.namePos]小穴般的乳头分开，露出了腔穴，让[npc2.name]把舌头伸进[npc.her]的[npc.crotchBoob]。");
					}
					break;
			}

			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) { // Milk:
				switch (Main.sex.getCharacterPerformingAction().getBreastCrotchStoredMilk()) {
					case ZERO_NONE:
						break;
					case ONE_TRICKLE:
					case TWO_SMALL_AMOUNT:
					case THREE_DECENT_AMOUNT:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"没过多久，[npc.namePos][npc2.milk+]就滴入了[npc2.name]口中，",
								"液体很快就滴入了[npc2.NamePos]的口中，[npc2.she]尝到了[npc.namePos][npc.milk+]的味道，"));
						break;
					case FOUR_LARGE_AMOUNT:
					case FIVE_VERY_LARGE_DROOLING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"没过多久，[npc.namePos][npc2.milk+]就流入了[npc2.name]口中，",
								"液体很快就流入了[npc2.NamePos]的口中，[npc2.she]尝到了[npc.namePos][npc.milk+]的味道，"));
						break;
					case SIX_EXTREME_AMOUNT_DRIPPING:
					case SEVEN_MONSTROUS_AMOUNT_POURING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"没过多久，[npc.namePos][npc2.milk+]就涌入了[npc2.name]口中，",
								"[npc2.she]尝到了流入口中[npc.namePos][npc.milk+]的味道，"));
						break;
				}
				
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伴随着吞咽的声音，[npc2.she]叹息着，把脸埋进[npc.namePos][npc.crotchBoobs+]里继续享用了起来。",
								"想要品尝更多，[npc2.her]温柔地把[npc2.face]埋入[npc.namePos][npc.crotchBoob+]，轻轻地吮吸[npc.crotchNipple+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伴随着吞咽的声音，[npc2.she][npc2.moan]着，把脸埋进[npc.namePos][npc.crotchBoobs+]里继续享用了起来。",
								"想要品尝更多，[npc2.her]开心地把[npc2.face]埋入[npc.namePos][npc.crotchBoob+]，饥渴地吮吸[npc.crotchNipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伴随着吞咽的声音，[npc2.she][npc2.moan]着，粗暴地把脸埋进[npc.namePos][npc.crotchBoobs+]里贪婪地享用了起来。",
								"想要品尝更多，[npc2.her]用力地把[npc2.face]埋入[npc.namePos][npc.crotchBoob+]，粗暴地吮吸[npc.crotchNipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.she]拼命地想要把[npc.Name]推开，但起伏不断的情欲却迫使着[npc2.she]发出一阵一阵[npc2.a_moan+]。",
								"[npc2.she]绝望地想要把[npc.namePos][npc.crotchBoobs+]推开，请求[npc.namePos]放过自己。"));
						break;
				}
			
			} else { // No milk:
				String suckleDesc = "";
				switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
					case LIPS:
						suckleDesc = "亲吻";
						break;
					case INVERTED:
					case NORMAL:
						suckleDesc = "亲吻吸吮";
						break;
					case VAGINA:
						suckleDesc = "亲吻舔舐";
						break;
				}
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case DOM_GENTLE:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"伴随着声音轻到听不清的叹息，[npc2.she]把脸埋进[npc.namePos][npc.crotchBoobs+]里温柔地"+suckleDesc+"着[npc.crotchNipple+]。",
								"[npc2.she]温柔地把[npc2.face]靠在[npc.namePos][npc.crotchBoob+]上，轻轻地"+suckleDesc+"着[npc.crotchNipple+]。"));
						break;
					case DOM_NORMAL:
					case SUB_EAGER:
					case SUB_NORMAL:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.she]发出一声不成语调的[npc2.moan+]，把脸埋入[npc.namePos][npc.crotchBoobs+]，饥渴地"+suckleDesc+"着[npc.crotchNipple+]。",
								"开心地将[npc2.her]的[npc2.face]压向[npc.namePos][npc.crotchBoob+]，[npc2.she]迫切地"+suckleDesc+"[npc.her][npc.crotchNipple+]。"));
						break;
					case DOM_ROUGH:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.she]发出一声模糊的[npc2.moan+]，粗暴地把脸埋入[npc.namePos][npc.crotchBoobs+]间，用力地"+suckleDesc+"着[npc.crotchNipple+]。",
								"[npc2.her]粗暴地把脸压在[npc.namePos][npc.crotchBoob+]上，用力地"+suckleDesc+"着[npc.crotchNipple+]。"));
						break;
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.Name]拼命地想要把[npc.Name]推开，却被情欲控制着，不断发出[npc2.a_moan+]。",
								"[npc2.name]拼命地想要远离[npc.namePos]那[npc.crotchBoobs+]，于是[npc.herHim]不断地哀求[npc.namePos]能够放过自己。"));
						break;
				}
			}
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			if(Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0) {
				float suckleAmount = Math.max(5, Math.min(100, Main.sex.getCharacterPerformingAction().getBreastCrotchRawMilkStorageValue()/5));
				
				if(suckleAmount>Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()) {
					suckleAmount = Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue();
				}
				
				String rs = Main.sex.getCharacterTargetedForSexAction(this).ingestFluid(
						Main.sex.getCharacterPerformingAction(),
						Main.sex.getCharacterPerformingAction().getMilkCrotch(),
						SexAreaOrifice.MOUTH,
						suckleAmount);
				Main.sex.getCharacterPerformingAction().incrementBreastCrotchStoredMilk(-suckleAmount);
				return rs;
			}
			return "";
		}
	};
	

	private static String getTargetedCharacterReceivingResponse(SexAction action) {
		if(!action.isTargetedCharacterInanimate()) {
			String actionDescription = "";
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					actionDescription = UtilText.returnStringAtRandom(
							"与[npc.her]的口状唇乳缠绵",
							"亲吻[npc.her]的乳唇");
					break;
				case INVERTED:
				case NORMAL:
					if(Main.sex.getCharacterTargetedForSexAction(action).getBreastCrotchRawStoredMilkValue()>0) {
						actionDescription = UtilText.returnStringAtRandom(
								"吸[npc.her][npc.crotchNipple+]",
								"从[npc.her]的乳头中吮吸[npc.milk]");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"吸[npc.her][npc.crotchNipple+]",
								"又亲又吸[npc.her][npc.crotchNipple]");
					}
					break;
				case VAGINA:
					if(Main.sex.getCharacterTargetedForSexAction(action).isBreastCrotchFuckableNipplePenetration()) {
						actionDescription = UtilText.returnStringAtRandom(
								"舔弄[npc.her]的乳头小穴",
								"用舌头抽插[npc.her]的乳穴");
					} else {
						actionDescription = UtilText.returnStringAtRandom(
								"舔[npc.her]乳穴",
								"亲吻舔舐[npc2.her]的乳穴");
					}
					break;
			}
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(action))) {
				case SUB_EAGER:
				case DOM_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]贪婪地把[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，喉间发出模糊不清的[npc2.moan]，继续"+actionDescription+"。",
							"[npc2.name]饥渴地将[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，继续"+actionDescription+"，口中飘出一声低沉的[npc2.moan]。",
							"[npc2.name]愉悦地[npc2.moaning]着，急切地将[npc2.lips+]压向[npc.namePos]的[npc.crotchBoob]，然后贪婪地"+actionDescription+"。"));
					break;
				case SUB_RESISTING:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]无法将[npc2.face]从[npc.namePos]的[npc.crotchBoobs]边移开，[npc2.she]徒劳地挣扎着，发出[npc2.a_sob+]。",
							"[npc2.namePos][npc2.lips]间爆发出一阵[npc2.A_sob+]，[npc2.she]无力地尝试推开[npc.name]，"
									+ "即使对方全力抵抗，[npc.name]依然将自己[npc.crotchNipple+]压向[npc2.her][npc2.lips+]。",
							"[npc2.name]在痛苦中[npc2.Sobbing]着，徒劳地尝试远离[npc.namePos]的[npc.crotchBoobs]，"
									+ "[npc.name]将[npc.crotchNipple+]压向[npc2.her][npc2.lips+]，[npc2.she]奋力反抗着[npc.name]。"));
					break;
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]温柔地把[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，喉间发出模糊不清的[npc2.moan]，继续"+actionDescription+"。",
							"[npc2.name]温柔地用[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，[npc2.she]继续"+actionDescription+"，口中飘出一声低沉的[npc2.moan]。",
							"[npc2.name]愉悦地[npc2.moaning]着，温柔地把[npc2.lips+]压向[npc.namePos]的[npc.crotchBoob]，轻轻地继续"+actionDescription+"。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]粗暴地把[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，喉间发出模糊不清的[npc2.moan]，继续"+actionDescription+"。",
							"[npc2.name]粗暴地将[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，[npc2.she]继续"+actionDescription+"，口中飘出一声低沉的[npc2.moan]。",
							"[npc2.name]愉悦地[npc2.moaning]着，粗暴地将[npc2.lips+]压向[npc.namePos]的[npc.crotchBoob]，然后激烈地"+actionDescription+"。"));
					break;
				case SUB_NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc2.name]把[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，喉间发出模糊不清的[npc2.moan]，继续"+actionDescription+"。",
							"[npc2.name]将[npc2.lips+]贴向[npc.namePos][npc.crotchBoob+]，[npc2.she]继续"+actionDescription+"，口中飘出一声低沉的[npc2.moan]。",
							"[npc2.name]愉悦地[npc2.moaning]着，开始将[npc2.lips+]压向[npc.namePos]的[npc.crotchBoob]，然后"+actionDescription+"。"));
					break;
			}
		}
		return "";
	}
	
	public static final SexAction BREASTFEED_DOM_GENTLE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_GENTLE) {
		
		@Override
		public String getActionTitle() {
			return getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction())+"(温柔)";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("温柔地把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]轻吻玩弄它们。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("温柔地把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]舔吸它们。");
					break;
				case VAGINA:
					sb.append("温柔地把乳穴推向[npc2.namePos]的嘴，让[npc2.herHim]舔弄它们。");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?"因为你正处于[style.colourMinorGood(哺乳期)]，你可以让[npc2.herHim]在该动作中喝到你的[pc.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵柔软的[npc.moan]，将乳唇推向[npc2.namePos][npc2.lips+]，利用其对[npc2.herHim]的控制温柔地吻着。",
							"随着一阵[npc.a_moan+]，[npc.Name]温柔地将其乳唇压向[npc2.namePos]的嘴，全心全意地亲吻着[npc2.lips+]。",
							"[npc.Name]发出一阵柔软的[npc.moan]，利用其对[npc2.name]的控制用乳唇全心全意的吻着[npc2.her][npc2.lips]。"));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵柔软的[npc.moan]，温柔地将[npc.her][npc.crotchNipple+]推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]温柔地将其[npc.crotchNipple+]压向[npc2.namePos]的嘴。",
							"[npc.Name]发出一阵柔软的[npc.moan]，温柔地用[npc.her][npc.crotchNipple+]摩擦[npc2.namePos][npc2.lips]。"));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵柔软的[npc.moan]，温柔地将[npc.her]的乳穴推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]温柔地将其乳穴压向[npc2.namePos]的嘴。",
							"[npc.Name]发出一阵柔软的[npc.moan]，温柔地用[npc.her]的乳穴摩擦[npc2.namePos][npc2.lips]。"));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_DOM_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻玩弄它们。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻舔舐它们。");
					break;
				case VAGINA:
					sb.append("把乳穴推向[npc2.namePos]的嘴，让[npc2.herHim]舔弄它们。");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?"因为你正处于[style.colourMinorGood(哺乳期)]，你可以让[npc2.herHim]在该动作中喝到你的[pc.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，将乳唇推向[npc2.namePos][npc2.lips+]，利用其对[npc2.herHim]的控制热情地吻着。",
							"随着一阵[npc.a_moan+]，[npc.Name]将其乳唇压向[npc2.namePos]的嘴，全心全意热情地亲吻着[npc2.lips+]。",
							"[npc.Name]发出一阵[npc.moan]，利用其对[npc2.name]的控制用乳唇全心全意热情地吻着[npc2.her][npc2.lips]。"));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，饥渴地将[npc.her][npc.crotchNipple+]推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]饥渴地将其[npc.crotchNipple+]压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]饥渴地把[npc.her][npc.crotchNipple+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，饥渴地将[npc.her]的乳穴推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]饥渴地将其乳穴压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]饥渴地把[npc.her]乳穴顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_DOM_ROUGH = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.DOM_ROUGH) {
		
		@Override
		public String getActionTitle() {
			return getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction())+"(粗暴)";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("粗暴地把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻玩弄它们。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("粗暴地把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻吮吸它们。");
					break;
				case VAGINA:
					sb.append("粗暴地把乳穴推向[npc2.namePos]的嘴，让[npc2.herHim]舔弄它们。");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?"因为你正处于[style.colourMinorGood(哺乳期)]，你可以让[npc2.herHim]在该动作中喝到你的[pc.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，将乳唇推向[npc2.namePos][npc2.lips+]，利用其对[npc2.herHim]的控制激烈地吻着。",
							"随着一阵[npc.a_moan+]，[npc.Name]粗暴地将其乳唇压向[npc2.namePos]的嘴，全心全意地亲吻着[npc2.lips+]。",
							"[npc.Name]发出一阵[npc.moan]，利用其对[npc2.name]的控制用乳唇全心全意激烈地吻着[npc2.her][npc2.lips]。"));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，粗暴地将[npc.her][npc.crotchNipple+]推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]粗暴地将其[npc.crotchNipple+]压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]粗暴地把[npc.her][npc.crotchNipple+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，粗暴地将[npc.her]的乳穴推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]粗暴地将其乳穴压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]粗暴地把[npc.her]乳穴顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_SUB_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public String getActionTitle() {
			return "抵抗"+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			return "努力让你[npc.nipples+]远离[npc2.namePos]的嘴。";
		}

		@Override
		public String getDescription() {

			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
				case DOM_GENTLE:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地尝试将[npc.her][npc.crotchBoobs]从[npc2.namePos][npc2.face]推开，"
									+ "发出一阵[npc.a_sob+]，[npc2.name]温柔地将其[npc2.tongue]滑过[npc.her][npc.crotchNipple+]。",
							"[npc.Name]发出一阵[npc.a_sob+]，急切地尝试将其[npc.crotchBoobs+]从[npc2.namePos][npc2.lips+]推开。"
									+ "[npc2.name]无视[npc.her]的抵抗，固定住[npc.Name]，在其[npc.crotchNipple+]上种下一串柔软的吻。",
							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将其[npc.crotchBoobs+]从[npc2.namePos][npc2.lips+]推开，但后者将[npc.herHim]牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，温柔地亲吻着[npc.crotchNipple+]。"));
					break;
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地尝试将[npc.her][npc.crotchBoobs]从[npc2.namePos][npc2.face]推开，"
									+ "发出一阵[npc.a_sob+]，[npc2.name]粗暴地将其[npc2.tongue]滑过[npc.her][npc.crotchNipple+]。",
							"[npc.Name]发出一阵[npc.a_sob+]，急切地尝试将其[npc.crotchBoobs+]从[npc2.namePos][npc2.lips+]推开。"
									+ "[npc2.name]无视[npc.her]的抵抗，激烈地固定住[npc.Name]，在其[npc.crotchNipple+]上种下一串湿润的吻。",
							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将其[npc.crotchBoobs+]从[npc2.namePos][npc2.lips+]推开，但后者将[npc.herHim]牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，粗暴地亲吻着[npc.crotchNipple+]。"));
					break;
				default: // DOM_NORMAL and in case anything goes wrong:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]急切地尝试将[npc.her][npc.crotchBoobs]从[npc2.namePos][npc2.face]推开，"
									+ "发出一阵[npc.a_sob+]，[npc2.name]贪婪地将其[npc2.tongue]滑过[npc.her][npc.crotchNipple+]。",
							"[npc.Name]发出一阵[npc.a_sob+]，急切地尝试将其[npc.crotchBoobs+]从[npc2.namePos][npc2.lips+]推开。"
									+ "[npc2.name]无视[npc.her]的抵抗，固定住[npc.Name]，在其[npc.crotchNipple+]上种下一串激情的吻。",
							"随着一阵[npc.a_sob+]，[npc.Name]拼命地尝试将其[npc.crotchBoobs+]从[npc2.namePos][npc2.lips+]推开，但后者将[npc.herHim]牢牢固定，"
									+ "但被[npc2.name]无视反抗摁住，饥渴地亲吻着[npc.crotchNipple+]。"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};
	
	public static final SexAction BREASTFEED_SUB_NORMAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_NORMAL) {
		
		@Override
		public String getActionTitle() {
			return Util.capitaliseSentence(getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction()));
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻玩弄它们。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻舔舐它们。");
					break;
				case VAGINA:
					sb.append("把乳穴推向[npc2.namePos]的嘴，让[npc2.herHim]舔弄它们。");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?"因为你正处于[style.colourMinorGood(哺乳期)]，你可以让[npc2.herHim]在该动作中喝到你的[pc.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，将乳唇推向[npc2.namePos][npc2.lips+]，利用其对[npc2.herHim]的控制热情地吻着。",
							"随着一阵[npc.a_moan+]，[npc.Name]将其乳唇压向[npc2.namePos]的嘴，全心全意热情地亲吻着[npc2.lips+]。",
							"[npc.Name]发出一阵[npc.moan]，利用其对[npc2.name]的控制用乳唇全心全意热情地吻着[npc2.her][npc2.lips]。"));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，将[npc.her][npc.crotchNipple+]推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]将其[npc.crotchNipple+]压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]把[npc.her][npc.crotchNipple+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，将[npc.her]的乳穴推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]将其乳穴压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]把[npc.her]乳穴顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_SUB_EAGER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL,
			SexPace.SUB_EAGER) {
		
		@Override
		public String getActionTitle() {
			return getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction())+"(渴求)";
		}

		@Override
		public String getActionDescription() {
			StringBuilder sb = new StringBuilder();
			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					sb.append("饥渴地把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻玩弄它们。");
					break;
				case INVERTED:
				case NORMAL:
					sb.append("饥渴地把乳唇推向[npc2.namePos]的嘴，让[npc2.herHim]亲吻舔舐它们。");
					break;
				case VAGINA:
					sb.append("饥渴地把乳穴推向[npc2.namePos]的嘴，让[npc2.herHim]舔弄它们。");
					break;
			}
			sb.append((Main.sex.getCharacterPerformingAction().getBreastCrotchRawStoredMilkValue()>0
						?"因为你正处于[style.colourMinorGood(哺乳期)]，你可以让[npc2.herHim]在该动作中喝到你的[pc.milk]。"
						:""));
			return sb.toString();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			switch(Main.sex.getCharacterPerformingAction().getNippleCrotchShape()) {
				case LIPS:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，将乳唇推向[npc2.namePos][npc2.lips+]，利用其对[npc2.herHim]的控制热情地吻着。",
							"随着一阵[npc.a_moan+]，[npc.Name]将其乳唇压向[npc2.namePos]的嘴，全心全意热情地亲吻着[npc2.lips+]。",
							"[npc.Name]发出一阵[npc.moan]，利用其对[npc2.name]的控制用乳唇全心全意热情地吻着[npc2.her][npc2.lips]。"));
					break;
				case INVERTED:
				case NORMAL:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，饥渴地将[npc.her][npc.crotchNipple+]推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]饥渴地将其[npc.crotchNipple+]压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]饥渴地把[npc.her][npc.crotchNipple+]顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
				case VAGINA:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]发出一阵[npc.moan]，饥渴地将[npc.her]的乳穴推向[npc2.namePos][npc2.lips+]。",
							"随着一阵[npc.a_moan+]，[npc.Name]饥渴地将其乳穴压向[npc2.namePos]的嘴。",
							"伴随着一阵[npc.a_moan+]，[npc.name]饥渴地把[npc.her]乳穴顶着[npc2.namePos]的[npc2.lips]摩擦。"));
					break;
			}
			
			UtilText.nodeContentSB.append(getTargetedCharacterReceivingResponse(this));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String applyEffectsString() {
			return BREASTFEED_START.applyEffectsString();
		}
	};
	
	public static final SexAction BREASTFEED_STOP = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE_CROTCH, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "停止"+getBaseBreastFeedingActionName(Main.sex.getCharacterPerformingAction());
		}

		@Override
		public String getActionDescription() {
			return "让[npc2.name]从[npc.crotchNipple+]里拔出来。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			switch(Main.sex.getSexPace(Main.sex.getCharacterPerformingAction())) {
				case DOM_ROUGH:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]粗暴地将[npc2.namePos]的头从自己[npc.crotchBoobs+]拉开，命令[npc2.herHim]停止亲吻[npc.crotchNipple]。",
							"[npc.Name]最后一次粗暴地将自己[npc.crotchNipple+]在[npc2.namePos][npc2.face]上摩擦，之后将其推开。"));
					break;
				default:
					UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
							"[npc.Name]将[npc2.namePos]的头从自己[npc.crotchBoobs+]推开，告诉[npc2.herHim]停止亲吻自己的[npc.crotchNipple]。",
							"[npc.Name]最后一次将自己[npc.crotchNipple+]压向[npc2.namePos][npc2.face]，之后将其推开。"));
					break;
			}

			if(!isTargetedCharacterInanimate()) {
				switch(Main.sex.getSexPace(Main.sex.getCharacterTargetedForSexAction(this))) {
					case SUB_RESISTING:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"泪水从[npc2.name][npc2.face]划过，意识到[npc.nameIsFull]没有在[npc2.her]身上完成，发出一阵[npc2.a_sob+]。",
								"[npc2.name]继续奋力反抗着[npc.Name]，[npc2.she]发出一阵[npc2.a_sob+]，乞求[npc.name]放过自己。"));
						break;
					default:
						UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
								"[npc2.namePos][npc2.lips+]间漏出一阵[npc2.A_moan+]，暴露了[npc2.she]想给予[npc.namePos][npc.crotchNipple+]更多口交的渴望。",
								"[npc.Name]走开了，但[npc2.name]还未满足，发出一阵[npc2.a_moan+]。"));
						break;
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};

}
