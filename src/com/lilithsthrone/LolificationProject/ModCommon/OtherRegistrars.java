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
            "喜爱被淋尿",
            "被人淋尿/撒尿",
            "fetish_pee_receiving",
            FetishExperience.BASE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_EXCELLENT,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>解锁</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>被人淋尿</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>弱点</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>被人淋尿</span>"),
            null) {
        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "这个癖好与一个人渴望接受他人黄金液体沐浴有关。";
            else if (owner.isPlayer())
                return "你对被淋尿有着执念。你最喜欢的就是被别人用尿液覆盖全身。";
            return UtilText.parse(owner, "[npc.Name]有被淋尿的癖好。");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isPeeingEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "被淋尿");
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
            "喜爱向他人撒尿",
            "向他人撒尿",
            "fetish_pee_giving",
            FetishExperience.BASE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_EXCELLENT,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>解锁</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>向他人撒尿</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>弱点</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>膀胱释放</span>"),
            null) {

        @Override
        public AbstractFetish getOpposite() {
            return FETISH_PEE_RECEIVING;
        }

        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "这个癖好与一个人渴望用一股黄金液体淋浴他人有关。";
            else if (owner.isPlayer())
                return "你对向他人撒尿有着执念。你最喜欢的就是用尿液覆盖别人。";
            return UtilText.parse(owner, "[npc.Name]有向他人撒尿的癖好。");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isPeeingEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "向他人撒尿");
        }

        @Override
        public CorruptionLevel getAssociatedCorruptionLevel() {
            return CorruptionLevel.THREE_DIRTY;
        }
    };

    public static final AbstractFetish FETISH_AGE_YOUNGER = new AbstractFetish(60,
            "偏爱年轻者",
            "年轻伴侣",
            "fetish_age_younger",
            FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_ARCANE,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>增加</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>对比你小10岁以上者造成的淫欲伤害</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>受到</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>来自比你小10岁以上者的淫欲伤害</span>"),
            null) {
        @Override
        public AbstractFetish getOpposite() {
            return FETISH_AGE_OLDER;
        }

        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "这个癖好与一个人对年轻伴侣的渴望有关。";
            else if (owner.isPlayer())
                return "你喜欢与比你年轻得多的人调情和发生性关系。";
            return UtilText.parse(owner, "[npc.Name]有与明显年轻许多的伴侣发生关系的癖好。");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isExtremeAgeEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "年轻伴侣");
        }

        @Override
        public CorruptionLevel getAssociatedCorruptionLevel() {
            return CorruptionLevel.THREE_DIRTY;
        }
    };

    public static final AbstractFetish FETISH_AGE_OLDER = new AbstractFetish(60,
            "偏爱年长者",
            "年长伴侣",
            "fetish_age_older",
            FetishExperience.BASE_RARE_EXPERIENCE_GAIN,
            PresetColour.GENERIC_ARCANE,
            null,
            Util.newArrayListOfValues("<span style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>增加</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>对比你大10岁以上者造成的淫欲伤害</span>",
                    "<span style='color:" + PresetColour.GENERIC_BAD.toWebHexString() + ";'>受到</span> <span style='color:" + PresetColour.GENERIC_SEX.toWebHexString() + ";'>来自比你大10岁以上者的淫欲伤害</span>"),
            null) {
        @Override
        public AbstractFetish getOpposite() {
            return FETISH_AGE_YOUNGER;
        }

        @Override
        public String getDescription(GameCharacter owner) {
            if (owner == null)
                return "这个癖好与一个人对年长伴侣的渴望有关。";
            else if (owner.isPlayer())
                return "你喜欢与比你年长得多的人调情和发生性关系。";
            return UtilText.parse(owner, "[npc.Name]有与明显年长许多的伴侣发生关系的癖好。");
        }

        @Override
        public boolean isContentEnabled() {
            return ModProps.isExtremeAgeEnabled();
        }

        @Override
        public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
            return getGenericFetishDesireDescription(target, desire, "年长伴侣");
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
                line-height:19px;
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