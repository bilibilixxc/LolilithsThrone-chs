package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.main.Main;

public class LoadItemEffect {
    public static ItemEffect loadEffect(XMLLoader loader, String effectType) {
        if (loader.debugMode) Logger.logTest("Loading Effect : " + effectType);
        String primaryMod = loader.getCurrentElementAttributeAsStr("mod1", loader.getNextElementAttributeAsStr("primaryModifier"));
        String secondaryMod = loader.getCurrentElementAttributeAsStr("mod2", loader.getNextElementAttributeAsStr("secondaryModifier"));

        TFPotency loadedPotency = loader.getCurrentElementAttributeAsStr("potency").equals("null") ? null :
                TFPotency.valueOf(loader.getCurrentElementAttributeAsStr("potency"));

        switch(effectType) {
            case "ATTRIBUTE_STRENGTH":
            case "ATTRIBUTE_FITNESS":
                effectType = "ATTRIBUTE_PHYSIQUE";
                break;
            case "ATTRIBUTE_INTELLIGENCE":
                effectType = "ATTRIBUTE_ARCANE";
                break;
        }
        switch(primaryMod) {
            case "DAMAGE_ATTACK":
            case "RESISTANCE_ATTACK":
                return null;
            case "CLOTHING_SEALING":
                primaryMod = "CLOTHING_SPECIAL";
                secondaryMod = "CLOTHING_SEALING";
                break;
            case "CLOTHING_ENSLAVEMENT":
                primaryMod = "CLOTHING_SPECIAL";
                secondaryMod = "CLOTHING_ENSLAVEMENT";
                break;
            case "TF_PENIS":
                if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
                    if(secondaryMod=="TF_MOD_SIZE_SECONDARY") {
                        secondaryMod = "TF_MOD_SIZE_TERTIARY";
                    }
                }
                break;
            case "CLOTHING_SPECIAL":
                if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.10.10")
                        && (secondaryMod.equalsIgnoreCase("CLOTHING_VIBRATION") || secondaryMod.equalsIgnoreCase("CLOTHING_ORGASM_PREVENTION"))) {
                    primaryMod = "CLOTHING_SEXUAL";
                }
                break;
        }

        switch(secondaryMod) {
            case "TF_MOD_FETISH_SEEDER":
                secondaryMod = "TF_MOD_FETISH_IMPREGNATION";
                break;
            case "TF_MOD_FETISH_BROODMOTHER":
                secondaryMod = "TF_MOD_FETISH_PREGNANCY";
                break;
            case "CRITICAL_CHANCE":
                secondaryMod = "CRITICAL_DAMAGE";
                break;
            case "CLOTHING_ANTI_SELF_TRANSFORMATION":
                secondaryMod = "CLOTHING_SERVITUDE";
                break;
            case "TF_MOD_ORIFICE_DEEP":
                secondaryMod = "TF_MOD_DEPTH";
                break;
            case "TF_MOD_ORIFICE_DEEP_2":
                secondaryMod = "TF_MOD_DEPTH_2";
                break;
            case "CLOTHING_SEALING":
                if(loadedPotency==TFPotency.MAJOR_BOOST || loadedPotency==TFPotency.BOOST) {
                    loadedPotency = TFPotency.MINOR_BOOST;
                }
                break;
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1") && effectType=="TF_TAIL" && primaryMod=="TF_MOD_SIZE") { // Girth TF was moved from TF_MOD_SIZE to TF_MOD_SIZE_SECONDARY in v0.4
            primaryMod = "TF_MOD_SIZE_SECONDARY";
        }


        ItemEffect ie;
        TFModifier primary = (primaryMod.equals("null") || primaryMod.isEmpty() ? null : TFModifier.valueOf(primaryMod));
        TFModifier secondary = (secondaryMod.equals("null") || secondaryMod.isEmpty() ? null : TFModifier.valueOf(secondaryMod));

        if(secondary!=null && TFModifier.getWeaponMajorAttributeList().contains(secondary)) {
            primary = TFModifier.CLOTHING_MAJOR_ATTRIBUTE;
        }

        // If there is no ItemEffectType with the id type, return null, as otherwise the game tries to load an incorrect ItemEffectType.
        if(!ItemEffectType.idToItemEffectTypeMap.containsKey(effectType)) {
            return null;
        }

        ie = new ItemEffect(
                ItemEffectType.getItemEffectTypeFromId(effectType),
                primary,
                secondary,
                loadedPotency,
                loader.getCurrentElementAttributeAsInt("limit"));


        int time = loader.getCurrentElementAttributeAsInt("timer");

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.5")) {// I think this was to align effects to all be on the hour:
            int timer = time / 60;
            ie.getTimer().setSecondsPassed(((timer * 60) + (int)(Main.game.getMinutesPassed() % 60)));
        } else if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6")) {
            ie.getTimer().setSecondsPassed(time * 60);
        } else {
            ie.getTimer().setSecondsPassed(time);
        }
        if (loader.debugMode) Logger.logTest("Finished Loading Effect Type : " + effectType);
        return ie;
    }
}
