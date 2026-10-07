package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.ConfigStuff.LTOptions;
import com.lilithsthrone.LolificationProject.ModCommon.ModOptions;
import com.lilithsthrone.LolificationProject.Overrides.MenuOverrides;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.valueEnums.AgeCategory;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishPreference;
import com.lilithsthrone.game.character.gender.AndrogynousIdentification;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderNames;
import com.lilithsthrone.game.character.gender.GenderPronoun;
import com.lilithsthrone.game.character.gender.PronounType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.persona.SexualOrientationPreference;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.character.race.SubspeciesPreference;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.settings.ContentPreferenceValue;
import com.lilithsthrone.game.settings.DifficultyLevel;
import com.lilithsthrone.game.settings.ForcedFetishTendency;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.settings.KeyCodeWithModifiers;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.ArtistWebsite;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.CreditsSlot;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import static com.lilithsthrone.game.settings.ConfigOptions.*;
import static com.lilithsthrone.game.settings.ConfigOptions.INTERNATIONAL_DATE;
import static com.lilithsthrone.game.settings.ConfigOptions.METRIC_WEIGHTS;
import static com.lilithsthrone.game.settings.ConfigOptions.TWENTYFOUR_HOUR_TIME;

/**
 * @since 0.1.0
 * @version 0.4.10.10
 * @author Innoxia, Maxis
 */
public class OptionsDialogue {
	
	public static boolean confirmNewGame = false;
	public static boolean startingNewGame = false;
	
	private static boolean alphabeticalFileSort = false;
	
	private static boolean defaultResetConfirmation = false;
	
	public static final DialogueNode MENU = new DialogueNode("菜单", "菜单", true) {
		
		@Override
		public String getLabel() {
			return "";
		}
		
		@Override
		public String getContent(){
			StringBuilder sb = new StringBuilder();
			sb.append("<h1 class='special-text' style='font-size:38px; line-height:41px; text-align:center;'>"+(Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :"Lilith's Throne")+"</h1>");
			if(Main.game.isSillyMode()) {
				sb.append("<p class='special-text' style='text-align:center; margin:0 0; padding:0 0;'><i>关于我意外掉进魔法镜，进入到一个姨妈变成恶魔的世界这件事</i></p>");
			}
			
			sb.append("<h5 class='special-text' style='text-align:center;'>Created by "+(Configurations.DEGEN_MODE ? Main.AUTHOR : "KeldonSlayer")+"</h5>");
			
			if (Main.CheckNotUnpacked()) {
				sb.append("<h3 class='special-text' style='text-align:center;'>[style.italicsBad("+(Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :"Lilith's Throne")+" is currently running from a temporary directory!");
				sb.append("<br/>请解压 .zip 文件后再开始游戏！)]</h3>");
//				return sb.toString();
			}
			
			sb.append("<p>本游戏为一款文字色情RPG，并包含大量性相关图像内容。在游玩本游戏前必须同意该游戏的声明！(汉化版本在github发布，完全免费，若有问题请前往https://github.com/chinese-liliths-throne/liliths-throne-chinese-release提交Issue或在官方交流群内汇报)</p>")
					.append("<p>你可以访问我的博客(https://lilithsthrone.blogspot.co.uk) 查看最新开发进度 (点击下方“博客”按钮在浏览器中打开页面)。")
					//.append(" [style.italicsMinorBad(<b>Note:</b> Intrusive age verification is being rolled out on blogspot, so I will likely create a new blog soon.)]</p>")
					.append("<p style='text-align:center'><b>请通过我的博客或者Github获取最新官方版本的莉莉丝的王座！</b></p>")
					.append("<p style='text-align:center'><i>将你的“data”文件夹内容复制到新版本中，即可继续使用旧存档！</i></p>");
			
			sb.append(getJavaVersionInformation());
			
			if(Toolkit.getDefaultToolkit().getScreenSize().getHeight()<800) {
				sb.append("<p style='text-align:center; color:").append(PresetColour.GENERIC_ARCANE.toWebHexString()).append(";'>")
					.append("若游戏分辨率与屏幕不符，请按下：'Windows' + 'Up Arrow'来最大化窗口！</p>");
			}
			if(!Main.game.isStarted() && !Main.getProperties().name.isEmpty()) {
				sb.append("<h4 style='text-align:center;'>最近存档：</h4>");
				sb.append("<div class='container-full-width' style='width:50%;margin:0 25%;'>");
					sb.append("<h5 style='color:").append(Main.getProperties().nameColour).append(";text-align:center;'>").append(Main.getProperties().name).append("</h5>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'><b>Level ").append(Main.getProperties().level).append("</b></p>");
					String colourString = Main.getProperties().raceColour;
					if(!colourString.isEmpty()) {
						colourString = "color:"+colourString+";";
					}
					sb.append("<p style='text-align:center;margin:0;padding:0;").append(colourString).append("'><b>").append(Util.capitaliseSentence(Main.getProperties().race)).append("</b></p>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'>").append(UtilText.formatAsMoney(Main.getProperties().money, "b")).append("</p>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'>").append(UtilText.formatAsEssences(Main.getProperties().arcaneEssences, "b", false)).append("</p>");
					sb.append("<p style='text-align:center;margin:0;padding:0;'>任务：").append(Util.capitaliseSentence(Main.getProperties().quest)).append("</p>");
				sb.append("</div>");
			}
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 1) {
				 if(confirmNewGame || !Main.game.isStarted()) {
					return new ResponseEffectsOnly(
							(!Main.game.isStarted()
									?"新游戏"
									:"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>确认</b>"),
							"开始新游戏。"
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(记得先存档！)]</b>"
									:"")){
						@Override
						public void effects() {
							if(!startingNewGame) {
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
								Main.startNewGame(CharacterCreation.CHARACTER_CREATION_START);
								confirmNewGame = false;
							}
						}
					};
					
				 } else {
					 return new Response(
							 "新游戏",
							 "开始新游戏。"
								+(Main.game.isStarted()
									?"<br/><br/>[style.italicsMinorBad(记得先存档！)]"
									:""),
							 MenuOverrides.MENU){
							@Override
							public void effects() {
								confirmNewGame = true;
							}
						};
				 }
				
			} else if (index == 2) {
				return new Response("存档/读档", "打开存档/读档界面。", SAVE_LOAD){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 3) {
				return new Response("导出角色", "打开角色导出界面。", IMPORT_EXPORT){
					@Override
					public void effects() {
						loadConfirmationName = ""; overwriteConfirmationName = ""; deleteConfirmationName = "";
						confirmNewGame=false;
					}
				};
				
			} else if (index == 4) {
				return new Response("声明", "查看游戏声明。", DISCLAIMER){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
				
			} else if (index == 5) {
				return new ResponseEffectsOnly("退出", "退出当前游戏并关闭程序。<br/><br/><b>记得先存档！</b>"){
					@Override
					public void effects() {
						Main.primaryStage.close();
						confirmNewGame=false;
						System.exit(0);
					}
				};
				
			} else if (index == 6) {
				return new Response("选项", "打开选项界面。", OPTIONS){
					@Override
					public void effects() {
						confirmNewGame=false;
						
					}
				};

			} else if (index == 7) {
				return new Response("内容选项", "设置偏好内容。", MISCELLANEOUS){
					@Override
					public void effects() {
						confirmNewGame=false;
					}
				};
			
			} else if (index == 8) {
				return new Response("更新日志", "查看当前版本的更新日志。", PATCH_NOTES);
			
			} else if (index == 9) {
				return new Response("贡献名单", "查看游戏的贡献名单画面。", CREDITS);
				
			} else if (index == 11) {
				return new ResponseEffectsOnly("博客", "打开页面：<br/><br/><i>https://lilithsthrone.blogspot.co.uk/</i><br/><br/><b>将使用默认浏览器打开。</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://lilithsthrone.blogspot.co.uk/");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 12) {
				return new ResponseEffectsOnly("Github", "打开页面：<br/><br/><i>https://github.com/Innoxia/liliths-throne-public</i><br/><br/><b>将使用默认浏览器打开。</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://github.com/Innoxia/liliths-throne-public");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 13) {
				return new ResponseEffectsOnly("维基", "打开页面：<br/><br/><i>https://www.lilithsthrone.com/wiki/doku.php</i><br/><br/><b>将使用默认浏览器打开。</b>"){
					@Override
					public void effects() {
						Util.openLinkInDefaultBrowser("https://www.lilithsthrone.com/wiki/doku.php");
						confirmNewGame=false;
					}
				};
			
			} else if (index == 0) {
				if(Main.game.isStarted()) {
					return new ResponseEffectsOnly("返回", "返回打开此菜单前的位置。"){
						@Override
						public void effects() {
							Main.mainController.openOptions();
							confirmNewGame=false;
							
						}
					};
					
				} else {
					if(Main.isLoadGameAvailable(Main.getProperties().lastSaveLocation)) {
						return new ResponseEffectsOnly("继续", "继续游玩最新存档。"){
							@Override
							public void effects() {
								Main.loadGame(Main.getProperties().lastSaveLocation);
								confirmNewGame=false;
								
							}
						};
					} else if ( "".equals(Main.getProperties().lastSaveLocation) ) {
						return new Response("继续", "没有存档可以继续。", null);
					} else {
						return new Response("继续", "上次存档(名为'"+Main.getProperties().lastSaveLocation+"')未在'data/saves'文件夹中找到。", null);
					}
				}
				
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getJavaVersionInformation() {
		StringBuilder sb = new StringBuilder();
		String version = System.getProperty("java.version");
		
		sb.append("<p style='text-align:center;'>");
			sb.append("你的java版本："+System.getProperty("java.version"));
			if (!version.equals("1.8.0_172")) {
				sb.append("<br/>[style.italicsBad(推荐使用的java版本为 1.8.0_172 ！)]");
				sb.append("<br/>[style.italicsMinorBad(这可能导致异常事件，例如鼠标悬停界面卡顿！请使用推荐的版本号启动或使用.exe构建版本。)]");
			}
		sb.append("</p>");
//				+" | ");
		
//		String[] version = System.getProperty("java.version").split("\\.");
//		if(version[0]!=null) {
//			if(Integer.valueOf(version[0])<9) {
//				sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least 9.0.1 to work correctly!");
//			} else {
//				sb.append("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//			}
//		}
//		if(version.length>=2) {
//			if(Integer.valueOf(version[1])<8) {
//				sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//				
//			} else {
//				if(version.length==3){
//					String[] versionMinor = version[2].split("_");
//					if(versionMinor.length>=2)
//						if(Integer.valueOf(versionMinor[1])<131) {
//							sb.append("<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>You have an old version of java!</span> This game needs at least v1.8.0_131 to work correctly!");
//							
//						} else {
//							sb.append("<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>Your java is up to date!</span>");
//						}
//				} else {
//					sb.append("This game needs at least v1.8.0_131 to work correctly!");
//				}
//			}
//		}
		
		
		return sb.toString();
	}

	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";
	
	public static final DialogueNode SAVE_LOAD = new DialogueNode("存档文件", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(
					"<p style='text-align:center;'>"
						+ "<b>请注意：</b>"
					+ "</p>"
					+ "<p>"
						+ "1. 存档文件名仅支持标准字符(字母及数字)。<br/>"
						+ "2. “AutoSave”文件会在地图间移动时进行自动覆盖。<br/>"
						+ "3. “QuickSave”文件会在每次按下快捷存档(按键为"+Main.getProperties().hotkeyMapPrimary.get(KeyboardAction.QUICKSAVE).getFullName()+")时进行自动覆盖。<br/>"
						+ "4. 在禁止移动的场景中无法存档，包括战斗与性交。"
					+ "</p>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "时间"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); text-align:center; background:transparent;'>"
							+ "名称"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "存档 | 读档 | 删除"
						+ "</div>"
					+ "</div>");


			int i=0;
			
			if(Main.game.isStarted()) {
				saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
				i++;
			}
			
//			Main.getSavedGames(alphabeticalFileSort).sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSavedGames(alphabeticalFileSort)) {
				saveLoadSB.append(getSaveLoadRow("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("确认状态：",
						"在点击读取、覆写或删除存档时会进入确认状态。"
							+ "启用时，需要进行两次点击才能使行为生效。"
							+ "关闭时只需要一次点击。",
						SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "确认状态："+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>开启</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>关闭</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 2) {
				return new Response("排序：日期", "根据日期对所有存档进行排序。", SAVE_LOAD) {
					@Override
					public void effects() {
						alphabeticalFileSort = false;
					}
				};

			} else if (index == 3) {
				return new Response("排序：名称", "根据名称对所有存档进行排序。", SAVE_LOAD) {
					@Override
					public void effects() {
						alphabeticalFileSort = true;
					}
				};

			} else if (index == 0) {
				return new Response("Back", "Back to the main menu.", MenuOverrides.MENU);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode IMPORT_EXPORT = new DialogueNode("导出角色", "", true) {
	
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
	
			saveLoadSB.append("<p>"
						+ "在这里你可以导出当前角色，或删除任意曾导出角色。"
						+ "任意NPC都能在游戏内进行导出，只需查看其信息界面(通过“角色状态”或手机中的“联系”界面)，并点击“导出角色”按钮(位于UI的右下角)。"
					+ "</p>"
					+ "<p>"
						+ "导出的角色可以在开始新游戏时作为游玩角色(选择“开始(导入)”)，或在奴隶巷的拍卖台处作为奴隶导入。"
					+ "</p>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-quarter-width' style='text-align:center;'>"
							+ "时间"
						+ "</div>"
						+ "<div class='container-half-width' style='width:calc(55% - 16px); text-align:center; background:transparent;'>"
							+ "名称"
						+ "</div>"
						+ "<div class='container-quarter-width' style='width:calc(20% - 16px); text-align:center; background:transparent;'>"
							+ "功能"
						+ "</div>"
					+ "</div>");
			
			Main.getCharactersForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			int i = 0;
			for(File f : Main.getCharactersForImport()){
				saveLoadSB.append(OptionsDialogue.getImportRow("<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+Util.getFileTime(f)+"</span>", f.getName(), i%2==0));
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("确认状态：",
						"在点击读取、覆写或删除存档时会进入确认状态。"
							+ "启用时，需要进行两次点击才能使行为生效。"
							+ "关闭时只需要一次点击。",
							IMPORT_EXPORT) {
					@Override
					public String getTitle() {
						return "确认状态："+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>开启</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>关闭</span>");
					}
					
					@Override
					public void effects() {
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};
	
			} else if (index == 2) {
				if(Main.game.isStarted()) {
					return new Response("导出角色", "将你的角色文件导出至'data/characters/'文件夹。", IMPORT_EXPORT){
						@Override
						public void effects() {
							Main.game.getCharacterUtils().saveCharacterAsXML(Main.game.getPlayer());
							Main.game.flashMessage(PresetColour.GENERIC_GOOD, "角色已导出！");
						}
					};
				} else {
					return new Response("导出角色", "你需要先开始一场游戏！", null);
				}
			
			} else if (index == 0) {
				return new Response("Back", "Back to the main menu.", MenuOverrides.MENU);
	
			} else {
				return null;
			}
		}
	
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getSaveLoadRow(String date, String name, boolean altColour) {
		if(name!=null){
			String baseName = Util.getFileName(name);
			String identifierName = Util.getFileIdentifier(name);

			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ date
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+ baseName
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
									?(name.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled' id='OVERWRITE_" + identifierName + "_DISABLED'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (name.equals(loadConfirmationName)
									?"<div class='square-button saveIcon' id='LOAD_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
									:"<div class='square-button saveIcon' id='LOAD_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
	
	
							+ (name.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";":"")+"'>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); background:transparent;'>"
							+ "-"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(50% - 16px); background:transparent;'>"
							+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' value='New Save' style='padding:0;margin:0;width:100%;'></form>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ (Main.isSaveGameAvailable()
								?"<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
								:"<div class='square-button saveIcon disabled' id='NEW_SAVE_DISABLED' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
						+ "</div>"
					+ "</div>";
				
		}
	}

	private static String getImportRow(String date, String name, boolean altColour) {
		String baseName = Util.getFileName(name);
		String identifierName = Util.getFileIdentifier(name);
		
		return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					+ "<div class='container-quarter-width' style='background:transparent;'>"
						+ date
					+ "</div>"
					+ "<div class='container-half-width' style='width: calc(55% - 16px); background:transparent;'>"
						+ baseName
					+ "</div>"
					+ "<div class='container-quarter-width' style='padding:auto 0; margin:auto 0; width:20%; text-align:center; background:transparent;'>"
					+ (name.equals(deleteConfirmationName)
							?"<div class='square-button big' id='DELETE_CHARACTER_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
							:"<div class='square-button big' id='DELETE_CHARACTER_" + identifierName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
					+ "</div>"
				+ "</div>";
	}
	
	
	public static final DialogueNode OPTIONS = new DialogueNode("选项", "选项", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
					+ "<b>明亮/黑暗主题：</b>"
					+ "<br/>在明亮和黑暗主题显示风格之间切换。(正在完善！)"
					+ "</p>"
					
					+"<p>"
					+ "<b>字体大小：</b><br/>"
					+ "循环调整游戏的基础字体大小。当前只影响主对话栏中的文本大小，但未来将会扩展到所有现实元素。<br/>"
					+ "最小字体大小为"+Game.FONT_SIZE_MINIMUM+"。默认字体大小为"+Game.FONT_SIZE_NORMAL+"。最大字体大小为"+Game.FONT_SIZE_HUGE+"。<br/>"
					+ "当前字体大小为："+Main.getProperties().fontSize+"。"
					+ "</p>"

					+"<p>"
					+ "<b>渐显：</b>"
					+ "<br/>该选项与每次新场景展示时的文本逐渐显示有关。"
					+ "尽管该功能会使场景切换显得更顺眼些，但默认是关闭状态，开启可能会导致物品栏界面造成烦人的卡顿。"
					+ "</p>"

					+"<p>"
					+ "<b>难度(当前设置为"+Main.getProperties().difficultyLevel.getName()+")：</b>");
			
			for(DifficultyLevel dl : DifficultyLevel.values()) {
				UtilText.nodeContentSB.append("<br/>"+(
						Main.getProperties().difficultyLevel==dl
							?"<b style='color:"+dl.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(dl.getName())+"</b>"+dl.getDescription()
							:"<span style='color:"+dl.getColour().getShades()[0]+";'>"+Util.capitaliseSentence(dl.getName())+"</span> [style.colourDisabled("+dl.getDescription()+")]")
						 );
			}

			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("键位绑定", "打开键位绑定页面，可以自定义游戏所有的键位绑定。", KEYBINDS);
				
			} else if (index == 2) {

				if (Main.getProperties().hasValue(PropertyValue.lightTheme)) {
					return new Response("黑暗主题", "切换主题至黑暗模式。", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
						};
				} else {
					return new Response("明亮主题(未完成)", "切换主题至明亮模式。<br/><br/><b>仍在进展中……</b>", OPTIONS){
						@Override
						public void effects() {
							Main.mainController.switchTheme();
							
						}
					};
				}

			} else if (index == 3) {
				return new Response("字体大小-",
						"缩小游戏字体大小。默认为18。当前值为"+Main.getProperties().fontSize+"。",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize > Game.FONT_SIZE_MINIMUM) {
							Main.getProperties().fontSize--;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 4) {
				return new Response("字体大小+",
						"增大字体大小。默认为18。当前值为"+Main.getProperties().fontSize+"。",
								OPTIONS){
					@Override
					public void effects() {
						if (Main.getProperties().fontSize < Game.FONT_SIZE_HUGE) {
							Main.getProperties().fontSize++;
						}
						Main.saveProperties();
						
					}
				};
			
			} else if (index == 5) {
				return new Response("渐显：" + (Main.getProperties().hasValue(PropertyValue.fadeInText)
						? "[style.boldGood(开启)]"
						: "[style.boldBad(关闭)]"), "打开游戏文本渐显。开启时可能会导致物品栏界面卡顿。", OPTIONS) {
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.fadeInText, !Main.getProperties().hasValue(PropertyValue.fadeInText));
						Main.saveProperties();
					}
				};
				
			} else if (index == 6) {
				return new Response("性别称呼", "自定义所有性别称呼与名字。", OPTIONS_PRONOUNS);
				
			} else if (index == 7) {
				return new Response("单位偏好", "设置你偏好的计量单位。", UNIT_PREFERENCE);
			} else if (index == 8) {
				return new Response("难度："+Main.getProperties().difficultyLevel.getName(), "循环设置游戏难度。", OPTIONS){
					@Override
					public void effects() {
						switch(Main.getProperties().difficultyLevel) {
							case NORMAL:
								Main.getProperties().difficultyLevel = DifficultyLevel.LEVEL_SCALING;
								break;
							case LEVEL_SCALING:
								Main.getProperties().difficultyLevel = DifficultyLevel.HARD;
								break;
							case HARD:
								Main.getProperties().difficultyLevel = DifficultyLevel.NIGHTMARE;
								break;
							case NIGHTMARE:
								Main.getProperties().difficultyLevel = DifficultyLevel.HELL;
								break;
							case HELL:
								Main.getProperties().difficultyLevel = DifficultyLevel.NORMAL;
								break;
						}
						Main.saveProperties();
						
						for(NPC npc : Main.game.getAllNPCs()) {
							if(!Main.game.isInCombat() || !Main.combat.getAllCombatants(false).contains(npc)) {
								npc.setMana(npc.getAttributeValue(Attribute.MANA_MAXIMUM));
								npc.setHealth(npc.getAttributeValue(Attribute.HEALTH_MAXIMUM));
							}
						}
					}
				};
			} else if (index == 0) {
				return new Response("Back", "Back to the main menu.", MenuOverrides.MENU);

			}
			else if (index == 12 || index == 13 || index == 14) return ModOptions.getModOptionsResponses(index);

			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode KEYBINDS = new DialogueNode("选项", "选项", true) {

		@Override
		public String getHeaderContent() {
			return "<p>"
					+ "<table align='center'>"
					+ "<tr><th>动作</th><th>主键位</th><th>备用键位</th></tr>"
					+ getKeybindTableRow(KeyboardAction.MENU)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.QUICKSAVE)
					+ getKeybindTableRow(KeyboardAction.QUICKLOAD)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MENU_SELECT)
					+ getKeybindTableRow(KeyboardAction.INVENTORY)
					+ getKeybindTableRow(KeyboardAction.JOURNAL)
					+ getKeybindTableRow(KeyboardAction.MAP)
					+ getKeybindTableRow(KeyboardAction.CHARACTERS)
					+ getKeybindTableRow(KeyboardAction.ZOOM)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_EAST)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_1)
					+ getKeybindTableRow(KeyboardAction.RESPOND_2)
					+ getKeybindTableRow(KeyboardAction.RESPOND_3)
					+ getKeybindTableRow(KeyboardAction.RESPOND_4)
					+ getKeybindTableRow(KeyboardAction.RESPOND_5)
					+ getKeybindTableRow(KeyboardAction.RESPOND_6)
					+ getKeybindTableRow(KeyboardAction.RESPOND_7)
					+ getKeybindTableRow(KeyboardAction.RESPOND_8)
					+ getKeybindTableRow(KeyboardAction.RESPOND_9)
					+ getKeybindTableRow(KeyboardAction.RESPOND_10)
					+ getKeybindTableRow(KeyboardAction.RESPOND_11)
					+ getKeybindTableRow(KeyboardAction.RESPOND_12)
					+ getKeybindTableRow(KeyboardAction.RESPOND_13)
					+ getKeybindTableRow(KeyboardAction.RESPOND_14)
					+ getKeybindTableRow(KeyboardAction.RESPOND_0)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_PAGE)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_PAGE)

					+ getKeybindTableRow(KeyboardAction.RESPOND_NEXT_TAB)
					+ getKeybindTableRow(KeyboardAction.RESPOND_PREVIOUS_TAB)
					+ "<tr style='height:8px;'></tr>"

					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_NORTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_WEST)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_SOUTH)
					+ getKeybindTableRow(KeyboardAction.MOVE_RESPONSE_CURSOR_EAST)
					+ "</table>"
					+ "</p>";
		}
		
		@Override
		public String getContent(){
			return "";
		}

		ArrayList<Properties> presets;

		private void loadPresets() {
			presets = new ArrayList<>();

			// Load all text files in the folder as properties
			File presetFolder = new File("res/keybinds");
			if (presetFolder.exists() && presetFolder.isDirectory()) {
				for (File f : presetFolder.listFiles((dir, name) -> name.endsWith(".txt"))) {
					try (FileInputStream input = new FileInputStream(f)) {
						Properties preset = new Properties();
						preset.load(input);
						presets.add(preset);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				presetFolder.mkdirs();
			}
		}

		private void loadPreset(Properties preset) {
			// Clear existing mappings
			Main.getProperties().hotkeyMapPrimary.clear();
			Main.getProperties().hotkeyMapSecondary.clear();

			// Create a key mapping for every action contained in the given property
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (preset.containsKey(ka.name())) {
					String[] keys = preset.getProperty(ka.name()).split(" or ");
					Main.getProperties().hotkeyMapPrimary.put(ka, KeyCodeWithModifiers.fromString(keys[0]));
					if (keys.length == 2) Main.getProperties().hotkeyMapSecondary.put(ka, KeyCodeWithModifiers.fromString(keys[1]));
				}
			}
		}

		private void savePreset(int index) {
			// Create new properties containing current key mappings
			Properties preset = new Properties();
			preset.setProperty("NAME", "Custom " + index);
			preset.setProperty("DESCRIPTION", "Reapply your previously saved key bindings.");

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapPrimary.entrySet())
				if (e.getValue() != null)
					preset.setProperty(e.getKey().name(), e.getValue().toString());

			for (Map.Entry<KeyboardAction, KeyCodeWithModifiers> e : Main.getProperties().hotkeyMapSecondary.entrySet()) {
				if (e.getValue() != null) {
					// Write or append to existing entry
					String primary = preset.getProperty(e.getKey().name());
					primary = primary == null ? e.getValue().toString() : primary + " or " + e.getValue().toString();
					preset.setProperty(e.getKey().name(), primary);
				}
			}

			// Write properties to file
			try (FileOutputStream output = new FileOutputStream("res/keybinds/custom" + index + ".txt")) {
				preset.store(output, "");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			// Load the presets if uninitialized
			if (presets == null) loadPresets();

			if (index == 0) {
				return new Response("返回", "返回选项菜单。", OPTIONS);
				
			} else if (index <= presets.size()) {
				Properties preset = presets.get(index - 1);
				return new Response(preset.getProperty("NAME", "自定义" + index), preset.getProperty("DESCRIPTION", "重新应用曾经保存的键位绑定。"), KEYBINDS) {
					@Override
					public void effects() {
						loadPreset(preset);
						Main.saveProperties();
					}
				};
				
			} else if (index == 14) {
				return new Response("保存预设",
						"将当前键位绑定保存到文件中。若想删除已经保存的预设，请前往“res/keybinds”文件夹删除不再需要的txt文件。"
								+ "(游戏重启后不会再于该列表内出现)",
						KEYBINDS) {
					@Override
					public void effects() {
						savePreset(presets.size() - 2);
						loadPresets();
					}
				};
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getKeybindTableRow(KeyboardAction action) {
		return "<tr>"
				+ "<td>"
				+ action.getName()
				+ "</td>"
				+ "<td style='min-width:160px;'>"
				+ "<div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='KB_PRIMARY_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapPrimary.get(action).getFullName())
				+ "</div>"
				+ "<div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapPrimary.get(action) == null ? " empty" : "")
				+ "' id='KB_PRIMARY_CLEAR_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
				+ "<div class='bindingButton"
				+ (Main.mainController.getActionToBind() == action
						&& !Main.mainController.isPrimaryBinding() ? " active" : "")
				+ "' id='KB_SECONDARY_"
				+ action
				+ "'>"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? "<span class='option-disabled'>-</span>" : Main.getProperties().hotkeyMapSecondary.get(action).getFullName())
				+ "</div>"
				+ "<div class='bindingClearButton"
				+ (Main.getProperties().hotkeyMapSecondary.get(action) == null ? " empty" : "")
				+ "' id='KB_SECONDARY_CLEAR_"
				+ action
				+ "'><b>x</b></div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNode OPTIONS_PRONOUNS = new DialogueNode("选项", "选项", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
						+ "<h5 style='text-align:center;'>全局性别名称：</h5>"
						+ "<table align='center'>"
							+ "<tr>"
							+ "<th>身体部位</th>"
								+ "<th style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>男性</th>"
								+ "<th style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>中性</th>"
								+ "<th style='color:"+PresetColour.FEMININE.toWebHexString()+";'>女性</th>"
							+ "</tr>");
			
			for(GenderNames gn : GenderNames.values()) {
				sb.append(getGenderNameTableRow(gn));
			}
							
			sb.append("</table>"
					+ "</p>"
					
					+ "<p>"
						+ "<h5 style='text-align:center;'>角色人称：</h5>"
						+ "<table align='center'>"
							+ "<tr>"
								+ "<th>人称代词</th>"
								+ "<th style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>男性</th>"
								+ "<th style='color:"+PresetColour.FEMININE.toWebHexString()+";'>女性</th>"
							+ "</tr>"
							+ getPronounTableRow(GenderPronoun.NOUN)
							+ getPronounTableRow(GenderPronoun.YOUNG_NOUN)
							+ getPronounTableRow(GenderPronoun.SECOND_PERSON)
							+ getPronounTableRow(GenderPronoun.THIRD_PERSON)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_BEFORE_NOUN)
							+ getPronounTableRow(GenderPronoun.POSSESSIVE_ALONE)
						+ "</table>"
					+ "</p>"
					+ "<h5 style='text-align:center;'><span style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>中性身体</span>(选项3)</h5>"
					+ "<p>"
					+ "<b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>女性：</b>被视作<b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>女性</b>。<br/>"
					+ "<b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>衣着女性：</b>根据衣着偏向决定。"
							+ "若衣着为中性，则被视作<b style='color:"+PresetColour.FEMININE.toWebHexString()+";'>女性</b>。<br/>"
					+ "<b style='color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>衣着男性：</b>根据衣着偏向决定。"
							+ "若衣着为中性，则被视作<b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>男性</b>。<br/>"
					+ "<b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>男性：</b>被视作<b style='color:"+PresetColour.MASCULINE.toWebHexString()+";'>男性</b>。<br/>"
					+ "</p>");
			
			return sb.toString();	
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("保存", "保存当前显示的所有人称。") {
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_MASCULINE_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameNeutral.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_ANDROGYNOUS_" + gn +"').value")).toLowerCase());
							Main.getProperties().genderNameFemale.put(gn, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('GENDER_NAME_FEMININE_" + gn +"').value")).toLowerCase());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('feminine_" + gp +"').value")).toLowerCase());
							Main.getProperties().genderPronounMale.put(gp, ((String) Main.mainController.getWebEngine().executeScript("document.getElementById('masculine_" + gp +"').value")).toLowerCase());
						}
						Main.saveProperties();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "人称已保存！");
					}
				};
				
			} else if (index == 2) {
				return new Response("<span style='color:"+Main.getProperties().androgynousIdentification.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(Main.getProperties().androgynousIdentification.getName())+"</span>",
						"循环设置游戏中中性身体被如何看待，如上文所示。", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						switch(Main.getProperties().androgynousIdentification){
							case FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_FEMININE;
								break;
							case CLOTHING_FEMININE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.CLOTHING_MASCULINE;
								break;
							case CLOTHING_MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.MASCULINE;
								break;
							case MASCULINE:
								Main.getProperties().androgynousIdentification=AndrogynousIdentification.FEMININE;
								break;
							default:
								break;
						}
						
						Main.saveProperties();
					}
				};
				
			} else if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(确认恢复默认)]", "重设所有人称为预设值。", OPTIONS_PRONOUNS){
					@Override
					public void effects() {
						for(GenderNames gn : GenderNames.values()) {
							Main.getProperties().genderNameMale.put(gn, gn.getMasculine());
							Main.getProperties().genderNameNeutral.put(gn, gn.getNeutral());
							Main.getProperties().genderNameFemale.put(gn, gn.getFeminine());
						}
						for (GenderPronoun gp : GenderPronoun.values()) {
							Main.getProperties().genderPronounFemale.put(gp, gp.getFeminine());
							Main.getProperties().genderPronounMale.put(gp, gp.getMasculine());
						}
						Main.saveProperties();
						
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回选项菜单。", OPTIONS);
				
			} else {
				return null;
			}
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static Response getDefaultResetConfirmationResponse() {
		return new ResponseEffectsOnly("默认", "将所有人称代词重置为默认值。<br/>[style.italicsMinorBad(需要二次确认。)]") {
			@Override
			public void effects() {
				defaultResetConfirmation = true;
				Main.game.updateResponses();
			}
		};
	}
	
	private static String getGenderNameTableRow(GenderNames name) {
		return "<tr>"
					+ "<td>"
						+ (name.isHasPenis()?"[style.colourGood(阴茎)]":"[style.colourDisabled(阴茎)]")
						+ "" + (name.isHasVagina()?"[style.colourGood(阴道)]":"[style.colourDisabled(阴道)]")
						+ "" + (name.isHasBreasts()?"[style.colourGood(乳房)]":"[style.colourDisabled(乳房)]")
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_MASCULINE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameMale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_ANDROGYNOUS_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameNeutral.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
					+ "<td style='min-width:160px;'>"
						+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='GENDER_NAME_FEMININE_" + name + "' value='"
						+ UtilText.parseForHTMLDisplay(Main.getProperties().genderNameFemale.get(name))
						+ "'>"
						+ "</form>"
					+ "</td>"
				+ "</tr>";
	}
	
	private static String getPronounTableRow(GenderPronoun pronoun) {
		return "<tr>"
				+ "<td>"
					+ pronoun.getName()
				+ "</td>"
					+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='masculine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounMale.get(pronoun))+ "'>"
					+ "</form>"
				+ "</td>"
				+ "<td style='min-width:160px;'>"
					+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='feminine_" + pronoun + "' value='"+ UtilText.parseForHTMLDisplay(Main.getProperties().genderPronounFemale.get(pronoun))+ "'></form>"
				+ "</td>"
				+ "</tr>";
	}
	
	
	public static final DialogueNode PATCH_NOTES = new DialogueNode("更新日志", "更新日志", true) {
		
		@Override
		public String getContent(){
			return Main.getPatchNotes();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MenuOverrides.MENU);
				
			}else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}

		@Override
		public boolean isContentParsed() {
			return false;
		}
	};
	
	public static final DialogueNode DISCLAIMER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent(){
			return Main.disclaimer;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("Back", "Go back to the options menu.", MenuOverrides.MENU);
			}
			return null;
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	
	public static final DialogueNode GENDER_PREFERENCE = new DialogueNode("性别偏好", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
						+ "<summary>[style.boldFeminine(点击以获得更多信息。)]</summary>"
						+ "这些选项将会决定游戏中遇到的随机NPC的性别比例。"
						+ "部分NPC，如随机魅魔袭击者，在性别上有限制，但你的偏好会尽可能地生效。<br/>"
						+ "<b>遭遇概率会在每一部分的底部以可视化的条状图显示。</b>"
						+ "(每个性别的都会以不同颜色表示方便辨识，颜色并不代表什么)"
						+ "<br/>"
						+ "如果一个角色至少有AA罩杯，就会被认为有乳房。"
					+ "</details>");
			
			// Offspring preferences:

			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.ANDROGYNOUS,  "后代性别偏好", "定义哪些后代将套用你当前的性别偏好。"));
				int[] orderOptions = new int[] {3, 0, 2, 1};
				for(int i : orderOptions) {
					UtilText.nodeContentSB.append(
							(Main.getProperties().offspringGenderLevel==i
								?"<div id='OFFSPRING_GENDER_PREF_"+i+"' class='normal-button selected' style='width:48%; margin:1%; text-align:center; float:right; color:"+PresetColour.ANDROGYNOUS.toWebHexString()+";'>"
									+ com.lilithsthrone.game.Properties.offspringGenderName[i]
									+ "</div>"
								:"<div id='OFFSPRING_GENDER_PREF_"+i+"' class='normal-button' style='width:48%; margin:1%; text-align:center; float:right;'>"
									+ "[style.colourDisabled("+com.lilithsthrone.game.Properties.offspringGenderName[i]+")]"
									+ "</div>"));
				}
			UtilText.nodeContentSB.append("</div></div>");
			
			// Gender preferences:
			
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(getGenderPreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			 if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(确认恢复默认)]", "恢复所有性别偏好至默认值。", GENDER_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetGenderPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getGenderPreferencesPanel(PronounType type) {
		int count = 0;
		Colour colour = PresetColour.MASCULINE;
		switch(type) {
			case FEMININE:
				colour = PresetColour.FEMININE;
				break;
			case MASCULINE:
				colour = PresetColour.MASCULINE;
				break;
			case NEUTRAL:
				colour = PresetColour.ANDROGYNOUS;
				break;
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		for(Gender g : Gender.values()) {
			if(g.getType()==type) {
				sb.append(
						"<div style='display:inline-block; margin:4px auto;width:100%;'>"
							+ "<div style='display:inline-block; margin:0 auto;'>"
								+ "<div style='width:140px; float:left;'><b style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"</b></div>");
				
				for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
					sb.append("<div id='"+preference+"_"+g+"' class='preference-button"+(Main.getProperties().genderPreferencesMap.get(g)==preference.getValue()?" selected":"")+"'>"+Util.capitaliseSentence(preference.getName())+"</div>");
				}
								
				sb.append("<p><br/>"
								+ "<span style='color:"+colour.getShades(8)[count]+";'>" +Util.capitaliseSentence(g.getName())+"</span>"
										+(g.getGenderName().isHasVagina()?"拥有[style.colourGood(阴道)]":"没有[style.colourBad(阴道)]")+"，"
										+(g.getGenderName().isHasPenis()?"拥有[style.colourGood(阴茎)]":"没有[style.colourBad(阴茎)]")+"，"
										+ (g.getGenderName().isHasBreasts()?"拥有[style.colourGood(乳房)]":"没有[style.colourBad(乳房)]")+"。"
								+ "</p>"
							+ "</div>"
						+ "</div>"
						+ "<hr/>");
				count++;
			}
		}
		
		sb.append(
				getGenderRepresentation()
				+"</div>");
		
		return sb.toString();
	}

	private static String getOrientationRepresentation() {
		float total=0;
		for(SexualOrientation o : SexualOrientation.values()) {
			total+=Main.getProperties().orientationPreferencesMap.get(o);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+PresetColour.ANDROGYNOUS.toWebHexString()+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			for(SexualOrientation o : SexualOrientation.values()) {
				sb.append("<div style='width:" + (Main.getProperties().orientationPreferencesMap.get(o)/total) * (100) + "%; height:12px; background:"
						+ o.getColour().toWebHexString() + "; float:left; border-radius: 2;'></div>");
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode ORIENTATION_PREFERENCE = new DialogueNode("性取向偏好", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
						+ "<summary>[style.boldAndrogynous(点击以获得更多信息。)]</summary>"
						+ "这些选项会决定遭遇的随机NPC的性取向比例。"
						+ "请注意NPC的种族和女性化程度会对其取向有一定影响，且部分NPC拥有预设性取向，但你的偏好会尽可能地生效。</br>"
						+ "<b>遭遇概率会在每一部分的底部以可视化的条状图显示。</b>"
						+ "(每个取向的都会以不同颜色表示方便辨识，颜色并不代表什么)"
					+ "</details>"
		
					+ "<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.ANDROPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.AMBIPHILIC));
			UtilText.nodeContentSB.append(getOrientationPreferencesPanel(SexualOrientation.GYNEPHILIC));

			UtilText.nodeContentSB.append(getOrientationRepresentation() + "</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(确认恢复默认)]", "恢复所有取向偏好至默认值。", ORIENTATION_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetOrientationPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode FETISH_PREFERENCE = new DialogueNode("性癖偏好", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
						+ "<summary>[style.boldFetish(点击以获得更多信息。)]</summary>"
						+ "这些选项会决定遭遇的随机NPC拥有这些性癖或偏好的比例。"
						+ "部分NPC的种族会更有可能拥有特定性癖，但你的偏好会尽可能地生效。<br/>"
						+ "内容设置将会允许/禁用相关性癖。"
					+ "</details>"
							
					+ "<div class='container-full-width' style='text-align:center;'>");
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					UtilText.nodeContentSB.append(getFetishPreferencesPanel(fetish));
				}
			}
			
			UtilText.nodeContentSB.append("</div>");
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(确定恢复默认)]", "恢复所有性癖偏好至默认值。", FETISH_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetFetishPreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getOrientationPreferencesPanel(SexualOrientation orient) {
		Colour colour = orient.getColour();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
					+ "<div style='width:140px; float:left;'><b style='color:"+colour.toWebHexString()+";'>" +Util.capitaliseSentence(orient.getName())+"</b></div>");
		
		for(SexualOrientationPreference preference : SexualOrientationPreference.values()) {
			sb.append("<div id='"+preference+"_"+orient+"' class='preference-button"+(Main.getProperties().orientationPreferencesMap.get(orient)==preference.getValue()?" selected":"")+"'>"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
		}
						
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}

	public static String getInformationDiv(String id, TooltipInformationEventListener information) {
		Game.informationTooltips.put(id, information);
		return "<div class='title-button no-select' id='"+id+"' style='position:relative; float:left; background:transparent; padding:0; margin:0;'>"
					+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
				+"</div>";
	}
	
	private static String getFetishPreferencesPanel(AbstractFetish fetish) {
		StringBuilder sb = new StringBuilder();
		
		Colour highlightColour = FetishPreference.valueOf(Main.getProperties().fetishPreferencesMap.get(fetish)).getColour();
		
		sb.append("<div style='display:inline-block; margin:4px auto;width:100%;'>"
				+ "<div style='display:inline-block; margin:0 auto;'>"
				+ getInformationDiv(fetish.getId()+"_INFO", new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(fetish.getName(Main.game.getPlayer())), fetish.getDescription(null)))
				+ "<div style='width:150px; float:left;'><b style='color:"+highlightColour.toWebHexString()+";'>"+Util.capitaliseSentence(fetish.getName(null))+"</b></div>");
		
		for(FetishPreference preference : FetishPreference.values()) {
			String disabledMsg=null;
			if(!fetish.isContentEnabled()) {
				if (Fetish.FETISH_SIZE_QUEEN.equals(fetish)) {
					disabledMsg = "插入大小差别";
				} else if (Fetish.FETISH_NON_CON_DOM.equals(fetish) || Fetish.FETISH_NON_CON_SUB.equals(fetish)) {
					disabledMsg = "非自愿";
				} else if (Fetish.FETISH_INCEST.equals(fetish)) {
					disabledMsg = "乱伦";
				} else if (Fetish.FETISH_LACTATION_SELF.equals(fetish) || Fetish.FETISH_LACTATION_OTHERS.equals(fetish)) {
					disabledMsg = "泌乳";
				} else if (Fetish.FETISH_ANAL_RECEIVING.equals(fetish) || Fetish.FETISH_ANAL_GIVING.equals(fetish)) {
					disabledMsg = "肛门内容";
				} else if (Fetish.FETISH_FOOT_RECEIVING.equals(fetish) || Fetish.FETISH_FOOT_GIVING.equals(fetish)) {
					disabledMsg = "足控内容";
				} else if (Fetish.FETISH_ARMPIT_RECEIVING.equals(fetish) || Fetish.FETISH_ARMPIT_GIVING.equals(fetish)) {
					disabledMsg = "腋窝内容";
				} else {
					disabledMsg = "未确认内容";
				}
			}
			if(disabledMsg!=null) {
				// Disabled fetishes to default, the fetish won't be a valid option for the generator anyway
				Main.getProperties().fetishPreferencesMap.put(fetish, fetish.getFetishPreferenceDefault().getValue());
				sb.append("<div style='display:inline-block;'><span class='option-disabled'>由于"+disabledMsg+"设置，该性癖被强制禁用！</span></div>");
				break;
			} else {
				sb.append("<div id='"+preference+"_"+Fetish.getIdFromFetish(fetish)+"' class='preference-button"+(Main.getProperties().fetishPreferencesMap.get(fetish)==preference.getValue()?" selected":"")+"'"
						+ " style='width:70px;'"
						+ ">"
							+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
		}
		
		sb.append("</div>"
				+ "</div>"
				+ "<hr></hr>");
		
		return sb.toString();
	}
	
	private static String getGenderRepresentation() {
		
		float total=0;
		for(Gender g : Gender.values()) {
			total+=Main.getProperties().genderPreferencesMap.get(g);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+PresetColour.FEMININE.getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
			int f=0, m=0, n=0;
			for(Gender g : Gender.values()) {
				switch(g.getType()) {
					case MASCULINE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+PresetColour.MASCULINE.getShades(8)[m] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						m++;
						break;
					case NEUTRAL:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+PresetColour.ANDROGYNOUS.getShades(8)[n] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						n++;
						break;
					case FEMININE:
						if(Main.getProperties().genderPreferencesMap.get(g)>0) {
							sb.append("<div style='width:calc(" + (Main.getProperties().genderPreferencesMap.get(g)/total) * (100) + "% - 1px); height:12px;"
									+ " background:"+PresetColour.FEMININE.getShades(8)[f] + "; float:left; border-radius: 2;'></div>");
							sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
						}
						f++;
						break;
					default:
						break;
				}
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode AGE_PREFERENCE = new DialogueNode("年龄偏好", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
						+ "<summary>[style.boldAge(点击以获得更多信息。)]</summary>"
						+ "这些选项将会决定游戏中遇到的随机NPC的年龄比例，基于其女性化程度。"
						+ "部分NPC，如随机恶魔和哈比，可能比实际上显得更年轻，但你的偏好会尽可能地生效。<br/>"
						+ "<b>年龄概率会在每一部分的底部以可视化的条状图显示。</b>"
					+ "</details>");
			
			UtilText.nodeContentSB.append(LTOptions.getAgePreferencesPanel(PronounType.MASCULINE));
			UtilText.nodeContentSB.append(LTOptions.getAgePreferencesPanel(PronounType.NEUTRAL));
			UtilText.nodeContentSB.append(LTOptions.getAgePreferencesPanel(PronounType.FEMININE));
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(确认恢复默认)]", "恢复所有年龄偏好至默认值。", AGE_PREFERENCE) {
					@Override
					public void effects() {
						Main.getProperties().resetAgePreferences();
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static String getAgePreferencesPanel(PronounType type) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>"
				+ "<p><b style='color:"+type.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(type.getName())+"</b></p>");
		
		int i=AgeCategory.values().length-1;
		for(AgeCategory ageCat : AgeCategory.values()) {
			sb.append(
					"<div style='display:inline-block; margin:4px auto;width:100%;'>"
						+ "<div style='display:inline-block; margin:0 auto;'>"
							+ "<div style='width:140px; float:left;'><b style='color:"+type.getColour().getShades(AgeCategory.values().length)[i]+";'>" +Util.capitaliseSentence(ageCat.getName())+"</b></div>");
			
			for(ContentPreferenceValue preference : ContentPreferenceValue.values()) {
				sb.append(
						"<div id='"+type+"_"+preference+"_"+ageCat+"' class='preference-button"+(Main.getProperties().agePreferencesMap.get(type).get(ageCat)==preference.getValue()?" selected":"")+"'>"
								+Util.capitaliseSentence(preference.getName())
						+"</div>");
			}
							
			sb.append("</div>"
					+ "</div>"
					+ "<hr/>");
			i--;
		}
		
		sb.append(
				getAgeRepresentation(type)
				+"</div>");
		
		return sb.toString();
	}
	
	public static String getAgeRepresentation(PronounType type) {
		
		float total=0;
		for(AgeCategory ageCat : AgeCategory.values()) {
			total+=Main.getProperties().agePreferencesMap.get(type).get(ageCat);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(total==0) {
			sb.append("<div style='width:100%;height:12px;background:"+type.getColour().getShades()[3]+";float:left;margin:4vw 0 0 0;border-radius: 2px;'>");
			
		} else {
			sb.append("<div style='width:100%;height:12px;background:#222;float:left;margin:4vw 0 0 0;border-radius: 2px;'>");

			int i=(AgeCategory.values().length*2)-1;
			for(AgeCategory ageCat : AgeCategory.values()) {
				if(Main.getProperties().agePreferencesMap.get(type).get(ageCat)>0) {
					sb.append("<div style='width:calc(" + (Main.getProperties().agePreferencesMap.get(type).get(ageCat)/total) * (100) + "% - 1px); height:12px;"
							+ " background:"+type.getColour().getShades(AgeCategory.values().length*2)[i] + "; float:left; border-radius: 2;'></div>");
					sb.append("<div style='width:1px; height:12px; background:#000; float:left; border-radius: 2;'></div>");
				}
				i--;
				i--;
			}
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode FURRY_PREFERENCE = new DialogueNode("福瑞程度偏好", "", true) {
		@Override
		public void applyPreParsingEffects() {
			defaultResetConfirmation = false;
		}
		
		@Override
		public String getHeaderContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<details>"
						+ "<summary>[style.boldHuman(点击以获得更多信息。)]</summary>"
						+ "这些选项决定了你在游戏中会遭遇的福瑞内容数量。"
						+ "<i>这些选项暂时只会影响随机NPC，但在之后的版本开发中或许会为所有主要角色添加非福瑞版本。</i>"
						
						+ "<br/>[style.italicsGood(将鼠标悬浮在按钮上查看各个选项的意思！)]"
						
						+ "<br/>请注意，部分种族(如恶魔和哈比)在福瑞程度偏好的可用设置上受限。"
					+ "</details>"
							
					+ "<span style='height:16px;width:800px;float:left;'></span>");
					
			UtilText.nodeContentSB.append("<div class='container-full-width'>");
					
				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_HUMAN.toWebHexString()+"; float:left; width:100%; text-align:center;'>人类生成率</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"HUMAN_SPAWN_RATE",
											PresetColour.RACE_HUMAN,
											Main.getProperties().humanSpawnRate+"%",
											Main.getProperties().humanSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");

				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_CENTAUR.toWebHexString()+"; float:left; width:100%; text-align:center;'>半兽身人生成率</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"TAUR_SPAWN_RATE",
											PresetColour.RACE_CENTAUR,
											Main.getProperties().taurSpawnRate+"%",
											Main.getProperties().taurSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");

				UtilText.nodeContentSB.append("<div class='container-half-width inner' style='width:31.3%; margin:1%; padding:1%;'>");
					UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RACE_HALF_DEMON.toWebHexString()+"; float:left; width:100%; text-align:center;'>半恶魔生成率</b>");
					UtilText.nodeContentSB.append("<div style='display:inline-block; padding-left:5%; width:100%;'>");
							UtilText.nodeContentSB.append(getSpawnRateDiv(
											"HALF_DEMON_SPAWN_RATE",
											PresetColour.RACE_HALF_DEMON,
											Main.getProperties().halfDemonSpawnRate+"%",
											Main.getProperties().halfDemonSpawnRate,
											0,
											100));
					UtilText.nodeContentSB.append("</div>");
				UtilText.nodeContentSB.append("</div>");
			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.RACE_CENTAUR, "半兽身人上半身福瑞程度", "设置你期望半兽身人上半身的福瑞程度。"));
			UtilText.nodeContentSB.append(
					(Main.getProperties().taurFurryLevel==2
						?"<div id='TAUR_FURRY_LIMIT_"+2+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.MINIMUM.getColour().toWebHexString()+";'>"
							+ FurryPreference.MINIMUM.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+2+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.MINIMUM.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==1
						?"<div id='TAUR_FURRY_LIMIT_"+1+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.HUMAN.getColour().toWebHexString()+";'>"
							+ "人类"
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+1+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled(人类)]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==0
						?"<div id='TAUR_FURRY_LIMIT_"+0+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>"
							+ "不修改"
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+0+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled(不修改)]"
							+ "</div>")
					
					+(Main.getProperties().taurFurryLevel==5
						?"<div id='TAUR_FURRY_LIMIT_"+5+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.MAXIMUM.getColour().toWebHexString()+";'>"
							+ FurryPreference.MAXIMUM.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+5+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.MAXIMUM.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==4
						?"<div id='TAUR_FURRY_LIMIT_"+4+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.NORMAL.getColour().toWebHexString()+";'>"
							+ FurryPreference.NORMAL.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+4+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.NORMAL.getName()+")]"
							+ "</div>")
					+(Main.getProperties().taurFurryLevel==3
						?"<div id='TAUR_FURRY_LIMIT_"+3+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"+FurryPreference.REDUCED.getColour().toWebHexString()+";'>"
							+ FurryPreference.REDUCED.getName()
							+ "</div>"
						:"<div id='TAUR_FURRY_LIMIT_"+3+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+ "[style.colourDisabled("+FurryPreference.REDUCED.getName()+")]"
							+ "</div>"));
			UtilText.nodeContentSB.append("</div></div>");
			
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'>"
												+ "<div style='display:inline-block; margin:4px auto;'>"
													+"<div style='float:left; text-align:right; margin-right:16px;'>"
														+ "<b>设置全局福瑞程度偏好：</b>"
													+ "</div>");
			for(FurryPreference fp : FurryPreference.values()) {
				UtilText.nodeContentSB.append("<div id='ALL_FURRY_"+fp+"' class='normal-button' style='width:80px; margin:0 2px;'>"+fp.getName()+"</div>");
			}
			UtilText.nodeContentSB.append("</div>"
												+ "<div style='display:inline-block; margin:4px auto;'>"
													+"<div style='float:left; text-align:right; margin-right:16px;'>"
														+ "<b>设置全局生成频率：</b>"
													+ "</div>");
			for(SubspeciesPreference sp : SubspeciesPreference.values()) {
				UtilText.nodeContentSB.append("<div id='ALL_SPAWN_"+sp+"' class='normal-button' style='width:80px; margin:0 2px;'>"+Util.capitaliseSentence(sp.getName())+"</div>");
			}
			UtilText.nodeContentSB.append("</div>"
											+"</div>");
												
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align: center;'>"
											+"<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(false)+";'>"
												+"<div class='container-full-width' style='text-align:center; width:calc(60% - 16px);background:transparent; margin:0 0 0 40%; padding:0;'>"
													+ "<b style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+"; float:left; width:50%; text-align:center;'>福瑞程度偏好</b>"
													+ "<b style='color:"+PresetColour.BASE_YELLOW_LIGHT.toWebHexString()+"; float:left; width:50%; text-align:center;'>生成频率</b>"
												+ "</div>"
											+ "</div>");

			int i=0;
			for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
				if(subspecies.isDisplayedInFurryPreferences()) {
					UtilText.nodeContentSB.append(getSubspeciesPreferencesPanel(subspecies, i%2==0));
					i++;
				}
			}
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				if(!defaultResetConfirmation) {
					return getDefaultResetConfirmationResponse();
				}
				return new Response("[style.colourMinorBad(确实恢复默认)]", "恢复所有福瑞和生成偏好至其默认值。", FURRY_PREFERENCE) {
					@Override
					public void effects() {
						for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							Main.getProperties().setFeminineFurryPreference(subspecies, subspecies.getDefaultFemininePreference());
							Main.getProperties().setMasculineFurryPreference(subspecies, subspecies.getDefaultMasculinePreference());

							Main.getProperties().setFeminineSubspeciesPreference(subspecies, subspecies.getSubspeciesPreferenceDefault());
							Main.getProperties().setMasculineSubspeciesPreference(subspecies, subspecies.getSubspeciesPreferenceDefault());
						}
						Main.getProperties().humanSpawnRate = 5;
						Main.getProperties().taurSpawnRate = 5;
						Main.getProperties().halfDemonSpawnRate = 5;
						Main.saveProperties();
					}
				};
			}
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};

	private static String getSpawnRateDiv(String id, Colour colour, String valueDisplay, int value, int minimum, int maximum) {

        return "<div class='container-full-width' style='padding:0; margin:2px 0;'>" +
                "<div id='" + id + "_INCREASE_LARGE' class='normal-button" + (value == maximum ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (value == maximum ? "[style.boldDisabled(+)]" : "[style.boldGood(+)]")
                + "</div>"
                + "<div id='" + id + "_INCREASE' class='normal-button" + (value == maximum ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (value == maximum ? "[style.boldDisabled(+)]" : "[style.boldMinorGood(+)]")
                + "</div>"
                + "<div class='container-full-width' style='text-align:center; width:40%; float:right; margin:0;'>"
                + "<b>" + valueDisplay + "</b>"
                + "</div>"
                + "<div id='" + id + "_DECREASE' class='normal-button" + (value == minimum ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (value == minimum ? "[style.boldDisabled(-)]" : "[style.boldMinorBad(-)]")
                + "</div>"
                + "<div id='" + id + "_DECREASE_LARGE' class='normal-button" + (value == minimum ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (value == minimum ? "[style.boldDisabled(-)]" : "[style.boldBad(-)]")
                + "</div>" +
                "</div>";
	}
	
	private static String getEntryBackgroundColour(boolean alternative) {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			if(alternative) {
				return "#d9d9d9";
			}
			return "#dddddd";
		} else {
			if(alternative) {
				return "#222222";
			}
			return "#1f1f1f";  
		}
	}
	
	private static String getSubspeciesPreferencesPanel(AbstractSubspecies s, boolean altColour) {
		StringBuilder sb = new StringBuilder();
		String baseStyle = "max-width:30px; width:14%; margin:0 1%; padding:0;";
		String subspeciesId = Subspecies.getIdFromSubspecies(s);
		
		sb.append("<div class='container-full-width' style='text-align:center; background:"+getEntryBackgroundColour(altColour)+"; padding:0; margin:0 0 6px 0; border-left:solid 4px "+s.getColour(null).toWebHexString()+";'>");
		
			// Feminine:
			sb.append("<div class='container-full-width' style='text-align:center; width:40%; background:transparent; margin:0; padding:0;'>"
						+"<b style='color:"+PresetColour.FEMININE.toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getSingularFemaleName(null))+"</b>"
					+"</div>");
			
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");

				for(FurryPreference preference : FurryPreference.values()) {
					sb.append("<div id='FEMININE_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isFurryPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesFeminineFurryPreferencesMap().get(s)==preference && s.isFurryPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+preference.getColour().toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				sb.append("</div>");
				sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					sb.append("<div id='FEMININE_SPAWN_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isSpawnPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesFemininePreferencesMap().get(s)==preference && s.isSpawnPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+PresetColour.FEMININE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				
			sb.append("</div>");
			
			// Masculine:
			sb.append("<div class='container-full-width' style='text-align:center; width:40%; background:transparent; margin:0; padding:0;'>"
					+"<b style='color:"+PresetColour.MASCULINE.toWebHexString()+"; float:left; width:100%; text-align:center;'>" +Util.capitaliseSentence(s.getSingularMaleName(null))+"</b>"
				+"</div>");
		
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
			
				for(FurryPreference preference : FurryPreference.values()) {
					sb.append("<div id='MASCULINE_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isFurryPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesMasculineFurryPreferencesMap().get(s)==preference && s.isFurryPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+preference.getColour().toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='text-align:center; width:30%; background:transparent; margin:2px 0; padding:0;'>");
				for(SubspeciesPreference preference : SubspeciesPreference.values()) {
					sb.append("<div id='MASCULINE_SPAWN_"+preference+"_"+subspeciesId+"' class='square-button small"+(!s.isSpawnPreferencesEnabled()?" disabled":"")
								+(Main.getProperties().getSubspeciesMasculinePreferencesMap().get(s)==preference && s.isSpawnPreferencesEnabled()
									?" selected' style='"+baseStyle+" border-color:"+PresetColour.MASCULINE_PLUS.toWebHexString()+";'><div class='square-button-content'>"+preference.getSVGImage(false)+"</div></div>"
									:"' style='"+baseStyle+"'><div class='square-button-content'>"+preference.getSVGImage(true)+"</div></div>"));
				}
				
			sb.append("</div>");

			sb.append("<div class='title-button no-select' id='SUBSPECIES_PREFERENCE_INFO_"+subspeciesId+"' style='position:absolute; margin:0; padding:0; left:1%; right:auto; top:auto; bottom:auto;'>"
							+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()
						+"</div>");
		sb.append("</div>");
		
		return sb.toString();
	}

	public static final DialogueNode UNIT_PREFERENCE = new DialogueNode("单位偏好", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "这些选项决定了游戏中使用的计量单位。"
							+ "启用自动检测会根据你的系统语言矫正设置。"
							+ "<br/><b>调整任意选项都会关闭自动检测。</b>"
							+ "</div>"

							+ "<span style='height:16px;width:800px;float:left;'></span>");

			UtilText.nodeContentSB.append(AUTO_LOCALE.getDiv());
			UtilText.nodeContentSB.append(METRIC_SIZES.getDiv());
			UtilText.nodeContentSB.append(METRIC_FLUIDS.getDiv());
			UtilText.nodeContentSB.append(METRIC_WEIGHTS.getDiv());
			UtilText.nodeContentSB.append(TWENTYFOUR_HOUR_TIME.getDiv());
			UtilText.nodeContentSB.append(INTERNATIONAL_DATE.getDiv());
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("返回", "返回选项菜单。", OPTIONS);

			} else {
				return null;
			}
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
		    return DialogueNodeType.OPTIONS;
		}
	};
	
	/**
	 * To be followed by two closing div elements.
	 */
	private static String getCustomContentPreferenceDivStart(Colour colour, String title, String description) {

        return "<div class='container-full-width' style='padding:0; margin:2px 0;'>"
                + "<div class='container-half-width' style='width:calc(55% - 16px);'>"
                + "<b style='text-align:center; color:" + colour.toWebHexString() + ";'>" + title + "</b><b>:</b> "
                + description
                + "</div>"
                + "<div class='container-half-width' style='width:calc(45% - 16px);'>";
	}
	
	private static String getContentPreferenceDiv(String id, Colour colour, String title, String description, boolean enabled) {
		StringBuilder contentSB = new StringBuilder();
		
		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b>"
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		if(enabled) {
			contentSB.append(
					"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
							+ "[style.boldGood(开启)]"
						+ "</div>"
					+ "<div id='"+id+"_OFF' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.colourDisabled(关闭)]"
					+ "</div>");
		} else {
			contentSB.append(
					"<div id='"+id+"_ON' class='normal-button' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.colourDisabled(开启)]"
					+ "</div>"
					+"<div class='normal-button selected' style='width:25%; margin-right:4%; text-align:center; float:right;'>"
						+ "[style.boldBad(关闭)]"
					+ "</div>");
		}

		contentSB.append("</div>"
				+ "</div>");
		
		return contentSB.toString();
	}
	
	private static String getBreastsContentPreferenceVariableDiv(
			String id,
			Colour colour,
			String title,
			String description,
			String valueDisplay,
			int value, int minimum, int maximum,
			String valueDisplayUdders,
			int valueUdders, int minimumUdders, int maximumUdders) {

        return "<div class='container-full-width' style='padding:0; margin:2px 0;'>"
                + "<div class='container-half-width' style='width:calc(55% - 16px);'>"
                + "<b style='text-align:center; color:" + colour.toWebHexString() + ";'>" + title + "</b><b>:</b> "
                + description
                + "</div>"
                + "<div class='container-half-width' style='width:calc(45% - 16px);'>" +
                "<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
                + "乳房："
                + "<div id='" + id + "_ON' class='normal-button" + (value == maximum ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (value == maximum ? "[style.boldDisabled(+)]" : "[style.boldGood(+)]")
                + "</div>"
                + "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
                + "<b>" + valueDisplay + "</b>"
                + "</div>"
                + "<div id='" + id + "_OFF' class='normal-button" + (value == minimum ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (value == minimum ? "[style.boldDisabled(-)]" : "[style.boldBad(-)]")
                + "</div>"
                + "</div>" +
                "<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
                + "腹乳："
                + "<div id='" + id + "_UDDERS_ON' class='normal-button" + (valueUdders == maximumUdders ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (valueUdders == maximumUdders ? "[style.boldDisabled(+)]" : "[style.boldGood(+)]")
                + "</div>"
                + "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
                + "<b>" + valueDisplayUdders + "</b>"
                + "</div>"
                + "<div id='" + id + "_UDDERS_OFF' class='normal-button" + (valueUdders == minimumUdders ? " disabled" : "") + "' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
                + (valueUdders == minimumUdders ? "[style.boldDisabled(-)]" : "[style.boldBad(-)]")
                + "</div>"
                + "</div>" +
                "</div>"
                + "</div>";
	}

	private static String getSkinColourContentPreferenceVariableDiv(
			String id,
			Colour colour,
			String title,
			String description) {
		
		StringBuilder contentSB = new StringBuilder();
		int minimum = 0;
		int maximum = 10;

		contentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>");
			contentSB.append(
					"<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b>"
						+ description
					+ "</div>");
			
			contentSB.append("<div class='container-half-width' style='width:calc(45% - 16px);'>");
			
				for(Entry<Colour, Integer> entry : Main.getProperties().skinColourPreferencesMap.entrySet()) {
					Colour skinColour = entry.getKey();
					int value = entry.getValue();
					contentSB.append(
							"<div class='container-full-width' style='width:100%; margin:0; padding:0; text-align:right;'>"
								+ "<span style='color:"+skinColour.toWebHexString()+";'>"+Util.capitaliseSentence(skinColour.getName())+":</span> "
								+ "<div id='"+id+"_"+(skinColour).getId()+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
										+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
								+ "</div>"
								+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
									+ "[style.colourSize"+value+"("+value+")]"
								+ "</div>"
								+ "<div id='"+id+"_"+(skinColour).getId()+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
									+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
								+ "</div>"
							+ "</div>");
				}
			
			contentSB.append("</div>");
		contentSB.append("</div>");
		
		return contentSB.toString();
	}
	
	private static String getContentPreferenceVariableDiv(String id, Colour colour, String title, String description, String valueDisplay, int value, int minimum, int maximum) {
		StringBuilder contentSB = new StringBuilder();

		contentSB.append(
				"<div class='container-full-width' style='padding:0; margin:2px 0;'>"
					+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
						+ "<b style='text-align:center; color:"+colour.toWebHexString()+";'>"+ title+"</b><b>:</b>"
						+ description
					+ "</div>"
					+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
		
		contentSB.append(
				"<div id='"+id+"_ON' class='normal-button"+(value==maximum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
						+ (value==maximum?"[style.boldDisabled(+)]":"[style.boldGood(+)]")
				+ "</div>"
				+ "<div class='container-full-width' style='text-align:center; width:calc(30%); float:right; margin:0;'>"
					+ "<b>"+valueDisplay+"</b>"
				+ "</div>"
				+ "<div id='"+id+"_OFF' class='normal-button"+(value==minimum?" disabled":"")+"' style='width:10%; margin:0 2.5%; text-align:center; float:right;'>"
					+ (value==minimum?"[style.boldDisabled(-)]":"[style.boldBad(-)]")
				+ "</div>");
		
		contentSB.append("</div>"
				+"</div>");
		
		return contentSB.toString();
	}
	
	
	public static final DialogueNode CREDITS = new DialogueNode("贡献名单", "", true) {
		
		@Override
		public String getContent(){
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Thank you for playing "+(Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :"Lilith's Throne")+", I hope you enjoy it just as much as I do making it!"
						+ " Thank you so much to all of you who offer financial support! Thanks to you, I'm able to spend more time working on "+(Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :"Lilith's Throne")+", and I promise that I'll make this game the very best that I can!"
					+ "</p>"
					+"<p style='text-align:center;'>"
						+ (Configurations.DEGEN_MODE ? Main.NAME_OF_GAME :"Lilith's Throne")+" has been created by:<br/>"
						+ "<b style='color:#9b78fa;'>Innoxia</b> and modified by <b style='color:00C4C4;'>" + (Configurations.DEGEN_MODE ? Main.AUTHOR : "KeldonSlayer") + "</b>"
						+ "<br/><br/>"
						+ "这些画师为游戏提供了角色绘图：<br/>");
			
			for(Artist artist : Artwork.allArtists) {
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append("<b style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</b><br/>");
				}
			}

			UtilText.nodeContentSB.append("<br/>"
					+ "贡献者：</br>" // In alphabetical order:
					+ "<b style='color:#21bfc5;'>AceXP</b></br>"
					+ "<b style='color:#21bfc5;'>DJ Addi</b></br>"
					+ "<b style='color:#21bfc5;'>DSG</b></br>"
					+ "<b style='color:#21bfc5;'>Irbynx</b></br>"
					+ "<b style='color:#21bfc5;'>Maxis010</b></br>"
					+ "<b style='color:#21bfc5;'>Nnxx</b></br>"
					+ "<b style='color:#21bfc5;'>Norin</b></br>"
					+ "<b style='color:#21bfc5;'>NoStepOnSnek</b></br>"
					+ "<b style='color:#21bfc5;'>Phlarx</b></br>"
					+ "<b style='color:#21bfc5;'>Pimgd</b></br>"
					+ "<b style='color:#21bfc5;'>PoyntFury</b></br>"
					+ "<b style='color:#21bfc5;'>Rfpnj</b></br>"
					+ "<b style='color:#21bfc5;'>Tukaima</b></br>");
			
			UtilText.nodeContentSB.append("<br/>"
						+ "特别鸣谢：<br/>"
						+ "<b>Sensei</b>、<br/>"
						+ "<b style='color:#fa0063;'>loveless</b>、<b style='color:#c790b2;'>Blue999</b>和<b style='color:#ec9538;'>DesuDemona</b><br/>"
						+ "<b style='color:#21bec4;'>Github和维基的贡献者</b><br/>"
						+ "<b style='color:#e06e5f;'>所有在经济上支援过我的人</b>、<br/>"
						+ "<b>错误报告者</b>、<br/>"
						+ "以及<br/>"
						+ "<b>所有游玩莉莉丝的王座的玩家！</b>"
					+ "</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>传说级赞助者</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					if(cs.getName().equals("Anonymous")) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>?</b>");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>?</b>");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>?</b>");
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>?</b>");
					} else {
						for(int i=0; i<cs.getUncommonCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b>");
						}
						
						for(int i=0; i<cs.getRareCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>&#9679</b>");
						}
						
						for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#127775</b>");
						}
						for(int i=0; i<cs.getEpicCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#9679</b>");
						}
						
						for(int i=0; i<cs.getLegendaryCount()/5; i++) {// 5-marks:
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>&#127775</b>");
						}
						for(int i=0; i<cs.getLegendaryCount()%5; i++) {
							UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_LEGENDARY.toWebHexString()+";'>&#9679</b>");
						}
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>史诗级赞助者</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()>0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:right;'>");
					for(int i=0; i<cs.getUncommonCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_UNCOMMON.toWebHexString()+";'>&#9679</b>");
					}
					
					for(int i=0; i<cs.getRareCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_RARE.toWebHexString()+";'>&#9679</b>");
					}
					
					for(int i=0; i<cs.getEpicCount()/5; i++) {// 5-marks:
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#127775</b>");
					}
					for(int i=0; i<cs.getEpicCount()%5; i++) {
						UtilText.nodeContentSB.append("<b style='color:"+PresetColour.RARITY_EPIC.toWebHexString()+";'>&#9679</b>");
					}
					UtilText.nodeContentSB.append("</div>");
					UtilText.nodeContentSB.append("<div style='width:50%; display:inline-block; text-align:left;'>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
					UtilText.nodeContentSB.append("</div>");
				}
			}
			
			UtilText.nodeContentSB.append(
					"</p>"
					+ "<br/>"
					+ "<h5 style='text-align:center; color:"+Subspecies.DEMON.getColour(null).toWebHexString()+";'>恶魔级支持者</h5>"
					+ "<p style='text-align:center;'>");
			
			for(CreditsSlot cs : Main.credits) {
				if(cs.getLegendaryCount()==0 && cs.getEpicCount()==0) {
					UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("&nbsp;"+(cs.getSubspeciesTier()!=null?"<b style='color:"+cs.getSubspeciesTier().getColour(null).toWebHexString()+";'>"+cs.getName()+"</b>":cs.getName()));
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Back", "Go back to the options menu.", MenuOverrides.MENU);
				
			} else {
				int i=1;
				for(Artist artist : Artwork.allArtists) {
					for(ArtistWebsite website : artist.getWebsites()) {
						if(index==i) {
							return new ResponseEffectsOnly(website.getName(), "打开页面：<br/><br/><i>"+website.getURL()+"</i><br/><br/><b>将使用默认浏览器打开。</b>"){
								@Override
								public void effects() {
									Util.openLinkInDefaultBrowser(website.getURL());
								}
							};
						}
						i++;
					}
				}
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	private static Response getContentOptionsResponse(int responseTab, int index) {
		if (index == 1) {
			if (Main.game.getCurrentDialogueNode().equals(MISCELLANEOUS)) {
				return new Response("杂项", "你正在查看杂项内容选项！", null);
			}
			return new Response("杂项", "查看杂项内容选项。", MISCELLANEOUS);
		} else if (index == 2) {
			if (Main.game.getCurrentDialogueNode().equals(GAMEPLAY)) {
				return new Response("游戏玩法", "你正在查看游戏玩法内容选项！", null);
			}
			return new Response("游戏玩法", "查看游戏的游戏玩法内容选项。", GAMEPLAY);
		} else if (index == 3) {
			if (Main.game.getCurrentDialogueNode().equals(SEX)) {
				return new Response("性与性癖", "你正在查看性与性癖内容选项！", null);
			}
			return new Response("性与性癖", "查看游戏的性与性癖内容选项。", SEX);
		} else if (index == 4) {
			if (Main.game.getCurrentDialogueNode().equals(BODIES)) {
				return new Response("身体", "你正在查看身体内容选项！", null);
			}
			return new Response("身体", "查看游戏的身体内容选项。", BODIES);
		} else if (index == 5) {
			return new Response("[style.colourMinorBad(重置)]",
					"将<b>所有“杂项”、“游戏玩法”、“性与性癖”和“身体”</b>内容偏好重置为默认值！"
							+"<br/><b>不会</b>重置性别、去向、年龄、福瑞和性癖偏好。",
					MISCELLANEOUS) {
				@Override
				public void effects() {
					for (PropertyValue pv : PropertyValue.values()) {
						Main.getProperties().setValue(pv, pv.getDefaultValue());
					}
					Main.getProperties().resetContentOptions();
					Main.saveProperties();
				}
			};
		} else if (index == 6) {
			if (Main.game.getCurrentDialogueNode().equals(GENDER_PREFERENCE)) {
				return new Response("性别偏好", "你正在查看性别偏好界面！", null);
			}
			return new Response("性别偏好", "设置你偏好的遭遇性别比例。", GENDER_PREFERENCE);
		} else if (index == 7) {
			if (Main.game.getCurrentDialogueNode().equals(ORIENTATION_PREFERENCE)) {
				return new Response("取向偏好", "你正在查看性取向偏好界面！", null);
			}
			return new Response("取向偏好", "设置你偏好的遭遇性取向比例。", ORIENTATION_PREFERENCE);
		} else if (index == 8) {
			if (Main.game.getCurrentDialogueNode().equals(AGE_PREFERENCE)) {
				return new Response("年龄偏好", "你正在查看年龄偏好界面！", null);
			}
			return new Response("年龄偏好", "设置你偏好的遭遇年龄比例。", AGE_PREFERENCE);
		} else if (index == 9) {
			if (Main.game.getCurrentDialogueNode().equals(FURRY_PREFERENCE)) {
				return new Response("福瑞程度偏好", "你正在查看福瑞程度偏好界面！", null);
			}
			return new Response("福瑞程度偏好", "设置你偏好的遭遇福瑞程度。", FURRY_PREFERENCE);
		} else if (index == 10) {
			if (Main.game.getCurrentDialogueNode().equals(FETISH_PREFERENCE)) {
				return new Response("性癖偏好", "你正在查看性癖偏好界面！", null);
			}
			return new Response("性癖偏好", "设置你偏好的遭遇性癖比例。", FETISH_PREFERENCE);
		} else if (index == 0) {
			return new Response("Back", "Go back to the main menu.", MenuOverrides.MENU);
		}
		else if (index == 12 || index == 13 || index == 14) return ModOptions.getModOptionsResponses(index);
		return null;
	}
	
	public static final DialogueNode MISCELLANEOUS = new DialogueNode("内容选项(杂项)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.GENERIC_GOOD, "自动存档频率", "选择从不同地图切换时自动存档的频率。"));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='AUTOSAVE_FREQUENCY_"+i+"' class='normal-button"+(Main.getProperties().autoSaveFrequency == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().autoSaveFrequency == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.autoSaveLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			UtilText.nodeContentSB.append(ARTWORK.getDiv());
			UtilText.nodeContentSB.append(THUMBNAIL.getDiv());
			
//			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_AQUA, "Preferred Artist", "Which artist's work is used by default."));
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:0; margin:2px 0;'>"
				+ "<div class='container-half-width' style='width:calc(55% - 16px);'>"
					+ "<b style='text-align:center; color:"+PresetColour.BASE_AQUA.toWebHexString()+";'>偏好画师</b><b>：</b>"
					+ "默认使用哪位画师的作品。"
				+ "</div>"
				+ "<div class='container-half-width' style='width:calc(45% - 16px);'>");
			
			List<Artist> artists = new ArrayList<>(Artwork.allArtists);
			artists.remove(Artwork.customArtist);
			Collections.sort(artists, (e1, e2)->Main.getProperties().getArtistPriority(e2.getFolderName())-Main.getProperties().getArtistPriority(e1.getFolderName()));
			
			for(int i=0; i<artists.size(); i++) {
				Artist artist = artists.get(i);
				if (!artist.getName().equals("Custom")) {
					UtilText.nodeContentSB.append("<div style='width:100%;  margin:1px 0; border-radius:4px; background-color:"+PresetColour.BACKGROUND.toWebHexString()+";'>");
						UtilText.nodeContentSB.append("<div "+(i==0?"":"id='ARTIST_"+artist.getFolderName()+"_UP'")+" class='normal-button"+(i==0?" disabled":"")+"' style='width:10%; margin:0; text-align:center;'>");
							UtilText.nodeContentSB.append("&#8593;");
						UtilText.nodeContentSB.append("</div>");
						UtilText.nodeContentSB.append("<div "+(i==artists.size()-1?"":"id='ARTIST_"+artist.getFolderName()+"_DOWN'")
									+" class='normal-button"+(i==artists.size()-1?" disabled":"")+"' style='width:10%; margin:0; text-align:center; float:right;'>"
								+"&#8595;"
							+"</div>");
						UtilText.nodeContentSB.append("<div style='width:80%; margin:0; text-align:center; float:right;'>"
								+"<span style='color:"+artist.getColour().toWebHexString()+";'>"+artist.getName()+"</span> ("+artist.getArtworkCount()+")"
							+"</div>");
					UtilText.nodeContentSB.append("</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			UtilText.nodeContentSB.append(SHARED_ENCYCLOPEDIA.getDiv());
			UtilText.nodeContentSB.append(WEATHER_INTERRUPTION.getDiv());
			UtilText.nodeContentSB.append(DIALOGUE_COPY.getDiv());
			UtilText.nodeContentSB.append(SILLY.getDiv());


			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode GAMEPLAY = new DialogueNode("内容选项(游戏玩法)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(ENCHANTMENT_LIMITS.getDiv());

			UtilText.nodeContentSB.append(BAD_END.getDiv());
			UtilText.nodeContentSB.append(LEVEL_DRAIN.getDiv());
			UtilText.nodeContentSB.append(OPPORTUNISTIC_ATTACKERS.getDiv());
			UtilText.nodeContentSB.append(OFFSPRING_ENCOUNTERS.getDiv());
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_BLUE_LIGHT, "衣着女性化", "该选项限制了衣着的女性化程度。"));
			for (int i=Main.getProperties().clothingFemininityTitles.length-1; i>=0; i--) {
				if (Main.getProperties().getClothingFemininityLevel() == i) {
					UtilText.nodeContentSB.append("<div id='CLOTHING_FEMININITY_"+i
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+Main.getProperties().clothingFemininityColours[i].toWebHexString()+";'><b>"+Main.getProperties().clothingFemininityTitles[i]+"</b></div>");
				} else {
					UtilText.nodeContentSB.append("<div id='CLOTHING_FEMININITY_"+i
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+Main.getProperties().clothingFemininityTitles[i]+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_PINK, "性动作绕过", "启用后，行动做的堕落要求会被绕过。"));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='BYPASS_SEX_ACTIONS_"+i+"' class='normal-button"+(Main.getProperties().bypassSexActions == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().bypassSexActions == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.bypassSexActionsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");

			UtilText.nodeContentSB.append(PREGNANCY_DURATION.getDiv());

			UtilText.nodeContentSB.append(SPITTING_ENABLED.getDiv());

			UtilText.nodeContentSB.append(FORCED_TF.getDiv());
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_GREEN, "强制转化种族限制", "该选项允许你设置NPC强制转化你时最高的福瑞程度。"));
			for (FurryPreference fp : Util.newArrayListOfValues(FurryPreference.REDUCED,
					FurryPreference.MINIMUM,
					FurryPreference.HUMAN,
					FurryPreference.MAXIMUM,
					FurryPreference.NORMAL)) {
				if (Main.getProperties().getForcedTFPreference() == fp) {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_FURRY_LIMIT_"+fp
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+fp.getColour().toWebHexString()+";'>"+fp.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_FURRY_LIMIT_"+fp
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+fp.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_GREEN, "强制转化性别倾向", "该选项允许你覆盖NPC在使用强制转化改变你的性别时的喜好。"));
			for (ForcedTFTendency ftt : Util.newArrayListOfValues(ForcedTFTendency.NEUTRAL,
					ForcedTFTendency.FEMININE,
					ForcedTFTendency.FEMININE_HEAVY,
					ForcedTFTendency.MASCULINE_HEAVY,
					ForcedTFTendency.MASCULINE)) {
				if (Main.getProperties().getForcedTFTendency() == ftt) {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_TENDENCY_"+ftt
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+ftt.getColour().toWebHexString()+";'>"+ftt.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_TF_TENDENCY_"+ftt
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+ftt.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");

			UtilText.nodeContentSB.append(FORCED_FETISH.getDiv());
			
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.FETISH, "强制性癖倾向",
					"该选项允许你覆盖NPC在强制给予性癖时，对于低姿态或高姿态性癖的喜好。"));
			for (ForcedFetishTendency fft : Util.newArrayListOfValues(ForcedFetishTendency.NEUTRAL,
					ForcedFetishTendency.BOTTOM,
					ForcedFetishTendency.BOTTOM_HEAVY,
					ForcedFetishTendency.TOP_HEAVY,
					ForcedFetishTendency.TOP)) {
				if (Main.getProperties().getForcedFetishTendency() == fft) {
					UtilText.nodeContentSB.append("<div id='FORCED_FETISH_TENDENCY_"+fft
							+"' class='normal-button selected' style='width:31%; margin:1%; text-align:center; float:right; color:"
							+fft.getColour().toWebHexString()+";'>"+fft.getName()+"</div>");
				} else {
					UtilText.nodeContentSB.append("<div id='FORCED_FETISH_TENDENCY_"+fft
							+"' class='normal-button' style='width:31%; margin:1%; text-align:center; float:right;'>"
							+"[style.colourDisabled("+fft.getName()+")]</div>");
				}
			}
			UtilText.nodeContentSB.append("</div></div>");

			UtilText.nodeContentSB.append(COMPANION.getDiv());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode SEX = new DialogueNode("内容选项(性与性癖)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(NON_CON.getDiv());
			UtilText.nodeContentSB.append(SADISTIC_SEX.getDiv());
			UtilText.nodeContentSB.append(LIPSTICK_MARKING.getDiv());

			UtilText.nodeContentSB.append(VOLUNTARY_NTR.getDiv());
			UtilText.nodeContentSB.append(INVOLUNTARY_NTR.getDiv());
			UtilText.nodeContentSB.append(INCEST.getDiv());
			UtilText.nodeContentSB.append(LACTATION.getDiv());
			UtilText.nodeContentSB.append(SEXUAL_UDDERS.getDiv());
			UtilText.nodeContentSB.append(URETHRAL.getDiv());
			UtilText.nodeContentSB.append(NIPPLE_PEN.getDiv());
			UtilText.nodeContentSB.append(ANAL.getDiv());
			UtilText.nodeContentSB.append(GAPE.getDiv());
			UtilText.nodeContentSB.append(PENETRATION_LIMITATION.getDiv());
			UtilText.nodeContentSB.append(PENETRATION_LIMITATION_DYNAMIC.getDiv());
			UtilText.nodeContentSB.append(FOOT.getDiv());
			UtilText.nodeContentSB.append(ARMPIT.getDiv());
			UtilText.nodeContentSB.append(MUSK.getDiv());
			UtilText.nodeContentSB.append(FURRY_TAIL_PENETRATION.getDiv());
			UtilText.nodeContentSB.append(INFLATION_CONTENT.getDiv());
			UtilText.nodeContentSB.append(AUTO_SEX_CLOTHING_MANAGEMENT.getDiv());
			UtilText.nodeContentSB.append(AUTO_SEX_CLOTHING_STRIP.getDiv());
			UtilText.nodeContentSB.append(RAPE_PLAY_BY_DEFAULT.getDiv());
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_PINK, "暴露描述", "设置完整描述暴露出的身体部分的频率。"));
			for (int i = 2; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='FULL_EXPOSURE_DESCRIPTIONS_"+i+"' class='normal-button"+(Main.getProperties().bypassSexActions == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().fullExposureDescriptions == i
						?"[style.boldGood("
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.fullExposureDescriptionsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
	
	public static final DialogueNode BODIES = new DialogueNode("内容选项(身体)", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(AGE.getDiv());
			UtilText.nodeContentSB.append(FERAL.getDiv());
			UtilText.nodeContentSB.append(CUM_REGENERATION.getDiv());
			UtilText.nodeContentSB.append(FUTA_BALLS.getDiv());
			UtilText.nodeContentSB.append(CLOACA.getDiv());
			UtilText.nodeContentSB.append(VESTIGIAL_MULTI_BREAST.getDiv());
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.NIPPLES, "多对乳房", "决定随机生成的福瑞角色是否拥有多对乳房。"));
			int[] buttonOrder = new int[] {2, 1, 0, 3}; // Order buttons in this manner so that they appear to be a little more logical
			for (int i : buttonOrder) {
				UtilText.nodeContentSB.append("<div id='MULTI_BREAST_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().multiBreasts == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().multiBreasts == i
							?(i == 0
								?"[style.boldTerrible("
								:(i == 1
									?"[style.boldBad("
									:"[style.boldGood("))
							:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.multiBreastsLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.NIPPLES_CROTCH, "胯乳与腹乳", "决定随机生成的半兽身人和福瑞是否应该拥有腹乳或胯乳。"));
			for (int i = com.lilithsthrone.game.Properties.uddersLabels.length-1; i>=0; i--) {
				UtilText.nodeContentSB.append("<div id='UDDER_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().getUddersLevel() == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(Main.getProperties().getUddersLevel() == i
						?(i == 0?"[style.boldBad(":"[style.boldGood(")
						:"[style.colourDisabled(")
						+com.lilithsthrone.game.Properties.uddersLabels[i]+")]</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");
			
			UtilText.nodeContentSB.append(getCustomContentPreferenceDivStart(PresetColour.BASE_BROWN_LIGHT, "头发生长", "选择玩家角色的头发每生长1厘米所需要的时间。NPC的头发长度恒定，该设置只会影响你的角色。"));
			int[] hairButtonOrder = new int[] {2, 1, 0, 3}; // Order buttons in this manner so that they appear to be a little more logical
			for (int i : hairButtonOrder) {
				boolean active = Main.getProperties().getHairGrowth() == i;
				UtilText.nodeContentSB.append("<div id='HAIR_GROWTH_PREFERENCE_"+i+"' class='normal-button"+(Main.getProperties().getHairGrowth() == i?" selected":"")+"' style='width:calc(33% - 8px); margin-right:8px; text-align:center; float:right;'>"
						+(i == 0
								?"[style.bold"+(active?"Bad":"Disabled")+"(从不)]"
								:(i == 1
									?"[style.bold"+(active?"Size10":"Disabled")+"(每周)]"
									:(i == 2
										?"[style.bold"+(active?"Size5":"Disabled")+"(每天)]"
										:"[style.bold"+(active?"Size0":"Disabled")+"(每小时)]")))
						+"</div>");
			}
			UtilText.nodeContentSB.append("</div></div>");

			UtilText.nodeContentSB.append(HAIR_FACIAL.getDiv());
			UtilText.nodeContentSB.append(HAIR_PUBIC.getDiv());
			UtilText.nodeContentSB.append(HAIR_BODY.getDiv());
			UtilText.nodeContentSB.append(HAIR_ASS.getDiv());
			UtilText.nodeContentSB.append(FEMININE_BEARD.getDiv());
			UtilText.nodeContentSB.append(FURRY_HAIR.getDiv());
			UtilText.nodeContentSB.append(SCALY_HAIR.getDiv());
			UtilText.nodeContentSB.append(LIP_LISP.getDiv());


			UtilText.nodeContentSB.append(PREGNANCY_BREAST_GROWTH.getDiv());
			UtilText.nodeContentSB.append(PREGNANCY_BREAST_GROWTH_LIMIT.getDiv());

			UtilText.nodeContentSB.append(PREGNANCY_LACTATION.getDiv());
			UtilText.nodeContentSB.append(PREGNANCY_LACTATION_LIMIT.getDiv());
			UtilText.nodeContentSB.append(BREAST_SIZE_PREFERENCE.getDiv());

			UtilText.nodeContentSB.append(PENIS_SIZE_PREFERENCE.getDiv());
			UtilText.nodeContentSB.append(TRAP_PENIS_SIZE_PREFERENCE.getDiv());
			UtilText.nodeContentSB.append(getSkinColourContentPreferenceVariableDiv(
					"SKIN_COLOUR_PREFERENCE",
					PresetColour.RACE_HUMAN,
					"皮肤颜色偏好",
					"影响随机生成的人类皮肤颜色权重。"
							+"该选项不会影响达到“纯粹”福瑞的NPC，他们并没有类似人类的皮肤覆盖。"));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getContentOptionsResponse(responseTab, index);
		}
		
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OPTIONS;
		}
	};
}
