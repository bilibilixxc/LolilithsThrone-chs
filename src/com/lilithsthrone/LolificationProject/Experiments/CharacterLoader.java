package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.LolificationProject.ModCommon.MiscStuff;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.*;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.*;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.dominion.*;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.npc.submission.SubmissionAttacker;
import com.lilithsthrone.game.character.persona.*;
import com.lilithsthrone.game.character.pregnancy.FertilisationType;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.*;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.ImageCache;
import com.lilithsthrone.utils.TreeNode;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;
import java_cup.version;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CharacterLoader {
    public static PlayerCharacter loadPlayer(XMLLoader loader, CharacterImportSetting... settings)  {
        StringBuilder log = null;
        loader.moveToNode("character");
        PlayerCharacter character = new PlayerCharacter(true, new NameTriplet(""), 0, null, Gender.F_V_B_FEMALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.DOMINION, PlaceType.DOMINION_AUNTS_HOME);

        loadGameCharacterVariables(loader, character, settings);


        if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.6")) {
            character.setGenderIdentity(character.getGender());
        }

        character.sortInventory();

        boolean newGameImport = Arrays.asList(settings).contains(CharacterImportSetting.NEW_GAME_IMPORT);

        String version;
        if (loader.moveToIfHasChildren("core")) {
            version = loader.getElementAttributeAsStr("version");

            if(newGameImport) {
                character.setLocation(WorldType.MUSEUM_LOST, PlaceType.MUSEUM_MIRROR);
            }
            if (loader.moveToIfHasChildren("playerSpecific")) {
                if (!newGameImport) {
                    character.setTitle(loader.getElementAttributeAsStr("title"));
                    character.setKarma(loader.getElementAttributeAsInt("karma"));
                    character.setMainQuestUpdated(loader.getElementAttributeAsBoolean("mainQuestUpdated"));
                    character.setSideQuestUpdated(loader.getElementAttributeAsBoolean("sideQuestUpdated"));
                    character.setRelationshipQuestUpdated(loader.getElementAttributeAsBoolean("relationshipQuestUpdated"));

                    if(Main.isVersionOlderThan(version, "0.3.7.7")) {
                        if (loader.moveToIfHasChildren("racesDiscovered")) {
                            loader.forEachAttributeOfElement("race", "value", raceVal -> {
                                character.addRaceDiscoveredFromBook(Subspecies.getSubspeciesFromId(raceVal));
                            });
                            loader.goToParent();
                        }
                    } else {
                        if (loader.moveToIfHasChildren("raceBooksDiscovered")) {
                            loader.forEachAttributeOfElement("race", "value", raceVal -> {
                                character.addRaceDiscoveredFromBook(Subspecies.getSubspeciesFromId(raceVal));
                            });
                            loader.goToParent();
                        }
                    }

                    if (loader.moveToIfHasChildren("charactersEncountered")) {
                        loader.forEachAttributeOfElement("id", "value", charID -> {
                            if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.9")) {
                                charID = charID.replaceAll("Alexa", "Helena");
                            }
                            character.addCharacterEncountered(charID);
                        });
                        loader.goToParent();
                    }

                    if (loader.moveToIfHasChildren("questMap")) {
                        loader.forEachAttributeOfElement("id", "value", questlineID -> {

                            if(Main.isVersionOlderThan(version, "0.1.99.5")) {
                                int progress = loader.getCurrentElementAttributeAsInt("progress");
                                QuestLine questLine = QuestLine.valueOf(questlineID);
                                TreeNode<Quest> q = questLine.getQuestTree();

                                for(int it = 0; it < progress; it++) {
                                    if (q.getChildren().isEmpty()) continue;
                                    q = q.getChildren().get(0);
                                }

                                Quest quest = q.getData();
                                List<Quest> questList = new ArrayList<>();
                                TreeNode<Quest> node = questLine.getQuestTree().getFirstNodeWithData(quest);

                                while(node != null) {
                                    questList.add(node.getData());
                                    node = node.getParent();
                                }
                                Collections.reverse(questList);
                                character.quests.put(questLine, questList);
                            } else if(Main.isVersionOlderThan(version, "0.3.5.3")) {
                                if(questlineID.contains("SIDE_NYAN")) {
                                    questlineID = questlineID.replace("SIDE_NYAN", "RELATIONSHIP_NYAN");
                                }

                                String questString = loader.getCurrentElementAttributeAsStr("quest");
                                if(questString.contains("SIDE_NYAN")) {
                                    questString = questString.replace("SIDE_NYAN", "RELATIONSHIP_NYAN");
                                }
                                if(questString.equals("MAIN_1_E_REPORT_TO_ALEXA")) {
                                    questString = "MAIN_1_E_REPORT_TO_HELENA";
                                }

                                QuestLine questLine = QuestLine.valueOf(questlineID);
                                Quest quest = Quest.getQuestFromId(questString);

                                List<Quest> questList = new ArrayList<>();
                                TreeNode<Quest> node = questLine.getQuestTree().getFirstNodeWithData(quest);

                                while(node != null) {
                                    questList.add(node.getData());
                                    node = node.getParent();
                                }
                                Collections.reverse(questList);
                                character.quests.put(questLine, questList);
                            } else {
                                QuestLine questLine = QuestLine.valueOf(questlineID);
                                String questString = loader.getCurrentElementAttributeAsStr("q"+0);
                                if(questString.equals("MAIN_1_E_REPORT_TO_ALEXA")) {
                                    questString = "MAIN_1_E_REPORT_TO_HELENA";
                                }
                                if(!version.isEmpty() && Main.isVersionOlderThan(version, "0.3.14") && questString.startsWith("RELATIONSHIP_NYAN")) {
                                    return;
                                }
                                Quest quest = Quest.getQuestFromId(questString);
                                List<Quest> questList = new ArrayList<>();

                                int questIncrement=0;
                                while(!questString.isEmpty()) {
                                    quest = Quest.getQuestFromId(questString);
                                    if(quest != Quest.MAIN_1_J_ARTHURS_ROOM) {
                                        questList.add(quest);
                                    }

                                    questIncrement++;
                                    questString = loader.getCurrentElementAttributeAsStr("q"+questIncrement);
                                    if(questString.equals("MAIN_1_E_REPORT_TO_ALEXA")) {
                                        questString = "MAIN_1_E_REPORT_TO_HELENA";
                                    }
                                }

                                character.quests.put(questLine, questList);
                            }
                        });
                        loader.goToParent();
                    }

                    // Failed quests:
                    if (loader.moveToIfHasChildren("questFailedMap")) {
                        loader.forEachAttributeOfElement("entry", "questLine", questlineID -> {
                            String questString = loader.getCurrentElementAttributeAsStr("q");
                            QuestLine questLine = QuestLine.valueOf(questlineID);
                            Quest quest = Quest.getQuestFromId(questString);
                            character.questsFailed.put(questLine, quest);
                        });
                        loader.goToParent();
                    }

                    if (loader.moveToIfHasChildren("friendlyOccupants")) {
                        loader.forEachAttributeOfElement("occupant", "id", occupantID -> {
                            if(!occupantID.equals("NOT_SET")) {
                                character.getFriendlyOccupants().add(occupantID);
                                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added occupant: " + occupantID);
                            }
                        });
                        loader.goToParent();
                    }

                    if (loader.moveToIfHasChildren("worldsVisited")) {
                        loader.forEachAttributeOfElement("world", "id", worldID -> {
                            character.getWorldsVisited().add(WorldType.getWorldTypeFromId(worldID));
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added world visited: " + worldID);
                        });
                        loader.goToParent();
                    }
                }
                if(!Main.isVersionOlderThan(version, "0.3.7.7")) {
                    if (loader.moveToIfHasChildren("itemsDiscovered")) {
                        loader.forEachTextContent("type", type -> {
                            character.itemsDiscovered.add(ItemType.getItemTypeFromId(type));
                        });
                        loader.goToParent();
                    }
                    if (loader.moveToIfHasChildren("weaponsDiscovered")) {
                        loader.forEachTextContent("type", type -> {
                            character.weaponsDiscovered.add(WeaponType.getWeaponTypeFromId(type));
                        });
                        loader.goToParent();
                    }
                    if (loader.moveToIfHasChildren("clothingDiscovered")) {
                        loader.forEachTextContent("type", type -> {
                            character.clothingDiscovered.add(ClothingType.getClothingTypeFromId(type));
                        });
                        loader.goToParent();
                    }
                    if (loader.moveToIfHasChildren("racesDiscovered")) {
                        loader.forEachTextContent("race", type -> {
                            character.subspeciesDiscovered.add(Subspecies.getSubspeciesFromId(type));
                        });
                        loader.goToParent();
                    }
                    if (loader.moveToIfHasChildren("racesDiscoveredAdvanced")) {
                        loader.forEachTextContent("race", type -> {
                            AbstractSubspecies subs = Subspecies.getSubspeciesFromId(type);
                            character.subspeciesDiscovered.add(subs);
                            character.subspeciesAdvancedKnowledge.add(subs);
                        });
                        loader.goToParent();
                    }
                }
            }
            loader.goToParent();
        } else {
            version = "";
        }

        doRetrogen(character, version);
        if (loader.debugMode) Logger.logTest("Fully Finished Loading Player");
        return character;
    }

    public static void loadGameCharacterVariables(XMLLoader loader, GameCharacter character, CharacterImportSetting... settings) {
        if (loader.debugMode)
            Logger.logTest("Loading Character Variables for : " + character.getClass().getSimpleName());
//        loader.setDebugMode(character.getClass().getSimpleName().equalsIgnoreCase("RatWarrensCaptive"));
        StringBuilder log = null;
        boolean noPregnancy = Arrays.asList(settings).contains(CharacterImportSetting.NO_PREGNANCY);
        boolean noCompanions = Arrays.asList(settings).contains(CharacterImportSetting.NO_COMPANIONS);
        boolean noElemental = Arrays.asList(settings).contains(CharacterImportSetting.NO_ELEMENTAL);
        boolean noSlavery = Arrays.asList(settings).contains(CharacterImportSetting.CLEAR_SLAVERY);
        boolean noLocationSetup = Arrays.asList(settings).contains(CharacterImportSetting.NO_LOCATION_SETUP);
        boolean clearKeyItems = Arrays.asList(settings).contains(CharacterImportSetting.CLEAR_KEY_ITEMS);
        boolean clearCombatHistory = Arrays.asList(settings).contains(CharacterImportSetting.CLEAR_COMBAT_HISTORY);
        boolean clearSexHistory = Arrays.asList(settings).contains(CharacterImportSetting.CLEAR_SEX_HISTORY);
        boolean removeRaceConcealed = Arrays.asList(settings).contains(CharacterImportSetting.REMOVE_RACE_CONCEALED);
        boolean clearFamilyIDs = Arrays.asList(settings).contains(CharacterImportSetting.CLEAR_FAMILY_ID);

        String version;// This is Effectively final for the Lambdas
        // ************** Core information **************//
        boolean setGenderIdentity = false;
        if (loader.moveToIfHasChildren("core")) {
            String tVer = loader.getElementAttributeAsStr("version");
            if (tVer.isEmpty()) {
                tVer = Game.loadingVersion; // Just as a backup if character version was not added
            }
            version = tVer;
            String loadedCharacterId = loader.getElementAttributeAsStr("id");
            if (!loadedCharacterId.isEmpty()) {
                character.setId(loadedCharacterId);
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set id: " + character.getId());
            }

            String loadedParserTarget = loader.getElementAttributeAsStr("parserTarget");
            if (!loadedParserTarget.isEmpty()) {
                character.parserTarget = loadedParserTarget;
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set parserTarget: " + loadedParserTarget);
            }

            // Name:
            String nameElementValue = loader.getElementAttributeAsStr("name");
            if (!nameElementValue.isEmpty()) {
                character.setName(new NameTriplet(nameElementValue));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set name: " + nameElementValue);
            } else {
                String nameMasculine = loader.getElementAttributeAsStr("name", "nameMasculine");
                String nameAndrogynous = loader.getElementAttributeAsStr("name", "nameAndrogynous");
                String nameFeminine = loader.getElementAttributeAsStr("name", "nameFeminine");
                NameTriplet backup = Name.getRandomTriplet(Subspecies.HUMAN);
                character.setName(new NameTriplet(
                        nameMasculine.isEmpty() ? backup.getMasculine() : nameMasculine,
                        nameAndrogynous.isEmpty() ? backup.getAndrogynous() : nameAndrogynous,
                        nameFeminine.isEmpty() ? backup.getFeminine() : nameFeminine));
            }

            // Surname:
            character.setSurname(loader.getElementAttributeAsStr("surname"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set surname: " + character.getSurname());
            character.setGenericName(loader.getElementAttributeAsStr("genericName"));

            // Speech colour:
            String speechColour = loader.getElementAttributeAsStr("speechColour");
            if (!speechColour.isEmpty()) {
                character.setSpeechColour(PresetColour.getColourFromId(speechColour));
            }

            // Level:
            character.setLevel(loader.getElementAttributeAsInt("level"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set level: " + character.getLevel());

            // Age appearance difference:
            character.setAgeAppearanceDifference(loader.getElementAttributeAsInt("ageAppearanceDifference"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set ageAppearanceDifference: " + character.getAgeAppearanceDifference());
            // Age appearance absolute:
            int ageAppearanceAbsolute = loader.getElementAttributeAsInt("ageAppearanceAbsolute", -1);
            if (ageAppearanceAbsolute != -1) {
                if (Configurations.AUTOMATIC_DEGEN_MODE && !Configurations.DEGEN_MODE && ageAppearanceAbsolute < 18) {
                    MiscStuff.degenMode();
                }
                character.setAgeAppearanceAbsolute(ageAppearanceAbsolute);
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set ageAppearanceAbsolute: " + ageAppearanceAbsolute);
            }

            // Birthday:
            int day = loader.getElementAttributeAsInt("dayOfBirth");
            Month month = Month.valueOf(loader.getElementAttributeAsStr("monthOfBirth"));
            int year = loader.getElementAttributeAsInt("yearOfBirth");

            if (Configurations.AUTOMATIC_DEGEN_MODE) {
                int age = Math.max(0, (int) ChronoUnit.YEARS.between(LocalDateTime.of(year, month, day, 12, 0), Main.game.getDateNow()));
                if (Configurations.AUTOMATIC_DEGEN_MODE && !Configurations.DEGEN_MODE && age < 18) {
                    MiscStuff.degenMode();
                }
            }
            character.setBirthday(LocalDateTime.of(year, month, day, 12, 0));

            // Sexual Orientation:
            character.setSexualOrientation(SexualOrientation.valueOf(loader.getElementAttributeAsStr("sexualOrientation", "value",
                    SexualOrientation.AMBIPHILIC.toString())));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set Sexual Orientation: " + character.getSexualOrientation());

            character.setDescription(loader.getElementAttributeAsStr("description"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set description");
            String playerPetName;
            if (loader.moveToIfHasChildren("petNames")) {
                String petNameID;
                while (!(petNameID = loader.getNextElementAttributeAsStr("petNameEntry", "id")).isEmpty()) {
                    String petName = loader.getCurrentElementAttributeAsStr("petName");
                    character.setPetName(petNameID, petName);
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added pet name: " + petNameID + " " + petName);
                }
                loader.goToParent();
            } else if (!(playerPetName = loader.getElementAttributeAsStr("playerPetName")).isEmpty()) {
                if (!playerPetName.isEmpty()) {
                    character.setPetName(Main.game.getPlayer().getId(), playerPetName);
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set playerPetName: " + playerPetName);
                }
            }
            character.setPlayerOnFirstNameTerms(loader.getElementAttributeAsBoolean("playerOnFirstNameTerms"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set playerOnFirstNameTerms: " + character.isPlayerOnFirstNameTerms());

            character.setRaceConcealed(loader.getElementAttributeAsBoolean("raceConcealed", !removeRaceConcealed));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set raceConcealed: " + character.isRaceConcealed());

            character.setCaptive(loader.getElementAttributeAsBoolean("captive"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set captive: " + character.captive);

            String history = loader.getElementAttributeAsStr("history", "value", Occupation.STUDENT.toString());
            if (history.equals("OCCUPATION_UNEMPLOYED_NPC")) {
                history = "OCCUPATION_NPC_UNEMPLOYED";
            }

            character.setHistory(Occupation.valueOf(history));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set history: " + character.getHistory());

            if (loader.moveToIfHasChildren("desiredJobs")) {
                loader.forEachTextContent("job", a -> character.addDesiredJob(Occupation.valueOf(a)));
                loader.goToParent();
            }

            if (!noElemental) {
                character.setElementalID(loader.getElementAttributeAsStr("elemental"));
                character.elementalSummoned = loader.getElementAttributeAsBoolean("elementalSummoned");
            }

            if (!Main.isVersionOlderThan(version, "0.3.5.6")) {
                long lastSexed = loader.getElementAttributeAsLong("lastTimeHadSex");
                long lastOrgasmed = loader.getElementAttributeAsLong("lastTimeOrgasmed", lastSexed);
                character.setLastTimeHadSex(lastSexed, false);
                if (Main.isVersionOlderThan(version, "0.3.14")) {
                    lastOrgasmed *= 60; // In old versions, this was recorded in minutes, not seconds
                }
                character.setLastTimeOrgasmedSeconds(lastOrgasmed);
            }

            String muskID;
            if (!(muskID = loader.getElementAttributeAsStr("muskMarker")).isEmpty()) {
                character.addMuskMarkerID(muskID); // Old version support
            } else if (loader.moveToIfHasChildren("muskMarkerIDs")) {
                loader.forEachTextContent("id", character::addMuskMarkerID);
                loader.goToParent();
            }
            character.setCombatBehaviour(CombatBehaviour.valueOf(loader.getElementAttributeAsStr("combatBehaviour", "value", "BALANCED")));

            if (loader.moveToIfHasChildren("personality")) {
                if (!Main.isVersionOlderThan(version, "0.3.4.9")) {
                    if (!Main.isVersionOlderThan(version, "0.3.5.1")) { // Do not load traits if prior to v0.3.5.1, as that version had a bug where too many traits were being assigned.
                        character.clearPersonalityTraits();
                        loader.forEachTextContent("trait", trait -> {
                            PersonalityTrait t = PersonalityTrait.valueOf(trait);
                            character.addPersonalityTrait(t);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added personality trait: " + t);
                        });
                    }
                } else { // Old version conversion:
                    loader.forEachAttributeOfElement("personalityEntry", "trait", trait -> {
                        String weight = loader.getCurrentElementAttributeAsStr("weight");
                        if (trait.equalsIgnoreCase("ADVENTUROUSNESS")) {
                            if (weight.equalsIgnoreCase("LOW")) {
                                character.addPersonalityTrait(PersonalityTrait.COWARDLY);
                            } else if (weight.equalsIgnoreCase("HIGH")) {
                                character.addPersonalityTrait(PersonalityTrait.BRAVE);
                            }
                        }
                        if (trait.equalsIgnoreCase("AGREEABLENESS")) {
                            if (weight.equalsIgnoreCase("LOW")) {
                                character.addPersonalityTrait(PersonalityTrait.SELFISH);
                            } else if (weight.equalsIgnoreCase("HIGH")) {
                                character.addPersonalityTrait(PersonalityTrait.KIND);
                            }
                        }
                        if (trait.equalsIgnoreCase("EXTROVERSION")) {
                            if (weight.equalsIgnoreCase("LOW")) {
                                character.addPersonalityTrait(PersonalityTrait.SHY);
                            } else if (weight.equalsIgnoreCase("HIGH")) {
                                character.addPersonalityTrait(PersonalityTrait.CONFIDENT);
                            }
                        }
                        if (trait.equalsIgnoreCase("NEUROTICISM") && weight.equalsIgnoreCase("LOW")) {
                            character.addPersonalityTrait(PersonalityTrait.SHY);
                        }
                    });
                }
                loader.goToParent();
            }

            character.setObedienceSilentlyFromSavefile(loader.getElementAttributeAsFloat("obedience"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set obedience: " + character.getObedienceValue());

            setGenderIdentity = false;
            String genIdent;
            if (!(genIdent = loader.getElementAttributeAsStr("genderIdentity")).isEmpty() && !genIdent.equalsIgnoreCase("null")) {
                character.setGenderIdentity(Gender.valueOf(genIdent));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set genderIdentity: " + character.getGenderIdentity());
                setGenderIdentity = true;
            }

            if (!clearCombatHistory) {
                character.setFoughtPlayerCount(loader.getElementAttributeAsInt("foughtPlayerCount"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set foughtPlayerCount: " + character.getFoughtPlayerCount());

                character.setLostCombatCount(loader.getElementAttributeAsInt("lostCombatCount"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set lostCombatCount: " + character.getLostCombatCount());

                character.setWonCombatCount(loader.getElementAttributeAsInt("wonCombatCount"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set wonCombatCount: " + character.getWonCombatCount());
            }
            loader.goToParent();
        } else {
            version = Main.game.loadingVersion;
        }
        int extraLevelUpPoints = 0;
        // If there is a version number, attributes should be working correctly:
        if (loader.moveToIfHasChildren("attributes")) {
            List<AbstractAttribute> damageAtts = Util.newArrayListOfValues(Attribute.DAMAGE_FIRE, Attribute.DAMAGE_ICE, Attribute.DAMAGE_LUST, Attribute.DAMAGE_PHYSICAL, Attribute.DAMAGE_POISON, Attribute.DAMAGE_SPELLS);
            // If we change the getter to return default value if it's not assigned (and ensure everything uses getter), we could yeet this.
            for (AbstractAttribute att : Attribute.getAllAttributes()) {// Not doing this would be better for performance, saves, and memory
                character.setAttribute(att, att.getBaseValue(), false);
            }
            loader.forEachAttributeOfElement("attribute", "type", type -> {
                float val = loader.getCurrentElementAttributeAsFloat("value");
                AbstractAttribute attribute = Attribute.getAttributeFromId(type);

                if (attribute != null) {
                    if (!version.isEmpty() && (!Main.isVersionOlderThan(version, "0.3.3.6") && (!character.isPlayer() || !Main.isVersionOlderThan(version, "0.3.3.9")))) { // Reset all attributes at version 0.3.3.6
                        if (!version.isEmpty() && Main.isVersionOlderThan(version, "0.2.0")) {
                            if (damageAtts.contains(attribute)) {
                                character.setAttribute(attribute, val - 100, false);
                            } else {
                                character.setAttribute(attribute, val, false);
                            }
                        } else if (!version.isEmpty() && Main.isVersionOlderThan(version, "0.2.12")) {
                            if (attribute != Attribute.HEALTH_MAXIMUM && attribute != Attribute.MANA_MAXIMUM) {
                                character.setAttribute(attribute, val, false);
                            }

                        } else {
                            if (attribute == Attribute.getRacialDamageAttribute(Race.ELEMENTAL)) {
                                character.incrementAttribute(attribute, val, false);
                            } else {
                                character.setAttribute(attribute, val, false);
                            }
                        }
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set Attribute: " + attribute.getName() + " to " + val);
                    }
                    if (!version.isEmpty() && Main.isVersionOlderThan(version, "0.3.3.6") && attribute == Attribute.DAMAGE_IMP) {
                        if (val >= 100) {
                            character.addSpecialPerk(Perk.IMP_SLAYER);
                        }
                    }
                }
            });
            if (version.isEmpty()) {
                extraLevelUpPoints = character.getLevel() * 5;
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Old character version. Extra LevelUpPoints set to: " + extraLevelUpPoints);
            }
            loader.goToParent();
        }


        //Have to set health and mana after setting attributes, as otherwise they will either overflow or be adjusted based on percentage when attributes are increased.
        float newHealth = loader.getElementAttributeAsFloat("health", 100f);
        float newMana = loader.getElementAttributeAsFloat("mana", 100f);
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Loaded health: " + character.getHealth());
        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Loaded mana: " + character.getMana());

        if (loader.moveToIfHasChildren("creampieRetentionAreas")) {
            loader.forEachTextContent("area", area -> {
                SexAreaOrifice orifice = SexAreaOrifice.valueOf(area);
                // Fix for previous versions (prior to 0.4.11.3) using incorrect areas:
                if (orifice == SexAreaOrifice.BREAST) {
                    orifice = SexAreaOrifice.NIPPLE;
                }
                if (orifice == SexAreaOrifice.BREAST_CROTCH) {
                    orifice = SexAreaOrifice.NIPPLE_CROTCH;
                }
                character.addCreampieRetentionArea(orifice);
            });
            loader.goToParent();
        }

        // Knows area map:
        if (Main.isVersionOlderThan(version, "0.2.10")) {
            if (loader.moveToIfHasChildren("playerKnowsAreas")) {
                loader.forEachAttributeOfElement("area", "type", areaType -> {
                    CoverableArea ca = CoverableArea.of(areaType);
                    String id = Main.game.getPlayer().getId();
                    character.setAreaKnownByCharacter(ca, id, true);
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added character knows area: " + ca + ", " + id);
                });
                loader.goToParent();
            }
        } else if (Main.isVersionOlderThan(version, "0.4.6.2")) {
            if (loader.moveToIfHasChildren("areasKnownByCharacters")) {
                loader.forEachAttributeOfElement("area", "type", type -> {
                    CoverableArea ca = CoverableArea.of(type);
                    loader.forEachAttributeOfElement("character", "id", id -> {
                        character.setAreaKnownByCharacter(ca, id, true);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added character knows area: " + ca + ", " + id);
                    });
                });
                loader.goToParent();// probably need a second one...
            }
        } else {
            if (loader.moveToIfHasChildren("areasKnownByCharacters")) {
                loader.forEachAttributeOfElement("area", "type", type -> {
                    CoverableArea ca = CoverableArea.of(type);
                    loader.forEachTextContent("c", id -> {
                        character.setAreaKnownByCharacter(ca, id, true);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added character knows area: " + ca + ", " + id);
                    });
                });
                loader.goToParent();// probably need a second one...
            }
        }


        if (loader.moveToIfHasChildren("playerCore")) { // Old version support:
            int exp = loader.getElementAttributeAsInt("experience");
            character.incrementExperience(loader.getElementAttributeAsInt("experience"), false);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set experience: " + exp);
            loader.goToParent();
        } else {
            int exp = loader.getElementAttributeAsInt("experience");

            character.incrementExperience(exp, false);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set experience: " + exp);

            if (!version.isEmpty() && !Main.isVersionOlderThan(version, "0.3.3.9")) {
                int pp = loader.getElementAttributeAsInt("perkPoints");
                character.setPerkPoints(pp);
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set perkPoints: " + pp);
            }
        }
        if (loader.moveToIfHasChildren("perkCategoryPoints")) {
            character.perkCategoryPoints = new HashMap<>();
            loader.forEachAttributeOfElement("points", "category", pCat -> {
                character.perkCategoryPoints.put(PerkCategory.valueOf(pCat), loader.getCurrentElementInt());
            });
            loader.goToParent();
        }

        // ************** Location Information **************//

        if (!noLocationSetup && loader.moveToIfHasChildren("locationInformation")) {
            String worldName;
            if (!(worldName = loader.getElementAttributeAsStr("worldLocation")).isEmpty()) {
                int x = loader.getElementAttributeAsInt("location", "x");
                int y = loader.getElementAttributeAsInt("location", "y");
                int homeX = loader.getElementAttributeAsInt("homeLocation", "x");
                int homeY = loader.getElementAttributeAsInt("homeLocation", "y");
                int globalX = loader.getElementAttributeAsInt("globalLocation", "x");
                int globalY = loader.getElementAttributeAsInt("globalLocation", "y");
                String homeLocation = loader.getElementAttributeAsStr("homeWorldLocation");
                if (Main.isVersionOlderThan(version, "0.3.1.1") && worldName.equals("JUNGLE")) {
                    character.setLocation(WorldType.EMPTY, Main.game.getWorlds().get(WorldType.EMPTY).getRandomCell(PlaceType.GENERIC_EMPTY_TILE).getLocation(), true);
                } else {
                    AbstractWorldType worldType = WorldType.getWorldTypeFromId(worldName);

                    if ((worldType == WorldType.DOMINION || worldType == WorldType.SUBMISSION || worldType == WorldType.HARPY_NEST) &&
                            Main.isVersionOlderThan(version, "0.2.1.5")) {
                        AbstractPlaceType placeType = PlaceType.DOMINION_BACK_ALLEYS;

                        if (character.isPlayer()) {
                            if (worldType == WorldType.DOMINION) {
                                placeType = PlaceType.DOMINION_AUNTS_HOME;
                            } else if (worldType == WorldType.SUBMISSION) {
                                placeType = PlaceType.SUBMISSION_ENTRANCE;
                            } else {
                                placeType = PlaceType.HARPY_NESTS_ENTRANCE_ENFORCER_POST;
                            }
                        } else {
                            if (character instanceof DominionAlleywayAttacker) {
                                placeType = PlaceType.DOMINION_BACK_ALLEYS;
                            } else if (character instanceof DominionSuccubusAttacker) {
                                placeType = PlaceType.DOMINION_DARK_ALLEYS;
                            } else if (character instanceof Cultist) {
                                placeType = PlaceType.DOMINION_STREET;
                            } else if (character instanceof ReindeerOverseer) {
                                placeType = PlaceType.DOMINION_STREET;
                            } else if (character instanceof SubmissionAttacker) {
                                placeType = PlaceType.SUBMISSION_TUNNELS;
                            } else if (character instanceof HarpyNestsAttacker) {
                                placeType = PlaceType.HARPY_NESTS_WALKWAYS;
                            } else if (character instanceof HarpyBimbo || character instanceof HarpyBimboCompanion) {
                                placeType = PlaceType.HARPY_NESTS_HARPY_NEST_YELLOW;
                            } else if (character instanceof HarpyDominant || character instanceof HarpyDominantCompanion) {
                                placeType = PlaceType.HARPY_NESTS_HARPY_NEST_RED;
                            } else if (character instanceof HarpyNympho || character instanceof HarpyNymphoCompanion) {
                                placeType = PlaceType.HARPY_NESTS_HARPY_NEST_PINK;
                            } else if (character instanceof Scarlett || character instanceof Helena) {
                                placeType = PlaceType.HARPY_NESTS_HELENAS_NEST;
                            } else { // Catch if no location found:
                                if (worldType == WorldType.DOMINION) {
                                    placeType = PlaceType.DOMINION_BACK_ALLEYS;
                                } else if (worldType == WorldType.SUBMISSION) {
                                    placeType = PlaceType.SUBMISSION_TUNNELS;
                                } else {
                                    placeType = PlaceType.HARPY_NESTS_WALKWAYS;
                                }
                            }
                        }

                        character.setLocation(worldType, Main.game.getWorlds().get(worldType).getRandomUnoccupiedCell(placeType).getLocation(), !character.isPlayer());

                        if (character.isPlayer()) {
                            Main.game.getWorlds().get(worldType).getCell(character.getLocation()).setDiscovered(true);
                            if (character.getLocation().getY() < Main.game.getWorlds().get(worldType).WORLD_HEIGHT - 1) {
                                Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX(), character.getLocation().getY() + 1).setDiscovered(true);
                            }
                            if (character.getLocation().getY() != 0) {
                                Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX(), character.getLocation().getY() - 1).setDiscovered(true);
                            }
                            if (character.getLocation().getX() < Main.game.getWorlds().get(worldType).WORLD_WIDTH - 1) {
                                Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX() + 1, character.getLocation().getY()).setDiscovered(true);
                            }
                            if (character.getLocation().getX() != 0) {
                                Main.game.getWorlds().get(worldType).getCell(character.getLocation().getX() - 1, character.getLocation().getY()).setDiscovered(true);
                            }
                        }
                    } else {
                        character.setLocation(worldType, new Vector2i(x, y), false);
                        character.setHomeLocation(WorldType.getWorldTypeFromId(homeLocation), new Vector2i(homeX, homeY));
                        character.setGlobalLocation(new Vector2i(globalX, globalY));
                    }
                }
            } else {
                character.setLocation(new Vector2i(0, 0));
            }
            loader.goToParent();
        }


        // ************** Body **************//
        character.removeStatusEffect(StatusEffect.SUBSPECIES_BONUS);
        character.body = LoadBody.loadBody(loader, log);
        if (!setGenderIdentity) {
            character.setGenderIdentity(character.getGender());
        }
        character.body.calculateRace(character);


        // ************** Inventory **************//

        character.resetInventory(true);
        if (loader.hasChildren("characterInventory")) {
            CharacterInventory.loadingFromFloorBackupCheck = false;
            character.setInventory(LoadInventory.loadInventoryFromXML(loader));
        } else {
            CharacterCreation.getDressed(character, false);
        }

        if (clearKeyItems) {
            character.inventory.removeAllItemsByRarity(Rarity.QUEST);
            character.inventory.removeAllWeaponsByRarity(Rarity.QUEST);
            character.inventory.removeAllClothingByRarity(Rarity.QUEST);
        }

        character.holdingClothing = new HashMap<>();
        if (loader.moveToIfHasChildren("holdingClothing")) {
            loader.forEachAttributeOfElement("clothing", "name", clothingName -> {
                AbstractClothing clothing = LoadInventory.loadClothing(loader);
                InventorySlot slot = clothing.getSlotEquippedTo();
                clothing.setSlotEquippedTo(null);
                character.holdingClothing.put(slot, clothing);
            });
            loader.goToParent();
        }

        // ************** Outfits **************//

        character.savedOutfits = new ArrayList<>();
        if (loader.moveToIfHasChildren("savedOutfits")) {
            loader.forEachAttributeOfElement("outfit", "name", outfitName -> {
                character.savedOutfits.add(LoadOutfit.loadFromXML(loader));
            });
            loader.goToParent();
        }

        // ************** Markings **************//
        if (loader.moveToIfHasChildren("scars")) {
            loader.forEachAttributeOfElement("scarEntry", "slot", scarSlot -> {
                if (loader.moveToNode("scar")) {
                    character.setScar(InventorySlot.valueOf(scarSlot), LoadMarking.loadScar(loader));
                    loader.goToParent();
                }
            });
            loader.goToParent();
        }
        if (loader.moveToIfHasChildren("tattoos")) {
            loader.forEachAttributeOfElement("tattooEntry", "slot", tatSlot -> {
                if (loader.moveToNode("tattoo")) {
                    character.addTattoo(InventorySlot.valueOf(tatSlot), LoadMarking.loadTattoo(loader));
                    loader.goToParent();
                }
            });
            loader.goToParent();
        }
        if (loader.moveToIfHasChildren("lipstickMarks")) {
            if (loader.moveToNode("covering")) {
                loader.forEachAttributeOfElement("lipstickEntry", "slot", lipSlot -> {
                    character.addLipstickMarking(null, InventorySlot.valueOf(lipSlot), LoadMarking.loadCovering(loader));
                });
                loader.goToParent();
            }
            loader.goToParent();
        }


        // ************** Attributes **************//

        // Core attributes are set in the first section.

        // Potion attributes:
        if (loader.moveToIfHasChildren("potionAttributes")) {
            loader.forEachAttributeOfElement("attribute", "type", attType -> {
                AbstractAttribute attribute = Attribute.getAttributeFromId(attType);
                float val = loader.getCurrentElementAttributeAsFloat("value");
                character.addPotionEffect(attribute, val, false);
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set Potion Attribute: " + attribute.getName() + " to " + val);
            });
            loader.goToParent();
        }

        // Perks:
        character.completePerkReset();
        if (loader.moveToIfHasChildren("traits")) {
            loader.forEachAttributeOfElement("perk", "type", perkType -> {
                AbstractPerk p = Perk.getPerkFromId(perkType);
                if (p.isEquippableTrait() && (!Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12") || PerkManager.MANAGER.isPerkAnywhereInAvailableTree(p, character))) {
                    // If older than 0.2.12, check to see if the perk should actually be added.
                    character.addTrait(p);
                }
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Equipped Perk: " + p.getName(character));
            });
            loader.goToParent();
        }

        character.resetSpecialPerksMap();
        if (loader.moveToIfHasChildren("specialPerks")) {
            loader.forEachAttributeOfElement("perk", "type", perkType -> {
                AbstractPerk p = Perk.getPerkFromId(perkType);
                if (p.isEquippableTrait() && (!Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12") || PerkManager.MANAGER.isPerkAnywhereInAvailableTree(p, character))) {
                    // If older than 0.2.12, check to see if the perk should actually be added.
                    character.addSpecialPerk(p);
                }
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Special Perk: " + p.getName(character));
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("perks")) {
            if (!version.isEmpty() && Main.isVersionOlderThan(version, "0.2.0.2")) {
                character.clearTraits();
            }
            loader.forEachAttributeOfElement("perk", "type", type -> {
                type = type.replaceAll("STRENGTH_", "PHYSIQUE_");
                if (!type.equals("ELEMENTAL_CORE") && !type.equals("ELEMENTAL_CORRUPTION")) {
                    AbstractPerk p = Perk.getPerkFromId(type);
                    if (!Main.isVersionOlderThan(version, "0.2.12") || PerkManager.MANAGER.isPerkAnywhereInAvailableTree(p, character)) {
                        // If older than 0.3, check to see if the perk should actually be added.
                        character.addPerk(loader.getCurrentElementAttributeAsInt("row"), p);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Perk: " + p.getName(character));
                    }
                }
            });
            loader.goToParent();
        }

        if (!version.isEmpty() && Main.isVersionOlderThan(version, "0.3.8.2")) { // Reset perks map for characters who had no starting perks assigned to their tree:
            boolean reset = true;
            for (Set<AbstractPerk> a : character.perks.values()) {
                if (!a.isEmpty()) {
                    reset = false;
                    continue;
                }
            }
            if (reset) {
                character.initPerkTreeAndBackgroundPerks();
            }
        }

        // Spells:
        if (loader.moveToIfHasChildren("knownSpells")) {
            loader.forEachAttributeOfElement("spell", "type", spellType -> {
                Spell s = Spell.valueOf(spellType);
                if (s != Spell.DARK_SIREN_SIRENS_CALL) {
                    character.addSpell(s);
                }
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("spellUpgrades")) {
            loader.forEachAttributeOfElement("upgrade", "type", upgradeType -> {
                character.addSpellUpgrade(SpellUpgrade.valueOf(upgradeType));
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("spellUpgradePoints")) {
            loader.forEachAttributeOfElement("upgradeEntry", "school", upgradeSchool -> {
                character.setSpellUpgradePoints(SpellSchool.valueOf(upgradeSchool), loader.getCurrentElementAttributeAsInt("points"));
            });
            loader.goToParent();
        }

        // Fetishes:
        if (Main.isVersionOlderThan(version, "0.4.6.2")) {
            if (loader.moveToIfHasChildren("fetishes")) {
                loader.forEachAttributeOfElement("fetish", "type", fetishType -> {
                    AbstractFetish fet;
                    if (fetishType.equals("FETISH_NON_CON")) { // Support for old non-con fetish:
                        character.incrementEssenceCount(5, false);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added refund for old non-con fetish. (+5 arcane essences)");
                    } else if ((fet = Fetish.getFetishFromId(fetishType)) != null) {
                        String val = loader.getCurrentElementAttributeAsStr("value");
                        if (!val.isEmpty() && Boolean.valueOf(val)) {
                            character.addFetish(fet);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Fetish: " + fet.getName(character));
                        } else {
                            if (fetishType.equals("FETISH_NON_CON")) { // Support for old non-con fetish:
                                character.incrementEssenceCount(5, false);
                                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added refund for old non-con fetish. (+5 arcane essences)");
                            } else if ((fet = Fetish.getFetishFromId(fetishType)) != null) {
                                character.addFetish(fet);
                                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Fetish: " + fet.getName(character));
                            }
                        }
                    }
                });
                loader.goToParent();
            }

            if (loader.moveToIfHasChildren("fetishDesire")) {
                loader.forEachAttributeOfElement("entry", "fetish", fetishID -> {
                    String desire = loader.getCurrentElementAttributeAsStr("desire");
                    character.setFetishDesire(Fetish.getFetishFromId(fetishID), FetishDesire.valueOf(desire));
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set fetish desire: " + fetishID + " , " + desire);
                });
                loader.goToParent();
            }

            if (loader.moveToIfHasChildren("fetishExperience")) {
                loader.forEachAttributeOfElement("entry", "fetish", fetishID -> {
                    int experience = loader.getCurrentElementAttributeAsInt("experience");
                    character.setFetishExperience(Fetish.getFetishFromId(fetishID), experience);
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set fetish experience: " + fetishID + " , " + experience);
                });
                loader.goToParent();
            }

        } else { // New fetish saving structure:
            if (loader.moveToIfHasChildren("fetishes")) {
                loader.forEachTextContent("f", fetishID -> {
                    AbstractFetish loadedFetish = Fetish.getFetishFromId(fetishID);
                    if (loadedFetish != null) {
                        boolean o = loader.getCurrentElementAttributeAsBoolean("o");
                        int exp = loader.getCurrentElementAttributeAsInt("xp", -1);
                        int fetDesire = loader.getCurrentElementAttributeAsInt("desire", -1);
                        if (o) {// Really, a single letter variable Inno?
                            character.addFetish(loadedFetish);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Fetish: " + fetishID);
                        }
                        if (fetDesire != -1) {
                            FetishDesire desire = FetishDesire.getDesireFromValue(fetDesire);
                            character.setFetishDesire(loadedFetish, desire);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set fetish desire: " + fetishID + ", " + desire.toString());
                        }
                        if (exp != -1) {
                            character.setFetishExperience(loadedFetish, exp);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set fetish experience: " + fetishID + ", " + exp);
                        }
                    }
                });
                loader.goToParent();
            }
        }


        // Status Effects:
        if (loader.moveToIfHasChildren("statusEffects")) {
            loader.forEachAttributeOfElement("statusEffect", "type", statusType -> {
                int val = loader.getCurrentElementAttributeAsInt("value", -1);
                int sr = loader.getCurrentElementAttributeAsInt("sr", -1);
                long lta = loader.getCurrentElementAttributeAsLong("lta", -1L);
                long sp = loader.getCurrentElementAttributeAsLong("sp", -1L);
                String fl = loader.getCurrentElementAttributeAsStr("fl");

                if (val != -1) {
                    AbstractStatusEffect effect = StatusEffect.getStatusEffectFromId(statusType);
                    if (!noPregnancy || (effect != StatusEffect.PREGNANT_0 && effect != StatusEffect.PREGNANT_1 && effect != StatusEffect.PREGNANT_2 && effect != StatusEffect.PREGNANT_3)) {
                        int seconds = val;
                        if (Main.isVersionOlderThan(version, "0.3.0.6")) {
                            seconds *= 60;
                        }
                        character.addStatusEffect(effect, seconds);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Status Effect: " + effect.getName(character));
                    }
                }

                if (sr != -1 || !fl.isEmpty()) {
                    AbstractStatusEffect effect = StatusEffect.getStatusEffectFromId(statusType);
                    if (!noPregnancy || (effect != StatusEffect.PREGNANT_0 && effect != StatusEffect.PREGNANT_1 && effect != StatusEffect.PREGNANT_2 && effect != StatusEffect.PREGNANT_3)) {
                        character.addStatusEffect(effect, lta, sp, sr);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Status Effect: " + effect.getName(character));
                    }
                }
            });
            loader.goToParent();
        }

        // Moves:
        if (loader.moveToIfHasChildren("knownMoves")) {
            character.resetMoveData();
            loader.forEachAttributeOfElement("move", "type", moveType -> {
                character.addKnownMove(moveType);
            });
            loader.goToParent();
        }

        List<String> movesToEquip = new ArrayList<>();
        if (loader.moveToIfHasChildren("equippedMoves")) {
            character.resetMoveData();
            loader.forEachAttributeOfElement("move", "type", moveType -> {
                if (moveType.equalsIgnoreCase("offhand strike")) {
                    moveType = "offhand-strike";
                }
                movesToEquip.add(moveType);
            });
            loader.goToParent();
        }

        // ************** Relationships **************//
        if (loader.moveToIfHasChildren("characterRelationships")) {
            loader.forEachAttributeOfElement("relationship", "character", characterId -> {
                float affection = loader.getCurrentElementAttributeAsFloat("value");
                if (Main.isVersionOlderThan(version, "0.3.5.9")) {
                    characterId = characterId.replaceAll("Alexa", "Helena");
                }
                if (!characterId.equals("NOT_SET")) {
                    character.setAffection(characterId, affection);
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set Relationship: " + characterId + " , " + affection);
                }
            });
            loader.goToParent();
        }

        // ************** Pregnancy **************//

        if (!noPregnancy) { // Keldon Investigate Potential Bug Related to Orifice line below
            int littersGenerated = loader.getElementAttributeAsInt("pregnancy", "littersGenerated");
            int timeProgressedToFinalPregnancyStage = loader.getElementAttributeAsInt("pregnancy", "timeProgressedToFinalPregnancyStage");
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/><br/>Pregnancies:");

            if (Main.isVersionOlderThan(version, "0.3.0.6")) {
                character.setTimeProgressedToFinalPregnancyStage(timeProgressedToFinalPregnancyStage * 60);
            } else {
                character.setTimeProgressedToFinalPregnancyStage(timeProgressedToFinalPregnancyStage);
            }
            character.setLittersGenerated(littersGenerated);
            long timeProgressedToFinalIncubationStage = loader.getCurrentElementAttributeAsLong("timeProgressedToFinalIncubationStage");
            String orificeStored = loader.getCurrentElementAttributeAsStr("orifice");
            if (!orificeStored.isEmpty()) {
                SexAreaOrifice orifice = SexAreaOrifice.valueOf(orificeStored);
                character.timeProgressedToFinalIncubationStage.put(orifice, timeProgressedToFinalIncubationStage);
            }
            if (loader.moveToIfHasChildren("pregnancy")) {
                if (loader.moveToIfHasChildren("potentialPartnersAsMother")) {
                    loader.forEachAttributeOfElement("pregnancyPossiblity", "fatherId", fatherId -> {
                        String motherId = loader.getCurrentElementAttributeAsStr("motherId");
                        float probability = loader.getCurrentElementAttributeAsFloat("probability");
                        PregnancyPossibility preg = new PregnancyPossibility(motherId, fatherId, probability);

                        character.getPotentialPartnersAsMother().add(preg);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Pregnancy Possibility as mother.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("potentialPartnersAsFather")) {
                    loader.forEachAttributeOfElement("pregnancyPossiblity", "fatherId", fatherId -> {
                        String motherId = loader.getCurrentElementAttributeAsStr("motherId");
                        float probability = loader.getCurrentElementAttributeAsFloat("probability");
                        PregnancyPossibility preg = new PregnancyPossibility(motherId, fatherId, probability);

                        character.getPotentialPartnersAsFather().add(preg);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Pregnancy Possibility as father.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("pregnantLitter")) {
                    loader.forEachAttributeOfElement("litter", "fatherId", fatherId -> {
                        Litter loadedLitter = getLitterFromXML(loader);

                        character.setPregnantLitter(loadedLitter);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Pregnant litter.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("incubatingLitters")) {
                    loader.forEachAttributeOfElement("entry", "orifice", orificeName -> {
                        SexAreaOrifice orifice = SexAreaOrifice.valueOf(orificeName);
                        Litter loadedLitter = getLitterFromXML(loader);

                        character.addIncubationLitter(orifice, loadedLitter);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added Pregnant litter.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("birthedLitters")) {
                    loader.forEachAttributeOfElement ("litter","fatherId", fatherId -> {
                        character.getLittersBirthed().add(getLitterFromXML(loader));
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added litter birthed.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("littersFathered")) {
                    loader.forEachAttributeOfElement ("litter","fatherId", fatherId -> {
                        character.getLittersFathered().add(getLitterFromXML(loader));
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added litter fathered.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("incubatedLitters")) {
                    loader.forEachAttributeOfElement ("litter","fatherId", fatherId -> {
                        character.getLittersIncubated().add(getLitterFromXML(loader));
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added litter incubated.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("implantedLitters")) {
                    loader.forEachAttributeOfElement ("litter","fatherId", fatherId -> {
                        character.getLittersImplanted().add(getLitterFromXML(loader));
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added litter implanted.");
                    });
                    loader.goToParent();
                }
                if (loader.moveToIfHasChildren("pregnancyReactions")) {
                    loader.forEachAttributeOfElement("id", "value", reactId -> character.pregnancyReactions.add(reactId));
                    loader.goToParent();
                }
                loader.goToParent();
            }
        }


        // ************** Family **************//

        if (loader.moveToIfHasChildren("family")) {
            if (!clearFamilyIDs) {
                character.setMother(loader.getElementAttributeAsStr("motherId"));
            }
            character.motherName = loader.getElementAttributeAsStr("motherName");
            character.motherFemininity = Femininity.valueOf(loader.getElementAttributeAsStr("motherFemininity"));
            character.motherSubspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("motherSubspecies"));

            if (!clearFamilyIDs) {
                character.setFather(loader.getElementAttributeAsStr("fatherId"));
            }
            character.fatherName = loader.getElementAttributeAsStr("fatherName");
            character.fatherFemininity = Femininity.valueOf(loader.getElementAttributeAsStr("fatherFemininity"));
            character.fatherSubspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("fatherSubspecies"));

            if (!loader.getElementAttributeAsStr("incubatorId").isEmpty()) {
                character.setIncubator(loader.getElementAttributeAsStr("incubatorId"));
                character.incubatorName = loader.getElementAttributeAsStr("incubatorName");
                character.incubatorFemininity = Femininity.valueOf(loader.getElementAttributeAsStr("incubatorFemininity"));
                character.incubatorSubspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("incubatorSubspecies"));
            }

            int day = loader.getElementAttributeAsInt("dayOfConception");
            Month month = Month.valueOf(loader.getElementAttributeAsStr("monthOfConception"));
            int year = loader.getElementAttributeAsInt("yearOfConception");

            character.setConceptionDate(LocalDateTime.of(year, month, day, 12, 0));

            if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.4") && !character.isPlayer()) { // Birthdays prior to v0.3.7.4 are loaded based on conception date, as a bug introduced in v0.3.7.3 caused birthdays to regress by 18 years:
                if (year + 18 <= Main.game.getDateNow().getYear()) {
                    character.setBirthday(LocalDateTime.of(year + 18, character.getBirthMonth(), character.getDayOfBirth(), 12, 0));
                    character.setConceptionDate(LocalDateTime.of(year + 18, character.getBirthMonth(), character.getDayOfBirth(), 12, 0).minusDays(15 + Util.random.nextInt(30)));
                } else {
                    character.setBirthday(LocalDateTime.of(year, character.getBirthMonth(), character.getDayOfBirth(), 12, 0));
                }

            } else if (Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.7") && character.isPlayer()) { // Reverting the above change for player characters
                int age = (int) ChronoUnit.YEARS.between(character.getBirthday(), Main.game.getDateNow());
//				if(age<18) {
//					character.setBirthday(LocalDateTime.of(character.getBirthday().getYear()-18, character.getBirthMonth(), character.getDayOfBirth(), 12, 0));
//					character.setConceptionDate(LocalDateTime.of(year-18, month, day, 12, 0));
//				}
            }
            loader.goToParent();
        }


        // ************** Slavery **************//

        if (!noSlavery) {
            if (loader.moveToIfHasChildren("slavery")) {
                character.setAbleToBeEnslaved(loader.getElementAttributeAsBoolean("ableToBeEnslaved"));

                if (loader.moveToIfHasChildren("slaveStation")) {
                    character.setSlaveStationWorldType(WorldType.getWorldTypeFromId(loader.getElementAttributeAsStr("worldType")));
                    character.setSlaveStationLocation(new Vector2i(
                            loader.getElementAttributeAsInt("location", "x"),
                            loader.getElementAttributeAsInt("location", "y")));
                    loader.goToParent();
                }

                if (loader.moveToIfHasChildren("slavesOwned")) {
                    loader.forEachAttributeOfElement("slave", "id", slaveId -> {
                        if (!slaveId.equalsIgnoreCase("NOT_SET")) {
                            character.getSlavesOwned().add(slaveId);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added owned slave: " + slaveId);
                        }
                    });
                    loader.goToParent();
                }

                String ownerId = loader.getElementAttributeAsStr("owner");
                if (Main.isVersionOlderThan(version, "0.3.5.9")) {
                    ownerId = ownerId.replaceAll("Alexa", "Helena");
                }
                character.setOwner(ownerId);
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set owner: " + ownerId);

                if (!loader.getElementAttributeAsStr("slaveJob").isEmpty()) { // Old slave job versions:
                    SlaveJob sJob = SlaveJob.valueOf(loader.getElementAttributeAsStr("slaveJob"));

                    for (int i = 0; i < character.workHours.length; i++) {
                        if (loader.getElementAttributeAsBoolean("hour" + i)) {
                            character.workHours[i] = sJob;
                        }
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set legacy work hour: " + i + ", " + character.workHours[i]);
                    }

                    if (loader.moveToIfHasChildren("slaveJobSettings")) {
                        loader.forEachAttributeOfElement("setting", "value", settingVal -> {
                            SlaveJobSetting setting = SlaveJobSetting.valueOf(settingVal);
                            character.addSlaveJobSettings(sJob, setting);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added slave job setting: " + setting);
                        });
                        loader.goToParent();
                    }
                } else { // New versions:
                    if (loader.moveToIfHasChildren("slaveAssignedJobs")) {
                        if (Main.isVersionOlderThan(version, "0.3.5.2")) {
                            for (int i = 0; i < character.workHours.length; i++) {
                                character.workHours[i] = SlaveJob.valueOf(loader.getCurrentElementAttributeAsStr("hour" + i));
                            }
                        } else {
                            for (int i = 0; i < character.workHours.length; i++) {
                                String job = loader.getCurrentElementAttributeAsStr("h" + i);
                                if (!job.isEmpty()) {
                                    character.workHours[i] = SlaveJob.valueOf(job);
                                }
                            }
                        }
                        loader.goToParent();
                    }

                    if (Main.isVersionOlderThan(version, "0.3.9")) { // Add new spa permissions:
                        character.addSlaveJobSettings(SlaveJob.SPA, SlaveJobSetting.SPA_BATHING);
                        character.addSlaveJobSettings(SlaveJob.SPA, SlaveJobSetting.SPA_STRIP_TO_BATHE);
                        character.addSlaveJobSettings(SlaveJob.SPA, SlaveJobSetting.SPA_MASSAGE);
                        character.addSlaveJobSettings(SlaveJob.SPA_RECEPTIONIST, SlaveJobSetting.SPA_SHOWERING);
                    }

                    if (loader.moveToIfHasChildren("slaveJobSettings")) {
                        loader.forEachAttributeOfElement("jobSetting", "job", jobSet -> {
                            SlaveJob job = SlaveJob.valueOf(jobSet);
                            if (Main.isVersionOlderThan(version, "0.3.9")) {
                                if (job == SlaveJob.MILKING) { // Handle conversion from old negative permission to new permission:
                                    character.addSlaveJobSettings(job, SlaveJobSetting.MILKING_MILK);
                                    character.addSlaveJobSettings(job, SlaveJobSetting.MILKING_MILK_CROTCH);
                                    character.addSlaveJobSettings(job, SlaveJobSetting.MILKING_CUM);
                                    character.addSlaveJobSettings(job, SlaveJobSetting.MILKING_GIRLCUM);
                                }
                            }
                            loader.forEachTextContent("setting", id -> {
                                if (Main.isVersionOlderThan(version, "0.3.9") && job == SlaveJob.MILKING) {
                                    if (id.equalsIgnoreCase("MILKING_MILK_DISABLE")) {
                                        character.removeSlaveJobSettings(job, SlaveJobSetting.MILKING_MILK);
                                    } else if (id.equalsIgnoreCase("MILKING_MILK_CROTCH_DISABLE")) {
                                        character.removeSlaveJobSettings(job, SlaveJobSetting.MILKING_MILK_CROTCH);
                                    } else if (id.equalsIgnoreCase("MILKING_CUM_DISABLE")) {
                                        character.removeSlaveJobSettings(job, SlaveJobSetting.MILKING_CUM);
                                    } else if (id.equalsIgnoreCase("MILKING_GIRLCUM_DISABLE")) {
                                        character.removeSlaveJobSettings(job, SlaveJobSetting.MILKING_GIRLCUM);
                                    }
                                } else {
                                    SlaveJobSetting setting = SlaveJobSetting.valueOf(id);
                                    character.addSlaveJobSettings(job, setting);
                                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added slave job (" + job + ") setting: " + setting);
                                }
                            });
                        });
                        loader.goToParent();
                    }
                }

                if (loader.moveToIfHasChildren("slavePermissionSettings")) {
                    // Clear settings first:
                    for (SlavePermission key : character.getSlavePermissionSettings().keySet()) {
                        if (!key.isMutuallyExclusiveSettings()) {
                            character.getSlavePermissionSettings().get(key).clear();
                        }
                    }
                    loader.forEachAttributeOfElement("permission", "type", typeId -> {
                        if (Main.isVersionOlderThan(version, "0.4.5.7") && typeId.equalsIgnoreCase("PREGNANCY")) {
                            typeId = "PILLS";
                        }
                        SlavePermission slavePermission = SlavePermission.valueOf(typeId);

                        loader.forEachAttributeOfElement("setting", "value", settingId -> {
                            if (Main.isVersionOlderThan(version, "0.4.5.7") && settingId.startsWith("PREGNANCY_")) {
                                settingId = settingId.replaceFirst("PREGNANCY_", "PILLS_");
                            }
                            SlavePermissionSetting setting = SlavePermissionSetting.valueOf(settingId);
                            character.addSlavePermissionSetting(slavePermission, setting);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added slave permission setting: " + slavePermission + ", " + setting);
                        });
                    });
                    if (Main.isVersionOlderThan(version, "0.3.9.3")) {
                        character.addSlavePermissionSetting(SlavePermission.SEX, SlavePermissionSetting.SEX_SAVE_VIRGINITY);
                    }
                    if (Main.isVersionOlderThan(version, "0.4.5.7")) {
                        character.addSlavePermissionSetting(SlavePermission.PREGNANCY, SlavePermissionSetting.PREGNANCY_ALLOW_BIRTHING);
                        character.addSlavePermissionSetting(SlavePermission.PREGNANCY, SlavePermissionSetting.PREGNANCY_ALLOW_EGG_LAYING);
                    }
                    loader.goToParent();
                }

//				// Since 0.4.3.X
//				Element slaveCat = (Element)slaveryElement.getElementsByTagName("category").item(0);
//				if (slaveCat != null) {
//					character.setSlaveCategory(slaveCat.getAttribute("value"));
//				}
//				Element slaveNotes = (Element)slaveryElement.getElementsByTagName("notes").item(0);
//				if (slaveNotes != null) {
//					character.setSlaveNotes(slaveNotes.getTextContent());
//				}
            }
        }


        // ************** Companions **************//

        if (!noCompanions && loader.moveToIfHasChildren("companions")) {
            if (loader.moveToIfHasChildren("companionsFollowing")) {
                loader.forEachAttributeOfElement("companion", "id", companionId -> {
                    if (!companionId.equals("NOT_SET")) {
                        character.getCompanionsId().add(companionId);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added companion: " + companionId);
                    }
                });
                loader.goToParent();
            }
            String partyLead = loader.getCurrentElementAttributeAsStr("partyLeader");
            character.setPartyLeader(partyLead);
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set party leader: " + partyLead);

            character.setMaxCompanions(loader.getElementAttributeAsInt("maxCompanions"));
            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set max companions: " + character.getMaxCompanions());
        }


        // ************** Sex Stats **************//

        if (!clearSexHistory && loader.moveToIfHasChildren("sexStats")) {
            if (!loader.getElementAttributeAsStr("orgasmsForSatisfaction").isEmpty()) {
                character.setOrgasmsForSatisfaction(loader.getElementAttributeAsInt("orgasmsForSatisfaction"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set orgasmsForSatisfaction: " + character.orgasmsForSatisfaction);
            }

            if (!loader.getElementAttributeAsStr("daysOrgasmCountRecord").isEmpty()) {
                character.setDaysOrgasmCountRecord(loader.getElementAttributeAsInt("daysOrgasmCountRecord"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set daysOrgasmCountRecord: " + character.getDaysOrgasmCountRecord());
            }

            if (!loader.getElementAttributeAsStr("daysOrgasmCount").isEmpty()) {
                character.setDaysOrgasmCount(loader.getElementAttributeAsInt("daysOrgasmCount"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set daysOrgasmCount: " + character.getDaysOrgasmCount());
            }

            if (!loader.getElementAttributeAsStr("totalOrgasmCount").isEmpty()) {
                character.setTotalOrgasmCount(loader.getElementAttributeAsInt("totalOrgasmCount"));
                Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set totalOrgasmCount: " + character.getTotalOrgasmCount());
            }

            // Fluids stored:
            if (loader.moveToIfHasChildren("fluidsStoredMap")) {
                loader.forEachAttributeOfElement("entry", "orifice", orifice -> {
                    SexAreaOrifice ot = SexAreaOrifice.valueOf(orifice);
                    loader.forEachAttributeOfElement("fluidStored", "charactersFluidID", charactersFluidID -> {
                        character.addFluidStored(ot, LoadFluidStored.loadFluidStored(loader, charactersFluidID, log));
                    });
                });
                loader.goToParent();
            }

            // Partner sex count:
            if (!Main.isVersionOlderThan(version, "0.3.7.6")) { // New version of sex stat tracking:
                if (loader.moveToIfHasChildren("sexPartners")) {
                    loader.forEachAttributeOfElement("sexCount", "id", partnerId -> {
                        character.sexCount.put(partnerId, loadSexCount(loader, log));
                    });
                    loader.goToParent();
                }
            } else { // Old version of sex stat tracking (old 'sexPartnerMap' is not loaded as it wasn't working correctly...):
                if (loader.moveToIfHasChildren("sexPartnerCount")) {
                    loader.forEachAttributeOfElement("sexPartner", "partner", sexPartner -> {
                        SexCount count = character.getSexCount(sexPartner);
                        count.setSexConsensualCount(loader.getCurrentElementAttributeAsInt("sexConsensualCount"));
                        count.setSexAsSubCount(loader.getCurrentElementAttributeAsInt("sexAsSubCount"));
                        count.setSexAsDomCount(loader.getCurrentElementAttributeAsInt("sexAsDomCount"));
                    });
                    loader.goToParent();
                }
                // Cum counts:
                if (loader.moveToNode("cumCounts")) {
                    if (Main.isVersionOlderThan(version, "0.3.5.2")) { // Old version support:
                        loader.forEachAttributeIntOfElement("cumCountGiving", "count", count -> {
                            SexType sexType = loadSexTypeLegacy(loader, false);
                            character.setCumCount(null, sexType, character.getCumCount(null, sexType) + count);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added cum count:" + loader.getCurrentElementAttributeAsStr("penetrationType") +
                                    " " + loader.getCurrentElementAttributeAsStr("orificeType") + " x " + count);
                        });
                        loader.forEachAttributeIntOfElement("cumCountReceiving", "count", count -> {
                            SexType sexType = loadSexTypeLegacy(loader, true);
                            character.setCumCount(null, sexType, character.getCumCount(null, sexType) + count);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added cum count:" + loader.getCurrentElementAttributeAsStr("penetrationType") +
                                    " " + loader.getCurrentElementAttributeAsStr("orificeType") + " x " + count);
                        });
                    } else {
                        loader.forEachAttributeOfElement("cumCount", "pt", participantType -> {
                            String performer = loader.getCurrentElementAttributeAsStr("p");
                            String performee = loader.getCurrentElementAttributeAsStr("t");
                            SexType sexType = getSexType(participantType, performer, performee);
                            int count = loader.getCurrentElementAttributeAsInt("c");
                            character.setCumCount(null, sexType, character.getCumCount(null, sexType) + count);
                        });
                    }
                    loader.goToParent();
                }
                // Sex counts:
                if (loader.moveToIfHasChildren("sexCounts")) {
                    if (Main.isVersionOlderThan(version, "0.3.5.2")) { // Old version support:
                        loader.forEachAttributeIntOfElement("sexCountGiving", "count", count -> {
                            String participantType = loader.getCurrentElementAttributeAsStr("participantType");
                            String penetrationType = loader.getCurrentElementAttributeAsStr("penetrationType");
                            String orificeType = loader.getCurrentElementAttributeAsStr("orificeType");
                            SexType sexType = getSexType(participantType, penetrationType, orificeType);
                            character.setSexCount(null, sexType, character.getSexCount(null, sexType) + count);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added sex count:" + penetrationType + " " + orificeType + " x " + count);
                        });
                        loader.forEachAttributeIntOfElement("sexCountReceiving", "count", count -> {
                            String participantType = loader.getCurrentElementAttributeAsStr("participantType");
                            String penetrationType = loader.getCurrentElementAttributeAsStr("penetrationType");
                            String orificeType = loader.getCurrentElementAttributeAsStr("orificeType");
                            SexType sexType = getSexType(participantType, orificeType, penetrationType);
                            character.setSexCount(null, sexType, character.getSexCount(null, sexType) + count);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added sex count:" + orificeType + " " + penetrationType + " x " + count);
                        });
                    } else {
                        loader.forEachAttributeIntOfElement("sexCount", "count", count -> {
                            String participantType = loader.getCurrentElementAttributeAsStr("pt");
                            String performer = loader.getCurrentElementAttributeAsStr("p");
                            String performee = loader.getCurrentElementAttributeAsStr("t");
                            SexType sexType = getSexType(participantType, performer, performee);
                            character.setSexCount(null, sexType, character.getSexCount(null, sexType) + count);
                            Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added sex count:" + performer + " " + performee + " x " + count);
                        });
                    }
                    loader.goToParent();
                }
            }

            // Virginity losses:
            if (Main.isVersionOlderThan(version, "0.2.10.1")) {
                if (loader.moveToIfHasChildren("virginityTakenBy")) {
                    loader.forEachAttributeOfElement("virginity", "takenBy", takenBy -> {
                        String participantType = loader.getCurrentElementAttributeAsStr("participantType");
                        String performer = loader.getCurrentElementAttributeAsStr("penetrationType");
                        String performee = loader.getCurrentElementAttributeAsStr("orificeType");
                        SexType sexType = getSexType(participantType, performer, performee);
                        character.setVirginityLoss(sexType, "", takenBy);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added virginity loss:" + performer + " " + performee + " (taken by:" + takenBy + ")");
                    });
                    loader.goToParent();
                }
            } else if (Main.isVersionOlderThan(version, "0.3.7.6")) {
                if (loader.moveToIfHasChildren("virginityTakenBy")) {
                    loader.forEachAttributeOfElement("penetrationTypeVirginity", "takenBy", takenBy -> {
                        String participantType = loader.getCurrentElementAttributeAsStr("participantType");
                        String performer = loader.getCurrentElementAttributeAsStr("penetrationType");
                        String performee = loader.getCurrentElementAttributeAsStr("orificeType");
                        String takenDescription = loader.getCurrentElementAttributeAsStr("takenDescription");
                        SexType sexType = getSexType(participantType, performer, performee);
                        character.setVirginityLoss(sexType, takenBy, takenDescription);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added virginity loss:" + performer + " " + performee + " (taken by:" + takenBy + ")");

                    });
                    loader.forEachAttributeOfElement("orificeTypeVirginity", "takenBy", takenBy -> {
                        String participantType = loader.getCurrentElementAttributeAsStr("participantType");
                        String performer = loader.getCurrentElementAttributeAsStr("penetrationType");
                        String performee = loader.getCurrentElementAttributeAsStr("orificeType");
                        String takenDescription = loader.getCurrentElementAttributeAsStr("takenDescription");
                        SexType sexType = getSexType(participantType, performee, performer);
                        character.setVirginityLoss(sexType, takenBy, takenDescription);
                        Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added virginity loss:" + performee + " " + performer + " (taken by:" + takenBy + ")");
                    });
                    loader.goToParent();
                }
            } else {
                if (loader.moveToIfHasChildren("virginityLosses")) {
                    loader.forEachAttributeOfElement("sexType", "takenBy", takenBy -> {
                        String takenDescription = loader.getCurrentElementAttributeAsStr("takenDescription");
                        SexType sexType = loadSexType(loader);
                        character.setVirginityLoss(sexType, takenBy, takenDescription);
                    });
                    loader.goToParent();
                }
            }

            if (loader.moveToIfHasChildren("virginityLossesBackup")) {
                loader.forEachAttributeOfElement("sexType", "takenBy", takenBy -> {
                    String takenDescription = loader.getCurrentElementAttributeAsStr("takenDescription");
                    SexType sexType = loadSexType(loader);
                    character.setBackupVirginityLoss(sexType, takenDescription);
                });
                loader.goToParent();
            }
            loader.goToParent();
        }

        // ************** Addictions **************//

//		Element characterAddictions = doc.createElement("addictions");
//		properties.appendChild(characterAddictions);
//		for(Addiction add : addictions) {
//			add.saveAsXML(characterAddictions, doc);
//		}
//
//		XMLUtil.addAttribute(doc, characterAddictions, "alcoholLevel", String.valueOf(alcoholLevel));
//
//		Element psychoactives = doc.createElement("psychoactiveFluids");
//		properties.appendChild(psychoactives);
//		for(FluidType ft : this.getPsychoactiveFluidsIngested()) {
//			Element element = doc.createElement("fluid");
//			psychoactives.appendChild(element);
//			XMLUtil.addAttribute(doc, element, "value", ft.toString());
//		}

        if (loader.moveToIfHasChildren("addictionsCore")) {
            character.alcoholLevel = loader.getCurrentElementAttributeAsFloat("alcoholLevel");
            if (loader.moveToIfHasChildren("psychoactiveFluids")) {
                loader.forEachAttributeOfElement("fluid", "value", fluidType -> {
                    character.addPsychoactiveFluidIngested(FluidType.getFluidTypeFromId(fluidType));
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Added psychoactive fluid:" + fluidType);
                });
                loader.goToParent();
            }
            // Old addiction support:
            if (loader.moveToIfHasChildren("addictionSatisfiedMap")) {
                loader.forEachAttributeOfElement("addiction", "type", fluidType -> {
                    long val = loader.getCurrentElementAttributeAsLong("value");
                    character.setLastTimeSatisfiedAddiction(FluidType.getFluidTypeFromId(fluidType), val);
                    Main.game.getCharacterUtils().appendToImportLog(log, "<br/>Set satisfied time:" + fluidType + " " + val);
                });
                loader.goToParent();
            }
            // New addiction:
            if (loader.moveToIfHasChildren("addictions")) {
                loader.forEachAttributeOfElement("addiction", "fluid", addictedFluid -> {
                    character.addAddiction(loadAddiction(loader));
                });
                loader.goToParent();
            }
            loader.goToParent();
        }


        // ************** Artwork **************//

        // Initialize artworks (name and femininity must be set at this point)
        character.loadImages();

        if (character.hasArtwork() && Main.getProperties().hasValue(PropertyValue.artwork)) {
            // Retrieve overrides for artist and image index
            int artistIndex = -1, imageIndex = -1;
            if (loader.moveToIfHasChildren("artwork")) {
                artistIndex = loader.getElementAttributeAsInt("overrideArtist", "index", -1);
                imageIndex = loader.getElementAttributeAsInt("overrideImage", "index", -1);
                loader.goToParent();
            }
            // Apply override indices
            if (artistIndex > -1)
                character.setArtworkIndex(artistIndex);
            if (imageIndex > -1)
                character.getCurrentArtwork().setIndex(imageIndex);

            // Cache current image
            ImageCache.INSTANCE.requestCache(character.getCurrentArtwork().getCurrentImage());
        }


        // ************** Version Overrides **************//

        if (Main.isVersionOlderThan(version, "0.2.10") && !character.isPlayer()) {
            PerkManager.initialisePerks(character, true);

            // All non-unique characters are muggers or prostitutes as of version 0.2.10:
            if (!character.isUnique() && character.getHistory() != Occupation.NPC_PROSTITUTE) {
                character.setHistory(Occupation.NPC_MUGGER);
            }
        }

        if (Main.isVersionOlderThan(version, "0.3.3.6")) {
            boolean impSlayer = character.getAttributeValue(Attribute.DAMAGE_IMP) >= 100;
            character.resetPerksMap(true, !character.isUnique());
            PerkManager.initialiseSpecialPerksUponCreation(character);
            if (impSlayer) {
                character.addSpecialPerk(Perk.IMP_SLAYER);
            }
        }

        if (Main.isVersionOlderThan(version, "0.4.9.2")) {
            if (character.getTrueSubspecies().isDoesNotAge() && character.getAgeAppearanceAbsolute() == 0) {
                character.setAgeAppearanceAbsolute(character.getAppearsAsAgeValue());
            }
        }

        character.calculateStatusEffects(0);
        character.recalculateSleepHours();
        for (String moveId : movesToEquip) { // Equip moves after status effects have been calculated:
            character.equipMove(moveId);
        }

        character.health = newHealth;
        character.mana = newMana;
        if (loader.debugMode) Logger.logTest("Finished Loading Character Variables for : " + character.getId());
        Library.loadedNPCs.add(character.getClass().getSimpleName());
    }

    private static Addiction loadAddiction(XMLLoader loader)  {
        Set<String> IDs = new HashSet<>();
//        loader.forEachAttributeOfElement("providerIDs", "value", v -> IDs.add(v));
        if (loader.moveToIfHasChildren("providerIDs")) {
            loader.forEachAttributeOfElement("id", "value", v -> IDs.add(v));
            loader.goToParent();
        }

        return new Addiction(FluidType.getFluidTypeFromId(loader.getCurrentElementAttributeAsStr("fluid")),
                loader.getCurrentElementAttributeAsLong("lastTimeSatisfied"),
                IDs);
    }

    private static SexCount loadSexCount(XMLLoader loader, StringBuilder log) {
        SexCount count = new SexCount();
        count.setSexConsensualCount(loader.getCurrentElementAttributeAsInt("consensual"));
        count.setSexAsSubCount(loader.getCurrentElementAttributeAsInt("asSub"));
        count.setSexAsDomCount(loader.getCurrentElementAttributeAsInt("asDom"));

        loader.forEachAttributeOfElement("sexType", "participant", participantType -> {
            SexType st = loadSexType(loader);
            count.incrementSexCount(st, loader.getCurrentElementAttributeAsInt("sex"));
            count.incrementCumCount(st, loader.getCurrentElementAttributeAsInt("cum"));
        });
        return count;
    }

    private static SexType loadSexType(XMLLoader loader)  {
        String participantType = loader.getCurrentElementAttributeAsStr("participant");
        if (participantType.isEmpty()) {
            return loadSexTypeLegacy(loader, false);
        }
        String performer = loader.getCurrentElementAttributeAsStr("self");
        if (performer.isEmpty()) {
            performer = loader.getCurrentElementAttributeAsStr("penetrationType");
        }
        String performee = loader.getCurrentElementAttributeAsStr("other");
        if (performee.isEmpty()) {
            performee = loader.getCurrentElementAttributeAsStr("orificeType");
        }
        return getSexType(participantType, performer, performee);
    }

    private static SexType getSexType(String participantType, String performer, String performee) {

        SexAreaInterface selfArea = null;
        SexAreaInterface otherArea = null;
        for (SexAreaOrifice value : SexAreaOrifice.values()) {
            if (value.toString().equalsIgnoreCase(performer)) selfArea = value;
            if (value.toString().equalsIgnoreCase(performee)) otherArea = value;
        }
        for (SexAreaPenetration value : SexAreaPenetration.values()) {
            if (value.toString().equalsIgnoreCase(performer)) selfArea = value;
            if (value.toString().equalsIgnoreCase(performee)) otherArea = value;
        }
        return new SexType(SexParticipantType.valueOf(participantType), selfArea, otherArea);
    }

    private static SexType loadSexTypeLegacy(XMLLoader loader, boolean reverseParticipants) {
        String participantType = loader.getCurrentElementAttributeAsStr("participant");
        if (participantType.isEmpty()) {
            participantType = loader.getCurrentElementAttributeAsStr("SexParticipantType");
        }
        String performer = loader.getCurrentElementAttributeAsStr("self");
        if (performer.isEmpty()) {
            performer = loader.getCurrentElementAttributeAsStr(reverseParticipants ? "orificeType" : "penetrationType");
        }
        String performee = loader.getCurrentElementAttributeAsStr("other");
        if (performee.isEmpty()) {
            performee = loader.getCurrentElementAttributeAsStr(reverseParticipants ? "penetrationType" : "orificeType");
        }
        return getSexType(participantType, performer, performee);
    }


    private static void doRetrogen(PlayerCharacter character, String version) {
        if (!Main.isVersionOlderThan(version, Main.VERSION_NUMBER)) return;

        if(Main.isVersionOlderThan(version, "0.3.0.5")) {
            // Reset player's demon parts to human if prior to 0.3.0.5:
            if(character.getArmType().getRace()== Race.DEMON) {
                character.setArmType(ArmType.HUMAN);
            }
            if(character.getAssType().getRace()==Race.DEMON) {
                character.setAssType(AssType.HUMAN);
            }
            if(character.getBreastType().getRace()==Race.DEMON) {
                character.setBreastType(BreastType.HUMAN);
            }
            if(character.getEarType().getRace()==Race.DEMON) {
                character.setEarType(EarType.HUMAN);
            }
            if(character.getEyeType().getRace()==Race.DEMON) {
                character.setEyeType(EyeType.HUMAN);
            }
            if(character.getFaceType().getRace()==Race.DEMON) {
                character.setFaceType(FaceType.HUMAN);
            }
            if(character.getHairType().getRace()==Race.DEMON) {
                character.setHairType(HairType.HUMAN);
            }
            if(character.getHornType().getRace()==Race.DEMON) {
                character.setHornType(HornType.NONE);
            }
            if(character.getLegType().getRace()==Race.DEMON) {
                character.setLegType(LegType.HUMAN);
            }
            if(character.getPenisType().getRace()==Race.DEMON) {
                character.setPenisType(PenisType.HUMAN);
            }
            if(character.getTorsoType().getRace()==Race.DEMON) {
                character.setTorsoType(TorsoType.HUMAN);
            }
            if(character.getTailType().getRace()==Race.DEMON) {
                character.setTailType(TailType.NONE);
            }
            if(character.getVaginaType().getRace()==Race.DEMON) {
                character.setVaginaType(VaginaType.HUMAN);
            }
            if(character.getWingType().getRace()==Race.DEMON) {
                character.setWingType(WingType.NONE);
            }
            character.setSubspeciesOverride(null);
            character.getBody().calculateRace(character);
        }

        if(Main.isVersionOlderThan(version, "0.3.3.5")) {
            character.equipBasicCombatMoves();
        }

        if(Main.isVersionOlderThan(version, "0.3.4")) {
            character.ageAppearanceDifference = -Game.TIME_SKIP_YEARS;
        }

        if(Main.isVersionOlderThan(version, "0.3.7.9") && character.hasQuest(QuestLine.ROMANCE_NATALYA)) {
            character.removeQuest(QuestLine.ROMANCE_NATALYA);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaVisited, false);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaInterviewOffered, false);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.natalyaBusy, false);
        }

        if(Main.isVersionOlderThan(version, "0.3.8") && character.isHasSlaverLicense()) {
            character.addItem(Main.game.getItemGen().generateItem(ItemType.SLAVER_LICENSE), false);
        }

        if(Main.isVersionOlderThan(version, "0.3.8.1")) {
            if(character.hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED)) {
                character.removeItemByType(ItemType.NATALYA_BUSINESS_CARD);

            } else if(character.isQuestProgressGreaterThan(QuestLine.ROMANCE_HELENA, Quest.ROMANCE_HELENA_3_B_EXTERIOR_DECORATOR)
                    && !character.hasItemType(ItemType.NATALYA_BUSINESS_CARD)
                    && !character.hasItemType(ItemType.NATALYA_BUSINESS_CARD_STAMPED)) {
                character.addItem(Main.game.getItemGen().generateItem(ItemType.NATALYA_BUSINESS_CARD), false);
            }
        }

        if(Main.isVersionOlderThan(version, "0.4.1.8")) {
            if(!character.hasItemType("innoxia_quest_clothing_keys")) {
                character.addItem(Main.game.getItemGen().generateItem("innoxia_quest_clothing_keys"), false);
            }
        }

        if(Main.isVersionOlderThan(version, "0.4.4.2")) {
            if(character.getQuest(QuestLine.MAIN)==Quest.MAIN_1_J_ARTHURS_ROOM) {
                character.setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE);
            }
        }

        if(Main.isVersionOlderThan(version, "0.4.8.10") && character.isQuestCompleted(QuestLine.SIDE_EISEK_STALL)) {
            character.removeItem(Main.game.getItemGen().generateItem("dsg_quest_fabricbolt"), 1);
            character.removeItem(Main.game.getItemGen().generateItem("dsg_quest_embsign"), 1);
            character.removeItem(Main.game.getItemGen().generateItem("dsg_quest_awningpoles"), 4);
        }
    }

    public static Litter getLitterFromXML(XMLLoader loader)  {
        String loadedId = loader.getCurrentElementAttributeAsStr("id");
        String motherId = loader.getCurrentElementAttributeAsStr("motherId");
        String fatherId = loader.getCurrentElementAttributeAsStr("fatherId");
        String incubatorId = loader.getCurrentElementAttributeAsStr("incubatorId");
        String fertilisationType = loader.getCurrentElementAttributeAsStr("fertilisationType");

        List<String> offspring = new ArrayList<>();
        if (loader.moveToIfHasChildren("offspringList")) {
            loader.forEachAttributeOfElement("offspring", "id", offspringID -> offspring.add(offspringID));
            loader.goToParent();
        }

        AbstractSubspecies motherRace = Subspecies.HUMAN;
        AbstractSubspecies fatherRace = Subspecies.HUMAN;
        motherRace = Subspecies.getSubspeciesFromId(loader.getCurrentElementAttributeAsStr("motherRace"));
        fatherRace = Subspecies.getSubspeciesFromId(loader.getCurrentElementAttributeAsStr("fatherRace"));

        LocalDateTime loadedConceptionDate;
        LocalDateTime loadedBirthDate;
        LocalDateTime loadedIncubationStartDate = null;

        int day = loader.getElementAttributeAsInt("dayOfConception", -1);
        Month month = Month.valueOf(loader.getElementAttributeAsStr("monthOfConception", "value", "MAY"));
        int year = loader.getElementAttributeAsInt("yearOfConception");
        int birthDay = loader.getElementAttributeAsInt("dayOfBirth");
        Month birthMonth = Month.valueOf(loader.getElementAttributeAsStr("monthOfBirth", "value", "MAY"));
        int birthYear = loader.getElementAttributeAsInt("yearOfBirth");
        if (day != -1) {
            loadedConceptionDate = LocalDateTime.of(year, month, day, 12, 0);
            loadedBirthDate = LocalDateTime.of(birthYear, birthMonth, birthDay, 12, 0);
        } else {
            int conceptionDayOffset = loader.getCurrentElementAttributeAsInt("dayOfConception");
            int birthDayOffset = loader.getCurrentElementAttributeAsInt("dayOfBirth");
            loadedConceptionDate = Main.game.getStartingDate().plusDays(conceptionDayOffset);
            loadedBirthDate = Main.game.getStartingDate().plusDays(birthDayOffset);
        }

        int dayOfIncubationStart = loader.getElementAttributeAsInt("dayOfIncubationStart", -1);
        Month monthOfIncubationStart = Month.valueOf(loader.getElementAttributeAsStr("monthOfIncubationStart", "value", "MAY"));
        int yearOfIncubationStart = loader.getElementAttributeAsInt("yearOfIncubationStart");

        if (dayOfIncubationStart != -1)
            loadedIncubationStartDate = LocalDateTime.of(yearOfIncubationStart, monthOfIncubationStart, dayOfIncubationStart, 12, 0);

        String birthedDescription = loader.getCurrentElementAttributeAsStr("birthedDescription");

        Litter litter = new Litter(loadedId,
                loadedConceptionDate,
                loadedBirthDate,
                motherId,
                fatherId,
                FertilisationType.NORMAL,
                loader.getCurrentElementAttributeAsInt("sonsMother"),
                loader.getCurrentElementAttributeAsInt("daughtersMother"),
                loader.getCurrentElementAttributeAsInt("sonsFather"),
                loader.getCurrentElementAttributeAsInt("daughtersFather"),
                offspring,
                motherRace,
                fatherRace,
                birthedDescription);

        if(!incubatorId.isEmpty()) {
            litter.setIncubatorId(incubatorId);
        }

        if(!fertilisationType.isEmpty()) {
            litter.setFertilisationType(FertilisationType.valueOf(fertilisationType));
        }

        if(loadedIncubationStartDate != null) {
            litter.setIncubationStartDate(loadedIncubationStartDate);
        }

        return litter;
    }

    public static OffspringSeed loadOffspringSeedFromXML(XMLLoader loader) {
        StringBuilder sb = new StringBuilder();
        OffspringSeed os = new OffspringSeed();

        if (loader.moveToIfHasChildren("data")) {
            String loadedCharacterId = loader.getElementAttributeAsStr( "id");
            if (!loadedCharacterId.isEmpty()) os.setId(loadedCharacterId);
            os.setFromPlayer(loader.getElementAttributeAsBoolean("fromPlayer"));
            os.setBorn(loader.getElementAttributeAsBoolean("born"));

            // Name:
            String nameElementValue = loader.getElementAttributeAsStr("name");
            if (!nameElementValue.isEmpty()) {
                os.setName(new NameTriplet(nameElementValue));
            } else {
                String nameMasculine = loader.getElementAttributeAsStr("name","nameMasculine");
                String nameAndrogynous = loader.getElementAttributeAsStr("name","nameAndrogynous");
                String nameFeminine = loader.getElementAttributeAsStr("name","nameFeminine");
                NameTriplet backup = Name.getRandomTriplet(Subspecies.HUMAN);
                os.setName(new NameTriplet(
                        nameMasculine.isEmpty() ? backup.getMasculine() : nameMasculine,
                        nameAndrogynous.isEmpty() ? backup.getAndrogynous() : nameAndrogynous,
                        nameFeminine.isEmpty() ? backup.getFeminine() : nameFeminine));
            }

            // Surname:
            String surname = loader.getElementAttributeAsStr("surname");
            if (!surname.isEmpty()) {
                os.setSurname(surname);
            }
            os.setDescription(loader.getElementAttributeAsStr("description"));
        }

        // Birthday:
        int day = loader.getCurrentElementAttributeAsInt("dayOfBirth");
        Month month = Month.valueOf(loader.getCurrentElementAttributeAsStr("monthOfBirth"));
        int year = loader.getCurrentElementAttributeAsInt("yearOfBirth");

        os.setBirthday(LocalDateTime.of(year, month, Math.min(month.maxLength(), day), 12, 0));

        // ************** Body **************//
        os.subspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("body", "subspecies"));

        os.body = LoadBody.loadBody(loader, sb);
        os.body.calculateRace(null);

        // ************** Family **************//

        if(loader.moveToIfHasChildren("family")) {
            os.setMother(loader.getElementAttributeAsStr("motherId"));
            os.motherName = loader.getElementAttributeAsStr("motherName");
            os.motherFemininity = Femininity.valueOf(loader.getElementAttributeAsStr("motherFemininity"));
            os.motherSubspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("motherSubspecies"));

            os.setFather(loader.getElementAttributeAsStr("fatherId"));
            os.fatherName = loader.getElementAttributeAsStr("fatherName");
            os.fatherFemininity = Femininity.valueOf(loader.getElementAttributeAsStr("fatherFemininity"));
            os.fatherSubspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("fatherSubspecies"));

            if(!loader.getElementAttributeAsStr("incubatorId").isEmpty()) {
                os.setIncubator(loader.getElementAttributeAsStr("incubatorId"));
            }
            os.incubatorName = loader.getElementAttributeAsStr("incubatorName");
            os.incubatorFemininity = Femininity.valueOf(loader.getElementAttributeAsStr("incubatorFemininity"));
            os.incubatorSubspecies = Subspecies.getSubspeciesFromId(loader.getElementAttributeAsStr("incubatorSubspecies"));

            day = loader.getElementAttributeAsInt("dayOfConception");
            month = Month.valueOf(loader.getElementAttributeAsStr("monthOfConception"));
            year = loader.getElementAttributeAsInt("yearOfConception");

            os.setConceptionDate(LocalDateTime.of(year, month, day, 12, 0));
        }

        return os;
    }
}
