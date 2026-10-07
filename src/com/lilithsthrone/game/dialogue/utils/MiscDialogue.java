package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Fiammetta;
import com.lilithsthrone.game.character.npc.dominion.Saellatrix;
import com.lilithsthrone.game.character.npc.fields.Angelixx;
import com.lilithsthrone.game.character.npc.misc.BasicDoll;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueManager;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.inventory.AbstractSetBonus;
import com.lilithsthrone.game.inventory.ColourReplacement;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.SetBonus;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractFilledCondom;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.sexActions.SexActionUtility;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.62
 * @version 0.3.7.7
 * @author Innoxia
 */
public class MiscDialogue {
	
	public static final DialogueNode STATUS_EFFECTS = new DialogueNode("重要状态效果更新", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			for(Entry<Long, Map<AbstractStatusEffect, String>> entry : Main.game.getPlayer().getStatusEffectDescriptions().entrySet()){
				if(!entry.getValue().isEmpty()) {
					sb.append("<div class='container-full-width'>");
						sb.append("<h6 style='text-align:center; margin:16px auto 0 auto; padding:0;'>"+Units.dateTime(Main.game.getStartingDate().plusSeconds(entry.getKey()))+":</h6>");
						for(Entry<AbstractStatusEffect, String> innerEntry : entry.getValue().entrySet()) {
							sb.append("<hr/>");
							sb.append("<h6 style='text-align:center; margin:0; padding:0;'>");
								sb.append(Util.capitaliseSentence(innerEntry.getKey()==null?"杂项效果":innerEntry.getKey().getName(Main.game.getPlayer())));
							sb.append("</h6>");
							sb.append("<p style='margin-top:0;'>");
								sb.append(UtilText.parse(Main.game.getPlayer(), innerEntry.getValue()));
							sb.append("</p>");
						}
					sb.append("</div>");
				}
			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new ResponseEffectsOnly("继续", "继续你之前的行为。"){
					@Override
					public void effects() {
						Main.game.getPlayer().getStatusEffectDescriptions().clear();
						Main.game.restoreSavedContent(false);
					}
				};
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.STATUS_EFFECT_MESSAGE;
		}
	};

	private static boolean withHairStyle;
	private static String makeupOpeningDescription;

	public static DialogueNode getMakeupDialogueForEqualityCheck() {
		return BODY_CHANGING_MAKEUP;
	}
	
	public static DialogueNode getMakeupDialogue(boolean withHairStyle, String makeupOpeningDescription) {
		MiscDialogue.withHairStyle = withHairStyle;
		MiscDialogue.makeupOpeningDescription = makeupOpeningDescription;
		return BODY_CHANGING_MAKEUP;
	}
	
	private static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("化妆", "", true) {
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ makeupOpeningDescription
					+ "</div>"
					
					+ (withHairStyle
						?CharacterModificationUtils.getSelfDivHairStyles("发型", UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos]的发型。"))
						:""));
			if(!BodyChanging.getTarget().isAbleToWearMakeup()) {
				sb.append("<div class='container-full-width' style='text-align:center;'>"
						+ UtilText.parse(BodyChanging.getTarget(),
								"<i>由于[npc.namePos]的身体由"+BodyChanging.getTarget().getBodyMaterial().getName()+"构成，[npc.sheIsFull][style.colourBad(无法化妆)]！</i>")
					+ "</div>");
				
			} else {
				sb.append(CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "腮红", "腮红(也叫胭脂)被用来粉饰脸颊，以显得更加年轻或凸显颧骨。", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "口红", "口红被用来为嘴唇提供色彩、质地或保护。", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "眼线", "眼线用于眼廓周围，有助于修饰眼型或突出不同的特征。", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "眼影", "眼影用来让使用者的眼睛更加凸显迷人。", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "指甲油", "指甲油用于给[pc.hands]添加色彩或提供保护。", true, true)
						
						+CharacterModificationUtils.getKatesDivCoveringsNew(
								false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚趾甲油", "脚趾甲油用于给[pc.feet]添加色彩或提供保护。", true, true));
			}
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isInSex()) {
					return new Response(
							"结束",
							"结束化妆……",
							Main.sex.SEX_DIALOGUE){
						@Override
						public void effects(){
							Main.mainController.openInventory();
							Main.sex.setUsingItemText(UtilText.parse(BodyChanging.getTarget(),
								BodyChanging.getTarget().isPlayer()
									?"你用奥术化妆套装开始给自己化妆……"
									:"你用奥术化妆套装开始给[npc.Name]化妆……"));
							Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
							Main.sex.setSexStarted(true);
						}
					};
					
				} else {
					return new ResponseEffectsOnly("结束", "返回物品栏界面。") {
						@Override
						public void effects() {
							if(BodyChanging.getTarget().isPlayer()) {
								Main.mainController.openInventory();
							} else {
								Main.mainController.openInventory((NPC) BodyChanging.getTarget(), InventoryInteraction.FULL_MANAGEMENT);
							}
						}
					};
				}
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(Main.game.isInSex()) {
				return DialogueNodeType.NORMAL;
			}
			return DialogueNodeType.PHONE;
		}
		@Override
		public boolean isInventoryForcedDisabledInSex() {
			return true;
		}
	};
	
	// Condom use:
	
	// init for hook to condom and user/target:
	private static GameCharacter condomOwner;
	private static GameCharacter condomUser;
	private static GameCharacter condomTarget;
	private static AbstractFilledCondom usedCondom;
	private static String condomUseDescription;
	
	public static DialogueNode getUsedCondomSelectionDialogue(GameCharacter condomOwner, GameCharacter condomUser, GameCharacter condomTarget, AbstractFilledCondom usedCondom, String condomUseDescription) {
		boolean debug = false;
		
		MiscDialogue.condomOwner = condomOwner;
		MiscDialogue.condomUser = condomUser;
		MiscDialogue.condomTarget = condomTarget;
		MiscDialogue.usedCondom = usedCondom;
		MiscDialogue.condomUseDescription = condomUseDescription;
		
		if(debug) {
			System.out.println("UsedCondomInit:");
			System.out.println(UtilText.parse(condomOwner, "避孕套拥有者：[npc.name]"));
			System.out.println(UtilText.parse(condomUser, "避孕套使用者：[npc.name]"));
			System.out.println(UtilText.parse(condomTarget, "condomTarget: [npc.name]"));
		}
		
		return USED_CONDOM_SELECTION;
	}
	
	private static final DialogueNode USED_CONDOM_SELECTION = new DialogueNode("使用过的避孕套", "", true) {
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();
			
			FluidStored fs = usedCondom.getCum();
			
			sb.append("<p>");
				sb.append(condomUseDescription);
				sb.append("避孕套里面装着：");
			sb.append("</p>");
			
			sb.append("<p style='text-align:center;'>");
			sb.append("<b>[units.fluid("+fs.getMillilitres()+")]</b>");
			sb.append("<br/>");
				try {
					sb.append(UtilText.parse(fs.getFluidCharacter(), "<span style='color:"+fs.getFluidCharacter().getFemininity().getColour().toWebHexString()+";'>[npc.NamePos]</span>"));
					sb.append("");
					sb.append(" [style.colourCum("+fs.getFluid().getName(condomOwner)+")]");
				} catch(Exception ex) {
					String raceName = fs.getBody().getRace().getName(false);
					sb.append("<span style='color:"+fs.getBody().getRace().getColour().toWebHexString()+";'>");
					sb.append(UtilText.generateSingularDeterminer(raceName)+ Util.capitaliseSentence(raceName)+"的");
					sb.append("</span> ");
					sb.append(" [style.colourCum("+fs.getFluid().getName(null)+")]");
				}
				sb.append("<br/>");
				sb.append("是<span style='color:"+fs.getFluid().getFlavour().getColour().toWebHexString()+";'>"+fs.getFluid().getFlavour().getName()+"</span>味的");
				if(!fs.getFluid().getFluidModifiers().isEmpty()) {
					sb.append("<br/>");
					StringBuilder modifiersSB = new StringBuilder();
					modifiersSB.append("");
					List<String> modList = new ArrayList<>();
					for(FluidModifier mod : fs.getFluid().getFluidModifiers()) {
						modList.add("<span style='color:"+mod.getColour().toWebHexString()+";'>"+mod.getName()+"</span>");
					}
					modifiersSB.append(Util.stringsToStringList(modList, false));
					sb.append(modifiersSB.toString());
					sb.append("。");
				}
			sb.append("</p>");

			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append("由于你过于沉迷精液，只是预想到能够用到避孕套中的内容物，你就起了性欲……");
				} else {
					sb.append("精液已经放凉，味道也很差，让你怀疑自己是否真的应该解开避孕套……");
				}
			sb.append("</p>");
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				if(Main.game.isInSex()) {
					return new Response(
							"返回",
							"决定还是不使用避孕套的内容物了……",
							Main.sex.SEX_DIALOGUE){
						@Override
						public void effects(){
							condomOwner.addItem(usedCondom);
							Main.mainController.openInventory();
						}
					};
					
				} else {
					return new ResponseEffectsOnly("返回", "决定还是不使用避孕套的内容物了……") {
						@Override
						public void effects() {
							condomOwner.addItem(usedCondom);
							if(condomUser.isPlayer()) {
								if(InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.TRADING) {
									NPC trader = InventoryDialogue.getInventoryNPC();
									Main.game.restoreSavedContent(false);
									Main.mainController.openInventory(trader, InventoryInteraction.TRADING);
								} else {
									Main.mainController.openInventory();
								}
							} else {
								Main.mainController.openInventory((NPC) condomUser, InventoryInteraction.FULL_MANAGEMENT);
							}
						}
					};
				}
			}
			
			List<Response> responses = new ArrayList<>();
			
			if(!condomTarget.isSexAreaExposed(SexAreaOrifice.MOUTH)) {
				responses.add(new Response("咽下",
						UtilText.parse(condomTarget, "[npc.NamePos]的嘴巴被堵住了……"),
						null));
			} else {
				responses.add(getUsedCondomResponse("咽下",
							"吞下避孕套的内容物……",
							"让[npc.name]吞下避孕套的内容物……",
							(SexAreaOrifice.MOUTH)));
			}
			
			if(!condomTarget.hasVagina()) {
				responses.add(new Response("小穴",
						UtilText.parse(condomTarget, "[npc.Name]没有阴道……"),
						null));
			} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.VAGINA)) {
				responses.add(new Response("小穴",
						UtilText.parse(condomTarget, "[npc.NamePos]的小穴被阻挡了……"),
						null));
			} else {
				responses.add(getUsedCondomResponse("小穴",
							"将避孕套的内容物塞进你的小穴……",
							"将避孕套的内容物塞进[npc.namePos]的小穴……",
							(SexAreaOrifice.VAGINA)));
			}

			if(Main.game.isAnalContentEnabled()) {
				if(!condomTarget.isSexAreaExposed(SexAreaOrifice.ANUS)) {
					responses.add(new Response("肛门",
							UtilText.parse(condomTarget, "[npc.NamePos]的肛门被阻挡了……"),
							null));
				} else {
					responses.add(getUsedCondomResponse("肛门",
								"将避孕套的内容物塞进你的肛门……",
								"将避孕套的内容物塞进[npc.namePos]的肛门……",
								(SexAreaOrifice.ANUS)));
				}
			}
			
			if(Main.game.isNipplePenEnabled()) {
				if(!condomTarget.isBreastFuckableNipplePenetration()) {
					responses.add(new Response("乳头",
							UtilText.parse(condomTarget, "[npc.Name]没有可供插入的乳头，来放入避孕套的内容物……"),
							null));
				} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.NIPPLE)) {
					responses.add(new Response("乳头",
							UtilText.parse(condomTarget, "[npc.NamePos]的乳头被阻挡了……"),
							null));
				} else {
					responses.add(getUsedCondomResponse("乳头",
							"将避孕套的内容物塞进你可插入的乳头……",
							"将避孕套的内容物塞进[npc.namePos]可插入的乳头……",
							(SexAreaOrifice.NIPPLE)));
				}
				
				if(Main.game.isUdderContentEnabled()) {
					if(!condomTarget.isBreastCrotchFuckableNipplePenetration()) {
						responses.add(new Response(condomTarget.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳",
								UtilText.parse(condomTarget, "[npc.Name]没有可供插入的[npc.nipplesCrotch]，来放入避孕套的内容物……"),
								null));
					} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.NIPPLE_CROTCH)) {
						responses.add(new Response(condomTarget.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳",
								UtilText.parse(condomTarget, "[npc.NamePos]的[npc.nipplesCrotch]被阻挡了……"),
								null));
					} else {
						responses.add(getUsedCondomResponse(condomTarget.getBreastCrotchShape()==BreastShape.UDDERS?"腹乳":"胯乳",
								"将避孕套的内容物塞进你[npc.nipplesCrotch+]……",
								"将避孕套的内容物塞进[npc.namePos][npc.nipplesCrotch+]……",
								(SexAreaOrifice.NIPPLE_CROTCH)));
					}
				}
			}
			
			if(Main.game.isUrethraEnabled()) {
				if(!condomTarget.hasPenisIgnoreDildo() || !condomTarget.isUrethraFuckable()) {
					responses.add(new Response("阴茎尿道",
							condomTarget.hasPenisIgnoreDildo()
								?UtilText.parse(condomTarget, "[npc.Name]没有可供插入的尿道，来放入避孕套的内容物……")
								:UtilText.parse(condomTarget, "[npc.Name]没有阴茎……"),
							null));
				} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.URETHRA_PENIS)) {
					responses.add(new Response("阴茎尿道",
							UtilText.parse(condomTarget, "[npc.NamePos]的尿道被阻挡了……"),
							null));
				} else {
					responses.add(getUsedCondomResponse("阴茎尿道",
							"将避孕套的内容物塞进你的[npc.urethraPenis]……",
							"将避孕套的内容物塞进[npc.namePos][npc.urethraPenis+]……",
							(SexAreaOrifice.URETHRA_PENIS)));
				}
				
				if(!condomTarget.hasVagina() || !condomTarget.isVaginaUrethraFuckable()) {
					responses.add(new Response("阴道尿道",
							condomTarget.hasVagina()
								?UtilText.parse(condomTarget, "[npc.Name]没有可供插入的尿道，来放入避孕套的内容物……")
								:UtilText.parse(condomTarget, "[npc.Name]没有阴道……"),
							null));
				} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.URETHRA_VAGINA)) {
					responses.add(new Response("阴道尿道",
							UtilText.parse(condomTarget, "[npc.NamePos]的尿道被阻挡了……"),
							null));
				} else {
					responses.add(getUsedCondomResponse("阴道尿道",
							"将避孕套的内容物塞进你的[npc.urethraVagina]……",
							"将避孕套的内容物塞进[npc.namePos]的[npc.urethraVagina+]……",
							(SexAreaOrifice.URETHRA_VAGINA)));
				}
			}

			if(!condomTarget.hasSpinneret()) {
				responses.add(new Response("丝囊",
						UtilText.parse(condomTarget, "[npc.Name]没有丝囊来放入避孕套的内容物……"),
						null));
			} else if(!condomTarget.isSexAreaExposed(SexAreaOrifice.SPINNERET)) {
				responses.add(new Response("丝囊",
						UtilText.parse(condomTarget, "[npc.NamePos]的丝囊被阻挡了……"),
						null));
			} else {
				responses.add(getUsedCondomResponse("丝囊",
							"将避孕套的内容物塞进你的丝囊……",
							"将避孕套的内容物塞进[npc.namePos]的丝囊……",
							(SexAreaOrifice.SPINNERET)));
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index-1==i) {
					return responses.get(i);
				}
			}
			
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(Main.game.isInSex()) {
				return DialogueNodeType.NORMAL;
			}
			return DialogueNodeType.PHONE;
		}
		@Override
		public boolean isInventoryForcedDisabledInSex() {
			return true;
		}
	};
	
	private static Response getUsedCondomResponse(String title, String descriptionSelf, String description, SexAreaOrifice orifice) {
		if(Main.game.isInSex()) {
			return new Response(
					title,
					UtilText.parse(condomTarget, condomUser==condomTarget?descriptionSelf:description),
					Main.sex.SEX_DIALOGUE){
				@Override
				public void effects(){
					String condomEffectString = getAndApplyCondomUseDescription(orifice);
					condomTarget.calculateStatusEffects(0);
					Main.mainController.openInventory();
					Main.sex.setUsingItemText(
							condomEffectString
							+ Main.sex.calculateWetAreas(false));
					Main.sex.endSexTurn(SexActionUtility.PLAYER_USE_ITEM);
					Main.sex.setSexStarted(true);
					
				}
			};
			
		} else {
			return new ResponseEffectsOnly(title, 
					UtilText.parse(condomTarget, condomUser==condomTarget?descriptionSelf:description)) {
				@Override
				public void effects() {
					String condomEffectString = getAndApplyCondomUseDescription(orifice);
					condomTarget.calculateStatusEffects(0);
					Main.game.getTextEndStringBuilder().append(condomEffectString);
					if(condomTarget.isPlayer()) {
						if(InventoryDialogue.getNPCInventoryInteraction()==InventoryInteraction.TRADING) {
							NPC trader = InventoryDialogue.getInventoryNPC();
							Main.game.restoreSavedContent(false);
							Main.game.getTextEndStringBuilder().append(condomEffectString);
							Main.mainController.openInventory(trader, InventoryInteraction.TRADING);
						} else {
							Main.mainController.openInventory();
						}
					} else {
						Main.mainController.openInventory((NPC) condomTarget, InventoryInteraction.FULL_MANAGEMENT);
					}
				}
			};
		}
	}
	
	private static String getAndApplyCondomUseDescription(SexAreaOrifice orifice) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p>");
		String targetNameSelfShe = condomTarget==condomUser?"[npc.she]":"[npc2.name]";
		String userNamePosSelfHer = condomTarget==condomUser?"[npc.her]":"[npc.namePos]";
		String cumName = "精液";
		try {
			cumName = usedCondom.getCum().getFluid().getName(usedCondom.getCum().getFluidCharacter());
		} catch(Exception ex) {
		}
		switch(orifice) {
			case ARMPITS:
			case ASS:
			case BREAST:
			case BREAST_CROTCH:
			case THIGHS:
				return "";// These orifices are never used
			// Internal orifices:
			case ANUS:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.asshole+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的[npc.ass]里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.asshole+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己[npc.ass]里那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case MOUTH:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name]按捺不住欣喜地[npc.moan]一声，不知满足地吞咽下了那黏糊糊的液体。"
							+ "[npc.she]伸出[npc.tongue]来，饥渴难耐地舔干净了每一滴精液；保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							"[npc.Name]紧闭着[npc.eyes]，才勉强咽下了那黏糊糊的液体，"
									+ "只能竭尽全力不去想自己刚才做过的事情，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case NIPPLE:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.nipple+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的[npc.breasts]里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.nipple+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己[npc.breasts]那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case NIPPLE_CROTCH:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.nippleCrotch+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的[npc.crotchBoobs]里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.nippleCrotch+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己[npc.crotchBoobs]那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case SPINNERET:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.spinneret+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的丝囊穴里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.spinneret+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己丝囊穴中那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case URETHRA_PENIS:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.urethraPenis+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的[npc.cock]里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.urethraPenis+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己[npc.cock]里那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case URETHRA_VAGINA:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.urethraVagina+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的[npc.pussy]里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.urethraVagina+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己[npc.pussy]里那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
			case VAGINA:
				if(condomTarget.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"急切地把那黏糊糊的液体往[npc.Name][npc.pussy+]里塞，惹得[npc.she]不禁发出一声愉悦的[npc.moan]。"
							+ targetNameSelfShe + "疯了似的，把套套里的东西往"+userNamePosSelfHer+"的[npc.pussy]里塞，保证完全清空之后就只是把避孕套丢到了一旁。"));
				} else {
					sb.append(UtilText.parse(condomTarget, condomUser,
							targetNameSelfShe+"将那黏糊糊的液体往[npc.Name][npc.pussy+]里塞得时候，[npc.she]浑身都颤抖起来，"
									+ "只能竭尽全力不去想自己[npc.pussy]里那冰冷的"+cumName+"，"+targetNameSelfShe+"清空之后，就将避孕套丢在了地上……"));
				}
				break;
		}
		sb.append("</p>");
		
		sb.append(condomTarget.ingestFluid(usedCondom.getCum(), orifice));
		
		return sb.toString();
	}
	
	
	// Dolls:
	
	public static int dollOption = 0;
	private static int[] dollCost = {200_000, 300_000, 600_000};
	public static int genitalsOption = 0;
	private static int[] genitalCost = {0, 20_000, 30_000};
	public static int ageOption = 0;
	private static int[] ageCost = {0, 25_000, 25_000, 25_000, 25_000, 25_000};
	public static int outfitOption = 0;
	private static String[] outfitId = {null, "innoxia_rainbow", "innoxia_kitty", "innoxia_maid"};
	private static int[] outfitCost = {0, 2_500, 5_000, 15_000};
	
	public static boolean barcodeRemoval = false;
	private static int barcodeCost = 5_000;
	public static boolean toySet = false;
	private static int toyCost = 15_000;
	public static boolean hair = false;
	private static int hairCost = 25_000;
	public static boolean deck = false;
	public static int deckCost = 1_000_000;
	
	public static boolean fucked = false;
	private static int fuckedCost = 1_000;
	
	private static AbstractSubspecies dollSubspecies = Subspecies.HUMAN;
	
	private static GameCharacter slaveToDollify = null;
	private static GameCharacter newDoll = null;
	
	public static int getDollBrochureCost() {
		int cost  = 0;
		
		cost += getDollCost(dollOption);
		cost += getGenitalCost(genitalsOption);
		cost += getAgeCost(ageOption);
		cost += getOutfitCost(outfitOption);
		
		cost += barcodeRemoval?getBarcodeCost():0;
		cost += toySet?getToyCost():0;
		cost += hair?getHairCost():0;
		cost += deck?getDeckCost():0;

		cost += fucked?getFuckedCost():0;
		
		if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_discount")) {
			cost *= 0.75f;
		} else if(Main.game.getDialogueFlags().hasFlag("innoxia_sex_shop_penalty")) {
			cost *= 2f;
		}
		
		return cost;
	}
	
	private static float getCostModifier() {
		if(slaveToDollify!=null) {
			return 0.05f;
		}
		return 1f;
	}
	
	private static int getDollCost(int option) {
		return (int) (dollCost[option] * getCostModifier());
	}
	
	private static int getGenitalCost(int option) {
		return (int) (genitalCost[option] * getCostModifier());
	}
	
	private static int getAgeCost(int option) {
		return (int) (ageCost[option] * getCostModifier());
	}
	
	private static int getOutfitCost(int option) {
		return outfitCost[option];
	}
	
	private static int getBarcodeCost() {
		return barcodeCost;
	}
	
	private static int getToyCost() {
		return toyCost;
	}
	
	private static int getHairCost() {
		return (int) (hairCost * getCostModifier());
	}
	
	private static int getDeckCost() {
		return deckCost;
	}
	
	private static int getFuckedCost() {
		return fuckedCost;
	}
	
	
	public static final DialogueNode DOLL_BROCHURE = new DialogueNode("玩偶手册", "", true) {
		@Override
		public void applyPreParsingEffects() {
			dollOption = 0;
			genitalsOption = 0;
			ageOption = 0;
			outfitOption = 0;
			barcodeRemoval = false;
			toySet = false;
			hair = false;
			deck = false;
			fucked = false;
			dollSubspecies = Subspecies.HUMAN;
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			if(slaveToDollify!=null) {
				sb.append(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_SLAVE", slaveToDollify));
			} else {
				sb.append(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE"));
			}
			
			sb.append(startWrapper("型号"));
				sb.append(applyWrapperDiscounted("DD", "我们的标准型号，‘DD’带有DD罩杯的胸部和女性化的外表。", PresetColour.GENERIC_GOOD, "DOLL_CORE_0", "选择", dollOption==0, getDollCost(0), false));
				sb.append(applyWrapperDiscounted("HH", "我们的高端型号，‘HH’带有HH罩杯的胸部和女性特点更丰富的外表。", PresetColour.GENERIC_GOOD, "DOLL_CORE_1", "选择", dollOption==1, getDollCost(1), false));
				sb.append(applyWrapperDiscounted("特别定制人形", "我们的特别定制款人形玩偶，你能想到的种族全都有。填好表格后就可以选择心仪的种族。",
							PresetColour.GENERIC_EXCELLENT, "DOLL_CORE_2", "选择", dollOption==2, getDollCost(2), false));
			sb.append(endWrapper());

			sb.append(startWrapper("生殖器"));
				sb.append(applyWrapperDiscounted("阴道", "默认设置，人造阴道。", PresetColour.FEMININE, "DOLL_GENITALS_0", "选择", genitalsOption==0, getGenitalCost(0), true));
				sb.append(applyWrapperDiscounted("阴茎", "将玩偶的阴道替换为阴茎，我们玩偶的阴茎勃起自如。",
						PresetColour.MASCULINE, "DOLL_GENITALS_1", "选择", genitalsOption==1, getGenitalCost(1), true));
				sb.append(applyWrapperDiscounted("共有", "专为愿享受双重极乐的客户打造，玩偶同时拥有阴道和阴茎。", PresetColour.ANDROGYNOUS, "DOLL_GENITALS_2", "选择", genitalsOption==2, getGenitalCost(2), true));
			sb.append(endWrapper());

			if(Main.getProperties().hasValue(PropertyValue.ageContent)) {
				sb.append(startWrapper("外表年龄"));
					sb.append(applyWrapperDiscounted("18岁", "我们的默认型号，拥有18岁的外表。", PresetColour.AGE_TEENS, "DOLL_AGE_0", "选择", ageOption==0, getAgeCost(0), true));
					sb.append(applyWrapperDiscounted("20多岁", "我们可以依您的喜好稍微增加玩偶的年龄。", PresetColour.AGE_TWENTIES, "DOLL_AGE_1", "选择", ageOption==1, getAgeCost(1), true));
					sb.append(applyWrapperDiscounted("30多岁", "如果您愿意，也可以在默认年龄上加倍。", PresetColour.AGE_THIRTIES, "DOLL_AGE_2", "选择", ageOption==2, getAgeCost(2), true));
					sb.append(applyWrapperDiscounted("40多岁", "喜欢辣妈玩偶吗？我们也能做到。", PresetColour.AGE_FORTIES, "DOLL_AGE_3", "选择", ageOption==3, getAgeCost(3), true));
					sb.append(applyWrapperDiscounted("50多岁", "如果您青睐更成熟的躯体，我们还能继续……", PresetColour.AGE_FIFTIES, "DOLL_AGE_4", "选择", ageOption==4, getAgeCost(4), true));
					sb.append(applyWrapperDiscounted("60多岁", "让它们看起来*超级*成熟……", PresetColour.AGE_SIXTIES, "DOLL_AGE_5", "选择", ageOption==5, getAgeCost(5), true));
				sb.append(endWrapper());
			}
			
			sb.append(startWrapper("套装"));
				sb.append(applyWrapper("裸体", "不随玩偶附带衣物。", PresetColour.BASE_BLUE_STEEL, "DOLL_CLOTHING_0", "选择", outfitOption==0, getOutfitCost(0), true));
				sb.append(applyWrapper("彩虹", "您的玩偶将会穿着整套彩虹套装。", PresetColour.BASE_INDIGO, "DOLL_CLOTHING_1", "选择", outfitOption==1, getOutfitCost(1), true));
				sb.append(applyWrapper("猫咪内衣", "您的玩偶将会穿着整套猫咪内衣。", PresetColour.BASE_PINK_LIGHT, "DOLL_CLOTHING_2", "选择", outfitOption==2, getOutfitCost(2), true));
				sb.append(applyWrapper("女仆装", "您的玩偶将会穿着整套女仆装。", PresetColour.BASE_PINK, "DOLL_CLOTHING_3", "选择", outfitOption==3, getOutfitCost(3), true));
			sb.append(endWrapper());
			
			sb.append(startWrapper("额外配件"));
				sb.append(applyWrapper("擦除条码", "我们将会在送货前去除您的玩偶身上的条码纹身。", PresetColour.GENERIC_MINOR_GOOD, "DOLL_BARCODE", "去除", barcodeRemoval, getBarcodeCost(), true));
				sb.append(applyWrapper("玩具套装", "您的玩偶将会附带一套玩具。", PresetColour.BASE_PINK, "DOLL_TOYS", "附加", toySet, getToyCost(), true));
				sb.append(applyWrapperDiscounted("头发", "我们将会给您的玩偶一头人造秀发。", PresetColour.BASE_BROWN_LIGHT, "DOLL_HAIR", "附加", hair, getHairCost(), true));
				sb.append(applyWrapperDisabled("D.E.C.K.",
						(Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE)
							?"[style.italicsDisabled(你已经有一个D.E.C.K.了……)]"
							:"随玩偶买一部D.E.C.K.，允许您随时随地全套定制玩偶。"),
						PresetColour.GENERIC_EXCELLENT, "DOLL_DECK", "购买", deck, getDeckCost(), true, Main.game.getPlayer().hasItemType(ItemType.DOLL_CONSOLE)));
			sb.append(endWrapper());

			sb.append(startWrapper("送货前服务"));
				sb.append(applyWrapper("破处", "不喜欢处女玩偶吗？赛拉特里克斯很乐意预先攻入它身上的每一个小洞。", PresetColour.BASE_PINK_DEEP, "DOLL_FUCKED", "被干", fucked, getFuckedCost(), true));
			sb.append(endWrapper());
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()>=getDollBrochureCost()) {
					return new Response("继续("+UtilText.formatAsMoney(getDollBrochureCost(), "span")+")",
							"填好表格后交还赛拉特里克斯。",
							dollOption==2
								?DOLL_BROCHURE_RACE_SELECTION
								:DOLL_BROCHURE_FINISHED) {
						@Override
						public void effects() {
							if(dollOption!=2) {
								Main.game.appendToTextStartStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED_STANDARD"));
							}
						}
					};
					
				} else {
					return new Response("继续("+UtilText.formatAsMoneyUncoloured(getDollBrochureCost(), "span")+")", "你付不起……", null);
				}
				
			} else if(index==2) {
				return new Response("返回", "还是不买玩偶了。", DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_counter"));
			}
			return null;
		}
	};

	public static final DialogueNode DOLL_BROCHURE_INTERNAL = new DialogueNode("玩偶手册", "", true) {
		@Override
		public String getHeaderContent() {
			return DOLL_BROCHURE.getHeaderContent();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOLL_BROCHURE.getResponse(responseTab, index);
		}
	};
	
	private static List<AbstractSubspecies> getProhibitedSubspecies() {
		return Util.newArrayListOfValues(
				Subspecies.ANGEL, // No angels
				Subspecies.FOX_ASCENDANT, // They don't TF youko in case it pisses off the youko lilin
				Subspecies.FOX_ASCENDANT_ARCTIC,
				Subspecies.FOX_ASCENDANT_FENNEC,
				Subspecies.DOLL // Silly!
			);
	}
	
	private static List<AbstractSubspecies> dollCompatibleSubspecies = new ArrayList<>();
	private static List<AbstractSubspecies> getDollCompatibleSubspecies() {
		// check every subspecies for compatibility with dolls...
		if(dollCompatibleSubspecies.isEmpty()) {
			dollCompatibleSubspecies.addAll(Subspecies.getAllSubspecies());
			dollCompatibleSubspecies.removeIf(s->s.getRace()==Race.ELEMENTAL
					 || s.getRace()==Race.getRaceFromId("dsg_dragon") // They aren't able to capture dragons to TF
					 || s.getRace()==Race.DEMON // They don't TF demons in case it pisses off a lilin
					 || s.getRace()==Race.SLIME // This would make no sense
					 || s.getRace().isAbleToSelfTransform() // Catch to make sure special future races aren't added
					 || getProhibitedSubspecies().contains(s));
			NPC doll = new BasicDoll();
			for(AbstractSubspecies s : new ArrayList<>(dollCompatibleSubspecies)) {
				doll.setBody(doll.getGender(), s, RaceStage.GREATER, true);
				doll.setBodyMaterial(BodyMaterial.SILICONE);
				if(doll.getFleshSubspecies()!=s) {
					dollCompatibleSubspecies.remove(s);
				}
			}
		}
		return dollCompatibleSubspecies;
	}
	
	public static final DialogueNode DOLL_BROCHURE_RACE_SELECTION = new DialogueNode("玩偶手册", "", true) {
		@Override
		public String getContent() {
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_RACE_SELECTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<AbstractSubspecies> availableSubspecies = getDollCompatibleSubspecies();//new ArrayList<>();
//			availableSubspecies.addAll(Subspecies.getAllSubspecies());
//			availableSubspecies.removeIf(s->s.getRace()==Race.ELEMENTAL
//					 || s.getRace()==Race.getRaceFromId("dsg_dragon") // They aren't able to capture dragons to TF
//					 || s.getRace()==Race.DEMON // They don't TF demons in case it pisses off a lilin
//					 || s.getRace()==Race.SLIME // This would make no sense
//					 || s.getRace().isAbleToSelfTransform() // Catch to make sure special future races aren't added
//					 || getProhibitedSubspecies().contains(s));
			
			if (index!=0 && index<availableSubspecies.size()+1) {
				AbstractSubspecies subspecies = availableSubspecies.get(index - 1);
				String name = subspecies.getSingularFemaleName(null);
				
				return new Response(
						Util.capitaliseSentence(name),
						"选择"+UtilText.generateSingularDeterminer(name)+name+"为玩偶的亚种。",
						DOLL_BROCHURE_FINISHED){
					@Override
					public void effects() {
						Main.game.appendToTextStartStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED_SPECIAL"));
						dollSubspecies = subspecies;
					}
				};
				
			} else if (index == 0) {
				return new Response("返回", "返回到上一页。", DOLL_BROCHURE_INTERNAL);
			}
			return null;
		}
	};
	
	public static final DialogueNode DOLL_BROCHURE_FINISHED = new DialogueNode("玩偶手册", "", true) {
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(dollOption), true);
			UtilText.addSpecialParsingString(dollSubspecies==null?"":dollSubspecies.getSingularFemaleName(null), false);
			UtilText.addSpecialParsingString(String.valueOf(genitalsOption), false);
			UtilText.addSpecialParsingString(String.valueOf(ageOption), false);
			UtilText.addSpecialParsingString(String.valueOf(outfitOption), false);
			UtilText.addSpecialParsingString(String.valueOf(toySet), false);
			UtilText.addSpecialParsingString(String.valueOf(barcodeRemoval), false);
			UtilText.addSpecialParsingString(String.valueOf(hair), false);
			UtilText.addSpecialParsingString(String.valueOf(deck), false);
			UtilText.addSpecialParsingString(String.valueOf(fucked), false);
			UtilText.addSpecialParsingString(Util.intToString(getDollBrochureCost()), false);
			if(slaveToDollify==null) {
				return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED");
			} else {
				return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_FINISHED_SLAVE");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("支付("+UtilText.formatAsMoney(getDollBrochureCost(), "span")+")",
						"支付"+UtilText.formatAsMoney(getDollBrochureCost(), "span")+"购入选定选项的玩偶。",
						slaveToDollify==null
							?DOLL_BROCHURE_END
							:DOLL_BROCHURE_END_SLAVE) {
					@Override
					public void effects() {
						Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().incrementMoney(-getDollBrochureCost()));
						Main.game.getNpc(Saellatrix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop_factory"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_factory_doll_machine"));
					}
				};
				
			} else if (index == 2) {
				return new Response("返回", "返回手册界面。", DOLL_BROCHURE_INTERNAL);
			}
			
			return null;
		}
	};
	
	public static final DialogueNode DOLL_BROCHURE_END = new DialogueNode("玩偶手册", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(deck) {
				Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.DOLL_CONSOLE)));
			}
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(deck), true);
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待",
						"等赛拉特里克斯带着你的新玩偶回来。",
						DOLL_BROCHURE_END_FINAL) {
					@Override
					public void effects() {
						initDoll();
						Main.game.getNpc(Saellatrix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_dolls"));
						Main.game.getPlayer().setLocation(Main.game.getNpc(Saellatrix.class));
						newDoll.setLocation(Main.game.getNpc(Saellatrix.class), true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode DOLL_BROCHURE_END_FINAL = new DialogueNode("玩偶手册", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.appendToTextEndStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_NEW_DOLL_END", newDoll));
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 15;
		}
		@Override
		public String getContent() {
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END_FINAL", newDoll);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"你得到了一个新玩偶！",
						DOLL_BROCHURE_NEW_DOLL_END);
			}
			return null;
		}
	};
	
	
	public static final DialogueNode DOLL_BROCHURE_END_SLAVE = new DialogueNode("玩偶手册", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(deck) {
				Main.game.appendToTextEndStringBuilder(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.DOLL_CONSOLE)));
			}
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(String.valueOf(deck), true);
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END_SLAVE", slaveToDollify);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("等待",
						"等赛拉特里克斯带着你的新玩偶回来。",
						DOLL_BROCHURE_END_SLAVE_FINAL) {
					@Override
					public void effects() {
						initDoll();
						Main.game.getNpc(Saellatrix.class).setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_dolls"));
						Main.game.getPlayer().setLocation(Main.game.getNpc(Saellatrix.class));
						newDoll.setLocation(Main.game.getNpc(Saellatrix.class), true);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOLL_BROCHURE_END_SLAVE_FINAL = new DialogueNode("玩偶手册", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.appendToTextEndStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_NEW_DOLL_END", newDoll));
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 30;
		}
		@Override
		public String getContent() {
			return Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_END_SLAVE_FINAL", newDoll);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续",
						"你得到了一个新玩偶！",
						DOLL_BROCHURE_NEW_DOLL_END);
			}
			return null;
		}
	};

	public static final DialogueNode DOLL_BROCHURE_NEW_DOLL_END = new DialogueNode("玩偶手册", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.appendToTextEndStringBuilder(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "DOLL_BROCHURE_NEW_DOLL_END", newDoll));
			newDoll = null;
			Main.game.getNpc(Saellatrix.class).returnToHome();
		}
		@Override
		public int getSecondsPassed() {
			return 60 * 1;
		}
		@Override
		public String getContent() {
			return Main.game.getDefaultDialogue().getContent();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_dolls").getResponse(responseTab, index);
		}
	};

	private static void initDoll() {
		NPC doll;
		if(slaveToDollify!=null) {
			Main.game.getPlayer().removeSlave(slaveToDollify);
			slaveToDollify.unequipAllClothing(slaveToDollify, true, true);
			doll = (NPC) slaveToDollify;
		} else {
			doll = new BasicDoll();
		}
		
		Gender gender = Gender.F_V_B_FEMALE;
		if(genitalsOption==1) {
			gender = Gender.F_P_B_SHEMALE;
		} else if(genitalsOption==2) {
			gender = Gender.F_P_V_B_FUTANARI;
		}

		if(dollOption!=2) {
			dollSubspecies = Subspecies.HUMAN;
		}
		
		doll.setBody(gender, dollSubspecies, RaceStage.GREATER, true);
		doll.setBodyMaterial(BodyMaterial.SILICONE); // Birthday is set in here
		if(slaveToDollify==null) {
			doll.setBirthday(doll.getBirthday().minusDays(Util.random.nextInt(61))); // Creation date is 0-60 days before purchase
		}
		if(ageOption==1) {
			doll.setAgeAppearanceAbsolute(25);
		} else if(ageOption==2) {
			doll.setAgeAppearanceAbsolute(35);
		} else if(ageOption==3) {
			doll.setAgeAppearanceAbsolute(45);
		} else if(ageOption==4) {
			doll.setAgeAppearanceAbsolute(55);
		} else if(ageOption==5) {
			doll.setAgeAppearanceAbsolute(65);
		}
		doll.setPlayerKnowsName(true);

		int dollCount = ((Saellatrix)Main.game.getNpc(Saellatrix.class)).getDollsSold();
		String dollNumber = "#"+String.format("%05d", dollCount);
		
		if(slaveToDollify==null) {
			doll.setName("玩偶");
			((Saellatrix)Main.game.getNpc(Saellatrix.class)).incrementDollsSold(1);
			doll.setSurname(dollNumber);
		}
		
		doll.addTattoo(InventorySlot.EYES,
				new Tattoo("innoxia_property_barcode",
						PresetColour.CLOTHING_WHITE,
						false,
						new TattooWriting(
								dollNumber,
								PresetColour.CLOTHING_WHITE,
								false),
						null));

		if(slaveToDollify!=null) {
			doll.setDescription("[npc.Name]曾是你的一名奴隶，但你已经在洛维耶纳奢侈品店将[npc.herHim]永久地转化成了一个唯命是从的性爱玩偶。");
		} else {
			doll.setDescription("该玩偶由“洛维耶纳奢侈品店”制作。");
		}
		
		doll.setPetName(Main.game.getPlayer(), "master");
		
		doll.setLocation(WorldType.getWorldTypeFromId("innoxia_dominion_sex_shop"), PlaceType.getPlaceTypeFromId("innoxia_dominion_sex_shop_dolls"), true);


		if(slaveToDollify!=null) {
			// Reset all affections:
			for(NPC npc : Main.game.getAllNPCs()) {
				if(npc.getAffectionMap().containsKey(slaveToDollify.getId())) {
					npc.setAffection(slaveToDollify, 0);
				}
			}
		}
		
		if(dollOption==1) {
			doll.setBreastSize(CupSize.HH);
			doll.setHipSize(HipSize.FIVE_VERY_WIDE);
			doll.setAssSize(AssSize.FIVE_HUGE);
		}
		
		doll.unequipAllClothingIntoVoid(true, true);
		doll.setMoney(0);
		
		if(outfitOption!=0) {
			String id = outfitId[outfitOption];
			AbstractSetBonus sb = SetBonus.getSetBonusFromId(id);

			if(ClothingType.getAllClothingInSet(sb)!=null) {
				for (AbstractClothingType ct : ClothingType.getAllClothingInSet(sb)) {
					AbstractClothing clothing = Main.game.getItemGen().generateClothing(ct, false);
					for(int i=0; i<ct.getColourReplacements().size(); i++) {
						ColourReplacement cr = ct.getColourReplacement(i);
						clothing.setColour(i, cr.getFirstOfDefaultColours());
					}
					doll.equipClothingFromNowhere(clothing, true, doll);
				}
			}
			if(outfitId[outfitOption]=="innoxia_rainbow") { // Rainbow dolls come with heels
				doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_RED, PresetColour.CLOTHING_BLUE, PresetColour.CLOTHING_YELLOW, false), true, doll);
			}
		}
		
		if(barcodeRemoval) {
			doll.clearTattoos();
		}
		
		if(toySet) {
			if(doll.hasVagina()) {
				doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("norin_dildos_realistic_dildo", PresetColour.CLOTHING_PINK_LIGHT, false), true, doll);
			}
			if(doll.hasPenis()) {
				AbstractClothing cage = Main.game.getItemGen().generateClothing("innoxia_bdsm_ornate_chastity_cage", PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_PINK_LIGHT, PresetColour.CLOTHING_BRASS, false);
				cage.setSealed(false);
				doll.equipClothingFromNowhere(cage, true, doll);
			}
			doll.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_heart", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_PINK_LIGHT, null, false), true, doll);
			AbstractClothing gag = Main.game.getItemGen().generateClothing("innoxia_bdsm_ballgag", PresetColour.CLOTHING_PINK_LIGHT, false);
			gag.setSealed(false);
			doll.equipClothingFromNowhere(gag, true, doll);
			AbstractClothing blindfold = Main.game.getItemGen().generateClothing("innoxia_bdsm_blindfold", PresetColour.CLOTHING_PINK_LIGHT, false);
			blindfold.setSealed(false);
			doll.equipClothingFromNowhere(blindfold, true, doll);
		}
		
		if(hair) {
			doll.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			doll.setHairStyle(HairStyle.STRAIGHT);
		}

		if(fucked) {
			doll.completeVirginityLoss();
			Saellatrix saellatrix = ((Saellatrix)Main.game.getNpc(Saellatrix.class));
//			if(doll.hasPenis()) {
//				doll.setVirginityLoss(new SexType(SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), saellatrix, "before being delivered to you");
//			}
			if(doll.hasVagina()) {
				doll.setVirginityLoss(new SexType(SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), saellatrix, "before being delivered to you");
			}
			doll.setVirginityLoss(new SexType(SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), saellatrix, "before being delivered to you");
			doll.setVirginityLoss(new SexType(SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), saellatrix, "before being delivered to you");
			doll.setVirginityLoss(new SexType(SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS), saellatrix, "before being delivered to you");
		}
		
		try {
			if(slaveToDollify==null) {
				Main.game.addNPC(doll, false);
			}
			Main.game.getPlayer().addSlave(doll);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		newDoll = doll;
		slaveToDollify = null; // Reset to null so standard DOLL_BROCHURE works
	}
	
	private static String startWrapper(String title) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='cosmetics-inner-container' style='margin:1% 1%; width:98%; padding:1%; box-sizing:border-box; position:relative;'>");
		sb.append("<b>"+title+"</b>");
		
		return sb.toString();
	}
	
	private static String endWrapper() {
		return "</div>";
	}
	
	private static String applyWrapper(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional) {
		return applyWrapper(title, description, buttonColour, buttonId, buttonText, buttonActive, cost, isCostAdditional, false, false);
	}

	private static String applyWrapperDiscounted(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional) {
		return applyWrapper(title, description, buttonColour, buttonId, buttonText, buttonActive, cost, isCostAdditional, false, slaveToDollify!=null);
	}
	
	private static String applyWrapperDisabled(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional, boolean isDisabled) {
		return applyWrapper(title, description, buttonColour, buttonId, buttonText, buttonActive, cost, isCostAdditional, isDisabled, false);
	}
	
	private static String applyWrapper(String title, String description, Colour buttonColour, String buttonId, String buttonText, boolean buttonActive, int cost, boolean isCostAdditional, boolean isDisabled, boolean isDiscounted) {
		StringBuilder sb = new StringBuilder();

		String border = "border: 1px solid "+(buttonActive?buttonColour:PresetColour.BASE_GREY_DARK).toWebHexString()+"55;";
		String background = "";//buttonActive?"background:#555;":"";
		String buttonStyle= "style='min-width:0; width:calc(100% - 8px); padding:4px; margin:0;'";
		
		buttonText = buttonActive?"&#10003;":"-";
		
		sb.append("<div class='container-full-width' style='width:100%; padding:0; margin:2px 0 2px 0; text-align:center; "+border+" "+background+"'>");
			sb.append("<div class='container-full-width' style='width:20%; padding:0; margin:0;'>");
				sb.append(isDisabled?"[style.boldDisabled(":"[style.bold(");
					sb.append(title);
				sb.append(")]");
			sb.append("</div>");
			
			sb.append("<div class='container-full-width' style='width:55%; padding:0; margin:0;'>");
				sb.append("<i>");
					sb.append(description);
				sb.append("</i>");
			sb.append("</div>");

			sb.append("<div class='container-full-width' style='width:5%; padding:0; margin:0;'>");
				if(isDisabled) {
					sb.append(
							"<div class='cosmetics-button disabled' "+buttonStyle+">"
								+ buttonText
							+ "</div>");
					
				} else if(buttonActive) {
					sb.append(
							"<div id='"+buttonId+"' class='cosmetics-button active' "+buttonStyle+">"
								+ "<span style='color:"+buttonColour.toWebHexString()+";'>"+buttonText+"</span>"
							+ "</div>");
					
				} else {
					sb.append(
							"<div id='"+buttonId+"' class='cosmetics-button' "+buttonStyle+">"
								+ "<span style='color:"+PresetColour.TEXT_GREY.toWebHexString()+";'>"+buttonText+"</span>"
							+ "</div>");
				}
			sb.append("</div>");
			
			sb.append("<div class='container-full-width' style='width:20%; padding:0; margin:0;'>");
				if(isDiscounted) {
					sb.append("<span style='text-decoration: line-through;'>[style.colourDisabled("+(isCostAdditional?"+":"")+UtilText.formatAsMoneyUncoloured((int) (cost * (1/getCostModifier())), "span")+")]</span><br/>");
				}
				if(buttonActive) {
					sb.append((isCostAdditional?"+":"")+UtilText.formatAsMoney(cost, "span"));
				} else {
					sb.append("[style.colourDisabled("+(isCostAdditional?"+":"")+UtilText.formatAsMoneyUncoloured(cost, "span")+")]");
				}
			sb.append("</div>");
		
		sb.append("</div>");
		return sb.toString();
	}
	
	private static GameCharacter getDollTarget() {
		if(Main.game.getNpc(Angelixx.class).isDoll()) {
			return Main.game.getNpc(Angelixx.class);
		} else {
			return Main.game.getNpc(Fiammetta.class);
		}
	}

	public static final DialogueNode SAELLATRIX_DOLL_CORE = new DialogueNode("核心", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getDollTarget());
			SuccubisSecrets.initCoveringsMap(getDollTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
					+ UtilText.parse(getDollTarget(), "<i>将D.E.C.K.的数据线插入[npc.namePos]后颈的接口中，之后你就能够自定义[npc.her]的身体了……</i>")
				+ "</div>");
				
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();

				// Remove vagina, anus, fluids:
				if(!Collections.disjoint(bct.getAllPatterns().keySet(), Util.newArrayListOfValues(CoveringPattern.ORIFICE_VAGINA, CoveringPattern.ORIFICE_ANUS, CoveringPattern.FLUID))) {
					continue;
				}
				
				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(getDollTarget(), bct, entry.getValue().getValue());

				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(getDollTarget(), titleDescription.getValue()),
						true,
						true));
			}
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false, getDollTarget().getEyeType().getRace(), getDollTarget().getCovering(getDollTarget().getEyeCovering()).getType(),
					"虹膜颜色",
					UtilText.parse(getDollTarget(), "[npc.namePos]虹膜的颜色和形状。"),
					true, true));

			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getDollTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
					"瞳孔颜色",
					UtilText.parse(getDollTarget(), "[npc.namePos]瞳孔的颜色与图案。"),
					true, true));

			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getDollTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
					"巩膜颜色",
					UtilText.parse(getDollTarget(), "[npc.namePos]巩膜的颜色和形状。"),
					true, true));
			
			return UtilText.parse(getDollTarget(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Angelixx.class).isDoll()) {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_angelixx_doll").getResponse(responseTab, index);
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_fia_doll").getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode SAELLATRIX_DOLL_PUSSY = new DialogueNode("阴道", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getDollTarget());
			SuccubisSecrets.initCoveringsMap(getDollTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
				UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
						+CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaModifiersDiv()
						+CharacterModificationUtils.getSelfTransformVaginaWetnessDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaCapacityDiv()
						+CharacterModificationUtils.getSelfTransformVaginaDepthDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformClitorisSizeDiv()
						+CharacterModificationUtils.getSelfTransformClitorisGirthDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformClitorisModifiersDiv()
						+CharacterModificationUtils.getSelfTransformVaginaUrethraModifiersDiv()
					+"</div>"
					+"<div style='clear:left;'>"
						+CharacterModificationUtils.getSelfTransformVaginaUrethraCapacityDiv()
						+CharacterModificationUtils.getSelfTransformVaginaUrethraDepthDiv()
					+"</div>");
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(false,
					getDollTarget().getVaginaRace(),
					getDollTarget().getCovering(BodyCoveringType.VAGINA).getType(),
					"阴道颜色",
					UtilText.parse(getDollTarget(), "改变[npc.namePos]阴道的颜色。"),
					true, true));

			return UtilText.parse(getDollTarget(), UtilText.nodeContentSB.toString());
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Angelixx.class).isDoll()) {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_angelixx_doll").getResponse(responseTab, index);
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_fia_doll").getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode SAELLATRIX_DOLL_ASS = new DialogueNode("屁股", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget(getDollTarget());
			SuccubisSecrets.initCoveringsMap(getDollTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformAnusWetnessDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformAnusDepthDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getDollTarget().getAssRace(),
							getDollTarget().getCovering(BodyCoveringType.ANUS).getType(),
							"肛门颜色", 
							UtilText.parse(getDollTarget(), "改变[npc.namePos]肛门的颜色。"),
							true, true));
				
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getNpc(Angelixx.class).isDoll()) {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_angelixx_doll").getResponse(responseTab, index);
			} else {
				return DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_display_fia_doll").getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode SLAVE_DOLLIFICATION = new DialogueNode("奴隶玩偶化", "", true) {
		@Override
		public void applyPreParsingEffects() {
			slaveToDollify = null;
		}
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			sb.append(Main.game.parseFromFile("txt/places/dominion/sex_shop/generic", "SLAVE_DOLLIFICATION"));
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> slaveResponses = new ArrayList<>();
			for(GameCharacter slave : Main.game.getPlayer().getSlavesOwnedAsCharacters()) {
				if(slave.isDoll() || slave.isUnique()) {
					continue;
				}
				slaveResponses.add(new Response(
						"<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+UtilText.parse(slave, "[npc.Name]")+"</span>",
						UtilText.parse(slave,
							"告诉赛拉特里克斯，你想将你的[npc.raceFull(true)]奴隶[npc.name]转化为一个玩偶。"
								+ "<br/>[style.italicsTerrible(这是永久且不可逆的转化！)]"),
						DOLL_BROCHURE) {
					@Override
					public void effects() {
						slaveToDollify = slave;
					}
				});
			}
			
			if(index==0) {
				return new Response("返回", "还是不将奴隶玩偶化了。", DialogueManager.getDialogueFromId("innoxia_places_dominion_sex_shop_generic_counter")) {
					@Override
					public void effects() {
						slaveToDollify = null;
					}
				};
			}
			for(int i=0; i<slaveResponses.size(); i++) {
				if(index==i+1) {
					return slaveResponses.get(i);
				}
			}
			
			return null;
		}
	};
}
