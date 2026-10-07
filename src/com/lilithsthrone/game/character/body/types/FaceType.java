package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.9.1
 * @author Innoxia
 */
public class FaceType {
	
	public static AbstractFaceType HUMAN = new AbstractFaceType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			MouthType.HUMAN,
			null,
			null,
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"万幸的是，转化只持续了很短的时间，[npc.herHim]变成了普通的人类面容，[npc.materialDescriptor][npc.faceSkin+]。<br/>"
				+ "[npc.Name]现在拥有[style.boldHuman(人类的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldHuman(人类舌头)]。",
			"[npc.SheHasFull]长着一副[npc.a_feminineDescriptor(true)]的人类面孔，[npc.materialDescriptor][npc.faceFullDescription(true)]。",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType ANGEL = new AbstractFaceType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			MouthType.ANGEL,
			null,
			null,
			Util.newArrayListOfValues("完美", "无暇", "天使般"),
			Util.newArrayListOfValues("完美", "无暇", "天使般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"万幸的是，转化只持续了很短的时间，[npc.herHim]变成了与人类相似的天使面容，[npc.materialDescriptor][npc.faceSkin+]。<br/>"
					+ "[npc.Name]现在拥有[style.boldAngel(天使的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
					+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldAngel(天使舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的天使面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]。",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType DEMON_COMMON = new AbstractFaceType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			MouthType.DEMON_COMMON,
			null,
			null,
			Util.newArrayListOfValues("完美", "无暇", "恶魔般"),
			Util.newArrayListOfValues("完美", "无暇", "恶魔般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"#IF(npc.isShortStature())"
				+ "万幸的是，转化只持续了很短的时间，[npc.herHim]变成了与人类相似的小恶魔面容，[npc.materialDescriptor][npc.faceSkin+]。<br/>"
				+ "[npc.Name]现在拥有[style.boldImp(小恶魔的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldImp(小恶魔舌头)]。"
			+ "#ELSE"
				+ "万幸的是，转化只持续了很短的时间，[npc.herHim]变成了与人类相似的恶魔面容，[npc.materialDescriptor][npc.faceSkin+]。<br/>"
				+ "[npc.Name]现在拥有[style.boldDemon(恶魔的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldDemon(恶魔舌头)]。"
			+ "#ENDIF",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的#IF(npc.isShortStature())小恶魔#ELSE恶魔#ENDIF面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]。",
			Util.newArrayListOfValues()){
	};

	public static AbstractFaceType ALLIGATOR_MORPH = new AbstractFaceType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			MouthType.ALLIGATOR_MORPH,
			null,
			null,
			Util.newArrayListOfValues("鳄鱼般拟人", "鳄鱼般", "爬行动物般"),
			Util.newArrayListOfValues("鳄鱼般拟人", "鳄鱼般", "爬行动物般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的爬行动物的吻部，舌头也变得十分有力，与鳄鱼类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldAlligatorMorph(鳄鱼般的拟人面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldAlligatorMorph(有力的鳄鱼舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的鳄鱼面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有长而平的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有长而平的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_SCALY
				)){
	};

	public static AbstractFaceType BAT_MORPH = new AbstractFaceType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			MouthType.BAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("蝙蝠般拟人", "蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般拟人", "蝙蝠般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的蝙蝠吻部，舌头也变得比较细窄，与蝙蝠类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldBatMorph(蝙蝠般的拟人面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldBatMorph(细窄的蝙蝠舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的蝙蝠面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有短小的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有短小的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType CAT_MORPH = new AbstractFaceType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			MouthType.CAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("猫一般拟人", "猫一般", "猫类"),
			Util.newArrayListOfValues("猫一般拟人", "猫一般", "猫类"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的猫类吻部，舌头也变得平直，与猫类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldCatMorph(猫一般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldCatMorph(平直的猫类舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的猫的面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有小而可爱的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有小而可爱的猫科吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

//	public static AbstractFaceType CAT_MORPH_PANTHER = new AbstractFaceType(BodyCoveringType.FELINE_FUR,
//			Race.CAT_MORPH,
//			MouthType.CAT_MORPH,
//			null,
//			null,
//			Util.newArrayListOfValues("anthropomorphic panther-like", "panther-like", "panther"),
//			Util.newArrayListOfValues("anthropomorphic panther-like", "panther-like", "panther"),
//			"nose",
//			"noses",
//			Util.newArrayListOfValues(""),
//			Util.newArrayListOfValues(""),
//			"[npc.Her] nose and mouth twitch and transform as they push out into an anthropomorphic panther-like muzzle, and [npc.her] tongue flattens and transforms into a cat-like one."
//				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
//					+ " A layer of [npc.faceSkin+] quickly grows to cover [npc.her] new face"
//				+ "#ELSE"
//					+ " Just like the rest of [npc.her] body, [npc.her] new face is [npc.materialDescriptor] [npc.faceSkin+]"
//				+ "#ENDIF"
//					+ ", and as the transformation finally comes to an end, [npc.sheIs] left panting and sighing as [npc.she] [npc.verb(try)] to catch [npc.her] breath.<br/>"
//				+ "[npc.Name] now [npc.has] an anthropomorphic [style.boldCatMorph(panther-like face)], [npc.materialDescriptor] [npc.faceFullDescription]."
//				+ " Within [npc.her] [npc.mouth], [npc.sheHasFull] a [style.boldCatMorph(flat, cat-like tongue)].",
//			"[npc.SheHasFull] [npc.a_feminineDescriptor(true)], anthropomorphic, panther-like face [npc.materialCompositionDescriptor] [npc.faceFullDescription(true)] and complete with a powerful, toothy muzzle, big nose, and strong jawline.",
//			"[npc.SheHasFull] the [npc.feminineDescriptor(true)] face of a feral [npc.legRace], which is [npc.materialDescriptor] [npc.faceFullDescription(true)] and complete with a powerful, toothy muzzle, big nose, and strong jawline.",
//			Util.newArrayListOfValues(
//					BodyPartTag.FACE_MUZZLE,
//					BodyPartTag.FACE_FANGS,
//					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
//				)){
//	};

	public static AbstractFaceType COW_MORPH = new AbstractFaceType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			MouthType.COW_MORPH,
			null,
			null,
			Util.newArrayListOfValues("牛一般拟人", "牛一般", "牛类"),
			Util.newArrayListOfValues("牛一般拟人", "牛一般", "牛类"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的爬行动物的牛类吻部，舌头也变得十分有力，与牛类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldCowMorph(牛一般的拟人面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldCowMorph(有力的牛舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的牛一般的面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有牛类的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有牛类的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType DOG_MORPH = new AbstractFaceType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			MouthType.DOG_MORPH,
			null,
			null,
			Util.newArrayListOfValues("犬一般拟人", "犬一般", "犬类"),
			Util.newArrayListOfValues("犬一般拟人", "犬一般", "犬类"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的犬类吻部，舌头也变又平又宽，与狗类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldDogMorph(犬一般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldDogMorph(平直的犬类舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的犬一般的面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有犬类的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有犬类的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType FOX_MORPH = new AbstractFaceType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			MouthType.FOX_MORPH,
			null,
			null,
			Util.newArrayListOfValues("狐狸般拟人", "狐狸般"),
			Util.newArrayListOfValues("狐狸般拟人", "狐狸般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的狐类吻部，舌头也变得平直，与狐狸类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldFoxMorph(狐狸般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldFoxMorph(平直的狐狸舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的狐狸般的面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有细长的狐类吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有细长的狐类吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType HARPY = new AbstractFaceType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			MouthType.HARPY,
			null,
			null,
			Util.newArrayListOfValues("鸟一般拟人", "鸟一般"),
			Util.newArrayListOfValues("鸟一般拟人", "鸟一般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便融合在一起，变成了个粗短的鸟喙，舌头也变得很细，与鸟类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部，"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]，"
				+ "#ENDIF"
					+ "转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。"
				+ "[npc.She]发现自己可以随意地软化硬化喙部边缘，这才松了口气，"
					+ "这使得[npc.her]可以通过弯曲喙的边缘，模仿各种嘴巴能够摆出的样子，来展现面部动作。<br/>"
				+ "[npc.Name]现在拥有[style.boldHarpy(鸟一般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldHarpy(细窄的鸟类舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的鸟一般的面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有一只喙。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有一只喙。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_BEAK,
					BodyPartTag.FACE_NATURAL_BALDNESS_AVIAN
				)){
	};

	public static AbstractFaceType HORSE_MORPH = new AbstractFaceType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			MouthType.HORSE_MORPH,
			null,
			null,
			Util.newArrayListOfValues("马一般拟人", "马一般", "马类"),
			Util.newArrayListOfValues("马一般拟人", "马一般", "马类"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的马类吻部，舌头也变得十分有力，与马类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldHorseMorph(马一般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldHorseMorph(有力的马类舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的马一般的面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有长长的马类吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有长长的马类吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY // Note: Some horse races only have hair on the neck aka a mane so its not totally unnatural to have a bald face
				)){
	};

	public static AbstractFaceType RABBIT_MORPH = new AbstractFaceType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			MouthType.RABBIT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("兔子般拟人", "兔子般"),
			Util.newArrayListOfValues("兔子般拟人", "兔子般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的兔子吻部，舌头也变得比较细窄，与兔子类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldRabbitMorph(兔子般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldRabbitMorph(细窄的兔子舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的兔子面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有短小的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有短小的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType RAT_MORPH = new AbstractFaceType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			MouthType.RAT_MORPH,
			null,
			null,
			Util.newArrayListOfValues("老鼠般拟人", "老鼠般", "啮齿类"),
			Util.newArrayListOfValues("老鼠般拟人", "老鼠般", "啮齿类"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的老鼠吻部，舌头也变得比较细窄，与老鼠类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldRatMorph(老鼠般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldRatMorph(细窄的老鼠舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的老鼠面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有长长的啮齿类吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有长长的啮齿类吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType REINDEER_MORPH = new AbstractFaceType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			MouthType.REINDEER_MORPH,
			null,
			null,
			Util.newArrayListOfValues("驯鹿般拟人", "驯鹿般", "驯鹿"),
			Util.newArrayListOfValues("驯鹿般拟人", "驯鹿般", "驯鹿"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的驯鹿吻部，舌头也变得十分有力，与驯鹿类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldReindeerMorph(驯鹿般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldReindeerMorph(有力的驯鹿舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的驯鹿面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有长长的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有长长的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType SQUIRREL_MORPH = new AbstractFaceType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			MouthType.SQUIRREL_MORPH,
			null,
			null,
			Util.newArrayListOfValues("松鼠般拟人", "松鼠般", "啮齿类"),
			Util.newArrayListOfValues("松鼠般拟人", "松鼠般", "啮齿类"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的松鼠吻部，舌头也变得比较细窄，与松鼠类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldSquirrelMorph(松鼠般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldSquirrelMorph(细窄的松鼠舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的松鼠面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有短小的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有短小的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};

	public static AbstractFaceType WOLF_MORPH = new AbstractFaceType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			MouthType.WOLF_MORPH,
			null,
			null,
			Util.newArrayListOfValues("狼一般拟人", "狼一般"),
			Util.newArrayListOfValues("狼一般拟人", "狼一般"),
			"鼻子",
			"鼻子",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"[npc.Her]的鼻子和嘴巴微微抽搐，随后便转化为似人的狼类吻部，舌头也变又平又宽，与狼类似。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
					+ "一层[npc.faceSkin+]迅速覆盖了[npc.her]的面部"
				+ "#ELSE"
					+ "与身体的其余部分一样，[npc.her]的面部[npc.materialDescriptor][npc.faceSkin+]"
				+ "#ENDIF"
					+ "，转化终于步入尾声，[npc.sheIs]气喘吁吁地攫取着氧气。<br/>"
				+ "[npc.Name]现在拥有[style.boldWolfMorph(狼一般拟人的面容)]，[npc.materialDescriptor][npc.faceFullDescription]。"
				+ "在[npc.her]的[npc.mouth]里，有着一根[style.boldWolfMorph(平直的狼类舌头)]。",
			"[npc.SheHasFull]拥有[npc.a_feminineDescriptor(true)]的狼类面庞，有人类特征，[npc.materialCompositionDescriptor][npc.faceFullDescription(true)]，且有长长的吻部。",
			"[npc.SheHasFull]拥有[npc.feminineDescriptor(true)]的兽态[npc.legRace]面庞，[npc.materialDescriptor][npc.faceFullDescription(true)]，且有长长的吻部。",
			Util.newArrayListOfValues(
					BodyPartTag.FACE_MUZZLE,
					BodyPartTag.FACE_FANGS,
					BodyPartTag.FACE_NATURAL_BALDNESS_FURRY
				)){
	};
	

	private static List<AbstractFaceType> allFaceTypes;
	private static Map<AbstractFaceType, String> faceToIdMap = new HashMap<>();
	private static Map<String, AbstractFaceType> idToFaceMap = new HashMap<>();
	
	static {
		allFaceTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("face")) {
					try {
						AbstractFaceType type = new AbstractFaceType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFaceTypes.add(type);
						faceToIdMap.put(type, id);
						idToFaceMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("face")) {
					try {
						AbstractFaceType type = new AbstractFaceType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allFaceTypes.add(type);
						faceToIdMap.put(type, id);
						idToFaceMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded face types:
		
		Field[] fields = FaceType.class.getFields();
		
		for(Field f : fields){
			if (AbstractFaceType.class.isAssignableFrom(f.getType())) {
				
				AbstractFaceType ct;
				try {
					ct = ((AbstractFaceType) f.get(null));

					faceToIdMap.put(ct, f.getName());
					idToFaceMap.put(f.getName(), ct);
					
					allFaceTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allFaceTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractFaceType getFaceTypeFromId(String id) {
		if(id.equals("IMP")) {
			return FaceType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return FaceType.WOLF_MORPH;
		}
		if(id.equals("TENGU")) {
			return FaceType.HARPY;
		}
		if(id.equals("CAT_MORPH_PANTHER")) {
			id = "innoxia_panther_face";
		}
		
		id = Util.getClosestStringMatch(id, idToFaceMap.keySet());
		return idToFaceMap.get(id);
	}
	
	public static String getIdFromFaceType(AbstractFaceType faceType) {
		return faceToIdMap.get(faceType);
	}
	
	public static List<AbstractFaceType> getAllFaceTypes() {
		return allFaceTypes;
	}
	
	private static Map<AbstractRace, List<AbstractFaceType>> typesMap = new HashMap<>();
	
	public static List<AbstractFaceType> getFaceTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractFaceType> types = new ArrayList<>();
		for(AbstractFaceType type : FaceType.getAllFaceTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}