package com.lilithsthrone.game.dialogue.companions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.controller.MainController;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevel;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.Scarlett;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectTimer;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobFlag;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobHours;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermission;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.5.1
 * @version 0.4.10.8
 * @author Innoxia
 */
public class CompanionManagement {

	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	
	private static DialogueNode coreNode;
	private static int defaultResponseTab;
	
	private static SlaveJob[] savedJobSchedule = null;
	private static Map<SlaveJob, Set<SlaveJobSetting>> savedJobSettings = null;
	private static Map<SlavePermission, Set<SlavePermissionSetting>> savedPermissions = null;
	
	// Saved job schedule:
	
	public static boolean isJobSchedulePasteAvailable() {
		return savedJobSchedule!=null;
	}
	
	public static void copyJobSchedule() {
		savedJobSchedule = new SlaveJob[24];
		for(int i=0; i<savedJobSchedule.length; i++) {
			savedJobSchedule[i] = characterSelected().getSlaveJob(i);
		}
	}

	/**
	 * @return true if all jobs were successfully pasted, false if some were unavailable.
	 */
	public static boolean pasteJobSchedule() {
		boolean fullSuccess = true;
		characterSelected().setSlaveJob24Hours(SlaveJob.IDLE);
		
		for(int i=0; i<savedJobSchedule.length; i++) {
			SlaveJob jobAtHour = savedJobSchedule[i];
			float stamina = characterSelected().getDailySlaveJobStamina();
			
			if(jobAtHour.isAvailable(i, characterSelected())
					&& !jobAtHour.isHidden(characterSelected())
					&& (characterSelected().isSlave()
							// If not a slave, don't work while wanting to sleep or if stamina is depleted
							|| (stamina-jobAtHour.getHourlyStaminaDrain(characterSelected())+characterSelected().getSlaveJob(i).getHourlyStaminaDrain(characterSelected())>=0f
								&& !characterSelected().isSleepingAtHour(i)
								&& jobAtHour.hasFlag(SlaveJobFlag.GUEST_CAN_WORK)))) {
				characterSelected().setSlaveJob(i, jobAtHour);
			} else {
				fullSuccess = false;
			}
		}
		
		return fullSuccess;
	}
	
	// Saved job settings:
	
	public static boolean isJobSettingsPasteAvailable() {
		return savedJobSettings!=null;
	}
	
	public static void copyJobSettings() {
		savedJobSettings = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			savedJobSettings.putIfAbsent(job, new HashSet<>());
			for(SlaveJobSetting jobSetting : characterSelected().getSlaveJobSettings(job)) {
				savedJobSettings.get(job).add(jobSetting);
			}
		}
	}
	
	public static void pasteJobSettings() {
		for(SlaveJob job : SlaveJob.values()) {
			characterSelected().clearSlaveJobSettings(job);
			for(SlaveJobSetting jobSetting : savedJobSettings.get(job)) {
				characterSelected().addSlaveJobSettings(job, jobSetting);
			}
		}
	}

	// Saved permissions:

	public static boolean isPermissionsPasteAvailable() {
		return savedPermissions!=null;
	}
	
	public static void copyPermissions() {
		savedPermissions = new HashMap<>();
		for(Entry<SlavePermission, Set<SlavePermissionSetting>> entry : characterSelected().getSlavePermissionSettings().entrySet()) {
			savedPermissions.put(entry.getKey(), new HashSet<>());
			for(SlavePermissionSetting setting : entry.getValue()) {
				savedPermissions.get(entry.getKey()).add(setting);
			}
		}
	}
	
	public static void pastePermissions() {
		characterSelected().replaceSlavePermissionSettings(savedPermissions);
	}
	
	public static DialogueNode getCoreNode() {
		return coreNode;
	}

	public static int getDefaultResponseTab() {
		return defaultResponseTab;
	}

	public static void initManagement(DialogueNode coreNode, int defaultResponseTab, NPC targetedCharacter) {
		if(Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.NORMAL) {
			Main.game.saveDialogueNode();
		}
		CompanionManagement.coreNode = coreNode;
		CompanionManagement.defaultResponseTab = defaultResponseTab;
		Main.game.getDialogueFlags().setManagementCompanion(targetedCharacter);
		
		// If job is not available for guests, change it to IDLE:
		if(targetedCharacter!=null
				&& !targetedCharacter.isSlave()
				&& !Main.game.getDialogueFlags().getSlaveryManagerJobSelected().hasFlag(SlaveJobFlag.GUEST_CAN_WORK)) {
			Main.game.getDialogueFlags().setSlaveryManagerJobSelected(SlaveJob.IDLE);
		}
	}

	public static DialogueNode getSlaveryManagementInspectSlaveDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_INSPECT;
	}
	
	public static DialogueNode getSlaveryManagementSlaveJobsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_JOBS;
	}
	
	public static DialogueNode getSlaveryManagementSlavePermissionsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_PERMISSIONS;
	}
	
	public static DialogueNode getSlaveryManagementSlaveCosmeticsDialogue(NPC slave) {
		Main.game.getDialogueFlags().setManagementCompanion(slave);
		CharactersPresentDialogue.resetContent(slave);
//		coreNode = Main.game.getCurrentDialogueNode();
		return CompanionManagement.SLAVE_MANAGEMENT_COSMETICS_MAKEUP;
	}
	
	public static NPC characterSelected() {
		return Main.game.getDialogueFlags().getManagementCompanion();
	}
	
	private static String getSlaveInformationHeader(NPC character) {
		StringBuilder headerSB = new StringBuilder();
		AffectionLevel affection = AffectionLevel.getAffectionLevelFromValue(character.getAffection(Main.game.getPlayer()));
		ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
		float affectionChange = character.getDailyAffectionChange();
		float obedienceChange = character.getDailyObedienceChange();
		
		headerSB.append(
				"<div class='container-full-width' style='margin-top:0; text-align:center; border-radius: 5px 0 0 5px;'>"
					// Extra core information:
					+"<div class='container-full-width inner' style='margin-bottom:0;'>"
						+ "<div style='width:30%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "位置"
						+ "</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>好感</b>"
						+"</div>"
						+ "<div style='float:left; width:20%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>服从</b>"
						+"</div>"
						+ "<div style='float:left; width:15%; font-weight:bold; margin:0; padding:0;'>"
							+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>收入</b>"
						+"</div>"
						+ "<div style='width:15%; float:left; font-weight:bold; margin:0; padding:0;'>"
							+ "价值"
						+ "</div>"
					+ "</div>"
					+"<div class='container-full-width inner' style='margin-top:0; border-radius: 0 5px 5px; 0'>"
						+"<div style='width:30%; float:left; margin:0; padding:0;'>"
							+ "<b style='color:"+character.getLocationPlace().getColour().toWebHexString()+";'>"+character.getLocationPlace().getName()+"</b>"
							+",<br/>"
							+ "<span style='color:"+character.getWorldLocation().getColour().toWebHexString()+";'>"+character.getWorldLocation().getName()+"</span>"
						+ "</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+affection.getColour().toWebHexString()+";'>"+character.getAffection(Main.game.getPlayer())+ "</b>"
							+ "<br/><span style='color:"+(affectionChange==0?PresetColour.BASE_GREY:(affectionChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(affectionChange>0?"+":"")
								+decimalFormat.format(affectionChange)+"</span>/天"
							+ "<br/>"
							+ "<span style='color:"+affection.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(affection.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:20%; margin:0; padding:0;'>"
							+ "<b style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+ "</b>"
							+ "<br/><span style='color:"+(obedienceChange==0?PresetColour.BASE_GREY:(obedienceChange>0?PresetColour.GENERIC_GOOD:PresetColour.GENERIC_BAD)).toWebHexString()+";'>"+(obedienceChange>0?"+":"")
								+decimalFormat.format(obedienceChange)+"</span>/天"
							+ "<br/>"
							+ "<span style='color:"+obedience.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(obedience.getName())+"</span>"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(SlaveJob.getFinalDailyIncomeAfterModifiers(character))+"/天"
						+"</div>"
						+ "<div style='float:left; width:15%; margin:0; padding:0;'>"
							+ UtilText.formatAsMoney(character.getValueAsSlave(true))
						+"</div>"
					+ "</div>");

		
		// Job:
		headerSB.append("<div class='container-half-width' style='width:50%; margin:0;'>[style.boldExcellent(职业设置: )]");
		List<String> noPermissions = new ArrayList<>();
		for(SlaveJob job : SlaveJob.values()) {
			if(character.hasSlaveJobAssigned(job)) {
				List<String> permissions = new ArrayList<>();
				if(!character.getSlaveJobSettings(job).isEmpty()) {
					headerSB.append("<br/><b>"+Util.capitaliseSentence(job.getName(character))+":</b>");
					for(SlaveJobSetting setting : character.getSlaveJobSettings(job)) {
						permissions.add("<span style='color:"+setting.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
					}
					headerSB.append(Util.stringsToStringList(permissions, false)+"。");
				} else {
					noPermissions.add(job.getName(character));
				}
			}
		}
		if(!noPermissions.isEmpty()) {
			headerSB.append("<br/><b>"+Util.stringsToStringList(noPermissions, true)+":</b>[style.colourDisabled(n/a)]");
		}
		headerSB.append("</div>");
		
		
		// Permissions:
		if(characterSelected().isSlave()) {
			headerSB.append("<div class='container-half-width' style='width:50%; margin:0;'>[style.boldArcane(常规权限: )]<br/>");
			int permissionCount=0;
			for(SlavePermission permission : SlavePermission.values()) {
				if(permission.isAvailableForCharacter(character)) {
					for(SlavePermissionSetting setting : permission.getSettings()) {
						if(setting.isAvailableForCharacter(character)) {
							if(character.getSlavePermissionSettings().get(permission).contains(setting)) {
								headerSB.append((permissionCount==0?"":", ")+"<span style='color:"+permission.getColour().toWebHexString()+";'>"+setting.getName()+"</span>");
								permissionCount++;
							}
						}
					}
				}
			}
			headerSB.append("。</div>");
		}
		
		
		headerSB.append("</div>");
		
		return headerSB.toString();
	}
	
	private static String getBusyWarning() {
		return "<br/>[style.italicsMinorBad(只能在一个地块的默认对话中使用。)]";
	}
	
	public static Response getManagementResponses(int index) {
		if(coreNode==CharactersPresentDialogue.MENU) { // Companion in the player's party:
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			Collections.sort(charactersPresent, (c1, c2) -> Main.game.getPlayer().hasCompanion(c1)?1:0);
			
			if (index == 0) {
				return new ResponseEffectsOnly("返回", "停止浏览在场角色并回到游戏。"){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
						Main.game.restoreSavedContent(false);
						MainController.updateUI();
					}
				};

			} else if(index==1) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("审视", UtilText.parse(characterSelected(), "你正在仔细打量[npc.name]！"), null);
				}
				return new Response("审视", UtilText.parse(characterSelected(), "仔细打量[npc.name]。"), SLAVE_MANAGEMENT_INSPECT);

			} else if(index==2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("工作", UtilText.parse(characterSelected(), "你正在设定[npc.namePos]的工作和工时！"), null);
				}
				if(!characterSelected().hasJob()) {
					return new Response("工作", "设定该角色的工作和工时。", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("工作", "你不能管理已受雇居民的工作。该选项只能应用于奴隶和无业游民。", null);

			} else if(index==3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("权限", UtilText.parse(characterSelected(), "你已经设置了[npc.namePos]的权限！"), null);
				}
				if(characterSelected().isSlave()) {
					return new Response("权限", "设定该奴隶的工作和工时。", SLAVE_MANAGEMENT_PERMISSIONS);
				}
				return new Response("权限", "你无法管理自由同伴的权限。该选项仅适用于奴隶。", null);
				
			} else if(index==4) {
				if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("物品栏", "你现在正忙得不可开交！"+getBusyWarning(), null);
					
				} else {
					return new ResponseEffectsOnly("物品栏", UtilText.parse(characterSelected(), "管理[npc.namePos]的物品栏。")) {
						@Override
						public void effects() {
							Main.mainController.openInventory((NPC) characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				}
						
			} else if(index==5) {
				return new Response("送到凯特那里", UtilText.parse(characterSelected(), "你不能把队伍成员送到凯特那里！你需要先把[npc.name]送回家……"), null);
				
			} else if (index == 6) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(重置天赋)]",
							UtilText.parse(characterSelected(), "重置[npc.namePos]所有的天赋和特性，返还所有消耗的天赋点。(临时可用，由于天赋树仍在开发中。)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("天赋", UtilText.parse(characterSelected(), "分配[npc.namePos]的天赋点。"), SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected().isDoll() && (Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE) || characterSelected().hasItemType(ItemType.DOLL_CONSOLE))) {
					return new ResponseEffectsOnly("转化", UtilText.parse(characterSelected(), "使用你的D.E.C.K.来定制[npc.namePos]的外貌。")) {
						@Override
						public void effects() {
							ItemEffectType.DOLL_CONSOLE.itemEffectOverride(TFModifier.NONE, TFModifier.NONE, TFPotency.BOOST, 0, Main.game.getPlayer(), characterSelected(), new ItemEffectTimer());
						}
					};
					
				} else if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("转化", characterSelected().getUnableToTransformDescription(), null);
					
				} else if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("转化", "你现在正忙着做别的事！"+getBusyWarning(), null);
					
				} else {
					return new Response("转化",
							UtilText.parse(characterSelected(), "仔细观察[npc.name]会将自己转化成什么样……"),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected());
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("设定名字", UtilText.parse(characterSelected(), "你正在管理[npc.namePos]的名字！"), null);
				}
				 if(!Main.game.isSavedDialogueNeutral()) {
					return new Response("设定名字", "你现在正忙着做别的事！"+getBusyWarning(), null);
				}
				return new Response("设定名字",
						characterSelected().isSlave()
							?UtilText.parse(characterSelected(), "更改[npc.namePos]的名字或让[npc.herHim]以不同的名字称呼你。")
							:UtilText.parse(characterSelected(), "让[npc.name]以不同的名字称呼你。"),
						OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10) {
				if(Main.game.getPlayer().isCaptive()) {
					return new Response(
							characterSelected().isElemental()
								?"驱散"
								:"送回家",
							"因为你是个俘虏，现在不能解散同伴！",
							null);
					
				} else if(!Main.game.isSavedDialogueNeutral()) {
					return new Response(
							characterSelected().isElemental()
								?"驱散"
								:"送回家",
							"你现在正忙着做别的事！"+getBusyWarning(),
							null);
					
				} else {
					if(charactersPresent.size()==1 || (charactersPresent.size()==2 && characterSelected().isElementalSummoned() && !Main.game.getPlayer().isElementalSummoned())) {
						return new ResponseEffectsOnly(
								characterSelected().isElemental()
									?"驱散"
									:"送回家",
									UtilText.parse(characterSelected(), 
										characterSelected().isElemental()
											?"驱散[npc.namePos]的物理形态，让[npc.herHim]转变回奥术灵气。"
											:"让[npc.name]回家。")) {
							@Override
							public void effects() {
								if(characterSelected().isElementalSummoned()) {
									characterSelected().removeCompanion(characterSelected().getElemental());
									characterSelected().getElemental().returnToHome();
								}
								Main.game.getPlayer().removeCompanion(characterSelected());
								characterSelected().returnToHome();
//								Main.mainController.openCharactersPresent();
								Main.game.restoreSavedContent(false);
							}
						};
						
					} else {
						return new Response(
								characterSelected().isElemental()
									?"驱散"
									:"送回家",
									UtilText.parse(characterSelected(), 
										characterSelected().isElemental()
											?"驱散[npc.namePos]的物理形态，让[npc.herHim]转变回奥术灵气。"
											:"让[npc.name]回家。"),
								coreNode){
							@Override
							public void effects() {
								if(characterSelected().isElementalSummoned()) {
									characterSelected().removeCompanion(characterSelected().getElemental());
									characterSelected().getElemental().returnToHome();
								}
								Main.game.getPlayer().removeCompanion(characterSelected());
								characterSelected().returnToHome();
								
								Main.game.setResponseTab(0);
								CharactersPresentDialogue.resetContent(Main.game.getCharactersPresent().get(0));
							}
						};
					}
				}
				
			} else if(index==11) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("战斗动作", UtilText.parse(characterSelected(), "调整[npc.Name]战斗中可使用的动作。"), CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(characterSelected(), coreNode);
						}
					};
				} else {
					return new Response("战斗动作", "你现在正忙着做别的事！"+getBusyWarning(), null);
				}
				
			} else if(index==12) {
				if(Main.game.isSavedDialogueNeutral()) {
					return new Response("法术", UtilText.parse(characterSelected(), "管理[npc.namePos]的法术。"), SpellManagement.CHARACTER_SPELLS_EARTH) {
						@Override
						public void effects() {
							SpellManagement.setSpellOwner(characterSelected(), coreNode);
						}
					};
					
				} else {
					return new Response("法术", "你现在正忙着做别的事！"+getBusyWarning(), null);
				}
				
			} else if(index==13) {
				if(!characterSelected().isElementalSummoned()) {
					return new Response("驱散元素体", UtilText.parse(characterSelected(), "[npc.Name]还没有召唤元素体……"), null);
					
				} else {
					if(!Main.game.isSavedDialogueNeutral()) {
						return new Response("驱散元素体", "你现在正忙着做别的事！"+getBusyWarning(), null);
						
					} else {
						return new Response("驱散元素体", UtilText.parse(characterSelected(), "让[npc.name]驱散[npc.her]的元素体。"), coreNode){
							@Override
							public void effects() {
								characterSelected().getElemental().returnToHome();
								characterSelected().setElementalSummoned(false);
							}
						};
					}
				}
			}
			
		} else if(characterSelected()!=null && characterSelected().isSlave()) { // Slave not currently in the player's party:
			if (index == 1) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("审视", UtilText.parse(characterSelected(), "你正在仔细打量[npc.name]！"), null);
				}
				return new Response("审视", UtilText.parse(characterSelected(), "仔细打量[npc.name]！"), SLAVE_MANAGEMENT_INSPECT);
				
			} else if (index == 2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("工作", UtilText.parse(characterSelected(), "你正在设定[npc.namePos]的工作和工时！"), null);
				}
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("工作", "你不能管理不属于你的奴隶的工作！", null);
				}
				return new Response("工作", "设定该奴隶的工作和工时。", SLAVE_MANAGEMENT_JOBS);
				
			} else if (index == 3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("权限", UtilText.parse(characterSelected(), "你正在设定[npc.namePos]的权限！"), null);
				}
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("权限", "你不能管理不属于你的奴隶的权限！", null);
				}
				return new Response("权限", "设定该奴隶的权限。", SLAVE_MANAGEMENT_PERMISSIONS);
				
			} else if (index == 4) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("物品栏", "你不能管理不属于你的奴隶的物品栏！", null);
				}
				
				if(characterSelected().getOwner().isPlayer()) {
					return new ResponseEffectsOnly("物品栏", UtilText.parse(characterSelected(), "管理[npc.namePos]的物品栏。")){
						@Override
						public void effects() {
							Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
				} else {
					return new Response("物品栏", UtilText.parse(characterSelected(), "你不能管理[npc.namePos]的物品栏，因为你没拥有[npc.herHim]！"), null);
				}
				
			} else if(index == 5) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("送到凯特那里", "你不能把不属于自己的奴隶送到凯特那里！", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					return new Response("送到凯特那里",
							UtilText.parse(characterSelected(), "将[npc.name]送去凯特的美容沙龙“魅魔的秘密”改变外貌。"),
							SLAVE_MANAGEMENT_COSMETICS_MAKEUP) {
								@Override
								public void effects() {
									BodyChanging.setTarget(characterSelected());
								}
							};
				} else {
					return new Response("送到凯特那里", "你还没见过凯特！", null);
				}
				
			} else if (index == 6) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("天赋", "你不能管理不属于自己的奴隶的天赋！", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(重置天赋)]",
							UtilText.parse(characterSelected(), "重置[npc.namePos]所有的天赋和特性，返还所有消耗的天赋点。(临时可用，由于天赋树仍在开发中。)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("天赋", "使用你的奴隶的天赋点。", SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected().isDoll() && (Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE) || characterSelected().hasItemType(ItemType.DOLL_CONSOLE))) {
					return new ResponseEffectsOnly("转化", UtilText.parse(characterSelected(), "使用你的D.E.C.K.来定制[npc.namePos]的外貌。")) {
						@Override
						public void effects() {
							ItemEffectType.DOLL_CONSOLE.itemEffectOverride(TFModifier.NONE, TFModifier.NONE, TFPotency.BOOST, 0, Main.game.getPlayer(), characterSelected(), new ItemEffectTimer());
						}
					};
					
				} else if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("转化", characterSelected().getUnableToTransformDescription(), null);
					
				} else {
					return new Response("转化",
							UtilText.parse(characterSelected(), "仔细观察[npc.name]会将自己转化成什么样……"),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected(), coreNode, defaultResponseTab);
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("设定名字", UtilText.parse(characterSelected(), "你正在管理[npc.namePos]的名字！"), null);
				}
				if(characterSelected() == null) {
					return new Response("设定名字", "你还没有选择角色……", null);
				}
				return new Response("设定名字", UtilText.parse(characterSelected(), "更改[npc.namePos]的名字或让[npc.herHim]以不同的名字称呼你。"), OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
				return new Response("送回家", UtilText.parse(characterSelected(), "[npc.Name]不在你的队伍里，所以你不能把[npc.herHim]送回家……"), null);
				
			} else if(index==11) {
				if(!characterSelected().getOwner().isPlayer()) {
					return new Response("战斗动作", "你不能管理不属于自己的奴隶的战斗动作！", null);
				}
				return new Response("战斗动作", UtilText.parse(characterSelected(), "调整[npc.Name]战斗中可使用的动作。"), CombatMovesSetup.COMBAT_MOVES_CORE) {
					@Override
					public void effects() {
						CombatMovesSetup.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==12) {
				return new Response("法术", UtilText.parse(characterSelected(), "管理[npc.namePos]的法术。"), SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setSpellOwner(characterSelected(), coreNode);
					}
				};
				
			} else if(index==13) {
				if(!characterSelected().isElementalSummoned()) {
					return new Response("驱散元素体", UtilText.parse(characterSelected(), "[npc.Name]还没有召唤元素体……"), null);
					
				} else {
					return new Response("驱散元素体", UtilText.parse(characterSelected(), "让[npc.name]驱散[npc.her]的元素体。"), coreNode) {
						@Override
						public void effects() {
							characterSelected().removeCompanion(characterSelected().getElemental());
							characterSelected().getElemental().returnToHome();
						}
					};
				}
				
			} else if(index==14) {
				if(characterSelected().isDoll()) {
					return new Response("释放",
							UtilText.parse(characterSelected(), "玩偶无法释放……"),
							null);
					
				} else if(!Main.game.getPlayer().hasItemType("innoxia_slavery_freedom_certification")) {
					return new Response("释放",
							UtilText.parse(characterSelected(),
									"你没有自由证明，所以不能释放[npc.name]……"
									+ "<br/><i>自由证明可以向奴隶巷的奴隶管理局里的芬奇购买。</i>"),
							null);
					
				} else {
					if(characterSelected() instanceof Scarlett) {
						return new Response("释放",
								UtilText.parse(characterSelected(),
										"填写一份自由证明来释放[npc.name]！"
										+ "<br/>如果你这么做，[npc.she]无疑会立刻离开并返回海伦娜的巢……"),
								SET_SLAVE_FREE_SCARLETT);
					}
					if(characterSelected() instanceof Brax) {
						return new Response("释放",
								UtilText.parse(characterSelected(),
										"你无法让[npc.name]逃脱……<br/><i>(这将在为布拉克斯添加更多内容时加入！)</i>"),
								null);
					}
					
					String unavailableGuestDescription = "";
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
						unavailableGuestDescription = "[style.italicsMinorBad(由于莉莱雅不允许你接待客人，释放[npc.herHim]之后将无法再邀请[npc.name]住在豪宅中！)]";
						
					} else if(!characterSelected().isAffectionHighEnoughToInviteHome()) {
						unavailableGuestDescription = "[style.italicsBad([npc.Name]还不够喜欢你，不会在被释放后考虑留在你身边！)]";
						
					} else if(!OccupancyUtil.isFreeRoomAvailableForOccupant()) {
						unavailableGuestDescription = "[style.italicsMinorBad(由于你没有空闲的客房供[npc.name]入住，你无法在释放[npc.herHim]后邀请[npc.herHim]留在豪宅中！)]";
					}
					String thanksjava = unavailableGuestDescription;
					
					return new Response("释放",
							UtilText.parse(characterSelected(),
									"填写一份自由证明来释放[npc.name]！"
									+ "<br/>"
									+ (thanksjava.isEmpty()
										?"[style.italicsMinorGood([npc.Name]现在已经足够喜欢你，愿意留下来和你住一起，所以在释放[npc.herHim]之后，你可以让[npc.herHim]住在空闲的客房里！)]"
										:thanksjava+"<br/>[style.italicsBad([npc.herHim](以及[npc.her]物品栏里所有物品)将会从游戏中永久删除！)]")),
							SET_SLAVE_FREE) {
						@Override
						public Colour getHighlightColour() {
							if(thanksjava.isEmpty()) {
								return PresetColour.GENERIC_MINOR_GOOD;
							}
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
					};
				}
				
			} else if(index == 0) {
				if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
					return new Response("返回", "回到友人住客列表界面。", coreNode) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							coreNode = null;
						}
					};
				}
				return new Response("返回",
						characterSelected()==null
							?"返回到上一段对话。"
							:UtilText.parse(characterSelected(),
								characterSelected().isDollStatue()
									?"让[npc.name]静止在原地，继续你的旅程。"
									:(characterSelected().isAsleep()
										?"让[npc.name]继续睡觉……"
										:"告诉[npc.name]你会在其他时间再去找[npc.herHim]。")),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
		
		} else { // Friendly occupant or null character not currently in the player's party:
			if (index == 1) {
				if(characterSelected() == null) {
					return new Response("审视", "你还没选择任何人……", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_INSPECT) {
					return new Response("审视", UtilText.parse(characterSelected(), "你正在仔细打量[npc.name]！"), null);
				}
				return new Response("审视", UtilText.parse(characterSelected(), "仔细打量[npc.name]。"), SLAVE_MANAGEMENT_INSPECT);
				
			} else if (index == 2) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_JOBS) {
					return new Response("工作", UtilText.parse(characterSelected(), "你正在设定[npc.namePos]的工作和工时！"), null);
				}
				if(characterSelected() == null) {
					return new Response("工作", "你还没选择任何人……", null);
				}
				if(!characterSelected().hasJob()) {
					return new Response("工作", "设定友人住客的临时工作和工时。", SLAVE_MANAGEMENT_JOBS);
				}
				return new Response("工作", "该友人住客已有永久工作。", null);
			} else if (index == 3) {
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERMISSIONS) {
					return new Response("权限", UtilText.parse(characterSelected(), "你正在设定[npc.namePos]的权限！"), null);
				}
				if(characterSelected() == null) {
					return new Response("许可", "你还没选择任何人……", null);
				}
				return new Response("权限", "你无法管理自由同伴的权限。该选项仅适用于奴隶。", null);
				
			} else if (index == 4) {
				if(characterSelected() == null) {
					return new Response("物品栏", "你还没选择任何人……", null);
				}
				return new ResponseEffectsOnly("物品栏", UtilText.parse(characterSelected(), "管理[npc.namePos]的物品栏。")){
					@Override
					public void effects() {
						Main.mainController.openInventory(characterSelected(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if(index == 5) {
				if(characterSelected() == null) {
					return new Response("送到凯特那里", "你还没选择任何人……", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kateIntroduced)) {
					return new Response("送到凯特那里",
							UtilText.parse(characterSelected(), "将[npc.name]送去凯特的美容沙龙“魅魔的秘密”改变外貌。"),
							SLAVE_MANAGEMENT_COSMETICS_MAKEUP) {
								@Override
								public void effects() {
									BodyChanging.setTarget(characterSelected());
								}
							};
				} else {
					return new Response("送到凯特那里", "你还没见过凯特！", null);
				}
				
			} else if (index == 6) {
				if(characterSelected() == null) {
					return new Response("天赋", "你还没选择任何人……", null);
				}
				if(Main.game.getCurrentDialogueNode()==SLAVE_MANAGEMENT_PERKS) {
					return new Response("[style.colourMinorBad(重置天赋)]",
							UtilText.parse(characterSelected(), "重置[npc.namePos]所有的天赋和特性，返还所有消耗的天赋点。(临时可用，由于天赋树仍在开发中。)"),
							Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							characterSelected().resetPerksMap(false, false);
						}
					};
				}
				return new Response("天赋", UtilText.parse(characterSelected(), "分配[npc.namePos]的天赋点。"), SLAVE_MANAGEMENT_PERKS);
				
			} else if(index==7) {
				if(characterSelected() == null) {
					return new Response("转化", "你还没选择任何人……", null);
				}
				if(characterSelected().isDoll() && (Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE) || characterSelected().hasItemType(ItemType.DOLL_CONSOLE))) {
					return new ResponseEffectsOnly("转化", UtilText.parse(characterSelected(), "使用你的D.E.C.K.来定制[npc.namePos]的外貌。")) {
						@Override
						public void effects() {
							ItemEffectType.DOLL_CONSOLE.itemEffectOverride(TFModifier.NONE, TFModifier.NONE, TFPotency.BOOST, 0, Main.game.getPlayer(), characterSelected(), new ItemEffectTimer());
						}
					};
					
				} else if(!characterSelected().isAbleToSelfTransform()) {
					return new Response("转化", characterSelected().getUnableToTransformDescription(), null);
					
				} else {
					return new Response("转化",
							UtilText.parse(characterSelected(), "仔细观察[npc.name]会将自己转化成什么样……"),
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							BodyChanging.setTarget(characterSelected(), coreNode, defaultResponseTab);
						}
					};
				}
				
			} else if(index==8) {
				if(Main.game.getCurrentDialogueNode()==OCCUPANT_CHOOSE_NAME) {
					return new Response("设定名字", UtilText.parse(characterSelected(), "你正在管理[npc.namePos]的名字！"), null);
				}
				if(characterSelected() == null) {
					return new Response("设定名字", "你还没选择任何人……", null);
				}
				return new Response("设定名字", UtilText.parse(characterSelected(), "让[npc.Name]以不同的名字称呼你。"), OCCUPANT_CHOOSE_NAME);
				
			} else if(index==10 && Main.getProperties().hasValue(PropertyValue.companionContent)) {
				if(characterSelected() == null) {
					return new Response("送回家", "你还没选择任何人……", null);
				}
				return new Response("送回家", UtilText.parse(characterSelected(), "[npc.Name]不在你的队伍里，所以你不能把[npc.herHim]送回家……"), null);
				
			} else if(index==11) {
				if(characterSelected() == null) {
					return new Response("战斗动作", "你还没选择任何人……", null);
				}
				return new Response("战斗动作", UtilText.parse(characterSelected(), "调整[npc.Name]战斗中可使用的动作。"), CombatMovesSetup.COMBAT_MOVES_CORE) {
					@Override
					public void effects() {
						CombatMovesSetup.setTarget(characterSelected(), coreNode);
					}
				};
				
			} else if(index==12) {
				if(characterSelected() == null) {
					return new Response("法术", "你还没选择任何人……", null);
				}
				return new Response("法术", UtilText.parse(characterSelected(), "管理[npc.namePos]的法术。"), SpellManagement.CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						SpellManagement.setSpellOwner(characterSelected(), coreNode);
					}
				};
				
			} else if(index==13) {
				if(characterSelected() == null) {
					return new Response("驱散元素体", "你还没选择任何人……", null);
				}
				if(!characterSelected().isElementalSummoned()) {
					return new Response("驱散元素体", UtilText.parse(characterSelected(), "[npc.Name]还没有召唤元素体……"), null);
					
				} else {
					return new Response("驱散元素体", UtilText.parse(characterSelected(), "让[npc.name]驱散[npc.her]的元素体。"), coreNode) {
						@Override
						public void effects() {
							characterSelected().removeCompanion(characterSelected().getElemental());
							characterSelected().getElemental().returnToHome();
						}
					};
				}
				
			} else if(index == 0) {
				if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
					return new Response("返回", "回到友人住客列表界面。", coreNode) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setManagementCompanion(null);
							coreNode = null;
						}
					};
				}
				return new Response("离开",
							characterSelected()==null
								?"返回到上一段对话。"
								:UtilText.parse(characterSelected(),
										characterSelected().isDollStatue()
											?"让[npc.name]静止在原地，继续你的旅程。"
											:(characterSelected().isAsleep()
												?"让[npc.name]继续睡觉……"
												:"告诉[npc.name]你会在其他时间再去找[npc.herHim]。")),
							Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
		}
		
		return null;
	}
	
	/**
	 * <b>Use getSlaveryManagementDetailedDialogue(NPC slave) to initialise this!!!</b>
	 */
	public static final DialogueNode SLAVE_MANAGEMENT_INSPECT = new DialogueNode("管理奴隶", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "审视[npc.Name]");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			if(character.isSlave() && character.getOwner().isPlayer()) {
				UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			}
			
			UtilText.nodeContentSB.append(
					"<div class='container-full-width'>"
							+ "<h6 style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+"; text-align:center;'>检查</h6>"
							+ "<div class='container-full-width inner'>"
								+character.getCharacterInformationScreen(false)
							+"</div>"
					+"</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_JOBS = new DialogueNode("管理奴隶", "。", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().getSlaveryManagerJobSelected().isHidden(characterSelected())) {
				Main.game.getDialogueFlags().setSlaveryManagerJobSelected(SlaveJob.IDLE);
			}
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos]的工作");
		}
		@Override
		public String getContent() {
			NPC character = characterSelected();
			ObedienceLevel obedience = ObedienceLevel.getObedienceLevelFromValue(character.getObedienceValue());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Job hours
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");
			
			UtilText.nodeContentSB.append("<div class='container-full-width inner' style='text-align:center;padding-left:2px;padding-right:2px;'>");

			UtilText.nodeContentSB.append("<div class='title-button "+(!isJobSchedulePasteAvailable()?"no-select":"")+"' id='pasteSlaveJobSchedule'"
					+ " style='position:absolute; left:auto; right:8px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999; "+(!isJobSchedulePasteAvailable()?"opacity:0.5;":"")+"'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getPasteIcon()
								+ "</div>");
			UtilText.nodeContentSB.append("<div class='title-button' id='copySlaveJobSchedule' style='position:absolute; left:auto; right:48px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999;'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()
								+ "</div>");
			
			UtilText.nodeContentSB.append("<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>工作安排</h6>");
			UtilText.nodeContentSB.append("<div style='width:100%;margin-top:8px;'><b>可行工作</b></div>");
			
			
			for(SlaveJob job : SlaveJob.values()) {
				if(!job.isHidden(character) && (character.isSlave() || job.hasFlag(SlaveJobFlag.GUEST_CAN_WORK))) {
					UtilText.nodeContentSB.append(
							"<div class='normal-button' id='"+job+"_ASSIGN' style='width:calc(16.6% - 2px); margin:1px;color:"
									+job.getColour().toWebHexString()+";"+(Main.game.getDialogueFlags().getSlaveryManagerJobSelected()==job?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
									+Util.capitaliseSentence(job.getName(character))
									+"</div>");
				}
			}
			
			UtilText.nodeContentSB.append("<div style='width:100%;margin-top:8px;'><b>时间段</b></div>");
			float stamina = character.getDailySlaveJobStamina();
			SlaveJob jobSelected = Main.game.getDialogueFlags().getSlaveryManagerJobSelected();
			for(int i=0 ; i< 24; i++) {
				Colour colour = character.getSlaveJob(i).getColour();
				Colour borderColour = colour;
				Colour backgroundColour = colour;
				String background = "background:"+backgroundColour.getShades()[0]+";";
//				if(character.isSleepingAtHour(i)) {
////					colour = PresetColour.BASE_PURPLE_DARK;
////					background = "background:"+PresetColour.BASE_PURPLE.getShades()[0]+";";
////					String c1 = backgroundColour.getShades()[0];
////					String c2 = PresetColour.BASE_PURPLE_LIGHT.getShades()[0];
////					background = "background: repeating-linear-gradient(135deg, "+c1+", "+c1+" 10px, "+c2+" 10px, "+c2+" 20px);";
//				}
				if((!jobSelected.isAvailable(i, character)
							|| (!character.isSlave() && stamina-jobSelected.getHourlyStaminaDrain(character)+character.getSlaveJob(i).getHourlyStaminaDrain(character)<0f)
							|| (!character.isSlave() && character.isSleepingAtHour(i)))
						&& !jobSelected.equals(SlaveJob.IDLE)) { // Always allow idle job
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour disabled' style='"+background+"border-color:"+borderColour.toWebHexString()+";color:"+colour.getShades()[4]+";' id='"+i+"_WORK_DISABLED'>");
				} else {
					UtilText.nodeContentSB.append(
							"<div class='normal-button hour' style='"+background+"border-color:"+borderColour.toWebHexString()+";color:"+colour.getShades()[4]+";' id='"+i+"_WORK'>");
				}
				UtilText.nodeContentSB.append(String.format("%02d", i)+":00");
				if(character.isSleepingAtHour(i)) { // Sleeping indication via 'zzZ'
					String stroke = "text-shadow:"
									+ "1px 1px 0 #000,"
									+ "-1px -1px 0 #000, "
									+ "1px -1px 0 #000,"
									+ "-1px 1px 0 #000,"
									+ "1px 1px 0 #000;";
					UtilText.nodeContentSB.append("<b class='hotkey-icon' style='color:"+PresetColour.SLEEP.toWebHexString()+";"+stroke+"'><span style='font-size:0.8em;'>z</span>zZ</b>");
				}
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append(
								"<div style='width:100%;margin-top:8px;'>"
									+"<i>[style.colourStamina(当前每日体力：)] "+(stamina>=0?"[style.colourGood(":"[style.colourBad(")+stamina+")]/"+character.getBaseStaminaForSlaveJobs()+"</i>"
								+ "</div>");
								for(SlaveJobHours preset : SlaveJobHours.values()) {
									boolean jobDisabled = false;
									boolean resetI = false;
									boolean nonSlaveSleeping = false;
									for (int i = preset.getStartHour(); i<preset.getStartHour()+preset.getLength(); i++) {
										if (i>23) {
											i = i-24; // Wrap around to 0
											resetI = true;
										}
										if (!jobSelected.isAvailable(i, character)) {
											jobDisabled = true;
											break;
										}
										if(!nonSlaveSleeping) {
											nonSlaveSleeping = !character.isSlave() && character.isSleepingAtHour(i);
										}
										if (resetI) {
											i = i+24; // Reset i to maintain the loop
										}
									}
									if ((!character.isSlave() && jobSelected.getHourlyStaminaDrain(character)*preset.getLength()>stamina) || nonSlaveSleeping || jobDisabled) {
										UtilText.nodeContentSB.append("<div class='normal-button disabled' id='"+preset+"_TIME_DISABLED' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
									} else {
										UtilText.nodeContentSB.append("<div class='normal-button' id='"+preset+"_TIME' style='width:16%; margin:2px;'>"+preset.getName()+"</div>");
									}
								}
			UtilText.nodeContentSB.append(
							"</div>"
//						+ "</div>"
					+ "</div>");
			
			
			// Jobs:
			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='text-align:center;'>");

			UtilText.nodeContentSB.append("<div class='title-button "+(!isJobSettingsPasteAvailable()?"no-select":"")+"' id='pasteSlaveJobSettings'"
					+ " style='position:absolute; left:auto; right:8px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999; "+(!isJobSettingsPasteAvailable()?"opacity:0.5;":"")+"'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getPasteIcon()
								+ "</div>");
			UtilText.nodeContentSB.append("<div class='title-button' id='copySlaveJobSettings' style='position:absolute; left:auto; right:48px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999;'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()
								+ "</div>");
			
			UtilText.nodeContentSB.append("<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>工作设置与相关信息</h6>"
						+"<div class='container-full-width' style='margin-bottom:0;'>"
							+ "<div style='width:20%; float:left; font-weight:bold; margin:0 0 0 4%; padding:0;'>"
								+ "工作"
							+ "</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b>工人</b>"
							+"</div>"
							+ "<div style='float:left; width:14%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>好感</b>"
							+"</div>"
							+ "<div style='float:left; width:14%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>服从</b>"
							+"</div>"
							+ "<div style='float:left; width:39%; font-weight:bold; margin:0; padding:0;'>"
								+ "<b style='color:"+PresetColour.CURRENCY_GOLD.toWebHexString()+";'>收入</b>"
										+ "(+<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>服从加成</b>)"
							+"</div>"
						+ "</div>");
			
			for(SlaveJob job : SlaveJob.values()) {
				if(job.isHidden(character) || (!character.isSlave() && !job.hasFlag(SlaveJobFlag.GUEST_CAN_WORK))) {
					continue;
				}
				float affectionChange = job.getAffectionGain(character);
				float obedienceChange = job.getObedienceGain(character);
				int income = job.getFinalHourlyIncomeAfterModifiers(character);
				boolean isCurrentJob = character.hasSlaveJobAssigned(job);
				
				UtilText.nodeContentSB.append(
						"<div class='container-full-width inner' "+(isCurrentJob?"style='background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'":"")+">"
							+ "<div style='width:4%; float:left; margin:0; padding:0;'>"
								+ "<div class='title-button no-select' id='SLAVE_JOB_INFO_"+job+"' style='position:relative; top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getInformationIcon()+"</div>"
							+ "</div>"
							+"<div style='width:20%; float:left; margin:0; padding:0;'>"
								+ (isCurrentJob
									? "<b style='color:"+job.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(job.getName(character))+"</b>"
									: "[style.colourDisabled("+Util.capitaliseSentence(job.getName(character))+")]")
							+ "</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
								+ Main.game.getOccupancyUtil().getTotalCharactersWorkingJob(job)+"/"+(job.getSlaveLimit()<0?"∞":job.getSlaveLimit())
							+"</div>"
							+ "<div style='float:left; width:14%; margin:0; padding:0;'>"
								+ (affectionChange>0
										?"<b style='color:"+PresetColour.AFFECTION.toWebHexString()+";'>+"+decimalFormat.format(affectionChange)+ "</b>"
										:(affectionChange<0
												?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(affectionChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/小时"
							+"</div>"
							+ "<div style='float:left; width:14%; margin:0; padding:0;'>"
								+ (obedienceChange>0
										?"<b style='color:"+PresetColour.OBEDIENCE.toWebHexString()+";'>+"+decimalFormat.format(obedienceChange)+ "</b>"
										:(obedienceChange<0
												?"<b style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>"+decimalFormat.format(obedienceChange)+ "</b>"
												:"[style.colourDisabled(0)]"))+"/小时"
							+"</div>"
							+ "<div style='float:left; width:39%; margin:0; padding:0;'>"
								+ UtilText.formatAsMoney(job.getIncome())
								+ " + ("
								+ (job.getObedienceIncomeModifier()>0
										?"[style.colourObedience("+job.getObedienceIncomeModifier()+")]"
										:"[style.colourDisabled("+job.getObedienceIncomeModifier()+")]")
										+ "*<span style='color:"+obedience.getColour().toWebHexString()+";'>"+character.getObedienceValue()+"</span>)"
								+ " = "
								+(income>0
									?UtilText.formatAsMoney(income, "b")
									:UtilText.formatAsMoney(income, "b", PresetColour.GENERIC_BAD))
								+"/小时"
							+"</div>"
							);
				
				// Job Settings:
				for(SlaveJobSetting setting : job.getMutualSettings()) {
					if(!setting.isAvailable()) {
						continue;
					}
					boolean settingActive = character.hasSlaveJobSetting(job, setting);
					
					String id = settingActive
							?job.toString()+setting.toString()+"_REMOVE"
							:job.toString()+setting.toString()+"_ADD";
			
					UtilText.nodeContentSB.append(
							"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='width:23%; margin:1%; text-align:center;"
										+(settingActive?"border-color:"+job.getColour().toWebHexString()+";":"border-color:"+job.getColour().getShades()[0]+";")+"'>"
								+ (settingActive
										?"<span style='color:"+job.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
										:"[style.colourDisabled("+setting.getName()+")]")
							+ "</div>");
				}
				// More Job Settings:
				for(Entry<String, List<SlaveJobSetting>> entry : job.getMutuallyExclusiveSettings().entrySet()) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner' style='"+(!isCurrentJob?"background:#1B1B1B;":"")+"'>"
													+ "<div style='width:100%; float:left; margin:0; padding:0;"+(isCurrentJob?"":"color:#777;")+"'>"
													+ "<b>"+Util.capitaliseSentence(entry.getKey())+"</b>"
													+ "</div>");
					
					for(SlaveJobSetting setting : entry.getValue()) {
						if(!setting.isAvailable()) {
							continue;
						}
						boolean settingActive = character.hasSlaveJobSetting(job, setting);
						
						String id = settingActive
								?setting.toString()+"_DISABLED"
								:setting.toString()+"_TOGGLE_ADD";
				
						UtilText.nodeContentSB.append(
								"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='width:23%; margin:1%; text-align:center;"
											+(settingActive?"border-color:"+job.getColour().toWebHexString()+";":"")+"'>"
									+ (settingActive
											?"<span style='color:"+job.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
											:"[style.colourDisabled("+setting.getName()+")]")
								+ "</div>");
					}
					UtilText.nodeContentSB.append("</div>");
				}
				
				UtilText.nodeContentSB.append("</div>");
			}
			UtilText.nodeContentSB.append("</div>");

//			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");
//			UtilText.nodeContentSB.append(  "<h6 style='color:"+PresetColour.GENERIC_EXPERIENCE.toWebHexString()+"; text-align:center;'>Additional Book-Keeping Information</h6>");
//			UtilText.nodeContentSB.append(  "<p>Optional extra information you may add for this slave for book-keeping purposes.</p>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(    "<h7>Category</h7>");
//			UtilText.nodeContentSB.append(    "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(      "<input id='SET_SLAVE_CATEGORY' type='text' value='"+UtilText.parseForHTMLDisplay(character.getSlaveCategory())+"'/>");
//			UtilText.nodeContentSB.append(    "</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append(  "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(    "<h7>Notes</h7>");
//			UtilText.nodeContentSB.append(    "<div class='container-full-width inner' style='margin-bottom:0;'>");
//			UtilText.nodeContentSB.append(      "<form style='padding:0;margin:0;text-align:center;'>");
//			UtilText.nodeContentSB.append(        "<textarea id='SET_SLAVE_NOTES' style='width:760px;height:200px;'>"+character.getSlaveNotes()+"</textarea>");
//			UtilText.nodeContentSB.append(      "</form>");
//			UtilText.nodeContentSB.append(    "</div>");
//			UtilText.nodeContentSB.append(  "</div>");
//			UtilText.nodeContentSB.append("</div>");
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_PERMISSIONS = new DialogueNode("管理奴隶", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 许可");
		}
		
		@Override
		public String getContent() {
			NPC character = characterSelected();
			
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSlaveInformationHeader(character));
			
			// Permissions:
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>");

			UtilText.nodeContentSB.append("<div class='title-button "+(!isPermissionsPasteAvailable()?"no-select":"")+"' id='pastePermissions'"
					+ " style='position:absolute; left:auto; right:8px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999; "+(!isPermissionsPasteAvailable()?"opacity:0.5;":"")+"'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getPasteIcon()
								+ "</div>");
			UtilText.nodeContentSB.append("<div class='title-button' id='copyPermissions' style='position:absolute; left:auto; right:48px; top:8px; padding:2px; margin:0; width:24px; height:24px; border:1px solid #999;'>"
									+ SVGImages.SVG_IMAGE_PROVIDER.getCopyIcon()
								+ "</div>");
			
			UtilText.nodeContentSB.append("<h6 style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+"; text-align:center;'>权限</h6>");
			
			for(SlavePermission permission : SlavePermission.values()) {
				if(permission.isAvailableForCharacter(character)) {
					UtilText.nodeContentSB.append("<div class='container-full-width inner' style='box-sizing:border-box; position:relative; width:98%; margin:4px 1%; background:"+PresetColour.BACKGROUND_ALT.toWebHexString()+";'>");
					
					for(SlavePermissionSetting setting : permission.getSettings()) {
						if(setting.isAvailableForCharacter(character)) {
							boolean settingActive = character.getSlavePermissionSettings().get(permission).contains(setting);
							
							String id = (permission.isMutuallyExclusiveSettings()
											?(settingActive?setting+"_REMOVE_ME":setting+"_ADD")
											:(settingActive?setting+"_REMOVE":setting+"_ADD"));
							
							String style = "width:23%; margin:1%;";
							if(permission.getSettings().size()==5) {
								style = "width:18%; margin:1%;"; // These settings can fit 5 on a line
							}
							
							UtilText.nodeContentSB.append(
									"<div id='"+id+"' class='normal-button"+(settingActive?" selected":"")+"' style='"+style+"text-align:center;"
												+(settingActive
													?"border-color:"+permission.getColour().toWebHexString()+";"
													:(permission.isMutuallyExclusiveSettings()
															?""
															:"border-color:"+permission.getColour().getShades()[0]+";"))+"'>"
										+ (settingActive
												?"<span style='color:"+permission.getColour().toWebHexString()+";margin:8px;'>"+setting.getName()+"</span>"
												:"[style.colourDisabled("+setting.getName()+")]")
									+ "</div>");
						}
					}
					UtilText.nodeContentSB.append("</div>");
				}
			}
			UtilText.nodeContentSB.append("</div>"
					+ "<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.parse(character, UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	private static Response getCosmeticsResponse(int responseTab, int index) {
		if (index == 1) {
			if(!BodyChanging.getTarget().isAbleToWearMakeup()) {
				return new Response("妆容", UtilText.parse(BodyChanging.getTarget(), "由于[npc.namePos]身体由"+Main.game.getPlayer().getBodyMaterial().getName()+"构成，凯特无法进行化妆！"), null);
				
			} else {
				return new Response("化妆",
						"凯特提供广泛的化妆服务，小册子上有好几页都是各种图片，展示着各色各样的口红、指甲油和其他化妆品的效果。",
						SLAVE_MANAGEMENT_COSMETICS_MAKEUP);
			}
			
		} else if (index == 2) {
			return new Response("头发",
					"上面有一张跨页的内容，展示了凯特能给头发染各种颜色，换不同造型和长度。",
					SLAVE_MANAGEMENT_COSMETICS_HAIR);

		} else if (index == 3) {
			return new Response("穿孔",
					"凯特提供多种多样、范围广泛的穿孔。",
					SLAVE_MANAGEMENT_COSMETICS_PIERCINGS);

		}  else if (index == 4) {
			if(BodyChanging.getTarget().isDoll()) {
				return new Response("眼睛", "凯特无法给奥术硅胶重新染色，所以该选项不可用……", null);
			}
			return new Response("眼睛",
					"小册子刚开始专门有一页，推销着凯特眼睛易色的功夫。"
					+ "与皮肤易色类似，这对她的灵气要求很高，所以也很昂贵。", SLAVE_MANAGEMENT_COSMETICS_EYES);

		} else if (index == 5) {
			if(BodyChanging.getTarget().isDoll()) {
				return new Response("体表", "凯特无法给奥术硅胶重新染色，所以该选项不可用……", null);
			}
			return new Response("体表",
					"小册子中间专门有一页，推销着凯特驾驭奥术给皮肤或皮毛易色的能力。"
					+ "显然这对她的灵气有很高要求，所以也很行昂贵。",
					SLAVE_MANAGEMENT_COSMETICS_COVERINGS){
				@Override
				public void effects() {
					SuccubisSecrets.initCoveringsMap(BodyChanging.getTarget());
				}
			};

		} else if (index == 6) {
			return new Response("其他", "凯特还提供各种杂项服务，例如肛门漂白。", SLAVE_MANAGEMENT_COSMETICS_OTHER);

		} else if (index == 7) {
			return new Response("纹身", "小册子的绝大部分都被各种图画和照片占据，体现出凯特非凡的艺术潜质。"
					+ "她甚至能纹上奥术附魔的纹身，不过看上去不算便宜……", SLAVE_MANAGEMENT_TATTOOS);

		} else if (index == 0) {
			if(coreNode==OccupantManagementDialogue.SLAVE_LIST) {
				return new Response("返回", "回到友人住客列表界面。", coreNode) {
					@Override
					public void effects() {
						Main.game.setResponseTab(defaultResponseTab);
						Main.game.getDialogueFlags().setManagementCompanion(null);
						coreNode = null;
					}
				};
			}
			return new Response("返回", "回到奴隶管理界面。", coreNode) {
				@Override
				public void effects() {
					Main.game.setResponseTab(defaultResponseTab);
				}
			};

		} else {
			return null;
		}
	}
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_MAKEUP = new DialogueNode("奴隶管理", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 妆容");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "你当前拥有"+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "腮红", "腮红(也叫胭脂)被用来粉饰脸颊，以显得更加年轻或凸显颧骨。", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "口红", "口红被用来为嘴唇提供色彩、质地或保护。", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "眼线", "眼线用于眼廓周围，有助于修饰眼型或突出不同的特征。", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "眼影", "眼影用来让使用者的眼睛更加凸显迷人。", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "指甲油", "指甲油用于给[npc.namePos]的[npc.hands]添加色彩或提供保护。", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚趾甲油", "脚趾甲油用于给[npc.namePos]的[npc.feet]添加色彩或提供保护。", true, true)
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("化妆",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的妆容！"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_HAIR = new DialogueNode("奴隶管理", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 毛发");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "你当前拥有"+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					
					+ (BodyChanging.getTarget().isDoll()
							?""
							:CharacterModificationUtils.getKatesDivHairLengths(true, "头发长度", "头发长度决定了[npc.namePos]能做的发型种类。[npc.her][npc.hair(true)]越长，发型种类就越丰富。"))

					+CharacterModificationUtils.getKatesDivHairStyles(true, "发型", "可用的发型是由[npc.namePos][npc.hair(true)]长度决定的。")
					
					+(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME && !BodyChanging.getTarget().isDoll()
						?CharacterModificationUtils.getKatesDivCoveringsNew(
								true, BodyChanging.getTarget().getHairType().getRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(),
								UtilText.parse(BodyChanging.getTarget(), "[npc.Hair]颜色"),
								"所有毛发的改变颜色都是永久的，如果你之后又想要改变颜色，那就必须再来找凯特。", true, true)
						:"")
					);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 2) {
				return new Response("头发",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的毛发！"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_PIERCINGS = new DialogueNode("奴隶管理", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 穿孔");
		}
		
		@Override
		public String getHeaderContent() {
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "你当前拥有"+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					
				+CharacterModificationUtils.getKatesDivPiercings(false));
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 3) {
				return new Response("穿孔",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的穿孔！"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_EYES = new DialogueNode("奴隶管理", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 眼睛");
		}
		
		@Override
		public String getHeaderContent() {
			
			return UtilText.parse(BodyChanging.getTarget(),
					"<h6 style='text-align:center;'>"
						+ "你当前拥有"+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyChanging.getTarget().getEyeType().getRace(), BodyChanging.getTarget().getEyeCovering(),
							"虹膜", "虹膜指的是眼睛中染色的部分，负责控制瞳孔的直径和大小。", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"瞳孔", "瞳孔是位于虹膜中心的透明物体，以便光线打在视网膜上。", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"单眼巩膜", "巩膜指得是眼睛中包围虹膜的部分(通常是白色的)。", true, true));
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 4) {
				return new Response("眼睛",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的眼睛！"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_COVERINGS = new DialogueNode("奴隶管理", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 体毛");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>"
									+ "你当前拥有"+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
								+ "</h6>");
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = BodyChanging.getTarget();

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
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 5) {
				return new Response("体表",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的体毛！"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_COSMETICS_OTHER = new DialogueNode("奴隶管理", "。", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.Name]: 其他");
		}
		
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<h6 style='text-align:center;'>"
						+ "你当前拥有"+UtilText.formatAsMoney(Main.game.getPlayer().getMoney(), "span")
					+ "</h6>"
					+CharacterModificationUtils.getKatesDivAnalBleaching());
			
			if(!BodyChanging.getTarget().isDoll()) {
				UtilText.nodeContentSB.append((Main.game.isFacialHairEnabled()
								? CharacterModificationUtils.getKatesDivFacialHair(true, "脸部毛发", "[npc.namePos]脸上的毛发。" 
										+ (Main.game.isFemaleFacialHairEnabled() ? "" : "女性化角色无法长出脸部毛发。"))
								:"")
						
						+(Main.game.isPubicHairEnabled()
								?CharacterModificationUtils.getKatesDivPubicHair(true, "阴毛", "生殖器附近的体毛；位于[npc.namePos]性器官和胯部附近。")
								:"")
						
						+(Main.game.isBodyHairEnabled()
								?CharacterModificationUtils.getKatesDivUnderarmHair(true, "腋毛", "[npc.namePos]腋下的体毛。")
								:"")
						
						+(Main.game.isAssHairEnabled()
								?CharacterModificationUtils.getKatesDivAssHair(true, "肛毛", "[npc.namePos]肛门附近的体毛。")
								:"")
						);
			
				for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
					if((Main.game.isFacialHairEnabled() && BodyChanging.getTarget().getFacialHairType().getType()==bct)
							|| (Main.game.isBodyHairEnabled() && BodyChanging.getTarget().getUnderarmHairType().getType()==bct)
							|| (Main.game.isAssHairEnabled() && BodyChanging.getTarget().getAssHairType().getType()==bct)
							|| (Main.game.isPubicHairEnabled() && BodyChanging.getTarget().getPubicHairType().getType()==bct)) {
						UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
								true, Race.NONE, bct, "体毛", "你的体毛。", true, true));
					}
				}
			}
			
			return UtilText.parse(BodyChanging.getTarget(), UtilText.nodeContentSB.toString());
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 6) {
				return new Response("其他",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的其他特征！"),
						null);
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_TATTOOS = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoos();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 7) {
				return new Response("纹身",
						UtilText.parse(BodyChanging.getTarget(), "你正在更改[npc.namePos]的纹身！"),
						null);
				
			} else if(index==11) {
				return new Response("确认状态：",
						"开启纹身去除确认。"
							+ "启用时，需要点击两次才能移除纹身。"
							+ "关闭时只需要一次点击。",
							SLAVE_MANAGEMENT_TATTOOS) {
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
				
			} else {
				return getCosmeticsResponse(responseTab, index);
			}
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_TATTOOS_ADD = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getLabel() {
			return "魅魔的秘密-"+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getName()) +"纹身";
		}
		
		@Override
		public String getContent() {
			return CharacterModificationUtils.getKatesDivTattoosAdd();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("应用("+UtilText.formatAsMoneyUncoloured(value, "span")+")",
							UtilText.parse(BodyChanging.getTarget(), "你没有足够的钱可以给[npc.name]纹身！"),  null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("应用("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "你需要选择纹身类型，添加一些文字或计数指示才能形成完整的纹身！", null);
					
				} else {
					return new Response("应用("+UtilText.formatAsMoney(value, "span")+")", 
							UtilText.parse(BodyChanging.getTarget(), "付钱让凯特给[npc.name]纹这个纹身！"), SLAVE_MANAGEMENT_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							BodyChanging.getTarget().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("保存/加载", "保存/加载纹身预设。", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(SLAVE_MANAGEMENT_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("返回", "取消纹身并回到选择菜单。", SLAVE_MANAGEMENT_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SLAVE_MANAGEMENT_PERKS = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getLabel() {
			return UtilText.parse(characterSelected(), "[npc.NamePos]的天赋");
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(),
					"<details>"
							+ "<summary>[style.boldPerk(天赋与特性信息)]</summary>"
							+ "[style.colourPerk(天赋)](圆形图标)为[npc.namePos]属性提供永久增益。<br/>"
							+ "[style.colourPerk(特性)](方形图标)为[npc.name]提供独特的效果。"
								+ "与天赋不同，<b>特性在加入“生效特性”栏之前不会有任何效果</b>。<br/>"
							+ "天赋需需要天赋点数解锁。[npc.Name]每当升级时便会获得一点天赋点数，并且每五级获得额外两点天赋点数。<br/><br/>"
							+ "除了这些通过天赋点解锁的天赋以外，还存在着一些通过特殊事件解锁的特殊隐藏天赋。"
					+ "</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(characterSelected(), true));
			
			UtilText.nodeContentSB.append("</div>");
			
			if(!characterSelected().isElemental() && !characterSelected().isDoll()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='padding:8px; text-align:center;'>"
							+ "<i>请注意此天赋树仍在施工，并非最终版本，仅用于展示概念！</i>"
						+ "</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode OCCUPANT_CHOOSE_NAME = new DialogueNode("", "", true) {

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(characterSelected().isSlave()) {

				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
					"<p>"
						+ "从现在开始，[npc.nameIsFull]将称呼你为“[npc.pcName]”，你不禁思考起该不该让[npc.herHim]换个说法称呼你。"
						+ "[npc.sheIs]不是你的奴隶，你无法随心所欲地改变[npc.her]的名字……"
					+ "</p>"));
				
				UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px 16px;'>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "名字"
						+ "</div>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "姓氏"
						+ "</div>"
						+ "<div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "[npc.she]对你的称呼")
						+ "</div>"
						
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
							
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
						
						+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ " <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:12%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "全体奴隶"
						+ "</div>");
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p>"
							+ "从现在开始，[npc.nameIsFull]将称呼你为“[npc.pcName]”，你不禁思考起该不该让[npc.herHim]换个说法称呼你。"
							+ "[npc.sheIs]不是你的奴隶，你无法改变[npc.herHim]的名字。"
						+ "</p>"));
				

				UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='padding:8px 16px;'>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "名字"
						+ "</div>"
						+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
							+ "姓氏"
						+ "</div>"
						+ "<div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
							+ UtilText.parse(characterSelected(), "[npc.she]对你的称呼")
						+ "</div>"
						
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
							
						+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
							+ " value='"+ UtilText.parseForHTMLDisplay(characterSelected().getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
							+ "&#127922;"
						+ "</div>"
						
						+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(characterSelected().getPetName(Main.game.getPlayer()))
							+ "' style='width:100%; margin:0; padding:0;'></form>"
						+ "<div class='normal-button' id='"+characterSelected().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "&#10003;"
						+ "</div>"
						+ " <div class='normal-button disabled' style='float:left; width:12%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
							+ "全体奴隶"
						+ "</div>");
			}
			
			UtilText.nodeContentSB.append(UtilText.parse(characterSelected(), 
						"<p style='text-align:center; margin-top:4px;'>"
							+ "<i>如果[npc.name]被告知称呼你为“爸爸”/“妈妈”、“爹地”/“妈咪”、“女主人”/“男主人”或“女士”/“先生”，"
							+ "那么[npc.she]将根据你角色的女性化程度自动切换合适的称呼。</i>"
						+ "</p>"
					+ "</div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getResponseTabTitle(int index) {
			return coreNode.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return coreNode.getResponse(responseTab, index);
		}
	};
	
	private static boolean isFreedSlaveAvailableAsGuest() {
		return characterSelected().isAffectionHighEnoughToInviteHome()
				&& Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)
				&& OccupancyUtil.isFreeRoomAvailableForOccupant();
	}

	public static final DialogueNode SET_SLAVE_FREE_SCARLETT = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeSlave(characterSelected());
			characterSelected().getPetNameMap().remove(Main.game.getPlayer().getId());// Reset pet name
			Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
			
			Main.game.getNpc(Scarlett.class).setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST, true);
			Main.game.getNpc(Scarlett.class).setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
			((Scarlett)Main.game.getNpc(Scarlett.class)).resetName();
			((Scarlett)Main.game.getNpc(Scarlett.class)).completeBodyReset();

			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_SCARLETT", characterSelected()));
			Main.game.getDialogueFlags().setManagementCompanion(null);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						"由于斯嘉丽已经跑开了，你除了继续你的一天外没什么可做的……",
						Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	private static boolean freedSlaveDeleted;
	
	public static final DialogueNode SET_SLAVE_FREE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().removeItemByType(ItemType.getItemTypeFromId("innoxia_slavery_freedom_certification"));
			Main.game.getPlayer().removeSlave(characterSelected());
			characterSelected().setEnslavementDialogue(SlaveDialogue.FREEDOM_DIALOG, false);
			if(!isFreedSlaveAvailableAsGuest()) {
				freedSlaveDeleted = true;
				if(!characterSelected().isAffectionHighEnoughToInviteHome()) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_DISLIKE", characterSelected()));
					
				} else if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION) || !OccupancyUtil.isFreeRoomAvailableForOccupant()) {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_NO_GUEST", characterSelected()));
					
				}
				Main.game.banishNPC(characterSelected());
				Main.game.getDialogueFlags().setManagementCompanion(null);
				
			} else {
				freedSlaveDeleted = false;
				characterSelected().getPetNameMap().remove(Main.game.getPlayer().getId());// Reset pet name
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GUEST_CHOICE", characterSelected()));
				Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
			}
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(freedSlaveDeleted) {
				if(index == 1) {
					return new Response("继续",
							"现在你的奴隶已经被释放并永远离开了你，你只能继续你今天的其他计划了……",
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
						}
					};
				}
			} else {
				if(index == 1) {
					return new Response("提供房间",
							UtilText.parse(characterSelected(), "问一问[npc.name]想不想作为客人留在宅邸里。"),
							SET_SLAVE_FREE_GUEST_ROOM) {
						@Override
						public void effects() {
							Cell c = OccupancyUtil.getFreeRoomForOccupant();
							characterSelected().setLocation(c.getType(), c.getLocation(), true);
							Main.game.getPlayer().setLocation(c.getType(), c.getLocation(), false);
							Main.game.getPlayer().addFriendlyOccupant(characterSelected());
							Main.game.getTextEndStringBuilder().append(characterSelected().incrementAffection(Main.game.getPlayer(), 25));
						}
					};
					
				} else if(index == 2) {
					return new Response("告别",
							UtilText.parse(characterSelected(), "告别[npc.name]……<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
							SET_SLAVE_FREE_END_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GOODBYE", characterSelected()));
							Main.game.banishNPC(characterSelected());
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
					
				} else if(index == 3) {
					return new Response("扔出去",
							UtilText.parse(characterSelected(), "让萝丝毫不客气地把[npc.name]赶出宅邸……<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
							SET_SLAVE_FREE_END_NO_CONTENT) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_THROWN_OUT", characterSelected()));
							Main.game.banishNPC(characterSelected());
							Main.game.getDialogueFlags().setManagementCompanion(null);
						}
					};
				}
				
			}
			return null;
		}
	};
	
	public static final DialogueNode SET_SLAVE_FREE_END_NO_CONTENT = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode SET_SLAVE_FREE_GUEST_ROOM = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/enslavement", "SET_SLAVE_FREE_GUEST_ROOM", characterSelected());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", UtilText.parse(characterSelected(), "你把[npc.name]安置到了[npc.her]的新房间，继续今天的计划……"), Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setManagementCompanion(null);
					}
				};
			}
			return null;
		}
	};
	
	
}
