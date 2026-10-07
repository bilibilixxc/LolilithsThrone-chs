package com.lilithsthrone.game.dialogue.places.dominion;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Callie;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.ReindeerOverseer;
import com.lilithsthrone.game.character.npc.dominion.RentalMommy;
import com.lilithsthrone.game.character.npc.submission.Claire;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.OccupantDialogue;
import com.lilithsthrone.game.dialogue.encounters.AbstractEncounter;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.CultistDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.ReindeerOverseerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.RentalMommyDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.helenaHotel.HelenaHotel;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanDateFinalRepeat;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanFirstDate;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanFirstDoubleDate;
import com.lilithsthrone.game.dialogue.places.dominion.nyansApartment.NyanRepeatDate;
import com.lilithsthrone.game.dialogue.places.submission.BatCaverns;
import com.lilithsthrone.game.dialogue.places.submission.SubmissionGenericPlaces;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldRegion;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.2
 * @author Innoxia
 */
public class DominionPlaces {

	public static final int TRAVEL_TIME_STREET = 2*60;
	
	public static boolean isCloseToEnforcerHQ() {
		return Vector2i.getDistance(Main.game.getPlayerCell().getLocation(), Main.game.getWorlds().get(WorldType.DOMINION).getCell(PlaceType.DOMINION_ENFORCER_HQ).getLocation())<4f;
	}
	
	private static String getExtraStreetFeatures() {
		StringBuilder mommySB = new StringBuilder();
		StringBuilder occupantSB = new StringBuilder();
		StringBuilder cultistSB = new StringBuilder();
		StringBuilder reindeerSB = new StringBuilder();
		
		Set<NPC> characters = new HashSet<>(Main.game.getNonCompanionCharactersPresent());
		characters.addAll(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));

		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_NYAN_APARTMENT) {
			mommySB.append("<p>"
							+ "[style.boldPinkLight(妮安的公寓：)]<br/>"
							+ (Main.game.getNpc(Nyan.class).getWorldLocation()==WorldType.NYANS_APARTMENT
								?"妮安就住在这片区域，如果你想的话，可以去她的公寓拜访她……"
								:"妮安就住在这片区域，不过你知道她这个时候在工作，所以没必要去她的公寓楼……")
						+ "</p>");
		}
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_CALLIE_BAKERY) {
			mommySB.append("<p>[style.boldBrown(奶油烘焙店：)]</p>");
			mommySB.append(UtilText.parseFromXMLFile("nnxx/callie_bakery", "EXTERIOR"));
		}
		
		for(NPC npc : characters) {
			if(npc instanceof RentalMommy) {
				mommySB.append(
						UtilText.parse(npc,
								"<p>"
									+ "<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>“妈咪”的家：</b><br/>"
									+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM
										?"“妈咪”的房子就在这条街上，但由于正在肆虐的奥术风暴，她经常坐的那条长凳现在空无一人。"
												+ "如果你想和她互动，最好等风暴过去后再回来……"
										:"妈咪的房子就在这条街的尽头，当你望向房子时，你能看到她坐在外面她常坐的那条长凳上。"
											+ "她仍然穿着“租赁妈咪”T恤衫，显然是如常开张了……")
								+ "</p>"));
				break;
			}
			if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
				occupantSB.append("<p>");
				occupantSB.append(UtilText.parse(npc,
								"<b style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>[npc.NamePos]的公寓：</b><br/>"
									+ "[npc.name]从莉莱雅家搬出去后，最终住进了这附近的一栋公寓。"));

				if(npc.isAsleep()) {
					occupantSB.append(UtilText.parse(npc,
							"你如果想的话，就可以去看望[npc.race]。但因为[npc.sheIs]目前[style.colourSleep(在睡觉)]，你得叫醒[npc.herHim]……"));
				} else {
					occupantSB.append(UtilText.parse(npc, "你如果想的话，就可以去看望[npc.race]……"));
				}
				occupantSB.append("<br/>");
				occupantSB.append(UtilText.parse(npc, "<i>[npc.Name]在[style.time("+npc.getSleepStartHour()+")]-[style.time("+npc.getSleepEndHour()+")]之间睡觉</i>"));
				occupantSB.append("</p>");
				break;
			}
			
			if(npc instanceof Cultist) {
				cultistSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.GENERIC_ARCANE.toWebHexString()+";'>狂信者的小礼拜堂：</b><br/>"
							+ UtilText.parse(npc, "你记得[npc.namePos]地小教堂就在附近，如果你愿意，可以很容易地再次找到它……")
						+ "</p>");
				break;
			}
			
			if(npc instanceof ReindeerOverseer) {
				reindeerSB.append(
						"<p>"
							+ "<b style='color:"+PresetColour.RACE_REINDEER_MORPH.toWebHexString()+";'>驯鹿监工:</b><br/>"
							+ (Main.game.getCurrentWeather()==Weather.MAGIC_STORM
								?UtilText.parse(npc, "这些驯鹿化形正在躲避逼近的奥术风暴。"
										+ "如果想跟监工对话，最好等到风暴过去。")
								:UtilText.parse(npc, "一大群驯鹿化形，正在努力除雪。"
										+ "领头的[npc.a_race]，正在大喊着命令，在工人中间来回穿梭，确保工作成果能让[npc.her]满意。"
										+ "尽管这些工人忙到没功夫停下来说话，但如果想的话，你还是可以跟监工聊上几句。"))
						+ "</p>");
				break;
			}
		}
		
		mommySB.append(cultistSB.toString()).append(occupantSB.toString()).append(reindeerSB.toString());
		
		AbstractClothing collar = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
		if(collar!=null && collar.getClothingType().getId().equals("innoxia_neck_filly_choker")) {
			mommySB.append("<p>");
				mommySB.append("[style.boldPinkLight([style.Mule]项圈：)]<br/>");
				mommySB.append("你戴着[style.mule]项圈，相当于对御城速递的半人马奴隶传达着一个信息，你可以为他们提供性服务。");
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					mommySB.append("然而由于奥术风暴将至，你[style.colourMinorBad(没有可能)]遇见半人马……");
				} else if(!Main.game.isExtendedWorkTime()) {
					mommySB.append("然而由于现在不是工作时间，你[style.colourMinorBad(没有可能)]遇见半人马……");
				} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					mommySB.append("然而由于你无法使用自己的嘴巴，他们[style.colourMinorBad(没有可能)]向你走来……");
				} else {
					mommySB.append("尽管御城区十分广袤，但御城速递的半人马奴隶也人数众多，这就意味着你[style.colourMinorGood(有一点可能)]遇见其中一个……");
				}
			mommySB.append("</p>");
		}
		
		
		return mommySB.toString();
	}
	
	private static List<Response> getExtraStreetResponses() {
		List<Response> mommyResponses = new ArrayList<>();
		List<Response> occupantResponses = new ArrayList<>();
		List<Response> cultistResponses = new ArrayList<>();
		List<Response> reindeerResponses = new ArrayList<>();

		Set<NPC> characters = new HashSet<>(Main.game.getNonCompanionCharactersPresent());
		characters.addAll(Main.game.getCharactersTreatingCellAsHome(Main.game.getPlayerCell()));
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_NYAN_APARTMENT) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
				//TODO
//				if(Main.game.getNpc(Nyan.class).getWorldLocation()==WorldType.NYANS_APARTMENT) {
//					mommyResponses.add(new Response("Visit Nyan", "Head over to Nyan's apartment building and pay her a visit.", Main.game.getDefaultDialogue(false)));
//					
//				} else {
//					mommyResponses.add(new Response("Visit Nyan", "Nyan is out at work at this time of day, so you're unable to head over to her apartment building and pay her a visit...", null));
//				}
			}
			
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)
					&& (!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend))) {
				int dateCost = 4000;
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWeekendDated)) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)) {
						mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"你这个周末已经带妮安出去约会过了。你需要等到下周末才能带她和[nyanmum.name]出去双重约会……",
								null));
					} else {
						mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"你这个周末已经带妮安和[nyanmum.name]出去约会过了。你需要等到下周末……",
								null));
					}
					
				} else if((Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY) && (Main.game.isHourBetween(20, 23))) {
					if(Main.game.getNpc(Nyan.class).getWorldLocation()!=WorldType.NYANS_APARTMENT) {
						mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"妮安和[nyanmum.name]现在不在家。你需要等工作日结束再来……",
								null));
						
					} else if(Main.game.getPlayer().getMoney()<dateCost) {
						mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"“橡木林荫”看起来是个高消费的约会地点。你想和妮安还有[nyanmum.name]在这约会，至少要有"+Util.intToString(dateCost)+"火币。",
								null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"你吃不了东西，所以不能去餐厅约会！"
									+ "<br/>[style.italicsMinorBad(你需要能够使用自己的嘴巴，才能带妮安和[nyanmum.name]出去约会……)]",
								null));
						
					} else {
						mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoney(dateCost, "span")+")",
								"去公寓接妮安和[nyanmum.name]，然后一起去“橡木林荫”餐厅约会。",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
									?NyanDateFinalRepeat.DOUBLE_DATE_START
									:NyanFirstDoubleDate.DATE_START));
					}
					
				} else {
					mommyResponses.add(new Response("双人约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"你现在不能带妮安和[nyanmum.name]出去约会……"
								+ "<br/><i>你需要在"
								+ (Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
									?"[style.italicsMinorGood(星期五)]"
									:"[style.italicsMinorBad(星期五)]")
								+"或"
								+ (Main.game.getDayOfWeek()==DayOfWeek.SATURDAY
									?"[style.italicsMinorGood(星期六)]"
									:"[style.italicsMinorBad(星期六)]")
								+"，而且在"
								+ (Main.game.isHourBetween(20, 23)
									?"[style.italicsMinorGood([unit.time(20)]-[unit.time(23)])]"
									:"[style.italicsMinorBad([unit.time(20)]-[unit.time(23)])]")
								+"才能带妮安和[nyanmum.name]出去约会！",
							null));
				}
				
			} else {
				int dateCost = 2500;
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanWeekendDated)) {
					mommyResponses.add(new Response("跟妮安约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"你这个周末已经带妮安出去约会过了。你需要等到下周末……",
							null));
					
				} else if((Main.game.getDayOfWeek()==DayOfWeek.FRIDAY || Main.game.getDayOfWeek()==DayOfWeek.SATURDAY)
						&& (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23)) {
					if(Main.game.getNpc(Nyan.class).getWorldLocation()!=WorldType.NYANS_APARTMENT) {
						mommyResponses.add(new Response("跟妮安约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"妮安现在在工作。你需要等工作日结束再来……",
								null));
						
					} else if(Main.game.getPlayer().getMoney()<dateCost) {
						mommyResponses.add(new Response("跟妮安约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"“橡木林荫”看起来是个高消费的约会地点。你想和妮安在这约会，至少要有"+Util.intToString(dateCost)+"火币。",
								null));
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						mommyResponses.add(new Response("跟妮安约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
								"你吃不了东西，所以不能去餐厅约会！"
									+ "<br/>[style.italicsMinorBad(你需要能够使用自己的嘴巴，才能带妮安出去约会……)]",
								null));
						
					} else {
						mommyResponses.add(new Response("跟妮安约会 ("+UtilText.formatAsMoney(dateCost, "span")+")",
								"去妮安的公寓接她，然后一起去“橡木林荫”餐厅约会。",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumDateCompleted)
									?NyanDateFinalRepeat.SOLO_DATE_START
									:(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanRestaurantDateCompleted) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumInterviewPassed)
										?NyanRepeatDate.DATE_START
										:NyanFirstDate.DATE_START)));
					}
					
				} else {
					mommyResponses.add(new Response("跟妮安约会 ("+UtilText.formatAsMoneyUncoloured(dateCost, "span")+")",
							"你现在还不能带妮安去约会……"
								+ "<br/><i>你需要在"
								+ (Main.game.getDayOfWeek()==DayOfWeek.FRIDAY
									?"[style.italicsMinorGood(星期五)]"
									:"[style.italicsMinorBad(星期五)]")
								+"或"
								+ (Main.game.getDayOfWeek()==DayOfWeek.SATURDAY
									?"[style.italicsMinorGood(星期六)]"
									:"[style.italicsMinorBad(星期六)]")
								+"，而且在"
								+ (Main.game.getHourOfDay()>=18 && Main.game.getHourOfDay()<23
									?"[style.italicsMinorGood([unit.time(18)]-[unit.time(23)])]"
									:"[style.italicsMinorBad([unit.time(18)]-[unit.time(23)])]")
								+"才能带妮安出去约会！",
							null));
				}
			}
		}
		
		if(Main.game.getPlayerCell().getPlace().getPlaceType()==PlaceType.DOMINION_CALLIE_BAKERY) {
			int hourOpen = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("nnxx_callie_upgrade_2"))?7:9;
			int hourClose = Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.getDialogueFlagValueFromId("nnxx_callie_upgrade_2"))?17:15;
			
			if(Main.game.isHourBetween(hourOpen, hourClose) && Main.game.getDayOfWeek()!=DayOfWeek.SUNDAY) {
				DialogueNode initNode = DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry_first_time");
				if(Main.game.getDialogueFlags().hasFlag("nnxx_callie_introduced")) {
					AbstractClothing playerNeckClothing = Main.game.getPlayer().getClothingInSlot(InventorySlot.NECK);
					if(Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_3")
							&& !Main.game.getDialogueFlags().hasFlag("nnxx_callie_upgrade_reaction_pending")
							&& Main.game.getPlayer().getSexCount(Main.game.getNpc(Callie.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))>0
							&& Main.game.getNpc(Callie.class).isAttractedTo(Main.game.getPlayer())
							&& Main.game.getPlayer().isQuestCompleted(QuestLine.ROMANCE_NATALYA)
							&& (playerNeckClothing!=null && playerNeckClothing.getClothingType().getId().equals("innoxia_neck_filly_choker"))
							&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
							&& (!Main.game.getDialogueFlags().hasFlag("innoxia_callie_natalya_encountered")
									|| Main.game.getSecondsPassed() - Main.game.getDialogueFlags().getSavedLong("callie_natalya_encounter_time") >= 60*60*24*3)) {
						initNode = DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry_natalya"); // Can be encountered every three days
						
					} else {
						initNode = DialogueManager.getDialogueFromId("nnxx_callie_bakery_entry");
					}
				}
				
				mommyResponses.add(new Response("奶油烘焙店",
						"去附近的面包店“奶油烘焙”看看吧。"
								+ "<br/><i>这家面包店[style.italicsMinorGood([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])]开门营业。</i>",
								initNode) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.getWorldTypeFromId("nnxx_callie_bakery"), PlaceType.getPlaceTypeFromId("nnxx_callie_bakery_counter"));
					}
				});
				
			} else {
				mommyResponses.add(new Response("奶油烘焙店",
						"附近的面包房，“奶油烘焙店”，在这个时间点不营业。"
								+ "<br/><i>你得在"
								+ (Main.game.isHourBetween(hourOpen, hourClose)
										?"[style.italicsMinorGood([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])]，"
										:"[style.italicsMinorBad([unit.time("+hourOpen+")]-[unit.time("+hourClose+")])]，")
								+ (Main.game.getDayOfWeek()!=DayOfWeek.SUNDAY
										?"[style.italicsMinorGood(周一到周六)]"
										:"[style.italicsMinorBad(周一到周六)]")
								+ "再来。</i>",
						null));
			}
		}
		
		for(NPC npc : characters) {
			if(npc instanceof RentalMommy) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					mommyResponses.add(new Response("妈咪", "“妈咪”没像以往一样坐在长凳上，你猜她可能是在家里躲避当前的风暴。", null));
				} else {
					mommyResponses.add(new Response("妈咪", "你看见“妈咪”坐在屋外的木制长凳上，走过去和她打招呼。", RentalMommyDialogue.ENCOUNTER) {
						@Override
						public void effects() {
							Main.game.setActiveNPC(npc);	
						}
					});
				}
			}
			
			if(Main.game.getPlayer().getFriendlyOccupants().contains(npc.getId())) {
//				if(!Main.game.getCharactersPresent().contains(npc)) {
//					occupantResponses.add(new Response(
//							UtilText.parse(npc, "[npc.Name]"),
//							UtilText.parse(npc, "[npc.Name] is out at work at the moment, and so you'll have to return at another time if you wanted to pay [npc.herHim] a visit..."),
//							null));
//				}
				occupantResponses.add(new Response(
						UtilText.parse(npc, "[npc.Name]"),
						UtilText.parse(npc,
								Main.game.getPlayer().getCompanions().contains(npc)
									?"转头回[npc.namePos]的公寓。"
									:"转头回[npc.namePos]的公寓拜访[npc.herHim]。"),
						OccupantDialogue.OCCUPANT_APARTMENT) {
					@Override
					public void effects() {
						OccupantDialogue.initDialogue(npc, true, false);
					}
				});
			}
			
			if(npc instanceof Cultist) {
				cultistResponses.add(new Response("小教堂", UtilText.parse(npc, "再次拜访[npc.namePos]的小教堂。"), CultistDialogue.ENCOUNTER_CHAPEL_REPEAT) {
						@Override
						public void effects() {
							Main.game.setActiveNPC(npc);
						}
					});
			}
			
			if(npc instanceof ReindeerOverseer) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					reindeerResponses.add(new Response("监工",
							"驯鹿化形工人们正在躲避肆虐的奥术风暴。你如果想和监工说话，只能稍后再来。",
							null));
				} else {
					reindeerResponses.add(new Response("监工", UtilText.parse(npc, "走过去和[npc.name]打招呼。"), ReindeerOverseerDialogue.ENCOUNTER_START) {
							@Override
							public void effects() {
								Main.game.setActiveNPC(npc);
								npc.setPlayerKnowsName(true);
							}
						});
				}
			}
		}
		
		mommyResponses.addAll(cultistResponses);
		mommyResponses.addAll(occupantResponses);
		mommyResponses.addAll(reindeerResponses);
		
		return mommyResponses;
	}

	private static String getRandomStreetEvent() {
		int extraText = Util.random.nextInt(100) + 1;
		if (extraText <= 3) {
			return ("<p><i>一个身材高大、威风凛凛的淫梦魔穿过人群，牵着三个纯猫女奴隶。"
					+ "她们每个都一丝不挂，你经过时可以清楚地看到她们的淫穴在兴奋地流着水</i></p>");
		} else if (extraText <= 6) {
			return ("<p><i>在一侧，你看到一对狗男执法者正在审问一个面目可憎的猫男。"
					+ "当你经过时，猫男试图逃跑，但很快就被扑倒在地上。"
					+ "执法者在他的手腕上套上了一副手铐，然后把他拖到附近的一条小巷里。</i></p>");
		} else if (extraText <= 9) {
			return ("<p><i>一个巨大的广告牌覆盖了街对面一栋建筑的整个正面。"
					+ "上面有一则“孤注一掷”的比赛广告，承诺任何有实力战胜挑战的人都能获得丰厚的奖励。"
					+ "下面用醒目的红色字体写着“申请即将开始！”。</i></p>");
		} else if (extraText == 10) {
			return ("<p><i>一个纯猫女在你的前面发传单，当你经过时，她把一张传单塞到你的手里。"
					+ "你低头一看，这只是饮料的广告：“"+ ItemType.getItemTypeFromId("innoxia_race_cat_felines_fancy").getName(false)+ "”。</i></p>");
		} else if (extraText == 11) {
			return ("<p><i>一个纯狼男在你的前面发传单，当你经过时，他把一张传单塞到你的手里。"
					+ "你低头一看，这只是饮料的广告：“"+ ItemType.getItemTypeFromId("innoxia_race_wolf_wolf_whiskey").getName(false)+ "”。</i></p>");
		} else if (extraText == 12) {
			return ("<p><i>一个纯狗女在你的前面发传单，当你经过时，她把一张传单塞到你的手里。"
					+ "你低头一看，这只是饮料的广告：“"+ ItemType.getItemTypeFromId("innoxia_race_dog_canine_crush").getName(false)+ "”。</i></p>");
		} else if (extraText == 13) {
			return ("<p><i>一个纯马男在你的前面发传单，当你经过时，他把一张传单塞到你的手里。"
					+ "你低头一看，这只是饮料的广告：“"+ ItemType.getItemTypeFromId("innoxia_race_horse_equine_cider").getName(false)+ "”。</i></p>");
		} else if (extraText == 14) {
			return ("<p><i>欢呼的人群聚集在街道的一侧，当你瞥向对面时，人群中瞬间出现的空隙让你瞥见了正在发生的事情。"
					+ "一个纯狗女四脚着地，被一个纯马男的一对巨大的马鸡巴双重插入。"
					+ "女孩的“汁水”顺着双腿流下，巨大的肉棒在她被撑大的小穴中进进出出，她的舌头也从口中舔出。</i></p>");
		}
		return "";
	}
	
	private static String getEnforcersPresent() {
		StringBuilder sb = new StringBuilder();

		if(Main.game.getSavedEnforcers(WorldType.DOMINION).isEmpty()) {
			if(isCloseToEnforcerHQ()) {
				sb.append("<p style='text-align:center;'><i>");
					sb.append("这一区域由于靠近御城区的[style.colourBlueDark(执法者总部)]，有[style.italicsBad(很高概率)]遇到[style.colourBlueDark(执法者巡逻队)]！");
					if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
						sb.append("<br/>然而，由于持续的奥术风暴，目前不可能遇到巡逻队……");
					}
				sb.append("</i></p>");
			}
			
		} else {
			sb.append("<p style='text-align:center;'><i>");
			
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					sb.append("由于[style.italicsArcane(奥术风暴)]正在肆虐，你[style.italicsGood(根本没机会)]遇见[style.colourBlueDark(执法者巡逻队)]:");
				} else if(isCloseToEnforcerHQ()) {
					sb.append("这一区域由于靠近御城区的[style.colourBlueDark(执法者总部)]，有[style.italicsBad(很高概率)]遇到[style.colourBlueDark(执法者巡逻队)]其中之一的：");
				} else {
					sb.append("有[style.italicsMinorBad(较低概率)]遭遇其中一支[style.colourBlueDark(执法者巡逻队)]：");
				}
				for(List<String> enforcerIds : Main.game.getSavedEnforcers(WorldType.DOMINION)) {
					sb.append("<br/>");
					List<String> names = new ArrayList<>();
					for(String id : enforcerIds) {
						try {
							GameCharacter enforcer = Main.game.getNPCById(id);
							names.add(UtilText.parse(enforcer, "<span style='color:"+enforcer.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					sb.append(Util.stringsToStringList(names, false));
				}
			
			sb.append("</i></p>");
		}
		
		return sb.toString();
	}
	
	public static final DialogueNode STREET = new DialogueNode("御城区街道", "", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET"));
			if (Main.game.getCurrentWeather() != Weather.MAGIC_STORM) {
				sb.append(getRandomStreetEvent());
			}

			sb.append(getExtraStreetFeatures());
			
			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_OCTOBER"));
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_SNOW"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index-1 < responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	


	public static final DialogueNode BACK_ALLEYS_SAFE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS_SAFE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode BACK_ALLEYS = new DialogueNode("", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("小巷");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DARK_ALLEYS = new DialogueNode("Dark 小巷", "", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DARK_ALLEYS", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription(false));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("小巷");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode BACK_ALLEYS_CANAL = new DialogueNode("运河交汇处", "。", false) {
		
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BACK_ALLEYS_CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("小巷");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode BOULEVARD = new DialogueNode("御城区主干道", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 90;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "BOULEVARD"));
			
			sb.append(getExtraStreetFeatures());
			
			if(Main.game.getDateNow().getMonth()==Month.OCTOBER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_OCTOBER"));
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter) && Main.game.getSeason()==Season.WINTER) {
				sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_EVENT_SNOW"));
			}
			
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index-1 < responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};

	private static Set<Integer> viewedNewsIndexes = new HashSet<>();
	private static boolean viewedAllNews = false;
	
	private static String getRandomNewsText() {
		List<AbstractSubspecies> possibleSubspecies = new ArrayList<>();
		for(AbstractSubspecies s : Subspecies.allSubspecies) {
			if(s.getMostCommonWorldRegions().contains(WorldRegion.DOMINION)) {
				possibleSubspecies.add(s);
			}
		}
		
		String randomFemalePerson = Util.randomItemFrom(possibleSubspecies).getSingularFemaleName(null);
		String randomMalePerson = Util.randomItemFrom(possibleSubspecies).getSingularMaleName(null);
		
		List<String> strings = Util.newArrayListOfValues(
				"一个面相不善的"+randomMalePerson+"展开一个大卷轴，清了清嗓子喊道，"
						+ "[maleNPC.speech(奉莉莉丝敕谕，为了御城区的安全，"
							+ "在白天以外的时间，任何在街上行走的人都可能被执法者依法进行全身搜查。)]",
					Util.capitaliseSentence(UtilText.addDeterminer(randomFemalePerson))+"举起一张盖有红色蜡印的官方文件公布着什么，"
						+ "[femaleNPC.speech(悬赏二十万火币，以奖励任何提供线索者，"
							+ "缉拿在哈比之巢下的地区散发非法报纸的嫌疑人！)]",
					"一个穿着万圣节女巫服、外表狂野的魅魔，一边尖叫一边指着人群中的各色人等，"
						+ "[femaleNPC.speech(我数了一下，人群中至少有三个恶魔没有穿狂信者制服！"
							+ "莉莉丝如果看到这些会怎么说？！)]",
					Util.capitaliseSentence(UtilText.addDeterminer(randomMalePerson))+"正向群众传播几条无聊的琐碎新闻。"
							+ "没有任何你感兴趣的东西，你最终转身离去，觉得自己只是在浪费时间。",
					Util.capitaliseSentence(UtilText.addDeterminer(randomFemalePerson))+" 正向群众传播几条无聊的琐碎新闻。"
							+ "没有任何你感兴趣的东西，你最终转身离去，觉得自己只是在浪费时间。",
					Util.capitaliseSentence(UtilText.addDeterminer(randomMalePerson))+"正在宣读当地商店的广告清单。"
							+ "没有任何你感兴趣的东西，你很快发现自己只是在浪费时间，转身离开。",
					Util.capitaliseSentence(UtilText.addDeterminer(randomFemalePerson))+"正在宣读当地商店的广告清单。"
							+ "没有任何你感兴趣的东西，你很快发现自己只是在浪费时间，转身离开。");
		
		List<Integer> availableIndexes = new ArrayList<>();
		for(int i=0; i<strings.size(); i++) {
			availableIndexes.add(i);
		}
		for(Integer i : viewedNewsIndexes) {
			availableIndexes.remove(i);
		}
		if(availableIndexes.isEmpty()) {
			viewedAllNews = true;
			return "<p style='text-align:center;'>"
						+ "[style.italicsDisabled(你已经全都听过了……)]"
					+ "</p>";
			
		} else {
			int index = Util.randomItemFrom(availableIndexes);
			viewedNewsIndexes.add(index);
			
			return "<p>"
						+ strings.get(index)
					+ "</p>";
		}
	}
	
	public static final DialogueNode DOMINION_PLAZA = new DialogueNode("莉莉丝的广场", "", false) {
		@Override
		public void applyPreParsingEffects() {
			viewedNewsIndexes.clear();
			viewedAllNews = false;
		}
		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "DOMINION_PLAZA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getCurrentWeather()==Weather.MAGIC_STORM) {
					return new Response("新闻", "因为奥术风暴正在肆虐，现在没人在这儿……", null);
					
				} else if(viewedAllNews) {
					return new Response("新闻", "你已经全都听过了……", null);
					
				} else {
					return new Response(
							"新闻",
							"决定在此逗留片刻，聆听一位演说者……", DOMINION_PLAZA_NEWS){
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append("<p>"
											+ getRandomNewsText()
											+"</p>");
								}
							};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DOMINION_PLAZA_NEWS = new DialogueNode("莉莉丝的广场", "。", false, true) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
					+ "你决定留下来聆听众多正向人群发表演说的演说者中的一位……"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return DOMINION_PLAZA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode HELENAS_HOTEL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(STREET.getContent());
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_SHADED"));
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "HELENAS_HOTEL"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			if(index == 0) {
				return null;
				
			} else if(index==1) {
				return new Response("海伦娜的巢", "使用旅馆电梯，直达海伦娜的巢。", HelenaHotel.HOTEL_TRAVEL_TO_NEST) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HELENAS_NEST);
					}
				};
					
			} else if(index-2 < responses.size()) {
				return responses.get(index-2);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode STREET_SHADED = new DialogueNode("御城区街道(阴暗)", "。", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(STREET.getContent());
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "STREET_SHADED"));
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = getExtraStreetResponses();
			
			 if(index!=0 && index-1<responses.size()) {
				return responses.get(index-1);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode CANAL = new DialogueNode("御城区运河", "。", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return AbstractEncounter.exploreArea("运河");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};
	
	public static final DialogueNode CANAL_END = new DialogueNode("御城区运河", "。", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CANAL_END", new ArrayList<GameCharacter>(Main.game.getNonCompanionCharactersPresent())));
			
			for(GameCharacter npc : Main.game.getNonCompanionCharactersPresent()) {
				UtilText.nodeContentSB.append(((NPC) npc).getPresentInTileDescription());
			}
			UtilText.nodeContentSB.append(getEnforcersPresent());
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return AbstractEncounter.exploreArea("运河");
			} else if(index == 2) {
				return AbstractEncounter.useOffspringMap();
			}
			return null;
		}
	};

	// Entrances and exits:

	public static final DialogueNode CITY_EXIT_SEWERS = new DialogueNode("屈城区入口", "进入屈城区地下城。", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_SEWERS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("屈城区", "进入屈城区下城。", CITY_EXIT_SEWERS_ENTERING_SUBMISSION){
					@Override
					public void effects() {
						if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.visitedSubmission, false);
						}
						Main.game.getPlayer().setLocation(WorldType.SUBMISSION, PlaceType.SUBMISSION_ENTRANCE, false);
						
						Main.game.getNpc(Claire.class).setLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_SEWERS_ENTERING_SUBMISSION = new DialogueNode("执法者检查点", "进入屈城区地下城。", false) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.visitedSubmission);
		}
		
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION"));
			if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLIME_QUEEN)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_FIRST_TIME"));
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_REPEAT"));
			}
			return sb.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.visitedSubmission)) {
				if (index == 1) {
					return new Response("肯定", "向猫女肯定这确实是你第一次来屈城区。", CITY_EXIT_SEWERS_ENTERING_SUBMISSION_FIRST_TIME) {
						@Override
						public void effects() {
							Main.game.getNpc(Claire.class).setPlayerKnowsName(true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLIME_QUEEN));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return SubmissionGenericPlaces.SEWER_ENTRANCE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_SEWERS_ENTERING_SUBMISSION_FIRST_TIME = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 3*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "ENTER_SUBMISSION_FIRST_TIME_CONFIRMATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续穿过执法者岗哨。", Main.game.getDefaultDialogue(false)){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.visitedSubmission, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode CITY_EXIT_BAT_CAVERNS = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_BAT_CAVERNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isAbleToFly()) {
					if(!Main.game.getPlayer().isPartyAbleToFly()) {
						return new Response("蝙蝠洞窟", "由于你的队伍成员无法飞行，所以不能使用这口竖井进入蝙蝠洞窟……", null);
						
					} else {
						return new Response("蝙蝠洞窟", "从竖井飞进蝙蝠洞窟。", CITY_EXIT_BAT_CAVERNS_FLY_DOWN) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.BAT_CAVERNS, PlaceType.BAT_CAVERN_SHAFT, false);
							}
						};
					}
					
				} else {
					return new Response("Bat Caverns", "由于你无法飞行，所以不能使用这口竖井进入蝙蝠洞窟……", null);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode CITY_EXIT_BAT_CAVERNS_FLY_DOWN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/dominionPlaces", "CITY_EXIT_BAT_CAVERNS_FLY_DOWN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return BatCaverns.SHAFT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode CITY_EXIT = new DialogueNode("御城区出口", "", false) {

		@Override
		public int getSecondsPassed() {
			return TRAVEL_TIME_STREET;
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isDiscoveredWorldMap()) {
				return "<p>"
						+ "一对精英恶魔执法者正在这里密切地监视着进出城市的所有人。"
						+ "现在你有了地图，御城区的任务也结束了，没什么能阻止你出去旅行了。"
					+ "</p>";
				
			} else {
				return "<p>"
							+ "一对精英恶魔执法者正在这里密切地监视着进出城市的所有人。"
							+ "虽然没有什么可以阻止你去外面的世界，但你现在没理由离开御城区，而且你没有地图，很容易迷路。"
						+ "</p>"
						+ "<p>"
							+ "你在寻找返回旧世界的方法，毫无疑问，这最终会把你引向御城区之外。但暂时，你在这座城市里还有任务要完成。"
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isDiscoveredWorldMap()) {
					return new ResponseEffectsOnly("世界旅行", "退出御城区，向着更广阔的天地行进……") {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.WORLD_MAP, Main.game.getPlayer().getGlobalLocation(), false);
							Main.game.setContent(new Response("", "", Main.game.getDefaultDialogue(false)));
						}
					};
					
				} else {
					return new Response("世界旅行", "你尚未知晓世界的全貌，并且暂时，在这个城市里还有事要做。", null);
				}

			} else {
				return null;
			}
		}
	};
}
