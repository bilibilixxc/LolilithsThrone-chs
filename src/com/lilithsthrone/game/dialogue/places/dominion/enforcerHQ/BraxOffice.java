package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import java.util.stream.Collectors;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.4.6.3
 * @author Innoxia
 */
public class BraxOffice {

	public static void setBraxsPostQuestStatus(boolean applyPlayerLocationChange) {
		Main.game.getNpc(Brax.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK, true);
		Main.game.getNpc(Brax.class).setPendingClothingDressing(true);
		Main.game.getNpc(Brax.class).setAffection(Main.game.getPlayer(), -50);
		
		Main.game.getNpc(CandiReceptionist.class).addSlave(Main.game.getNpc(Brax.class));
		Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory().clearNonEquippedInventory(true);
		
		if(applyPlayerLocationChange) {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
		}
	};
	
	public static void givePlayerEnforcerUniform(StringBuilder sb, int outfitFem) {
		if(sb==null) {
			sb = new StringBuilder();
		}
		if(outfitFem==1) {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfpumps", PresetColour.CLOTHING_BLACK, false), false));

			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
		} else if(outfitFem==0) {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_lsldshirt", PresetColour.CLOTHING_BLUE, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, false), false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
		} else {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_lsldshirt", PresetColour.CLOTHING_BLUE, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfpumps", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, false), false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
			hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
		}

		TransformativePotion tfPotion = Main.game.getNpc(Brax.class).generateTransformativePotion(Main.game.getPlayer());
		AbstractItem potion = EnchantingUtils.craftItem(
			Main.game.getItemGen().generateItem(tfPotion.getItemType()),
			tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
		potion.setName("布拉克斯的小惊喜");
		sb.append(Main.game.getPlayer().addItem(potion, false));
	};
	
	public static final DialogueNode INTERIOR_BRAX = new DialogueNode("[brax.namePos]的办公室", "-", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("真相", "告诉[brax.name]，你究竟是谁，以及你来这是为了寻找亚瑟的下落的。", INTERIOR_BRAX_TRUTH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 2) {
				return new Response("说谎", "你注意到四周海报的模特全都是狼女。或许可以骗他说亚瑟是某个狼女主题妓院的赞助人，你刚好继承了过来……",
						INTERIOR_BRAX_LIE,
						null, null, Util.newArrayListOfValues(Perk.OBSERVANT), null, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 3) {
				return new Response("动之以狼貌", "借助你充满女性魅力的狼一般的身躯，勾引[brax.name]让他告诉你亚瑟的消息。", INTERIOR_BRAX_GETTING_TEASED,
						null, null, null, Femininity.FEMININE, Util.newArrayListOfValues(Subspecies.WOLF_MORPH, Subspecies.getSubspeciesFromId("innoxia_wolf_subspecies_arctic"))){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_REPEAT = new DialogueNode("[brax.namePos]的办公室", "-", true) {
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_REPEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				return new ResponseCombat("战斗", "[brax.name]看着像是要好好揍你一顿！", Main.game.getNpc(Brax.class));
			}
			return null;
		}
	};

	public static final DialogueNode INTERIOR_BRAX_TRUTH = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_TRUTH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "如果你想找出亚瑟的情报，那你必须要跟[brax.name]战斗！", Main.game.getNpc(Brax.class));
					
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTERIOR_BRAX_LIE = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("继续唬骗", "暗示“母狼巢穴”就是你旗下的妓院。如果他能告诉你亚瑟的消息，那你就会给他VIP。", INTERIOR_BRAX_LIE_BLUFFING);
					
			} else if (index == 2) {
				return new Response("不演了", "告诉[brax.name]他就是个憨憨，而你来这是想弄明白他到底对亚瑟做了什么。", INTERIOR_BRAX_LIE_IDIOT_BRAX);
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_IDIOT_BRAX = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_IDIOT_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "[brax.name]看着非常尴尬，你确信这样戏弄他，至少能给你带来一点点优势！", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(30);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("放他走", "祝[brax.name]玩得开心。你觉得他至少得花上好几个小时才能明白自己是被人耍了。", INTERIOR_BRAX_LIE_BLUFFING_SUCCESS){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				};
					
			} else if (index == 2) {
				return new Response("阻止[brax.name]", "告诉[brax.name]他就是个憨憨，而你要给他这个容易上当受骗的傻瓜好好“上一堂课”。", INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX) {
					@Override
					public boolean isCombatHighlight() {
						return true;
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_SUCCESS = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING_SUCCESS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("女性制服", "拿一套女性制服，离开执法者总部。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("男性制服", "拿一套男性制服，离开执法者总部。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 0);
						setBraxsPostQuestStatus(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("战斗", "[brax.name]看着非常尴尬，你确信这样戏弄他，能给你带来很大优势！", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(50);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("阅读", "阅读[brax.name]刚刚递给你的文件。", INTERIOR_BRAX_GETTING_TEASED_UH_OH) {
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_UH_OH = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("快逃！", "把[brax.name]推开，借口迅速逃离。", INTERIOR_BRAX_GETTING_TEASED_ESCAPE) {
					
				};
					
			} else if (index == 2) {
				return new ResponseSex("挨操", "让[brax.name]控制住你，然后挨操。",
						true, false, 
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH_GET_FUCKED"));
					
			} else if (index == 3) {
				return new ResponseSex("反客为主", "反过来掌控局势，把[brax.name]变成你胯下的小母狗。", Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Brax.class)),
								null,
								null),
						AFTER_DOMINANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH_TAKE_CONTROL"));
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_ESCAPE = new DialogueNode("[brax.namePos]的办公室", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_ESCAPE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("女性制服", "拿一套女性制服，离开执法者总部。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("男性制服", "拿一套男性制服，离开执法者总部。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 0);
						setBraxsPostQuestStatus(true);
					}
				};
			} 
			else {
				return null;
			}
		};
	};
	
	
	//----------- BRAX COMBAT/SEX -----------
	

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {

		@Override
		public String getDescription() {
			return "你已击败了布拉克斯！";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("离开", "你真的不想和布拉克斯做爱，便决定离开他的办公室，继续做别的事情。", AFTER_COMBAT_VICTORY_NO_SEX){
					
				};
				
			} else if (index == 2) {
				return new ResponseSex("支配布拉克斯",
						"布拉克斯一败涂地且饥渴难耐的模样令你无法抗拒，你忍不住对着狼男露出狡黠的笑容，准备将他变成你的母狗。",
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, CorruptionLevel.TWO_HORNY, null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DOMINANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_DOMINATE"));
				
			} else if (index == 3) {
				return new ResponseSex("向布拉克斯屈服",
						"虽然你已经击败了他，但你那顺从的天性使你开始考虑起让布拉克斯以支配位上你……",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, Femininity.FEMININE, null,
						false, true,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_SUBMIT"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_NO_SEX = new DialogueNode("", "", true, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_NO_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("女性制服", "你拿了一套女性制服，再次回到外面，但现在已经有了亚瑟位置的新线索。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("男性制服", "你拿了一套男性制服，再次回到外面，但现在已经有了亚瑟位置的新线索。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),0);
						setBraxsPostQuestStatus(true);
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("落败", "", true) {
		
		@Override
		public String getDescription() {
			return "你已被布拉克斯击败！";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_DEFEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isSpittingDisabled()) {
					return Response.getDisallowedSpittingResponse();
				}
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					return new Response("吐出",
							"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
								+"</b>的性癖，你乐于被转化，所以无法把转化液体吐出来！",
							null);
				} else {
					return new Response("吐出", "吐出转化液体。", AFTER_DEFEAT_TRANSFORMATION_REFUSED);
				}
				
			} else if (index == 2) {
				return new Response("咽下",
						"按布拉克斯说的做，吞下奇怪的液体。",
						AFTER_DEFEAT_TRANSFORMATION,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxTransformedPlayer);
						
						TransformativePotion tfPotion = Main.game.getNpc(Brax.class).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(tfPotion.getItemType()),
							tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						potion.setName("布拉克斯的小惊喜");
						
						Main.game.getNpc(Brax.class).useItem(potion, Main.game.getPlayer(), false);
						
						Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, PresetColour.EYE_YELLOW));
						Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, PresetColour.COVERING_BLACK), true);
						Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, PresetColour.COVERING_WHITE), true);
						
						if(Main.getProperties().forcedFetishPercentage!=0 && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE));
						}
						if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)<CorruptionLevel.TWO_HORNY.getMinimumValue()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, CorruptionLevel.TWO_HORNY.getMinimumValue()));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_TRANSFORMATION_REFUSED = new DialogueNode("布拉克斯的办公室", "被强行灌入药水后，布拉克斯的办公室内。", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("被支配", "布拉克斯太过强大，你无可阻挡……",
						false, false,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null, AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_REFUSED_DOMINATED"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_TRANSFORMATION = new DialogueNode("布拉克斯的办公室", "被强行灌入药水后，布拉克斯的办公室内。", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("屈服",
						"你刚刚被灌入的药水能够勾起情欲，迫使你屈服了，你极其渴求地四肢着地趴了下去，让布拉克斯能够后入你。",
						false, false,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_OBEY"));
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SUBMISSIVE_SEX = new DialogueNode("布拉克斯完事了", "布拉克斯跟你爽过了。", true) {
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_SUBMISSIVE_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				if (index == 1) {
					return new Response ("女性制服", "拿一套女性制服，继续赶路。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
						@Override
						public void effects() {
							givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),1);
							setBraxsPostQuestStatus(true);
						}
					};
					
				} else if (index == 2) {
					return new Response("男性制服", "拿一套男性制服，继续赶路。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
						public void effects( ) {
							givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(),0);
							setBraxsPostQuestStatus(true);
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new Response("继续", "起身继续你的旅程。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode AFTER_DOMINANT_SEX = new DialogueNode("布拉克斯瘫倒在地", "布拉克斯倒下了，你返回了他的办公室。", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DOMINANT_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("女性制服", "你拿了一套女性制服，再次回到外面，但现在已经有了亚瑟位置的新线索。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 1);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else if (index == 2) {
				return new Response("男性制服", "你拿了一套男性制服，再次回到外面，但现在已经有了亚瑟位置的新线索。", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder(), 0);
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
