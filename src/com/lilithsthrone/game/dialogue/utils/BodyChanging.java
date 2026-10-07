package com.lilithsthrone.game.dialogue.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.lilithsthrone.LolificationProject.Lexi.TussyDisplay;
import com.lilithsthrone.LolificationProject.ModCommon.Menus;
import org.w3c.dom.Document;
import com.lilithsthrone.threading.DocBuilders;import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractBreastType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractEyeType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFaceType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractHornType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractPenisType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTorsoType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractVaginaType;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractWingType;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.90
 * @version 0.3.9.1
 * @author Innoxia
 */
public class BodyChanging {
	
	private static GameCharacter target;
	private static DialogueNode coreNode;
	private static int defaultResponseTab;
	private static boolean debugMenu;
	
	public static boolean isDebugMenu() {
		return debugMenu;
	}

	public static GameCharacter getTarget() {
		if(target==null) {
			return Main.game.getPlayer();
		}
		return target;
	}

	public static void setTarget(GameCharacter target) {
		BodyChanging.target = target;
		BodyChanging.coreNode = null;
		BodyChanging.debugMenu = false;
	}

	public static void setTarget(GameCharacter target, DialogueNode coreNode, int defaultResponseTab) {
		BodyChanging.target = target;
		BodyChanging.coreNode = coreNode;
		BodyChanging.defaultResponseTab = defaultResponseTab;
		BodyChanging.debugMenu = false;
	}
	
	/**
	 * @param target Character being changed
	 * @param debugMenu If this menu is accessed from the debug menu.
	 */
	public static void setTarget(GameCharacter target, boolean debugMenu) {
		BodyChanging.target = target;
		BodyChanging.coreNode = null;
		BodyChanging.debugMenu = debugMenu;
	}
	
	public static Response getBodyChangingResponse(int responseTab, int index) {
		if (index == 0) {
			if (isDebugMenu()) {
				return new Response("返回", "回到上一界面。", DebugDialogue.DEBUG_MENU);

			} else if (coreNode != null) {
				return new Response("返回", "回到上一界面。", coreNode) {
					@Override
					public void effects() {
						Main.game.setResponseTab(defaultResponseTab);
					}
				};

			} else {
				return new ResponseEffectsOnly("返回", "返回上一界面。") {
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
			}
		}

		if (responseTab==0) {
			if (index == 1) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_CORE) {
					return new Response("躯体", "你已在此界面中！", null);
				}
				return new Response("躯体",
						UtilText.parse(getTarget(), "改变[npc.namePos]身体的核心部分。"),
						BODY_CHANGING_CORE);

			} else if (index == 2) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_EYES) {
					return new Response("眼部", "你已处于该界面！", null);
				}
				return new Response("眼睛",
						UtilText.parse(getTarget(), "改变[npc.namePos]眼睛"),
						BODY_CHANGING_EYES);

			} else if (index == 3) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_HAIR) {
					return new Response("头发", "你已处于此界面！", null);
				}
				return new Response("头发",
						UtilText.parse(getTarget(), "改变[npc.namePos]头发。"),
						BODY_CHANGING_HAIR);

			} else if (index == 4) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_HEAD) {
					return new Response("头部", "你已处于此界面！", null);
				}
				return new Response("头部",
						UtilText.parse(getTarget(), "改变[npc.namePos]面容和头部。"),
						BODY_CHANGING_HEAD);

			} else if (index == 5) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_ASS) {
					return new Response("屁股", "你已处于此界面！", null);
				}
				return new Response("屁股",
						UtilText.parse(getTarget(), "改变[npc.namePos]屁股。"),
						BODY_CHANGING_ASS);

			} else if (index == 6) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_BREASTS) {
					return new Response("胸部", "你已处于此界面！", null);
				} else if (!getTarget().hasNipples()) {
					return new Response("胸部",
							UtilText.parse(getTarget(), "[npc.Name]没有乳房！"),
							null);
				}
				return new Response("胸部",
						UtilText.parse(getTarget(), "改变[npc.namePos]胸部。"),
						BODY_CHANGING_BREASTS);

			} else if (index == 7) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_VAGINA) {
					return new Response("阴道", "你已处于此界面！", null);
				}
				return new Response("阴道",
						UtilText.parse(getTarget(), "改变[npc.namePos]阴道。"),
						BODY_CHANGING_VAGINA);

			} else if (index == 8) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_PENIS) {
					return new Response("阴茎", "你已处于此界面！", null);
				}
				return new Response("阴茎",
						UtilText.parse(getTarget(), "改变[npc.namePos]阴茎。"),
						BODY_CHANGING_PENIS);

			} else if (index == 9) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_SPINNERET) {
					return new Response("丝囊", "你已处于此界面！", null);
				} else if (!getTarget().hasSpinneret()) {
					return new Response("丝囊",
							UtilText.parse(getTarget(), "[npc.Name]没有丝囊！<br/><i>丝囊只会在特定的尾巴或腿部类型中出现。</i>"),
							null);
				}
				return new Response("丝囊",
						UtilText.parse(getTarget(), "改变[npc.namePos]吐丝器。"),
						BODY_CHANGING_SPINNERET);

			} else if (index == 10) {
				String title = getTarget().getBreastCrotchShape() == BreastShape.UDDERS?"腹乳":"胯乳";
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_BREASTS_CROTCH) {
					return new Response(title, "你已处于此界面！", null);
				}
				return new Response(title,
						UtilText.parse(getTarget(), "改变[npc.namePos][npc.crotchBoobs]。"),
						BODY_CHANGING_BREASTS_CROTCH);

			} else if (index == 11) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_SAVE_LOAD) {
					return new Response("保存/加载", "你已处于此界面！", null);
				}
				return new Response("保存/加载",
						UtilText.parse(getTarget(), "保存或加载变形预设，让你迅速调整自己的外观。"),
						BODY_CHANGING_SAVE_LOAD) {
					@Override
					public void effects() {
						initSaveLoadMenu();
					}
				};

			} else if (index == 12 && isDebugMenu()) {
				if (Main.game.getCurrentDialogueNode() == BODY_CHANGING_MAKEUP) {
					return new Response("妆容(debug)", "你已处于此界面！", null);
				}
				return new Response("妆容(debug)",
						UtilText.parse(getTarget(), "改变[npc.namePos]妆容。(该转化菜单只出现在Debug界面。)"),
						BODY_CHANGING_MAKEUP);

			} else if (index == 13) {
				if (Main.game.getCurrentDialogueNode() == Menus.BODY_CHANGING_TUSSY) {
					return new Response("Tussy", "You are already in this screen!", null);
				} else if (!BodyChanging.getTarget().hasTussy()) {
					return new Response("Tussy",
							UtilText.parse(getTarget(), "[npc.Name] [npc.do] not have a tussy!<br/><i>A tussy is gained via certain tail types.</i>"),
							null);
				}
				return new Response("Tussy",
						UtilText.parse(getTarget(), "Change aspects of [npc.namePos] tussy."),
						Menus.BODY_CHANGING_TUSSY);
				
			} else {
				return null;
			}
		} else if (responseTab == 1 && isDebugMenu()) {
			if (index == 1) {
				if (getTarget() == Main.game.getPlayer()) {
					return new Response(Main.game.getPlayer().getName(), "你已是当前目标。", null);
				} else {
					return new Response(Main.game.getPlayer().getName(), "以自己为目标。", BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							setTarget(Main.game.getPlayer(), isDebugMenu());
						}
					};
				}
			} else {
				index-=2;
			}

			if (index >= Main.game.getCharactersPresent().size() || index<0) {
				return null;
			}
			GameCharacter gc = Main.game.getCharactersPresent().get(index);
			if (!gc.isUnique()) {
				if (getTarget() == gc) {
					return new Response(gc.getName(), gc.getName()+"已是当前目标。", null);
				} else {
					return new Response(gc.getName(), "将目标改为"+gc.getName(), BODY_CHANGING_CORE) {
						@Override
						public void effects() {
							setTarget(gc, isDebugMenu());
						}
					};
				}
			}
		}
		return null;
	}

	private static String getBodyChangingTabTitle(int index) {
		if (!isDebugMenu()) {
			return null;
		}
		if (index == 0) {
			return "转化";
		} else if (index == 1) {
			return "目标";
		}
		return null;
	}

	private static final List<AbstractRace> allRaces = new ArrayList<>(Race.getAllRaces());

	private static List<AbstractRace> getTFRaces(AbstractRace forceAllowedRace) {
		return getTFRaces(Util.newArrayListOfValues(forceAllowedRace), false, false, false);
	}

	private static List<AbstractRace> getTFRaces(AbstractRace forceAllowedRace, boolean forceDemon, boolean allowHDDemon, boolean allowHDHuman) {
		return getTFRaces(Util.newArrayListOfValues(forceAllowedRace), forceDemon, allowHDDemon, allowHDHuman);
	}
	
	private static List<AbstractRace> getTFRaces(List<AbstractRace> forceAllowedRaces, boolean forceDemon, boolean allowHDDemon, boolean allowHDHuman) {
		List<AbstractRace> allowedRaces = Util.newArrayListOfValues();
		if (forceAllowedRaces != null) {
			allowedRaces.addAll(forceAllowedRaces);
		}
		GameCharacter target = getTarget();
		
		if (isDebugMenu()) {
			return allRaces;
		} else if (target.isDoll()) {
			return Util.newArrayListOfValues(Race.NONE, target.getFleshSubspecies().getRace());
		} else if(target.isYouko()) {
			allowedRaces.add(Race.FOX_MORPH);
			allowedRaces.add(Race.HUMAN);
		} else if(isSlimeTFMenu() || target.isElemental()) {
			for (AbstractRace race : allRaces) {
				if (race!=Race.NONE
						&& Main.getProperties().isAdvancedRaceKnowledgeDiscovered(AbstractSubspecies.getMainSubspeciesOfRace(race))
//						&& Main.getProperties().isRaceDiscovered(AbstractSubspecies.getMainSubspeciesOfRace(race))
						) {
					allowedRaces.add(race);
				}
			}
		} else if (isHalfDemon()) {
			if (forceDemon) {
				return Util.newArrayListOfValues(Race.DEMON);
			}
			allowedRaces.add(target.getHalfDemonSubspecies().getRace());
			if (allowHDDemon) {
				allowedRaces.add(Race.DEMON);
			}
			if (allowHDHuman) {
				allowedRaces.add(Race.HUMAN);
			}
		} else if (ScarlettsShop.isSlaveCustomisationMenu()) {
			for (AbstractRace race : allRaces) {
				if(!race.isAbleToSelfTransform()
						&& (Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false).keySet().stream().anyMatch(s->s.getRace()==race)
						|| Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false).keySet().stream().anyMatch(s->s.getRace()==race))) {
					allowedRaces.add(race);
				}
			}
		} else if (isDemonTFMenu()) {
			allowedRaces.add(Race.DEMON);
			for (AbstractRace race : allRaces) {
				if (race == Race.HUMAN) {
					if(target.hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4_DEMON)) {
						allowedRaces.add(race);
					}
					continue;
				}
				switch (race.getRacialClass()) {
					case MAMMAL:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LUNETTE_5_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case BIRD:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LYXIAS_6_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case REPTILE:
					case AMPHIBIAN:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LISOPHIA_7_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case FISH:
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LIRECEA_1_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case INSECT:
						// Inno you overlooked this one...
						if (target.hasPerkAnywhereInTree(Perk.POWER_OF_LOVIENNE_2_DEMON)) {
							allowedRaces.add(race);
						}
						break;
					case OTHER:
						// Elementals, Slimes and Dolls
						break;
				}
			}
		}
		allowedRaces.add(Race.NONE);
		
		return allowedRaces;
	}
	
	private static boolean removeNoneFromTailChoices() {
		if(isHalfDemon() && !(getTarget().isElemental())) {
			return !RacialBody.valueOfRace(getTarget().getHalfDemonSubspecies().getRace()).getTailType().contains(TailType.NONE);
		}
		return false;
	}
	
	private static boolean removeNoneFromWingChoices() {
		if(isHalfDemon() && !(getTarget().isElemental())) {
			return !RacialBody.valueOfRace(getTarget().getHalfDemonSubspecies().getRace()).getWingTypes().contains(WingType.NONE);
		}
		return false;
	}
	
	private static boolean isDemonTFMenu() {
		return !isDebugMenu()
				&& (getTarget().getSubspeciesOverride()==Subspecies.IMP
					|| getTarget().getSubspeciesOverride()==Subspecies.IMP_ALPHA
					|| getTarget().getSubspeciesOverride()==Subspecies.HALF_DEMON
					|| getTarget().getSubspeciesOverride()==Subspecies.DEMON
					|| getTarget().getSubspeciesOverride()==Subspecies.LILIN
					|| getTarget().getSubspeciesOverride()==Subspecies.ELDER_LILIN);
	}

	private static boolean isSelfTFMenu() {
		return !isDebugMenu()
				&& !isDemonTFMenu()
				&& getTarget().getBodyMaterial()!=BodyMaterial.SLIME
				&& getTarget().getTrueSubspecies().isAbleToSelfTransform();
	}

	private static boolean isSlimeTFMenu() {
		return !isDebugMenu()
//				&& !isDemonTFMenu()
				&& !isSelfTFMenu()
				&& getTarget().getBodyMaterial()==BodyMaterial.SLIME;
	}

	private static boolean isHalfDemon() {
		return getTarget().getSubspeciesOverride()==Subspecies.HALF_DEMON;
	}
	
	private static boolean isNonHumanHalfDemon() {
		return getTarget().getHalfDemonSubspecies().getRace() == Race.HUMAN;
	}

	public static String getSelfTransformDescription(String area) {
		if(ScarlettsShop.isSlaveCustomisationMenu()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("<div class='container-full-width' style='text-align:center;'>");
			if(isDemonTFMenu()) {
				sb.append("<i>[npc.Name]可以控制[npc.her]的恶魔之力来自我转化[npc.her]的"+area+"。</i>");

			} else if(isSelfTFMenu()) {
				sb.append("<i>[npc.Name]可以利用[npc.her]的先天能力来改变[npc.her]的"+area+"。</i>");
				if(target.isElemental()) {
					sb.append("<br/>[style.italicsMinorBad(你只能将[npc.name]转化成已解锁了高阶知识的种族。)]");
				}

			} else if(isDebugMenu()) {
				sb.append("<i>[npc.Name]可以控制debug之力来改变[npc.her]的"+area+"。</i>");

			} else if(getTarget().isDoll()) {
				sb.append("<i>将D.E.C.K.的数据线插入[npc.namePos]后颈的接口中，之后你就能够自定义[npc.her]的 "+area+"了……</i>");

			} else {
				sb.append("<i>[npc.NamePos]可化形的，粘液组成的身体使[npc.herHim]能够自我转化[npc.her]的"+area+"。</i>");
				if(getTarget().isPlayer()) {
					sb.append("<br/>[style.italicsMinorBad(你只能自我转化成已解锁了高阶知识的种族。)]");
				} else {
					sb.append("<br/>[style.italicsMinorBad(你只能将[npc.name]转化成已解锁了高阶知识的种族。)]");
				}
			}
		sb.append("</div>");

		return UtilText.parse(getTarget(), sb.toString());
	}

	public static final DialogueNode BODY_CHANGING_CORE = new DialogueNode("核心", "", true) {
		@Override
		public void applyPreParsingEffects() {
			SuccubisSecrets.initCoveringsMap(getTarget());
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			if (ScarlettsShop.isSlaveCustomisationMenu()) {
				SuccubisSecrets.initCoveringsMap(getTarget());
			}
			UtilText.nodeContentSB.append(getSelfTransformDescription("身体"));

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getAgeAppearanceChoiceDiv()
							+"</div>"

							+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformFemininityChoiceDiv()
								+CharacterModificationUtils.getHeightChoiceDiv(false)
							+"</div>");

			if (getTarget().isElemental()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getSelfTransformBodyMaterialChoiceDiv(getTarget()));
			}

			UtilText.nodeContentSB.append(
					"<div class='cosmetics-container' style='background:transparent;'>"
							+CharacterModificationUtils.getBodySizeChoiceDiv()
							+CharacterModificationUtils.getMuscleChoiceDiv()
							+"<div class='container-full-width' style='text-align:center;'>"
							+UtilText.parse(getTarget(), "[npc.NamePos]肌肉和体型数值塑造了[npc.herHim]这样的身体形态："
							+"<b style='color:"+getTarget().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(getTarget().getBodyShape().getName(false))+"</b>")
							+"</div>"
							+"</div>");

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformFaceChoiceDiv(getTFRaces(getTarget().getFaceRace(), false, false, true))
								+CharacterModificationUtils.getSelfTransformBodyChoiceDiv(getTFRaces(getTarget().getTorsoType().getRace(), false, false, true))
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformArmChoiceDiv(getTFRaces(getTarget().getArmRace()))
								+CharacterModificationUtils.getSelfTransformLegChoiceDiv(getTFRaces(getTarget().getLegRace()), isDebugMenu())
								+"</div>");

				if (getTarget().isYouko()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
									+CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
									+"</div>");
				} else {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformArmCountDiv()
									+CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
									+"</div>"

									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
									+CharacterModificationUtils.getSelfTransformGenitalArrangementChoiceDiv()
									+"</div>");
				}

				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTailChoiceDiv(
								getTFRaces(Util.newArrayListOfValues(getTarget().getTailRace(), getTarget().getLegRace()), isNonHumanHalfDemon(), false, false),
								removeNoneFromTailChoices())
								+CharacterModificationUtils.getSelfTransformTailLengthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTailCountDiv()
								+CharacterModificationUtils.getSelfTransformTailGirthDiv()
								+"</div>");

				if (!getTarget().isYouko()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
									+CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
									+"</div>"

									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformWingChoiceDiv(
									getTFRaces(Util.newArrayListOfValues(getTarget().getWingRace(), getTarget().getTorsoType().getRace()), isNonHumanHalfDemon(), false, false),
									removeNoneFromWingChoices())
									+CharacterModificationUtils.getSelfTransformWingSizeDiv()
									+"</div>");
				}
			} else {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformArmCountDiv()
								+CharacterModificationUtils.getSelfTransformWingSizeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+ CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
								+ CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTailLengthDiv()
								+CharacterModificationUtils.getSelfTransformTailGirthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
								+CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
								+"</div>");
			}

			for (Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()) {
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();

				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(getTarget(), bct, entry.getValue().getValue());

				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(getTarget(), titleDescription.getValue()),
						true,
						true));
			}

			if (Main.game.isBodyHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivUnderarmHair(false, "腋毛",
						UtilText.parse(getTarget(), "改变[npc.namePos]的腋下毛发量。"))

						+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getUnderarmHairType().getType(), "腋毛颜色",
						UtilText.parse(getTarget(), "[npc.namePos]腋毛颜色。"),
						true, true));
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_EYES = new DialogueNode("眼睛", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("眼部"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformEyeChoiceDiv(
								getTFRaces(getTarget().getEyeRace(), true, false, false)));

				if (!getTarget().isYouko() || isDebugMenu()) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformEyeCountDiv());
				}

				UtilText.nodeContentSB.append("</div>");
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getEyeType().getRace(), getTarget().getCovering(getTarget().getEyeCovering()).getType(),
							"虹膜颜色",
							UtilText.parse(getTarget(), "[npc.namePos]虹膜的颜色与图案。"),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getEyeType().getRace(), getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
							"瞳孔颜色",
							UtilText.parse(getTarget(), "[npc.namePos]瞳孔的颜色与图案。"),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getEyeType().getRace(), getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
							"巩膜颜色",
							UtilText.parse(getTarget(), "[npc.namePos]巩膜的颜色和图案。"),
							true, true));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_HAIR = new DialogueNode("Hair", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("头发"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformHairChoiceDiv(
						getTFRaces(getTarget().getHairRace())));
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformHairLengthDiv()
							+CharacterModificationUtils.getNeckFluffDiv()
							+"</div>"

							+CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(getTarget(), "改变[npc.namePos]发型。"))

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getHairRace(),
							getTarget().getCovering(getTarget().getHairCovering()).getType(), "发色",
							UtilText.parse(getTarget(), "改变[npc.her]头发的颜色。"), true, true));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_HEAD = new DialogueNode("头部", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("脑袋和面部"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformEarChoiceDiv(
						getTFRaces(getTarget().getEarRace())));
			}

			if (!getTarget().isYouko() || isDebugMenu()) {
				if (!getTarget().isDoll() || isDebugMenu()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformHornChoiceDiv(
									getTFRaces(getTarget().getHornRace(), false, true, false))
									+CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(
									getTFRaces(getTarget().getAntennaRace()))
									+"</div>");
				}

				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformHornSizeDiv()
								+CharacterModificationUtils.getSelfTransformAntennaSizeDiv()
								+"</div>");

				if (!getTarget().isDoll() || isDebugMenu()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformHornCountDiv()
									+CharacterModificationUtils.getSelfTransformAntennaCountDiv()
									+"</div>"

									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
									+CharacterModificationUtils.getSelfTransformAntennaePerRowCountDiv()
									+"</div>");
				}

				if (getTarget().hasHorns()) {
					UtilText.nodeContentSB.append(
							CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getHornRace(),
									getTarget().getCovering(getTarget().getHornCovering()).getType(), "角的颜色",
									UtilText.parse(getTarget(), "[npc.namePos]角的颜色。"),
									true, true));
				}
			}

			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getSelfTransformLipSizeDiv()

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
							+CharacterModificationUtils.getSelfTransformThroatWetnessDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformThroatCapacityDiv()
							+CharacterModificationUtils.getSelfTransformThroatDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformThroatElasticityDiv()
							+CharacterModificationUtils.getSelfTransformThroatPlasticityDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getMouthType().getRace(), getTarget().getCovering(BodyCoveringType.MOUTH).getType(),
							"嘴唇和喉咙的颜色",
							UtilText.parse(getTarget(),
									"[npc.namePos]"+(getTarget().getFaceType() == FaceType.HARPY?"鸟喙":"嘴唇")+"(上方选项)和[npc.her]喉咙(下方选项)的原色。"
											+"口红可以用来掩盖[npc.her]原本的嘴唇颜色。"),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getTongueType().getRace(), getTarget().getCovering(BodyCoveringType.TONGUE).getType(),
							"舌头颜色",
							(getTarget().isPlayer()
									?"你的舌头的颜色。"
									:UtilText.parse(getTarget(), "[npc.namePos]舌头的颜色。")),
							true, true));

			if (Main.game.isFacialHairEnabled() && (!getTarget().isFeminine() || Main.game.isFemaleFacialHairEnabled())) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "胡须长度",
						UtilText.parse(getTarget(), "改变[npc.namePos]胡须的长度。"))

						+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getFacialHairType().getType(), "胡须颜色",
						UtilText.parse(getTarget(), "[npc.namePos]胡须的颜色。"),
						true, true));
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_ASS = new DialogueNode("屁股", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("屁股和臀部"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformAssChoiceDiv(
						getTFRaces(getTarget().getAssRace(), true, false, false)));
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAssSizeDiv()
							+CharacterModificationUtils.getSelfTransformHipSizeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAnusModifiersDiv()
							+CharacterModificationUtils.getSelfTransformAnusWetnessDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAnusCapacityDiv()
							+CharacterModificationUtils.getSelfTransformAnusDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformAnusElasticityDiv()
							+CharacterModificationUtils.getSelfTransformAnusPlasticityDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getTarget().getAssRace(),
							getTarget().getCovering(BodyCoveringType.ANUS).getType(),
							"肛门颜色",
							UtilText.parse(getTarget(), "改变[npc.namePos]肛门的颜色。"),
							true, true));

			if (Main.game.isAssHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "肛毛",
						UtilText.parse(getTarget(), "改变[npc.namePos]肛门周围的毛发量。"))

						+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getAssHairType().getType(), "肛毛颜色",
						UtilText.parse(getTarget(), "[npc.namePos]肛毛的颜色。"),
						true, true));
			}

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_BREASTS = new DialogueNode("胸部", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("胸部"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformBreastChoiceDiv(
						getTFRaces(getTarget().getBreastRace(), true, false, false)));
			}

			UtilText.nodeContentSB.append(
					"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformBreastSizeDiv()
							+CharacterModificationUtils.getSelfTransformBreastShapeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformBreastRowsDiv()
							+CharacterModificationUtils.getSelfTransformNippleModifiersDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformLactationDiv()
							+CharacterModificationUtils.getSelfTransformLactationRegenerationDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformLactationFlavourDiv()
							+CharacterModificationUtils.getSelfTransformLactationModifiersDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleCountDiv()
							+CharacterModificationUtils.getSelfTransformNippleShapeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleSizeDiv()
							+CharacterModificationUtils.getSelfTransformAreolaeSizeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleCapacityDiv()
							+CharacterModificationUtils.getSelfTransformNippleDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformNippleElasticityDiv()
							+CharacterModificationUtils.getSelfTransformNipplePlasticityDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getTarget().getBreastRace(),
							getTarget().getCovering(BodyCoveringType.NIPPLES).getType(),
							"乳头颜色",
							UtilText.parse(getTarget(), "改变[npc.namePos]乳头的颜色。"),
							true, true)

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							Race.NONE,
							getTarget().getCovering(BodyCoveringType.MILK).getType(),
							"乳汁颜色",
							UtilText.parse(getTarget(), "改变[npc.namePos][npc.milk]的颜色。"),
							true, true));

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_VAGINA = new DialogueNode("阴道", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("阴道"));

			//if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformVaginaChoiceDiv(
						getTFRaces(getTarget().getVaginaRace(), true, false, false)));
			//}

			if (getTarget().hasVagina()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getSelfTransformGirlcumFlavourDiv()
								+CharacterModificationUtils.getSelfTransformGirlcumModifiersDiv());

				if (!getTarget().isDoll() || isDebugMenu()) {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
									+CharacterModificationUtils.getSelfTransformVaginaHymenDiv()
									+"</div>"
									+"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
									+CharacterModificationUtils.getSelfTransformVaginaEggLayerDiv()
									+"</div>");
				} else {
					UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
									+CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
									+CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
									+"</div>");
				}

				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaModifiersDiv()
								+CharacterModificationUtils.getSelfTransformVaginaWetnessDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaCapacityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaDepthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaElasticityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaPlasticityDiv()
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
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformVaginaUrethraElasticityDiv()
								+CharacterModificationUtils.getSelfTransformVaginaUrethraPlasticityDiv()
								+"</div>"

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								getTarget().getVaginaRace(),
								getTarget().getCovering(BodyCoveringType.VAGINA).getType(),
								"阴道颜色",
								UtilText.parse(getTarget(), "改变[npc.namePos]阴道的颜色。"),
								true, true)

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								Race.NONE,
								getTarget().getCovering(BodyCoveringType.GIRL_CUM).getType(),
								"爱液颜色",
								UtilText.parse(getTarget(), "改变[npc.namePos][npc.girlcum]的颜色。"),
								true, true));

				if (Main.game.isPubicHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "阴毛长度",
							UtilText.parse(getTarget(), "改变[npc.namePos]生殖器周围的毛发量。"))

							+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getPubicHairType().getType(), "阴毛颜色",
							UtilText.parse(getTarget(), "[npc.namePos]阴毛的颜色。"),
							true, true));
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
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_PENIS = new DialogueNode("Penis", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(getSelfTransformDescription("阴茎"));

			//if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformPenisChoiceDiv(
						getTFRaces(getTarget().getPenisRace(), true, false, false), false));
			//}
			
			if (getTarget().hasPenis()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformPenisSizeDiv()
								+CharacterModificationUtils.getSelfTransformPenisGirthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformPenisModifiersDiv()
								+CharacterModificationUtils.getSelfTransformCumExplusionDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformCumProductionDiv()
								+CharacterModificationUtils.getSelfTransformCumRegenerationDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformCumFlavourDiv()
								+CharacterModificationUtils.getSelfTransformCumModifiersDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTesticleCountDiv()
								+CharacterModificationUtils.getSelfTransformInternalTesticleDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformTesticleSizeDiv()
								+CharacterModificationUtils.getSelfTransformUrethraModifiersDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformUrethraCapacityDiv()
								+CharacterModificationUtils.getSelfTransformUrethraDepthDiv()
								+"</div>"
								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformUrethraElasticityDiv()
								+CharacterModificationUtils.getSelfTransformUrethraPlasticityDiv()
								+"</div>"

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								getTarget().getPenisRace(),
								getTarget().getCovering(BodyCoveringType.PENIS).getType(),
								"阴茎颜色",
								UtilText.parse(getTarget(), "改变[npc.namePos]阴茎的颜色。"),
								true, true)

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								Race.NONE,
								getTarget().getCovering(BodyCoveringType.CUM).getType(),
								"精液颜色",
								UtilText.parse(getTarget(), "改变[npc.namePos][npc.cum]的颜色。"),
								true, true));
				
				if (Main.game.isPubicHairEnabled() && (!getTarget().isDoll() || isDebugMenu())) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "阴毛",
							UtilText.parse(getTarget(), "改变[npc.namePos]生殖器周围的毛发量。"))
							+CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getPubicHairType().getType(), "阴毛颜色",
							UtilText.parse(getTarget(), "[npc.namePos]阴毛的颜色。"),
							true, true));
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
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_BREASTS_CROTCH = new DialogueNode("胯乳", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(getSelfTransformDescription("[npc.crotchBoobs]"));

			if (!getTarget().isDoll() || isDebugMenu()) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getSelfTransformBreastCrotchChoiceDiv(
						getTFRaces(getTarget().getBreastCrotchRace(), true, false, false)));
			}
			
			if (getTarget().hasBreastsCrotch()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformBreastCrotchSizeDiv()
								+CharacterModificationUtils.getSelfTransformBreastCrotchShapeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformBreastCrotchRowsDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchModifiersDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformLactationCrotchDiv()
								+CharacterModificationUtils.getSelfTransformLactationCrotchRegenerationDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformLactationCrotchFlavourDiv()
								+CharacterModificationUtils.getSelfTransformLactationCrotchModifiersDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchCountDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchShapeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchSizeDiv()
								+CharacterModificationUtils.getSelfTransformAreolaeCrotchSizeDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchCapacityDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchDepthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+CharacterModificationUtils.getSelfTransformNippleCrotchElasticityDiv()
								+CharacterModificationUtils.getSelfTransformNippleCrotchPlasticityDiv()
								+"</div>"

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								getTarget().getBreastCrotchRace(),
								getTarget().getCovering(BodyCoveringType.NIPPLES_CROTCH).getType(),
								"乳头颜色",
								UtilText.parse(getTarget(), "改变[npc.namePos][npc.crotchNipples]的颜色。"),
								true, true)

								+CharacterModificationUtils.getKatesDivCoveringsNew(false,
								Race.NONE,
								getTarget().getCovering(BodyCoveringType.MILK).getType(),
								"乳汁颜色",
								UtilText.parse(getTarget(), "改变[npc.namePos][npc.milk]的颜色。"),
								true, true));
			}

			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_SPINNERET = new DialogueNode("丝囊", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					getSelfTransformDescription("吐丝孔")

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformSpinneretModifiersDiv()
							+CharacterModificationUtils.getSelfTransformSpinneretWetnessDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformSpinneretCapacityDiv()
							+CharacterModificationUtils.getSelfTransformSpinneretDepthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+CharacterModificationUtils.getSelfTransformSpinneretElasticityDiv()
							+CharacterModificationUtils.getSelfTransformSpinneretPlasticityDiv()
							+"</div>"

							+CharacterModificationUtils.getKatesDivCoveringsNew(false,
							getTarget().hasLegSpinneret()
									?getTarget().getLegRace()
									:getTarget().getTailRace(),
							getTarget().getCovering(BodyCoveringType.SPINNERET).getType(),
							"丝囊颜色",
							UtilText.parse(getTarget(), "改变[npc.namePos]丝囊的颜色。"),
							true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("妆容", "", true) {
		@Override
		public String getHeaderContent() {
			return getSelfTransformDescription("妆容")

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
					false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "脚指甲油", "脚指甲有用来给你[pc.feet]上的脚指甲提供色彩和保护。", true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public String getResponseTabTitle(int index) {
			return getBodyChangingTabTitle(index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	// Save/Load dialogue and associated methods/variables:
	public static void initSaveLoadMenu() {
		presetTransformationsMap = new HashMap<>();
		
		for(File f : getSavedBodies()) {
			try {
				String name = Util.getFileIdentifier(f);
				String nameReadable = Util.capitaliseSentence(name.replaceAll("_", " "));
				Body loadedBody = loadBody(name);
				Femininity fem = Femininity.valueOf(loadedBody.getFemininity());
				AbstractSubspecies subspecies = loadedBody.getLoadedSubspecies();
				String subspeciesName = loadedBody.isFeminine()?subspecies.getSingularFemaleName(loadedBody):subspecies.getSingularMaleName(loadedBody);
				String displayName;
				if(isPresetTransformationAvailable(loadedBody)) {
					displayName = "<b>"+nameReadable+"</b>"
							+ " (<span style='color:"+fem.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(fem.getNames().get(0))+"</span>"
							+ "<span style='color:"+subspecies.getColour(null).toWebHexString()+";'>"+subspeciesName+"</span>)";
				} else {
					displayName = "[style.boldDisabled("+nameReadable+")][style.colourDisabled(("+Util.capitaliseSentence(fem.getNames().get(0))+""+subspeciesName+"))]";
				}
				presetTransformationsMap.put(name, new Value<>(displayName, loadedBody));
			} catch(Exception ex) {
			}
		}
	}
	
	public static String loadConfirmationName = "";
	public static String overwriteConfirmationName = "";
	public static String deleteConfirmationName = "";
	/** Mapping file location to a Value of saved name and Body. */
	private static Map<String, Value<String, Body>> presetTransformationsMap = new HashMap<>();
	
	public static Map<String, Value<String, Body>> getPresetTransformationsMap() {
		return presetTransformationsMap;
	}

	public static final DialogueNode BODY_CHANGING_SAVE_LOAD = new DialogueNode("保存转化文件", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();
			
			saveLoadSB.append(
					"<div class='container-full-width' style='padding:0; margin:0 0 8px 0;'>"
						+ "<p>"
							+ "只有标准字符(字母和数字)才能用于保存文件名。"
							+ "<br/>将指针悬停在每种变形的预设图标上来查看身体预览。"
							+ UtilText.parse(getTarget(), "<br/>如果NPC的名字[style.colourDisabled(被涂灰)]，说明[npc.name]没有能力转化成预设的样子，"
								+ "你可以将鼠标置于灰色加载按钮上知晓无法转变的原因。")
						+ "</p>"
					+ "</div>"
					+ "<div class='container-full-width' style='padding:0; margin:0;'>"
						+ "<div class='container-full-width' style='width:calc(75% - 16px); text-align:center; background:transparent;'>"
							+ "名字"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "读档 | 存档 | 删除"
						+ "</div>"
					+ "</div>");

			int i=0;
			
			saveLoadSB.append(getSaveLoadRow(null, "", null, i%2==0));
			i++;
			
			for(Entry<String, Value<String, Body>> entry : presetTransformationsMap.entrySet()) {
				saveLoadSB.append(getSaveLoadRow(entry.getKey(), entry.getValue().getKey(), entry.getValue().getValue(), i%2==0));
				i++;
			}
			
			saveLoadSB.append("<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("确认状态：",
						"在点击读取、覆写或删除转化时会进入确认状态。"
							+ "启用时，需要进行两次点击才能使行为生效。"
							+ "关闭时只需要一次点击。",
							BODY_CHANGING_SAVE_LOAD) {
					@Override
					public String getTitle() {
						return "再次确认："+(Main.getProperties().hasValue(PropertyValue.overwriteWarning)
								?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>开启</span>"
								:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>关闭</span>");
					}
					
					@Override
					public void effects() {
						loadConfirmationName = "";
						overwriteConfirmationName = "";
						deleteConfirmationName = "";
						Main.getProperties().setValue(PropertyValue.overwriteWarning, !Main.getProperties().hasValue(PropertyValue.overwriteWarning));
						Main.getProperties().savePropertiesAsXML();
					}
				};

			} else if (index == 0) {
				return new Response("返回", "返回到转化菜单。", BODY_CHANGING_CORE);

			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static List<File> getSavedBodies() {
		List<File> filesList = new ArrayList<>();
		
		File dir = new File("data/transformation_presets");
		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, name) -> name.endsWith(".xml"));
			if (directoryListing != null) {
				filesList.addAll(Arrays.asList(directoryListing));
			}
		}

		filesList.sort(Comparator.comparing(File::getName).reversed());
		
		return filesList;
	}

	private static String getSaveLoadRow(String baseName, String displayName, Body body, boolean altColour) {
		
		if(body!=null){
			String fileName = (baseName+".xml");
			
			boolean canTransform = isPresetTransformationAvailable(body);
			
//			System.out.println(body.getLoadedSubspecies().getName(body));
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+" position:relative;'>"
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'"+(canTransform?"":" style='opacity:0.25;'")+">"
										+ body.getLoadedSubspecies().getSVGStringFromBody(body)
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_BODY_" + baseName + "'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+ "<p style='margin:0; padding:2px;'>"+displayName+"</p>"
								+ "<p style='margin:0; padding:2px;'>[style.colourDisabled(data/transformation_presets/)]"+baseName+"[style.colourDisabled(.xml)]</p>"
							+"</div>"
						+ "</div>"
						+ "<div class='container-full-width' style='width:calc(25% - 16px);text-align:center; background:transparent;'>"
							+ (Main.game.isStarted()
									?(fileName.equals(overwriteConfirmationName)
										?"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='OVERWRITE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskOverwrite()+"</div></div>")
									:"<div class='square-button saveIcon disabled'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSaveDisabled()+"</div></div>")
							
							+ (canTransform
									? (fileName.equals(loadConfirmationName)
										?"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadConfirm()+"</div></div>"
										:"<div class='square-button saveIcon' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoad()+"</div></div>")
									:"<div class='square-button saveIcon disabled' id='LOAD_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskLoadDisabled()+"</div></div>")
	
	
							+ (fileName.equals(deleteConfirmationName)
								?"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDeleteConfirm()+"</div></div>"
								:"<div class='square-button saveIcon' id='DELETE_" + baseName + "'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskDelete()+"</div></div>")
						+ "</div>"
					+ "</div>";
			
		} else {
			String svgString = getTarget().getSubspecies().getSVGString(getTarget());
			
			return "<div class='container-full-width' style='padding:0; margin:0 0 4px 0;"+(altColour?"background:#222;":"")+"'>"
					
						+ "<div class='container-full-width' style='width:calc(75% - 16px); background:transparent;'>"
					
							+ "<div class='container-full-width' style='width:10%; margin:0; padding:0; background:transparent; position:relative; float:left;'>"
								+"<div class='inventoryImage' style='width:100%;'>"
									+ "<div class='inventoryImage-content'>"
										+ svgString
									+ "</div>"
									+ "<div class='overlay no-pointer' id='LOADED_BODY_CURRENT'></div>"
								+ "</div>"
							+ "</div>"
						
							+ "<div style='width:calc(90% - 8px); padding:0; margin:0 0 0 8px; position:relative; float:left;'>"
								+"<form style='padding:0;margin:0;text-align:center;'><input type='text' id='new_save_name' placeholder='Enter File Name' style='padding:0;margin:0;width:100%;'></form>"
							+"</div>"
							
						+ "</div>"
					
						+ "<div class='container-full-width' style='width:calc(25% - 16px); text-align:center; background:transparent;'>"
							+ "<div class='square-button saveIcon' id='NEW_SAVE' style='float:left;'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getDiskSave()+"</div></div>"
						+ "</div>"
					+ "</div>";
		}
	}

	public static void saveBody(String name, boolean allowOverwrite) {
		name = Main.checkFileName(name);
		if(name.isEmpty()) {
			return;
		}
		
		Body body = getTarget().getBody();
		
		File dir = new File("data/");
		dir.mkdir();

		dir = new File("data/transformation_presets");
		dir.mkdir();

		if (dir.isDirectory()) {
			File[] directoryListing = dir.listFiles((path, filename) -> filename.endsWith(".xml"));
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().equals(name+".xml")){
						if(!allowOverwrite) {
							Main.game.flashMessage(PresetColour.GENERIC_BAD, "名称已存在！");
							return;
						}
					}
				}
			}
		}

		try {
			// Starting stuff:
			Document doc = Main.getDocBuilder().newDocument();
			
			Element coreElement = doc.createElement("body");
			doc.appendChild(coreElement);

			body.saveAsXML(coreElement, doc);
			
			// Ending stuff:
			
			Transformer transformer1 = Main.transformerFactory.newTransformer();
			transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();

			transformer1.transform(new DOMSource(doc), new StreamResult(writer));
			
			// Save this xml:
			Transformer transformer = Main.transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			
			String saveLocation = "data/transformation_presets/"+name+".xml";
			StreamResult result = new StreamResult(saveLocation);
			
			transformer.transform(source, result);
			
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
		BodyChanging.initSaveLoadMenu();
		Main.game.setContent(new Response("", "", BodyChanging.BODY_CHANGING_SAVE_LOAD));
	}

	public static Body loadBody(String name) {
		if(isLoadBodyAvailable(name)) {
			File file = new File("data/transformation_presets/"+name+".xml");

			if (file.exists()) {
				try {
					Document doc = DocBuilders.parseDoc(file);
					
					// Cast magic:
					doc.getDocumentElement().normalize();
					
					Body body = Body.loadFromXML(null, (Element) doc.getDocumentElement(), doc);
					body.calculateRace(null);
					
					return body;
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	public static boolean isLoadBodyAvailable(String name) {
		File file = new File("data/transformation_presets/"+name+".xml");

		if(!file.exists()) {
			return false;
		}
		
		return true;
	}

	public static void deleteBody(String name) {
		File file = new File("data/transformation_presets/"+name+".xml");

		if (file.exists()) {
			try {
				file.delete();
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} else {
			Main.game.flashMessage(PresetColour.GENERIC_BAD, "未找到文件……");
		}
	}

	public static boolean isPresetTransformationAvailable(Body body) {
		String unavailabilityText = getPresetTransformationUnavailabilityText(body);
		return unavailabilityText==null || unavailabilityText.isEmpty();
	}
	
	public static String getPresetTransformationUnavailabilityText(Body body) {
		if(isDebugMenu()) {
			return "";
		}
		
		// Height limitation:
		if(getTarget().isShortStature()!=body.isShortStature()) {
			if(getTarget().isShortStature()) {
				return UtilText.parse(getTarget(), "[npc.NameIsFull]太矮小以至于无法转化成这个形态！");
			} else {
				UtilText.parse(getTarget(), "[npc.NameIsFull]太高了，无法转化成这个形态！");
			}
		}
		
		// Material limitations:
		Set<BodyMaterial> materialsAllowed = Util.newHashSetOfValues(getTarget().getBodyMaterial());
		if(getTarget() instanceof Elemental) {
			switch(getTarget().getBodyMaterial()) {
				// Air:
				case AIR:
					break;
				// Arcane:
				case ARCANE:
					break;
				// Fire:
				case FIRE:
					break;
				// Earth:
				case RUBBER:
					materialsAllowed.add(BodyMaterial.STONE);
					break;
				case STONE:
					materialsAllowed.add(BodyMaterial.RUBBER);
					break;
				// Water:
				case WATER:
					materialsAllowed.add(BodyMaterial.ICE);
					break;
				case ICE:
					materialsAllowed.add(BodyMaterial.WATER);
					break;
				// Non-elemental materials:
				case FLESH:
				case SLIME:
				case SILICONE:
					break;
			}
		}
		if(!materialsAllowed.contains(body.getBodyMaterial())) {
			BodyMaterial matCurrent = getTarget().getBodyMaterial();
			BodyMaterial matTarget = body.getBodyMaterial();
			return UtilText.parse(getTarget(),
					"[npc.Name]无法转化成与[npc.her]当前身体不同材质的身体！"
					+ "<br/>Current material: <span style='color:"+matCurrent.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(matCurrent.getName())+"</span>"
					+ "<br/>Transformation target's material: <span style='color:"+matTarget.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(matTarget.getName())+"</span>");
		}
				
		// Feral limitations:
		if(body.isFeral()!=getTarget().isFeral()) {
			if(body.isFeral()) {
				return UtilText.parse(getTarget(), "[npc.Name]无法转化成[style.colourFeral(兽态身体)]，因为[npc.her]当前的身体并非兽态！");
			}
			return UtilText.parse(getTarget(), "[npc.Name]无法转化成[style.colourHuman(非兽态身体)]，因为[npc.her]当前的身体是兽态！");
		}

		StringBuilder sb = new StringBuilder();
		List<String> partsList = new ArrayList<>();

		for (BodyPartInterface currentPart : getTarget().getBody().getAllBodyParts()) {
			List<AbstractRace> availableRaces;
			if (currentPart instanceof AbstractAssType
					|| currentPart instanceof AbstractBreastType
					|| currentPart instanceof AbstractEyeType
					|| currentPart instanceof AbstractPenisType
					|| currentPart instanceof AbstractVaginaType) {
				availableRaces = getTFRaces(currentPart.getType().getRace(), true, false, false);
			} else if (currentPart instanceof AbstractHornType) {
				availableRaces = getTFRaces(currentPart.getType().getRace(), false, true, false);
			} else if (currentPart instanceof AbstractFaceType
					|| currentPart instanceof AbstractTorsoType) {
				availableRaces = getTFRaces(currentPart.getType().getRace(), false, false, true);
			} else if (currentPart instanceof AbstractTailType) {
				availableRaces = getTFRaces(Util.newArrayListOfValues(getTarget().getTailRace(), getTarget().getLegRace()), isNonHumanHalfDemon(), false, false);
				if (removeNoneFromTailChoices()) {
					availableRaces.remove(Race.NONE);
				}
			} else if (currentPart instanceof AbstractWingType) {
				availableRaces = getTFRaces(Util.newArrayListOfValues(getTarget().getWingRace(), getTarget().getTorsoType().getRace()), isNonHumanHalfDemon(), false, false);
				if (removeNoneFromWingChoices()) {
					availableRaces.remove(Race.NONE);
				}
			} else {
				availableRaces = getTFRaces(currentPart.getType().getRace());
			}
			BodyPartInterface presetPart = currentPart;
			for (BodyPartInterface part : body.getAllBodyParts()) {
				if (currentPart.getType() == part.getType()) {
					presetPart = part;
					break;
				}
			}
			if (!availableRaces.contains(presetPart.getType().getRace())) {
				if (sb.length() == 0) {
					sb.append("[npc.NameIsFull]因为下列部位无法转化为该种族：");
					sb.append("<br/>");
				}
				partsList.add(presetPart.getType().getName(getTarget()));
			}
		}
		if(sb.length()>0) {
			sb.append(Util.capitaliseSentence(Util.stringsToStringList(partsList, false)));
			sb.append("。");
			return UtilText.parse(getTarget(), sb.toString());
		}

		if(getTarget().isYouko()) { // Youko self-TF limitations:
			if(body.getArm().getArmRows()!=getTarget().getArmRows()) {
				sb.append("<br/>手臂对数");
			}
			if(body.getGenitalArrangement()!=getTarget().getGenitalArrangement()) {
				sb.append("<br/>生殖器调整");
			}
			if(body.getTentacle().getType()!=getTarget().getTentacleType()) {
				sb.append("<br/>触手种类");
			}
			if(body.getWing().getType()!=getTarget().getWingType()) {
				sb.append("<br/>飞翼种类");
			}
			if(body.getEye().getEyePairs()!=getTarget().getEyePairs()) {
				sb.append("<br/>眼睛数量");
			}
			if(body.getHorn().getType()!=getTarget().getHornType()) {
				sb.append("<br/>角的种类");
			}
			if(body.getAntenna().getType()!=getTarget().getAntennaType()) {
				sb.append("<br/>触角种类");
			}
			if(sb.length()>0) {
				sb.insert(0, "Youkos' limited transformation powers prevent [npc.name] from transforming:");
				return UtilText.parse(getTarget(), sb.toString());
			}
		}
		return "";
	}

	/**
	 * Sets the supplied body as the getTarget()'s new body.
	 * <br/>Retains all getTarget()'s covering colours which are not actively used by the new body, replacing the loaded body's unused covering colours.
	 * <br/>If the getTarget() does not have covering colours saved which are present in the loaded body, then these loaded body's covering colours are retained.
	 */
	public static void applyLoadedBody(Body body) {
		AbstractSubspecies subspeciesOverride = getTarget().getSubspeciesOverride();
		Map<AbstractBodyCoveringType, Covering> oldCoverings = getTarget().getBody().getCoverings();
		
		getTarget().setBody(body, false);
		
		getTarget().setSubspeciesOverride(subspeciesOverride);
		List<AbstractBodyCoveringType> currentlyActiveCoverings = new ArrayList<>();
		for(BodyPartInterface part : body.getAllBodyPartsWithAllOrifices()) {
			AbstractBodyCoveringType bct = getTarget().getCovering(part);
			if(bct==null) {
//				System.out.println(part.getName(getTarget()));
				continue;
			}
			if(BodyCoveringType.getAllMakeupTypes().contains(bct) || bct==BodyCoveringType.DILDO) {
				continue;
			}
			if(body.getBodyMaterial()!=BodyMaterial.FLESH) {
				currentlyActiveCoverings.add(BodyCoveringType.getMaterialBodyCoveringType(body.getBodyMaterial(), bct.getCategory()));
			}
			currentlyActiveCoverings.add(bct);
		}
		
		for(Entry<AbstractBodyCoveringType, Covering> entry: oldCoverings.entrySet()) {
			if(!currentlyActiveCoverings.contains(entry.getKey())) {
				getTarget().getBody().getCoverings().put(entry.getKey(), entry.getValue());
			}
		}
		
	}
}
