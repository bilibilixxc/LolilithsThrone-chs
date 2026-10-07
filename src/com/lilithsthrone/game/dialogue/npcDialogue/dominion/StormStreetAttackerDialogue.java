package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.npcDialogue.QuickTransformations;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.8.8
 * @version 0.3.8.8
 * @author Innoxia
 */
public class StormStreetAttackerDialogue {
	
	private static boolean isCompanionDialogue() {
		return Main.game.getPlayer().hasCompanions();
	}
	
	private static NPC getMugger() {
		return Main.game.getActiveNPC();
	}
	
	private static GameCharacter getMainCompanion() {
		return Main.game.getPlayer().getMainCompanion();
	}
	
	public static final DialogueNode STORM_ATTACK = new DialogueNode("敌袭！", "", true) {
		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK", getMugger());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "在这场讨厌的遭遇中对抗[npc.name]，保护你自己！", getMugger());
				
			} else if (index == 2) {
				return new Response("献上财物",
						"[npc.Name]受到奥术风暴的影响，不想要钱，满脑子只想着和你做爱！你只能选择打倒[npc.herHim]或者满足[npc.herHim]的欲望！",
						null);
				
			} else if (index == 3) {
				return new ResponseSex("献出身体", "向[npc.name]献上你的身体，以此来避免发生一场暴力对抗。",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
						null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getMugger()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Main.game.getPlayer().getCompanions()) {
							@Override
							public SexControl getSexControl(GameCharacter character) {
								if(character.isPlayer()) {
									return SexControl.ONGOING_PLUS_LIMITED_PENETRATIONS;
								}
								return super.getSexControl(character);
							}
						},
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_BODY", getMugger()));
					
			} else if (index == 4 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
					return new Response("三人行",
							UtilText.parse(getMugger(), "你看得出来，[npc.name]一点也不想和你做爱，所以也就不会想要和你三人行……"),
							null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response("三人行",
							UtilText.parse(getMugger(), "你看得出来，[npc.name]一点也不想和[com.name]做爱，所以这场三人行准是没戏了……"),
							null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("三人行",
							UtilText.parse(getMugger(), "你看得出来[com.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[com.herHim]这么做……"),
							null);
					
				} else {
					return new ResponseSex("三人行",
							UtilText.parse(getMugger(), "让[npc.name]能够和你，还有[com.name]一起做爱，以此来避免一场暴力冲突。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_THREESOME", getMugger()));
				}
				
			} else if (index == 5 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();
				
				if(!getMugger().isAttractedTo(companion)) {
					return new Response("献上[com.name]",
							UtilText.parse(getMugger(), "很明显，[npc.name]对于和[com.name]做爱完全不感兴趣……"),
							null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("献上[com.name]",
							UtilText.parse(getMugger(), "你看得出来[com.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[com.herHim]这么做……"),
							null);
					
				} else {
					return new ResponseSex("献上[com.name]",
							UtilText.parse(getMugger(), "为了避免发生暴力冲突，你告诉[npc.name]，[npc.she]可以享用[com.namePos]的身体。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "STORM_ATTACK_OFFER_COMPANION", getMugger())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
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
			return "你打败了[npc.name]！";
		}
		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_ATTRACTION", getMugger());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getMugger());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						"把[npc.name]甩在身后，继续你的旅程……"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("做爱",
						"反正这<i>正是</i>[npc.she]想要的！",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX", getMugger()));
				
			} else if (index == 3) {
				return new ResponseSex("做爱(温柔)",
						"反正这<i>正是</i>[npc.she]想要的！",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null,
								ResponseTag.START_PACE_PLAYER_DOM_GENTLE),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getMugger()));
				
			} else if (index == 4) {
				return new ResponseSex("做爱(粗暴)",
						"反正这<i>正是</i>[npc.she]想要的！",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(getMugger()),
								Main.game.getPlayer().getCompanions(),
								null,
								ResponseTag.START_PACE_PLAYER_DOM_ROUGH),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getMugger()));
				
			} else if (index == 5) {
				return new ResponseSex("顺从",
						"你不太确定现在该做什么……也许最好让[npc.name]来决定接下来做什么？",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
						null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(getMugger()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								Util.newArrayListOfValues(getMainCompanion())),
						AFTER_SEX_DEFEAT, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getMugger()));
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你已经打败了[npc.name]，没什么能够阻止你向[npc.her]的衣服和道具伸出手……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 8 && getMugger().isAbleToSelfTransform()) {
				return new Response("转化[npc.herHim]",
						"仔细观察[npc.name]会将自己转化成什么样……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(getMugger());
					}
				};
				
			} else if (index == 9 && getMugger().isAbleToSelfTransform()) {
				return new Response("快速转化",
						"[npc.she]能够转化自己，你脑海中闪过一些点子……"
								+ "(你完成[npc.herHim]的转化后将返回选项界面。)",
						QuickTransformations.initQuickTransformations("misc/quickTransformations", getMugger(), AFTER_COMBAT_VICTORY));
			
			} else if (index == 11 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();
				
				if(!companion.isAttractedTo(getMugger())) {
					return new Response("三人行", UtilText.parse(getMugger(), "[com.Name]并没有被[npc.name]所吸引，所以将不会愿意和[npc.herHim]一起做爱！"), null);
					
				} else {
					return new ResponseSex("三人行",
							UtilText.parse(getMugger(), companion, "和[npc.name]来一场支配型性爱，同时让[npc2.name]也来爽一爽。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer(), companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									null,
									ResponseTag.PREFER_DOGGY),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_THREESOME", getMugger()));
				}
				
			} else if (index == 12 && isCompanionDialogue()) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !getMugger().isAttractedTo(companion)) {
					return new Response("给予[com.name]", UtilText.parse(getMugger(), "[npc.Name]没有被[com.name]所吸引，因此并不愿意和[npc.herHim]做爱！"), null);
					
				} else if(!companion.isAttractedTo(getMugger())) {
					return new Response("给予[com.Name]", UtilText.parse(getMugger(), "[com.Name]并没有被[npc.name]所吸引，所以并不愿意和[npc.herHim]一起做爱！"), null);
					
				} else {
					return new ResponseSex("给予[com.name]",
							UtilText.parse(getMugger(), "告诉[com.she]可以和[npc.name]找点乐子，你会旁观。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(companion),
									Util.newArrayListOfValues(getMugger()),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger()));
				}
				
			} else if (index == 13 && isCompanionDialogue() && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				if(!Main.game.isNonConEnabled() && !companion.isAttractedTo(getMugger())) {
					return new Response("献上[com.name]", UtilText.parse(getMugger(), "[com.Name]没兴趣和[npc.name]做爱！"), null);
					
				} else if(!getMugger().isAttractedTo(companion)) {
					return new Response("献上[com.name]", UtilText.parse(getMugger(), "[npc.Name]没兴趣和[com.name]做爱！"), null);
					
				} else if(!companion.isAttractedTo(getMugger()) && companion.isAbleToRefuseSexAsCompanion()) {
					return new Response("献上[com.name]",
							UtilText.parse(getMugger(), "你看得出来[com.name]完全没兴趣和[npc.name]做爱，而你也无法强迫[com.herHim]这么做……"),
							null);
					
				} else {
					return new ResponseSex("献上[com.name]",
							UtilText.parse(getMugger(), "告诉[npc.name][npc.she]可以使用[com.name]。"),
							true, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(companion),
									null,
									Util.newArrayListOfValues(Main.game.getPlayer())),
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger())) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
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
			return "你被[npc.name]打败了！";
		}

		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_COMBAT_DEFEAT_GENERIC_START", getMugger()));
			
			if(isCompanionDialogue()) {
				if(getMugger().isWillingToRape()) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "RAPE_BOTH", getMugger()));
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "OFFER_SEX_BOTH", getMugger()));
				}
				
			} else {
				if(getMugger().isWillingToRape()) {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "RAPE_PLAYER", getMugger()));
				} else {
					sb.append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "OFFER_SEX", getMugger()));
				}
			}
			
			return sb.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(isCompanionDialogue()) {
				boolean companionHappyToHaveSex = getMainCompanion().isAttractedTo(getMugger()) || getMainCompanion().isAttractedTo(Main.game.getPlayer());
				boolean companionSex = getMugger().isAttractedTo(getMainCompanion()) && (companionHappyToHaveSex || getMugger().isWillingToRape());
				
				if (index == 1) {
					return new ResponseSex("做爱",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name]把[npc.herself]强行压在了你身上"+(companionSex?" and [com.name]":"")+"……"
										:"告诉[npc.name]你"+(companionSex?"和[com.name]":"")+"愿意和[npc.herHim]做爱。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name]把[npc.herself]强行压在了你身上"+(companionSex?" and [com.name]":"")+"……"
										:"告诉[npc.name]你"+(companionSex?"和[com.name]":"")+"非常乐意和[npc.herHim]做爱。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							UtilText.parse(getMugger(), "[npc.Name]用[npc.herself]强行压住了你"+(companionSex?"和[com.name]":"")+"……"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(
											Main.game.getPlayer(),
											companionSex
												?getMainCompanion()
												:null),
									null,
									Util.newArrayListOfValues(
										companionSex
											?null
											:getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_THREESOME_RESIST", getMugger()));
					
				} else if (index == 4 && !getMugger().isWillingToRape()) {
					return new Response("拒绝",
							UtilText.parse(getMugger(), "拒绝和[npc.name]做爱，然后接着上路。"),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "DEFEATED_REFUSE_THREESOME", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
				
			} else {
				if (index == 1) {
					return new ResponseSex("做爱",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name]强行压住了你……"
										:"告诉[npc.name]你愿意同[npc.herHim]做爱。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion())),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX", getMugger()));
					
				} else if (index == 2) {
					return new ResponseSex("做爱(渴求)",
							UtilText.parse(getMugger(),
									getMugger().isWillingToRape()
										?"[npc.Name]强行压住了你……"
										:"告诉[npc.name]非常乐意和[npc.herHim]做爱。"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_EAGER),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX", getMugger()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("抵抗做爱",
							UtilText.parse(getMugger(), "[npc.Name]强行压住了你……"),
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(getMugger()),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									Util.newArrayListOfValues(getMainCompanion()),
									ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "START_DEFEATED_SEX_RESIST", getMugger()));
					
				} else if (index == 4 && !getMugger().isWillingToRape()) {
					return new Response("拒绝",
							UtilText.parse(getMugger(), "拒绝和[npc.name]做爱，你继续你的行程。"),
							Main.game.getDefaultDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "DEFEATED_REFUSE_SEX", getMugger()));
							Main.game.banishNPC(getMugger());
						}
					};
				}
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("返回", "", true) {
		@Override
		public String getDescription(){
			return "你爽够了，可以走了，让[npc.name]自己歇会儿。";
		}
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(getMugger()) >= getMugger().getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_VICTORY", getMugger());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_VICTORY_NO_ORGASM", getMugger());
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						"把[npc.name]甩在身后，继续你的旅程。"
								+ "<br/>[style.italicsBad([npc.herHim]将会从游戏中永久删除！)]",
						Main.game.getDefaultDialogue(false)){
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("物品栏", "现在你可以随意拿取[npc.namePos]衣服和道具，谁也拦不了你……"){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
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
			return UtilText.parseFromXMLFile("encounters/dominion/stormStreetAttack", "AFTER_SEX_DEFEAT", getMugger());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续",
						"继续走路。"
								+ "<br/>[style.italicsBad([npc.name]将会从游戏中永久删除！)]",
						Main.game.getDefaultDialogue(false)) {
					@Override
					public Colour getHighlightColour() {
						return PresetColour.GENERIC_NPC_REMOVAL;
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
			}
			return null;
		}
	};
}
