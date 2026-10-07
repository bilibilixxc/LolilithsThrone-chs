package com.lilithsthrone.controller;

import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInventoryEventListener;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.places.dominion.cityHall.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaDressingRoomDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.EnchantmentDialogue;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.LoadedEnchantment;
import com.lilithsthrone.game.inventory.outfit.Outfit;
import com.lilithsthrone.game.inventory.outfit.OutfitSource;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import javafx.stage.FileChooser;
import org.w3c.dom.events.EventTarget;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class FileController {
	
	private static File lastOpened = null;
	
	public static void initArtworkListeners() {
		GameCharacter character = Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)
				?Main.game.getPlayer()
				:(Main.game.getCurrentDialogueNode().equals(CompanionManagement.SLAVE_MANAGEMENT_INSPECT)
					?Main.game.getDialogueFlags().getManagementCompanion()
					:CharactersPresentDialogue.characterViewed);
		
		String id = "ARTWORK_ADD";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				// Create file chooser for .jpg and .png images in the most recently used directory
				FileChooser chooser = new FileChooser();
				chooser.setTitle("Add Images");
				chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
				if (lastOpened != null) {
					chooser.setInitialDirectory(lastOpened);
				}
				
				List<File> files = chooser.showOpenMultipleDialog(Main.primaryStage);
				if (files != null && !files.isEmpty()) {
					lastOpened = files.get(0).getParentFile();
					
					character.importImages(files);
					
					if (!character.isPlayer()) {
						CharactersPresentDialogue.resetContent(character);
					}
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"添加自定义图像",
					"浏览你拥有的图像，并添加给该角色。"
							+"请注意GIF动图<b>最大限制在10MB以内</b>，一旦大于1MB，则根据系统不同，<b>可能</b>造成[style.italicsBad(严重卡顿)]。"
							+ "<br/>当前游戏的自定义图像位于文件夹:<b>'data/images/"+Main.game.getId()+"'</b>内"
							+ "<br/>该角色的ID为<b>'"+character.getId()+"'</b>",
					130));
		}
		
		if (character.hasArtwork()) {
			try {
				Artwork artwork = character.getCurrentArtwork();
				
				id = "ARTWORK_INFO";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (!artwork.getArtist().getWebsites().isEmpty()) {
							Util.openLinkInDefaultBrowser(artwork.getArtist().getWebsites().get(0).getURL());
						}
					}, false);
					
					String description;
					if (artwork.getArtist().getName().equals("Custom")) {
						description = "由你自行添加。";
					} else if (artwork.getArtist().getWebsites().isEmpty()) {
						description = "该画师没有提供相关网站！";
					} else {
						description = "点击后打开<b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getWebsites().get(0).getName()+"</b>"
								+"("+artwork.getArtist().getWebsites().get(0).getURL()+")通过你的默认<b>外部</b>浏览器！";
					}
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getName()+"</b>创作的图像",
							description));
				}
				
				id = "ARTWORK_PREVIOUS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (artwork.getTotalArtworkCount()>1) {
							artwork.incrementIndex(-1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_NEXT";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (artwork.getTotalArtworkCount()>1) {
							artwork.incrementIndex(1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_ARTIST_PREVIOUS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (character.getArtworkList().size()>1) {
							character.incrementArtworkIndex(-1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_ARTIST_NEXT";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (character.getArtworkList().size()>1) {
							character.incrementArtworkIndex(1);
							if (!character.isPlayer())
								CharactersPresentDialogue.resetContent(character);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}
					}, false);
				}
				
				id = "ARTWORK_DELETE";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						artwork.getCurrentImage().delete();
						character.updateImages();
						if (!character.isPlayer())
							CharactersPresentDialogue.resetContent(character);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"移除自定义图像",
							"移除该角色当前的图像。"
									+"<br/>[style.italicsBad(请注意这将从游戏文件夹中删除该图像！)]"));
				}
			} catch (Exception ex) {
				System.err.println("MainController Artwork handling error.");
			}
		}
	}
	
	public static void initSaveLoadListeners() {
		String id;
		for (File f : Main.getSavedGames(false)) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
						OptionsDialogue.overwriteConfirmationName = "";
						Main.saveGame(fileName, true, false);
					} else {
						OptionsDialogue.overwriteConfirmationName = f.getName();
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载游戏窗口", OptionsDialogue.SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("覆盖", ""));
			} else {
				id = "OVERWRITE_"+fileIdentifier+"_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("覆盖(禁用)",
							(!Main.game.isStarted()
									?"你需要先开始游戏才能覆盖存档！"
									:"除非位于某地块的默认场景，否则无法覆盖存档文件！"));
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
						OptionsDialogue.loadConfirmationName = "";
						Main.loadGame(fileName);
					} else {
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.loadConfirmationName = f.getName();
						OptionsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载游戏窗口", OptionsDialogue.SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("加载", ""));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
						OptionsDialogue.deleteConfirmationName = "";
						Main.deleteGame(fileName);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					} else {
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("保存/加载", "打开保存/加载游戏窗口", OptionsDialogue.SAVE_LOAD));
					}
					
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("删除", ""));
			}
		}
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, false);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("保存", ""));
		} else {
			id = "NEW_SAVE_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("保存(禁用)",
						(!Main.game.isStarted()
								?"你需要先开始游戏才能存档！"
								:"除非位于某地块的默认场景，否则无法存档！")));
			}
		}
	}
	
	public static void initImportExportListeners() {
		for (File f : Main.getCharactersForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			String id = "DELETE_CHARACTER_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
						OptionsDialogue.deleteConfirmationName = "";
						Main.deleteExportedCharacter(fileName);
					} else {
						OptionsDialogue.overwriteConfirmationName = "";
						OptionsDialogue.loadConfirmationName = "";
						OptionsDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("导入/导出", "打开导入/导出窗口。", OptionsDialogue.IMPORT_EXPORT));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("删除", ""));
			}
		}
		if (MainController.document.getElementById("NEW_SAVE") != null) {
			((EventTarget) MainController.document.getElementById("NEW_SAVE")).addEventListener("click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				Main.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, false);
			}, false);
		}
	}
	
	public static void initPlayerImportListeners() {
		for (File f : Main.getCharactersForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			if (MainController.document.getElementById("IMPORT_CHARACTER_"+fileIdentifier) != null) {
				((EventTarget) MainController.document.getElementById("IMPORT_CHARACTER_"+fileIdentifier)).addEventListener("click", e->{
					Main.importCharacter(f);
				}, false);
			}
		}
	}
	
	public static void initSlaveImportListeners() {
		for (File f : Main.getSlavesForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			if (MainController.document.getElementById("IMPORT_SLAVE_"+fileIdentifier) != null) {
				((EventTarget) MainController.document.getElementById("IMPORT_SLAVE_"+fileIdentifier)).addEventListener("click", e->{
					try {
						Game.importCharacterAsSlave(fileName);
						MainController.updateUI();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "角色已导入！");
					} catch (Exception ex) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "导入失败！");
					}
				}, false);
			}
		}
	}
	
	public static void initAuctionListeners() {
		for (NPC npc : Main.game.getCharactersPresent()) {
			String id = npc.getId()+"_BID";
			if (MainController.document.getElementById(id) != null) {
				if (Main.game.getPlayer().isHasSlaverLicense()) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						SlaverAlleyDialogue.setupBidding(npc);
						Main.game.setContent(new Response("", "", SlaverAlleyDialogue.AUCTION_BIDDING));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							UtilText.parse(npc, "给[npc.name]竞价"),
							UtilText.parse(npc, "开始给[npc.name]竞价。有可能竞价会超过[npc.her]的价值，所以请先准备好足够的钱！")));
				} else {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							UtilText.parse(npc, "给[npc.name]竞价"),
							UtilText.parse(npc, "你没有贩奴许可，所以无法为任何奴隶竞价！")));
				}
			}
		}
	}
	
	public static void initLodgerImportListeners() {
		for (File f : Main.getSlavesForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			if (MainController.document.getElementById("IMPORT_LODGER_"+fileIdentifier) != null) {
				((EventTarget) MainController.document.getElementById("IMPORT_LODGER_"+fileIdentifier)).addEventListener("click", e->{
					try {
						Game.importCharacterAsLodger(fileName);
						MainController.updateUI();
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "角色已导入！");
					} catch (Exception ex) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "导入失败！");
					}
				}, false);
			}
		}
	}
	
	public static void initLodgerWaitingListeners() {
		for (NPC npc : Main.game.getCharactersPresent()) {
			String id = npc.getId()+"_LODGER";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					CityHall.setupLodger(npc);
					Main.game.setContent(new Response("", "", CityHall.CITY_HALL_APPROACH_LODGER));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						UtilText.parse(npc, "寻找[npc.name]"),
						UtilText.parse(npc, "环顾四周，看看能否在等待区找到[npc.name]……")));
			}
		}
	}
	
	public static void initClubberImportListeners() {
		for (File f : Main.getSlavesForImport()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			if (MainController.document.getElementById("IMPORT_CLUBBER_"+fileIdentifier) != null) {
				((EventTarget) MainController.document.getElementById("IMPORT_CLUBBER_"+fileIdentifier)).addEventListener("click", e->{
					try {
						Game.importCharacterAsClubber(fileName);
						MainController.updateUI();
						Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue()));
						Main.game.flashMessage(PresetColour.GENERIC_GOOD, "角色已导入！");
					} catch (Exception ex) {
						Main.game.flashMessage(PresetColour.GENERIC_BAD, "导入失败！");
					}
				}, false);
			}
		}
	}
	
	public static void initEnchantmentSaveLoadListeners() {
		String id;
		for (File f : EnchantmentDialogue.getSavedEnchants()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;

			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.overwriteConfirmationName.equals(f.getName())) {
						EnchantmentDialogue.overwriteConfirmationName = "";
						EnchantmentDialogue.saveEnchant(fileName, true, EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD);
					} else {
						EnchantmentDialogue.overwriteConfirmationName = f.getName();
						EnchantmentDialogue.loadConfirmationName = "";
						EnchantmentDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载附魔窗口。", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("覆盖", ""));
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.loadConfirmationName.equals(f.getName())) {
						EnchantmentDialogue.loadConfirmationName = "";
						LoadedEnchantment lEnch = EnchantmentDialogue.loadEnchant(fileName);
						
						EnchantmentDialogue.resetNonTattooEnchantmentVariables();
						AbstractCoreItem abstractItem = lEnch.getSuitableItem();
						EnchantmentDialogue.initModifiers(abstractItem);
						EnchantmentDialogue.getEffects().clear();
						for (ItemEffect ie : lEnch.getEffects()) {
							EnchantmentDialogue.addEffect(ie);
						}
						EnchantmentDialogue.setOutputName(lEnch.getName());
						Main.game.setContent(new Response("保存/加载", "打开保存/加载附魔窗口。", EnchantmentDialogue.ENCHANTMENT_MENU));
					} else {
						EnchantmentDialogue.overwriteConfirmationName = "";
						EnchantmentDialogue.loadConfirmationName = f.getName();
						EnchantmentDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载附魔窗口。", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("加载", ""));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || EnchantmentDialogue.deleteConfirmationName.equals(f.getName())) {
						EnchantmentDialogue.deleteConfirmationName = "";
						EnchantmentDialogue.deleteEnchant(fileName);
						EnchantmentDialogue.initSaveLoadMenu();
						Main.game.setContent(new Response("保存/加载", "", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					} else {
						EnchantmentDialogue.overwriteConfirmationName = "";
						EnchantmentDialogue.loadConfirmationName = "";
						EnchantmentDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("保存/加载", "", EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("删除", ""));
			}
		}
		
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				EnchantmentDialogue.saveEnchant(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, EnchantmentDialogue.ENCHANTMENT_SAVE_LOAD);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("保存", ""));
		}
		for (Map.Entry<String, LoadedEnchantment> entry : EnchantmentDialogue.getLoadedEnchantmentsMap().entrySet()) {
			id = "LOADED_ENCHANTMENT_"+entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLoadedEnchantment(entry.getValue()));
			}
		}
		id = "LOADED_ENCHANTMENT_CURRENT";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLoadedEnchantment(EnchantmentDialogue.getCurrentEnchantmentAsLoadedEnchantment()));
		}
	}
	
	public static void initTattooSaveLoadListeners() {
		String id;
		for (File f : CosmeticsDialogue.getSavedTattoos()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || CosmeticsDialogue.overwriteConfirmationName.equals(f.getName())) {
						CosmeticsDialogue.overwriteConfirmationName = "";
						CosmeticsDialogue.saveTattoo(fileName, true, CosmeticsDialogue.TATTOO_SAVE_LOAD);
					} else {
						CosmeticsDialogue.overwriteConfirmationName = f.getName();
						CosmeticsDialogue.loadConfirmationName = "";
						CosmeticsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载纹身窗口。", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("覆盖", ""));
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || CosmeticsDialogue.loadConfirmationName.equals(f.getName())) {
						CosmeticsDialogue.loadConfirmationName = "";
						Tattoo loadedTattoo = CosmeticsDialogue.loadTattoo(fileName);
						CharacterModificationUtils.tattoo = loadedTattoo;
						
						Main.game.setContent(new Response("保存/加载", "打开保存/加载纹身窗口。", CosmeticsDialogue.getReturnToNodeFromTattooSaveLoad()));
					} else {
						CosmeticsDialogue.overwriteConfirmationName = "";
						CosmeticsDialogue.loadConfirmationName = f.getName();
						CosmeticsDialogue.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载纹身窗口。", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("加载", ""));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || CosmeticsDialogue.deleteConfirmationName.equals(f.getName())) {
						CosmeticsDialogue.deleteConfirmationName = "";
						CosmeticsDialogue.deleteTattoo(fileName);
						CosmeticsDialogue.initSaveLoadMenu();
						Main.game.setContent(new Response("保存/加载", ".", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					} else {
						CosmeticsDialogue.overwriteConfirmationName = "";
						CosmeticsDialogue.loadConfirmationName = "";
						CosmeticsDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("保存/加载", ".", CosmeticsDialogue.TATTOO_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("删除", ""));
			}
		}
		
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				CosmeticsDialogue.saveTattoo(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false, CosmeticsDialogue.TATTOO_SAVE_LOAD);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("保存", ""));
		}
		for (Entry<String, Tattoo> entry : CosmeticsDialogue.getLoadedTattoosMap().entrySet()) {
			id = "LOADED_TATTOO_"+entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setTattoo(CharacterModificationUtils.tattooInventorySlot, entry.getValue(), BodyChanging.getTarget(), BodyChanging.getTarget()));
			}
		}
		id = "LOADED_TATTOO_CURRENT";
		if (MainController.document.getElementById(id) != null) {
			MainController.addTooltipListeners(id, new TooltipInventoryEventListener().setTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo, BodyChanging.getTarget(), BodyChanging.getTarget()));
		}
	}
	
	public static void initBodySaveLoadListeners() {
		String id;
		for (File f : BodyChanging.getSavedBodies()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			id = "OVERWRITE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.overwriteConfirmationName.equals(f.getName())) {
						BodyChanging.overwriteConfirmationName = "";
						BodyChanging.saveBody(fileName, true);
					} else {
						BodyChanging.overwriteConfirmationName = f.getName();
						BodyChanging.loadConfirmationName = "";
						BodyChanging.deleteConfirmationName = "";
						Main.game.setContent(new Response("保存/加载", "打开保存/加载转化窗口。", BodyChanging.BODY_CHANGING_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("覆盖", ""));
			}
			id = "LOAD_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Body loadedBody = BodyChanging.loadBody(fileName);
					if(BodyChanging.isPresetTransformationAvailable(loadedBody)) {
						if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.loadConfirmationName.equals(f.getName())) {
							BodyChanging.loadConfirmationName = "";
							BodyChanging.applyLoadedBody(loadedBody);
							Main.game.setContent(new Response("保存/加载", "打开保存/加载转化窗口。", BodyChanging.BODY_CHANGING_CORE));
						} else {
							BodyChanging.overwriteConfirmationName = "";
							BodyChanging.loadConfirmationName = f.getName();
							BodyChanging.deleteConfirmationName = "";
							Main.game.setContent(new Response("保存/加载", "打开保存/加载转化窗口。", BodyChanging.BODY_CHANGING_SAVE_LOAD));
						}
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("读取",
						BodyChanging.isPresetTransformationAvailable(BodyChanging.loadBody(fileName))
								?""
								:BodyChanging.getPresetTransformationUnavailabilityText(BodyChanging.loadBody(fileName))));
			}
			id = "DELETE_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || BodyChanging.deleteConfirmationName.equals(f.getName())) {
						BodyChanging.deleteConfirmationName = "";
						BodyChanging.deleteBody(fileName);
						BodyChanging.initSaveLoadMenu();
						Main.game.setContent(new Response("保存/加载", "", BodyChanging.BODY_CHANGING_SAVE_LOAD));
					} else {
						BodyChanging.overwriteConfirmationName = "";
						BodyChanging.loadConfirmationName = "";
						BodyChanging.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("保存/加载", "", BodyChanging.BODY_CHANGING_SAVE_LOAD));
					}
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("删除", ""));
			}
		}
		id = "NEW_SAVE";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
				BodyChanging.saveBody(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("保存", ""));
		}
		for (Map.Entry<String, Util.Value<String, Body>> entry : BodyChanging.getPresetTransformationsMap().entrySet()) {
			id = "LOADED_BODY_"+entry.getKey();
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setLoadedBody(entry.getValue().getValue(), BodyChanging.getTarget()));
			}
		}
	}
	
	public static void initOutfitListeners() {
		String id;
		for (File f : LilayaDressingRoomDialogue.getSavedOutfits()) {
			String fileIdentifier = Util.getFileIdentifier(f);
			String fileName = Util.getFileName(f);
			
			
			id = "LOADED_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					String name = Util.getFileIdentifier(f);
					Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
					LilayaDressingRoomDialogue.setActiveOutfit(loadedOutfit);
					Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
					
				}, false);
				String name = Util.getFileIdentifier(f);
				Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
				int availabeOutfits = LilayaDressingRoomDialogue.getOutfitAvailabilityFromTile(loadedOutfit);
				int essenceCost = loadedOutfit.getEssenceCost();
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						Util.capitaliseSentence(loadedOutfit.getName()),
						"该套装中的衣物总数："+loadedOutfit.getClothing().size()
							+ "<br/>该套装中的武器总数："+loadedOutfit.getWeapons().size()
							+ "<br/>存储在区域中的可用物品数："+availabeOutfits//Math.max(0, availabeOutfits)
							+ "<br/>购买完整套装的费用："
								+UtilText.formatAsMoney(loadedOutfit.getCost(), "b")
								+"，"
								+(essenceCost==0
									?UtilText.formatAsEssencesUncoloured(essenceCost, "b", false)
									:UtilText.formatAsEssences(essenceCost, "b", false))
							+"</div>",
						72));
			}
			
			id = "WEAR_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					
					String name = Util.getFileIdentifier(f);
					Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
					Main.game.getPlayer().loadOutfit(loadedOutfit, OutfitSource.CELL, OutfitSource.NOWHERE);
					Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFITS));
					
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"装备",
						"你的角色将会装备此套装所定义的所有衣物和武器。"
							+ "任何你当前装备的衣物和武器，如果在应用此套装时被卸下，将被放入你的更衣室。"));
			}
			
			id = "EDIT_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					
					String name = Util.getFileIdentifier(f);
					Outfit loadedOutfit = LilayaDressingRoomDialogue.loadOutfit(name);
					LilayaDressingRoomDialogue.setActiveOutfit(loadedOutfit);
					Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR));
					
				}, false);
//				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("View", ""));
			}
			
			id = "DELETE_OUTFIT_"+fileIdentifier;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || LilayaDressingRoomDialogue.deleteConfirmationName.equals(f.getName())) {
						LilayaDressingRoomDialogue.deleteConfirmationName = "";
						LilayaDressingRoomDialogue.deleteOutfit(fileName);
						Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFITS));
						
					} else {
						LilayaDressingRoomDialogue.deleteConfirmationName = f.getName();
						Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFITS));
					}
				}, false);
//				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("Delete", ""));
			}
		}
	}
}
