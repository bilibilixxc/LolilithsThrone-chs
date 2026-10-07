package com.lilithsthrone.game.sex.sexActions.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Ralph;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.DisplacementType;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.managers.dominion.SMRalphDiscountBig;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionLimitation;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.64
 * @version 0.2.8
 * @author Innoxia
 */
public class RalphOral {

	// Player actions:

	public static final SexAction PLAYER_START_ORAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "开始吮吸";
		}

		@Override
		public String getActionDescription() {
			return "将拉尔夫马阴茎的龟头塞进嘴里";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyOngoingActionHappening()
					&& Main.sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("拉尔夫的肉棒不是一般的大，甚至不用凑上去就够你赚到这点折扣了。"
					+ "你张开嘴，在他那马屌平坦的龟头上留下了好几个吻，接连向下。");
			
			if(Main.sex.hasLubricationTypeFromAnyone(Main.sex.getCharacterPerformingAction(), SexAreaPenetration.PENIS, LubricationType.PRECUM))
				UtilText.nodeContentSB.append("你的舌尖轻抚过他凹凸不平的马尿道时，一团咸腥的先走液落进了你的嘴里。"
						+ "那咸腥的液体顺着你的嘴唇渗了进去，你惊诧地喘息一声");
			else
				UtilText.nodeContentSB.append("你的舌尖轻抚过他凹凸不平的马尿道时，你会感到他兽性阴茎散发的热量辐射着你的脸"
						+ "你微微娇喘着，迫不及待地想舔弄他那美味的肉棒");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append(", 但等想起柜台那边还有一位顾客时，已经太晚了！<br/>"
						+ "你急于弥补自己的过失，快速摇晃向前，大大下巴张开，将滚烫跳动的阴茎含入口中。"
						+ "匆忙之中你制造了更多噪音，然后听到拉尔夫为了保守他的秘密连忙为顾客提供一部分折扣。");
			else
				UtilText.nodeContentSB.append("，但很快娇喘声就变得呜呜嗯嗯，你大张着嘴巴，将那颤动着的滚烫肉棒衔进了嘴里。");
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
			
	public static final SexAction PLAYER_STAY_QUIET = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "保持安静";
		}

		@Override
		public String getActionDescription() {
			return "一动不动地坐着，沉默无声。这样柜台前的顾客就不会知道你在这里。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.customerAtCounter;
		}

		@Override
		public String getDescription() {
			return "当拉尔夫处理完上面那位顾客的问题时，你一动不动地坐着，努力防止自己发出声音。"
					+ "一团黏糊糊的唾液和先走液混合着从你张开的嘴唇中流下，你默默地往前挪了一点，企图用拉尔夫的阴茎把液体推回喉咙里。";
		}
	};
	
	public static final SexAction PLAYER_STAY_QUIET_TEASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "沉默地挑逗";
		}

		@Override
		public String getActionDescription() {
			return "你一边挑逗拉尔夫的鸡巴，一边试图破坏他在顾客面前的镇定。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.customerAtCounter;
		}

		@Override
		public String getDescription() {
			return "当拉尔夫处理完上面那位顾客后，你决定找点乐子。"
					+ "你向后退了一些，用舌头轻轻地舔他马屌上喇叭形状的龟头，用舌尖挑逗他那凹凸不平的尿道。"
					+ "一股黏糊糊的唾液和先走液从你张开的嘴唇中流下，你伸手将这些天然润滑剂上下涂抹在他的阴茎上，轻轻抚摸他的睾丸，同时继续用舌头挑逗他的龟头。"
					+ "你听到拉尔夫在顾客面前努力保持镇定，当你听到他委婉地引导顾客看商店的另一侧时， 你坏笑起来。"
					+ "他显然对你的小游戏没什么兴趣，一挺腰就把鸡巴顶进了你的喉咙……";
		}
	};
	
	public static final SexAction PLAYER_SUCK_COCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FIVE_EXTREME,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "吮吸鸡巴";
		}

		@Override
		public String getActionDescription() {
			return "继续给拉尔夫口交。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你不停地前后摆动你的头，将拉尔夫宽大、膨胀的龟头塞进喉咙。"
					+ "当你的嘴唇将黏糊糊的先走液和唾液混合物上下涂抹时，他那粗大的黑色阴茎在店里的灯光下闪闪发光。");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("狭窄的小房间中，你的周围回荡着淫荡的挤压声和你前后摇晃时地板发出的咯吱咯吱声。"
						+ "发出的噪音大得惊人。"
						+ "你太沉迷于吸吮面前美味粗大的鸡巴了，以至于完全忘记了上面还有一位顾客，你听到拉尔夫连忙为顾客提供一部分折扣"
						+ "以贿赂他保持沉默。");
			else
				UtilText.nodeContentSB.append("你每次往前吞进去的时候，感觉得到拉尔夫也在微微送腰，让他那巨大的雄物满满当当地塞进你的口穴中。"
						+ "狭窄的小房间中，淫荡的挤压声和你前后摇晃时地板发出的咯吱咯吱声在四处回响，"
						+ "发出的噪音大得惊人，你很庆幸附近没有顾客听到你的声音。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
	
	public static final SexAction PLAYER_FONDLE_BALLS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "爱抚蛋蛋";
		}

		@Override
		public String getActionDescription() {
			return "给拉尔夫的睾丸一些关注。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你吮吸着拉尔夫滚烫的黑色马屌，忽然想起了他说过要记得关照一下蛋蛋。"
					+ "你不想让他失望，但活动空间由十分有限，只得笨手笨脚地将手伸向挂在他抖动的阴茎根部那沉甸甸的囊袋。"
					+ "你对着那充满精液的巨大睾丸轻轻揉捏起来，想象着它们在你的喉咙里倾泻而出的场面，不由自主地发出模糊的呻吟。");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "你局促不安的动作加上你刚刚发出的不太平静的呻吟声，足以让站在柜台另一侧的顾客警觉起来。"
						+ "当你听到拉尔夫为了让他们保持沉默而让出你的一部分折扣时，你感到有点泄气。");
			else
				UtilText.nodeContentSB.append("你听到拉尔夫开始在你上方发出鼓励的呻吟声，你继续摩挲爱抚他那沉甸甸的囊袋，与此同时，他那同样匀称的鸡巴也在你嘴里插进抽出。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};
	
	public static final SexAction PLAYER_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.TWO_HORNY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "深喉";
		}

		@Override
		public String getActionDescription() {
			return "看看你的喉咙吞下马屌的本事有多大。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你鼓起勇气，决定试着尽可能多的把拉尔夫的鸡巴含进喉咙。"
					+ "你向前挪动身子，靠向他的下体，放松喉咙，打开食道，试图吞下拉尔夫那一整根粗长温热的马屌。"
					+ "你毫不畏惧，直直地把嘴巴送上前去，将他巨大的阴茎整整一根都深深插入喉咙之中。"
					+ "你慢慢后退，过程中下巴上早就沾满了一层黏滑的口水，等看到半根肉棒都退出来后，你决定就此重复，又一次向着拉尔夫的胯部推进。"
					+ "也多亏了口中产生的大量唾液，他兽状的马屌轻易滑进了你的嘴里，你又将其全部吞入喉中，"
					+ "你一直保持着这种姿势，直到觉得有些呼吸不过来了才一下子退出来，稍稍恢复了一会儿，就再次像平常一样舔弄起来。");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "你一前一后深深吞入那巨大的马屌时，发出的滋滋声和地板的吱嘎声混合在一起，形成了相当明显的噪音。"
						+ "那个先前听到你狂热表演的顾客依然在场，你听见拉尔夫又给他送上了一点折扣，你完全忘记了，只得呜呜嗯嗯地叹了口气。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
		}
		
	};

	public static final SexAction PLAYER_BIG_DISCOUNT = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "超大折扣";
		}
		@Override
		public String getActionDescription() {
			return "询问拉尔夫折扣能不能再高一点。<i style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>这个提议会让拉尔夫插入你的小穴！</i>";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
					&& !SexFlags.askedForBigDiscount;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你刚刚舔完拉尔夫的鸡巴，逐渐觉得股间燃起一股难以抑制的渴望。"
					+ "你想要感受他进入你身体的感觉。他那粗壮的动物阴茎在你的小穴里抽插，用活跃的种子填满你的子宫……"
					+ "你再也忍受不住，把他的肉棒整根吐了出来，一歪头，露出楚楚可怜的大眼睛抬头看向他。"
					+ "<br/>"
					+ UtilText.parseSpeechNoEffects("或许折扣还能再高一点？", Main.game.getPlayer())+"你使出浑身解数，希望语调能足够诱人。");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "你听见柜台另一边的顾客吓了一跳，都被逗笑了，拉尔夫赶紧把你应得的折扣送出去了一些，想堵住那人的嘴。");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter)
				SexFlags.alertedCustomer=true;
			
			SexFlags.askedForBigDiscount=true;

			Map<AbstractClothing, DisplacementType> clothingTouched = Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_PREGNANCY);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_IMPREGNATION);
			}
		}
	};
	
	public static final SexAction PLAYER_ANAL_BIG_DISCOUNT = new SexAction(
			SexActionType.STOP_ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.THREE_DIRTY,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "超大折扣";
		}
		@Override
		public String getActionDescription() {
			return "询问拉尔夫折扣能不能再高一点。<i style='color:"+PresetColour.GENERIC_SEX.toWebHexString()+";'>这个提议会让拉尔夫操你的屁股！</i>";
		}
		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.game.getPlayer().hasVagina()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
					&& !SexFlags.askedForBigDiscount;
		}
		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("你刚刚舔完拉尔夫的鸡巴，逐渐觉得股间燃起一股难以抑制的渴望。"
					+ "你想要感受他进入你身体的感觉。他那粗壮的动物阴茎在你的屁穴里抽插，用活跃的种子填满你的直肠……"
					+ "你再也忍受不住，把他的肉棒整根吐了出来，一歪头，露出楚楚可怜的大眼睛抬头看向他。"
					+ "<br/>"
					+ UtilText.parseSpeechNoEffects("或许折扣还能再高一点？", Main.game.getPlayer())+"你使出浑身解数，希望语调能足够诱人。");
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("<br/>"
						+ "你听见柜台另一边的顾客吓了一跳，都被逗笑了，拉尔夫赶紧把你应得的折扣送出去了一些，想堵住那人的嘴。");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public void applyEffects() {
			if(SexFlags.customerAtCounter) {
				SexFlags.alertedCustomer=true;
			}
			
			SexFlags.askedForBigDiscount=true;
			
			Map<AbstractClothing, DisplacementType> clothingTouched = Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS, null);
			for(Entry<AbstractClothing, DisplacementType> e : clothingTouched.entrySet()) {
				if(e.getValue()==DisplacementType.REMOVE_OR_EQUIP) {
					Main.game.getPlayerCell().getInventory().addClothing(e.getKey());
				}
			}
		}
		@Override
		public List<AbstractFetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE, Fetish.FETISH_ANAL_RECEIVING);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
			}
		}
	};

	public static final SexAction PLAYER_TAKE_IT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
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
			return "放任自然";
		}

		@Override
		public String getActionDescription() {
			return "你别无选择，只能趴着让拉尔夫的巨型肉棒在你的小穴中抽插。";
		}

		@Override
		public String getDescription() {
				return "拉尔夫强壮的大手攥住了你"+Main.game.getPlayer().getHipSize().getDescriptor()+"的屁股，在你的小穴中抽送起来，你只得趴在原处，放任自然。"
					+ "你连连发出淫荡的呻吟，把胳膊枕在下巴上，用身下这条细缝求取着额外的折扣。";
		}
		
	};
	
	public static final SexAction PLAYER_TAKE_IT_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
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
			return "放任自然";
		}

		@Override
		public String getActionDescription() {
			return "你别无选择，只能趴着让拉尔夫的巨型肉棒在你的屁股中抽插。";
		}

		@Override
		public String getDescription() {
			return "拉尔夫强壮的大手攥住了你"+Main.game.getPlayer().getHipSize().getDescriptor()+"的屁股，在你的菊穴中抽送起来，你只得趴在原处，放任自然。"
				+ "你连连发出淫荡的呻吟，把胳膊枕在下巴上，用这道后门求取着额外的折扣。";
		}
		
	};

	public static final SexAction PLAYER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS)),
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
			return "既然拉尔夫的大屌换了个洞插，你就可以挑逗他几句了。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("拉尔夫强硬地摁住你的身体，让你不能动作太大，于是只好说几句挑逗的骚话。"
					+ "你回过头去，轻咬着下唇请求道，");
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("嗯嗯！我的小穴都属于你哦！"));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("太爽了！当我是匹小母马，给我配种吧！"));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("啊！拉尔夫！你，你的肉棒好大！"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("啊，啊啊！好爽！继续操！"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

	};
	
	public static final SexAction PLAYER_DIRTY_TALK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS)),
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
			return "既然拉尔夫的大屌换了个洞插，你就可以挑逗他几句了。";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("拉尔夫强硬地摁住你的身体，让你不能动作太大，于是只好说几句挑逗的骚话。"
					+ "你回过头去，轻咬着下唇请求道，");
			
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("嗯嗯！我的小菊花全都是你的！"));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("太爽了！我就是你的小母马！"));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("啊！拉尔夫！你，你的肉棒好大！"));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parsePlayerSpeech("啊，啊啊！好爽！继续操！"));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
	};

	
	// Partner actions:

	public static final SexAction PARTNER_CUSTOMER = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.getTurn() - SexFlags.customerTurnAppearance >= 3; // Have 2 turns of non-customer at minimum
		}
		@Override
		public String getActionTitle() {
			return "有顾客！";
		}
		@Override
		public String getActionDescription() {
			return "";
		}
		@Override
		public String getDescription() {
			return "拉尔夫一下子停下了腰部的动作，你正想着出了什么差错，就听见了他向顾客的问好声。"
					+ "你听见那名顾客沉重的步伐越来越近，一直到了柜台对面，如果此时不保持安静的话，他肯定就知道你在下面了！"
					+ "<br/><br/>"
					+ "<b>现在有</b><b style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>一位顾客</b><b>在柜台对面！</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = true;
			SexFlags.customerTurnAppearance = Main.sex.getTurn();
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "说骚话";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("拉尔夫向你弯下腰，你感觉到他的鸡巴向后滑动了一点，");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("没错，继续赚取你的折扣吧！", Main.game.getNpc(Ralph.class)));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("啊……你很擅长这个！", Main.game.getNpc(Ralph.class)));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("只需要记住别闹出太大动静！", Main.game.getNpc(Ralph.class)));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("继续，你~嗯嗯~做得很好！", Main.game.getNpc(Ralph.class)));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK_VAGINAL = new SexAction(
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
			return "说骚话";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("你感受到拉尔夫不断向下摸索揉捏你"+Main.game.getPlayer().getAssSize().getDescriptor()+"屁股，他呻吟着，");
				switch(Util.random.nextInt(4)){
					case 0:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("真不敢相信你居然想要这个！", Main.game.getNpc(Ralph.class)));
						break;
					case 1:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("太爽了！操……", Main.game.getNpc(Ralph.class)));
						break;
					case 2:
						UtilText.nodeContentSB.append(UtilText.parseSpeech("太棒了，操！操你的小穴真爽！", Main.game.getNpc(Ralph.class)));
						break;
					default: 
						UtilText.nodeContentSB.append(UtilText.parseSpeech("妈的！你真的争取到了这个折扣，对吧？", Main.game.getNpc(Ralph.class)));
						break;
				}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_DIRTY_TALK_ANAL = new SexAction(
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
			return "说骚话";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Main.sex.isAnyOngoingActionHappening();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("你感受到拉尔夫不断向下摸索揉捏你"+Main.game.getPlayer().getAssSize().getDescriptor()+"屁股，他呻吟着，");
			switch(Util.random.nextInt(4)){
				case 0:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("真不敢相信你居然要求我这样！", Main.game.getNpc(Ralph.class)));
					break;
				case 1:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("太爽了！操……", Main.game.getNpc(Ralph.class)));
					break;
				case 2:
					UtilText.nodeContentSB.append(UtilText.parseSpeech("啊哈~操！这操起来真爽！", Main.game.getNpc(Ralph.class)));
					break;
				default: 
					UtilText.nodeContentSB.append(UtilText.parseSpeech("你真的争取到了这个折扣，对吧？", Main.game.getNpc(Ralph.class)));
					break;
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
	};
	
	public static final SexAction PARTNER_COMMAND_START_ORAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
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
			return "要求口交";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Main.sex.isAnyNonSelfOngoingActionHappening();
		}

		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Ralph.class))!=PARTNER_COMMAND_START_ORAL) {
				return SexActionPriority.HIGH;
			} else {
				return  SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("拉尔夫向你弯下腰，你听到他不耐烦的嘟哝声，"
					+ UtilText.parseSpeech("你还在磨蹭什么？你明明说好的，别让我久等！", Main.game.getNpc(Ralph.class)));
			
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}

	};
	
	public static final SexAction PARTNER_START_ORAL = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "开始口交";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public SexActionPriority getPriority() {
			if(Main.sex.getLastUsedSexAction(Main.game.getNpc(Ralph.class))==PARTNER_COMMAND_START_ORAL) {
				return SexActionPriority.HIGH;
			} else {
				return SexActionPriority.NORMAL;
			}
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("拉尔夫向你弯下腰，你听到他焦躁的嘟哝声，"
					+ UtilText.parseSpeech("我会让你见识一下怎么开始！", Main.game.getNpc(Ralph.class))
					+"<br/>"
					+ "你还来不及反应，他就突然把臀部压向你。"
					+ "你还没意识到发生了什么，他硬实平坦的马屌就迅速把你的头顶向身后的柜台，猛烈地摩擦你的脸。"
					+ "拉尔夫快速摆正又再次坚决地插入，你感到嘴唇被分开，他饥渴的马屌从你唇边拉开，撞进嘴里。"
					+ "当你意识发生了什么时，你终于开始遵守诺言，吮吸拉尔夫的鸡巴。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(SexFlags.customerAtCounter) {
				return "与客户打交道(接受口交)";
			}
			return "接受口交";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("拉尔夫仍然一动不动地看守着商店，你继续努力地吮吸他的鸡巴。"
						+ "他时不时地轻推臀部，帮助你将他巨大的阴茎容纳到喉咙，但除此之外不能为你的口交提供什么帮助。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE_VAGINAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "收屌入“鞘”";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("腿间持续的抽插逐渐慢了下来，你微微扭头，想看看发生了什么事情。"
					+ "拉尔夫微笑着看向你，你发现到他的胸口起起伏伏，原来只是放慢片刻来喘口气。"
					+ "他将整根肉棒都沉入你贪婪的小穴中，对着你"+Main.game.getPlayer().getAssSize().getDescriptor()+"的屁股又揉又捏，你也重新把脑袋枕回了[pc.arms]上，"
							+ "两人一面稍事休息，一面还是不断地呻吟喘息。"
					+ "<br/><br/>"
					+ "不到一分钟后，你忽然感到他那马屌又宽又平的龟头从蜜穴中退了出来，正为这股空虚感悲伤地小声呜咽着，"
					+ "拉尔夫反而更紧抓住你的屁股，挺身向前，再次如刚才那般节奏活塞运动起来，惹得你那呜咽顿时变成了高声的尖叫。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_PASSIVE_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.THREE_NORMAL,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "收屌入“鞘”";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("后庭里的抽插逐渐慢了下来，你微微扭头，想看看发生了什么事情。"
					+ "拉尔夫微笑着看向你，你发现到他的胸口起起伏伏，原来只是放慢片刻来喘口气。"
					+ "他将整根肉棒都沉入你贪婪的菊穴中，对着你"+Main.game.getPlayer().getAssSize().getDescriptor()+"的屁股又揉又捏，你也重新把脑袋枕回了[pc.arms]上，"
							+ "两人一面稍事休息，一面还是不断地低吟喘息。"
					+ "<br/><br/>"
					+ "不到一分钟后，你忽然感到他那马屌又宽又平的龟头从后穴中退了出来，正为这股空虚感悲伤地小声呜咽着，"
					+ "拉尔夫反而更紧抓住你的屁股，挺身向前，再次如刚才那般节奏活塞运动起来，惹得你那呜咽顿时变成了难以抑制的浪叫。");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_DEEP_THROAT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			if(SexFlags.customerAtCounter) {
				return "应付顾客(深喉)";
			}
			return "深喉";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.customerAtCounter)
				UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
						+ "<br/><br/>"
						+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
								+ "<br/><br/>");
			
			UtilText.nodeContentSB.append("一小声铃响传入你的耳中，这意味着商店里唯一的顾客已经离开，此时此刻只剩下了你和拉尔夫。"
					+ "随之而来的事情就很明显了，你忽然感到脑袋两边传来了一股强烈的压迫感。"
					+ "你抬起头，之间拉尔夫用双手抱住你的脑袋，没等你做什么反应，他就前踏一步，同时把你向他的下体方向拉来，将他那巨大的马肉棒深深塞进你的咽喉。"
					+ "顿时泪水从严重涌出，面前的一切都模糊起来，正当你以为自己要氧气耗尽晕厥过去的时候，拉尔夫就放开了你。"
					+ "你弯着身子深深地跪了下去，那根雄伟的肉棒也暂时从你的嘴里滑出，你不断咳嗽着，"
					+ "一道口水和先走液混合而成的粘稠液体从嘴里淌了出来，你准备休息一会儿。"
					+ "正当这个时候，铃声再次响起，拉尔夫往前踏了一布，用柜台遮住了下半身，并且又一次将鸡巴送进了你的嘴里。");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			SexFlags.customerAtCounter = false;
		}
		
	};
	
	public static final SexAction PARTNER_REDUCES_DISCOUNT = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "降低折扣";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.alertedCustomer;
		}

		@Override
		public String getDescription() {
			return "你感觉拉尔夫的肉棒抽回去了一点，原来是他弓起了身子看向你，"
					+ UtilText.parseSpeech("我之前是不是告诉过你，这是一家很有信誉的商店？！我已经说得很清楚了，搞出这么大的噪声有什么后果！"
							+ "扣掉百分之五，不接受反驳！", Main.game.getNpc(Ralph.class))
					+"<br/>"
					+ "尽管他平常一直表现得礼貌而友好，但责备你的时候，却听起来相当生气，你忽然觉得让他的店铺蒙羞有些自责。"
					+ "但话又说回来，是他提出的这个建议，所以要有错也不是全怪你一个人……"
					+ "<br/><br/>"
					+ "<b>你会得到</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b><b>的打折。</b>";
		}

		@Override
		public void applyEffects() {
			SexFlags.alertedCustomer=false;
			SexFlags.customerAtCounter = false;
			if(SexFlags.ralphDiscount>0)
				SexFlags.ralphDiscount-=5;
		}
	};
	
	public static final SexAction PARTNER_PENETRATES = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
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
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				return "操[pc.herHim]";
			}
			return "给[pc.herHim]播种";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.askedForBigDiscount && Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.alertedCustomer){
				UtilText.nodeContentSB.append("拉尔夫俯身靠近你，"
						+ UtilText.parseSpeech("我之前是不是告诉过你，这是一家很有信誉的商店？！我已经说得很清楚了，搞出这么大的噪声有什么后果！"
								+ "扣掉百分之五，不接受反驳！", Main.game.getNpc(Ralph.class))
						+"<br/>"
						+ "尽管他平常一直表现得礼貌而友好，但责备你的时候，却听起来相当生气，你忽然觉得让他的店铺蒙羞有些自责。"
						+ "但话又说回来，是他提出的这个建议，所以要有错也不是全怪你一个人……"
						+ "<br/><br/>"
						+ "<b>你会得到</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b><b>的折扣。</b>"
						+ "<br/><br/>");
			}
				
			UtilText.nodeContentSB.append("拉尔夫后撤一步，踢掉他的裤子，径直走到了商店的前门。"
					+ "你听见他锁上了前门， 然后又拉死了百叶窗，原来最后一名顾客不久前也已经离开了。"
					+ "你想看看接下来会发生什么，于是从柜台底下过了出来，准备起身。"
					+ "<br/><br/>"
					+ "几乎是瞬间，一阵剧痛传遍了腿部，你轻呼一声，俯身抓住了柜台边缘。"
					+ " 突然，你感到一双有力的大手抓住了你的臀部，你还没来得及反应，"
					+(Main.game.getPlayer().isTaur()
						?"拉尔夫强硬地将你往前推去，迫使你骑乘在柜台上。"
						:"拉尔夫把你脸朝下按在了柜台上。")
					+ " 他这般支配者式的对待让你不禁发出一声轻吟，他粗暴地揉捏着你"+Main.game.getPlayer().getAssSize().getDescriptor()+"的屁股，在你耳边咆哮道，"
					+ UtilText.parseSpeech(
						(Main.sex.getCharacterPerformingAction().isWearingCondom()
							?"就这么想要折扣，啊？！跟你说，可惜了你非要用套，但只要让我操，我就再给你两成。"
							:(Main.game.getPlayer().isVisiblyPregnant()
									?"就这么想要折扣，啊？！跟你说，可惜了你已经怀了，但只要让我操，我就再给你两成。"
									:"就这么想要折扣，啊？！行啊，那就让我在你肚子里添几只小马驹子，我就再给你两成五！")
						), Main.game.getNpc(Ralph.class))
					+"<br/><br/>"
					+ "这时候你早就把折扣忘到九霄云外了，满脑子只是想品尝一番那美味的大屌送入腿间的感觉。"
					+ "还没等拉尔夫把话说完，你就呻吟着同意了，他轻笑一声，便对着你"
					+ (Main.game.getPlayer().getVaginaType()==VaginaType.HORSE_MORPH?"饥渴的马穴。":"骚热的小穴。"));
			
			UtilText.nodeContentSB.append("<br/><br/>");
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				if(Main.game.getPlayer().isTaur()) {
					UtilText.nodeContentSB.append("他小心翼翼地避免让你的孕肚撞到柜台，将你的上半身按在了台面上。");
				} else {
					UtilText.nodeContentSB.append("他小心翼翼地避免让你的孕肚撞到柜台，引导你向前趴上台面，让你骑乘在柜台上。");
				}
				UtilText.nodeContentSB.append(" 他抓住你的臀部，毫不犹豫地将他那根粗大的肉棒对准你等待着的小穴，随着他开始向前挺入，你发出了一声迷乱的尖叫。");
				
			} else {
				if(Main.game.getPlayer().isTaur()) {
					UtilText.nodeContentSB.append("引导你向前趴上柜台，让你骑乘在台面上之后，"
							+ "拉尔夫毫不耽搁地将硕大的肉棒对准你等待已久的小穴，当他开始向前挺进时，你发出一声狂乱的尖叫。");
				} else {
					UtilText.nodeContentSB.append("拉尔夫将你的上半身按在柜台上，毫不耽搁地将硕大的肉棒对准你等待已久的小穴，当他开始向前挺进时，你发出一声狂乱的尖叫。");
				}
			}
			UtilText.nodeContentSB.append(
					" 他宽大的冠状龟头慢慢挤入你体内，当你的褶皱淫荡地在他那兽性的马屌周围张开时，你喘息着扭动身体。"
					+ "拉尔夫上前一小步，把那急不可耐的兄弟深深送入了你饥渴的细缝，在柜台上把你操了起来，让你喘息呻吟连连。"
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "<b>你会得到一个</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
							+(SexFlags.ralphDiscount+(Main.sex.getCharacterPerformingAction().isWearingCondom()||Main.game.getPlayer().isVisiblyPregnant()?20:25)-(SexFlags.alertedCustomer?5:0))
					+"%</b><b>打折。</b>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String applyPreParsingEffects() {
			Main.sex.setSexManager(
					new SMRalphDiscountBig(
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM_SEX)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB_SEX))));
			return "";
		}
		@Override
		public void applyEffects() {
			Main.game.getPlayer().displaceClothingForAccess(CoverableArea.VAGINA, new ArrayList<>());
			AbstractClothing trousers = Main.game.getNpc(Ralph.class).getClothingInSlot(InventorySlot.LEG);
			if(trousers!=null) {
				Main.game.getNpc(Ralph.class).unequipClothingOntoFloor(trousers, true, Main.game.getNpc(Ralph.class));
			}
			
			SexFlags.customerAtCounter = false;
			if(SexFlags.alertedCustomer) {
				if(SexFlags.ralphDiscount>0) {
					SexFlags.ralphDiscount-=5;
				}
			}
			
			SexFlags.alertedCustomer=false;
			if(Main.sex.getCharacterPerformingAction().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}
		}
		
	};
	
	public static final SexAction PARTNER_PENETRATES_ANUS = new SexAction(
			SexActionType.START_ONGOING,
			ArousalIncrease.FOUR_HIGH,
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
			return "操[pc.her]屁股";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return SexFlags.askedForBigDiscount && !Main.game.getPlayer().hasVagina();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(SexFlags.alertedCustomer){
				UtilText.nodeContentSB.append("拉尔夫俯身靠近你，"
						+ UtilText.parseSpeech("我之前是不是告诉过你，这是一家很有信誉的商店？！我已经说得很清楚了，搞出这么大的噪声有什么后果！"
								+ "扣掉百分之五，不接受反驳！", Main.game.getNpc(Ralph.class))
						+"<br/>"
						+ "尽管他平常一直表现得礼貌而友好，但责备你的时候，却听起来相当生气，你忽然觉得让他的店铺蒙羞有些自责。"
						+ "但话又说回来，是他提出的这个建议，所以要有错也不是全怪你一个人……"
						+ "<br/><br/>"
						+ "<b>你会得到</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+(SexFlags.ralphDiscount>0?SexFlags.ralphDiscount-5:0)+"%</b><b>的折扣。</b>"
						+ "<br/><br/>");
			}
			
			UtilText.nodeContentSB.append("拉尔夫后撤一步，踢掉他的裤子，径直走到了商店的前门。"
					+ "你听见他锁上了前门， 然后又拉死了百叶窗，原来最后一名顾客不久前也已经离开了。"
					+ "你还来不及从柜台下走出，拉尔夫高大的身影就又出现了，他挡住你的去路。"
					+ "<br/><br/>"
					+ UtilText.parseSpeech("你想要更多的折扣吗？！你又打算怎么来争取呢？", Main.game.getNpc(Ralph.class))
					+"他交叉双臂，皱着眉头朝你问道。"
					+ "<br/><br/>"
					+ "尽管他对你的口交服务非常满意，但你知道他还需要一些说服才会愿意插你其他的洞，"
					+ "你将衣服拉到一边，尴尬地挪动着步子，弯腰露出你"+Main.game.getPlayer().getAssCapacity().getDescriptor()+"的肛门。"
					+ "拉尔夫竭力压抑他饥渴的呻吟，但你左右摇晃着"+Main.game.getPlayer().getHipSize().getDescriptor()+"臀部，令他无法再保持冷静。"
					+ "<br/><br/>"
					+ "突然，你感觉有双强有力的手抓住了你的屁股，还来不及反应，拉尔夫就把你从柜台下扯了出来，又迅速推倒你，把你的脸贴在柜台上。"
					+ "你因他的粗暴对待而发出愉悦的呻吟。他粗暴地摸索揉捏你"+Main.game.getPlayer().getAssSize().getDescriptor()+"屁股，俯身在你耳边怒吼，"
					+ UtilText.parseSpeech(
							"你知道我通常不这么做……但是，他妈的，你的屁股真棒！如果你让我碰，我就再给你打八折。", Main.game.getNpc(Ralph.class))
					+"<br/><br/>"
					+ "但你已经毫不在意折扣多少了，你现在只想要他可口的鸡巴顶进你的后庭。"
					+ "拉尔夫话还没说完，你就迫切地同意了，他立刻把他巨大的鸡巴对准你的屁眼。"
					+ "你感到他宽大平坦的马屌磨进你淫荡的屁眼，慢慢地把他动物化的马屌推向深处，你扭动着身体，发出尖叫。"
					+ "拉尔夫向前迈步，将他迫不及待的马屌猛塞进你饥渴的后庭，在商店柜台上与你做爱，你发出一声震耳欲聋的呻吟。"
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ "<b>你会得到一个</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
						+(SexFlags.ralphDiscount+20-(SexFlags.alertedCustomer?5:0))
						+"%</b><b>打折。</b>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String applyPreParsingEffects() {
			Main.sex.setSexManager(
					new SMRalphDiscountBig(
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Ralph.class), SexSlotUnique.RALPH_DOM_SEX)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotUnique.RALPH_SUB_SEX))));
			return "";
		}
		@Override
		public void applyEffects() {
			Main.game.getPlayer().displaceClothingForAccess(CoverableArea.ANUS, new ArrayList<>());
			AbstractClothing trousers = Main.game.getNpc(Ralph.class).getClothingInSlot(InventorySlot.LEG);
			if(trousers!=null) {
				Main.game.getNpc(Ralph.class).unequipClothingOntoFloor(trousers, true, Main.game.getNpc(Ralph.class));
			}
			
			SexFlags.customerAtCounter = false;
			if(SexFlags.alertedCustomer) {
				if(SexFlags.ralphDiscount>0) {
					SexFlags.ralphDiscount-=5;
				}
			}
			
			SexFlags.alertedCustomer=false;
			if(Main.sex.getCharacterPerformingAction().isWearingCondom() || Main.game.getPlayer().isVisiblyPregnant() || Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				SexFlags.ralphDiscount+=20;
			} else {
				SexFlags.ralphDiscount+=25;
			}

		}
		
	};
	
	public static final SexAction PARTNER_ROUGH_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "连续猛击[pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			StringBuilder sb = new StringBuilder();
			
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				if(Main.game.getPlayer().isTaur()) {
					sb.append("抓住你"+Main.game.getPlayer().getHipSize().getDescriptor()+"的臀部，拉尔夫将你稍稍向后拉，当你用后腿向后退时，"
							+ "他突然将硕大的肉棒整根撞入你体内，撞得你向前晃动，并发出一声狂乱的哀嚎。");
				} else {
					sb.append("拉尔夫抓住你"+Main.game.getPlayer().getHipSize().getDescriptor()+"的臀部，把你稍稍往后拉了一些，让你的孕肚安全地远离柜台边缘。"
							+ " 确认给你留出了足够的空间后，他猛地将那根粗大的肉棒整根捅了进来，你被顶得身体前倾，发出一声迷乱的哀叫。");
				}
				sb.append(" 你好色的反应让拉尔夫备受鼓舞，他开始迅速地前后挺动臀部，毫不留情地猛撞着你那淫荡的小穴。"
								+ " 他的双腿分立在你两腿外侧，所有的动作都集中在臀部上，你的尖叫声随着他那根巨大的马屌如打桩机般不断贯穿你的穴缝，被撞得一声一声断断续续地溢出来。");
				
			} else {
				sb.append("拉尔夫抓住你"+Main.game.getPlayer().getHipSize().getDescriptor()+"的臀部，用力把你按在柜台上，整个人沉沉地压在你身上，开始毫不留情地猛操你那淫荡的小穴。"
							+"把你的身体当作垫子，他只靠臀部发力，每一次猛烈的撞击都让你发出断断续续的尖叫，那根粗大的马屌在你双腿间疯狂抽插。");
			}
			
			sb.append((Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"每次他深入尽头，他沉甸甸的囊袋总是上下拍打你，令你忘记它们被包在你所要求戴上的避孕套里。"
						:"每当他顶到最深处，沉甸甸的囊袋随着惯性荡起来拍在你身上，仿佛在提醒你，接下来要被灌满的东西都是从那里来的。"));
			
			return sb.toString();
		}
		
	};
	
	public static final SexAction PARTNER_ROUGH_FUCK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "连续猛击[pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return "拉尔夫抓着你"+Main.game.getPlayer().getHipSize().getDescriptor()+"的屁股，用力将你摁倒在柜台，重重压在你身上，无情地拍打你淫荡的屁股。"
				+ "他把你当作肉垫，专心顶着屁股，巨大的马屌捅入后庭，你爆发出极乐的尖叫。"
				+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
					?"每次他深入尽头，他沉甸甸的囊袋总是上下拍打你，令你忘记它们被包在你所要求戴上的避孕套里。"
					:"每次他深入尽头，他沉甸甸的囊袋总是上下拍打你，暗示你被内射的来源。");
		}
		
	};
	
	public static final SexAction PARTNER_NORMAL_FUCK = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "操[pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
			return (Main.game.getPlayer().isVisiblyPregnant()
						?"拉尔夫依旧紧握住你的屁股，他前后摇摆，用巨大乌黑的马屌填满你。"
						:"拉尔夫依旧紧握住你的屁股，把你摁在柜台上前后摇晃，用巨大乌黑的马屌填满你。")
					+ "他那沉甸甸的睾丸来回摆动着，迫不及待想要清空它们强而有力的负荷 "
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"射入你提供的避孕套里。"
						:(Main.game.getPlayer().isVisiblyPregnant()?"深深灌进你饥渴的穴道。":"深深灌进你等待已久的子宫。"));
		}
		
	};
	
	public static final SexAction PARTNER_NORMAL_FUCK_ANAL = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.THREE_NORMAL,
			CorruptionLevel.ONE_VANILLA,
			Util.newHashMapOfValues(new Value<>(SexAreaPenetration.PENIS, SexAreaOrifice.ANUS)),
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		@Override
		public String getActionTitle() {
			return "操[pc.herHim]";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public String getDescription() {
				return "拉尔夫依旧紧握住你的屁股，把你摁在柜台上前后摇晃，用巨大乌黑的马屌填满你。"
					+ "他那沉甸甸的睾丸来回摆动着，迫不及待想要清空它们强而有力的负荷 "
					+ (Main.sex.getCharacterPerformingAction().isWearingCondom()
						?"射入你提供的避孕套里。"
						:"插入你等待已久的屁眼深处。");
		}
		
	};
	

	public static final SexAction PLAYER_PREPARE = new SexAction(
			SexActionType.PREPARE_FOR_PARTNER_ORGASM,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		@Override
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.PLAYER_ONLY;
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
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "你做好准备，发出轻声[pc.moan]进一步催动[npc.Name]达到高潮。";
				case DOM_NORMAL:
					return "你做好准备，发出[pc.a_moan+]进一步催动[npc.name]达到高潮。";
				case DOM_ROUGH:
					return "你做好准备，发出[pc.a_moan+]进一步催动[npc.name]达到高潮。";
				case SUB_EAGER:
					return "你做好准备，发出[pc.a_moan+]进一步催动[npc.name]达到高潮。";
				case SUB_NORMAL:
					return "你做好准备，发出一声[pc.a_moan]进一步催动[npc.name]达到高潮。";
				case SUB_RESISTING:
					return "你发出[pc.a_moan+]，拼命想在[npc.name]高潮之前躲开。";
			}
			
			return "";
		}
	};
	
	public static final SexAction PLAYER_ORGASM = new SexAction(
			SexActionType.ORGASM,
			ArousalIncrease.FIVE_EXTREME,
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
			return "你已经到达了快感的极限，再无法阻止高潮的到来。";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("当拉尔夫乌黑的巨大鸡巴插入你娇嫩的喉咙深处时，你感到自己达到了高潮，大脑一片空白，只是难以自已地发出了模糊的[pc.moan]。");
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("拉尔夫又黑又大的鸡巴狠狠的刺入你[pc.pussy+]深处，你马上就要高潮，大脑一片空白，不禁发出[pc.a_moan+]。");
				
			} else if(Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).contains(SexAreaPenetration.PENIS)) {
				UtilText.nodeContentSB.append("拉尔夫又黑又大的鸡巴狠狠的刺入你[pc.asshole+]深处，你马上就要高潮，大脑一片空白，不禁发出[pc.a_moan+]。");
				
			} else {
				UtilText.nodeContentSB.append("你感觉股间升腾起一股难以抑制的热流，随着一声[pc.a_moan+]，你到达了高潮。");
			}
			
			// PENIS ORGASM:
			
			if(Main.game.getPlayer().hasPenisIgnoreDildo()){

				UtilText.nodeContentSB.append("<br/><br/>");
				
				if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.KNOTTED)) {
					UtilText.nodeContentSB.append("你低喘着发出悠长的呻吟，血液涌向了[pc.cock+]根部的结，膨大肿胀起来。你马上就要射精了。");
					
				} else if(Main.game.getPlayer().hasPenisModifier(PenetrationModifier.FLARED)) {
					UtilText.nodeContentSB.append("你低喘着发出悠长的呻吟，血液涌向[pc.cock+]宽大的龟头，膨大肿胀起来。你马上就要射精了。");
					
				} else {
					UtilText.nodeContentSB.append("你低喘着发出悠长的呻吟，[pc.cock+]抽搐起来。你马上就要射精了。");
					
				}
				
				// Describe cum amount:
				UtilText.nodeContentSB.append("你"+Main.game.getPlayer().getTesticleSize().getDescriptor()+"的玉袋一紧");
				switch (Main.game.getPlayer().getPenisOrgasmCumQuantity()) {
					case ZERO_NONE:
						UtilText.nodeContentSB.append("，你这才发现自己竟然连一滴都没有了。");
						break;
					case ONE_TRICKLE:
						UtilText.nodeContentSB.append("，你感到几滴[pc.cum]挤了出来");
						break;
					case TWO_SMALL_AMOUNT:
						UtilText.nodeContentSB.append("，你感到有一些[pc.cum]挤了出来");
						break;
					case THREE_AVERAGE:
						UtilText.nodeContentSB.append("，你感到[pc.cum]挤了出来");
						break;
					case FOUR_LARGE:
						UtilText.nodeContentSB.append("，你感到[pc.cum]射了出来");
						break;
					case FIVE_HUGE:
						UtilText.nodeContentSB.append("，你感到[pc.cum]溢了出来");
						break;
					case SIX_EXTREME:
						UtilText.nodeContentSB.append("，你感到[pc.cum]溢了出来");
						break;
					case SEVEN_MONSTROUS:
						UtilText.nodeContentSB.append("，你感到[pc.cum]溢了出来");
						break;
					default:
						break;
				}
				
				// Describe where cum is going:
				if(Main.game.getPlayer().getPenisOrgasmCumQuantity()!=CumProduction.ZERO_NONE){
					if(Main.game.getPlayer().isWearingCondom()) {
						UtilText.nodeContentSB.append("，落在了避孕套里。");
						
					} else if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)) {
						UtilText.nodeContentSB.append("，溅在了你的[pc.lowClothing(PENIS)]上。");
						
					} else {
						UtilText.nodeContentSB.append("，溅在了脚下的地上。");
					}
				}
			}
			
			// VAGINA ORGASM:
			
			if (Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
				
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "渴望、战栗的热流冲向你[npc.pussy+]，你疯狂地尖叫，强烈而纯粹的快感席卷而过。");
				
				if (!Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()) {
					GameCharacter characterPenetrating = Main.sex.getCharacterOngoingSexArea(Main.game.getPlayer(), SexAreaOrifice.VAGINA).get(0);
					switch(Main.sex.getFirstOngoingSexAreaPenetration(Main.game.getPlayer(), SexAreaOrifice.VAGINA)) {
						case FINGER:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append("你把[pc.pussy+]里面的手指狠狠地弓了起来"
										+"，来回在其中搅动着，最后阴道内的肌肉忽然收缩，紧紧吸住了插入的手指，你也不住地发出高声的呻吟。");
							} else {
								UtilText.nodeContentSB.append("[npc.NamePos]的手指继续在你[pc.pussy+]​​中搅动"
										+"，随后阴道内的肌肉忽然收缩，紧紧吸住了插入的手指，你也不住地发出高声的呻吟。");
							}
							break;
						case PENIS:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append("你在高潮的途中继续在自己体内抽插，阴道的肌肉紧紧包裹挤压着你[pc.cock+]，你发出了一连串高亢的呻吟。");
							} else {
								UtilText.nodeContentSB.append("拉尔夫在你高潮的途中依然不停地在你[pc.pussy+]中抽插，阴道内的肌肉忽然收缩，紧紧吸住了那根马肉棒，你也不住地发出高声的呻吟。"
										+ "在阴道的榨取感之下，拉尔夫更进一步将他黢黑的粗壮马屌深刺入你[pc.pussy+]，整个身子都压了上来。"
										+ "你感受到他壮硕的雄性身躯紧紧压在你的背上，那股热量传递而来，你顿时崩溃似的呻吟起来，那根马肉棒终于将你征服，你成了全新的性战利品。");
							}
							break;
						case TAIL:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append("你在高潮途中，继续用尾巴抽插着，阴道的肌肉紧紧包裹挤压着入侵的物体，你发出了一连串高亢的呻吟。");
							} else {
								UtilText.nodeContentSB.append("[npc.NamePos]在你高潮的途中，继续用尾巴在你[pc.pussy+]"
										+"里抽插，随后阴道的肌肉紧紧包裹挤压着入侵的物体，你发出了一连串高亢的呻吟。");
							}
							break;
						case TONGUE:
							if(characterPenetrating.isPlayer()) {
								UtilText.nodeContentSB.append("你在高潮途中，继续将舌头深深送入你[pc.pussy+]"
										+"，随后阴道的肌肉紧紧包裹挤压着入侵的物体，你发出了一连串高亢的呻吟。");
							} else {
								UtilText.nodeContentSB.append("[npc.Name]在你高潮的途中，继续贪婪地舔弄亲吻着你[pc.pussy+]"
										+"，随后阴道的肌肉紧紧包裹挤压着入侵的物体，你发出了一连串高亢的呻吟。");
							}
							break;
						default:
							break;
					}
					
				} else { // No penetration:
					UtilText.nodeContentSB.append("你[pc.pussy+]忽然紧缩起来，但随之而来的空虚感却让你顿时有些失望，几乎压倒了股间传来的快感。");
				}

				UtilText.nodeContentSB.append("随着最后一声纯粹快感的尖叫划过耳膜，高潮终于降临，过了一会儿，你便瘫倒了，满足地不住喘息着。");
			}
			
			// MOUND ORGASM:
			if (Main.game.getPlayer().getPenisType()==PenisType.NONE && Main.game.getPlayer().getVaginaType()==VaginaType.NONE) {
				UtilText.nodeContentSB.append("<br/><br/>"
									+ "尽管没有性器官，你还是能感受到一股无法抑制地暖流迅速在下腹部产生，你还没有反应过来，"
									+ "这感觉便汇聚到下体，你顿时被一股令人目眩神迷的高潮冲击了。"
									+ "你忽然夹紧了腿，一阵无可抑制的浪叫脱口而出，突如其来的高潮袭击了你玩偶般平坦的下体。"
									+ "一阵热烈的快感辐射了全身，你浑身颤抖起来，汹涌的狂喜席卷而来。");
			}
			
			if(SexFlags.customerAtCounter) {
				UtilText.nodeContentSB.append("<br/><br/>"
						+ "你从高潮状态中缓过来，听到拉尔夫向刚刚听到你不小的高潮声的顾客提供折扣。"
						+ "你倒吸一口凉气，因为你刚刚忘记了他们的存在。");
			}
			
			return UtilText.parse(Main.sex.getCharacterTargetedForSexAction(this), UtilText.nodeContentSB.toString());
		}

		@Override
		public void applyEffects() {
			if (!Main.game.getPlayer().isCoverableAreaExposed(CoverableArea.PENIS)
					&& Main.game.getPlayer().getPenisType()!=PenisType.NONE
					&& !Main.game.getPlayer().isWearingCondom()
					&& Main.game.getPlayer().getPenisOrgasmCumQuantity() != CumProduction.ZERO_NONE)
				Main.game.getPlayer().getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(Main.game.getPlayer(), true);
			
			if(SexFlags.customerAtCounter) {
				if(SexFlags.ralphDiscount>0)
					SexFlags.ralphDiscount-=5;
			}
			
			SexFlags.customerAtCounter = false;
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
		public SexActionLimitation getLimitation() {
			return SexActionLimitation.NPC_ONLY;
		}
		
		@Override
		public String getActionTitle() {
			return "准备";
		}

		@Override
		public String getActionDescription() {
			return "你能感觉到[pc.name]马上就要高潮了。做好准备。";
		}
		
		@Override
		public String getDescription() {
			switch(Main.sex.getSexPace(Main.game.getPlayer())) {
				case DOM_GENTLE:
					return "[npc.Name]将要把你推向高潮时，发出轻柔的鼓励的[npc.moan]。";
				case DOM_NORMAL:
					return "[npc.Name]想要把你推向高潮时，发出[npc.a_moan+]鼓励。";
				case DOM_ROUGH:
					return "[npc.Name]想要把你推向高潮时，发出[npc.a_moan+]鼓励。";
				case SUB_EAGER:
					return "[npc.Name]想要把你推向高潮时，发出[npc.a_moan+]鼓励。";
				case SUB_NORMAL:
					return "[npc.Name]想要把你推向高潮时，发出[npc.a_moan+]鼓励。";
				case SUB_RESISTING:
					return "[npc.Name]发出[npc.a_moan+]，极力想在你高潮前从你身边抽离。";
			}
			
			return "";
		}
	};
	
	public static final SexAction PARTNER_ORGASM = new SexAction(
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
			return "保持原位";
		}

		@Override
		public String getActionDescription() {
			return "你已经到达了快感的极限，再无法阻止高潮的到来。";
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return true;
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA).isEmpty()){
				UtilText.nodeContentSB.append("拉尔夫发出低沉的咕哝声。你感受到他的肉棒在体内抽动，倒吸了一口气。"
						+ "他咆哮着把他巨大的马屌深深捅进小穴，你感觉到他有力的阴茎往你体内推进，把你推向高潮的边缘，发出一声呻吟。"
						+ "<br/>"
						+ "拉尔夫用阴茎根部摩擦你的阴唇，在射精时，他绷紧了巨大的阴囊。");
				
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append("随着他有力的摇动，他巨大的鸡巴在避孕套里射出高负荷的精液，你感觉到火热的精液从你体内的橡胶中膨胀开来，发出了满意的叹息。"
							+ "他的鸡巴继续抽插了许久，巨大的黑色马屌迅速将他的精液安全射入你提供的安全套中。"
							+ "<br/><br/>"
							+ "拉尔夫排空阴囊存量，退后一步，从你被操透的小穴中快速抽出他软化的鸡巴。"
							+ "你突然感到极度空虚，轻叹了口气，你翻过身，仰面朝天，开玩笑地把腿盘在拉尔夫身上，"
							+ "他刚戴好避孕套，你便拉他向前，将小穴贴着他软趴趴的马屌摩擦。"
							+ "他把装满精子的避孕套放在你身旁，从你腿的束缚中抽身。你对他噘嘴，他因你的反应笑了，"
							+ "[ralph.speechNoEffects(嘿！我还得继续在这营业呢，记得吗？不管怎么说，我觉得你应该得到更多的折扣……)]");
				} else {
					UtilText.nodeContentSB.append("随着他有力的摇动，他巨大的鸡巴射出高负荷的精液"
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?"，你感到火热的精液将你填满，发出满意的呻吟。"
								:"直捣子宫，你感到滚烫的精液将你填满，发出满意的呻吟。")
							+ "他继续抽插了许久，以确保他巨大的黑色马屌将精液深深注入你饥渴的小穴里。"
							+ (Main.game.getPlayer().isVisiblyPregnant()
								?"你的小穴被拉尔夫黏糊糊的白色马精完全浸湿，感觉到在高潮结束后他放松此前紧捏着臀部的力道。"
								:"你的子宫被拉尔夫黏糊糊的白色马精完全浸润，感觉到在高潮结束后他放松了此前紧捏着臀部的力道。")
							+ "<br/><br/>"
							+ "拉尔夫排空阴囊存量后，退后一步，伴随着一阵湿漉漉的吸吮声，他从你被操透的小穴中快速抽出软化的鸡巴。"
							+ "你突然感到极度空虚，发出一声叹息，感觉到他温暖湿润的精液正从你的入口处溢出。"
							+(Main.game.getPlayer().isTaur()
								?"你回头望向身后，调皮地把臀部往拉尔夫身上蹭，用灌满精液的小穴磨蹭着他已经软下来的马屌。"
										+ "不过看来他并不打算再来第二轮，你冲他撅起了嘴；他笑了起来，"
								:"你翻过身仰躺着，俏皮地用双腿缠住拉尔夫，把他拉向自己，用灌满精液的小穴蹭着他已经软下来的马屌。"
									+ " 不过看来他并不打算再来第二轮，你冲他撅起了嘴；他抓住你的双腿抽身退开，看到你的反应后笑了起来，")
							+ "[ralph.speechNoEffects(嘿！我还得继续在这营业呢，记得吗？不管怎么说，我觉得你应该得到更多的折扣……)]");
				}
				
			}else if(!Main.sex.getAllOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS).isEmpty()){
				UtilText.nodeContentSB.append("拉尔夫发出低沉的咕哝声。你感受到他的肉棒在体内抽动，倒吸了一口气。"
						+ "他咆哮着把巨大的马屌深深插入你的屁股，你感觉到有力的阴茎往体内推进，将你推向高潮的边缘，令你发出呻吟。"
						+ "<br/>"
						+ "拉尔夫用阴茎根部摩擦你的肛门，他在射精时绷紧了巨大的阴囊。");
				 
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append("随着他有力的摇动，他巨大的鸡巴在避孕套里射出高负荷的精液，你感觉到火热的精液从你体内的橡胶中膨胀开来，发出了满意的叹息。"
							+ "他的鸡巴继续抽插了许久，巨大的黑色马屌迅速将他的精液安全射入你提供的安全套中。"
							+ "<br/><br/>"
							+ "拉尔夫排空阴囊存量后，退后一步，从你被操透的后庭中快速抽出软化的鸡巴。"
							+ "你突然感到极度空虚，轻叹了口气，你翻过身，仰面朝天，开玩笑地把腿盘在拉尔夫身上，"
							+ "他刚戴好避孕套，你便拉他向前，将下体贴着他软趴趴的马屌摩擦。"
							+ "他把装满精子的避孕套放在你身旁，从你腿的束缚中抽身。你对他噘嘴，他因你的反应笑了，"
							+ "[ralph.speechNoEffects(嘿！我还得继续在这营业呢，记得吗？不管怎么说，我觉得你应该得到更多的折扣……)]");
				} else {
					UtilText.nodeContentSB.append("随着他有力的摇动，他巨大的鸡巴将高负荷的精液射进肛门，你感觉到火热的精液将你填满，发出了满意的呻吟。"
							+ "他继续抽插了许久，以确保他巨大的黑色马屌将精液深深注入你饥渴的后庭里。"
							+ "你的后庭被拉尔夫黏糊糊的白色马精完全浸润，感觉到在高潮结束后他放松了此前紧捏着臀部的力道。"
							+ "<br/><br/>"
							+ "拉尔夫排空阴囊存量后，退后一步，伴随着一阵湿漉漉的吸吮声，他从你被操透的后庭中快速抽出软化的鸡巴。"
							+ "你突然感到极度空虚，发出一声叹息，感觉到他温暖湿润的精液正从你的后庭处溢出。"
							+(Main.game.getPlayer().isTaur()
								?" 你回头越过肩膀看去，调皮地往后顶向拉尔夫，用灌满精液的肛门蹭着他此刻疲软的马屌。"
										+ "不过看来他并不打算再来第二轮，你冲他撅起了嘴；他笑了起来，"
								:" 你翻身仰面躺下，调皮地用双腿缠住拉尔夫，把他往前拉，用灌满精液的肛门蹭着他此刻疲软的马屌。"
									+ " 不过看来他并不打算再来第二轮，你冲他撅起了嘴；他抓住你的双腿抽身退开，看到你的反应后笑了起来，")
							+ "[ralph.speechNoEffects(嘿！我还得继续在这营业呢，记得吗？不管怎么说，我觉得你应该得到更多的折扣……)]");
				}
				
			} else {
				if(SexFlags.customerAtCounter){
					if(SexFlags.alertedCustomer)
						UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，顾客知道柜台下发生的事，咯咯笑着。"
								+ "拉尔夫似乎想尽快打发他们，而且忘记了他刚给你打的折扣！"
								+ "他疏忽的原因很快就清楚了……"
								+ "<br/><br/>"
								+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
										+ "<br/><br/>");
					else
						UtilText.nodeContentSB.append("拉尔夫很快就和顾客聊完了，你保持安静，听着他离开的声音。他没发现你在这！"
								+ "<br/><br/>"
								+ "<b>现在</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>没有客人</b><b>在柜台附近。</b>"
										+ "<br/><br/>");
				}
				
				UtilText.nodeContentSB.append("拉尔夫发出一串低沉的咕哝声，当他在你嘴里抽插时，你倒吸了一口气。"
						+ "他发出压抑的呻吟，把巨大的阴茎重重塞进你的喉咙，在柜台下扭动。你感觉到他那有力的阴茎试图推进，正处于高潮的边缘。"
						+ "<br/><br/>"
						+ "拉尔夫用阴茎根部摩擦你的嘴唇，在射精时，他绷紧了巨大的阴囊。");
				
				if(Main.sex.getCharacterPerformingAction().isWearingCondom()) {
					UtilText.nodeContentSB.append("随着他有力的摇动，巨大的鸡巴将高负荷的精液射进避孕套。你感觉到火热的精液从避孕套的顶端溢出，"
								+ "你发出低沉的呻吟。"
							+ "他继续抽插了许久，巨大的黑色马屌把精液安全射入你提供的安全套中。"
							+ "<br/><br/>"
							+ "拉尔夫排空阴囊存量后，退后一步，从你被操透的喉咙中快速抽出软化的鸡巴。"
							+ "你突然感到极度空虚，发出一声叹息，你挪步向前，顽皮地对拉尔夫咬着嘴唇，看着他把用过的避孕套绑好。"
							+ "他把装满精液的避孕套放在你旁边，在确定没有人看着后，抓住你[pc.arms+]，把你拉起。"
							+ "达成协议后，他示意你继续购物，"
							+ "[ralph.speechNoEffects(好吧，我觉得你应该得到更多的折扣……)]");
				} else {
					UtilText.nodeContentSB.append("随着他有力的摇动，巨大的鸡巴将高负荷的精液注入腹部。你感觉到火热的精液将你填满，发出一声低沉的呻吟。"
							+ "他的鸡巴继续抽插了许久，巨大的黑色马屌将精液射入你的脸。"
							+ "拉尔夫迎来高潮，你的肚子被他黏糊糊的白色马精灌满。"
							+ "<br/><br/>"
							+ "拉尔夫排空阴囊存量后，退后一步，从你被操透的喉咙中快速抽出软化的鸡巴。"
							+ "黏糊糊的唾液从你嘴中汩汩流下，你挪动着向前，一边擦拭，一边玩味地朝拉尔夫微笑。"
							+ "交易完成，他确定没人注意后，抓着你[pc.arms+]将你拉起，示意你继续购物，"
							+ "[ralph.speechNoEffects(好吧，我觉得你应该得到更多的折扣……)]");
				}
			}
			
			
			
			if(((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive() && (SexFlags.ralphDiscount<Main.game.getDialogueFlags().ralphDiscount)){
				UtilText.nodeContentSB.append(
						"<br/><br/>"
						+ "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>你在接下来三天获得了"+SexFlags.ralphDiscount+"%减免！</b>"
						+ "<br/><br/>"
						+ "<b>因为拉尔夫已经给你更大的折扣了</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"
							+Main.game.getDialogueFlags().ralphDiscount+"%</b><b>三天后他才会改变主意接受提议！</b>");
				
			} else {
				UtilText.nodeContentSB.append("<br/><br/><b>你在接下来三天获得了</b><b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+SexFlags.ralphDiscount+"%</b><b>减免！</b>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			// If your existing discount is bigger, just refresh the bigger discount:
			if(!((Ralph)Main.game.getNpc(Ralph.class)).isDiscountActive() || SexFlags.ralphDiscount>Main.game.getDialogueFlags().ralphDiscount){
				Main.game.getDialogueFlags().ralphDiscount=SexFlags.ralphDiscount;
			}
			
			Main.game.getDialogueFlags().setSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID, Main.game.getMinutesPassed());
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
		public boolean endsSex() {
			return true;
		}
	};

}
