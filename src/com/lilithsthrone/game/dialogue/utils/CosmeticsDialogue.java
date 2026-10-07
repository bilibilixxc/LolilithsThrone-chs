package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Holds generic dialogue nodes associated with application of cosmetics, for use in external dialogue files.
 * This is effectively just a generic copy of SuccubisSecrets.java
 * 
 * @since 0.4.2.5
 * @version 0.4.2.5
 * @author Innoxia
 */
public class CosmeticsDialogue {

	public static InventorySlot invSlotTattooToRemove = null;
	
	private static NPC beautician;
	private static DialogueNode returnToNode;
	
	public static void initDialogue(NPC beautician, GameCharacter target, DialogueNode returnToNode) {
		CosmeticsDialogue.beautician = beautician;
		CosmeticsDialogue.returnToNode = returnToNode;
		BodyChanging.setTarget(target);
	}
	
	private static Response getMainResponse(int index) {
		if(index == 1){
			return new ResponseTrade("交易", UtilText.parse(beautician, "询问[npc.name][npc.she]卖什么化妆品或者珠宝。"), beautician);
			
		} else if (index == 2) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_COSMETICS) {
				return new Response("妆容", "你正在查看有什么化妆品……", null);
				
			} else if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				return new Response("妆容", UtilText.parse(beautician, "由于你的身体由"+Main.game.getPlayer().getBodyMaterial().getName()+"构成，[npc.name]无法对你化妆！"), null);
				
			} else {
				return new Response("化妆",
						UtilText.parse(beautician, "[npc.Name]可以涂上各种风格和颜色的口红、指甲油以及其他形式的妆容。"),
						BEAUTICIAN_COSMETICS);
			}
			
		} else if (index == 3) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_HAIR) {
				return new Response("头发", "你正在查看有什么可用的发型……", null);
				
			} else {
				return new Response("发型",
					UtilText.parse(beautician, "[npc.Name]可以给你的[pc.hair]染色、做造型、修剪甚至延长。"),
					BEAUTICIAN_HAIR);
			}

		} else if (index == 4) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_PIERCINGS) {
				return new Response("穿孔", "你正在查看有什么可用的穿孔……", null);
				
			} else {
				return new Response("穿孔",
						UtilText.parse(beautician, "[npc.Name]可以给你的身体各处做穿孔。"),
						BEAUTICIAN_PIERCINGS);
			}

		}  else if (index == 5) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_EYES) {
				return new Response("眼睛", "你正在查看有什么可以对你眼睛进行的调整……", null);
				
			} else {
				return new Response("眼睛",
						UtilText.parse(beautician, "[npc.Name]可以更换你眼睛的颜色，不过这对[npc.her]的灵气要求很高，从而也十分昂贵。"),
						BEAUTICIAN_EYES);
			}

		} else if (index == 6) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_SKIN_COLOUR) {
				return new Response("体表覆盖", "你正在查看有什么可以对你体表进行的调整……", null);
				
			} else {
				return new Response("体表",
						UtilText.parse(beautician, "[npc.Name]可以更改你各种类型体表的颜色，包括皮肤、皮毛、羽毛等。不过这对[npc.her]的灵气要求很高，从而也十分昂贵。"),
						BEAUTICIAN_SKIN_COLOUR){
					@Override
					public void effects() {
						SuccubisSecrets.initCoveringsMap(Main.game.getPlayer());
					}
				};
			}
			
		} else if (index == 7) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_OTHER) {
				return new Response("杂项", "你正在查看有什么可用的杂项服务……", null);
				
			} else {
				return new Response("杂项", UtilText.parse(beautician, "[npc.Name]提供各类杂项服务，例如肛门漂白。"), BEAUTICIAN_OTHER);
			}
			
		} else if (index == 8) {
			if (Main.game.getCurrentDialogueNode()==BEAUTICIAN_TATTOOS) {
				return new Response("纹身", "你正在查看有什么可用的纹身……", null);
				
			} else {
				return new Response("纹身",
						UtilText.parse(beautician, "[npc.Name]可以添加或移除纹身。[npc.her]甚至能添加附魔了奥术的纹身，但看上去不便宜……"),
						BEAUTICIAN_TATTOOS);
			}

		} else if (index == 0) {
			return new Response("返回", "你已经对外表做了足够的调整了。", returnToNode) {
				@Override
				public void effects() {
					Main.game.setResponseTab(0);
				}
			};
			
		} else {
			return null;
		}
	}
	
	private static String getMoneyRemainingString() {
		return "<h6 style='text-align:center;'>"
					+ "你当前拥有[style.moneyFormat([pc.money], span)]"
				+ "</h6>";
	}

	public static final DialogueNode BEAUTICIAN_START = new DialogueNode("化妆品", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	public static final DialogueNode BEAUTICIAN_COSMETICS = new DialogueNode("化妆品", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "腮红", "腮红(也叫胭脂)被用来粉饰脸颊，以显得更加年轻或凸显颧骨。", true, true)
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "口红", "口红被用来为嘴唇提供色彩、质地或保护。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "眼线", "眼线用于眼廓周围，有助于修饰眼型或突出不同的特征。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "眼影", "眼影用来让使用者的眼睛更加凸显迷人。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "指甲油", "指甲油用于给[pc.hands]添加色彩或提供保护。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚趾甲油", "脚趾甲油用于给[pc.feet]添加色彩或提供保护。", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_HAIR = new DialogueNode("头发", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
				CharacterModificationUtils.getKatesDivHairLengths(true, "头发长度", "头发长度决定了你能做的发型种类。头发越长，发型种类就越丰富。")

				+CharacterModificationUtils.getKatesDivHairStyles(true, "发型", "发型是否可用取决于你的头发长度。")
				
				+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getHairType().getRace(), Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering()).getType(),
						"[pc.Hair]颜色", "所有毛发的改变颜色都是永久的，如果你之后又想要改变颜色，那就必须再来找凯特。", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_SKIN_COLOUR = new DialogueNode("体表覆盖", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = Main.game.getPlayer();
				
				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(target, bct, entry.getValue().getValue());
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
						true,
						true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_EYES = new DialogueNode("眼睛", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());

			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Main.game.getPlayer().getEyeType().getRace(), Main.game.getPlayer().getEyeCovering(),
							"虹膜", "虹膜指的是眼睛中染色的部分，负责控制瞳孔的直径和大小。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"瞳孔", "瞳孔是位于虹膜中心的透明物体，以便光线打在视网膜上。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"巩膜", "巩膜是眼睛中虹膜周围的部分(一般是白色)。", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_PIERCINGS = new DialogueNode("穿孔", "-", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPiercings(false));
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_OTHER = new DialogueNode("杂项", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivAnalBleaching()
					
					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair(true, "胡须", "面部可见的体毛。" 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : "女性化角色无法长出面部毛发。"))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair(true, "阴毛", "生殖器附近的体毛；位于性器官和下体附近。")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair(true, "腋毛", "腋窝处的体毛。")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair(true, "肛毛", "肛门附近的体毛。")
							:"")
					);
			
			for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, bct, "体毛", "你的体毛。", true, true));
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_TATTOOS = new DialogueNode("纹身", "-", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoos());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("确认状态：",
						"开启纹身去除确认。"
							+ "启用时，需要点击两次才能移除纹身。"
							+ "关闭时只需要一次点击。",
							BEAUTICIAN_TATTOOS) {
					@Override
					public String getTitle() {
						return "确认状态："+(Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)
									?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>开启</span>"
									:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>关闭</span>");
					}
					
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.tattooRemovalConfirmations, !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations));
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			return getMainResponse(index);
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode BEAUTICIAN_TATTOOS_ADD = new DialogueNode("纹身", "-", true) {
		@Override
		public String getLabel() {
			return "纹身："+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName());
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoosAdd());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("应用("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "你没有足够的钱用来纹纹身！", null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("应用("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "你需要选择纹身类型，添加一些文字或计数指示才能形成完整的纹身！", null);
					
				} else {
					return new Response("应用("+UtilText.formatAsMoney(value, "span")+")", UtilText.parse(beautician, "告诉[npc.name]你想让[npc.herHim]帮你纹上这个纹身。"), BEAUTICIAN_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							Main.game.getPlayer().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("保存/加载", "保存/加载纹身预设。", TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						initTattooSaveLoadDialogue(BEAUTICIAN_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("返回", "取消纹身并回到选择菜单。", BEAUTICIAN_TATTOOS);
			}
			
			return null;
		}
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	// Save/Load screen for tattoos:

	private static DialogueNode returnToNodeFromTattooSaveLoad;
	private static Map<String, Tattoo> loadedTattoosMap;
	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";

	public static void initTattooSaveLoadDialogue(DialogueNode returnToNodeFromTattooSaveLoad) {
		Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
		CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
		CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
		CosmeticsDialogue.returnToNodeFromTattooSaveLoad = returnToNodeFromTattooSaveLoad;
	}
	
	public static DialogueNode getReturnToNodeFromTattooSaveLoad() {
		return returnToNodeFromTattooSaveLoad;
	}

	public static void initSaveLoadMenu() {
		loadedTattoosMap = new TreeMap<>();
		
		for(File f : getSavedTattoos()) {
			try {
				String name = Util.getFileIdentifier(f);
				Tattoo loadedTattoo = loadTattoo(name);
				loadedTattoosMap.put(name, loadedTattoo);
			} catch(Exception ex) {
			}
		}
	}
	
	public static Map<String, Tattoo> getLoadedTattoosMap() {
		return loadedTattoosMap;
	}

	public static final DialogueNode TATTOO_SAVE_LOAD = new DialogueNode("保存纹身文件", "", true) {
		@Override
		public void applyPreParsingEffects() {
			initSaveLoadMenu();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0 0 8px 0;'>"
							+ "只有标准字符(字母和数字)才能用于保存文件名。"
							+ "<br/>悬停在纹身图标上以查看要保存/读取的详细信息。"
							+(!Main.game.isInNewWorld()
								?"<br/>若名称为[style.colourBad(红色)]，则代表由于与选择的身体部位不兼容而无法读取该纹身，或由于纹身拥有你目前无法获取的特殊效果！"
								:"<br/>若名称为[style.colourBad(红色)]，则代表由于与选择的身体部位不兼容而无法读取该纹身。")
					+ "</div>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(75% - 16px); text-align:center; background:transparent;'>"
							+ "名字"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "读档 | 存档 | 删除"
						+ "</div>"
					+ "</div>");

			int i=0;
			
			saveLoadSB.append(getSaveLoadRow(null, null, i%2==0));
			i++;
			
			for(Entry<String, Tattoo> entry : loadedTattoosMap.entrySet()){
				saveLoadSB.append(getSaveLoadRow(entry.getKey(), entry.getValue(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("确认状态：",
						"在点击读取、覆写或删除纹身时会进入确认状态。"
							+ "启用时，需要进行两次点击才能使行为生效。"
							+ "关闭时只需要一次点击。",
						TATTOO_SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "再次确认："+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
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

			} else if (index == 0) {
				return new Response("返回", "返回纹身菜单。", returnToNodeFromTattooSaveLoad);
			}
			
			return null;
		}
	};
	
	public static List<File> getSavedTattoos() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/tattoos");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getSaveLoadRow(String baseName, Tattoo loadedTattoo, boolean altColour) {
		if(loadedTattoo!=null){
			String fileName = (baseName+".xml");
			
			boolean suitableSlot = loadedTattoo.getType().getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot);
			boolean specialEffectsLimitation = !Main.game.isInNewWorld()
					&& (loadedTattoo.getCounter()!=null || loadedTattoo.isGlowing() || (loadedTattoo.getWriting()!=null && loadedTattoo.getWriting().isGlow()));
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+" position:relative;'>"
						
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
						
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ loadedTattoo.getSVGString()
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_TATTOO_" + baseName + "'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+ "<h6 style='margin:0; padding:2px;'>"+(!suitableSlot || specialEffectsLimitation?"[style.boldBad("+loadedTattoo.getName()+")]":loadedTattoo.getName())+"</h6>"
								+ "<p style='margin:0; padding:2px;'>[style.colourDisabled(data/tattoos/)]"+baseName+"[style.colourDisabled(.xml)]</p>"
							+"</div>"
							
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.game.isStarted() && !Main.game.isInCombat() && !Main.game.isInSex()
									?(fileName.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (suitableSlot && !specialEffectsLimitation
									? (fileName.equals(loadConfirmationName)
										?"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadDisabled()+"</div></div>")
	
	
							+ (fileName.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else { //TODO hmmmmmmm (also enchantment _CURRENT)
			String svgString = CharacterModificationUtils.tattoo.getSVGString();
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
					
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ svgString
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_TATTOO_CURRENT'></div>"//TODO
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' placeholder='Enter File Name' style='padding:0;margin:0;width:100%;'></form>"
							+"</div>"
							
						+ "</div>"
					
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
						+ "</div>"
					+ "</div>";
				
		}
	}

	public static void saveTattoo(String name, boolean allowOverwrite, DialogueNode dialogueNode) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/tattoos");
		dir.mkdir();

		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(name+".xml")){
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
			
			Element tattooCoreElement = doc.createElement("tattooSave");
			
			doc.appendChild(tattooCoreElement);
			
			Element tattooElement = CharacterModificationUtils.tattoo.saveAsXML(tattooCoreElement, doc);
			
			// Do not save item effects:
			if(tattooElement.getElementsByTagName("effects").item(0)!=null) {
				tattooElement.removeChild(tattooElement.getElementsByTagName("effects").item(0));
			}
			
			
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
			
			String saveLocation = "data/tattoos/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		if(dialogueNode!=null) {
			Main.game.setContent(new Response("", "", dialogueNode));
		}
		Main.game.flashMessage(PresetColour.GENERIC_GOOD, "纹身已保存！");
	}

	public static Tattoo loadTattoo(String name) {
		if (isLoadTattooAvailable(name)) {
			File file = new File("data/tattoos/"+name+".xml");

			if (file.exists()) {
				try {
					Document doc = DocBuilders.parseDoc(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					Element rootElement = (Element) doc.getElementsByTagName("tattooSave").item(0);
					
					return Tattoo.loadFromXML((Element) rootElement.getElementsByTagName("tattoo").item(0), doc);
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadTattooAvailable(String name) {
		File file = new File("data/tattoos/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static void deleteTattoo(String name) {
		File file = new File("data/tattoos/"+name+".xml");

		if (file.exists()) {
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
	
}
