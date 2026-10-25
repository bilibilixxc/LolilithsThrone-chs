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
            "尾穴状态",
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
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在用手指抽插)] [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在用手指抽插)] [npc.namePos] 的 [npc.tussy]！"));
                            }
                            break;
                        case PENIS:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在干)] [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在干)] [npc.namePos] 的 [npc.tussy]！"));
                            }
                            break;
                        case TAIL:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在用尾巴干)] [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在用尾巴干)] [npc.namePos] 的 [npc.tussy]！"));
                            }
                            break;
                        case TENTACLE:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在用触手干)] [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在用触手干)] [npc.namePos] 的 [npc.tussy]！"));
                            }
                            break;
                        case TONGUE:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在舔弄)] [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在舔弄)] [npc.name] 的 [npc2.tussy]！"));
                            }
                            break;
                        case CLIT:
                            if(selfAction) {
                                if(main.isClitorisPseudoPenis()) {
                                    descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在用阴蒂干)] [npc.her] 自己的 [npc.tussy]！"));
                                } else {
                                    descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在磨蹭)] [npc.her] 自己的 [npc.tussy]！"));
                                }
                            } else {
                                if(main.isClitorisPseudoPenis()) {
                                    descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                            +" [style.boldSex(正在用阴蒂干)] [npc.namePos] 的 [npc.tussy]！"));
                                } else {
                                    descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                            +" [style.boldSex(正在与)] [npc2.name] [style.boldSex(磨蹭)]！"));
                                }
                            }
                            break;
                        case FOOT:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正将 [npc.her] 的 [npc.toes])] 塞入 [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))
                                        +(names.size()==1?UtilText.parse(main, " [npc.is] [style.boldSex(正将 [npc.her] 的 [npc.toes])]"):" are [style.boldSex(正将她们的脚趾)]")
                                        +" 塞入 [npc.namePos] 的 [npc.tussy]！"));
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
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在舔弄)] [npc.herself]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在舔弄)] [npc2.name]！"));
                            }
                            break;
                        case THIGHS:
                            break;
                        case URETHRA_PENIS:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在干)] [npc.her] 自己的 [npc.tussy]！"));
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在干)] [npc.namePos] 的 [npc.tussy]！"));
                            }
                            break;
                        case URETHRA_VAGINA:
                            break;
                        case TUSSY:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在磨蹭)] [npc.her] 自己的 [npc.tussy]！")); //???
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在与)] [npc2.name] [style.boldSex(磨蹭)]！"));
                            }
                            break;
                        case VAGINA:
                            if(selfAction) {
                                descriptionSB.append(UtilText.parse(target, "[npc.NameIsFull] [style.boldSex(正在磨蹭)] [npc.her] 自己的 [npc.tussy]！")); //???
                            } else {
                                descriptionSB.append(UtilText.parse(target, Util.capitaliseSentence(Util.stringsToStringList(names, false))+(names.size()==1?UtilText.parse(main, " [npc.is]"):" are")
                                        +" [style.boldSex(正在与)] [npc2.name] [style.boldSex(磨蹭)]！"));
                            }
                            break;
                        case SPINNERET:
                            break;
                    }
                }
            }

            if(!descriptionAdded) {
                descriptionSB.append("[style.boldDisabled(当前没有进行中的动作。)]");
            }

            appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.Her] 尾穴"), descriptionSB);

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