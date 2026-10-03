package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.MiscStuff;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.*;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.*;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.utils.colours.PresetColour;

import java.io.File;
import java.util.*;
import com.lilithsthrone.game.Properties;

public class LoadProperties {

    public static void loadPropertiesFromXML(Properties properties){
        if (new File("data/properties.xml").exists()) {
            File propertiesXML = new File("data/properties.xml");

            XMLLoader loader = XMLLoader.load(propertiesXML);
            // Previous save information:
            loader.moveToNode("previousSave");
            properties.lastSaveLocation = loader.getElementAttributeAsStr("location", "value");
            properties.nameColour = loader.getElementAttributeAsStr("nameColour", "value");
            properties.name = loader.getElementAttributeAsStr("name", "value");
            properties.raceColour = loader.getElementAttributeAsStr("raceColour", "value");
            properties.race = loader.getElementAttributeAsStr("race", "value");
            properties.quest = loader.getElementAttributeAsStr("quest", "value");
            properties.level = loader.getElementAttributeAsInt("level", "value");
            properties.money = loader.getElementAttributeAsInt("money", "value");
            properties.arcaneEssences = loader.getElementAttributeAsInt("arcaneEssences", "value");
            properties.versionNumber = loader.getElementAttributeAsStr("versionNumber", "value");
            properties.lastQuickSaveName = loader.getElementAttributeAsStr("lastQuickSaveName", "value");

            if (loader.moveToIfHasChildren("propertyValues")) {
                properties.values.clear();
                loader.forEachAttributeOfElement("propertyValue", "value", propVal -> {
                    propVal.replace("legContent", "footContent");//old val name
                    properties.values.add(PropertyValue.valueOf(propVal));
                });
                loader.goToParent();
            }
            loader.moveToNode("settings");
            if (properties.values.isEmpty()) {// Old Way "settings" Support
                properties.setValue(PropertyValue.lightTheme, loader.getElementAttributeAsBoolean("lightTheme" , "value"));
                properties.setValue(PropertyValue.furryTailPenetrationContent, loader.getElementAttributeAsBoolean("furryTailPenetrationContent" , "value"));
                properties.setValue(PropertyValue.nonConContent, loader.getElementAttributeAsBoolean("nonConContent" , "value"));
                properties.setValue(PropertyValue.incestContent, loader.getElementAttributeAsBoolean("incestContent" , "value"));
                properties.setValue(PropertyValue.inflationContent, loader.getElementAttributeAsBoolean("inflationContent" , "value"));
                properties.setValue(PropertyValue.facialHairContent, loader.getElementAttributeAsBoolean("facialHairContent" , "value"));
                properties.setValue(PropertyValue.pubicHairContent, loader.getElementAttributeAsBoolean("pubicHairContent" , "value"));
                properties.setValue(PropertyValue.bodyHairContent, loader.getElementAttributeAsBoolean("bodyHairContent" , "value"));
                properties.setValue(PropertyValue.feminineBeardsContent, loader.getElementAttributeAsBoolean("feminineBeardsContent" , "value"));
                properties.setValue(PropertyValue.lactationContent, loader.getElementAttributeAsBoolean("lactationContent" , "value"));
                properties.setValue(PropertyValue.cumRegenerationContent, loader.getElementAttributeAsBoolean("cumRegenerationContent" , "value"));
                properties.setValue(PropertyValue.urethralContent, loader.getElementAttributeAsBoolean("urethralContent" , "value"));
                properties.setValue(PropertyValue.newWeaponDiscovered, loader.getElementAttributeAsBoolean("newWeaponDiscovered" , "value"));
                properties.setValue(PropertyValue.newClothingDiscovered, loader.getElementAttributeAsBoolean("newClothingDiscovered" , "value"));
                properties.setValue(PropertyValue.newItemDiscovered, loader.getElementAttributeAsBoolean("newItemDiscovered" , "value"));
                properties.setValue(PropertyValue.newRaceDiscovered, loader.getElementAttributeAsBoolean("newRaceDiscovered" , "value"));
                properties.setValue(PropertyValue.overwriteWarning, loader.getElementAttributeAsBoolean("overwriteWarning" , "value"));
                properties.setValue(PropertyValue.fadeInText, loader.getElementAttributeAsBoolean("fadeInText" , "value"));
                properties.setValue(PropertyValue.calendarDisplay, loader.getElementAttributeAsBoolean("calendarDisplay" , "value"));
                properties.setValue(PropertyValue.twentyFourHourTime, loader.getElementAttributeAsBoolean("twentyFourHourTime" , "value"));
            }

            // Settings:
            properties.fontSize = loader.getElementAttributeAsInt("fontSize", "value");
            properties.badEndTitle = loader.getElementAttributeAsStr("badEndTitle", "value");

            if (!loader.getElementAttributeAsStr("difficultyLevel", "value").isEmpty()) {
                properties.difficultyLevel = DifficultyLevel.valueOf(loader.getElementAttributeAsStr("difficultyLevel", "value"));
            }

            properties.AIblunderRate = loader.getElementAttributeAsFloat("AIblunderRate", "value");

            if (!loader.getElementAttributeAsStr("androgynousIdentification", "value").isEmpty()) {
                properties.androgynousIdentification = AndrogynousIdentification.valueOf(loader.getElementAttributeAsStr("androgynousIdentification", "value"));
            }

            properties.offspringGenderLevel = loader.getElementAttributeAsInt("offspringGenderLevel");
            if (loader.getElementAttributeAsInt("humanEncountersLevel") > 0) {
                properties.humanSpawnRate = loader.getElementAttributeAsInt("humanEncountersLevel");
                if (properties.humanSpawnRate == 1) {
                    properties.humanSpawnRate = 5;
                } else if (properties.humanSpawnRate == 2) {
                    properties.humanSpawnRate = 25;
                } else if (properties.humanSpawnRate == 3) {
                    properties.humanSpawnRate = 50;
                } else if (properties.humanSpawnRate == 4) {
                    properties.humanSpawnRate = 75;
                }
            } else {
                properties.humanSpawnRate = loader.getElementAttributeAsInt("humanSpawnRate", 5);
            }
            properties.taurSpawnRate = loader.getElementAttributeAsInt("taurSpawnRate", 5);
            properties.halfDemonSpawnRate = loader.getElementAttributeAsInt("halfDemonSpawnRate", 5);
            properties.multiBreasts = loader.getElementAttributeAsInt("multiBreasts", 1);
            properties.udders = loader.getElementAttributeAsInt("udders", 1);
            properties.hairGrowth = loader.getElementAttributeAsInt("hairGrowth", 1);
            properties.autoSaveFrequency = loader.getElementAttributeAsInt("autoSaveFrequency");
            properties.bypassSexActions = loader.getElementAttributeAsInt("bypassSexActions", 2);
            properties.fullExposureDescriptions = loader.getElementAttributeAsInt("fullExposureDescriptions", 2);

            properties.clothingFemininityLevel = loader.getElementAttributeAsInt("clothingFemininityLevel");
            properties.pregnancyDuration = loader.getElementAttributeAsInt("pregnancyDuration");
            properties.forcedTFPercentage = loader.getElementAttributeAsInt("forcedTFPercentage");
            properties.forcedFetishPercentage = loader.getElementAttributeAsInt("forcedFetishPercentage");

            // Randomized percentage of body pref race.
            properties.randomRacePercentage = loader.getElementAttributeAsFloat("randomRacePercentage");

            ModProps.loadProps(loader);
            MiscStuff.updateValues();

            // Forced TF preference:
            if (!loader.getElementAttributeAsStr("forcedTFPreference").isEmpty()) {
                properties.forcedTFPreference = FurryPreference.valueOf(loader.getElementAttributeAsStr("forcedTFPreference"));
            }
            if (!loader.getElementAttributeAsStr("forcedTFTendency").isEmpty()) {
                properties.forcedTFTendency = ForcedTFTendency.valueOf(loader.getElementAttributeAsStr("forcedTFTendency"));
            }
            if (!loader.getElementAttributeAsStr("forcedFetishTendency").isEmpty()) {
                properties.forcedFetishTendency = ForcedFetishTendency.valueOf(loader.getElementAttributeAsStr("forcedFetishTendency"));
            }

            properties.pregnancyBreastGrowthVariance = loader.getElementAttributeAsInt("pregnancyBreastGrowthVariance");
            properties.pregnancyBreastGrowth = loader.getElementAttributeAsInt("pregnancyBreastGrowth");
            properties.pregnancyUdderGrowth = loader.getElementAttributeAsInt("pregnancyUdderGrowth");
            properties.pregnancyBreastGrowthLimit = loader.getElementAttributeAsInt("pregnancyBreastGrowthLimit");
            properties.pregnancyUdderGrowthLimit = loader.getElementAttributeAsInt("pregnancyUdderGrowthLimit");
            properties.pregnancyLactationIncreaseVariance = loader.getElementAttributeAsInt("pregnancyLactationIncreaseVariance");
            properties.pregnancyLactationIncrease = loader.getElementAttributeAsInt("pregnancyLactationIncrease");
            properties.pregnancyUdderLactationIncrease = loader.getElementAttributeAsInt("pregnancyUdderLactationIncrease");
            properties.pregnancyLactationLimit = loader.getElementAttributeAsInt("pregnancyLactationLimit");
            properties.pregnancyUdderLactationLimit = loader.getElementAttributeAsInt("pregnancyUdderLactationLimit");
            properties.breastSizePreference = loader.getElementAttributeAsInt("breastSizePreference");
            properties.udderSizePreference = loader.getElementAttributeAsInt("udderSizePreference");
            properties.penisSizePreference = loader.getElementAttributeAsInt("penisSizePreference");
            properties.trapPenisSizePreference = loader.getElementAttributeAsInt("trapPenisSizePreference");

            // Artist priority:
            properties.artistPriority = new ArrayList<>();
            List<Artist> artists = new ArrayList<>(Artwork.allArtists);
            artists.remove(Artwork.customArtist);
            Collections.sort(artists, (e1, e2) -> e2.getArtworkCount() - e1.getArtworkCount());
            for (Artist a : artists) {
                properties.artistPriority.add(a.getFolderName());
            }
            if (loader.moveToIfHasChildren("preferredArtist")) {
                loader.forEachAttributeOfElement("preferredArtist", "value", artistPrio -> {
                    properties.artistPriority.remove(artistPrio);
                    properties.artistPriority.add(0, artistPrio);
                });
                loader.goToParent();
            }

            if (loader.moveToIfHasChildren("artistPriority")) {
                Map<String, Integer> loadedArtists = new HashMap<>();
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("artist", "id", artistName -> {
                    loadedArtists.put(artistName, finalLoader.getCurrentElementAttributeAsInt("order"));
                    properties.artistPriority.remove(artistName);
                });
                loadedArtists.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach(entry -> properties.artistPriority.add(entry.getKey()));
                loader.goToParent();
            }

            // Keys:
            if (loader.moveToIfHasChildren("keyBinds")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("binding", "bindName", bindName -> {
                    String primaryBind = finalLoader.getCurrentElementAttributeAsStr("primaryBind");
                    String secondaryBind = finalLoader.getCurrentElementAttributeAsStr("secondaryBind");
                    properties.hotkeyMapPrimary.put(KeyboardAction.valueOf(bindName), KeyCodeWithModifiers.fromString(primaryBind));
                    if (!secondaryBind.isEmpty())
                        properties.hotkeyMapSecondary.put(KeyboardAction.valueOf(bindName), KeyCodeWithModifiers.fromString(secondaryBind));
                });
                loader.goToParent();
            }


            // Gender names:
            if (loader.moveToIfHasChildren("genderNames")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("genderName", "name", genderName -> {
                    properties.genderNameFemale.put(GenderNames.valueOf(genderName),
                            finalLoader.getCurrentElementAttributeAsStr("feminineValue", GenderNames.valueOf(genderName).getFeminine()));
                    properties.genderNameMale.put(GenderNames.valueOf(genderName),
                            finalLoader.getCurrentElementAttributeAsStr("masculineValue", GenderNames.valueOf(genderName).getMasculine()));
                    properties.genderNameNeutral.put(GenderNames.valueOf(genderName),
                            finalLoader.getCurrentElementAttributeAsStr("neutralValue", GenderNames.valueOf(genderName).getNeutral()));
                });
                loader.goToParent();
            }

            // Gender pronouns:
            if (loader.moveToIfHasChildren("genderPronouns")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("pronoun", "pronounName", genderPronoun -> {
                    properties.genderPronounFemale.put(GenderPronoun.valueOf(genderPronoun),
                            finalLoader.getCurrentElementAttributeAsStr("feminineValue", GenderPronoun.valueOf(genderPronoun).getFeminine()));
                    properties.genderPronounMale.put(GenderPronoun.valueOf(genderPronoun),
                            finalLoader.getCurrentElementAttributeAsStr("masculineValue", GenderPronoun.valueOf(genderPronoun).getMasculine()));
                });
                loader.goToParent();
            }

            // Gender preferences:
            if (loader.moveToIfHasChildren("genderPreferences")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("preference", "gender", genderPreference -> {
                    properties.genderPreferencesMap.put(Gender.valueOf(genderPreference), finalLoader.getCurrentElementAttributeAsInt("value"));
                });
                loader.goToParent();
            }


            // Age preferences:
            if (loader.moveToIfHasChildren("agePreferences")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("preference", "age", agePreference -> {
                    AgeCategory cat = AgeCategory.valueOf(agePreference);
                    properties.agePreferencesMap.get(PronounType.FEMININE).put(cat, finalLoader.getCurrentElementAttributeAsInt("FEMININE"));
                    properties.agePreferencesMap.get(PronounType.MASCULINE).put(cat, finalLoader.getCurrentElementAttributeAsInt("MASCULINE"));
                    properties.agePreferencesMap.get(PronounType.NEUTRAL).put(cat, finalLoader.getCurrentElementAttributeAsInt("NEUTRAL"));
                });
                loader.goToParent();
            }


            // Sexual orientation preferences:
            if (loader.moveToIfHasChildren("orientationPreferences")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("preference", "orientation", orientationPreference -> {
                    properties.orientationPreferencesMap.put(SexualOrientation.valueOf(orientationPreference),
                            finalLoader.getCurrentElementAttributeAsInt("value"));
                });
                loader.goToParent();
            }

            // Fetish preferences:
            if (loader.moveToIfHasChildren("fetishPreferences")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("preference", "fetish", fetishPreference -> {
                    properties.fetishPreferencesMap.put(Fetish.getFetishFromId(fetishPreference), finalLoader.getCurrentElementAttributeAsInt("value"));
                });
                loader.goToParent();
            }

            // Race preferences:
            if (loader.moveToIfHasChildren("subspeciesPreferences")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("preferenceFeminine", "subspecies", subspeciesPreference -> {
                    AbstractSubspecies subs = Subspecies.getSubspeciesFromId(subspeciesPreference);
                    properties.setFeminineSubspeciesPreference(subs,
                            SubspeciesPreference.valueOf(finalLoader.getCurrentElementAttributeAsStr("preference")));
                    properties.setFeminineFurryPreference(subs,
                            FurryPreference.valueOf(finalLoader.getCurrentElementAttributeAsStr("furryPreference")));
                });
                loader.forEachAttributeOfElement("preferenceMasculine", "subspecies", subspeciesPreference -> {
                    AbstractSubspecies subs = Subspecies.getSubspeciesFromId(subspeciesPreference);
                    properties.setMasculineSubspeciesPreference(subs,
                            SubspeciesPreference.valueOf(finalLoader.getCurrentElementAttributeAsStr("preference")));
                    properties.setMasculineFurryPreference(subs,
                            FurryPreference.valueOf(finalLoader.getCurrentElementAttributeAsStr("furryPreference")));
                });
                loader.goToParent();
            }


            // Skin colour preferences:
            if (loader.moveToIfHasChildren("skinColourPreferences")) {
                XMLLoader finalLoader = loader;
                loader.forEachAttributeOfElement("preference", "colour", skinColourPreference -> {
                    properties.skinColourPreferencesMap.put(PresetColour.getColourFromId(skinColourPreference), finalLoader.getCurrentElementAttributeAsInt("value"));
                });
                loader.goToParent();
            }

            // Item Discoveries:
            if (loader.moveToIfHasChildren("itemsDiscovered")) {
                loader.forEachTextContent("type", type -> {
                    properties.itemsDiscovered.add(ItemType.getItemTypeFromId(type));
                });
                loader.goToParent();
            }

            if (loader.moveToIfHasChildren("weaponsDiscovered")) {
                loader.forEachTextContent("type", type -> {
                    properties.weaponsDiscovered.add(WeaponType.getWeaponTypeFromId(type));
                });
                loader.goToParent();
            }


            if (loader.moveToIfHasChildren("clothingDiscovered")) {
                loader.forEachTextContent("type", clothingId -> {
                    if (!clothingId.startsWith("dsg_eep_uniques")) {
                        if (clothingId.startsWith("dsg_eep_servequipset_enfdjacket")) {
                            clothingId = "dsg_eep_servequipset_enfdjacket";
                        } else if (clothingId.startsWith("dsg_eep_servequipset_enfdwaistcoat")) {
                            clothingId = "dsg_eep_servequipset_enfdwaistcoat";
                        } else if (clothingId.startsWith("dsg_eep_servequipset_enfberet")) {
                            clothingId = "dsg_eep_servequipset_enfberet";
                        }
                        properties.clothingDiscovered.add(ClothingType.getClothingTypeFromId(clothingId));
                    }
                });
                loader.goToParent();
            }

            // Subspecies Discoveries:

            if (loader.moveToIfHasChildren("racesDiscovered")) {
                loader.forEachTextContent("race", race -> {
                    properties.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(race));
                });
                loader.goToParent();
            }

            if (loader.moveToIfHasChildren("racesDiscoveredAdvanced")) {
                loader.forEachTextContent("race", race -> {
                    AbstractSubspecies subs = Subspecies.getSubspeciesFromId(race);
                    properties.subspeciesDiscovered.add(subs);
                    properties.subspeciesAdvancedKnowledge.add(subs);
                });
                loader.goToParent();
            }

            loader.close();
            loader = null;
        }
    }
}
