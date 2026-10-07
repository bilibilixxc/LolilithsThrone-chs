package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.3.5.5
 * @author Innoxia
 */
public class TunnelImpsDialogue {

	private static TransformativePotion potion = null;
	private static TransformativePotion companionPotion = null;
	
	public static List<GameCharacter> getImpGroup() {
		List<GameCharacter> guards = new ArrayList<>();
		guards.addAll(Main.game.getCharactersPresent());
		guards.removeIf(npc -> Main.game.getPlayer().getParty().contains(npc) || !(npc instanceof ImpAttacker));
		Collections.sort(guards, (a, b)->b.getLevel()-a.getLevel());
		return guards;
	}
	
	public static ImpAttacker getImpLeader() {
		return (ImpAttacker) getImpGroup().get(0);
	}

	public static void banishImpGroup() {
		for(GameCharacter imp : getImpGroup()) {
			if(!imp.isSlave()) {
				Main.game.banishNPC(imp.getId());
			}
		}
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		if(isCompanionDialogue()) {
			List<GameCharacter> allCharacters = new ArrayList<>();
			allCharacters.add(getMainCompanion());
			allCharacters.addAll(getImpGroup());
			return allCharacters;
			
		} else {
			return getImpGroup();
		}
	}
	
	private static boolean isCompanionDialogue() {
		return !Main.game.getPlayer().getCompanions().isEmpty();
	}
	
	private static String getImpEncounterId() {
		StringBuilder idSB = new StringBuilder();
		if(isCompanionDialogue()) {
			idSB.append("Companions");
		}
		if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA)) {
			// Alpha imp group encounter:
			idSB.append("Alpha");
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_DEMON)) {
			// Demon group encounter:
			idSB.append("Demon");
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES)) {
			// Female imps encounter:
			idSB.append("Females");
			
		} else {
			// Male imps encounter:
			idSB.append("Males");
		}
		
		return idSB.toString();
	}
	
	public static final DialogueNode IMP_ATTACK = new DialogueNode("小恶魔团伙", "一群小恶魔发动攻击！", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!isCompanionDialogue()) {
				if (index == 1) {
					return new ResponseCombat("战斗", "挺身而出，与这群小恶魔一决胜负！", getImpLeader(), getImpGroup(), null);
					
				} else if (index == 2) {
					return new Response("献上财物", "这些小恶魔对你的钱没兴趣，你只能选择战或是降……", null);
					
				} else if (index == 3) {
					return new Response("献出身体",
							"向小恶魔献出你的身体，避免不必要的伤害。",
							IMP_ATTACK_OFFER_BODY,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new ResponseCombat("战斗", UtilText.parse(getMainCompanion(), "挺身而出，在[npc.name]的帮助下与小恶魔战斗！"), getImpLeader(), getImpGroup(), null);
					
				} else if (index == 2) {
					return new Response("献上财物", "这些小恶魔对你的钱没兴趣，你只能选择战或是降……", null);
					
				} else if (index == 3) {
					return new Response("献出自己的身体",
							UtilText.parse(getMainCompanion(), "你决定把身体献给小恶魔，让[npc.name]靠边站，不要被波及到。"),
							IMP_ATTACK_OFFER_BODY,
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null,
							null,
							null){
						@Override
						public boolean isSexHighlight() {
							return true;
						}
					};
					
				} else if (index == 4) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "三人行"),
								UtilText.parse(companion, "很明显，[npc.name]一点儿也不想把自己交给小恶魔，你也不能强迫[npc.herHim]这样做……"),
								null);
						
					} else {
						return new Response("三人行",
								UtilText.parse(companion, "主动建议小恶魔跟你和[npc.name]做爱，来避免暴力冲突。"),
								IMP_ATTACK_OFFER_THREESOME,
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null) {
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(companion, "很明显，[npc.name]一点儿也不想把自己交给小恶魔，你也不能强迫[npc.herHim]这样做……"),
								null);
						
					} else {
						return new Response(UtilText.parse(companion, "献上[npc.name]"),
								UtilText.parse(companion, "告诉这些小恶魔他们可以随时使用[npc.namePos]的身体，来避免暴力冲突。"),
								IMP_ATTACK_OFFER_COMPANION) {
							@Override
							public void effects() {
								if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
							@Override
							public boolean isSexHighlight() {
								return true;
							}
						};
					}
					
				} else {
					return null;
				}
			
			}
		}
	};
	
	public static final DialogueNode IMP_ATTACK_OFFER_BODY = new DialogueNode("小恶魔团伙", "", true) {

		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_BODY", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("吐出",
								"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b>的性癖，你乐于被转化，所以无法把转化液体吐出来！",
								null);
					} else {
						return new Response("吐出", "吐掉药水。", AFTER_COMBAT_TRANSFORMATION_SOLO) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters())); // Re-use TF refuse dialogue
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

					return new Response("吞咽",
							(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
								?"咽下这种药水，如果小恶魔们的话可信，那么它会让喝下这种药水的人长出阴茎和阴道，并变得女性化并长出乳房……"
								:"咽下药水，如果小恶魔们的话可信，它会让喝下药水的人同时长出阴茎和阴道……",
							AFTER_COMBAT_TRANSFORMATION_SOLO,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()) // Re-use TF refuse dialogue
									);
							Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));
						}
					};
				}
				
			} else {
				return AFTER_COMBAT_TRANSFORMATION_SOLO.getResponse(responseTab, index); // Sex responses
			}
			
			return null;
		}
	};

	public static final DialogueNode IMP_ATTACK_OFFER_COMPANION = new DialogueNode("小恶魔团伙", "", true) {

		public void applyPreParsingEffects() {
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(companionPotion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					return new Response("要求吐出",
							UtilText.parse(getMainCompanion(), "告诉[npc.name]吐出小恶魔试图强迫[npc.herHim]喝下的药水。"
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听你的。)"
											:"")),
							AFTER_OFFER_COMPANION_TRANSFORMATION) {
						@Override
						public void effects(){
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT_COMPANION_SWALLOWS", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SPIT", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
	
						return new Response("要求吞咽",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"让[npc.Name]咽下药水，如果小恶魔们的话可信，那么药水会让喝下药水的人长出阴茎和阴道，并变得女性化并长出乳房。"
										:"让[npc.Name]咽下药水，如果小恶魔们的话可信，那么药水会让喝下药水的人同时长出阴茎和阴道。")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?" (然而，由于讨厌被转化，[npc.sheIsFull]并不愿意听从你！)"
											:"")),
								AFTER_OFFER_COMPANION_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
	
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_ORDER_SWALLOW_COMPANION_SPITS", getAllCharacters()));
								}
							}
						};
				}
				
			} else {
				return AFTER_OFFER_COMPANION_TRANSFORMATION.getResponse(responseTab, index); // Sex responses
			}
			
			return null;
		}
	};
	
	
	public static final DialogueNode IMP_ATTACK_OFFER_THREESOME = new DialogueNode("小恶魔团伙", "", true) {

		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
			if(getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(potion != null && companionPotion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("吐出",
									"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b>的性癖，你乐于被转化，所以无法把转化液体吐出来！",
								null);
					} else {
						return new Response("吐出", 
								UtilText.parse(getMainCompanion(),
										"吐出药水。([npc.Name]将决定自己是吐出还是吞下药剂。)"), AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
									
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters()) // Re-use description
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT", getAllCharacters()));
								}
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					return new Response("吞咽",
							UtilText.parse(getMainCompanion(),
								((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
									?"咽下这种药水，如果小恶魔们的话可信，那么它会让喝下这种药水的人长出阴茎和阴道，并变得女性化并长出乳房……"
									:"咽下药水，如果小恶魔们的话可信，那么它会让喝下药水的人长出阴茎和阴道……")
								+ "([npc.Name]将决定[npc.herself]是吐出还是吞下药剂。)"),
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							if(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isPositive()) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
							} else {
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SPITS", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("吐出(双人)");
					}
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("吐出(双人)",
								UtilText.parse(getMainCompanion(),
									"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
										+"</b>性癖，你太喜欢被转化了，以至于无法吐出改造液，也无法让[npc.name]也这么做！"),
								null);
						
					} else {
						return new Response("吐出(双人)",
								UtilText.parse(getMainCompanion(), "吐出药水，并让[npc.Name]也这样做。"
										+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
												?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听你的。)"
												:"")),
								AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects(){
								if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
									
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SPIT", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_BOTH_SPIT_WITH_ORDER", getAllCharacters()));
								}
							}
						};
						
					}
					
				} else if (index == 7) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

						return new Response("吞咽(双人)",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"咽下这种药水，如果小恶魔们的话可信，那么它能让喝下这种药水的人长出阴茎和阴道，还能变得女性化并长出乳房。"
										:"咽下药水，如果小恶魔们的话可信，它会让喝下药水的人同时长出阴茎和阴道……")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?"咽下后，告诉[npc.Name]也这样做。(不过，由于[npc.she]不喜欢被转化，所以[npc.sheIsFull]不太可能听你的！)"
											:"咽下后，告诉[npc.Name]也这样做……")),
								AFTER_COMBAT_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
									}
								}
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters())
											+ UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_COMPANION_SWALLOWS", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));

									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								}
							}
						};
				}
				
			} else if(potion==null && companionPotion==null) {
				return AFTER_COMBAT_TRANSFORMATION.getResponse(responseTab, index);  // Sex responses
				
			} else if(potion != null) {
				if (index == 1) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse();
					};
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("吐出",
								"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b>的性癖，你乐于被转化，所以无法把转化液体吐出来！",
								null);
					} else {
						return new Response("吐出", "吐出药水。", AFTER_COMBAT_TRANSFORMATION) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 2) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					return new Response("吞咽",
							(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
								?"咽下这种药水，如果小恶魔们的话可信，那么它会让喝下这种药水的人长出阴茎和阴道，并变得女性化并长出乳房……"
								:"咽下药水，如果小恶魔们的话可信，它会让喝下药水的人同时长出阴茎和阴道……",
							AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorruptionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							for(GameCharacter imp : getImpGroup()) {
								imp.setKnowsCharacterArea(CoverableArea.VAGINA, Main.game.getPlayer(), true);
								imp.setKnowsCharacterArea(CoverableArea.PENIS, Main.game.getPlayer(), true);
								if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
									imp.setKnowsCharacterArea(CoverableArea.BREASTS, Main.game.getPlayer(), true);
								}
							}
							
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getAllCharacters()));
							Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(potion, Main.game.getPlayer()));
						}
					};
					
				} else if (index == 6) {
					return new Response("吞咽(双人)",
							UtilText.parse(getMainCompanion(), "由于小恶魔无法把药水灌进[npc.namePos]的嘴，所以他们并没有试图强迫[npc.herHim]喝下他们的转化药水。"),
							null);
					
				}  else if (index == 7) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("吐出(双人)");
					}
					return new Response("吐出(双人)",
							UtilText.parse(getMainCompanion(), "由于小恶魔无法把药水灌进[npc.namePos]的嘴，所以他们并没有试图强迫[npc.herHim]喝下他们的转化药水。"),
							null);
				}
				
			} else {
				if (index == 1) {
					return new Response("吐掉", UtilText.parse(getMainCompanion(),"由于小恶魔们无法把药水倒进你的嘴巴，所以它们没有继续理会你，而是专注于转化[npc.Name]！"), null);
					
				} else if (index == 2) {
					return new Response("咽下", UtilText.parse(getMainCompanion(),"由于小恶魔们无法把药水倒进你的嘴巴，所以它们没有继续理会你，而是专注于转化[npc.Name]！"), null);
					
				} else if (index == 6) {
					if(Main.game.isSpittingDisabled()) {
						return Response.getDisallowedSpittingResponse("命令吐出");
					}
					return new Response("要求吐出",
							UtilText.parse(getMainCompanion(), "告诉[npc.name]吐出小恶魔试图强迫[npc.herHim]喝下的药水。"
									+ (getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)
											?" (然而，因为[npc.name]拥有"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(getMainCompanion())+"的性癖，[npc.sheIsFull]并不愿意听你的。)"
											:"")),
							AFTER_COMBAT_TRANSFORMATION) {
						@Override
						public void effects(){
							if(getMainCompanion().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
								for(GameCharacter imp : getImpGroup()) {
									imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
									imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
									if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
										imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
									}
								}
								
								Main.game.getTextStartStringBuilder().append(
										UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
							} else {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_SPIT_WITH_ORDER", getAllCharacters()));
							}
						}
					};
					
				} else if (index == 7) {
					ArrayList<AbstractFetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorruptionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
	
						return new Response("要求吞咽",
								UtilText.parse(getMainCompanion(),
									((Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY)
										?"让[npc.Name]咽下药水，如果小恶魔们的话可信，那么药水会让喝下药水的人长出阴茎和阴道，并变得女性化并长出乳房。"
										:"让[npc.Name]咽下药水，如果小恶魔们的话可信，那么药水会让喝下药水的人同时长出阴茎和阴道。")
									+(getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()
											?" (然而，由于讨厌被转化，[npc.sheIsFull]并不愿意听从你！)"
											:"")),
								AFTER_COMBAT_TRANSFORMATION,
								applicableFetishes,
								applicableCorruptionLevel,
								null,
								null,
								null) {
							@Override
							public void effects(){
								if(!getMainCompanion().getFetishDesire(Fetish.FETISH_TRANSFORMATION_RECEIVING).isNegative()) {
									for(GameCharacter imp : getImpGroup()) {
										imp.setKnowsCharacterArea(CoverableArea.VAGINA, getMainCompanion(), true);
										imp.setKnowsCharacterArea(CoverableArea.PENIS, getMainCompanion(), true);
										if(Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE || Main.getProperties().getForcedTFTendency()==ForcedTFTendency.FEMININE_HEAVY) {
											imp.setKnowsCharacterArea(CoverableArea.BREASTS, getMainCompanion(), true);
										}
									}
	
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_OBEYS_ORDER_TO_SWALLOW", getAllCharacters()));
									Main.game.getTextStartStringBuilder().append(getImpLeader().applyPotion(companionPotion, getMainCompanion()));
								} else {
									Main.game.getTextStartStringBuilder().append(
											UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_THREESOME_REFUSES_ORDER_TO_SWALLOW", getAllCharacters()));
								}
							}
						};
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {

		@Override
		public String getDescription() {
			return "你已击败这些小恶魔！";
		}

		@Override
		public String getContent() {
			if(getImpGroup().isEmpty()) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_ALL_ENSLAVED", getImpGroup());
				
			} else if(getImpGroup().size()==1) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED_ONE", getImpGroup());
				
			} else if(getImpGroup().size()<4) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_FIGHT_AFTER_COMBAT_VICTORY_ENSLAVED", getImpGroup());
			}
			return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(!getImpGroup().isEmpty()) {
				if(index==0) {
					return "互动";
					
				} else if(index==1) {
					return "物品栏";
					
				} else if(index==2) {
					return "转化";
					
				}
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getImpGroup().isEmpty()) {
				if(index==1) {
					return new Response("继续", "既然你已经奴役了所有的小恶魔，那就没什么可做的了，继续上路吧……", Main.game.getDefaultDialogue(false));
				}
				return null;
			}
			if(!isCompanionDialogue()) {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("继续", "离开小恶魔，继续你的旅程……", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_CONTINUE", getImpGroup()));
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("做爱",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("做爱(温柔)",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("做爱(粗暴)",
								"当然，这些家伙<i>就是</i>想要这个！",
								true,
								false,
								Main.game.getPlayer().getParty(),
								getImpGroup(),
								null,
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("顺从",
								"你不太确定现在该做什么……也许最好让小恶魔来决定接下来做什么……",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Main.game.getPlayer().getParty(),
								null,
								null,
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
									Main.game.setResponseTab(0);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			} else {
				if(responseTab == 0) {
					if (index == 1) {
						return new Response("继续", "离开小恶魔，继续你的旅程……", Main.game.getDefaultDialogue(false)) {
							@Override
							public void effects() {
								banishImpGroup();
							}
						};
						
					} else if (index == 2) {
						return new ResponseSex("单人做爱",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
						
					} else if (index == 3) {
						return new ResponseSex("单人做爱(温柔)",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_GENTLE);
						
					} else if (index == 4) {
						return new ResponseSex("单人做爱(粗暴)",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你和小恶魔做爱。"),
								true,
								false,
								Util.newArrayListOfValues(Main.game.getPlayer()),
								getImpGroup(),
								Util.newArrayListOfValues(getMainCompanion()),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()), ResponseTag.START_PACE_PLAYER_DOM_ROUGH);
						
					} else if (index == 5) {
						return new ResponseSex("单人屈从",
								UtilText.parse(getMainCompanion(), "让[npc.Name]站在一边，看着你臣服于小恶魔，让他们与你发生支配型性爱。"),
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
								null,
								Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
								null,
								null,
								null,
								true,
								false,
								getImpGroup(),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion()),
								AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
						
					} else if (index == 6) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群交",
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群交"),
									UtilText.parse(companion, "与小恶魔进行支配型性爱，并让[npc.Name]也加入其中找找乐子。"),
									true,
									false,
									Main.game.getPlayer().getParty(),
									getImpGroup(),
									null,
									null,
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX", getImpGroup()));
						}
						
					} else if (index == 7) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response("群体屈从",
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "群体屈从"),
									UtilText.parse(companion, "让[npc.name]和你一起臣服于小恶魔，让他们和你俩发生支配型性爱。"),
									true,
									false,
									getImpGroup(),
									Main.game.getPlayer().getParty(),
									null,
									null,
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GROUP_SEX_SUBMISSION", getImpGroup()));
						}
						
					} else if (index == 8) {
						GameCharacter companion = getMainCompanion();

						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "给[npc.name]"),
									UtilText.parse(companion, "[npc.Name]半点也不想同小恶魔做爱，而且[npc.sheIs]不是你的奴隶，所以你也无法强迫[npc.herHim]……"), null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "交给[npc.name]"),
									UtilText.parse(companion, "告诉[npc.Name]让[npc.she]和小恶魔玩玩，你在旁边看着。"),
									false,
									false,
									Util.newArrayListOfValues(getMainCompanion()),
									getImpGroup(),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_GIVE_TO_COMPANION", getImpGroup()));
						}
						
					} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
						GameCharacter companion = getMainCompanion();
						
						if(!companion.isAttractedTo(getImpLeader()) && companion.isAbleToRefuseSexAsCompanion()) {
							return new Response(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, "你看出[npc.name]一点也不想跟小恶魔做爱，你也没法逼[npc.herHim]去做……"),
									null);
							
						} else {
							return new ResponseSex(UtilText.parse(companion, "献上[npc.name]"),
									UtilText.parse(companion, "将[npc.Name]交给小恶魔，然后看着他们与[npc.herHim]发生性关系。"),
									true,
									false,
									getImpGroup(),
									Util.newArrayListOfValues(getMainCompanion()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer()),
									AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "IMP_ATTACK_COMBAT_VICTORY_OFFER_COMPANION", getImpGroup())) {
								@Override
								public void effects() {
									if(!companion.isAttractedTo(getImpLeader()) && Main.game.isNonConEnabled()) {
										Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
						}
						
					} else {
						return null;
					}
					
				} else if(responseTab == 1) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……")) {
								@Override
								public void effects() {
									Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
									Main.game.setResponseTab(0);
								}
							};
						}
					}
					
				} else if(responseTab == 2) {
					for(int i=1; i<=getImpGroup().size(); i++) {
						if(index==i) {
							NPC imp = (NPC) getImpGroup().get(i-1);
							return new Response(UtilText.parse(imp, "[npc.Name]"),
									UtilText.parse(imp, "仔细观察[npc.name]会将自己转化成什么样……"),
									BodyChanging.BODY_CHANGING_CORE){
								@Override
								public void effects() {
									Main.game.saveDialogueNode();
									BodyChanging.setTarget(imp);
								}
							};
						}
					}
				}
				
				return null;
			
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {
		
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				potion = getImpLeader().generateTransformativePotion(Main.game.getPlayer());
			} else {
				potion = null;
			}
			if(isCompanionDialogue() && getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				companionPotion = getImpLeader().generateTransformativePotion(getMainCompanion());
			} else {
				companionPotion = null;
			}
		}
		
		@Override
		public String getDescription() {
			return "你已被小恶魔们击败！";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				return IMP_ATTACK_OFFER_THREESOME.getResponse(responseTab, index);
			} else {
				return IMP_ATTACK_OFFER_BODY.getResponse(responseTab, index);
			}
		}
	};
	

	public static final DialogueNode AFTER_OFFER_COMPANION_TRANSFORMATION = new DialogueNode("小恶魔团伙", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("旁观",
						UtilText.parse(getMainCompanion(), "旁观小恶魔和[npc.name]做爱……"),
						true,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(getMainCompanion()),
						null,
						Util.newArrayListOfValues(Main.game.getPlayer()),
						AFTER_SEX_WATCHING_COMPANION,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_COMPANION_START_SEX", getAllCharacters()));
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION_SOLO = new DialogueNode("小恶魔团伙", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("做爱",
						"让这群小恶魔跟你摆好姿势……",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("做爱(渴求)",
						"在这群小恶魔跟你摆好姿势的时候，表现得十分饥渴……",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("抵抗做爱",
						"在这群小恶魔跟你摆好姿势的时候，奋力抵抗……",
						false,
						false,
						getImpGroup(),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						isCompanionDialogue()?Util.newArrayListOfValues(getMainCompanion()):null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()),
						ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_TRANSFORMATION = new DialogueNode("小恶魔团伙", "", true) {

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("做爱",
						"让这群小恶魔跟你摆好姿势……",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
				
			} else if (index == 2) {
				return new ResponseSex("做爱(渴求)",
						"在这群小恶魔跟你摆好姿势的时候，表现得十分饥渴……",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_EAGER);
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("抵抗做爱",
						"在这群小恶魔跟你摆好姿势的时候，奋力抵抗……",
						false,
						false,
						getImpGroup(),
						Main.game.getPlayer().getParty(),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()), ResponseTag.START_PACE_PLAYER_SUB_RESISTING);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		
		@Override
		public String getDescription(){
			return "你已经爽过了，于是退到一旁，让这群小恶魔恢复过来后，自行散去了。";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttackCombatVictory"+(isCompanionDialogue()?"Companions":""), "AFTER_VICTORY_SEX", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							banishImpGroup();
						}
					};
				}
				
			} else if(responseTab==1) {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("瘫软", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		
		@Override
		public String getDescription(){
			return "你在[npc.namePos]支配之下精疲力竭，你需要休息一会儿……";
		}

		@Override
		public String getContent() {
			if(Main.sex.getAllParticipants().contains(getMainCompanion())) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX_WITH_COMPANION", getAllCharacters());
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX", getAllCharacters());
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGroup()) {
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_WATCHING_COMPANION = new DialogueNode("完成", "", true) {
		
		@Override
		public String getDescription(){
			return UtilText.parse(getMainCompanion(), "小恶魔和[npc.name]做够了……");
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_SEX_WATCHING_COMPANION", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续", "继续你的旅程。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						for(GameCharacter imp :getImpGroup()) {
							if(!getMainCompanion().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && getMainCompanion().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								getMainCompanion().addDirtySlot(InventorySlot.HEAD);
							}
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true) && Main.game.getPlayer().isCharactersCumInOrifice(SexAreaOrifice.VAGINA, imp.getId())) {
								Main.game.getPlayer().addDirtySlot(InventorySlot.HEAD);
							}
						}
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
}
