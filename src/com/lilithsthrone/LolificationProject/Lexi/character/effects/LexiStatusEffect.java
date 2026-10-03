package com.lilithsthrone.LolificationProject.Lexi.character.effects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.ArrayList;
import java.util.List;

public class LexiStatusEffect {
    public static AbstractStatusEffect TUSSY_STATUS = new AbstractStatusEffect(95,
            "Tussy status",
            null,
            PresetColour.GENERIC_SEX,
            false,
            null,
            null) {
        @Override
        public float getArousalPerTurnSelf(GameCharacter target) {
            return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.TUSSY);
        }
        @Override
        public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
            return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.TUSSY);
        }
        @Override
        public List<String> getModifiersAsStringList(GameCharacter target) {
            return getOrificeModifiersAsStringList(target, SexAreaOrifice.TUSSY);
        }
        @Override
        public String getDescription(GameCharacter target) {
            StringBuilder descriptionSB = new StringBuilder();
            SexAreaOrifice type = SexAreaOrifice.TUSSY;

            descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");

            boolean descriptionAdded = false;
            for(SexAreaPenetration pen : SexAreaPenetration.values()) {
                List<String> names = new ArrayList<>();
                GameCharacter main = null;
                for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, pen)) {
                    if(main==null) {
                        main = c;
                    }
                    if(c.isPlayer()) {
                        names.add(0, UtilText.parse(c, "[npc.name]"));
                    } else {
                        names.add(UtilText.parse(c, "[npc.name]"));
                    }
                }
                if(!names.isEmpty()) {
                    descriptionAdded = true;
                    boolean selfAction = target.equals(main);
                    switch(pen) {
                        case FINGER:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fingering)] [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(fingering)] [npc.namePos] [npc.tussy]!"));
                            }
                            break;
                        case PENIS:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(fucking)] [npc.namePos] [npc.tussy]!"));
                            }
                            break;
                        case TAIL:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tail-fucking)] [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(tail-fucking)] [npc.namePos] [npc.tussy]!"));
                            }
                            break;
                        case TENTACLE:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tentacle-fucking)] [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(tentacle-fucking)] [npc.namePos] [npc.tussy]!"));
                            }
                            break;
                        case TONGUE:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" performing [style.boldSex(cunnilingus)] on [npc.name]!"));
                            }
                            break;
                        case CLIT:
                            if(selfAction) {
                                if(main.isClitorisPseudoPenis()) {
                                    descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(clit-fucking)] [npc.her] own [npc.tussy]!"));
                                } else {
                                    descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.tussy]!"));
                                }
                            } else {
                                if(main.isClitorisPseudoPenis()) {
                                    descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                            +" [style.boldSex(clit-fucking)] [npc.namePos] [npc.tussy]!"));
                                } else {
                                    descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                            +" [style.boldSex(tribbing)] with [npc2.name]!"));
                                }
                            }
                            break;
                        case FOOT:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(pushing [npc.her] [npc.toes])] into [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
                                        +(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(pushing [npc.her] [npc.toes])]"):" are [style.boldSex(pushing their toes)]")
                                        +"  into [npc.namePos] [npc.tussy]!"));
                            }
                            break;
                    }
                }
            }

            for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
                List<String> names = new ArrayList<>();
                GameCharacter main = null;
                for(GameCharacter c : Main.sex.getOngoingCharactersUsingAreas(target, type, orifice)) {
                    if(main==null) {
                        main = c;
                    }
                    if(c.isPlayer()) {
                        names.add(0, UtilText.parse(c, "[npc.name]"));
                    } else {
                        names.add(UtilText.parse(c, "[npc.name]"));
                    }
                }
                if(!names.isEmpty()) {
                    descriptionAdded = true;
                    boolean selfAction = target.equals(main);
                    switch(orifice) {
                        case ARMPITS:
                        case ANUS:
                        case ASS:
                        case BREAST:
                        case NIPPLE:
                        case BREAST_CROTCH:
                        case NIPPLE_CROTCH:
                            break;
                        case MOUTH:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] performing [style.boldSex(cunnilingus)] on [npc.herself]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" performing [style.boldSex(cunnilingus)] on [npc2.name]!"));
                            }
                            break;
                        case THIGHS:
                            break;
                        case URETHRA_PENIS:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(fucking)] [npc.her] own [npc.tussy]!"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(fucking)] [npc.namePos] [npc.tussy]!"));
                            }
                            break;
                        case URETHRA_VAGINA:
                            break;
                        case TUSSY:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.tussy]!")); //???
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(tribbing)] with [npc2.name]!"));
                            }
                            break;
                        case VAGINA:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(tribbing)] [npc.her] own [npc.tussy]!")); //???
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(tribbing)] with [npc2.name]!"));
                            }
                            break;
                        case SPINNERET:
                            break;
                    }
                }
            }

            if(!descriptionAdded) {
                descriptionSB.append("[style.boldDisabled(No ongoing actions.)]");
            }

            appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] tussy"), descriptionSB);

            descriptionSB.append("</p>");

            return descriptionSB.toString();
        }
        @Override
        public List<Util.Value<Integer, String>> getAdditionalDescriptions(GameCharacter target) {
            return getInternalOrificeExtraDescriptions(target, SexAreaOrifice.TUSSY);
        }
        @Override
        public boolean isConditionsMet(GameCharacter target) {
            return Main.game.isInSex()
                    && Main.sex.getAllParticipants(true).contains(target)
                    && target.hasTussy();
        }
        @Override
        public boolean isSexEffect() {
            return true;
        }
        @Override
        public String getSVGString(GameCharacter owner) {
            return getOrificeSVGString(owner, SexAreaOrifice.TUSSY, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaSpinneret());
        }
    };
}
