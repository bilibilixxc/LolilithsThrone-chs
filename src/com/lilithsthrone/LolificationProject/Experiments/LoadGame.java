package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.*;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.FluidGirlCum;
import com.lilithsthrone.game.character.body.FluidMilk;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCRegistry;
import com.lilithsthrone.game.character.npc.dominion.*;
import com.lilithsthrone.game.character.npc.fields.*;
import com.lilithsthrone.game.character.npc.misc.*;
import com.lilithsthrone.game.character.npc.submission.*;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityCategory;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.AbstractDialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueFlags;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntry;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ.EnforcerHQDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpCitadelDialogue;
import com.lilithsthrone.game.dialogue.places.submission.impFortress.ImpFortressDialogue;
import com.lilithsthrone.game.dialogue.places.submission.ratWarrens.RatWarrensDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.ParserTarget;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEvent;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEventTag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.*;
import com.lilithsthrone.world.places.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static com.lilithsthrone.game.Game.TIME_SKIP_YEARS;
import static com.lilithsthrone.world.Cell.refundMoney;

public class LoadGame {
    public static void loadGameFromXML(File xmlFile) {
        long timeTest = System.nanoTime();
        XMLLoader loader = XMLLoader.load(xmlFile, false);

        Main.game.loadingVersion = loader.getNextElementAttributeAsStr("coreInfo", "version");
        Main.game.id = loader.getCurrentElementAttributeAsLong("id");
        Main.game.lastAutoSaveTime = loader.getCurrentElementAttributeAsLong("lastAutoSaveTime");
        Main.game.secondsPassed = loader.getCurrentElementAttributeAsLong("secondsPassed",
                loader.getCurrentElementAttributeAsLong("minutesPassed") * 60);
        Main.game.nextStormTimeInSeconds = loader.getCurrentElementAttributeAsLong("nextStormTimeInSeconds",
                loader.getCurrentElementAttributeAsLong("nextStormTime") * 60);
        Main.game.gatheringStormDurationInSeconds = loader.getCurrentElementAttributeAsInt("gatheringStormDurationInSeconds",
                loader.getCurrentElementAttributeAsInt("gatheringStormDuration") * 60);
        Main.game.weatherTimeRemainingInSeconds = loader.getCurrentElementAttributeAsInt("weatherTimeRemainingInSeconds",
                loader.getCurrentElementAttributeAsInt("weatherTimeRemaining") * 60);
        Main.game.currentWeather = Weather.valueOf(loader.getCurrentElementAttributeAsStr("weather"));

        int yearOfStart = loader.getElementAttributeAsInt("date", "year");
        if (yearOfStart > 0) {
            int dayOfMonth = loader.getElementAttributeAsInt("date", "dayOfMonth");
            int hour = loader.getElementAttributeAsInt("date", "hour");
            int minute = loader.getElementAttributeAsInt("date", "minute");
            int month = loader.getElementAttributeAsInt("date", "month");
            Main.game.startingDate = LocalDateTime.of(yearOfStart, month, dayOfMonth, hour, minute);
        }

        if (loader.moveToIfHasChildren("savedInventories")) {
            loader.forEachAttributeOfElement("savedInventory", "character", charId -> {
                CharacterInventory.loadingFromFloorBackupCheck = false;
                CharacterInventory inventory = LoadInventory.loadInventoryFromXML(loader);
                Main.game.savedInventories.put(charId, inventory);
            });
            loader.goToParent();
        }


        Main.game.savedEnforcers = new HashMap<>();
        if (loader.moveToIfHasChildren("savedEnforcers")) {
            loader.forEachAttributeOfElement("world", "type", worldType -> {
                List<List<String>> loadedEnforcers = new ArrayList<>();
                while (loader.moveToNode("enforcers", true)) {
                    List<String> enforcers = new ArrayList<>(2);
                    loader.forEachTextContent("id", enfId -> {
                        if (!enfId.isEmpty()) {
                            enforcers.add(enfId);
                        }
                    });
//                    loader.goToParent();
                    loadedEnforcers.add(enforcers);
                }
                loader.goToParent();
                Main.game.savedEnforcers.put(WorldType.getWorldTypeFromId(worldType), loadedEnforcers);
            });
            loader.goToParent();
        }

        LoadOccupancy.loadOccupancy(loader);

        if (loader.moveToIfHasChildren("dialogueFlags")) {
            Main.game.dialogueFlags = loadDialogueFlags(loader);
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("eventLog")) {
            loader.forEachAttributeOfElement("eventLogEntry", "name", eventLogName -> {
                String description = loader.getCurrentElementAttributeAsStr("description");
                Main.game.addEvent(new EventLogEntry(eventLogName, description), false);
            });
            loader.goToParent();
        }

        if (loader.moveToIfHasChildren("slaveryEventLog")) {
            loader.forEachAttributeIntOfElement("day", "value", dayValue -> {
                Main.game.slaveryEventLog.push(new Util.Value<>(dayValue, new ArrayList<>()));
                loader.forEachAttributeOfElement("eventLogEntry", "slaveID", slaveID -> {
                    Main.game.addSlaveryEvent(dayValue, loadSlaveryLogEntry(loader));
                });
            });
            loader.goToParent();
        }


        // Maps:
        if (loader.moveToIfHasChildren("maps")) {
            loader.forEachAttributeOfElement("world", "worldType", worldType -> {
                if(shouldLoad(worldType)) { // Skip removed worlds
                    World world = loadWorld(loader, worldType);
                    if (loader.debugMode) Logger.logTest("Finished Loading World : " + worldType);
                    Main.game.worlds.put(world.getWorldType(), world);
                }
            });
            loader.goToParent();
        }

        // Add missing world types:
        Generation gen = new Generation();
//				if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.1.99.5")) { 
//					Main.game.getWorlds().put(WorldType.SHOPPING_ARCADE, gen.worldGeneration(WorldType.SHOPPING_ARCADE));
//				}
        handleRetrogen(gen);
        for(AbstractWorldType wt : WorldType.getAllWorldTypes()) {
            if(Main.game.worlds.get(wt)==null) {
                Main.game.getWorlds().put(wt, gen.worldGeneration(wt));
            }
        }
        handleRetrogenPart2(gen);

//        loader.setDebugMode(true);
        if (loader.moveToIfHasChildren("playerCharacter")) {
            Main.game.player = CharacterLoader.loadPlayer(loader);
            loader.goToParent();
        }

        // Load NPCs:
        Map<String, Class<? extends NPC>> npcClasses = new ConcurrentHashMap<>();
        Map<Class<? extends NPC>, Method> loadFromXMLMethods = new ConcurrentHashMap<>();
        Map<Class<? extends NPC>, Constructor<? extends NPC>> constructors = new ConcurrentHashMap<>();
        int totalNpcCount = 0;
        while (loader.moveToNode("NPC", true)) {
            totalNpcCount++;
            loader.moveToNode("character");
            loader.moveToNode("core");

            String id = loader.getElementAttributeAsStr("id");
            String className = loader.getElementAttributeAsStr("pathName");
            loader.goToParent();
            if (Main.game.NPCMap.containsKey(id)) {
                if (!id.contains("Helena")) {
                    Logger.logTest("duplicate character attempted to be imported");
                    continue;
                }
            }
            className = repairRetroNames(className);

            if(!Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.9") || !id.contains("Helena")) {
                if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.9")) {
                    className = className.replace("Alexa", "Helena");
                }
                if (loader.debugMode) Logger.logTest("Loading NPC : " + id);
                NPC npc = loadNPC(className, loader, npcClasses, loadFromXMLMethods, constructors); // @Keldon Alternative is use loader.clone()
                if(npc!=null)  {
                    if(!Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.11.5")
                            || (npc.getClass()!= DarkSiren.class
                            && npc.getClass()!= FortressAlphaLeader.class
                            && npc.getClass()!= FortressMalesLeader.class
                            && npc.getClass()!= FortressFemalesLeader.class)) {
                        Main.game.safeAddNPC(npc, true);
                    }

                    // To fix issues with older versions hair length:
                    if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.1.90.5")) {
                        npc.getBody().getHair().setLength(null, npc.isFeminine()? RacialBody.valueOfRace(npc.getRace()).getFemaleHairLength():RacialBody.valueOfRace(npc.getRace()).getMaleHairLength());
                    }
                    // Generate desires in non-unique NPCs:
                    if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.1.98.5") && !npc.isUnique() && npc.getFetishDesireMap().isEmpty()) {
                        Main.game.getCharacterUtils().generateDesires(npc);
                    }

                    if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.0") && npc.getFetishDesireMap().size()>10) {
                        npc.clearFetishDesires();
                        Main.game.getCharacterUtils().generateDesires(npc);
                    }
                    if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.4") && npc.getWorldLocation()==WorldType.GAMBLING_DEN) {
                        if(npc instanceof Roxy) {
                            npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_TRADER, true);
                        } else if(npc instanceof Axel) {
                            npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_ENTRANCE, true);
                        } else if(npc instanceof Epona) {
                            npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE, true);
                        } else {
                            npc.setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_GAMBLING, true);
                        }
                    }

                    // Assign parser target:
                    if(npc.getParserTarget()!=null) {
                        ParserTarget.addAdditionalParserTarget(npc.getParserTarget(), npc);
                    }


                } else {
                    Logger.logTest("LOADNPC returned null: "+id);
                    Logger.logTest("CLASS: " + className);
                }
            }
            loader.goToParent();
        }
//        Element gameElement = null;
        // Load offspringSeed:
//        loader.setDebugMode(true);
        while (loader.moveToNode("OffspringSeed", true)) {
            try {
                Main.game.addOffspringSeed(CharacterLoader.loadOffspringSeedFromXML(loader), true);
            } catch (Exception ignored) {} // Angry Keldon Noises
        }

        int OSConversions = 0;
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.2.4")) {
            for(NPC npc: Main.game.getAllNPCs()) {
                if(npc instanceof NPCOffspring &&
                        npc.getLocationPlace().getPlaceType()==PlaceType.GENERIC_HOLDING_CELL &&
                        npc.getHomeLocationPlace().getPlaceType()==PlaceType.GENERIC_HOLDING_CELL) {
                    // Remove this npc and replace with offspringSeed.
                    // new OffspringSeed(npc) calls the following methods to do this:
                    // Main.game.getOffspring().remove(npc);
                    // Main.game.removeNPC(npc);
                    new OffspringSeed(npc);
                    OSConversions++;
                }
            }
        }

        // Add in new NPCS:
        NPCRegistry.initUniqueNPCs(true);

        //TODO This needs more thorough testing...
        // In versions prior to v0.4.1, deleted NPCs who had relationship or sex data with the player were moved to an empty tile instead of being deleted.
        // This was causing save file bloat, so now they are fully deleted.
        for(NPC npc: Main.game.getAllNPCs()) {
            if(npc!=null && npc.getLocationPlace().getPlaceType() == PlaceType.GENERIC_EMPTY_TILE && npc.isReadyToBeDeleted()) {
                Main.game.banishNPC(npc);
            }
        }

        doOldVersionCleanup();

        Main.game.pendingSlaveInStocksReset = false;

        if(Main.game.getNpc(GenericAndrogynousNPC.class)==null) { // If was accidentally deleted in version 0.2.10:
            try {
                Main.game.addNPC(new GenericAndrogynousNPC(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Main.game.getPlayer().updateLocationListeners();

        Main.game.setRenderMap(true);
        Main.game.setRenderAttributesSection(true);

        Main.game.setRequestAutosave(false);

        DialogueNode startingDialogueNode = Main.game.getPlayerCell().getDialogue(false);
        Main.game.addEvent(new EventLogEntry("[style.colourGood(Game loaded)]", "data/saves/"+Util.getFileName(xmlFile)+".xml"), false);
        Main.game.setStarted(true); // Set started before setting content so that it parses correctly (as the scripting engine is initialised fully in the setStarted() method).
        Main.game.setContent(new Response("", startingDialogueNode.getDescription(), startingDialogueNode), false);

        Main.game.occupancyUtil.updateSlavesResting(Main.game.getHourOfDay()); // Makes sure that resting slaves are correctly accounted for
        Main.game.endTurn(0);

        // Do a zero-time status effect update after declaring that the game has started to make sure that everything is initialised properly (mainly just so external status effects are initialised):
        for(NPC npc : Main.game.getAllNPCs()) {
            npc.updateInventoryListeners();
            npc.updateAttributeListeners(true);
            npc.calculateStatusEffects(0);
        }
        Main.game.getPlayer().updateInventoryListeners();
        Main.game.getPlayer().updateAttributeListeners(true);
        Main.game.getPlayer().calculateStatusEffects(0);

        Main.getProperties().addAllDiscoveredFromCurrentPlayer();
        Main.saveProperties();
        Logger.logTime("Save Loading V2 took", System.nanoTime() - timeTest);
    }

    private static void doOldVersionCleanup() {
        if (!Main.isVersionOlderThan(Main.game.loadingVersion, Main.VERSION_NUMBER)) return;

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.8")) { // Fix for incorrect positioning bug in an old version:
            Main.game.getNpc(Jules.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_ENTRANCE);
            Main.game.getNpc(Kruger.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_VIP_AREA);
            Main.game.getNpc(Kalahari.class).setLocation(WorldType.NIGHTLIFE_CLUB, PlaceType.WATERING_HOLE_BAR);
        }

        // To prevent errors from previous versions, reset Zaranix progress if prior to 0.1.95:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.1.90.5")) {
            if(Main.game.getPlayer().getWorldLocation() == WorldType.ZARANIX_HOUSE_GROUND_FLOOR
                    || Main.game.getPlayer().getWorldLocation() == WorldType.ZARANIX_HOUSE_FIRST_FLOOR) {
                Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);

                ZaranixHomeGroundFloor.resetHouseAfterLeaving();
                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixDiscoveredHome, false);
                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKickedDownDoor, false);
                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixKnockedOnDoor, false);
                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixMaidsHostile, false);

                Main.game.getNpc(Arthur.class).setLocation(WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);

                if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
                    Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE);
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.1.95")) {
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)) {
                Main.game.getNpc(Arthur.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, true);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.4.5")) { //Try to remove unused NPCs TODO better method to delete all banished NPCs
            for(NPC npc : Main.game.getAllNPCs()) {
                if(!npc.isUnique() && npc.getWorldLocation()==WorldType.EMPTY) {
                    Main.game.banishNPC(npc);
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.5.1")) { //Reset ass/nipple/lip colours
            for(NPC npc : Main.game.getAllNPCs()) {
                if(!npc.isSlave() || (npc.getOwner()!=null && !npc.getOwner().isPlayer()))
                    npc.setSkinCovering(new Covering(npc.getTorsoCovering(), npc.getCovering(npc.getTorsoCovering()).getPrimaryColour()), true);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.5")) { //Add milking rooms

            Cell[][] grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCellGrid();
            for(int i=0 ; i<grid.length ; i++) {
                for(int j=0 ; j<grid[0].length ; j++) {
                    if(grid[i][j].getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
                        Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, new Vector2i(i, j)));
                    }
                }
            }
            grid = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCellGrid();
            for(int i=0 ; i<grid.length ; i++) {
                for(int j=0 ; j<grid[0].length ; j++) {
                    if(grid[i][j].getPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
                        Main.game.getOccupancyUtil().addMilkingRoom(new MilkingRoom(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, new Vector2i(i, j)));
                    }
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12.6")) { //Reset imp fortresses
            ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
            ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
            ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);

            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressAlphaBossEncountered, false);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressFemalesBossEncountered, false);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.impFortressMalesBossEncountered, false);

            if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_ALPHA) {
                Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_ALPHA);

            } else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_FEMALES) {
                Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_FEMALES);

            } else if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_MALES) {
                Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_MALES);
            }

            Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
            Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
            Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12.6")
                || (Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.0.5") && !Main.game.getNpc(DarkSiren.class).isSlave() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated))) {
            if(Main.game.getPlayer().getWorldLocation()==WorldType.IMP_FORTRESS_DEMON) {
                Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_IMP_FORTRESS_DEMON);
            }
            ImpCitadelDialogue.resetFortress();
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12.9")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()) && npc.getHomeWorldLocation()==WorldType.DOMINION && !npc.hasJob()) {
                    npc.assignNewJob();
                }
                // Set surname to be the same as their mother's line:
                if(npc.getSurname()==null || npc.getSurname().isEmpty()) {
                    GameCharacter mother = npc.getMother();
                    if(mother!=null) {
                        while(mother.getMother()!=null) {
                            mother = mother.getMother();
                        }
                        if(mother.getSurname()!=null && !mother.getSurname().isEmpty()) {
                            npc.setSurname(mother.getSurname());
                        }
                    }
                }
            }
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.LYSSIETHS_RING));
            }
            if(Main.game.getPlayer().hasClothingType(ClothingType.getClothingTypeFromId("innoxia_neck_key_chain"), true)) {
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
            }
            while(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY))>1) {
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY));
            }
            while(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2))>1) {
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_2));
            }
            while(Main.game.getPlayer().getItemCount(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3))>1) {
                Main.game.getPlayer().removeItem(Main.game.getItemGen().generateItem(ItemType.IMP_FORTRESS_ARCANE_KEY_3));
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3")) {
            if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.impFortressDemonDefeated)) {
                Main.game.getNpc(Takahashi.class).setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL);
            }
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
                Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL);
                if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_LILIN_PALACE)
                        || Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_LILIN_PALACE_GATE)){
                    Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_LILIN_PALACE_CAVERN);
                }
            }
            Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(PlaceType.LILAYA_HOME_LIBRARY).getInventory().addItem(Main.game.getItemGen().generateItem(ItemType.getLoreBook(Subspecies.HALF_DEMON)));
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.0.5")) {
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
                ImpCitadelDialogue.clearFortress(true);
                ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_ALPHA);
                ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_FEMALES);
                ImpFortressDialogue.resetFortress(WorldType.IMP_FORTRESS_MALES);
                ((DarkSiren)Main.game.getNpc(DarkSiren.class)).postDefeatReset();
            }
        }

        // For affection/incest gains that I missed in v0.3 & v0.3.0.5:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.0.6")) {
            if(Main.game.getNpc(Lilaya.class).getRaceStage()== RaceStage.GREATER) {
                Main.game.getNpc(Lilaya.class).setAffection(Main.game.getNpc(Lyssieth.class), 75);
                Main.game.getNpc(Lilaya.class).incrementAffection(Main.game.getPlayer(), 50);
            }
            if(Main.game.getNpc(DarkSiren.class).getRaceStage()==RaceStage.GREATER) {
                Main.game.getNpc(DarkSiren.class).setAffection(Main.game.getNpc(Lyssieth.class), 75);
                Main.game.getNpc(DarkSiren.class).addFetish(Fetish.FETISH_INCEST);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.1.2")
                && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)
                && !Main.game.getNpc(Brax.class).getLocationPlace().getPlaceType().equals(PlaceType.ENFORCER_HQ_RECEPTION_DESK)) {
            // Move Brax to reception desk if he was incorrectly reset to his office (after fixing the bug where NPCs were disappearing):
            Main.game.getNpc(Brax.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK);

            // Remove all NPC offspring who are not related to the player:
            for(NPC npc : new HashSet<>(Main.game.getNPCMap().values())) {
                if(npc instanceof NPCOffspring) {
                    if(!npc.isRelatedTo(Main.game.getPlayer())) {
                        Main.game.banishNPC(npc);
                    }
                }
            }
        }

        // Fix overflow of clients in brothel:
        for(Cell cell : Main.game.getWorlds().get(WorldType.ANGELS_KISS_GROUND_FLOOR).getCells(PlaceType.ANGELS_KISS_BEDROOM)) {
            if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.1.7")) {
                for(NPC npc : Main.game.getCharactersPresent(cell)) {
                    if(npc instanceof GenericSexualPartner) {
                        Main.game.banishNPC(npc);
                    }
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.3.9")) { // Add in starting Elemental Perks that were overlooked.
            for(NPC npc : Main.game.getAllNPCs()) {
                if((npc.isElemental())) {
                    PerkManager.initialisePerks(npc);
                }
            }
            if(Main.game.getPlayer().removeTrait(Perk.JOB_ELDER_LILIN)) {
                Main.game.getPlayer().addTrait(Main.game.getPlayer().getHistory().getAssociatedPerk());
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.3.9")) { // Generate new Zaranix tile and remove Arthur tile (if applicable):
            if(!Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_H_THE_GREAT_ESCAPE)
                    && Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ZARANIX)==null) {
                ((Zaranix) Main.game.getNpc(Zaranix.class)).generateNewTile();
            }
            if(Main.game.getPlayer().isQuestProgressLessThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)
                    && Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR)!=null) {
                Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR).getPlace().setPlaceType(PlaceType.DOMINION_DEMON_HOME);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.4.1")) {
            Cell c = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR);
            if(c!=null) {
                while(c!=null) {
                    c.getPlace().setPlaceType(PlaceType.DOMINION_DEMON_HOME);
                    c = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_ARTHUR);
                }
                ((Arthur)Main.game.getNpc(Arthur.class)).generateNewTile();
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.4.5")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof DominionAlleywayAttacker && ((DominionAlleywayAttacker) npc).isStormAttacker()) {
                    Main.game.banishNPC(npc); // Catch for storm attackers who were stuck on a dominion street tile.

                } else {
                    if(!npc.isSlave() && !npc.isUnique() && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
                        npc.setHomeLocation();
                    }
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.4.9")) {
            if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
                Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory().clearNonEquippedInventory(false);
                PlaceType.ENFORCER_HQ_BRAXS_OFFICE.applyInventoryInit(Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory());
            }
            if(Main.game.getNpc(Brax.class).isSlave() && Main.game.getNpc(Brax.class).getOwner().isPlayer()) {
                if(Main.game.getNpc(Brax.class).isFeminine()) {
                    Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false);
                    Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false);
                    Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
                } else {
                    Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false);
                    Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_ssldshirt", PresetColour.CLOTHING_BLUE, false), false);
                    Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), false);
                }

                AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, null, false);
                jacket.setSticker("collar", "tab_ip");
                jacket.setSticker("name", "name_brax");
                jacket.setSticker("ribbon", "ribbon_brax");
                Main.game.getNpc(Brax.class).addClothing(jacket, false);

                Main.game.getNpc(Brax.class).addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false);

                AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
                hat.setSticker("badge", "badge_dominion");
                Main.game.getNpc(Brax.class).addClothing(hat, false);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.6")) {
            if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_BUYING_BRAX)
                    && (!Main.game.getNpc(Brax.class).isSlave() || !Main.game.getNpc(Brax.class).getOwner().isPlayer() || Main.game.getNpc(Brax.class).getLocationPlace().getPlaceType()==PlaceType.ENFORCER_HQ_RECEPTION_DESK)) {
                EnforcerHQDialogue.obtainBraxAsSlave();
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.6.6")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId()) && npc.getHistory().isLowlife()) {
                    npc.setHistory(Occupation.NPC_UNEMPLOYED);
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.7")) { // This map update is located here as the character present in the new alleyway tile needs to have been initialised.
            // Add safe alleyway:
            Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SLAVER_ALLEY).getLocation();
            vec.setY(vec.getY()+1);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BACK_ALLEYS_SAFE);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BACK_ALLEYS_SAFE.getName());
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);


            vec = new Vector2i(0, 3);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BACK_ALLEYS);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BACK_ALLEYS.getName());
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);

            vec = new Vector2i(0, 4);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BACK_ALLEYS);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BACK_ALLEYS.getName());
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);

            // Move mugger character out of safe alleyway and into new one:
            if(!Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_BACK_ALLEYS_SAFE)).isEmpty()) {
                GameCharacter character = Main.game.getCharactersTreatingCellAsHome(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_BACK_ALLEYS_SAFE)).get(0);
                character.setLocation(WorldType.DOMINION, vec, true);
            }
        }

        // Catch for pre-v0.3.7.3 versions where characters could end up not having their pregnancy possibilities cleared upon PREGNANT_0 removal:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.7.3")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(!npc.isPregnant() && !npc.hasStatusEffect(StatusEffect.PREGNANT_0)) {
                    npc.endPregnancy(false);
                }
            }
            if(!Main.game.getPlayer().isPregnant() && !Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_0)) {
                Main.game.getPlayer().endPregnancy(false);
            }
        }

        // Catch for pre-v0.3.8.2 versions where Thunder could have been deleted:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.8.2") && Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)) {
            try {
                GameCharacter thunder = Main.game.getNPCById(Main.game.getDialogueFlags().getSadistNatalyaSlave());
                thunder.setHomeLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_STABLES);
            } catch (Exception e) {
                Logger.logTest("So innoxia has decided that a try/catch is a normal piece of functioning code... Printing error anyway");
                e.printStackTrace();
                Main.game.getNpc(Natalya.class).getSlavesOwned().remove(Main.game.getDialogueFlags().getSadistNatalyaSlave()); // Make sure that she doesn't still count the deleted slave as hers.
                GameCharacter thunder = DominionExpress.spawnSlave(Main.game.getPlayer().getSexualOrientation().isAttractedToFeminine(), PresetColour.CLOTHING_BRONZE);
                DominionExpress.applySadistSlave(thunder);
                thunder.returnToHome();
            }
        }

        // Remove companions:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.8.6")) {
            if(Main.game.getPlayer().isElementalSummoned()) {
                Main.game.getPlayer().removeCompanion(Main.game.getPlayer().getElemental());
                Main.game.getPlayer().getElemental().returnToHome();
            }
            for(GameCharacter companion : new ArrayList<>(Main.game.getPlayer().getCompanions())) {
                Main.game.getPlayer().removeCompanion(companion);
                companion.returnToHome();
            }
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof EnforcerPatrol) {
                    Main.game.banishNPC(npc);
                }
            }
        }

        // Set Murk's milkers as loving him:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.9")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof RatWarrensCaptive) {
                    npc.setAffection(Main.game.getNpc(Murk.class), 100);
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.9.1")) {
            // Rat Warrens now always end up being raided:
            if(Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_END)
                    || Main.game.getPlayer().hasQuestInLine(QuestLine.SIDE_VENGAR, Quest.VENGAR_THREE_COOPERATION_END)) {
                RatWarrensDialogue.applyRatWarrensRaid();
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.9.3")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof DominionAlleywayAttacker && ((DominionAlleywayAttacker) npc).isStormAttacker()) {
                    Main.game.banishNPC(npc); // Catch for storm attackers who were stuck on a dominion street tile. (Again again...)
                }
                if(npc.getSubspecies()==Subspecies.IMP || npc.getSubspecies()==Subspecies.IMP_ALPHA) { // Fix issue with imps having an override of 'DEMON'
                    npc.setSubspeciesOverride(npc.getSubspecies());
                }
            }
        }

        // Resets for Nyan's expanded romance quest:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.14")) {
//					if(Main.game.getPlayer().hasQuest(QuestLine.RELATIONSHIP_NYAN_HELP)) {
            // Reset quest:
            Main.game.getPlayer().removeQuest(QuestLine.RELATIONSHIP_NYAN_HELP);

            // Reset player knowledge:
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanIntroduced, false);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, false);
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, false);
            Main.game.getPlayer().getCharactersEncountered().remove(Main.game.getNpc(Nyan.class).getId());
            Main.game.getPlayer().getCharactersEncountered().remove(Main.game.getNpc(SupplierPartner.class).getId());
            Main.game.getPlayer().getCharactersEncountered().remove(Main.game.getNpc(SupplierLeader.class).getId());
            Main.game.getNpc(SupplierLeader.class).setPlayerKnowsName(false);
            Main.game.getNpc(SupplierPartner.class).setPlayerKnowsName(false);

            // Reset Nyan:
            Main.game.getNpc(Nyan.class).clearAffectionMap();
            if(Main.game.getNpc(Nyan.class).isPregnant()) {
                Main.game.getNpc(Nyan.class).endPregnancy(false);
            }
            Main.game.getNpc(Nyan.class).setStartingBody(true);
            Main.game.getNpc(Nyan.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
            Main.game.getNpc(Nyan.class).getSexCountMap().clear();
            Main.game.getPlayer().getSexCountMap().put(Main.game.getNpc(Nyan.class).getId(), new SexCount());

            // Reset Supplier leader:
            Main.game.getNpc(SupplierLeader.class).clearAffectionMap();
            Main.game.getNpc(SupplierLeader.class).setStartingBody(true);
            if(Main.game.getNpc(SupplierLeader.class).isPregnant()) {
                Main.game.getNpc(SupplierLeader.class).endPregnancy(false);
            }
            Main.game.getNpc(SupplierLeader.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
            Main.game.getNpc(SupplierLeader.class).getSexCountMap().clear();
            Main.game.getPlayer().getSexCountMap().put(Main.game.getNpc(SupplierLeader.class).getId(), new SexCount());
            Main.game.getNpc(SupplierLeader.class).setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OVERSEER_STATION, true);

            // Reset Supplier partner:
            Main.game.getNpc(SupplierPartner.class).clearAffectionMap();
            Main.game.getNpc(SupplierPartner.class).setStartingBody(true);
            if(Main.game.getNpc(SupplierPartner.class).isPregnant()) {
                Main.game.getNpc(SupplierPartner.class).endPregnancy(false);
            }
            Main.game.getNpc(SupplierPartner.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
            Main.game.getNpc(SupplierPartner.class).getSexCountMap().clear();
            Main.game.getPlayer().getSexCountMap().put(Main.game.getNpc(SupplierPartner.class).getId(), new SexCount());
            Main.game.getNpc(SupplierPartner.class).setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OVERSEER_STATION, true);

            // Apply starting affections:
            Main.game.getNpc(SupplierLeader.class).setAffection(Main.game.getNpc(SupplierPartner.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
            Main.game.getNpc(SupplierPartner.class).setAffection(Main.game.getNpc(SupplierLeader.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());

//					}
            // Move out of supplier den:
            if(Main.game.getPlayer().getWorldLocation()==WorldType.TEXTILES_WAREHOUSE) {
                Main.game.getPlayer().setLocation(WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_ENTRANCE);
            }
            Main.game.getPlayer().getWorldsVisited().remove(WorldType.TEXTILES_WAREHOUSE);
            // Remove supplier den tile:
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.15")) {
            Main.game.getNpc(Nyan.class).setAffection(Main.game.getNpc(Ashley.class), AffectionLevel.POSITIVE_TWO_LIKE.getMedianValue());
            Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }

        NPC lyssieth = Main.game.getNpc(Lyssieth.class);
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.17")
                && Main.game.getPlayer().getTrueRace()== Race.DEMON) { // Players could only become a demon via Lyssieth before 0.3.17, so it's ok to just check for true race being demon
            if(lyssieth.getAffection(Main.game.getPlayer())<75) {
                lyssieth.setAffection(Main.game.getPlayer(), 75);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.17")) { // Reset home locations:
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof EnforcerPatrol) {
                    npc.setHomeLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_CELLS_OFFICE);
                }
            }
            Main.game.getNpc(Wes.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
            Main.game.getNpc(Elle.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_REQUISITIONS, true);
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.0.5")) { // Reset Meraxis sex counts:
            Main.game.getPlayer().getSexCountMap().remove(Main.game.getNpc(DarkSiren.class).getId());
            Main.game.getNpc(Lilaya.class).getSexCountMap().remove(Main.game.getNpc(DarkSiren.class).getId());
            Main.game.getNpc(Lyssieth.class).getSexCountMap().remove(Main.game.getNpc(DarkSiren.class).getId());
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.5") && Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_B_DEMON_HOME)) {
            Main.game.getNpc(Felicia.class).setPlayerKnowsName(true); // If progressed past meeting Felicia, set her name as known
            Main.game.getPlayer().addCharacterEncountered(Main.game.getNpc(Felicia.class));
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.5")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof FieldsBandit && !npc.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
                    Main.game.banishNPC(npc); // Catch for fields bandits who were stuck in the fields (happened if player escaped from combat with them)
                }
            }
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.1")) { // NPCs generated before this version were affected by the game's 3-year time skip at the start, so were older than intended
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc.isUnique()) {
                    npc.setBirthday(npc.getBirthday().plusYears(TIME_SKIP_YEARS));
                }
            }
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.2.2")) {
            Main.game.getNpc(Ashley.class).clearAffectionMap();
            Main.game.getNpc(Ashley.class).setAffection(Main.game.getNpc(Nyan.class), AffectionLevel.POSITIVE_ONE_FRIENDLY.getMedianValue());
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.3.1")) { // Update Dominion to add Callie's bakery:
            Vector2i vec = new Vector2i(2, 18);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_CALLIE_BAKERY);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_CALLIE_BAKERY.getName());
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.4.2")) {
            // Player perk resets:
            Main.game.getPlayer().resetPerksMap(false);
            if(!Main.game.getPlayer().hasPerkAnywhereInTree(Perk.SPECIAL_PLAYER)) {
                Main.game.getPlayer().addSpecialPerk(Perk.SPECIAL_PLAYER);
            }

            // NPC perk resets:
            for(NPC npc : Main.game.getAllNPCs()) {
                if(!(npc.isElemental())) {
                    npc.resetPerksMap(true, false);
                }
                PerkManager.initialiseSpecialPerksUponCreation(npc); // Generate unique perks for slaves/occupants as well
            }

            // Arthur's room change:
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE)) {
                if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).isEmpty()) {
                    Cell arthurRoom = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).get(0);
                    arthurRoom.removePlaceUpgrade(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
                    if(arthurRoom.getLocation().getX()>2 && arthurRoom.getLocation().getX()<8 && arthurRoom.getLocation().getY()>2 && arthurRoom.getLocation().getY()<8) {
                        arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR);
                    } else {
                        arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR);
                    }
                } else if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).isEmpty()) {
                    Cell arthurRoom = Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_ARTHUR_ROOM).get(0);
                    arthurRoom.removePlaceUpgrade(PlaceUpgrade.LILAYA_ARTHUR_ROOM);
                    if(arthurRoom.getLocation().getX()>2 && arthurRoom.getLocation().getX()<8 && arthurRoom.getLocation().getY()>2 && arthurRoom.getLocation().getY()<8) {
                        arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR);
                    } else {
                        arthurRoom.getPlace().setPlaceType(PlaceType.LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR);
                    }
                }
                Cell c = Lab.addArthurRoom();
                Main.game.getNpc(Arthur.class).setLocation(c, true);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.4.3")) {
            if(Main.game.getPlayer().hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4) && Main.game.getPlayer().getTrueRace()==Race.DEMON) {
                Main.game.getPlayer().removeSpecialPerk(Perk.POWER_OF_LYSSIETH_4);
                Main.game.getPlayer().addSpecialPerk(Perk.POWER_OF_LYSSIETH_4_DEMON);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.5.7")) {
            // Moving Ursa, Aurokaris, and Lunexis to their proper tiles:
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_3_E_THEMISCYRA_ATTACK)) {
                Main.game.getNpc(Ursa.class).setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_queen", true);
                Main.game.getNpc(Aurokaris.class).setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_aurokaris", true);
                Main.game.getNpc(Lunexis.class).setLocation("innoxia_fields_elis_amazon_camp", "innoxia_fields_elis_amazon_camp_lunexis", true);
            }

            // Moving clubber NPCs to the newly-added club holding cell:
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof DominionClubNPC) {
                    if(!npc.getHomeLocationPlace().getPlaceType().equals(PlaceType.DOMINION_BOULEVARD)) {
                        npc.setHomeLocation(WorldType.EMPTY, PlaceType.GENERIC_CLUB_HOLDING_CELL);
                    }
                    if(npc.getWorldLocation()==WorldType.EMPTY) {
                        npc.returnToHome();
                    }
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.6.1") && Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_SLIME_QUEEN)) {
            Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem("dsg_quest_hazmat_rat_card"), false);
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.6.6") && Main.game.getPlayer().getTrueRace()==Race.DEMON) {
            Main.game.getDialogueFlags().setFlag("innoxia_child_of_lyssieth", true); // Players could only become a demon via Lyssieth before v0.4.6.6, so set the flag to represent this
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.2")) {
            Main.game.getNpc(Lunexis.class).setAffection(Main.game.getPlayer(), -100);
            Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(DarkSiren.class), -100);
            Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(Aurokaris.class), -100);
            Main.game.getNpc(Lunexis.class).setAffection(Main.game.getNpc(Ursa.class), -100);
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.9")) {
            Main.game.getNpc(Pix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_shopping_arcade_gym"), PlaceType.getPlaceTypeFromId("innoxia_dominion_shopping_arcade_gym_reception"), true);
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.7.11")) {
            if(Main.game.getPlayer().getWorldLocation() == WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) {
                Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_tavern_taur"), false);
            }
            Main.game.getNpc(Kheiron.class).setLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_bar"), true);
            if(Main.game.getNpc(Fae.class).getWorldLocation()==WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")) {
                Main.game.getNpc(Fae.class).setRandomLocation(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_seating"), false);
                Main.game.getNpc(Silvia.class).setLocation(Main.game.getNpc(Fae.class));
            }
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.8.10")) {
            List<NPC> charactersInStable = Main.game.getCharactersPresent(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_tavern_taur_stable"));
            boolean steropePresent = charactersInStable.contains(Main.game.getNpc(Sterope.class));
            int charactersToBanish = charactersInStable.size() - (steropePresent?2:0);
            for(GameCharacter c : charactersInStable) {
                if(charactersToBanish<=0) {
                    break;
                }
                if(c instanceof GenericSexualPartner) {
                    Main.game.banishNPC((NPC) c);
                    charactersToBanish--;
                }
            }
        }
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9")) {
            // Generate Lovienne's Luxuries:
            if(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_DEMON_HOME_SEX_SHOP)==null) {
                Vector2i towerLoc = new Vector2i(Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_LILITHS_TOWER).getLocation());
                towerLoc.setX(towerLoc.getX()+2);
                Main.game.getWorlds().get(WorldType.DOMINION).getCell(towerLoc).setPlace(new GenericPlace(PlaceType.DOMINION_DEMON_HOME_SEX_SHOP), false);
            }
        }
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.2")) {
            // Set all elementals to look the same age as their summoners:
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc instanceof Elemental) {
                    npc.setAgeAppearanceAbsolute(((Elemental)npc).getSummoner().getAppearsAsAgeValue());
                }
            }
        }

        // Reset doll fluids to their correct types:
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.8")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(npc.isDoll()) {
                    npc.getBody().getBreast().setMilk(new FluidMilk(FluidType.MILK_DOLL, false));
                    npc.getBody().getBreastCrotch().setMilk(new FluidMilk(FluidType.MILK_DOLL, true));
                    npc.getBody().getPenis().getTesticle().setCum(new FluidCum(FluidType.CUM_DOLL));
                    npc.getBody().getVagina().setGirlcum(new FluidGirlCum(FluidType.GIRL_CUM_DOLL));
                }
            }
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.9")) { // Remove excess consoles
            int count = Main.game.getPlayer().getItemCount(ItemType.DOLL_CONSOLE);
            for(int i=0; i<count-1; i++) {
                Main.game.getPlayer().removeItemByType(ItemType.DOLL_CONSOLE);
                Main.game.getPlayer().incrementMoney(MiscDialogue.deckCost);
            }
        }


        if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.9.10")) {
            for(NPC npc : Main.game.getAllNPCs()) {
                if(!npc.isSlave()) {
                    for(int i=0; i<24; i++) {
                        if(!npc.getSlaveJob(i).hasFlag(SlaveJobFlag.GUEST_CAN_WORK)) {
                            npc.setSlaveJob(i, SlaveJob.IDLE);
                        }
                    }
                }
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.9.11")) {
            if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_2_C_SIRENS_FALL)) {
                ImpFortressDialogue.clearFortress(WorldType.IMP_FORTRESS_ALPHA);
                ImpFortressDialogue.clearFortress(WorldType.IMP_FORTRESS_FEMALES);
                ImpFortressDialogue.clearFortress(WorldType.IMP_FORTRESS_MALES);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.10.8")) {
            Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dressingRoomAutoClean, true);
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.10.10")) {
            if(!Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCells(PlaceUpgrade.LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE).isEmpty()
                    || !Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCells(PlaceUpgrade.LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE).isEmpty()) {
                Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated, true);
            }
        }

        // Add cafe slaves to Finch's owned slaves:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.11.3")) {
            for(NPC npc :Main.game.getAllNPCs()) {
                if(npc instanceof SlaveForSale
                        && !npc.isSlave()
                        && !Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())
                        && (npc.getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE
                        || npc.getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_2
                        || npc.getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_3
                        || npc.getLocationPlaceType()==PlaceType.SLAVER_ALLEY_CAFE_4)) {
                    Main.game.getNpc(Finch.class).addSlave(npc);
                }
            }
        }

        // Fix bug where personalities were all deleted:
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.11.8")) {
            if(!Main.game.getNpc(Lilaya.class).isShy()) { // Lilaya's personality was deleted, so reset all:
                for(NPC npc : Main.game.getAllNPCs()) {
                    if(npc.isUnique()) {
                        npc.setStartingPersona(true, false, false, false, false);

                    } else { // Regenerate personalities
                        // Starting personalities based on race, copied from GameCharacter.additionalBodySetup():
                        for(Map.Entry<PersonalityTrait, Float> entry : npc.getRace().getRacialBody().getPersonalityTraitChances().entrySet()) {
                            double rnd = Math.random();
                            if(rnd<=entry.getValue()) {
                                npc.addPersonalityTrait(entry.getKey());
                            }
                        }

                        for(Map.Entry<PersonalityTrait, Float> entry : npc.getTrueSubspecies().getPersonalityTraitChances().entrySet()) {
                            double rnd = Math.random();
                            if(rnd<=entry.getValue()) {
                                npc.addPersonalityTrait(entry.getKey());
                            }
                        }

                        if(npc.hasPersonalityTrait(PersonalityTrait.MUTE)) { // If mute, remove all other speech traits
                            npc.removePersonalityTraits(PersonalityCategory.SPEECH);
                            npc.addPersonalityTrait(PersonalityTrait.MUTE);
                        }

                        // Roughly copying effects from CharacterUtils' setHistoryAndPersonality():
                        if(npc.getHistory()==Occupation.NPC_PROSTITUTE) {
                            npc.removePersonalityTrait(PersonalityTrait.PRUDE);
                            npc.removePersonalityTrait(PersonalityTrait.INNOCENT);
                        }
                        if(npc.getHistory().isLowlife()) {
                            if(Math.random()<0.25f) {
                                npc.addPersonalityTrait(PersonalityTrait.SLOVENLY);
                            }
                        }
                    }
                }
            }
        }

    }

    private static String repairRetroNames(String className) {
        if (!Main.isVersionOlderThan(Main.game.loadingVersion, Main.VERSION_NUMBER)) return className;

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.4")) {
            int lastIndex = className.lastIndexOf('.');
            if(className.substring(lastIndex-3, lastIndex).equals("npc")) {
                className = className.substring(0, lastIndex) + ".misc" + className.substring(lastIndex, className.length());
            }
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.1.5")) {
            className = className.replace("SubmissionCitadelArcanist", "Takahashi");
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4")) {
            className = className.replace("BatMorphCavernAttacker", "BatCavernLurkerAttacker");
            className = className.replace("SlimeCavernAttacker", "BatCavernSlimeAttacker");
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3")) {
            className = className.replace("FortressDemonLeader", "DarkSiren");
        }
        return className;
    }

    private static World loadWorld(XMLLoader loader, String worldType){
        AbstractWorldType abstractWorldType = WorldType.EMPTY;
        abstractWorldType = WorldType.getWorldTypeFromId(worldType);

        int width = loader.getCurrentElementAttributeAsInt("width");
        int height = loader.getCurrentElementAttributeAsInt("height");
        Cell[][] newGrid = new Cell[width][height];
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                newGrid[i][j] = new Cell(abstractWorldType, new Vector2i(i, j));
                newGrid[i][j].getPlace().setPlaceType(PlaceType.GENERIC_IMPASSABLE);
            }
        }

        if (loader.moveToIfHasChildren("grid")) {
            if (loader.debugMode) Logger.logTest("Start Loading Grid");
            while (loader.moveToNode("cell", true)) {
                Cell c = loadCell(loader, abstractWorldType);
                newGrid[c.getLocation().getX()][c.getLocation().getY()] = c;
            }
            loader.goToParent();
            if (loader.debugMode) Logger.logTest("Finished Loading Grid");
            loader.goToParent();
        }

        return new World(width, height, newGrid, abstractWorldType);
    }

    private static Cell loadCell(XMLLoader loader, AbstractWorldType abstractWorldType) {

        int x = loader.getElementAttributeAsInt("location", "x");
        int y = loader.getElementAttributeAsInt("location", "y");
        String placeType = loader.getElementAttributeAsStr("place", "type");
        Cell cell = new Cell(abstractWorldType, new Vector2i(x, y));

        cell.setDiscovered(loader.getCurrentElementAttributeAsBoolean("discovered"));
        if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) {
            cell.setTravelledTo(loader.getCurrentElementAttributeAsBoolean("discovered"));
        } else {
            cell.setTravelledTo(loader.getCurrentElementAttributeAsBoolean("travelledTo", abstractWorldType.isRevealedOnStart()));
        }
        cell.setPlace(loadPlace(loader, placeType, cell), false);

        if (loader.hasChildren("characterInventory")) {
            cell.setInventory(LoadInventory.loadInventoryFromXML(loader));
            if(refundMoney > 0) {
                cell.getInventory().incrementMoney(refundMoney);
                refundMoney = 0;
            }
        }
//		cell.getInventory().setMaximumInventorySpace(CELL_MAXIMUM_INVENTORY_SPACE);
        return cell;
    }

    private static GenericPlace loadPlace(XMLLoader loader, String placeType, Cell cell) {
        AbstractPlaceType pt = PlaceType.getPlaceTypeFromId(placeType);
        GenericPlace place = new GenericPlace(pt);
        String placeName = loader.getCurrentElementAttributeAsStr("name");

        if(!placeName.isEmpty()
                && (!Main.isVersionOlderThan(Game.loadingVersion, "0.3")
                || (!place.getPlaceType().equals(PlaceType.DOMINION_EXIT_NORTH)
                && !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_SOUTH)
                && !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_EAST)
                && !place.getPlaceType().equals(PlaceType.DOMINION_EXIT_WEST)))) {
            String name = placeName;
            if(!pt.getName().equals(name)) {
                place.setName(name);
            }
        }
        if (loader.moveToIfHasChildren("placeUpgrades")) {
            List<AbstractPlaceUpgrade> coreUpgrades = new ArrayList<>();
            List<AbstractPlaceUpgrade> upgrades = new ArrayList<>();
            String upgradeType;
            while (!(upgradeType = loader.getNextElementAttributeAsStr("upgrade", "type")).isEmpty()) {
                if(upgradeType.equals("LILAYA_PLAYER_ROOM_BATH")) {
                    Cell.refundMoney += 300_000;
                } else {
                    AbstractPlaceUpgrade upgrade = PlaceUpgrade.getPlaceUpgradeFromId(upgradeType);
                    if(upgrade.isCoreRoomUpgrade()) {
                        coreUpgrades.add(upgrade);
                    } else {
                        upgrades.add(upgrade);
                    }
                }
            }
            place.getPlaceUpgrades().clear();

            // Add core upgrades first:
            for(AbstractPlaceUpgrade coreUpgrade : coreUpgrades) {
                if(coreUpgrade == PlaceUpgrade.LILAYA_EMPTY_ROOM && coreUpgrades.size() > 1) {
                    continue;
                }
                if(!place.getPlaceUpgrades().contains(coreUpgrade)) {
                    if(!place.addPlaceUpgrade(cell, coreUpgrade)) { // This line attempts to add the upgrade
                        Logger.logTest("WARNING: Import of GenericPlace (" + place.getPlaceType() + ") was unable to add core upgrade: " + coreUpgrade.getName());
                    } else {
                        break; // There should only be one core upgrade added.
                    }
                }
            }
            for(AbstractPlaceUpgrade upgrade : upgrades) {
                if(!place.getPlaceUpgrades().contains(upgrade)) {
                    if(!place.addPlaceUpgrade(cell, upgrade)) { // This line attempts to add the upgrade
                        Logger.logTest("WARNING: Import of GenericPlace (" + place.getPlaceType() + ") was unable to add upgrade: " + upgrade.getName());
                    }
                }
            }
            loader.goToParent();
        }

        return place;
    }

    private static boolean shouldLoad(String worldType) {// Say no to Removed Worlds
//                          && !worldType.equals("REBEL_BASE")
        return (!worldType.equals("SEWERS") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.0.5"))
                && (!worldType.equals("SUBMISSION") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.10.8"))
                && ((!worldType.equals("IMP_FORTRESS_ALPHA")
                && !worldType.equals("IMP_FORTRESS_FEMALES")
                && !worldType.equals("IMP_FORTRESS_MALES"))
                || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.11"))
                && (!worldType.equals("IMP_FORTRESS_DEMON") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12.5"))
                && (!worldType.equals("DOMINION") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.2"))
                && (!worldType.equals("HARPY_NEST") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.1.5"))
                && (!worldType.equals("BAT_CAVERNS") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.3.5"))
                && (!worldType.equals("LYSSIETH_PALACE") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.3"))
                && (!worldType.equals("RAT_WARRENS") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.6"))
                && (!worldType.equals("DOMINION_EXPRESS") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.7.9"))
                && (!worldType.equals("SHOPPING_ARCADE") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.14"))
                && (!worldType.equals("innoxia_fields_elis_market") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.1.1"))
                && (!worldType.equals("innoxia_fields_elis_tavern_alley") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.4"))
                && (!worldType.equals("innoxia_fields_themiscyra") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.4.5"))
                && (!worldType.equals("EMPTY") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.5.7"))
                && (!worldType.equals("SLAVER_ALLEY") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.5.7"))
                && (!worldType.equals("innoxia_fields_elis_market") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.8.7"))
                && (!worldType.equals("innoxia_dominion_sex_shop") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.9.12"))
                && (!worldType.equals("innoxia_dominion_sex_shop_factory") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.9.13"))
                && (!worldType.equals("BOUNTY_HUNTER_LODGE_UPSTAIRS") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.10.2"))
                && (!worldType.equals("innoxia_shinrin_highlands_hideout") || !Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.11.6"))
                && !worldType.equals("SUPPLIER_DEN") // Removed
                && !worldType.equals("JUNGLE");
    }

    private static SlaveryEventLogEntry loadSlaveryLogEntry(XMLLoader loader) {
        List<SlaveEventTag> loadedTags = new ArrayList<>();
        if (loader.moveToIfHasChildren("tags")) {
            loader.forEachAttributeOfElement("tag", "value", tagValue -> {
                if(tagValue.equals("JOB_LILAYA_MILK_SOLD")) {
                    tagValue = "JOB_MILK_SOLD";
                } else if(tagValue.equals("JOB_LILAYA_CUM_SOLD")) {
                    tagValue = "JOB_CUM_SOLD";
                }
                loadedTags.add(SlaveEventTag.valueOf(tagValue));
            });
            loader.goToParent();
        }

        List<String> loadedSlaveIDs = new ArrayList<>();
        if (loader.moveToIfHasChildren("involvedSlaves")) {
            loader.forEachTextContent("id", id -> {
                loadedSlaveIDs.add(id);
            });
            loader.goToParent();
        }

        List<String> loadedExtraEffects = new ArrayList<>();
        if (loader.moveToIfHasChildren("extraEffects")) {
            loader.forEachAttributeOfElement("entry", "value", entryValue -> {
                loadedExtraEffects.add(entryValue);
            });
            loader.goToParent();
        }

        SlaveryEventLogEntry entry = new SlaveryEventLogEntry(
                loader.getCurrentElementAttributeAsLong("time"),
                loader.getCurrentElementAttributeAsStr("slaveID"),
                loadedSlaveIDs,
                SlaveEvent.valueOf(loader.getCurrentElementAttributeAsStr("event")),
                loadedTags.isEmpty() ? loadedTags : null,
                loadedExtraEffects);

        boolean loadedSlaveDeleted = false;
        if(loadedSlaveDeleted = loader.getCurrentElementAttributeAsBoolean("slaveDeleted")) {
            String delName = loader.getElementAttributeAsStr("deletedInfo", "parsedName");
            String delDesc = loader.getElementAttributeAsStr("deletedInfo", "parsedDescription");
            entry.parsedName = delName;
            entry.parsedDescription = delDesc;
        }

        entry.slaveDeleted = loadedSlaveDeleted;
        return entry;
    }

    private static DialogueFlags loadDialogueFlags(XMLLoader loader) {
        DialogueFlags newFlags = new DialogueFlags();

        newFlags.ralphDiscount = loader.getElementAttributeAsInt("ralphDiscount");
        newFlags.scarlettPrice = loader.getElementAttributeAsInt("scarlettPrice");

        newFlags.offspringDialogueTokens = loader.getElementAttributeAsInt("offspringDialogueTokens");
        newFlags.slaveTrader = loader.getElementAttributeAsStr("slaveTrader");
        newFlags.managementCompanion = loader.getElementAttributeAsStr("slaveryManagerSlaveSelected");

        newFlags.eponaStamps = loader.getElementAttributeAsInt("eponaStamps");
        newFlags.helenaSlaveOrderDay = loader.getElementAttributeAsInt("helenaSlaveOrderDay", -1);
        newFlags.impCitadelImpWave = loader.getElementAttributeAsInt("impCitadelImpWave");
        newFlags.murkPlayerTfStage = loader.getElementAttributeAsInt("murkPlayerTfStage");
        newFlags.murkCompanionTfStage = loader.getElementAttributeAsInt("murkCompanionTfStage");
        newFlags.natalyaCollarColour = PresetColour.getColourFromId(loader.getElementAttributeAsStr("natalyaCollarColour"));
        if(newFlags.natalyaCollarColour==PresetColour.CLOTHING_STEEL) {
            newFlags.natalyaCollarColour = PresetColour.CLOTHING_BRONZE;
        }
        newFlags.natalyaPoints = loader.getElementAttributeAsInt("natalyaPoints");
        newFlags.sadistNatalyaSlave = loader.getElementAttributeAsStr("sadistNatalyaSlave");

        // Load saved longs:
        if(loader.moveToIfHasChildren("savedLongs")) {
            loader.forEachAttributeOfElement("save", "id", longID -> {
                long val = Long.parseLong(loader.getCurrentElementText());
                if(longID.equals("MACHINES") || longID.equals("INTERCOM")
                        || longID.equals("BUSINESS") || longID.equals("BOUNTY_HUNTERS")) {
                    longID = "KAY_" + longID;
                }
                newFlags.setSavedLong(longID, val);
            });
            loader.goToParent();
        } else { // Support for old timers (pre-version 0.3.9):
            newFlags.setSavedLong(Ralph.RALPH_DISCOUNT_TIMER_ID, loader.getElementAttributeAsLong("ralphDiscountStartTime"));
            newFlags.setSavedLong(Kalahari.KALAHARI_BREAK_TIMER_ID, loader.getElementAttributeAsLong("kalahariBreakStartTime"));
            newFlags.setSavedLong(Daddy.DADDY_RESET_TIMER_ID, loader.getElementAttributeAsLong("daddyResetTimer"));
            if(!Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) {
                newFlags.setSavedLong(ImpFortressDialogue.FORTRESS_ALPHA_CLEAR_TIMER_ID, loader.getElementAttributeAsLong("impFortressAlphaDefeatedTime"));
                newFlags.setSavedLong(ImpFortressDialogue.FORTRESS_FEMALES_CLEAR_TIMER_ID, loader.getElementAttributeAsLong("impFortressFemalesDefeatedTime"));
                newFlags.setSavedLong(ImpFortressDialogue.FORTRESS_MALES_CLEAR_TIMER_ID, loader.getElementAttributeAsLong("impFortressMalesDefeatedTime"));
            }
        }

        // Load flags:
        if (loader.moveToIfHasChildren("dialogueValues")) {
            loader.forEachAttributeOfElement("dialogueValue", "value", diaVal -> {
                if(diaVal.equalsIgnoreCase("punishedByAlexa")) {
                    newFlags.values.add(DialogueFlagValue.punishedByHelena);
                } else {
                    AbstractDialogueFlagValue flagValue = DialogueFlagValue.getDialogueFlagValueFromId(diaVal);
                    if(flagValue != null) {
                        newFlags.values.add(flagValue);
                    }
                }
            });
            loader.goToParent();
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.1")) {
            newFlags.values.remove(DialogueFlagValue.axelIntroduced);
            newFlags.values.remove(DialogueFlagValue.roxyIntroduced);
            newFlags.values.remove(DialogueFlagValue.eponaIntroduced);
        }

        if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11.5")) { // Add defeated flags so that the fortress will reset.
            newFlags.values.add(DialogueFlagValue.impFortressAlphaDefeated);
            newFlags.values.add(DialogueFlagValue.impFortressDemonDefeated);
            newFlags.values.add(DialogueFlagValue.impFortressFemalesDefeated);
            newFlags.values.add(DialogueFlagValue.impFortressMalesDefeated);
        }

        loadDialogueFlagSet(loader, newFlags.helenaConversationTopics, "helenaConversationTopics");
        loadDialogueFlagSet(loader, newFlags.reindeerEncounteredIDs, "reindeerEncounteredIDs");
        loadDialogueFlagSet(loader, newFlags.reindeerWorkedForIDs, "reindeerWorkedForIDs");
        loadDialogueFlagSet(loader, newFlags.reindeerFuckedIDs, "reindeerFuckedIDs");
        loadDialogueFlagSet(loader, newFlags.warehouseDefeatedIDs, "warehouseDefeatedIDs");

//		if(parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)!=null) {
//			for(int i=0; i<((Element) parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)).getElementsByTagName("location").getLength(); i++){
//				Element e = (Element) ((Element) parentElement.getElementsByTagName("supplierStorageRoomsChecked").item(0)).getElementsByTagName("location").item(i);
//
//				newFlags.supplierStorageRoomsChecked.add(
//						new Vector2i(
//								Integer.valueOf(e.getAttribute("x")),
//								Integer.valueOf(e.getAttribute("y"))));
//			}
//		}

        return newFlags;
    }

    private static void loadDialogueFlagSet(XMLLoader loader, Set<String> set, String title) {
        if (loader.hasChildren(title)) {
            loader.moveToNode(title);
            String valueSquared;
            while (!(valueSquared = loader.getNextElementAttributeAsStr("value", "value")).isEmpty()) {
                set.add(valueSquared);
            }
            loader.goToParent();
        }
    }

    private static void handleRetrogen(Generation gen) {
        if (!Main.isVersionOlderThan(Main.game.loadingVersion, Main.VERSION_NUMBER)) return;

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.1.5")) {
            Main.game.getWorlds().put(WorldType.HARPY_NEST, gen.worldGeneration(WorldType.HARPY_NEST));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.10.8")) {
            Main.game.getWorlds().put(WorldType.SUBMISSION, gen.worldGeneration(WorldType.SUBMISSION));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.11")) {
            Main.game.getWorlds().put(WorldType.IMP_FORTRESS_ALPHA, gen.worldGeneration(WorldType.IMP_FORTRESS_ALPHA));
            Main.game.getWorlds().put(WorldType.IMP_FORTRESS_FEMALES, gen.worldGeneration(WorldType.IMP_FORTRESS_FEMALES));
            Main.game.getWorlds().put(WorldType.IMP_FORTRESS_MALES, gen.worldGeneration(WorldType.IMP_FORTRESS_MALES));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.12.5")) {
            Main.game.getWorlds().put(WorldType.IMP_FORTRESS_DEMON, gen.worldGeneration(WorldType.IMP_FORTRESS_DEMON));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.2")) {
            Main.game.getWorlds().put(WorldType.DOMINION, gen.worldGeneration(WorldType.DOMINION));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.3.5")) {
            Main.game.getWorlds().put(WorldType.BAT_CAVERNS, gen.worldGeneration(WorldType.BAT_CAVERNS));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.8")) {
            Main.game.getWorlds().put(WorldType.NIGHTLIFE_CLUB, gen.worldGeneration(WorldType.NIGHTLIFE_CLUB));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.8.1")) {
            Main.game.getWorlds().put(WorldType.EMPTY, gen.worldGeneration(WorldType.EMPTY));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3")) {
            Main.game.getWorlds().put(WorldType.LYSSIETH_PALACE, gen.worldGeneration(WorldType.LYSSIETH_PALACE));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.2.2")) {
            Main.game.getWorlds().put(WorldType.CITY_HALL, gen.worldGeneration(WorldType.CITY_HALL));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.4.9")) {
            Main.game.getWorlds().put(WorldType.ENFORCER_WAREHOUSE, gen.worldGeneration(WorldType.ENFORCER_WAREHOUSE));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.4")) {
            Main.game.getWorlds().put(WorldType.GAMBLING_DEN, gen.worldGeneration(WorldType.GAMBLING_DEN));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.5.6")) {
            Main.game.getWorlds().put(WorldType.RAT_WARRENS, gen.worldGeneration(WorldType.RAT_WARRENS));
            Main.game.getWorlds().put(WorldType.SLAVER_ALLEY, gen.worldGeneration(WorldType.SLAVER_ALLEY));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.7")) {
            Main.game.getWorlds().put(WorldType.HELENAS_APARTMENT, gen.worldGeneration(WorldType.HELENAS_APARTMENT));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.7.9")) {
            Main.game.getWorlds().put(WorldType.DOMINION_EXPRESS, gen.worldGeneration(WorldType.DOMINION_EXPRESS));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.9.4")) {
            Main.game.getWorlds().put(WorldType.ENFORCER_HQ, gen.worldGeneration(WorldType.ENFORCER_HQ));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.0.5")) {
            Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_town"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")));
            Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_f1")));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.3.9")) {
            Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_alley")));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.5.7")) {
            Main.game.getWorlds().put(WorldType.EMPTY, gen.worldGeneration(WorldType.EMPTY));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.5.7")) {
            Main.game.getWorlds().put(WorldType.SLAVER_ALLEY, gen.worldGeneration(WorldType.SLAVER_ALLEY));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.7.11")) {
            Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_fields_elis_tavern_taur")));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.9.1")) {
            Main.game.getWorlds().put(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), gen.worldGeneration(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop")));
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.10.2")) {
            Main.game.getWorlds().put(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS, gen.worldGeneration(WorldType.BOUNTY_HUNTER_LODGE_UPSTAIRS));
        }

    }

    private static void handleRetrogenPart2(Generation gen) {
        if (!Main.isVersionOlderThan(Main.game.loadingVersion, Main.VERSION_NUMBER)) return;

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.6.9")) {
            // Add home improvements:
            Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SLAVER_ALLEY).getLocation();
            vec.setX(vec.getX()+1);
            vec.setY(vec.getY()-5);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_HOME_IMPROVEMENT);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_HOME_IMPROVEMENT.getName());
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);

            // Add warehouse district:
            vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SLAVER_ALLEY).getLocation();
            vec.setX(vec.getX()+1);
            vec.setY(vec.getY()+4);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_WAREHOUSES);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_WAREHOUSES.getName());
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).setDiscovered(true);

            // Add antiques:
            vec = Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(PlaceType.SHOPPING_ARCADE_NYANS_SHOP).getLocation();
            vec.setX(vec.getX()+1);
            vec.setY(vec.getY()-1);
            Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(vec).getPlace().setPlaceType(PlaceType.SHOPPING_ARCADE_ANTIQUES);
            Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(vec).getPlace().setName(PlaceType.SHOPPING_ARCADE_ANTIQUES.getName());
            Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE).getCell(vec).setDiscovered(true);

        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.2.4")) {
            AbstractItem spellBook = Main.game.getItemGen().generateItem(ItemType.getSpellBookType(Spell.ICE_SHARD));
            Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(PlaceType.LILAYA_HOME_ROOM_PLAYER).getInventory().addItem(spellBook);
        }
        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.3.8.8")) {
            List<Vector2i> vecs = Util.newArrayListOfValues(
                    new Vector2i(2, 5),
                    new Vector2i(5, 8),
                    new Vector2i(8, 5));
            for(Vector2i vec : vecs) {
                Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(vec).getPlace().setPlaceType(PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY);
                Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(vec).getPlace().setName(PlaceType.LILAYA_HOME_STAIR_UP_SECONDARY.getName());
                Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR).getCell(vec).setDiscovered(true);
                Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(vec).getPlace().setPlaceType(PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY);
                Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(vec).getPlace().setName(PlaceType.LILAYA_HOME_STAIR_DOWN_SECONDARY.getName());
                Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_FIRST_FLOOR).getCell(vec).setDiscovered(true);
            }
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.7.2")) {
            // Add shaft tile:
            Vector2i vec = Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(PlaceType.BAT_CAVERN_SLIME_QUEEN_LAIR).getLocation();
            vec.setY(vec.getY()-3);
            Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(vec).getPlace().setPlaceType(PlaceType.BAT_CAVERN_SHAFT);
            Main.game.getWorlds().get(WorldType.BAT_CAVERNS).getCell(vec).getPlace().setName(PlaceType.BAT_CAVERN_SHAFT.getName());
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.7.7")) {
            // Add bank to Dominion tile:
            Vector2i vec = Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_SHOPPING_ARCADE).getLocation();
            vec.setY(vec.getY()+2);
            vec.setX(vec.getX()-1);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setPlaceType(PlaceType.DOMINION_BANK);
            Main.game.getWorlds().get(WorldType.DOMINION).getCell(vec).getPlace().setName(PlaceType.DOMINION_BANK.getName());
        }

        if(Main.isVersionOlderThan(Main.game.loadingVersion, "0.4.10.8")) {
            // Replace tower in Elis with Yui's tower:
            Cell towerCell = Main.game.getWorlds().get(WorldType.getWorldTypeFromId("innoxia_fields_elis_town")).getClosestCell(new Vector2i(0, 0), PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_tower"));
            towerCell.getPlace().setPlaceType(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_tower_yui"));
            towerCell.getPlace().setName(PlaceType.getPlaceTypeFromId("innoxia_fields_elis_town_tower_yui").getName());
        }
    }
    public static NPC loadNPC(String className, XMLLoader loader,
                              Map<String, Class<? extends NPC>> classMap,
                              Map<Class<? extends NPC>, Method> loadFromXMLMethodMap,
                              Map<Class<? extends NPC>, Constructor<? extends NPC>> constructorMap){

        try {
            Class<? extends NPC> npcClass = classMap.get(className);
            if (npcClass == null) {
                npcClass = (Class<? extends NPC>) Class.forName(className);
                synchronized (npcClass) {
                    if(classMap.containsKey(className)){
                        npcClass = classMap.get(className);
                    } else {
                        classMap.putIfAbsent(className, npcClass);
                        Method m = npcClass.getMethod("loadFromXML", XMLLoader.class, CharacterImportSetting[].class);
                        loadFromXMLMethodMap.put(npcClass, m);

                        Constructor<? extends NPC> declaredConstructor = npcClass.getDeclaredConstructor(boolean.class);
                        constructorMap.put(npcClass, declaredConstructor);
                    }
                }
            }
            Constructor<? extends NPC> declaredConstructor = constructorMap.get(npcClass);
            if (declaredConstructor == null) {
                synchronized (npcClass) {
                    declaredConstructor = constructorMap.get(npcClass);
                }
            }
            NPC npc = declaredConstructor.newInstance(true);
            loadFromXMLMethodMap.get(npcClass).invoke(npc, loader, new CharacterImportSetting[] {});
            return npc;
        } catch(NoSuchMethodException nsme) {
            Logger.logTest("Couldn't find required method(loadFromXML or constructor(boolean)) for class: " + className);
            nsme.printStackTrace();
            return null;
        } catch(Exception ex) {
            Logger.logTest("Failed to load NPC class: "+className);
            ex.printStackTrace();
            return null;
        }
    }
}
