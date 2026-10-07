package com.lilithsthrone.LolificationProject.Overrides;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.Experiments.NMS.NPCModSys;
import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.LolificationProject.ModCommon.ExtraDialogue;
import com.lilithsthrone.LolificationProject.ModCommon.ModOptions;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.threading.PreInitializationThread;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.EXPERIMENTAL;
import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.MULTITHREADED_PRELOADING;
import static com.lilithsthrone.LolificationProject.Library.*;
import static com.lilithsthrone.LolificationProject.ModCommon.MiscStuff.paragraphWrap;
import static com.lilithsthrone.game.dialogue.utils.OptionsDialogue.confirmNewGame;
import static com.lilithsthrone.game.dialogue.utils.OptionsDialogue.startingNewGame;

public class MenuOverrides {

    public static final DialogueNode MENU = new DialogueNode("Menu", "Menu", true) {

        @Override
        public String getLabel() {
            return "";
        }

        @Override
        public String getContent(){
            return "<h1 class='special-text' style='font-size:38px; line-height:41px; text-align:center;'>" + (Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :
                    "Lilith's Throne") +
                    "</h1>"
                    + (Main.game.isSillyMode()// More like cheesy mode
                        ? "<p class='special-text' style='text-align:center; margin:0 0; padding:0 0;'><i>Or, I can't believe I fell into a magic mirror and entered a world in which my aunt is a demon?!</i></p>"
                        : "")
                    + "<h5 class='special-text' style='text-align:center;'>Modded by " + (Configurations.DEGEN_MODE ? Main.AUTHOR : "KeldonSlayer") + "</h5>"
                    + "<br/>"
                    + (Configurations.DEVELOPER_MODE ? paragraphWrap("Developer Mode is enabled, if you're not a developer, turn it off, or expect things to be broken",
                    PresetColour.BASE_RED_LIGHT.toWebHexString()) : "")
                    + paragraphWrap("This game is a text-based erotic RPG, and contains a lot of graphic sexual content.")
//                    + (updateFound ? paragraphWrap("Version " + newVersion.replace("_", "") + " is available!", "#0FC") : "")
//                    + (updateFound && !versionInfo.isEmpty() ? paragraphWrap(versionInfo, "#0FC") : "")
//                    + paragraphWrap(versionInfo, "#0FC") // KELDON TEMP
                    + (updateFound ? paragraphWrap("Version " + newVersion.replace("_", "") + " is available!<br>"+versionInfo, "#0FC") : "")
                    + (updateFound ? paragraphWrap("<b style='#0CC;'>Please use either my website or github to get the latest modified version of Lilith's Throne!</b>") : paragraphWrap("<b style='#0CC;'>Please report any issues you find to my DMs or on the github issues page!</b>"))
                    + getJavaVersionInformation()
                    + (Library.newErrorLog ? paragraphWrap("You have a new error log file in /data/", "#F44") : "")
                    + (Toolkit.getDefaultToolkit().getScreenSize().getHeight() < 800
                        ? paragraphWrap("If the game's resolution isn't fitting to your screen, press the keys: 'Windows' + 'Up Arrow' to maximise!", PresetColour.GENERIC_ARCANE.toWebHexString())
                        : "")
                    + (Main.game.isStarted() || Main.getProperties().name.isEmpty()
                    ? ""
                    : "<h4 style='text-align:center;'>Last save:</h4>"
                    + "<h5 style='color:" + Main.getProperties().nameColour + ";text-align:center;'>" + Main.getProperties().name + "</h5>"
                    + paragraphWrap("<b>Level " + Main.getProperties().level + " " + Util.capitaliseSentence(Main.getProperties().race) + "</b>")
                    + paragraphWrap(UtilText.formatAsMoney(Main.getProperties().money, "b"))
                    + "<div style='text-align:center; display:block; margin:auto;'>" + UtilText.formatAsEssences(Main.getProperties().arcaneEssences, "b", false) + "</div>"
                    + paragraphWrap("Quest: " + Util.capitaliseSentence(Main.getProperties().quest)));
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            return ModProps.lexiMenu() ? getLexiMainMenuButtons(responseTab, index) : getMainMenuButtons(responseTab, index);
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.OPTIONS;
        }
    };

    private static Response getMainMenuButtons(int responseTab, int index) {
         if (index == 0) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.HelperThread1.finished())
                return new Response("Game Loading", "The game is still loading", null);
            if (Main.game.isStarted()) {
                return new ResponseEffectsOnly("Resume", "Return to whatever you were doing before opening this menu.") {
                    @Override
                    public void effects() {
                        Main.mainController.openOptions();
                        confirmNewGame = false;
                    }
                };
            } else {
                if (Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
                    return new ResponseEffectsOnly("Resume", "Continue playing from your last save.") {
                        @Override
                        public void effects() {
                            Main.loadGame(Main.getProperties().lastSaveLocation);
                            confirmNewGame = false;
                        }
                    };
                } else if ("".equals(Main.getProperties().lastSaveLocation)) {
                    return new Response("Resume", "There is no previously saved game to resume.", null);
                } else return new Response("Resume", "Previously saved game (by the title '" + Main.getProperties().lastSaveLocation + "') not found in 'data/saves' " +
                        "folder.", null);
            }
        } else if (index == 1) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("Game Loading", "The game is still loading", null);
            if (confirmNewGame || !Main.game.isStarted()) {
                return new ResponseEffectsOnly(
                        (!Main.game.isStarted()
                                ? "New Game"
                                : "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Confirm</b>"),
                        "Start a new game."
                                + (Main.game.isStarted()
                                ? "<br/><br/>[style.italicsMinorBad(Remember to save your game first!)]</b>"
                                : "")) {
                    @Override
                    public void effects() {
                        if (!startingNewGame) {
                            Library.T_T = System.nanoTime();
                            startingNewGame = true;

                            //Fixes a bug where inventory would stay on screen
                            if (Main.game.isStarted()) {
                                Main.game.setInCombat(false);
                                Main.game.setInSex(false);
                            }

                            Main.mainController.setAttributePanelContent("");
                            Main.mainController.setRightPanelContent("");
                            Main.mainController.setButtonsLeftContent("");
                            Main.mainController.setButtonsRightContent("");
                            Main.game.setRenderMap(false);
                            Main.startNewGame(CharacterCreation.CHOOSE_APPEARANCE);
                            confirmNewGame = false;
                        }
                    }
                };
            } else {
                return new Response("New Game", "Start a new game."
                                + (Main.game.isStarted() ? "<br/><br/>[style.italicsMinorBad(Remember to save your game first!)]" : ""), MENU) {
                    @Override
                    public void effects() {
                        confirmNewGame = true;
                    }
                };
            }
        } else if (index == 2) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("Game Loading", "The game is still loading", null);
            return new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };
        } else if (index == 3) {
            return new Response("Export character", "Open the character export game window.", OptionsDialogue.IMPORT_EXPORT) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };
        } else if (index == 4) {
            return new ResponseEffectsOnly("Github", "Opens the page:<br/><br/><i>https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest</i><br/><br/><b>Externally in your default browser.</b>") {
                @Override
                public void effects() {
                    Util.openLinkInDefaultBrowser("https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest");
                    confirmNewGame = false;
                }
            };
        } else if (index == 5) {
            return new ResponseEffectsOnly("Quit", "Quits your current game and closes the program.<br/><br/><b>Remember to save your game first!</b>") {
                @Override
                public void effects() {
                    Main.primaryStage.close();
                    confirmNewGame = false;
                    System.exit(0);
                }
            };
        } else if (index == 6 && EXPERIMENTAL) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("Game Loading", "The game is still loading", null);
            return new Response("NPC Mod", "NPC Mod System, Heavily WIP", (Main.game == null || !Main.game.isStarted()) ? NPCModSys.NPC_MOD_SYS : null);
        } else if (index == 10) {
            return new Response("Links", "Quick Links for messaging, issue reporting, or checking for updates.", ExtraDialogue.LINKS_OUT);
        } else if (index == 11) {
            return new ResponseEffectsOnly("Open Config Folder", "Opens the configurations folder in File Explorer") {
                @Override
                public void effects() {
                    Desktop desktop = null;
                    File file = new File(System.getProperty("user.home") + "\\Lilith's Throne");
                    try {
                        if (Desktop.isDesktopSupported()) {
                            desktop = Desktop.getDesktop();
                            desktop.open(file);
                        } else {
                            System.out.println("desktop is not supported");
                        }
                    } catch (Exception ignored) {}
                }
            };
        } else if (index == 12) {
            return new Response("Options", "Open the options page.", OptionsDialogue.OPTIONS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 13) {
            return new Response("Content Options", "Set your preferred content settings.", OptionsDialogue.MISCELLANEOUS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 14) {
            return new Response("Mod Options", "Set your preferred Mod settings.", ModOptions.MOD_CONTENT_PREFERENCE) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else {
            return null;
        }
    }

    private static Response getLexiMainMenuButtons(int responseTab, int index) {
        if (index == 0) { // Last Button, bottom row
            return new ResponseEffectsOnly("Quit", "Quits your current game and closes the program.<br/><br/><b>Remember to save your game first!</b>") {
                @Override
                public void effects() {
                    Main.primaryStage.close();
                    confirmNewGame = false;
                    System.exit(0);
                }
            };
        } else if (index == 1) { // First Button, Top row
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.HelperThread1.finished())
                return new Response("Game Loading", "The game is still loading", null);
            if (Main.game.isStarted()) {
                return new ResponseEffectsOnly("Resume", "Return to whatever you were doing before opening this menu.") {
                    @Override
                    public void effects() {
                        Main.mainController.openOptions();
                        confirmNewGame = false;
                    }
                };
            } else {
                if (Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
                    return new ResponseEffectsOnly("Resume", "Continue playing from your last save.") {
                        @Override
                        public void effects() {
                            Main.loadGame(Main.getProperties().lastSaveLocation);
                            confirmNewGame = false;
                        }
                    };
                } else if ("".equals(Main.getProperties().lastSaveLocation)) {
                    return new Response("Resume", "There is no previously saved game to resume.", null);
                } else {
                    return new Response("Resume", "Previously saved game (by the title '" + Main.getProperties().lastSaveLocation + "') not found in 'data/saves' folder.", null);
                }
            }

        } else if (index == 2) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("Game Loading", "The game is still loading", null);
            if (confirmNewGame || !Main.game.isStarted()) {
                return new ResponseEffectsOnly(
                        (!Main.game.isStarted()
                                ? "New Game"
                                : "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>Confirm</b>"),
                        "Start a new game."
                                + (Main.game.isStarted()
                                ? "<br/><br/>[style.italicsMinorBad(Remember to save your game first!)]</b>"
                                : "")) {
                    @Override
                    public void effects() {
                        if (!startingNewGame) {
                            Library.T_T = System.nanoTime();
                            startingNewGame = true;

                            //Fixes a bug where inventory would stay on screen
                            if (Main.game.isStarted()) {
                                Main.game.setInCombat(false);
                                Main.game.setInSex(false);
                            }

                            Main.mainController.setAttributePanelContent("");
                            Main.mainController.setRightPanelContent("");
                            Main.mainController.setButtonsLeftContent("");
                            Main.mainController.setButtonsRightContent("");
                            Main.game.setRenderMap(false);
                            Main.startNewGame(CharacterCreation.CHOOSE_APPEARANCE);
                            confirmNewGame = false;
                        }
                    }
                };

            } else {
                return new Response("New Game", "Start a new game."
                                + (Main.game.isStarted() ? "<br/><br/>[style.italicsMinorBad(Remember to save your game first!)]" : ""), MENU) {
                    @Override
                    public void effects() {
                        confirmNewGame = true;
                    }
                };
            }

        } else if (index == 3) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("Game Loading", "The game is still loading", null);
            return new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };

        } else if (index == 4) {
            return new Response("Export character", "Open the character export game window.", OptionsDialogue.IMPORT_EXPORT) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };

        } else if (index == 5) {
            return new ResponseEffectsOnly("Github", "Opens the page:<br/><br/><i>https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest</i><br/><br/><b>Externally in your default browser.</b>") {
                @Override
                public void effects() {
                    Util.openLinkInDefaultBrowser("https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest");
                    confirmNewGame = false;
                }
            };

        } else if (index == 6 && EXPERIMENTAL) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("Game Loading", "The game is still loading", null);
            return new Response("NPC Mod", "NPC Mod System, Heavily WIP", (Main.game == null || !Main.game.isStarted()) ? NPCModSys.NPC_MOD_SYS : null);
        } else if (index == 10) {
            return new Response("Links", "Quick Links for messaging, issue reporting, or checking for updates.", ExtraDialogue.LINKS_OUT);
        } else if (index == 11) {
            return new ResponseEffectsOnly("Open Config Folder", "Opens the configurations folder in File Explorer") {
                @Override
                public void effects() {
                    Desktop desktop = null;
                    File file = new File(System.getProperty("user.home") + "\\Lilith's Throne");
                    try {
                        if (Desktop.isDesktopSupported()) {
                            desktop = Desktop.getDesktop();
                            desktop.open(file);
                        } else {
                            System.out.println("desktop is not supported");
                        }
                    } catch (Exception ignored) {}
                }
            };

        } else if (index == 12) {
            return new Response("Options", "Open the options page.", OptionsDialogue.OPTIONS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 13) {
            return new Response("Content Options", "Set your preferred content settings.", OptionsDialogue.MISCELLANEOUS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 14) {
            return new Response("Mod Options", "Set your preferred Mod settings.", ModOptions.MOD_CONTENT_PREFERENCE) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else {
            return null;
        }
    }


    private static String getJavaVersionInformation() {
        return "<p style='text-align:center;'>Your java version: " + System.getProperty("java.version") + "</p>";
    }
}
