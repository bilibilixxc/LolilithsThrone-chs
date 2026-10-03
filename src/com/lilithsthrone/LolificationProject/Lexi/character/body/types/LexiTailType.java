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
            "manticore",
            "",
            "",
            "tail",
            "tails",
            Util.newArrayListOfValues("chitinous", "manticore", "unique", "lewd"),
            Util.newArrayListOfValues("chitinous", "manticore", "unique", "lewd"),
            "tussy",
            "tail pussies",
            Util.newArrayListOfValues(""),
            Util.newArrayListOfValues(""),
            "#IF(npc.getTailCount()==1)"
                    + " A chitinous, tapered tail sprouts from just above [npc.her] ass, rapidly growing in size until it's about [npc.tailLength] long."
                    + " The end swells out into a bulbous, flared bulge, before a slit forms across its tip."
                    + " Letting out [npc.a_moan], [npc.name] instinctively [npc.verb(feel)] that this new tail of [npc.hers] can be used as an additional fuckable orifice."
                    + " Turning it this way and that, [npc.she] quickly [npc.verb(discover)] that [npc.she] [npc.has] complete control over where it goes, allowing [npc.herHim] to use it like a third limb."
                    + "<br/>"
                    + "[npc.Name] now [npc.has] an [style.boldPanther(chitinous, manticore tail)], [npc.materialDescriptor] [npc.tailFullDescription(true)]."
                    + "#ELSE"
                    + " [npc.TailCount] chitinous, tapered tails sprout from just above [npc.her] ass, rapidly growing in size until they're each about [npc.tailLength] long."
                    + " The ends of each one swell out a into bulbous, flared bulges, before, slits form across their tips."
                    + " Letting out [npc.a_moan], [npc.name] instinctively [npc.verb(feel)] that these new tails of [npc.hers] can be used as additional fuckable orifices."
                    + " Turning them this way and that, [npc.she] quickly [npc.verb(discover)] that [npc.she] [npc.has] complete control over where they go, allowing [npc.herHim] to use them like extra limbs."
                    + "<br/>"
                    + "[npc.Name] now [npc.has] [npc.tailCount] [style.boldPanther(chitinous, manticore tails)], [npc.materialDescriptor] [npc.tailFullDescription(true)]."
                    + "#ENDIF",
            "Growing out from just above [npc.her] ass, [npc.sheHasFull]"
                    + "#IF(npc.getTailCount()==1)"
                    + " a tapered, [npc.tailColour(true)] chitinous tail, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use it to grip and hold objects."
                    + "  The end has swollen out into a bulbous, flared bulge, while a slit has formed across its tip, allowing [npc.herHim] to use it as an additional fuckable orifice."
                    + "#ELSE"
                    + " [npc.tailCount] tapered, [npc.tailColour(true)] chitinous tails, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use them to grip and hold objects."
                    + "  The ends of each one have swollen out a little into bulbous, flared bulges, while slits have formed across their tips, allowing [npc.herHim] to use them as additional fuckable orifices."
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
            "demonic slitted",
            "",
            "",
            "tail",
            "tails",
            Util.newArrayListOfValues("slitted", "demonic"),
            Util.newArrayListOfValues("slitted", "demonic"),
            "tussy",
            "tail pussies",
            Util.newArrayListOfValues(""),
            Util.newArrayListOfValues(""),
            "#IF(npc.getTailCount()==1)"
                    + " A demonic, tapered tail sprouts from just above [npc.her] ass, rapidly growing in size until it's about [npc.tailLength] long."
                    + " The end swells out into a bulbous, tapered bulge, before a slit forms across its tip."
                    + " Letting out [npc.a_moan], [npc.name] instinctively [npc.verb(feel)] that this new tail of [npc.hers] can be used as an additional fuckable orifice."
                    + " Turning it this way and that, [npc.she] quickly [npc.verb(discover)] that [npc.she] [npc.has] complete control over where it goes, allowing [npc.herHim] to use it like a third limb."
                    + "<br/>"
                    + "[npc.Name] now [npc.has] an [style.boldDemon(demonic, slitted tail)], [npc.materialDescriptor] [npc.tailFullDescription(true)]."
                    + "#ELSE"
                    + " [npc.TailCount] demonic, tapered tails sprout from just above [npc.her] ass, rapidly growing in size until they're each about [npc.tailLength] long."
                    + " The ends of each one swell out a into bulbous, tapered bulges, before, slits form across their tips."
                    + " Letting out [npc.a_moan], [npc.name] instinctively [npc.verb(feel)] that these new tails of [npc.hers] can be used as additional fuckable orifices."
                    + " Turning them this way and that, [npc.she] quickly [npc.verb(discover)] that [npc.she] [npc.has] complete control over where they go, allowing [npc.herHim] to use them like extra limbs."
                    + "<br/>"
                    + "[npc.Name] now [npc.has] [npc.tailCount] [style.boldDemon(demonic, slitted tails)], [npc.materialDescriptor] [npc.tailFullDescription(true)]."
                    + "#ENDIF",
            "Growing out from just above [npc.her] ass, [npc.sheHasFull]"
                    + "#IF(npc.getTailCount()==1)"
                    + " a tapered, [npc.tailColour(true)] demonic tail, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use it to grip and hold objects."
                    + "  The end has swollen out into a bulbous, tapered bulge, while a slit has formed across its tip, allowing [npc.herHim] to use it as an additional fuckable orifice."
                    + "#ELSE"
                    + " [npc.tailCount] tapered, [npc.tailColour(true)] demonic tails, over which [npc.sheHasFull] complete control, allowing [npc.herHim] to use them to grip and hold objects."
                    + "  The ends of each one have swollen out a little into bulbous, tapered bulges, while slits have formed across their tips, allowing [npc.herHim] to use them as additional fuckable orifices."
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
