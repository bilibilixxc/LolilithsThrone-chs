package com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.dominion.Kay;
import com.lilithsthrone.game.character.npc.dominion.SupplierLeader;
import com.lilithsthrone.game.character.npc.dominion.SupplierPartner;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerPenis;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.FootMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisFeet;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisNipple;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.99
 * @version 0.4
 * @author Innoxia
 */
public class KaysWarehouse {
	
	private static final int PAY_OFF_PRICE = 50_000;
	private static final int PAY_OFF_PRICE_WOLFGANG_SHARE = 30_000;
	private static final int PAY_OFF_PRICE_KARL_SHARE = 20_000;

	/**
	 * Sets quest progress to RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN and appends to the TextEndStringBuilder.
	 * <br/>Moves Wolfgang and Karl to the bounty hunter lodge.
	 */
	public static void applySuppliersBeatenEffects() {
		Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_4_STOCK_ISSUES_SUPPLIERS_BEATEN));
		((SupplierLeader)Main.game.getNpc(SupplierLeader.class)).moveToBountyHunterLodge();
		((SupplierPartner)Main.game.getNpc(SupplierPartner.class)).moveToBountyHunterLodge();
	}
	
	/** The amount of flames the dobermann-boys give to you if you demonically intimidate them. */
	private static final int DEMONIC_PAYOFF = 6_000;
	
	public static boolean isPlayerMouthFree() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	public static boolean isPlayerAssFree() {
		return Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true);
	}
	
	public static boolean isPlayerPenisFree() {
		return Main.game.getPlayer().hasPenisIgnoreDildo() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
	}
	
	public static boolean isPlayerVaginaFree() {
		return Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
	}
	
	public static boolean isPlayerNippleFuckFree() {
		return Main.game.getPlayer().hasBreasts() && Main.game.getPlayer().isBreastFuckableNipplePenetration() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true);
	}
	
	public static boolean isSexAvailable() {
		return isPlayerMouthFree() || isPlayerAssFree() || isPlayerVaginaFree();
	}
	
	public static ResponseSex getDobermannsSexResponse(AbstractSexPosition position,
			SexSlot slotWolfgang, SexAreaInterface sexAreaWolfgang,
			SexSlot slotKarl, SexAreaInterface sexAreaKarl,
			SexSlot slotPlayer,
			String title, String description, DialogueNode postSexDialogue, String startingText) {
		
		SexType sexTypeWolfgang = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, sexAreaWolfgang);
		SexType sexTypeKarl = new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, sexAreaKarl);
		
		String fuckingText = "沃尔夫冈操着你的" +sexTypeKarl.getTargetedSexArea().getName(Main.game.getPlayer());
		if(sexTypeWolfgang.getTargetedSexArea()==sexTypeKarl.getTargetedSexArea()) {
			fuckingText = "沃尔夫冈和卡尔都在操着你的" + sexTypeWolfgang.getTargetedSexArea().getName(Main.game.getPlayer());
		}
		if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
			fuckingText = "沃尔夫冈要操你的嘴，而卡尔要你给他手淫";
		}
		
		return new ResponseSex(title,
				description
					+"<br/>[style.italicsSex(这将会导向"+fuckingText+"！)]",
				true, false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(SupplierLeader.class), slotWolfgang),
								new Value<>(Main.game.getNpc(SupplierPartner.class), slotKarl)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), slotPlayer))) {
						@Override
						public boolean isPositionChangingAllowed(GameCharacter character) {
							return false;
						}
						@Override
						public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
							return new ArrayList<>();
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(
									new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
									new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
									new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.NIPPLE
												?CoverableArea.NIPPLES
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.MOUTH
												?CoverableArea.MOUTH
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.ANUS
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.ANUS
												?CoverableArea.ANUS
												:null,
											sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.VAGINA
												|| sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.VAGINA
												?CoverableArea.VAGINA
												:null)));
						}
						@Override
						public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(!character.isPlayer()) {
								return character.getMainSexPreference(targetedCharacter);
							}
							return super.getForeplayPreference(character, targetedCharacter);
						}
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.equals(Main.game.getNpc(SupplierLeader.class))) {
								return sexTypeWolfgang;
							}
							if(character.equals(Main.game.getNpc(SupplierPartner.class))) {
								return sexTypeKarl;
							}
							return super.getMainSexPreference(character, targetedCharacter);
						}
					},
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				startingText) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				List<InitialSexActionInformation> initialActions = new ArrayList<>();
				
				boolean sameTarget = sexTypeWolfgang.getTargetedSexArea()==sexTypeKarl.getTargetedSexArea();
				
				if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierLeader.class), Main.game.getPlayer(), PenisNipple.PENIS_FUCKING_START, false, true));
				}
				
				if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.MOUTH) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), sameTarget?PenisVagina.PENIS_FUCKING_START_ADDITIONAL:PenisVagina.PENIS_FUCKING_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaOrifice.ANUS) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), sameTarget?PenisAnus.PENIS_FUCKING_START_ADDITIONAL:PenisAnus.PENIS_FUCKING_START, false, true));
				} else if(sexTypeKarl.getTargetedSexArea()==SexAreaPenetration.FINGER) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATED_START, false, true));
				} else if(sexTypeWolfgang.getTargetedSexArea()==SexAreaOrifice.NIPPLE) {
					initialActions.add(new InitialSexActionInformation(Main.game.getNpc(SupplierPartner.class), Main.game.getPlayer(), PenisNipple.PENIS_FUCKING_START, false, true));
				}
				
				return initialActions;
			}
		};
	}
	
	private static Response getDobermannsRewardSexResponse(int startIndex, int index, String title) {
		if(index == startIndex) {
			title = title.replaceAll("sex_type_replacement", "口交");
			
			if(!isPlayerMouthFree()) {
				return new Response(title,
						"你无法使用嘴巴，所以也就不能给这两个杜宾口交……",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.SITTING,
						SexSlotSitting.SITTING, SexAreaOrifice.MOUTH,
						SexSlotSitting.SITTING, SexAreaPenetration.FINGER,
						SexSlotSitting.PERFORMING_ORAL,
						title,
						"主动提出舔这两个杜宾男的鸡巴……",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_ORAL"));
			}
			
		} else if(index == startIndex+1) {
			title = title.replaceAll("sex_type_replacement", "串肉串");
			
			if(!isPlayerMouthFree()) {
				return new Response(title,
						"你无法使用自己的嘴巴，所以不能跟杜宾兄弟玩“串肉串”……",
						null);
				
			} else if(!isPlayerAssFree() && !isPlayerVaginaFree()) {
				return new Response(title,
						"你无法使用自己的屁股"+(Main.game.getPlayer().hasVagina()?"或小穴":"")+"，所以不能跟杜宾兄弟玩“串肉串”……",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.ALL_FOURS,
						SexSlotAllFours.BEHIND, isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotAllFours.IN_FRONT, SexAreaOrifice.MOUTH,
						SexSlotAllFours.ALL_FOURS,
						title,
						"主动提出让杜宾兄弟跟你玩“串肉串”……",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_SPITROAST"));
			}
			
		} else if(index == startIndex+2) {
			title = title.replaceAll("sex_type_replacement", "骑乘");
			
			if(!isPlayerAssFree() && !isPlayerVaginaFree()) {
				return new Response(title,
						"你无法使用后穴"+(Main.game.getPlayer().hasVagina()?"或小穴":"")+"，所以无法跟这两个杜宾骑乘……",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.LYING_DOWN,
						SexSlotLyingDown.LYING_DOWN, isPlayerVaginaFree()?SexAreaOrifice.VAGINA:SexAreaOrifice.ANUS,
						SexSlotLyingDown.MISSIONARY, isPlayerAssFree()?SexAreaOrifice.ANUS:SexAreaOrifice.VAGINA,
						SexSlotLyingDown.COWGIRL,
						title,
						"主动提出跟这两个杜宾来骑乘位……",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_RIDE"));
			}
			
		} else if(index == startIndex+3 && Main.game.isNipplePenEnabled()) {
			title = title.replaceAll("sex_type_replacement", "操乳头");
			
			if(!isPlayerNippleFuckFree()) {
				return new Response(title,
						Main.game.getPlayer().isBreastFuckableNipplePenetration()
							?"由于你无法使用足以插入的乳头，所以不能提供给杜宾兄弟……"
							:"由于你没有足以插入的乳头，所以不能提供给杜宾兄弟……",
						null);
				
			} else {
				return getDobermannsSexResponse(SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT, SexAreaOrifice.NIPPLE,
						SexSlotStanding.STANDING_DOMINANT_TWO, SexAreaOrifice.NIPPLE,
						SexSlotStanding.PERFORMING_ORAL,
						title,
						"主动提出让两个杜宾男来操你的乳头……",
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEX_OFFER_NIPPLES"));
			}
		}
		return null;
	}
	
	private static ResponseSex getKaySexResponse(String title,
			String description,
			DialogueNode postSexDialogue,
			String startingText,
			AbstractSexPosition position,
			SexSlot slotKay, SexType sexTypeKay,
			SexSlot slotPlayer,
			List<CoverableArea> kayExposedParts,
			List<CoverableArea> playerExposedParts,
			List<InitialSexActionInformation> initialActions) {
		return getKaySexResponse(title,
				description,
				postSexDialogue,
				startingText,
				position,
				slotKay,
				sexTypeKay,
				slotPlayer,
				kayExposedParts,
				playerExposedParts,
				initialActions,
				null);
	}

	/** @param startingWetAreas Maps: character who is lubricated -> Map of areas -> Map of owner of lubrication -> lubrications*/
	private static ResponseSex getKaySexResponse(String title,
			String description,
			DialogueNode postSexDialogue,
			String startingText,
			AbstractSexPosition position,
			SexSlot slotKay, SexType sexTypeKay,
			SexSlot slotPlayer,
			List<CoverableArea> kayExposedParts,
			List<CoverableArea> playerExposedParts,
			List<InitialSexActionInformation> initialActions,
			Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> startingWetAreas) {
		return new ResponseSex(title,
				description,
				true, false,
				new SexManagerDefault(
						position,
						Util.newHashMapOfValues(
								new Value<>(Main.game.getPlayer(), slotPlayer)),
						Util.newHashMapOfValues(
								new Value<>(Main.game.getNpc(Kay.class), slotKay))) {
						@Override
						public SexPace getForcedSexPace(GameCharacter character) {
							if(character==Main.game.getNpc(Kay.class)) {
								return SexPace.SUB_EAGER; // Lock Kay into being eager, as if they were to fall into resisting, the scenes don't account for that
							}
							return null;
						}
						@Override
						public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
							return clothingToEquip.isCondom(); // Do not let sex clothing be equipped onto Kay, as they have special actions for this and it would otherwise break the flow of their scenes
						}
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(
									new Value<>(Main.game.getNpc(Kay.class), kayExposedParts),
									new Value<>(Main.game.getPlayer(), playerExposedParts));
						}
						@Override
						public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(sexTypeKay!=null) {
								return character.getMainSexPreference(targetedCharacter);
							}
							return super.getForeplayPreference(character, targetedCharacter);
						}
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(sexTypeKay==null) {
								return super.getMainSexPreference(character, targetedCharacter);
							}
							if(character.equals(Main.game.getNpc(Kay.class))) {
								return sexTypeKay;
							} else {
								return sexTypeKay.getReversedSexType();
							}
						}
						@Override
						public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
							if(startingWetAreas==null) {
								return super.getStartingWetAreas();
							}
							return startingWetAreas;
						}
					},
				new ArrayList<>(),
				new ArrayList<>(),
				postSexDialogue,
				startingText) {
			@Override
			public List<InitialSexActionInformation> getInitialSexActions() {
				return initialActions;
			}
		};
	}
	
	
	public static final DialogueNode INITIAL_ENTRY = new DialogueNode("接待区", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "INITIAL_ENTRY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
					return new Response("出口", "决定暂时离开仓库。", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
						}
					};
					
				} else {
					return new Response("接待员", "靠近接待员，告诉她你是来这儿见凯的。", RECEPTIONIST_UNLOCKING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.supplierDepotDoorUnlocked, true);
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode RECEPTIONIST_UNLOCKING = new DialogueNode("接待区", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "RECEPTIONIST_UNLOCKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return INITIAL_ENTRY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode RECEPTION = new DialogueNode("接待区", "", false) {
		@Override
		public int getSecondsPassed() {
			return 60;
		}
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked);
		}
		@Override
		public String getContent() {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.supplierDepotDoorUnlocked)) {
				return INITIAL_ENTRY.getContent();
			}
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "RECEPTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return INITIAL_ENTRY.getResponse(responseTab, index);}
	};
	
	public static final DialogueNode CORRIDOR = new DialogueNode("走廊", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "CORRIDOR");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode STORAGE_AREA = new DialogueNode("仓储区", "", false) {
		@Override
		public int getSecondsPassed() {
			return 30;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				if(!Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
					return new Response("过剩处理", "你认为在未获得许可的情况下，自行从“过剩处理”板条箱中取用物品不是一个好主意……", null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayCratesSearched)) {
					return new Response("过剩处理", "你已经拿了今天的过剩库存了！", null);
					
				} else {
					return new Response("过剩处理", "自行拿去今天的过剩库存。", STORAGE_AREA_SEARCHING) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayCratesSearched, true);
							
							List<AbstractClothingType> clothingToGenerate = new ArrayList<>(ClothingType.getAllClothing());
							clothingToGenerate.removeIf((clothing) -> clothing.getRarity()!=Rarity.COMMON || !clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_NYAN) || clothing.getDefaultItemTags().contains(ItemTag.NO_RANDOM_SPAWN));
							
							Main.game.getTextEndStringBuilder().append(
									Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), false), false)
									+ Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false)
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false):"")
									+ (Math.random()>0.5?Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing(Util.randomItemFrom(clothingToGenerate), true), false):"")
							        + UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA_SEARCHED"));
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode STORAGE_AREA_SEARCHING = new DialogueNode("储藏室", "", false) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "STORAGE_AREA_SEARCHING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return STORAGE_AREA.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode WEAVING_MACHINES = new DialogueNode("织机", "", false) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "WEAVING_MACHINES");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode OVERSEER_STATION = new DialogueNode("监督站", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OVERSEER_STATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS)) {
					return new Response("[kay.NamePos]的办公室", "走上楼梯，经过监督站，去见[kay.name]一面。", OFFICE) {
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OFFICE, false);
							Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
						}
					};
					
				} else {
					return new Response("上楼", "走上楼梯，进入监督站。", DOBERMANNS) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().getQuest(QuestLine.RELATIONSHIP_NYAN_HELP)==Quest.RELATIONSHIP_NYAN_2_STOCK_ISSUES_AGREED_TO_HELP) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.RELATIONSHIP_NYAN_HELP, Quest.RELATIONSHIP_NYAN_3_STOCK_ISSUES_DOBERMANNS));
							}
						}
					};
				}
				
			} else if(index==2) {
				return new Response("转身", "离开楼梯，沿着走廊往回走。", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS = new DialogueNode("监督站", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getNpc(SupplierLeader.class).setPlayerKnowsName(true);
			Main.game.getNpc(SupplierPartner.class).setPlayerKnowsName(true);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("离开", "杜宾兄弟将你误认为了执法者，最好在解决问题之后再离开……", null);
				}
				if(Main.game.getPlayer().getRace()==Race.DEMON) {
					return new Response("离开", "杜宾兄弟已经见到了你的恶魔形态，最好解决问题之后再离开……", null);
				}
				return new Response("离开", "从杜宾兄弟身旁退开，原路返回。", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_LEAVE"));
					}
				};
				
			} else if(index == 1) {
				if(Main.game.getPlayer().getMoney()<PAY_OFF_PRICE) {
					return new Response("支付("+UtilText.formatAsMoneyUncoloured(PAY_OFF_PRICE, "span")+")",
							"你的火币抵不上杜宾兄弟合同的那一部分……",
							null);
					
				} else {
					return new Response("支付("+UtilText.formatAsMoney(PAY_OFF_PRICE, "span")+")",
							"付上杜宾兄弟合同中剩下的部分款项，同时尖锐地指出他们的所作所为已经违法，这应该足以赶他们走了。",
							DOBERMANNS_PAID_OFF) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatPaid, true);
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-PAY_OFF_PRICE));
							Main.game.getNpc(SupplierLeader.class).incrementMoney(PAY_OFF_PRICE_WOLFGANG_SHARE);
							Main.game.getNpc(SupplierPartner.class).incrementMoney(PAY_OFF_PRICE_KARL_SHARE);
						}
					};
				}
				
			} else if (index == 2) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("假装执法者", "杜宾兄弟把你误认为了执法者，利用这个机会赶他们离开。", DOBERMANNS_ENFORCER_BLUFF) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatEnforcer, true);
						}
					};
					
				} else {
					return new Response("假装执法者", "你需要穿上执法者的制服才能这么做！", null);
				}
				
			} else if(index==3) {
				if(Main.game.getPlayer().getRace()!=Race.DEMON
						&& Main.game.getPlayer().getSubspeciesOverrideRace()==Race.DEMON) {
					if(!Main.game.getPlayer().isAbleToSelfTransform()) {
						return new Response("显露真身", "你自我转化的能力目前被抑制了，无法再杜宾兄弟面前转化为恶魔，吓跑他们！", null);
					} else {
						return new Response("显露真身",
								"在杜宾兄弟面前转化为恶魔，吓跑他们。",
								DOBERMANNS_DEMON_REVEAL) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatDemon, true);
							}
						};
					}
					
				} else {
					return new Response("恶魔威慑",
							"你是个恶魔，利用这一点恐吓杜宾兄弟离开。",
							DOBERMANNS_DEMONIC_INTIMIDATION,
							null,
							null,
							null,
							null,
							Util.newArrayListOfValues(Subspecies.DEMON)) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatDemon, true);
						}
					};
				}
				
			} else if(index==4) {
				if(!isSexAvailable()) {
					return new Response("诱惑", "由于你无法使用任何腔穴，所以不能诱惑这对杜宾兄弟……", null);
				}
				return new Response("诱惑",
						"诱惑杜宾兄弟，将身体献给他们，从而让他们离开凯。",
						DOBERMANNS_SEDUCE,
						null,
						null,
						Util.newArrayListOfValues(Perk.MALE_ATTRACTION, Perk.OBJECT_OF_DESIRE),
						null,
						null) {
					@Override
					public boolean isSexHighlight() {
						return true;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatSeduced, true);
					}
				};
				
			} else if(index==5) {
				if(Main.game.getPlayer().hasAnyEnforcerStatusEffect()) {
					return new Response("战斗", "杜宾兄弟把你误认为了执法者，最好利用一下……", null);
					
				} else {
					return new ResponseCombat("战斗", "战斗一触即发！",
							Main.game.getNpc(SupplierLeader.class),
							Util.newArrayListOfValues(
									Main.game.getNpc(SupplierLeader.class),
									Main.game.getNpc(SupplierPartner.class)),
							Util.newHashMapOfValues(
									new Value<>(Main.game.getPlayer(), "[pc.speech(我马上就把你们两个从这赶出去，)]"
											+ "你大喝一声，准备好战斗，"
											+ "[pc.speech(我了解你们这种人，只屈服于力量，这也是你们逼我的！)]"),
									new Value<>(Main.game.getNpc(SupplierLeader.class), "[wolfgang.speech(哈！)]沃尔夫冈大喊道，[wolfgang.speech(如果你想打架，那我们就陪你战个痛快！)]"),
									new Value<>(Main.game.getNpc(SupplierPartner.class), "[karl.speech(你有好果子吃，混球！)]卡尔嘶吼道。"))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.suppliersEncountered, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS_PAID_OFF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(PAY_OFF_PRICE), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_PAID_OFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("放他们走", "站到一旁，让这两个杜宾男离开。", DOBERMANNS_BANISHED);
				
			} else {
				return getDobermannsRewardSexResponse(2, index, "“答谢”他们(sex_type_replacement)");
			}
		}
	};
	
	public static final DialogueNode DOBERMANNS_ENFORCER_BLUFF = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_ENFORCER_BLUFF");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode DOBERMANNS_DEMON_REVEAL = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(DEMONIC_PAYOFF));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(DEMONIC_PAYOFF), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_DEMON_REVEAL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOBERMANNS_DEMONIC_INTIMIDATION = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(DEMONIC_PAYOFF));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(DEMONIC_PAYOFF), true);
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_DEMONIC_INTIMIDATION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOBERMANNS_PAID_OFF.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOBERMANNS_SEDUCE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_SEDUCE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDobermannsRewardSexResponse(1, index, "提供sex_type_replacement");
		}
	};
	
	public static final DialogueNode DOBERMANNS_COMBAT_PLAYER_VICTORY = new DialogueNode("胜利", "两个杜宾男被击溃了！", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.dobermannDefeatCombat, true);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("放他们走", "站到一旁，让这两个杜宾男离开。", DOBERMANNS_BANISHED);
				
			} else if (index == 2) {
				return new ResponseSex("干爆他们",
						"你将沃尔夫冈和卡尔推倒，让他们并排摆出狗爬式的姿势，准备好一同享乐……",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								null,
								null,
								ResponseTag.PREFER_DOGGY),
						AFTER_SEX_WILLING,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_VICTORY_FUCK_THEM"));
				
			} else {
				return getDobermannsRewardSexResponse(3, index, "屈服(sex_type_replacement)");
			}
		}
	};
	
	public static final DialogueNode DOBERMANNS_COMBAT_PLAYER_LOSS = new DialogueNode("落败", "两个杜宾男一起上，你还是处理不了……", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("挨操",
						"力尽落败后，你甚至无法抵抗，只能看着杜宾兄弟对你蠢蠢欲动……",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
						},
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED"));
				
			} else if(index == 2) {
				return new ResponseSex("挨操(渴求)",
						"被狠狠地教做人之后，你顿时起了性欲，按捺不住地乞求着两只杜宾来操你……",
						null, null, null, null, null, null,
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.PREFER_DOGGY, ResponseTag.START_PACE_PLAYER_SUB_EAGER) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(
										new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
										new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
							}
						},
						AFTER_SEX_FUCKED,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED_EAGER"));
				
			} else if(index==3) {
				if(!Main.game.isNonConEnabled()) {
					return new Response("拒绝", "你或许确实败了，但绝不能让这两个畜生得逞！", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_THROWN_OUT"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
						}
					};
					
				} else {
					return new ResponseSex("抵抗",
							"虽然已经精疲力竭，完全落败，但你还是动用起最后一丝力量，反抗这两个想强奸你的杜宾……",
							null, null, null, null, null, null,
							false, false,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getNpc(SupplierLeader.class), Main.game.getNpc(SupplierPartner.class)),
									Util.newArrayListOfValues(Main.game.getPlayer()),
									null,
									null,
									ResponseTag.PREFER_DOGGY, ResponseTag.START_PACE_PLAYER_SUB_RESISTING) {
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									return Util.newHashMapOfValues(
											new Value<>(Main.game.getNpc(SupplierLeader.class), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getNpc(SupplierPartner.class), Util.newArrayListOfValues(CoverableArea.PENIS)));
								}
							},
							AFTER_SEX_FUCKED,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_COMBAT_PLAYER_LOSS_FUCKED_RESIST"));
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_WILLING = new DialogueNode("结束", "两个杜宾男爽够了……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_WILLING_DOMMED_THEM");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_WILLING");
			}
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("放他们走", "站到一旁，让这两个杜宾男离开。", DOBERMANNS_BANISHED);
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_FUCKED = new DialogueNode("结束", "两个杜宾男和你爽够了……", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_FUCKED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("被扔出去", "卡尔毫不客气地把你扔出了仓库。", PlaceType.DOMINION_WAREHOUSES.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "AFTER_SEX_FUCKED_THROWN_OUT"));
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_WAREHOUSES, false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode DOBERMANNS_BANISHED = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			applySuppliersBeatenEffects();
			Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
			Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).setAffection(Main.game.getPlayer(), 25));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "DOBERMANNS_BANISHED");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("跟随", "跟着激动不已的凯进入办公室。", KAY_SAVED_OFFICE) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_OFFICE, false);
						Main.game.getNpc(Kay.class).setLocation(Main.game.getPlayer(), false);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_SAVED_OFFICE = new DialogueNode("", "", true, true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_SAVED_OFFICE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode OFFICE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE"));
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(Main.game.getNpc(Kay.class), true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("离开", "离开[kay.namePos]的办公室，回到仓库走廊。", CORRIDOR) {
					@Override
					public void effects() {
						Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_LEAVE"));
					}
				};
				
			} else if(index==1) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayTalkedTo)) {
					return new Response("闲聊", "你已经和[kay.name]交谈了很久，今天再也找不出话题了。", null);
					
				} else {
					return new Response("闲聊", "花点时间和[kay.name]聊聊。", KAY_OFFICE_UTIL_EMPTY) {
						@Override
						public int getSecondsPassed() {
							return 30*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK"));
							
							List<String> topics = Util.newArrayListOfValues(
									"KAY_MACHINES",
									"KAY_INTERCOM",
									"KAY_BUSINESS",
									"KAY_BOUNTY_HUNTERS");
							long lowestValue = 1_000_000;
							for(String topic : topics) {
								if(Main.game.getDialogueFlags().getSavedLong(topic)<lowestValue) {
									lowestValue = Main.game.getDialogueFlags().getSavedLong(topic);
								}
							}
							long thanksJava = lowestValue;
							topics.removeIf(s -> Main.game.getDialogueFlags().getSavedLong(s)>thanksJava);
							String topicSelected = Util.randomItemFrom(topics);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK_"+topicSelected));
							Main.game.getDialogueFlags().incrementSavedLong(topicSelected, 1);
							
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_TALK_END"));
							
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).incrementAffection(Main.game.getPlayer(), 5));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayTalkedTo, true);
						}
					};
				}
				
			} else if(index==2) {
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFlirtedWith)) {
					return new Response("调情", "你今天已经与[kay.name]调情了。如果你还想调情，得明天再来。", null);
					
				} else {
					return new Response("调情", "与[kay.name]调情一会。", KAY_OFFICE_UTIL_EMPTY) {
						@Override
						public int getSecondsPassed() {
							return 20*60;
						}
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "OFFICE_FLIRT"));
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).incrementAffection(Main.game.getPlayer(), 10));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFlirtedWith, true);
						}
					};
				}
				
			} else if(index==3) {
				if(Main.game.getNpc(Kay.class).getAffectionLevel(Main.game.getPlayer()).isLessThan(AffectionLevel.POSITIVE_FOUR_LOVE)) {
					return new Response("支配",
							"虽然他显然是喜欢你的，但你看的出来现在让[kay.name]在性爱中主导会让[kay.her]不太舒服。"
							+ "<br/>[style.italicsMinorBad(需要凯对你的好感至少达到“"+AffectionLevel.POSITIVE_FOUR_LOVE.getName()+"”。)]",
							null);
					
				} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayDommed)) {
					return new Response("支配", "你今天已经支配过[kay.name]了，这么舒服的事情太过消耗精力，明天再来吧。", null);
					
				} else {
					return new Response("支配",
							"支配[kay.name]，让[kay.herHim]向你屈服。",
							KAY_OFFICE_DOMINATE,
							Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
							Fetish.FETISH_DOMINANT.getAssociatedCorruptionLevel(),
							null,
							null,
							null) {
						@Override
						public void effects() {
							if(Main.game.getNpc(Kay.class).getAffection(Main.game.getPlayer())<100) {
								Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Kay.class).setAffection(Main.game.getPlayer(), 100));
							}
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayDommed, true);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_UTIL_EMPTY = new DialogueNode("", "", true, true) {
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
			return OFFICE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kaySubmitted)) {
				if(index==1) {
					return new Response("[pc.Name]", "并不指示[kay.name]以特殊的名字称呼你。<br/>[style.colourMinorGood(该场景过后可以随时修改。)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NO_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==2) {
					return new Response("[pc.Mistress]", "让[kay.name]称呼你为“[pc.Mistress]”。<br/>[style.colourMinorGood(该场景过后可以随时修改。)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Mistress]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==3) {
					return new Response("[pc.Maam]", "让[kay.name]称呼你为“[pc.Maam]”。<br/>[style.colourMinorGood(该场景过后可以随时修改。)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Maam]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==4) {
					String surname = (Main.game.getPlayer().getSurname()!=null && !Main.game.getPlayer().getSurname().isEmpty()?"[pc.Surname]":"[pc.Name]");
					return new Response(surname+"[pc.Miss]","让[kay.name]称呼你为“"+surname+"[pc.Miss]”。<br/>[style.colourMinorGood(该场景过后可以随时修改。)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Miss] "+surname);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
					
				} else if(index==5) {
					return new Response("[pc.Mommy]", "让[kay.name]称呼你为“[pc.Mommy]”。<br/>[style.colourMinorGood(该场景过后可以随时修改。)]", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setPetName(Main.game.getPlayer(), "[pc.Mommy]");
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PET_NAME"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kaySubmitted, true);
						}
					};
				}
				
			} else {
				boolean buttplugActionAvailable = Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)!=null || Main.game.isAnalContentEnabled();
				
				if(index==0) {
					return new Response("离开",
							"跟[kay.name]爽过之后，是时候离开办公室了。<br/>[style.italicsMinorBad(如果现在离开，再次支配他就要等到明天了！)]",
							CORRIDOR) {
						@Override
						public void effects() {
							Main.game.getPlayer().setNearestLocation(WorldType.TEXTILES_WAREHOUSE, PlaceType.TEXTILE_WAREHOUSE_CORRIDOR, false);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_LEAVE"));
						}
					};
				}
				// Standard actions:
				if(index==1) {
					return new Response("抚摸[kay.herHim]", "让[kay.name]跪在你的身旁，这样你就能抚摸[kay.her]柔软的耳朵和头发了。", KAY_OFFICE_DOMINATE_PETTING);
					
				} else if(index==2) {
					return new Response("享受按摩", "让[kay.name]帮你揉肩按背。", KAY_OFFICE_DOMINATE_MASSAGE);
					
				} else if(index==3 && Main.game.isFootContentEnabled()) {
					if(!Main.game.getPlayer().hasLegs()) {
						return new Response("享受足部按摩", "你没有脚能让[kay.name]按摩……", null);
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.FEET, true)) {
						return new Response("享受足部按摩", "你无法使用自己的[pc.feet]，[kay.name]不能给你足部按摩！", null);
					} else {
						return new Response("享受足部按摩", "让[kay.name]给你足部按摩。", KAY_OFFICE_DOMINATE_MASSAGE_FEET);
					}
					
				} else if(Main.game.isFootContentEnabled()?index==4:index==3) {
					return getKaySexResponse("操[kay.herHim]",
							"把[kay.name]揽进怀里，告诉[kay.herHim]你要准备干[kay.herHim]了。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_FUCK"),
							SexPosition.STANDING,
							SexSlotStanding.STANDING_SUBMISSIVE,
							null,
							SexSlotStanding.STANDING_DOMINANT,
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues());
				}
				
				// Toys and behaviour changes:
				if(index==6) {
					if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) {
						return new Response("贞操笼: [style.colourMinorBad(解除)]",
								"[kay.Name]没有戴着贞操笼。如果你想的话可以给[kay.her]戴上……"
										+ "<br/>[style.italicsMinorGood(你可以随时自由地给[kay.namePos]装上或摘下贞操笼。)]",
								KAY_OFFICE_DOMINATE_CAGE);
					} else {
						return new Response("贞操笼: [style.colourMinorGood(已装备)]",
								"[kay.Name]正戴着贞操笼。如果你想的话可以摘下来……"
									+ "<br/>[style.italicsMinorGood(你可以随时自由地给[kay.namePos]装上或摘下贞操笼。)]",
								KAY_OFFICE_DOMINATE_CAGE);
					}
					
				} else if(index==7 && buttplugActionAvailable) {
					if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
						return new Response("肛塞: [style.colourMinorBad(移除)]", "[kay.Name]现在没有塞着肛塞。如果你想的话可以给[kay.her]塞上……", KAY_OFFICE_DOMINATE_BUTTPLUG);
					} else {
						return new Response("肛塞: [style.colourMinorGood(插入)]", "[kay.Name]正塞着肛塞。如果你想的话可以拔下来……", KAY_OFFICE_DOMINATE_BUTTPLUG);
					}
					
				} else if(buttplugActionAvailable?index==8:index==7) {
					if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
						return new Response("衣物: [style.colourFeminine(女性化)]",
								"告诉[kay.name]重新换上之前的男性西服。"
										+ "<br/>[style.italicsMinorBad([kay.Name]同时也会改换发型，并且抹掉妆容。)]",
								KAY_OFFICE_DOMINATE_CLOTHING);
					} else {
						return new Response("衣物: [style.colourMasculine(男性化)]",
								"告诉[kay.name]应该换一件女性西服穿。",
								Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayPreviouslyFeminised)
									?KAY_OFFICE_DOMINATE_CLOTHING
									:KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME);
					}
					
				} else if(buttplugActionAvailable?index==9:index==8) {
					if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
						return new Response("化妆", "在你让[kay.Name]穿上女性化服装之前，[kay.she]不太愿意化妆！", null);
						
					} else if(!Main.game.getPlayer().hasItemType(ItemType.MAKEUP_SET)) {
						return new Response("化妆", "你需要"+ItemType.MAKEUP_SET.getDeterminer()+" "+ItemType.MAKEUP_SET.getName(false)+"才能这么做！", null);
						
					} else {
						return new Response("化妆", "让[kay.name]坐好在椅子上，你来帮[kay.her]修改发型和化妆。", KAY_OFFICE_DOMINATE_MAKEUP);
					}
					
				} else if(index==10) {
					return new Response("称呼", "选择[kay.name]对你的称呼。", KAY_OFFICE_DOMINATE_NAMING);
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_UTIL_EMPTY = new DialogueNode("", "", true, true) {
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
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_POST_SEX = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_POST_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_PETTING = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();

			responses.add(new Response("不摸了", "你抚摸[kay.name]已经摸够了，于是坐在[kay.her]的椅子上，思索起接下来的计划。", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_END"));
				}
			});
			
			if(isPlayerPenisFree()) {
				responses.add(getKaySexResponse("接受口交",
						Main.game.getPlayer().isTaur()
							?"站起来让[kay.name]跪在你的[pc.legRace]下肢之下，[kay.she]就可以舔你的肉棒了。"
							:"把[kay.namePos]的脑袋塞进你的股间，让[kay.herHim]舔你的鸡巴。",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_BLOWJOB"),
						Main.game.getPlayer().isTaur()
							?SexPosition.STANDING
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.PERFORMING_ORAL
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS),
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.STANDING_DOMINANT
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisMouth.BLOWJOB_START, false, true))));
				
			} else {
				responses.add(new Response("接受口交", "你需要有阴茎，并且能够使用，才能让[kay.name]给你口交。", null));
			}
			
			if(isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("接受舔阴",
						Main.game.getPlayer().isTaur()
							?"站起来让[kay.name]跪在你的[pc.legRace]下肢后面，[kay.she]就可以舔你的小穴了。"
							:"把[kay.namePos]的脑袋塞进你的股间，让[kay.herHim]给你舔阴。",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_CUNNILINGUS"),
						Main.game.getPlayer().isTaur()
							?SexPosition.STANDING
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.PERFORMING_ORAL_BEHIND
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotStanding.STANDING_DOMINANT
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true))));
				
				if(Main.game.getPlayer().isClitorisPseudoPenis()) {
					responses.add(getKaySexResponse("吮吸阴蒂",
							Main.game.getPlayer().isTaur()
								?"站起来让[kay.name]跪在你的[pc.legRace]下肢之后，[kay.she]就可以吮吸你[pc.clitSize]的阴蒂了。"
								:"把[kay.namePos]的脑袋塞进你的股间，让[kay.herHim]舔你[pc.clitSize]的阴核。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_PETTING_CLIT_SUCK"),
							Main.game.getPlayer().isTaur()
								?SexPosition.STANDING
								:SexPosition.SITTING,
							Main.game.getPlayer().isTaur()
								?SexSlotStanding.PERFORMING_ORAL
								:SexSlotSitting.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.CLIT),
							Main.game.getPlayer().isTaur()
								?SexSlotStanding.STANDING_DOMINANT
								:SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(CoverableArea.VAGINA),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), ClitMouth.CLIT_ORAL_START, false, true))));
				} else {
					responses.add(new Response("吮吸阴核", "的你阴核的大小至少需要为“"+ClitorisSize.getMinimumClitorisSizeForPseudoPenis()+"”才能让[kay.name]给你吸。", null));
				}
				
			} else {
				responses.add(new Response("接受舔阴", "你需要有阴道，并且能够使用，才能让[kay.name]给你舔阴。", null));
				responses.add(new Response("吮吸阴核", "你需要拥有阴道，以及大小至少为“"+ClitorisSize.getMinimumClitorisSizeForPseudoPenis()+"”的阴核，并且能够使用，才能让[kay.name]给你吸。", null));
			}
			
			for(int i=0;i<responses.size();i++) {
				if(i==index-1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_MASSAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+15)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 15*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();

			responses.add(new Response("停止按摩", "告诉[kay.name]做得很棒，但是你已经享受够了。", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
				@Override
				public void effects() {
					Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_END"));
				}
			});
			
			if(isPlayerPenisFree() || isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("下体“按摩”",
						"告诉[kay.name]接下来专注在胯部，"
						+(isPlayerPenisFree() && isPlayerVaginaFree()
							?"一边给你手淫一边给你指交。"
							:(isPlayerPenisFree()
								?"给你手淫。"
								:"给你指交。")),
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_GROIN"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.BEHIND
							:SexSlotDesk.BETWEEN_LEGS,
						isPlayerPenisFree()
							?new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaPenetration.PENIS)
							:new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(),
						Util.newArrayListOfValues(CoverableArea.PENIS, CoverableArea.VAGINA),
						Util.newArrayListOfValues(
								(isPlayerPenisFree()
									?new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), FingerPenis.COCK_MASTURBATING_START, false, true)
									:null),
								(isPlayerVaginaFree()
									?new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), FingerVagina.FINGERING_START, false, true)
									:null))));
				
			} else {
				responses.add(new Response("下体“按摩”", "你需要拥有阴茎或者阴道，并且能够使用，才能接受[kay.name]的下体“按摩”。", null));
			}

			if(isPlayerVaginaFree()) {
				responses.add(getKaySexResponse("舔阴",
						"让[kay.name]跪在你身后给你舔阴。",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_CUNNILINGUS"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.BEHIND_ORAL
							:SexSlotDesk.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START, false, true))));
				
			} else {
				responses.add(new Response("舔阴", "你需要拥有阴道，并且能够使用，才能让[kay.name]给你舔阴。", null));
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(isPlayerAssFree()) {
					responses.add(getKaySexResponse("吻肛",
							"让[kay.name]跪在你身后给你吻肛。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_ANILINGUS"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.OVER_DESK,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.BEHIND_ORAL
								:SexSlotDesk.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotDesk.OVER_DESK_ON_FRONT,
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), TongueAnus.ANILINGUS_START, false, true))));
					
				} else {
					responses.add(new Response("吻肛", "你需要能够使用肛门，才能让[kay.name]给你吻肛。", null));
				}
			}
			
			if(!isPlayerVaginaFree()) {
				responses.add(new Response("挨操", "你需要拥有阴道，并且能够使用，才能让[kay.name]操你。", null));
				
			} else if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
				responses.add(new Response("挨操", "[kay.namePos]的鸡巴依然被锁在贞操笼里，所以不能操你！", null));
				
			} else {
				responses.add(getKaySexResponse("挨操",
						"让[kay.name]去到你身后操你。",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_VAGINAL"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.OVER_DESK,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.HUMPING
							:SexSlotDesk.HUMPING,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotDesk.OVER_DESK_ON_FRONT,
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(CoverableArea.VAGINA),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true)),
						Util.newHashMapOfValues(
								new Value<>(
										Main.game.getPlayer(),
										Util.newHashMapOfValues(
												new Value<>(
													SexAreaOrifice.VAGINA,
													Util.newHashMapOfValues(
														new Value<>(
															Main.game.getNpc(Kay.class),
															Util.newHashSetOfValues(LubricationType.PRECUM)))))))));
				
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!isPlayerAssFree()) {
					responses.add(new Response("挨操(肛交)", "你需要能够使用肛门，才能让[kay.name]给你肛交。", null));
					
				} else if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					responses.add(new Response("挨操(肛交)", "[kay.namePos]的鸡巴依然被锁在贞操笼里，所以不能操你的屁股！", null));
					
				} else {
					responses.add(getKaySexResponse("挨操(肛交)",
							"让[kay.name]去到你身后操你的屁股。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_ANAL"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.OVER_DESK,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.HUMPING
								:SexSlotDesk.HUMPING,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotDesk.OVER_DESK_ON_FRONT,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true)),
							Util.newHashMapOfValues(
									new Value<>(
											Main.game.getPlayer(),
											Util.newHashMapOfValues(
													new Value<>(
														SexAreaOrifice.ANUS,
														Util.newHashMapOfValues(
															new Value<>(
																Main.game.getNpc(Kay.class),
																Util.newHashSetOfValues(LubricationType.PRECUM)))))))));
					
				}
			}
			
			for(int i=0;i<responses.size();i++) {
				if(i==index-1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_MASSAGE_FEET = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().addStatusEffect(StatusEffect.CLEANED_MASSAGED, (240+15)*60);
		}
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("停止按摩", "告诉[kay.name]做得很棒，但是你已经享受够了。", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_END"));
					}
				};
				
			} else if(index==2) {
				return getKaySexResponse("吻[pc.Foot(true)]",
						"让[kay.name]用嘴巴侍奉你[pc.feet+(true)]。",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_FOOT_WORSHIP"),
						Main.game.getPlayer().isTaur()
							?SexPosition.ALL_FOURS
							:SexPosition.SITTING,
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.USING_FEET
							:SexSlotSitting.PERFORMING_ORAL,
						new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.FOOT),
						Main.game.getPlayer().isTaur()
							?SexSlotAllFours.ALL_FOURS
							:SexSlotSitting.SITTING,
						Util.newArrayListOfValues(CoverableArea.MOUTH),
						Util.newArrayListOfValues(CoverableArea.FEET),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), FootMouth.FOOT_ORAL_RECEIVING_START, false, true)));
				
			} else if(index==3) {
				if(!Main.game.getNpc(Kay.class).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
					return new Response("[pc.Footjob]", "[kay.namePos]的鸡巴依然被锁在贞操笼里，所以不能让你[pc.footjob]！", null);
					
				} else {
					return getKaySexResponse("[pc.Footjob]",
							"让[kay.name]把肉棒在你面前露出来，奖励[kay.herHim]一次[pc.footjob]。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MASSAGE_FEET_FOOTJOB"),
							Main.game.getPlayer().isTaur()
								?SexPosition.ALL_FOURS
								:SexPosition.SITTING,
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.USING_FEET
								:SexSlotSitting.PERFORMING_ORAL,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FOOT),
							Main.game.getPlayer().isTaur()
								?SexSlotAllFours.ALL_FOURS
								:SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.FEET),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisFeet.FOOT_JOB_DOUBLE_GIVING_START, false, true)));
					
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CAGE = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).equipClothingFromNowhere(
								Main.game.getItemGen().generateClothing("innoxia_bdsm_chastity_cage", PresetColour.CLOTHING_WHITE, PresetColour.CLOTHING_BLUE_LIGHT, PresetColour.CLOTHING_STEEL, false), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_EQUIP"));
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_UNEQUIP"));
				Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS).setSealed(false);
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).unequipClothingIntoVoid(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_UNEQUIP_END"));
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
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.PENIS)==null) { // Taken cage off:
				List<Response> responses = new ArrayList<>();

				responses.add(new Response("坐下", "坐下来让[kay.name]提上裤子。", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_SIT_BACK"));
					}
				});
				
				if(!Main.game.getPlayer().isTaur()) {
					responses.add(getKaySexResponse("坐在腿上",
							"让[kay.name]做到你的腿上，告诉[kay.herHim]你要准备干[kay.herHim]了。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_LAP"),
							SexPosition.SITTING,
							SexSlotSitting.SITTING_IN_LAP,
							null,
							SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				} else {
					responses.add(new Response("坐在腿上", "你的下半身是[pc.a_legRace]，所以不能让[kay.name]坐在你的腿上！", null));
				}

				if(Main.game.isAnalContentEnabled()) {
					responses.add(getKaySexResponse("推倒在桌上",
							"把[kay.name]推倒在桌子上，告诉[kay.herHim]你要准备干[kay.herHim]了。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_DESK"),
							SexPosition.OVER_DESK,
							SexSlotDesk.OVER_DESK_ON_FRONT,
							null,
							SexSlotDesk.BETWEEN_LEGS,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				}

				responses.add(getKaySexResponse("手交",
						"奖励一下[kay.name]，握住[kay.her]的肉棒撸动起来。",
						KAY_OFFICE_DOMINATE_POST_SEX,
						UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_HANDJOB"),
						SexPosition.STANDING,
						SexSlotStanding.STANDING_DOMINANT,
						new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaPenetration.FINGER),
						SexSlotStanding.PERFORMING_ORAL,
						Util.newArrayListOfValues(CoverableArea.PENIS),
						Util.newArrayListOfValues(),
						Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), FingerPenis.COCK_MASTURBATING_START, false, true))));

				if(isPlayerMouthFree()) {
					responses.add(getKaySexResponse("舔肉棒",
							"奖励一下[kay.name]，俯下身去舔[kay.her]的肉棒。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_BLOWJOB"),
							SexPosition.STANDING,
							SexSlotStanding.STANDING_DOMINANT,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH),
							SexSlotStanding.PERFORMING_ORAL,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.MOUTH),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisMouth.GIVING_BLOWJOB_START, false, true))));
					
				} else {
					responses.add(new Response("舔肉棒", "你无法使用嘴巴，所以不能舔[kay.namePos]的肉棒。", null));
				}

				if(!isPlayerVaginaFree()) {
					responses.add(new Response("骑背", "你需要拥有阴道，并且能够使用，才能让[kay.name]操你。", null));
					
				} else {
					responses.add(getKaySexResponse("骑背",
							"将下体展露在[kay.name]面前，让[kay.herHim]插入你的小穴。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_VAGINAL"),
							SexPosition.ALL_FOURS,
							SexSlotAllFours.HUMPING,
							new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA),
							SexSlotAllFours.ALL_FOURS,
							Util.newArrayListOfValues(CoverableArea.PENIS),
							Util.newArrayListOfValues(CoverableArea.VAGINA),
							Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true))));
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(!isPlayerAssFree()) {
						responses.add(new Response("骑背(肛交)", "你需要能够使用肛门，才能让[kay.name]给你肛交。", null));
						
					} else {
						responses.add(getKaySexResponse("骑背(肛交)",
								"将下体展露在[kay.name]面前，让[kay.herHim]插入你的屁股。",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CAGE_ANAL"),
								SexPosition.ALL_FOURS,
								SexSlotAllFours.HUMPING,
								new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS),
								SexSlotAllFours.ALL_FOURS,
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Kay.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true))));
					}
				}

				for(int i=0;i<responses.size();i++) {
					if(i==index-1) {
						return responses.get(i);
					}
				}
				
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_BUTTPLUG = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).equipClothingFromNowhere(
								Main.game.getItemGen().generateClothing("innoxia_buttPlugs_butt_plug_jewel", PresetColour.CLOTHING_SILVER, PresetColour.CLOTHING_BLUE_LIGHT, null, false), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_EQUIP"));
				
			} else {
				Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS).setSealed(false); // Just in case
				UtilText.addSpecialParsingString(
						Main.game.getNpc(Kay.class).unequipClothingIntoVoid(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS), true, Main.game.getPlayer()),
						true);
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_UNEQUIP"));
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
			if(Main.game.getNpc(Kay.class).getClothingInSlot(InventorySlot.ANUS)==null) {
				List<Response> responses = new ArrayList<>();

				responses.add(new Response("坐下", "坐下来让[kay.name]提上裤子。", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_SIT_BACK"));
					}
				});
				
				if(!Main.game.getPlayer().isTaur()) {
					responses.add(getKaySexResponse("坐在腿上",
							"让[kay.name]做到你的腿上，告诉[kay.herHim]你要准备干[kay.herHim]了。",
							KAY_OFFICE_DOMINATE_POST_SEX,
							UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_LAP"),
							SexPosition.SITTING,
							SexSlotSitting.SITTING_IN_LAP,
							null,
							SexSlotSitting.SITTING,
							Util.newArrayListOfValues(CoverableArea.ANUS),
							Util.newArrayListOfValues(),
							Util.newArrayListOfValues()));
				} else {
					responses.add(new Response("坐在腿上", "你的下半身是[pc.a_legRace]，所以不能让[kay.name]坐在你的腿上！", null));
				}
				
				if(Main.game.isAnalContentEnabled()) {
					if(isPlayerMouthFree()) {
						responses.add(getKaySexResponse("桌上吻肛",
								"把[kay.name]推倒在桌子上，开始舔[kay.her]的屁股。",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_DESK_ANILINGUS"),
								SexPosition.OVER_DESK,
								SexSlotDesk.OVER_DESK_ON_FRONT,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE),
								SexSlotDesk.PERFORMING_ORAL,
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.MOUTH),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), TongueAnus.ANILINGUS_START, false, true))));
						
					} else {
						responses.add(new Response("桌上吻肛", "你无法使用自己的嘴巴，不能给[kay.name]吻肛！", null));
					}
					
					if(isPlayerPenisFree()) {
						responses.add(getKaySexResponse("桌上肛交",
								"把[kay.name]推倒在桌子上，开始干[kay.her]的屁股。",
								KAY_OFFICE_DOMINATE_POST_SEX,
								UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_BUTTPLUG_DESK_FUCK"),
								SexPosition.OVER_DESK,
								SexSlotDesk.OVER_DESK_ON_FRONT,
								new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS),
								SexSlotDesk.BETWEEN_LEGS,
								Util.newArrayListOfValues(CoverableArea.ANUS),
								Util.newArrayListOfValues(CoverableArea.PENIS),
								Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Kay.class), PenisAnus.PENIS_FUCKING_START, false, true))));
						
					} else {
						responses.add(new Response("桌上性交", "你需要拥有阴茎，并且能够使用，才能跟[kay.name]肛交。", null));
					}
				}

				for(int i=0;i<responses.size();i++) {
					if(i==index-1) {
						return responses.get(i);
					}
				}
				
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 20*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_FIRST_TIME");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "告诉凯可以换上“她”的新衣服了……", KAY_OFFICE_DOMINATE_CLOTHING);
				
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_CLOTHING = new DialogueNode("", "", true) {
		@Override
		public boolean isContinuesDialogue() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayPreviouslyFeminised);
		}
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_MASCULINE"));
				Main.game.getNpc(Kay.class).removeAllMakeup();
				Main.game.getNpc(Kay.class).setName(new NameTriplet("杰克", "杰克", "杰姬"));
				Main.game.getNpc(Kay.class).setHairStyle(HairStyle.LOOSE);
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFeminised, false);
				Main.game.getNpc(Kay.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
				
			} else {
				Main.game.getTextEndStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_FEMININE"));
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayFeminised, true);
				Main.game.getNpc(Kay.class).equipClothing(EquipClothingSetting.getAllClothingSettings());
			}
			Main.game.getNpc(Kay.class).loadImages(true); // reload images to use correct artwork
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
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.kayFeminised)) {
				if(index==1) {
					return new Response("凯", "继续用姓氏喊这个可爱的猫女，说她现在漂亮极了。", KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("杰克", "杰克", "杰姬"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==2) {
					return new Response("杰姬",
							"说这个可爱[kay.race]看上去漂亮极了，“杰姬”这个名字更适合[kay.herHim]的外表。"
							+ "<br/>[style.colourFeminine(凯会被重新命名为“杰姬”，直到你让[kay.herHim]换回男性服装。)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("杰姬", "杰姬", "杰姬"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_JACKIE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==3) {
					return new Response("凯蒂",
							"说这个可爱的[kay.race]看上去漂亮极了，“凯蒂”这个名字更适合[kay.herHim]的外表。"
							+ "<br/>[style.colourFeminine(凯会被重新命名为“凯蒂”，直到你让[kay.herHim]换回男性服装。)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("凯蒂", "凯蒂", "凯蒂"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_KAYTIE"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				} else if(index==4) {
					return new Response("米凯菈",
							"说这个可爱的[kay.race]看上去漂亮极了，“米凯菈”这个名字更适合[kay.herHim]的外表。"
							+ "<br/>[style.colourFeminine(凯会被重新命名为“米凯菈”，直到你让[kay.herHim]换回男性服装。)]",
							KAY_OFFICE_DOMINATE_UTIL_EMPTY) {
						@Override
						public void effects() {
							Main.game.getNpc(Kay.class).setName(new NameTriplet("米凯拉", "米凯拉", "米凯拉"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_START"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MID"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_MIKAYLA"));
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_CLOTHING_NAME_END"));
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.kayPreviouslyFeminised, true);
						}
					};
					
				}
				return null;
				
			} else {
				return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
			}
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_MAKEUP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			BodyChanging.setTarget( Main.game.getNpc(Kay.class));
		}
		@Override
		public String getHeaderContent() {
			StringBuilder sb = new StringBuilder();

			sb.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MAKEUP"));
					
			sb.append(CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "改变[npc.namePos]的发型。"))
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
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
			
			return sb.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("结束", "你已经给[kay.name]化完妆了。", KAY_OFFICE_DOMINATE_MAKEUP_END);
			}
			return null;
		}
	};

	public static final DialogueNode KAY_OFFICE_DOMINATE_MAKEUP_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_MAKEUP_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_NAMING = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			NPC kay = Main.game.getNpc(Kay.class);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NAMING"));
			
			UtilText.nodeContentSB.append(UtilText.parse(kay,
					"<p>"
						+ "从现在开始，[npc.nameIsFull]将称呼你为“[npc.pcName]”，你不禁思考起该不该让[npc.herHim]换个说法称呼你。"
						+ "[npc.sheIs]不是你的奴隶，你不能让[npc.herHim]改变名字。"
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
						+ UtilText.parse(kay, "[npc.she]对你的称呼")
					+ "</div>"
					
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(kay.getName(false))+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
						
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(kay.getSurname())+ "' style='width:100%; margin:0; padding:0;' disabled></form>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button disabled' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
					
					+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(kay.getPetName(Main.game.getPlayer()))
						+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+kay.getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button disabled' style='float:left; width:12%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "全体奴隶"
					+ "</div>");
			
			UtilText.nodeContentSB.append(UtilText.parse(kay,
						"<p style='text-align:center; margin-top:4px;'>"
							+ "<i>如果[npc.name]被告知称呼你为“爸爸”/“妈妈”、“爹地”/“妈咪”、“女主人”/“男主人”或“女士”/“先生”，"
							+ "那么[npc.she]将根据你角色的女性化程度自动切换合适的称呼。</i>"
						+ "</p>"
					+ "</div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("结束", "你结束了[kay.name]对你称呼的选择。", KAY_OFFICE_DOMINATE_NAMING_END);
			}
			return null;
		}
	};
	
	public static final DialogueNode KAY_OFFICE_DOMINATE_NAMING_END = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/warehouseDistrict/kaysTextiles", "KAY_OFFICE_DOMINATE_NAMING_END");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return KAY_OFFICE_DOMINATE.getResponse(responseTab, index);
		}
	};
	
}
