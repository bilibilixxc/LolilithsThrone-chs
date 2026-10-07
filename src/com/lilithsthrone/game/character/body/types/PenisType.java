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
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.8.9
 * @author Innoxia
 */
public class PenisType {
	
	public static AbstractPenisType NONE = new AbstractPenisType(null,
			Race.NONE,
			TesticleType.NONE,
			"[npc.her]的阴茎和阴囊迅速收缩着，[npc.She]发出[npc.moansVerb]，身体扭动起来，没过几秒钟，曾经雄性的象征便无影无踪。<br/>"
				+ "[npc.Name]现在[style.boldSex(没有阴茎)]。",
			"", // Shouldn't need a description of no cock.
			null) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(!applicationAfterChangeApplied) {
				owner.setPiercedPenis(false);
			}
			return "";
		}
	};

	public static AbstractPenisType DILDO = new AbstractPenisType(BodyCoveringType.DILDO,
			Race.NONE,
			TesticleType.DILDO,
			Util.newArrayListOfValues("假阳具"),
			Util.newArrayListOfValues("假阳具"),
			Util.newArrayListOfValues("假阳具"),
			Util.newArrayListOfValues("假阳具"),
			Util.newArrayListOfValues("橡胶", "有弹性", "硅胶", "人造"),
			"你不知怎么把阴茎变成了假阳具，这是个bug。(请告知Innoxia！或汉化组)", // Dildos are not a transformable option
			"[npc.She]正戴着[npc.a_cockGirth]的假阳具，有[npc.cockLengthValue]长，由[npc.cockFullDescription(true)]制成。",
			null) {
	};
	
	public static AbstractPenisType HUMAN = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HUMAN,
			TesticleType.HUMAN,
			"[npc.She]现在拥有[style.boldHuman(人类的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldHuman([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF人类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldHuman(人类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的人类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			null) {
	};

	public static AbstractPenisType ANGEL = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.ANGEL,
			TesticleType.ANGEL,
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues("天使"),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldAngel(天使的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldAngel([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF天使阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldAngel(天使精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的天使阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			null) {
	};

	public static AbstractPenisType DEMON_COMMON = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.DEMON,
			TesticleType.DEMON_COMMON,
			Util.newArrayListOfValues("魅魔"),
			Util.newArrayListOfValues("魅魔"),
			Util.newArrayListOfValues("淫梦魔"),
			Util.newArrayListOfValues("淫梦魔"),
			Util.newArrayListOfValues("恶魔"),
			"[npc.She]浑身扭动起来，发出一声[npc.moansVerb]，覆盖[npc.her]阴茎的皮肤变得跟恶魔一般，十分光滑，也极度敏感。"
				+ "粘滑的先走液慢慢从尖端渗出，整根肉棒上都浮现出明显的青筋，[npc.she]发出了[npc.a_moan+]。"
				+ "转化还没有结束，上面逐渐长出许多突起，变作了细小的触手，自行蜿蜒扭动着。<br/>"
				+ "[npc.She]现在拥有"
				+ "#IF(npc.isShortStature())"
					+ "[style.boldImp(小恶魔的阴茎)]"
				+ "#ELSE"
					+ "[style.boldDemon(恶魔的阴茎)]"
				+ "#ENDIF"
				+ "，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]现在拥有"
					+ "#IF(npc.isShortStature())"
						+ "[style.boldImp([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF小恶魔阴囊)]，[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldImp(小恶魔精液)]。"
					+ "#ELSE"
						+ "[style.boldDemon([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF恶魔阴囊)]，[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldDemon(恶魔精液)]。"
					+ "#ENDIF",
			"[npc.She]有着[npc.a_cockGirth]的恶魔阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.RIBBED,
				PenetrationModifier.TENTACLED,
				PenetrationModifier.PREHENSILE)) {
	};

	public static AbstractPenisType COW_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.COW_MORPH,
			TesticleType.BOVINE,
			Util.newArrayListOfValues("牛"),
			Util.newArrayListOfValues("牛"),
			Util.newArrayListOfValues("牛"),
			Util.newArrayListOfValues("牛"),
			Util.newArrayListOfValues(""),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉竿变得粗壮起来，龟头也逐渐变尖。<br/>"
				+ "[npc.She]现在拥有[style.boldCowMorph(牛类阴茎)]，[npc.materialDescriptor][npc.penisFullDescription]。<br/>"
				+ "[npc.She]有着[style.boldCowMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF牛类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldCowMorph(牛类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的牛类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.TAPERED,
				PenetrationModifier.VEINY,
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType DOG_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.DOG_MORPH,
			TesticleType.CANINE,
			Util.newArrayListOfValues("犬", "狗"),
			Util.newArrayListOfValues("犬", "狗"),
			Util.newArrayListOfValues("犬"),
			Util.newArrayListOfValues("犬"),
			Util.newArrayListOfValues("犬类"),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉棒根部忽然鼓起一个粗壮的结。"
				+ "[npc.she]急促喘息着，阴茎的顶端渐渐变窄，成了完全不同的样子。<br/>"
				+ "[npc.She]现在拥有[style.boldDogMorph(犬类的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldDogMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF犬类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldDogMorph(犬类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的犬类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};
	
	public static AbstractPenisType WOLF_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.WOLF_MORPH,
			TesticleType.LUPINE,
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("狼"),
			Util.newArrayListOfValues("狼类"),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉棒根部忽然鼓起一个粗壮的结。"
				+ "[npc.she]急促喘息着，阴茎的顶端渐渐变窄，成了完全不同的样子。<br/>"
				+ "[npc.She]现在拥有[style.boldWolfMorph(狼类的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldWolfMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF狼类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldWolfMorph(狼类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的狼类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};
	
	public static AbstractPenisType FOX_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.FOX_MORPH,
			TesticleType.FOX_MORPH,
			Util.newArrayListOfValues("狐", "狐狸"),
			Util.newArrayListOfValues("狐", "狐狸"),
			Util.newArrayListOfValues("狐"),
			Util.newArrayListOfValues("狐"),
			Util.newArrayListOfValues("狐类"),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉棒根部忽然鼓起一个粗壮的结。"
				+ "[npc.she]急促喘息着，阴茎的顶端渐渐变窄，成了完全不同的样子。<br/>"
				+ "[npc.She]现在拥有[style.boldFoxMorph(狐类的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldFoxMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF狐类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldFoxMorph(狐类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的狐类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.KNOTTED,
				PenetrationModifier.SHEATHED,
				PenetrationModifier.TAPERED)) {
		@Override
		public String applyAdditionalTransformationEffects(GameCharacter owner, boolean applicationAfterChangeApplied) {
			if(applicationAfterChangeApplied) {
				return owner.setSkinCovering(new Covering(BodyCoveringType.PENIS, PresetColour.SKIN_RED), false);
			}
			return "";
		}
	};

	public static AbstractPenisType CAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.CAT_MORPH,
			TesticleType.FELINE,
			Util.newArrayListOfValues("猫"),
			Util.newArrayListOfValues("猫"),
			Util.newArrayListOfValues("猫"),
			Util.newArrayListOfValues("猫"),
			Util.newArrayListOfValues("猫类"),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，"
					+ "[npc.sheIs]忽然被一股无法抗拒的快感袭击，一行行肉质的倒刺在肉竿上浮现出来。<br/>"
				+ "[npc.She]现在拥有[style.boldCatMorph(猫类的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldCatMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF猫类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldCatMorph(猫类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的猫类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.BARBED,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType ALLIGATOR_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.ALLIGATOR_MORPH,
			TesticleType.ALLIGATOR_MORPH,
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("鳄鱼"),
			Util.newArrayListOfValues("爬行类"),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉竿挺立起来，也变成了圆头。<br/>"
				+ "[npc.She]现在拥有[style.boldGatorMorph(鳄鱼般的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldGatorMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF爬行类阴囊)]，"
						+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldGatorMorph(鳄鱼精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的鳄鱼阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.BLUNT)) {
	};

	public static AbstractPenisType EQUINE = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HORSE_MORPH,
			TesticleType.EQUINE,
			Util.newArrayListOfValues("马", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF", "马类"),
			Util.newArrayListOfValues("马", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF", "马类"),
			Util.newArrayListOfValues("马", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF", "马类"),
			Util.newArrayListOfValues("马", "#IF(npc.getRace()==npc.getPenisRace())[npc.raceFeral]#ELSE[npc.cockRaceFeral]#ENDIF", "马类"),
			Util.newArrayListOfValues(""),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉竿变得粗壮起来，龟头也变得平坦。<br/>"
				+ "[npc.She]现在拥有[style.boldHorseMorph(马类阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldHorseMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF马类阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldHorseMorph(马类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的马类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.FLARED,
				PenetrationModifier.VEINY,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType REINDEER_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.REINDEER_MORPH,
			TesticleType.REINDEER_MORPH,
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues("驯鹿"),
			Util.newArrayListOfValues(""),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉竿变得粗壮起来，龟头也变得平坦。<br/>"
				+ "[npc.She]现在拥有[style.boldReindeerMorph(驯鹿般的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldReindeerMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内#ENDIF驯鹿般的阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldReindeerMorph(驯鹿精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的驯鹿阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.TAPERED,
				PenetrationModifier.SHEATHED)) {
	};

	public static AbstractPenisType HARPY = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.HARPY,
			TesticleType.AVIAN,
			Util.newArrayListOfValues("哈比"),
			Util.newArrayListOfValues("哈比"),
			Util.newArrayListOfValues("哈比"),
			Util.newArrayListOfValues("哈比"),
			Util.newArrayListOfValues("鸟类"),
			"[npc.Name]意识到[npc.her]的阴茎开始转化成其他形态，发出了一声不情愿的呻吟，[npc.sheIs]忽然被一股无法抗拒的快感袭击，肉棒收回了根部刚刚出现的阴茎鞘中。<br/>"
				+ "[npc.She]现在拥有[style.boldHarpy(鸟类的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldHarpy([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF鸟类阴囊)]，[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldHarpy(鸟类精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的鸟类阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType SQUIRREL_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.SQUIRREL_MORPH,
			TesticleType.SQUIRREL,
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("松鼠"),
			Util.newArrayListOfValues("啮齿类"),
			"[npc.She]现在拥有[style.boldSquirrelMorph(松鼠般的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldSquirrelMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF松鼠阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldSquirrelMorph(松鼠精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的松鼠阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType RAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.RAT_MORPH,
			TesticleType.RAT_MORPH,
			Util.newArrayListOfValues("鼠"),
			Util.newArrayListOfValues("鼠"),
			Util.newArrayListOfValues("鼠"),
			Util.newArrayListOfValues("鼠"),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldRatMorph(老鼠般的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldRatMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF老鼠阴囊)]，"
					+ "，能够生产[npc.cumColour(true)]的[style.boldRatMorph(老鼠精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的老鼠阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType RABBIT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.RABBIT_MORPH,
			TesticleType.RABBIT_MORPH,
			Util.newArrayListOfValues("兔"),
			Util.newArrayListOfValues("兔"),
			Util.newArrayListOfValues("兔"),
			Util.newArrayListOfValues("兔"),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldRabbitMorph(兔子般的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldRabbitMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF兔子阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldRabbitMorph(兔子精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的兔子阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	public static AbstractPenisType BAT_MORPH = new AbstractPenisType(BodyCoveringType.PENIS,
			Race.BAT_MORPH,
			TesticleType.BAT_MORPH,
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues("蝙蝠"),
			Util.newArrayListOfValues(""),
			"[npc.She]现在拥有[style.boldBatMorph(蝙蝠般的阴茎)]，[npc.materialDescriptor][npc.penisFullDescription(true)]。<br/>"
				+ "[npc.She]有着[style.boldBatMorph([npc.ballsCount]颗#IF(npc.isInternalTesticles())体内的#ENDIF蝙蝠阴囊)]，"
					+ "[npc.materialDescriptor][npc.ballsFullDescription(true)]，能够生产[npc.cumColour(true)]的[style.boldBatMorph(蝙蝠精液)]。",
			"[npc.She]有着[npc.a_cockGirth]的蝙蝠阴茎，[npc.cockLengthValue]长，[npc.materialCompositionDescriptor][npc.cockFullDescription(true)]。",
			Util.newArrayListOfValues(
				PenetrationModifier.SHEATHED)) {
	};
	
	
	private static List<AbstractPenisType> allPenisTypes;
	private static Map<AbstractPenisType, String> penisToIdMap = new HashMap<>();
	private static Map<String, AbstractPenisType> idToPenisMap = new HashMap<>();
	
	static {
		allPenisTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("penis")) {
					try {
						AbstractPenisType type = new AbstractPenisType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allPenisTypes.add(type);
						penisToIdMap.put(type, id);
						idToPenisMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("penis")) {
					try {
						AbstractPenisType type = new AbstractPenisType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allPenisTypes.add(type);
						penisToIdMap.put(type, id);
						idToPenisMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded penis types:
		
		Field[] fields = PenisType.class.getFields();
		
		for(Field f : fields){
			if (AbstractPenisType.class.isAssignableFrom(f.getType())) {
				
				AbstractPenisType ct;
				try {
					ct = ((AbstractPenisType) f.get(null));

					penisToIdMap.put(ct, f.getName());
					idToPenisMap.put(f.getName(), ct);
					
					allPenisTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allPenisTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractPenisType getPenisTypeFromId(String id) {
		if(id.equals("IMP")) {
			return PenisType.DEMON_COMMON;
		}
		if(id.equals("BOVINE")) {
			return PenisType.COW_MORPH;
		}
		if(id.equals("CANINE")) {
			return PenisType.DOG_MORPH;
		}
		if(id.equals("LUPINE")) {
			return PenisType.WOLF_MORPH;
		}
		if(id.equals("VULPINE")) {
			return PenisType.FOX_MORPH;
		}
		if(id.equals("FELINE")) {
			return PenisType.CAT_MORPH;
		}
		if(id.equals("AVIAN")) {
			return PenisType.HARPY;
		}
		if(id.equals("SQUIRREL")) {
			return PenisType.SQUIRREL_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToPenisMap.keySet());
		return idToPenisMap.get(id);
	}
	
	public static String getIdFromPenisType(AbstractPenisType penisType) {
		return penisToIdMap.get(penisType);
	}
	
	public static List<AbstractPenisType> getAllPenisTypes() {
		return allPenisTypes;
	}
	
	private static Map<AbstractRace, List<AbstractPenisType>> typesMap = new HashMap<>();
	
	public static List<AbstractPenisType> getPenisTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractPenisType> types = new ArrayList<>();
		for(AbstractPenisType type : PenisType.getAllPenisTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}

}