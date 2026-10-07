package com.lilithsthrone.game.dialogue.npcDialogue.elemental;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class ElementalDialogue {
	
	private static Elemental getElemental() {
		return Main.game.getPlayer().getElemental();
	}

	public static final DialogueNode UTIL_NO_CONTENT = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELEMENTAL_START = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "互动";
			} else if(index==1) {
				return UtilText.parse("[style.colourCompanion(管理)]");
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) { // Interactions:
				if(index==1) {
					if(!getElemental().isActive()) {
						return new Response("审视", "你需要让[el.name]处于主动形态，才能审视[el.herHim]。", null);
					}
					return new Response("审视", "仔细打量[el.name]。", ELEMENTAL_INSPECT);
					
				} else if(index==2) {
					return new Response("对话", "和[el.name]说说话。", ELEMENTAL_TALK);
					
				} else if(index==3) {
					if(getElemental().isActive()) {
						return new Response("安抚", "[el.Name]需要处于被动形态，你才能安抚[el.herHim]……", null);
					}
					return new Response("安抚", "好好摸摸[el.name]。", ELEMENTAL_PETTING);
					
				}
				// index==5 is transform toggle defined below
				if(index==6) {
					if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("支配型性爱", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("支配型性爱",
								"鉴于你没有[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+"性癖)]，你在能被别人看见的地方和[el.name]做爱会很难受！",
								null);
						
					} else if(!getElemental().isActive()) {
						return new Response("支配型性爱", "你需要让[el.name]处于主动形态，才能与[el.herHim]做爱。", null);
						
					} else if(getElemental().isSummonerServant() && !getElemental().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
						return new Response("支配型性爱",
								"由于你已经宣誓效忠[el.school]学派，且[el.name]没有“"+Fetish.FETISH_SUBMISSIVE.getName(getElemental())+"”性癖，所以[el.name]不会让你强硬地操[el.herHim]……",
								null);
					}
					return new ResponseSex("支配型性爱",
							"和[el.name]进行支配型性爱。",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getElemental(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								public boolean isCharacterAbleToStopSex(GameCharacter character) {
									return character.isPlayer();
								}
							},
							null,
							null,
							ELEMENTAL_AFTER_SEX,
							UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START_SEX_DOMINANT"));
					
				} else if(index==7) {
					if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("服从型性爱", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("服从型性爱",
								"鉴于你没有[style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+"性癖)]，你在能被别人看见的地方和[el.name]做爱会很难受！",
								null);
						
					} else if(!getElemental().isActive()) {
						return new Response("服从型性爱", "你需要让[el.name]处于主动形态，才能和[el.herHim]做爱。", null);
					}
					return new ResponseSex("服从性爱",
							"让[el.name]掌握主动权，和[el.herHim]进行服从型性爱。",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getElemental(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))),
							null,
							null,
							ELEMENTAL_AFTER_SEX,
							UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START_SEX_SUBMISSIVE"));
					
				}
			
			} else if(responseTab==1) { // Management:
				if(index==1) {
					if(getElemental().isActive()) {
						return new Response("被动转化", "你需要让[el.name]进入被动形态，才能令其转化为其他样子……", null);
					}
					return new Response("被动转化", "询问[el.name]被动形态的事情，还有[el.she]能否转化成别的样子……", ELEMENTAL_PASSIVE_FORM);
					
				} else if(index==2) {
					if(!getElemental().isActive()) {
						return new Response("主动转化", "你需要让[el.name]进入主动形态，才能令其发生变化……", null);
					}
					return new Response("主动转化",
							"让[el.name]将自己转化成其他形态……",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getElemental());
						}
					};
					
				} else if(index==3) {
					return new Response("天赋", "分配[el.namePos]的天赋点。", ELEMENTAL_PERKS);
					
				} else if(index==4) {
					return new Response("性癖", "使用密切的精神链接，使[el.name]根据你的性癖改变性癖。", ELEMENTAL_FETISHES);
					
				}
				// index==5 is transform toggle defined below
				if(index==6) {
					return new Response("战斗动作", "调整[el.name]战斗中可使用的动作。", CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(getElemental(), ELEMENTAL_START);
						}
					};
					
				} else if(index==7) {
					if(!getElemental().isActive()) {
						return new Response("物品栏", "你需要让[el.name]处于主动形态，才能管理[el.her]的物品栏……", null);
					}
					return new ResponseEffectsOnly("物品栏", "管理[el.namePos]的物品栏。") {
						@Override
						public void effects() {
							Main.mainController.openInventory((NPC) getElemental(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if(index==8) {
					return new ResponseEffectsOnly("自我清洁: "+(!getElemental().hasFlag(NPCFlagValue.elementalStayDirty)?"[style.colourGood(开启)]":"[style.colourBad(关闭)]"),
							!getElemental().hasFlag(NPCFlagValue.elementalStayDirty)
								?"[el.Name]正在使用其自身转化的能力清理体内和衣物。<br/>[style.colourMinorBad(点击关闭自我清洁。)]"
								:"[el.Name]正在使用其自我转化能力尽量留存体内或衣物上的肮脏液体。<br/>[style.colourMinorGood(点击开启自我清洁。)]") {
						@Override
						public void effects() {
							if(getElemental().hasFlag(NPCFlagValue.elementalStayDirty)) {
								getElemental().removeFlag(NPCFlagValue.elementalStayDirty);
							} else {
								getElemental().addFlag(NPCFlagValue.elementalStayDirty);
							}
							Main.game.updateResponses();
						}
					};
					
				} else if(index==9) {
					return new Response("设定名字", "修改[el.namePos]的名字，或者让[el.herHim]用其他名字称呼你。", ELEMENTAL_CHOOSE_NAME);
					
				}
			}
			
			if(index==5) {
				if(getElemental().isActive()) {
					return new Response("形态:<b style='color:"+getElemental().getCurrentSchool().getColour().toWebHexString()+";'>主动</b>",
							"让[el.name]切换回被动形态。",
							UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							getElemental().returnToHome();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TAKE_FORM_PASSIVE"));
						}
					};
					
				} else {
					return new Response("形态:<i style='color:"+getElemental().getCurrentSchool().getColour().toWebHexString()+";'>被动</i>",
							"让[el.name]切换为主动形态。",
							UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							getElemental().setLocation(Main.game.getPlayer(), false);
							UtilText.addSpecialParsingString(String.valueOf(Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TAKE_FORM_ACTIVE"));
						}
					};
				}
				
			} else if(index==10) {
				return new Response("驱散", "驱散[el.name]……",  Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getElemental().returnToHome();
						Main.game.getPlayer().setElementalSummoned(false);
					}
				};
			}
			
			if(index==0) {
				return new Response("结束", "告诉[el.name]你跟[el.herHim]谈完了，可以转化回被动形态了。", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						getElemental().returnToHome();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ELEMENTAL_AFTER_SEX = new DialogueNode("结束", "[el.name]和你爽够了，结束了性爱……", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_AFTER_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_INSPECT = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_INSPECT")
					+ getElemental().getCharacterInformationScreen(false);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_INSPECT) {
				return new Response("审视", "你正在仔细打量[el.name]。", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_TALK = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TALK");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_TALK) {
				return new Response("对话", "你正在跟[el.name]对话。", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_PETTING = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PETTING");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_PETTING) {
				return new Response("安抚", "你已经在安抚[el.name]了！", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELEMENTAL_PASSIVE_FORM = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("返回", "决定还是不让[el.name]切换为其他的被动形态了。", UTIL_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_BACK"));
					}
				};
			}
			
			List<Response> responses = new ArrayList<>();
			List<AbstractSubspecies> subspecies = new ArrayList<>();
			subspecies.addAll(Subspecies.getAllSubspecies());
			subspecies.removeIf(s -> !s.getRace().isFeralPartsAvailable());
			subspecies.removeIf(s -> s.getRace()==Race.DEMON || s.getRace()==Race.ELEMENTAL || s.getRace()==Race.SLIME);
//			subspecies.removeIf(s -> s.isNonBiped());
			subspecies.removeIf(s -> s==Subspecies.CENTAUR || s==Subspecies.PEGATAUR || s==Subspecies.ALITAUR || s==Subspecies.UNITAUR); // Centaurs are a special case, as we don't want centaur ferals
			
			if(getElemental().getPassiveForm()==null) {
				responses.add(new Response("精灵", "[el.Name]已经是被动的元素精灵形态了！", null));
			} else {
				responses.add(new Response("精灵", "让[el.name]转化成被动的元素精灵形态。", UTIL_NO_CONTENT) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("精灵", true);
						getElemental().setPassiveForm(null);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_CHANGE"));
					}
				});
			}
			
			List<String> nameDuplicates = new ArrayList<>(); // Do not populate responses with duplicates. This should remove repeated instances of the same feral animal, e.g. "snake" 3 times from snake, lamia, and melusine
			
			for(AbstractSubspecies sub : subspecies) {
				String feralName = sub.getFeralName(getElemental().getBody());
				if(!nameDuplicates.contains(feralName)) {
					nameDuplicates.add(feralName);
					if(getElemental().getPassiveForm()==sub) {
						responses.add(new Response(Util.capitaliseSentence(feralName), "[el.Name]的被动形态已经是小型的兽态"+feralName+"了！", null));
						
					} else {
						responses.add(new Response(Util.capitaliseSentence(feralName), "让[el.name]将被动形态修改为小型的兽态"+feralName+"。", UTIL_NO_CONTENT) {
							@Override
							public void effects() {
								UtilText.addSpecialParsingString(UtilText.generateSingularDeterminer(feralName)+""+feralName, true);
								UtilText.addSpecialParsingString(feralName, false);
								getElemental().setPassiveForm(sub);
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_CHANGE"));
							}
						});
					}
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ELEMENTAL_PERKS = new DialogueNode("[el.NamePos]天赋树", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(),
					"<details>"
							+ "<summary>[style.boldPerk(天赋与特性信息)]</summary>"
							+ "[style.colourPerk(天赋)](圆形图标)为[npc.namePos]属性提供永久增益。<br/>"
							+ "[style.colourPerk(特性)](方形图标)为[npc.name]提供独特的效果。"
								+ "与天赋不同，<b>特性在加入“生效特性”栏之前不会有任何效果</b>。<br/>"
							+ "天赋需需要天赋点数解锁。[npc.Name]每当升级时便会获得一点天赋点数，并且每五级获得额外两点天赋点数。<br/><br/>"
							+ "除了这些通过天赋点解锁的天赋以外，还存在着一些通过特殊事件解锁的特殊隐藏天赋。"
					+ "</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(getElemental(), true));
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_PERKS) {
				return new Response("天赋", "你正在分配[el.namePos]的天赋点！", null);
			}
			if(responseTab==1 && index==11) {
				return new Response("重置天赋", "重置所有天赋和特性，并且退回所有花费的点数。(临时可用，由于天赋树仍在开发中。)", ELEMENTAL_PERKS) {
					@Override
					public void effects() {
						getElemental().resetPerksMap(false, false);
					}
				};
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_FETISHES = new DialogueNode("[el.NamePos]的性癖", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb = new StringBuilder(
					"<details>"
						+ "<summary>[style.boldFetish(性癖信息)]</summary>"
							+ "你可以为[el.namePos][style.colourArcane(免费)]选择对于各个性癖的[style.colourLust(渴望值)]，"
							+ "或者消耗[style.colourArcane(奥术精华)]直接选择相关的[style.colourFetish(性癖)]。<br/><br/>"
							+ "选择渴望会影响性交中的额外性欲获取，而取得某性癖则会永久将[el.namePos]渴望值设置为“热爱”，并给予[el.herHim]特殊增益。"
							+ "性癖只能通过附魔药水去除。<br/><br/>"
							+ "[el.NamePos]当前选择的渴望值使[el.her]拥有"+PresetColour.FETISH.getName()+"的边框，但[el.her]的真实渴望值(由染色的渴望值图标表示)可以通过附魔衣物或其他物品调整。<br/><br/>"
							+ "[el.name]每次在性交中执行与某性癖相关的动作时，就会获得相应经验。"
							+ "无论[el.she]是否拥有相关性癖，都会获得经验。"
							+ "更高的性癖等级会令[el.name]和[el.her]的对象从相关性动作中获得更高的快感，并且也会提升性癖的增益。<br/><br/>"
							+ "最后，派生性癖无法直接解锁，但在满足要求后会自动解锁。"
					+ "</details>");
			
			// Normal fetishes:

			sb.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>性癖</h6></div>");
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING));
			if(Main.game.isAnalContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			if(Main.game.isLactationContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			if(Main.game.isFootContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
			}
			if(Main.game.isArmpitContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ARMPIT_GIVING, Fetish.FETISH_ARMPIT_RECEIVING));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST));
			if(Main.game.isNonConEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB));
			}

			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BONDAGE_APPLIER, Fetish.FETISH_BONDAGE_VICTIM));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BIMBO, Fetish.FETISH_CROSS_DRESSER));
			if(Main.game.isIncestEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));
				if(Main.game.isPenetrationLimitationsEnabled()) {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_SIZE_QUEEN, null));
				}
			} else {
				if(Main.game.isPenetrationLimitationsEnabled()) {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_SIZE_QUEEN));
				} else {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, null));
				}
			}
			
			// Derived fetishes:

			sb.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>派生性癖</h6></div>");
			sb.append("<div class='fetish-container'>");
			
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					sb.append(
							"<div id='FETISH_" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:rgba(0,0,0,0.7); border-radius:5px;'></div>"))
							+ "</div>");
				}
			}
			
			sb.append("</div>");
			
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_FETISHES) {
				return new Response("性癖", "你正在为[el.namePos]分配性癖！", null);
			}
			//TODO
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_CHOOSE_NAME = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(), 
				"<p>"
					+ "从现在开始，[npc.nameIsFull]将称呼你为“[npc.pcName]”，你不禁思考起该不该让[npc.herHim]换个说法称呼你。"
					+ "[npc.sheIs]不是你的奴隶，你无法随心所欲地改变[npc.her]名字……"
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
						+ UtilText.parse(getElemental(), "[npc.she]对你的称呼")
					+ "</div>"
					
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(getElemental().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
						
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(getElemental().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:22px; line-height:22px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
					
					+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(getElemental().getPetName(Main.game.getPlayer()))
						+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ " <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:12%; height:22px; line-height:22px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "全体奴隶"
					+ "</div>");
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(), 
						"<p style='text-align:center; margin-top:4px;'>"
							+ "<i>如果[npc.name]被告知称呼你为“爸爸”/“妈妈”、“爹地”/“妈咪”、“女主人”/“男主人”或“女士”/“先生”，"
							+ "那么[npc.she]将根据你角色的女性化程度自动切换合适的称呼。</i>"
						+ "</p>"
					+ "</div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_CHOOSE_NAME) {
				return new Response("设定名字", "你正在设定[el.name]的名字！", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
}
