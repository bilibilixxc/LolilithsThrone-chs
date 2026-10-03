package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.markings.*;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.colours.PresetColour;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class LoadMarking {
    public static Scar loadScar(XMLLoader loader) {
        return new Scar(ScarType.getScarTypeFromString(loader.getCurrentElementAttributeAsStr("type")), loader.getCurrentElementAttributeAsBoolean("plural"));
    }

    public static Tattoo loadTattoo(XMLLoader loader) {
        TattooWriting writing = null;
        if (loader.moveToIfHasChildren("tattooWriting")) {
            writing = loadTattooWriting(loader);
            loader.goToParent();
        }
        String secondaryColour = loader.getCurrentElementAttributeAsStr("secondaryColour");
        String tertiaryColour = loader.getCurrentElementAttributeAsStr("tertiaryColour");

        TattooCounter counter = null;
        String tatCounterType;
        if(!(tatCounterType = loader.getNextElementAttributeAsStr("tattooCounter", "countType")).isEmpty()) {
            counter = loadTattooCounter(loader);
            loader.goToParent();
        }
        Tattoo tat = new Tattoo(
                TattooType.getTattooTypeFromId(loader.getCurrentElementAttributeAsStr("id")),
                PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("primaryColour")),
                secondaryColour.isEmpty() ? null : PresetColour.getColourFromId(secondaryColour),
                tertiaryColour.isEmpty() ? null : PresetColour.getColourFromId(tertiaryColour),
                loader.getCurrentElementAttributeAsBoolean("glowing"), writing, counter);

        tat.setName(loader.getCurrentElementAttributeAsStr("name"));

        if (loader.moveToIfHasChildren("effects")) {
            loader.forEachAttributeOfElement("effect", "type", effectType -> {
                ItemEffect eff = LoadItemEffect.loadEffect(loader, effectType);
                tat.addEffect(eff);
            });
            loader.goToParent();
        }

        return tat;
    }

    private static TattooCounter loadTattooCounter(XMLLoader loader)  {
        int loadedRetroactiveApplicationOffset = loader.getCurrentElementAttributeAsInt("retroactiveApplicationOffset", 0);

        return new TattooCounter(
                TattooCounterType.valueOf(loader.getCurrentElementAttributeAsStr("type")),
                TattooCountType.valueOf(loader.getCurrentElementAttributeAsStr("countType")),
                PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour")),
                loader.getCurrentElementAttributeAsBoolean("glow"),
                loadedRetroactiveApplicationOffset);
    }

    private static TattooWriting loadTattooWriting(XMLLoader loader)  {
        List<TattooWritingStyle> importedStyles = new ArrayList<>();
        if (loader.moveToIfHasChildren("styles")) {
            loader.forEachAttributeOfElement("style", "value", styleVal -> {
                TattooWritingStyle style = TattooWritingStyle.valueOf(styleVal);
                importedStyles.add(style);
            });
            loader.goToParent();
        }

        String text = loader.getCurrentElementText();

        TattooWriting tw = new TattooWriting(text.trim(),
                PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("colour")),
                loader.getCurrentElementAttributeAsBoolean("glow"));

        tw.styles = importedStyles;
        return tw;
    }

    public static Covering loadCovering(XMLLoader loader) {
        String type = loader.getCurrentElementAttributeAsStr("type");
        try {
            return new Covering(
                    BodyCoveringType.getBodyCoveringTypeFromId(type),
                    CoveringPattern.valueOf(loader.getCurrentElementAttributeAsStr("pat")),
                    CoveringModifier.valueOf(loader.getCurrentElementAttributeAsStr("mod")),
                    PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("c1")),
                    loader.getCurrentElementAttributeAsBoolean("g1"),
                    PresetColour.getColourFromId(loader.getCurrentElementAttributeAsStr("c2")),
                    loader.getCurrentElementAttributeAsBoolean("g2"));
        } catch (Exception e) { e.printStackTrace(); }
        return new Covering(BodyCoveringType.getBodyCoveringTypeFromId(type));
    }
}
