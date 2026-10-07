package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Zaranix;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloor;
import com.lilithsthrone.game.dialogue.places.dominion.zaranixHome.ZaranixHomeGroundFloorRepeat;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class ZaranixDialogue {

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("胜利", "", true) {
		@Override
		public String getDescription() {
			return "[zaranix.Name]一败涂地，[zaranix.she]疲惫地叹息一声，踉跄着退后，向你投降了……";
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("离开", "离开扎拉尼克斯家。", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("使用扎拉尼克斯", "跟这只淫梦魔做点有意思的事。",
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Zaranix.class)),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY_USE_HIM"));
				
			} else if(index==3) {
				return new ResponseSex("顺从",
						"让扎拉尼克斯操你",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.THREE_DIRTY, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getNpc(Zaranix.class)),
								Util.newArrayListOfValues(Main.game.getPlayer()),
						null,
						null),
						AFTER_SEX_VICTORY,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_VICTORY_SUBMIT"));
				
			} else if (index == 4) {
				return new Response("转化",
						"让[zaranix.her]使用恶魔之力转化自己……",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getNpc(Zaranix.class));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_VICTORY = new DialogueNode("继续", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_SEX_VICTORY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("继续", "继续你的旅程。", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloorRepeat.resetHouseAfterLeaving();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("被击败", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
				if(index==1) {
					return new Response("被扔出去", "由于没法使用你的嘴巴，扎拉尼克斯把你扔到了街上。", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_NO_MOUTH"));
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
							ZaranixHomeGroundFloor.resetHouseAfterLeaving();
						}
					};
				}
				
			} else if(!Main.game.isNonConEnabled()) {
				if(index==1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("拒绝",
								"由于你<b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b>的性癖，你乐于被转化，所以无法拒绝转化液体！",
								null);
					} else {
						return new Response("拒绝", "拒绝喝下转化药剂。这会让扎拉尼克斯把你扔到街上。", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_REFUSE"));
								Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
								ZaranixHomeGroundFloor.resetHouseAfterLeaving();
							}
						};
					}
					
				} else if(index==2) {
					return new Response("喝", "同意喝下转化药水。", AFTER_COMBAT_DEFEAT_SWALLOW);
				}
				
			} else {
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
						return new Response("吐出", "吐掉药水。", AFTER_COMBAT_DEFEAT_SPIT);
					}
					
				} else if(index==2) {
					return new Response("吞下", "吞下药水。", AFTER_COMBAT_DEFEAT_SWALLOW);
				}
			}
			
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SPIT = new DialogueNode("落败", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixTransformedPlayer, false);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("服从", "扎拉尼克斯强迫你给他口交。",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("急于服从", "扎拉尼克斯强迫你给他口。",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START_EAGER")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else if(index==3) {
				return new ResponseSex("抵抗", "扎拉尼克斯强迫你给他口交。",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SPIT_SEX_START_RESIST")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getNpc(Zaranix.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SWALLOW = new DialogueNode("落败", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.zaranixTransformedPlayer, true);
		}
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("需要……鸡巴", "扎拉尼克斯的药剂效果十分强大……你现在想舔他的鸡巴想得不得了，或许你还能清醒过来？",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
			}
			if(!Main.game.getPlayer().getSexualOrientation().isAttractedToMasculine()) {
				if(index==2) {
					return new Response("女性形态？",
							"你很想舔扎拉尼克斯的肉棒，但你对于男性又并无兴趣。或许你可以恳求对方，能让他大发慈悲，为了你转化成魅魔？",
							AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF);
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF = new DialogueNode("被使用", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF_START"));
			((Zaranix)Main.game.getNpc(Zaranix.class)).transformFeminine();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_ZARANIX_TF_END"));
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
			if(index==1) {
				return new ResponseSex("需要……鸡巴", "你如愿后，满脑子就只剩下舔扎拉尼克斯肉棒一件事了……",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Zaranix.class), SexSlotSitting.SITTING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL))),
						null,
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_COMBAT_DEFEAT_SWALLOW_START")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), Main.game.getNpc(Zaranix.class), PenisMouth.GIVING_BLOWJOB_START, false, true));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_SEX_DEFEAT = new DialogueNode("被使用", "扎拉尼克斯体验够了你的口技……", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/zaranix", "AFTER_SEX_DEFEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("被扔出去", "扎拉尼克斯把你扔到了街上。", PlaceType.DOMINION_DEMON_HOME_ZARANIX.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME_ZARANIX, false);
						ZaranixHomeGroundFloor.resetHouseAfterLeaving();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
