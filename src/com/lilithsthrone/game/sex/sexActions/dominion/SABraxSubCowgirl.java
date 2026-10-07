package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.68
 * @version 0.1.8
 * @author Innoxia
 */
public class SABraxSubCowgirl {

	// TODO: This class isn't being used anymore, but I need the facesitting actions for reference for when I add that position.
	
	// Player's actions:

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "说骚话";
		}

		@Override
		public String getActionDescription() {
			return "告诉布拉克斯他现在是属于你的母狗了。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"你坐在布拉克斯[npc.penis+]上，起起落落，淫叫不断，",
						"在呻吟之间，你朝着布拉克斯喊道，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"～啊！～太棒了！我爱你的鸡巴！",

							"操！~啊啊！~你下面真大！",

							"~嗯嗯！~我就喜欢骑上你这种可怜的贝塔！",

							"真乖！~啊啊！~让阿尔法给你坐一坐吧，小婊砸！")));
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"你坐在布拉克斯[npc.penis+]上，起起落落，淫叫不断，",
						"在呻吟之间，你朝着布拉克斯喊道，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"～啊！～太棒了！我爱你的鸡巴！",

							"操！~啊啊！~你下面真大！",

							"~嗯嗯！~我就喜欢骑上你这种可怜的贝塔！",

							"真乖！~啊啊！~让阿尔法给你坐一坐吧，小婊砸！")));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"你低头看向从你股间露出的布拉克斯的脸，说道，",
						"你带着一抹笑容，低头向布拉克斯说道，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~啊啊！~太好了，真是个乖巧的小贝塔！",

							"~嗯啊~继续！呜，就这样！",

							"真是个好孩子，布拉克斯！继续舔啊！不要停下来！",

							"~啊啊！~太爽了！再继续啊！")));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"你低头看向从你股间露出的布拉克斯的脸，说道，",
						"你带着一抹笑容，低头向布拉克斯说道，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"~啊啊！~太好了，真是个乖巧的小贝塔！",

							"~嗯啊~继续！呜，就这样！",

							"真是个好孩子，布拉克斯！继续舔啊！不要停下来！",

							"~啊啊！~太爽了！再继续啊！")));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"你低头看着布拉克斯在你身下顺从的姿态，居高临下地对他说，",
						"你带着一抹笑容，低头向布拉克斯说道，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parsePlayerSpeech(
							UtilText.returnStringAtRandom(
							"你真适合当个可爱的贝塔，不是吗布拉克斯？",

							"这就对了，当个乖乖的贝塔，听我的命令！",

							"真是个听话的小贝塔！",

							"你这小贝塔的鸡巴，很想让我坐上去吧？")));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE);
			}
		}
	};
	

	public static final SexAction PLAYER_COWGIRL_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "接吻";
		}

		@Override
		public String getActionDescription() {
			return "俯身吻向布拉克斯";
		}

		@Override
		public String getDescription() {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
				switch(Util.random.nextInt(5)){
					case 0:
						return "你向着布拉克斯[npc.penis+]坐下去，感受到那雄物深深进入你[pc.asshole+]时，你顿时冒出了快乐的尖叫。"
								+ "最后肉棒被整根吞入，你俯下身子，紧紧扣住了布拉克斯的脑袋，将他拉入了一场深沉的热吻。";
					case 1:
						return "你发出一声淫荡的呻吟，顺着布拉克斯[npc.penis+]便坐了下去，随后俯身在他的怀中，呼吸着那充满雄性荷尔蒙的气味，最后将嘴唇对了上去。";
					case 2:
						return "你先是让布拉克斯[npc.penis+]从你的屁股里稍稍抽出了一会儿，接着就俯下身子将他带入了一场无法抗拒的激吻。"
						+ "两人的舌头在互相的口中纠缠了一阵子，你坐了起来，重新沉下身子，没入了布拉克斯的大屌，"
						+ "一道涎水构成的银桥在二人的唇间搭起，不一会儿便断裂开来，落在了他毛绒绒的胸脯上。";
					case 3:
						return "看到你俯身向前，布拉克斯也迫不及待地用胳膊肘撑起身子，想要将嘴唇贴上去。你发出的呻吟声在二人口腔形成的空洞中闷响，布拉克斯那[npc.penis+]依然深深地埋入你[pc.asshole+]。";
					default:
						return "你俯下身去，完完整整地包裹住了布拉克斯[npc.penis+]，不免地发出一声低吟，接着就立刻将嘴唇压了上去，跟这尽显雄风的狼男激情热吻起来。";
				}

			} else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
				switch(Util.random.nextInt(5)){
					case 0:
						return "你向着布拉克斯[npc.penis+]坐下去，感受到那雄物深深没入你的"+Main.game.getPlayer().getVaginaName(true)+"之中，愉悦地细声尖叫起来。"
								+ "最后肉棒被整根吞入，你俯下身子，紧紧扣住了布拉克斯的脑袋，将他拉入了一场深沉的热吻。";
					case 1:
						return "你发出一声淫荡的呻吟，顺着布拉克斯[npc.penis+]便坐了下去，随后俯身在他的怀中，呼吸着那充满雄性荷尔蒙的气味，最后将嘴唇对了上去。";
					case 2:
						return "你先是让布拉克斯[npc.penis+]从你的蜜穴中稍稍抽出了一会儿，接着就俯下身子将他带入了一场无法抗拒的激吻。"
								+ "两人的舌头在互相的口中纠缠了一阵子，你坐了起来，重新沉下身子，没入了布拉克斯的大屌，"
								+ "一道涎水构成的银桥在二人的唇间搭起，不一会儿便断裂开来，落在了他毛绒绒的胸脯上。";
					case 3:
						return "看到你俯身向前，布拉克斯也迫不及待地用胳膊肘撑起身子，想要将嘴唇贴上去。你发出的呻吟声在二人口腔形成的空洞中闷响，布拉克斯那[npc.penis+]依然深深地埋入你的"
								+Main.game.getPlayer().getVaginaName(true)+"。";
					default:
						return "你俯下身去，完完整整地包裹住了布拉克斯[npc.penis+]，不免地发出一声低吟，接着就立刻将嘴唇压了上去，跟这尽显雄风的狼男激情热吻起来。";
				}

			} else {
				switch(Util.random.nextInt(5)){
					case 0:
						return "你俯下身子，紧紧扣住了布拉克斯的脑袋，将他拉入了一场深沉的热吻。你感觉得到"+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
								+"顶到了你的腰，便发出一阵嬉笑，在那狼男的口中回响着。";
					case 1:
						return "你咧嘴一笑，俯身趴在布拉克斯强壮的胸膛上，呼吸着他男性的气息，然后吻上了他的嘴唇。";
					case 2:
						return "你将布拉克斯拉进一个狂乱的吻中，感觉到他[npc.penis+]没用地顶在腰背上。"
						+ "你们唇齿交缠了许久后，你坐起来，发现一股唾液垂挂在唇间，稍纵即逝地滴到他毛茸茸的胸上。";
					case 3:
						return "你身体前倾，布拉克斯用胳膊肘支撑着自己，饥渴地与你唇齿相接。你们的呻吟声都被闷在彼此的嘴中"+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
								+"没用地顶着你的腰背。";
					default:
						return "你俯下身来，花了些时间欣赏布拉克斯的男性气质，发出呻吟，然后抵着他的唇热切地亲吻着。";
				}
			}
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PUSSY = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}

		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return CorruptionLevel.THREE_DIRTY;
			else
				return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return "清洁小穴";
			else
				return "颜面乘骑(小穴)";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA))
				return "你向前摇晃，坐上布拉克斯的脸，让他用舌头把你"+Main.game.getPlayer().getVaginaName(true)+"中的爱液舔干净。";
			else
				return "你向前摇晃，坐上布拉克斯的脸，让他用舌头舔你的"+Main.game.getPlayer().getVaginaName(true)+"。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()
					&& !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)
					&& Main.game.getPlayer().getVaginaType() != VaginaType.NONE;
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
				UtilText.nodeContentSB.setLength(0);
				
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
					UtilText.nodeContentSB.append("你已经受够布拉克斯在你[pc.asshole+]的舌头了，你站起并挪开直到你"
								+Main.game.getPlayer().getVaginaName(true)+"将身体压在他的脸上。");
					
				} else {
					UtilText.nodeContentSB.append("你决定让他用舌头舔你，挪动着将胯部压在他的脸上。");
				}
				
				UtilText.nodeContentSB.append("你感觉一团粘稠的精液从你的"+Main.game.getPlayer().getVaginaName(true)+"中滴下。你咯咯笑着，把黏糊糊的内射液抹在布拉克斯似狼的吻部上。"
						+ "你按住他扭动的头，把充满精液的阴部压在他的嘴上，他别无选择，只能乖乖给你清理。"
						+ "过了一会儿，布拉克斯发现反抗是徒劳的后停止扭动，将精液从被内射过"+Main.game.getPlayer().getVaginaName(false)+"中舔掉，你发出满足的叹息。");
				
				return UtilText.nodeContentSB.toString();
						
			} else {
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
					return "你已经受够布拉克斯在你[pc.asshole+]里的舌头了，站起并挪动直到你"
								+Main.game.getPlayer().getVaginaName(true)+"将身体压在他似狼的吻部上。"
							+ "你低下身，感受布拉克斯扁平的舌头贪婪地舔舐你敏感的褶皱，发出愉快的呻吟。当你"
								+Main.game.getPlayer().getVaginaName(true)+"想要休息时，布拉克斯却拼命舔舐你的下体。";
					
				} else {
					return "你决定让他用舌头舔你，挪动着将胯部压在他似狼的吻部上。"
							+ "你低下身，感受布拉克斯扁平的舌头贪婪地舔舐你敏感的褶皱，发出愉快的呻吟。当你"
								+Main.game.getPlayer().getVaginaName(true)+"想要休息时，布拉克斯却拼命舔舐你的下体。";
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_CUM_STUD);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
				}
				
			} else {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_ASS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public CorruptionLevel getCorruptionNeeded(){
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return CorruptionLevel.FOUR_LUSTFUL;
			else
				return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return "清洁屁股";
			else
				return "颜面骑乘(屁股)";
		}
		
		@Override
		public String getActionDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS))
				return "你向前摇晃，坐上布拉克斯的脸，让他用舌头把你[pc.asshole]中的精液舔干净。";
			else
				return "你向前摇晃，坐上布拉克斯的脸，让他用舌头舔你[pc.asshole+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
				UtilText.nodeContentSB.setLength(0);
				
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
					UtilText.nodeContentSB.append("你已经受够了在你"+Main.game.getPlayer().getVaginaName(true)+"中的舌头，你站起并挪动着将[pc.asshole]压在他脸上。");
					
				} else {
					UtilText.nodeContentSB.append("你决定让他用舌头舔你，挪动着将肛门压在他的脸上。");
				}
				
				UtilText.nodeContentSB.append("你感觉一大团粘稠的精液从你[pc.asshole+]中滴下。你咯咯笑着，把黏糊糊的内射液抹在布拉克斯似狼的吻部上。"
						+ "你按住他扭动的头，把充满精液的肛门压在他的嘴上，他别无选择，只能乖乖给你清理。"
						+ "过了一会儿，布拉克斯发现反抗是徒劳的后停止扭动，将精液从被内射过的[pc.asshole]中舔掉，你发出满足的叹息。");
				
				return UtilText.nodeContentSB.toString();
						
			} else {
				if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
					return "你已经受够了在你"+Main.game.getPlayer().getVaginaName(true)+"中的舌头，你站起并挪动着把[pc.asshole]压在他似狼的吻部上。"
							+ "你低下身，感受到布拉克斯扁平的舌头正顺从地舔舐你[pc.asshole+]，发出满意的呻吟。";
					
				} else {
					return "你决定让他用舌头舔你，挪动着将[pc.asshole]压在他似狼的吻部上。"
							+ "你低下身，感受到布拉克斯扁平的舌头正顺从地舔舐你[pc.asshole+]，发出满意的呻吟。";
				}
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING, Fetish.FETISH_CUM_STUD);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
				}
				
			} else {
				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_CUM_ADDICT);
				} else {
					return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ORAL_GIVING, Fetish.FETISH_ANAL_GIVING);
				}
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PASSIVE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "被动";
		}

		@Override
		public String getActionDescription() {
			return "坐着不动，任由布拉克斯干他的口活。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你将身体压在布拉克斯的嘴上，他似狼的舌头饥渴地舔舐你"+Main.game.getPlayer().getVaginaName(true)+"，令你发出轻柔的呻吟。",
					
					"你心不在焉地抚摸布拉克斯的头顶，当他舔舐你时高兴地呻吟着。",
					
					"布拉克斯把舌头深入你"+Main.game.getPlayer().getVaginaName(true)+"，令你发出满足的呻吟，你顽皮地抚摸着他毛茸茸的狼耳朵。",
						
					"你的身体继续压在布拉克斯脸上，他宽大的狼舌急切地插进你"+Main.game.getPlayer().getVaginaName(true)+"，令你发出色情的呻吟。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_PASSIVE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "被动";
		}

		@Override
		public String getActionDescription() {
			return "坐着不动，任由布拉克斯干他的口活。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你的身体继续压在布拉克斯脸上，他似狼的舌头顺从地舔着你[pc.asshole+]，令你发出轻柔的呻吟。",
					
					"你心不在焉地抚摸布拉克斯的头顶，在他舔舐你[pc.asshole+]时高兴地呻吟着。",
					
					"布拉克斯把舌头深入你[pc.asshole+]，你顽皮地抚摸着他毛茸茸的狼耳朵，发出满足的呻吟。",
						
					"你的身体继续压在布拉克斯脸上，他宽大的狼舌顺从地插进你[pc.asshole+]，令你发出色情的呻吟。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_GRIND = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "磨蹭";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地压向布拉克斯似狼的吻部。";
		}

		@Override
		public String getDescription() {
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				return UtilText.returnStringAtRandom(
						"感受到更大的快感，你瘫倒在布拉克斯的脸上，不再用你"+Main.game.getPlayer().getVaginaName(true)+"闷着他的嘴。"
								+ "当他开始哀鸣扭动时，你"+Main.game.getPlayer().getVaginaName(true)+"压向他似狼的吻部，舌头的深入令你发出狂乱的尖叫。",
						
						"你张开双腿，压倒在布拉克斯脸上，你"+Main.game.getPlayer().getVaginaName(true)+"紧紧地压在他嘴上，使他发出哀嚎，在你身下扭动。",
						
						"你伸手抓住他毛茸茸的狼耳朵，压倒在他脸上。布拉克斯被迫把舌头深入你"+Main.game.getPlayer().getVaginaName(true)+"，令你发出快乐的尖叫。",
							
						"你张开腿，沉向布拉克斯似狼的吻部，把你"+Main.game.getPlayer().getVaginaName(true)+"紧压在他热切的舌头上，发出狂乱的呻吟。");
			} else {
				return UtilText.returnStringAtRandom(
						"感受到更大的快感，你瘫倒在布拉克斯的脸上，用你"+Main.game.getPlayer().getAssName(true)+"彻底地闷着他的嘴。"
								+ "当他开始哀鸣扭动时，你压向他似狼的吻部，迫使他的舌头深入你[pc.asshole+]，你因此发出迷乱的尖叫。",
						
						"你张开双腿，压倒在布拉克斯脸上，你[pc.asshole+]紧紧地压在他嘴上，使他发出哀嚎，在你身下扭动。",
						
						"你伸手抓住他毛茸茸的狼耳朵，压倒在他脸上。布拉克斯被迫把舌头深入你[pc.asshole+]，令你发出快乐的尖叫。",
							
						"你张开腿，沉向布拉克斯似狼的吻部，把你[pc.asshole+]紧压在他热切的舌头上，发出狂乱的呻吟。");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FACESIT_GRIND_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "磨蹭";
		}

		@Override
		public String getActionDescription() {
			return "粗暴地压向布拉克斯似狼的吻部。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"感受到更大的快感，你瘫倒在布拉克斯的脸上，用你"+Main.game.getPlayer().getAssName(true)+"彻底地闷着他的嘴。"
							+ "当他开始哀鸣扭动时，你压向他似狼的吻部，迫使他的舌头深入你[pc.asshole+]，你因此发出迷乱的尖叫。",
					
					"你张开双腿，压倒在布拉克斯脸上，你[pc.asshole+]紧紧地压在他嘴上，使他发出哀嚎，在你身下扭动。",
					
					"你伸手抓住他毛茸茸的狼耳朵，压倒在他脸上。布拉克斯被迫把舌头深入你[pc.asshole+]，令你发出快乐的尖叫。",
						
					"你张开腿，沉向布拉克斯似狼的吻部，把你[pc.asshole+]紧压在他热切的舌头上，发出狂乱的呻吟。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT, Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};

	public static final SexAction PLAYER_COWGIRL_PENETRATE_PUSSY = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "贯穿小穴";
		}

		@Override
		public String getActionDescription() {
			return "使布拉克斯[npc.penis+]滑进你"+Main.game.getPlayer().getVaginaName(true)+"。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE);
		}
		
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty())
				UtilText.nodeContentSB.append("你站起身，使布拉克斯尖尖的狗屌从你[pc.asshole+]中抽出。");

			UtilText.nodeContentSB.append("你向下握紧他[npc.penis+]，撸动着使它直立，对准你"+Main.game.getPlayer().getVaginaName(true)+"。"
					+ "你毫不拖泥带水地压下，感觉到布拉克斯[npc.penis+]尖头的根部轻松地插入你。"
					+ "你迷乱地哀嚎着，沉下身子把拓宽中的阴茎吞进你的"+Main.game.getPlayer().getVaginaName(true)
					+"，最后，你带着喜悦的颤抖，感受肿胀的结紧贴你的阴唇的感觉。");

			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_PENETRATE_ASS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "贯穿屁股";
		}

		@Override
		public String getActionDescription() {
			return "使布拉克斯[npc.penis+]滑进你[pc.asshole+]。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE);
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty())
				UtilText.nodeContentSB.append("你站起身，使布拉克斯尖尖的狗屌从你"+Main.game.getPlayer().getVaginaName(true)+"中抽出。");

			UtilText.nodeContentSB.append("你向下握紧他[npc.penis+]，撸动着使它直立，对准你[pc.asshole+]。"
					+ "你毫不拖泥带水地压下，感觉到布拉克斯[npc.penis+]尖头的根部插入你。"
					+ "你迷乱地哀嚎着，沉下身子把拓宽中的阴茎吞进你的[pc.ass]，最后，你带着喜悦的颤抖，感受肿胀的结紧贴你[pc.asshole+]的感觉。");

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "停止插入";
		}

		@Override
		public String getActionDescription() {
			return"重新坐在布拉克斯的腹部上。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "你站了起来，并且伴随着布拉克斯失望的哀鸣，你让他的[npc.penis+]滑出你的"+Main.game.getPlayer().getVaginaName(true)+"。"
					+ "你快步走了几步，重新坐在小狼男那结实、长满皮毛的腹部上。";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_RIDING_ANAL = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "停止插入";
		}

		@Override
		public String getActionDescription() {
			return"重新坐在布拉克斯的腹部上。";
		}

		@Override
		public String getDescription() {
			return "你站了起来，并且伴随着布拉克斯失望的哀鸣，你让他的[npc.penis+]滑出你[pc.asshole+]。"
					+ "你快步走了几步，重新坐在小狼男那结实、长满皮毛的腹部上。";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_TONGUE_RIDING = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "停止插入";
		}

		@Override
		public String getActionDescription() {
			return"重新坐在布拉克斯的腹部上。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "你站了起来，并且伴随着布拉克斯失望的哀鸣，你让他的舌头滑出你的"+Main.game.getPlayer().getVaginaName(true)+"。"
					+ "你快步走了几步，重新坐在小狼男那结实、长满皮毛的腹部上。";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_STOP_TONGUE_RIDING_ANAL = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "停止插入";
		}

		@Override
		public String getActionDescription() {
			return"重新坐在布拉克斯的腹部上。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			return "你站了起来，并且伴随着布拉克斯失望的哀鸣，你让他的舌头滑出你[pc.asshole+]。"
					+ "你快步走了几步，重新坐在小狼男那结实、长满皮毛的腹部上。";
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_BRAXS_HANDS_ON_TITS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "布拉克斯在胸部";
		}

		@Override
		public String getActionDescription() {
			return "引导布拉克斯狼型的双手到你的胸部，鼓励他玩弄你的乳房。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
				return UtilText.returnStringAtRandom(
						"你伸出手，抓住布拉克斯狼型的双手，把它们带到你的胸前，发出一声淫荡的呻吟，他急切地开始摸索和挤压你柔软的"+Main.game.getPlayer().getBreastName(true)+"。",
						
						"将布拉克斯狼般的双手引导到你裸露的胸部上，你发出一声淫荡的呻吟，感觉到他手指底部坚硬的小垫子往下捏，挤压你的"+Main.game.getPlayer().getNippleName(true)+"。",
						
						"把你的胸部推向布拉克斯，你引导他的手到你裸露的乳房上，让他开始热切地揉捏你的"+Main.game.getPlayer().getNippleName(true)+"。",
							
						"将布拉克斯的手领到你裸露的"+Main.game.getPlayer().getBreastName(true)+"，他开始急切地摸索你的胸部，把手掌上坚硬的小垫子粗暴地扎进你柔软的乳肉中，你轻吟一声。");
			} else {
				return UtilText.returnStringAtRandom(
						"你伸出手，抓住布拉克斯狼型的双手，把它们带到你的胸前，发出一声淫荡的呻吟，他急切地开始摸索和挤压你的"+Main.game.getPlayer().getBreastName(true)+"。"
								+ "通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"。",
						
						"将布拉克斯狼型的双手引导到你的胸部上，你发出一声淫荡的呻吟，感觉到他的手指往下捏，挤压你的"+Main.game.getPlayer().getNippleName(true)+""
								+ "通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"。",
						
						"把你的胸部推向布拉克斯，你引导他的手到你的乳房上，让他开始摸索你的胸部"
								+ "通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"。",
							
						"将布拉克斯的手领到你的"+Main.game.getPlayer().getBreastName(true)+"，当他开始急切地摸索你的胸部，把手掌上坚硬的小垫子粗暴地扎进你柔软的乳肉中时，你轻吟一声。"
								+ "通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"。");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_SLOW = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "慢骑";
		}

		@Override
		public String getActionDescription() {
			return "缓慢地上下撸动布拉克斯硬起的肉棒。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你撑着腿，缓慢地起落身子，让布拉克斯[npc.penis+]进进出出你的"+Main.game.getPlayer().getVaginaName(true)+"，你俩都发出了快乐的呻吟和叹息。",
					
					"身体前倾，你用双手支撑身体的重量，慢慢地在布拉克斯[npc.penis+]上下滑动。"
							+ "当你俯下脸面对这个顺从的小狼孩时，你闻到了他那股阳刚的雄味，你咬着嘴唇，饥渴地呼吸着那令人陶醉的香味。",
					
					"你把双手放在膝盖上，用双腿在布拉克斯的阴茎上上下滑动，同时发出高亢的呻吟声，将自己刺穿在这咧着嘴笑的小狼男身上。",
						
					"你慢慢地把自己抬起来，然后又滑下来，让你的"+Main.game.getPlayer().getVaginaName(true)+"纳入布拉克斯[npc.penis+]，同时发出一声淫荡的呻吟。");
			
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_SLOW_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "慢骑";
		}

		@Override
		public String getActionDescription() {
			return "缓慢地上下撸动布拉克斯硬起的肉棒。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你撑着腿，缓慢地起落身体，让布拉克斯[npc.penis+]进进出出你[pc.asshole+]，你俩都发出了快乐的呻吟和叹息。",
					
					"身体前倾，你用双手支撑身体的重量，慢慢地在布拉克斯[npc.penis+]上下滑动。"
							+ "当你俯下脸面对这个顺从的小狼孩时，你闻到了他那股阳刚的雄味，你咬着嘴唇，饥渴地呼吸着那令人陶醉的香味。",
					
					"你把双手放在膝盖上，用双腿在布拉克斯的阴茎上上下滑动，同时发出高亢的呻吟声，将自己刺穿在这咧着嘴笑的小狼男身上。",
						
					"你慢慢地把自己抬起来，然后又滑下来，让你[pc.asshole+]纳入布拉克斯[npc.penis+]，同时发出一声淫荡的呻吟。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FAST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "快骑";
		}

		@Override
		public String getActionDescription() {
			return "快速地上下撸动布拉克斯[npc.penis+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你撑着腿，迅速起落身子，让布拉克斯[npc.penis+]进进出出你的"+Main.game.getPlayer().getVaginaName(true)
						+"当你在快乐中尖叫和呻吟。",
					
					"身体前倾，你用布拉克斯那福瑞，阳刚的胸部支撑身体的重量，快速地在布拉克斯[npc.penis+]上下滑动。"
							+ "当你的脸接近他的身体时，你嗅到了他那股阳刚的雄味，你咬着嘴唇，饥渴地呼吸着那令人陶醉的香味。",
					
					"把你的手放在你身后的地上作为支撑，你开始快速地在布拉克斯的"+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)+"上下摇晃"
						+"，当你不停地榨取着这咧着嘴笑的小狼男时，你发出颤抖着的高亢呻吟。",
						
					"你朝布拉克斯咧嘴一笑，开始亢奋地起起落落，反复用你的"+Main.game.getPlayer().getVaginaName(true)+"榨取布拉克斯的"+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+"你发出一连串高亢的呻吟。");
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FAST_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "快骑";
		}

		@Override
		public String getActionDescription() {
			return "快速地上下撸动布拉克斯[npc.penis+]。";
		}

		@Override
		public String getDescription() {
			return UtilText.returnStringAtRandom(
					"你撑着腿，快速地起落身子，让布拉克斯[npc.penis+]进进出出你[pc.asshole+]，你俩都发出了快乐的呻吟和叹息。",
					
					"身体前倾，你用布拉克斯那福瑞，阳刚的胸部支撑身体的重量，快速地在布拉克斯[npc.penis+]上下滑动。"
							+ "当你的脸接近他的身体时，你嗅到了他那股阳刚的雄味，你咬着嘴唇，饥渴地呼吸着那令人陶醉的香味。",
					
					"把你的手放在你身后的地上作为支撑，你开始快速地在布拉克斯的"+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)+"上下摇晃"
						+"，当你不停地榨取着这咧着嘴笑的小狼男时，你发出颤抖着的高亢呻吟。",
						
					"你朝布拉克斯咧嘴一笑，开始亢奋地起起落落，反复用你[pc.asshole+]榨取布拉克斯的"+Main.sex.getCharacterTargetedForSexAction(this).getPenisName(true)
						+"你发出一连串迷乱的呻吟。");
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_CUM_KISS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "浓精接吻";
		}

		@Override
		public String getActionDescription() {
			return "让布拉克斯尝尝他的精液多美味……";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.braxCumOnChest;
		}

		@Override
		public String getDescription() {
			return "看到布拉克斯的精液溅满了他毛茸茸的肚子和胸膛，你会突然有一种冲动，想让他尝尝自己的味道。"
					+ "你俯下身，开始用嘴巴舔舐所有黏糊糊的种子，一边呼吸着狼男那强烈的男性气息，一边慢慢地确保一滴也不漏。"
					+ "<br/><br/>"
					+ "当你满嘴都是那甜甜咸咸的“奶油”时，你顺着布拉克斯的胸膛往上舔，直到你凝视着他那双充满担忧的眼睛。"
					+ "把你的嘴唇贴在他的嘴唇上，他试图把头扭开，但是，你没有让他有说不的余地，而是伸手抓住他狼一样的口鼻，捏住他的脸颊，直到你迫使他的嘴张开一点。"
					+ "<br/><br/>"
					+ "抓住机会，你把舌头伸进他的嘴唇，开始用这小口把你那口中美味的“奶油”送入其中。"
					+ "布拉克斯开始在你下面扭动，但他不愿意分享你对他的精液的爱只会坚定你的决心，不久之后，你成功地把他自己的种子塞进了他的嘴里。"
					+ "<br/><br/>"
					+ "为了给自己留一点，你中断了亲吻，迅速地用手捂住布拉克斯的口鼻，以防他浪费宝贵的精液。"
					+ "你再也忍不住了，你示意性地吞下了你嘴里少量的美味精液，然后要求布拉克斯也这样做。"
					+ "<br/><br/>"
					+ "你把布拉克斯的嘴闭上，直到你看到他不情愿地模仿你的行为，当你放手的时候，他顺从地张开嘴，向你展示他已经咽下了所有的东西。"
					+ "对狼男的表现感到高兴，你俯身，给了他一个热情的，满是精液的吻。";
		}

		@Override
		public void applyEffects() {
			if (SexFlags.braxCumOnChest)
				SexFlags.braxCumOnChest = false;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			}
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_FEED_CUM = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.FOUR_LUSTFUL,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "给布拉克斯喂下";
		}

		@Override
		public String getActionDescription() {
			return "用你的手指将布拉克斯的精液喂回给他自己。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.braxCumOnChest;
		}

		@Override
		public String getDescription() {
			return "看到布拉克斯的精液溅满了他毛茸茸的肚子和胸膛，你会突然有一种冲动，想让他尝尝自己的味道。"
					+ "你俯下身，开始用手指舀起所有黏糊糊的种子，一边呼吸着狼男那强烈的男性气息，一边慢慢地确保一滴也不漏。"
					+ "<br/><br/>"
					+ "当你满手都是那甜甜咸咸的“奶油”时，你俯下身，伏在布拉克斯的胸膛上，直到你凝视着他那双充满担忧的眼睛。"
					+ "把你覆盖着精液的手指拿到他的嘴边，布拉克斯试图把头扭开，但是，你没有让他有说不的余地，而是伸出空闲的手抓住他狼一样的口鼻。"
					+ "捏住他的脸颊，直到你迫使他的嘴张开一点。"
					+ "<br/><br/>"
					+ "抓住机会，你把沾满精液的手指伸进他的嘴唇，开始用这小口把你那指上美味的“奶油”送入其中。"
					+ "布拉克斯开始在你下面扭动，但他不愿意分享你对他的精液的爱只会坚定你的决心，不久之后，你成功地把他自己的种子塞进了他的嘴里。"
					+ "<br/><br/>"
					+ "看到他嘴里塞满了精液，你高兴地把手指缩回来，用另一只手迅速地捂住布拉克斯的口鼻，以免他浪费宝贵的精液。"
					+ "你朝那个愁眉苦脸的小狼男咯咯地笑着，命令他将精液咽下去。"
					+ "<br/><br/>"
					+ "你把布拉克斯的嘴闭上，直到你看到他不情愿地服从你的命令，当你放手的时候，他顺从地张开嘴，向你展示他已经咽下了所有的东西。"
					+ "你对小狼男的表现很满意，伸出你满是精液的手，然后，让他认为在这件事上没有太多的选择，顺从地舔干净你的手。";

		}

		@Override
		public void applyEffects() {
			if (SexFlags.braxCumOnChest)
				SexFlags.braxCumOnChest = false;
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_STUD);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
			}
		}
	};

	// Partner's actions:

	public static final SexAction PARTNER_TALK_DIRTY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"当你在布拉克斯[npc.penis+]上起起落落时，布拉克斯的呼喊不断，",
						"在他的呻吟中，布拉克斯向你呼喊，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~啊啊！~太爽了，继续骑我吧！",

							"操！~啊啊！~我是你的小贝塔！",

							"~啊！~我是你的小玩具！",

							"我是你的好小贝塔！~啊啊！~使用我！"), Main.sex.getCharacterPerformingAction()));
				
			} else if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"当你在布拉克斯[npc.penis+]上起起落落时，布拉克斯的呼喊不断，",
						"在他的呻吟中，布拉克斯向你呼喊，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~啊啊！~太爽了，继续骑我吧！",

							"操！~啊啊！~我是你的小贝塔！",

							"~啊！~我是你的小玩具！",

							"我是你的好小贝塔！~啊啊！~使用我！"), Main.sex.getCharacterPerformingAction()));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"从你的双腿间抬起头，布拉克斯对着你的阴部喃喃自语，",
						"布拉克斯咕哝着对你说，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~嗯哼！~喜欢……~嗯哼！~小穴！",

							"~嗯！~我是……~嗯哼！~贝塔！",

							"~嗯哼！~你是……~嗯哼！~阿尔法！",

							"~嗯哼！~ 味道……~嗯！~ 很好！"), Main.sex.getCharacterPerformingAction()));
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)) {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"布拉克斯对着你的屁股喃喃自语，",
						"布拉克斯咕哝着对你说，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"~嗯哼！~还要……~嗯哼！~多久……",

							"~嗯！~我是……~嗯哼！~贝塔！",

							"~嗯哼！~你是……~嗯哼！~阿尔法！",

							"~嗯哼！~ 想要……~嗯！~ 快乐！"), Main.sex.getCharacterPerformingAction()));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.returnStringAtRandom(
						"布拉克斯抬头看着你，喃喃自语道，",
						"布拉克斯咕哝着对你说，"));
				
				UtilText.nodeContentSB.append(
						UtilText.parseSpeech(
							UtilText.returnStringAtRandom(
							"我会做个好小贝塔的……",

							"你说什么我就做什么……",

							"我……我真的喜欢你骑我……拜托……",

							"你是我的阿尔法……"), Main.sex.getCharacterPerformingAction()));
			}
			
			return UtilText.nodeContentSB.toString();
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_CUNNILINGUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			return UtilText.returnStringAtRandom(
					"布拉克斯一动不动，发出轻微的呻吟声，急切地舔着你的"+Main.game.getPlayer().getVaginaName(true)+".",
					
					"当你坐在布拉克斯的脸上时，他一动不动，发出轻微的呜呜声，他饥渴地舔着你的"+Main.game.getPlayer().getVaginaName(true)+"。",
					
					"布拉克斯在你下面愉快地呻吟着，似乎很满足于像一个顺从的婊子一样舔舐你。",
						
					"当布拉克斯愉快地舔你的"+Main.game.getPlayer().getVaginaName(true)+"时，他一动不动，用顺从的大眼睛从你的两腿之间看着你。");
		}

		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_ANILINGUS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			return UtilText.returnStringAtRandom(
					"布拉克斯一动不动，发出轻微的呻吟声，他顺从地舔你[pc.asshole+]。",
					
					"当你坐在布拉克斯的脸上时，他一动不动，发出轻微的呜呜声，他尽职尽责地舔着你[pc.asshole+]。",
					
					"布拉克斯在你下面呻吟着，似乎很满足于像一个顺从的婊子一样亲吻你的菊花。",
						
					"当布拉克斯舔你[pc.asshole+]时，他一动不动，时不时地发出低低的、顺从的呜呜声。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ANAL_RECEIVING);
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT_RIDING = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			return UtilText.returnStringAtRandom(
					"布拉克斯一动不动，发出满足的呻吟声，当你用你的"+Main.game.getPlayer().getVaginaName(true)+"榨取他的[npc.penis+]时。",
					
					"当你继续骑乘在布拉克斯[npc.penis+]上时，他一动不动，发出轻微的呻吟声，每当他阴茎底部的结撞到你的"+Main.game.getPlayer().getVaginaName(true)+"。",
					
					"布拉克斯在你下面呻吟着，似乎很满足只是静静地躺着，让你骑他的[npc.penis+]。",
						
					"当你继续用你的"+Main.game.getPlayer().getVaginaName(true)+"榨取布拉克斯[npc.penis+]时，他一动不动，用顺从的大眼睛抬头看着你。");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_TAKE_IT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			return UtilText.returnStringAtRandom(
					"布拉克斯一动不动，发出满足的呻吟声，当你用你[pc.asshole+]榨取他的[npc.penis+]时。",
					
					"当你继续骑乘在布拉克斯[npc.penis+]上时，他一动不动，发出轻微的呻吟声，每当他阴茎底部的结撞到你[pc.asshole+]。",
					
					"布拉克斯在你下面呻吟着，似乎很满足只是静静地躺着，让你骑他的[npc.penis+]。",
						
					"当你继续用你[pc.asshole+]榨取布拉克斯[npc.penis+]时，他一动不动，用顺从的大眼睛抬头看着你。");
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
		}
		
	};
	
	public static final SexAction PARTNER_COWGIRL_MASTURBATE = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, null)),
			SexParticipantType.SELF) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE)
					|| Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE)) {
				return UtilText.returnStringAtRandom(
						"你继续用他的舌头，布拉克斯伸手抓向他[npc.penis+]，抽动着发出一长串可怜的哀鸣。",

						"布拉克斯的舌头仍然深埋在你两腿之间，他抓住他[npc.penis+]，低哼一声，在你身下手淫。",
						
						"布拉克斯[npc.penis+]未得到一丝关注，他把手伸向两腿间，顺从地撸动着它。",
							
						"你继续坐在布拉克斯的脸上，他把手伸向下体，揉捏爱抚了自己肥大的结一段时间后，开始顺从地抚摸[npc.penis]。");
			
			} else {
				return UtilText.returnStringAtRandom(
						"当你坐在布拉克斯的肚子上时，你感到他[npc.penis+]顶着你的下背部，他呜呜恳求着，转过身来，顺从地把自己拉开。",

						"你感觉到布拉克斯[npc.penis+]戳着你的下背部，知道他现在积攒了多少快感。"
								+ "你调笑着低头看向这位顺从的狼男，他正在你身后手淫。",
						
						"布拉克斯[npc.penis+]未得到一丝关注，他把手伸向两腿间，顺从地手淫着。",
							
						"布拉克斯发出可怜的呜呜声，他把手伸向下体，揉捏爱抚了自己肥大的结一段时间后，开始顺从地抚摸他[npc.penis+]。");
			}
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_BOUNCE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			return UtilText.returnStringAtRandom(
					"布拉克斯随着你的起伏而顶弄着臀部，有助于他[npc.penis+]插进你"+Main.game.getPlayer().getVaginaName(true)+"。",
					
					"你继续骑着布拉克斯，他顶弄臀部，让他[npc.penis+]填满你"+Main.game.getPlayer().getVaginaName(true)+"。",
					
					"布拉克斯发出色情的呻吟，顶弄臀部撞向你，让你骑乘在他[npc.penis+]上。",
						
					"当你继续用你"+Main.game.getPlayer().getVaginaName(true)+"榨取布拉克斯[npc.penis+]时，他轻柔地随着你的起伏而顶弄着。");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_BOUNCE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
			return UtilText.returnStringAtRandom(
					"布拉克斯随着你的起伏轻柔地顶弄臀部，让他[npc.penis+]插进你[pc.asshole+]。",
					
					"你继续骑着布拉克斯，他顶弄臀部，用他[npc.penis+]填满你[pc.asshole+]。",
					
					"布拉克斯发出色情的呻吟，顶弄臀部撞向你，让你骑乘在他[npc.penis+]上。",
						
					"当你继续用[pc.asshole+]榨取布拉克斯[npc.penis+]时，他轻柔地随着你的起伏而顶弄着。");
		}
	};
	
	public static final SexAction PARTNER_COWGIRL_GROPE_BREASTS = new SexAction(
			SexActionType.REQUIRES_NO_PENETRATION_AND_EXPOSED,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasBreasts();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.NIPPLES)) {
				return UtilText.returnStringAtRandom(
						"你看到布拉克斯盯着你的胸部，发出顽皮的笑声，引导他的手伸向你的乳房，"
								+ "他立刻开始摸索揉捏你柔软"+Main.game.getPlayer().getBreastName(true)+"。",
						
						"布拉克斯暗示性地把狼型的双手伸向你裸露的胸部，而你并不打算阻止，"
								+ "布拉克斯挤压揉捏你的"+Main.game.getPlayer().getNippleName(true)+"，令你发出一声淫荡的呻吟。",
						
						"布拉克斯把手伸向乳房享受了一会，你允许他急切地挤压揉捏你的"+Main.game.getPlayer().getNippleName(true)+"，这让你发出淫荡的呻吟。",
							
						"让布拉克斯伸向你裸露的"+Main.game.getPlayer().getBreastName(true)+"，当他开始急切地摸索你的胸部，把手掌上坚硬的小垫子粗暴地扎进你柔软的乳肉中时，你轻吟一声。");
			} else {
				return UtilText.returnStringAtRandom(
						"你看到布拉克斯盯着你的胸部，发出顽皮的笑声，引导他的手伸向你的乳房。他急切地摸索揉搓你的"+Main.game.getPlayer().getBreastName(true)+"，令你发出淫荡的呻吟。"
								+ "通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"。",
						
						"布拉克斯暗示性地把狼型的双手伸向你裸露的胸部，而你并不打算阻止，"
								+ "你感觉到他的手指往下摸索揉捏你的"+Main.game.getPlayer().getNippleName(true)+"，发出一声淫荡的呻吟。"
								+ "通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"。",
						
						"布拉克斯把手伸向你的乳房，为了让他享受一会，你允许他通过你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"急切地挤压揉捏你的胸部。",
							
						"让布拉克斯伸向你裸露的"+Main.game.getPlayer().getBreastName(true)+"，当他开始急切地摸索你的胸部，把他狼型的双手粗暴地按下去时，你轻吟一声"
								+ "在你的"+Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"上。");
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_BREASTS_SELF);
		}
	};
	
	// Player's orgasms:

	public static final SexAction PLAYER_COWGIRL_ORGASM_NO_PEN = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "高潮";
		}

		@Override
		public String getActionDescription() {
			return "你感觉自己就要高潮了。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你感觉自己就要高潮了，发出迷乱的呻吟，准备好迎接高潮。");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
				
				if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
					UtilText.nodeContentSB.append("你向下摸去，抓住你的"+Main.game.getPlayer().getPenisName(true)+"，开始疯狂自慰起来，在布拉克斯毛茸茸的肚子上轻轻地来回摇晃。"
							+ "你被高潮席卷，发出迷乱的呻吟，感觉到鸡巴在抽搐。");
					
				} else {
					UtilText.nodeContentSB.append("你温柔地在布拉克斯毛茸茸的肚子上来回摇晃，把手伸向并撸动你的"+Main.game.getPlayer().getPenisName(true)+"通过你的"
							+ Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"
							+ "你被高潮席卷，发出迷乱的呻吟，感觉到鸡巴在抽搐。");
				}

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append("，然而你一滴也不剩了，这在某种程度上降低了你高潮的快感。");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("，射出一小滴精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("，喷出少量精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append("，射出精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append("，射出精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append("，你射出大量的精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append("，你射出大量的精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append("，你射出巨量的精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯肌肉发达的胸部上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					default:
						break;
				}
				
				// Vagina:
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
					UtilText.nodeContentSB.append("<br/><br/>"
							+ "你在布拉克斯的肚子里排空阴囊时，又感觉到有第二波热流在你下体间汇聚。"
							+ "你抓住布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，燥热的细缝一缩一缩。"
							+ "震撼心灵的高潮又一次席卷而来，给你带来了一波又一波的强烈快感，你高兴地呻吟和尖叫着。");
			
			} else if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append("你温柔地在布拉克斯毛茸茸的肚子上来回摇晃，感觉一股强烈的热流汇聚到腹股沟。"
						+ "你抓住狼男布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，燥热的细缝一缩一缩。"
						+ "一股震撼心灵的高潮席卷而来，雌性器给你带来一波强烈的快感，你高兴地呻吟和尖叫着。");
				
			}else {
				UtilText.nodeContentSB.append("你温柔地在布拉克斯毛茸茸的肚子上来回摇晃，感觉一股强烈的热流汇聚到你无性的下体。"
						+ "你抓住狼男布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，玩偶一样光滑的胯部愉悦地震颤着。"
						+ "一股震撼心灵的高潮席卷而来，你饥渴地抚摸腿间光滑的敏感部位，高兴地呻吟和尖叫着。");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};

	public static final SexAction PLAYER_COWGIRL_ORGASM_FACESITTING = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "高潮";
		}

		@Override
		public String getActionDescription() {
			return "你感觉自己就要高潮了。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE))
					|| (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.TONGUE));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.TONGUE))
				UtilText.nodeContentSB.append("你感受到布拉克斯的舌头舔弄着你[pc.asshole+]，你意识到自己快要高潮了，发出迷乱的呻吟，准备好迎接高潮。");
			else
				UtilText.nodeContentSB.append("布拉克斯的舌头舔弄着你的"+Main.game.getPlayer().getVaginaName(true)+"，你感到快感就要到达顶峰，发出迷乱的呻吟，准备好迎接高潮。");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
				
				if(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
					UtilText.nodeContentSB.append("你向下摸去，抓住你的"+Main.game.getPlayer().getPenisName(true)+"，开始疯狂自慰起来，压向布拉克斯似狼的吻部。"
							+ "你被高潮席卷，发出迷乱的呻吟，感觉到你的"+Main.game.getPlayer().getPenisName(true)+"在抽搐。");
					
				} else {
					UtilText.nodeContentSB.append("你压向布拉克斯似狼的吻部，伸手撸动你的"+Main.game.getPlayer().getPenisName(true)+"经过你的"
							+ Main.game.getPlayer().getHighestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"
							+ "你被高潮席卷，发出迷乱的呻吟，感觉到你的"+Main.game.getPlayer().getPenisName(true)+"在抽搐。");
				}

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append("，然而你一滴也不剩了，这在某种程度上降低了你高潮的快感。");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("，射出一小滴精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("，喷出少量精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append("，射出精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append("，射出精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append("，你射出大量的精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append("，你射出大量的精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append("，你射出巨量的精液"
								+(Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
									?"在布拉克斯伏在地上的脑袋上。"
									:"羞耻地插入你的"+ Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"。"));
						break;
					default:
						break;
				}
				
				// Vagina:
				if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE)
					UtilText.nodeContentSB.append("<br/><br/>"
							+ "你排空阴囊时，又感觉到有第二波强烈的热流在你下体间汇聚。"
							+ "你双膝发软，完全倾倒在布拉克斯脸上，你夹紧大腿，燥热的细缝一缩一缩。"
							+ "震撼心灵的高潮又一次席卷而来，给你带来了一波又一波的强烈快感，你高兴地呻吟和尖叫着。");
			
			} else if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				UtilText.nodeContentSB.append( "你愉悦地尖叫，感觉一股强烈的热流汇聚到腹股沟。"
						+ "你双膝发软，完全倾倒在布拉克斯脸上，你夹紧大腿，燥热的细缝一缩一缩。"
						+ "一股震撼心灵的高潮席卷而来，雌性器给你带来一波强烈的快感，你高兴地呻吟和尖叫着。");
				
			}else {
				UtilText.nodeContentSB.append("你温柔地在布拉克斯毛茸茸的肚子上来回摇晃，感觉一股强烈的热流汇聚到你无性的下体。"
						+ "你抓住狼男布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，玩偶一样光滑的胯部愉悦地震颤着。"
						+ "一股震撼心灵的高潮席卷而来，你饥渴地抚摸腿间光滑的敏感部位，高兴地呻吟和尖叫着。");
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
		}
	};
	
	public static final SexAction PLAYER_COWGIRL_ORGASM_RIDING_COCK = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "高潮";
		}

		@Override
		public String getActionDescription() {
			return "你感觉自己就要高潮了。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS))
					|| (Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS));
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);

			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS))
				UtilText.nodeContentSB.append("你感觉自己就要高潮了，提高了在布拉克斯[npc.penis+]上起伏的频率。"
						+ "你被自己[pc.asshole+]带来的快感吞没，发出迷乱的呻吟，准备好迎接高潮。");
			else
				UtilText.nodeContentSB.append("你感觉自己就要高潮了，提高了在布拉克斯[npc.penis+]上起伏的频率。"
						+ "你被你的"+Main.game.getPlayer().getVaginaName(true)+"带来的快感吞没，发出迷乱的呻吟，准备好迎接高潮。");

			// Penis:
			if (Main.game.getPlayer().hasPenis()) {
					UtilText.nodeContentSB.append(
							"你[pc.cock+]突然开始剧烈抽动，你知道马上就要射精了。"
					+ "你单手抓住你硬邦邦的老二，对着布拉克斯的脸疯狂地手淫。你马上就要射精了。");

				UtilText.nodeContentSB.append("你被高潮席卷，发出迷乱的呻吟，感觉到鸡巴在抽搐。");

				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
				case ZERO_NONE:
					UtilText.nodeContentSB.append("不幸的是，你甚至不能产生一滴爱液，这在某种程度上降低了你高潮的快感。");
					break;
				case ONE_TRICKLE:
					UtilText.nodeContentSB.append("你把一小股粘稠的精液射到布拉克斯的胸口，发出满意的呻吟。");
					break;
				case TWO_SMALL_AMOUNT:
					UtilText.nodeContentSB.append("你把少量粘稠的精液射向布拉克斯的胸口，发出满意的呻吟。");
					break;
				case THREE_AVERAGE:
					UtilText.nodeContentSB.append("你的鸡巴抽动着射出粘稠的精液，布满布拉克斯的胸口，你发出满意的呻吟。");
					break;
				case FOUR_LARGE:
					UtilText.nodeContentSB.append("你的鸡巴抽动着射出粘稠的精液，布满布拉克斯的胸口，你发出满意的呻吟。"
							+ "几滴精液甚至溅到他的脸上，当你的咸腥的精液落在他的嘴边时，他退缩了。");
					break;
				case FIVE_HUGE:
					UtilText.nodeContentSB.append("你的鸡巴抽动着射出大量粘稠的精液，布满布拉克斯的胸口，你发出满意的呻吟。"
							+ "一滴精液溅到他的脸上，当你的咸腥的精液落在他的嘴边时，他退缩了。");
					break;
				case SIX_EXTREME:
					UtilText.nodeContentSB.append("你的鸡巴抽动着射出大量粘稠的精液，布满布拉克斯的胸口，你发出满意的呻吟。"
							+ "几股精液流到他的脸上，当你的咸腥的精液落在他的嘴边时，他退缩了。");
					break;
				case SEVEN_MONSTROUS:
					UtilText.nodeContentSB.append("你的鸡巴抽动着射出巨量粘稠的精液，布满布拉克斯的胸口，你发出满意的呻吟。"
							+ "几股精液流到他的脸上，当你的咸腥的精液完全覆上他的脸和身体时，他退缩了。");
					break;
				default:
					break;
				}

				UtilText.nodeContentSB.append("<br/><br/>");
			}

			// Vagina:
			if (Main.game.getPlayer().getVaginaType() != VaginaType.NONE) {
				if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
					UtilText.nodeContentSB.append("你继续用你的屁股套弄着布雷克斯的结状狗屌，感觉小穴汇聚起一股强烈热量。"
							+ "你抓住布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，燥热的细缝一缩一缩。"
							+ "一股震撼心灵的高潮席卷而来，雌性器给你带来一波强烈的快感，你高兴地呻吟和尖叫着。");
				} else {
					UtilText.nodeContentSB.append("你继续用你的小穴套弄着布雷克斯结状的狗屌，感觉下体汇聚起一股强烈热量。"
							+ "你抓住布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，燥热的细缝一缩一缩。你紧抓着深插阴部的抽动老二。"
							+ "一股震撼心灵的高潮席卷而来，雌性器给你带来一波强烈的快感，你高兴地呻吟和尖叫着。");
				}
			}

			// Mound:
			if (Main.game.getPlayer().getPenisType() == PenisType.NONE && Main.game.getPlayer().getVaginaType() == VaginaType.NONE) {
				if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
					UtilText.nodeContentSB.append("你继续用你的屁股套弄着布雷克斯的结状狗屌，感觉你无性的下体汇聚起一股强烈热量。"
							+ "你抓住布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，玩偶一样光滑的胯部愉悦地震颤着。"
							+ "一股震撼心灵的高潮席卷而来，你饥渴地抚摸腿间光滑的敏感部位，高兴地呻吟和尖叫着。");
				} else {
					UtilText.nodeContentSB.append("你温柔地在布拉克斯肚子上来回摇晃，感觉一股强烈的热流汇聚到你无性的下体。"
							+ "你抓住布拉克斯的胸部来撑起自己，你双膝发软，夹紧大腿，玩偶一样光滑的胯部愉悦地震颤着。"
							+ "一股震撼心灵的高潮席卷而来，你饥渴地抚摸腿间光滑的敏感部位，高兴地呻吟和尖叫着。");
				}
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
//			Main.sex.removePenetration(PenetrationType.PENIS, OrificeType.VAGINA); TODO
//			Main.sex.removePenetration(PenetrationType.PENIS, OrificeType.ANUS);
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			return Util.newArrayListOfValues(Fetish.FETISH_DOMINANT);
		}
	};

	// Partner's orgasms:

	public static final SexAction PARTNER_COWGIRL_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
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
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()) {
				return "布拉克斯的呻吟声越来越大，让你知道他就要高潮了。"
						+ "你感觉到肥大的阴茎在你屁股里粗暴地抽插，你饥渴地想要让他用浓稠的种子填满你的直肠。"
						+ "你最后一次抬起身体，重重压向他肿胀的老二上，把他阴茎根部的结也吞入你的肛门里。"
						+ "你感觉结立即肿胀起来，发出了惊叫，把布拉克斯抽动的狗屌深深地锁在屁股里。"
						+ "<br/>"
						+ "布拉克斯发出野性的呻吟，阴茎将高负荷的精液射进你放荡的后庭，你高兴地扭动着。"
						+ "片刻过后，你感觉结变萎并滑了出来，发出一声湿漉漉的啵声，让你重获自由。";
				
			} else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
				return "布拉克斯的呻吟声越来越大，让你知道他就要高潮了。"
						+ "你感觉到肥大的阴茎在你小穴里粗暴地抽插，饥渴地想让他用浓稠的种子填满你的"+(Main.game.getPlayer().isVisiblyPregnant()?"be":"have your womb")+"。"
						+ "你最后一次抬起身体，重重压向他肿胀的老二上，把他阴茎根部的结也吞入你的贪婪的小穴里。"
						+ "你感觉结立即肿胀起来，发出了惊叫，把布拉克斯抽动的狗屌深深地锁在湿润的细缝里。"
						+ "<br/>"
						+ "布拉克斯发出野性的呻吟，阴茎将高负荷的精液射进你的"+(Main.game.getPlayer().isVisiblyPregnant()?"waiting pussy":"waiting womb")+"，你高兴地扭动着。"
						+ "片刻过后，你感觉结变萎并滑了出来，发出一声湿漉漉的啵声，让你重获自由。";
				
			} else {
				return "布拉克斯的呻吟声越来越大，让你知道他就要高潮了。"
						+ "你回头看他[npc.penis+]，饥渴的欲望压倒你去撸动它。"
						+ "你向后挪动，坐上他的大腿，同时向下伸出双手抓住他巨大的阴茎。"
						+ "你把握他的整根长度来回磨蹭，向下按压根部那粗大的结，又向上摩蹭他锥形的龟头。"
						+ "你感觉结肿胀起来，发出了呻吟，在阴茎开始抽动之时将其指向布拉克斯的脸。"
						+ "<br/>"
						+ "布拉克斯发出野性的呻吟，你看到他的阴茎将高负荷的精液喷满他的胸膛，高兴地扭动着。"
						+ "片刻过后，布拉克斯结束高潮，结也变萎，他发出满意的呻吟。";
			}
		}

		@Override
		public void applyEffects() {
//			if(Main.sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.ANUS)==null && Main.sex.getPenetrationTypeInOrifice(OrificeType.VAGINA)==null)
//				SexFlags.braxCumOnChest = true; TODO
		}
		

		@Override
		public List<SexAreaInterface> getAreasCummedIn(GameCharacter cumProvider, GameCharacter cumTarget) {
			if(!cumProvider.isPlayer() && cumTarget.equals(Main.sex.getTargetedPartner(cumProvider))) {
				if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.VAGINA).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.VAGINA);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.ANUS).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.ANUS);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.MOUTH).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.MOUTH);
					
				} else if (Main.sex.getAllOngoingSexAreas(cumTarget, SexAreaOrifice.NIPPLE).contains(SexAreaPenetration.PENIS) && Main.sex.getCharacterOngoingSexArea(cumTarget, SexAreaOrifice.NIPPLE).contains(cumProvider)) {
					return Util.newArrayListOfValues(SexAreaOrifice.NIPPLE);
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty())
				return Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY);
			else if (!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty())
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING);
			else
				return null;
		}
	};
	
	
}
