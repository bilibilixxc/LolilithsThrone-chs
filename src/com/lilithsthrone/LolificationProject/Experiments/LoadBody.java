package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.Lexi.character.body.OrificeTussy;
import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.body.*;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractLegType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTentacleType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.lilithsthrone.game.character.body.Body.handleCapacityLoading;

public class LoadBody {

    static <T extends Enum<T>> void handleLoadingOfModifiers(XMLLoader loader, T[] enumValues, StringBuilder log, String modifiersElementName, Collection<T> modifiers) {
        loader.moveToNode(modifiersElementName);
        for(T enumValue : enumValues) {
            String attributeValue = loader.getCurrentElementAttributeAsStr(enumValue.toString());
            if(!attributeValue.isEmpty()) {
                if(Boolean.valueOf(attributeValue)) {
                    if(!modifiers.contains(enumValue)) {
                        modifiers.add(enumValue);
                    }
                    if(log!=null) {
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":true");
                    }
                } else if (!attributeValue.isEmpty()) {
                    modifiers.remove(enumValue);
                    if(log!=null) {
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":false");
                    }
                } else {
                    if(log!=null) {
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>"+enumValue.toString()+":not present, defaulted to "+modifiers.contains(enumValue));
                    }
                }
            }
        }
        loader.goToParent();
    }

    static AbstractSubspecies importedSubspeciesOverride = null;
    public static Body loadBody(XMLLoader loader, StringBuilder log) {
        loader.moveToNode("body");

        // **************** Core **************** //
        String version = loader.getNextElementAttributeAsStr("bodyCore", "version");
        int importedFemininity = loader.getCurrentElementAttributeAsInt("femininity");
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set femininity: " + importedFemininity);
        int importedHeight = loader.getCurrentElementAttributeAsInt("height");
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set height: " + importedHeight);
        int importedBodySize = loader.getCurrentElementAttributeAsInt("bodySize");
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set body size: " + importedBodySize);
        int importedMuscle = loader.getCurrentElementAttributeAsInt("muscle");
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set muscle: " + importedMuscle);


        GenitalArrangement importedGenitalArrangement = GenitalArrangement.valueOf(loader.getCurrentElementAttributeAsStr("genitalArrangement",
                GenitalArrangement.NORMAL.toString()));

        BodyMaterial importedBodyMaterial = BodyMaterial.valueOf(loader.getCurrentElementAttributeAsStr("bodyMaterial", BodyMaterial.FLESH.toString()));

        boolean feralBody = loader.getCurrentElementAttributeAsBoolean("feral", false);

        String attributeSubspeciesOverride;
        if (!(attributeSubspeciesOverride = loader.getCurrentElementAttributeAsStr("subspeciesOverride")).isEmpty()) {
            importedSubspeciesOverride = Subspecies.getSubspeciesFromId(attributeSubspeciesOverride);
        }
        AbstractSubspecies importedLoadedSubspecies = null;
        String attributeLoadedSubspecies;
        if (!(attributeLoadedSubspecies = loader.getCurrentElementAttributeAsStr("subspecies")).isEmpty()) {
            importedSubspeciesOverride = Subspecies.getSubspeciesFromId(attributeLoadedSubspecies);
        }
        boolean piercedStomach = loader.getCurrentElementAttributeAsBoolean("piercedStomach");
        BodyHair pubicHair = BodyHair.valueOf(loader.getCurrentElementAttributeAsStr("pubicHair", "ZERO_NONE"));
        boolean takesAfterMother = loader.getCurrentElementAttributeAsBoolean("takesAfterMother");
        loader.goToParent();// because we sneakily went into <bodyCore>

        // **************** Antenna **************** //
        Antenna importedAntenna = loadAntennae(loader, version, log);

        // **************** Arm **************** //
        Arm importedArm = loadArm(loader, version, log);

        // **************** Ass **************** //
        Ass importedAss = loadAss(loader, version, log);

        // **************** Breasts **************** //
        Breast importedBreast = loadBreast(loader, version, log);

        // **************** Ear **************** // // TODO ADD THREADING MUST FILL BRAIN TANK WITH JIZZ
        Ear importedEar = loadEar(loader, version, log);

        // **************** Eye **************** //
        Eye importedEye = loadEye(loader, version, log);

        // **************** Face **************** //
        Face importedFace = loadFace(loader, version, log);

        // **************** Hair **************** //
        Hair importedHair = loadHair(loader, version, log);

        // **************** Horn **************** //
        Horn importedHorn = loadHorn(loader, version, log);

        // **************** Leg **************** //
        Leg importedLeg = loadLeg(loader, version, log);

        // **************** Penis **************** //
        Penis importedPenis = loadPenis(loader, version, log);

        // **************** Skin **************** //
        Torso importedTorso = loadTorso(loader, version, log);


        // **************** Spinneret **************** //
        OrificeSpinneret importedSpinneret = loadSpinneret(loader, version, log);


        // **************** Tussy **************** //
        OrificeTussy importedTussy = loadTussy(loader, version, log);


        // **************** Tail **************** //
        Tail importedTail = loadTail(loader, version, log);


        // **************** Tentacle **************** //
        Tentacle importedTentacle = loadTentacle(loader, version, log);


        // **************** Vagina **************** //
        Vagina importedVagina = loadVagina(loader, version, log);

        // **************** Wing **************** //
        Wing importedWing = loadWing(loader, version, log);


        // ************** Version Overrides **************//

        if (Main.isVersionOlderThan(Game.loadingVersion, "0.2.5.1")) {
            importedVagina.girlcum.type = importedVagina.getType().getFluidType();
            importedPenis.testicle.cum.type = importedPenis.getType().getTesticleType().getFluidType();
            importedBreast.milk.type = importedBreast.getType().getFluidType();
        }


        if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
            // Convert all sizes from inch to cm
            importedHair.length *= 2.54;
            importedHorn.length *= 2.54;
            importedFace.tongue.tongueLength *= 2.54;
            importedPenis.length *= 2.54;
            importedVagina.clitoris.clitSize *= 2.54;

            // Convert all capacities from inch to cm
            importedFace.mouth.orificeMouth.capacity *= 2.54;
            importedFace.mouth.orificeMouth.stretchedCapacity *= 2.54;
            importedPenis.orificeUrethra.capacity *= 2.54;
            importedPenis.orificeUrethra.stretchedCapacity *= 2.54;
            importedVagina.orificeVagina.capacity *= 2.54;
            importedVagina.orificeVagina.stretchedCapacity *= 2.54;
            importedVagina.orificeUrethra.capacity *= 2.54;
            importedVagina.orificeUrethra.stretchedCapacity *= 2.54;
            importedAss.anus.orificeAnus.capacity *= 2.54;
            importedAss.anus.orificeAnus.stretchedCapacity *= 2.54;
            importedBreast.nipples.orificeNipples.capacity *= 2.54;
            importedBreast.nipples.orificeNipples.stretchedCapacity *= 2.54;
        }

        if (oldPantherReplacement) {
            if (importedArm.getType().getRace() == Race.CAT_MORPH) {
                importedArm.setType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedAss.getType().getRace() == Race.CAT_MORPH) {
                importedAss.setType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedBreast.getType().getRace() == Race.CAT_MORPH) {
                importedBreast.setType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedFace.getType().getRace() == Race.CAT_MORPH) {
                importedFace.setType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedEye.getType().getRace() == Race.CAT_MORPH) {
                importedEye.setType(null, EyeType.getEyeTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedEar.getType().getRace() == Race.CAT_MORPH) {
                importedEar.setType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedHair.getType().getRace() == Race.CAT_MORPH) {
                importedHair.setType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedLeg.getType().getRace() == Race.CAT_MORPH) {
                importedLeg.setType(null, LegType.getLegTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedTorso.getType().getRace() == Race.CAT_MORPH) {
                importedTorso.setType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }

            if (importedVagina.getType().getRace() == Race.CAT_MORPH) {
                importedVagina.setType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedPenis.getType().getRace() == Race.CAT_MORPH) {
                importedPenis.setType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
            if (importedTail.getType().getRace() == Race.CAT_MORPH) {
                importedTail.setType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_panther")).get(0));
            }
        }

        Body body = new Body.BodyBuilder(
                importedArm,
                importedAss,
                importedBreast,
                importedFace,
                importedEye,
                importedEar,
                importedHair,
                importedLeg,
                importedTorso,
                importedBodyMaterial,
                importedGenitalArrangement,
                importedHeight,
                importedFemininity,
                importedBodySize,
                importedMuscle)
                .vagina(importedVagina)
                .penis(importedPenis)
                .horn(importedHorn)
                .antenna(importedAntenna)
                .tail(importedTail)
                .wing(importedWing)
                .spinneret(importedSpinneret)
                .tussy(importedTussy)
                .tentacle(importedTentacle)
                .build();


        // **************** Crotch Breasts **************** //
        BreastCrotch importedBreastCrotch = loadCrotchBreast(loader, version, log, body);

        if (importedBreastCrotch != null) {
            if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.8")) {
                importedBreastCrotch.nipples.orificeNipples.capacity *= 2.54;
                importedBreastCrotch.nipples.orificeNipples.stretchedCapacity *= 2.54;
            }

            body.setBreastCrotch(importedBreastCrotch);
        }


        // **************** OTHER **************** //

        body.setSubspeciesOverride(importedSubspeciesOverride);
        body.loadedSubspecies = importedLoadedSubspecies;

        body.setPiercedStomach(piercedStomach);
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set piercedStomach: " + piercedStomach);

        body.setTakesAfterMother(takesAfterMother);
        body.setPubicHair(pubicHair);
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set pubicHair: " + body.getPubicHair());

        loadBodyCoverings(loader, version, log, body);// Also handles Heavy Makeup

        body.feral = feralBody;
        body.addDiscoveredBodyCoveringsFromMaterial(importedBodyMaterial);
        body.calculateRace(null);

        // Converting harpy bald eagles to raptor bald eagles:
        if (Main.isVersionOlderThan(version, "0.4.2.5")) {
            if (body.getRace() == Race.HARPY) {
                AbstractBodyCoveringType feathers = body.getBodyMaterial() == BodyMaterial.SLIME ? BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MAIN_FEATHER) : BodyCoveringType.FEATHERS;
                AbstractBodyCoveringType headFeathers = body.getBodyMaterial() == BodyMaterial.SLIME ? BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.HAIR) : BodyCoveringType.HAIR_HARPY;

                if (body.getCoverings().get(feathers).getPrimaryColour() == PresetColour.COVERING_BROWN_DARK && body.getCoverings().get(headFeathers).getPrimaryColour() == PresetColour.COVERING_WHITE) {

                    if (body.getArmType().getRace() == Race.HARPY) {
                        body.setArmType(null, ArmType.getArmTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getAssType().getRace() == Race.HARPY) {
                        body.setAssType(null, AssType.getAssTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getBreastType().getRace() == Race.HARPY) {
                        body.setBreastType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getBreastCrotchType().getRace() == Race.HARPY) {
                        body.setBreastCrotchType(null, BreastType.getBreastTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getFaceType().getRace() == Race.HARPY) {
                        body.setFaceType(null, FaceType.getFaceTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getEyeType().getRace() == Race.HARPY) {
                        body.setEyeType(null, EyeType.getEyeTypeFromId("innoxia_raptor_eye"));
                    }
                    if (body.getEarType().getRace() == Race.HARPY) {
                        body.setEarType(null, EarType.getEarTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getHairType().getRace() == Race.HARPY) {
                        body.setHairType(null, HairType.getHairTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getLegType().getRace() == Race.HARPY) {
                        body.setLegType(null, LegType.getLegTypeFromId("innoxia_raptor_leg_large"));
                    }
                    if (body.getTorsoType().getRace() == Race.HARPY) {
                        body.setTorsoType(null, TorsoType.getTorsoTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }

                    if (body.getVaginaType().getRace() == Race.HARPY) {
                        body.setVaginaType(null, VaginaType.getVaginaTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getPenisType().getRace() == Race.HARPY) {
                        body.setPenisType(null, PenisType.getPenisTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }
                    if (body.getTailType().getRace() == Race.HARPY) {
                        body.setTailType(null, TailType.getTailTypes(Race.getRaceFromId("innoxia_raptor")).get(0));
                    }

                    // coverings:
                    Body.coveringCopyConversion(body, BodyCoveringType.FEATHERS, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_feathers"));
                    Body.coveringCopyConversion(body, BodyCoveringType.HARPY_SKIN, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_skin"));
                    Body.coveringCopyConversion(body, BodyCoveringType.BODY_HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_body_hair"));
                    Body.coveringCopyConversion(body, BodyCoveringType.EYE_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_eye"));
                    Body.coveringCopyConversion(body, BodyCoveringType.HAIR_HARPY, BodyCoveringType.getBodyCoveringTypeFromId("innoxia_raptor_hair"));
                }
            }
        }

        if (Main.isVersionOlderThan(version, "0.3.0.5")) {
            body.updateNippleCrotchColouring();
        }
        loader.goToParent();

        return body;

    }

    private static void loadBodyCoverings(XMLLoader loader, String version, StringBuilder log, Body body) {
        loader.moveToNode("bodyCore");
        loader.forEachAttributeOfElement("bodyCovering", "type", type -> {
            if (type.equals("HORN_COW") || type.equals("HORN_DEMON")) {
                type = "HORN";
            }

            String colourPrimary = loader.getCurrentElementAttributeAsStr("colourPrimary", loader.getCurrentElementAttributeAsStr("c1"));
            String colourSecondary = loader.getCurrentElementAttributeAsStr("colourSecondary",
                    loader.getCurrentElementAttributeAsStr("c2", colourPrimary));// If secondary colour is missing, then it's the same as the primary

            if (type.startsWith("HAIR_")) {
                if (colourPrimary.equals("COVERING_TAN")) {
                    colourPrimary = "COVERING_DIRTY_BLONDE";
                }
                if (colourSecondary.equals("COVERING_TAN")) {
                    colourSecondary = "COVERING_DIRTY_BLONDE";
                }
            }

            if (type.startsWith("EYE_")) {
                if (colourPrimary.equals("COVERING_BLUE")) {
                    colourPrimary = "COVERING_BLUE_LIGHT";
                }
                if (colourSecondary.equals("COVERING_BLUE")) {
                    colourSecondary = "COVERING_BLUE_LIGHT";
                }
            }

            AbstractBodyCoveringType coveringType = BodyCoveringType.getBodyCoveringTypeFromId(type);
            CoveringPattern loadedPattern = CoveringPattern.valueOf(loader.getCurrentElementAttributeAsStr("pattern", "NONE"));
            if (!coveringType.getAllPatterns().containsKey(loadedPattern)) {
                loadedPattern = Util.getRandomObjectFromWeightedMap(coveringType.getNaturalPatterns());
            }
            String coveringModifier;
            boolean g1 = loader.getCurrentElementAttributeAsBoolean("g1");
            boolean g2 = loader.getCurrentElementAttributeAsBoolean("g2");
            if ((coveringModifier = loader.getCurrentElementAttributeAsStr("modifier")).isEmpty()) {
                body.setBodyCoveringForXMLImport(coveringType,
                        loadedPattern,
                        PresetColour.getColourFromId(colourPrimary),
                        g1,
                        PresetColour.getColourFromId(colourSecondary),
                        g2);

            } else { //TODO
                CoveringModifier modifier = CoveringModifier.valueOf(coveringModifier);

                body.setBodyCoveringForXMLImport(coveringType,
                        loadedPattern,
                        coveringType.getNaturalModifiers().contains(modifier) || coveringType.getExtraModifiers().contains(modifier) ? modifier : coveringType.getNaturalModifiers().get(0),
                        PresetColour.getColourFromId(colourPrimary), g1,
                        PresetColour.getColourFromId(colourSecondary), g2);
            }

            if (oldPantherReplacement && coveringType == BodyCoveringType.FELINE_FUR) {
                Covering felineCovering = body.getCovering(coveringType, false);
                body.setBodyCoveringForXMLImport(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"),
                        felineCovering.getPattern(),
                        felineCovering.getModifier(),
                        felineCovering.getPrimaryColour(),
                        felineCovering.isPrimaryGlowing(),
                        felineCovering.getSecondaryColour(),
                        felineCovering.isSecondaryGlowing());
                body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId("innoxia_panther_fur"));
            }

            body.addBodyCoveringTypesDiscovered(BodyCoveringType.getBodyCoveringTypeFromId(type));

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Body: Set bodyCovering: " + colourPrimary + ", " + colourSecondary);

            if (loader.moveToIfHasChildren("heavyMakeup")) {
                loader.forEachTextContent("type", makeupType -> {
                    body.addHeavyMakeup(BodyCoveringType.getBodyCoveringTypeFromId(makeupType));
                });
            }
        });
        loader.goToParent();
    }

    private static Wing loadWing(XMLLoader loader, String version, StringBuilder log) {
        Wing importedWing = null;
        String wingType;
        if (!(wingType = loader.getNextElementAttributeAsStr("wing", "type")).isEmpty()) {
            int wingSize = loader.getCurrentElementAttributeAsInt("size");
            importedWing = new Wing(WingType.getWingTypeFromId(wingType), wingSize);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Wing: "
                    + "<br/>type: " + importedWing.getType() + "<br/>"
                    + "<br/>size: " + importedWing.getSizeValue() + "<br/>");
        }
        loader.goToParent();
        return importedWing;
    }

    private static Vagina loadVagina(XMLLoader loader, String version, StringBuilder log) {
        Vagina importedVagina;
        String vaginaType;
        if (!(vaginaType = loader.getNextElementAttributeAsStr("vagina", "type")).isEmpty()) {
            int clitGirth = loader.getCurrentElementAttributeAsInt("clitGirth", PenetrationGirth.THREE_AVERAGE.getValue());
            int clitSize = loader.getCurrentElementAttributeAsInt("clitSize");
            int labiaSize = loader.getCurrentElementAttributeAsInt("labiaSize", 1);
            boolean eggLayer = loader.getCurrentElementAttributeAsBoolean("eggLayer", vaginaType.equals("DEMON_EGGS") || vaginaType.equals("NoStepOnSnek_snake_vagina_e"));
            boolean hymen = loader.getCurrentElementAttributeAsBoolean("hymen");
            boolean squirter = loader.getCurrentElementAttributeAsBoolean("squirter");
            boolean pierced = loader.getCurrentElementAttributeAsBoolean("pierced");

            int depth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            int elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            int plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            int wetness = loader.getCurrentElementAttributeAsInt("wetness");
            boolean virgin = loader.getCurrentElementAttributeAsBoolean("virgin", true);
            float capacity = loader.getCurrentElementAttributeAsFloat("capacity");
            float stretchedCapacity = loader.getCurrentElementAttributeAsFloat("stretchedCapacity");

            int urethraDepth = loader.getCurrentElementAttributeAsInt("urethraDepth", OrificeDepth.TWO_AVERAGE.getValue());
            int urethraElasticity = loader.getCurrentElementAttributeAsInt("urethraElasticity");
            int urethraPlasticity = loader.getCurrentElementAttributeAsInt("urethraPlasticity");
            boolean urethraVirgin = loader.getCurrentElementAttributeAsBoolean("urethraVirgin", true);
            float urethraCapacity = loader.getCurrentElementAttributeAsFloat("urethraCapacity");
            float urethraStretchedCapacity = loader.getCurrentElementAttributeAsFloat("urethraStretchedCapacity");

            importedVagina = new Vagina(VaginaType.getVaginaTypeFromId(vaginaType),
                    labiaSize, clitSize, clitGirth, wetness, handleCapacityLoading(capacity), depth, elasticity, plasticity, virgin);

            Collection<PenetrationModifier> clitModifiers = importedVagina.clitoris.clitModifiers;
            clitModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.nodeExists("clitModifiers")) {
                    handleLoadingOfModifiers(loader, PenetrationModifier.values(), log, "clitModifiers", clitModifiers);
                }
            } else {
                loader.forEachTextContent("modClit", modifier -> {
                    importedVagina.getClitoris().addClitorisModifier(null, PenetrationModifier.valueOf(modifier));
                });
            }

            importedVagina.pierced = pierced;
            importedVagina.eggLayer = eggLayer;
            importedVagina.orificeVagina.stretchedCapacity = handleCapacityLoading(stretchedCapacity);
            importedVagina.orificeVagina.squirter = squirter;
            if (Main.isVersionOlderThan(version, "0.3.6.9")) {
                importedVagina.orificeVagina.hymen = importedVagina.getOrificeVagina().isVirgin();
            } else {
                importedVagina.orificeVagina.hymen = hymen;
            }

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Vagina: "
                    + "<br/>type: " + importedVagina.getType()
                    + "<br/>clitSize: " + importedVagina.clitoris.getClitorisSize()
                    + "<br/>pierced: " + importedVagina.isPierced()

                    + "<br/>wetness: " + importedVagina.orificeVagina.wetness
                    + "<br/>depth: " + importedVagina.orificeVagina.getDepth(null)
                    + "<br/>elasticity: " + importedVagina.orificeVagina.getElasticity()
                    + "<br/>plasticity: " + importedVagina.orificeVagina.getPlasticity()
                    + "<br/>capacity: " + importedVagina.orificeVagina.getCapacity()
                    + "<br/>stretchedCapacity: " + importedVagina.orificeVagina.getStretchedCapacity()
                    + "<br/>virgin: " + importedVagina.orificeVagina.isVirgin());

            Collection<OrificeModifier> vaginaOrificeModifiers = importedVagina.orificeVagina.orificeModifiers;
            vaginaOrificeModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("vaginaModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedVagina.orificeVagina.setDepth(null, depth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "vaginaModifiers", vaginaOrificeModifiers);
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedVagina.orificeVagina.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }

            importedVagina.orificeUrethra.depth = urethraDepth;
            importedVagina.orificeUrethra.elasticity = urethraElasticity;
            importedVagina.orificeUrethra.plasticity = urethraPlasticity;
            importedVagina.orificeUrethra.capacity = handleCapacityLoading(urethraCapacity);
            importedVagina.orificeUrethra.stretchedCapacity = handleCapacityLoading(urethraStretchedCapacity);
            importedVagina.orificeUrethra.virgin = urethraVirgin;

            Collection<OrificeModifier> vaginaUrethraOrificeModifiers = importedVagina.orificeUrethra.orificeModifiers;
            vaginaUrethraOrificeModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("urethraModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedVagina.orificeUrethra.setDepth(null, depth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "urethraModifiers", vaginaUrethraOrificeModifiers);
                }
            } else {
                loader.forEachTextContent("modUrethra", modifier -> {
                    importedVagina.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Girlcum:");
            importedVagina.girlcum = FluidLoaders.loadGirlCum(loader,"girlcum", importedVagina.getType().getFluidType());
        } else {
            importedVagina = null;
        }

        loader.goToParent();
        return importedVagina;
    }

    private static Tentacle loadTentacle(XMLLoader loader, String version, StringBuilder log) {
        Tentacle importedTentacle = new Tentacle(TentacleType.NONE);
        String importedType;
        if (!(importedType = loader.getNextElementAttributeAsStr("tentacle", "type")).isEmpty()) {
            AbstractTentacleType tentacleType = TentacleType.getTentacleTypeFromId(importedType);
            importedTentacle = new Tentacle(tentacleType);
            int count = loader.getCurrentElementAttributeAsInt("count");
            int girth = loader.getCurrentElementAttributeAsInt("girth");
            float length = loader.getCurrentElementAttributeAsFloat("length", tentacleType.getDefaultLengthAsPercentageOfHeight());

            importedTentacle.tentacleCount = count;
            importedTentacle.girth = girth;
            importedTentacle.lengthAsPercentageOfHeight = length;

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tentacle: "
                    + "<br/>type: " + importedTentacle.getType()
                    + "<br/>count: " + importedTentacle.getTentacleCount());
        }
        loader.goToParent();
        return importedTentacle;
    }

    private static Tail loadTail(XMLLoader loader, String version, StringBuilder log) {
        Tail importedTail = null;
        String importedType;
        if (!(importedType = loader.getNextElementAttributeAsStr("tail", "type")).isEmpty()) {

            AbstractTailType tailType = TailType.getTailTypeFromId(importedType);
            int count = loader.getCurrentElementAttributeAsInt("count");
            int girth = loader.getCurrentElementAttributeAsInt("girth");
            float length = loader.getCurrentElementAttributeAsFloat("length", tailType.getDefaultLengthAsPercentageOfHeight());


            importedTail = new Tail(tailType);

            importedTail.tailCount = count;
            if (Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
                girth += 1;
            }
            importedTail.girth = girth;

            if (Main.isVersionOlderThan(version, "0.3.10")) {
                importedTail.lengthAsPercentageOfHeight = tailType.getDefaultLengthAsPercentageOfHeight();
            } else {// Leaving this, due to the above making me consider whatever it could've been before may break, maybe it tries to load a 120cm as 12000% of length.
                importedTail.lengthAsPercentageOfHeight = length;
            }

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Tail: "
                    + "<br/>type: " + importedTail.getType()
                    + "<br/>count: " + importedTail.getTailCount());
        }


        loader.goToParent();
        return importedTail;
    }

    private static OrificeTussy loadTussy(XMLLoader loader, String version, StringBuilder log) {
        OrificeTussy importedTussy;
        int tussyDepth;
        if ((tussyDepth = loader.getNextElementAttributeAsInt("tussy","depth", -1)) != -1) {
            importedTussy = new OrificeTussy();
            importedTussy.wetness = loader.getCurrentElementAttributeAsInt("wetness");
            importedTussy.capacity = handleCapacityLoading(loader.getCurrentElementAttributeAsFloat("capacity"));
            importedTussy.depth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            importedTussy.elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            importedTussy.plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            importedTussy.virgin = loader.getCurrentElementAttributeAsBoolean("virgin");
            importedTussy.stretchedCapacity = handleCapacityLoading(loader.getCurrentElementAttributeAsFloat("stretchedCapacity"));

            Collection<OrificeModifier> tussyOrificeModifiers = importedTussy.orificeModifiers;
            tussyOrificeModifiers.clear();
            loader.forEachTextContent("mod", modifier -> {
                importedTussy.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
            });
        } else {
            importedTussy = null;
        }

        loader.goToParent();
        return importedTussy;
    }

    private static OrificeSpinneret loadSpinneret(XMLLoader loader, String version, StringBuilder log) {
        OrificeSpinneret importedSpinneret;
        int spinneretDepth;
        if ((spinneretDepth = loader.getNextElementAttributeAsInt("spinneret", "depth", -1)) != -1) {

            importedSpinneret = new OrificeSpinneret();

            importedSpinneret.wetness = loader.getCurrentElementAttributeAsInt("wetness");
            importedSpinneret.depth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            importedSpinneret.capacity = handleCapacityLoading(loader.getCurrentElementAttributeAsFloat("capacity"));
            importedSpinneret.elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            importedSpinneret.plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            importedSpinneret.virgin = loader.getCurrentElementAttributeAsBoolean("virgin");
            importedSpinneret.stretchedCapacity = handleCapacityLoading(loader.getCurrentElementAttributeAsFloat("stretchedCapacity"));

            Collection<OrificeModifier> spinneretOrificeModifiers = importedSpinneret.orificeModifiers;
            spinneretOrificeModifiers.clear();

            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.nodeExists("spinneretModifiers")) {
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "spinneretModifiers", spinneretOrificeModifiers);
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedSpinneret.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }
        } else {
            importedSpinneret = null;
        }


        loader.goToParent();
        return importedSpinneret;
    }

    private static Torso loadTorso(XMLLoader loader, String version, StringBuilder log) {
        Torso importedTorso = null;
        String torsoType;
        if (!(torsoType = loader.getNextElementAttributeAsStr("torso", "type", loader.getNextElementAttributeAsStr("skin", "type"))).isEmpty()) {
            importedTorso = new Torso(TorsoType.getTorsoTypeFromId(torsoType));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Torso: <br/>type: " + importedTorso.getType());
        }
        loader.goToParent();
        return importedTorso;
    }

    private static Penis loadPenis(XMLLoader loader, String version, StringBuilder log) {
        Penis importedPenis;
        String penisType;
        if (!(penisType = loader.getNextElementAttributeAsStr("penis", "type")).isEmpty()) {
            boolean virgin = loader.getCurrentElementAttributeAsBoolean("virgin", true);
            boolean pierced = loader.getCurrentElementAttributeAsBoolean("pierced", true);
            int girth = loader.getCurrentElementAttributeAsInt("girth");
            int size = loader.getCurrentElementAttributeAsInt("size");
            String previousType = loader.getCurrentElementAttributeAsStr("previousType");

            int urethraDepth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            int urethraElasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            int urethraPlasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            boolean urethraVirgin = loader.getCurrentElementAttributeAsBoolean("urethraVirgin", true);
            float urethraCapacity = loader.getCurrentElementAttributeAsFloat("capacity");
            float urethraStretchedCapacity = loader.getCurrentElementAttributeAsFloat("stretchedCapacity");

            if (Main.isVersionOlderThan(version, "0.3.7.6")) {// An extra level was added in 0.3.7.6, so +1 if the version is older than that
                girth += 1;
            }


            int numberOfTesticles = loader.getNextElementAttributeAsInt("testicles", "numberOfTesticles", 2);
            int cumExpulsion = loader.getCurrentElementAttributeAsInt("cumExpulsion");
            int cumRegeneration = loader.getCurrentElementAttributeAsInt("cumRegeneration");
            int cumStorage = loader.getCurrentElementAttributeAsInt("cumStorage",
                    loader.getCurrentElementAttributeAsInt("cumProduction"));
            int testicleSize = loader.getCurrentElementAttributeAsInt("testicleSize");
            boolean internal = loader.getCurrentElementAttributeAsBoolean("internal");
            float storedCum = loader.getCurrentElementAttributeAsFloat("storedCum");

            importedPenis = new Penis(PenisType.getPenisTypeFromId(penisType),
                    size,false, girth, testicleSize,
                    cumStorage, numberOfTesticles);
            importedPenis.pierced = pierced;
            importedPenis.virgin = virgin;

            Collection<PenetrationModifier> penisModifiers = importedPenis.penisModifiers;
            penisModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.nodeExists("penisModifiers")) {
                    handleLoadingOfModifiers(loader, PenetrationModifier.values(), log, "penisModifiers", penisModifiers);
                }
            } else {
                if (loader.hasChildren("penis")) {
                    loader.forEachTextContent("mod", modifier -> {
                        importedPenis.addPenisModifier(null, PenetrationModifier.valueOf(modifier));
                    });
                }
            }

            if (!previousType.isEmpty()) {
                importedPenis.previousType = PenisType.getPenisTypeFromId(previousType);
            }

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Penis: "
                    + "<br/>type: " + importedPenis.getType()
                    + "<br/>size: " + importedPenis.getRawLengthValue()
                    + "<br/>pierced: " + importedPenis.isPierced()
                    + "<br/>Penis Modifiers: ");


            importedPenis.orificeUrethra.depth = urethraDepth;
            importedPenis.orificeUrethra.elasticity = urethraElasticity;
            importedPenis.orificeUrethra.plasticity = urethraPlasticity;
            importedPenis.orificeUrethra.capacity = handleCapacityLoading(urethraCapacity);
            importedPenis.orificeUrethra.stretchedCapacity = handleCapacityLoading(urethraStretchedCapacity);
            importedPenis.orificeUrethra.virgin = urethraVirgin;

            Main.game.getCharacterUtils().appendToImportLog(log,
                    "<br/>depth: " + importedPenis.orificeUrethra.getDepth(null)
                            + "<br/>elasticity: " + importedPenis.orificeUrethra.getElasticity()
                            + "<br/>plasticity: " + importedPenis.orificeUrethra.getPlasticity()
                            + "<br/>capacity: " + importedPenis.orificeUrethra.getCapacity()
                            + "<br/>stretchedCapacity: " + importedPenis.orificeUrethra.getStretchedCapacity()
                            + "<br/>virgin: " + importedPenis.orificeUrethra.isVirgin()
                            + "<br/>Urethra Modifiers:");

            Collection<OrificeModifier> urethraOrificeModifiers = importedPenis.orificeUrethra.orificeModifiers;
            urethraOrificeModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("urethraModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedPenis.orificeUrethra.setDepth(null, urethraDepth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "urethraModifiers", urethraOrificeModifiers);
                }
            } else {
                if (loader.hasChildren("penis")) {
                    loader.forEachTextContent("modUrethra", modifier -> {
                        importedPenis.orificeUrethra.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                    });
                }
            }

            importedPenis.testicle.internal = internal;
            importedPenis.testicle.cumStored = storedCum;
            importedPenis.testicle.cumRegeneration = cumRegeneration;
            importedPenis.testicle.setCumExpulsion(null, cumExpulsion);
            if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
                importedPenis.testicle.cumRegeneration = FluidRegeneration.CUM_REGEN_DEFAULT;
            }

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Testicles: "
                    + "<br/>cumProduction: " + importedPenis.testicle.getCumStorage()
                    + "<br/>numberOfTesticles: " + importedPenis.testicle.getTesticleCount()
                    + "<br/>testicleSize: " + importedPenis.testicle.getTesticleSize()
                    + "<br/>internal: " + importedPenis.testicle.internal);

            loader.goToParent(); // go up before fluid load, it could be before testies or penis
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Cum:");
            importedPenis.testicle.cum = FluidLoaders.loadCum(loader, "cum", importedPenis.getType().getTesticleType().getFluidType());
        } else {
            importedPenis = null;
        }

        return importedPenis;
    }

    private static Leg loadLeg(XMLLoader loader, String version, StringBuilder log) {
        Leg importedLeg = null;
        String importedType;
        if (!(importedType = loader.getNextElementAttributeAsStr("leg", "type")).isEmpty()) {
            AbstractLegType legType = LegType.getLegTypeFromId(importedType);

            LegConfiguration configuration = LegConfiguration.valueOf(loader.getCurrentElementAttributeAsStr("configuration", LegConfiguration.BIPEDAL.toString()));


            FootStructure footStructure = FootStructure.valueOf(loader.getCurrentElementAttributeAsStr("footStructure",
                    legType.getDefaultFootStructure(configuration).toString()));

            importedLeg = new Leg(legType, configuration);
            importedLeg.setFootStructure(null, footStructure);
            float tailLength = loader.getCurrentElementAttributeAsFloat("tailLength", -1);
            importedLeg.setLengthAsPercentageOfHeight(null, tailLength);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Leg: "
                    + "<br/>type: " + importedLeg.getType());
        }

        loader.goToParent();
        return importedLeg;
    }

    private static Horn loadHorn(XMLLoader loader, String version, StringBuilder log) {
        Horn importedHorn = new Horn(HornType.NONE, 0);
        String hornType;
        if (!(hornType = loader.getNextElementAttributeAsStr("horn", "type")).isEmpty()) {
            int rows = loader.getCurrentElementAttributeAsInt("rows");
            int length = loader.getCurrentElementAttributeAsInt("length", HornLength.TWO_LONG.getMedianValue());
            int hornsPerRow = loader.getCurrentElementAttributeAsInt("hornsPerRow", 2);
            /*if (!hornType.equals("NONE")) {
                length = HornLength.TWO_LONG.getMedianValue();
            }*/

            if (hornType.equals("DEMON")) {
                importedHorn = new Horn(HornType.SWEPT_BACK, length);
            } else if (hornType.equals("BOVINE")) {
                importedHorn = new Horn(HornType.CURVED, length);
            } else {
                importedHorn = new Horn(HornType.getHornTypeFromId(hornType), length);
            }
            importedHorn.rows = rows;
            importedHorn.hornsPerRow = hornsPerRow;
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Horn: "
                    + "<br/>type: " + importedHorn.getType()
                    + "<br/>length: " + length
                    + "<br/>rows: " + importedHorn.getHornRows()
                    + "<br/>horns per row: " + importedHorn.getHornsPerRow());
        }
        loader.goToParent();
        return importedHorn;
    }

    private static Hair loadHair(XMLLoader loader, String version, StringBuilder log) {
        Hair importedHair = null;
        String hairTypeFromSave;
        if (!(hairTypeFromSave = loader.getNextElementAttributeAsStr("hair", "type")).isEmpty()) {

            Map<String, String> hairTypeConverterMap = new HashMap<>();
            hairTypeConverterMap.put("HAIR_HUMAN", "HUMAN");
            hairTypeConverterMap.put("HAIR_ANGEL", "ANGEL");
            hairTypeConverterMap.put("HAIR_DEMON", "DEMON_COMMON");
            hairTypeConverterMap.put("HAIR_CANINE_FUR", "DOG_MORPH");
            hairTypeConverterMap.put("HAIR_LYCAN_FUR", "LYCAN");
            hairTypeConverterMap.put("HAIR_FELINE_FUR", "CAT_MORPH");
            hairTypeConverterMap.put("HAIR_HORSE_HAIR", "HORSE_MORPH");
            hairTypeConverterMap.put("HAIR_SQUIRREL_FUR", "SQUIRREL_MORPH");
            hairTypeConverterMap.put("SLIME", "SLIME");
            hairTypeConverterMap.put("HAIR_HARPY", "HARPY");
            if (hairTypeConverterMap.containsKey(hairTypeFromSave)) {
                hairTypeFromSave = hairTypeConverterMap.get(hairTypeFromSave);
            }

            importedHair = new Hair(HairType.getHairTypeFromId(hairTypeFromSave),
                    loader.getCurrentElementAttributeAsInt("length"),
                    HairStyle.valueOf(loader.getCurrentElementAttributeAsStr("hairStyle")),
                    null);

            importedHair.neckFluff = loader.getCurrentElementAttributeAsBoolean("neckFluff");
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Hair: "
                    + "<br/>type: " + importedHair.getType()
                    + "<br/>length: " + importedHair.getLength()
                    + "<br/>hairStyle: " + importedHair.getStyle());
        }

        loader.goToParent();
        return importedHair;
    }

    private static boolean oldPantherReplacement = false;
    private static Face loadFace(XMLLoader loader, String version, StringBuilder log) {
        Face importedFace;
        String faceType;
        if (!(faceType = loader.getNextElementAttributeAsStr("face", "type")).isEmpty()) {
            oldPantherReplacement = faceType.equals("CAT_MORPH_PANTHER");
            boolean piercedNose = loader.getCurrentElementAttributeAsBoolean("piercedNose");
            BodyHair facialHair = BodyHair.valueOf(loader.getCurrentElementAttributeAsStr("facialHair", BodyHair.ZERO_NONE.toString()));
            int depth = loader.getNextElementAttributeAsInt("mouth", "depth", OrificeDepth.TWO_AVERAGE.getValue());
            int elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            int plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            int lipSize = loader.getCurrentElementAttributeAsInt("lipSize");
            int wetness = loader.getCurrentElementAttributeAsInt("wetness");
            float capacity = handleCapacityLoading(loader.getCurrentElementAttributeAsFloat("capacity"));
            float stretchedCapacity = handleCapacityLoading(loader.getCurrentElementAttributeAsFloat("stretchedCapacity"));
            boolean virgin = loader.getCurrentElementAttributeAsBoolean("virgin");
            boolean piercedLip = loader.getCurrentElementAttributeAsBoolean("piercedLip");
            importedFace = new Face(FaceType.getFaceTypeFromId(faceType), lipSize);
            importedFace.piercedNose = piercedNose;
            importedFace.facialHair = facialHair;
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Face: "
                    + "<br/>type: " + importedFace.getType()
                    + "<br/>piercedNose: " + importedFace.isPiercedNose()
                    + "<br/>facial hair: " + importedFace.getFacialHair()

                    + "<br/><br/>Mouth: ");

            importedFace.mouth.orificeMouth.depth =  depth;
            importedFace.mouth.orificeMouth.elasticity =  elasticity;
            importedFace.mouth.orificeMouth.plasticity =  plasticity;
            importedFace.mouth.lipSize =  lipSize;
            importedFace.mouth.orificeMouth.wetness =  wetness;
            importedFace.mouth.orificeMouth.capacity =  capacity;
            importedFace.mouth.orificeMouth.stretchedCapacity =  stretchedCapacity;
            importedFace.mouth.orificeMouth.virgin =  virgin;
            importedFace.mouth.piercedLip =  piercedLip;

            Main.game.getCharacterUtils().appendToImportLog(log,
                    "<br/>depth: " + importedFace.mouth.orificeMouth.getDepth(null)
                            + "<br/>elasticity: " + importedFace.mouth.orificeMouth.getElasticity()
                            + "<br/>plasticity: " + importedFace.mouth.orificeMouth.getPlasticity()
                            + "<br/>capacity: " + importedFace.mouth.orificeMouth.getCapacity()
                            + "<br/>stretchedCapacity: " + importedFace.mouth.orificeMouth.getStretchedCapacity()
                            + "<br/>virgin: " + importedFace.mouth.orificeMouth.isVirgin()
                            + "<br/>piercedLip: " + importedFace.mouth.isPiercedLip()
                            + "<br/>lipSize: " + importedFace.mouth.getLipSize()
                            + "<br/>Modifiers: ");

            Collection<OrificeModifier> mouthOrificeModifiers = importedFace.mouth.orificeMouth.orificeModifiers;
            mouthOrificeModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("mouthModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedFace.mouth.orificeMouth.setDepth(null, importedFace.mouth.orificeMouth.depth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "mouthModifiers", mouthOrificeModifiers);
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedFace.mouth.orificeMouth.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }

            importedFace.tongue.tongueLength = loader.getNextElementAttributeAsInt("tongue","tongueLength");
            importedFace.tongue.pierced =  loader.getCurrentElementAttributeAsBoolean("piercedTongue");

            Main.game.getCharacterUtils().appendToImportLog(log,
                    "<br/><br/>Tongue: "
                            + "<br/>piercedTongue: " + importedFace.tongue.isPierced()
                            + "<br/>tongueLength: " + importedFace.tongue.getTongueLength()
                            + "<br/>Modifiers: ");

            Collection<TongueModifier> tongueModifiers = importedFace.tongue.tongueModifiers;
            tongueModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.nodeExists("tongueModifiers")) {
                    handleLoadingOfModifiers(loader, TongueModifier.values(), log, "tongueModifiers", tongueModifiers);
                }
                if (version.isEmpty()) { // Version tracking was added in v0.3.7, so if there is no version, it is before then. Add new default modifiers added in v0.3.7:
                    for (TongueModifier mod : importedFace.tongue.getType().getDefaultRacialTongueModifiers()) {
                        if (mod == TongueModifier.FLAT || mod == TongueModifier.WIDE || mod == TongueModifier.STRONG) {
                            importedFace.tongue.tongueModifiers.add(mod);
                        }
                    }
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedFace.tongue.tongueModifiers.add(TongueModifier.valueOf(modifier));
                });
            }
        } else {
            importedFace = null;
        }
        loader.goToParent();
        return importedFace;
    }

    private static Eye loadEye(XMLLoader loader, String version, StringBuilder log) {
        Eye importedEye = null;
        String eyeTypeFromSave;
        if (!(eyeTypeFromSave = loader.getNextElementAttributeAsStr("eye", "type")).isEmpty()) {

            Map<String, String> eyeTypeConverterMap = new HashMap<>();
            eyeTypeConverterMap.put("EYE_HUMAN", "HUMAN");
            eyeTypeConverterMap.put("EYE_ANGEL", "ANGEL");
            eyeTypeConverterMap.put("EYE_DEMON_COMMON", "DEMON_COMMON");
            eyeTypeConverterMap.put("EYE_DOG_MORPH", "DOG_MORPH");
            eyeTypeConverterMap.put("EYE_LYCAN", "LYCAN");
            eyeTypeConverterMap.put("EYE_FELINE", "CAT_MORPH");
            eyeTypeConverterMap.put("EYE_SQUIRREL", "SQUIRREL_MORPH");
            eyeTypeConverterMap.put("EYE_HORSE_MORPH", "HORSE_MORPH");
            eyeTypeConverterMap.put("EYE_HARPY", "HARPY");
            eyeTypeConverterMap.put("EYE_SLIME", "SLIME");
            if (eyeTypeConverterMap.containsKey(eyeTypeFromSave)) {
                eyeTypeFromSave = eyeTypeConverterMap.get(eyeTypeFromSave);
            }

            importedEye = new Eye(EyeType.getEyeTypeFromId(eyeTypeFromSave));

            if (Main.isVersionOlderThan(version, "0.3.9") && importedSubspeciesOverride == Subspecies.HALF_DEMON) { // Fix to taurs spawning with no demon parts at all in v0.3.8.9
                importedEye = new Eye(EyeType.DEMON_COMMON);
            }

            importedEye.eyePairs = loader.getCurrentElementAttributeAsInt("eyePairs");
            importedEye.irisShape = EyeShape.valueOf(loader.getCurrentElementAttributeAsStr("irisShape"));
            importedEye.pupilShape = EyeShape.valueOf(loader.getCurrentElementAttributeAsStr("pupilShape"));

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Eye:"
                    + "<br/>type: " + importedEye.getType()
                    + "<br/>pairs: " + importedEye.getEyePairs()
                    + "<br/>iris shape: " + importedEye.getIrisShape()
                    + "<br/>pupil shape: " + importedEye.getPupilShape());
        }
        loader.goToParent();
        return importedEye;
    }

    private static Ear loadEar(XMLLoader loader, String version, StringBuilder log) {
        Ear importedEar = null;
        String earType;
        if (!(earType = loader.getNextElementAttributeAsStr("ear", "type")).isEmpty()) {
            importedEar = new Ear(EarType.getEarTypeFromId(earType));

            importedEar.pierced = loader.getCurrentElementAttributeAsBoolean("pierced");
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ear:"
                    + "<br/>type: " + importedEar.getType()
                    + "<br/>pierced: " + importedEar.isPierced());
        }
        loader.goToParent();
        return importedEar;
    }

    private static Ass loadAss(XMLLoader loader, String version, StringBuilder log) {
        Ass importedAss;
        String assType, assHair;
        if (!(assType = loader.getNextElementAttributeAsStr("ass", "type")).isEmpty()) {
            int assSize = loader.getCurrentElementAttributeAsInt("assSize");
            int hipSize = loader.getCurrentElementAttributeAsInt("hipSize");

            //Why is this not stored on ass, instead of anus?
            if ((assHair = loader.getNextElementAttributeAsStr("anus", "assHair", BodyHair.ZERO_NONE.toString())).isEmpty()) {
                Logger.logTest("Anus is missing, we're going to be in for a shitty situation.");
            }
            boolean bleached = loader.getCurrentElementAttributeAsBoolean("bleached");
            float capacity = loader.getCurrentElementAttributeAsFloat("capacity");//Maybe set default values, otherwise it returns 0 as default
            int depth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            int elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            int plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            float stretchedCapacity = loader.getCurrentElementAttributeAsFloat("stretchedCapacity");
            boolean virgin = loader.getCurrentElementAttributeAsBoolean("virgin");
            int wetness = loader.getCurrentElementAttributeAsInt("wetness");

            importedAss = new Ass(AssType.getAssTypeFromId(assType),
                    assSize, hipSize, wetness,
                    handleCapacityLoading(capacity),
                    depth, elasticity, plasticity, virgin);
            importedAss.anus.orificeAnus.stretchedCapacity = handleCapacityLoading(stretchedCapacity);
            importedAss.anus.bleached = bleached;
            importedAss.anus.assHair = BodyHair.valueOf(assHair);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Ass:"
                    + "<br/>type: " + importedAss.getType()
                    + "<br/>assSize: " + importedAss.getAssSize()
                    + "<br/>hipSize: " + importedAss.getHipSize());
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Anus:"
                    + "<br/>wetness: " + importedAss.anus.orificeAnus.wetness
                    + "<br/>depth: " + importedAss.anus.orificeAnus.depth
                    + "<br/>elasticity: " + importedAss.anus.orificeAnus.elasticity
                    + "<br/>elasticity: " + importedAss.anus.orificeAnus.plasticity
                    + "<br/>capacity: " + importedAss.anus.orificeAnus.capacity
                    + "<br/>stretchedCapacity: " + importedAss.anus.orificeAnus.stretchedCapacity
                    + "<br/>virgin: " + importedAss.anus.orificeAnus.virgin
                    + "<br/>bleached: " + importedAss.anus.bleached
                    + "<br/>assHair: " + importedAss.anus.assHair
                    + "<br/>Modifiers:");

            Collection<OrificeModifier> anusModifiers = importedAss.anus.orificeAnus.getOrificeModifiers();
            anusModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("anusModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedAss.anus.orificeAnus.setDepth(null, depth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "anusModifiers", anusModifiers);
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedAss.anus.orificeAnus.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }
        } else {
            importedAss = null;
        }
        loader.goToParent();
        return importedAss;
    }

    private static Arm loadArm(XMLLoader loader, String version, StringBuilder log) {
        Arm importedArm = null;
        String armType;
        if (!(armType = loader.getNextElementAttributeAsStr("arm", "type")).isEmpty()) {
            int rows = loader.getCurrentElementAttributeAsInt("rows");
            importedArm = new Arm(ArmType.getArmTypeFromId(armType), rows);
            String hair = loader.getCurrentElementAttributeAsStr("underarmHair", BodyHair.ZERO_NONE.toString());
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Arm:" + "<br/>type: " + importedArm.getType());
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: " + importedArm.getArmRows());
            importedArm.underarmHair = BodyHair.valueOf(hair);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>underarm hair: " + importedArm.getUnderarmHair());

        }
        loader.goToParent();
        return importedArm;
    }

    private static Antenna loadAntennae(XMLLoader loader, String version, StringBuilder log) {
        Antenna importedAntenna = null;
        String antennaeType;
        if (!(antennaeType = loader.getNextElementAttributeAsStr("antennae", "type")).isEmpty()) {
            importedAntenna = new Antenna(AntennaType.getAntennaTypeFromId(antennaeType), 0);
            int rows = loader.getCurrentElementAttributeAsInt("rows");
            int length = loader.getCurrentElementAttributeAsInt("length");
            int antennaePerRow = loader.getCurrentElementAttributeAsInt("antennaePerRow");
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Antennae:" + "<br/>type: " + importedAntenna.getType());
            importedAntenna.setAntennaRows(null, rows);
            importedAntenna.length = length;
            importedAntenna.antennaePerRow = antennaePerRow;
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>rows: " + importedAntenna.getAntennaRows());
        }
        loader.goToParent();
        return importedAntenna;
    }

    private static Breast loadBreast(XMLLoader loader, String version, StringBuilder log) {
        Breast importedBreast;
        String breastType, nippleShape;
        if (!(breastType = loader.getNextElementAttributeAsStr("breasts", "type")).isEmpty()) {
            int milkRegeneration = loader.getCurrentElementAttributeAsInt("milkRegeneration");
            int milkStorage = loader.getCurrentElementAttributeAsInt("milkStorage",
                    loader.getCurrentElementAttributeAsInt("lactation"));
            int nippleCountPerBreast = loader.getCurrentElementAttributeAsInt("nippleCountPerBreast");
            int rows = loader.getCurrentElementAttributeAsInt("rows");
            int size = loader.getCurrentElementAttributeAsInt("size");
            float storedMilk = loader.getCurrentElementAttributeAsFloat("storedMilk");
            String breastShape = loader.getCurrentElementAttributeAsStr("shape", BreastShape.ROUND.toString());

            if ((nippleShape = loader.getNextElementAttributeAsStr("nipples", "nippleShape", NippleShape.NORMAL.toString())).isEmpty()) {
                Logger.logTest("Loading Breasts without nipples, this might go tits up.");
            }
            String areolaeShape = loader.getCurrentElementAttributeAsStr("areolaeShape", AreolaeShape.NORMAL.toString());
            int areolaeSize = loader.getCurrentElementAttributeAsInt("areolaeSize");
            int depth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            int elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            int plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            int nippleSize = loader.getCurrentElementAttributeAsInt("nippleSize");
            boolean pierced = loader.getCurrentElementAttributeAsBoolean("pierced");
            boolean virgin = loader.getCurrentElementAttributeAsBoolean("virgin");
            float capacity = loader.getCurrentElementAttributeAsFloat("capacity");
            float stretchedCapacity = loader.getCurrentElementAttributeAsFloat("stretchedCapacity");

            importedBreast = new Breast(BreastType.getBreastTypeFromId(breastType),
                    BreastShape.valueOf(breastShape), size, milkStorage,
                    rows, nippleSize, NippleShape.valueOf(nippleShape),
                    areolaeSize, AreolaeShape.valueOf(areolaeShape),
                    nippleCountPerBreast, handleCapacityLoading(capacity),
                    depth, elasticity, plasticity, virgin);
            importedBreast.milkStored = storedMilk;
            importedBreast.milkRegeneration = milkRegeneration;
            if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.1.9")) { // Change from percentage-based to set value:
                importedBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
            }
            importedBreast.nipples.crotchNipples = false;
            importedBreast.nipples.orificeNipples.stretchedCapacity = handleCapacityLoading(stretchedCapacity);
            importedBreast.nipples.pierced = pierced;
//		    importedBreast.nipples.areolaeShape = AreolaeShape.valueOf(areolaeShape);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Breasts:"
                    + "<br/>type: " + importedBreast.getType()
                    + "<br/>size: " + importedBreast.getSize()
                    + "<br/>rows: " + importedBreast.getRows()
                    + "<br/>lactation: " + importedBreast.getRawMilkStorageValue()
                    + "<br/>nippleCountPer: " + importedBreast.getNippleCountPerBreast()

                    + "<br/><br/>Nipples:"
                    + "<br/>depth: " + importedBreast.nipples.orificeNipples.getDepth(null)
                    + "<br/>elasticity: " + importedBreast.nipples.orificeNipples.getElasticity()
                    + "<br/>plasticity: " + importedBreast.nipples.orificeNipples.getPlasticity()
                    + "<br/>capacity: " + importedBreast.nipples.orificeNipples.getRawCapacityValue()
                    + "<br/>stretchedCapacity: " + importedBreast.nipples.orificeNipples.getStretchedCapacity()
                    + "<br/>virgin: " + importedBreast.nipples.orificeNipples.isVirgin()
                    + "<br/>pierced: " + importedBreast.nipples.isPierced()
                    + "<br/>nippleSize: " + importedBreast.nipples.getNippleSize()
                    + "<br/>nippleShape: " + importedBreast.nipples.getNippleShape()
                    + "<br/>areolaeSize: " + importedBreast.nipples.getAreolaeSize()
                    + "<br/>areolaeShape: " + importedBreast.nipples.getAreolaeShape()
                    + "<br/>Modifiers:");
            Collection<OrificeModifier> nippleOrificeModifiers = importedBreast.nipples.orificeNipples.orificeModifiers;
            nippleOrificeModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("nippleModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedBreast.nipples.orificeNipples.setDepth(null, depth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "nippleModifiers", nippleOrificeModifiers);
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }
            loader.goToParent();

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");
            if (Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
                importedBreast.milk.type = importedBreast.getType().getFluidType();
            } else {
                importedBreast.milk = FluidLoaders.loadMilk(loader, "milk", importedBreast.getType().getFluidType(), false);
            }
        } else {
            importedBreast = null;
        }
        return importedBreast;
    }

    private static BreastCrotch loadCrotchBreast(XMLLoader loader, String version, StringBuilder log, Body body) {
        BreastCrotch importedCrotchBreast;
        String bcType, bcNippleShape;
        if (!(bcType = loader.getNextElementAttributeAsStr("breastsCrotch", "type")).isEmpty()) {
            int milkRegeneration = loader.getCurrentElementAttributeAsInt("milkRegeneration");
            int milkStorage = loader.getCurrentElementAttributeAsInt("milkStorage",
                    loader.getCurrentElementAttributeAsInt("lactation"));
            int nippleCountPerBreast = loader.getCurrentElementAttributeAsInt("nippleCountPerBreast");
            int rows = loader.getCurrentElementAttributeAsInt("rows");
            int size = loader.getCurrentElementAttributeAsInt("size");
            float storedMilk = loader.getCurrentElementAttributeAsFloat("storedMilk");
            String breastShape = loader.getCurrentElementAttributeAsStr("shape", BreastShape.ROUND.toString());

            if ((bcNippleShape = loader.getNextElementAttributeAsStr("nipplesCrotch", "nippleShape", NippleShape.NORMAL.toString())).isEmpty()) {
                Logger.logTest("Loading Breasts without nipples, this might go tits up.");
            }
            String areolaeShape = loader.getCurrentElementAttributeAsStr("areolaeShape", AreolaeShape.NORMAL.toString());
            int areolaeSize = loader.getCurrentElementAttributeAsInt("areolaeSize");
            int depth = loader.getCurrentElementAttributeAsInt("depth", OrificeDepth.TWO_AVERAGE.getValue());
            int elasticity = loader.getCurrentElementAttributeAsInt("elasticity");
            int plasticity = loader.getCurrentElementAttributeAsInt("plasticity");
            int nippleSize = loader.getCurrentElementAttributeAsInt("nippleSize");
            boolean pierced = loader.getCurrentElementAttributeAsBoolean("pierced");
            boolean virgin = loader.getCurrentElementAttributeAsBoolean("virgin");
            float capacity = loader.getCurrentElementAttributeAsFloat("capacity");
            float stretchedCapacity = loader.getCurrentElementAttributeAsFloat("stretchedCapacity");

            AbstractBreastType crotchBoobType = BreastType.getBreastTypeFromId(bcType);
            if (oldPantherReplacement) {
                if (crotchBoobType.getRace() == Race.CAT_MORPH) {
                    crotchBoobType = BreastType.getBreastTypes(Race.getRaceFromId("innoxia_panther")).get(0);
                }
            }
            if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6") && body.getLegConfiguration().isBipedalPositionedCrotchBoobs()) { // Reset crotch-boob type as I accidentally applied crotch-boobs to demons
                if (body.isFeminine()) {
                    crotchBoobType = RacialBody.valueOfRace(body.getRace()).getBreastCrotchType();
                } else {
                    crotchBoobType = BreastType.NONE;
                }
            }
            importedCrotchBreast = new BreastCrotch(crotchBoobType, BreastShape.valueOf(breastShape),
                    size, milkStorage, rows, nippleSize,
                    NippleShape.valueOf(bcNippleShape), areolaeSize, AreolaeShape.valueOf(areolaeShape),
                    nippleCountPerBreast, capacity, depth, elasticity, plasticity, virgin);
            importedCrotchBreast.milkStored = storedMilk;
            importedCrotchBreast.milkRegeneration = milkRegeneration;
            if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.2")) { // Change from percentage-based to set value:
                importedCrotchBreast.milkRegeneration = FluidRegeneration.ONE_AVERAGE.getMedianRegenerationValuePerDay();
            }
            importedCrotchBreast.nipples.crotchNipples = true;
            importedCrotchBreast.nipples.orificeNipples.stretchedCapacity = stretchedCapacity;
            importedCrotchBreast.nipples.pierced = pierced;
//			importedCrotchBreast.nipples.areolaeShape = AreolaeShape.valueOf(areolaeShape);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Body: Crotch Breasts:"
                    + "<br/>type: " + importedCrotchBreast.getType()
                    + "<br/>size: " + importedCrotchBreast.getSize()
                    + "<br/>rows: " + importedCrotchBreast.getRows()
                    + "<br/>lactation: " + importedCrotchBreast.getRawMilkStorageValue()
                    + "<br/>nippleCountPer: " + importedCrotchBreast.getNippleCountPerBreast()

                    + "<br/><br/>Nipples:"
                    + "<br/>depth: " + importedCrotchBreast.nipples.orificeNipples.getDepth(null)
                    + "<br/>elasticity: " + importedCrotchBreast.nipples.orificeNipples.getElasticity()
                    + "<br/>plasticity: " + importedCrotchBreast.nipples.orificeNipples.getPlasticity()
                    + "<br/>capacity: " + importedCrotchBreast.nipples.orificeNipples.getRawCapacityValue()
                    + "<br/>stretchedCapacity: " + importedCrotchBreast.nipples.orificeNipples.getStretchedCapacity()
                    + "<br/>virgin: " + importedCrotchBreast.nipples.orificeNipples.isVirgin()
                    + "<br/>pierced: " + importedCrotchBreast.nipples.isPierced()
                    + "<br/>nippleSize: " + importedCrotchBreast.nipples.getNippleSize()
                    + "<br/>nippleShape: " + importedCrotchBreast.nipples.getNippleShape()
                    + "<br/>areolaeSize: " + importedCrotchBreast.nipples.getAreolaeSize()
                    + "<br/>areolaeShape: " + importedCrotchBreast.nipples.getAreolaeShape()
                    + "<br/>Modifiers:");

            Collection<OrificeModifier> crotchNippleOrificeModifiers = importedCrotchBreast.nipples.orificeNipples.orificeModifiers;
            crotchNippleOrificeModifiers.clear();
            if (Main.isVersionOlderThan(version, "0.4.9.7")) {
                if (loader.moveToNode("nippleModifiers")) {
                    if (!loader.getCurrentElementAttributeAsStr("EXTRA_DEEP").isEmpty()) {
                        importedCrotchBreast.nipples.orificeNipples.setDepth(null, depth + 2);
                    }
                    loader.goToParent();// due to how I wrote the below function, must go back up
                    handleLoadingOfModifiers(loader, OrificeModifier.values(), log, "nippleModifiers", crotchNippleOrificeModifiers);
                }
            } else {
                loader.forEachTextContent("mod", modifier -> {
                    importedCrotchBreast.nipples.orificeNipples.addOrificeModifier(null, OrificeModifier.valueOf(modifier));
                });
            }

            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Milk:");

            loader.goToParent(); // go up to <body> before loading fluids
            if (!loader.nodeExists("milkCrotch")) {
                importedCrotchBreast.milk = FluidLoaders.loadMilk(loader, "milk", importedCrotchBreast.getType().getFluidType(), true);
            } else {
                importedCrotchBreast.milk = FluidLoaders.loadMilk(loader, "milkCrotch", importedCrotchBreast.getType().getFluidType(), true);
            }
            if (Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.5.1")) {
                importedCrotchBreast.milk.type = importedCrotchBreast.getType().getFluidType();
            }
        } else {
            importedCrotchBreast = null;
        }
        return importedCrotchBreast;
    }
}