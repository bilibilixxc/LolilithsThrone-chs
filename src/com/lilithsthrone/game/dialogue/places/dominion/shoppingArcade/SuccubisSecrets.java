package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Face;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.66
 * @version 0.4
 * @author Innoxia
 */
public class SuccubisSecrets {

	public static InventorySlot invSlotTattooToRemove = null;
	
	public static Map<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> coveringsNamesMap;
	
	private static StringBuilder descriptionSB;
	
	public static final int BASE_COSMETICS_COST = 200;
	public static final int BASE_PIERCINGS_COST = 25;
	public static final int BASE_HAIR_LENGTH_COST = 25;
	public static final int BASE_HAIR_STYLE_COST = 50;
	public static final int BASE_ANAL_BLEACHING_COST = 100;
	public static final int BASE_BODY_HAIR_COST = 50;
	
	public static final HashMap<AbstractBodyCoveringType, Integer> cosmeticCostsMap = Util.newHashMapOfValues(
			new Value<>(BodyCoveringType.MAKEUP_BLUSHER, 25),
			new Value<>(BodyCoveringType.MAKEUP_EYE_LINER, 25),
			new Value<>(BodyCoveringType.MAKEUP_EYE_SHADOW, 25),
			new Value<>(BodyCoveringType.MAKEUP_LIPSTICK, 25),
			new Value<>(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, 25),
			new Value<>(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, 25));

	public static final HashMap<PiercingType, Integer> piercingCostsMap = Util.newHashMapOfValues(
			new Value<>(PiercingType.EAR, 10),
			new Value<>(PiercingType.LIP, 25),
			new Value<>(PiercingType.NAVEL, 25),
			new Value<>(PiercingType.NIPPLE, 50),
			new Value<>(PiercingType.NOSE, 25),
			new Value<>(PiercingType.PENIS, 100),
			new Value<>(PiercingType.TONGUE, 50),
			new Value<>(PiercingType.VAGINA, 100));
	
	public static void initCoveringsMap(GameCharacter target) {
		coveringsNamesMap = new LinkedHashMap<>();
		
		for(BodyPartInterface bp : target.getAllBodyParts()){
			if(bp.getBodyCoveringType(target)!=null
					&& !(bp instanceof Hair)
					&& !(bp instanceof Eye)) {
				
				String name = bp.getName(target);
				if(bp instanceof Torso) {
					name = "躯干";
				} else if(bp instanceof Vagina) {
					name = "阴道";
				}
				
				boolean addBpi = true;
				// Check for parts not owned:
				if((bp instanceof Antenna && !target.hasAntennae())
						|| (bp instanceof Arm && !target.hasArms())
						|| (bp instanceof Breast && !target.hasNipples())
						|| (bp instanceof BreastCrotch && !target.hasBreastsCrotch())
						|| (bp instanceof Hair && !target.hasHair())
						|| (bp instanceof Horn && !target.hasHorns())
						|| (bp instanceof Penis && !target.hasPenisIgnoreDildo())
						|| (bp instanceof Tail && !target.hasTail())
						|| (bp instanceof Tentacle && !target.hasTentacle())
						|| (bp instanceof Vagina && !target.hasVagina())
						|| (bp instanceof Wing && !target.hasWings())) {
					addBpi = false;
				}
				AbstractRace race = bp.getType().getRace();
				if(addBpi) {
					AbstractBodyCoveringType coveringType = bp.getBodyCoveringType(target);
					if(bp instanceof Ass) {
						coveringType = BodyCoveringType.ANUS;
					} else if(bp instanceof Breast) {
						coveringType = BodyCoveringType.NIPPLES;
					} else if(bp instanceof BreastCrotch) {
						coveringType = BodyCoveringType.NIPPLES_CROTCH;
					}
					if(coveringsNamesMap.containsKey(coveringType)) {
						coveringsNamesMap.get(coveringType).getValue().add(name);
					} else {
						coveringsNamesMap.put(coveringType, new Value<>(race, Util.newArrayListOfValues(name)));
					}
					
					if(bp instanceof Face) {
						coveringType = BodyCoveringType.MOUTH;
						if(coveringsNamesMap.containsKey(coveringType)) {
							coveringsNamesMap.get(coveringType).getValue().add(name);
						} else {
							coveringsNamesMap.put(coveringType, new Value<>(race, Util.newArrayListOfValues(name)));
						}
						coveringType = BodyCoveringType.TONGUE;
						if(coveringsNamesMap.containsKey(coveringType)) {
							coveringsNamesMap.get(coveringType).getValue().add(name);
						} else {
							coveringsNamesMap.put(coveringType, new Value<>(race, Util.newArrayListOfValues(name)));
						}
					}
				}
			}
		}
		
		if(target.getTailType()==TailType.DEMON_HAIR_TIP && !coveringsNamesMap.containsKey(BodyCoveringType.HAIR_DEMON)) {
			coveringsNamesMap.put(BodyCoveringType.HAIR_DEMON, new Value<>(Race.DEMON, Util.newArrayListOfValues(BodyCoveringType.HAIR_DEMON.getName(target))));
		}
		
		if(target.hasNipples()) {
			coveringsNamesMap.putIfAbsent(BodyCoveringType.MILK, new Value<>(Race.NONE, Util.newArrayListOfValues("乳汁")));
		}
		if(target.hasPenisIgnoreDildo()) {
			coveringsNamesMap.putIfAbsent(BodyCoveringType.CUM, new Value<>(Race.NONE, Util.newArrayListOfValues("精液")));
		}
		if(target.hasVagina()) {
			coveringsNamesMap.putIfAbsent(BodyCoveringType.GIRL_CUM, new Value<>(Race.NONE, Util.newArrayListOfValues("爱液")));
		}
		
		
		if(Main.getProperties().hasValue(PropertyValue.pubicHairContent) && target.getPubicHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getPubicHairType().getType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getPubicHairType().getType()).getValue().add(UtilText.parse(target, "生长在[npc.namePos]的阴部"));
		}
		if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && target.getFacialHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getFacialHairType().getType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getFacialHairType().getType()).getValue().add(UtilText.parse(target, "覆盖[npc.namePos]的面部"));
		}
		if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && target.getUnderarmHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getBodyHairCoveringType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getBodyHairCoveringType()).getValue().add(UtilText.parse(target, "生长在[npc.namePos]的腋下"));
		}
		if(Main.getProperties().hasValue(PropertyValue.assHairContent) && target.getAssHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getAssHairType().getType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getAssHairType().getType()).getValue().add(UtilText.parse(target, "生长在[npc.namePos]的肛周"));
		}
		
		// Alter the map for if the target's body is not made of flesh:
		if(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH) {
			Map<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> altMaterialCoveringsNamesMap = new LinkedHashMap<>();
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : coveringsNamesMap.entrySet()) {
				if(entry.getKey().getCategory().isInfluencedByMaterialType()) {
					altMaterialCoveringsNamesMap.put(BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), entry.getKey().getCategory()), entry.getValue());
				} else {
					altMaterialCoveringsNamesMap.put(entry.getKey(), entry.getValue());
				}
			}
			coveringsNamesMap = altMaterialCoveringsNamesMap;
		}

		for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : coveringsNamesMap.entrySet()) {
			if(entry.getKey().getCategory()==BodyCoveringCategory.ANUS) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("肛门");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.MOUTH) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("嘴");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.NIPPLE) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("乳头");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.NIPPLE_CROTCH) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("胯乳乳头");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.TONGUE) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("舌头");
			}
		}
	}
	
	public static Value<String, String> getCoveringTitleDescription(GameCharacter target, AbstractBodyCoveringType coveringType, List<String> areasList) {
		String title = Util.capitaliseSentence(coveringType.getNameTransformation(target));
		
		String description = "这里是"+coveringType.getName(target)+"，现在覆盖着[npc.namePos]的"+Util.stringsToStringList(areasList, false)+"。";
		
		if(coveringType.getCategory()==BodyCoveringCategory.FLUID) {
			description = "正如其名，这只是[npc.namePos]的"+Util.stringsToStringList(areasList, false)+"。";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.ANUS) {
			title = "肛门";
			description = "这是[npc.namePos]肛周的皮肤。次要颜色则决定了肛门内壁的颜色。";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.VAGINA) {
			title = "阴道";
			description = "这是[npc.namePos]阴唇皮肤的颜色。次要颜色是阴道内壁的颜色。";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.PENIS) {
			title = "阴茎";
			description = "这是[npc.namePos]阴茎的皮肤。次要颜色决定了尿道内部的颜色(如果足以插入)。";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.NIPPLE) {
			title = "乳头";
			description = "这是[npc.namePos]乳头和乳晕的颜色。次要颜色决定了乳头内壁的颜色(如果足以插入)。";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.NIPPLE_CROTCH) {
			title = "胯乳乳头";
			description = "这是[npc.namePos][npc.crotchBoobs]乳头和乳晕的颜色。次要颜色是乳头内壁的颜色(如果足以插入)。";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.MOUTH) {
			title = "嘴唇与喉咙";
			if(target.getFaceType().getTags().contains(BodyPartTag.FACE_BEAK)) {
				description = "这是[npc.namePos]喙的颜色。次要颜色决定了嘴巴内部和喉咙的颜色。";
			} else {
				description = "这是[npc.namePos]嘴唇的皮肤。要颜色决定了嘴巴内部和喉咙的颜色。";
			}
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.TONGUE) {
			title = "舌头";
			description = "这是[npc.namePos]舌头的皮肤。";
		
		} else if(Main.getProperties().hasValue(PropertyValue.pubicHairContent) && coveringType == target.getPubicHairType().getType()) {
			title = "阴部"+coveringType.getName(target);
			description = "这是"+coveringType.getName(target)+"，目前"+Util.stringsToStringList(areasList, false)+"。";
			
		} else if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && coveringType == target.getFacialHairType().getType()) {
			title = "面部"+coveringType.getName(target);
			description = "这是"+coveringType.getName(target)+"，目前"+Util.stringsToStringList(areasList, false)+"。";
			
		} else if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && coveringType == target.getBodyHairCoveringType()) {
			title = "身体"+coveringType.getName(target);
			description = "这是"+coveringType.getName(target)+"，目前"+Util.stringsToStringList(areasList, false)+"。";
		}
		
		return new Value<>(title, description);
	}
	
	public static int getBodyCoveringTypeCost(AbstractBodyCoveringType type) {
		if(cosmeticCostsMap.containsKey(type)) {
			return cosmeticCostsMap.get(type);
		}
		
		return BASE_COSMETICS_COST;
	}

	public static int getPiercingCost(PiercingType type) {
		if(piercingCostsMap.containsKey(type)) {
			return piercingCostsMap.get(type);
		}
		
		return BASE_PIERCINGS_COST;
	}
	
	private static Kate getKate() {
		return (Kate) Main.game.getNpc(Kate.class);
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("魅魔的秘密(外部)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("进入", "“魅魔的秘密”目前打烊了。如果你想进去转转，得营业时间再来。", null);
						
					} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)) {
						return new Response("进入", "进入魅魔的秘密。", SHOP_BEAUTY_SALON_ENTER) {
							@Override
							public void effects() {
								BodyChanging.setTarget(Main.game.getPlayer());
							}
						};
						
					} else {
						return new Response("进入", "进入魅魔的秘密。", SHOP_BEAUTY_SALON) {
							@Override
							public void effects() {
								BodyChanging.setTarget(Main.game.getPlayer());
							}
						};
					}
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("叫醒她", "叫醒睡着的恶魔。", SHOP_BEAUTY_SALON_WAKE);
				
			} else if (index == 2) {
				return new Response("看着", "等睡着的恶魔自然醒来。", SHOP_BEAUTY_SALON_WATCH);

			} else if (index == 0) {
				return new Response("离开", "返回购物中心。", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
					}
				};
			}
			return null;
		}
	};
	public static final DialogueNode SHOP_BEAUTY_SALON_WAKE = new DialogueNode("魅魔的秘密", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getKate().wakeUp();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WAKE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("算了", "告诉她你不是那种随便找个店主就开干的货色。", SHOP_BEAUTY_SALON_NO_THANKS);
				
			} else if (index == 2) {
				return new ResponseSex("做爱", "你无法抵抗这个性奋的魅魔的要求……",
						true, true,
						new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WAKE_START_SEX"));
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_WATCH = new DialogueNode("魅魔的秘密", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getKate().wakeUp();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WATCH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("算了", "告诉她你不是那种随便找个店主就开干的货色。", SHOP_BEAUTY_SALON_NO_THANKS);
				
			} else if (index == 2) {
				return new ResponseSex("干她！", "按她说的跟她做爱。",
						true, true,
						new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WATCH_START_SEX"));
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_NO_THANKS = new DialogueNode("魅魔的秘密", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_NO_THANKS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("业务", "阅读凯特刚刚递给你的小册子。", SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.kateIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_ENTER = new DialogueNode("魅魔的秘密", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			getKate().wakeUp();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_ENTER"));
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(getKate(), true);
			}
			if(getKate().isVisiblyPregnant()) {
				getKate().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			}
		}
		@Override
		public String getContent() {
			return "";//UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_ENTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_MAIN = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static Response getMainResponse(int index) {
		if(index == 1){
			return new ResponseTrade("跟凯特交易", "小册子背后还塞着一张分开的纸片。上面告诉你凯特是一家大型珠宝公司的分销商。", getKate());
			
		} else if (index == 2) {
			if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				return new Response("妆容", "由于你的身体由"+Main.game.getPlayer().getBodyMaterial().getName()+"构成，凯特无法对你化妆！", null);
				
			} else {
				return new Response("妆容",
						"凯特提供广泛的化妆服务，小册子上有好几页都是各种图片，展示着各色各样的口红、指甲油和其他化妆品的效果。",
						SHOP_BEAUTY_SALON_COSMETICS);
			}

		} else if (index == 3) {
			return new Response("头发",
					"上面有一张跨页的内容，展示了凯特能给头发染各种颜色，换不同造型和长度。",
					SHOP_BEAUTY_SALON_HAIR);

		} else if (index == 4) {
				return new Response("穿孔",
						"凯特提供多种多样、范围广泛的穿孔。",
						SHOP_BEAUTY_SALON_PIERCINGS);

		}  else if (index == 5) {
				return new Response("眼睛",
						"小册子刚开始专门有一页，推销着凯特眼睛易色的功夫。"
						+ "与皮肤易色类似，这对她的灵气要求很高，所以也很昂贵。", SHOP_BEAUTY_SALON_EYES);

		} else if (index == 6) {
			return new Response("体表",
					"小册子中间专门有一页，推销着凯特驾驭奥术给皮肤或皮毛易色的能力。"
					+ "显然这对她的灵气有很高要求，所以也很行昂贵。",
					SHOP_BEAUTY_SALON_SKIN_COLOUR){
				@Override
				public void effects() {
					initCoveringsMap(Main.game.getPlayer());
				}
			};

		} else if (index == 7) {
			return new Response("其他", "凯特还提供各种杂项服务，例如肛门漂白。", SHOP_BEAUTY_SALON_OTHER);

		} else if (index == 8) {
			return new Response("纹身", "小册子的绝大部分都被各种图画和照片占据，体现出凯特非凡的艺术潜质。"
					+ "她甚至能纹上奥术附魔的纹身，不过看上去不算便宜……", SHOP_BEAUTY_SALON_TATTOOS);

		} else if (index == 9) {
			return new ResponseSex("做爱",
					"宣传册的结尾处全部都是凯特把尾巴插进自己各处腔穴的样子，还配有极具暗示性的描述：“别让我自己来……”",
					true, true,
					new SMSitting(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
						}
					},
					null,
					null,
					AFTER_SEX_REPEATED,
					UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN_SEX"));
			
		} else if(index==10) {
			return new ResponseSex("睡奸",
					"她是个深度睡眠者，你有自信可以在不吵醒凯特的情况下操她。"
							+ "你所要做的就是等她睡着，然后动作小心点儿……",
					true, false,
					new SMSitting(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
						}
						@Override
						public SexPace getStartingSexPaceModifier(GameCharacter character) {
							if(character.isPlayer()) {
								return SexPace.DOM_GENTLE;
							}
							return super.getStartingSexPaceModifier(character);
						}
						@Override
						public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
							Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
							map.put(ImmobilisationType.SLEEP, new HashMap<>());
							map.get(ImmobilisationType.SLEEP).put(Main.game.getPlayer(), Util.newHashSetOfValues(getKate()));
							return map;
						}
					},
					null,
					null,
					AFTER_SEX_REPEATED,
					UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN_SEX_SLEEP")){
				@Override
				public void effects() {
					getKate().addStatusEffect(StatusEffect.SLEEPING_HEAVY, -1);
				}
			};
			
		} else if (index == 11
				&& Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)
				&& Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_START
				&& !Main.game.getPlayer().hasItemType(ItemType.CANDI_PERFUMES)) {
			if(Main.game.getPlayer().getMoney()<500) {
				return new Response("坎迪的香水", "你需要至少拥有500火币，才能付得起坎迪香水的钱！", null);
			}
			return new Response("坎迪的香水", "告诉凯特你是来帮坎迪取香水的。", SHOP_BEAUTY_SALON_CANDI_PERFUME) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-500));
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_PERFUMES), false));
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_PERFUME));
				}
			};
			
		} else if (index == 0) {
			return new Response("离开", "离开凯特的商店，回到购物中心。", EXTERIOR){
				@Override
				public void effects() {
					Main.game.setResponseTab(0);
				}
			};
		}
		
		return null;
	}
	
	public static final DialogueNode SHOP_BEAUTY_SALON_CANDI_PERFUME = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_CANDI_PERFUME");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static String getMoneyRemainingString() {
		return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MONEY_REMAINING");
	}
	
	public static final DialogueNode SHOP_BEAUTY_SALON_COSMETICS = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "妆容";
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_COSMETICS"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "腮红", "腮红(也叫胭脂)被用来粉饰脸颊，以显得更加年轻或凸显颧骨。", true, true)
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "口红", "口红被用来为嘴唇提供色彩、质地或保护。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "眼线", "眼线用于眼廓周围，有助于修饰眼型或突出不同的特征。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "眼影", "眼影用来让使用者的眼睛更加凸显迷人。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "指甲油", "指甲油用于给[pc.hands]添加色彩或提供保护。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚趾甲油", "脚趾甲油用于给[pc.feet]添加色彩或提供保护。", true, true));
			
			return UtilText.nodeContentSB.toString();
			
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_HAIR = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_HAIR"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
				CharacterModificationUtils.getKatesDivHairLengths(true, "头发长度", "头发长度决定了你能做的发型种类。头发越长，发型种类就越丰富。")

				+CharacterModificationUtils.getKatesDivHairStyles(true, "发型", "可用的发型是由你的头发长度决定的。")
				
				+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getHairType().getRace(), Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering()).getType(),
						"[pc.Hair]颜色", "头发的染色都是永久性的，如果你以后想再改变头发颜色，就需要再去凯特那里染发。", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_SKIN_COLOUR = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_SKIN_COLOUR"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = Main.game.getPlayer();
				
				Value<String, String> titleDescription = getCoveringTitleDescription(target, bct, entry.getValue().getValue());
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
						true,
						true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_EYES = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_EYES"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());

			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Main.game.getPlayer().getEyeType().getRace(), Main.game.getPlayer().getEyeCovering(),
							"虹膜", "虹膜指的是眼睛中染色的部分，负责控制瞳孔的直径和大小。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"瞳孔", "瞳孔是位于虹膜中心的透明物体，以便光线打在视网膜上。", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"虹膜", "虹膜指的是眼睛中染色的部分，负责控制瞳孔的直径和大小。", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_PIERCINGS = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_PIERCINGS"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPiercings(false));
			
			return UtilText.nodeContentSB.toString();
		
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_OTHER = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "妆容";
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_OTHER"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivAnalBleaching()

//					+(Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled() || Main.game.isPubicHairEnabled()
//							?CharacterModificationUtils.getKatesDivCoveringsNew(
//									true, Main.game.getPlayer().getBodyHairCoveringType(), "Body hair", "This is the hair that covers all areas other than the head.", true, true)
//							:"")
					
					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair(true, "面部毛发", "你脸上的毛发。" 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : "女性化角色无法长出面部毛发。"))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair(true, "阴毛", "生殖器附近的体毛；位于性器官和下体附近。")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair(true, "腋毛", "腋下的体毛。")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair(true, "肛毛", "肛门周围的体毛。")
							:"")
					);
			
			for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, bct, "体毛", "你的体毛。", true, true));
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_TATTOOS = new DialogueNode("魅魔的秘密", "-", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_TATTOOS"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoos());
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 8) {
				return new Response("纹身", "你正在查看可用的纹身……", null);
				
			} else if(index==11) {
				return new Response("确认状态：",
						"开启纹身去除确认。"
							+ "启用时，需要点击两次才能移除纹身。"
							+ "关闭时只需要一次点击。",
							SHOP_BEAUTY_SALON_TATTOOS) {
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
			}
			
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_TATTOOS_ADD = new DialogueNode("魅魔的秘密", "-", true) {

		@Override
		public String getLabel() {
			return "魅魔的秘密 - "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName()) +" 纹身";
		}
		
		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append(CharacterModificationUtils.getKatesDivTattoosAdd());
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("应用("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "你没有足够的钱用来纹纹身！", null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("应用("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "你需要选择纹身类型，添加一些文字或计数指示才能形成完整的纹身！", null);
					
				} else {
					return new Response("应用("+UtilText.formatAsMoney(value, "span")+")", "告诉凯特你想让她帮你纹上这个纹身。", SHOP_BEAUTY_SALON_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							Main.game.getPlayer().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("保存/加载", "保存/加载纹身预设。", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(SHOP_BEAUTY_SALON_TATTOOS_ADD);
					}
				};
			
			} else if(index==0) {
				return new Response("返回", "放弃这个纹身，回到主选择界面。", SHOP_BEAUTY_SALON_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	// Sex:
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("结束", "", true, false) {
		@Override
		public String getDescription() {
			return "退开让凯特恢复一下。";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("业务", "阅读凯特刚刚递给你的小册子。", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.kateIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_REPEATED = new DialogueNode("结束", "", true, false) {
		@Override
		public String getDescription() {
			if(getKate().isAsleep()) {
				return "[pc.Step]回去并离开，让凯特继续睡觉。";
			}
			return "退开让凯特恢复一下。";
		}
		public void applyPreParsingEffects() {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "AFTER_SEX_REPEATED"));
			getKate().wakeUp();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("业务", "把注意力转回到宣传册上。", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN);
			}
			return null;
		}
	};
	
}
