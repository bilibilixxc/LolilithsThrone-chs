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
                        ? "<p class='special-text' style='text-align:center; margin:0 0; padding:0 0;'><i>或者说，我不敢相信我掉进了魔法镜子里，进入了一个我姑姑是恶魔的世界？！</i></p>"
                        : "")
                    + "<h5 class='special-text' style='text-align:center;'>由 " + (Configurations.DEGEN_MODE ? Main.AUTHOR : "KeldonSlayer") + " 修改</h5>"
                    + "<br/>"
                    + (Configurations.DEVELOPER_MODE ? paragraphWrap("已启用开发者模式，如果你不是开发者，请将其关闭，否则可能会出各种故障",
                    PresetColour.BASE_RED_LIGHT.toWebHexString()) : "")
                    + paragraphWrap("这是一款基于文字的情色 RPG 游戏，包含大量露骨的性内容。")
//                    + (updateFound ? paragraphWrap("Version " + newVersion.replace("_", "") + " is available!", "#0FC") : "")
//                    + (updateFound && !versionInfo.isEmpty() ? paragraphWrap(versionInfo, "#0FC") : "")
//                    + paragraphWrap(versionInfo, "#0FC") // KELDON TEMP
                    + (updateFound ? paragraphWrap("有新版本 " + newVersion.replace("_", "") + " 可用！<br>"+versionInfo, "#0FC") : "")
                    + (updateFound ? paragraphWrap("<b style='#0CC;'>请使用我的网站或 github 获取莉莉丝王座最新的修改版！</b>") : paragraphWrap("<b style='#0CC;'>请将你遇到的任何问题报告给我的私信或 github 的 issues 页面！</b>"))
                    + getJavaVersionInformation()
                    + (Library.newErrorLog ? paragraphWrap("你的 /data/ 目录下有一份新的错误日志文件", "#F44") : "")
                    + (Toolkit.getDefaultToolkit().getScreenSize().getHeight() < 800
                        ? paragraphWrap("如果游戏的屏幕分辨率不匹配，请按下「Windows + 向上箭头」键以最大化窗口！", PresetColour.GENERIC_ARCANE.toWebHexString())
                        : "")
                    + (Main.game.isStarted() || Main.getProperties().name.isEmpty()
                    ? ""
                    : "<h4 style='text-align:center;'>上次存档：</h4>"
                    + "<h5 style='color:" + Main.getProperties().nameColour + ";text-align:center;'>" + Main.getProperties().name + "</h5>"
                    + paragraphWrap("<b>等级 " + Main.getProperties().level + " " + Util.capitaliseSentence(Main.getProperties().race) + "</b>")
                    + paragraphWrap(UtilText.formatAsMoney(Main.getProperties().money, "b"))
                    + "<div style='text-align:center; display:block; margin:auto;'>" + UtilText.formatAsEssences(Main.getProperties().arcaneEssences, "b", false) + "</div>"
                    + paragraphWrap("任务：" + Util.capitaliseSentence(Main.getProperties().quest)));
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
                return new Response("游戏加载中", "游戏仍在加载中", null);
            if (Main.game.isStarted()) {
                return new ResponseEffectsOnly("继续游戏", "返回打开该菜单前你正在进行的操作。") {
                    @Override
                    public void effects() {
                        Main.mainController.openOptions();
                        confirmNewGame = false;
                    }
                };
            } else {
                if (Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
                    return new ResponseEffectsOnly("继续游戏", "从上一次存档继续游戏。") {
                        @Override
                        public void effects() {
                            Main.loadGame(Main.getProperties().lastSaveLocation);
                            confirmNewGame = false;
                        }
                    };
                } else if ("".equals(Main.getProperties().lastSaveLocation)) {
                    return new Response("继续游戏", "没有可继续的上一次游戏存档。", null);
                } else return new Response("继续游戏", "在 'data/saves' 文件夹中没有找到上一次存储的存档（标题名为 '" + Main.getProperties().lastSaveLocation + "'）。", null);
            }
        } else if (index == 1) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            if (confirmNewGame || !Main.game.isStarted()) {
                return new ResponseEffectsOnly(
                        (!Main.game.isStarted()
                                ? "新游戏"
                                : "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>确认</b>"),
                        "开始一个新的游戏。"
                                + (Main.game.isStarted()
                                ? "<br/><br/>[style.italicsMinorBad(请记得先保存你的游戏！)]</b>"
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
                return new Response("新游戏", "开始一个新的游戏。"
                                + (Main.game.isStarted() ? "<br/><br/>[style.italicsMinorBad(请记得先保存你的游戏！)]" : ""), MENU) {
                    @Override
                    public void effects() {
                        confirmNewGame = true;
                    }
                };
            }
        } else if (index == 2) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            return new Response("存档/读档", "打开存档/读档窗口。", OptionsDialogue.SAVE_LOAD) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };
        } else if (index == 3) {
            return new Response("导出角色", "打开角色导出窗口。", OptionsDialogue.IMPORT_EXPORT) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };
        } else if (index == 4) {
            return new ResponseEffectsOnly("Github", "在默认浏览器中打开页面：<br/><br/><i>https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest</i><br/><br/><b>（外部浏览器）</b>") {
                @Override
                public void effects() {
                    Util.openLinkInDefaultBrowser("https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest");
                    confirmNewGame = false;
                }
            };
        } else if (index == 5) {
            return new ResponseEffectsOnly("退出", "退出当前游戏并关闭程序。<br/><br/><b>请记得先保存你的游戏！</b>") {
                @Override
                public void effects() {
                    Main.primaryStage.close();
                    confirmNewGame = false;
                    System.exit(0);
                }
            };
        } else if (index == 6 && EXPERIMENTAL) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            return new Response("NPC 模组", "NPC 模组系统，重度开发中", (Main.game == null || !Main.game.isStarted()) ? NPCModSys.NPC_MOD_SYS : null);
        } else if (index == 10) {
            return new Response("链接", "用于联系、报告问题或检查更新的快捷链接。", ExtraDialogue.LINKS_OUT);
        } else if (index == 11) {
            return new ResponseEffectsOnly("打开配置文件夹", "在文件资源管理器中打开配置文件夹") {
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
            return new Response("选项", "打开选项页面。", OptionsDialogue.OPTIONS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 13) {
            return new Response("内容选项", "设置你的首选内容设置。", OptionsDialogue.MISCELLANEOUS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 14) {
            return new Response("模组选项", "设置你的首选模组设置。", ModOptions.MOD_CONTENT_PREFERENCE) {
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
            return new ResponseEffectsOnly("退出", "退出当前游戏并关闭程序。<br/><br/><b>请记得先保存你的游戏！</b>") {
                @Override
                public void effects() {
                    Main.primaryStage.close();
                    confirmNewGame = false;
                    System.exit(0);
                }
            };
        } else if (index == 1) { // First Button, Top row
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.HelperThread1.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            if (Main.game.isStarted()) {
                return new ResponseEffectsOnly("继续游戏", "返回打开该菜单前你正在进行的操作。") {
                    @Override
                    public void effects() {
                        Main.mainController.openOptions();
                        confirmNewGame = false;
                    }
                };
            } else {
                if (Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
                    return new ResponseEffectsOnly("继续游戏", "从上一次存档继续游戏。") {
                        @Override
                        public void effects() {
                            Main.loadGame(Main.getProperties().lastSaveLocation);
                            confirmNewGame = false;
                        }
                    };
                } else if ("".equals(Main.getProperties().lastSaveLocation)) {
                    return new Response("继续游戏", "没有可继续的上一次游戏存档。", null);
                } else {
                    return new Response("继续游戏", "在 'data/saves' 文件夹中没有找到上一次存储的存档（标题名为 '" + Main.getProperties().lastSaveLocation + "'）。", null);
                }
            }

        } else if (index == 2) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            if (confirmNewGame || !Main.game.isStarted()) {
                return new ResponseEffectsOnly(
                        (!Main.game.isStarted()
                                ? "新游戏"
                                : "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>确认</b>"),
                        "开始一个新的游戏。"
                                + (Main.game.isStarted()
                                ? "<br/><br/>[style.italicsMinorBad(请记得先保存你的游戏！)]</b>"
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
                return new Response("新游戏", "开始一个新的游戏。"
                                + (Main.game.isStarted() ? "<br/><br/>[style.italicsMinorBad(请记得先保存你的游戏！)]" : ""), MENU) {
                    @Override
                    public void effects() {
                        confirmNewGame = true;
                    }
                };
            }

        } else if (index == 3) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            return new Response("存档/读档", "打开存档/读档窗口。", OptionsDialogue.SAVE_LOAD) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };

        } else if (index == 4) {
            return new Response("导出角色", "打开角色导出窗口。", OptionsDialogue.IMPORT_EXPORT) {
                @Override
                public void effects() {
                    OptionsDialogue.loadConfirmationName = "";
                    OptionsDialogue.overwriteConfirmationName = "";
                    OptionsDialogue.deleteConfirmationName = "";
                    confirmNewGame = false;
                }
            };

        } else if (index == 5) {
            return new ResponseEffectsOnly("Github", "在默认浏览器中打开页面：<br/><br/><i>https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest</i><br/><br/><b>（外部浏览器）</b>") {
                @Override
                public void effects() {
                    Util.openLinkInDefaultBrowser("https://github.com/DegenDomain/LolilithsThroneIssueTracker/releases/latest");
                    confirmNewGame = false;
                }
            };

        } else if (index == 6 && EXPERIMENTAL) {
            if (MULTITHREADED_PRELOADING && !PreInitializationThread.finished())
                return new Response("游戏加载中", "游戏仍在加载中", null);
            return new Response("NPC 模组", "NPC 模组系统，重度开发中", (Main.game == null || !Main.game.isStarted()) ? NPCModSys.NPC_MOD_SYS : null);
        } else if (index == 10) {
            return new Response("链接", "用于联系、报告问题或检查更新的快捷链接。", ExtraDialogue.LINKS_OUT);
        } else if (index == 11) {
            return new ResponseEffectsOnly("打开配置文件夹", "在文件资源管理器中打开配置文件夹") {
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
            return new Response("选项", "打开选项页面。", OptionsDialogue.OPTIONS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 13) {
            return new Response("内容选项", "设置你的首选内容设置。", OptionsDialogue.MISCELLANEOUS) {
                @Override
                public void effects() {
                    confirmNewGame = false;
                }
            };
        } else if (index == 14) {
            return new Response("模组选项", "设置你的首选模组设置。", ModOptions.MOD_CONTENT_PREFERENCE) {
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
        return "<p style='text-align:center;'>你的 Java 版本：" + System.getProperty("java.version") + "</p>";
    }
}