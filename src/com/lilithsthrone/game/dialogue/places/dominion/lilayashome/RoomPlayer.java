package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.companions.CompanionManagement;
import com.lilithsthrone.game.dialogue.companions.OccupantManagementDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.nightlife.NightlifeDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMBath;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMShower;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.utils.time.DateAndTime;
import com.lilithsthrone.utils.time.SolarElevationAngle;
import com.lilithsthrone.world.places.GenericPlace;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.75
 * @version 0.3.5.5
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimeInMinutes = 240;

    private static GameCharacter makeupTarget;
    
    private static List<GameCharacter> slavesWashing;
    
    public static GameCharacter getMakeupTarget() {
        if(makeupTarget==null) {
            return Main.game.getPlayer();
        }
        return makeupTarget;
    }
    
    /**
     * @param sleepTimeInMinutes Calls an endTurn(sleepTimeInMinutes*60) so that NPCs have their status effects updated before the next scene is parsed.
     */
	public static void applySleep(int sleepTimeInMinutes) {
		List<GameCharacter> charactersPresent = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
		charactersPresent.addAll(Main.game.getPlayer().getCompanions());
		charactersPresent.add(Main.game.getPlayer());

		for(GameCharacter character : charactersPresent) {
			character.applySleep(sleepTimeInMinutes);
		}
		
		slavesPresentWhenGoingToSleep = slavesInRoom(Main.game.getHourOfDay());
//		Main.game.getTextStartStringBuilder().append("X: "+Main.game.getHourOfDay());

		Main.game.getPlayer().setActive(false);
		Main.game.endTurn(sleepTimeInMinutes*60);
		Main.game.getPlayer().setActive(true);
		Main.game.endTurnTimeTakenAddition = Main.game.endTurnTimeTaken;

		slavesPresentWhenWaking = slavesInRoom(Main.game.getHourOfDay());
		slavesToWakePlayer = slavesInRoom(Main.game.getHourOfDay()).stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_WAKE_UP)).collect(Collectors.toList());
	}
	
	private static Response getResponseRoom(int responseTab, int index) {
		if(responseTab==1) {
			return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
			
		} else if(responseTab==0) {
			if(index==0) {
				return null;
				
			} else if (index == 1) {
				return new Response("休息 (1小时)",
						"休息一小时，不仅会恢复你的"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+"，你还能获得“充分休息”效果。",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60;
						applySleep(sleepTimeInMinutes);
					}
				};

			} else if (index == 2) {
				return new Response("休息(4小时)",
						"休息四小时，不仅会恢复你的"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+"，你还能获得“充分休息”效果。",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60 * 4;
						applySleep(sleepTimeInMinutes);
					}
				};

            } else if (index == 3) {
                return new Response("休息(8小时)",
                        "休息八小时，不仅会恢复你的"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+"，你还能获得“充分休息”效果。",
                        AUNT_HOME_PLAYERS_ROOM_SLEEP){
                    @Override
                    public void effects() {
                        sleepTimeInMinutes = 60 * 8;
                        applySleep(sleepTimeInMinutes);
                    }
                };

            } else if (index == 4) {
				return new Response("休息(12小时)",
						"休息十二小时，不仅会恢复你的"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+"，你还能获得“充分休息”效果。",
						AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = 60 * 12;
						applySleep(sleepTimeInMinutes);
					}
				};
	
			} else if (index == 5) {
				int timeUntilChange = Main.game.getMinutesUntilNextMorningOrEvening() + 5; // Add 5 minutes so that if the days are drawing in, you don't get stuck in a loop of always sleeping to sunset/sunrise
				LocalDateTime[] sunriseSunset = DateAndTime.getTimeOfSolarElevationChange(Main.game.getDateNow(), SolarElevationAngle.SUN_ALTITUDE_SUNRISE_SUNSET, Game.DOMINION_LATITUDE, Game.DOMINION_LONGITUDE);
				return new Response("休息到" + (Main.game.isDayTime() ? "日落" : "日出"),
						"休息" + (timeUntilChange >= 60 ?timeUntilChange / 60 + "小时" : "")
							+ (timeUntilChange % 60 != 0 ? timeUntilChange % 60 + "分钟" : "")
							+ (Main.game.isDayTime()
									? "，即日落后五分钟("+Units.time(sunriseSunset[1].plusMinutes(5))+")。"
									: "，即日出后五分钟("+Units.time(sunriseSunset[0].plusMinutes(5))+")。")
							+ "不仅会恢复"+Attribute.HEALTH_MAXIMUM.getName()+"与"+Attribute.MANA_MAXIMUM.getName()+", 还能获得“充分休息”效果。",
							AUNT_HOME_PLAYERS_ROOM_SLEEP){
					@Override
					public void effects() {
						sleepTimeInMinutes = timeUntilChange;
						applySleep(sleepTimeInMinutes);
					}
				};
				
			} else if (index == 6) {
				return new Response("管理房间", "进入该房间的管理界面。", OccupantManagementDialogue.ROOM_UPGRADES) {
					@Override
					public void effects() {
						OccupantManagementDialogue.cellToInspect = Main.game.getPlayerCell();
					}
				};
				
			}  else if (index == 7) {
				if(Main.game.getPlayer().isAbleToAccessRoomManagement()) {
					return new Response("人员管理", "进入奴隶和友人住客的管理界面。", ROOM) {
						@Override
						public DialogueNode getNextDialogue() {
							return OccupantManagementDialogue.getSlaveryRoomListDialogue(null, null);
						}
						@Override
						public void effects() {
							CompanionManagement.initManagement(Main.game.getDefaultDialogue(), 0, null);
						}
					};
				} else {
					return new Response("管理人员", "在进入该界面前，你需要获得贩奴许可；莉莱雅要是允许你带人或玩偶住下的话那也行。",  null);
				}
				
			} else if (index == 8) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
					return new Response("日历", "再看一眼挂在墙上的附魔日历", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				} else {
					return new Response("<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>日历</span>", "墙上挂着日历，仔细看看它", AUNT_HOME_PLAYERS_ROOM_CALENDAR);
				}
				
			} else if (index == 9) {
				return new Response("调闹钟", "调个手机闹钟，这样你可以在特定的时间醒来。", RoomPlayer.ROOM_SET_ALARM) {
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
					}
				};

			} else if (index == 10) {
				long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
				if(alarmTime >= 0) {
					String alarmTimeStr = Main.game.getDisplayTime(LocalTime.ofSecondOfDay(alarmTime*60));
					int timeUntilAlarm = Main.game.getMinutesUntilTimeInMinutes((int)alarmTime);
					
					return new Response("睡到闹钟响(" + alarmTimeStr + ")",
							"休息"
									+ (timeUntilAlarm==0
										?"24小时"
										:((timeUntilAlarm >= 60 ? timeUntilAlarm / 60 + "小时，" : "")
												+ (timeUntilAlarm % 60 != 0 ? timeUntilAlarm % 60 + "分钟，" : "")))
									+ "睡到闹钟响起。除了恢复" + Attribute.HEALTH_MAXIMUM.getName() + "与" + Attribute.MANA_MAXIMUM.getName() + "，还能获得“充分休息”效果。",
							AUNT_HOME_PLAYERS_ROOM_SLEEP) {
						@Override
						public void effects() {
							sleepTimeInMinutes = timeUntilAlarm==0?24*60:timeUntilAlarm;
							RoomPlayer.applySleep(sleepTimeInMinutes);
						}
					};
				} else {
					return new Response("睡到闹钟响(未设定)", "<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>你还没有设定闹钟！</span>", null);
				}
			}
			
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			
			int indexPresentStart = 11;
			if(index-indexPresentStart<charactersPresent.size() && index-indexPresentStart>=0) {
				NPC character = charactersPresent.get(index-indexPresentStart);
				return LilayaHomeGeneric.interactWithNPC(character);
			}
			
		} else if(responseTab==2) {
			if (index == 1) {
				return new Response("快速淋浴",
						"使用你卧室的私人套间浴室快速淋浴。"
								+ "<br/>[style.italicsGood(从所有腔穴中清理<b>最多"+Units.fluid(500)+"</b>液体。)]"
								+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
//								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER){
					@Override
					public void effects() {
						List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 120+30);
						}

						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>你把衣服扔在浴室外面，洗澡的时候顺便洗下衣服……</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(false, false, null, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 10*60;
					}
				};
				
			} else if (index == 2) {
				return new Response("全身冲淋",
						"在套间里洗澡，花些时间彻底洗净自己。"
								+ "<br/>[style.italicsExcellent(清理所有腔穴中的<b>全部</b>液体。)]"
								+ "<br/>[style.italicsGood(将<b>仅会</b>清理当前装备的衣物。)]",
//								+ "<br/>[style.italicsMinorBad(This does <b>not</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER){
					@Override
					public void effects() {
						List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
						}
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>你把衣服扔在浴室外面，洗澡的时候顺便洗下衣服……</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, false, StatusEffect.CLEANED_SHOWER, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==3) {
				return new Response("泡澡",
						"用房间附带的浴室泡个澡，花一些时间进行全身清洁。"
								+ "<br/>[style.italicsExcellent(清理所有腔穴中的<b>全部</b>液体。)]"
								+ "<br/>[style.italicsExcellent(将会清理物品栏中的<b>所有</b>衣物。)]",
//								+ "<br/>[style.italicsMinorGood(This <b>does</b> clean companions.)]",
						AUNT_HOME_PLAYERS_ROOM_BATH){
					@Override
					public void effects() {
						List<GameCharacter> charactersPresent = new ArrayList<>(LilayaHomeGeneric.getSlavesAndOccupantsPresent());
						slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
						for(GameCharacter npc : slavesWashing) {
							npc.applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
						}
						
						Main.game.getTextEndStringBuilder().append("<p style='text-align:center'><i>你把衣服扔在浴室外面，洗澡的时候顺便洗下衣服……</i></p>");
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30));
					}
					@Override
					public int getSecondsPassed() {
						return 30*60;
					}
				};
				
			} else if(index==11) {
				return new ResponseEffectsOnly(
						UtilText.parse(getMakeupTarget(), "目标：<b style='color:"+getMakeupTarget().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
						"循环切换选择化妆角色。") {
					@Override
					public void effects() {
						List<GameCharacter> companions = Util.newArrayListOfValues(Main.game.getPlayer());
						companions.addAll(Main.game.getCharactersPresent());
//						companions.removeIf((c) -> !c.isPlayer() && (!c.isSlave() || !c.getOwner().isPlayer()));
						if(!companions.isEmpty()) {
							for(int i=0; i<companions.size();i++) {
								if(companions.get(i).equals(getMakeupTarget())) {
									if(i==companions.size()-1) {
										makeupTarget = companions.get(0);
										break;
										
									} else {
										makeupTarget = companions.get(i+1);
										break;
									}
								}
							}
						}
						Main.game.updateResponses();
					}
				};
				
			} else if(index==12) {
				return new Response("发型和妆容",
						UtilText.parse(getMakeupTarget(), "浴室的橱柜中准备好了各色化妆品和发型工具。如果你愿意，可以花些时间改善一下[npc.namePos]的外貌……"),
						AUNT_HOME_PLAYERS_ROOM_MAKEUP){
					@Override
					public int getSecondsPassed() {
						return 5*60;
					}
				};
				
			}
		}
		return null;
	}
	
	private static String getShowerSlavesDescription(List<GameCharacter> slavesWashing) {
		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing,
						"你的奴隶"+Util.stringsToStringList(names, false)+"接受到你的指示，要帮忙清理身体后，同样也将衣物留在门边，跟你一同进入了浴室。"));
					sb.append(UtilText.parse(slavesWashing,
							"所幸，豪华淋浴间足够宽敞，[npc.name]可以非常舒适地与你并肩相处。"));
				
			} else {
				sb.append("你的奴隶"+Util.stringsToStringList(names, false)+"接受到你的指示，要帮忙清理身体后，同样也将衣物留在门边，跟你一同进入了浴室。");
					sb.append(UtilText.parse(slavesWashing,
							"所幸，豪华淋浴间足够宽敞，你的奴隶可以非常舒适地与你并肩相处。"));
			}
		sb.append("</p>");
		
		// Slave reactions while helping wash:
		
		List<GameCharacter> washingNice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		boolean firstWashing = true;
		for(GameCharacter npc : washingNice) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
			if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
				if(firstWashing) {
					if(Main.game.getPlayer().hasHair()) {
						start.add("打开水龙头，[npc.name]尽职尽责地开始帮你清洗[pc.hair(true)]和身体。[npc.she]提高嗓门，在流水声中说道，");
					} else {
						start.add("[npc.name]打开水龙头，尽职尽责地开始帮你清洗身体。她提高声音，以便在流水声中也能听到，");
					}
				} else {
					start.add("拿起一块肥皂，[npc.name]协助奴隶同伴清洁你的身体。[npc.she]开始执行任务，说道，");
					start.add("[npc.name]向前走了一步，手里拿着一块肥皂，开始为你清洗身体，一边说着[npc.she]的工作，");
					start.add("走到[npc.her]奴隶同伴的身边，[npc.name]开始清洗你的身体，并说道，");
				}
				speech.add("[npc.speech(希望这能让你满意，[pc.name]。)]");
				speech.add("[npc.speech(如果你还需要做点不同的事，叫我就好，[pc.name]。)]");
				speech.add("[npc.speech(我一定会好好帮你清洗干净的，[pc.name]。)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("打开水龙头，[npc.name]紧跟在你身后，然后在你背上抹上肥皂，开始为你清洗。"
								+ "突然，[npc.she]走上前来，将[npc.her][npc.breasts+]贴在你背上，发出诱人的[npc.moans]，");
					} else {
						start.add("打开水龙头，[npc.name]紧跟在你身后，在你背上抹上肥皂，开始为你清洗。"
								+ "突然，[npc.she]向前走了一步，把自己压在你背上，发出诱人的[npc.moans]，");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("向前走一步，[npc.name]将[npc.her][npc.breast+]压在你背上，然后诱惑地[npc.moaning]，");
						start.add("[npc.name]在你的背上擦着肥皂，突然走上前来，将[npc.her]的[npc.breasts+]紧贴在你身上，[npc.she]发出诱人的[npc.moans]，");
						start.add("[npc.name]决定先从清洁你的背部开始，然后绕到你身后，身体前倾，将[npc.her]的[npc.breast+]压在你身上，[npc.moaning]，");
					} else {
						start.add("[npc.name]向前几步，将自己压在你背上，诱惑地[npc.moaning]，");
						start.add("[npc.name]在你背上擦着肥皂，却突然上前，把自己压在你身上，还发出诱人的[npc.moans]，");
						start.add("[npc.name]决定从清洁你的背部开始，绕到你身后，然后向前倾身，将[npc.herself]压在你身上，[npc.moaning]，");
					}
				}
				speech.add("[npc.speech(你不介意我靠得这么近吧？)]");
				speech.add("[npc.speech(你喜欢我离你这么近，对吧？)]");
				speech.add("[npc.speech(没错，放轻松，让我来照顾你……)]");
				speech.add("[npc.speech(也许等你洗干净了，你会愿意和我做一些脏脏的事……)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("打开水龙头，[npc.name]靠近你的身后，然后立即将[npc.her]的[npc.breasts+]压在你的背上。"
								+ "显然，[npc.she]认为这是和你玩乐的好机会。[npc.she]伸手开始摸索你的身体同时诱惑地对着你的[pc.ear][npc.moaning]，");
					} else {
						start.add("打开水龙头，[npc.name]靠近你身后，然后立即将身体压在你背上。"
								+ "显然，[npc.she]认为这是和你玩乐的好机会。[npc.she]伸手开始摸索你的身体同时诱惑地对着你的[pc.ear][npc.moaning]，");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("向前走一步，[npc.name]将[npc.her][npc.breast+]压在你背上，诱惑地[npc.moaning]，");
						start.add("[npc.name]在你的背上擦着肥皂，突然走上前来，将[npc.her]的[npc.breasts+]紧贴在你身上，[npc.she]发出诱人的[npc.moans]，");
						start.add("[npc.name]决定先从清洁你的背部开始，然后绕到你身后，身体前倾，将[npc.her]的[npc.breast+]压在你身上，[npc.moaning]，");
					} else {
						start.add("[npc.name]向前几步，将自己压在你背上，诱惑地[npc.moaning]，");
						start.add("[npc.name]在你背上擦着肥皂，却突然上前，把自己压在你身上，还发出诱人的[npc.moans]，");
						start.add("[npc.name]决定从清洁你的背部开始，绕到你身后，然后向前倾身，将[npc.herself]压在你身上，[npc.moaning]，");
					}
				}
				speech.add("[npc.speech(哦~你喜欢我这样摸你，不是吗？)]");
				speech.add("[npc.speech(这么近距离的接触真是让我兴奋不已，你想在这之后做吗？)]");
				speech.add("[npc.speech(这真是让我欲火焚身……你想在这之后大干一场吗？)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)
					|| npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
				if(npc.isShy()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("打开水龙头，[npc.name]紧张地向前挪动，然后开始帮你清洗[pc.hair(true)]和身体。"
									+ "为了让[npc.her]的声音透过流水声，[npc.she]略微提高[npc.her]的声音说，");
						} else {
							start.add("[npc.name]紧张地向前挪动，打开水龙头帮你清洗身体。"
									+ "为了让[npc.her]的声音透过流水声，[npc.she]略微提高[npc.her]的声音说，");
						}
					} else {
						start.add("[npc.name]拿着一块肥皂紧张地向前挪动，帮助同伴"+(washingNice.size()>2?"slaves":"slave")+"来清洗你。[npc.her]拔高了一点声音说：");
						start.add("[npc.name]拿着一块肥皂走上前，紧张地清洁你的身体。她的声音在流水声中吱吱作响，[npc.she]说：");
						start.add("[npc.name]走到奴隶同伴的身边，边红着脸给你清洗身体，边说：");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(我喜欢离你这么近，[pc.name]。)]");
						speech.add("[npc.speech(我很高兴能像这样帮助你……)]");
						speech.add("[npc.speech(我很高兴能像这样帮助你……)]");
					} else {
						speech.add("[npc.speech(我没做错吧，没有吧，[pc.name]？)]");
						speech.add("[npc.speech(如果我做错了，请告诉我……)]");
						speech.add("[npc.speech(我希望这是你想要的清洗方式……)]");
					}
					
				} else if(npc.isKind()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("[npc.name]高兴地上前，打开水龙头并轻柔地清洗你的[pc.hair(true)]和身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高了[npc.her]的声音说道：");
						} else {
							start.add("[npc.name]高兴地上前，打开水龙头并轻柔地清洗你的身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高了[npc.her]的声音说道：");
						}
					} else {
						start.add("[npc.name]拿着一块肥皂，高兴地上前帮助同伴"+(washingNice.size()>2?"slaves":"slave")+"清洗你。[npc.she]提高声音说：");
						start.add("[npc.name]高兴地拿着一块肥皂走上前清洗你。[npc.she]拔高声音盖过流水声，说：");
						start.add("[npc.name]走到奴隶同伴身边，清洗你的身体，[npc.she]微笑道：");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(我喜欢离你这么近，[pc.name]。如果你想要些别的服务，请告诉我。)]");
						speech.add("[npc.speech(我很高兴能这样帮助你；能成为你的人是我的荣幸。)]");
						speech.add("[npc.speech(让我照顾你，[pc.name]；我想让你知道你对我有多重要。)]");
					} else {
						speech.add("[npc.speech(如果你想要别的服务，请让我知道，可以吗[pc.name]？)]");
						speech.add("[npc.speech(如果你想要别的服务，请让我知道。)]");
						speech.add("[npc.speech(让我照顾你，[pc.name]。)]");
					}
					
				} else {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("[npc.name]走上前，打开水龙头，开始清洗你的[pc.hair(true)]和身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高了[npc.her]的声音说道：");
						} else {
							start.add("[npc.name]走上前，打开水龙头，开始清洗你的身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高声音说道：");
						}
					} else {
						start.add("[npc.name]拿着一块肥皂走上前，帮助同伴"+(washingNice.size()>2?"slaves":"slave")+"清洗你。她拔高声音说：");
						start.add("[npc.name]拿着一块肥皂走近，开始清洗你的身体。[npc.she]将声音拔高到盖过流水声，说：");
						start.add("[npc.name]走到奴隶同伴身边，清洗你的身体，[npc.she]微笑道：");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(我喜欢离你这么近，[pc.name]。如果你想要些别的服务，请告诉我。)]");
						speech.add("[npc.speech(我很高兴能这样帮助你；能成为你的人是我的荣幸。)]");
						speech.add("[npc.speech(让我照顾你，[pc.name]；我想让你知道你对我有多重要。)]");
					} else {
						speech.add("[npc.speech(如果你还需要做点不同的事，叫我就好。)]");
						speech.add("[npc.speech(如果你还需要做点不同的事，叫我就好。)]");
						speech.add("[npc.speech(让我照顾你，[pc.name]。)]");
					}
				}
			}
			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append("");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		List<GameCharacter> washingRude = new ArrayList<>(slavesWashing);
		washingRude.removeAll(washingNice);
		for(GameCharacter npc : washingRude) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
				if(npc.isShy()) {
					if(firstWashing) {
						start.add("[npc.name]不情愿地走上前，打开水龙头，漫不经心地给你清洗。"
								+ "[npc.she]试图用流水声掩盖评价，嘀咕着：");
					} else {
						start.add("[npc.name]不情愿地走上前，盯着地面，漫不经心地给你清洗。[npc.she]发出疲惫的叹息，嘀咕着：");
						start.add("[npc.name]盯着地面，不情愿地走上前给你清洗。[npc.she]发出恼怒的叹息，低声嘀咕着：");
						start.add("显然，[npc.name]对此并不高兴，但[npc.she]还是走上前，漫不经心地开始帮你洗漱。[npc.her]小声嘀咕着，叹了口气，");
					}
					speech.add("[npc.speech(我讨厌这样……)]");
					speech.add("[npc.speech(我怎么最后不得不做这样的事情……)]");
					speech.add("[npc.speech(这糟透了……)]");
					
				} else if(npc.isSelfish()) {
					if(firstWashing) {
						start.add("[npc.name]不情愿地走上前，打开水龙头，漫不经心地给你清洗。"
								+ "[npc.she]带着明显的敌意抱怨道，");
					} else {
						start.add("[npc.name]不情愿地走上前，漫不经心地开始帮你洗漱。发出一声疲惫的叹息，[npc.she]抱怨道，");
						start.add("[npc.name]不耐烦地转了转[npc.eyes]。[npc.her]走上前，一边不情愿地开始帮你洗漱，一边抱怨，");
						start.add("[npc.name]恼怒地叹了口气，走上前，心不在焉地开始帮你洗漱。[npc.she]丝毫不掩饰自己的恼怒，生气地说，");
					}
					speech.add("[npc.speech(你知道吗？我非常讨厌这样。下次找别的奴隶来帮你吧！)]");
					speech.add("[npc.speech(你为什么非要让我这么做？我讨厌这样帮你，这还不清楚吗？)]");
					speech.add("[npc.speech(这真他妈的糟透了。下次让别的奴隶帮你洗怎么样？)]");
					
				} else {
					if(firstWashing) {
						start.add("不情愿地打开水龙头，[npc.name]走前一步，然后心不在焉地开始帮你洗漱。[npc.she]叹了口气，明显地表示不满，");
					} else {
						start.add("不情愿地走上前，[npc.name]心不在焉地开始帮你洗漱。[npc.she]发出一声疲惫的叹息，");
						start.add("[npc.name]不耐烦地转了转[npc.eyes]。[npc.her]走上前，一边不情愿地开始帮你洗漱，一边抱怨，");
						start.add("[npc.name]恼怒地叹了口气，走上前心不在焉地开始帮你洗漱。[npc.she]甚至没有试图掩饰[npc.her]的恼怒，叹了口气，");
					}
					speech.add("[npc.speech(你就不能找别人吗？)]");
					speech.add("[npc.speech(我真希望你不要逼我这么做……)]");
					speech.add("[npc.speech(这糟透了……)]");
					speech.add("[npc.speech(我真的很讨厌这样做，你知道吗？)]");
				}

			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append("");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		return sb.toString();
	}
	
	private static String getBathSlavesDescription(List<GameCharacter> slavesWashing) {
		StringBuilder sb = new StringBuilder();
		
		boolean soloSlave = slavesWashing.size()==1;
		List<String> names = new ArrayList<>();
		for(GameCharacter npc : slavesWashing) {
			names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
		}
		
		sb.append("<p>");
			if(soloSlave) {
				sb.append(UtilText.parse(slavesWashing,
						"你的奴隶"+Util.stringsToStringList(names, false)+"接受到你的指示，要帮忙清理身体后，同样也将衣物留在门边，跟你一同进入了浴室。"));
					sb.append(UtilText.parse(slavesWashing,
							"所幸，你的豪华浴室足够宽敞，就算跟[npc.name]一同相处也不会感到拥挤。"));
				
			} else {
				sb.append("你的奴隶"+Util.stringsToStringList(names, false)+"接受到你的指示，要帮忙清理身体后，同样也将衣物留在门边，跟你一同进入了浴室。");
					sb.append(UtilText.parse(slavesWashing,
							"还好，你的豪华浴室足够宽敞，把奴隶们全塞进来也绰绰有余。"));
			}
		sb.append("</p>");
		
		// Slave reactions while helping wash:
		
		List<GameCharacter> washingNice = slavesWashing.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
		boolean firstWashing = true;
		for(GameCharacter npc : washingNice) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
			if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
				if(firstWashing) {
					if(Main.game.getPlayer().hasHair()) {
						start.add("[npc.name]打开水龙头出洗澡水，尽职尽责地开始帮你清洗[pc.hair(true)]和身体。[npc.her]提高声音，以便在流水声中也能听到，[npc.she]说道，");
					} else {
						start.add("[npc.name]打开水龙头放出洗澡水，然后尽职尽责地开始帮你清洗身体。[npc.her]提高音量，以便在流水声中也能听到，[npc.she]说，");
					}
				} else {
					start.add("[npc.name]拿起一块肥皂，协助[npc.her]的奴隶同伴清洁你的身体。当[npc.she]开始执行[npc.her]的任务时，[npc.she]说，");
					start.add("[npc.name]向前走了一步，手里拿着一块肥皂，开始为你清洗身体，[npc.she]一边工作，一边说道，");
					start.add("[npc.name]走到[npc.her]的奴隶同伴身边，开始清洗你的身体，并说道，");
				}
				speech.add("[npc.speech(我希望只能让你满意，[pc.name]。)]");
				speech.add("[npc.speech(如果你还需要做点不同的事，叫我就好，[pc.name]。)]");
				speech.add("[npc.speech(我一定会好好为你打扫卫生的，[pc.name]。)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("[npc.name]打开水龙头给你洗澡，并紧跟在你身后，然后给你的后背抹上肥皂，开始给你擦洗。"
								+ "突然，[npc.she]走上前来，将[npc.her]的[npc.breasts+]贴在你的背上，[npc.she]发出诱人的[npc.moans]，");
					} else {
						start.add("[npc.name]打开水龙头给你洗澡，并紧跟在你身后，然后给你的后背抹上肥皂，开始给你擦洗。"
								+ "突然，[npc.she]向前走了一步，把自己压在你背上，[npc.she]发出诱人的[npc.moans]，");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("向前走一步，[npc.name]将[npc.her]的[npc.breast+]压在你的背上，然后诱惑地[npc.moaning]，");
						start.add("[npc.name]在你的背上擦着肥皂，突然走上前来，将[npc.her]的[npc.breasts+]紧贴在你身上，发出诱人的[npc.moans]，");
						start.add("[npc.name]决定先从清洁你的背部开始，然后绕到你身后，身体前倾，将[npc.her]的[npc.breast+]压在你身上，[npc.moaning]，");
					} else {
						start.add("[npc.name]向前几步，将自己压在你背上，诱惑地[npc.moaning]，");
						start.add("[npc.name]在你背上擦着肥皂，却突然上前，把自己压在你身上，还发出诱人的[npc.moans]，");
						start.add("[npc.name]决定从清洁你的背部开始，绕到你身后，然后向前倾身，将[npc.herself]压在你身上，[npc.moaning]，");
					}
				}
				speech.add("[npc.speech(你不介意我靠得这么近吧？)]");
				speech.add("[npc.speech(你喜欢我离你这么近，对吧？)]");
				speech.add("[npc.speech(没错，放轻松，让我来照顾你……)]");
				speech.add("[npc.speech(也许等你洗干净了，你会愿意和我做一些脏脏的事……)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
				if(firstWashing) {
					if(npc.hasBreasts()) {
						start.add("[npc.name]打开水龙头给你放水洗澡，靠近你的身后，然后立即将[npc.her]的[npc.breasts+]压在你的背上。"
								+ "显然，[npc.she]认为这是和你玩乐的好机会。[npc.she]伸手开始摸索你的身体同时诱惑地对着你的[pc.ear][npc.moaning]，");
					} else {
						start.add("[npc.name]打开水龙头给你放水洗澡，靠近你的身后，然后立即将[npc.her]的身体紧贴在你的背上。"
								+ "显然，[npc.she]认为这是和你玩乐的好机会。[npc.she]伸手开始摸索你的身体同时诱惑地对着你的[pc.ear][npc.moaning]，");
					}
				} else {
					if(npc.hasBreasts()) {
						start.add("向前走一步，[npc.name]将[npc.her]的[npc.breast+]压在你的背上，然后诱惑地[npc.moaning]，");
						start.add("[npc.name]在你的背上擦着肥皂，突然走上前来，将[npc.her]的[npc.breasts+]紧贴在你身上，发出诱人的[npc.moans]，");
						start.add("[npc.name]决定先从清洁你的背部开始，然后绕到你身后，身体前倾，将[npc.her][npc.breast+]压在你身上，[npc.moaning]，");
					} else {
						start.add("[npc.name]向前几步，将自己压在你背上，诱惑地[npc.moaning]，");
						start.add("[npc.name]在你背上擦着肥皂，却突然上前，把自己压在你身上，还发出诱人的[npc.moans]，");
						start.add("[npc.name]决定从清洁你的背部开始，绕到你身后，然后向前倾身，将自己压在你身上，[npc.moaning]，");
					}
				}
				speech.add("[npc.speech(哦~你喜欢我这样摸你，不是吗？)]");
				speech.add("[npc.speech(这么近距离的接触真是让我兴奋不已，你想在这之后做吗？)]");
				speech.add("[npc.speech(这真是让我欲火焚身……你想在这之后大干一场吗？)]");
				
			} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)
					|| npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
				if(npc.isShy()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("[npc.name]紧张地向前挪动，打开水龙头为你放水泡澡，然后开始帮你清洗[pc.hair(true)]和身体。"
									+ "为了让[npc.her]的声音透过流水声，[npc.she]略微提高[npc.her]的声音说，");
						} else {
							start.add("[npc.name]紧张地向前挪动，打开水龙头为你放水泡澡，然后帮你清洗身体。"
									+ "为了让[npc.her]的声音透过流水声，[npc.she]略微提高[npc.her]的声音说，");
						}
					} else {
						start.add("[npc.name]拿着一块肥皂紧张地向前挪动，帮助同伴"+(washingNice.size()>2?"slaves":"slave")+"来清洗你。[npc.her]拔高了一点声音说：");
						start.add("[npc.name]拿着一块肥皂走上前，紧张地清洁你的身体。[npc.she]在流水声中尖声道，");
						start.add("[npc.name]走到奴隶同伴的身边，边红着脸给你清洗，边说：");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(我喜欢离你这么近，[pc.name]。)]");
						speech.add("[npc.speech(我很高兴能像这样帮助你……)]");
						speech.add("[npc.speech(我很高兴能像这样帮助你……)]");
					} else {
						speech.add("[npc.speech(我没做错吧，没有吧，[pc.name]？)]");
						speech.add("[npc.speech(如果我做错了，请告诉我……)]");
						speech.add("[npc.speech(我希望这是你想要的清洗方式……)]");
					}
					
				} else if(npc.isKind()) {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("[npc.name]高兴地上前，打开水龙头为你放水洗澡，然后开始轻柔地清洗你的[pc.hair(true)]和身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高声音说道：");
						} else {
							start.add("[npc.name]高兴地上前，打开水龙头为你放水洗澡，然后开始轻柔地帮你清洗身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高声音说道：");
						}
					} else {
						start.add("[npc.name]拿着一块肥皂，高兴地上前帮助奴隶同伴清洗你。[npc.she]提高声音说：");
						start.add("[npc.name]高兴地拿着一块肥皂走上前清洗你。[npc.she]将声音拔高到盖过流水声，说：");
						start.add("[npc.name]走到奴隶同伴身边，清洗你的身体，[npc.she]微笑道：");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(我喜欢离你这么近，[pc.name]。如果你想要些别的服务，请告诉我。)]");
						speech.add("[npc.speech(我很高兴能这样帮助你；能成为你的人是我的荣幸。)]");
						speech.add("[npc.speech(请让我照顾你，[pc.name]；我想让你知道你对我有多重要。)]");
					} else {
						speech.add("[npc.speech(如果你想要别的服务，请让我知道，可以吗[pc.name]？)]");
						speech.add("[npc.speech(如果你想要别的服务，请让我知道。)]");
						speech.add("[npc.speech(让我照顾你，[pc.name]。)]");
					}
					
				} else {
					if(firstWashing) {
						if(Main.game.getPlayer().hasHair()) {
							start.add("[npc.name]走上前，打开水龙头为你放水洗澡，然后开始帮你清洗[pc.hair(true)]和身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高声音说道：");
						} else {
							start.add("[npc.name]走上前，打开水龙头为你放水洗澡，然后开始帮你清洗身体。"
									+ "为了让你能在流水声中听到，[npc.she]提高声音说道：");
						}
					} else {
						start.add("[npc.name]拿着一块肥皂走上前，帮助奴隶同伴清洗你。她拔高声音说：");
						start.add("[npc.name]拿着一块肥皂走近，开始清洗你的身体。[npc.she]将声音拔高到盖过流水声，说：");
						start.add("[npc.name]走到奴隶同伴身边，清洗你的身体，[npc.she]微笑道：");
					}
					if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
						speech.add("[npc.speech(我喜欢离你这么近，[pc.name]。如果你想要些别的服务，请告诉我。)]");
						speech.add("[npc.speech(我很高兴能这样帮助你；能成为你的人是我的荣幸。)]");
						speech.add("[npc.speech(请让我照顾你，[pc.name]；我想让你知道你对我有多重要。)]");
					} else {
						speech.add("[npc.speech(如果你还需要做点不同的事，叫我就好。)]");
						speech.add("[npc.speech(如果你还需要做点不同的事，叫我就好。)]");
						speech.add("[npc.speech(让我照顾你，[pc.name]。)]");
					}
				}
			}
			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append("");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		List<GameCharacter> washingRude = new ArrayList<>(slavesWashing);
		washingRude.removeAll(washingNice);
		for(GameCharacter npc : washingRude) {
			sb.append("<p>");
			List<String> start = new ArrayList<>();
			List<String> speech = new ArrayList<>();
			
				if(npc.isShy()) {
					if(firstWashing) {
						start.add("[npc.name]不情愿地走上前，打开水龙头，漫不经心地给你清洗。"
								+ "[npc.she]试图用流水声掩盖评价，嘀咕着：");
					} else {
						start.add("[npc.name]不情愿地走上前，盯着地面，漫不经心地给你清洗。[npc.she]发出疲惫的叹息，嘀咕着：");
						start.add("[npc.name]盯着地面，不情愿地走上前给你清洗。[npc.she]发出恼怒的叹息，低声嘀咕着：");
						start.add("显然，[npc.name]对此并不高兴，但[npc.she]还是走上前，漫不经心地开始帮你洗漱。[npc.her]小声嘀咕着，叹了口气，");
					}
					speech.add("[npc.speech(我讨厌这样……)]");
					speech.add("[npc.speech(我怎么会做现在这样的事……)]");
					speech.add("[npc.speech(这太糟糕了……)]");
					
				} else if(npc.isSelfish()) {
					if(firstWashing) {
						start.add("[npc.name]不情愿地走上前，打开水龙头，漫不经心地给你清洗。"
								+ "[npc.she]带着明显的敌意抱怨道，");
					} else {
						start.add("不情愿地走上前，[npc.name]漫不经心地开始帮你洗漱。发出一声疲惫的叹息，[npc.she]抱怨道，");
						start.add("[npc.name]不耐烦地转了转[npc.eyes]。[npc.her]走上前，一边不情愿地开始帮你洗漱，一边抱怨，");
						start.add("[npc.name]恼怒地叹了口气，走上前，心不在焉地开始帮你洗漱。[npc.she]丝毫不掩饰自己的恼怒，生气地说，");
					}
					speech.add("[npc.speech(我真的很讨厌这样，你懂吗？下次找其他奴隶来帮你吧！)]");
					speech.add("[npc.speech(你为什么非要让我这么做？我讨厌这样帮你，这还不清楚吗？)]");
					speech.add("[npc.speech(这真他妈的糟透了。下次让别的奴隶帮你洗怎么样？)]");
					
				} else {
					if(firstWashing) {
						start.add("不情愿地打开水龙头，[npc.name]走前一步，然后心不在焉地开始帮你洗漱。[npc.she]叹了口气，明显地表示不满，");
					} else {
						start.add("不情愿地走上前，[npc.name]心不在焉地开始帮你洗漱。[npc.she]发出一声疲惫的叹息，");
						start.add("[npc.name]不耐烦地转了转[npc.eyes]。[npc.her]走上前，一边不情愿地开始帮你洗漱，一边抱怨，");
						start.add("[npc.name]恼怒地叹了口气，走上前心不在焉地开始帮你洗漱。[npc.she]甚至没有试图掩饰[npc.her]的恼怒，叹了口气，");
					}
					speech.add("[npc.speech(你没其他人可以叫来做这事了吗？)]");
					speech.add("[npc.speech(我真希望你不要逼我这么做……)]");
					speech.add("[npc.speech(这太糟糕了……)]");
					speech.add("[npc.speech(我真的很讨厌这样做，你知道吗？)]");
				}

			sb.append(UtilText.parse(npc,Util.randomItemFrom(start)));
			sb.append("");
			sb.append(UtilText.parse(npc,Util.randomItemFrom(speech)));
			sb.append("</p>");
			firstWashing = false;
		}
		
		return sb.toString();
	}
	
	/** Calendar's associated animal-morphs are based on the twelve animals of the Chinese zodiac, with the Monkey being replaced with a demon, the Rooster with a harpy, and the Snake with a lamia.
	 *  The ordering of the demon and harpy have also been switched, so that October has demons.<br/>
	 *  There is also a 15% chance of giving a different, random animal-morph for each month.<br/>
	 * Animals are:<br/>
	 * Rat, Cow, Tiger, Rabbit, Dragon, Lamia (Snake), Horse, Sheep/Goat, Harpy (Rooster), Demon (Monkey), Dog, Pig
	 */
	private static String getCalendarImageDescription(Month month) {
		StringBuilder sb = new StringBuilder();

		sb.append("<p>"
				+ "你翻到"+month.getDisplayName(TextStyle.FULL, Locale.CHINESE)+"这页，插画上");
		
		if(Util.random.nextInt()<15) {
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append(UtilText.returnStringAtRandom(
						"是一条英俊的人鱼男子，正趴在一块波涛汹涌的岩石上，炫耀着自己的肌肉。",
						"是肌肉发达的驯鹿男，正咧嘴笑着向你展示他的大鸡巴。"));
			} else {
				sb.append(UtilText.returnStringAtRandom(
						"是一条美丽的人鱼，她在海浪拍打的岩石上快乐地展示着自己高耸丰满的大白兔。",
						"是一个曲线优美的驯鹿女，正弯腰趴在木桌上，向你展示她湿得泥泞不堪的热带雨林。"));
			}
			
		} else {
			switch(month) {
				case JANUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是身形结实的"+Subspecies.RAT_MORPH.getSingularMaleName(null)+"，他正一边抚摸着自己勃起的大鸡巴，一边调皮地冲你咧嘴笑。");
					} else {
						sb.append("是欲望高涨的"+Subspecies.RAT_MORPH.getSingularFemaleName(null)+"，她正趴在桌子上，挺起腰肢，向你展示不断流出蜜液的小缝。");
					}
					break;
				case FEBRUARY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是一个赤膊的"+Subspecies.COW_MORPH.getSingularMaleName(null)+"。"
								+ "他单肩扛着一棵砍倒的树，虬结的肌肉高高隆起。你在不要看挑战中彻底失败，目光滑向他的两腿之间，短裤布料上那个更大的凸起。");
					} else {
						sb.append("是一个黑白相间的"+Subspecies.COW_MORPH.getSingularFemaleName(null)+"，她坐在一个小挤奶凳上。"
								+ "她脸上洋溢着幸福的笑容，忙着捏弄充血的乳头，把一股股乳汁滋到金属桶里。");
					}
					break;
				case MARCH:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个凶神恶煞的"+Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger").getSingularMaleName(null)+"。"
								+ "他摆出一个霸气十足的姿势，向你露出呲牙咧嘴的笑容。他那巨大的猫科动物阴茎完全展现在你面前，让你心潮澎湃。");
					} else {
						sb.append("是个凶神恶煞的"+Subspecies.getSubspeciesFromId("innoxia_panther_subspecies_tiger").getSingularFemaleName(null)+"。"
								+ "她摆出一个霸气十足的姿势，向你露出呲牙咧嘴的笑容，显然，她的大乳房和紧致的阴部完全展现在你面前，让你心潮澎湃。");
					}
					break;
				case APRIL:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个英俊的"+Subspecies.RABBIT_MORPH.getSingularMaleName(null)+"握着硕大的鸡巴向你抛媚眼。");
					} else {
						sb.append("是三只面颊绯红的"+Subspecies.RABBIT_MORPH.getPluralFemaleName(null)+"四肢着地，相互挨在一起，向你展示着小穴。");
					}
					break;
				case MAY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是坐在黄金王座上的龙之强者，它守护着的珍奇瑰丽的宝藏堆成了山，王座就在山顶上。"
								+ "他从鳞片中伸出的巨屌一览无余，脸上带着一抹哂笑，投来期待的目光，仿佛在等你爬上前去饱尝一番。");
					} else {
						sb.append("是坐在黄金王座上的龙之强者，它守护着的珍奇瑰丽的宝藏堆成了山，王座就在山顶上。"
								+ "她鳞片中潜藏的湿润小穴一览无余，脸上带着一抹哂笑，投来期待的目光，仿佛在等你爬上前去饱尝一番。");
					}
					break;
				case JUNE:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是一只异域风情的雄性拉米亚。"
								+ "他显然已经被激起了性欲，分叉开来的阴茎从泄殖腔内探出，渴望着同某个人做爱；龟头在阳光下微微反射着亮光，粘滑的先走液已经开始涌出。");
					} else {
						sb.append("是一只异域风情的雌性拉米亚。"
								+ "她显然已经被激起了性欲， 渴望着同某个人做爱；她伸手张开了泄殖孔，向你展示着不断滴水的湿穴。");
					}
					break;
				case JULY:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个风流倜傥的"+Subspecies.HORSE_MORPH.getSingularMaleName(null)+"，一面将自己挺立的巨屌对向你，一面展示着美妙的肌肉。");
					} else {
						sb.append("是个身形矫健的"+Subspecies.HORSE_MORPH.getSingularFemaleName(null)+"，正倚在栏杆上，将马尾偏向一旁，动物样子的小穴在你面前一览无余。");
					}
					break;
				case AUGUST:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个绵羊男和山羊男，两人肩并肩站在一起，炫耀着挺立的阴茎，向你投来挑逗的眼神。");
					} else {
						sb.append("是个浑身软毛的绵羊女和山羊女，身体后仰双腿大开，炫耀着她们紧致湿润的小穴。");
					}
					break;
				case SEPTEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个非同寻常的男性化哈比"
								+ "尽管阴茎的大小并不值得大书特书，但那英俊潇洒的外貌，伴随着撩拨的眨眼，让你不禁小鹿乱撞。");
					} else {
						sb.append("是只闭月羞花的雌性哈比"
								+ "尽管她毫不抗拒地将湿润的小穴展露在你面前，但脸上的表情却显得居高临下，毫不示弱，"
									+ "你不禁在脑海中幻想，她定然会放出些无理的要求，以换取与她云雨的机会。");
					}
					break;
				case OCTOBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个英俊而健壮的"+Subspecies.DEMON.getSingularMaleName(null)+"，一面用手指在自己挺立的大屌上游走，一面投来了撩拨的眼神。");
					} else {
						sb.append("是个漂亮又健康的"+Subspecies.DEMON.getSingularFemaleName(null)+"，浑身一丝不挂，只戴着一顶女巫帽，一面用手指在湿润的小穴和巨大的乳房之间游走，一面投来了撩拨的眼神。");
					}
					break;
				case NOVEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是一个精力旺盛的"+Subspecies.DOG_MORPH.getSingularMaleName(null)+"，一面轻抚着自己坚挺的带结狗屌，一面向你露出了微笑。");
					} else {
						sb.append("是个性奋的"+Subspecies.DOG_MORPH.getSingularFemaleName(null)+"四肢着地，朝你撅起屁股，展示她湿润的小穴。");
					}
					break;
				case DECEMBER:
					if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
						sb.append("是个肌肉分明的猪男，期待地咧着嘴笑着，手上正爱抚着那巨大的阴茎，和其下一对充满了精液的巨大阴囊。");
					} else {
						sb.append("是个满脸通红的可爱猪女，正背靠着墙，伸手张开她粉红色的阴部给你看。");
					}
					break;
			}
		}
		
		sb.append("你凝视好一会儿插画，然后强迫自己移开视线，阅读插画下方的信息："
				+ "</p>");
		
		return sb.toString();
	}

	public static final DialogueNode ROOM = new DialogueNode("你的房间", "", false) {
		@Override
		public void applyPreParsingEffects() {
			makeupTarget = Main.game.getPlayer();
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();

			GenericPlace place = Main.game.getPlayerCell().getPlace();
			
			sb.append("<p>"
					+ "你的卧室在二楼，紧挨着入口大厅和主楼梯。放眼整栋豪宅也算是数一数二的大房间。"
					+ "房间正门前方是四扇宽大的窗棂，从这里看，楼下的庭院花园一览无余。而左侧的另一扇门则通向你的私人浴室。"
				+ "</p>");
			
			if(place.getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_PLAYER_ROOM_BED)) {
				sb.append("<p>"
							+ "房间内家具一应俱全，有两套抽屉和一个全高衣柜，这储物空间可真够宽敞了，塞多少东西也不在话下。"
							+ "除了这些家具外，还有沙发、配了椅子的书桌，以及一面全身镜。"
						+ "</p>");
			} else {
				sb.append("<p>"
						+ "右侧墙边有张大到没边的床。再就是两组抽屉和一个全高衣柜，塞多少东西都没问题吧。"
						+ "除了这些家具外，还有沙发、配了椅子的书桌，以及一面全身镜。"
					+ "</p>");
			}
			
			sb.append(
					"<p>"
						+ "就像以前的世界一样，灯光、暖气和自来水管道这些现代化设施都在，但不是用电力，而是用奥术力量驱动的。"
					+ "</p>");
			
			sb.append(LilayaHomeGeneric.getRoomModificationsDescription(false));

			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			if(!charactersPresent.isEmpty()) {
				List<String> names = new ArrayList<>();
				boolean soloSlave = charactersPresent.size()==1;
				for(NPC npc : charactersPresent) {
					names.add("<span style='color:"+npc.getFemininity().getColour().toWebHexString()+";'>"+npc.getName()+"</span>");
				}
				sb.append("<p>"
							+ "派到你房间的是你的奴隶："+Util.stringsToStringList(names, false)+"。");
				
				List<NPC> greetings = charactersPresent.stream().filter(npc -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_GREETING)).collect(Collectors.toList());
				names = new ArrayList<>();
				for(NPC npc : greetings) {
					names.add(npc.getName());
				}
				soloSlave = greetings.size()==1;
				if(!greetings.isEmpty()) {
					sb.append("接到迎你归来的指令，"
								+(soloSlave
										?UtilText.parse(charactersPresent.get(0), "[npc.she]向前走来，欢迎你回到家中。")
										:Util.stringsToStringList(names, false)+"向前走来，欢迎你回到家中。")
							+ "</p>");

					List<NPC> greetingsNice = greetings.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(NPC npc : greetingsNice) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						List<String> endSpeechGreetings = new ArrayList<>();
						
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							speechGreetings.add("[npc.speech(欢迎回来，[pc.name]，)]");
							speechGreetings.add("[npc.speech([style.morning]好，[pc.name]，)]");
							speechGreetings.add("[npc.speech(欢迎回来，[pc.name]，)]");
							
							if(npc.isFeminine()) {
								endGreetings.add("[npc.name]说，毕恭毕敬地向你行屈膝礼。");
								endGreetings.add("[npc.name]向你问好，如以往一般恭敬地行礼。");
								endGreetings.add("[npc.name]行了个礼，向你致意。");
							} else {
								endGreetings.add("[npc.name]说，毕恭毕敬地向你鞠躬。");
								endGreetings.add("[npc.name]向你问好，毕恭毕敬地朝你鞠躬。");
								endGreetings.add("[npc.name]向你问好，同时朝你鞠躬。");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							speechGreetings.add("[npc.speech(你这么快就回来了，[pc.name]？)]");
							speechGreetings.add("[npc.speech(你这就回来了，[pc.name]？)]");
							speechGreetings.add("[npc.speech(你很快就会回来看我们对吧，[pc.name]？)]");
							
							if(npc.isFeminine()) {
								endGreetings.add("[npc.name]害羞地问道，咬着唇朝你眨眼。");
								endGreetings.add("[npc.name]害羞地问道，然后微微转向一边，向你露出诱人的微笑。");
								endGreetings.add("[npc.name]害羞地问道，她向你眨眼，然后给你一个飞吻。");
							} else {
								endGreetings.add("[npc.name]挑逗地问，然后给你一个迷人的微笑。");
								endGreetings.add("[npc.name]挑逗地问，他喘着粗气，给你一个迷人的微笑。");
								endGreetings.add("[npc.name]挑逗地问，给你一个迷人的微笑。");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							speechGreetings.add("[npc.speech(你是回来找操的吗，[pc.name]？)]");
							speechGreetings.add("[npc.speech(哦，[pc.name]，你来这是为了挨操的对吗？)]");
							speechGreetings.add("[npc.speech(嘿，[pc.name]，你想被操对吗？)]");

							endGreetings.add("[npc.name]直截了当地问道，");
							endGreetings.add("[npc.name]直截了当地问道，");
							endGreetings.add("[npc.name]不知羞耻地问，");
							
							if(npc.isFeminine()) {
								endSpeechGreetings.add("[npc.speech(你知道我总是想要你……)]");
								endSpeechGreetings.add("[npc.speech(我无法停止对你的幻想……)]");
								endSpeechGreetings.add("[npc.speech(每次想起你我都欲火焚身……)]");
							} else {
								endSpeechGreetings.add("[npc.speech(我现在非常想要了你。)]");
								endSpeechGreetings.add("[npc.speech(我无法停止对你的幻想。)]");
								endSpeechGreetings.add("[npc.speech(只要你准备好了，我随时都可以。)]");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							speechGreetings.add("[npc.speech(你好，[pc.name]，)]");
							speechGreetings.add("[npc.speech([style.morning]好，[pc.name]，)]");
							speechGreetings.add("[npc.speech(欢迎回来，[pc.name]，)]");
							
							if(npc.isShy()) {
								endGreetings.add("[npc.name]看着地面，害羞地挪动着[npc.her][npc.feet]说。");
								endGreetings.add("[npc.name]平静地说，然后脸红着低头看向地面。");
								endGreetings.add("[npc.name]轻声与你打招呼，害羞地挪动着[npc.her][npc.feet]。");
								
							} else if(npc.isKind()) {
								endGreetings.add("[npc.name]说，友善地对你微笑，");
								endGreetings.add("[npc.name]以一种柔和的声音说，朝你温柔地笑了笑，继续道，");
								endGreetings.add("[npc.name]以一种柔情的音调开心地问候你，");
								
								endSpeechGreetings.add("[npc.speech(我能为你做什么吗？)]");
								endSpeechGreetings.add("[npc.speech([style.morning]怎么样？)]");
								endSpeechGreetings.add("[npc.speech(如果我能为你做什么，叫我就好！)]");
								
							} else {
								endGreetings.add("[npc.name]喊道，朝你开心地微笑。");
								endGreetings.add("[npc.name]开心地和你打招呼，笑着等候你的命令。");
								endGreetings.add("[npc.name]说道，对你微笑。");
								endGreetings.add("[npc.name]微笑，开心地说。");
								endGreetings.add("[npc.name]向你问好，微笑着，等待着你将要做什么。");
								endGreetings.add("[npc.name]微笑着问候你。");
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							speechGreetings.add("[npc.speech(你好呀，[pc.name]，)]");
							speechGreetings.add("[npc.speech([style.morning]好，[pc.name]，)]");
							speechGreetings.add("[npc.speech(欢迎回家，[pc.name]，)]");
							
							if(npc.isShy()) {
								endGreetings.add("[npc.name]说，瞟向地面，害羞地挪着[npc.feet]，而后继续，");
								endGreetings.add("[npc.name]悄声说，脸唰地红了，害羞地继续道，");
								endGreetings.add("[npc.name]轻声与你问好，害羞地挪着[npc.feet]，脸红着接着说：");
								
								endSpeechGreetings.add("[npc.speech(很高兴你回来了；我真的很想你……)]");
								endSpeechGreetings.add("[npc.speech(我很高兴能再看到你……)]");
								endSpeechGreetings.add("[npc.speech(你不在的时候我真的很想你……)]");
								
							} else if(npc.isKind()) {
								endGreetings.add("[npc.name]说，开心地对你微笑，接着说：");
								endGreetings.add("[npc.name]亲切地说，给你一个充满爱意的微笑，接着说：");
								endGreetings.add("[npc.name]亲切地问候你，给你一个充满爱意的微笑，接着说：");

								endSpeechGreetings.add("[npc.speech(能再见到你真好。你好像永远都不会离开！)]");
								endSpeechGreetings.add("[npc.speech(我很高兴再次见到你；我希望你下次别离开这么久！)]");
								endSpeechGreetings.add("[npc.speech(你知道的，我很想你； 谢天谢地，你终于回来了！)]");
								
							} else {
								endGreetings.add("[npc.name]高兴的说， 然后继续说，");
								endGreetings.add("[npc.name]开心地说，对你微笑然后继续说，");
								endGreetings.add("[npc.name]兴奋地和你打招呼，朝你笑了笑，继续说，");

								endSpeechGreetings.add("[npc.speech(我很高兴再次见到你！)]");
								endSpeechGreetings.add("[npc.speech(再次见到你让我很高兴！)]");
								endSpeechGreetings.add("[npc.speech(我希望你下次别离开我这么久，我很想你，你知道吗？)]");
							}
						}
						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append("");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						if(!endSpeechGreetings.isEmpty()) {
							sb.append("");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endSpeechGreetings)));
						}
						sb.append("</p>");
					}
					
					List<NPC> greetingsRude = new ArrayList<>(greetings);
					greetingsRude.removeAll(greetingsNice);
					for(NPC npc : greetingsRude) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						
							if(npc.isShy()) {
								speechGreetings.add("[npc.speech(啊，是你，)]");
								speechGreetings.add("[npc.speech(妈的，我还希望是别人呢，)]");
								speechGreetings.add("[npc.speech(哦不，不是你，)]");
								speechGreetings.add("[npc.speech(为什么[pc.she]一定要回来，)]");
								
								endGreetings.add("[npc.name]小声嘟囔，目光投向地面，挪了下[npc.her]的[npc.feet]。");
								endGreetings.add("[npc.name]喃喃自语着，然后看向地面，不愿抬头看你。");
								endGreetings.add("[npc.name]烦躁地咕哝着，挪着[npc.feet]，小声咒骂。");
								
							} else if(npc.isSelfish()) {
								speechGreetings.add("[npc.speech(好吧，你现在想要什么，[pc.name]？)]");
								speechGreetings.add("[npc.speech(<i>这次</i>你到底想要什么，[pc.name]？)]");
								speechGreetings.add("[npc.speech(那你怎么回来了？)]");
								speechGreetings.add("[npc.speech(你为什么要回来？)]");
								
								endGreetings.add("[npc.name]愤怒地问，甚至没有掩饰对你的敌意。");
								endGreetings.add("[npc.name]问，眯起[npc.eyes]，愤怒地瞪着你。");
								endGreetings.add("[npc.name]厉声说，她瞪着你，眼神充满怒火。");
								
							} else {
								speechGreetings.add("[npc.speech(做你该做的事，[pc.name]，)]");
								speechGreetings.add("[npc.speech(继续吧，[pc.name]，你想做什么都行，)]");
								speechGreetings.add("[npc.speech(让我们赶快结束这一切吧，)]");
								speechGreetings.add("[npc.speech(来吧，告诉我这次你想要什么，)]");
								
								endGreetings.add("[npc.name]叹了口气，愤怒地翻了个白眼。");
								endGreetings.add("[npc.name]恼怒地叹了口气，双手交叉在胸前，等着你下一步行动。");
								endGreetings.add("[npc.name]叹气，显然他因为分到你的房间而不开心。");
								endGreetings.add("[npc.name]说，发出一声疲惫的叹息，[npc.foot]敲着地面。");
								endGreetings.add("[npc.name]说，然后叹了一口气，显然不高兴地翻了个白眼。");
								endGreetings.add("[npc.name]说完， 抱着[npc.her][npc.arms]然后发出一声叹息。");
							}

						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append("");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						sb.append("</p>");
					}
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
			return getResponseRoom(responseTab, index);
		}
	};

	private static List<GameCharacter> slavesInRoom(int hour) {
		List<GameCharacter> charactersPresent = new ArrayList<>();
		
		for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			try {
				GameCharacter slave = Main.game.getNPCById(slaveId);
				if(slave.getSlaveJob(hour)==SlaveJob.BEDROOM) {
					charactersPresent.add(slave);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return charactersPresent;
	}
	
	private static List<GameCharacter> slavesWantingToSexPlayer(int hour) {
		List<GameCharacter> charactersPresent = new ArrayList<>();
		
		for(String slaveId : Main.game.getPlayer().getSlavesOwned()) {
			try {
				GameCharacter slave = Main.game.getNPCById(slaveId);
				if(slave.getSlaveJob(hour)==SlaveJob.BEDROOM
						&& slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_PLAYER)
						&& slave.isAttractedTo(Main.game.getPlayer())
						&& (slave.hasStatusEffect(StatusEffect.PENT_UP_SLAVE) || !((NPC)slave).hasFlag(NPCFlagValue.slaveBedroomHadSleepSex))) {
					charactersPresent.add(slave);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return charactersPresent;
	}
	
	public static final DialogueNode ROOM_SET_ALARM = new DialogueNode("调闹钟", "", true) {
		@Override
		public void applyPreParsingEffects() {
			super.applyPreParsingEffects();
			if(Main.game.getDialogueFlags().getSavedLong("player_phone_alarm") < 0) {
				// If unset, default to 8:00 AM
				Main.game.getDialogueFlags().setSavedLong("player_phone_alarm", 8*60);
			}
		}
		@Override
		public String getContent() {
			long alarmTime = Main.game.getDialogueFlags().getSavedLong("player_phone_alarm");
			String alarmTimeStr = Units.time(LocalTime.ofSecondOfDay(alarmTime*60));
			return "<div><p style='text-align:center;'>你拿出手机，打开闹钟界面，准备设置好起床的时间……</p></div>"
					+ "<div class='cosmetics-inner-container' style='margin:1% 10%; width:78%; padding:1%; box-sizing:border-box; position:relative;'>"
						+ "<p style='margin:0; padding:0;'>"
							+ "<b>调闹钟</b>"
						+"</p>"
						+ "<div class='container-full-width' style='width:35%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='PLAYER_ALARM_DECREASE_LARGE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldBad(-1小时)]"
							+ "</div>"
							+ "<div id='PLAYER_ALARM_DECREASE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldBadMinor(-5分钟)]"
							+ "</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:28%; margin:1%; padding:0; text-align:center; float:left; position:relative;'>"
							+ alarmTimeStr
						+ "</div>"
						+ "<div class='container-full-width' style='width:35%; text-align:center; float:left; position:relative; padding:0; margin:0;'>"
							+ "<div id='PLAYER_ALARM_INCREASE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldGoodMinor(+5分钟)]"
							+ "</div>"
							+ "<div id='PLAYER_ALARM_INCREASE_LARGE' class='normal-button' style='width:48%; margin:1%; padding:0;'>"
								+ "[style.boldGood(+1小时)]"
							+ "</div>"
						+ "</div>"
					+ "</div>";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("调闹钟", "你的闹钟会设置到你输入的时刻。", Main.game.getSavedDialogueNode());
				
			} else if(index == 2) {
				return new Response("删除闹钟", "你删除了闹钟，恢复到未设置状态。", Main.game.getSavedDialogueNode()) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().removeSavedLong("player_phone_alarm");
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
	
//	private static int getHourPlusSleep() {
//		return (Main.game.getHourOfDay() + (sleepTimeInMinutes/60))%24;
//	}
	private static List<GameCharacter> slavesPresentWhenGoingToSleep;
	private static List<GameCharacter> slavesPresentWhenWaking;
	private static List<GameCharacter> slavesToWakePlayer;
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNode("你的房间", "", false) {

		@Override
		public boolean isTravelDisabled() {
			return !slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty();
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			// Going to sleep:
			
			if(!slavesPresentWhenGoingToSleep.isEmpty()) {
				boolean soloSlave = slavesPresentWhenGoingToSleep.size()==1;
				
				List<String> names = new ArrayList<>();
				slavesPresentWhenGoingToSleep.stream().forEach((npc) -> names.add(npc.getName()));

				sb.append("<p>"
						+ "你觉得好疲倦，需要补充睡眠。于是晃到床边，扑通倒下，把被子扯过来，裹住自己。"
						+ "发现你想睡觉，"+Util.stringsToStringList(names, false)+"迅速拉上窗帘，然后朝你走来。"
					+ "</p>");

				// Sleeping arrangements:
				List<GameCharacter> floorSlaves = slavesPresentWhenGoingToSleep.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_FLOOR)).collect(Collectors.toList());
				List<GameCharacter> onBedSlaves = slavesPresentWhenGoingToSleep.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_ON_BED)).collect(Collectors.toList());
				if(!floorSlaves.isEmpty() || !onBedSlaves.isEmpty()) {
					sb.append("<p>");
					boolean soloFloor = floorSlaves.size()==1;
					List<String> floorNames = new ArrayList<>();
					floorSlaves.stream().forEach((npc) -> floorNames.add(npc.getName()));
					if(!floorSlaves.isEmpty()) {
						sb.append(soloFloor
								?UtilText.parse(floorSlaves.get(0), "[npc.Name]知道[npc.sheIs]不被允许睡在你的床上，于是就躺在地上睡了起来。")
								:Util.stringsToStringList(floorNames, false)+"知道自己不被允许睡在你的床上，于是就躺在地上睡了起来。");
					}
					if(!onBedSlaves.isEmpty()) {
						boolean soloOnBed = onBedSlaves.size()==1;
						List<String> onBedNames = new ArrayList<>();
						onBedSlaves.stream().forEach((npc) -> onBedNames.add(npc.getName()));
						if(!floorSlaves.isEmpty()) {
							sb.append(soloOnBed
									?UtilText.parse(onBedSlaves.get(0),  "[npc.name]跨过"+Util.stringsToStringList(floorNames, false)+"的身子，上了你的床，释然地长松一口气，蜷缩进了被子里。")
									:"跨过"+(soloFloor?UtilText.parse(floorSlaves.get(0), "[npc.name]"):"躺在地上的奴隶")
										+"，"+Util.stringsToStringList(onBedNames, false)+"爬上了你的床，全都释然地长松一口气，蜷缩进了被子里。");
						} else {
							sb.append(soloOnBed
									?UtilText.parse(onBedSlaves.get(0), "[npc.Name]知道[npc.sheIs]允许睡在你的床上，于是爬上了床，释然地长松一口气，蜷缩进了被子。")
									:Util.stringsToStringList(onBedNames, false)+"知道自己都能睡在床上，于是爬上床，全都释然地长松一口气，蜷缩进了被子里。");
						}
					}
					sb.append("</p>");
				}

				List<GameCharacter> inBedSlaves = slavesPresentWhenGoingToSleep.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_SLEEP_IN_BED)).collect(Collectors.toList());
				if(!inBedSlaves.isEmpty()) {
					sb.append("<p>");
					boolean soloInBed = inBedSlaves.size()==1;
					List<String> inBedNames = new ArrayList<>();
					inBedSlaves.stream().forEach((npc) -> inBedNames.add(npc.getName()));
					sb.append(soloInBed
							?UtilText.parse(inBedSlaves.get(0), "在得到你的明确许可后，[npc.name]拉开羽绒被的一角，然后飞速钻进被窝里。")
							:"在得到你的明确许可后，"+Util.stringsToStringList(inBedNames, false)
								+"拉开羽绒被最上面的两个角，然后一齐飞速钻进被窝里。");
					sb.append("</p>");
					
					List<GameCharacter> niceSlaves = inBedSlaves.stream().filter(npc -> npc.getObedienceBasic()==ObedienceLevelBasic.OBEDIENT || npc.getAffectionLevelBasic(Main.game.getPlayer())==AffectionLevelBasic.LIKE).collect(Collectors.toList());
					for(GameCharacter npc : niceSlaves) {
						sb.append("<p>");
						List<String> speechGreetings = new ArrayList<>();
						List<String> endGreetings = new ArrayList<>();
						List<String> endSpeechGreetings = new ArrayList<>();
						
						if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(请容许我为您取暖，[pc.name]，)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(请容许我为您侍寝，[pc.name]，)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(请容许我为您取暖，[pc.name]，)]"));
							
							endGreetings.add(UtilText.parse(npc, "[npc.name]主动开口，然后依偎在你身上。"));
							endGreetings.add(UtilText.parse(npc, "[npc.name]主动提议，在被子里挪动身体，依偎在你身上。"));
							endGreetings.add(UtilText.parse(npc, "[npc.name]说完，便依偎在你身上，身子紧紧贴在一起。"));
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(叫醒您后，或许我们还能在床上做些别的？)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(您休息过后，或许我们两个可以活动一下？)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech([pc.name]，我叫醒您后，是否愿意和我舒服舒服呢？)]"));
							
							endGreetings.add(UtilText.parse(npc, "[npc.name]紧紧贴着你，调笑道。"));
							endGreetings.add(UtilText.parse(npc, "[npc.name]引诱般暗示道，说完就在被子下挪动了一下身体，更贴近你了。"));
							endGreetings.add(UtilText.parse(npc, "[npc.name]玩笑后，在被子下面挪动着身子，紧紧贴在了你身上。"));
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							if(npc.hasBreasts()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(如果你愿意的话，可以枕在我胸上，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(好好摸摸我的奶子吧，这会让你睡得更香，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(来嘛，睡前玩玩我的奶子好不好，)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]提议完，便将[npc.breastSize]的乳房紧贴在你身上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]提出了无耻建议，之后立刻钻进被子里，将[npc.breastSize]的乳房紧紧地压在你身上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]说完便依偎上来，那[npc.breastSize]的乳房压在了你的身上。"));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(等你睡醒我们再好好干一场，好吗？)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(等你休息好，我们就能好好地爽上一次，对吧？)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(等你起床，我们就可以好好爽一发，对吧？)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]直截了当地提问，并漫不经心地摸起你的[pc.leg]。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]厚着脸皮建议完之后便迅速地滑进被子里，将自己压在你身上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]说完后便依偎上来，随后压在了你身上。"));
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(npc.isShy()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(嗯……[pc.name]，如果我占了太多位置还请告诉我。)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(如果我有妨碍到你的话，还请告诉我一声。)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(如果我靠的太近的话还请告诉我一声，)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]羞答答地说，拉起被角遮掩[npc.her]羞红的[npc.face]。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]说道，同时紧紧地抓着被子，随后靠在你肩上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]小声说道，[npc.she]与你一同躺在被窝里，你注意到[npc.she]的脸正羞红到发烫。"));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(请让我再靠近你一点，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(我只是需要适应一下，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(好了，这样就好多了，)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]在说完之后便枕在了你的[pc.breasts]上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]说，随即溜进被窝里，压在你身上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]在微笑着说完之后便将整个身体靠在了你的身上。"));
							}
							
						} else if(npc.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(我喜欢和你一起睡觉，[pc.name]，)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(像这样和你躺在一起真是太好了，)]"));
							speechGreetings.add(UtilText.parse(npc, "[npc.speech(和你一起入睡是一天中最美好的事，)]"));
							
							if(npc.isShy()) {
								endGreetings.add(UtilText.parse(npc, "[npc.name]羞答答地说，拉起被角遮掩[npc.her]羞红的[npc.face]，整个身子靠在你身上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]在说着的同时紧紧的抓着被子，随后靠在了你的肩膀上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]小声说道，[npc.she]与你一同躺在被窝里，你注意到[npc.she]的脸正羞红到发烫。"));
								
							} else {
								endGreetings.add(UtilText.parse(npc, "[npc.name]说，[npc.she]愉悦地微笑，整个身子依偎着你。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]说，随即溜进被窝里，情意绵绵地压在你身上。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]情意绵绵地说，随后依偎在你身上，转而压住你。"));
							}
						}
						if(npc.getTailType().isSuitableForSleepHugging()) {
							endSpeechGreetings.add(UtilText.parse(npc, "[npc.race]发出饕足的叹息，[npc.tail+]缩进被子里，又压在你身上。[npc.her]给了你一个爱的尾抱。"));
							endSpeechGreetings.add(UtilText.parse(npc, "[npc.her]把[npc.tail+]缩进被子，缠住你的[pc.leg]，而后靠在你身旁。"));
							endSpeechGreetings.add(
									UtilText.parse(npc, "[npc.her]对[npc.tail+]的控制能力简直让人印象深刻，[npc.race]迅速用尾巴缠绕住你的下半身，然后将整个身体贴了上来。"));
						}
						
						sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
						sb.append("");
						sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						if(!endSpeechGreetings.isEmpty()) {
							sb.append("");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endSpeechGreetings)));
						}
						sb.append("</p>");
					}
					
					List<GameCharacter> rudeSlaves = new ArrayList<>(inBedSlaves);
					rudeSlaves.removeAll(niceSlaves);
					for(GameCharacter npc : rudeSlaves) {
						sb.append("<p>");
							List<String> speechGreetings = new ArrayList<>();
							List<String> endGreetings = new ArrayList<>();
							if(npc.isShy()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(请给我留半张床，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(我真希望你别靠太近，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(你别碰我，)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]紧张地恳求，又翻身背对着你。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]嘀咕着，从你身边悄悄溜走。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]羞怯地恳求着，在被子下悄悄挪动，翻身背对着你。"));
								
							} else if(npc.isSelfish()) {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(你睡觉的时候别滚到我这边，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(给我留半张床，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(你那边位置好大，别挤我，)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]警告道，发出恼怒的喷气声，然后悄悄地从你身边离开。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]直言不讳地说道，难过地叹了口气，悄悄从你身边离开。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]说，又翻了个身背对你。"));
								
							} else {
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(我只是想有点地方睡觉，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(我就待在我那边，)]"));
								speechGreetings.add(UtilText.parse(npc, "[npc.speech(我会给你留足够的空间，不用担心，)]"));
								
								endGreetings.add(UtilText.parse(npc, "[npc.name]说道，发出一声疲惫的叹息，悄悄地从你身边离开。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]叹了口气，稍微挪了下，然后翻身背对着你。"));
								endGreetings.add(UtilText.parse(npc, "[npc.name]叹了口气，背向你，慢慢挪向床边。"));
							}
							sb.append(UtilText.parse(npc,Util.randomItemFrom(speechGreetings)));
							sb.append("");
							sb.append(UtilText.parse(npc,Util.randomItemFrom(endGreetings)));
						sb.append("</p>");
					}
				}
				
			} else {
				sb.append("<p>"
						+ "你设好手机闹铃，然后才拉上窗帘，躺在床上，闭上眼睛。"
						+ "在莉莱雅的家里，你感到安心落意，想着最近的所有遭遇，很快就坠入了梦乡……"
					+ "</p>");
				
			}
			
			
			// Sleeping:
			sb.append("<p>"
					+ "[style.italics(……)]"
				+ "</p>");
			
			
			// Waking up:
			if(!slavesPresentWhenWaking.isEmpty()) {
				int hour = Main.game.getHourOfDay();
				String morningString = "晚上";
				if(hour<4) {
					morningString = "晚上";
				} else if(hour<12) {
					morningString = "早上";
				}else if(hour<17) {
					morningString = "下午";
				}

				List<GameCharacter> hornySlaves = slavesWantingToSexPlayer(Main.game.getHourOfDay());
				
//				boolean soloSlave = slavesToWakePlayer.size()==1;
				
				if(!slavesToWakePlayer.isEmpty()) {
					List<String> names = new ArrayList<>();
					slavesToWakePlayer.stream().forEach((npc) -> names.add(npc.getName()));
					
					GameCharacter slaveWaking = Util.randomItemFrom(slavesToWakePlayer);
//					sb.append("<p>"
//							+ (soloSlave
//								?UtilText.parse(slaveWaking,
//									"With you and [npc.name] now in your respective positions, you ask [npc.herHim] to wake you at the time that you'd like to be getting up."
//										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep...")
//								:UtilText.parse(slaveWaking,
//									"With your slaves now being settled into their respective positions, you ask [npc.name] to wake you at the time that you'd like to be getting up."
//										+ " After [npc.sheHas] reassured you that [npc.she] won't let you sleep in too late, you let out a contented sigh, close your eyes, and start to drift off to sleep..."))
//						+ "</p>"
//						+ "<p>"
//							+ "[style.italics(...)]"
//						+ "</p>");
					
					if(hornySlaves.isEmpty()) {
						sb.append("<p>");
						if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_PROFESSIONAL)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech([pc.Name]？该起床啦，)]"
									+ "你听到呼唤你的声音，缓缓睁开了[pc.eyes]，[npc.name]正在向你微笑。"
									+ "[npc.she]见你醒了，便转身拉开窗帘，[npc.she]喊道，[npc.speech("+morningString+"好，[pc.name]！)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SEDUCTIVE)) {
							sb.append(UtilText.parse(slaveWaking, 
									"[npc.speech(醒醒，[pc.name]，该起床了，)]"
									+ "你听见[pc.ear]边撩人的低语声，缓缓睁开[pc.eyes]，[npc.name]正咬着嘴唇朝你微笑。"
									+ "[npc.she]见你醒了，一手抚上你的[pc.chest]，而后转身拉开窗帘，[npc.she]撩拨着你，"
									+ "[npc.speech("+morningString+"好，[pc.name]……今天想不想做点<i>特别的事</i>？)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_SLUTTY)) {
							sb.append(UtilText.parse(slaveWaking,
									"[npc.speech(你要是现在起来，我们还来得及小干一场，可以吗？)]"
									+ "你听到呼唤的声音，缓缓睁开[pc.eyes]，[npc.name]正饥渴地向你微笑。"
									+ "[npc.she]见你醒了，俯身亲吻你[pc.lips]，而后转身拉开窗帘，[npc.she]撩拨着你，"
									+ "[npc.speech("+morningString+"好，[pc.name]！所以你愿意操我了吗？可以吗？)]"));
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_STANDARD)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]？喂，听得见吗，[pc.name]？该起床了，)]"
										+ "你听见焦急的声音呼唤你，缓缓睁开[pc.eyes]，[npc.name]担忧地看着你。"
											+ "[npc.she]见你醒了，长出一口气，转头迅速拉开窗帘，[npc.speech("+morningString+"好，[pc.name]！)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking, 
										"[npc.speech([pc.Name]？喂喂，[pc.name]，该起床了，)]"
										+ "你听到呼唤你的声音，缓缓睁开了[pc.eyes]，[npc.name]正在向你微笑。"
												+ "[npc.she]见你醒了，便转身拉开窗帘，[npc.she]喊道，[npc.speech("+morningString+"好，[pc.name]！)]"));
							}
						} else if(slaveWaking.hasSlavePermissionSetting(SlavePermissionSetting.BEHAVIOUR_WHOLESOME)) {
							if(slaveWaking.isShy()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]？嗯……喂喂，你好困哦……该起床了，)]"
										+ "你听见呼唤你的轻声细语，缓缓睁开[pc.eyes]，[npc.name]正俯身看着你。"
												+ "[npc.she]见你醒了，长出一口气，转头迅速拉开窗帘，接着轻声呼唤，"
												+ "[npc.speech("+morningString+"好，[pc.name]……祝你今天顺利……)]"));
							} else if(slaveWaking.isKind()) {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]？醒醒，瞌睡虫！你打算在床上睡一辈子吗？)]"
										+ "你听到呼唤你的声音，缓缓睁开了[pc.eyes]，[npc.name]正在向你微笑。"
												+ "[npc.she]看到你醒来，轻柔地摸了摸你的脸，迅速拉开窗帘，转身呼喊，"
												+ "[npc.speech("+morningString+"好，[pc.name]！祝你度过愉快的一天！)]"));
							} else {
								sb.append(UtilText.parse(slaveWaking,
										"[npc.speech([pc.Name]？醒醒，瞌睡虫！你打算在床上睡一辈子吗？)]"
										+ "你听到呼唤你的声音，缓缓睁开了[pc.eyes]，[npc.name]正在向你微笑。"
												+ "[npc.she]见你醒了，便迅速转身，拉开窗帘，"
												+ "[npc.speech("+morningString+"好，[pc.name]！祝你度过愉快的一天！)]"));
							}
						}
						sb.append("</p>");

						sb.append(UtilText.parse(slaveWaking,
								"<p>"
									+ "尽管你躺着很舒服，但你决定听[npc.name]的话，于是你只赖床了一分钟。"
									+ "你心满意足地打了个哈欠，拉长[pc.arms]，让"+ Util.stringsToStringList(names, false)+"帮你收拾东西。你伸伸懒腰，准备再次出发……"
								+ "</p>"));
					}
					
				} else {
//					sb.append("<p>"
//								+ (soloSlave
//									?UtilText.parse(slavesPresentWhenGoingToSleep.get(0),
//											"With you and [npc.name] now in your respective positions, you set your phone's alarm and place it on the bedside cabinet beside you."
//											+ " Letting out a contented sigh, you close your eyes and start to drift off to sleep...")
//									:"With your slaves now being settled into their respective positions, you set your phone's alarm and place it on the bedside cabinet beside you."
//										+ " Letting out a contented sigh, you close your eyes and start to drift off to sleep...")
//							+ "</p>"
//							+ "<p>"
//								+ "[style.italics(...)]"
//							+ "</p>");

					if(hornySlaves.isEmpty()) {
						List<String> names = new ArrayList<>();
						slavesPresentWhenWaking.stream().forEach((npc) -> names.add(npc.getName()));
						sb.append(
								"<p>"
									+ "<i>哔哔……哔哔……哔——</i>"
								+ "</p>"
								+ "<p>"
									+ "你翻了个身摸索手机，关掉闹钟，叹了口气，重新躺回床上。"
									+ "你睡得很舒服。你决定最好还是起床，于是只赖床了一分钟。"
									+ "你心满意足地打了个哈欠，拉长[pc.arms]，让"+ Util.stringsToStringList(names, false)+"帮你收拾东西。你伸伸懒腰，准备再次出发……"
								+ "</p>");
					}
				}

				if(!hornySlaves.isEmpty()) {
					Collections.shuffle(hornySlaves);
					boolean soloHornySex = hornySlaves.size()==1;
					List<String> hornyNames = new ArrayList<>();
					hornySlaves.stream().forEach((npc) -> hornyNames.add(npc.getName()));
					if(Main.game.getPlayer().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						if(!hornySlaves.get(0).isMute()) {
							sb.append(UtilText.parse(hornySlaves,
									"<p>"
										+ "[npc.speech(~唔姆！~这就对了……不要醒过来……)]"
									+ "</p>"
									+ "<p>"
										+ "作为深度睡眠者，[npc.name]说出的这些话根本不足以把你唤醒。"
										+ (soloHornySex
												?"[npc.name]爬上了你的床，饥渴地朝你露出了笑容。"
												:"爬上了你的床，"+Util.stringsToStringList(hornyNames, false)+"饥渴地朝你露出了笑容。")
										+ "随着一声渴望的[npc.moan]，[npc.race]开始一边小心翼翼地抚摸你，一边急切地喘息着，"
										+ "[npc.speech(我要操你了，[pc.name]，而且你不可能知道这件事……)]"
									+ "</p>"));
						} else {
							sb.append(UtilText.parse(hornySlaves,
									"<p>"
										+ "悄悄地潜至你的床边，[npc.name]低头看着熟睡中的你，发出了一声兴奋的呻吟。"
										+ "作为深度睡眠者，这些噪音根本不足以把你唤醒。"
										+ (soloHornySex
												?"[npc.name]爬上了你的床，饥渴地朝你露出了笑容。"
												:"爬上了你的床，"+Util.stringsToStringList(hornyNames, false)+"饥渴地朝你露出了笑容。")
										+ "[npc.her]舔了舔[npc.lips]，[npc.race]开始一边小心翼翼地抚摸你，一边急切地喘息，[npc.she]准备在你睡觉时操你……"
									+ "</p>"));
						}
						
					} else {
						sb.append(UtilText.parse(hornySlaves,
								"<p>"
									+ (soloHornySex
											?"[npc.speech(~唔姆！~这就对了……我抓到你了……)]"
											:"[npc2.speech(~唔姆！~这就对了……我抓到你了……)][npc2.name]的声音进入你沉睡的大脑。")
								+ "</p>"
								+ "<p>"
									+ "你恍然间不确定是否身处梦中，旋即你感受到身上持续承受奇怪的重压。你瞬间醒来。"
									+ (soloHornySex
											?"你睁开双眼，看见坐在胸口上的[npc.name]对你饥渴地笑着。"
											:"你睁开双眼，看见"+Util.stringsToStringList(hornyNames, false)+"趴在你身上，他们都饥渴地笑着。")
									+ "[npc.name]发出一声淫荡的[npc.moan]，饥渴地喊道，"
									+ "[npc.speech("+morningString+"好啊，[pc.name]！我希望你做好大干一场的准备了！)]"
								+ "</p>"));
					}
					return sb.toString();
				}
				
			} else {
				sb.append("<p>"
						+ "<i>哔哔……哔哔……哔——</i>"
					+ "</p>"
					+ "<p>"
						+ "你翻了个身摸索手机，关掉闹钟，叹了口气，重新躺回床上。"
						+ "你睡得很舒服。你决定最好还是起床，于是只赖床了一分钟。"
						+ "你拉开窗帘，开始收拾东西。你伸伸懒腰，准备再次出发……"
					+ "</p>");
			}
			
			if(!slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty()) {
				sb.append("<p style='text-align:center;'>"
							+ "[style.italicsGood(你感觉焕然一新！)]"
						+ "</p>");
			}
			
			return sb.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!slavesWantingToSexPlayer(Main.game.getHourOfDay()).isEmpty()) {
				return null;
			}
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			List<GameCharacter> hornySlaves = slavesWantingToSexPlayer(Main.game.getHourOfDay());
			if(!hornySlaves.isEmpty()) {
				boolean soloSex = hornySlaves.size()==1;
				List<String> names = new ArrayList<>();
				hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
				List<GameCharacter> spectators = new ArrayList<>(slavesInRoom(Main.game.getHourOfDay()));
				spectators.removeAll(hornySlaves);
				
				UtilText.addSpecialParsingString(String.valueOf(soloSex), true);
				UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
				
				if(index==1) {
					if(Main.game.getPlayer().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						return new ResponseSex("睡奸",
								(soloSex
									?UtilText.parse(hornySlaves.get(0), "[npc.Name]在你入睡的时候操了你……")
									:Util.stringsToStringList(names, false)+"在你入睡的时候操了你……"),
								false,
								false,
								new SMGeneric(
										hornySlaves,
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.PREFER_MISSIONARY){
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_GENTLE;
										}
										return super.getStartingSexPaceModifier(character);
									}
									@Override
									public SexPace getForcedSexPace(GameCharacter character) {
										if(!character.isPlayer()) {
											return SexPace.DOM_GENTLE;
										}
										return super.getForcedSexPace(character);
									}
									@Override
									public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
										Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
										map.put(ImmobilisationType.SLEEP, new HashMap<>());
										map.get(ImmobilisationType.SLEEP).put(hornySlaves.get(0), Util.newHashSetOfValues(Main.game.getPlayer()));
										return map;
									}
								},
								POST_WAKE_UP_SEX,
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "BED_SEX_START_SLEEP", hornySlaves)) {
							@Override
							public void effects() {
								Main.game.getPlayer().addStatusEffect(StatusEffect.SLEEPING_HEAVY, -1);
								for(GameCharacter slave : hornySlaves) {
									((NPC)slave).addFlag(NPCFlagValue.slaveBedroomHadSleepSex);
								}
							}
						};
						
					} else {
						return new ResponseSex("性爱",
								(soloSex
									?UtilText.parse(hornySlaves.get(0), "[npc.Name]强压在你身上……")
									:Util.stringsToStringList(names, false)+"强压在你身上……"),
								!hornySlaves.stream().anyMatch(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)),
								!hornySlaves.stream().anyMatch(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)),
								new SMGeneric(
										hornySlaves,
										Util.newArrayListOfValues(Main.game.getPlayer()),
										spectators,
										null,
										ResponseTag.PREFER_MISSIONARY),
								POST_WAKE_UP_SEX,
								UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "BED_SEX_START", hornySlaves));
					}
					
				} else if(index==2) {
					if(Main.game.getPlayer().hasTrait(Perk.HEAVY_SLEEPER, true)) {
						return new Response("拒绝",
								"你处于深度睡眠之中，无法拒绝！",
								null);
						
					} else if(hornySlaves.stream().anyMatch(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST))) {
						GameCharacter rapist = hornySlaves.stream().filter(s->s.isWillingToRape(Main.game.getPlayer()) && s.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RAPIST)).findFirst().get();
						return new Response("拒绝",
								UtilText.parse(rapist, "由于你给了[npc.nameIsFull]强奸的许可，[npc.herHim]不会接受你的反对意见！"),
								null);
						
					} else {
						return new Response("拒绝",
								(soloSex
										?UtilText.parse(hornySlaves.get(0), "你现在没心情做，于是坚决地命令[npc.name]停下。")
										:"你现在没心情做，于是坚决地命令"+ Util.stringsToStringList(names, false)+"停下。"),
								REFUSE_SLAVE_SEX);
						
					}
				}
				return null;
			}
			return getResponseRoom(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode POST_WAKE_UP_SEX = new DialogueNode("结束", "", false) {
		@Override
		public void applyPreParsingEffects() {
			List<GameCharacter> hornySlaves = new ArrayList<>(Main.sex.getDominantParticipants(false).keySet());
			boolean soloSex = hornySlaves.size()==1;
			List<String> names = new ArrayList<>();
			hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
			
			UtilText.addSpecialParsingString(String.valueOf(soloSex), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "POST_WAKE_UP_SEX", hornySlaves));
			
			Main.game.getPlayer().wakeUp();
		}
		@Override
		public String getDescription() {
			if(Main.game.getPlayer().isAsleep()) {
				return "你继续睡觉……";
			}
			return "你无力地倒回床上……";
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode REFUSE_SLAVE_SEX = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			List<GameCharacter> hornySlaves = new ArrayList<>(slavesWantingToSexPlayer(Main.game.getHourOfDay()));
			boolean soloSex = hornySlaves.size()==1;
			List<String> names = new ArrayList<>();
			hornySlaves.stream().forEach((npc) -> names.add(npc.getName()));
			
			UtilText.addSpecialParsingString(String.valueOf(soloSex), true);
			UtilText.addSpecialParsingString(Util.stringsToStringList(names, false), false);
			
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "REFUSE_SLAVE_SEX", hornySlaves));
			
			Main.game.getPlayer().wakeUp();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(responseTab, index);
		}
		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER = new DialogueNode("你的房间", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<GameCharacter> slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"你没多少时间可用，又想洗个澡。于是你走进宽敞的套间浴室，决定快速冲个澡。"
								+ "你脱下衣服，把它们放在门边，然后走进铺设着豪华的大理石和精致玻璃的步入式淋浴间。");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getShowerSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "在打开水龙头感受着温水流淌在皮肤上的感觉后，你发出轻松的叹息声。"
							+ "你不想在淋浴间花太多时间，所以你选择来一场快速清洁……"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] showerSlots = new SexSlot[] {
					SexSlotStanding.STANDING_SUBMISSIVE,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND,
					SexSlotStanding.STANDING_SUBMISSIVE_TWO,
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO};
			
			if(index==1) {
				return new Response("完成", "洗好澡回房间。", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"你不想在浴室里待太久，在叫[npc.name]关掉水龙头后就来帮你擦干身体。"
													+ "做完这些之后，你们俩穿好衣服然后回到你的房间……"));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"你不想花太多时间来洗澡，于是你让你的奴隶关掉水龙头并把你的身体擦干。"
													+ "做完这些后，你们穿好衣服然后回到你的房间……");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"你在浴室里好好洗了一会儿，然后关掉花洒，用松软的毛巾擦干身体，穿好衣服，回到自己的房间……");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} else if(index==2) { // If you change this, be aware that it is called in AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER
				if(slavesWashing.isEmpty()) {
					return new Response("做爱", "你没让奴隶跟到卧室里给你洗澡，所以没人跟你做爱……", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size() && i<4; i++) {
					slaveSlots.put(slavesWashing.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("性爱",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "和[npc.name]边洗澡边做支配型性爱。")
							:"和你的奴隶在浴室里做爱。",
						true, false,
						new SMShower(SexPosition.STANDING,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								slaveSlots),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_SHOWER_SEX_AS_DOM", slavesWashing));
				
			} else if(index==3) {  // If you change this, be aware that it is called in AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER
				if(slavesWashing.isEmpty()) {
					return new Response("服从型性爱", "你没让奴隶跟到卧室里给你洗澡，所以没人跟你进行服从型性爱……", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("服从性爱",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "[npc.name]没被你吸引，不想跟你做支配型性爱……")
								:"和你的奴隶在浴室里做爱。",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), showerSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("服从性爱",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "[npc.name]会边洗澡边强势操你。")
										:"你的奴隶会边洗澡边强势操你。",
						true, true,
						new SMShower(SexPosition.STANDING,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT))),
						null,
						null,
						AFTER_SHOWER_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_SHOWER_SEX_AS_SUB", attractedSlaves));
			}
			
			return null;
		
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_THOROUGH_SHOWER = new DialogueNode("你的房间", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			List<NPC> charactersPresent = LilayaHomeGeneric.getSlavesAndOccupantsPresent();
			List<GameCharacter> slavesWashing = charactersPresent.stream().filter((npc) -> npc.hasSlaveJobSetting(SlaveJob.BEDROOM, SlaveJobSetting.BEDROOM_HELP_WASH)).collect(Collectors.toList());
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"你想花点时间彻底清洁一下自己，于是走进宽敞的套间浴室，决定洗个长时淋浴。"
						+ "你脱下衣服，把它们放在门边，然后走进铺设着豪华的大理石和精致玻璃的步入式淋浴间。");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getShowerSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "打开水龙头，感受着温水流淌在裸露的身体上，你发出一声满足的叹息。"
							+ "你决定抽出一些时间来放松一下，这并不是什么坏事，于是你不慌不忙地开始彻底清洁自己。"
						+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("完成", "洗好澡回房间。", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"确保身体的每一寸肌肤都光洁如新后，你让[npc.name]关闭水龙头，帮你擦干身体。"
													+ "做完这些之后，你们俩穿好衣服然后回到你的房间……"));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"在确保身体的每一寸肌肤都光洁如新之后，你让奴隶们关掉水龙头，帮你擦干身体。"
													+ "做完这些后，你们穿好衣服然后回到你的房间……");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"你在浴室里好好洗了一会儿，然后关掉花洒，用松软的毛巾擦干身体，穿好衣服，回到自己的房间……");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} if(index==2) {
				return AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER.getResponse(responseTab, index);
				
			} else if(index==3) {
				return AUNT_HOME_PLAYERS_ROOM_QUICK_SHOWER.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SHOWER_SEX = new DialogueNode("完成", "", true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 240+30);
		}
		@Override
		public String getDescription() {
			return "你的奴隶都玩尽兴了，提醒你还另有事情要做……";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "AFTER_SHOWER_SEX", slavesWashing);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "你已经爽过了，是时候回房间了。", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_BATH = new DialogueNode("你的房间", "", true) {
		@Override
		public void applyPreParsingEffects() {
			// Make sure that the washing slaves don't disappear during this scene:
			for(GameCharacter slave : slavesWashing) {
				slave.setLocation(Main.game.getPlayer(), false);
			}
		}
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>");
				UtilText.nodeContentSB.append(
						"你想花点时间放松一下，好好洗个澡，于是来到你的私人浴室。"
						+ "脱掉衣服后，你将衣物放在门边，然后步入豪华大理石浴缸。");
			UtilText.nodeContentSB.append("</p>");
			
			if(!slavesWashing.isEmpty()) {
				UtilText.nodeContentSB.append(getBathSlavesDescription(slavesWashing));
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "你打开水龙头放出洗澡水，浸入热水中，发出一声满足的叹息。"
							+ "你决定抽出一些时间来放松一下，这并不是什么坏事，于是你不慌不忙地开始彻底清洁自己……"
						+"</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			SexSlot[] bathSlots = new SexSlot[] {
					SexSlotLyingDown.MISSIONARY,
					SexSlotLyingDown.BESIDE,
					SexSlotLyingDown.BESIDE_TWO,
					SexSlotLyingDown.BESIDE_THREE};
			
			if(index==1) {
				return new Response("结束", "洗完澡回到房间。", ROOM) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append("<p>");
							if(!slavesWashing.isEmpty()) {
									if(slavesWashing.size()==1) {
										Main.game.getTextStartStringBuilder().append(UtilText.parse(slavesWashing,
												"在确保身体的每一寸肌肤都光洁如新后，你拔出塞子，离开浴缸，并让[npc.name]帮你擦干身体。"
														+ "做完这些之后，你们俩穿好衣服然后回到你的房间……"));
									} else {
										Main.game.getTextStartStringBuilder().append(
												"在确保身体的每一寸肌肤都光洁如新之后，你拔掉塞子，从浴缸中出来，让奴隶帮你擦干身体。"
														+ "做完这些后，你们穿好衣服然后回到你的房间……");
									}
								
							} else {
								Main.game.getTextStartStringBuilder().append(
										"舒舒服服地泡完了澡，你感到舒适惬意。"
										+ "你拔掉插头，擦干身体，迅速穿好衣服，走回房间……");
							}
						Main.game.getTextStartStringBuilder().append("</p>");
					}
				};
				
			} else if(index==2) {
				if(slavesWashing.isEmpty()) {
					return new Response("做爱", "你没让奴隶跟到卧室里给你洗澡，所以没人跟你做爱……", null);
				}
				
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				for(int i=0 ; i<slavesWashing.size() && i<4; i++) {
					slaveSlots.put(slavesWashing.get(i), i==0?SexSlotLyingDown.LYING_DOWN:bathSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(slavesWashing.size()), true);
				return new ResponseSex("做爱",
						slavesWashing.size()==1
							?UtilText.parse(slavesWashing, "与[npc.name]在浴缸中进行支配型性爱。")
							:"在浴缸里与你的奴隶进行支配型性爱",
						true, false,
						new SMBath(SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
								slaveSlots),
						null,
						null,
						AFTER_BATH_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_BATH_SEX_AS_DOM", slavesWashing));
				
			} else if(index==3) {
				if(slavesWashing.isEmpty()) {
					return new Response("服从型性爱", "你没让奴隶跟到卧室里给你洗澡，所以没人跟你进行服从型性爱……", null);
				}
				if(!slavesWashing.stream().anyMatch(s->s.isAttractedTo(Main.game.getPlayer()))) {
					return new Response("服从性爱",
							slavesWashing.size()==1
								?UtilText.parse(slavesWashing, "[npc.name]没被你吸引，不想跟你做支配型性爱……")
								:"在浴缸里与你的奴隶进行支配型性爱",
							null);
				}
				Map<GameCharacter, SexSlot> slaveSlots = new HashMap<>();
				List<GameCharacter> attractedSlaves = slavesWashing.stream().filter(s->s.isAttractedTo(Main.game.getPlayer())).collect(Collectors.toList());
				for(int i=0 ; i<attractedSlaves.size(); i++) {
					slaveSlots.put(attractedSlaves.get(i), bathSlots[i]);
				}
				UtilText.addSpecialParsingString(String.valueOf(attractedSlaves.size()), true);
				return new ResponseSex("服从性爱",
						attractedSlaves.size()==1
								?UtilText.parse(attractedSlaves, "[npc.name]会在浴室强势操你。")
										:"你的奴隶会在浴室强势操你。",
						true, true,
						new SMBath(SexPosition.LYING_DOWN,
								slaveSlots,
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN))),
						null,
						null,
						AFTER_BATH_SEX,
						UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "START_BATH_SEX_AS_SUB", attractedSlaves));
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_BATH_SEX = new DialogueNode("完成", "", true) {
		@Override
		public void applyPreParsingEffects() {
			for(GameCharacter npc : slavesWashing) {
				npc.applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
			}
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_BATH, 240+30);
		}
		@Override
		public String getDescription() {
			return "你的奴隶都玩尽兴了，提醒你还另有事情要做……";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/playersRoom", "AFTER_BATH_SEX", slavesWashing);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "你已经爽过了，是时候回房间了。", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_MAKEUP = new DialogueNode("发型与妆容", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getMakeupTarget());
		}
		@Override
		public String getHeaderContent() {
			return MiscDialogue.getMakeupDialogue(true,
					BodyChanging.getTarget().isPlayer()
						?"你坐在镜子前，开始打扮自己……"
						:UtilText.parse(BodyChanging.getTarget(), "你把[npc.name]按坐在镜前，准备打扮打扮[npc.her]……")).getHeaderContent();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("完成", "你化好妆返回卧室。", ROOM);
			}
			return null;
		}
	};
	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR = new DialogueNode("日历", "", true) {
		@Override
		public void applyPreParsingEffects() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>"
					+ "你走到了你房间的一边，在那里有一本日历被挂在墙上。"
					+ "很明显，日历是被附魔过的，因为当你翻阅日历时，发现每个月的图片都会根据你当前的想法而改变。");
						
			if(Main.game.getPlayer().getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
				sb.append("当你想到每个月的时候，就会有一个穿着主题服饰的男人，淫梦魔或者是动物男出现在页面上。");
			} else {
				sb.append("当你想到每个月的时候，就会有一个穿着主题服饰的女人，魅魔或者是动物女出现在页面上。");
			}
			
			if(Main.game.getPlayer().getCorruptionLevel()==CorruptionLevel.ZERO_PURE) {
				sb.append("越翻日历，插画上的角色穿着就越暴露。你突然反应了过来，震惊地退后一步。");
			} else {
				sb.append("越翻日历，插画上的角色穿着就越暴露。你的呼吸也越来越粗重……");
			}
			sb.append("</p>");
			
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				sb.append("<p>"
						+ "你突然想起自己想看什么，打开日历查看当前日程，");
			} else {
				sb.append("<p>"
						+ "你被不断变化的图片吸引了注意力，以至于一时忘记了自己想要检查的是什么。"
						+ "你摇摇头，翻开日历，看看今天是什么日子，");
			}
			
			sb.append("看来今天是<b style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>"
						+ Units.date(Main.game.getDateNow(), Units.DateType.LONG)
					+"</b>。经过"+(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)<IntelligenceLevel.ONE_AVERAGE.getMaximumValue()?"手机计算器的":"")+"一番计算"
					+ "，你来到这个世界已经<b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>"+Main.game.getDayNumber()+" 天"+(Main.game.getDayNumber()>1?"":"")+"</b>了。"
					+ "</p>");
			
			if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.knowsDate)) {
				sb.append("<p>"
						+ "[pc.thought(等下……"+Main.game.getDateNow().format(DateTimeFormatter.ofPattern("yyyy", Locale.CHINESE))+"？！我得跟莉莱雅确认一下……)]"
						+ "</p>");
			}
			
			sb.append("<p>"
					+ "你注意到，在日历的每一页上，都有几段文字详细介绍了那个月的事件。"
					+ "</p>");
			
			Main.game.getTextStartStringBuilder().append(sb.toString());
			
			Main.game.getDialogueFlags().values.add(DialogueFlagValue.knowsDate);
		}
		@Override
		public String getContent() {
			return "";
		}

//		@Override
//		public String getResponseTabTitle(int index) {
//			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
//		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
//			if(responseTab==1) {
//				return LilayaHomeGeneric.getLilayasHouseFastTravelResponses(index);
//			}
			if (index == 0) {
				return new Response("返回", "回到日历界面。", ROOM);
			} else if(index==1) {
				return new Response("一月", "阅读一月的信息。[style.italicsMinorBad(一月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JANUARY);
			} else if(index==2) {
				return new Response("二月", "阅读二月的信息。[style.italicsMinorBad(二月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_FEBRUARY);
			} else if(index==3) {
				return new Response("三月", "阅读三月的信息。[style.italicsMinorBad(三月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MARCH);
			} else if(index==4) {
				return new Response("四月", "阅读四月的信息。[style.italicsMinorBad(四月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_APRIL);
			} else if(index==5) {
				return new Response("五月", "阅读五月的信息。[style.italicsMinorBad(五月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY);
			} else if(index==6) {
				return new Response("六月", "阅读六月的信息。[style.italicsMinorBad(六月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE);
			} else if(index==7) {
				return new Response("七月", "阅读七月的信息。[style.italicsMinorBad(七月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_JULY);
			} else if(index==8) {
				return new Response("八月", "阅读八月的信息。[style.italicsMinorBad(八月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_AUGUST);
			} else if(index==9) {
				return new Response("九月", "阅读九月的信息。[style.italicsMinorBad(九月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_SEPTEMBER);
			} else if(index==10) {
				return new Response("十月", "阅读十月的信息。", AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER);
			} else if(index==11) {
				return new Response("十一月", "阅读十一月的信息[style.italicsMinorBad(十一月目前没有特殊事件。)]", AUNT_HOME_PLAYERS_ROOM_CALENDAR_NOVEMBER);
			} else if(index==12) {
				return new Response("十二月", "阅读十二月的信息。", AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER);
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JANUARY = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JANUARY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(一月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("一月", "你在阅读一月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_FEBRUARY = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.FEBRUARY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(二月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("二月", "你在阅读二月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MARCH = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MARCH));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(三月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("三月", "你在阅读三月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_APRIL = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.APRIL));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(四月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==4) {
				return new Response("四月", "你在阅读四月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_MAY = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.MAY));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>五月</span>"
					+ "</h4>"
					+ "<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_PINK_LIGHT.toWebHexString()+";'>母亲周</span>"
						+ "<br/>"
						+ "五月八日到十四日"
					+ "</h6>"
					+ "<p><i>"
						+ "五月的第二个星期是庆祝母亲、母性以及母子之间血脉相连的时刻。"
						+ "在此期间，慷慨地为御城区全体居民提供免费的生育增强用品，由志愿者沿着主干道分发。"
						+ "通过这种方式，莉莉丝展示了她对妈妈们的爱，并且保证会有更多产出！"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response("五月", "你在阅读五月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JUNE = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JUNE));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>六月</span>"
					+ "</h4>"
					+"<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_BLUE.toWebHexString()+";'>父亲周</span>"
						+ "<br/>"
						+ "六月十五日到二十一日"
					+ "</h6>"
					+ "<p><i>"
						+ "六月的第三个星期是庆祝父亲、父性以及父子之间血脉相连的时刻。"
						+ "在此期间，慷慨地为御城区全体居民提供免费的生育增强用品，由志愿者沿着主干道分发。"
						+ "通过这种方式，莉莉丝展示了她对爸爸们的爱，并且保证会有更多产出！"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("六月", "你在阅读六月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_JULY = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.JULY));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(七月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==7) {
				return new Response("七月", "你在阅读七月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_AUGUST = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.AUGUST));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(八月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==8) {
				return new Response("八月", "你在阅读八月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_SEPTEMBER = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.SEPTEMBER));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(九月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==9) {
				return new Response("九月", "你在阅读九月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_OCTOBER = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.OCTOBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>十月</span>"
					+ "</h4>"
					+"<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>莉莉丝之月</span>"
						+ "<br/>"
						+ "整月"
					+ "</h6>"
					+ "<p><i>"
						+ "莉莉丝亲自选定了十月，在这个月里，所有御城区人都要向他们光荣的女王表达自己的虔诚之心！"
						+ "每栋建筑上都悬挂着以莉莉丝的传统颜色橙色、紫色和黑色为主色调的旗帜和彩带，向我们的女王展示她的臣民是多么的忠心耿耿！"
						+ "虽然所有公民都要庆祝莉莉丝的统治，但她最虔诚的追随者会穿上传统的恶魔服饰，以证明自己的忠诚。"
					+ "</p>"
					+ "<p>"
						+ "官方认可的“莉莉丝异教”是女王崇拜者中最狂热的群体，经常出没在十月份，"
							+"因为她们除了传统的魔女服饰外不会选择其他衣服，就像几个世纪前莉莉丝经常穿的那套衣服。"
						+ "虽然在一年的其他时间里，这些崇拜者都满足于秘密进行他们的虔诚行为，但在 10 月份，他们会变得相当狂热，"
							+"有时甚至会接近普通民众，要求他们表现出对女王的忠诚！"
					+ "</i></p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==10) {
				return new Response("十月", "你在阅读十月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_NOVEMBER = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.NOVEMBER));

			UtilText.nodeContentSB.append(
					"<h6 style='text-align:center;'>"
							+ "[style.italicsMinorBad(十一月目前没有特殊事件。)]");

			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==11) {
				return new Response("十一月", "你在阅读十一月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CALENDAR_DECEMBER = new DialogueNode("日历", "", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getCalendarImageDescription(Month.DECEMBER));

			UtilText.nodeContentSB.append(
					"<h4 style='text-align:center;'>"
							+ "<span style='color:"+PresetColour.BASE_BLUE_LIGHT.toWebHexString()+";'>十二月</span>"
					+ "</h4>"
					+ "<h6 style='text-align:center;'>"
						+ "<span style='color:"+PresetColour.BASE_GOLD.toWebHexString()+";'>圣诞节假</span>"
						+ "<br/>"
						+ "整月"
					+ "</h6>"
					+ "<i>"
					+ "<p>"
						+"圣诞节的庆祝活动在整个12月都会举行，有时甚至会延长到1月和2月！"
						+"送礼、举行宴会和派对是庆祝圣诞节的方式。"
						+"由于每次庆祝活动驯鹿化形都会抵达御城区，所以圣诞节期间从驯鹿化形这里购买用于赠送友人的礼物已经成了节日传统。"
					+ "</p>"
					+ "<p>"
						+ "与这个节日相关的人物是莉琳“Jólnir”(意为“Yule one”或“Yule figure”)。"
						+ "关于这名莉琳没有太多信息，但最明显的是其名字打破了莉琳名均以“L”开头的传统，而且他是“野性猎手”的领袖。"
					+ "</p>"
					+ "<p>"
						+ "“野性猎手”由一大群游荡的奥术元素体组成，数年之前便离开了御城区，如今只在圣诞节期间才会在弗洛伊原野和附近的森林中被目击。"
					+ "</p>"
					+ "</i>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==12) {
				return new Response("十二月", "你在阅读十二月的日程安排。", null);
			}
			return AUNT_HOME_PLAYERS_ROOM_CALENDAR.getResponse(responseTab, index);
		}
	};
	

	
	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME = new DialogueNode("你的房间", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME", NightlifeDistrict.getClubbersPresent());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("性爱(支配)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "与[npc.name]来一场支配型性爱。"),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_DOM", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==2) {
				return new ResponseSex("性爱(服从)", UtilText.parse(NightlifeDistrict.getClubbersPresent(), "与[npc.name]来一场服从型性爱。"),
						true, true,
						new SMGeneric(
								Util.newArrayListOfValues(NightlifeDistrict.getClubbersPresent().get(0)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null), BACK_HOME_AFTER_CLUBBER_SEX, UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEX_AS_SUB", NightlifeDistrict.getClubbersPresent()));
				
			} else if(index==4) {
				return new Response("道别",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你改变了主意，将[npc.herHim]送回家，并且保证下次肯定还会在夜店见到[npc.herHim]的。"
								+ "</br>[style.italicsGood(保存该角色，可以再次在夜店遇到。)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==5) {
				return new Response("送回家",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你改变了主意，仓促地送[npc.herHim]回家。"
								+ "</br>[style.italicsBad(将该角色从游戏中移除。)]"),
						AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_CHANGE_MIND_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode BACK_HOME_AFTER_CLUBBER_SEX = new DialogueNode("你的房间", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(NightlifeDistrict.getPartner())>=NightlifeDistrict.getPartner().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX", NightlifeDistrict.getClubbersPresent());
			} else {
				return UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_CLUBBER_SEX_NO_ORGASM", NightlifeDistrict.getClubbersPresent());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("再见一面",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "告诉[npc.name]你想和[npc.herHim]再见一面。</br>"
								+ "[style.italicsGood(保存该角色，然后就有机会在夜店中再度邂逅。)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.saveClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==2) {
				return new Response("还是算了(委婉)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "做出不置可否的回应，暗自希望不要再看到[npc.name]。</br>[style.italicsBad(将此角色从游戏中删除。)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
				
			} else if(index==3) {
				return new Response("还是算了(直接)",
						UtilText.parse(NightlifeDistrict.getClubbersPresent(), "残忍地告诉[npc.name]，你只是想操[npc.herHim]。</br>[style.italicsBad(在游戏中移除该角色。)]"),
						BACK_HOME_AFTER_SEX) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nightlife/theWateringHole", "BACK_HOME_AFTER_SEX_DO_NOT_SEE_AGAIN_RUDE", NightlifeDistrict.getClubbersPresent()));
						NightlifeDistrict.removeClubbers();
						Main.game.setRequestAutosave(true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode BACK_HOME_AFTER_SEX = new DialogueNode("你的房间", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME = new DialogueNode("你的房间", "", false) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return LilayaHomeGeneric.getLilayasHouseStandardResponseTabs(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};
}
