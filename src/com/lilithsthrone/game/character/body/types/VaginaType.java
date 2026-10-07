package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia
 */
public class VaginaType {

	public static AbstractVaginaType NONE = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HUMAN,
			Race.NONE,
			false,
			"[npc.She]感觉阴道突然收缩，紧紧闭合了起来，顿时开始扭动着身子呻吟起来，随着一声难以压抑的哀嚎，一股奇妙的压迫感涌上了[npc.her]的下腹部。"
				+ "所幸那感觉来得快去得也快，等到不适感消散后，[npc.she]却发现自己的阴道早已消失得无影无踪，不禁倒吸了一口凉气！"
				+ "<br/>[npc.Name][style.boldSex(失去了[npc.her]的阴道)]。",
			"无阴道。", // Shouldn't need a description of no vagina.
			null) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				owner.setHymen(true);
				owner.setPiercedVagina(false);
				if(owner.isPlayer() && owner.hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					if(!owner.isVaginaVirgin()) {
						return UtilText.parse(owner,
								"<p style='text-align:center;'>"
									+ "[style.boldGood(无暇处女)]"
									+ "<br/><i>"
										+ "[npc.her]毫无价值的细缝已经消失不见，[npc.name]开始感觉自己已经不再只是个肮脏的荡妇了。"
										+ "毕竟既然[npc.she]都没有小穴，理论上也就不会失去贞洁！"
										+ "[npc.she]长叹一声，总算说服了自己不必再为自己是个失格处女而担忧了。"
									+ "<br/>"
										+ "尽管终于又能将自己视为充满尊严的个体，但拥有阴道的那种熟悉感觉从此一去不返还是让[npc.her]心神不宁。"
										+ "或许[npc.she]还会再长出小穴，重获一张完整的处女膜，那样[npc.herHim]便又能成为纯洁的“处女”了……"
									+ "</i><br/>"
									+ "[npc.NameIsFull][style.boldGood(不再是失格处女)]！"
								+ "</p>");
					} else {
						return UtilText.parse(owner,
								"<p style='text-align:center;'>"
									+ "[style.boldBad(贞洁处女？)]"
									+ "<br/><i>"
									+ "[npc.name]的阴道已经消失不见，[npc.she]脑中却油然而生一股危机感。"
									+ "毕竟，既然[npc.she]都没有小穴，那也就不能被认为是贞洁处女了！"
									+ "</i><br/>"
									+ "在[npc.name]恢复[npc.her]失去的阴道前，[npc.she][style.boldBad(不会再被认为是贞洁处女)]！"
								+ "</p>");
					}
				}
			}
			return "";
		}
	};
	
	public static AbstractVaginaType ONAHOLE = new AbstractVaginaType(BodyCoveringType.DILDO,
			FluidType.GIRL_CUM_HUMAN,
			Race.NONE,
			false,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("玩具", "人造"),
			"不知道为什么，你把你的阴道转化成了飞机杯，这是一个BUG。(请联系作者！)", // Onaholes are not a transformable option
			"[npc.she]的阴道中被插入了一个飞机杯，由[npc.vaginaFullDescription(true)]制成。",
			null) {
	};

	public static AbstractVaginaType HUMAN = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HUMAN,
			Race.HUMAN,
			false,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
				+ "没过多久，这感觉便开始褪去，[npc.she]发现自己的小穴已经变成了常规的人类模样，惊讶地倒吸了一口凉气。"
				+ "一股温暖而刺痒的感觉在[npc.her]的下腹部涌动，随着转化步入尾声，[npc.she]意识到体内的生殖器官也已经变得和人类一样了。"
				+ "<br/>[npc.Name]现在拥有[style.boldHuman(人类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着人类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType ANGEL = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_ANGEL,
			Race.ANGEL,
			false,
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("无法抗拒", "完美"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
				+ "这种感觉不一会儿就消失了，[npc.she]的阴部已经转化成一处外观绝对完美的天使阴道，这令她惊讶地喘息出声。"
				+ "一股温暖而刺痒的感觉在[npc.her]的下腹部涌动，随着转化步入尾声，[npc.she]意识到体内的生殖器官也已经变得和天使一样了。"
				+ "<br/>[npc.Name]现在拥有[style.boldImp(天使的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着天使的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues()) {
	};

	public static AbstractVaginaType DEMON_COMMON = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_DEMON,
			Race.DEMON,
			false,
			Util.newArrayListOfValues("魅魔"),
			Util.newArrayListOfValues("魅魔"),
			Util.newArrayListOfValues("无法抗拒", "完美"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "一股奇怪的躁动感在[npc.her]的狭缝深处愈发强烈，"
						+ "[npc.she]感受到一组全新的肌肉正在[npc.her]小穴的内壁生长，不禁发出一声淫荡的呻吟。"
					+ "[npc.she]试探性地收缩了一下，发现小穴内部现在有着大量可以自由控制的[style.boldGrow(肌肉)]，"
					+ "伴随着身体最后愉悦地一颤，[npc.her]小穴的外貌已经变得完美无瑕。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，小穴中新生的肌肉却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "恶魔粗壮的巨屌深深插进[npc.her]的秘缝中的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]的恶魔子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>"
					+ "#IF(npc.isShortStature())"
						+ "[npc.Name]现在拥有[style.boldImp(小恶魔的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。"
					+ "#ELSE"
						+ "[npc.Name]现在拥有[style.boldDemon(恶魔的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。"
					+"#ENDIF",
			"[npc.she]有着恶魔的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues(
				OrificeModifier.MUSCLE_CONTROL)) {
	};

//	public static AbstractVaginaType DEMON_EGGS = new AbstractVaginaType(BodyCoveringType.VAGINA,
//			FluidType.GIRL_CUM_DEMON,
//			Race.DEMON,
//			true,
//			Util.newArrayListOfValues("succubus-"),
//			Util.newArrayListOfValues("succubus-"),
//			Util.newArrayListOfValues("irresistible", "perfect"),
//			"[npc.She] [npc.verb(let)] out a squeal of excitement as a wave of pleasure runs up through [npc.her] groin, which increases in intensity as [npc.she] [npc.verb(feel)] [npc.her] slit uncontrollably shifting and contracting."
//					+ " A strange, bubbling sensation starts running down deep into [npc.her] cunt,"
//						+ " and [npc.she] [npc.verb(let)] out a lewd moan as [npc.she] [npc.verb(feel)] a new set of muscles forming all along the inner-walls of [npc.her] pussy."
//					+ " With an experimental squeeze, [npc.she] quickly [npc.verb(discover)] that [npc.she] [npc.has] an incredible amount of control over [npc.her] pussy's new muscles."
//					+ " With one last shiver of pleasure, [npc.her] pussy reshapes its exterior into an absolutely perfect-looking vagina."
//				+ "</p>"
//				+ "<p>"
//					+ "Just as [npc.she] [npc.verb(start)] think that the transformation [npc.has] come to an end, [npc.her] pussy's new muscles involuntarily clench down,"
//						+ " and a desperate squeal escapes from between [npc.her] [npc.lips+] as a warm, tingling feeling spreads up through [npc.her] lower abdomen."
//					+ " Images of fat demonic cocks slamming deep into [npc.her] new cunt flash before [npc.her] eyes,"
//						+ " and [npc.her] squeal turns into a satisfied moan as [npc.she] [npc.verb(imagine)] them pumping their hot, virile seed deep into [npc.her] demonic womb."
//					+ " Just as quickly as they came, the images fade from [npc.her] mind, and as one last wave of tingling pleasure washes through [npc.her] body,"
//						+ " [npc.she] [npc.verb(feel)] [npc.her] female reproductive organs finishing their transformation."
//					+ "<br/>"
//					+ "#IF(npc.isShortStature())"
//						+ "[npc.Name] now [npc.has] an [style.boldImp(impish, egg-laying vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
//					+ "#ELSE"
//						+ "[npc.Name] now [npc.has] a [style.boldDemon(demonic, egg-laying vagina)], with [npc.pussyColourPrimary(true)] labia and [npc.pussyColourSecondary(true)] internal walls."
//					+"#ENDIF",
//			"[npc.she] [npc.has] #IF(npc.isPiercedVagina())a pierced,#ELSEa#ENDIF demonic, egg-laying pussy, with [npc.labiaSize], [npc.pussyPrimaryColour(true)] labia and [npc.pussySecondaryColour(true)] inner-walls.",
//			Util.newArrayListOfValues(
//				OrificeModifier.MUSCLE_CONTROL)) {
//		@Override
//		public String getTransformName() {
//			return "demonic (egg-laying)";
//		}
//	};

	public static AbstractVaginaType DOG_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_DOG_MORPH,
			Race.DOG_MORPH,
			false,
			Util.newArrayListOfValues("狗", "犬"),
			Util.newArrayListOfValues("狗", "犬"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "没过多久，这感觉便开始褪去，[npc.she]发现自己的小穴已经完全变成了犬一般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "猩红色带结的犬类肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]的犬类子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldDogMorph(犬类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着犬类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};

	public static AbstractVaginaType WOLF_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_WOLF_MORPH,
			Race.WOLF_MORPH,
			false,
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "没过多久，这感觉便开始褪去，[npc.she]发现自己的小穴已经完全变成了狼一般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "猩红色带结的狼类肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]的狼类子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldWolfMorph(狼类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着狼类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};
	
	public static AbstractVaginaType FOX_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_FOX_MORPH,
			Race.FOX_MORPH,
			false,
			Util.newArrayListOfValues("狐狸", "狐"),
			Util.newArrayListOfValues("狐狸", "狐"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "没过多久，这感觉便开始褪去，[npc.she]发现自己的小穴已经完全变成了狐狸般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "猩红锁结的狐狸肉棒深深插进[npc.her]新生小穴的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]的秃鹫子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldFoxMorph(狐狸的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着狐狸的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};
	
	public static AbstractVaginaType CAT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_CAT_MORPH,
			Race.CAT_MORPH,
			false,
			Util.newArrayListOfValues("猫"),
			Util.newArrayListOfValues("猫"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了猫一般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "附着倒刺的猫类肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]的猫类子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldCatMorph(猫类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着猫类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType SQUIRREL_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_SQUIRREL_MORPH,
			Race.SQUIRREL_MORPH,
			false,
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了松鼠般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "松鼠般巨大的肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股浓郁的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldSquirrelMorph(松鼠的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着松鼠的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType RAT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_RAT_MORPH,
			Race.RAT_MORPH,
			false,
			Util.newArrayListOfValues("老鼠"),
			Util.newArrayListOfValues("老鼠"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了老鼠般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "不停颤动的老鼠肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]的啮齿类子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldRatMorph(老鼠的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着老鼠的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType RABBIT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_RABBIT_MORPH,
			Race.RABBIT_MORPH,
			false,
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues("兔子"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了兔子般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "兔子般粗大的肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫而浓郁的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldRabbitMorph(兔子的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着兔子的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType BAT_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_BAT_MORPH,
			Race.BAT_MORPH,
			false,
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了蝙蝠般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "不停颤动的蝙蝠肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldBatMorph(蝙蝠的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着蝙蝠的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};
	
	public static AbstractVaginaType ALLIGATOR_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_ALLIGATOR_MORPH,
			Race.ALLIGATOR_MORPH,
			true,
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了鳄鱼般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "鳄鱼粗壮的巨屌深深插进[npc.her]的秘缝中的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldAlligatorMorph(鳄鱼的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着鳄鱼的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType COW_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_COW_MORPH,
			Race.COW_MORPH,
			false,
			Util.newArrayListOfValues("牛"),
			Util.newArrayListOfValues("牛"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了牛一般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "粗壮的牛屌深深插进[npc.her]的秘缝中的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldCowMorph(牛类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着牛类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};

	public static AbstractVaginaType HORSE_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HORSE_MORPH,
			Race.HORSE_MORPH,
			false,
			Util.newArrayListOfValues("#IF(npc.getRace()==npc.getVaginaRace())[npc.raceFeral]#ELSE[npc.pussyRaceFeral]#ENDIF", "马类"),
			Util.newArrayListOfValues("#IF(npc.getRace()==npc.getVaginaRace())[npc.raceFeral]#ELSE[npc.pussyRaceFeral]#ENDIF", "马类"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "一股奇怪的躁动感在[npc.her]的狭缝深处愈发强烈，"
						+ "[npc.she]感受到一组全新的肌肉正在[npc.her]小穴的内壁生长，不禁发出一声淫荡的呻吟。"
					+ "[npc.she]试探性地收缩了一下，发现小穴内部现在有着大量可以自由控制的[style.boldGrow(肌肉)]，"
					+ "没过多久，这感觉便开始褪去，[npc.she]发现自己的小穴已经完全变成了马一般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "粗壮的平头马屌插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldHorseMorph(马类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着马类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY,
				OrificeModifier.MUSCLE_CONTROL)) {
	};

	public static AbstractVaginaType REINDEER_MORPH = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_REINDEER_MORPH,
			Race.REINDEER_MORPH,
			false,
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "没过多久，这感觉便开始褪去，[npc.she]发现自己的小穴已经完全变成了驯鹿般的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "不停颤动的驯鹿肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫的子种汁深深注入了[npc.her]全新的子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldReindeerMorph(驯鹿的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着驯鹿的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			Util.newArrayListOfValues(
				OrificeModifier.PUFFY)) {
	};

	public static AbstractVaginaType HARPY = new AbstractVaginaType(BodyCoveringType.VAGINA,
			FluidType.GIRL_CUM_HARPY,
			Race.HARPY,
			true,
			Util.newArrayListOfValues("鸟"),
			Util.newArrayListOfValues("鸟"),
			Util.newArrayListOfValues("温热"),
			"一股快感袭击了[npc.her]的下体，[npc.She]激动地尖叫起来，而伴随着那细缝不受控制地蠕动收缩，这种感觉愈加强烈。"
					+ "片刻后，感觉开始褪去，[npc.she]发现自己的小穴已经完全变成了鸟类的模样。"
				+ "</p>"
				+ "<p>"
					+ "正当[npc.she]以为转化已经步入尾声，[npc.her]的小穴却又不受控制地紧缩起来，"
						+ "温暖而刺痒的感觉在[npc.her]的下腹部蔓延，一阵难以压抑的尖叫从[npc.her][npc.lips+]中泄露而出。"
					+ "不停颤动的鸟类肉棒插进[npc.her]的秘缝的景象从[npc.her]眼前一掠而过，"
						+ "尖叫声逐渐转变为满足的呻吟，幻想中一股滚烫的子种汁深深注入了[npc.her]的鸟类子宫。"
					+ "这番想象来得快去得也快，转瞬便从脑海中消散，快感的余波席卷了[npc.her]的全身，"
						+ "[npc.she]觉得自己女性的生殖器官大概也已经完成了转化。"
					+ "<br/>[npc.Name]现在拥有[style.boldHarpy(鸟类的阴道)]，阴唇呈[npc.pussyColourPrimary(true)]，而内壁则是[npc.pussyColourSecondary(true)]。",
			"[npc.she]有着鸟类的小穴，#IF(npc.isPiercedVagina())且已经穿过孔，#ENDIF[npc.labiaSize]的[npc.pussyPrimaryColour(true)]阴唇，以及[npc.pussySecondaryColour(true)]的肉壁。",
			null) {
	};
	
	
	private static List<AbstractVaginaType> allVaginaTypes;
	private static Map<AbstractVaginaType, String> vaginaToIdMap = new HashMap<>();
	private static Map<String, AbstractVaginaType> idToVaginaMap = new HashMap<>();
	
	static {
		allVaginaTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("vagina")) {
					try {
						AbstractVaginaType type = new AbstractVaginaType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allVaginaTypes.add(type);
						vaginaToIdMap.put(type, id);
						idToVaginaMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// External res types:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("vagina")) {
					try {
						AbstractVaginaType type = new AbstractVaginaType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allVaginaTypes.add(type);
						vaginaToIdMap.put(type, id);
						idToVaginaMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded vagina types:
		
		Field[] fields = VaginaType.class.getFields();
		
		for(Field f : fields){
			if (AbstractVaginaType.class.isAssignableFrom(f.getType())) {
				
				AbstractVaginaType ct;
				try {
					ct = ((AbstractVaginaType) f.get(null));

					vaginaToIdMap.put(ct, f.getName());
					idToVaginaMap.put(f.getName(), ct);
					
					allVaginaTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allVaginaTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractVaginaType getVaginaTypeFromId(String id) {
		if(id.equals("IMP") || id.equals("DEMON_EGGS")) {
			return VaginaType.DEMON_COMMON;
		}
		if(id.equals("NoStepOnSnek_snake_vagina_e")) {
			id = "NoStepOnSnek_snake_vagina";
		}
		id = Util.getClosestStringMatch(id, idToVaginaMap.keySet());
		return idToVaginaMap.get(id);
	}
	
	public static String getIdFromVaginaType(AbstractVaginaType vaginaType) {
		return vaginaToIdMap.get(vaginaType);
	}
	
	public static List<AbstractVaginaType> getAllVaginaTypes() {
		return allVaginaTypes;
	}
	
	private static Map<AbstractRace, List<AbstractVaginaType>> typesMap = new HashMap<>();
	
	public static List<AbstractVaginaType> getVaginaTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractVaginaType> types = new ArrayList<>();
		for(AbstractVaginaType type : VaginaType.getAllVaginaTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}