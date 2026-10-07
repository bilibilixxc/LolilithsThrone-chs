package com.lilithsthrone.controller;

import com.lilithsthrone.LolificationProject.ModCommon.Initializers;

import com.lilithsthrone.LolificationProject.ModCommon.ModProps;
import com.lilithsthrone.controller.eventListeners.tooltips.TooltipInformationEventListener;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.companions.OccupantSortingMethod;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaMilkingRoomDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.MilkingRoom;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceUpgrade;
import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.world.places.PlaceUpgrade;
import org.w3c.dom.events.EventTarget;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class OccupantController {
	public static void initRoomManagerListeners() {
		for (Cell c : OccupantManagementDialogue.getImportantCells()) {
			if (MainController.document.getElementById(c.getId()+"_PRESENT_DISABLED") != null) {
				MainController.addTooltipListeners(c.getId()+"_PRESENT_DISABLED", new TooltipInformationEventListener().setInformation("管理房间", "你无法管理该房间！"));
			} else if (MainController.document.getElementById(c.getId()+"_DISABLED") != null) {
				MainController.addTooltipListeners(c.getId()+"_DISABLED", new TooltipInformationEventListener().setInformation("管理房间", "你无法管理该房间！"));
			} else if (MainController.document.getElementById(c.getId()+"_PRESENT") != null) {
				((EventTarget) MainController.document.getElementById(c.getId()+"_PRESENT")).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = c;
						}
						
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.ROOM_UPGRADES;
						}
					});
				}, false);
				MainController.addTooltipListeners(c.getId()+"_PRESENT", new TooltipInformationEventListener().setInformation("管理房间", "打开该房间的管理界面。"));
			} else if (MainController.document.getElementById(c.getId()) != null) {
				((EventTarget) MainController.document.getElementById(c.getId())).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect = c;
						}
						
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.ROOM_UPGRADES;
						}
					});
				}, false);
				MainController.addTooltipListeners(c.getId(), new TooltipInformationEventListener().setInformation("管理房间", "打开该房间的管理界面。"));
			}
		}
	}
	
	public static void initRoomUpgradesListeners() {
		String id;
		for (AbstractPlaceUpgrade placeUpgrade : PlaceUpgrade.getAllPlaceUpgrades()) {
			id = "ROOM_MOD_INFO_"+PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade);
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("", (OccupantManagementDialogue.cellToInspect.getPlace().getPlaceUpgrades().contains(placeUpgrade)
								?placeUpgrade.getDescriptionAfterPurchase()
								:placeUpgrade.getDescriptionForPurchase())));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(
							new Response("",
									"",
									placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect) == null
											?Main.game.getCurrentDialogueNode()
											:placeUpgrade.getInstallationDialogue(OccupantManagementDialogue.cellToInspect)) {
								@Override
								public void effects() {
									OccupantManagementDialogue.cellToInspect.addPlaceUpgrade(placeUpgrade);
									Main.game.getPlayer().incrementMoney(-placeUpgrade.getInstallCost());
								}
							});
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("购入改造",
								"将会花费:"+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_BUY_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("购入改造",
								"将会花费:"+UtilText.formatAsMoney(placeUpgrade.getInstallCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.cellToInspect.removePlaceUpgrade(placeUpgrade);
							Main.game.getPlayer().incrementMoney(-placeUpgrade.getRemovalCost());
						}
					});
				}, false);
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("移除改造",
								"将会花费:"+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade)));
			}
			id = PlaceUpgrade.getIdFromPlaceUpgrade(placeUpgrade)+"_SELL_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id,
						new TooltipInformationEventListener().setInformation("移除改造",
								(!placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getKey()
										?placeUpgrade.getRemovalAvailability(OccupantManagementDialogue.cellToInspect).getValue()
										:"将会花费:"+UtilText.formatAsMoney(placeUpgrade.getRemovalCost())
										+"<br/>"+OccupantManagementDialogue.getPurchaseAvailabilityTooltipText(OccupantManagementDialogue.cellToInspect, placeUpgrade))));
			}
		}
		
		id = "rename_room_button";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				boolean unsuitableName = false;
				if (Main.mainController.getWebEngine().executeScript("document.getElementById('nameInput')") != null) {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('nameInput').value;");
					if (Main.mainController.getWebEngine().getDocument() != null) {
						unsuitableName = Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()<1
								|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length()>32;
					}
					if (!unsuitableName) {
						Main.game.setContent(new Response("重命名房间", "将该房间重命名为你输入在文本栏的名字。", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								OccupantManagementDialogue.cellToInspect.getPlace().setName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
							}
						});
					}
				}
			}, false);
		}
	}
	
	static void fluidHandler(MilkingRoom room, FluidStored fluid) {
		String idModifier = "MILK";
		if (fluid.isCum()) {
			idModifier = "CUM";
		} else if (fluid.isGirlCum()) {
			idModifier = "GIRLCUM";
		}
		GameCharacter fluidOwner;
		try {
			fluidOwner = fluid.getFluidCharacter();
		} catch (Exception e1) {
			fluidOwner = null;
		}
		
		String fluidName = fluid.getFluid().getName(fluidOwner);
		
		Map<CoverableArea, SexAreaOrifice> areas = Util.newHashMapOfValues(
				new Util.Value<>(CoverableArea.MOUTH, SexAreaOrifice.MOUTH),
				new Util.Value<>(CoverableArea.VAGINA, SexAreaOrifice.VAGINA),
				new Util.Value<>(CoverableArea.ANUS, SexAreaOrifice.ANUS));
		
		for (Entry<CoverableArea, SexAreaOrifice> area : areas.entrySet()) {
			String id = idModifier+"_"+area.getKey()+"_"+fluid.hashCode();
			if (MainController.document.getElementById(id) != null) {
				float milkAmount = Math.min(fluid.getMillilitres(), MilkingRoom.INGESTION_AMOUNT);
				boolean canIngest = room.isAbleToIngestThroughArea(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount);
				
				String fluidOwnerName = fluidOwner == null
						?""
						:(fluidOwner.equals(MilkingRoom.getTargetedCharacter())
						?UtilText.parse(fluidOwner, "[npc.her]自己的")
						:UtilText.parse(fluidOwner, "[npc.namePos]的"));
				
				if (canIngest) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getTextEndStringBuilder().append("<p>");
						if (MilkingRoom.getTargetedCharacter().isPlayer()) {
							switch (area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											fluidOwnerName+fluidName+"罐体旁还有无人使用的软管，你抓起一根，取下末端的吸纳装置，拧上了一个漏斗零件。"
													+"你将软管的末端缓缓引导至[pc.ass+]旁，迫不及待地将漏斗口塞进了[pc.asshole+]。"
													+"你将罐体边的开关从“吸收”改为“泵出”，便按下了启动键。你感觉到"+fluidName+"一点一点灌进你的[pc.asshole]，不禁发出一声愉悦的[pc.moan]。"));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											fluidOwnerName+fluidName+"罐体旁还有无人使用的软管，你抓起一根，取下末端的吸纳装置，拧上了一个吸管样子的零件。"
													+"你将吸管伸到嘴边，迫不及待地塞进了你[pc.lips+]之间。"
													+"你将罐体边的拉杆从“吸收”改为“泵出”，便按下了启动键。你大口大口地吞咽着"+fluidName+"，不禁发出愉悦的[pc.moan]。"));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											fluidOwnerName+fluidName+"罐体旁还有无人使用的软管，你抓起一根，取下末端的吸纳装置，拧上了一个漏斗零件。"
													+"接着将软管末端引向#IF(pc.hasLegs())你[pc.legs+]之间#ELSE你的下体#ENDIF，毫不犹豫地将漏斗口塞进了[pc.pussy+]中。"
													+"随后将罐体边的开关从“吸收”改为“泵出”，便按下了启动键。你感觉到"+fluidName+"一点一点灌进"
													+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?"你的[pc.pussy]，不禁发出一声愉悦的[pc.moan]。"
													:"等待已久的子宫中，不禁发出一声愉悦的[pc.moan]。")));
									break;
								default:
									break;
							}
						} else {
							switch (area.getKey()) {
								case ANUS:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"你想把[npc.namePos][npc.ass+]里灌满"+fluidOwnerName+fluidName+"，于是就指示[npc.herHim]"
													+(!MilkingRoom.getTargetedCharacter().isTaur() && MilkingRoom.getTargetedCharacter().getGenitalArrangement() == GenitalArrangement.NORMAL
													?"在你身前弯下腰。"
													:"跪下来，将身后展示给你。")
													+"等到[npc.asshole+]完全展露在眼前，你抓起一根罐体旁还有无人使用的软管，"
													+"取下末端的吸纳装置，拧上了一个漏斗零件。"
													+"</p>"
													+"<p>"
													+"你将软管的末端缓缓引导至[npc.namePos][npc.ass+]旁，迫不及待地将漏斗口塞进了[npc.asshole+]中，听到一声[npc.a_moan+]后，你不禁露出了微笑。"
													+"随后将罐体边的拉杆从“吸收”改为“泵出”，便按下了启动键。"
													+"[npc.Name]感觉到"+fluidName+"被泵入[npc.asshole]中，反而发出更加愉悦的[npc.moans]声来。"));
									break;
								case MOUTH:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"你想让[npc.namePos]尝一尝"+fluidOwnerName+fluidName+"，于是指示[npc.herHim]跪在你面前。"
													+"[npc.she]才刚刚服从了命令，你便抓起一根罐体旁还有无人使用的软管，"
													+"取下末端的吸纳装置，拧上了一个吸管样子的零件。"
													+"</p>"
													+"你将吸管伸到[npc.namePos]的嘴边，迫不及待地塞进了[npc.lips+]之间，告诉[npc.herHim]要准备好美餐一顿了。"
													+"随后将罐体边的开关从“吸收”改为“泵出”，便按下了启动键。[npc.Name]贪婪地吞咽着"+fluidName+"，愉悦的[pc.moan]从口中漏出。"));
									break;
								case VAGINA:
									Main.game.getTextEndStringBuilder().append(UtilText.parse(MilkingRoom.getTargetedCharacter(),
											"你想把[npc.namePos][npc.pussy+]里灌满"+fluidOwnerName+fluidName+"，于是就指示[npc.herHim]"
													+(!MilkingRoom.getTargetedCharacter().isTaur()
													&& MilkingRoom.getTargetedCharacter().hasLegs()
													&& MilkingRoom.getTargetedCharacter().getGenitalArrangement() == GenitalArrangement.NORMAL
													?"坐在附近的椅子上张开[npc.legs]。"
													:"跪下来，将身后展示给你。")
													+"[npc.she]才刚刚服从了命令，[npc.pussy+]完全展露在眼前，你便抓起一根罐体旁还有无人使用的软管，"
													+"取下末端的吸纳装置，拧上了一个漏斗零件。"
													+"</p>"
													+"<p>"
													+"你将软管的末端缓缓引导至[npc.namePos][npc.labia+]旁，迫不及待地将漏斗口塞进了[npc.pussy+]中，听到一声[npc.a_moan+]后，你不禁露出了微笑。"
													+"随后将罐体边的拉杆从“吸收”改为“泵出”，便按下了启动键。"
													+"[npc.Name]感觉到"+fluidName+"被泵入"
													+(MilkingRoom.getTargetedCharacter().isVisiblyPregnant()
													?"[npc.pussy]中，反而发出更加愉悦的[npc.moans]声来。"
													:"[npc.her]等待已久的子宫中，反而发出更加愉悦的[npc.moans]声来。")));
									break;
								default:
									break;
							}
						}
						
						String ingestion;
//						try {
//							GameCharacter c = fluid.getFluidCharacter();
//							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(c, fluid.getBody(), fluid.getFluid(), area.getValue(), milkAmount);
//						} catch (Exception e1) {
							ingestion = MilkingRoom.getTargetedCharacter().ingestFluid(fluid, area.getValue(), milkAmount);
//						}
						if (!ingestion.isEmpty()) {
							Main.game.getTextEndStringBuilder().append("</p>"
									+"<p>"
									+ingestion);
						}
						Main.game.getTextEndStringBuilder().append("</p>");
						Main.game.getTextEndStringBuilder().append(
								"<p style='text-align:center;'>"
										+"<i style='color:"+PresetColour.GENERIC_MINOR_BAD.toWebHexString()+";'>"+fluidOwnerName+"的"+fluidName+"消耗了"+Units.fluid(milkAmount)+"！</i>"
										+"</p>");
						
						room.incrementFluidStored(fluid, -milkAmount);
						
						Main.game.setContent(new Response("", "", LilayaMilkingRoomDialogue.INGEST));
						
					}, false);
				}
				String verb = "饮用";
				String description;
				
				if (MilkingRoom.getTargetedCharacter().isPlayer()) {
					description = "饮用"+Units.fluid(milkAmount)+fluidName+"。";
					if (area.getKey() != CoverableArea.MOUTH) {
						verb = "榨取";
						description = "将"+Units.fluid(milkAmount)+fluidName+"灌入你的"+area.getKey().getName()+"。";
					}
					
				} else {
					description = UtilText.parse(MilkingRoom.getTargetedCharacter(), "让[npc.name]饮用")+Units.fluid(milkAmount)+fluidName+"。";
					if (area.getKey() != CoverableArea.MOUTH) {
						verb = "榨取";
						description = "将"+Units.fluid(milkAmount)+fluidName+"灌入"+UtilText.parse(MilkingRoom.getTargetedCharacter(), "[npc.namePos]的")+area.getKey().getName()+"。";
					}
				}
				
				TooltipInformationEventListener el;
				if (canIngest) {
					el = new TooltipInformationEventListener().setInformation(verb+"("+Units.fluid(milkAmount)+")",
							description);
				} else {
					el = new TooltipInformationEventListener().setInformation(verb+"("+Units.fluid(milkAmount)+")",
							room.getAreaIngestionBlockedDescription(fluid.getFluid().getType().getBaseType(), MilkingRoom.getTargetedCharacter(), area.getKey(), milkAmount));
				}
				MainController.addTooltipListeners(id, el);
			}
		}
		
		String id = idModifier+"_SELL_"+fluid.hashCode();
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				int income = Math.max(1, (int) (fluid.getFluid().getValuePerMl()*fluid.getMillilitres()));
				if (Main.getProperties().hasValue(PropertyValue.applyMoneyMultToIncome))
					income = Math.round((ModProps.moneyMultiplier / 100f) * income);
				Main.game.getPlayer().incrementMoney(income);
				room.getFluidsStored().remove(fluid);
				Main.game.getTextEndStringBuilder().append("<p style='text-align:center;'>你将"+fluidName+"卖出了"+(UtilText.formatAsMoney(income, "span"))+"！</p>");
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id,
					new TooltipInformationEventListener().setInformation("出售", "出售所有"+fluidName+"以换取"+(Math.max(1, (int) (fluid.getFluid().getValuePerMl()*fluid.getMillilitres())))+"火币。"));
		}
		Initializers.initFluidAmountListeners();
	}
	
	public static void initOverviewListeners() {
		for (int i = 6; i>=0; i--) {
			String id = "SLAVE_DAY_"+i;
			if (MainController.document.getElementById(id) != null) {
				int finalI = i; // Lambda requirement
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					OccupantManagementDialogue.setDayNumber(Main.game.getDayNumber()-finalI);
					Main.game.setContent(new Response("重命名", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
	}
	
	public static void initSlaveJobListeners() {
		String id;
		
		// Copy & paste settings:
		// Schedule:
		id = "copySlaveJobSchedule";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				CompanionManagement.copyJobSchedule();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("复制工作安排",
					UtilText.parse(CompanionManagement.characterSelected(),
							"复制[npc.namePos]的工作安排，使得你能够将完全相同的工作安排粘贴到其他奴隶的工作管理界面上。")));
		}
		id = "pasteSlaveJobSchedule";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if(CompanionManagement.isJobSchedulePasteAvailable()) {
					boolean fullyPasted = CompanionManagement.pasteJobSchedule();
					if(fullyPasted) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					} else {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()), PresetColour.GENERIC_BAD, "部分工作无法粘贴！");
					}
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("粘贴工作安排",
					UtilText.parse(CompanionManagement.characterSelected(),
							CompanionManagement.isJobSchedulePasteAvailable()
								?"粘贴当前复制的工作安排，使得[npc.name]的工作安排与复制的完全相同。"
								:"你尚未复制一份工作安排，所以无法粘贴替换[npc.namePos]的……")));
		}
		// Job settings:
		id = "copySlaveJobSettings";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				CompanionManagement.copyJobSettings();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("复制工作设置",
					UtilText.parse(CompanionManagement.characterSelected(),
							"复制[npc.namePos]的工作设置，使得你能够将完全相同的工作设置粘贴到其他奴隶的工作管理界面上。")));
		}
		id = "pasteSlaveJobSettings";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if(CompanionManagement.isJobSettingsPasteAvailable()) {
					CompanionManagement.pasteJobSettings();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("粘贴工作设置",
					UtilText.parse(CompanionManagement.characterSelected(),
							CompanionManagement.isJobSettingsPasteAvailable()
								?"粘贴当前复制的工作设置，使得[npc.name]的工作设置与复制的完全相同。"
								:"你尚未复制一组工作设置，所以无法粘贴替换[npc.namePos]的……")));
		}
		
		// Job hours:
		for (int i = 0; i<24; i++) {
			id = i+"_WORK";
			if (MainController.document.getElementById(id) != null) {
				int finalI = i;
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
					if (Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(finalI) == job) {
						Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(finalI, SlaveJob.IDLE);
					} else {
						Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(finalI, job);
					}
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
			} else {
				id = i+"_WORK_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"[style.colourBad(时间栏位不可用)]",
							Main.game.getDialogueFlags().getSlaveryManagerJobSelected().getAvailabilityText(i, Main.game.getDialogueFlags().getManagementCompanion())));
				}
			}
		}
		for (SlaveJobHours preset : SlaveJobHours.values()) {
			id = preset+"_TIME";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (preset == SlaveJobHours.NONE) {
						for (int hour = 0; hour<24; hour++) {
							SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
							if (Main.game.getDialogueFlags().getManagementCompanion().getSlaveJob(hour) == job) {
								Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(hour, SlaveJob.IDLE);
							}
						}
					} else {
						for (int hour = preset.getStartHour(); hour<preset.getStartHour()+preset.getLength(); hour++) {
							int appliedHour = hour%24;
							SlaveJob job = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
							if (job.isAvailable(appliedHour, Main.game.getDialogueFlags().getManagementCompanion())) {
								Main.game.getDialogueFlags().getManagementCompanion().setSlaveJob(appliedHour, job);
							}
						}
					}
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("设置预设工作时间", preset.getDescription()));
			}
			
			id = preset+"_TIME_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						"[style.colourBad(设置预设工作时间)]",
						"你无法安排这些时间。或是该工作无法在这些时间进行，或是工作已经太繁重。"));
			}
		}
		
		// Jobs:
		for (SlaveJob job : SlaveJob.values()) {
			id = "SLAVE_JOB_INFO_"+job;
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion()));
			}
			
			id = job+"_ASSIGN";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.getDialogueFlags().setSlaveryManagerJobSelected(job);
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setSlaveJob(job, Main.game.getDialogueFlags().getManagementCompanion()));
			}
			
			for (SlaveJobSetting setting : job.getMutualSettings()) {
				id = job+setting.toString()+"_ADD";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+"：</b>"
									+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
							setting.getDescription()
									+"[style.italicsMinorGood(点击后应用该许可。)]"));
				}
				
				id = job.toString()+setting+"_REMOVE";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+"：</b>"
									+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
							setting.getDescription()
									+"[style.italicsMinorBad(点击后移除该许可。)]"));
				}
			}
			
			for (Map.Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
				for (SlaveJobSetting setting : entry.getValue()) {
					id = setting+"_TOGGLE_ADD";
					if (MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
							for (SlaveJobSetting settingRem : entry.getValue()) {
								Main.game.getDialogueFlags().getManagementCompanion().removeSlaveJobSettings(job, settingRem);
							}
							Main.game.getDialogueFlags().getManagementCompanion().addSlaveJobSettings(job, setting);
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
								"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+"：</b>"
										+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
								setting.getDescription()
										+"[style.italicsMinorGood(点击后应用该许可。)]"));
					}
					
					id = setting+"_DISABLED";
					if (MainController.document.getElementById(id) != null) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
								"<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(Main.game.getDialogueFlags().getManagementCompanion()))+"：</b>"
										+"<b style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</b>",
								setting.getDescription()
										+"[style.italicsMinorBad(你无法移除这一类中的许可。只能选择另一种许可。)]"));
					}
				}
			}
		}
	}
	
	public static void initSlavePermissionsListeners() {
		// Copy & paste settings:
		// Permissions:
		String id = "copyPermissions";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				CompanionManagement.copyPermissions();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("复制权限",
					UtilText.parse(CompanionManagement.characterSelected(),
							"复制[npc.namePos]的权限，使得你能够将完全相同的权限粘贴到其他奴隶的权限管理界面上。")));
		}
		id = "pastePermissions";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				if(CompanionManagement.isPermissionsPasteAvailable()) {
					CompanionManagement.pastePermissions();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("粘贴权限",
					UtilText.parse(CompanionManagement.characterSelected(),
							CompanionManagement.isPermissionsPasteAvailable()
								?"粘贴当前复制的权限，使得[npc.name]的权限与复制的完全相同。"
								:"你尚未复制一组权限许可，所以无法粘贴替换[npc.namePos]的……")));
		}
		
		// Permissions:
		for (SlavePermission permission : SlavePermission.values()) {
			for (SlavePermissionSetting setting : permission.getSettings()) {
				id = setting+"_ADD";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().addSlavePermissionSetting(permission, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b>"+setting.getName(),
							setting.getDescription()
									+"[style.italicsMinorGood(点击后应用该许可。)]"
									+(permission.isMutuallyExclusiveSettings()
									?"[style.italicsMinorBad(该类只能同时启用一种许可。)]"
									:""),
							91 + (setting.getAdditionalDescriptionLines()*16)));
				}
				
				id = setting+"_REMOVE";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.getDialogueFlags().getManagementCompanion().removeSlavePermissionSetting(permission, setting);
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(Main.game.getDialogueFlags().getManagementCompanion())));
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b>"+setting.getName(),
							setting.getDescription()
									+"[style.italicsMinorBad(点击后移除该许可。)]",
							91 + (setting.getAdditionalDescriptionLines()*16)));
				}
				
				id = setting+"_REMOVE_ME";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
							"<b style='color:"+permission.getColour().toWebHexString()+";'>"+permission.getName()+":</b>"+setting.getName(),
							setting.getDescription()
									+" [style.italicsMinorBad(你无法移除这一类中的许可。只能选择另一种许可。)]",
							91 + (setting.getAdditionalDescriptionLines()*16)));
				}
			}
		}
	}
	
	public static void initOccupantListListeners() {
		String id;
		// Sorting
		for (OccupantSortingMethod osm : OccupantSortingMethod.values()) {
			id = "SORT_SLAVES_BY_"+osm;
			if (MainController.document.getElementById(id) != null) {
				String friendlyName = Util.capitaliseSentence(osm.getName());
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							OccupantManagementDialogue.setSlavesSortedBy(osm);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
						osm==OccupantSortingMethod.NONE
							?"未排序"
							:"按"+friendlyName+"排序",
						osm.getSortingDescription(),
						18*2));
			}
		}
		
		// Sort ascending
		id = "SORT_SLAVES_ASC";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						OccupantManagementDialogue.setSlavesAreInReverseOrder(false);
					}
				});
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"升序",
					""));
		}
		
		// Sort Descending
		id = "SORT_SLAVES_DESC";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						OccupantManagementDialogue.setSlavesAreInReverseOrder(true);
					}
				});
			}, false);
			MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation(
					"降序",
					""));
		}
		
		for (String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			id = slaveId;
			NPC nslave = null;
			
			try {
				if (Main.game.isCharacterExisting(slaveId))
					nslave = (NPC) Main.game.getNPCById(slaveId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, getSlavesOwned", slaveId);
				continue;
			}
			NPC slave = nslave;

			if (slave != null) { // slave shouldn't be null...
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("检查奴隶",
							UtilText.parse(slave, "检查[npc.name]。"),
							18));
				}
				
				id = slaveId+"_JOB";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(slave)) {
							@Override
							public boolean isIgnoreContentScroll() {
								return true;
							}
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理奴隶工作",
							UtilText.parse(slave, "设置[npc.namePos]的工作和工作时间。"),
							18));
				}
				
				id = slaveId+"_PERMISSIONS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlavePermissionsDialogue(slave)) {
							@Override
							public boolean isIgnoreContentScroll() {
								return true;
							}
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理奴隶许可",
							UtilText.parse(slave, "管理[npc.namePos]的许可。"),
							18));
				}
				
				id = slaveId+"_INVENTORY";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
						Main.mainController.openInventory(slave, InventoryInteraction.FULL_MANAGEMENT);
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理奴隶物品栏",
							UtilText.parse(slave, "管理[npc.namePos]的物品栏。"),
							18));
				}
				
				id = slaveId+"_TRANSFER";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								slave.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
								if(!slave.isAtWork()
										|| slave.getLocationPlaceType().equals(PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION)
										|| slave.getWorldLocation().equals(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"))) {
									slave.returnToHome();
								}
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("将奴隶移动至此",
							UtilText.parse(slave, "将[npc.name]移动至你所在的位置。"),
							18));
				}
				
				id = slaveId+"_TRANSFER_DISABLED_FULL";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("将奴隶移动至此",
							UtilText.parse(slave, "你无法将[npc.name]移动至该处，因为这里没有[npc.herHim]能待的房间。"),
							18*2));
				}
				id = slaveId+"_TRANSFER_DISABLED_INAPPPROPRIATE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("将奴隶移动至此",
							UtilText.parse(slave,
									"你无法将[npc.name]移动至该处，因为此类房间不适合[npc.herHim]……"
									+(slave.isDoll()
										?"<br/><i>玩偶只能用玩偶贮藏室作为生活地块……</i>"
										:"<br/><i>奴隶只能用奴隶房作为生活地块……</i>")
									),
							18*3));
				}
				id = slaveId+"_TRANSFER_DISABLED_ALREADY_HERE";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("将奴隶移动至此",
							UtilText.parse(slave, "你无法将[npc.name]移动至该处，因为[npc.sheIs]已经把这个房间当做[npc.her]的家了。"),
							18*2));
				}
				
				id = slaveId+"_SELL";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						if (slave.isAbleToBeSold()) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney((long) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()));
									Main.game.getDialogueFlags().getSlaveTrader().addSlave(slave);
									slave.setLocation(Main.game.getDialogueFlags().getSlaveTrader().getWorldLocation(), Main.game.getDialogueFlags().getSlaveTrader().getLocation(), true);
								}
							});
						}
					}, false);
					
					if(slave.isAbleToBeSold()) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("出售奴隶",
								UtilText.parse(slave,
										"[npc.Name]值"+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)
											+"<br/>"
											+"然而"+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+"只会出"
											+UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getBuyModifier()), "b", PresetColour.GENERIC_ARCANE)+"。")));
					} else {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("出售奴隶",
								UtilText.parse(slave,"[npc.Name]无法出售！"),
								18));
					}
				}
				
				id = slaveId+"_SELL_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("出售奴隶",
							UtilText.parse(slave,
									slave.isAbleToBeSold()
											?"你无法出售[npc.name]，因为没有人买。"
											:"[npc.Name]无法出售！"),
							slave.isAbleToBeSold()
								?18*2
								:18));
				}
				
				id = slaveId+"_COSMETICS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(slave)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								BodyChanging.setTarget(slave);
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("去找凯特",
							UtilText.parse(slave, "将[npc.name]送去凯特的美容沙龙“魅魔的秘密”改变外观。"),
							18*2));
				}
				
				id = slaveId+"_COSMETICS_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("去找凯特",
							UtilText.parse(slave, "你还没见过凯特！"),
							18));
				}
			}
		}
		
		for (String occupantId : Main.game.getPlayer().getFriendlyOccupants()) {
			id = occupantId;
			NPC noccupant = null;
			
			try {
				if (Main.game.isCharacterExisting(occupantId))
					noccupant = (NPC) Main.game.getNPCById(occupantId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, getFriendlyOccupants", occupantId);
				continue;
			}
			NPC occupant = noccupant;
			
			if (occupant != null) { // It shouldn't equal null...
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(occupant)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("检查角色",
							UtilText.parse(occupant, "检查[npc.name]。"),
							18));
				}
				
				id = occupantId+"_JOB";
				if (MainController.document.getElementById(id) != null) {
					if(occupant.hasJob()) {
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理职业",
								UtilText.parse(occupant, "[npc.name]已经有固定工作了，所以不能分配在宅邸里工作……"),
								18*2));
						
					} else {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveJobsDialogue(occupant)) {
								@Override
								public void effects() {
									CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
									Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								}
							});
						}, false);
						MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理职业",
								UtilText.parse(occupant, "安排[npc.name]一些临时工作。"),
								18));
					}
				}
				
				id = occupantId+"_PERMISSIONS";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理许可",
							"你无法管理一位自由住户的许可。",
							18*2));
				}
				
				id = occupantId+"_INVENTORY";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
//						Main.game.getDialogueFlags().setManagementCompanion(occupant);
						CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
						Main.mainController.openInventory(occupant, InventoryInteraction.FULL_MANAGEMENT);
						
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理物品栏",
							UtilText.parse(occupant, "管理[npc.namePos]的物品栏。"),
							18));
				}
				
				id = occupantId+"_TRANSFER";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								occupant.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
								occupant.returnToHome();
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("移动至此",
							UtilText.parse(occupant, "将[npc.name]移动至你所在的位置。"),
							18));
				}
				
				id = occupantId+"_TRANSFER_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("移动至此",
							UtilText.parse(occupant, "你无法将[npc.name]移动至该处，因为这里没有[npc.herHim]能待的房间。"),
							18*2));
				}
				
				id = occupantId+"_COSMETICS";
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementSlaveCosmeticsDialogue(occupant)) {
							@Override
							public void effects() {
								CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), occupant);
								Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
								BodyChanging.setTarget(occupant);
							}
						});
					}, false);
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("去找凯特",
							UtilText.parse(occupant, "将[npc.name]送去凯特的美容沙龙“魅魔的秘密”改变外观。"),
							18*2));
				}
				
				id = occupantId+"_COSMETICS_DISABLED";
				if (MainController.document.getElementById(id) != null) {
					MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("去找凯特",
							"你还没见过凯特！",
							18));
				}
			}
		}
	}
	
	public static void initSlaveTraderListeners() {
		for (String slaveId : Main.game.getDialogueFlags().getSlaveTrader().getSlavesOwned()) {
			String id = slaveId+"_TRADER";
			NPC nslave = null;
			try {
				if (Main.game.isCharacterExisting(slaveId))
					nslave = (NPC) Main.game.getNPCById(slaveId);
			} catch (Exception e) {
				Util.logGetNpcByIdError("OccupantController.initOccupantListListeners, TRADER.", slaveId);
				continue;
			}
			NPC slave = nslave;
			if (slave == null) continue;
			
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", CompanionManagement.getSlaveryManagementInspectSlaveDialogue(slave)) {
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getCurrentDialogueNode(), CompanionManagement.getDefaultResponseTab(), slave);
							Main.game.setResponseTab(CompanionManagement.getDefaultResponseTab());
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("检查奴隶",
						UtilText.parse(slave, "对[npc.name]仔细检查一番。")));
			}
			
			id = slaveId+"_TRADER_JOB";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理奴隶工作",
						UtilText.parse(slave, "你无法管理[npc.namePos]的工作，因为你不是[npc.herHim]的主人！")));
			}
			
			id = slaveId+"_TRADER_PERMISSIONS";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理奴隶许可",
						UtilText.parse(slave, "你无法管理[npc.namePos]的许可，因为你不是[npc.herHim]的主人！")));
			}
			
			id = slaveId+"_TRADER_INVENTORY";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("管理奴隶物品栏",
						UtilText.parse(slave, "你无法管理[npc.namePos]的物品栏，因为你不是[npc.herHim]的主人！")));
			}
			
			id = slaveId+"_TRADER_TRANSFER";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("将奴隶移动至此",
						UtilText.parse(slave, "你无法将[npc.namePos]移动至该处，因为你不是[npc.herHim]的主人，而且[npc.sheIs]已经在这了！")));
			}
			
			id = slaveId+"_BUY";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-(int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)));
							Main.game.getPlayer().addSlave(slave);
							slave.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
						}
					});
				}, false);
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("购买奴隶",
						UtilText.parse(slave, "[npc.Name]值"+UtilText.formatAsMoney(slave.getValueAsSlave(true), "b", PresetColour.GENERIC_GOOD)+"<br/>"
								+"然而"+Main.game.getDialogueFlags().getSlaveTrader().getName(true)+"却要[npc.herHim]卖"
								+UtilText.formatAsMoney((int) (slave.getValueAsSlave(true)*Main.game.getDialogueFlags().getSlaveTrader().getSellModifier(null)), "b", PresetColour.GENERIC_ARCANE)+"。")));
			}
			
			id = slaveId+"_BUY_DISABLED";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("购买奴隶",
						UtilText.parse(slave, "你无法购买[npc.name]，因为你没有足够的钱！")));
			}
			
			id = slaveId+"_TRADER_COSMETICS";
			if (MainController.document.getElementById(id) != null) {
				MainController.addTooltipListeners(id, new TooltipInformationEventListener().setInformation("去找凯特",
						UtilText.parse(slave, "你不能把自己未拥有的奴隶送到凯特那里！")));
			}
		}
	}
}
