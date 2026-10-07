package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMBreedingStall;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.9.4
 * @author Innoxia
 */
public class PregnancyRoulette {

	private static List<GenericSexualPartner> breeders = new ArrayList<>();
	private static int breederIndex = 0;
	private static int roll = 1;
	private static GenericSexualPartner mother;
	private static GameCharacter selectedBreeder;
	private static SexType murkPreference = null;
	
	private static void initBreeder(NPC partner) {
		partner.deleteAllEquippedClothing(true);
		partner.clearFetishes();
		partner.clearFetishDesires();
		partner.addFetish(Fetish.FETISH_IMPREGNATION);
		partner.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		partner.setPlayerKnowsName(true);
		partner.setPenisVirgin(false);
		partner.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		partner.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		partner.setAttribute(Attribute.VIRILITY, (partner.getPenisRawSizeValue()*2)+(partner.getTesticleSize().getValue() * 5)+partner.getPenisRawCumStorageValue());
		
		try {
			Main.game.addNPC(partner, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initMother() {
		mother = new GenericSexualPartner(Gender.F_V_B_FEMALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		mother.clearFetishes();
		mother.clearFetishDesires();
		mother.addFetish(Fetish.FETISH_PREGNANCY);
		mother.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		mother.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		mother.deleteAllEquippedClothing(true);
		mother.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		mother.setPlayerKnowsName(true);
		mother.useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), mother, false);
		try {
			Main.game.addNPC(mother, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNode PREGNANCY_ROULETTE = new DialogueNode("怀孕轮盘赌柜台", "", false) {
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)
					|| (Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer()));
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE"));
				
				boolean preg = Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY);
				boolean impreg = Main.game.getPlayer().hasFetish(Fetish.FETISH_IMPREGNATION);
				if(preg) {
					if(impreg) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BOTH_FETISH"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_PREGNANCY_FETISH"));
					}
					
				} else if(impreg) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_IMPREGNATION_FETISH"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_NO_FETISH"));
				}
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_END"));
				
			} else if(Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_EPONA_IMPREGNATED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_REPEAT"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "你走到柜台旁，也向她问好，那满面春光的马女问起来，[epona.speech(还有兴趣再来一场怀孕轮盘赌吗？");
					
					if(Main.game.getDialogueFlags().eponaStamps>=6) {
						UtilText.nodeContentSB.append("或者你想兑现印章，来骑一场！)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append("你还需要"+Util.intToString(6-Main.game.getDialogueFlags().eponaStamps)+"个印章才能来骑呢！)]"
								+ "</p>");
					}
				}  else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "你走到柜台旁，也向她问好，那满面春光的马女问起来，[epona.speech(有兴趣再来一场怀孕轮盘赌吗？开始收集印章吧！)]"
							+ "</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				if(index==1) {
					return new Response("继续", "伊波娜已经告诉了你怀孕轮盘赌的规则，你思索着接下来该做什么……", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_GREETING_UTIL_END"));
						}
					};
				} else {
					return null;
				}
				
			} else if(Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("继续", "见到伊波娜做了孩子的妈妈，你开心得很。", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getNpc(Epona.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_GREETING_UTIL_PREG_END"));
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index == 1) {
					return new Response("规则", "询问伊波娜怀孕轮盘赌的规则。", PREGNANCY_ROULETTE_RULES);
					
				} else if(index==2) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("雄性配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你已经怀孕了，所以无法报名“母亲”一方！", null);
						
					} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
						return new Response("雄性配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你的子宫里装满了卵，所以无法报名“母亲”一方！", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("雄性配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "如果小穴里已经装满精液，就无法报名怀孕轮盘赌了！", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("雄性配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你没有阴道，所以无法报名“母亲”一方！", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("雄性配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你不能使用自己的阴道，所以无法报名“母亲”一方！", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("雄性配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你的钱不够，所以无法报名“母亲”一方！", null);
						
					} else {
						return new Response("雄性配种("+UtilText.formatAsMoney(10000, "span")+")", "报名成为怀孕轮盘赌的“母亲”一方，被雄性配种。", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, ((s) -> s.getRace()==Race.HARPY));
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("扶她配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你已经怀孕了，所以无法报名“母亲”一方！", null);
						
					} else if(Main.game.getPlayer().hasIncubationLitter(SexAreaOrifice.VAGINA)) {
						return new Response("扶她配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你的子宫里装满了卵，所以无法报名“母亲”一方！", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("扶她配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "如果小穴里已经装满精液，就无法报名怀孕轮盘赌了！", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("扶她配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你没有阴道，所以无法报名“母亲”一方！", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("扶她配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你不能使用自己的阴道，所以无法报名“母亲”一方！", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("扶她配种("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你的钱不够，所以无法报名“母亲”一方！", null);
						
					} else {
						return new Response("扶她配种("+UtilText.formatAsMoney(10000, "span")+")", "报名成为怀孕轮盘赌的“母亲”一方，被扶她配种。", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==4) {
					if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
						return new Response("繁育者("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你没有阴茎，所以无法报名“繁育者”一方！", null);
						
					} else if(!Main.game.getPlayer().isAbleToOrgasm()) {
						return new Response("繁育者("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你无法高潮，所以无法报名“繁育者”一方！", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("繁育者("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你不能使用自己的阴茎，所以无法报名“繁育者”一方！", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("繁育者("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "你的钱不够，所以无法报名“繁育者”一方！", null);
						
					} else {
						return new Response("繁育者("+UtilText.formatAsMoney(10000, "span")+")", "报名成为怀孕轮盘赌的繁育者。", PREGNANCY_ROULETTE_BREEDER) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								// Skew the dice roll in the player's favour (lowest number goes first):
								Dice d = new Dice(Util.newHashMapOfValues(
										new Value<>(DiceFace.ONE, 4f),
										new Value<>(DiceFace.TWO, 3f),
										new Value<>(DiceFace.THREE, 2f),
										new Value<>(DiceFace.FOUR, 1f),
										new Value<>(DiceFace.FIVE, 0.5f),
										new Value<>(DiceFace.SIX, 0.25f)));
								d.roll();
								roll = d.getFace().getValue();
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
									
								} else {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
								}
								
								initMother();
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("骑乘伊波娜", "你没收集到足够的印章，不能骑乘伊波娜！至少需要六个印章，你现在拥有"+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+"个。", null);
						
					} else {
						return new ResponseSex("骑乘伊波娜", "告诉伊波娜你想交出印章，来骑乘她。(作为支配方开始性爱。)",
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(Main.game.getNpc(Epona.class)),
										null,
										null) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								EPONA_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_START_SEX_AS_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==7) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("伊波娜骑乘", "你没收集到足够的印章，不能被伊波娜骑！至少需要六个印章，你现在拥有"+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+"个。", null);
						
					} else {
						return new ResponseSex("伊波娜骑乘", "告诉伊波娜你想交出印章，让她骑你。(作为服从方开始性爱。)",
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(Epona.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null) {
									@Override
									public boolean isPublicSex() {
										return false;
									}
								},
								EPONA_POST_SEX,
								UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_START_SEX_AS_DOM")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==11 && Main.game.getNpc(Murk.class).isSlave()) {
					return new Response("[murk.Name]", "问伊波娜你能不能看看[murk.name]。", MURK);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode EPONA_POST_SEX = new DialogueNode("结束", "伊波娜花了一会调整呼吸，准备回去工作。", false) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Epona.class))>=Main.game.getNpc(Epona.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_POST_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_POST_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_GREETING_UTIL = new DialogueNode("怀孕轮盘赌柜台", "", false, true) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_RULES = new DialogueNode("怀孕轮盘赌柜台", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	private static String getBreederPanel(NPC breeder) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-half-width'>"
				+ "[npc.Name] - [npc.FullRace(true)]<br/>"
				+ "[pc.cockColour(true)]的[pc.cockRace][pc.cock]有[pc.penisValue]长，[pc.CockGirth]的棒体，阴囊[pc.ballSize]。"
				+ "</div>");
		
		return UtilText.parse(breeder, sb.toString());
	}
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION"));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index==1) {
				return new Response("等待", "等待伊波娜带领繁育者进入房间。", PREGNANCY_ROULETTE_MOTHER_SELECTION) {
					@Override
					public void effects() {
						Main.game.getNpc(Epona.class).useItem(Main.game.getItemGen().generateItem("innoxia_pills_fertility"), Main.game.getPlayer(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_SELECTION = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_SELECTION"));
			}

			for(int i=0;i< breeders.size()/2;i++) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='width:100%; margin:0'>");
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2)));
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2 +1)));
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index<=breeders.size()) {
				NPC breeder = breeders.get(index-1);
				return new Response(breeder.getName(true), "告诉伊波娜你觉得"+breeder.getName(true)+"能让你怀孕。", PREGNANCY_ROULETTE_MOTHER_START) {
					@Override
					public void effects() {
						selectedBreeder=breeder;
						Collections.shuffle(breeders);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_START = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_START", Util.newArrayListOfValues(selectedBreeder));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				NPC breeder = breeders.get(breederIndex);
				
				if(!Main.game.getPlayer().isTaur() && !Main.game.getPlayer().hasLegs() && Main.game.getPlayer().getGenitalArrangement()!=GenitalArrangement.CLOACA_BEHIND) {
					return new Response("面对"+breeder.getName(true),
							"由于你没有腿，而且阴道位置向前，没法趴着接受配种。你不得不躺下来……",
							null);
				}
				
				return new ResponseSex("面对"+breeder.getName(true), "面朝上躺下，让"+breeder.getName(true)+"第一个来上你。",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLustNoText(80);
								}
							}
						},
						null,
						null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else if(index==2) {
				NPC breeder = breeders.get(breederIndex);

				if(Main.game.getPlayer().isTaur()) {
					return new Response("背对"+breeder.getName(true),
							"由于你的下半身是兽态的[pc.legRace]，所以没法躺到配种台上。你不得不趴下来……",
							null);
				}
				
				if(!Main.game.getPlayer().hasLegs() && Main.game.getPlayer().getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
					return new Response("背对"+breeder.getName(true),
							"由于你没有腿，而且阴道位置向后，没法躺着接受配种。你不得不趴下来……",
							null);
				}
				
				return new ResponseSex("背对"+breeder.getName(true), "面朝下趴下，让"+breeder.getName(true)+"第一个来上你。",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_BACK))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLustNoText(80);
								}
							}
						},
						null,
						null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROULETTE_SEX = new DialogueNode("", "", true) {
		@Override
		public String getLabel() {
			return breeders.get(breederIndex-1).getName(true)+"完事了";
		}
		@Override
		public String getDescription() {
			return UtilText.parse(breeders.get(breederIndex-1), "[npc.name]已经给你内射，结束了回合，于是退了下去……");
		}
		@Override
		public String getContent() {
			NPC breeder = breeders.get(breederIndex-1);
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_ROULETTE_SEX", Util.newArrayListOfValues(breeder));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(breederIndex<breeders.size()) {
				if(index==1) {
					NPC breeder = breeders.get(breederIndex);
					
					return new ResponseSex("面对"+breeder.getName(true), "调整姿势面朝上躺下，让"+breeder.getName(true)+"下一个来上你。",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStall(
									Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLustNoText(80);
									}
								}
							},
							null,
							null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else if(index==2) {
					NPC breeder = breeders.get(breederIndex);

					if(Main.game.getPlayer().isTaur()) {
						return new Response("背对"+breeder.getName(true),
								"由于你的下半身是兽态的[pc.legRace]，所以没法躺到配种台上。你不得不趴下来……",
								null);
					}
					
					return new ResponseSex("背对"+breeder.getName(true), "调整姿势面朝下趴着，让"+breeder.getName(true)+"下一个来上你。",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStall(
									Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_BACK))) {
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLustNoText(80);
									}
								}
							},
							null,
							null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("结束", "所有六名繁育者都在你[pc.pussy+]中留下精液了。", PREGNANCY_ROULETTE_MOTHER_FINISHED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Epona.class).useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false));
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_FINISHED = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant()) {
					if(Objects.equals(Main.game.getPlayer().getPregnantLitter().getFather(), selectedBreeder)) {
						return new Response("你赢了！", "你成功猜中了，"+selectedBreeder.getName(true)+"正是孩子的父亲！", PREGNANCY_ROULETTE_MOTHER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(50000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
						
					} else {
						return new Response("你输了", "你没有猜中"+selectedBreeder.getName(true)+"是孩子的父亲，所以输掉了赌注……", PREGNANCY_ROULETTE_MOTHER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
					}
						
				} else {
					return new Response("你输了", "你没有怀上，所以所有人都输掉了赌注……", PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_WINNER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_LOSER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isFeminine()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FUTA", Util.newArrayListOfValues(mother)));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER", Util.newArrayListOfValues(mother)));
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "伊波娜投出骰子，你盯着骰子经过一番滚动，停在了数字"+Util.intToString(roll, false)+"上。"
						+ "[epona.speech(那好吧，[pc.name]，你"+Util.intToPosition(roll)+"个上！我会继续给剩下的人投！)]"
					+ "</p>"
					+ "<p>"
						+ "你们随后都在原地等侯伊波娜给其他繁育者投点数。"
						+ "每次她投出已经出现的数字就会重投，如果她能选个更有效率的方式，你们大概不至于等这么久。"
					+ "</p>"
					+ "<p>"
						+ "不过，最终伊波娜还是完成了排序，你们走上前按顺序排成了一排……"
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<i>你是<b>"+Util.intToPosition(roll)+"个</b>！</i>"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1){
				return new ResponseSex(roll==1?"先发":"你的回合",
						roll==1
							?"由于你投出了一，所以你是第一个上场配种的。"
							:"上一个繁育者已经退下，现在是你的回合，上前去准备开始干那溢满了精液的小穴。",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(mother,
										mother.hasLegs() || mother.getGenitalArrangement()!=GenitalArrangement.CLOACA_BEHIND
											?SexSlotBreedingStall.BREEDING_STALL_BACK
											:SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								character.setArousal(50);
								character.setLustNoText(80);
							}
						},
						null,
						null,
						PREGNANCY_ROULETTE_BREEDER_POST_SEX,
						roll==1
							?UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FIRST", Util.newArrayListOfValues(mother))
							:UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_MIDDLE", Util.newArrayListOfValues(mother))){
					@Override
					public void effects() {
						for(int i=0; i<roll-1; i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCum(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity());
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_POST_SEX = new DialogueNode("结束", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(mother, "你已经高潮过，结束了回合，是时候从[npc.name]身边退开了……");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待", "等伊波娜回来。", PREGNANCY_ROULETTE_BREEDER_FINISHED) {
					@Override
					public void effects() {
						for(int i=roll-1; i<breeders.size(); i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCum(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity());
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_FINISHED = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FINISHED")
					+mother.useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), mother, false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(mother.isPregnant()) {
					if(mother.getPregnantLitter().getFather()!=null && mother.getPregnantLitter().getFather().isPlayer()) {
						return new Response("赢家！", "你让"+mother.getName(true)+"怀孕了！", PREGNANCY_ROULETTE_BREEDER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
						
					} else {
						return new Response("你输了", "你没能让"+mother.getName(true)+"怀孕，所以你输掉了赌注……", PREGNANCY_ROULETTE_BREEDER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
					}
						
				} else {
					return new Response("你输了", "没有人让"+mother.getName(true)+"怀孕，所以所有人都输掉了赌注……", PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
							Main.game.banishNPC(mother);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_WINNER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_LOSER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY = new DialogueNode("", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode MURK = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaMurkOwnerIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("进入", "进入关押[murk.name]的房间……", MURK_ALONE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkOwnerIntroduced, true);
					}
				};
				
			} else if(index==0) {
				return new Response("离开", "你对拜访[murk.name]心生了犹豫，还是选择了转身离开……", MURK_BACK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkOwnerIntroduced, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MURK_BACK = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_BACK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	private static void applyMurkReactions() {
		if(Main.game.getNpc(Murk.class).isVisiblyPregnant()) {
			Main.game.getNpc(Murk.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Murk.class), true);
		}
		Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSeen, true);
	}
	
	public static final DialogueNode MURK_ALONE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Map<SexType, Integer> sexMap = new HashMap<>();
			if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				sexMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), 10);
			}
			if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
				sexMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), 3);
			}
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				sexMap.put(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), 1);
			}
			if(sexMap.isEmpty()) {
				murkPreference = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER);
			} else {
				murkPreference = Util.getRandomObjectFromWeightedMap(sexMap);
			}
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_ALONE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.murkMaster)) {
				if(index==1) {
					return new Response("服从", "听从主人的命令，跪下来侍奉他的鸡巴。", MURK_SUBMIT_ACCEPT) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							applyMurkReactions();
						}
					};
				}
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaMurkSubmitted)) {
				if(index==1) {
					return new Response("服从", "听从[murk.name]的命令，跪下来侍奉[murk.her]的鸡巴。", MURK_SUBMIT_ACCEPT) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							applyMurkReactions();
						}
					};
					
				} else if(index==2) {
					return new Response("拒绝", "让[murk.name]别牛逼了，你是不会屈服于[murk.herHim]的。", MURK_SUBMIT_REFUSE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, false);
							applyMurkReactions();
						}
					};
				}
				
			} else {
				if(index==1) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.murkLectured)) {
						return new Response("教训", "你今天已经教训过[murk.name]了，明天之前没有理由再这样来一次……", null);
					}
					return new Response("教训", "花时间教训[murk.name]一下，让[murk.she]直到自己错在哪了。", MURK_LECTURING) {
						@Override
						public void effects() {
							applyMurkReactions();
							if(Main.game.getNpc(Murk.class).isFeminine() && Main.game.getNpc(Murk.class).getAffection(Main.game.getPlayer())<25) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), 5));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
					
				} else if(index==2) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.murkSpanked)) {
						return new Response("打屁股", "你已经打过[murk.name]的屁股了，你只能明天再来……", null);
					}
					return new Response("打屁股", "花些时间打[murk.name]的屁股。", MURK_SPANKING) {
						@Override
						public void effects() {
							applyMurkReactions();
							if(Main.game.getNpc(Murk.class).isFeminine()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), 10));
							} else {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), -10));
							}
						}
					};
					
				} else if(index==3) {
					return new ResponseSex(
							"操",
							"强硬地上了[murk.name]。",
							true,
							false,
							new SMLyingDown(
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
									Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(Murk.class), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							null,
							null,
							AFTER_MURK_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SEX_DOM")) {
						@Override
						public void effects() {
							applyMurkReactions();
						}
					};
					
				} else if(index==4 && !Main.game.getNpc(Murk.class).isFeminine()) {
					return new ResponseSex(
							"顺从",
							"告诉[murk.name]你想让[murk.herHim]支配你……",
							true,
							false,
							new SMAllFours(
									Util.newHashMapOfValues(
										new Value<>(
											Main.game.getNpc(Murk.class),
											murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA || murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
												?SexSlotAllFours.HUMPING
												:SexSlotAllFours.IN_FRONT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return murkPreference;
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return murkPreference;
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									
									if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
										
									} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
										
									} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
									}
									
									map.put(Main.game.getNpc(Murk.class), Util.newArrayListOfValues(CoverableArea.PENIS));
									
									return map;
								}
							},
							null,
							null,
							AFTER_MURK_SEX,
							UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette",
									murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA
										?"MURK_SEX_SUB_VAGINA"
										:(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
											?"MURK_SEX_SUB_ANUS"
											:(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH
												?"MURK_SEX_SUB_ORAL"
												:"MURK_SEX_SUB_HANDJOB")))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, true);
							applyMurkReactions();
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS){
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
								
							} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
								
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
							}
						}
					};
					
				} else if(index==5 && !Main.game.getNpc(Murk.class).isFeminine()) {
					return new Response("雌化",
							"使用架子顶端的药水将[murk.name]雌化成一个唯命是从的鼠女。"
									+ "<br/>[style.italicsGenericTf(这是永久转化，选择后你无法再对她使用“顺从”选项。)]",
							MURK_FEMINISE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, false);
							applyMurkReactions();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_FEMINISE"));
							Main.game.getTextStartStringBuilder().append(((Murk)Main.game.getNpc(Murk.class)).applyFeminisation());
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_FEMINISE_END"));
						}
					};
					
				} else if(index==0) {
					return new Response("离开", "离开储藏室，告诉伊波娜你跟[murk.name]的事情干完了。", MURK_LEAVE) {
						@Override
						public void effects() {
							applyMurkReactions();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_ALONE_LEAVE"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_LECTURING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkLectured, true);
		}
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_LECTURING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_SPANKING = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.murkSpanked, true);
		}
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SPANKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_MURK_SEX = new DialogueNode("结束", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getDescription() {
			return "[murk.Name]已经享受够了……";
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_AS_DOM");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_AS_SUB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("离开", "让[murk.name]躺在地上继续喘息，转身离开", MURK_LEAVE){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_DOM_LEAVE"));
						}
					};
					
				} else {
						return new Response("离开", "按[murk.name]说的做，离开", MURK_LEAVE){
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_SUB_LEAVE"));
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_FEMINISE = new DialogueNode("", "", true) {
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
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_LEAVE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setNearestLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_CORRIDOR, false);
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return GamblingDenDialogue.CORRIDOR.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MURK_SUBMIT_ACCEPT = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex(
						"顺从",
						"你向主人屈服，任他支配你……",
						true,
						false,
						new SMAllFours(
								Util.newHashMapOfValues(
									new Value<>(
										Main.game.getNpc(Murk.class),
										murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA || murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
											?SexSlotAllFours.HUMPING
											:SexSlotAllFours.IN_FRONT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
							@Override
							public boolean isPublicSex() {
								return false;
							}
							@Override
							public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
								return false;
							}
							@Override
							public boolean isPositionChangingAllowed(GameCharacter character) {
								return false;
							}
							@Override
							public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return murkPreference;
								}
								return super.getForeplayPreference(character, targetedCharacter);
							}
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(!character.isPlayer()) {
									return murkPreference;
								}
								return super.getMainSexPreference(character, targetedCharacter);
							}
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
								
								if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
									
								} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS) {
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
									
								} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
									map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
								}
								
								map.put(Main.game.getNpc(Murk.class), Util.newArrayListOfValues(CoverableArea.PENIS));
								
								return map;
							}
						},
						null,
						null,
						AFTER_MURK_SEX,
						UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette",
								murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA
									?"MURK_SEX_SUB_VAGINA"
									:(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS
										?"MURK_SEX_SUB_ANUS"
										:(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH
											?"MURK_SEX_SUB_ORAL"
											:"MURK_SEX_SUB_HANDJOB")))) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSeen, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, true);
					}
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						if(murkPreference.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
							
						} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.ANUS){
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
							
						} else if(murkPreference.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							
						} else {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
						}
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MURK_SUBMIT_REFUSE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_REFUSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};
	
	
}
