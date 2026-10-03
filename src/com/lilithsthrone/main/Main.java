package com.lilithsthrone.main;

import com.lilithsthrone.Launcher;
import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.Experiments.LoadProperties;
import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.LolificationProject.Magic.ModLoader;
import com.lilithsthrone.LolificationProject.ModCommon.ErrorStream;
import com.lilithsthrone.LolificationProject.ModCommon.Logger;
import com.lilithsthrone.LolificationProject.ModCommon.MiscStuff;
import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.LolificationProject.Overrides.MenuOverrides;
import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.controller.TooltipUpdateThread;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.Properties;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.MapTravelType;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.threading.PreInitializationThread;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Generation;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.BACKUP_SAVES;
import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.ERR_LOG_INFO_LENGTH;
import static com.lilithsthrone.LolificationProject.ModCommon.ErrorStream.getLogLengthSafe;

/**
 * @since 0.1.0
 * @version 0.4.11
 * @author Innoxia
 */
public class Main extends Application {
	public static Thread currentThread;
	public static Main instance;

	public static Game game;
	public static Sex sex;
	public static Combat combat;

	public static TransformerFactory transformerFactory = TransformerFactory.newInstance();
	private static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder;

	public static MainController mainController;

	public static Scene mainScene;

	public static Stage primaryStage;
	
	public static final String AUTHOR = "Loliphile";
	public static final String NAME_OF_GAME = "Lolilith's Throne";
	public static final String VERSION_NUMBER = "0.4.11.9"; // Remember to do the stuff below!
	/*
	 * BEFORE BUILDING:
	 * update pom.xml!
	 * use smaller images folder
	 * 
	 * launch4j include JVM options:
	 * -Dbuild.type=exe64 or -Dbuild.type=exe32 as appropriate
	 */
	public static final String VERSION_DESCRIPTION = "Alpha";

	public static boolean quickSaved = false;
	
	private static boolean displayingTurnTimer = false;
	
	/**
	 * To turn it on, just add -Ddebug=true to java's VM options. (You should be able to do this in Eclipse through Run::Run Configurations...::Arguments tab::VM Arguments).
	 * Help page: https://help.eclipse.org/mars/index.jsp?topic=%2Forg.eclipse.pde.doc.user%2Fguide%2Ftools%2Flaunchers%2Farguments.htm
	 *  Or, from the command line java -Ddebug=true -jar LilithsThrone.jar
	 */
	public final static boolean DEBUG = Boolean.valueOf(System.getProperty("debug", "false"));

	public static Image WINDOW_IMAGE;
	
	private static Properties properties;
	
	private static String patchNotes = "";
	
	public static String disclaimer = "<h6 style='text-align: center; color:" + PresetColour.GENERIC_ARCANE.toWebHexString() + ";'>You must enjoy yourself!</h6>" +
			"<p>This once was a long winded disclaimer of legal protections, lmao. Play or don't I'm not a lawyer.</br>" +
			"Most content can be disabled/changed. </br>Contains <span style='color:#FF0;'>Watersports</span>, <span style='color:#F66;'>Ageplay</span>, <span style='color:#FAA;'>Sexual Violence</span>, <span style='color:#F0F;'>Sex with animate creatures and inanimate objects alike</span>" +
			"</p>";
	
	public static List<CreditsSlot> credits = new ArrayList<>();

	// World generation:
	public static Generation gen;
	@Override
	public void start(Stage primaryStage) throws Exception {

		CheckForDataDirectory();
		CheckForResFolder();
		
		credits.add(new CreditsSlot("Anonymous", "", 99, 99, 99, 99));
//		credits.sort(Comparator.comparing((CreditsSlot a) -> a.getName().toLowerCase()));
		
		
		Main.primaryStage = primaryStage;
		
		Main.primaryStage.focusedProperty().addListener((ov, t, t1) -> {
            if(t) {
                TooltipUpdateThread.cancelThreads = true;
            }
        });

		WINDOW_IMAGE = new Image("/com/lilithsthrone/res/images/windowIcon32.png");

		Main.primaryStage.getIcons().add(WINDOW_IMAGE);

		refreshTitle();

		loadFonts();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));

		Pane pane = loader.load();

		mainScene = new Scene(pane);

		if (properties.hasValue(PropertyValue.lightTheme)) {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
		} else {
			mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
		}

		mainController = loader.getController();
		Main.primaryStage.setScene(mainScene);
		Main.primaryStage.show();
		currentThread = Thread.currentThread();
		currentThread.setName("Main Thread");
		instance = this;
		Main.game = new Game();
		Main.sex = new Sex();
		Main.combat = new Combat();
		
		loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
		try {
			if (Main.mainScene == null) {
				pane = loader.load();
				Main.mainController = loader.getController();

				Main.mainScene = new Scene(pane);
				if (Main.getProperties().hasValue(PropertyValue.lightTheme))
					Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
				else
					Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
			}

			Main.primaryStage.setScene(Main.mainScene);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Main.game.setContent(new Response("", "", MenuOverrides.MENU));
		Logger.logTime("Startup Took", System.nanoTime() - Launcher.LaunchTimer);
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		Platform.exit();
		System.exit(0);
	}

	public static boolean CheckNotUnpacked() {
		File dir = new File("");
		String currentDir = dir.getAbsolutePath();
		String tempDir = System.getProperty("java.io.tmpdir");
//		System.err.println(currentDir);
//		System.err.println(tempDir);
		return currentDir.contains(tempDir);
	}
	
	protected static void CheckForDataDirectory() {
		File dir = new File("data/");
		if(!dir.exists()) {
			Alert a = new Alert(AlertType.ERROR,
					"Unable to find the 'data' folder ("+dir.getAbsolutePath()+"). Saving and error logging is disabled."
							+ "\nMake sure that you've extracted the game from the zip file, and that the file has write permissions."
							+ "\n(Please read section 'MISSING FOLDERS' in the README.txt file.)"
							+ "\nContinue?",
					ButtonType.YES, ButtonType.NO);
			System.err.println("Unable to find the 'data' folder ("+dir.getAbsolutePath()+").");
			a.showAndWait().ifPresent(response -> {
			     if (response == ButtonType.NO) {
			         System.exit(1);
			     }
			 });
		}
	}
	
	protected static void CheckForResFolder() {
		File dir = new File("res/");
		if(!dir.exists()) {
			Alert a = new Alert(AlertType.WARNING,
					"Could not find the 'res' folder ("+dir.getAbsolutePath()+"). This WILL cause errors and present sections of missing text."
							+ "\nMake sure that you've extracted the game from the zip file, and that the file has write permissions."
							+ "\nIf you use the command line or a batch/script file to start the game, please try starting it in the game folder to prevent this error."
							+ "\n(Please read section 'MISSING FOLDERS' in the README.txt file.)"
							+ "\nContinue?",
					ButtonType.YES, ButtonType.NO);
			System.err.println("Unable to find the 'res' folder ("+dir.getAbsolutePath()+").");
			a.showAndWait().ifPresent(response -> {
				if(response == ButtonType.NO) {
					System.exit(1);
				}
			});
		}
	}

	/**
	 * Attempts to load fallback fonts to make sure that they are available later. The size doesn't actually matter as
	 * the WebEngine will reload other sizes as required. The files referenced must persist until application shutdown.
	 *
	 * Do not call Font.getFamilies() prior to this as additional fonts must be loaded before the list is cached.
	 */
	protected void loadFonts() {
		// Load fallback for Calibri
		if (Font.loadFont(toUri("res/fonts/Carlito/Carlito-Regular.ttf"), 11) != null) {
			// Load variants
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-Bold.ttf"), 11);
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-BoldItalic.ttf"), 11);
			Font.loadFont(toUri("res/fonts/Carlito/Carlito-Italic.ttf"), 11);
		} else {
			System.err.println("Carlito font could not be loaded.");
		}

		// Load fallback for Verdana
		if (Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans.ttf"), 12) != null) {
			// Load variants
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-Bold.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-BoldOblique.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-ExtraLight.ttf"), 12);
			Font.loadFont(toUri("res/fonts/DejaVu Sans/DejaVuSans-Oblique.ttf"), 12);
		} else {
			System.err.println("DejaVu Sans font could not be loaded.");
		}
	}

	/**
	 * Creates a URI string with spaces. The given path can be absolute or relative to the current working directory.
	 * @param path The path to convert
	 * @return A string containing a URI
	 */
	public static String toUri(String path) {
		return Paths.get(path).toUri().toString().replaceAll("%20", " ");
	}

	public static DocumentBuilder getDocBuilder() {
		if (docBuilder == null) {
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		return docBuilder;
	}

	public static String getPatchNotes() {
		if (!patchNotes.isEmpty()) {
			return patchNotes;
		}

		String prefaceText = "";
		StringBuilder patchNotesText = new StringBuilder();

		File patchNotesFolder = new File("res/patchNotes");
		if (!patchNotesFolder.exists() || !patchNotesFolder.isDirectory()) {
			System.err.println("Folder 'res/patchNotes' could not be loaded!");
			return "";
		}

		File[] fileList = patchNotesFolder.listFiles((dir, name) -> name.endsWith(".html"));
		Arrays.sort(fileList, Collections.reverseOrder());

		for (File file : fileList) {
			Matcher matcher = Pattern.compile("^\\d{8}-(preface|patchnotes)-", Pattern.CASE_INSENSITIVE).matcher(file.getName());
			if (!matcher.find()) {
				continue;
			}

			try (FileInputStream fis = new FileInputStream(file)) {
				String contentHTML = Util.inputStreamToString(fis);
				switch (matcher.group(1).toLowerCase()) {
					case "preface":
						if (prefaceText.isEmpty()) {
							prefaceText = contentHTML;
						}
						break;

					case "patchnotes":
						patchNotesText.append("<br />\n<div class=\"list\">\n").append(contentHTML).append("</div>\n");
						break;
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		patchNotes = prefaceText + patchNotesText;

		return patchNotes;
	}

	public static void main(String[] args) {
		
		// Create folders:
		File dir = new File("data/");
		dir.mkdir();
		dir = new File("data/saves");
		dir.mkdir();
		dir = new File("data/characters");
		dir.mkdir();
		
		
		// Open error log
		if(!DEBUG) {
			File errorLog = new File("data/error.log");
			if (errorLog.exists() && getLogLengthSafe(errorLog) > ERR_LOG_INFO_LENGTH) {
				Library.newErrorLog = true;
				errorLog.renameTo(new File("data/error_log_" + errorLog.lastModified() + ".log"));
			}
			System.out.println("Printing to error.log");
			try {
				ErrorStream stream = new ErrorStream("data/error.log");
				System.setErr(stream);
				stream.printData();
			} catch (FileNotFoundException e) {
				Alert a = new Alert(AlertType.ERROR,
						"Unable to create or modify error.log ("+ (new File("./data/error.log")).getAbsolutePath()+")."
								+ "\nMake sure that data exists, and that the file has write permissions."
								+ "\n(Please do not extract the game into protected folders [C:\\, Desktop, OneDrive, etc...])"
								+ "\nContinue?",
						ButtonType.YES, ButtonType.NO);
				System.err.println("Failed to create/modify error.log");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.NO) {
						System.exit(1);
					}
				});

			}
		}
		// Load properties:
		if (new File("data/properties.xml").exists()) {
			try {
				properties = new Properties();
				long timeTest = System.nanoTime();
//				properties.loadPropertiesFromXML();
				LoadProperties.loadPropertiesFromXML(properties);
				Logger.logTime("Alternative Property Load Took", System.nanoTime() - timeTest);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			properties = new Properties();
			properties.savePropertiesAsXML();
		}
		GameCharacter.MINIMUM_AGE = ModProps.minAge;
		GameCharacter.MAXIMUM_AGE = ModProps.maxAge;
		GameCharacter.LEVEL_CAP = ModProps.maxLevel;
		GameCharacter.MAX_TRAITS = ModProps.maxPerks; // fixes that bug, oops

		PreInitializationThread.preload();


		try {
			ModLoader.loadMods();
		} catch (Exception e) {
			e.printStackTrace();
		}

		launch(args);
	}
	
	/**
	 * Starts a completely new game. Runs a new World Generation.
	 */
	public static void startNewGame(DialogueNode startingDialogueNode) {
		
		Main.game = new Game();
		
		// Generate world:
		if (!(gen == null)) {
			if (gen.isRunning()) {
				gen.cancel();
			}
		}
		
		gen = new Generation();

		gen.setOnSucceeded(new EventHandler<>() {
            @Override
            public void handle(WorkerStateEvent t) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lilithsthrone/res/fxml/main.fxml"));
                Pane pane;
                try {
                    if (Main.mainScene == null) {
                        pane = loader.load();
                        Main.mainController = loader.getController();

                        Main.mainScene = new Scene(pane);
                        if (Main.getProperties().hasValue(PropertyValue.lightTheme))
                            Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet_light.css");
                        else
                            Main.mainScene.getStylesheets().add("/com/lilithsthrone/res/css/stylesheet.css");
                    }

                    Main.primaryStage.setScene(Main.mainScene);

                } catch (Exception e) {
                    e.printStackTrace();
                }

				Main.game.setPlayer(new PlayerCharacter(false, new NameTriplet("Player"), 1, null, Gender.M_P_MALE, Subspecies.HUMAN, RaceStage.HUMAN, WorldType.MUSEUM, PlaceType.MUSEUM_ENTRANCE));

                Main.game.initNewGame(startingDialogueNode);

                Main.game.endTurn(0);

                OptionsDialogue.startingNewGame = false;
                //Main.mainController.processNewDialogue();
            }
        });
		new Thread(gen).start();
	}
	
	public static boolean isVersionOlderThan(String versionToCheck, String versionToCheckAgainst) {
		String[] v1 = versionToCheck.split("\\.");
		String[] v2 = versionToCheckAgainst.split("\\.");
		
		try {
			int maxLength = (v1.length > v2.length) ? v1.length : v2.length;
			for (int i = 0; i < maxLength; i++) {
				int v1i;
				int v2i;
				
				if(v1[1].charAt(0)=='1') { // Versions prior to 0.2.x used an old system of the format: 0.1.10.1 being a lower version than 0.1.9.1:
					v1i = (i < v1.length) ? Integer.valueOf((v1[i]+"00").substring(0, 3)) : 0;
					v2i = (i < v2.length) ? Integer.valueOf((v2[i]+"00").substring(0, 3)) : 0;
					
				} else { // Versions of 0.2.x and higher use a new system of the format: 0.2.10.1 being a higher version than 0.2.9.1:
					v1i = (i < v1.length) ? Integer.valueOf(v1[i]) : 0;
					v2i = (i < v2.length) ? Integer.valueOf(v2[i]) : 0;
				}
				
				if (v1i < v2i) {
					return true;
				} else if (v1i > v2i) {
					return false;
				} 
			}
			
		} catch(Exception ex) {
			return true;
		}
		
		return false;
	}
	
	public static void refreshTitle() {
		Main.primaryStage.setTitle(getTitle());
	}
	
	public static String getTitle() {
		displayingTurnTimer = Main.game!=null && Main.game.isDebugMode() && Main.game.isStarted();
		
		return (Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :"Lilith's Throne")
				+ " " + VERSION_NUMBER + Library.VERSION
				+ " " + VERSION_DESCRIPTION
				+ (DEBUG?" (Debug Mode)":"")
				+ (Configurations.EXPERIMENTAL ? " EXPERIMENTAL" : "")
				+ (Configurations.DEVELOPER_MODE ? " DEVELOPER" : "")
				+ (displayingTurnTimer
					?" "+Math.round((Main.game.endTurnTimeTaken/1000000000d)*1000)/1000f+"s"
					:"");
	}
	
	public static boolean isDisplayingTurnTimer() {
		return displayingTurnTimer;
	}
	
	public static int getFontSize() {
		return properties.fontSize;
	}

	public static void setFontSize(int size) {
		properties.fontSize = size;
		properties.savePropertiesAsXML();
	}
	
	public static boolean isQuickSaveAvailable() {
		return Main.game.isStarted()
				&& !Main.game.isInCombat()
				&& !Main.game.isInSex()
				&& Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL
				&& Main.game.isInNeutralDialogue();
	}
	
	public static String getQuickSaveUnavailabilityDescription() {
		if (!Main.game.isInNewWorld()) {
			return "You cannot save the game during the character creation process or prologue!";
			
		} else if (Main.game.isInCombat()) {
			return "You cannot save the game while in combat!";
			
		} else if (Main.game.isInSex()) {
			return "You cannot save the game while in a sex scene!";
			
		} else if (Main.game.getCurrentDialogueNode().getDialogueNodeType()!=DialogueNodeType.NORMAL) {
			return "You cannot save the game unless you are in a neutral scene!";
			
		} else if (!Main.game.isStarted() || !Main.game.isInNeutralDialogue()) {
			return "You cannot save the game unless you are in a neutral scene!";
		}
		
		return "";
	}
	
	public static String getQuickSaveName() {
		String name;
		if(!Main.game.isStarted()) {
			name = "QuickSave_intro";
		} else {
			name = "QuickSave_"+Main.game.getPlayer().getName(false);
		}
		return Main.checkFileName(name);
	}
	
	public static void quickSaveGame() {
		if(isQuickSaveAvailable()){
			Main.getProperties().lastQuickSaveName = getQuickSaveName();
			saveGame(getQuickSaveName(), true, false);
			quickSaved = true;
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, getQuickSaveUnavailabilityDescription());
		}
	}

	public static void quickLoadGame() {
		String name = "";
		if(quickSaved) {
			name = Main.checkFileName(Main.properties.lastQuickSaveName);
		} else {
			name = Main.checkFileName(getQuickSaveName());
		}

		if(name.isEmpty()) {
			return;
		}
		loadGame(name);
		saveProperties();
	}

	public static boolean isSaveGameAvailable() {
		return Main.game.isStarted()
				&& ((!Main.game.getSavedDialogueNode().isTravelDisabled() && MapTravelType.WALK_SAFE.isAvailable(Main.game.getPlayerCell(), Main.game.getPlayer()))
						|| Main.game.getSavedDialogueNode().equals(Main.game.getDefaultDialogue(false)));
	}
	
	public static void saveGame(String name, boolean allowOverwrite, boolean isAutoSave) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}

		if (BACKUP_SAVES)
			MiscStuff.backupSave(name);
		Game.exportGame(name, allowOverwrite, isAutoSave);

		try {
			properties.lastSaveLocation = name;//"data/saves/"+name+".lts";
			properties.nameColour = Femininity.valueOf(game.getPlayer().getFemininityValue()).getColour().toWebHexString();
			properties.name = game.getPlayer().getName(false);
			properties.level = game.getPlayer().getLevel();
			properties.money = game.getPlayer().getMoney();
			properties.arcaneEssences = game.getPlayer().getEssenceCount();
			properties.raceColour = game.getPlayer().getSubspecies().getColour(Main.game.getPlayer()).toWebHexString();
			if (game.getPlayer().isFeminine()) {
				properties.race = game.getPlayer().getSubspecies().getSingularFemaleName(game.getPlayer().getBody());
			} else {
				properties.race = game.getPlayer().getSubspecies().getSingularMaleName(game.getPlayer().getBody());
			}
			properties.quest = game.getPlayer().getQuest(QuestLine.MAIN).getName();

			properties.savePropertiesAsXML();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String checkFileName(String name) {
		name = name.replace(" ", "_").replaceAll("[^\\w]+", "");
		if (name.length()==0) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name too short!");
			return "";
		}
		if (name.length() > 64) {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "Name too long!");
			return "";
		}
		return name;
	}

	public static boolean isLoadGameAvailable(String name) {
		File file = new File("data/saves/"+name+".xml");

		return file.exists();
	}
	
	public static void loadGame(String name) {
		if (isLoadGameAvailable(name)) {
			Game.importGame(name);
			MainController.updateUIButtons();
		}
	}

	public static void loadGame(File f) {
		System.out.println("Loading Game v1 ");
		Game.importGame(f);
		MainController.updateUIButtons();
	}
	
	public static void deleteGame(String name) {
		File file = new File("data/saves/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
		}
	}

	public static void deleteExportedGame(String name) {
		File file = new File("data/saves/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
		}
	}

	public static void deleteExportedCharacter(String name) {
		File file = new File("data/characters/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "File not found...");
		}
	}
	
	/**
	 * @param sortAlphabetically Pass in true if you want the files sorted alphabetically, false if you want them sorted by date modified.
	 * @return A list of xml files from the 'data/saves' folder.
	 */
	public static List<File> getSavedGames(boolean sortAlphabetically) {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/saves");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}
		
		if(sortAlphabetically) {
			filesList.sort(Comparator.comparing(File::getName));
		} else {
			filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		}
		
		return filesList;
	}
	
	public static List<File> getCharactersForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static List<File> getSlavesForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/characters");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}
		
		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static List<File> getGamesForImport() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/saves");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
		
		return filesList;
	}
	
	public static void importCharacter(File file) {
		if (file != null) {
			try {
				Main.game.setPlayer(Main.game.getCharacterUtils().startLoadingCharacterFromXML());
				Main.game.setPlayer(Main.game.getCharacterUtils().loadCharacterFromXML(file, Main.game.getPlayer(),
						CharacterImportSetting.NEW_GAME_IMPORT,
						CharacterImportSetting.NO_PREGNANCY,
						CharacterImportSetting.NO_COMPANIONS,
						CharacterImportSetting.NO_ELEMENTAL,
						CharacterImportSetting.CLEAR_SLAVERY,
						CharacterImportSetting.CLEAR_KEY_ITEMS,
						CharacterImportSetting.CLEAR_COMBAT_HISTORY,
						CharacterImportSetting.CLEAR_SEX_HISTORY,
						CharacterImportSetting.REMOVE_RACE_CONCEALED,
						CharacterImportSetting.CLEAR_FAMILY_ID));
				
				Main.game.getPlayer().getSlavesOwned().clear();
				Main.game.getPlayer().endPregnancy(false);
				
				Main.game.setRenderAttributesSection(true);
				Main.game.clearTextStartStringBuilder();
				Main.game.clearTextEndStringBuilder();
				Main.getProperties().setValue(PropertyValue.newWeaponDiscovered, false);
				Main.getProperties().setValue(PropertyValue.newClothingDiscovered, false);
				Main.getProperties().setValue(PropertyValue.newItemDiscovered, false);
				Main.game.getPlayer().calculateStatusEffects(0);

				Main.game.initNewGame(CharacterCreation.START_GAME_WITH_IMPORT);
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void saveProperties() {
		properties.savePropertiesAsXML();
		MiscStuff.updateValues();
	}


	public void resetContent() {
//		System.out.println(Thread.currentThread().getName() + " is resetting content.");
		Main.game.setContent(new Response("", "", MenuOverrides.MENU));
	}
}
