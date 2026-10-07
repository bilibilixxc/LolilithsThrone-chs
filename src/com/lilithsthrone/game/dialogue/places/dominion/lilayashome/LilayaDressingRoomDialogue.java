package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.Sticker;
import com.lilithsthrone.game.inventory.clothing.StickerCategory;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.outfit.Outfit;
import com.lilithsthrone.game.inventory.outfit.OutfitSource;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.4.10.8
 * @version 0.4.10.9
 * @author Innoxia
 */
public class LilayaDressingRoomDialogue {
	
	public static String deleteConfirmationName = "";
	private static Map<String, Outfit> loadedOutfitsMap;
	private static Map<Outfit, Integer> loadedOutfitsAvailabilityFromTile;
	private static Outfit activeOutfit;
	private static String loadedFileName;
	private static boolean outfitFilesFullVisibility = false;
	private static boolean outfitObtainedViaPurchase = false;
	
	public static boolean newlyCreatedWeapon = false;

	private static String dollID;
	private static void initDressupDoll() {
		BasicDoll doll = new BasicDoll();
		try {
			dollID = Main.game.addNPC(doll, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doll.setBody(Gender.F_P_V_B_FUTANARI, Subspecies.HUMAN, RaceStage.GREATER, true);
		doll.setBodyMaterial(BodyMaterial.SILICONE);
		doll.setTailType(TailType.DEMON_COMMON);
		doll.setWingType(WingType.DEMON_COMMON);
		doll.setHornType(HornType.STRAIGHT);
		doll.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
		doll.setArmRows(3);
		
		doll.setPiercedEar(true);
		doll.setPiercedLip(true);
		doll.setPiercedNavel(true);
		doll.setPiercedNipples(true);
		doll.setPiercedNipplesCrotch(true);
		doll.setPiercedNose(true);
		doll.setPiercedPenis(true);
		doll.setPiercedTongue(true);
		doll.setPiercedVagina(true);
		
		doll.setName("装扮玩偶");
//		doll.setLocation(Main.game.getPlayer());
		Main.game.setActiveNPC(doll);
	}
	
	/**
	 * @return A GameCharacter which is used for utility methods, primarily for detecting whether equipped outfit clothing is incompatible with other clothing.
	 */
	public static GameCharacter getDoll() {
		if(dollID==null || !Main.game.isCharacterExisting(dollID)) {
			initDressupDoll();
		}
		try {
			if (dollID == null || dollID.isBlank()) return null;
			return Main.game.getNPCById(dollID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static final DialogueNode ROOM_DRESSING_ROOM = new DialogueNode("更衣室", "", false) {
		@Override
		public int getSecondsPassed() {
			return 10;
		}
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(LilayaHomeGeneric.getBaseRoomDescription());
			
			sb.append("<p>"
						+ "<b style='color:"+PlaceUpgrade.LILAYA_DRESSING_ROOM.getColour().toWebHexString()+";'>"+PlaceUpgrade.LILAYA_DRESSING_ROOM.getName()+"</b><br/>"
						+ PlaceUpgrade.LILAYA_DRESSING_ROOM.getRoomDescription(Main.game.getPlayerCell())
					+ "</p>");
			
			for(AbstractPlaceUpgrade up : Main.game.getPlayerCell().getPlace().getPlaceUpgrades()) {
				if(!up.isCoreRoomUpgrade()) {
					sb.append("<p>"
								+ "<b style='color:"+up.getColour().toWebHexString()+";'>"+up.getName()+"</b><br/>"
								+ up.getRoomDescription(Main.game.getPlayerCell())
							+ "</p>");
				}
			}
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<NPC> slavesAssignedToRoom = new ArrayList<>();
			slavesAssignedToRoom.addAll(charactersPresent);
			
			if(index==0) {
				return null;
				
			} else if(index == 1) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("管理房间", "进入该房间的管理界面。", OccupantManagementDialogue.ROOM_UPGRADES) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
						}
					};
				} else {
					return new Response("管理房间", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if(index == 2) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("人员管理", "进入奴隶和友人住客的管理界面。", OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("管理人员", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_DRESSING_ROOM_LYSSIETH_WARDROBE)) {
					return new Response("服装",
							"得益于莉西丝的衣柜的能力，你现在可以为自己和奴隶创建或编辑套装。",
							OUTFITS);
				} else {
					return new Response("服装",
							"你需要给莉莱雅付钱来重新激活莉西丝的衣柜，之后才能使用更衣室的套装管理功能……",
							null);
				}
				
			} else if(index==5) {
				boolean autoCleaning = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.dressingRoomAutoClean);
				return new Response("自动清洗"+(autoCleaning?"[style.colourGood(开启)]":"[style.colourBad(关闭)]"),
						"确认你在更衣室中存放的衣物是否会被自动清洗。"
							+ "<br/><b>注意：</b>在检查该区域的衣物以组成完整的套装时，脏衣物[style.italicsBad(将不会)]被计算在内。"
							+ "所以如果你想使用“从地块装备”的功能，那么建议保持这个设置开启。",
						ROOM_DRESSING_ROOM) {
					@Override
					public int getSecondsPassed() {
						return 0;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dressingRoomAutoClean, !autoCleaning);
					}
				};
			}
			
			// Slaves cannot be assigned here, but commented out in case added later:
			
//			int indexPresentStart = 4;
//			if(index-indexPresentStart<slavesAssignedToRoom.size()) {
//				NPC character = slavesAssignedToRoom.get(index-indexPresentStart);
//				if(charactersPresent.contains(character) || (character.getHomeCell().equals(Main.game.getPlayerCell()) && Main.game.getPlayer().getCompanions().contains(character))) {
//					return LilayaHomeGeneric.interactWithNPC(character);
//				} else {
//					return new Response(UtilText.parse(character, "[npc.Name]"), 
//							UtilText.parse(character, "Although this is [npc.namePos] room, [npc.sheIs] "
//									+(character.getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_SLAVE_LOUNGE)
//											?"relaxing in a slave lounge at the moment."
//											:"out at work at the moment.")), null);
//				}
//			}
			
			return null;
		}
	};
	

	public static void initOutfitsMenu() {
		if(Main.game.getCurrentDialogueNode()!=OUTFITS) {
			deleteConfirmationName = "";
		}
		loadedOutfitsMap = new TreeMap<>();
		
		for(File f : getSavedOutfits()) {
			try {
				String name = Util.getFileIdentifier(f);
				Outfit loadedOutfit = loadOutfit(name);
				if(loadedOutfit.getGameCreationID()==Main.game.getId() || outfitFilesFullVisibility) {
					loadedOutfitsMap.put(name, loadedOutfit);
				}
			} catch(Exception ex) {
			}
		}
		
		calculateOutfitAvailability();
	}
	
	public static final DialogueNode OUTFITS = new DialogueNode("套装管理", "", true) {
		@Override
		public void applyPreParsingEffects() {
			initOutfitsMenu();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
//			saveLoadSB.append(
//					"<p style='text-align:center;'>"
//						+ "<i>"
//							+ "From here you can create a new outfit or edit or delete an existing one."
//						+ "</i>"
//					+ "</p>");
					
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:70%; margin:0; text-align:center; background:transparent;'>"
							+ "套装名称与文件地址"
						+ "</div>"
						+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
							+ "可用物品数"
						+ "</div>"
						+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
							+ "成本"
						+ "</div>"
						+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
							+ "删除"
						+ "</div>"
					+ "</div>");

			if(loadedOutfitsMap.isEmpty()) {
				saveLoadSB.append(
						"<div class='container-full-width' style='padding:0; margin:0;'>"
								+ "<div class='container-full-width' style='width:calc(100% - 16px); text-align:center; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>"
									+ "<i>你还没有保存任何套装！</i>"
								+ "</div>"
						+ "</div>");
				
			} else {
				int i=0;
				for(Entry<String, Outfit> entry : loadedOutfitsMap.entrySet()){
					saveLoadSB.append(getOutfitListRow(entry.getKey(), entry.getValue(), i%2==0));
					i++;
				}
			}
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("新建套装", "创建一套新套装", OUTFIT_EDITOR) {
					@Override
					public void effects() {
						activeOutfit = new Outfit();
						loadedFileName = "";
					}
				};
				
			} else if(index==2) {
				return new Response("新建(来自当前装备)",
						"创建一套新套装，以你当前装备的衣物和武器生成初始模板。"
							+ "<br/>[style.italicsMinorBad(请注意，你装备的所有独特或传奇物品都将被忽略，因为套装无法复制如此强大的物品。)]",
						OUTFIT_EDITOR) {
					@Override
					public void effects() {
						activeOutfit = new Outfit();
						loadedFileName = "";
						
						for(InventorySlot slot : InventorySlot.allWeaponSlots) {
							AbstractWeapon weapon = Main.game.getPlayer().getWeaponInSlot(slot);
							if(weapon!=null && weapon.getRarity()!=Rarity.QUEST && weapon.getRarity()!=Rarity.LEGENDARY) {
								activeOutfit.addWeapon(slot, Main.game.getItemGen().generateWeapon(weapon));
							}
						}
						for(InventorySlot slot : InventorySlot.getClothingSlots()) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingInSlot(slot);
							if(clothing!=null && clothing.getRarity()!=Rarity.QUEST && clothing.getRarity()!=Rarity.LEGENDARY) {
								AbstractClothing clothingClone = Main.game.getItemGen().generateClothing(clothing);
								clothingClone.setDirty(null, false);
//								clothingClone.setUnlocked(true);
								activeOutfit.addClothing(slot, clothingClone);
							}
						}
					}
				};
				
				
			} else if(index == 11) {
				return new Response("确认状态：",
						"在点击删除套装存档时会进入确认状态。"
							+ "启用时，需要进行两次点击才能使删除生效。"
							+ "关闭时只需要一次点击。",
							OUTFITS) {
					@Override
					public String getTitle() {
						return "再次确认："+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>开启</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>关闭</span>");
					}
					
					@Override
					public void effects() {
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if(index==12) {
				if(outfitFilesFullVisibility) {
					return new Response("显示：[style.colourExcellent(全部)]",
							"你正在查看“res/outfits”文件夹中的[style.colourExcellent(所有)]服装。"
							+ "在不同存档文件中创建的服装会标有「[style.boldExcellent(*)]」。"
							+ "<br/><i>激活此选项可切换到“本地”模式，该模式仅显示当前游存档创建的服装。</i>",
							OUTFITS) {
						@Override
						public void effects() {
							outfitFilesFullVisibility = !outfitFilesFullVisibility;
						}
					};
					
				} else {
					return new Response("显示：[style.colourMinorGood(本地)]",
							"你当前仅查看在当前游戏中创建的服装。"
							+ "<br/><i>激活此选项可切换到“全部”模式，该模式将显示“res/outfits”文件夹中的所有服装。</i>",
							OUTFITS) {
						@Override
						public void effects() {
							outfitFilesFullVisibility = !outfitFilesFullVisibility;
						}
					};
				}
				
			} else if(index == 0) {
				return new Response("返回", "退出服装管理菜单。", ROOM_DRESSING_ROOM);
			}
			return null;
		}
	};
	
	public static List<File> getSavedOutfits() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/outfits");
		if(dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if(directoryListing!=null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getOutfitListRow(String baseName, Outfit loadedOutfit, boolean altColour) {
		String fileName = (baseName+".xml");
		
		int availabilityCount = getOutfitAvailabilityFromTile(loadedOutfit);
		int essenceCost = loadedOutfit.getEssenceCost();
		
		return "<div class='container-full-width"+(altColour?" light":"")+"' style='padding:0; margin:0 0 4px 0; position:relative;'>"
					
					+ "<div class='container-full-width"+(altColour?" light":"")+" hover-enabled' id='LOADED_OUTFIT_" + baseName + "' style='width:90%; margin:0;'>"
					
						+ "<div class='container-full-width' style='width:calc(10% - 8px); margin:0 0 0 8px; padding:0; background:transparent; position:relative; float:left;'>"
							+"<div class='inventoryImage' style='width:100%;'>"
								+ "<div class='inventoryImage-content'>"
									+ loadedOutfit.getIconSVG()
								+ "</div>"
								+ "<div class='overlay no-highlight' style='cursor:pointer;'></div>"
							+ "</div>"
						+ "</div>"
					
						+ "<div style='width:calc(67.7% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
							+ "<h6 style='margin:0; padding:2px;'>"
								+ (loadedOutfit.getGameCreationID()==Main.game.getId()?"":"[style.boldExcellent(*)]")
								+loadedOutfit.getName()
							+"</h6>"
							+ "<p style='margin:0; padding:2px;'>[style.colourDisabled(data/outfits/)]"+baseName+"[style.colourDisabled(.xml)]</p>"
						+"</div>"
						
						+ "<div style='width:calc(11.1% - 8px); padding:0; margin:0 0 0 8px; position:relative; text-align:center; float:left;'>"
							+ "<p style='margin:0; padding:2px;'>"+availabilityCount+"</p>"
						+"</div>"
						+ "<div style='width:calc(11.1% - 8px); padding:0; margin:0 0 0 8px; position:relative; text-align:center; float:left;'>"
							+ "<p style='margin:0; padding:2px;'>[style.moneyFormat("+loadedOutfit.getCost()+", span)]</p>"
							+ (essenceCost==0
								?UtilText.formatAsEssencesUncoloured(essenceCost, "b", false)
								:UtilText.formatAsEssences(essenceCost, "b", false))
						+"</div>"
					+ "</div>"
					+ "<div class='container-full-width' style='width:10%; margin:0; text-align:center; background:transparent;'>"
						+ (fileName.equals(deleteConfirmationName)
							?"<div class='square-button saveIcon' style='width:75%; margin:12.5%;' id='DELETE_OUTFIT_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
							:"<div class='square-button saveIcon' style='width:75%; margin:12.5%;' id='DELETE_OUTFIT_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
					+ "</div>"
				+ "</div>";
	}

	public static void saveOutfit(String name, Outfit outfit, boolean allowOverwrite, DialogueNode dialogueNode) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/outfits");
		dir.mkdir();

		if(dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if(directoryListing!=null) {
				for (File child : directoryListing) {
					if(child.getName().equals(name+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "名称已存在！");
							return;
						}
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();

			Element element = doc.createElement("exportedOutfit");
			doc.appendChild(element);
			
			outfit.saveAsXML(element, doc);
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/outfits/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		if(dialogueNode!=null) {
			Main.game.setContent(new Response("", "", dialogueNode));
		}
		Main.game.flashMessage(PresetColour.GENERIC_GOOD, "服装已保存！");
	}

	public static Outfit loadOutfit(String name) {
		if(isLoadOutfitAvailable(name)) {
			File file = new File("data/outfits/"+name+".xml");

			if(file.exists()) {
				try {
					Document doc = DocBuilders.parseDoc(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					
					Element outerOutfitElement = (Element) doc.getElementsByTagName("exportedOutfit").item(0);
					Element outfitElement = (Element) outerOutfitElement.getElementsByTagName("outfit").item(0);
					
					loadedFileName = name;
					
					return Outfit.loadFromXML(outfitElement, doc);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadOutfitAvailable(String name) {
		File file = new File("data/outfits/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static String getOutfitSaveName() {
		return Main.checkFileName(activeOutfit.getName()).toLowerCase();
	}

	public static boolean isSaveOutfitAvailable(boolean allowOverwrite) {
		return isSaveOutfitAvailable(getOutfitSaveName(), allowOverwrite);
	}
	
	public static boolean isSaveOutfitAvailable(String saveName, boolean allowOverwrite) {
		if(saveName.isEmpty()) {
			return false;
		}

		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/outfits");
		dir.mkdir();

		if(dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if(directoryListing!=null) {
				for (File child : directoryListing) {
					if(child.getName().equals(saveName+".xml")){
						if(!allowOverwrite) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	public static void deleteOutfit(String name) {
		File file = new File("data/outfits/"+name+".xml");

		if(file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "未找到文件……");
		}
	}

	public static Outfit getActiveOutfit() {
		return activeOutfit;
	}
	
	public static void setActiveOutfit(Outfit outfit) {
		activeOutfit = outfit;
	}
	
	public static void setOutfitName(String name) {
//		if(isSaveOutfitAvailable(Main.checkFileName(name).toLowerCase(), true)) {
			activeOutfit.setName(name);
//		}
	}
	
	public static final DialogueNode OUTFIT_EDITOR = new DialogueNode("服装编辑器", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getDoll().resetInventory(true);
			getDoll().loadOutfit(activeOutfit, OutfitSource.NOWHERE, OutfitSource.NOWHERE);
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			sb.append("<div style='float:left; border-radius:5px; background:"+PresetColour.BACKGROUND_DARK.toWebHexString()+"; text-align:center; width:90%; margin:1% 5%; padding:8px;'>");
				sb.append("<i>");
					sb.append("点击任意栏位打开衣物/武器选择界面。");
				sb.append("</i>");
			sb.append("</div>");
			
			sb.append(getInventoryEquippedPanel());
			
			sb.append("<div style='width:calc("+(100-inventoryUIWidth)+"% - 8px); padding:0; margin:0 0 0 8px; float:left;'>");
				sb.append("<h5 style='text-align:center;'>服装详情</h5>");
				
				// Name field:
				sb.append("<form style='text-align:center;float:left; width:80%; padding:0; margin:0 0 5% 5%;'>");
					sb.append("<input type='text' id='outfit_name' placeholder='Outfit name' value='"+activeOutfit.getName()+"' style='padding:0;margin:0;width:100%;'>");
				sb.append("</form>");
				sb.append("<div class='normal-button' id='apply_outfit_name' style='float:left; width:9.5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
					+ "&#10003;"
				+ "</div>");
				
				// Details:
				sb.append("<p style='margin-bottom:0; padding-bottom:0;'>");
					sb.append("名称：");
					sb.append("<br/><span style='font-size:1.1em;'>"+activeOutfit.getName()+"</span>");
					sb.append("<br/>");
					sb.append("<br/>");
					
					sb.append("服装费用：");
					sb.append("<br/><span style='font-size:1.1em;'>"+UtilText.formatAsMoney(activeOutfit.getCost())+"</span>");
					int essenceCost = activeOutfit.getEssenceCost();
					if(essenceCost==0) {
						sb.append("<br/><span style='font-size:1.1em;'>"+UtilText.formatAsEssencesUncoloured(essenceCost, "b", false)+"</span>");
					} else {
						sb.append("<br/><span style='font-size:1.1em;'>"+UtilText.formatAsEssences(essenceCost, "b", false)+"</span>");
					}
					sb.append("<br/>");
					sb.append("<br/>");
					
					sb.append("该区域可获取的套装：");
					sb.append("<br/><span style='font-size:1.1em;'>"+getOutfitAvailabilityFromTile(activeOutfit)+"</span>");
					sb.append("</p>");

			sb.append("</div>");

			
			sb.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			Outfit loadedOutfit = loadOutfit(loadedFileName);
			boolean outfitChanged = loadedFileName!=null
					&& !loadedFileName.isEmpty()
					&& activeOutfit!=null
					&& loadedOutfit!=null
					&& activeOutfit.hashCode()!=loadedOutfit.hashCode();
			
			if(index == 1) {
				if(isSaveOutfitAvailable(false)) {
					return new ResponseEffectsOnly("[style.colourMinorGood(保存)]", "将该服装以文件名“"+getOutfitSaveName()+"”保存，从而可以穿着或分配给奴隶。") {
						@Override
						public void effects() {
							saveOutfit(getOutfitSaveName(), activeOutfit, true, OUTFIT_EDITOR);
							loadedFileName = getOutfitSaveName();
						}
					};
					
				} else if(isSaveOutfitAvailable(true)) {
					return new Response("保存", "已存在名为“"+getOutfitSaveName()+"”的文件，无法另存为新文件……", null);
					
				} else {
					return new Response("保存", "无法保存未命名的服装！", null);
				}

			} else if(index==2) {
				if(loadedFileName==null || loadedFileName.isEmpty()) {
					return new Response("覆盖", "这是新创建的服装，没有文件可以覆盖……", null);
				}
				if(!outfitChanged) {
					return new Response("覆盖", "你还没修改过服装，现在无需覆盖……", null);
				}
				String fileNameForOverwrite = !isSaveOutfitAvailable(false)?getOutfitSaveName():loadedFileName;
				return new ResponseEffectsOnly("[style.colourMinorBad(覆盖)]",
						"将文件名称为“" + fileNameForOverwrite + "”的已保存服装替换为这套服装。"
							+ "<br/>[style.italicsBad(如果继续操作，现有的名为“"+fileNameForOverwrite+"”的服装将被覆盖丢失！)]"){
					@Override
					public void effects() {
						deleteOutfit(fileNameForOverwrite);
						saveOutfit(getOutfitSaveName(), activeOutfit, true, OUTFIT_EDITOR);
						loadedFileName = getOutfitSaveName();
					}
				};
				
//				if((loadedFileName!=null && !loadedFileName.isEmpty() && outfitChanged) || !isSaveOutfitAvailable(false)) {
//					String fileNameForOverwrite = !isSaveOutfitAvailable(false)?getOutfitSaveName():loadedFileName;
//					return new ResponseEffectsOnly("[style.colourMinorBad(Overwrite)]",
//							"Replace your saved outfit which has the file name '"+fileNameForOverwrite+"' with this outfit."
//								+ "<br/>[style.italicsBad(Your existing outfit with the name '"+fileNameForOverwrite+"' will be lost if you do this!)]"){
//						@Override
//						public void effects() {
//							deleteOutfit(fileNameForOverwrite);
//							saveOutfit(getOutfitSaveName(), activeOutfit, true, OUTFIT_EDITOR);
//							loadedFileName = getOutfitSaveName();
//						}
//					};
//					
//				} else if(loadedFileName!=null && !loadedFileName.isEmpty() && !outfitChanged) {
//					return new Response("Overwrite", "You haven't modified your outfit, so there's no need to overwrite it at the moment...", null);
//					
//				} else {
//					return new Response("Overwrite", "This is a newly created outfit, so there's no file to overwrite...", null);
//				}
				
			} else if(index == 4) {
				return new Response("忽略所有",
						"将所有当前为空的栏位设为“忽略”。"
							+ "这意味着每当套装被应用到角色身上时，角色目前在该栏位上装备的任何物品都不会被移除，而是会被忽略并保持原样。",
						OUTFIT_EDITOR) {
					@Override
					public void effects() {
						for(InventorySlot slot : InventorySlot.values()) {
							if(activeOutfit.getClothing().get(slot)==null && activeOutfit.getWeapons().get(slot)==null) {
								activeOutfit.addIgnoredSlot(slot);
							}
						}
					}
				};
				
			} else if(index == 5) {
				return new Response("清除忽略",
						"将所有当前设为“忽略”的栏位改为空。"
							+ "这意味着每当套装被应用到角色身上时，所有被定义为空的栏位将被清空，而不是保持原样。",
						OUTFIT_EDITOR) {
					@Override
					public void effects() {
						for(InventorySlot slot : InventorySlot.values()) {
							if(activeOutfit.getIgnoredSlots().contains(slot)) {
								if(slot.isWeapon()) {
									activeOutfit.addWeapon(slot, null);
								} else {
									activeOutfit.addClothing(slot, null);
								}
							}
						}
					}
				};
				
			} else if(index == 6) {
				if(Main.game.getPlayer().getMoney()<activeOutfit.getCost() || Main.game.getPlayer().getEssenceCount()<activeOutfit.getEssenceCost()) {
					return new Response("购买 ("+UtilText.formatAsMoneyUncoloured(activeOutfit.getCost(), "span")+")",
							"你买不起这套服装。"
							+ "<br/>"
								+(Main.game.getPlayer().getMoney()<activeOutfit.getCost()
									?"你[style.colourBad(没有)][style.moneyFormat("+activeOutfit.getCost()+", span)](只有[style.moneyFormat("+Main.game.getPlayer().getMoney()+", span)])。"
									:"你[style.colourGood(拥有)]所需的[style.moneyFormat("+activeOutfit.getCost()+", span)]。")
							+ (activeOutfit.getEssenceCost()<=0
								?""
								:"<br/>"
									+(Main.game.getPlayer().getEssenceCount()<activeOutfit.getEssenceCost()
										?"你[style.colourBad(没有)][style.essenceFormat("+activeOutfit.getEssenceCost()+", span)](只有[style.essenceFormat("+Main.game.getPlayer().getEssenceCount()+", span)])。"
										:"你[style.colourGood(拥有)]所需的[style.essenceFormat("+activeOutfit.getEssenceCost()+", span)]。")),
							null);
				} else {
					return new Response("购买 ("+UtilText.formatAsMoney(activeOutfit.getCost(), "span")+")",
							"购买这套服装后，你可以选择自己穿着或给一个奴隶穿上。"
							+ "<br/>"
							+ "这会[style.colorBad(花费)]你[style.moneyFormat("+activeOutfit.getCost()+", span)]"
							+(activeOutfit.getEssenceCost()<=0
								?"。"
								:"和[style.essenceFormat("+activeOutfit.getEssenceCost()+", span)]。")
							+ "<br/>"
							+ "[style.colorGood(你拥有)][style.moneyFormat("+Main.game.getPlayer().getMoney()+", span)]"
								+(activeOutfit.getEssenceCost()<=0
								?"。"
								:"和[style.essenceFormat("+Main.game.getPlayer().getEssenceCount()+", span)]。"),
							OUTFIT_PURCHASE) {
						@Override
						public void effects() {
							if(activeOutfit.getCost()==0) {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_NOTHING"));
							} else {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_PURCHASE"));
							}
							outfitObtainedViaPurchase = true;
							if(activeOutfit.getCost()>0) {
								Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(-activeOutfit.getCost()));
							}
							if(activeOutfit.getEssenceCost()>0) {
								Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementEssenceCount(-activeOutfit.getEssenceCost(), false));
							}
							
						}
					};
				}
				
			} else if(index == 7) {
				if(getOutfitAvailabilityFromTile(activeOutfit)<=0) {
					return new Response("从该区域装备",
							"你没有在这个区域获得这套服装所需的全部部件，所以如果你想装备，就得先购买……",
							null);
				} else {
					return new Response("从该区域装备",
							"由于你在这个区域已经拥有这套服装的全部所需部件，可以直接给自己或其中一个奴隶装备，无需再额外购买。",
							OUTFIT_PURCHASE) {
						@Override
						public void effects() {
							if(activeOutfit.getCost()==0) {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_NOTHING"));
							} else {
								Main.game.appendToTextEndStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "OUTFIT_OWNED"));
							}
							outfitObtainedViaPurchase = false;
						}
					};
				}
				
			} else if(index == 0) {
				boolean unsavedNewOutfit = isSaveOutfitAvailable(false) && activeOutfit.hashCode()!=new Outfit().hashCode();
				
				return new Response(
						outfitChanged || unsavedNewOutfit
							?"取消"
							:"返回",
						(outfitChanged || unsavedNewOutfit
							?"取消修改并退出服装编辑器，返回服装选择界面。"
								+(unsavedNewOutfit
										?"<br/>[style.italicsTerrible(由于你尚未保存这套服装，如果继续操作，你所做的所有修改都将丢失！)]"
										:"<br/>[style.italicsTerrible(如果你这样做，你对这套服装所做的修改将会丢失！)]")
							:"退出服装编辑器并返回服装选择界面。"),
						OUTFITS) {
					@Override
					public Colour getHighlightColour() {
						if(outfitChanged || unsavedNewOutfit) {
							return PresetColour.GENERIC_TERRIBLE;
						}
						return super.getHighlightColour();
					}
				};
			}
			return null;
		}
	};
	
	private enum equipType {
		UNEQUIP("已卸下", PresetColour.BASE_BLUE_LIGHT, true),
		EQUIP("已装备", PresetColour.GENERIC_GOOD, false),
		FAILED("装备失败", PresetColour.GENERIC_BAD, true);
		private String name;
		private Colour colour;
		private boolean droppedOnFloor;
		private equipType(String name, Colour colour, boolean droppedOnFloor){
			this.name=name;
			this.colour=colour;
			this.droppedOnFloor = droppedOnFloor;
		}
	}
	private static String getOutfitEquipTextRow(equipType type, String name) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style='margin:0; padding:0; width:20%; text-align:right; color:"+type.colour.toWebHexString()+"; float:left;'>");
			sb.append(type.name);
		sb.append("</div>");

		sb.append("<div style='margin:0; padding:0; width:60%; text-align:center; float:left;'>");
			sb.append(name);
		sb.append("</div>");
		
		sb.append("<div style='margin:0; padding:0; width:20%; text-align:left; float:left;'>");
			if(type.droppedOnFloor) {
				sb.append("([style.italics(丢在地上)])");
			}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String applyRowWrapper(String content, boolean alternateRow) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='background:"+RenderingEngine.getEntryBackgroundColour(alternateRow)+"; width:100%; margin:0;'>");
			sb.append(content);
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode OUTFIT_PURCHASE = new DialogueNode("购买服装", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("返回", "决定还是不购买这套服装了……", OUTFIT_EDITOR) {
					@Override
					public void effects() {
						if(outfitObtainedViaPurchase) {
							Main.game.appendToTextEndStringBuilder("<p>");
								if(activeOutfit.getCost()>0) {
									Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(activeOutfit.getCost()));
								}
								if(activeOutfit.getEssenceCost()>0) {
									Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementEssenceCount(activeOutfit.getEssenceCost(), false));
								}
							Main.game.appendToTextEndStringBuilder("</p>");
						}
					}
				};
			}
			String helperText = "<br/>装备这套服装时卸下的任何衣物或武器将被放入更衣室。";
			if(index==1) {
				boolean sealedClothingEquipped = Main.game.getPlayer().isAnyEquippedClothingSealed();
				String additionalHelperText = "";//"<br/>[style.italicsMinorGood(Your slave currently has no sealed clothing equipped.)]";
				if(sealedClothingEquipped) {
					additionalHelperText = "<br/>[style.italicsMinorBad(*你当前装备了被封印的衣物，可能导致无法穿戴这套服装的所有物品。)]";
				}
				return new Response("你"+(sealedClothingEquipped?"[style.colourMinorBad(*)]":""),
						"给自己装备这套服装。"+helperText+additionalHelperText,
						OUTFIT_EDITOR) {
					@Override
					public void effects() {  //aaaaaaaaaaaaaaaaaaa kalm
						List<String> unequipsList = new ArrayList<>();
						List<String> equipsList = new ArrayList<>();
						List<String> failuresList = new ArrayList<>();

						Map<InventorySlot, AbstractWeapon> weaponsEquippedBeforeOutfitApplication = new HashMap<>();
						for(InventorySlot weaponSlot : InventorySlot.allWeaponSlots) {
							AbstractWeapon weapon = Main.game.getPlayer().getWeaponInSlot(weaponSlot);
							if(weapon!=null) {
								weaponsEquippedBeforeOutfitApplication.put(weaponSlot, weapon);
							}
						}
						
						Map<InventorySlot, AbstractClothing> clothingEquippedBeforeOutfitApplication = new HashMap<>();
						for(AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped()) {
							clothingEquippedBeforeOutfitApplication.put(c.getSlotEquippedTo(), c);
						}
						
						Map<InventorySlot, AbstractCoreItem> failureToEquipMap = Main.game.getPlayer().loadOutfit(activeOutfit, OutfitSource.CELL, outfitObtainedViaPurchase?OutfitSource.NOWHERE:OutfitSource.CELL);
						
						Main.game.appendToTextEndStringBuilder("<h3 style='text-align:center; margin-bottom:0;'>你装备了“"+activeOutfit.getName()+"”套装</h3>");
						
						for(Entry<InventorySlot, AbstractWeapon> weapons : activeOutfit.getWeapons().entrySet()) {
							if(!failureToEquipMap.containsKey(weapons.getKey())) {
								equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
							} else {
								failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
							}
						}
						for(Entry<InventorySlot, AbstractClothing> clothing : activeOutfit.getClothing().entrySet()) {
							if(!failureToEquipMap.containsKey(clothing.getKey())) {
								equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
							} else {
								failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
							}
						}
						
						for(Entry<InventorySlot, AbstractWeapon> weaponPreviouslyEquipped : weaponsEquippedBeforeOutfitApplication.entrySet()) {
							if(Main.game.getPlayer().getWeaponInSlot(weaponPreviouslyEquipped.getKey())!=weaponPreviouslyEquipped.getValue()) {
								unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(weaponPreviouslyEquipped.getValue().getName(false, true))));
							}
						}
						for(Entry<InventorySlot, AbstractClothing> clothingPreviouslyEquipped : clothingEquippedBeforeOutfitApplication.entrySet()) {
							if(Main.game.getPlayer().getClothingInSlot(clothingPreviouslyEquipped.getKey())!=clothingPreviouslyEquipped.getValue()) {
								unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(clothingPreviouslyEquipped.getValue().getName(false, true))));
							}
						}
						
						Main.game.appendToTextEndStringBuilder("<div class='container-full-width'>");
							int i = 0;
							for(String entry : unequipsList) {
								Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
								i++;
							}
							for(String entry : equipsList) {
								Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
								i++;
							}
							for(String entry : failuresList) {
								Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
								i++;
							}
						Main.game.appendToTextEndStringBuilder("</div>");
						
						if(outfitObtainedViaPurchase) {
							for(Entry<InventorySlot, AbstractCoreItem> entry: failureToEquipMap.entrySet()) {
								if(entry.getValue() instanceof AbstractClothing && !((AbstractClothing)entry.getValue()).isDiscardedOnUnequip(entry.getKey())) {
									Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing)entry.getValue());
								} else if(entry.getValue() instanceof AbstractWeapon) {
									Main.game.getPlayerCell().getInventory().addWeapon((AbstractWeapon)entry.getValue());
								}
							}
						}
						calculateOutfitAvailability();
					}
				};
			}
			List<Response> slaveResponses = new ArrayList<>();
			for(GameCharacter slave : Main.game.getPlayer().getSlavesOwnedAsCharacters()) {
				SlaveJob currentJob = slave.getSlaveJob(Main.game.getHourOfDay());
				if(currentJob.getFlags().contains(SlaveJobFlag.SPECIAL_UNIFORM)) {
					String jobName = currentJob.getName(slave);
					slaveResponses.add(new Response(
							UtilText.parse(slave, "[npc.Name]"),
							UtilText.parse(slave, "你的[npc.raceFull(true)]奴隶，[npc.name]目前正在担任"+jobName+"。"
									+ "由于这份工作的特殊制服要求，[npc.sheIs]暂时无法装备这套服装。"),
							null));
					
				} else {
					boolean sealedClothingEquipped = slave.isAnyEquippedClothingSealed();
					String additionalHelperText = "";//"<br/>[style.italicsMinorGood(Your slave currently has no sealed clothing equipped.)]";
					if(sealedClothingEquipped) {
						additionalHelperText = "<br/>[style.italicsMinorBad(*[npc.Name]当前装备了被封印的衣物，可能导致[npc.herHim]无法穿戴这套服装的所有物品。)]";
					}
					slaveResponses.add(new Response(
							UtilText.parse(slave, "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"+(sealedClothingEquipped?"[style.colourMinorBad(*)]":"")),
							UtilText.parse(slave, "给你的[npc.raceFull(true)]奴隶[npc.name]装备这套服装。"+helperText+additionalHelperText),
							OUTFIT_EDITOR) {
						@Override
						public void effects() {
							List<String> unequipsList = new ArrayList<>();
							List<String> equipsList = new ArrayList<>();
							List<String> failuresList = new ArrayList<>();
							Map<InventorySlot, AbstractWeapon> weaponsEquippedBeforeOutfitApplication = new HashMap<>();
							for(InventorySlot weaponSlot : InventorySlot.allWeaponSlots) {
								AbstractWeapon weapon = slave.getWeaponInSlot(weaponSlot);
								if(weapon!=null) {
									weaponsEquippedBeforeOutfitApplication.put(weaponSlot, weapon);
								}
							}
							
							Map<InventorySlot, AbstractClothing> clothingEquippedBeforeOutfitApplication = new HashMap<>();
							for(AbstractClothing c : slave.getClothingCurrentlyEquipped()) {
								clothingEquippedBeforeOutfitApplication.put(c.getSlotEquippedTo(), c);
							}
							
							Cell c = slave.getCell();
							slave.setLocation(Main.game.getPlayer());
							Map<InventorySlot, AbstractCoreItem> failureToEquipMap = slave.loadOutfit(activeOutfit, OutfitSource.CELL, outfitObtainedViaPurchase?OutfitSource.NOWHERE:OutfitSource.CELL);
							slave.setLocation(c);
							
							Main.game.appendToTextEndStringBuilder(UtilText.parse(slave,
									"<h3 style='text-align:center; margin-bottom:0;'>你的奴隶[npc.name]，装备了“"+activeOutfit.getName()+"”套装：</h3>"));
							
							for(Entry<InventorySlot, AbstractWeapon> weapons : activeOutfit.getWeapons().entrySet()) {
								if(!failureToEquipMap.containsKey(weapons.getKey())) {
									equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
								} else {
									failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(weapons.getValue().getName(false, true))));
								}
							}
							for(Entry<InventorySlot, AbstractClothing> clothing : activeOutfit.getClothing().entrySet()) {
								if(!failureToEquipMap.containsKey(clothing.getKey())) {
									equipsList.add(getOutfitEquipTextRow(equipType.EQUIP, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
								} else {
									failuresList.add(getOutfitEquipTextRow(equipType.FAILED, Util.capitaliseSentence(clothing.getValue().getName(false, true))));
								}
							}
							
							for(Entry<InventorySlot, AbstractWeapon> weaponPreviouslyEquipped : weaponsEquippedBeforeOutfitApplication.entrySet()) {
								if(slave.getWeaponInSlot(weaponPreviouslyEquipped.getKey())!=weaponPreviouslyEquipped.getValue()) {
									unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(weaponPreviouslyEquipped.getValue().getName(false, true))));
								}
							}
							for(Entry<InventorySlot, AbstractClothing> clothingPreviouslyEquipped : clothingEquippedBeforeOutfitApplication.entrySet()) {
								if(slave.getClothingInSlot(clothingPreviouslyEquipped.getKey())!=clothingPreviouslyEquipped.getValue()) {
									unequipsList.add(getOutfitEquipTextRow(equipType.UNEQUIP, Util.capitaliseSentence(clothingPreviouslyEquipped.getValue().getName(false, true))));
								}
							}
							
							Main.game.appendToTextEndStringBuilder("<div class='container-full-width'>");
								int i = 0;
								for(String entry : unequipsList) {
									Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
									i++;
								}
								for(String entry : equipsList) {
									Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
									i++;
								}
								for(String entry : failuresList) {
									Main.game.appendToTextEndStringBuilder(applyRowWrapper(entry, i%2==0));
									i++;
								}
							Main.game.appendToTextEndStringBuilder("</div>");

							if(outfitObtainedViaPurchase) {
								for(Entry<InventorySlot, AbstractCoreItem> entry: failureToEquipMap.entrySet()) {
									if(entry.getValue() instanceof AbstractClothing && !((AbstractClothing)entry.getValue()).isDiscardedOnUnequip(entry.getKey())) {
										Main.game.getPlayerCell().getInventory().addClothing((AbstractClothing)entry.getValue());
									} else if(entry.getValue() instanceof AbstractWeapon) {
										Main.game.getPlayerCell().getInventory().addWeapon((AbstractWeapon)entry.getValue());
									}
								}
							}
							calculateOutfitAvailability();
						}
					});
				}
			}
			for(int i=0; i< slaveResponses.size(); i++) {
				if(index-2==i) {
					return slaveResponses.get(i);
				}
			}
			
			return null;
		}
	};
	
	private static float inventoryUIWidth = 60f;
	private static float mainClothingPanelWidth = 69f; // nice
	
	private static String getInventoryEquippedPanel() {
		StringBuilder sb = new StringBuilder();

		// For more than 1 arm row rendering:
		String weaponStyle = "width:31%; margin:1%;";
		String piercingStyle = "width:40%; margin:5% 5%;";
		
		float piercingTopOffset = 7.5f;
		
		sb.append("<div class='container-full-width' style='width:"+(inventoryUIWidth-5)+"%; padding:0; margin:0 0 0 5%; float:left;'>");
		
		// EQUIPPED:
		sb.append("<div class='container-full-width' style='width:"+mainClothingPanelWidth+"%; padding:0; margin:0;'>");
			for(InventorySlot invSlot : RenderingEngine.mainInventorySlots) {
				sb.append(getClothingSlotDiv(invSlot, activeOutfit.getClothing().get(invSlot), false));
			}
		sb.append("</div>");
		
		// Render weapons & piercings:
		sb.append("<div class='container-full-width' style='width:"+(100-mainClothingPanelWidth)+"%; padding:0; margin:0.5% 0 0 0;'>");
		
		AbstractWeapon mainWeapon1 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_MAIN_1);
		AbstractWeapon mainWeapon2 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_MAIN_2);
		AbstractWeapon mainWeapon3 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_MAIN_3);
		
		AbstractWeapon offhandWeapon1 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_1);
		AbstractWeapon offhandWeapon2 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_2);
		AbstractWeapon offhandWeapon3 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_3);
		
		// Main weapon:
		if(mainWeapon1!=null) {
			sb.append(getWeaponDiv(mainWeapon1, InventorySlot.WEAPON_MAIN_1, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_MAIN_1, weaponStyle));
		}
		
		// Weapon in second slot:
		if(mainWeapon2!=null) {
			sb.append(getWeaponDiv(mainWeapon2, InventorySlot.WEAPON_MAIN_2, weaponStyle));
			
		}  else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_MAIN_2, weaponStyle));
		}

		// Weapon in third slot:
		if(mainWeapon3!=null) {
			sb.append(getWeaponDiv(mainWeapon3, InventorySlot.WEAPON_MAIN_3, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_MAIN_3, weaponStyle));
		}
		
		// Offhand weapon:
		if(offhandWeapon1!=null) {
			sb.append(getWeaponDiv(offhandWeapon1, InventorySlot.WEAPON_OFFHAND_1, weaponStyle));
			
		} else if(mainWeapon1!=null && mainWeapon1.getWeaponType().isTwoHanded()) {
			sb.append(getEmptyWeaponDiv(true, InventorySlot.WEAPON_OFFHAND_1, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_OFFHAND_1, weaponStyle));
		}
		
		// Weapon in second slot:
		offhandWeapon2 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_2);
		if(offhandWeapon2!=null) {
			sb.append(getWeaponDiv(offhandWeapon2, InventorySlot.WEAPON_OFFHAND_2, weaponStyle));
			
		} else if(mainWeapon2!=null && mainWeapon2.getWeaponType().isTwoHanded()) {
			sb.append(getEmptyWeaponDiv(true, InventorySlot.WEAPON_OFFHAND_2, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_OFFHAND_2, weaponStyle));
		}
		
		// Weapon in third slot:
		offhandWeapon3 = activeOutfit.getWeapons().get(InventorySlot.WEAPON_OFFHAND_3);
		if(offhandWeapon3!=null) {
			sb.append(getWeaponDiv(offhandWeapon3, InventorySlot.WEAPON_OFFHAND_3, weaponStyle));
			
		} else if(mainWeapon3!=null && mainWeapon3.getWeaponType().isTwoHanded()) {
			sb.append(getEmptyWeaponDiv(true, InventorySlot.WEAPON_OFFHAND_3, weaponStyle));
			
		} else {
			sb.append(getEmptyWeaponDiv(false, InventorySlot.WEAPON_OFFHAND_3, weaponStyle));
		}
		

		sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:"+piercingTopOffset+"% 0 0 0;'>");
			//piercingSlots
			for (InventorySlot invSlot : RenderingEngine.piercingSlots) {
				AbstractClothing clothing = activeOutfit.getClothing().get(invSlot);
				
				if(clothing!=null) {
					// add to content:
					sb.append(
							"<div class='inventory-item-slot " + getClassRarityIdentifier(clothing.getRarity()) + "'"
								+ (clothing.isSealed()
										? "style='"+piercingStyle+" border-width:2px; border-color:" + PresetColour.SEALED.toWebHexString() + "; border-style:solid;'"
										: "style='"+piercingStyle+"'")
								+ ">");
						sb.append("<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(Main.game.getPlayer())+"</div>");
						sb.append("<div class='item-price'>"
								+ UtilText.formatAsItemPrice(clothing.getValue())
							+ "</div>");
						sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "'>" + "</div>");
						sb.append(getItemDeleteButton(invSlot, 14));
					sb.append("</div>");
					
	
				} else {
					sb.append("<div class='inventory-item-slot' style='"+piercingStyle+"'>");

						if(activeOutfit.getIgnoredSlots().contains(invSlot)) {
							sb.append("<div class='inventory-icon-content' style='opacity:0.25;'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>");
						}
						sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "'>" + "</div>");
					sb.append("</div>");
				}
			}
		sb.append("</div>");
		
		sb.append("</div>");
		
		
		// Render final row of clothing:
		
		sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
		
		for (InventorySlot invSlot : RenderingEngine.secondaryInventorySlots) {
			sb.append(getClothingSlotDiv(invSlot, activeOutfit.getClothing().get(invSlot), true));
		}
		
		// Filler for final icon:
//		equippedPanelSB.append("<div class='inventory-item-slot secondary "+getClassRarityIdentifier(Rarity.COMMON)+"'>");
//			equippedPanelSB.append("<div class='inventory-icon-content'>"
//										+SVGImages.SVG_IMAGE_PROVIDER.getTattooSwitchTattoo()
//									+"</div>"
//									+ "<div class='overlay' id='TATTOO_SWITCH_LEFT'></div>");
//		equippedPanelSB.append("</div>");

		sb.append("</div>");
		
		sb.append("</div>");
		
		return sb.toString();
	}

	/**
	 * @return true if this InventorySlot is blocked by other clothing in this outfit.
	 */
	public static boolean isSlotDisabled(InventorySlot invSlot) {
		String blockText = getSlotDisabledText(invSlot);
		return blockText!=null && !blockText.isEmpty();
	}
	
	public static String getSlotDisabledText(InventorySlot invSlot) {
		List<String> clothingBlockingThisSlot = new ArrayList<>();
		for(AbstractClothing c : getDoll().getClothingCurrentlyEquipped()) {
			if(c.getIncompatibleSlots(getDoll(), c.getSlotEquippedTo()).contains(invSlot)) {
				clothingBlockingThisSlot.add(c.getName());
			}
		}
		
//		BodyPartClothingBlock block = invSlot.getBodyPartClothingBlock(getDoll());
		
		if(!clothingBlockingThisSlot.isEmpty()) {
			return "该栏位目前被" + Util.stringsToStringList(clothingBlockingThisSlot, false) + "<b style='color:" + PresetColour.SEALED.toWebHexString() + ";'>锁住了</b>。";
		}
//		else if(block != null) {
//			return UtilText.parse(getDoll(), block.getDescription());
//		}
		
		return "";
	}
	
	private static String getClothingSlotDiv(InventorySlot invSlot, AbstractClothing clothing, boolean isSecondary) {
		StringBuilder sb = new StringBuilder();
		
		String className = "inventory-item-slot";
		String styleModifier= "width:23%; margin:1%; padding:0;";
		if(isSecondary) {
			className = "inventory-item-slot secondary";
			styleModifier = "width:"+((mainClothingPanelWidth/4)-1.5f)+"%; margin:0.75%; padding:0;";
		}

		if(clothing!=null) {
			int essenceCost = getClothingEssenceCost(clothing);
			sb.append("<div class='"+className+getClassRarityIdentifier(clothing.getRarity())+"'"
					+(clothing.isSealed()
						?" style='border-width:2px; border-color:"+PresetColour.SEALED.toWebHexString()+"; border-style:solid;"+styleModifier+"'"
						:" style='"+styleModifier+"'")
					+">");
				sb.append("<div class='inventory-icon-content'>"+clothing.getSVGEquippedString(Main.game.getPlayer())+"</div>");

				if(essenceCost>0) {
					sb.append("<div class='item-price' style='bottom:14px;'>"
								+ (essenceCost==0
									?UtilText.formatAsEssencesUncoloured(getClothingEssenceCost(clothing), "b", false)
									:UtilText.formatAsEssences(getClothingEssenceCost(clothing), "b", false))
							+ "</div>");
				}
				
				sb.append("<div class='item-price'>"
						+ UtilText.formatAsItemPrice(clothing.getValue())
					+ "</div>");
				sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "'>" + "</div>");
				sb.append(getItemDeleteButton(invSlot));
			sb.append("</div>");
			
		} else {
			boolean disabled = isSlotDisabled(invSlot);
			sb.append("<div class='"+className+(disabled?" disabled":"")+"' style='"+styleModifier+"'>");
				if(activeOutfit.getIgnoredSlots().contains(invSlot) && !disabled) {
					sb.append("<div class='inventory-icon-content' style='opacity:0.25;'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>");
				}
				sb.append("<div class='overlay' id='outfit_select_slot_" + invSlot.toString() + "' style='"+(disabled?"cursor:default;":"")+"'>" + "</div>");
			sb.append("</div>");
		}
		
		return sb.toString();
	}

	public static int getClothingEssenceCost(AbstractClothing clothing) {
		int essenceCost = 0;
		for(ItemEffect ie : clothing.getEffects()) {
			if(!getDefaultEffects(clothing).contains(ie)) {
				essenceCost += EnchantingUtils.getModifierEffectCost(true, clothing, ie);
			}
		}
		return essenceCost;
	}
	
	private static String getEmptyWeaponDiv(boolean disabled, InventorySlot slot, String weaponStyle) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='inventory-item-slot"+(disabled?" disabled":"")+"' "+(disabled?"id='outfit_select_slot_" + slot.toString() + "'":"")+" style='"+weaponStyle+"'>");
		
		if(!disabled) {
			if(activeOutfit.getIgnoredSlots().contains(slot)) {
				sb.append("<div class='inventory-icon-content' style='opacity:0.25;'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>");
			} else {
				sb.append("<div class='inventory-icon-content' style='width:75%; margin:12.5%;'>"+SVGImages.SVG_IMAGE_PROVIDER.getFist()+"</div>");
			}
			sb.append("<div class='overlay' id='outfit_select_slot_" + slot.toString() + "'></div>");	
		}
		
		sb.append("</div>");
		
		return sb.toString();
	}
	

	private static String getWeaponDiv(AbstractWeapon weapon, InventorySlot slot, String weaponStyle) {
		StringBuilder sb = new StringBuilder();
		String weaponCount = getThrownWeaponCountDiv(weapon.getWeaponType());
		int essenceCost = getWeaponEssenceCost(weapon);
		sb.append("<div class='inventory-item-slot" + getClassRarityIdentifier(weapon.getRarity()) + "' style='"+weaponStyle+"'>"
					+ "<div class='inventory-icon-content'>"+weapon.getSVGEquippedString(Main.game.getPlayer())+"</div>");
		if(essenceCost>0) {
			sb.append("<div class='item-price' style='bottom:14px;'>"
							+ (essenceCost==0
								?UtilText.formatAsEssencesUncoloured(getWeaponEssenceCost(weapon), "b", false)
								:UtilText.formatAsEssences(getWeaponEssenceCost(weapon), "b", false))
						+ "</div>");
		}
		sb.append("<div class='item-price'>"
						+ UtilText.formatAsItemPrice(weapon.getValue())
					+ "</div>"
					+ "<div class='overlay' id='outfit_select_slot_" + slot.toString() + "'>"+weaponCount+"</div>");
			sb.append(getItemDeleteButton(slot, 14));
		sb.append("</div>");
		
		return sb.toString();
		
	}
	
	public static int getWeaponEssenceCost(AbstractWeapon weapon) {
		int essenceCost = 0;
		for(ItemEffect ie : weapon.getEffects()) {
			if(!getDefaultEffects(weapon).contains(ie)) {
				essenceCost += EnchantingUtils.getModifierEffectCost(true, weapon, ie);
			}
		}
		return essenceCost;
	}

	private static String getItemDeleteButton(InventorySlot slot) {
		return getItemDeleteButton(slot, 16);
	}
	
	private static String getItemDeleteButton(InventorySlot slot, int size) {
		StringBuilder sb = new StringBuilder();
		int sizeReduced = size/3;
		sb.append("<div class='normal-button' id='clear_slot_"+slot.toString()+"'"
						+ "style='position:absolute; right:-"+sizeReduced+"px; top:-"+sizeReduced+"px; text-align:center; font-size:"+size+"px; line-height:"+size+"px; width:"+(size+2)+"px;"
								+ "  padding:0; margin:0; color:"+PresetColour.GENERIC_BAD.toWebHexString()+"; border:1px solid "+PresetColour.BACKGROUND_DARK.toWebHexString()+";'>");
			sb.append("X");
//			sb.append(SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete());
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getThrownWeaponCountDiv(AbstractWeaponType weaponType) {
		int amount = 10;
		
		if(!weaponType.isOneShot()) {
			return "";
		}
		return "<div class='item-count' "+(amount==0?"style='opacity:0.5;'":"")+">+" + amount + "</div>";
	}

	private static String getClassRarityIdentifier(Rarity rarity) {
		return RenderingEngine.getClassRarityIdentifier(rarity);
	}
	
	// Item selection and modification:
	
	private static InventorySlot selectedSlot;
	private static AbstractWeapon weaponSelected;
	private static AbstractClothing clothingSelected;

	public static InventorySlot getSelectedSlot() {
		return selectedSlot;
	}
	public static void setSelectedSlot(InventorySlot selectedSlot) {
		LilayaDressingRoomDialogue.selectedSlot = selectedSlot;
	}
	
	public static boolean initItemFromSlot() {
		if(selectedSlot.isWeapon()) {
			AbstractWeapon weapon = activeOutfit.getWeapons().get(selectedSlot);
			if(weapon!=null) {
				weaponSelected = weapon;
				return true;
			}
			
		} else {
			AbstractClothing clothing = activeOutfit.getClothing().get(selectedSlot);
			if(clothing!=null) {
				clothingSelected = clothing;
				return true;
			}
		}
		return false;
	}
	
	public static AbstractWeapon getWeaponSelected() {
		return weaponSelected;
	}
	public static void setWeaponSelected(AbstractWeaponType weaponType) {
		LilayaDressingRoomDialogue.weaponSelected = Main.game.getItemGen().generateWeapon(weaponType);
	}

	public static AbstractClothing getClothingSelected() {
		return clothingSelected;
	}
	public static void setClothingSelected(AbstractClothingType clothingType) {
		LilayaDressingRoomDialogue.clothingSelected = Main.game.getItemGen().generateClothing(clothingType, false);
	}
	
	public static AbstractCoreItem getSelectedItem() {
		if(weaponSelected!=null) {
			return weaponSelected;
		}
		if(clothingSelected!=null) {
			return clothingSelected;
		}
		return null;
	}
	
	public static void clearSlot() {
		activeOutfit.clearSlot(selectedSlot);
	}

	public static void ignoreSlot() {
		activeOutfit.addIgnoredSlot(selectedSlot);
	}
	
	public static final DialogueNode OUTFIT_EDITOR_ITEM_CHOICE = new DialogueNode("选择", "", true) {
		@Override
		public void applyPreParsingEffects() {
			weaponSelected = null;
			clothingSelected = null;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder sb = new StringBuilder();

			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0 0 8px 0; text-align:center'>");
				sb.append("<h3 style='margin:0'>选择[style.colourDisabled(-> 染色 -> 附魔)]</h3>");
			sb.append("</div>");
			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0 0 8px 0; text-align:center'>");
				sb.append("<p style='margin:0;'>");
					sb.append("<i>所有已经发现的"+(selectedSlot.isWeapon()?"武器":"衣物")+"都可以选择。这会考虑你“共享百科全书”的内容设置。</i>");
				sb.append("</p>");
			sb.append("</div>");
			
			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+Rarity.COMMON.getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>"
						+ "<div class='inventory-icon-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div>"
						+ "<div class='overlay' id='clear_slot' style='cursor:default;'></div>"
					+ "</div>");
				sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+Rarity.COMMON.getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>"
						+ "<div class='inventory-icon-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDeniedIconDisabled()+"</div>"
						+ "<div class='overlay' id='ignore_slot' style='cursor:default;'></div>"
					+ "</div>");
			
				if(selectedSlot.isWeapon()) {
					for(AbstractWeaponType weaponType : PhoneDialogue.getWeaponsDiscoveredList()) {
						if(weaponType.getRarity()==Rarity.QUEST
								|| weaponType.getRarity()==Rarity.LEGENDARY
								|| weaponType.getItemTags().contains(ItemTag.REMOVE_FROM_DRESSING_ROOM_OUTFITS)) {
							continue;
						}
						boolean discovered = Main.getProperties().isWeaponDiscovered(weaponType) || Main.game.isDebugMode();
						
						sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+weaponType.getRarity().getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>"
								+ "<div class='inventory-icon-content'>"+(discovered?weaponType.getSVGImage():"")+"</div>"
								+ (discovered
									?"<div class='item-price'>"
										+ UtilText.formatAsItemPrice(weaponType.getBaseValue())
									+ "</div>"
									:"")
								+ "<div class='overlay"+(discovered?"' id='"+weaponType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>"
							+ "</div>");
					}
					
				} else {
					for(AbstractClothingType clothingType : PhoneDialogue.getClothingDiscoveredList()) {
						if(clothingType.getRarity()==Rarity.QUEST
								|| clothingType.getRarity()==Rarity.LEGENDARY
								|| clothingType.getDefaultItemTags().contains(ItemTag.REMOVE_FROM_DRESSING_ROOM_OUTFITS)
								|| clothingType.getDefaultItemTags().contains(ItemTag.MILKING_EQUIPMENT)) {
							continue;
						}
						if(!clothingType.getEquipSlots().contains(selectedSlot)) {
							continue;
						}
						boolean discovered = Main.getProperties().isClothingDiscovered(clothingType) || Main.game.isDebugMode();
						
						// Warn player if this clothing is incompatible with any of the outfit's currently selected clothing
						List<AbstractClothing> incompatibleClothing = new ArrayList<>();
						for(InventorySlot slot : Main.game.getItemGen().generateClothing(clothingType, false).getIncompatibleSlots(getDoll(), selectedSlot)) {
							if(getDoll().getClothingInSlot(slot)!=null) {
								incompatibleClothing.add(getDoll().getClothingInSlot(slot));
							}
						}
						
						List<Colour> clothingColours = new ArrayList<>();
						for(ColourReplacement cr : clothingType.getColourReplacements()) {
							// Use a consistent seed so the colours are always the same:
							Util.random.setSeed(clothingType.getName().hashCode());
							Colour colour = cr.getRandomOfDefaultColours();
							clothingColours.add(colour);
						}
						
						sb.append("<div class='inventory-item-slot unequipped' style='background-color:"+clothingType.getRarity().getBackgroundColour().toWebHexString()+"; width:12%; margin:0.25%; padding:0;'>");
							sb.append("<div class='inventory-icon-content'>"+(discovered?clothingType.getSVGEquippedImage(Main.game.getPlayer(), selectedSlot, clothingColours, null, new ArrayList<>(), null):"")+"</div>");
								if(discovered) {
									if(!incompatibleClothing.isEmpty()) {
									sb.append("<div class='item-price' style='top:8px; font-size:1.4em;'>"
												+ "[style.boldBad(!)]"
											+ "</div>");
									}
									sb.append("<div class='item-price'>"
												+ UtilText.formatAsItemPrice(clothingType.getBaseValue())
											+ "</div>");
								}
							sb.append("<div class='overlay"+(discovered?"' id='"+clothingType.getId()+"'":" disabled-dark'")+" style='cursor:default;'></div>");
						sb.append("</div>");
					}
				}

				Util.random.setSeed(System.nanoTime()); // Reset seed to be close to random
				
			sb.append("</div>");
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 0) {
				return new Response("返回", "返回服装编辑器。", OUTFIT_EDITOR);
			}
			return null;
		}
	};

	public static final DialogueNode OUTFIT_EDITOR_ITEM_DYE = new DialogueNode("染色", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder sb = new StringBuilder();

			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				sb.append("<h3 style='text-align:center'>[style.colourDisabled(选择 ->)]染色[style.colourDisabled(-> 附魔)]</h3>");
			sb.append("</div>");

			if(clothingSelected!=null) {
				sb.append(getClothingDyeUI());
			} else {
				sb.append(getWeaponDyeUI());
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "前往附魔界面。", OUTFIT_EDITOR_ITEM_ENCHANT) {
					@Override
					public void effects() {
						initEnchantDialogue();
					}
				};
				
			} else if(index == 0) {
				return new Response("返回",
						"返回物品选择界面。"
						+ "<br/>[style.italicsBad(如果你这样做，将失去所有的修改。)]",
						OUTFIT_EDITOR_ITEM_CHOICE);
			}
			return null;
		}
	};
	

	private static String getClothingDyeUI() {
		InventorySlot slotEquippedTo = getSelectedSlot();
		StringBuilder sb = new StringBuilder();
		
		sb.append(
//				"<div class='container-full-width'>"
//					+ "<div class='inventoryImage'>"
//						+ "<div class='inventoryImage-content'>"
//							+ clothingSelected.getSVGString()
//						+ "</div>"
//					+ "</div>"
//					+ "<h3 style='text-align:center;'><b>"+clothingSelected.getDisplayName(true)+"</b></h3>"
//					+ "<p>"
//						+ "Select the desired colours from the coloured buttons below, and after using the preview to see how the new clothing will look, press the 'Dye' option at the bottom of the screen to apply your changes."
//					+ "</p>"
//				+ "</div>"
//					
//				+ "<br/>"
				
				"<div class='container-full-width'>"
					+ "<div class='inventoryImage'>"
						+ "<div class='inventoryImage-content'>"
							+ clothingSelected.getClothingType().getSVGImage(
									slotEquippedTo,
									clothingSelected.getColours(),
									clothingSelected.getPattern(),
									clothingSelected.getPatternColours(),
									clothingSelected.getStickers())
						+ "</div>"
					+ "</div>");
		
		sb.append("<h3 style='text-align:center;'><b>染色并预览</b></h3>");
		
		if(!clothingSelected.getClothingType().getStickers().isEmpty()) {
			StringBuilder stickerSB = new StringBuilder();
			boolean stickerFound = false;
			List<StickerCategory> orderedCategories = new ArrayList<>(clothingSelected.getClothingType().getStickers().keySet());
			Collections.sort(orderedCategories, (s1, s2)->s1.getPriority()-s2.getPriority());
			
			for(StickerCategory cat : orderedCategories) {
				stickerSB.append("<div class='container-quarter-width' style='width:calc(75% - 16px); margin:0 8px; padding:0;'>");
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding-top:6px; width:20%;'>");
						stickerSB.append(Util.capitaliseSentence(cat.getName())+":"); // Category name
					stickerSB.append("</div>");
					
					stickerSB.append("<div class='container-quarter-width' style='margin:0; padding:0; width:80%;'>");
						List<Sticker> orderedStickers = new ArrayList<>(clothingSelected.getClothingType().getStickers().get(cat));
						Collections.sort(orderedStickers, (s1, s2)->s1.getPriority()-s2.getPriority());
						for(Sticker sticker : orderedStickers) {
							String requirements = UtilText.parse(sticker.getUnavailabilityText()).trim();
							if(requirements.isEmpty() || sticker.isShowDisabledButton()) {
								boolean specialSticker = !sticker.getAvailabilityText().isEmpty() || !sticker.getTagsApplied().isEmpty() || !sticker.getTagsRemoved().isEmpty();
								stickerFound = true;
								String id = "ITEM_STICKER_"+cat.getId()+sticker.getId();
								if(!requirements.isEmpty()) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button disabled'>"
													+ "<b style='color:" + PresetColour.TEXT_GREY.toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
									
								} else if(clothingSelected.getStickers().get(cat.getId()).equalsIgnoreCase(sticker.getId())) {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button active'>"
													+ "<b style='color:" + sticker.getColourSelected().toWebHexString() + ";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</b>"
											+ "</div>");
								} else {
									stickerSB.append(
											"<div id='"+id+"' class='cosmetics-button'>"
													+ "<span style='color:"+sticker.getColourDisabled().toWebHexString()+";'>" + Util.capitaliseSentence(sticker.getName()) + (specialSticker?"*":"") + "</span>"
											+ "</div>");
								}
							}
						}
					stickerSB.append("</div>");
				stickerSB.append("</div>");
				
				if(stickerFound) {
					stickerFound = false;
					sb.append(stickerSB.toString());
					stickerSB = new StringBuilder();
				}
			}
		}
		
		List<Colour> clothingColours = clothingSelected.getColours();
		for(int i=0; i<clothingSelected.getClothingType().getColourReplacements().size(); i++) {
			ColourReplacement cr = clothingSelected.getClothingType().getColourReplacement(i);
			if(!cr.getAllColours().isEmpty()) {
				sb.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
							+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+"颜色"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(无法更改)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
//					if(!c.isDesaturated()) {
						sb.append("<div class='normal-button"+(clothingColours.size()>i && clothingColours.get(i)==c?" selected":"")+"' id='DYE_CLOTHING_"+i+"_"+c.getId()+"'"
											+ " style='width:auto; margin-right:4px; border-width:1px;"
												+(cr.getDefaultColours().contains(c)
													?"border-color:"+PresetColour.TEXT_GREY.toWebHexString()+";"
													:"")
												+(clothingColours.size()>i && clothingColours.get(i)==c
													?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
													:"")+"'>"
										+ "<div class='phone-item-colour' style='"
											+ (c.isMetallic()
													?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
													:"background-color:" + c.toWebHexString() + ";")
											+ "'></div>"
							+ "</div>");
//					}
				}
				sb.append("</div>");
			}
		}
		
		if(clothingSelected.getClothingType().isPatternAvailable()){
			sb.append(
					"<br/>"
					+ "<div class='container-full-width'>"
					+ "花纹:<br/>");
	 
			for (Pattern pattern : Pattern.getAllPatterns()) {
				if (clothingSelected.getPattern().equals(pattern.getId())) {
					sb.append(
							"<div class='cosmetics-button active'>"
								+ "<b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</b>"
							+ "</div>");
				} else {
					sb.append(
							"<div id='ITEM_PATTERN_"+pattern.getId()+"' class='cosmetics-button'>"
							+ "<span style='color:"+PresetColour.TRANSFORMATION_GENERIC.getShades()[0]+";'>" + Util.capitaliseSentence(pattern.getNiceName()) + "</span>"
							+ "</div>");
				}
			}
			sb.append("</div>");

			for(int i=0; i<clothingSelected.getClothingType().getPatternColourReplacements().size(); i++) {
				ColourReplacement cr = clothingSelected.getClothingType().getPatternColourReplacement(i);
				if(!cr.getAllColours().isEmpty() && Pattern.getPattern(clothingSelected.getPattern()).isRecolourAvailable(cr)) {
					sb.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
								+ "Pattern "+Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+" 颜色:<br/>");
					
					for (Colour c : cr.getAllColours()) {
						sb.append("<div class='normal-button"+(clothingSelected.getPatternColours().size()>i && clothingSelected.getPatternColours().get(i)==c?" selected":"")+"' id='DYE_CLOTHING_PATTERN_"+i+"_"+c.getId()+"'"
										+ " style='width:auto; margin-right:4px;"+(clothingSelected.getPatternColours().size()>i && clothingSelected.getPatternColours().get(i)==c?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";":"")+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
							+ "</div>");
					}
					sb.append("</div>");
				}
			}
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	private static String getWeaponDyeUI() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(
//				"<div class='container-full-width'>"
//					+ "<div class='inventoryImage'>"
//						+ "<div class='inventoryImage-content'>"
//							+ weaponSelected.getSVGString()
//						+ "</div>"
//					+ "</div>"
//					+ "<h3 style='text-align:center;'><b>"+weaponSelected.getDisplayName(true)+"</b></h3>"
//					+ "<p>"
//						+ "Select the desired colours from the coloured buttons below, and after using the preview to see how the new weapon will look, press the 'Dye' option at the bottom of the screen to apply your changes."
//					+ "</p>"
//				+ "</div>"
//				+ "<br/>"
				"<div class='container-full-width'>"
					+ "<div class='container-full-width' style='text-align:center; width:calc(25% - 16px); float:right;'>"
						+ "<div class='inventoryImage' style='width:100%;'>"
							+ (weaponSelected.getWeaponType().isEquippedSVGImageDifferent()
								?"已卸下"
								:"")
							+ "<div class='inventoryImage-content'>"
								+ weaponSelected.getSVGString()
							+ "</div>"
						+ "</div>"
						+(weaponSelected.getWeaponType().isEquippedSVGImageDifferent()
							?"<div class='inventoryImage' style='width:100%;'>"
								+ "已装备"
									+ "<div class='inventoryImage-content'>"
										+ weaponSelected.getSVGEquippedString(Main.game.getPlayer())
									+ "</div>"
								+ "</div>"
							:"")
					+ "</div>"
					+ "<h3 style='text-align:center;'><b>染色并预览</b></h3>");
		
		
		sb.append("<div class='container-quarter-width' style='text-align:center;width:calc(75% - 16px);'>"
				+ "<b>伤害类型:</b><br/>");
		for(DamageType dt : weaponSelected.getWeaponType().getAvailableDamageTypes()) {
			sb.append("<div class='normal-button"+(weaponSelected.getDamageType()==dt?" selected":"")+"' id='DAMAGE_TYPE_"+dt.toString()+"'"
							+ "style='width:20%; margin:0 2.5%; color:"+(weaponSelected.getDamageType()==dt?dt.getColour().toWebHexString():dt.getColour().getShades(8)[0])+";'>"
						+ Util.capitaliseSentence(dt.getName())
					+ "</div>");
		}
		sb.append("</div>");

		boolean colourOptions = false;

		for(int i=0; i<weaponSelected.getWeaponType().getColourReplacements(false).size(); i++) {
			colourOptions = true;
			ColourReplacement cr = weaponSelected.getWeaponType().getColourReplacement(false, i);
			if(!cr.getAllColours().isEmpty()) {
				sb.append("<div class='container-quarter-width' style='width:calc(75% - 16px);'>"
						+ Util.capitaliseSentence(Util.intToPrimarySequence(i+1))+"颜色"+(cr.isRecolouringAllowed()?"":" ([style.italicsBad(无法更改)])")+":<br/>");
				
				for(Colour c : cr.getAllColours()) {
					sb.append("<div class='normal-button"+(weaponSelected.getColours().size()>i && weaponSelected.getColours().get(i)==c?" selected":"")+"' id='DYE_WEAPON_"+i+"_"+c.getId()+"'"
										+ " style='width:auto; margin-right:4px; border-width:1px;"
											+(cr.getDefaultColours().contains(c)
												?"border-color:"+PresetColour.TEXT_GREY.toWebHexString()+";"
												:"")
											+(weaponSelected.getColours().size()>i && weaponSelected.getColours().get(i)==c
												?" background-color:"+PresetColour.BASE_GREEN.getShades()[4]+";"
												:"")
										+"'>"
									+ "<div class='phone-item-colour' style='"
										+ (c.isMetallic()
												?"background: repeating-linear-gradient(135deg, " + c.toWebHexString() + ", " + c.getShades()[4] + " 10px);"
												:"background-color:" + c.toWebHexString() + ";")
										+ "'></div>"
						+ "</div>");
				}
				sb.append("</div>");
			}
		}
		
		if(!colourOptions) {
			sb.append("<div class='container-half-width' style='text-align:center;'>"
					+ "[style.colourDisabled(染色选项均不可用……)]"
					+ "</div>");
		}

		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static void initEnchantDialogue() {
		if(newlyCreatedWeapon) {
			getSelectedItem().getEffects().clear();
			getSelectedItem().getEffects().addAll(getDefaultEffects(getSelectedItem()));
		}
		
		LilayaDressingRoomDialogue.effects.clear();
		LilayaDressingRoomDialogue.resetEnchantmentVariables();
		LilayaDressingRoomDialogue.initModifiers();
		LilayaDressingRoomDialogue.setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), effects));

//		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
//		if(defaultName) {
//			setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
//		} else {
//			if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
//				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
//				setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
//			}
//		}
	}
	
	public static final DialogueNode OUTFIT_EDITOR_ITEM_ENCHANT = new DialogueNode("附魔", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder sb = new StringBuilder();

			sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
				sb.append("<h3 style='text-align:center'>[style.colourDisabled(选择 -> 染色 ->)]附魔</h3>");
			sb.append("</div>");
			
			sb.append(getEnchantmentUI());
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("确认", "使用这件物品并返回服装编辑器。", OUTFIT_EDITOR) {
					@Override
					public void effects() {
						if(clothingSelected!=null) {
							AbstractClothing craftedClothing = EnchantingUtils.craftClothing(clothingSelected, effects);
							activeOutfit.addClothing(selectedSlot, craftedClothing);

							// Remove incompatible clothing:
							for(InventorySlot slot : craftedClothing.getIncompatibleSlots(LilayaDressingRoomDialogue.getDoll(), LilayaDressingRoomDialogue.getSelectedSlot())) {
								activeOutfit.addClothing(slot, null);
							}
							
							clothingSelected = null;
						} else {
							AbstractWeapon craftedWeapon = EnchantingUtils.craftWeapon(weaponSelected, effects);
							activeOutfit.addWeapon(selectedSlot, craftedWeapon);
							weaponSelected = null;
						}
						
						calculateOutfitAvailability();
					}
				};
				
			//TODO add save/load with conditional based on if unlocked and slot compatible - index 2
				// (no save)
				
			} else if(index==4) {
				boolean defaultsMissing = false;
				for(ItemEffect ie : getDefaultEffects(getSelectedItem())) {
					if(!effects.contains(ie)) {
						defaultsMissing = true;
						break;
					}
				}
				String helperText = "<br/><i>默认附魔在购买服装时不消耗精华。</i>";
				
				if(getDefaultEffects(getSelectedItem()).isEmpty()) {
					return new Response("恢复默认", "该物品没有可恢复的默认附魔效果。"+helperText, null);
					
				} else if(!defaultsMissing) {
					return new Response("恢复默认", "该物品已拥有全部默认附魔效果。"+helperText, null);
					
				} else {
					return new ResponseEffectsOnly("恢复默认", "恢复该物品的默认附魔效果。"+helperText) {
						@Override
						public void effects() {
							int i=0;
							for(ItemEffect ie : getDefaultEffects(getSelectedItem())) {
								effects.remove(ie);
								effects.add(i, ie);
								i++;
							}
							Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
						}
					};
				}
				
			} else if(index==5) {
				if(activeOutfit.getIconSlotPriority()!=selectedSlot) {
					return new ResponseEffectsOnly("图标：[style.colourBad(关闭)]",
							(activeOutfit.getIconSlotPriority()==null
								?"这套服装当前使用的是最高价值物品作为图标。"
								:"这套服装当前使用“<i>"+activeOutfit.getIconSlotPriority().getName()+"</i>”栏位作为图标。")
							+"<br/>[style.italics(激活后，该套装将使用该栏位(<i>"+selectedSlot.getName()+"</i>)作为其默认图标。)]") {
						@Override
						public void effects() {
							activeOutfit.setIconSlotPriority(selectedSlot);
							Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
						}
					};
					
				} else {
					return new ResponseEffectsOnly("图标：[style.colourGood(开启)]",
							"这套服装当前使用该栏位(<i>"+selectedSlot.getName()+"</i>)作为其默认图标。"
									+"<br/>[style.italics(激活后，该套装将使用最高价值的物品作为其图标。)]") {
						@Override
						public void effects() {
							activeOutfit.setIconSlotPriority(null);
							Main.game.setContent(new Response("", "", LilayaDressingRoomDialogue.OUTFIT_EDITOR_ITEM_ENCHANT));
						}
					};
				}
				
			} else if(index == 0) {
				return new Response("返回", "返回到物品选择界面。", OUTFIT_EDITOR_ITEM_DYE);
			}
			return null;
		}
	};

	private static List<ItemEffect> effects = new ArrayList<>();
	private static TFModifier primaryMod = TFModifier.NONE;
	private static TFModifier secondaryMod = TFModifier.NONE;
	public static TFModifier previousPrimaryMod = TFModifier.NONE;
	public static TFModifier previousSecondaryMod = TFModifier.NONE;
	private static TFPotency potency = TFPotency.MINOR_BOOST;
	private static int limit = 0;
	private static String outputName = "";

	
	public static List<ItemEffect> getEffects() {
		return effects;
	}
	
	public static List<ItemEffect> getDefaultEffects(AbstractCoreItem item) {
		List<ItemEffect> defaultEffects = new ArrayList<>();
		
		if(item instanceof AbstractClothing) {
			AbstractClothing selectedClothing = (AbstractClothing)item;
			defaultEffects = new ArrayList<>(selectedClothing.getClothingType().getEffects());
			
		} else if(item instanceof AbstractWeapon) {
			AbstractWeapon selectedWeapon = (AbstractWeapon)item;
			AbstractWeapon defaultWeapon = Main.game.getItemGen().generateWeapon(selectedWeapon.getWeaponType(), selectedWeapon.getDamageType());
			defaultEffects = new ArrayList<>(defaultWeapon.getEffects());
		}
		
		return defaultEffects;
	}
	
	public static List<ItemEffect> getEffectsPlusDefaults() {
		List<ItemEffect> effectsPlusDefaults = new ArrayList<>(getDefaultEffects(getSelectedItem()));

		for(ItemEffect ie : effects) {
			if(!effectsPlusDefaults.contains(ie)) {
				effectsPlusDefaults.add(ie);
			}
		}
		
		return effectsPlusDefaults;
	}
	
	public static TFModifier getPrimaryMod() {
		return primaryMod;
	}

	public static void setPrimaryMod(TFModifier primaryMod) {
		LilayaDressingRoomDialogue.primaryMod = primaryMod;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static TFModifier getSecondaryMod() {
		return secondaryMod;
	}

	public static void setSecondaryMod(TFModifier secondaryMod) {
		LilayaDressingRoomDialogue.secondaryMod = secondaryMod;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static TFPotency getPotency() {
		return potency;
	}

	public static void setPotency(TFPotency potency) {
		LilayaDressingRoomDialogue.potency = potency;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static int getLimit() {
		return limit;
	}

	public static void setLimit(int limit) {
		LilayaDressingRoomDialogue.limit = limit;
		if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
			Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
			LilayaDressingRoomDialogue.setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		}
	}

	public static String getOutputName() {
		return outputName;
	}
	
	public static void setOutputName(String outputName) {
		// Handle parsing:
		outputName = outputName.replaceAll("\\[\\#(.*?)]", ""); // 移除游戏解析
		LilayaDressingRoomDialogue.outputName = outputName;
	}
	
	public static void resetEnchantmentVariables() {
		LilayaDressingRoomDialogue.primaryMod = TFModifier.NONE;
		LilayaDressingRoomDialogue.secondaryMod = TFModifier.NONE;
		LilayaDressingRoomDialogue.potency = TFPotency.MINOR_BOOST;
		LilayaDressingRoomDialogue.limit = 0;
	}
	
	public static boolean addEffect(ItemEffect effect) {
		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
		
		boolean added = false;
		
		if(effects.size()<getSelectedItem().getEnchantmentLimit()) {
			added = getEffects().add(effect);
			
			if(added) {
				if(defaultName) {
					setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
				} else {
					if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
						Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
						setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
					}
				}
			}
		}
		
		return added;
	}
	
	public static boolean removeEffect(int index) {
		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
		getEffects().remove(index);

		if(defaultName) {
			setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
		} else {
			if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
				Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
				setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
			}
		}
		
		return true;
	}
	
	public static boolean removeEffect(ItemEffect effect) {
		boolean defaultName = EnchantingUtils.getPotionName(getSelectedItem(), getEffects()).equalsIgnoreCase(getOutputName());
		boolean removed = getEffects().remove(effect);
		
		if(removed) {
			if(defaultName) {
				setOutputName(EnchantingUtils.getPotionName(getSelectedItem(), getEffects()));
			} else {
				if(Main.game.getCurrentDialogueNode().equals(OUTFIT_EDITOR_ITEM_ENCHANT)) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('output_name').value;");
					setOutputName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
				}
			}
		}
		
		return removed;
	}

	public static void initModifiers() {
		effects = new ArrayList<>(getSelectedItem().getEffects());
		
		if(!getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).contains(primaryMod)) {
			primaryMod = getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).get(0);
		}
		if(!getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).contains(secondaryMod)) {
			secondaryMod = getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).get(0);
		}
		if(!getSelectedItem().getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(potency)) {
			potency = TFPotency.MINOR_BOOST;
		}
		if(limit <= getSelectedItem().getEnchantmentEffect().getLimits(primaryMod, secondaryMod)) {
			limit = getSelectedItem().getEnchantmentEffect().getLimits(primaryMod, secondaryMod);
		}
	}
	
	private static String getEnchantmentUI() {
		StringBuilder inventorySB = new StringBuilder();
		
		ItemEffect effect = getCurrentEffect();
		
		int displaySlots = Math.max(32, 8*(int)Math.ceil(
				Math.max(getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).size(), getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).size())/8f));
		
		// Primary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem())) {
			inventorySB.append("<div class='modifier-icon' style='width:11.5%; background-color:"+tfMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = displaySlots; i > getSelectedItem().getEnchantmentEffect().getPrimaryModifiers(getSelectedItem()).size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
				+ "<h5 style='margin:0; padding:0;'>首要修饰词</h5>"
				+ "</div>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(primaryMod != null) {
			inventorySB.append("<div class='modifier-icon' style='width:100%; margin:0;background-color:"+primaryMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+primaryMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_PRIMARY_ENCHANTING'></div>"
					+ "</div>");
			
		} else {
			inventorySB.append("<div class='modifier-icon empty' style='width:30%; margin:0 1%;'>"
					+ "<div class='overlay' style='cursor:default;' id='MOD_PRIMARY_ENCHANTING'></div>"
					+ "</div>");
		}
		inventorySB.append("</div></div>");
		
		inventorySB.append("</div>");
		
		
		// Secondary mods:
		inventorySB.append("<div class='container-half-width' style='padding-bottom:0;'>");
		for (TFModifier tfMod : getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod)) {
			inventorySB.append("<div class='modifier-icon' style='width:11.5%; background-color:"+tfMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+tfMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_"+tfMod.hashCode()+"'></div>"
					+ "</div>");
		}
		for (int i = displaySlots; i > getSelectedItem().getEnchantmentEffect().getSecondaryModifiers(getSelectedItem(), primaryMod).size(); i--) {
			inventorySB.append("<div class='modifier-icon empty' style='width:11.5%;'></div>");
		}
		
		inventorySB.append("<div class='container-full-width'>"
				+ "<div class='container-half-width' style='width:18%; margin:0 1%;'>");
		if(secondaryMod != null) {
			inventorySB.append("<div class='modifier-icon' style='width:100%; margin:0; background-color:"+secondaryMod.getRarity().getBackgroundColour().toWebHexString()+";'>"
					+ "<div class='modifier-icon-content'>"+secondaryMod.getSVGString()+"</div>"
					+ "<div class='overlay' id='MOD_SECONDARY_ENCHANTING'></div>"
					+ "</div>");
			
		} else {
			inventorySB.append("<div class='modifier-icon empty' style='width:30%; margin:0 1%;'>"
					+ "<div class='overlay' style='cursor:default;' id='MOD_SECONDARY_ENCHANTING'></div>"
					+ "</div>");
		}
		inventorySB.append("</div>"
				+ "<div class='container-half-width' style='width:78%; margin:0 1%; text-align:center; line-height:100vh;'>"
					+ "<h5 style='margin:0; padding:0;'>次要修饰词</h5>"
				+ "</div>"
				+ "</div>");
		
		inventorySB.append("</div>");

		
		// Potency:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");
		
		for(TFPotency potency : TFPotency.getAllPotencies()) {
			inventorySB.append("<div class='normal-button"
									+(getSelectedItem().getEnchantmentEffect().getPotencyModifiers(primaryMod, secondaryMod).contains(potency)?"":" disabled")
									+(LilayaDressingRoomDialogue.potency==potency?" selected":"")+"'"
								+ " id='POTENCY_"+potency+"'"
								+ " style='"+(LilayaDressingRoomDialogue.potency==potency?"color:"+potency.getColour().toWebHexString()+";":"")+" margin:0 1%; width:14%;'>"+potency.getName()+"</div>");
		}
		
		inventorySB.append("</div>");

		// Limits:
		int ingredientLimit = getSelectedItem().getEnchantmentEffect().getLimits(primaryMod, secondaryMod);
		if(ingredientLimit!=0) {
			inventorySB.append(
					"<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_MINIMUM' style='width:100%;'>最低极限</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_DECREASE_LARGE' style='width:100%;'>极限--</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == 0?" disabled":"")+"' id='LIMIT_DECREASE' style='width:100%;'>极限-</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_INCREASE' style='width:100%;'>极限+</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_INCREASE_LARGE' style='width:100%;'>极限++</div>"
						+ "</div>"
						+ "<div style='float:left; width:14.6%; margin:0 1%; padding:0;'>"
							+ "<div class='normal-button"+(limit == ingredientLimit?" disabled":"")+"' id='LIMIT_MAXIMUM' style='width:100%;'>最高极限</div>"
						+ "</div>"
					+ "</div>");
		}
		
		// Effect:
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");

			inventorySB.append("<div class='container-half-width' style='width:28%; margin:0 1%;'>"
									+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>要添加的效果：</b>"
								+ "</div>");
		
			inventorySB.append("<div class='container-half-width' style='width:48%; margin:0 1%;'>");
				if(effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())!=null) {
					int i=0;
					for(String s : effect.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
						if(i>0) {
							inventorySB.append("<br/>");
						}
						inventorySB.append("<b>"+Util.capitaliseSentence(s)+"</b>");
						i++;
					}
				} else {
					inventorySB.append("<b>-</b>");
				}

				// Append enchantment capacity cost for weapons/clothing/tattoos
				if(Main.game.isEnchantmentCapacityEnabled()) {
					if((getSelectedItem() instanceof AbstractClothing)
							|| (getSelectedItem() instanceof AbstractWeapon)
							|| (getSelectedItem() instanceof Tattoo)) {
						
						
						if(effect.getItemEffectType()==ItemEffectType.CLOTHING
								|| effect.getItemEffectType()==ItemEffectType.WEAPON
								|| effect.getItemEffectType()==ItemEffectType.TATTOO) {
							if(effect.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE || effect.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
								int cost = Math.max(0, effect.getPotency().getClothingBonusValue());
								if(effect.getSecondaryModifier()==TFModifier.FERTILITY
										|| effect.getSecondaryModifier()==TFModifier.VIRILITY) {
									cost = 0;
								} else if(effect.getSecondaryModifier()==TFModifier.CORRUPTION) {
									if(effect.getPotency().isNegative()) {
										cost = Math.abs(effect.getPotency().getClothingBonusValue());
									} else {
										cost = 0;
									}
								}
								inventorySB.append("<br/>"
										+ (cost>0
												?"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+")]："+UtilText.formatAsEnchantmentCapacity(cost, "b")
												:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+"：[style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(cost, "b")+")]"));
							}
						}
					}
				}
			inventorySB.append("</div>");
			
			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%;'>");
				if(effects.size() >= getSelectedItem().getEnchantmentLimit()
						|| getSelectedItem().getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer())==null
						|| getSelectedItem().getEnchantmentEffect().getEffectsDescription(primaryMod, secondaryMod, potency, limit, Main.game.getPlayer(), Main.game.getPlayer()).isEmpty()
						|| getEnchantmentEffectBlockedReason(effect)!=null) {
					inventorySB.append(
							"<div class='normal-button disabled' style='width:100%; margin:auto 0;'>"
							+ "<b>添加</b>| "
							+ (getSelectedItem() instanceof Tattoo
									?UtilText.formatAsMoneyUncoloured(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect)*EnchantingUtils.FLAME_COST_MODIFER, "b")
									:UtilText.formatAsEssencesUncoloured(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect), "b", false))
							+ "<div class='overlay no-pointer' id='ENCHANT_ADD_BUTTON_DISABLED'></div>"
							+ "</div>");
					
				} else {
					inventorySB.append(
							"<div class='normal-button' style='width:100%; margin:auto 0;'>"
							+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>添加</b>| "
									+ (getSelectedItem() instanceof Tattoo
											?UtilText.formatAsMoney(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect)*EnchantingUtils.FLAME_COST_MODIFER, "b")
											:UtilText.formatAsEssences(EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), effect), "b", false))
							+ "<div class='overlay' id='ENCHANT_ADD_BUTTON'></div>"
							+ "</div>");
					
				}
			inventorySB.append("</div>");
		
		inventorySB.append("</div>");
		
		
		// Item crafting:
		// The costing UI differs here from the standard enchanting UI, as the costs for the enchantment need to all be shown at all times
		inventorySB.append("<div class='container-full-width' style='text-align:center; padding:8px 0; margin-top:0;'>");
		
			inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
				inventorySB.append("<b>输入</b>"
						+ "<div class='enchanting-ingredient' style='background-color:"+getSelectedItem().getRarity().getBackgroundColour().toWebHexString()+";'>"
						+ "<div class='enchanting-ingredient-content'>"+getSelectedItem().getSVGString()+"</div>"
						+ "<div class='overlay' id='INGREDIENT_ENCHANTING'  style='cursor:default;'></div>"
						+ "</div>");
			inventorySB.append("</div>");

			// Effects:
			inventorySB.append("<div class='container-half-width' style='width:58%; margin:0 1%;'>");

			//style='text-align:center;float:left; width:80%; padding:0; margin:0 0 5% 5%;'
				inventorySB.append("<form style='padding:0; margin:0 0 4px 0; float:left; width:90%; text-align:center;'>");
					inventorySB.append("<input type='text' id='output_name' value='" +UtilText.parseForHTMLDisplay(outputName)+"' style='padding:0;margin:0;width:100%;text-align:center;'>");
				inventorySB.append("</form>");
				inventorySB.append("<div class='normal-button' id='apply_enchanted_item_name' style='float:left; width:9.5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>");
					inventorySB.append("&#10003;");
				inventorySB.append("</div>");
				
				
				List<ItemEffect> defaultEffects = getDefaultEffects(getSelectedItem());
				int totalCost = 0;
				for(ItemEffect ie : effects) {
					if(!defaultEffects.contains(ie)) {
						totalCost += EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), ie);
					}
				}
				
				inventorySB.append("<b>效果(</b>"
									+ (effects.size()>=getSelectedItem().getEnchantmentLimit()?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>":"<b>")+""
											+ effects.size()+"/"+getSelectedItem().getEnchantmentLimit()+"</b><b>)</b>| 总成本："
												+ (getSelectedItem() instanceof Tattoo
														?UtilText.formatAsMoney(EnchantingUtils.getCost(getSelectedItem(), effects)*EnchantingUtils.FLAME_COST_MODIFER, "b")
														:UtilText.formatAsEssences(totalCost,  "b", false)
//															(getSelectedItem() instanceof AbstractClothing
//																?UtilText.formatAsEssences(EnchantingUtils.getCost(Main.game.getItemGen().generateClothing(((AbstractClothing)getSelectedItem()).getClothingType(), false), effects), "b", false)
//																:UtilText.formatAsEssences(EnchantingUtils.getCost(Main.game.getItemGen().generateWeapon(((AbstractWeapon)getSelectedItem()).getWeaponType()), effects), "b", false))
														// UtilText.formatAsEssences(EnchantingUtils.getCost(getSelectedItem(), effects), "b", false)
														)
								);
			
				if(effects.isEmpty()) {
					inventorySB.append("<br/><span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>无效果添加</span>");
				} else {
					if(!Collections.disjoint(effects, defaultEffects)) {
						inventorySB.append("<br/>[style.italicsMinorGood(默认效果由"+UtilText.getEssenceSymbol(PresetColour.TEXT_GREY)+"[style.boldDisabled('D')]标记，且免费提供。)]");
					}
					int capcityCost = 0;
					
					int it = 0;
					for(ItemEffect ie : effects) {
						boolean isDefaultEffect = defaultEffects.contains(ie);
						if(ie.getItemEffectType()==ItemEffectType.CLOTHING
								|| ie.getItemEffectType()==ItemEffectType.WEAPON
								|| ie.getItemEffectType()==ItemEffectType.TATTOO) {
							if(ie.getPrimaryModifier()==TFModifier.CLOTHING_ATTRIBUTE
									|| ie.getPrimaryModifier()==TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
								if(ie.getSecondaryModifier()==TFModifier.FERTILITY
										|| ie.getSecondaryModifier()==TFModifier.VIRILITY) {
									capcityCost += 0;
								} else if(ie.getSecondaryModifier()==TFModifier.CORRUPTION) {
									if(ie.getPotency().isNegative()) {
										capcityCost += Math.abs(ie.getPotency().getClothingBonusValue());
									}
								} else {
									capcityCost += Math.max(0, ie.getPotency().getClothingBonusValue());
								}
							}
						}
						
						int i=0;
						for(String s : ie.getEffectsDescription(Main.game.getPlayer(), Main.game.getPlayer())) {
							inventorySB.append(
									"<div class='container-full-width'"
											+ " style='background:"+RenderingEngine.getEntryBackgroundColour(it%2==0)+"; width:98%; margin:0 1%; padding:0;'>");

								inventorySB.append("<div style='width:calc(100% - 94px); line-height:17px; margin:0; padding:0; float:left;'>");
//									if(isDefaultEffect) {
//										inventorySB.append("<div style='position:absolute; left:-18px;'>"
//													+ "<i>[style.boldDisabled(D)]</i>"
//												+ "</div>");
//									}
									inventorySB.append(Util.capitaliseSentence(s));
								inventorySB.append("</div>");
								if(i==0) {
									// Show cost for adding this effect:
									inventorySB.append("<div style='width:64px; line-height:17px; margin:0; padding:0 0 0 4px; float:left;'>");
										int essenceCost = EnchantingUtils.getModifierEffectCost(true, getSelectedItem(), ie);
										if(isDefaultEffect) {
											if(effects.contains(ie)) {
												essenceCost = 0;
											} else {
												essenceCost = EnchantingUtils.getModifierEffectCost(false, getSelectedItem(), ie);
											}
										}
										if(essenceCost==0) {
											if(isDefaultEffect) {
												inventorySB.append(UtilText.getEssenceSymbol(PresetColour.TEXT_GREY));
												inventorySB.append("[style.boldDisabled(D)]");
											} else {
												inventorySB.append(UtilText.formatAsEssencesUncoloured(essenceCost, "b", false));
											}
										} else {
											inventorySB.append(UtilText.formatAsEssences(essenceCost, "b", false));
										}
									inventorySB.append("</div>");
									
//									if(getSelectedItem().getEffects().contains(ie)) {
										inventorySB.append(
												"<div class='normal-button' style='width:17px; height:17px; line-height:17px; font-size:14px; margin:0; padding:0 0 0 4px; float:left; text-align:left;'>"
													+ "<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>X</b>"
													+ "<div class='overlay' id='DELETE_EFFECT_"+it+"'></div>"
												+ "</div>");
										
//									} else {
//										inventorySB.append(
//												"<div class='normal-button' id='DELETE_EFFECT_"+it+"' style='width:17px; height:17px; line-height:17px; font-size:14px; margin:0; padding:0; float:right; color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"
//													+ "<b>X</b>"
//												+ "</div>");
//									}
								}
							inventorySB.append("</div>");
							i++;
						}
						it++;
					}
					
					if(Main.game.isEnchantmentCapacityEnabled()) {
						if((getSelectedItem() instanceof AbstractClothing)
								|| (getSelectedItem() instanceof AbstractWeapon)
								|| (getSelectedItem() instanceof Tattoo)) {
							inventorySB.append("<br/>"
									+ (capcityCost>0
											?"[style.colourEnchantment("+Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+")]："+UtilText.formatAsEnchantmentCapacity(capcityCost, "b")
											:Util.capitaliseSentence(Attribute.ENCHANTMENT_LIMIT.getName())+"：[style.colourDisabled("+UtilText.formatAsEnchantmentCapacityUncoloured(capcityCost, "b")+")]"));
						}
					}
				}
			inventorySB.append("</div>");
			
			
			AbstractCoreItem preview = null;
			if(getSelectedItem() instanceof AbstractItem) {
				preview = EnchantingUtils.craftItem(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());

			} else if(getSelectedItem() instanceof AbstractClothing) {
				preview = EnchantingUtils.craftClothing(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());

			} else if(getSelectedItem() instanceof AbstractWeapon) {
				preview = EnchantingUtils.craftWeapon(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());

			}  else if(getSelectedItem() instanceof Tattoo) {
				preview = EnchantingUtils.craftTattoo(getSelectedItem(), LilayaDressingRoomDialogue.getEffects());
			}
			
			if(preview!=null) {
				inventorySB.append("<div class='container-half-width' style='width:18%; margin:0 1%; text-align:center;'>");
					inventorySB.append("<b>结果</b>");
					inventorySB.append("<div class='enchanting-ingredient' style='background-color:"+preview.getRarity().getBackgroundColour().toWebHexString()+";'>");
						inventorySB.append("<div class='enchanting-ingredient-content'>"+preview.getSVGString()+"</div>");
						inventorySB.append("<div class='overlay' id='OUTPUT_ENCHANTING' style='cursor:default;'></div>");
					inventorySB.append("</div>");
				inventorySB.append("</div>");
			}
		inventorySB.append("</div>");
		inventorySB.append("<p id='hiddenPField' style='display:none;'></p>");
		
		
		return inventorySB.toString();
	}

	public static ItemEffect getCurrentEffect() {
		return new ItemEffect(getSelectedItem().getEnchantmentEffect(), primaryMod, secondaryMod, potency, limit);
	}
	
	public static String getEnchantmentEffectBlockedReason(ItemEffect effect) {
		if(getSelectedItem() instanceof AbstractClothing) {
			if(effect.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
				for(ItemEffect ie : effects) {
					if(ie.getSecondaryModifier()==TFModifier.CLOTHING_VIBRATION) {
						return "一件衣服只能添加一个“震动”效果！";
					}
				}
			}
		}
		return null;
	}
	
	
	// Utility methods for tile:
	
	public static void calculateOutfitAvailability() {
		loadedOutfitsAvailabilityFromTile = new HashMap<>();

		for(Entry<String, Outfit> entry : loadedOutfitsMap.entrySet()) {
			loadedOutfitsAvailabilityFromTile.put(entry.getValue(), getOutfitAvailabilityCount(entry.getValue()));
		}
		
//		Map<Outfit, Map<AbstractWeapon, Integer>> outfitToWeaponToRequirementMap = new HashMap<>();
//		Map<Outfit, Map<AbstractClothing, Integer>> outfitToClothingToRequirementMap = new HashMap<>();
//
//		for(Entry<String, Outfit> entry : loadedOutfitsMap.entrySet()) {
//			loadedOutfitsAvailabilityFromTile.put(entry.getValue(), 0);
//			outfitToWeaponToRequirementMap.put(entry.getValue(), new HashMap<>());
//			outfitToClothingToRequirementMap.put(entry.getValue(), new HashMap<>());
//			
//			for(Entry<InventorySlot, AbstractWeapon> e2 : entry.getValue().getWeapons().entrySet()) {
//				outfitToWeaponToRequirementMap.get(entry.getValue()).putIfAbsent(e2.getValue(), 0);
//				outfitToWeaponToRequirementMap.get(entry.getValue()).put(e2.getValue(), outfitToWeaponToRequirementMap.get(entry.getValue()).get(e2.getValue())+1);
//			}
//			
//			for(Entry<InventorySlot, AbstractClothing> e2 : entry.getValue().getClothing().entrySet()) {
//				outfitToClothingToRequirementMap.get(entry.getValue()).putIfAbsent(e2.getValue(), 0);
//				outfitToClothingToRequirementMap.get(entry.getValue()).put(e2.getValue(), outfitToClothingToRequirementMap.get(entry.getValue()).get(e2.getValue())+1);
//			}
//		}
//		
//
//		for(Outfit outfit : outfitToClothingToRequirementMap.keySet()) {
//			int minimumOutfitsCompleted = 9999;
//			for(Entry<AbstractWeapon, Integer> weaponRequirementEntry : outfitToWeaponToRequirementMap.get(outfit).entrySet()) {
//				int maximumOutfitsFromThisWeapon = 0;
//				for(Entry<AbstractWeapon, Integer> weaponPresentEntry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
//					if(weaponPresentEntry.getKey().equals(weaponRequirementEntry.getKey())) {
//						maximumOutfitsFromThisWeapon = weaponPresentEntry.getValue() / weaponRequirementEntry.getValue();
//					}
//				}
//				if(maximumOutfitsFromThisWeapon<minimumOutfitsCompleted) {
////					loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisWeapon);
//					minimumOutfitsCompleted = maximumOutfitsFromThisWeapon;
//				}
//				if(maximumOutfitsFromThisWeapon==0) {
//					break;
//				}
//			}
//			for(Entry<AbstractClothing, Integer> clothingRequirementEntry : outfitToClothingToRequirementMap.get(outfit).entrySet()) {
//				int maximumOutfitsFromThisClothing = 0;
//				for(Entry<AbstractClothing, Integer> clothingPresentEntry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
//					if(clothingPresentEntry.getKey().equals(clothingRequirementEntry.getKey())) {
//						maximumOutfitsFromThisClothing = clothingPresentEntry.getValue() / clothingRequirementEntry.getValue();
//					}
//				}
//				if(maximumOutfitsFromThisClothing<minimumOutfitsCompleted) {
////					loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisClothing);
//					minimumOutfitsCompleted = maximumOutfitsFromThisClothing;
//				}
//				if(maximumOutfitsFromThisClothing==0) {
//					break;
//				}
//			}
//			loadedOutfitsAvailabilityFromTile.put(outfit, minimumOutfitsCompleted);
//		}
		
//		for(Entry<Outfit, Integer> e : loadedOutfitsAvailabilityFromTile.entrySet()) {
//			System.out.println(e.getKey().getName()+": "+e.getValue());
//			System.out.println("###########");
//		}
	}
	

	private static int getOutfitAvailabilityCount(Outfit outfit) {
//		if(outfit.getWeapons().isEmpty() && outfit.getClothing().isEmpty()) {
//			
//		}
		
		Map<Outfit, Map<AbstractWeapon, Integer>> outfitToWeaponToRequirementMap = new HashMap<>();
		Map<Outfit, Map<AbstractClothing, Integer>> outfitToClothingToRequirementMap = new HashMap<>();
		
		outfitToWeaponToRequirementMap.put(outfit, new HashMap<>());
		outfitToClothingToRequirementMap.put(outfit, new HashMap<>());
		
		for(Entry<InventorySlot, AbstractWeapon> e2 : outfit.getWeapons().entrySet()) {
			outfitToWeaponToRequirementMap.get(outfit).putIfAbsent(e2.getValue(), 0);
			outfitToWeaponToRequirementMap.get(outfit).put(e2.getValue(), outfitToWeaponToRequirementMap.get(outfit).get(e2.getValue())+1);
		}
		
		for(Entry<InventorySlot, AbstractClothing> e2 : outfit.getClothing().entrySet()) {
			outfitToClothingToRequirementMap.get(outfit).putIfAbsent(e2.getValue(), 0);
			outfitToClothingToRequirementMap.get(outfit).put(e2.getValue(), outfitToClothingToRequirementMap.get(outfit).get(e2.getValue())+1);
		}
		
		int minimumOutfitsCompleted = 9999;
		for(Entry<AbstractWeapon, Integer> weaponRequirementEntry : outfitToWeaponToRequirementMap.get(outfit).entrySet()) {
			int maximumOutfitsFromThisWeapon = 0;
			for(Entry<AbstractWeapon, Integer> weaponPresentEntry : Main.game.getPlayerCell().getInventory().getAllWeaponsInInventory().entrySet()) {
				if(weaponPresentEntry.getKey().equals(weaponRequirementEntry.getKey())) {
					maximumOutfitsFromThisWeapon = weaponPresentEntry.getValue() / weaponRequirementEntry.getValue();
				}
			}
			if(maximumOutfitsFromThisWeapon<minimumOutfitsCompleted) {
//				loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisWeapon);
				minimumOutfitsCompleted = maximumOutfitsFromThisWeapon;
			}
			if(maximumOutfitsFromThisWeapon==0) {
				break;
			}
		}
		for(Entry<AbstractClothing, Integer> clothingRequirementEntry : outfitToClothingToRequirementMap.get(outfit).entrySet()) {
			int maximumOutfitsFromThisClothing = 0;
			for(Entry<AbstractClothing, Integer> clothingPresentEntry : Main.game.getPlayerCell().getInventory().getAllClothingInInventory().entrySet()) {
				if(clothingPresentEntry.getKey().equals(clothingRequirementEntry.getKey())) {
					maximumOutfitsFromThisClothing = clothingPresentEntry.getValue() / clothingRequirementEntry.getValue();
				}
			}
			if(maximumOutfitsFromThisClothing<minimumOutfitsCompleted) {
//				loadedOutfitsAvailabilityFromTile.put(outfit, maximumOutfitsFromThisClothing);
				minimumOutfitsCompleted = maximumOutfitsFromThisClothing;
			}
			if(maximumOutfitsFromThisClothing==0) {
				break;
			}
		}
		
		return minimumOutfitsCompleted;
	}
	
	/**
	 * @return Integer representing how many instances of the outfit can be constructed from items within the player's current tile.
	 */
	public static int getOutfitAvailabilityFromTile(Outfit outfit) {
		// If this outfit is not in the map, then calculate for just this outfit:
		if(!loadedOutfitsAvailabilityFromTile.containsKey(outfit)) {
			return getOutfitAvailabilityCount(outfit);
		}
		
		try {
			return loadedOutfitsAvailabilityFromTile.get(outfit);
		} catch(Exception ex) {
			System.out.println(outfit.getName());
			ex.printStackTrace();
		}
		return -1;
	}
	
	// Installation dialogue:
	
	public static final DialogueNode INSTALLATION = new DialogueNode("更衣室", "", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "INSTALLATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "你已经把这个房间改造成了更衣室，现在你在考虑是否要修理莉西丝的衣柜……", ROOM_DRESSING_ROOM);
			}
			return null;
		}
	};

	public static final DialogueNode WARDROBE_ACTIVATION = new DialogueNode("更衣室", "", true) {
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/dressingRoom", "WARDROBE_ACTIVATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "莉西丝的衣柜又可以使用了，让你能凭空创造出各种套装。", ROOM_DRESSING_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dressingRoomLyssiethsWardrobeActivated, true);
					}
				};
			}
			return null;
		}
	};
	
}
