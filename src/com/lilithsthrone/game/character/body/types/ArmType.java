package com.lilithsthrone.game.character.body.types;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.body.abstractTypes.AbstractArmType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * Contains static instances of AbstractArmType.
 * 
 * @since 0.1.0
 * @version 0.3
 * @author Innoxia
 */
public class ArmType {

	public static AbstractArmType HUMAN = new AbstractArmType(BodyCoveringType.HUMAN,
			Race.HUMAN,
			"手臂",
			"手臂",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues(""),
			"手",
			"手",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("柔嫩", "阴柔"),
			"手指",
			"手指",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("柔嫩", "阴柔"),
			"万幸的是，这种转化只持续了很短的时间，接着又还给了[npc.herHim]一副正常人类的手臂，连带着完好的手掌。<br/>"
				+ "[npc.Name]现在有着[style.boldHuman(人类的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]有着[npc.armRows]人类的手臂和手，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。") {
	};

	public static AbstractArmType ANGEL = new AbstractArmType(BodyCoveringType.HUMAN,
			Race.ANGEL,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("精致"),
			Util.newArrayListOfValues("精致"),
			"手",
			"手",
			Util.newArrayListOfValues("精致", "柔软"),
			Util.newArrayListOfValues("精致", "柔软", "阴柔"),
			"手指",
			"手指",
			Util.newArrayListOfValues("精致", "柔软"),
			Util.newArrayListOfValues("精致", "柔软", "阴柔"),
			"不一会儿，它们变成了细长的、像人一样的手臂，还有像人一样的手。"
				+ "尽管外表有些平庸，但却有一种微妙而诱人的质地，揭示了其真正的天使本质。<br/>"
				+ "[npc.Name]有着[style.boldAngel(天使的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]",
			"[npc.She]有着[npc.armRows]人类的手臂和手，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。") {
	};

	public static AbstractArmType DEMON_COMMON = new AbstractArmType(BodyCoveringType.DEMON_COMMON,
			Race.DEMON,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("无暇"),
			Util.newArrayListOfValues("无暇"),
			"手",
			"手",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("精致", "柔软", "阴柔"),
			"手指",
			"手指",
			Util.newArrayListOfValues(""),
			Util.newArrayListOfValues("精致", "柔软", "阴柔"),
			"不一会儿，它们变成了细长的、像人一样的手臂，还有像人一样的手。"
				+ "尽管外表有些平庸，但却有一种微妙而诱人的质地，揭示了其真正的恶魔本质。<br/>"
				+ "[npc.Name]有着[style.boldDemon(恶魔的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]",
			"[npc.She]有着[npc.armRows]纤细的人类手臂和手，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。") {
	};

	public static AbstractArmType COW_MORPH = new AbstractArmType(BodyCoveringType.BOVINE_FUR,
			Race.COW_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("牛类"),
			Util.newArrayListOfValues("阴柔", "牛类"),
			"手指",
			"手指",
			Util.newArrayListOfValues("牛类"),
			Util.newArrayListOfValues("阴柔", "牛类"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的毛发长在了手背上，坚硬、蹄状的指甲则取代了原先人类的指甲。"
				+ "尽管外表如此，但[npc.her]却没有感觉手部失去丝毫曾经的灵活，于是放下心来。"
				+ "转化结束后，[npc.she]看到大臂处的毛发自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.NameIsFull]获得了似人且[style.boldCowMorph(牛一般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手虽然还是人类的形状，但指甲已经变得很坚硬，有种蹄子的质感。") {
	};

	public static AbstractArmType DOG_MORPH = new AbstractArmType(BodyCoveringType.CANINE_FUR,
			Race.DOG_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("犬类", "爪般", "毛茸茸"),
			Util.newArrayListOfValues("柔软", "阴柔", "犬类", "爪般", "毛茸茸"),
			"手指",
			"手指",
			Util.newArrayListOfValues("带肉垫", "犬类"),
			Util.newArrayListOfValues("柔软", "阴柔", "带肉垫", "犬类"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，狗一般圆钝的爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]、稍有革质感的肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldDogMorph(犬一般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且犬一般的手，长着圆钝的爪子和皮质感的肉垫。") {
	};

	public static AbstractArmType WOLF_MORPH = new AbstractArmType(BodyCoveringType.LYCAN_FUR,
			Race.WOLF_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("狼一般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔", "狼一般", "毛茸茸", "爪般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("带肉垫", "狼一般"),
			Util.newArrayListOfValues("柔软", "阴柔", "带肉垫", "狼一般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，狼一般锋利的爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]、硬皮质感的肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldWolfMorph(狼一般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且狼一般的手，长着尖锐的爪子和硬皮质感的肉垫。") {
	};

	public static AbstractArmType FOX_MORPH = new AbstractArmType(BodyCoveringType.FOX_FUR,
			Race.FOX_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("狐狸般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔", "狐狸般", "毛茸茸", "爪般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("带肉垫", "狐狸般"),
			Util.newArrayListOfValues("柔软", "阴柔", "带肉垫", "狐狸般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，狐狸一般锋利的爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]的小肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。</br>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldFoxMorph(狐狸般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且狐狸般的手，长着尖锐的爪子和硬皮质感的肉垫。") {
	};

	public static AbstractArmType CAT_MORPH = new AbstractArmType(BodyCoveringType.FELINE_FUR,
			Race.CAT_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("柔软", "精致", "猫一般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔", "猫一般", "毛茸茸", "爪般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("柔软", "精致", "带肉垫", "猫一般"),
			Util.newArrayListOfValues("柔软", "阴柔", "带肉垫", "猫一般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，锋利且可伸缩的爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]的粉色小肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldCatMorph(猫一般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且猫一般的手，长着可伸缩的爪子和粉色的肉垫。") {
	};

	public static AbstractArmType HORSE_MORPH = new AbstractArmType(BodyCoveringType.HORSE_HAIR,
			Race.HORSE_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("覆盖毛发"),
			Util.newArrayListOfValues("覆盖毛发"),
			"手",
			"手",
			Util.newArrayListOfValues("马一般"),
			Util.newArrayListOfValues("阴柔", "马一般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("马一般"),
			Util.newArrayListOfValues("阴柔", "马一般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的毛发长在了手背上，坚硬、蹄状的指甲则取代了原先人类的指甲。"
				+ "尽管外表如此，但[npc.her]却没有感觉手部失去丝毫曾经的灵活，于是放下心来。"
				+ "转化结束后，[npc.she]看到大臂处的毛发自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.NameIsFull]获得了似人且[style.boldHorseMorph(马一般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手虽然还是人类的形状，但指甲已经变得很坚硬，有种蹄子的质感。") {
	};

	public static AbstractArmType REINDEER_MORPH = new AbstractArmType(BodyCoveringType.REINDEER_FUR,
			Race.REINDEER_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("覆盖毛发"),
			Util.newArrayListOfValues("覆盖毛发"),
			"手",
			"手",
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("阴柔", "驯鹿般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("驯鹿般"),
			Util.newArrayListOfValues("阴柔", "驯鹿般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了[npc.her]的手背上，坚硬、蹄状的指甲则取代了原先人类的指甲。"
				+ "尽管外表如此，但[npc.her]却没有感觉手部失去丝毫曾经的灵活，于是放下心来。"
				+ "转化结束后，[npc.she]看到大臂处的皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.NameIsFull]获得了似人且[style.boldReindeerMorph(驯鹿般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手虽然还是人类的形状，但指甲已经变得很坚硬，有种蹄子的质感。") {
	};

	public static AbstractArmType ALLIGATOR_MORPH = new AbstractArmType(BodyCoveringType.ALLIGATOR_SCALES,
			Race.ALLIGATOR_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("长有鳞片", "爬行动物般"),
			Util.newArrayListOfValues("长有鳞片", "爬行动物般"),
			"手",
			"手",
			Util.newArrayListOfValues("长有鳞片"),
			Util.newArrayListOfValues("阴柔", "长有鳞片"),
			"手指",
			"手指",
			Util.newArrayListOfValues("长有鳞片"),
			Util.newArrayListOfValues("阴柔", "长有鳞片"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
				+ "[npc.she]看见一片新的鳞片长在了手背上，锋利的爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]的细密鳞片，而在大臂处，鳞片自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldGatorMorph(类鳄的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且鳄鱼般的手，长着并不算大的爪子。") {
	};

	public static AbstractArmType SQUIRREL_MORPH = new AbstractArmType(BodyCoveringType.SQUIRREL_FUR,
			Race.SQUIRREL_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("柔软", "松鼠般", "啮齿动物般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔",  "松鼠般", "啮齿动物般", "毛茸茸", "爪般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("柔软", "长爪子", "啮齿动物般"),
			Util.newArrayListOfValues("柔软", "阴柔", "长爪子", "啮齿动物般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，锋利的小爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]的粉色小肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldSquirrelMorph(松鼠般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且松鼠般的手，长着爪子。") {
	};

	public static AbstractArmType RAT_MORPH = new AbstractArmType(BodyCoveringType.RAT_FUR,
			Race.RAT_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("柔软", "老鼠般", "啮齿动物般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔",  "老鼠般", "啮齿动物般", "毛茸茸", "爪般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("柔软", "老鼠般", "啮齿动物般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔",  "老鼠般", "啮齿动物般", "毛茸茸", "爪般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，锋利的小爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]的粉色小肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldRatMorph(老鼠般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且老鼠般的手，长着爪子。") {
	};

	public static AbstractArmType RABBIT_MORPH = new AbstractArmType(BodyCoveringType.RABBIT_FUR,
			Race.RABBIT_MORPH,
			"手臂",
			"手臂",
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			Util.newArrayListOfValues("毛茸茸", "覆盖皮毛"),
			"手",
			"手",
			Util.newArrayListOfValues("兔子般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔", "兔子般", "毛茸茸", "爪般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("兔子般", "毛茸茸", "爪般"),
			Util.newArrayListOfValues("柔软", "阴柔", "兔子般", "毛茸茸", "爪般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，"
					+ "[npc.she]看见一片新的皮毛长在了手背上，兔子一般圆钝的爪子从原本指甲的位置伸了出来。"
				+ "[npc.Her]的手掌迅速转化为[npc.materialDescriptor]、柔软的小肉垫，而在大臂处，皮毛自然过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "转化结束后，[npc.nameIsFull]获得了似人且[style.boldRabbitMorph(兔子般的手臂和手)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"[npc.She]拥有[npc.armRows]手臂，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "[npc.Her]的手转变成了似人且兔子般的手，长着圆钝的小爪子。") {
	};

	public static AbstractArmType BAT_MORPH = new AbstractArmType(BodyCoveringType.BAT_SKIN,
			Race.BAT_MORPH,
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("蝙蝠般"),
			"手",
			"手",
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("阴柔", "蝙蝠般"),
			"手指",
			"手指",
			Util.newArrayListOfValues("蝙蝠般"),
			Util.newArrayListOfValues("柔软", "阴柔", "蝙蝠般"),
			"很快，一层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]惊讶地向下看去，"
					+ "[npc.name]眼睁睁看着[npc.her]的手指变得又细又长，接着在手指之间长出了一层坚韧的薄膜。"
				+ "[npc.She]感觉到骨头正在断裂重组成新的形态，于是惊叫起来，没过多久，"
					+ "[npc.her]的手和手臂就已经完全转化为了蝙蝠般的巨大翅膀。"
				+ "[npc.her]的手曾经的位置上，两根手指已经收缩到新附肢的中间关节处，"
					+ "只留下两根前指和与之相对的拇指，每根手指的末端都带着小爪子。"
				+ "而在[npc.her]的新翅膀与身体在肩膀的交界处，[npc.her][npc.armFullDescription]自然地过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]在手臂的位置有着[style.boldBatMorph(蝙蝠般的巨大翅膀)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"在原本手臂和手的位置上，[npc.She]拥有[npc.armRows]蝙蝠般的翅膀，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "#IF(!npc.isFeral())"
				+ "而在手原本的位置上，[npc.she]拥有两根前指和一根拇指，每一根末端都带着圆钝的小爪子。"
				+ "尽管不如人类的手灵活，但[npc.sheIs]仍旧能够使用手指做到握持的动作。"
				+ "#ENDIF") {
		@Override
		public boolean allowsFlight() {
			return true;
		}
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.ARM_WINGS, BodyPartTag.ARM_WINGS_LEATHERY);
		}
	};

	public static AbstractArmType HARPY = new AbstractArmType(BodyCoveringType.FEATHERS,
			Race.HARPY,
			"翅膀",
			"翅膀",
			Util.newArrayListOfValues("长有羽毛", "鸟一般"),
			Util.newArrayListOfValues("长有羽毛", "鸟一般"),
			"手",
			"手",
			Util.newArrayListOfValues("覆羽"),
			Util.newArrayListOfValues("阴柔", "长有羽毛"),
			"手指",
			"手指",
			Util.newArrayListOfValues("覆羽"),
			Util.newArrayListOfValues("阴柔", "长有羽毛"),
			"很快，一层层[npc.armFullDescription]快速地在上面蔓延开来，[npc.she]向下看去，只见手背上也长出了全新的羽毛。"
				+ "正当[npc.she]以为转化已经结束的时候，[npc.she]感觉到骨头正在断裂重组成新的形态，于是惊叫起来。"
				+ "万幸的是，这种转化只持续了很短的时间，手臂的位置变成了一对长满羽毛的巨大翅膀。"
				+ "[npc.her]的手曾经的位置上，两根手指已经收缩到新附肢的中间关节处，只留下羽毛覆盖的两根前指和与之相对的拇指，"
					+ "每一根末端都带着圆钝的爪子。"
				+ "而在[npc.her]的新翅膀与身体在肩膀的交界处，[npc.her]的羽毛自然地过渡成了覆盖身体其他部位的[npc.skin]。<br/>"
				+ "[npc.Name]在手臂的位置有着巨大的[style.boldBatMorph(哈比翅膀)]，[npc.materialDescriptor][npc.armFullDescription]。",
			"在原本手臂和手的位置上，[npc.She]拥有[npc.armRows]巨大的翅膀，[npc.materialCompositionDescriptor][npc.armFullDescription(true)]。"
				+ "#IF(!npc.isFeral())"
				+ "而在手原本的位置上，[npc.she]拥有羽毛覆盖的两根前指和一根拇指，每一根末端都带着圆钝的小爪子。"
				+ "尽管不如人类的手灵活，但[npc.sheIs]仍旧能够使用手指做到握持的动作。"
				+ "#ENDIF") {
		@Override
		public boolean allowsFlight() {
			return true;
		}
		@Override
		public List<BodyPartTag> getTags() {
			return Util.newArrayListOfValues(BodyPartTag.ARM_WINGS, BodyPartTag.ARM_WINGS_FEATHERED);
		}
	};
	
	
	private static List<AbstractArmType> allArmTypes;
	private static Map<AbstractArmType, String> armToIdMap = new HashMap<>();
	private static Map<String, AbstractArmType> idToArmMap = new HashMap<>();
	
	static {
		allArmTypes = new ArrayList<>();

		// Modded types:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", "bodyParts", null);
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("arm")) {
					try {
						AbstractArmType type = new AbstractArmType(innerEntry.getValue(), entry.getKey(), true) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allArmTypes.add(type);
						armToIdMap.put(type, id);
						idToArmMap.put(id, type);
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
				if(Util.getXmlRootElementName(innerEntry.getValue()).equals("arm")) {
					try {
						AbstractArmType type = new AbstractArmType(innerEntry.getValue(), entry.getKey(), false) {};
						String id = innerEntry.getKey().replaceAll("bodyParts_", "");
						allArmTypes.add(type);
						armToIdMap.put(type, id);
						idToArmMap.put(id, type);
					} catch(Exception ex) {
						ex.printStackTrace(System.err);
					}
				}
			}
		}
		
		// Add in hard-coded arm types:
		
		Field[] fields = ArmType.class.getFields();
		
		for(Field f : fields){
			if (AbstractArmType.class.isAssignableFrom(f.getType())) {
				
				AbstractArmType ct;
				try {
					ct = ((AbstractArmType) f.get(null));

					armToIdMap.put(ct, f.getName());
					idToArmMap.put(f.getName(), ct);
					
					allArmTypes.add(ct);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Collections.sort(allArmTypes, (t1, t2)->
			t1.getRace()==Race.NONE
				?-1
				:(t2.getRace()==Race.NONE
					?1
					:t1.getRace().getName(false).compareTo(t2.getRace().getName(false))));
	}
	
	public static AbstractArmType getArmTypeFromId(String id) {
		if(id.equals("IMP")) {
			return ArmType.DEMON_COMMON;
		}
		if(id.equals("LYCAN")) {
			return ArmType.WOLF_MORPH;
		}

		id = Util.getClosestStringMatch(id, idToArmMap.keySet());
		return idToArmMap.get(id);
	}
	
	public static String getIdFromArmType(AbstractArmType armType) {
		return armToIdMap.get(armType);
	}
	
	public static List<AbstractArmType> getAllArmTypes() {
		return allArmTypes;
	}
	
	private static Map<AbstractRace, List<AbstractArmType>> typesMap = new HashMap<>();
	
	public static List<AbstractArmType> getArmTypes(AbstractRace r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<AbstractArmType> types = new ArrayList<>();
		for(AbstractArmType type : ArmType.getAllArmTypes()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
	
}
