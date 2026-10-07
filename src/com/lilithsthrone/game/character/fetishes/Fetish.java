package com.lilithsthrone.game.character.fetishes;

import com.lilithsthrone.LolificationProject.ModCommon.OtherRegistrars;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.1.?
 * @version 0.4.2
 * @author Innoxia, Maxis
 */
public class Fetish {
	
	// FETISHES:

	// Sex types:
	
	public static AbstractFetish FETISH_ANAL_GIVING = new AbstractFetish(60,
			"肛交者",
			"完成肛交",
			"fetish_anal_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>肛门挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>菊穴挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人对对象的屁股实行性动作的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "你特别喜爱肛交。使用对象的屁股，宣称对方的屁眼为自己的所有物，都让你欲火焚身！";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有肛交的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "进行肛门相关性动作");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "肛交挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isAnalContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ANAL_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ANAL_RECEIVING = new AbstractFetish(60,
			"屁穴荡妇",
			"接受肛交",
			"fetish_anal_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>菊穴挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>肛交挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人接受肛门性动作的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "你特别喜欢接受肛交。屁股被狂暴轰入的想法让你欲火焚身！";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有接受肛交的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "肛门受到照顾");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "菊穴挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}

		@Override
		public boolean isContentEnabled() { return Main.game.isAnalContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ANAL_GIVING; }
	};
	
	public static AbstractFetish FETISH_VAGINAL_GIVING = new AbstractFetish(60,
			"阴道爱人",
			"完成阴道性交",
			"fetish_vaginal_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>入阴挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>淫穴挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人对对象的小穴实行性动作的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "尽管这是所有性行为中最平常的一类，你对于阴道性爱的喜爱已近乎迷恋。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]对于阴道性爱极其迷恋。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "进行阴道相关性动作");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "入阴挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VAGINAL_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_VAGINAL_RECEIVING = new AbstractFetish(60,
			"淫穴荡妇",
			"阴道性交",
			"fetish_vaginal_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>淫穴挑逗</span> (需要拥有阴道)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>入阴挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人想要自己的小穴被玩弄的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "尽管这是所有性行为中最平常的一类，你对于接受任何类型的阴道性爱的喜爱已经近乎迷恋。";
			} else {
				return UtilText.parse(owner, "[npc.Name]对于接受阴道性爱极其迷恋。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "阴道受到照顾");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "淫穴挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VAGINAL_GIVING; }
	};
	
	public static AbstractFetish FETISH_ORAL_RECEIVING = new AbstractFetish(60,
			"口交喜爱者",
			"接受口交",
			"fetish_oral_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>口交挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>口技服务挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人接受与口相关的性动作的欲望有关。";
				
			} else if(owner.isPlayer()) {
				return "你特别接受口交。引导对象的脑袋在你双腿之间来回摆动是你的最爱，你对于嘴唇和舌头带来的高潮欲罢不能。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有接受口交的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "接受口交");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "口交挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ORAL_GIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ORAL_GIVING = new AbstractFetish(60,
			"口技表演者",
			"提供口交",
			"fetish_oral_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>口技服务挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>口交挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人给对象口交的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你超爱给人口交。在你对象的腿间干活是你最爱的一项行为，你时刻准备着，渴望用你的嘴为他们带来高潮！";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]有着给人口交的癖好。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "给予口交");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "口技服务挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ORAL_RECEIVING; }
	};
	
	public static AbstractFetish FETISH_BREASTS_OTHERS = new AbstractFetish(60,
			"乳房爱好者",
			"别人的胸部",
			"fetish_breasts_others",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>恋胸挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>胸脯挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人使用伴侣胸部的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你对乳房十分着迷，无论它们是大或小，如果有人有一对(或更多)乳房，你将想尽办法去使用它们。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有喜好他人乳房的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "玩弄他人乳房");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "恋胸挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BREASTS_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BREASTS_SELF = new AbstractFetish(60,
			"乳房爱玩者",
			"玩弄自己的胸部",
			"fetish_breasts_self",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>胸脯挑逗</span> (需要拥有乳房)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>恋胸挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人想要自己的胸部被他人玩弄的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "你对自己的乳房十分着迷。你非常热衷于用它们来取悦你的性伴侣，又或者是向你的众多崇拜者炫耀它们的存在。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有使用[npc.her]乳房的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "触摸并爱抚你的[npc.breasts]");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "胸脯挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BREASTS_OTHERS; }
	};
	
	public static AbstractFetish FETISH_LACTATION_OTHERS = new AbstractFetish(60,
			"乳汁爱好者",
			"被哺乳",
			"fetish_lactation_others",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>恋乳挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>泌乳挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人让性对象分泌乳汁的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你对母乳的喜好几近痴迷，你最喜欢做的事就是吸吮某人充血的奶香乳头。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有被哺乳的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "被哺乳");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "恋乳挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isLactationContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LACTATION_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_LACTATION_SELF = new AbstractFetish(60,
			"泌乳奶牛",
			"分泌乳汁",
			"fetish_lactation_self",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>泌乳挑逗</span> (需要拥有乳房)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>恋乳挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人让自己乳房泌乳的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你十分钟情于泌乳。你最喜爱的事情莫过于让你那饱胀满足的乳房挤出乳汁。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有泌乳的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "使你的[npc.breasts]挤出乳汁");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "泌乳挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isLactationContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LACTATION_OTHERS; }
		
	};
	
	public static AbstractFetish FETISH_LEG_LOVER = new AbstractFetish(60,
			"恋腿者",
			"伴侣的腿",
			"fetish_leg_lover",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>恋腿挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>秀腿挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人在性动作中使用腿部的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "你十分钟情于大腿。在性爱时使用对象的大腿或双股对于你来说简直就是终极享受。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有使用他人腿股的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "别人的腿");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "恋腿挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_STRUTTER; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_STRUTTER = new AbstractFetish(60,
			"秀腿者",
			"让腿部被使用",
			"fetish_strutter",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>秀腿挑逗</span> (需要有腿)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>恋腿挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人想要自己的大腿被玩弄的欲望关联。";
				
			} else if(owner.isPlayer()) {
				return "你十分钟情于炫耀自己的大腿。在性爱时让自己的大腿或双股被使用对于你来说简直就是终极享受。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有被他人使用腿股的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "在性爱中使用你的腿");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "秀腿挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_LEG_LOVER; }
	};
	
	public static AbstractFetish FETISH_FOOT_GIVING = new AbstractFetish(60,
			"支配之足",
			"使用足部",
			"fetish_foot_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>足之支配挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>足之顺从挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人在性爱过程中使用足部的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你非常喜欢在性爱过程中使用的足部。让你的性对象在足下趾间献上崇拜与性技，能为你带来极乐的快感。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有在性爱中使用[npc.her]的足部的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "在性爱中使用你的足部");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "足之支配挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isFootContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_FOOT_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_FOOT_RECEIVING = new AbstractFetish(60,
			"顺从之足",
			"使用性对象的足部",
			"fetish_foot_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>足之顺从挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>足之支配挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人在性爱过程中使用性对象足部的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你十分钟情于他人的足部。让你的性对象在性爱过程中使用足和足趾对于你来说是一种终极享受。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有使用他人足部的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "他人的足部");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "足之顺从挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isFootContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_FOOT_GIVING; }
	};

	public static AbstractFetish FETISH_ARMPIT_GIVING = new AbstractFetish(60,
			"腋窝爱好者",
			"进行腋交",
			"fetish_armpit_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>腋交挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>腋穴挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人对对象的腋窝实行性动作的欲望关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢取悦性对象的腋窝，甚至于这种喜爱有时候超过了对插入性行为的欲望。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "进行腋窝相关性动作");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "腋交挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isArmpitContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ARMPIT_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_ARMPIT_RECEIVING = new AbstractFetish(60,
			"腋穴荡妇",
			"接受腋交",
			"fetish_armpit_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>腋穴挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>腋交挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该性癖与某人想要自己的腋穴被玩弄的欲望关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢让性对象给自己的腋窝提供性服务，此等喜爱有时甚至超过了对插入性行为的欲望。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "腋窝受到照顾");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "腋穴挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isArmpitContentEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_ARMPIT_GIVING; }
	};
	
	public static AbstractFetish FETISH_PENIS_GIVING = new AbstractFetish(60,
			"人根合一",
			"使用自己的阴茎",
			"fetish_dick_dealer",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>阴茎挑逗</span> (需要拥有阴茎)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>挑逗阴茎</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人用阴茎插入他人的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你痴迷于插入性行为，你的脑海中充满了把你的大屌插入一切能插入的腔穴的渴望……";
			
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有使用[npc.her]的阴茎的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "使用你的阴茎");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "阴茎挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PENIS_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PENIS_RECEIVING = new AbstractFetish(60,
			"阳具崇拜",
			"别人的阴茎",
			"fetish_cock_addict",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>挑逗阴茎</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>阴茎挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要取悦阴茎的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你已经无可救药地对鸡巴上瘾了。大，小，粗，细，你才不在乎他们有什么区别，只要它能在你的洞里抽插……";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]对鸡巴无可救药地全身心上瘾。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "别人的阴茎");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "挑逗阴茎");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PENIS_GIVING; }
	};
	
	public static AbstractFetish FETISH_CUM_STUD = new AbstractFetish(60,
			"精液大炮",
			"精液",
			"fetish_cum",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>爆射挑逗</span> (需要拥有阴茎)",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>精瘾挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人对性对象射精的欲望相关联。";
				
			} else {
				return UtilText.parse(owner,
							"[npc.NameHasFull]非常痴迷于爆射精液。把能看见的所有腔穴都填满精液是[npc.she]的最爱，或者退而求其次，让他人遍体精淋也是个不错的选择。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "任何形式自我中心的射精");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "爆射挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_CUM_ADDICT; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_CUM_ADDICT = new AbstractFetish(60,
			"精液厕所",
			"精液游戏",
			"fetish_cum_addict",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.CLOTHING_WHITE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>精瘾挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>爆射挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要被精液浸没的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你已经无可救药地对精液上瘾了。你才不在乎是谁射的，你只在乎你的嘴里能不能填满那些美味咸腥的子种……"
						+ "让它们滑过你的舌头，细细品味每一刻……唔……精液真是绝妙……";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有喜好精液的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "他人的精液");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "精瘾挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_CUM_STUD; }
	};
	
	public static AbstractFetish FETISH_DEFLOWERING = new AbstractFetish(60,
			"败坏贞操",
			"败坏贞操",
			"fetish_deflowering",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("每当<span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>你夺走贞操</span>时，获得<span style='color:"+ PresetColour.GENERIC_EXPERIENCE.toWebHexString()+ ";'>经验值</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人夺走他人贞操的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你最喜欢的事情莫过于霸占贞洁少女的童贞。蹂躏一个即将成为荡妇的淫穴自然是你的最爱，但你也同样钟情于夺走他人乳头，屁股或是喉咙的第一次。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有败坏贞操的性癖。[npc.She]不但热衷于破坏少女的处女膜，而且也同样享受着夺走他人乳头，屁股或是喉咙的第一次。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "夺走贞操");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PURE_VIRGIN; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PURE_VIRGIN = new AbstractFetish(60,
			"处子之身",
			"保留处女贞洁",
			"fetish_virginity",
			FetishExperience.BASE_VERY_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(获得)][style.colourExcellent(贞洁处女)]状态效果",
					"[style.colourBad(遭受)][style.colourTerrible(失格处女)]状态效果"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人保持并珍视自身处女贞洁的欲望相关联。";
				
			} else if(owner.hasVagina()) {
				return UtilText.parse(owner, "[npc.Name]珍视[npc.her]的处子之身胜过这世上的任何一切。一旦[npc.she]失去了它，[npc.she]将会变得无所适从……");
				
			} else {
				if(owner.hasFetish(FETISH_PURE_VIRGIN)) {
					return UtilText.parse(owner, "尽管[npc.name]目前尚未拥有阴道，但[npc.she]知道如果[npc.she]拥有一个的话，[npc.she]一定会视其贞操远胜世上的一切。");
					
				} else {
					return UtilText.parse(owner, "[npc.name]并未拥有阴道，[npc.she]无法获得保持贞洁的性癖……");
				}
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "保持贞洁");
		}
		
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.getVaginaType()!=VaginaType.NONE && character.isVaginaVirgin();
		}

		@Override
		public List<String> getPerkRequirements(GameCharacter character) {
			perkRequirementsList.clear();
			
			if(character.getVaginaType()==VaginaType.NONE) {
				perkRequirementsList.add("[style.colourBad(需要阴道)]");
			} else {
				perkRequirementsList.add("[style.colourGood(需要阴道)]");
			}
			
			if(!character.isVaginaVirgin()) {
				perkRequirementsList.add("[style.colourBad(需要处子之身)]");
			} else {
				perkRequirementsList.add("[style.colourGood(需要处子之身)]");
			}
			
			return perkRequirementsList;
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ZERO_PURE;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DEFLOWERING; }
	};
	
	public static AbstractFetish FETISH_MASTURBATION = new AbstractFetish(60,
			"自慰",
			"自慰",
			"fetish_masturbation",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.TEXT_GREY.toWebHexString()+ ";'>无特殊能力</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人自慰的欲望相关联。";
				
			} else {
				return UtilText.parse(owner, "使用[npc.her]的[npc.fingers]来令[npc.herself]或[npc.her]的性对象进入高潮是[npc.namePos]在性交中的喜好之一。");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "手淫");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	};
	
	
	// FETISH_SPANKING("spanking", "You love the idea of spanking or being
	// spanked during sex."),

	// Effects:
	
	public static AbstractFetish FETISH_IMPREGNATION = new AbstractFetish(60,
			"播种机",
			"使人受孕",
			"fetish_impregnation",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.VIRILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
					+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>爸气挑逗</span> (需要拥有阴茎)",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>受制于</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>生育挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要让他人怀孕的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你发现你经常幻想着用你的子种填满肥沃的子宫，让你的性对象繁殖受孕的想法好似野兽般驱使着你疯狂的欲望。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]有着在性交中使性对象怀孕的癖好");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "令他人受孕");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "爸气挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_PREGNANCY; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_PREGNANCY = new AbstractFetish(60,
			"妊娠",
			"怀孕",
			"fetish_pregnancy",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.FERTILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
							+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>生育挑逗</span> (需要拥有阴道)",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>受制于</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>爸气挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要让自己怀孕的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你发现你经常幻想着自己怀孕的场景，并且诸如此类繁殖受孕的想法好似野兽一般驱使着你疯狂的欲望。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有接受怀孕的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "受孕");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "生育挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_IMPREGNATION; }
	};

	public static AbstractFetish FETISH_TRANSFORMATION_GIVING = new AbstractFetish(60,
			"转化者",
			"转化他人",
			"fetish_transformation_giving",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>所有药水的制作成本减半</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要令他人转化的欲望相关联。";
				
			} else if (owner.isPlayer()){
				return "你喜欢将他人转化的想法。看着他们的身体转变——无论自愿与否，可真叫你兴奋极了。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢转化他人，看着他们的身体转变——无论他们自愿与否，都会给[npc.herHim]带来极大的兴奋。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "转变他人");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_TRANSFORMATION_RECEIVING = new AbstractFetish(60,
			"测试用具",
			"被他人转化",
			"fetish_transformation_receiving",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.boldGood(增加)] <span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>受到的被迫转变的效力</span>",
					"[style.boldBad(禁用)]吐出转化药水"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要被他人转化的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢被他人转化的感觉。让自己身体的一部分改变——无论你是否出于自愿，都会让你极度兴奋。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢被他人转化，看着[npc.her]的身体转变——无论[npc.her]自愿与否，都会给[npc.herHim]带来极大的兴奋。");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "被他人转变");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_TRANSFORMATION_GIVING; }
	};
	
	public static AbstractFetish FETISH_KINK_GIVING = new AbstractFetish(60,
			"怪癖倡导者",
			"给予他人性癖",
//			"fetish_transformation_giving",
			"fetish_kink_giving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than triggering forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>乐于让他人尝试新鲜事物！</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要让他人获得新性癖的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "不论他人是否自愿，给予他人新性癖的想法极大地鼓舞着你。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢给予他人新的性癖。看着他们享受反常的新事物，无论他们是自愿与否，这对[npc.herHim]来说都是一种巨大的刺激。");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "给予他人新性癖");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_KINK_RECEIVING; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_KINK_RECEIVING = new AbstractFetish(60,
			"怪癖好奇者",
			"获得性癖",
//			"fetish_transformation_receiving",
			"fetish_kink_receiving",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than not taking corruption from receiving forced fetishes
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>获得性癖时将不再堕落。</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要获得新性癖的欲望相关联。";
				
			} else if (owner.isPlayer()) {
				return "你喜欢开发自己新性癖的想法。无论自愿与否，从新事物中获得一反常态的乐趣极大地鼓舞着你。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢开发新的性癖。无论自愿与否，从新事物中获得反常的乐趣，对[npc.herHim]来说是一种巨大的刺激。");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "被给予新性癖的想法");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_KINK_GIVING; }
	};
	
	// Behaviour (organised roughly in active/passive pairs):
	

	public static AbstractFetish FETISH_DENIAL = new AbstractFetish(60,
			"寸止者",
			"寸止",
			"fetish_denial",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人让其伴侣寸止的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "用你的身体戏弄他们，又或者是阻止对方进入高潮，你喜欢在性爱时寸止你的性对象。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有寸止他人的性癖。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "寸止");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL_SELF; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_DENIAL_SELF = new AbstractFetish(60,
			" 克己者",
			"被寸止",
			"fetish_denial_self",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1),
					new Value<>(Attribute.RESISTANCE_LUST, 2)),
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人使自己被寸止的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢边缘控制以及被寸止的感觉。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有被寸止的性癖。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "被寸止");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DENIAL; }
	};
	
	public static AbstractFetish FETISH_DOMINANT = new AbstractFetish(60,
			"支配者",
			"掌握主导权",
			"fetish_dominant",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MANA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>支配性挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>顺从性挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人在性爱中占据支配地位的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢在性爱中占据支配地位，而且，你也明白如何让你的性对象知道谁才是真正的老大。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]有着在性爱中支配性对象的癖好。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "支配性伴侣");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "支配性挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SUBMISSIVE; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_SUBMISSIVE = new AbstractFetish(60,
			"顺从者",
			"顺从地行动",
			"fetish_submissive",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.MAJOR_PHYSIQUE, 2)),
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>解锁</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>顺从性挑逗</span>",
					"<span style='color:"+ PresetColour.GENERIC_BAD.toWebHexString()+ ";'>受制于</span> <span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>支配性挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人在性爱中占据顺从地位的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢在性爱中占据顺从地位，你愿意做任何事来展示你的顺从，同时也非常乐意让你的性对象对你做任何他们想做的事。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]有着在性爱中顺从性对象的癖好。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "顺从性伴侣");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "顺从性挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_DOMINANT; }
	};
	
	public static AbstractFetish FETISH_INCEST = new AbstractFetish(60,
			"乱伦",
			"乱伦做爱",
			"fetish_incest",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"解锁<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>乱伦挑逗</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人同亲属做爱的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				if(owner.getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
					return "你一直对你的表亲怀有激情……";
				} else {
					return "你一直对莉莉姨妈怀有激情，当然，她是你的表亲……";
				}
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有乱伦的性癖。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "同亲属做爱");
		}
		@Override
		public String getAppliedFetishLevelEffectDescription(GameCharacter character) {
			return getAppliedFetishAttackLevelEffectDescription(character, this, "乱伦挑逗");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		@Override
		public boolean isContentEnabled() { return Main.game.isIncestEnabled(); }
	};
	
	public static AbstractFetish FETISH_SADIST = new AbstractFetish(60,
			"施虐狂",
			"施虐",
			"fetish_sadist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(解锁)]施虐[style.colourSex(性行为)]",
					"造成的[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"伤害)][style.boldExcellent(+5%)]",
					"10% 造成的",
					"[style.colourHealth("+Attribute.HEALTH_MAXIMUM.getName()+"伤害)]将以",
					"<span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>性欲伤害</span>的形式返还给你",
					"[style.boldArcane(+1精华)]",
					"每当对敌人造成暴击"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要向他人施虐的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢散播痛苦和羞辱，令他人陷入痛苦之中会唤醒你心中那最狂野的欲望。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有向他人施虐的性癖。");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "向他人施虐");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_MASOCHIST; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_MASOCHIST = new AbstractFetish(60,
			"受虐狂",
			"痛苦和羞辱",
			"fetish_masochist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.RESISTANCE_PHYSICAL, 2)),
			Util.newArrayListOfValues(
					"[style.boldSex(享受)][style.boldTerrible(施虐性行为)]",
					"25% 所受到的",
					"<span style='color:"+ PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+ ";'>"+Attribute.HEALTH_MAXIMUM.getName()+"伤害</span>"+ "将被转化",
					"为<span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>性欲伤害</span>",
					"[style.boldArcane(+1精华)]当你",
					"遭受暴击"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人的受虐欲望相关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]在遭受痛苦或屈辱的经历时将会极度地兴奋。"
					+ "[npc.She]发现[npc.herself]在[npc.her]的腔穴被拉伸或极深地进入时会感到性致高昂。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "受虐");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_SADIST; }
	};
	
	public static AbstractFetish FETISH_NON_CON_DOM = new AbstractFetish(60,
			"非自愿",
			"强奸",
			"fetish_noncon_dom",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>如果性对象处于抗拒状态</span> <span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>获得的快感会增加</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要强奸他人的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "强奸他人，让他人的意志在你的性爱下粉碎，除此之外没什么能让你更喜爱的事物了。而且他们越是挣扎，你就越是兴奋……";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]对非自愿的遭遇情有独钟。[npc.her]的受害者越是挣扎，[npc.she]就越是兴奋……");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "与非自愿对象性交");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isNonConEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_NON_CON_SUB; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_NON_CON_SUB = new AbstractFetish(60,
			"非自愿性玩具",
			"被强奸",
			"fetish_noncon_sub",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>如果自身正处于抗拒状态</span> <span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>获得的快感会增加</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要被他人强奸的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "被他人强奸，违背自己的意愿地同某个人性交，除这之外没什么能让你更快乐了。挣扎着哀声乞求他人放你离开简直让你欲火焚身……";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]期望着成为非自愿的遭遇中的受害者。挣扎和乞求被放走让[npc.she]感到无比兴奋……");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "被强迫与他人性交");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isNonConEnabled(); }
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_NON_CON_DOM; }
	};
	
	public static AbstractFetish FETISH_BONDAGE_APPLIER = new AbstractFetish(60,
			"施缚者",
			"施加束缚",
			"fetish_bondage_applier",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourGood(将封印、仆役和奴役附魔的精华消耗降为零)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要将他人锁入封印衣物的欲望相关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢将他人困在衣物里，使其无法移动，然后利用他们无法移动的时机……");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "他人穿着封印衣物");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_VICTIM; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BONDAGE_VICTIM = new AbstractFetish(60,
			"受缚婊子",
			"被束缚",
			"fetish_bondage_victim",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.CLOTHING_BLACK_STEEL, PresetColour.CLOTHING_GOLD, PresetColour.CLOTHING_GOLD),
			null,
			Util.newArrayListOfValues(
					"[style.colourSeal(解封)]自缚衣物的消耗[style.colourTerrible(变为5倍)]",
					"BDSM装束获得额外的[style.colourGood(加成效果)]"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人穿着封印衣物的欲望相关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢被困在衣物里，令自己无法移动，将自己的命运交给他者的仁慈……");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "自己穿着封印衣物");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_BONDAGE_APPLIER; }
	};
	
	public static AbstractFetish FETISH_EXHIBITIONIST = new AbstractFetish(60,
			"暴露狂",
			"暴露自己",
			"fetish_exhibitionist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>将</span><span style='color:"+ PresetColour.GENERIC_ARCANE.toWebHexString()+ ";'>暴露状态的影响</span>"
										+"<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>替换为有益的版本</span>"),
			null) {

		@Override
		public String getShortDescriptor(GameCharacter target) {
			if(target==null) {
				return "暴露自己";
			}
			return UtilText.parse(target, "暴露[npc.herself]");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人炫耀自己裸体以及在他人围观下性爱的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢炫耀自己的身体，在公共场所展示你的裸体会让你兴奋不已。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有暴露[npc.her]裸体的性癖。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "暴露身体");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}

		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_VOYEURIST; }
	};
	
	public static AbstractFetish FETISH_VOYEURIST = new AbstractFetish(60,
			"窥视者",
			"窥视他人",
			"fetish_voyeurist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>快感增加</span>当你窥探性爱情景"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人观看他人性交的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你喜欢看着其他人……尤其是当他们在做一些有趣的事情时……";
			} else {
				return UtilText.parse(owner, "[npc.Name]拥有观看他人的癖好……");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "窥视他人");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public AbstractFetish getOpposite() { return Fetish.FETISH_EXHIBITIONIST; }
		
		@Override
		public boolean isTopFetish() { return true; }
	};
	
	public static AbstractFetish FETISH_BIMBO = new AbstractFetish(60,
			"傻乎乎的性感青年",
			"当个傻乎乎的性感青年",
			null,
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<>(Attribute.DAMAGE_LUST, 10)),
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>说话像个傻乎乎的性感青年</span>"),
			null) {
		
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null ||owner.isFeminine()) {
				return "傻乎乎的性感青年";
			} else {
				return "哥们儿";
			}
		}
		
		@Override
		public String getShortDescriptor(GameCharacter target) {
			if (target==null ||target.isFeminine()) {
				return "当个傻乎乎的性感青年";
			} else {
				return "成为哥们儿";
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要表现得像个傻乎乎的性感青年的欲望相关联。";
				
			} else if(owner.isFeminine()) {
				return UtilText.parse(owner,
						"[npc.NameIsFull]痴迷于表现得像个全然傻乎乎的性感青年。"
						+ "而[npc.she]已经痴迷到了除了是一个小笨蛋外不知道自己还能是啥的地步，哪怕[npc.she]实际上再聪明也无济于事。");
			} else {
				return UtilText.parse(owner,
						"[npc.NameIsFull]痴迷于表现得像个哥们儿。"
						+ "而[npc.she]已经痴迷到了除了自己是一个傻屌外不知道自己还能是啥的地步，哪怕[npc.she]实际上再聪明也无济于事。(哥们儿欢乐多)");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			if(target==null || target.isFeminine()) {
				return getGenericFetishDesireDescription(target, desire, "表现得像个傻乎乎的性感青年");
			} else {
				return getGenericFetishDesireDescription(target, desire, "表现得像个哥们儿");
			}
		}

		@Override
		public List<String> getExtraEffects(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>说话像个傻乎乎的性感青年</span>");
			} else {
				return Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_SEX.toWebHexString()+ ";'>说话像个哥们儿</span>");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner==null || owner.isFeminine()) {
				return bimboString;
			} else {
				return broString;
			}
		}

		@Override
		public FetishPreference getFetishPreferenceDefault() {
			return FetishPreference.TWO_DISLIKE;
		}
	};
	
	public static AbstractFetish FETISH_CROSS_DRESSER = new AbstractFetish(60,
			"异装",
			"异装",
			"fetish_cross_dresser",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ PresetColour.GENERIC_GOOD.toWebHexString()+ ";'>不受“衣着女性化程度”效果的影响</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人穿对其太过女性化或太过男性化的衣服的欲望相关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢穿所有各式各样不同的衣服，而且[npc.she]一点儿也不在乎别人会认为这对[npc.her]来说是太过阳刚或是太过阴柔。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "穿着更适合另一性别的衣服");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}

		@Override
		public FetishPreference getFetishPreferenceDefault() {
			return FetishPreference.TWO_DISLIKE;
		}
	};
	
	public static AbstractFetish FETISH_SIZE_QUEEN = new AbstractFetish(60,
			"尺寸为王",
			"深度插入",
			"fetish_size_queen",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			Util.newArrayListOfValues(PresetColour.BASE_YELLOW, PresetColour.BASE_PINK),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 1)),
			Util.newArrayListOfValues(
					"[style.colourGood(享受)][style.colourSex(被拉伸)]",
					"把[style.colourSex(“深得令人难受的”)]插入当作[style.colourGood(“令人舒服的”)]"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人渴求性伴侣越大越好的阳具相关联。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]更希望自己的性伴侣拥有硕大无朋的尺寸，而且也更想在自己体内的难以想象的深处感受它。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "接受超巨插入");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean isContentEnabled() { return Main.game.isPenetrationLimitationsEnabled(); }
	};
	
	// Derived fetishes:
	
	public static AbstractFetish FETISH_SWITCH = new AbstractFetish(60,
			"可攻可受",
			"可以在攻受之间切换自如",
			"fetish_switch",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.MAJOR_PHYSIQUE, 5)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_DOMINANT,
					Fetish.FETISH_SUBMISSIVE)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人在性爱中占据支配或是顺从地位的欲望相关联。";
				
			} else if(owner.isPlayer()) {
				return "你十分乐意于在支配地位与顺从地位间切换，只要形势需要。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]乐意于在性爱中处于支配或顺从的地位。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "在支配和顺从间切换");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_BREEDER = new AbstractFetish(60,
			"繁育者",
			"繁育",
			"fetish_breeder",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.FERTILITY, 25),
					new Value<>(Attribute.VIRILITY, 25)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_PREGNANCY,
					Fetish.FETISH_IMPREGNATION)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要让他人受孕，以及让自己怀孕欲望相关联。";
				
			} else if (owner.isPlayer()) {
				return "你有一个梦想。一个关于让这世界上所有人都怀孕的梦想，也包括你自己！";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]渴望向每一个遇到的人都分享[npc.her]对受孕妊娠的爱。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "为了繁殖所做的任何事");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_SADOMASOCHIST = new AbstractFetish(60,
			"虐待狂",
			"性虐狂",
			"fetish_sadomasochist",
			FetishExperience.BASE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 3),
					new Value<>(Attribute.DAMAGE_PHYSICAL, 10)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_SADIST,
					Fetish.FETISH_MASOCHIST)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好与一个人想要向他人施虐，以及反被他人所虐待的欲望相关联。";
				
			} else if (owner.isPlayer()) {
				return "你才不在乎是谁给予，又是谁接受，只要有痛苦和羞辱，你可以做任何事。";
				
			} else {
				return UtilText.parse(owner, "[npc.Name]喜爱任何形式的痛苦与羞辱。");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "任何形式的痛苦和羞辱");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	};
	
	public static AbstractFetish FETISH_LUSTY_MAIDEN = new AbstractFetish(60,
			"色欲少女",
			"色欲少女",
			"fetish_lusty_maiden",
			FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
			PresetColour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>启用</span> <span style='color:" + PresetColour.GENERIC_EXCELLENT.toWebHexString() + ";'>“贞洁处女”</span>",
					"<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>强化</span> <span style='color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>“失格处女”</span>"),
			null) {
		@Override
		public List<AbstractFetish> getFetishesForAutomaticUnlock() {
			return Util.newArrayListOfValues(
					Fetish.FETISH_PURE_VIRGIN,
					Main.game.isAnalContentEnabled()
						?Fetish.FETISH_ANAL_RECEIVING
						:null,
					Fetish.FETISH_ORAL_GIVING,
					Fetish.FETISH_BREASTS_SELF);
		}
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner==null) {
				return "该癖好取决于一个人在保留阴道贞操的同时，使用屁股，胸部或口部，来使性伴侣达到高潮的欲望。";
				
			} else if (owner.isPlayer()) {
				return "你是终极挑逗者！你可以用你的屁股，嘴巴，胸部——甚至对你的小穴的一个承诺来勾引与挑逗他人，"
							+ "但这承诺永远不会兑现，谁也无法进入你的女性身体，夺走你宝贵的贞操。";
			} else {
				return UtilText.parse(owner, "[npc.Name]喜欢用[npc.her]的屁股，嘴巴，胸部——甚至对[npc.her]小穴的一个承诺来取悦他人，"
							+ "但这承诺永远不会兑现，谁也无法进入[npc.she]的女性身体，夺走[npc.her]宝贵的贞操。");
			}
		}
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "避免阴道性交");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	
	// Helper methods:
	
	private static String getAppliedFetishAttackLevelEffectDescription(GameCharacter character, AbstractFetish fetish, String fetishAttackName) {
		FetishLevel level = character.getFetishLevel(fetish);
		return "+"+level.getBonusTeaseDamage()+" 基础伤害至"+fetishAttackName;
	}
	
	// Access methods:
	
	public static List<AbstractFetish> allFetishes;
	
	public static Map<AbstractFetish, String> fetishToIdMap = new HashMap<>();
	public static Map<String, AbstractFetish> idToFetishMap = new HashMap<>();
	
	/**
	 * @param id Will be in the format of: 'innoxia_maid'.
	 */
	public static AbstractFetish getFetishFromId(String id) {
		id = Util.getClosestStringMatch(id, idToFetishMap.keySet());
		
		return idToFetishMap.get(id);
	}
	
	public static String getIdFromFetish(AbstractFetish fetish) {
		return fetishToIdMap.get(fetish);
	}

	static {
		allFetishes = new ArrayList<>();
		
		// Hard-coded fetishes (all those up above):
		
		Field[] fields = Fetish.class.getFields();
		
		for(Field f : fields){
			if (AbstractFetish.class.isAssignableFrom(f.getType())) {
				
				AbstractFetish fetish;
				
				try {
					fetish = ((AbstractFetish) f.get(null));

					fetishToIdMap.put(fetish, f.getName());
					idToFetishMap.put(f.getName(), fetish);
					allFetishes.add(fetish);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		OtherRegistrars.registerFetishes();
	}
	
	public static List<AbstractFetish> getAllFetishes() {
		return allFetishes;
	}
	
}
