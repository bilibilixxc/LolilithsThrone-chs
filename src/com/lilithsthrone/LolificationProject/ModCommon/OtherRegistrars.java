package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.fetishes.FetishExperience;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import static com.lilithsthrone.game.character.fetishes.Fetish.*;

public class OtherRegistrars {

    public static final AbstractFetish FETISH_PEE_RECEIVING = new AbstractFetish(60,
            "pee lover",
            "being pissed on/in",
            "fetish_pee_receiving",
            FetishExperience.BASE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_EXCELLENT,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Unlocks</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>being pissed on</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Weak to</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>being pissed on</span>"),
            null) {
        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "This fetish relates to a person's desire for receiving a shower of golden fluids from another.";
            else if (owner.isPlayer())
                return "You have an obsession with being pissed on. You love nothing more than to be covered by someone elses urine.";
            return UtilText.parse(owner, "[npc.Name] has a fetish for being pissed on.");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isPeeingEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "being pissed on");
        }

        @Override
        public AbstractFetish getOpposite() {
            return FETISH_PEE_GIVING;
        }

        @Override
        public CorruptionLevel getAssociatedCorruptionLevel() {
            return CorruptionLevel.THREE_DIRTY;
        }
    };

    public static final AbstractFetish FETISH_PEE_GIVING = new AbstractFetish(60,
            "pee provider",
            "pissing on others",
            "fetish_pee_giving",
            FetishExperience.BASE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_EXCELLENT,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Unlocks</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>peeing on others</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Weak to</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>bladder relief</span>"),
            null) {

        @Override
        public AbstractFetish getOpposite() {
            return FETISH_PEE_RECEIVING;
        }

        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "This fetish relates to a person's desire for showering another in a stream of golden fluids.";
            else if (owner.isPlayer())
                return "You have an obsession with pissing on others. You love nothing more than to cover someone else in urine.";
            return UtilText.parse(owner, "[npc.Name] has a fetish for pissing on others.");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isPeeingEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "peeing on others");
        }

        @Override
        public CorruptionLevel getAssociatedCorruptionLevel() {
            return CorruptionLevel.THREE_DIRTY;
        }
    };

    public static final AbstractFetish FETISH_AGE_YOUNGER = new AbstractFetish(60,
            "prefers younger",
            "younger partners",
            "fetish_age_younger",
            FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_ARCANE,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Increases</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>lust damage to people 10+ years younger</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Increases</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>lust damage from people 10+ years younger</span>"),
            null) {
        @Override
        public AbstractFetish getOpposite() {
            return FETISH_AGE_OLDER;
        }

        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "This fetish relates to a person's desire for younger partners.";
            else if (owner.isPlayer())
                return "You love flirting and having sex with those much younger than you.";
            return UtilText.parse(owner, "[npc.Name] has a fetish for significantly younger partners.");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isExtremeAgeEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "younger partners");
        }

        @Override
        public CorruptionLevel getAssociatedCorruptionLevel() {
            return CorruptionLevel.THREE_DIRTY;
        }
    };

    public static final AbstractFetish FETISH_AGE_OLDER = new AbstractFetish(60,
            "prefers older",
            "older partners",
            "fetish_age_older",
            FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_ARCANE,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Increases</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>lust damage to people 10+ years older</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>Increases</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>lust damage from people 10+ years older</span>"),
            null) {
        @Override
        public AbstractFetish getOpposite() {
            return FETISH_AGE_YOUNGER;
        }

        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "This fetish relates to a person's desire for older partners.";
            else if (owner.isPlayer())
                return "You love flirting and having sex with those much older than you.";
            return UtilText.parse(owner, "[npc.Name] has a fetish for significantly older partners.");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isExtremeAgeEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "older partners");
        }

        @Override
        public CorruptionLevel getAssociatedCorruptionLevel() {
            return CorruptionLevel.THREE_DIRTY;
        }
    };

    public static void registerFetishes() {
        allFetishes.add(FETISH_PEE_GIVING);
        fetishToIdMap.put(FETISH_PEE_GIVING, "FETISH_PEE_GIVING");
        idToFetishMap.put("FETISH_PEE_GIVING", FETISH_PEE_GIVING);
        allFetishes.add(FETISH_PEE_RECEIVING);
        fetishToIdMap.put(FETISH_PEE_RECEIVING, "FETISH_PEE_RECEIVING");
        idToFetishMap.put("FETISH_PEE_RECEIVING", FETISH_PEE_RECEIVING);
        allFetishes.add(FETISH_AGE_OLDER);
        fetishToIdMap.put(FETISH_AGE_OLDER, "FETISH_AGE_OLDER");
        idToFetishMap.put("FETISH_AGE_OLDER", FETISH_AGE_OLDER);
        allFetishes.add(FETISH_AGE_YOUNGER);
        fetishToIdMap.put(FETISH_AGE_YOUNGER, "FETISH_AGE_YOUNGER");
        idToFetishMap.put("FETISH_AGE_YOUNGER", FETISH_AGE_YOUNGER);
    }


    public static final String CUSTOM_CSS = """
            
            .pissedIcon{
                position:absolute;
                width:40%;
                height:40%;
                left:0;
                bottom:0;
                pointer-events: none;
                -webkit-user-select: none;
            }
            
            /*RACE OPTIONS*/
           
            .bodyPart-button {
                padding-right:5px;
                padding-left:5px;
                padding-top:1px;
                padding-bottom:1px;
                margin: 0;
                height:24px;
                text-align:center;
                line-height:24px;
                cursor:pointer;
            
                float:left;
                margin-right:4px;
                margin-top:4px;
                margin-left:4px;
                background-color: #333;
                border-radius:5px;
            
                -webkit-user-select: none;
                -webkit-box-sizing: border-box;
            }
            
            .bodyPart-button:hover {
                background-color: #666;
            }
            
            .bodyPart-button.selected {
                background-color: #444;
                border-width: 2px;
                border-style: solid;
                border-color: #BD0061;
            }
            
            .bodyPart-button.selected:hover {
                background-color: #777;
                border-width: 2px;
                border-style: solid;
                border-color: #BD0061;
            }
            
            .bodyPart-button.disabled {
                color: #444;
                cursor:default;
            }
            
            .bodyPart-button.disabled:hover {
                background-color: #333;
            }
            
            .slider-info {
                text-align:center;
                float:right;
                width:55%;
                margin-bottom: -5px;
                margin-top:-5px;
                z-index: 3;
                background-color: transparent;
                pointer-events: none;
            }
            
            .slider-info > input {
                text-align:center;
                float:right;
                width:55%;
                margin-bottom: -5px;
                margin-top:-5px;
                z-index: 3;
                background-color: transparent;
                border: transparent;
                pointer-events: auto;
            }
            
            .slider-style {
                float:right;
                margin-bottom:-5px;
                margin-top:-10px;
            }
            """;

    public static void addCustomCSS(Document document) {
        Element styleNode = document.createElement("style");
        Text styleContent = document.createTextNode(CUSTOM_CSS);
        styleNode.appendChild(styleContent);
        document.getDocumentElement().getElementsByTagName("head").item(0).appendChild(styleNode);
    }
}