package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.main.Main;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.Collection;
import java.util.Set;

public class FluidLoaders {
    public static FluidCum loadCum(XMLLoader loader, String rootElementName, AbstractFluidType baseType) {
        AbstractFluidType fluidType = FluidType.CUM_HUMAN;

        loader.moveToNode(rootElementName);
        fluidType = FluidType.getFluidTypeFromId(loader.getCurrentElementAttributeAsStr("type", fluidType.getId()));
        FluidCum fluidCum = new FluidCum(fluidType);
        String flavourID = loader.getCurrentElementAttributeAsStr("flavour");


        if(flavourID.equalsIgnoreCase("SLIME")) {
            fluidCum.flavour = FluidFlavour.BUBBLEGUM;
        } else {
            fluidCum.flavour = FluidFlavour.valueOf(flavourID);
        }

        fluidCum.getFluidModifiers().clear();
        loader.forEachTextContent("mod", modifier -> {
            fluidCum.getFluidModifiers().add(FluidModifier.valueOf(modifier));
        });
        if (loader.nodeExists("cumModifiers")) {
            Set<FluidModifier> fluidModifiers = fluidCum.getFluidModifiers();
            LoadBody.handleLoadingOfModifiers(loader, FluidModifier.values(), null, "cumModifiers", fluidModifiers);
        }
        loader.goToParent();
        return fluidCum;
    }

    public static FluidMilk loadMilk(XMLLoader loader, String rootElementName, AbstractFluidType baseType, boolean crotchMilk) {
        AbstractFluidType fluidType = FluidType.MILK_HUMAN;

        loader.moveToNode(rootElementName);
        fluidType = FluidType.getFluidTypeFromId(loader.getCurrentElementAttributeAsStr("type", fluidType.getId()));
        FluidMilk fluidMilk = new FluidMilk(fluidType, crotchMilk);
        String flavourID = loader.getCurrentElementAttributeAsStr("flavour");

        if(flavourID.equalsIgnoreCase("SLIME")) {
            fluidMilk.flavour = FluidFlavour.BUBBLEGUM;
        } else {
            fluidMilk.flavour = FluidFlavour.valueOf(flavourID);
        }

        fluidMilk.getFluidModifiers().clear();
        loader.forEachTextContent("mod", modifier -> {
            fluidMilk.getFluidModifiers().add(FluidModifier.valueOf(modifier));
        });
        if (loader.nodeExists("milkModifiers")) {
            Set<FluidModifier> fluidModifiers = fluidMilk.getFluidModifiers();
            LoadBody.handleLoadingOfModifiers(loader, FluidModifier.values(), null, "cumModifiers", fluidModifiers);
        }
        loader.goToParent();
        return fluidMilk;
    }

    public static FluidGirlCum loadGirlCum(XMLLoader loader, String rootElementName, AbstractFluidType baseType) {
        AbstractFluidType fluidType = FluidType.GIRL_CUM_HUMAN;

        loader.moveToNode(rootElementName);
        fluidType = FluidType.getFluidTypeFromId(loader.getCurrentElementAttributeAsStr("type", fluidType.getId()));
        FluidGirlCum fluidGirlcum = new FluidGirlCum(fluidType);
        String flavourID = loader.getCurrentElementAttributeAsStr("flavour");

        if(flavourID.equalsIgnoreCase("SLIME")) {
            fluidGirlcum.flavour = FluidFlavour.BUBBLEGUM;
        } else {
            fluidGirlcum.flavour = FluidFlavour.valueOf(flavourID);
        }

        fluidGirlcum.getFluidModifiers().clear();
        loader.forEachTextContent("mod", modifier -> {
            fluidGirlcum.getFluidModifiers().add(FluidModifier.valueOf(modifier));
        });
        if (loader.nodeExists("girlcumModifiers")) {
            Set<FluidModifier> fluidModifiers = fluidGirlcum.getFluidModifiers();
            LoadBody.handleLoadingOfModifiers(loader, FluidModifier.values(), null, "girlcumModifiers", fluidModifiers);
        }

        return fluidGirlcum;
    }
}
