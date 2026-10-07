package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractEarType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class EarType {
	
	public static AbstractEarType HUMAN = new AbstractEarType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			"人类",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("柔软", "阴柔"),
			"发热的瘙痒只持续了很短的一段时间，接着给[npc.herHim]留下了一对正常人类的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldHuman(人类的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对普通的人类耳朵，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType ANGEL = new AbstractEarType(BodyCoveringType.ANGEL,
			Race.ANGEL,
			"天使",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("尖尖", "精致", "天使"),
			Util.newArrayListOfValues("柔软", "阴柔", "尖尖", "精致", "天使"),
			"发热的瘙痒只持续了很短的一段时间，接着给[npc.herHim]留下了一对人类样子的耳朵，但顶端却尖尖的，长长探出。<br/>"
				+ "[npc.Name]现在拥有[style.boldAngel(尖尖的天使耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对尖尖的天使耳朵，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType DEMON_COMMON = new AbstractEarType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			"恶魔",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("尖尖", "恶魔"),
			Util.newArrayListOfValues("柔软", "阴柔", "尖尖", "恶魔"),
			"发热的瘙痒只持续了很短的一段时间，接着给[npc.herHim]留下了一对人类样子的耳朵，但顶端却尖尖的，长长探出。<br/>"
				+ "[npc.Name]现在拥有[style.boldDemon(尖尖的恶魔耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对尖尖的恶魔耳朵，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};
	
	public static AbstractEarType DOG_MORPH = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"犬(下垂)",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("下垂", "毛茸茸", "覆盖皮毛", "犬一般"),
			Util.newArrayListOfValues("阴柔", "下垂", "毛茸茸", "覆盖皮毛", "犬一般"),
			"[npc.ears]忽然就变得很大，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的犬一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldDog(犬一般下垂的大耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对犬一般下垂的大耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType DOG_MORPH_POINTED = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"尖犬",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("尖尖", "毛茸茸", "覆盖皮毛", "犬一般"),
			Util.newArrayListOfValues("阴柔", "尖尖", "毛茸茸", "覆盖皮毛", "犬一般"),
			"[npc.ears]忽然就变得竖直上指，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的犬一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldDog(犬一般尖尖的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对犬一般尖尖的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType DOG_MORPH_FOLDED = new AbstractEarType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"折犬",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("弯折", "毛茸茸", "覆盖皮毛", "犬一般"),
			Util.newArrayListOfValues("弯折", "下垂", "毛茸茸", "覆盖皮毛", "犬一般"),
			"[npc.ears]忽然就变得尖尖的，竖直上指，随后再中间又弯折下来，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的犬一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldDog(犬一般弯折的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对犬一般弯折的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType WOLF_MORPH = new AbstractEarType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			"狼",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛", "狼一般"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "狼一般"),
			"[npc.ears]忽然就变得很大，高高竖起，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的狼一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldWolf(狼一般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对狼一般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType FOX_MORPH = new AbstractEarType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			"狐狸",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("尖尖", "毛茸茸", "覆盖皮毛", "狐狸般"),
			Util.newArrayListOfValues("阴柔", "尖尖", "毛茸茸", "覆盖皮毛", "狐狸般"),
			"[npc.ears]忽然就变得很大，高高竖起，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的狐狸般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldFox(狐狸般尖尖的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对狐狸般尖尖的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType FOX_MORPH_BIG = new AbstractEarType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			"耳廓狐",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("尖尖", "毛茸茸", "覆盖皮毛", "格外大", "耳廓狐般"),
			Util.newArrayListOfValues("阴柔", "尖尖", "毛茸茸", "覆盖皮毛", "格外大", "耳廓狐般"),
			"[npc.ears]忽然就变得巨大，竖直上指，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的狐狸般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldFox(耳廓狐般巨大的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对耳廓狐般巨大的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType COW_MORPH = new AbstractEarType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			"牛",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛", "牛一般"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "牛一般"),
			"[npc.ears]忽然变成特别像牛的样子，向外探出，越靠近顶端越纤细，形成了稍有些内弯的椭圆形。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的牛一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldCow(牛一般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对#IF(npc.isPiercedEar())打过耳洞的#ENDIF牛一样的耳朵，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]。") {
	};

	public static AbstractEarType CAT_MORPH = new AbstractEarType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			"猫",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛", "猫一般"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "猫一般"),
			"[npc.ears]忽然就变得竖直上指，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的猫一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldCat(猫一般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对猫一般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType CAT_MORPH_TUFTED = new AbstractEarType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			"猫(簇毛)",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("顶端簇状", "毛茸茸", "覆盖皮毛", "猫般"),
			Util.newArrayListOfValues("阴柔", "顶端簇状", "毛茸茸", "覆盖皮毛", "猫般"),
			"[npc.ears]忽然就变得尖尖的，竖直上指，顶端还长了一簇敏感的皮毛，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的猫一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldCat(猫一般的耳朵)]，顶端长了一小撮毛，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对猫一般的耳朵，位于脑袋的顶端，尖端长了一小撮毛，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType SQUIRREL_MORPH = new AbstractEarType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			"松鼠",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛", "松鼠般"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "覆盖皮毛", "松鼠般"),
			"[npc.ears]忽然就变得很小巧，竖直上指，呈椭圆形，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的松鼠般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldSquirrel(松鼠般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对松鼠般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType RAT_MORPH = new AbstractEarType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			"老鼠",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("老鼠般"),
			Util.newArrayListOfValues("阴柔", "老鼠般"),
			"[npc.ears]忽然就变得很小巧，竖直上指，呈椭圆形，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的老鼠般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldRat(老鼠般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对老鼠般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType RABBIT_MORPH = new AbstractEarType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			"兔(直立)",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("竖直", "毛茸茸", "覆盖皮毛", "兔子般"),
			Util.newArrayListOfValues("阴柔", "竖直", "毛茸茸", "覆盖皮毛", "兔子般"),
			"[npc.ears]忽然就变得很大，高高竖起，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的兔子般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldRabbit(兔子般上竖的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对兔子般上竖的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType RABBIT_MORPH_FLOPPY = new AbstractEarType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			"兔(下垂)",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("下垂", "毛茸茸", "覆盖皮毛", "兔子般"),
			Util.newArrayListOfValues("阴柔", "下垂", "毛茸茸", "覆盖皮毛", "兔子般"),
			"[npc.ears]忽然就变得很大，高高竖起，并且转移到脑袋更高的部分，而非原来人类耳朵的位置，但最后又突然垂落下去，搭在了两边。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的兔子般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldRabbit(兔子般下垂的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对兔子般下垂的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.EAR_HANDLES_IN_SEX);
		}
	};

	public static AbstractEarType BAT_MORPH = new AbstractEarType(BodyCoveringType.BAT_FUR,
			Race.BAT_MORPH,
			"蝙蝠",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("格外大", "蝙蝠般"),
			Util.newArrayListOfValues("阴柔", "格外大", "蝙蝠般"),
			"[npc.ears]忽然就变得很大，高高竖起，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的蝙蝠般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldBat(蝙蝠般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对蝙蝠般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.ECHO_LOCATION);
		}
	};

	public static AbstractEarType HORSE_MORPH = new AbstractEarType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			"马",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "竖直", "马一般"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "竖直", "马一般"),
			"[npc.ears]忽然就变得有些粗短，略微上指，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的马一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldHorse(马一般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对马一般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType HORSE_MORPH_UPRIGHT = new AbstractEarType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			"马(长直)",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "较高", "竖直", "马一般"),
			Util.newArrayListOfValues("阴柔", "较高", "毛茸茸", "竖直", "马般"),
			"[npc.ears]忽然就变得竖直上指，更加长一些，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的马一般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldHorse(马一般高高的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对马一般高而上竖的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};
	
	public static AbstractEarType REINDEER_MORPH = new AbstractEarType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			"驯鹿",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("毛茸茸", "驯鹿般"),
			Util.newArrayListOfValues("阴柔", "毛茸茸", "驯鹿般"),
			"[npc.ears]忽然变成特别像驯鹿的样子，向外探出，越靠近顶端越纤细，形成了稍有些内弯的椭圆形，并且转移到脑袋更高的部分，而非原来人类耳朵的位置。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在上面，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]试着前后抖了抖刚刚获得的驯鹿般的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldReindeer(驯鹿般的耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.She]有一对驯鹿般的耳朵，位于脑袋的顶端，[npc.materialCompositionDescriptor][npc.earFullDescription(true)]#IF(npc.isPiercedEar())，且已经打过耳洞#ENDIF。") {
	};

	public static AbstractEarType ALLIGATOR_MORPH = new AbstractEarType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			"鳄鱼",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("带鳞", "覆盖鳞片", "鳄鱼般"),
			Util.newArrayListOfValues("阴柔", "带鳞", "覆盖鳞片", "鳄鱼般"),
			"[npc.ears]迅速收缩，只剩下了一小片，而大部分软骨组织则没入了脑袋里。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在几乎完全没入体内的耳朵上，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，这几乎完全没入体内的耳朵完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]得到了鳄鱼化形的耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldAlligator(鳞片覆盖、位于体内的鳄鱼耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.her]的耳朵现在位于脑袋内部，覆盖着一片[npc.earFullDescription(true)]。"
				+ "#IF(npc.isPiercedEar())并且很巧妙地穿了孔，从而能够戴上为耳部设计的首饰。#ENDIF") {
	};
	
	public static AbstractEarType HARPY = new AbstractEarType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			"哈比",
			"耳朵",
			"耳朵",
			Util.newArrayListOfValues("长有羽毛", "羽毛覆盖", "鸟一般"),
			Util.newArrayListOfValues("阴柔", "长有羽毛", "羽毛覆盖", "鸟一般"),
			"[npc.ears]迅速收缩，只剩下了一小片，而大部分软骨组织则没入了脑袋里。"
				+ "#IF(npc.getBodyMaterial()==BODY_MATERIAL_FLESH)"
				+ "一层[npc.earFullDescriptionColour]迅速覆盖在几乎完全没入体内的耳朵上，"
				+ "#ELSE"
				+ "就与身体的其他部位一样，这几乎完全没入体内的耳朵完全是由[npc.earFullDescription]构成，"
				+ "#ENDIF"
				+ "转化步入尾声后，[npc.she]得到了有美丽羽毛装饰的哈比耳朵。<br/>"
				+ "[npc.Name]现在拥有[style.boldHarpy(羽毛覆盖、位于体内的哈比耳朵)]，[npc.materialCompositionDescriptor][npc.earFullDescription]。",
			"[npc.her]的耳朵现在位于脑袋内部，覆盖着一片[npc.earFullDescription(true)]。"
				+ "#IF(npc.isPiercedEar())并且很巧妙地穿了孔，从而能够戴上为耳部设计的首饰。#ENDIF") {
	};
	
	private static List<AbstractEarType> allEarTypes;
	private static Map<AbstractEarType, String> earToIdMap = new HashMap<>();
	private static Map<String, AbstractEarType> idToEarMap = new HashMap<>();
	
	static {
		allEarTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ear")) {
					try {
						AbstractEarType type = new AbstractEarType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEarTypes.add(type);
						earToIdMap.put(type, id);
						idToEarMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("ear")) {
					try {
						AbstractEarType type = new AbstractEarType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allEarTypes.add(type);
						earToIdMap.put(type, id);
						idToEarMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded ear types:
		
		Field[] fields = EarType.class.getFields();
		
		for(Field f : fields){
			if (AbstractEarType.class.isAssignableFrom(f.getType())) {
				
				AbstractEarType ct;
				try {
					ct = ((AbstractEarType) f.get(null));

					earToIdMap.put(ct, f.getName());
					idToEarMap.put(f.getName(), ct);
					
					allEarTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allEarTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractEarType getEarTypeFromId(String id) {
		if(id.equals("IMP")) {
			return EarType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return EarType.WOLF_MORPH;
		}
		id = Util.getClosestStringMatch(id, idToEarMap.keySet());
		return idToEarMap.get(id);
	}
	
	public static String getIdFromEarType(AbstractEarType earType) {
		return earToIdMap.get(earType);
	}
	
	public static List<AbstractEarType> getAllEarTypes() {
		return allEarTypes;
	}
	
	private static Map<AbstractRace, List<AbstractEarType>> typesMap = new HashMap<>();
	
	public static List<AbstractEarType> getEarTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractEarType> types = new ArrayList<>();
		for(AbstractEarType type : EarType.getAllEarTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}