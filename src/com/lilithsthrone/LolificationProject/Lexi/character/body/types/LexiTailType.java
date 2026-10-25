package com.lilithsthrone.LolificationProject.Lexi.character.body.types;

import com.lilithsthrone.LolificationProject.Lexi.character.race.LexiRace;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

public class LexiTailType {

    public static final AbstractTailType MANTICORE_TUSSY = new AbstractTailType(
            BodyCoveringType.SPIDER_CHITIN,
            Race.MANTICORE,
            PenetrationGirth.FOUR_GIRTHY,
            0.75f,
            "甲壳质蝎狮尾",
            "",
            "",
            "尾巴",
            "尾巴",
            Util.newArrayListOfValues("甲壳质的", "蝎狮", "独特的", "淫荡的"),
            Util.newArrayListOfValues("甲壳质的", "蝎狮", "独特的", "淫荡的"),
            "尾穴",
            "尾巴穴",
            Util.newArrayListOfValues(""),
            Util.newArrayListOfValues(""),
            "#IF(npc.getTailCount()==1)"
                    + " 一根甲壳质、渐渐变细的尾巴从[npc.her]臀部正上方冒出，迅速生长，直到长约[npc.tailLength]。"
                    + " 末端肿胀成一个球状、外张的隆起，随后在尖端裂开一条缝。"
                    + " 发出一声[npc.a_moan]，[npc.name] 本能地[npc.verb(feel)]到自己这根新尾巴可以用来作为额外的可插入穴口。"
                    + " 来回摆动一下，[npc.she]很快就[npc.verb(discover)]自己对它的去向拥有完全的控制，能像第三条肢体一样使用它。"
                    + "<br/>"
                    + "[npc.Name] 现在[npc.has]一条[style.boldPanther(甲壳质蝎狮尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
                    + "#ELSE"
                    + " [npc.TailCount]根甲壳质、渐渐变细的尾巴从[npc.her]臀部正上方冒出，迅速生长，直到每根都长约[npc.tailLength]。"
                    + " 每根的末端都肿胀成球状、外张的隆起，随后在尖端裂开一条条缝。"
                    + " 发出一声[npc.a_moan]，[npc.name] 本能地[npc.verb(feel)]到自己这些新尾巴可以用来作为额外的可插入穴口。"
                    + " 来回摆动它们，[npc.she]很快就[npc.verb(discover)]自己对它们的去向拥有完全的控制，能像额外的肢体一样使用它们。"
                    + "<br/>"
                    + "[npc.Name] 现在[npc.has][npc.tailCount]条[style.boldPanther(甲壳质蝎狮尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
                    + "#ENDIF",
            "从[npc.her]臀部正上方长出来，[npc.sheHasFull]"
                    + "#IF(npc.getTailCount()==1)"
                    + " 一条渐渐变细的[npc.tailColour(true)]甲壳质尾巴，对它[npc.sheHasFull]完全的控制，能用来抓握、持有物品。"
                    + "  末端肿胀成一个球状、外张的隆起，尖端则形成了一条缝，能用来作为额外的可插入穴口。"
                    + "#ELSE"
                    + " [npc.tailCount]条渐渐变细的[npc.tailColour(true)]甲壳质尾巴，对它们[npc.sheHasFull]完全的控制，能用来抓握、持有物品。"
                    + "  每根的末端都微微肿胀成球状、外张的隆起，尖端则形成了一条条缝，能用来作为额外的可插入穴口。"
                    + "#ENDIF",
            Util.newArrayListOfValues(
                    BodyPartTag.TAIL_PREHENSILE,
                    BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION,
                    BodyPartTag.TAIL_SLEEP_HUGGING,
                    BodyPartTag.TAIL_TYPE_SCALES,
                    BodyPartTag.TAIL_FUCKABLE),
            false) {
    };


    public static final AbstractTailType DEMON_TUSSY = new AbstractTailType(
            BodyCoveringType.DEMON_COMMON,
            Race.DEMON,
            PenetrationGirth.THREE_AVERAGE,
            0.75f,
            "恶魔裂开尾",
            "",
            "",
            "尾巴",
            "尾巴",
            Util.newArrayListOfValues("裂开的", "恶魔"),
            Util.newArrayListOfValues("裂开的", "恶魔"),
            "尾穴",
            "尾巴穴",
            Util.newArrayListOfValues(""),
            Util.newArrayListOfValues(""),
            "#IF(npc.getTailCount()==1)"
                    + " 一根恶魔般、渐渐变细的尾巴从[npc.her]臀部正上方冒出，迅速生长，直到长约[npc.tailLength]。"
                    + " 末端肿胀成一个球状、外张的隆起，随后在尖端裂开一条缝。"
                    + " 发出一声[npc.a_moan]，[npc.name] 本能地[npc.verb(feel)]到自己这根新尾巴可以用来作为额外的可插入穴口。"
                    + " 来回摆动一下，[npc.she]很快就[npc.verb(discover)]自己对它的去向拥有完全的控制，能像第三条肢体一样使用它。"
                    + "<br/>"
                    + "[npc.Name] 现在[npc.has]一条[style.boldDemon(恶魔般裂开的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
                    + "#ELSE"
                    + " [npc.TailCount]根恶魔般、渐渐变细的尾巴从[npc.her]臀部正上方冒出，迅速生长，直到每根都长约[npc.tailLength]。"
                    + " 每根的末端都肿胀成球状、外张的隆起，随后在尖端裂开一条条缝。"
                    + " 发出一声[npc.a_moan]，[npc.name] 本能地[npc.verb(feel)]到自己这些新尾巴可以用来作为额外的可插入穴口。"
                    + " 来回摆动它们，[npc.she]很快就[npc.verb(discover)]自己对它们的去向拥有完全的控制，能像额外的肢体一样使用它们。"
                    + "<br/>"
                    + "[npc.Name] 现在[npc.has][npc.tailCount]条[style.boldDemon(恶魔般裂开的尾巴)]，[npc.materialDescriptor][npc.tailFullDescription(true)]。"
                    + "#ENDIF",
            "从[npc.her]臀部正上方长出来，[npc.sheHasFull]"
                    + "#IF(npc.getTailCount()==1)"
                    + " 一条渐渐变细的[npc.tailColour(true)]恶魔尾巴，对它[npc.sheHasFull]完全的控制，能用来抓握、持有物品。"
                    + "  末端已经肿胀成球状、外张的隆起，尖端则形成了一条缝，能用来作为额外的可插入穴口。"
                    + "#ELSE"
                    + " [npc.tailCount]条渐渐变细的[npc.tailColour(true)]恶魔尾巴，对它们[npc.sheHasFull]完全的控制，能用来抓握、持有物品。"
                    + "  每根的末端都微微肿胀成球状、外张的隆起，尖端则形成了一条条缝，能用来作为额外的可插入穴口。"
                    + "#ENDIF",
            Util.newArrayListOfValues(
                    BodyPartTag.TAIL_PREHENSILE,
                    BodyPartTag.TAIL_NEVER_SUITABLE_FOR_PENETRATION,
                    BodyPartTag.TAIL_SLEEP_HUGGING,
                    BodyPartTag.TAIL_TYPE_SKIN,
                    BodyPartTag.TAIL_TAPERING_BULBOUS,
                    BodyPartTag.TAIL_FUCKABLE),
            false) {
    };
}