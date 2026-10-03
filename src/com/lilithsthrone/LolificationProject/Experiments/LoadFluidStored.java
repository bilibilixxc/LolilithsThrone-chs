package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import org.w3c.dom.Element;

public class LoadFluidStored {
    public static FluidStored loadFluidStored(XMLLoader loader, String characterID, StringBuilder log) {
//        if (characterID.equalsIgnoreCase("-1,Epona")) loader.setDebugMode(true);
        if (loader.debugMode) Logger.logTest("Loading Fluid Stored for : " + characterID);
        boolean cumVirile = loader.getCurrentElementAttributeAsBoolean("cumVirile", true);
        boolean feral = loader.getCurrentElementAttributeAsBoolean("bestial");
        float virility = loader.getCurrentElementAttributeAsFloat("virility", 25);
        float millis = loader.getCurrentElementAttributeAsFloat("millilitres");

        FluidStored fluid;

        if (loader.moveToIfHasChildren("body")) {
            // Cum:
            if (loader.nodeExists("fluidCum")) {

                fluid = new FluidStored(characterID,
                        LoadBody.loadBody(loader, log),
                        FluidLoaders.loadCum(loader, "fluidCum", null), millis);
                fluid.feral = feral;
                fluid.cumVirile = cumVirile;
                fluid.virility = virility;
                if (loader.debugMode) Logger.logTest("Finished Loading Fluid Stored for : " + characterID);
//                loader.setDebugMode(false);
                return fluid;
            }
            loader.goToParent();
        } else { // Old version support:
            // Milk:
            if (loader.nodeExists("milk")) {
                fluid = new FluidStored(characterID, FluidLoaders.loadMilk(loader, "milk", null, false), millis);
                fluid.feral = feral;
                fluid.cumVirile = false;
                fluid.virility = 0;
                if (loader.debugMode) Logger.logTest("Finished Loading Fluid Stored for : " + characterID);
//                loader.setDebugMode(false);
                return fluid;

            } else if (loader.nodeExists("fluidMilk")) {
                fluid = new FluidStored(characterID, FluidLoaders.loadMilk(loader, "fluidMilk", null, false), millis);
                fluid.feral = feral;
                fluid.cumVirile = false;
                fluid.virility = 0;
                if (loader.debugMode) Logger.logTest("Finished Loading Fluid Stored for : " + characterID);
//                loader.setDebugMode(false);
                return fluid;
            }

            // Girlcum:
            if (loader.nodeExists("girlcum")) {
                fluid = new FluidStored(characterID, FluidLoaders.loadGirlCum(loader, "girlcum", null), millis);
                fluid.feral = feral;
                fluid.cumVirile = false;
                fluid.virility = 0;
                if (loader.debugMode) Logger.logTest("Finished Loading Fluid Stored for : " + characterID);
//                loader.setDebugMode(false);
                return fluid;

            } else if (loader.nodeExists("fluidGirlCum")) {
                fluid = new FluidStored(characterID, FluidLoaders.loadGirlCum(loader, "fluidGirlCum", null), millis);
                fluid.feral = feral;
                fluid.cumVirile = false;
                fluid.virility = 0;
//                loader.setDebugMode(false);
                if (loader.debugMode) Logger.logTest("Finished Loading Fluid Stored for : " + characterID);
                return fluid;
            }

            // Cum:
            if (loader.nodeExists("cum")) {
                AbstractSubspecies subspecies = Subspecies.HUMAN;
                AbstractSubspecies halfDemonSubspecies = Subspecies.HUMAN;
                subspecies = Subspecies.getSubspeciesFromId(loader.getCurrentElementAttributeAsStr("cumSubspecies"));
                halfDemonSubspecies = Subspecies.getSubspeciesFromId(loader.getCurrentElementAttributeAsStr("cumHalfDemonSubspecies"));

                fluid = new FluidStored(characterID,
                        subspecies == Subspecies.HALF_DEMON
                                ? Main.game.getCharacterUtils().generateHalfDemonBody(null, Gender.M_P_MALE, halfDemonSubspecies, false)
                                : Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, subspecies, RaceStage.GREATER),
                        FluidLoaders.loadCum(loader, "cum", null), millis);

                fluid.feral = feral;
                fluid.cumVirile = cumVirile;
                fluid.virility = virility;
                if (loader.debugMode) Logger.logTest("Finished Loading Fluid Stored for : " + characterID);
//                loader.setDebugMode(false);
                return fluid;
            }
        }
        if (loader.debugMode) Logger.logTest("WARNING: FluidStored failed to load!");
//        loader.setDebugMode(false);
        new Exception().printStackTrace();
        return null;
    }
}
