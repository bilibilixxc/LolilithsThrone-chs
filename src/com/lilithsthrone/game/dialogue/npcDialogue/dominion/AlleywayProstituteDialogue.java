package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.places.dominion.RedLightDistrict;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Weather;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.3.7.8
 * @author Innoxia
 */
public class AlleywayProstituteDialogue {
	
	private static boolean inApartment = false;
	private static boolean hadSex = false;
	
	/**
	 * Generate an estimate for the remaining value of the prostitute's fine.
	 * DOES NOT use randomness, so that the value is repeatable without requiring
	 * storage (and may change as the player interacts with them).
	 */
	public static int getModifiedFineAmount(GameCharacter character) {
		/*
		 * Estimate the maximum number of times they've had sex as an illegal prostitute:
		 * - percentageNonProstitute = sexCount ^ -0.33
		 *          1 -> 1.00
		 *         10 -> 0.47
		 *        100 -> 0.22
		 *       1000 -> 0.10
		 * - percentageProstitute = 1 - percentageNonProstitute
		 *          1 -> 0.00
		 *         10 -> 0.53
		 *        100 -> 0.78
		 *       1000 -> 0.90
		 * - countProstitute = sexCount * percentageProstitute
		 *          1 ->   0
		 *         10 ->   5
		 *        100 ->  78
		 *       1000 -> 897
		 */
		int maxSexCountAsProstitute = (int)((1 - Math.pow(character.getTotalSexConsensualCount(), -0.33)) * character.getTotalSexConsensualCount());
		
		/*
		 * Assume the profits are split:
		 * - 60% for food, pills, clothing, rent
		 * - 20% for 'protection' money
		 * - 5% of customers don't pay (10% if physically weak, or naive)
		 */
		double savingsPercentage = character.hasPersonalityTrait(PersonalityTrait.NAIVE) ? 0.10 : 0.15;
		
		/*
		 * For simplicity, assume threesomes are already accounted-for within sex-count.
		 */
		int maxSavings = (int)(maxSexCountAsProstitute * prostitutePrice(false) * savingsPercentage);
		
		/*
		 * We don't actually know how long ago (in lore) they got into debt
		 * So adjust their savings, so that it doesn't exceed the debt amount
		 * (to prevent issues where the effective fine is negative)
		 *        0 ->    0
		 *     1000 -> 1500
		 *     5000 -> 4500
		 *    10000 -> 6000
		 *   100000 -> 8571
		 * 13212918 -> 8997 (this is the current theoretical maximum -- 100yr old, 500sex/yr, max price of 2215)
		 */
		int fineAmount = Main.game.getDialogueFlags().getProstituteFine();
		int actualSavings = (int)(maxSavings * (fineAmount * 0.9) / (maxSavings + (fineAmount / 2)));
		
		return fineAmount - actualSavings;
	}
	
	private static int prostitutePrice(boolean threesome) {
		return getProstitute().getProstitutePrice() * (threesome?2:1);
	}
	
	private static NPC getProstitute() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isStorm() {
		return getProstitute().isVulnerableToArcaneStorm()
				&& Main.game.getCurrentWeather()==Weather.MAGIC_STORM;
	}
	
	public static final DialogueNode ALLEY_PROSTITUTE = new DialogueNode("妓女", "", true) {
		@Override
		public void applyPreParsingEffects() {
			inApartment = false;
			hadSex = false;
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(false)), true);
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(true)), false);
		}
		@Override
		public String getContent() {
			if(isStorm()) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_STORM", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			int cost = isStorm()?0:prostitutePrice(false);
			int threesomeCost = isStorm()?0:prostitutePrice(true);
			
			if (index == 1) {
				return new Response("离开", "你对与妓女做爱丝毫不感兴趣。绕过[npc.herHim]继续前行。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(getProstitute().isVisiblyPregnant()){
							getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_LEAVE", getProstitute()));
					}
				};
				
			} else if (index == 2 && !hadSex) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("支配("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(), "你没有"+cost+"火币，付不起[npc.name]的嫖资。"),
							null);
					
				} else {
					return new ResponseSex("主导("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(),
									cost==0
										?"[npc.Name]欲火中烧，你不需要付钱就能跟[npc.she]做爱！"
										:("付[npc.name]"+cost+"火币，来一场支配型性爱。")),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getProstitute()),
							null,
							null) {
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.equals(getProstitute())) {
										return SexPace.SUB_NORMAL;
									}
									return super.getStartingSexPaceModifier(character);
								}
							},
							AFTER_SEX_PAID,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_DOM_SEX", getProstitute())) {
						@Override
						public void effects() {
							hadSex = true;
							inApartment = !isStorm();
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							if(cost>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
								getProstitute().incrementMoney(cost);
							}
						}
					};
				}
				
			} else if (index == 3 && !hadSex) {
				if(Main.game.getPlayer().getMoney()<cost) {
					return new Response("顺从("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(), "你没有"+cost+"火币，付不起[npc.name]的嫖资。"),
							null);
					
				} else {
					return new ResponseSex("顺从("+UtilText.formatAsMoney(cost, "span")+")",
							UtilText.parse(getProstitute(),
									cost==0
										?"[npc.Name]欲火中烧，你不需要付钱就能跟[npc.she]做爱！"
										:("付[npc.name]"+cost+"火币，来一场服从型性爱。")),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
							null,
							null) {
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.equals(getProstitute())) {
										return SexPace.DOM_NORMAL;
									}
									return super.getStartingSexPaceModifier(character);
								}
							},
							AFTER_SEX_PAID,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_SUB_SEX", getProstitute())) {
						@Override
						public void effects() {
							hadSex = true;
							inApartment = !isStorm();
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							if(cost>0) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
								getProstitute().incrementMoney(cost);
							}
						}
					};
				}
				
			} else if(index == 4) {
				if(!getProstitute().hasFlag(NPCFlagValue.prostituteQuestioned)) {
					return new Response("疑问",
							UtilText.parse(getProstitute(), "问问[npc.name]为什么在小巷工作。毕竟在御城区的街上工作会安全得多……"),
							ALLEY_PROSTITUTE_QUESTION) {
						@Override
						public void effects() {
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							getProstitute().addFlag(NPCFlagValue.prostituteQuestioned);
						}
					};
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.prostitutionLicenseObtained)) {
					if(RedLightDistrict.isSpaceForMoreProstitutes()) {
						return new Response("天使之吻",
								UtilText.parse(getProstitute(), "告诉[npc.name]如果能到天使之吻工作，就能受到法律的保护。"),
								ALLEY_PROSTITUTE_ANGELS_KISS) {
							@Override
							public void effects() {
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								Main.game.getPlayer().incrementKarma(25);
								getProstitute().setDescription(UtilText.parse(getProstitute(), "你最初在御城区的小巷中遇见了非法卖淫的[npc.name]。"
										+ "你引荐[npc.herHim]去天使之吻工作，[npc.she]欣然接受。"));
								Main.game.getTextEndStringBuilder().append(getProstitute().incrementAffection(Main.game.getPlayer(), 50));
								getProstitute().setRandomUnoccupiedLocation(WorldType.ANGELS_KISS_GROUND_FLOOR, PlaceType.ANGELS_KISS_BEDROOM, true);
							}
						};
					} else {
						return new Response("天使之吻", "天使之吻没有空房间给其他妓女了……", null);
					}
				}
			}
			
			if(Main.game.getPlayer().hasCompanions()) {
				if(index == 6 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")",
								UtilText.parse(getProstitute(), "你的[com.companion]并没有被[npc.name]吸引，所以不愿意跟[npc.herHim]做爱。"),
								null);
						
					} else if(Main.game.getPlayer().getMoney()<threesomeCost) {
						return new Response("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")",
								UtilText.parse(getProstitute(), "你没有"+threesomeCost+"火币，付不起跟[com.name]和[npc.name]三人行的嫖资。"),
								null);
						
					} else {
						return new ResponseSex("三人行("+UtilText.formatAsMoney(threesomeCost, "span")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name]欲火中烧，你不需要付钱就能跟[npc.she]做爱！"
											:("付[npc.name]"+threesomeCost+"火币来和你以及[com.name]进行三人行。")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(
												Main.game.getPlayer(),
												Main.game.getPlayer().getMainCompanion()),
										Util.newArrayListOfValues(getProstitute()),
								null,
								null) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_THREESOME", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(threesomeCost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-threesomeCost));
									getProstitute().incrementMoney(threesomeCost);
								}
							}
						};
					}
					
				} else if (index == 7 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("主导([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(), "你的[com.companion]并没有被[npc.name]吸引，所以不愿意跟[npc.herHim]做爱。"),
								null);
						
					} else if(Main.game.getPlayer().getMoney()<cost) {
						return new Response("主导([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(), "你没有"+cost+"火币，付不起让[com.name]跟[npc.name]来一场主导型性爱的嫖资。"),
								null);
						
					} else {
						return new ResponseSex("主导([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name]欲火中烧，你不需要付钱就可以让[com.name]跟[npc.herHim]来一场主导型性爱！"
											:("付给[npc.name]"+cost+"火币，让[com.name]跟[npc.herHim]来一场主导型性爱。")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer().getMainCompanion()),
										Util.newArrayListOfValues(getProstitute()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null) {
									@Override
									public boolean isPositionChangingAllowed(GameCharacter character) {
										return !character.isPlayer();
									}
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.SUB_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_COMPANION_DOM_SEX", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(cost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
									getProstitute().incrementMoney(cost);
								}
							}
						};
					}
					
				} else if (index == 8 && !hadSex) {
					if(!Main.game.getPlayer().getMainCompanion().isAttractedTo(getProstitute())) {
						return new Response("顺从([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								"你的[com.companion]并没有被[npc.name]吸引，所以不愿意跟[npc.herHim]做爱。",
								null);
						
					} if(Main.game.getPlayer().getMoney()<cost) {
						return new Response("顺从([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								"你没有"+cost+"火币，付不起让[npc.name]跟[npc.name]来一场主导型性爱的嫖资。",
								null);
						
					} else {
						return new ResponseSex("顺从([com.Name]) ("+UtilText.formatAsMoney(cost, "span")+")",
								UtilText.parse(getProstitute(),
										threesomeCost==0
											?"[npc.Name]欲火中烧，你不需要付钱就可以让她主导跟[com.name]的性爱！"
											:("付给[npc.name]"+cost+"火币，让她主导跟[com.name]的性爱。")),
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getProstitute()),
										Util.newArrayListOfValues(Main.game.getPlayer().getMainCompanion()),
										null,
										Util.newArrayListOfValues(Main.game.getPlayer())) {
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.equals(getProstitute())) {
											return SexPace.DOM_NORMAL;
										}
										return super.getStartingSexPaceModifier(character);
									}
								},
								AFTER_SEX_PAID,
								UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_COMPANION_SUB_SEX", getProstitute())) {
							@Override
							public void effects() {
								hadSex = true;
								inApartment = !isStorm();
								if(getProstitute().isVisiblyPregnant()){
									getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(cost>0) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-cost));
									getProstitute().incrementMoney(cost);
								}
							}
						};
					}
				}
			}
		
			if(getProstitute().hasFlag(NPCFlagValue.prostituteQuestioned)) {
				if(index == 5) {
					return new Response("攻击",
							UtilText.parse(getProstitute(), "如果你真的想攻击[npc.name]，也无可厚非。毕竟[npc.sheHas]也违反了法律。这可以被视为“合法”的。"
									+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
							PROSTITUTE_FIGHT) {
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
						@Override
						public void effects() {
							if(getProstitute().isVisiblyPregnant()){
								getProstitute().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else if (index == 9) {
					return new Response(
							"移除(威胁)",
							UtilText.parse(getProstitute(), "告诉[npc.name]马上离开这片区域，否则你会立刻去告诉执法者[npc.she]的位置。"
									+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
							PROSTITUTE_REMOVAL_THREATENED) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
					};
					
				} else if (index == 10) {
					int fineAmount = getModifiedFineAmount(getProstitute());
					if(Main.game.getPlayer().getMoney()<fineAmount) {
						return new Response("移除("+UtilText.formatAsMoney(fineAmount, "span")+")",
								UtilText.parse(getProstitute(), "你没有"+Util.intToString(fineAmount)+"火币，因此你不能付钱让[npc.name]离开这片区域。"),
								null);
					} else {
						return new Response(
								"移除("+UtilText.formatAsMoney(fineAmount, "span")+")",
								UtilText.parse(getProstitute(), "给[npc.name]足够多的钱买通搜捕的执法者，这样[npc.herHim]就不用再在危险的巷子里工作了。"
										+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]"),
								PROSTITUTE_REMOVAL_PAID) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_NPC_REMOVAL;
							}
						};
					}
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode ALLEY_PROSTITUTE_QUESTION = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			UtilText.addSpecialParsingString(Util.intToString(getModifiedFineAmount(getProstitute())), true);
			if(inApartment) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_QUESTION_APARTMENT", getProstitute()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_QUESTION", getProstitute()));
			}
			// Reset special parsing so that ALLEY_PROSTITUTE.getResponse() parsing is correct:
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(false)), true);
			UtilText.addSpecialParsingString(Util.intToString(prostitutePrice(true)), false);
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ALLEY_PROSTITUTE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ALLEY_PROSTITUTE_ANGELS_KISS = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS", getProstitute()));
			if(inApartment) {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS_END_APARTMENT", getProstitute()));
			} else {
				sb.append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "ALLEY_PROSTITUTE_ANGELS_KISS_END", getProstitute()));
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "得知你现在可以在天使之吻找到[npc.name]，你再次踏上了旅途……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};

	public static final DialogueNode PROSTITUTE_REMOVAL_THREATENED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			UtilText.addSpecialParsingString(Util.intToString(getModifiedFineAmount(getProstitute())), true);
			if(inApartment) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_THREATENED_APARTMENT", getProstitute()));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_THREATENED", getProstitute()));
			}
			Main.game.banishNPC(getProstitute());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("继续", "既然你已经清理了城市的这片区域，你就可以继续你的旅程了……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode PROSTITUTE_REMOVAL_PAID = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			double rnd = Math.random();
			AbstractWeapon weapon;
			if(rnd<0.60f) {
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_enbaton_enbaton"); // 60% chance of getting a baton
			} else if(rnd>0.70f){
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_pbweap_pbpistol"); // 30% chance of getting a pistol
			} else {
				weapon = Main.game.getItemGen().generateWeapon("dsg_eep_taser_taser"); // 10% chance of getting a taser
			}
			UtilText.addSpecialParsingString(weapon.getName(true, true), true);
			UtilText.addSpecialParsingString(Util.intToString(getModifiedFineAmount(getProstitute())), false);
			if(inApartment) {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_PAID_APARTMENT", getProstitute()));
			} else {
				Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_REMOVAL_PAID", getProstitute()));
			}
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-getModifiedFineAmount(getProstitute())));
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addWeapon(weapon, false));
			Main.game.banishNPC(getProstitute());
		}
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "你对能帮到御城区陷入困境的市民感到高兴，继续出发……", Main.game.getDefaultDialogue(false));
			}
			return null;
		}
	};
	
	public static final DialogueNode PROSTITUTE_FIGHT = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().incrementKarma(-25);
		}
		@Override
		public String getContent() {
			if(inApartment) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_FIGHT_APARTMENT", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "PROSTITUTE_FIGHT", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "给这个不知天高地厚的婊子上一课！", getProstitute());
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getProstitute(), "你打败了[npc.name]！");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY", getProstitute());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开",
						"离开[npc.name]，然后继续你的旅程。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
						Main.game.getDefaultDialogue(false)) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("找些乐子",
						UtilText.parse(getProstitute(), "很明显，[npc.name]希望你能干[npc.herHim]，或许你应该让她如愿[npc.herHim]……"),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX", getProstitute()));
				
			} else if (index == 3) {
				return new ResponseSex("温柔玩弄",
						UtilText.parse(getProstitute(), "很明显，[npc.name]希望你能干[npc.herHim]，或许你应该让她如愿[npc.herHim]如愿……"),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
								null, null,
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getProstitute()));
				
			} else if (index == 4) {
				return new ResponseSex("粗暴玩弄",
						UtilText.parse(getProstitute(), "很明显，[npc.name]希望你能干[npc.herHim]，或许你应该让她如愿[npc.herHim]如愿……"),
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getProstitute()),
								null, null,
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getProstitute()));
				
			} else if (index == 5) {
				return new ResponseSex("顺从",
						UtilText.parse(getProstitute(), "你忽然觉得击败了[npc.name]有些愧疚。或许向[npc.herHim]屈服，让[npc.herHim]主导跟你的性爱能让[npc.herHim]舒服一些？"),
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getProstitute()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_SUBMIT", getProstitute()));
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏",
						UtilText.parse(getProstitute(), "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具出手…… ")){
					@Override
					public void effects() {
						Main.mainController.openInventory(getProstitute(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 8 && getProstitute().isAbleToSelfTransform()) {
				return new Response("转化[npc.herHim]",
						"仔细观察[npc.name]会将自己转化成什么样……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getProstitute());
					}
				};
				
			} else if (index == 9 && getProstitute().isAbleToSelfTransform()) {
				return new Response("快速转化",
						"[npc.she]能够转化自己，你脑海中闪过一些点子……"
								+ "(你完成[npc.herHim]的转化后将返回选项界面。)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getProstitute(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 11 && Main.game.getPlayer().hasCompanions()) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();
				
				if(!companion.isAttractedTo(getProstitute())) {
					return new Response(UtilText.parse(companion, "三人行"),
							UtilText.parse(getProstitute(), "[com.Name]并没有被[npc.name]吸引，所以不愿意跟[npc.herHim]做爱。"),
							null);
					
				} else {
					return new ResponseSex(UtilText.parse(companion, "三人行"),
							UtilText.parse(getProstitute(), "跟[npc.name]来一场支配型性爱，并且让[com.name]也一起来玩。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getProstitute()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_THREESOME", getProstitute()));
				}
				
			} else if (index == 12 && Main.game.getPlayer().hasCompanions()) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();

				if(!companion.isAttractedTo(getProstitute())) {
					return new Response("交给[com.name]",
							UtilText.parse(getProstitute(), "[com.Name]并没有被[npc.name]吸引，所以不愿意跟[npc.herHim]做爱。"),
							null);
					
				} else {
					return new ResponseSex("交给[com.name]",
							UtilText.parse(getProstitute(), "告诉[com.name][com.she]可以跟[npc.name]找找乐子，你在一旁看着。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getProstitute()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getProstitute()));
				}
				
			} else if (index == 13 && Main.game.getPlayer().hasCompanions() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = Main.game.getPlayer().getMainCompanion();

				if(!getProstitute().isAttractedTo(companion)) {
					return new Response("献上[com.name]",
							UtilText.parse(getProstitute(), "[com.Name]并没有被[npc.name]吸引，所以不愿意跟[npc.herHim]做爱。"),
							null);
					
				} else if(!companion.isAttractedTo(getProstitute()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("献上[com.name]",
							UtilText.parse(getProstitute(), "你看得出来[com.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[com.herHim]这么做……"),
							null);
					
				} else {
					return new ResponseSex("献上[com.name]",
							UtilText.parse(getProstitute(), "告诉[npc.name][npc.she]可以使用[com.name]。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getProstitute())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getProstitute()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getProstitute(), "你被[npc.name]打败了！");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT", getProstitute());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getProstitute().isAttractedTo(Main.game.getPlayer()) && getProstitute().isWillingToRape()) {
				if (index == 1) {
					return new ResponseSex("做爱",
							"[npc.Name]强行压住了你……",
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX", getProstitute()));
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							UtilText.parse(getProstitute(), "[npc.Name]强行压住了你……"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getProstitute()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							UtilText.parse(getProstitute(), "[npc.Name]强行压住了你……"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getProstitute()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getProstitute()));
				}
				
			} else {
				if (index == 1) {
					return new Response("继续",
							"继续你的旅程。"
									+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
							AFTER_COMBAT_DEFEAT){
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_NPC_REMOVAL;
						}
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogue(false);
						}
						@Override
						public void effects() {
							Main.game.banishNPC(getProstitute());
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_PAID = new DialogueNode("返回", "", true) {
		@Override
		public String getDescription(){
			return "你已经爽过了，离开这里让[npc.name]恢复一下吧。";
		}
		@Override
		public String getContent() {
			if(inApartment) {
				if(Main.game.getPlayer().hasCompanions()) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
						if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_THREESOME", getProstitute());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_COMPANION", getProstitute());
						}
					}
				}
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID", getProstitute());
				
			} else {
				if(Main.game.getPlayer().hasCompanions()) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
						if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_THREESOME", getProstitute());
						} else {
							return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_COMPANION", getProstitute());
						}
					}
				}
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM", getProstitute());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						UtilText.parse(getProstitute(), "把[npc.name]留在身后，继续你的路程。"),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						if(inApartment) {
							if(Main.game.getPlayer().hasCompanions() && Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_LEAVE_COMPANION", getProstitute()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_PAID_LEAVE", getProstitute()));
							}
							
						} else {
							if(Main.game.getPlayer().hasCompanions() && Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_LEAVE_COMPANION", getProstitute()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_STORM_LEAVE", getProstitute()));
							}
						}
					}
				};
				
			} else if(index >= 4) {
				return ALLEY_PROSTITUTE.getResponse(responseTab, index);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(getProstitute(), "你已经爽过了，离开这里让[npc.name]恢复一下吧。");
		}
		@Override
		public String getContent() {
			if(Main.game.getPlayer().hasCompanions()) {
				if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer().getMainCompanion())) {
					if(Main.sex.getAllParticipants(false).contains(Main.game.getPlayer())) {
						return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_THREESOME", getProstitute());
					} else {
						return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_COMPANION", getProstitute());
					}
				}
			}
			return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY", getProstitute());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开",
						"离开[npc.name]，然后继续你的旅程。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_VICTORY_LEAVE", getProstitute()));
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你可以随意拿取[npc.namePos]衣服和道具，谁也拦不了你……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getProstitute(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("瘫软", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "你在[npc.namePos]的支配下精疲力竭，需要休息一会儿。";
		}

		@Override
		public String getContent() {
			if(inApartment) {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT_APARTMENT", getProstitute());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT", getProstitute());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						"继续你的旅程。"
							+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/prostitute", "AFTER_SEX_DEFEAT_LEAVE", getProstitute()));
						Main.game.banishNPC(getProstitute());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
