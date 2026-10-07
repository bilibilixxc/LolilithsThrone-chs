package com.lilithsthrone.game.sex.positions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Leg;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexActionInteractions;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAgainstWall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMasturbation;
import com.lilithsthrone.game.sex.positions.slots.SexSlotMilkingStall;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStocks;
import com.lilithsthrone.game.sex.positions.slots.SexSlotTag;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.game.sex.sexActions.SexActionInterface;
import com.lilithsthrone.game.sex.sexActions.SexActionPresets;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericActions;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.GenericOrgasms;
import com.lilithsthrone.game.sex.sexActions.baseActionsMisc.PlayerTalk;
import com.lilithsthrone.game.sex.sexActions.dominion.MasturbationPanties;
import com.lilithsthrone.game.sex.sexActions.universal.ChairSex;
import com.lilithsthrone.game.sex.sexActions.universal.Cowgirl;
import com.lilithsthrone.game.sex.sexActions.universal.DoggyStyleAndProneBone;
import com.lilithsthrone.game.sex.sexActions.universal.FaceSitting;
import com.lilithsthrone.game.sex.sexActions.universal.GloryHole;
import com.lilithsthrone.game.sex.sexActions.universal.KneelingOral;
import com.lilithsthrone.game.sex.sexActions.universal.Masturbation;
import com.lilithsthrone.game.sex.sexActions.universal.MatingPress;
import com.lilithsthrone.game.sex.sexActions.universal.MilkingStall;
import com.lilithsthrone.game.sex.sexActions.universal.Missionary;
import com.lilithsthrone.game.sex.sexActions.universal.MissionaryDesk;
import com.lilithsthrone.game.sex.sexActions.universal.SixtyNine;
import com.lilithsthrone.game.sex.sexActions.universal.StocksSex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * AbstractSexPositions for taurs, including taur-biped interactions.
 * 
 * @since 0.3.1
 * @version 0.3.4
 * @author Innoxia
 */
public class SexPosition {
	
	public static final AbstractSexPosition MASTURBATION = new AbstractSexPosition("",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(
					MasturbationPanties.class,
					Masturbation.class)) {
		@Override
		public Set<SexSlot> getAllAvailableSexPositions() {
			return Util.newHashSetOfValues(SexSlotMasturbation.KNEELING, SexSlotMasturbation.KNEELING_PANTIES, SexSlotMasturbation.SITTING, SexSlotMasturbation.STANDING);
		}
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(characterToTakeSlot.isTaur() && slot==SexSlotMasturbation.SITTING) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "由于[npc.namePos]动物形态的下半身，[npc.she]无法在坐下的时候自慰。"));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING), "[npc.NameIsFull]跪在地上，准备自慰。");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.STANDING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.STANDING), "[npc.NameIsFull]站立着准备自慰。");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.SITTING)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.SITTING), "[npc.NameIsFull]坐下来准备自慰。");
			}
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES)!=null) {
				return UtilText.parse(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES), "[npc.NameIsFull]跪在地上，准备用莉莱雅的内裤自慰。");
			}
			
			return UtilText.parse("你准备好自慰了。");
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> masturbationSlots = Util.newArrayListOfValues(SexSlotMasturbation.KNEELING, SexSlotMasturbation.STANDING, SexSlotMasturbation.SITTING);
			
			if(Main.sex.getCharacterInPosition(SexSlotMasturbation.KNEELING_PANTIES)!=null) {
				interactions.add(StandardSexActionInteractions.masturbation.getSexActionInteractions(SexSlotMasturbation.KNEELING_PANTIES, SexSlotMasturbation.KNEELING_PANTIES));
			} else {
				for(SexSlot slot : masturbationSlots) {
					interactions.add(StandardSexActionInteractions.masturbation.getSexActionInteractions(slot, slot));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return true;
		}
	};
	
	
	public static final AbstractSexPosition STANDING = new AbstractSexPosition("站姿",
			8,
			true,
			SexActionPresets.positioningActionsNew, Util.newArrayListOfValues(KneelingOral.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.STANDING_SUBMISSIVE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.STANDING_SUBMISSIVE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.STANDING_SUBMISSIVE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_FOUR, SexSlotStanding.STANDING_SUBMISSIVE_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			// This position requires at least one character to be standing.
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotStanding.STANDING_DOMINANT
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_TWO
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_THREE
						|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_FOUR) {
					suitablePosition = true;
					break;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "这种姿势至少需要一个角色被分配至站立栏位才能生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			List<String> subNames = new ArrayList<>();
			List<String> subStandingNames = new ArrayList<>();
			List<String> subStandingBehindNames = new ArrayList<>();
			List<String> subKneelingNames = new ArrayList<>();
			List<String> subKneelingBehindNames = new ArrayList<>();
			List<String> subSizeDiffKneelingNames = new ArrayList<>();
			List<String> subSizeDiffBehindNames = new ArrayList<>();
			List<String> domNames = new ArrayList<>();
			
//			List<String> sizeDifferenceAdditions = new ArrayList<>();
			Map<GameCharacter, SexSlot> doms = new HashMap<>();
			Map<GameCharacter, SexSlot> domTaurs = new HashMap<>();
			Map<GameCharacter, SexSlot> subs = new HashMap<>();
			List<GameCharacter> subsStanding = new ArrayList<>();
			List<GameCharacter> subsStandingBehind = new ArrayList<>();
			
			boolean playerInDoms = false;
			boolean playerInSubs = false;
			
			GameCharacter mainDom = null;
			GameCharacter mainSub = null;
			GameCharacter mainStandingSub = null;
			GameCharacter mainStandingBehindSub = null;
			GameCharacter mainKneelingSub = null;
			GameCharacter mainBehindSub = null;
			GameCharacter mainKneelingSizeDiffSub = null;
			GameCharacter mainBehindSizeDiffSub = null;
			
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotStanding.STANDING_DOMINANT
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_TWO
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_THREE
							|| e.getValue()==SexSlotStanding.STANDING_DOMINANT_FOUR) {
						if(mainDom==null) {
							mainDom=e.getKey();
						}
						if(!e.getKey().isTaur()) {
							doms.put(e.getKey(), e.getValue());
						} else {
							domTaurs.put(e.getKey(), e.getValue());
						}
						if(e.getKey().isPlayer()) {
							playerInDoms = true;
						}
						
					} else {
						if(e.getKey().isPlayer()) {
							playerInSubs = true;
						}
						subs.put(e.getKey(), e.getValue());
					}
				}
			}
			
			for(Entry<GameCharacter, SexSlot> sub : subs.entrySet()) {
				if(mainSub==null) {
					mainSub = sub.getKey();
				}
				if(sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_TWO
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_THREE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_FOUR) {
					if(sub.getKey().isPlayer()) {
						subStandingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
					} else {
						subStandingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
					}
					if(mainStandingSub==null) {
						mainStandingSub=sub.getKey();
					}
					subsStanding.add(sub.getKey());
					
				} else if(sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE
						|| sub.getValue()==SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR) {
					if(sub.getKey().isPlayer()) {
						subStandingBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
					} else {
						subStandingBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
					}
					if(mainStandingBehindSub==null) {
						mainStandingBehindSub=sub.getKey();
					}
					subsStandingBehind.add(sub.getKey());
					
				} else if(sub.getValue()==SexSlotStanding.PERFORMING_ORAL
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_TWO
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_THREE
						|| sub.getValue()==SexSlotStanding.PERFORMING_ORAL_FOUR) {
					if(sub.getKey().isSizeDifferenceShorterThan(mainDom)) {
						if(sub.getKey().isPlayer()) {
							subSizeDiffKneelingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subSizeDiffKneelingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainKneelingSizeDiffSub==null) {
							mainKneelingSizeDiffSub=sub.getKey();
						}
					} else {
						if(sub.getKey().isPlayer()) {
							subKneelingNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subKneelingNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainKneelingSub==null) {
							mainKneelingSub=sub.getKey();
						}
					}
					
				} else {
					if(sub.getKey().isSizeDifferenceShorterThan(mainDom)) {
						if(sub.getKey().isPlayer()) {
							subSizeDiffBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subSizeDiffBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainBehindSizeDiffSub==null) {
							mainBehindSizeDiffSub=sub.getKey();
						}
					} else {
						if(sub.getKey().isPlayer()) {
							subKneelingBehindNames.add(0, UtilText.parse(sub.getKey(), "[npc.name]"));
						} else {
							subKneelingBehindNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
						}
						if(mainBehindSub==null) {
							mainBehindSub=sub.getKey();
						}
					}
				}
				subNames.add(UtilText.parse(sub.getKey(), "[npc.name]"));
			}
			
			for(GameCharacter c : doms.keySet()) {
				if(c.isPlayer()) {
					domNames.add(0, UtilText.parse(c, "[npc.name]"));
				} else {
					domNames.add(UtilText.parse(c, "[npc.name]"));
				}
			}
			for(GameCharacter c : domTaurs.keySet()) {
				if(c.isPlayer()) {
					domNames.add(0, UtilText.parse(c, "[npc.name]"));
				} else {
					domNames.add(UtilText.parse(c, "[npc.name]"));
				}
			}
			
			int totalDoms = doms.size()+domTaurs.size();
			if(totalDoms>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(domNames, false))+"正肩并肩地站在");
			} else {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(domNames, false))
						+"正站在");
			}

			sb.append(Util.stringsToStringList(subNames, false)+"的面前，期待着跟"
					+(subNames.size()>1
							?(playerInSubs?(playerInSubs?"你们"+Util.intToString(subs.size())+"个":"他们"):"他们")
							:UtilText.parse(subs.keySet().iterator().next(), "[npc.herHim]"))+"爽一爽。");
			
			// Standing:
			if(subStandingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingNames, false))
						+"也同样挨着站在"+(totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们"+Util.intToString(totalDoms)+"个":"他们"))+"面前。");
				
			} else if(subStandingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingNames, false))
						+"则站在"+(totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们"+Util.intToString(totalDoms)+"个":"他们"))+"面前。");
			}

			// Kneeling:
			if(subKneelingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingNames, false))
						+"挨着跪在");
				if(domTaurs.isEmpty()) {
					sb.append((totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们":"他们"+Util.intToString(totalDoms)+"个"))+"面前，准备好口交。" );
				} else if(doms.isEmpty()) {
					sb.append("地上，准备好交替向前，在"
								+(totalDoms==1?UtilText.parse(mainDom, "[npc.her]动物般的身躯"):(playerInDoms?"你们某个动物般的身躯":"他们某个动物般的身躯"))+"下依次进行口交。");
				} else {
					sb.append("地上，准备好交替向前，依次进行口交。");
				}
				
			} else if(subKneelingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingNames, false))
						+"跪在");
				if(domTaurs.isEmpty()) {
					sb.append((totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们":"他们"+Util.intToString(totalDoms)+"个"))+"面前，准备好口交。");
				} else if(doms.isEmpty()) {
					sb.append("地上，准备好交替向前，在"
								+(totalDoms==1?UtilText.parse(mainDom, "[npc.her]动物般的身躯"):(playerInDoms?"你们某个动物般的身躯":"他们某个动物般的身躯"))+"下依次进行口交。");
				} else {
					sb.append("在地上，准备好交替向前，依次进行口交。");
				}
			}
			// Kneeling size difference:
			if(subSizeDiffKneelingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffKneelingNames, false))
						+"已经准备好口交，但由于"+(playerInSubs?"你们":"他们")+"身材矮小，无须跪下，只要站在"
						+(totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们":"他们"+Util.intToString(totalDoms)+"个"))+"面前。");
				
			} else if(subSizeDiffKneelingNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffKneelingNames, false))
						+UtilText.parse(mainKneelingSizeDiffSub, "已经准备好口交，但由于[npc.her]身材矮小，无须跪下，只要站在")
						+(totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们":"他们"+Util.intToString(totalDoms)+"个"))+"面前。");
			}

			// Standing behind:
			if(subStandingBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingBehindNames, false))
						+"正肩并肩地站在身后。");
				
			} else if(subStandingBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subStandingBehindNames, false))
						+"正站在"+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"你们":"他们"))+"身后。");
			}
			
			// Kneeling behind:
			if(subKneelingBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingBehindNames, false))
						+"正紧挨着跪在");
				if(domTaurs.isEmpty()) {
					sb.append((totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们":"他们"))+"身后，准备好舔肛。");
				} else if(doms.isEmpty()) {
					sb.append(
							(totalDoms==1
								?UtilText.parse(mainDom, "[npc.herHim]身后，准备好对[npc.her]动物般的后半身进行口交。")
								:(playerInDoms
									?"你们身后，准备好对你们其中一个的动物般的后半身进行口交。"
									:"他们身后，准备好对他们其中一个的动物般的后半身进行口交。")));
				} else {
					sb.append("地上，准备好对"+(totalDoms==1?UtilText.parse(mainDom, "[npc.her]的后半身进行口交。"):(playerInDoms?"你们的后半身进行口交。":"他们的后半身进行口交。")));
				}
				
			} else if(subKneelingBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subKneelingBehindNames, false))
						+"跪在"+(totalDoms==1?UtilText.parse(mainDom, "[npc.herHim]"):(playerInDoms?"你们":"他们"))+"身后");
				if(domTaurs.isEmpty()) {
					sb.append("，准备好舔肛。");
				} else if(doms.isEmpty()) {
					sb.append((totalDoms==1
								?UtilText.parse(mainDom, "，准备好对[npc.her]动物般的后半身进行口交。")
								:(playerInDoms
									?"，准备好对你们其中一个的动物般的后半身进行口交。"
									:"，准备好对他们其中一个的动物般的后半身进行口交。")));
				} else {
					sb.append("，准备好对"+(totalDoms==1?UtilText.parse(mainDom, "[npc.her]的后半身进行口交。"):(playerInDoms?"你们的后半身进行口交。":"他们的后半身进行口交。")));
				}
			}
			// Kneeling behind size difference:
			if(subSizeDiffBehindNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffBehindNames, false))
						+"移动到"+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"你们":"他们"+Util.intToString(totalDoms)+"个"))
						+ "身后，准备好对"+(totalDoms==1?UtilText.parse(mainDom, "[npc.her]的后半身进行口交"):(playerInDoms?"你们的后半身进行口交":"他们的后半身进行口交"))
						+"，但由于"+(playerInSubs?"你们":"他们")+"身材矮小，无须跪下，只要站立即可。");
				
			} else if(subSizeDiffBehindNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(subSizeDiffBehindNames, false))
						+UtilText.parse(mainBehindSizeDiffSub, "移动到")+(totalDoms==1?UtilText.parse(mainDom, "[npc.name]"):(playerInDoms?"你们":"他们"+Util.intToString(totalDoms)+"个"))
						+ "身后，准备好对"+(totalDoms==1?UtilText.parse(mainDom, "[npc.her]的后半身进行口交"):(playerInDoms?"你们的后半身进行口交":"他们的后半身进行口交"))
						+UtilText.parse(mainBehindSizeDiffSub, "，但由于[npc.her]身材矮小，无须跪下，只要站立即可。"));
			}
			

			// Size difference:
//			List<GameCharacter> allDoms = new ArrayList<>(doms.keySet());
//			allDoms.addAll(domTaurs.keySet());
//			playerInDoms = false;
//			playerInSubs = false;
//			for(GameCharacter sub : subsStanding) {
//				mainSub = sub;
//				List<String> names = new ArrayList<>();
//				for(GameCharacter dom : allDoms) {
//					if(sub.isSizeDifferenceShorterThan(dom)) {
//						names.add(UtilText.parse(dom, "[npc.name]"));
//						if(dom.isPlayer()) {
//							playerInDoms = true;
//						}
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(sub,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "+(names.size()>1?(playerInDoms?"you":"them"):UtilText.parse(mainDom, "[npc.herHim]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(GameCharacter sub : subsStandingBehind) {
//				mainSub = sub;
//				List<String> names = new ArrayList<>();
//				for(GameCharacter dom : allDoms) {
//					if(sub.isSizeDifferenceShorterThan(dom)) {
//						names.add(UtilText.parse(dom, "[npc.name]"));
//						if(dom.isPlayer()) {
//							playerInDoms = true;
//						}
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(sub,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "
//									+(names.size()>1?(playerInDoms?"your rear end":"their rear ends"):UtilText.parse(mainDom, "[npc.namePos] [npc.ass+]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(GameCharacter dom : allDoms) {
//				List<String> names = new ArrayList<>();
//				for(GameCharacter sub : subsStanding) {
//					if(dom.isSizeDifferenceShorterThan(sub)) {
//						names.add(UtilText.parse(sub, "[npc.name]"));
//					}
//					if(sub.isPlayer()) {
//						playerInSubs = true;
//					}
//				}
//				if(!names.isEmpty()) {
//					sizeDifferenceAdditions.add(UtilText.parse(dom,
//							"As [npc.nameIsFull] considerably shorter than "+Util.stringsToStringList(names, false)
//								+", [npc.sheIs] in a position to perform oral on "+(names.size()>1?(playerInSubs?"you":"them"):UtilText.parse(mainSub, "[npc.herHim]"))+", even though [npc.sheIs] standing fully upright."));
//				}
//			}
//			for(String s : sizeDifferenceAdditions) {
//				sb.append("<br/>[style.italicsOrange("+s+")]");
//			}
			
			// Size difference:
			Map<GameCharacter, List<String>> shorterMap = new HashMap<>(); // Mapping short character to list of larger characters
			for(GameCharacter c1 : occupiedSlots.keySet()) {
				for(GameCharacter c2 : occupiedSlots.keySet()) {
					if(c1!=c2 && c1.isSizeDifferenceShorterThan(c2)) {
						shorterMap.putIfAbsent(c1, new ArrayList<>());
						shorterMap.get(c1).add(UtilText.parse(c2, "<span style='color:"+c2.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
					}
				}
			}
			if(!shorterMap.isEmpty()) {
				sb.append("<br/>[style.italicsOrange(部分角色比其他角色矮得多，所以即使是站立着也能对较高的对象进行口交: )]");
				for(Entry<GameCharacter, List<String>> entry : shorterMap.entrySet()) {
					sb.append(UtilText.parse(entry.getKey(), "<br/><span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>比"));
					sb.append("");
					sb.append(Util.stringsToStringList(entry.getValue(), false));
					sb.append("矮。");
				}
			}
						
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			
			List<SexSlot> domStanding = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_DOMINANT, SexSlotStanding.STANDING_DOMINANT_TWO, SexSlotStanding.STANDING_DOMINANT_THREE, SexSlotStanding.STANDING_DOMINANT_FOUR);
			List<SexSlot> subStanding = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_SUBMISSIVE, SexSlotStanding.STANDING_SUBMISSIVE_TWO, SexSlotStanding.STANDING_SUBMISSIVE_THREE, SexSlotStanding.STANDING_SUBMISSIVE_FOUR);
			List<SexSlot> subStandingBehind = Util.newArrayListOfValues(
					SexSlotStanding.STANDING_SUBMISSIVE_BEHIND, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_TWO, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_THREE, SexSlotStanding.STANDING_SUBMISSIVE_BEHIND_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(
					SexSlotStanding.PERFORMING_ORAL, SexSlotStanding.PERFORMING_ORAL_TWO, SexSlotStanding.PERFORMING_ORAL_THREE, SexSlotStanding.PERFORMING_ORAL_FOUR);
			List<SexSlot> performingOralBehind = Util.newArrayListOfValues(
					SexSlotStanding.PERFORMING_ORAL_BEHIND, SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO, SexSlotStanding.PERFORMING_ORAL_BEHIND_THREE, SexSlotStanding.PERFORMING_ORAL_BEHIND_FOUR);
			
			// Adding faceToFace for every dominant to every submissive:
			for(SexSlot slotD : domStanding) {
				for(SexSlot slotD2 : subStanding) {
					if(slotD!=slotD2) {
						interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotD, slotD2));
					}
				}
				for(SexSlot slotS : subStanding) {
					interactions.add(StandardSexActionInteractions.faceToFace.getSexActionInteractions(slotD, slotS));
					for(SexSlot slotS2 : subStanding) {
						if(slotS!=slotS2) {
							interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotS, slotS2));
						}
					}
				}
				for(SexSlot slotS : subStandingBehind) {
					interactions.add(StandardSexActionInteractions.standingBehind.getSexActionInteractions(slotS, slotD));
					for(SexSlot slotS2 : subStandingBehind) {
						if(slotS!=slotS2) {
							interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(slotS, slotS2));
						}
					}
				}
			}
			
			// Adding performingOral for to every dominant:
			for(SexSlot performingO : performingOral) {
				for(SexSlot slotD : domStanding) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(performingO, slotD));
				}
			}

			// Adding performingOralBehind for to every dominant:
			for(SexSlot performingOB : performingOralBehind) {
				for(SexSlot slotD : domStanding) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(performingOB, slotD));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotStanding.PERFORMING_ORAL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotStanding.STANDING_DOMINANT_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return true;
		}
	};
	

	public static final AbstractSexPosition AGAINST_WALL = new AbstractSexPosition("面对[pc.wall]",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			new ArrayList<>()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.BACK_TO_WALL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.BACK_TO_WALL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.BACK_TO_WALL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL_FOUR, SexSlotAgainstWall.BACK_TO_WALL_FOUR));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL_FOUR, SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePositionStandingOrOral=false;
			boolean suitablePositionWall=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotAgainstWall.FACE_TO_WALL
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_FOUR
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_FOUR) {
					suitablePositionWall = true;
				}
				if(e.getValue()==SexSlotAgainstWall.STANDING_WALL
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_FOUR
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE
						|| e.getValue()==SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR) {
					suitablePositionStandingOrOral = true;
				}
			}
			if(!suitablePositionWall || !suitablePositionStandingOrOral) {
				return new Value<Boolean, String>(false, "至少一个角色面向[pc.a_wall]，另外有角色站立或提供口交，才能使该姿势生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			List<String> standingNames = new ArrayList<>();
			List<String> facingWallNames = new ArrayList<>();
			List<String> backToWallNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			
			GameCharacter mainStanding = null;
			GameCharacter mainFacingWall = null;
			GameCharacter mainBackToWall = null;
			GameCharacter mainPerformingOral = null;
			
			boolean playerFacingWall = false;
			boolean playerBackToWall = false;
			boolean playerPerformingOral = false;
			
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotAgainstWall.STANDING_WALL
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.STANDING_WALL_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotAgainstWall.FACE_TO_WALL
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.FACE_TO_WALL_FOUR) {
						if(mainFacingWall==null) {
							mainFacingWall=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							facingWallNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							facingWallNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerFacingWall = true;
						}
						
					} else if(e.getValue()==SexSlotAgainstWall.BACK_TO_WALL
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_TWO
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_THREE
							|| e.getValue()==SexSlotAgainstWall.BACK_TO_WALL_FOUR) {
						if(mainBackToWall==null) {
							mainBackToWall=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							backToWallNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							backToWallNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerBackToWall = true;
						}
						
					} else {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						if(e.getKey().isPlayer()) {
							playerPerformingOral = true;
						}
					}
				}
			}

			int facingWallCount = facingWallNames.size();
			if(facingWallCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(facingWallNames, false))+"正肩并肩地面对着附近的[pc.wall]");
				
			} else if(facingWallCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(facingWallNames, false))+UtilText.parse(mainFacingWall, "正面对着附近的[pc.wall]"));
			}
			
			if(backToWallNames.size()>=2) {
				if(facingWallCount>0) {
					sb.append("，而"+Util.stringsToStringList(backToWallNames, false)+"则站在"
								+(facingWallCount==1?UtilText.parse(mainFacingWall,"[npc.herHim]"):(playerBackToWall?"你们":"他们"))+"旁边，背对着[pc.wall]。");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(backToWallNames, false))+"挨着站在一起，后半身顶在身后的[pc.wall]上。");
				}
				
			} else if(backToWallNames.size()==1) {
				if(facingWallCount>0) {
					sb.append("，而"+Util.stringsToStringList(backToWallNames, false)+UtilText.parse(mainBackToWall, "则站在")
							+(facingWallCount==1?UtilText.parse(mainFacingWall,"[npc.herHim]"):(playerBackToWall?"你们":"他们"))+"身边，背对着[pc.wall]。");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(backToWallNames, false))+UtilText.parse(mainBackToWall, "站立着，后半身顶在身后的[pc.wall]上。"));
				}
			} else {
				if(facingWallCount>1) {
					sb.append("，不时向后窥视着。");
				} else {
					sb.append(UtilText.parse(mainFacingWall, "，不时向后窥视着。"));
				}
			}

			int totalAgainstWall = facingWallCount + backToWallNames.size();
			int standingCount = standingNames.size();
			GameCharacter mainWall = mainBackToWall==null?mainFacingWall:mainBackToWall;
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+"移动到正好跟"
							+(totalAgainstWall>1?(playerFacingWall || playerBackToWall?"你们":"他们"):UtilText.parse(mainWall, "[npc.name]"))+"面对面的位置");
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding,"移动到正好跟")+(totalAgainstWall>1?(playerFacingWall || playerBackToWall?"你们":"他们"):UtilText.parse(mainWall, "[npc.name]"))+"面对面的位置");
			}
			
			if(performingOralNames.size()>=2) {
				if(standingCount>0) {
					sb.append("，而"+Util.stringsToStringList(performingOralNames, false)+"则跪在地上，准备好给"
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"你们":"他们"))+"提供口交。");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+"移动到正后方，准备好给"
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"你们":"他们"))+"提供口交。");
				}
				
			} else if(performingOralNames.size()==1) {
				if(standingCount>0) {
					sb.append("，而"+Util.stringsToStringList(performingOralNames, false)+UtilText.parse(mainPerformingOral, "则跪在地上，准备好给")
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"你们":"他们"))+"提供口交。");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral, "移动到正后方，准备好给")
								+(totalAgainstWall==1?UtilText.parse(mainWall,"[npc.name]"):(playerFacingWall || playerBackToWall?"你们":"他们"))+"提供口交。");
				}
				
			} else {
				if(totalAgainstWall>1) {
					sb.append("，期待着跟"+(playerFacingWall?"你们":"他们")+Util.intToString(totalAgainstWall)+"个爽一番。");
				} else {
					sb.append("，期待着跟"+UtilText.parse(mainWall,"[npc.herHim]")+"爽一番。");
				}
			}
			

			// Size difference:
			Map<GameCharacter, List<String>> shorterMap = new HashMap<>(); // Mapping short character to list of larger characters
			for(GameCharacter c1 : occupiedSlots.keySet()) {
				for(GameCharacter c2 : occupiedSlots.keySet()) {
					if(c1!=c2 && c1.isSizeDifferenceShorterThan(c2)) {
						shorterMap.putIfAbsent(c1, new ArrayList<>());
						shorterMap.get(c1).add(UtilText.parse(c2, "<span style='color:"+c2.getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>"));
					}
				}
			}
			if(!shorterMap.isEmpty()) {
				sb.append("<br/>[style.italicsOrange(部分角色比其他角色矮得多，所以即使是站立着也能对较高的对象进行口交: )]");
				for(Entry<GameCharacter, List<String>> entry : shorterMap.entrySet()) {
					sb.append(UtilText.parse(entry.getKey(), "<br/><span style='color:"+entry.getKey().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</span>比"));
					sb.append("");
					sb.append(Util.stringsToStringList(entry.getValue(), false));
					sb.append("矮。");
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> facingWall = Util.newArrayListOfValues(SexSlotAgainstWall.FACE_TO_WALL, SexSlotAgainstWall.FACE_TO_WALL_TWO, SexSlotAgainstWall.FACE_TO_WALL_THREE, SexSlotAgainstWall.FACE_TO_WALL_FOUR);
			List<SexSlot> backToWall = Util.newArrayListOfValues(SexSlotAgainstWall.BACK_TO_WALL, SexSlotAgainstWall.BACK_TO_WALL_TWO, SexSlotAgainstWall.BACK_TO_WALL_THREE, SexSlotAgainstWall.BACK_TO_WALL_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotAgainstWall.STANDING_WALL, SexSlotAgainstWall.STANDING_WALL_TWO, SexSlotAgainstWall.STANDING_WALL_THREE, SexSlotAgainstWall.STANDING_WALL_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotAgainstWall.PERFORMING_ORAL_WALL, SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO, SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE, SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR);
			
			// Assign all actions to all recipients.
			// It might make more sense to limit actions to just the target "in front" of the standing or oral-performing character, but then it would require a bit of micro-management for the player to move into the correct slot.
			// I decided that giving the performing characters a wide range of targets was better than making them micro-manage their slots.
			
			for(SexSlot slotStanding : standing) {
				for(SexSlot slotFacing : facingWall) {
					interactions.add(StandardSexActionInteractions.standingBehindCharacterFacingWall.getSexActionInteractions(slotStanding, slotFacing));
				}
				for(SexSlot slotBack : backToWall) {
					interactions.add(StandardSexActionInteractions.standingBehindCharacterBackToWall.getSexActionInteractions(slotStanding, slotBack));
				}
			}
			for(SexSlot slotOral : performingOral) {
				for(SexSlot slotFacing : facingWall) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(slotOral, slotFacing));
				}
				for(SexSlot slotBack : backToWall) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(slotOral, slotBack));
				}
			}

			// Those standing beside one another can kiss:
			for(int i=0;i<3;i++) {
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(backToWall.get(i), facingWall.get(i)));
				
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(backToWall.get(i), backToWall.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(facingWall.get(i), facingWall.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(backToWall.get(i), facingWall.get(i+1)));
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.PERFORMING_ORAL_WALL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_FOUR
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.FACE_TO_WALL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character pressing another against the wall can use body weight to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAgainstWall.STANDING_WALL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAgainstWall.BACK_TO_WALL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.FACE_TO_WALL)) {
				return false;
			}
			return true;
		}
	};
	

	public static final AbstractSexPosition OVER_DESK = new AbstractSexPosition("[pc.desk]上",
			8,
			true,
			SexActionPresets.positioningActionsNew, Util.newArrayListOfValues(MissionaryDesk.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			Map<SexSlot, GameCharacter> reversedPositioningSlotsMap = new HashMap<>();
			for(Entry<GameCharacter, SexSlot> entry : positioningSlots.entrySet()) {
				reversedPositioningSlotsMap.put(entry.getValue(), entry.getKey());
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS_FOUR, SexSlotDesk.PERFORMING_ORAL_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_BACK));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_BACK_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_BACK_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR, SexSlotDesk.OVER_DESK_ON_BACK_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}

			if((slot==SexSlotDesk.HUMPING && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK))
					|| (slot==SexSlotDesk.HUMPING_TWO && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_TWO) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_TWO))
					|| (slot==SexSlotDesk.HUMPING_THREE && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_THREE) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_THREE))
					|| (slot==SexSlotDesk.HUMPING_FOUR && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) && !positioningSlots.containsValue(SexSlotDesk.OVER_DESK_ON_BACK_FOUR))) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "没有人在桌子上，所以无法趴到背上！"));
			}

			// Cannot hump when the person standing behind the intended target is a taur:
			if((slot==SexSlotDesk.HUMPING && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS), "[npc.Name]不能津贴上去，因为[npc2.namePos]的[npc2.legRace]身躯挡住了！"));
			} else if((slot==SexSlotDesk.HUMPING_TWO && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_TWO) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_TWO).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_TWO), "[npc.Name]不能紧贴上去，因为[npc2.namePos]的[npc2.legRace]身躯挡住了！"));
			} else if((slot==SexSlotDesk.HUMPING_THREE && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_THREE) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_THREE).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_THREE), "[npc.Name]不能趴到背上，因为[npc2.namePos]的[npc2.legRace]身躯挡住了！"));
			} else if((slot==SexSlotDesk.HUMPING_FOUR && positioningSlots.containsValue(SexSlotDesk.BETWEEN_LEGS_FOUR) && reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_FOUR).isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.BETWEEN_LEGS_FOUR), "[npc.Name]不能趴到背上，因为[npc2.namePos]的[npc2.legRace]身躯挡住了！"));
			}
			// And vice-versa:
			if((slot==SexSlotDesk.BETWEEN_LEGS && positioningSlots.containsValue(SexSlotDesk.HUMPING) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING), "[npc.NamePos]的[npc2.legRace]身躯上不去，因为[npc2.name]挡住了！"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_TWO && positioningSlots.containsValue(SexSlotDesk.HUMPING_TWO) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_TWO), "[npc.NamePos]的[npc2.legRace]身躯上不去，因为[npc2.name]挡住了！"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_THREE && positioningSlots.containsValue(SexSlotDesk.HUMPING_THREE) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_THREE), "[npc.NamePos]的[npc2.legRace]身躯上不去，因为[npc2.name]挡住了！"));
			} else if((slot==SexSlotDesk.BETWEEN_LEGS_FOUR && positioningSlots.containsValue(SexSlotDesk.HUMPING_FOUR) && characterToTakeSlot.isTaur())) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, reversedPositioningSlotsMap.get(SexSlotDesk.HUMPING_FOUR), "[npc.NamePos]的[npc2.legRace]身躯上不去，因为[npc2.name]挡住了！"));
			}
			
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotDesk.OVER_DESK_ON_BACK
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| slot==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "由于[npc.namePos]动物般的下肢，[npc.her]没法平躺在[pc.desk]上。"));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePositionStandingOrOral=false;
			boolean suitablePositionDesk=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_FOUR
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
						|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) {
					suitablePositionDesk = true;
				}
				if(e.getValue()==SexSlotDesk.BETWEEN_LEGS
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_TWO
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_THREE
						|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_FOUR
						|| e.getValue()==SexSlotDesk.HUMPING
						|| e.getValue()==SexSlotDesk.HUMPING_TWO
						|| e.getValue()==SexSlotDesk.HUMPING_THREE
						|| e.getValue()==SexSlotDesk.HUMPING_FOUR
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_TWO
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_THREE
						|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_FOUR
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_TWO
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_THREE
						|| e.getValue()==SexSlotDesk.RECEIVING_ORAL_FOUR) {
					suitablePositionStandingOrOral = true;
				}
			}
			if(!suitablePositionDesk || !suitablePositionStandingOrOral) {
				return new Value<Boolean, String>(false, "至少需要一个角色在[pc.desk]上，另外有角色站立、趴背、接受口交或提供口交，才能使该姿势生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			List<String> deskBackNames = new ArrayList<>();
			List<String> deskFrontNames = new ArrayList<>();
			List<String> deskFrontTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainDeskFront = null;
			GameCharacter mainDeskFrontTaur = null;
			GameCharacter mainDeskBack = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerDeskFront = false;
			boolean playerDeskBack = false;

			// TODO Characters under 80cm tall need to be described as having climbed up onto the desk.
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR) {
						if(mainDeskFront==null && !e.getKey().isTaur()) {
							mainDeskFront=e.getKey();
						}
						if(mainDeskFrontTaur==null && e.getKey().isTaur()) {
							mainDeskFrontTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerDeskFront = true;
							if(!e.getKey().isTaur()) {
								deskFrontNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								deskFrontTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								deskFrontNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								deskFrontTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_TWO
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_THREE
							|| e.getValue()==SexSlotDesk.OVER_DESK_ON_BACK_FOUR) {
						if(mainDeskBack==null) {
							mainDeskBack=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerDeskBack = true;
							deskBackNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							deskBackNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotDesk.BETWEEN_LEGS
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_TWO
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_THREE
							|| e.getValue()==SexSlotDesk.BETWEEN_LEGS_FOUR) {
						if(mainStanding==null && !e.getKey().isTaur()) {
							mainStanding=e.getKey();
						}
						if(mainStandingTaur==null && e.getKey().isTaur()) {
							mainStandingTaur=e.getKey();
						}
						
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotDesk.HUMPING
							|| e.getValue()==SexSlotDesk.HUMPING_TWO
							|| e.getValue()==SexSlotDesk.HUMPING_THREE
							|| e.getValue()==SexSlotDesk.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotDesk.PERFORMING_ORAL
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotDesk.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			
			int deskBackCount = deskBackNames.size();
			if(deskBackCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskBackNames, false))+"平躺在[pc.desk]上");
				
			} else if(deskBackCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskBackNames, false))+UtilText.parse(mainDeskBack, "平躺在[pc.desk]上"));
			}
			
			if(deskFrontNames.size()>=2) {
				if(deskBackCount>0) {
					sb.append("，而"+Util.stringsToStringList(deskFrontNames, false)+"则趴在"
								+(deskBackCount==1?UtilText.parse(mainDeskBack,"[npc.herHim]"):(playerDeskBack?"你们":"他们"))+"旁边。");
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontNames, false))+"俯卧在[pc.desk]上。"); 
				}
				
			} else if(deskFrontNames.size()==1) {
				if(deskBackCount>0) {
					sb.append("，而"+Util.stringsToStringList(deskFrontNames, false)+UtilText.parse(mainDeskFront, "则俯卧在[pc.desk]上。"));
				} else {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontNames, false))+UtilText.parse(mainDeskFront, "俯卧在[pc.desk]上。"));
				}
				
			} else if(deskBackCount>0) {
				if(deskBackCount>1) {
					sb.append("，跟另一个人紧挨着。");
				} else {
					sb.append(UtilText.parse(mainDeskBack, "，准备好挨操。"));
				}
			}
			
			// Desk taurs:
			if(deskFrontTaurNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))+"用前腿踩在[pc.desk]顶上，然后让动物般的下肢躺在了上面。");
				
			} else if(deskFrontTaurNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))+UtilText.parse(mainDeskFrontTaur,
						"用前腿踩在[pc.desk]顶上，然后让动物般的下肢躺在了上面。"));
			}
			

			// Standing between legs:
			
			int standingCount = standingNames.size();
			int totalDeskCount = deskBackCount + deskFrontNames.size() + deskFrontTaurNames.size();
			GameCharacter mainDesk = mainDeskBack==null
					?mainDeskFront==null
						?mainDeskFrontTaur
						:mainDeskFront
					:mainDeskBack;
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+"上前来，正站在"
							+(totalDeskCount>1?(playerDeskBack||playerDeskFront?"你们的腿":"他们的腿"):UtilText.parse(mainDesk, "[npc.namePos]的[npc.legs]"))+"之间");
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding,"上前来，正站在")
							+(totalDeskCount>1?(playerDeskBack||playerDeskFront?"你们的腿":"他们的腿"):UtilText.parse(mainDesk, "[npc.namePos]的[npc.legs]"))+"之间");
			}
			if(standingCount>0) {
				if(totalDeskCount>1) {
					sb.append("，期待着跟"+(playerDeskBack||playerDeskFront?"你们":"他们")+Util.intToString(totalDeskCount)+"个爽一番。");
				} else {
					sb.append("，期待着跟"+UtilText.parse(mainDesk,"[npc.herHim]")+"爽一番。");
				}
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+"用前腿踩在[pc.desk]顶上，准备用其兽态的下半身");
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStandingTaur,"用前腿踩在[pc.desk]顶上，准备用其兽态的下半身"));
			}
			if(standingCountTaurs>0) {
				if(totalDeskCount>1) {
					sb.append("骑在"+(playerDeskBack||playerDeskFront?"你们":"他们")+Util.intToString(totalDeskCount)+"个上面。");
				} else {
					sb.append("骑在"+UtilText.parse(mainDesk,"[npc.herHim]")+"上面。");
				}
			}
			

			// Humping:
			
			int humpingCount = humpingNames.size();
			if(humpingCount>0) {
				if(humpingCount>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+"迫切地爬上[pc.desk]边缘，准备好贴在各自的对象身上。");
					
				} else if(humpingCount==1) {
					sb.append(UtilText.parse(mainHumping, "[npc.Name]迫切地爬上[pc.desk]边缘，准备好贴在"));
					if(totalDeskCount>1) {
						sb.append(UtilText.parse(mainHumping,"[npc.her]的对象身上。"));
					} else {
						sb.append(UtilText.parse(mainDesk,"[npc.name]")+"的身上。");
					}
				}
			}
			
			
			// Performing oral:
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+"正好移动到"
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"你们的腿":"他们的腿")+"之间，准备好给"+(playerDeskBack||playerDeskFront?"你们口交。":"他们口交。")
									:UtilText.parse(mainDesk, "[npc.namePos]的[npc.legs]之间，准备好给[npc.herHim]口交。")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral,"正好移动到")
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"你们的腿":"他们的腿")+"之间，准备好给"+(playerDeskBack||playerDeskFront?"你们口交。":"他们口交。")
									:UtilText.parse(mainDesk, "[npc.namePos]的[npc.legs]之间，准备好给[npc.herHim]口交。")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?"同时也":"")+"移动到[pc.desk]的另一边，靠近"
							+(totalDeskCount>1
									?(playerDeskBack||playerDeskFront?"你们的脸":"他们的脸")+"，准备好接受口交。"
									:UtilText.parse(mainDesk, "[npc.namePos]的[npc.face]，准备好接受口交。")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?"同时也":"")+UtilText.parse(mainReceivingOral, "移动到[pc.desk]的另一边，靠近")
						+(totalDeskCount>1
								?(playerDeskBack||playerDeskFront?"你们的脸":"他们的脸")+"，准备好接受口交。"
								:UtilText.parse(mainDesk, "[npc.namePos]的[npc.face]，准备好接受口交。")));
			}
			


			// Size difference:
			// Taurs cannot bend down far enough to perform oral.
			boolean playerTaurPerformingOral = playerDeskFront&&Main.game.getPlayer().isTaur();
			List<String> sizeDifferenceAdditions = new ArrayList<>();
			
			if(!deskFrontTaurNames.isEmpty() && !receivingOralNames.isEmpty()) {
				if(deskFrontTaurNames.size()>=2) {
					sizeDifferenceAdditions.add(
							Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))
								+"无法给"+Util.stringsToStringChoice(receivingOralNames, false)+"提供口交，因为"
									+(playerTaurPerformingOral
											?"你人形的上半身没法弯到那么低。"
											:"他们人形的上半身没法弯到那么低。"));
				} else {
					sizeDifferenceAdditions.add(
							Util.capitaliseSentence(Util.stringsToStringList(deskFrontTaurNames, false))
								+UtilText.parse(mainDeskFrontTaur,
										"无法给"+Util.stringsToStringChoice(receivingOralNames, false)+"提供口交，因为[npc.sheIsFull]人形的上半身没法弯到那么低。"));
				}
			}
			
			for(String s : sizeDifferenceAdditions) {
				sb.append("<br/>[style.italicsOrange("+s+")]");
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> onDeskBack = Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_BACK, SexSlotDesk.OVER_DESK_ON_BACK_TWO, SexSlotDesk.OVER_DESK_ON_BACK_THREE, SexSlotDesk.OVER_DESK_ON_BACK_FOUR);
			List<SexSlot> onDeskFront = Util.newArrayListOfValues(SexSlotDesk.OVER_DESK_ON_FRONT, SexSlotDesk.OVER_DESK_ON_FRONT_TWO, SexSlotDesk.OVER_DESK_ON_FRONT_THREE, SexSlotDesk.OVER_DESK_ON_FRONT_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotDesk.BETWEEN_LEGS, SexSlotDesk.BETWEEN_LEGS_TWO, SexSlotDesk.BETWEEN_LEGS_THREE, SexSlotDesk.BETWEEN_LEGS_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotDesk.HUMPING, SexSlotDesk.HUMPING_TWO, SexSlotDesk.HUMPING_THREE, SexSlotDesk.HUMPING_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotDesk.PERFORMING_ORAL, SexSlotDesk.PERFORMING_ORAL_TWO, SexSlotDesk.PERFORMING_ORAL_THREE, SexSlotDesk.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotDesk.RECEIVING_ORAL, SexSlotDesk.RECEIVING_ORAL_TWO, SexSlotDesk.RECEIVING_ORAL_THREE, SexSlotDesk.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskBack.getSexActionInteractions(slotStanding, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskFront.getSexActionInteractions(slotStanding, slotFront));
				}
				for(SexSlot receivingSlot : receivingOral) {
					interactions.add(StandardSexActionInteractions.standingDeskToReceivingOral.getSexActionInteractions(slotStanding, receivingSlot));
				}
			}

			for(SexSlot slotHumping : humping) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskBack.getSexActionInteractions(slotHumping, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.standingBeforeDeskFront.getSexActionInteractions(slotHumping, slotFront));
				}
				for(SexSlot receivingSlot : receivingOral) {
					interactions.add(StandardSexActionInteractions.standingDeskToReceivingOral.getSexActionInteractions(slotHumping, receivingSlot));
				}
			}
			
			for(SexSlot slotOral : performingOral) {
				for(SexSlot slotBack : onDeskBack) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(slotOral, slotBack));
				}
				for(SexSlot slotFront : onDeskFront) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(slotOral, slotFront));
				}
			}
			
			for(SexSlot slotBack : onDeskBack) {
				for(SexSlot slotOral : receivingOral) {
					interactions.add(StandardSexActionInteractions.lyingOnDeskPerformingOral.getSexActionInteractions(slotBack, slotOral));
				}
			}
			for(SexSlot slotFront : onDeskFront) {
				for(SexSlot slotOral : receivingOral) {
					interactions.add(StandardSexActionInteractions.lyingOnDeskPerformingOral.getSexActionInteractions(slotFront, slotOral));
				}
			}

			// Those on the desk can kiss the ones next to them:
			for(int i=0;i<3;i++) {
//				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskBack.get(i)));
//				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskFront.get(i), onDeskFront.get(i)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskFront.get(i)));
				
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskBack.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskFront.get(i), onDeskFront.get(i+1)));
				interactions.add(StandardSexActionInteractions.besideOneAnotherOnDesk.getSexActionInteractions(onDeskBack.get(i), onDeskFront.get(i+1)));
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character lying back can use their legs, tails, or tentacles to force a facial creampie on characters performing oral on them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.PERFORMING_ORAL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericFaceForceCreampieAreas),
						new Value<>(Tail.class, genericFaceForceCreampieAreas),
						new Value<>(Tentacle.class, genericFaceForceCreampieAreas));
			}
			// The character lying back can use their legs, tails, or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character lying on their front can use their tails, or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character between legs can use their weight to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.BETWEEN_LEGS_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			// The character lying back or on front can use their arms to force a facial creampie on characters receiving oral from them:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_BACK_FOUR
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotDesk.OVER_DESK_ON_FRONT_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotDesk.RECEIVING_ORAL_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.OVER_DESK_FRONT)) {
				return false;
			}
			return true;
		}
	};

	
	public static final AbstractSexPosition STOCKS = new AbstractSexPosition("颈手枷",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(StocksSex.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(slot!= positioningSlots.get(characterToTakeSlot)) {
				if(positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| positioningSlots.get(characterToTakeSlot)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "[npc.NameIsFull]被锁在颈手枷中，不能切换栏位！"));
					
				} else if(slot==SexSlotStocks.LOCKED_IN_STOCKS
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| slot==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "性爱过程中无法将角色锁入或移出颈手枷！"));
				}
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS, SexSlotStocks.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_TWO, SexSlotStocks.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_THREE, SexSlotStocks.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS_FOUR, SexSlotStocks.PERFORMING_ORAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean characterInStocks=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_TWO
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_THREE
						|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
					characterInStocks = true;
				}
			}
			if(!characterInStocks) {
				return new Value<Boolean, String>(false, "这种姿势至少需要一个角色被锁在颈手枷中才能生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> allStocksNames = new ArrayList<>();
			List<String> stocksNames = new ArrayList<>();
			List<String> stocksTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> beneathNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainStocks = null;
			GameCharacter mainStocksTaur = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainBeneath = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerStocks = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
						if(mainStocks==null && !e.getKey().isTaur()) {
							mainStocks=e.getKey();
						}
						if(mainStocksTaur==null && e.getKey().isTaur()) {
							mainStocksTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerStocks = true;
							allStocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allStocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotStocks.BEHIND_STOCKS
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.BEHIND_STOCKS_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotStocks.HUMPING
							|| e.getValue()==SexSlotStocks.HUMPING_TWO
							|| e.getValue()==SexSlotStocks.HUMPING_THREE
							|| e.getValue()==SexSlotStocks.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotStocks.BENEATH_STOCKS
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_TWO
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_THREE
							|| e.getValue()==SexSlotStocks.BENEATH_STOCKS_FOUR) {
						if(mainBeneath==null) {
							mainBeneath=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							beneathNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							beneathNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotStocks.PERFORMING_ORAL
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotStocks.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			int stocksCount = stocksNames.size() + stocksTaurNames.size();
			GameCharacter soloStocks = mainStocks==null?mainStocksTaur:mainStocks;
			if(stocksNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))+"被锁在"+Util.intToString(stocksNames.size())
						+"套颈手枷中，无论是谁想使用"+(playerStocks?"你们":"他们")+"，都只能任其摆布。"); 
				
			} else if(stocksTaurNames.isEmpty()) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))
						+UtilText.parse(soloStocks,
								"被锁在颈手枷中，无论是谁想使用[npc.herHim]，都只能任凭其摆布。")); 
			}
			
			if(stocksTaurNames.size()>=2) {
				sb.append(Util.stringsToStringList(stocksTaurNames, false)+"拥有兽态的下半身，"
						+"因而被锁在了特制的颈手枷中，迫使其前半身呈现跪姿，而动物般的后半身却是站立着的，"
						+ "无论是谁上前来都能随意使用他们的嘴巴和下体。"); 
				
			} else if(stocksTaurNames.size()==1) {
				sb.append(UtilText.parse(mainStocksTaur,
						Util.stringsToStringList(stocksTaurNames, false)+"拥有兽态[npc.a_legRace]的下半身，"
						+"因而被锁在了特制的颈手枷中，迫使其前半身呈现跪姿，而动物般的后半身却是站立着的，"
						+ "无论是谁上前来都能随意使用他们的嘴巴和下体。")); 
			}
			
			// Using stocks target:
			
			int standingCount = standingNames.size();
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+"上前来，正站在"
							+(stocksCount>1
									?(playerStocks?"你们身后，准备趁行动不便放肆一番。":"他们身后，准备趁行动不便放肆一番。")
									:UtilText.parse(soloStocks, "[npc.name]身后，准备趁行动不便放肆一番。")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding,"上前来，正站在")
							+(stocksCount>1
									?(playerStocks?"你们身后，准备趁行动不便放肆一番。":"他们身后，准备趁行动不便放肆一番。")
									:UtilText.parse(soloStocks, "[npc.name]身后，准备趁行动不便放肆一番。")));
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+"扬起前肢，稳稳地将"
							+UtilText.parse(mainStandingTaur, "[npc.feet]")+"落在颈手枷上方，这样便可以"
							+(stocksCount>1
									?(playerStocks?"方便地骑到你们"+(Util.intToString(stocksCount))+"个身上了。":"方便地骑到他们"+(Util.intToString(stocksCount))+"个身上了。")
									:UtilText.parse(soloStocks, "方便地骑到[npc.name]身上了。")));
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStanding,"扬起前肢，稳稳地将[npc.feet]落在颈手枷上方，这样便可以")
							+(stocksCount>1
									?(playerStocks
											?UtilText.parse(mainStanding,"方便地骑到你们"+(Util.intToString(stocksCount))+"个随便哪个人身上了。")
											:UtilText.parse(mainStanding,"方便地骑到他们"+(Util.intToString(stocksCount))+"个随便哪个人身上了。"))
									:UtilText.parse(soloStocks, "方便地骑到[npc.name]身上了。")));
			}
			
			if(humpingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+"爬上了"
						+(stocksCount>1
								?(playerStocks?"你们下半身的后背":"他们下半身的后背")+"，将身子紧贴了上去。"
								:UtilText.parse(soloStocks, "[npc.namePos]你们下半身的后背，将身子紧贴了上去。")));
			
			
			} else if(humpingNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+UtilText.parse(mainHumping,"爬上了")
							+UtilText.parse(soloStocks, "[npc.namePos]下半身的后背，将身子紧贴了上去。"));
			}
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+"正好移动到"
							+(stocksCount>1
									?(playerStocks?"你们的腿":"他们的腿")+"之间，准备好给"+(playerStocks?"你们口交。":"他们口交。")
									:UtilText.parse(soloStocks, "[npc.namePos]的[npc.legs]之间，准备好给[npc.herHim]口交。")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral,"正好移动到")
							+(stocksCount>1
									?(playerStocks?"你们的腿":"他们的腿")+"之间，准备好给"+(playerStocks?"你们口交。":"他们口交。")
									:UtilText.parse(soloStocks, "[npc.namePos]的[npc.legs]之间，准备好给[npc.herHim]口交。")));
			}
			

			if(beneathNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+"俯卧在"
							+(stocksCount>1
									?(playerStocks?"你们":"他们")+"身下，准备好挨操。"
									:UtilText.parse(soloStocks, "[npc.name]身下，准备好挨操。")));
				
				
			} else if(beneathNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+UtilText.parse(mainBeneath,"俯卧在")
							+(stocksCount>1
									?(playerStocks?"你们":"他们")+"身下，准备好挨操。"
									:UtilText.parse(soloStocks, "[npc.name]身下，准备好挨操。")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty() || !beneathNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?"同时也":"")+"移动到颈手枷的另一边，靠近"
							+(stocksCount>1
									?(playerStocks?"你们的脸":"他们的脸")+"，准备好接受口交。"
									:UtilText.parse(soloStocks, "[npc.namePos]的[npc.face]，准备好接受口交。")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?"同时也":"")+UtilText.parse(mainReceivingOral, "移动到颈手枷的另一半，靠近")
						+(stocksCount>1
								?(playerStocks?"你们的脸":"他们的脸")+"，准备好接受口交。"
								:UtilText.parse(soloStocks, "[npc.namePos]的[npc.face]，准备好接受口交。")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> inStocks = Util.newArrayListOfValues(SexSlotStocks.LOCKED_IN_STOCKS, SexSlotStocks.LOCKED_IN_STOCKS_TWO, SexSlotStocks.LOCKED_IN_STOCKS_THREE, SexSlotStocks.LOCKED_IN_STOCKS_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotStocks.BEHIND_STOCKS, SexSlotStocks.BEHIND_STOCKS_TWO, SexSlotStocks.BEHIND_STOCKS_THREE, SexSlotStocks.BEHIND_STOCKS_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotStocks.HUMPING, SexSlotStocks.HUMPING_TWO, SexSlotStocks.HUMPING_THREE, SexSlotStocks.HUMPING_FOUR);
			List<SexSlot> beneath = Util.newArrayListOfValues(SexSlotStocks.BENEATH_STOCKS, SexSlotStocks.BENEATH_STOCKS_TWO, SexSlotStocks.BENEATH_STOCKS_THREE, SexSlotStocks.BENEATH_STOCKS_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotStocks.PERFORMING_ORAL, SexSlotStocks.PERFORMING_ORAL_TWO, SexSlotStocks.PERFORMING_ORAL_THREE, SexSlotStocks.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotStocks.RECEIVING_ORAL, SexSlotStocks.RECEIVING_ORAL_TWO, SexSlotStocks.RECEIVING_ORAL_THREE, SexSlotStocks.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckingCharacterInStocks.getSexActionInteractions(slotStanding, stockSlot));
				}
				for(SexSlot slotHumping : humping) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(slotStanding, slotHumping));
				}
			}

			for(SexSlot slotHumping : humping) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.humpingCharacterInStocks.getSexActionInteractions(slotHumping, stockSlot));
				}
			}
			
			for(SexSlot slotBeneath: beneath) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckedByCharacterInStocks.getSexActionInteractions(slotBeneath, stockSlot));
				}
			}
			
			for(SexSlot slotOral : performingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.performingOralOnStocks.getSexActionInteractions(slotOral, stockSlot));
				}
			}

			for(SexSlot slotOral : receivingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.stocksCharacterPerformingOral.getSexActionInteractions(stockSlot, slotOral));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)
					&& action.getParticipantType()==SexParticipantType.SELF)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in the stocks can use their tails or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.BEHIND_STOCKS)
					|| Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.HUMPING_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character performing oral can use their arms to force a facial creampie from those locked in stocks:
			if((Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS))
					&& (Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			// Characters locked in stocks cannot use fingers:
			if(Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_TWO
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_THREE
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotStocks.LOCKED_IN_STOCKS_FOUR) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(penetrator.getArmRows()*2)));
			}
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.LOCKED_IN_STOCKS)) {
				return false;
			}
			return true;
		}
	};
	
	public static final AbstractSexPosition MILKING_STALL = new AbstractSexPosition("挤奶台",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(MilkingStall.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(slot!= positioningSlots.get(characterToTakeSlot)) {
				if(positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| positioningSlots.get(characterToTakeSlot)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "[npc.NameIsFull]被锁在挤奶台上，不能切换栏位！"));
					
				} else if(slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| slot==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "性爱过程中无法将角色锁入或移出挤奶台！"));
				}
			}
			
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.PERFORMING_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.PERFORMING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR, SexSlotMilkingStall.PERFORMING_ORAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean characterInStocks=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
						|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
					characterInStocks = true;
				}
			}
			if(!characterInStocks) {
				return new Value<Boolean, String>(false, "这种姿势至少需要一个角色被锁在挤奶台上才能生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> allStocksNames = new ArrayList<>();
			List<String> stocksNames = new ArrayList<>();
			List<String> stocksTaurNames = new ArrayList<>();
			List<String> standingNames = new ArrayList<>();
			List<String> standingNamesTaur = new ArrayList<>();
			List<String> humpingNames = new ArrayList<>();
			List<String> beneathNames = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> receivingOralNames = new ArrayList<>();

			GameCharacter mainStocks = null;
			GameCharacter mainStocksTaur = null;
			GameCharacter mainStanding = null;
			GameCharacter mainStandingTaur = null;
			GameCharacter mainHumping = null;
			GameCharacter mainBeneath = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainReceivingOral = null;
			
			boolean playerStocks = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
						if(mainStocks==null && !e.getKey().isTaur()) {
							mainStocks=e.getKey();
						}
						if(mainStocksTaur==null && e.getKey().isTaur()) {
							mainStocksTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerStocks = true;
							allStocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allStocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								stocksNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								stocksTaurNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR) {
						if(mainStanding==null) {
							mainStanding=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							if(!e.getKey().isTaur()) {
								standingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							if(!e.getKey().isTaur()) {
								standingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								standingNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.HUMPING
							|| e.getValue()==SexSlotMilkingStall.HUMPING_TWO
							|| e.getValue()==SexSlotMilkingStall.HUMPING_THREE
							|| e.getValue()==SexSlotMilkingStall.HUMPING_FOUR) {
						if(mainHumping==null) {
							mainHumping=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							humpingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							humpingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE
							|| e.getValue()==SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR) {
						if(mainBeneath==null) {
							mainBeneath=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							beneathNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							beneathNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotMilkingStall.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					}  else {
						if(mainReceivingOral==null) {
							mainReceivingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							receivingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							receivingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			int stocksCount = stocksNames.size() + stocksTaurNames.size();
			GameCharacter soloStocks = mainStocks==null?mainStocksTaur:mainStocks;
			if(stocksNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))+"被锁在"+Util.intToString(stocksNames.size())
						+"套挤奶台上，无论是谁想使用"+(playerStocks?"你们":"他们")+"，都只能任其摆布。"); 
				
			} else if(stocksTaurNames.isEmpty()) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(allStocksNames, false))
						+UtilText.parse(soloStocks,
								"被锁在挤奶台上，无论是谁想使用[npc.herHim]，都只能任凭其摆布。")); 
			}
			
			if(stocksTaurNames.size()>=2) {
				sb.append(Util.stringsToStringList(stocksTaurNames, false)+"拥有兽态的下半身，"
						+"因而被锁在了特制的挤奶台上，迫使其前半身呈现跪姿，而动物般的后半身却是站立着的，"
						+ "无论是谁上前来都能随意使用他们的嘴巴和下体。"); 
				
			} else if(stocksTaurNames.size()==1) {
				sb.append(UtilText.parse(mainStocksTaur,
						Util.stringsToStringList(stocksTaurNames, false)+"拥有兽态[npc.a_legRace]的下半身，"
						+"因而被锁在了特制的挤奶台上，迫使其前半身呈现跪姿，而动物般的后半身却是站立着的，"
						+ "无论是谁上前来都能随意使用他们的嘴巴和下体。")); 
			}
			
			// Standing behind:
			
			int standingCount = standingNames.size();
			if(standingCount>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))+"上前来，正站在"
							+(stocksCount>1
									?(playerStocks?"你们身后，准备趁行动不便放肆一番。":"他们身后，准备趁行动不便放肆一番。")
									:UtilText.parse(soloStocks, "[npc.name]身后，准备趁行动不便放肆一番。")));
				
			} else if(standingCount==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNames, false))
							+UtilText.parse(mainStanding,"上前来，正站在")
							+(stocksCount>1
									?(playerStocks?"你们身后，准备趁行动不便放肆一番。":"他们身后，准备趁行动不便放肆一番。")
									:UtilText.parse(soloStocks, "[npc.name]身后，准备趁行动不便放肆一番。")));
			}
			
			int standingCountTaurs = standingNamesTaur.size();
			if(standingCountTaurs>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))+"扬起前肢，稳稳地将"
							+UtilText.parse(mainStandingTaur, "[npc.feet]")+"落在挤奶台上方，这样便可以"
							+(stocksCount>1
									?(playerStocks?"方便地骑到你们"+(Util.intToString(stocksCount))+"个身上了。":"方便地骑到他们"+(Util.intToString(stocksCount))+"个身上了。")
									:UtilText.parse(soloStocks, "方便地骑到[npc.name]身上了。")));
				
			} else if(standingCountTaurs==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(standingNamesTaur, false))
							+UtilText.parse(mainStanding,"扬起前肢，稳稳地将[npc.feet]落在挤奶台上方，这样便可以")
							+(stocksCount>1
									?(playerStocks
											?UtilText.parse(mainStanding,"方便地骑到你们"+(Util.intToString(stocksCount))+"个随便哪个人身上了。")
											:UtilText.parse(mainStanding,"方便地骑到他们"+(Util.intToString(stocksCount))+"个随便哪个人身上了。"))
									:UtilText.parse(soloStocks, "方便地骑到[npc.name]身上了。")));
			}

			
			if(humpingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+"爬上了"
						+(stocksCount>1
								?(playerStocks?"你们下半身的后背":"他们下半身的后背")+"，将身子紧贴了上去。"
								:UtilText.parse(soloStocks, "[npc.namePos]你们下半身的后背，将身子紧贴了上去。")));
			
			
			} else if(humpingNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(humpingNames, false))+UtilText.parse(mainHumping,"爬上了")
							+UtilText.parse(soloStocks, "[npc.namePos]下半身的后背，将身子紧贴了上去。"));
			}
			
			if(performingOralNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+"正好移动到"
							+(stocksCount>1
									?(playerStocks?"你们的腿":"他们的腿")+"之间，准备好给"+(playerStocks?"你们口交。":"他们口交。")
									:UtilText.parse(soloStocks, "[npc.namePos]的[npc.legs]之间，准备好给[npc.herHim]口交。")));
				
				
			} else if(performingOralNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false))+UtilText.parse(mainPerformingOral,"正好移动到")
							+(stocksCount>1
									?(playerStocks?"你们的腿":"他们的腿")+"之间，准备好给"+(playerStocks?"你们口交。":"他们口交。")
									:UtilText.parse(soloStocks, "[npc.namePos]的[npc.legs]之间，准备好给[npc.herHim]口交。")));
			}

			
			if(beneathNames.size()>=2) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+"俯卧在"
							+(stocksCount>1
									?(playerStocks?"你们":"他们")+"身下，准备好挨操。"
									:UtilText.parse(soloStocks, "[npc.name]身下，准备好挨操。")));
				
				
			} else if(beneathNames.size()==1) {
					sb.append(Util.capitaliseSentence(Util.stringsToStringList(beneathNames, false))+UtilText.parse(mainBeneath,"俯卧在")
							+(stocksCount>1
									?(playerStocks?"你们":"他们")+"身下，准备好挨操。"
									:UtilText.parse(soloStocks, "[npc.name]身下，准备好挨操。")));
			}
			
			boolean additionalDoms = !standingNames.isEmpty() || !standingNamesTaur.isEmpty() || !performingOralNames.isEmpty();
			if(receivingOralNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?"同时也":"")+"移动到台子的另一边，靠近"
							+(stocksCount>1
									?(playerStocks?"你们的脸":"他们的脸")+"，准备好接受口交。"
									:UtilText.parse(soloStocks, "[npc.namePos]的[npc.face]，准备好接受口交。")));
				
			} else if(receivingOralNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(receivingOralNames, false))+(additionalDoms?"同时也":"")
						+UtilText.parse(mainReceivingOral, "移动到台子的另一边，靠近")
						+(stocksCount>1
								?(playerStocks?"你们的脸":"他们的脸")+"，准备好接受口交。"
								:UtilText.parse(soloStocks, "[npc.namePos]的[npc.face]，准备好接受口交。")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> inStocks = Util.newArrayListOfValues(SexSlotMilkingStall.LOCKED_IN_MILKING_STALL, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE, SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR);
			List<SexSlot> standing = Util.newArrayListOfValues(SexSlotMilkingStall.BEHIND_MILKING_STALL, SexSlotMilkingStall.BEHIND_MILKING_STALL_TWO, SexSlotMilkingStall.BEHIND_MILKING_STALL_THREE, SexSlotMilkingStall.BEHIND_MILKING_STALL_FOUR);
			List<SexSlot> humping = Util.newArrayListOfValues(SexSlotMilkingStall.HUMPING, SexSlotMilkingStall.HUMPING_TWO, SexSlotMilkingStall.HUMPING_THREE, SexSlotMilkingStall.HUMPING_FOUR);
			List<SexSlot> beneath = Util.newArrayListOfValues(SexSlotMilkingStall.BENEATH_MILKING_STALL, SexSlotMilkingStall.BENEATH_MILKING_STALL_TWO, SexSlotMilkingStall.BENEATH_MILKING_STALL_THREE, SexSlotMilkingStall.BENEATH_MILKING_STALL_FOUR);
			List<SexSlot> performingOral = Util.newArrayListOfValues(SexSlotMilkingStall.PERFORMING_ORAL, SexSlotMilkingStall.PERFORMING_ORAL_TWO, SexSlotMilkingStall.PERFORMING_ORAL_THREE, SexSlotMilkingStall.PERFORMING_ORAL_FOUR);
			List<SexSlot> receivingOral = Util.newArrayListOfValues(SexSlotMilkingStall.RECEIVING_ORAL, SexSlotMilkingStall.RECEIVING_ORAL_TWO, SexSlotMilkingStall.RECEIVING_ORAL_THREE, SexSlotMilkingStall.RECEIVING_ORAL_FOUR);

			for(SexSlot slotStanding : standing) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckingCharacterInStocks.getSexActionInteractions(slotStanding, stockSlot));
				}
				for(SexSlot slotHumping : humping) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(slotStanding, slotHumping));
				}
			}
			
			for(SexSlot slotHumping : humping) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.humpingCharacterInStocks.getSexActionInteractions(slotHumping, stockSlot));
				}
			}

			for(SexSlot slotBeneath: beneath) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.fuckedByCharacterInStocks.getSexActionInteractions(slotBeneath, stockSlot));
				}
			}

			for(SexSlot slotOral : performingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.performingOralOnStocks.getSexActionInteractions(slotOral, stockSlot));
				}
			}

			for(SexSlot slotOral : receivingOral) {
				for(SexSlot stockSlot : inStocks) {
					interactions.add(StandardSexActionInteractions.stocksCharacterPerformingOral.getSexActionInteractions(stockSlot, slotOral));
				}
			}
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			if((Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.LOCKED_IN_STOCKS)
					&& action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.FINGER)
					&& action.getParticipantType()==SexParticipantType.SELF)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character in the stocks can use their tails or tentacles to force a creampie on characters fucking them:
			if((Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.BEHIND_STOCKS)
					|| Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.HUMPING_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			// The character performing oral can use their arms to force a facial creampie from those locked in stocks:
			if((Main.sex.getSexPositionSlot(cumTarget).hasTag(SexSlotTag.PERFORMING_ORAL_STOCKS))
				&& (Main.sex.getSexPositionSlot(cumProvider).hasTag(SexSlotTag.LOCKED_IN_STOCKS))) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			// Characters locked in stocks cannot use fingers:
			if(Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_TWO
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_THREE
					|| Main.sex.getSexPositionSlot(penetrator)==SexSlotMilkingStall.LOCKED_IN_MILKING_STALL_FOUR) {
				return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(penetrator.getArmRows()*2)));
			}
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.LOCKED_IN_STOCKS)) {
				return false;
			}
			return true;
		}
	};
	
	
	public static final AbstractSexPosition ALL_FOURS = new AbstractSexPosition("四肢跪地",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(DoggyStyleAndProneBone.class)) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_ORAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.BEHIND_FOUR, SexSlotAllFours.BEHIND_ORAL_FOUR));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_ANAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_ANAL_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_ANAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_FOUR, SexSlotAllFours.IN_FRONT_ANAL_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}
			
			// Added to limit humping character to those of a small stature, but it's more fun to let anyone take that slot
//			List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
//			List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
//			for(int i=0;i<4;i++) {
//				if(slot==humpingList.get(i)) {
//					for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
//						if(e.getValue()==allFoursList.get(i)) {
//							if(!characterToTakeSlot.isSizeDifferenceShorterThan(e.getKey())) {
//								return new Value<Boolean, String>(
//										false,
//										"The slot '"+Util.capitaliseSentence(slot.getDescription())+"' can only be used if "+UtilText.parse(characterToTakeSlot, "[npc.nameIsFull]")
//											+" significantly shorter than the character in the '"+Util.capitaliseSentence(allFoursList.get(i).getDescription())+"' slot.");
//							}
//						}
//					}
//				}
//			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS)
					&& (slot==SexSlotAllFours.BEHIND
							|| slot==SexSlotAllFours.BEHIND_ORAL
							|| slot==SexSlotAllFours.HUMPING
							|| slot==SexSlotAllFours.IN_FRONT_ANAL
							|| slot==SexSlotAllFours.IN_FRONT
							|| slot==SexSlotAllFours.USING_FEET)) {
				return new Value<Boolean, String>(
						false,
						"栏位“"+Util.capitaliseSentence(slot.getDescription())+"”无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_TWO)
					&& (slot==SexSlotAllFours.BEHIND_TWO
							|| slot==SexSlotAllFours.BEHIND_ORAL_TWO
							|| slot==SexSlotAllFours.HUMPING_TWO
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_TWO
							|| slot==SexSlotAllFours.IN_FRONT_TWO
							|| slot==SexSlotAllFours.USING_FEET_TWO)) {
				return new Value<Boolean, String>(
						false,
						"栏位“"+Util.capitaliseSentence(slot.getDescription())+"”无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_TWO.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_THREE)
					&& (slot==SexSlotAllFours.BEHIND_THREE
							|| slot==SexSlotAllFours.BEHIND_ORAL_THREE
							|| slot==SexSlotAllFours.HUMPING_THREE
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_THREE
							|| slot==SexSlotAllFours.IN_FRONT_THREE
							|| slot==SexSlotAllFours.USING_FEET_THREE)) {
				return new Value<Boolean, String>(
						false,
						"栏位“"+Util.capitaliseSentence(slot.getDescription())+"”无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_THREE.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotAllFours.ALL_FOURS_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotAllFours.ALL_FOURS_FOUR)
					&& (slot==SexSlotAllFours.BEHIND_FOUR
							|| slot==SexSlotAllFours.BEHIND_ORAL_FOUR
							|| slot==SexSlotAllFours.HUMPING_FOUR
							|| slot==SexSlotAllFours.IN_FRONT_ANAL_FOUR
							|| slot==SexSlotAllFours.IN_FRONT_FOUR
							|| slot==SexSlotAllFours.USING_FEET_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"栏位“"+Util.capitaliseSentence(slot.getDescription())+"”无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotAllFours.ALL_FOURS_FOUR.getDescription())+"”栏位。");
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotAllFours.ALL_FOURS
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_TWO
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_THREE
						|| e.getValue()==SexSlotAllFours.ALL_FOURS_FOUR) {
					suitablePosition = true;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "这种姿势至少需要一个角色四肢跪地才能生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			// For each character on all fours, describe them and those who are interacting with them:

			List<SexSlot> position1 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS, SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.USING_FEET, SexSlotAllFours.HUMPING, SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_ANAL);
			List<SexSlot> position2 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_ORAL_TWO, SexSlotAllFours.USING_FEET_TWO, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_ANAL_TWO);
			List<SexSlot> position3 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_ORAL_THREE, SexSlotAllFours.USING_FEET_THREE, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_ANAL_THREE);
			List<SexSlot> position4 = Util.newArrayListOfValues(
					SexSlotAllFours.ALL_FOURS_FOUR, SexSlotAllFours.BEHIND_FOUR, SexSlotAllFours.BEHIND_ORAL_FOUR, SexSlotAllFours.USING_FEET_FOUR, SexSlotAllFours.HUMPING_FOUR, SexSlotAllFours.IN_FRONT_FOUR, SexSlotAllFours.IN_FRONT_ANAL_FOUR);
			
			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			int count=0;
			for(List<SexSlot> positions : positionLists) {
				GameCharacter allFours = null;
				GameCharacter behind = null;
				GameCharacter behindOral = null;
				GameCharacter usingFeet = null;
				GameCharacter humping = null;
				GameCharacter inFront = null;
				GameCharacter inFrontAnal = null;
				
				GameCharacter fallBackAllFours1 = null;
				GameCharacter fallBackAllFours2 = null;
				GameCharacter fallBackAllFours3 = null;
				
				for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
					if(e.getValue()==positions.get(0)) {
						allFours = e.getKey();
					}
					if(e.getValue()==positions.get(1)) {
						behind = e.getKey();
					}
					if(e.getValue()==positions.get(2)) {
						behindOral = e.getKey();
					}
					if(e.getValue()==positions.get(3)) {
						usingFeet = e.getKey();
					}
					if(e.getValue()==positions.get(4)) {
						humping = e.getKey();
					}
					if(e.getValue()==positions.get(5)) {
						inFront = e.getKey();
					}
					if(e.getValue()==positions.get(6)) {
						inFrontAnal = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS_THREE) {
						fallBackAllFours1 = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS_TWO) {
						fallBackAllFours2 = e.getKey();
					}
					if(e.getValue()==SexSlotAllFours.ALL_FOURS) {
						fallBackAllFours3 = e.getKey();
					}
				}
				
				boolean skipAllFours = false;
				if(allFours == null) {
					skipAllFours = true;
					allFours = fallBackAllFours1;
				}
				if(allFours == null) {
					allFours = fallBackAllFours2;
				}
				if(allFours == null) {
					allFours = fallBackAllFours3;
				}
				
				if(!skipAllFours) {
					switch(count) {
						case 0:
							sb.append(UtilText.parse(allFours,
									(!allFours.isTaur()
										?"[npc.NameIsFull]四肢跪地，准备好以后入式挨操了。"
										:"[npc.NameHasFull]让兽态的[npc.legRace]下半身呈跪姿，后端还立着，准备好挨操了。")));
							break;
						case 1:
							sb.append(UtilText.parse(allFours, fallBackAllFours3,
									(!allFours.isTaur()
										?"[npc.nameHasFull]跟[npc2.name]摆出类似的姿势，四肢跪地，期待着像只野兽一般交尾。"
										:"[npc.nameHasFull]跟[npc2.name]摆出类似的姿势，让[npc.legRace]的前半肢呈跪姿，高高扬起[npc.her][npc.ass+]，期待着像只野兽一般交尾。")));
							break;
						case 2:
							sb.append(UtilText.parse(Util.newArrayListOfValues(allFours, fallBackAllFours3, fallBackAllFours2),
									(!allFours.isTaur()
										?"[npc.nameHasFull]照着[npc2.name]和[npc3.name]的样子，五体投地俯下身去。"
										:"[npc.nameHasFull]照着[npc2.name]和[npc3.name]的样子，前[npc.legs]低跪下去，却高高扬起[npc.her][npc.ass+]，期待着[npc.legRace]的后半身被狠狠蹂躏。")));
							break;
						case 3:
							sb.append(UtilText.parse(Util.newArrayListOfValues(allFours, fallBackAllFours3, fallBackAllFours2, fallBackAllFours1),
									(!allFours.isTaur()
										?"[npc.nameIsFull]在[npc2.name]、[npc3.name]和[npc4.name]的身边，四肢跪地地俯下身去，补充了四人最后的空位。"
										:"[npc.nameIsFull]在[npc2.name]、[npc3.name]和[npc4.name]的身边，四肢跪地地俯下身去，如同一只发情的动物一般，展露着自己兽态的[npc.legRace]身躯。")));
							break;
					}
				}
				
				boolean continuation = false;
				if(humping!=null) {
					if(humping.isSizeDifferenceShorterThan(allFours)) {
						sb.append(UtilText.parse(humping, allFours,
								"[npc.nameHasFull]不愿因为自己矮小的身形就错过玩乐的好机会，于是爬上[npc2.namePos]下半身的后背，牢牢抓住之后将身子紧贴了上去。"));
					} else {
						sb.append(UtilText.parse(humping, allFours,
								"[npc.NameHasFull]整个俯贴在[npc2.namePos]的后背上，下半身抵着[npc2.her][npc2.ass+]，随时准备要开始交配。"));
					}
					continuation = true;
				}
				if(behind!=null) {
					sb.append(UtilText.parse(behind, allFours,
							!behind.isTaur()
								?"[npc.NameIsFull]"+(positions.get(1).isStanding(behind)?"站在":"跪在")+"[npc2.herHim]的正后方，随时都可以开始对着[npc2.herHim]开干。"
								:"[npc.NameHasFull]将自己兽态的[npc.legRace]身躯整个覆盖在[npc2.herHim]的上方，随时准备好骑上[npc2.herHim]。"));
					continuation = true;
				}
				if(behindOral!=null) {
					sb.append(UtilText.parse(behindOral, allFours,
							"[npc.NameHasFull]"+(positions.get(2).isStanding(behindOral)?"站到了":"跪在")+"[npc2.name]身后，准备好为[npc2.herHim]提供口交。"));
					continuation = true;
				}
				if(usingFeet!=null) {
					sb.append(UtilText.parse(usingFeet, allFours,
							"[npc.Name]低跪在[npc2.name]身后，这样就能随时使用到[npc2.her][npc2.feet+]了。"));
					continuation = true;
				}

				if(inFront!=null) {
					sb.append(UtilText.parse(inFront, allFours,
							continuation
							?"同时在[npc2.name]的另一边，[npc.nameHasFull]"+(positions.get(5).isStanding(inFront)?"立在":"跪在")
									+(inFront.isTaur()
										?"[npc2.herHim]的头顶，将动物化的下体抵住[npc2.her]的[npc2.face]，准备好了随时接受口交。"
										:"[npc2.her]的[npc2.face]面前，准备好接受口交。")
							:"[npc.nameHasFull]并没有从[npc2.herHim]的身后“趁人之危”，而是"+(positions.get(5).isStanding(inFront)?"走向":"跪在")
								+"[npc2.her][npc2.face]的正前方，准备好接受口交。"));
				}
				if(inFrontAnal!=null) {
					sb.append(UtilText.parse(inFrontAnal, allFours,
							continuation
							?"同时在[npc2.name]的另一边，[npc.nameHasFull]"+(positions.get(6).isStanding(inFrontAnal)?"上前并转过身去":"跪下并扭过身子")
									+"对准了[npc2.her]的[npc2.face]，准备好用[npc2.her]的嘴巴来满足自己[npc.ass+]了。"
							:"[npc.nameHasFull]并没有从[npc2.herHim]的身后“趁人之危”，而是"+(positions.get(6).isStanding(inFrontAnal)?"上前并转过身去":"跪下并扭过身子")
								+"对准了[npc2.her]的[npc2.face]，准备好用[npc2.her]的嘴巴来满足自己[npc.ass+]了。"));
				}

				if(!skipAllFours) {
					count++;
				}
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
			List<SexSlot> behindList = Util.newArrayListOfValues(SexSlotAllFours.BEHIND, SexSlotAllFours.BEHIND_TWO, SexSlotAllFours.BEHIND_THREE, SexSlotAllFours.BEHIND_FOUR);
			List<SexSlot> behindOralList = Util.newArrayListOfValues(SexSlotAllFours.BEHIND_ORAL, SexSlotAllFours.BEHIND_ORAL_TWO, SexSlotAllFours.BEHIND_ORAL_THREE, SexSlotAllFours.BEHIND_ORAL_FOUR);
			List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
			List<SexSlot> feetList = Util.newArrayListOfValues(SexSlotAllFours.USING_FEET, SexSlotAllFours.USING_FEET_TWO, SexSlotAllFours.USING_FEET_THREE, SexSlotAllFours.USING_FEET_FOUR);
			List<SexSlot> inFrontList = Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT, SexSlotAllFours.IN_FRONT_TWO, SexSlotAllFours.IN_FRONT_THREE, SexSlotAllFours.IN_FRONT_FOUR);
			List<SexSlot> inFrontAnalList = Util.newArrayListOfValues(SexSlotAllFours.IN_FRONT_ANAL, SexSlotAllFours.IN_FRONT_ANAL_TWO, SexSlotAllFours.IN_FRONT_ANAL_THREE, SexSlotAllFours.IN_FRONT_ANAL_FOUR);
			
			// Those down on all fours can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO));
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE));
			interactions.add(StandardSexActionInteractions.allFoursToAllFours.getSexActionInteractions(SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR));
			
			// All those behind can interact with all those on all fours, those humping, and those in front:
			for(SexSlot behindSlot : behindList) {
				for(SexSlot allFoursSlot : allFoursList) {
					interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(behindSlot, allFoursSlot));
				}
				for(SexSlot humpingSlot : humpingList) {
					interactions.add(StandardSexActionInteractions.allFoursBehindToHumping.getSexActionInteractions(behindSlot, humpingSlot));
				}
			}
			for(int i=0; i<4; i++) {
				if(Main.sex.getCharacterInPosition(allFoursList.get(i))!=null && !Main.sex.getCharacterInPosition(allFoursList.get(i)).isTaur()) {
					for(SexSlot inFrontSlot : inFrontList) {
						interactions.add(StandardSexActionInteractions.allFoursCharacterBehindToCharactersFront.getSexActionInteractions(behindList.get(i), inFrontSlot));
					}
				}
			}
			
			// Those performing oral behind can oral the humpers:
			for(SexSlot behindOralSlot : behindOralList) {
				for(SexSlot humpingSlot : humpingList) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(behindOralSlot, humpingSlot));
				}
			}
			
			for(SexSlot allFoursSlot : allFoursList) {
				// All those performing oral behind can interact with all those on all fours:
				for(SexSlot behindOralSlot : behindOralList) {
					interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(behindOralSlot, allFoursSlot));
				}
				// All those behind wanting to use feet can interact with all those on all fours:
				for(SexSlot feetSlot : feetList) {
					interactions.add(StandardSexActionInteractions.allFoursFeet.getSexActionInteractions(feetSlot, allFoursSlot));
				}
				// All those receiving oral interact with all those on all fours:
				for(SexSlot inFrontSlot : inFrontList) {
					interactions.add(StandardSexActionInteractions.allFoursPerformingOral.getSexActionInteractions(allFoursSlot, inFrontSlot));
				}
				// All those receiving anal oral interact with all those on all fours:
				for(SexSlot inFrontAnalSlot : inFrontAnalList) {
					interactions.add(StandardSexActionInteractions.allFoursPerformingOralBehind.getSexActionInteractions(allFoursSlot, inFrontAnalSlot));
				}
			}
			
			// Humping characters can only interact with those they are on top of:
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING, SexSlotAllFours.ALL_FOURS));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.ALL_FOURS_TWO));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.ALL_FOURS_THREE));
			interactions.add(StandardSexActionInteractions.allFoursHumping.getSexActionInteractions(SexSlotAllFours.HUMPING_FOUR, SexSlotAllFours.ALL_FOURS_FOUR));

			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character being fucked can use their tails or tentacles to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_FOUR)) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.BEHIND_FOUR
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.HUMPING_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
			}
			// Non-bipedal characters performing oral can use their arm(s) to force a mouth creampie:
			if(cumTarget.isTaur()
					&& (Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotAllFours.ALL_FOURS_FOUR)) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotAllFours.IN_FRONT_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			return null;
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			// Restrict anal actions if the one humping is in the way:
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.ALL_FOURS) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.BEHIND_ALL_FOURS)) {
				List<SexSlot> allFoursList = Util.newArrayListOfValues(SexSlotAllFours.ALL_FOURS, SexSlotAllFours.ALL_FOURS_TWO, SexSlotAllFours.ALL_FOURS_THREE, SexSlotAllFours.ALL_FOURS_FOUR);
				List<SexSlot> humpingList = Util.newArrayListOfValues(SexSlotAllFours.HUMPING, SexSlotAllFours.HUMPING_TWO, SexSlotAllFours.HUMPING_THREE, SexSlotAllFours.HUMPING_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter humper = Main.sex.getCharacterInPosition(humpingList.get(i));
					GameCharacter allFours = Main.sex.getCharacterInPosition(allFoursList.get(i));
					if(humper!=null
							&& target.equals(allFours)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.ASS))
							&& Main.sex.getCharactersHavingOngoingActionWith(target, SexAreaOrifice.VAGINA).contains(humper)) {
						return true;
					}
				}
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.ALL_FOURS)
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING_TWO
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING_THREE
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotAllFours.HUMPING_FOUR) {
				return false;
			}
			return true;
		}
	};
	
	
	//TODO cowgirl piazuri?
	/**
	 * Contains support for:<br/>
	 * <b>Face-sitting</b><br/>
	 * <b>Cow-girl</b><br/>
	 * <b>Missionary</b><br/>
	 * <b>Reverse cow-girl</b><br/>
	 * <b>Sixty-nine</b><br/>
	 * <b>Mating press</b><br/>
	 * <b>Scissoring</b>
	 */
	public static final AbstractSexPosition LYING_DOWN = new AbstractSexPosition("躺姿",
			8,
			true,
			SexActionPresets.positioningActionsNew,
			Util.newArrayListOfValues(Cowgirl.class, FaceSitting.class, MatingPress.class, Missionary.class, SixtyNine.class, DoggyStyleAndProneBone.class)) {
		
		private List<SexSlot> position1 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_FRONT, //0-1
				SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.SCISSORING, //2-6
				SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.SIXTY_NINE, SexSlotLyingDown.RECEIVING_ORAL, //7-10
				SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.BESIDE); //11-13
		private List<SexSlot> position2 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_FRONT_TWO, //0-1
				SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.SCISSORING_TWO, //2-6
				SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.SIXTY_NINE_TWO, SexSlotLyingDown.RECEIVING_ORAL_TWO, //7-10
				SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.BESIDE_TWO); //11-13
		private List<SexSlot> position3 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FRONT_THREE, //0-1
				SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.SCISSORING_THREE, //2-6
				SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.SIXTY_NINE_THREE, SexSlotLyingDown.RECEIVING_ORAL_THREE, //7-10
				SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.BESIDE_THREE); //11-13
		private List<SexSlot> position4 = Util.newArrayListOfValues(
				SexSlotLyingDown.LYING_DOWN_FOUR, SexSlotLyingDown.LYING_DOWN_FRONT_FOUR, //0-1
				SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.MISSIONARY_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.SCISSORING_FOUR, //2-6
				SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR, SexSlotLyingDown.RECEIVING_ORAL_FOUR, //7-10
				SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.MISSIONARY_ORAL_FOUR, SexSlotLyingDown.BESIDE_FOUR); //11-13
		
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_FRONT));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_FRONT_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FRONT_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN_FOUR, SexSlotLyingDown.LYING_DOWN_FRONT_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.SCISSORING));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.SCISSORING_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.SCISSORING_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.SCISSORING_FOUR));
			
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.SCISSORING));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.SCISSORING_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.SCISSORING_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_FOUR, SexSlotLyingDown.MISSIONARY_ORAL_FOUR, SexSlotLyingDown.SCISSORING_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}

			// Mating press is only compatible with missionary & oral:
			mutuallyExclusiveSlots.clear();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE,
					SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO,
					SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SCISSORING_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE,
					SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SCISSORING_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR,
					SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SCISSORING_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				SexSlot matingPressSlot = entry.get(0);
				for(SexSlot s : entry) {
					if(s==slot) {
						if(s==matingPressSlot) {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
													+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
								}
							}
							
						} else {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(e.getValue()==matingPressSlot && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
													+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
								}
							}
						}
					}
				}
			}

			// Frontal lying down positions are only compatible with "missionary" (between legs) and both oral positions:
			mutuallyExclusiveSlots.clear();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING_REVERSE,
					SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.LAP_PILLOW, SexSlotLyingDown.SCISSORING, SexSlotLyingDown.SIXTY_NINE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO,
					SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.LAP_PILLOW_TWO, SexSlotLyingDown.SCISSORING_TWO, SexSlotLyingDown.SIXTY_NINE_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE,
					SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.LAP_PILLOW_THREE, SexSlotLyingDown.SCISSORING_THREE, SexSlotLyingDown.SIXTY_NINE_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(
					SexSlotLyingDown.LYING_DOWN_FRONT_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR,
					SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.LAP_PILLOW_FOUR, SexSlotLyingDown.SCISSORING_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR));

			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				SexSlot frontalLyingDownSlot = entry.get(0);
				for(SexSlot s : entry) {
					if(s==slot) {
						if(s==frontalLyingDownSlot) {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
													+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
								}
							}
							
						} else {
							for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
								if(e.getValue()==frontalLyingDownSlot && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
									return new Value<Boolean, String>(
											false,
											"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
													+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
								}
							}
						}
					}
				}
			}
			
			
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotLyingDown.SCISSORING
						|| slot==SexSlotLyingDown.SCISSORING_TWO
						|| slot==SexSlotLyingDown.SCISSORING_THREE
						|| slot==SexSlotLyingDown.SCISSORING_FOUR)) {
				return new Value<Boolean, String>(false, "栏位“"+Util.capitaliseSentence(slot.getDescription())+"”无法由双足下肢角色使用。");
			}
			if(characterToTakeSlot.isTaur()
					&& (slot==SexSlotLyingDown.SIXTY_NINE
						|| slot==SexSlotLyingDown.SIXTY_NINE_TWO
						|| slot==SexSlotLyingDown.SIXTY_NINE_THREE
						|| slot==SexSlotLyingDown.SIXTY_NINE_FOUR)) {
				return new Value<Boolean, String>(false, "栏位“"+Util.capitaliseSentence(slot.getDescription())+"”无法由双足下肢角色使用。");
			}
			
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN)
					&& (slot==SexSlotLyingDown.COWGIRL
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE
							|| slot==SexSlotLyingDown.FACE_SITTING
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE
							|| slot==SexSlotLyingDown.LAP_PILLOW
							|| slot==SexSlotLyingDown.MATING_PRESS
							|| slot==SexSlotLyingDown.SCISSORING
							|| slot==SexSlotLyingDown.SIXTY_NINE)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_TWO)
					&& (slot==SexSlotLyingDown.COWGIRL_TWO
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_TWO
							|| slot==SexSlotLyingDown.FACE_SITTING_TWO
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_TWO
							|| slot==SexSlotLyingDown.LAP_PILLOW_TWO
							|| slot==SexSlotLyingDown.MATING_PRESS_TWO
							|| slot==SexSlotLyingDown.SCISSORING_TWO
							|| slot==SexSlotLyingDown.SIXTY_NINE_TWO)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_TWO.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_THREE)
					&& (slot==SexSlotLyingDown.COWGIRL_THREE
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_THREE
							|| slot==SexSlotLyingDown.FACE_SITTING_THREE
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_THREE
							|| slot==SexSlotLyingDown.LAP_PILLOW_THREE
							|| slot==SexSlotLyingDown.MATING_PRESS_THREE
							|| slot==SexSlotLyingDown.SCISSORING_THREE
							|| slot==SexSlotLyingDown.SIXTY_NINE_THREE)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_THREE.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FOUR)
					&& (slot==SexSlotLyingDown.COWGIRL_FOUR
							|| slot==SexSlotLyingDown.COWGIRL_REVERSE_FOUR
							|| slot==SexSlotLyingDown.FACE_SITTING_FOUR
							|| slot==SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR
							|| slot==SexSlotLyingDown.LAP_PILLOW_FOUR
							|| slot==SexSlotLyingDown.MATING_PRESS_FOUR
							|| slot==SexSlotLyingDown.SCISSORING_FOUR
							|| slot==SexSlotLyingDown.SIXTY_NINE_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FOUR.getDescription())+"”栏位。");
			}
			

			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_TWO)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL_TWO)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_TWO.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_THREE)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL_THREE)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_THREE.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR)
					&& (slot==SexSlotLyingDown.RECEIVING_ORAL_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR.getDescription())+"”栏位。");
			}
			
			
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT)
					&& (slot==SexSlotLyingDown.MISSIONARY
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至"
								+ "“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN.getDescription())+"”或“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_TWO)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_TWO) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_TWO)
					&& (slot==SexSlotLyingDown.MISSIONARY_TWO
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_TWO)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至"
								+ "“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_TWO.getDescription())+"”或“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_TWO.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_THREE)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_THREE) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_THREE)
					&& (slot==SexSlotLyingDown.MISSIONARY_THREE
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_THREE)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至"
								+ "“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_THREE.getDescription())+"”或“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_THREE.getDescription())+"”栏位。");
			}
			if((!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FOUR)
					&& (!positioningSlots.values().contains(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) || positioningSlots.get(characterToTakeSlot)==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR)
					&& (slot==SexSlotLyingDown.MISSIONARY_FOUR
							|| slot==SexSlotLyingDown.MISSIONARY_ORAL_FOUR)) {
				return new Value<Boolean, String>(
						false,
						"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位无法使用，除非有角色被分配至"
								+ "“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FOUR.getDescription())+"”或“"+Util.capitaliseSentence(SexSlotLyingDown.LYING_DOWN_FRONT_FOUR.getDescription())+"”栏位。");
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitablePosition=false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotLyingDown.LYING_DOWN
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_TWO
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_THREE
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FOUR
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT_TWO
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT_THREE
						|| e.getValue()==SexSlotLyingDown.LYING_DOWN_FRONT_FOUR) {
					suitablePosition = true;
				}
			}
			if(!suitablePosition) {
				return new Value<Boolean, String>(false, "这种姿势至少需要一个角色呈躺姿才能生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();
			
			// For each character lying down, describe them and those who are interacting with them:

			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			List<String> besideNames = new ArrayList<>();
			GameCharacter mainBeside = null;

			List<GameCharacter> lyingDownCharacters = new ArrayList<>();
			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue().hasTag(SexSlotTag.LYING_DOWN)
						|| e.getValue().hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
					lyingDownCharacters.add(e.getKey());
				}
			}
			
			int count=0;
			for(List<SexSlot> positions : positionLists) {
				GameCharacter lyingDown = null;
				GameCharacter lyingDownFront = null;
				GameCharacter cowgirl = null;
				GameCharacter cowgirlReverse = null;
				GameCharacter missionary = null;
				GameCharacter matingPress = null;
				GameCharacter scissoring = null;
				GameCharacter faceSitting = null;
				GameCharacter faceSittingReverse = null;
				GameCharacter sixtyNine = null;
				GameCharacter receivingOral = null;
				GameCharacter lapPillow = null;
				GameCharacter performingOral = null;
				
				for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
					if(e.getValue()==positions.get(0)) {
						lyingDown = e.getKey();
					}
					if(e.getValue()==positions.get(1)) {
						lyingDownFront = e.getKey();
					}
					if(e.getValue()==positions.get(2)) {
						cowgirl = e.getKey();
					}
					if(e.getValue()==positions.get(3)) {
						cowgirlReverse = e.getKey();
					}
					if(e.getValue()==positions.get(4)) {
						missionary = e.getKey();
					}
					if(e.getValue()==positions.get(5)) {
						matingPress = e.getKey();
					}
					if(e.getValue()==positions.get(6)) {
						scissoring = e.getKey();
					}
					if(e.getValue()==positions.get(7)) {
						faceSitting = e.getKey();
					}
					if(e.getValue()==positions.get(8)) {
						faceSittingReverse = e.getKey();
					}
					if(e.getValue()==positions.get(9)) {
						sixtyNine = e.getKey();
					}
					if(e.getValue()==positions.get(10)) {
						receivingOral = e.getKey();
					}
					if(e.getValue()==positions.get(11)) {
						lapPillow = e.getKey();
					}
					if(e.getValue()==positions.get(12)) {
						performingOral = e.getKey();
					}
					if(e.getValue()==positions.get(13)) {
						if(mainBeside==null) {
							mainBeside = e.getKey();
						}
						if(e.getKey().isPlayer()) {
							besideNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							besideNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
					
				}
				
				if(lyingDown==null) {
					// There always needs to be a character lying down, so if there are multiple characters in an oral position (or something similar), make the last encountered lyingDown character the focused one
					lyingDown = lyingDownCharacters.get(lyingDownCharacters.size()-1);
				}
				
				boolean skipLyingdown = count >= lyingDownCharacters.size();
				if(!skipLyingdown) {
					GameCharacter targetedCharacter1, targetedCharacter2, targetedCharacter3, targetedCharacter4;
					switch(count) {
						case 0:
							targetedCharacter1 = lyingDownCharacters.get(0);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(targetedCharacter1,
										(!targetedCharacter1.isTaur()
											?"[npc.NameIsFull]俯卧着，"
											:"[npc.NameIsFull]平躺着，伸展开[npc.her]兽态的[npc.legRace]身体，")
										+"顺从地暴露出[npc.her]的背部、臀部和下体。"));
							} else {
								sb.append(UtilText.parse(lyingDownCharacters.get(0),
										(!lyingDownCharacters.get(0).isTaur()
											?"[npc.NameIsFull]仰面平躺着，顺从地露出[npc.her]的肚子、[npc.face]和下体。"
											:"[npc.NameHasFull]让[npc.her]兽态的[npc.legRace]身躯跪坐下去，然后稍一翻身，顺从地露出了肚子。")));
							}
							break;
						case 1:
							targetedCharacter1 = lyingDownCharacters.get(0);
							targetedCharacter2 = lyingDownCharacters.get(1);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(targetedCharacter2, targetedCharacter1,
										(!targetedCharacter2.isTaur()
											?"[npc.nameIsFull]加入[npc2.name]，一起在地上俯卧着，"
											:"[npc.nameIsFull]加入[npc2.name]，让[npc.her]兽态的[npc.legRace]身体跪坐在地上，")
										+"顺从地准备好挨操。"));
							} else {
								sb.append(UtilText.parse(targetedCharacter2, targetedCharacter1,
										(!targetedCharacter2.isTaur()
											?"[npc.nameHasFull]加入[npc2.name]，一起仰面躺倒在地上。"
											:"[npc.nameHasFull]加入[npc2.name]，先让[npc.her]兽态的[npc.legRace]身体跪坐在地上，再一翻身子，展示出下面的身体。")));
							}
							break;
						case 2:
							targetedCharacter1 = lyingDownCharacters.get(0);
							targetedCharacter2 = lyingDownCharacters.get(1);
							targetedCharacter3 = lyingDownCharacters.get(2);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter3.isTaur()
											?"[npc.nameHasFull]照着[npc2.name]和[npc3.name]的样子，俯卧在地上，"
											:"[npc.nameHasFull]照着[npc2.name]和[npc3.name]的样子，让[npc.her]兽态的[npc.legRace]身体跪坐在地上，")
										+"顺从地准备好挨操。"));
							} else {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter3.isTaur()
											?"[npc.nameHasFull]照着[npc2.name]和[npc3.name]的样子，平躺了下来。"
											:"[npc.nameHasFull]照着[npc2.name]和[npc3.name]的样子，先让[npc.her]兽态的[npc.legRace]身躯跪下，再一翻身子，展示出下面的身体。")));
							}
							break;
						case 3:
							targetedCharacter1 = lyingDownCharacters.get(0);
							targetedCharacter2 = lyingDownCharacters.get(1);
							targetedCharacter3 = lyingDownCharacters.get(2);
							targetedCharacter4 = lyingDownCharacters.get(3);
							if(occupiedSlots.get(targetedCharacter1).hasTag(SexSlotTag.LYING_DOWN_ON_FRONT)) {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter4, targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter4.isTaur()
											?"[npc.nameIsFull]俯卧在[npc2.name]、[npc3.name]和[npc4.name]身旁，补齐了四人最后的空位。"
											:"[npc.nameIsFull]俯卧在[npc2.name]、[npc3.name]和[npc4.name]身旁，补齐了四人最后的空位。")));
							} else {
								sb.append(UtilText.parse(Util.newArrayListOfValues(targetedCharacter4, targetedCharacter3, targetedCharacter2, targetedCharacter1),
										(!targetedCharacter4.isTaur()
											?"[npc.nameIsFull]在[npc2.name]、[npc3.name]和[npc4.name]身边躺下，补充了四人最后的空位。"
											:"[npc.nameIsFull]在[npc2.name]、[npc3.name]和[npc4.name]的身边跪下，然后翻身露出了兽态的[npc.legRace]身躯的下面，补充了四人最后的空位。")));
							}
							break;
					}
					count++;
				}
				
				boolean continuation = false;
				
				if(lyingDownFront!=null) {
					if(missionary!=null) {
						if(!missionary.isTaur()) {
							sb.append(UtilText.parse(missionary, lyingDownFront,
									"[npc.NameHasFull]在[npc2.name]身后俯下，调整自己的位置到[npc2.her]的[npc2.legs]间，准备以俯卧位与[npc2.herHim]交欢。"));
						} else {
							sb.append(UtilText.parse(missionary, lyingDownFront,
									"[npc.NameHasFull]跨坐在[npc2.name]身上，放低了[npc.her]兽态的[npc.legRace]身躯，准备好以俯卧位跟[npc2.herHim]开干了。"));
						}
						continuation = true;
					}
					if(performingOral!=null) {
						sb.append(UtilText.parse(performingOral, lyingDownFront,
								"[npc.nameHasFull]想要为[npc2.name]提供口交，于是趴在[npc2.her]的[npc2.legs]间，正面对着下体的位置。"));
					}
					if(receivingOral!=null) {
						sb.append(UtilText.parse(receivingOral, lyingDownFront,
								"[npc.nameHasFull]想要接受[npc2.name]的口交，于是跪坐在[npc2.herHim]面前，将下体靠近[npc2.her]的[npc2.face]。"));
					}
					
					
				} else {
					// These four slots are mutually exclusive:
					if(cowgirl!=null) {
						if(!cowgirl.isTaur()) {
							sb.append(UtilText.parse(cowgirl, lyingDown,
									"[npc.NameHasFull]上前去，放低身子跨坐在了[npc2.name]的下体部位，摆出了骑乘位。"));
						} else {
							sb.append(UtilText.parse(cowgirl, lyingDown,
									"[npc.NameHasFull]上前去，将[npc.her]兽态的[npc.legRace]身躯后半部放低，紧紧地压在了[npc2.name]的下体上。"));
						}
						continuation = true;
					}
					if(cowgirlReverse!=null) {
						if(!cowgirlReverse.isTaur()) {
							sb.append(UtilText.parse(cowgirlReverse, lyingDown,
									"[npc.NameHasFull]上前去转了个身，放低身子跨坐在了[npc2.name]的下体部位，摆出了反骑乘位。"));
						} else {
							sb.append(UtilText.parse(cowgirlReverse, lyingDown,
									"[npc.NameHasFull]上前去转了个身，将[npc.her]兽态的[npc.legRace]身躯后半部放低，紧紧地压在了[npc2.name]的下体上。"));
						}
						continuation = true;
					}
					if(matingPress!=null) {
						if(!matingPress.hasLegs()) {
							sb.append(UtilText.parse(matingPress, lyingDown,
									"[npc.NameHasFull]把[npc2.namePos]紧紧压在地上，随后也俯下身子，两人的性器正对在一起。"
										+ "[npc.SheIs]强硬地用[npc.hands]将[npc2.namePos]的手腕按在[npc2.her]脑袋两边，"
											+ "这样便把[npc2.herHim]牢牢锁在身下，形成了正适合配种的姿势。"));
							
						} else if(!matingPress.isTaur()) {
							sb.append(UtilText.parse(matingPress, lyingDown,
									"[npc.NameHasFull]把[npc2.namePos]的[npc2.legs]拨到旁边，压向脑袋的方向，随后便俯下身子，两人的性器正对在一起。"
										+ "[npc.SheIs]强硬地用[npc.hands]将[npc2.namePos]的手腕按在[npc2.her]脑袋两边，"
											+ "这样便把[npc2.herHim]牢牢锁在身下，形成了正适合配种的姿势。"));
							
						} else {
							sb.append(UtilText.parse(matingPress, lyingDown,
									"[npc.NameHasFull]把[npc2.namePos]的[npc2.legs]拨到旁边，压向脑袋的方向，随后便放低俯下[npc.her]兽态的[npc.legRace]身躯，"
											+ "两人的性器正对在一起。"
										+ "[npc.SheIs]强硬地用前肢将[npc2.namePos]的手腕按在[npc2.her]脑袋两边，"
											+ "这样便把[npc2.herHim]牢牢锁在身下，形成了正适合配种的姿势。"));
						}
						continuation = true;
					}
					if(scissoring!=null) {
						sb.append(UtilText.parse(scissoring, lyingDown,
								"[npc.NameHasFull]平躺下来[npc.spreadingHerLegs]，缓缓向前移动，"
										+ "等到下体跟[npc2.hers]接触后，便准备开始跟[npc2.herHim]剪刀式了。"));
						continuation = true;
					}
	
					if(missionary!=null) {
						if(!missionary.isTaur()) {
							sb.append(UtilText.parse(missionary, lyingDown,
									"[npc.NameHasFull]趴在[npc2.namePos]身上，准备以传教士体位跟[npc2.herHim]开干了。"));
						} else {
							sb.append(UtilText.parse(missionary, lyingDown,
									"[npc.NameHasFull]罩在[npc2.name]身上，放低了[npc.her]兽态的[npc.legRace]身躯，准备好以传教士体位跟[npc2.herHim]开干了。"));
						}
						continuation = true;
					}
					
					if(faceSitting!=null) {
						if(!faceSitting.isTaur()) {
							sb.append(UtilText.parse(faceSitting, lyingDown,
									continuation
									?"[npc.nameHasFull]同时也迫切地想要来一番颜面骑乘，跨过[npc2.name]坐了下去，将下体紧紧地压在了[npc2.her]的[npc2.face]上。"
									:"[npc.nameHasFull]迫切地想要来一番颜面骑乘，跨过[npc2.name]坐了下去，将下体紧紧地压在了[npc2.her]的[npc2.face]上。"));
						} else {
							sb.append(UtilText.parse(faceSitting, lyingDown,
									continuation
									?"[npc.nameHasFull]同时也迫切地想要来一番颜面骑乘， 跨过[npc2.name]"
											+ "将兽态的[npc.legRace]身躯降了下去，动物般的下体紧紧地压在了[npc2.her]的[npc2.face]上。"
									:"[npc.nameHasFull]迫切地想要来一番颜面骑乘， 跨过[npc2.name]"
											+  "将兽态的[npc.legRace]身躯降了下去，动物般的下体紧紧地压在了[npc2.her]的[npc2.face]上。"));
						}
					}
					if(faceSittingReverse!=null) {
						if(!faceSittingReverse.isTaur()) {
							sb.append(UtilText.parse(faceSittingReverse, lyingDown,
									continuation
									?"[npc.nameHasFull]同时也跨过[npc2.name]，转过身面向[npc2.her]的下半身后便坐了下去，准备来一番反颜面骑乘。"
									:"[npc.nameHasFull]跨过[npc2.name]，转过身面向[npc2.her]的下半身后便坐了下去，准备来一番反颜面骑乘。"));
						} else {
							sb.append(UtilText.parse(faceSittingReverse, lyingDown,
									continuation
									?"[npc.nameHasFull]同时也跨过[npc2.name]，"
											+ "转过身面向[npc2.her]的下半身后，便将兽态的[npc.legRace]身躯降了下去，准备来一番反颜面骑乘。"
									:"[npc.NameHasFull]跨过[npc2.name]，"
											+ "转过身面向[npc2.her]的下半身后，便将兽态的[npc.legRace]身躯降了下去，准备来一番反颜面骑乘。"));
						}
					}
					if(sixtyNine!=null) {
						sb.append(UtilText.parse(sixtyNine, lyingDown,
								continuation
								?"[npc.nameHasFull]同时也迫切地想要用一下嘴巴，跨过[npc2.name]，转身趴了下去，摆成六九的姿势。"
								:"[npc.nameHasFull]迫切地想要用一下嘴巴，跨过[npc2.name]，转身趴了下去，摆成六九的姿势。"));
					}
					if(lapPillow!=null) {
						sb.append(UtilText.parse(lapPillow, lyingDown,
								continuation
								?"[npc.nameHasFull]同时也迫切地想关照一下[npc2.namePos]，在[npc2.her]的[npc2.face]旁跪了下来，抬起[npc2.her]的头让[npc2.her]躺在了大腿上。"
								:"[npc.nameHasFull]迫切地想关照一下[npc2.namePos]，在[npc2.her]的[npc2.face]旁跪了下来，抬起[npc2.her]的头让[npc2.her]躺在了大腿上。"));
					}
	
					if(performingOral!=null) {
						sb.append(UtilText.parse(performingOral, lyingDown,
								"[npc.nameHasFull]想要为[npc2.name]提供口交，于是趴在[npc2.her]的[npc2.legs]间，正面对着下体的位置。"));
					}
				}
			}
			
			if(besideNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(besideNames, false))
						+"移动到旁边，准备好接受口交或其他非插入行为。"); 
				
			} else if(besideNames.size()==1) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(besideNames, false))
						+UtilText.parse(mainBeside,
								"动到旁边，准备好接受口交或其他非插入行为。")); 
			}
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			// For each character lying down, describe them and those who are interacting with them:

			List<List<SexSlot>> positionLists = new ArrayList<>();
			positionLists.add(position1);
			positionLists.add(position2);
			positionLists.add(position3);
			positionLists.add(position4);
			
			List<SexSlot> besideSlots = Util.newArrayListOfValues(SexSlotLyingDown.BESIDE, SexSlotLyingDown.BESIDE_TWO, SexSlotLyingDown.BESIDE_THREE, SexSlotLyingDown.BESIDE_FOUR);
			
			for(int i=0; i<positionLists.size(); i++) {
				List<SexSlot> positions = positionLists.get(i);
				
				SexSlot lyingDown = positions.get(0);
				SexSlot lyingDownFront = positions.get(1);
				SexSlot cowgirl = positions.get(2);
				SexSlot cowgirlReverse = positions.get(3);
				SexSlot missionary = positions.get(4);
				SexSlot matingPress = positions.get(5);
				SexSlot scissoring = positions.get(6);
				SexSlot faceSitting = positions.get(7);
				SexSlot faceSittingReverse = positions.get(8);
				SexSlot sixtyNine = positions.get(9);
				SexSlot receivingOral = positions.get(10);
				SexSlot lapPillow = positions.get(11);
				SexSlot performingOral = positions.get(12);
				
				// Lying on front:

				interactions.add(StandardSexActionInteractions.allFoursBehind.getSexActionInteractions(missionary, lyingDownFront));
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, lyingDownFront));
				interactions.add(StandardSexActionInteractions.faceSittingRiding.getSexActionInteractions(receivingOral, lyingDownFront)); // Use same interactions as face sitting
				interactions.add(StandardSexActionInteractions.allFoursCharacterBehindToCharactersFront.getSexActionInteractions(receivingOral, missionary)); // Use same interaction as all fours in front/behind
				
				// Lying on back:
				
				interactions.add(StandardSexActionInteractions.cowgirlRiding.getSexActionInteractions(cowgirl, lyingDown));
				interactions.add(StandardSexActionInteractions.cowgirlReverseRiding.getSexActionInteractions(cowgirlReverse, lyingDown));
				interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, lyingDown));
				// Add missionary to lying down characters beside the main target:
				if(i>0) {
					interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, positionLists.get(i-1).get(0)));
				}
				if(i+1<positionLists.size()) {
					interactions.add(StandardSexActionInteractions.missionary.getSexActionInteractions(missionary, positionLists.get(i+1).get(0)));
				}
				
				interactions.add(StandardSexActionInteractions.matingPress.getSexActionInteractions(matingPress, lyingDown));
				interactions.add(StandardSexActionInteractions.scissoring.getSexActionInteractions(scissoring, lyingDown));
				interactions.add(StandardSexActionInteractions.faceSittingRiding.getSexActionInteractions(faceSitting, lyingDown));
				interactions.add(StandardSexActionInteractions.faceSittingReverseRiding.getSexActionInteractions(faceSittingReverse, lyingDown));
				interactions.add(StandardSexActionInteractions.sixtyNine.getSexActionInteractions(sixtyNine, lyingDown));
				interactions.add(StandardSexActionInteractions.sixtyNine.getSexActionInteractions(lyingDown, sixtyNine));
				interactions.add(StandardSexActionInteractions.lapPillow.getSexActionInteractions(lapPillow, lyingDown));
				
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, lyingDown));
				// Add oral to lying down characters beside the main target:
				if(i>0) {
					interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, positionLists.get(i-1).get(0)));
				}
				if(i+1<positionLists.size()) {
					interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, positionLists.get(i+1).get(0)));
				}
				
				interactions.add(StandardSexActionInteractions.performingOralToLyingDown.getSexActionInteractions(performingOral, matingPress));
				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(performingOral, cowgirl));
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(performingOral, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.performingOralToSixtyNine.getSexActionInteractions(performingOral, sixtyNine));
				
				
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(faceSittingReverse, scissoring));
				interactions.add(StandardSexActionInteractions.characterToCharactersBack.getSexActionInteractions(faceSittingReverse, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(lapPillow, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(lapPillow, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersBack.getSexActionInteractions(lapPillow, cowgirlReverse));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(cowgirlReverse, missionary));
				interactions.add(StandardSexActionInteractions.characterToCharactersFront.getSexActionInteractions(cowgirlReverse, scissoring));

				interactions.add(StandardSexActionInteractions.characterToCharactersBackSex.getSexActionInteractions(missionary, cowgirl));
				interactions.add(StandardSexActionInteractions.characterToCharactersBackSex.getSexActionInteractions(missionary, matingPress));

				// Those beside can interact with all lying down and kneeling:
				for(SexSlot beside : besideSlots) {
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, lyingDown));
					interactions.add(StandardSexActionInteractions.besideKneeling.getSexActionInteractions(beside, lyingDownFront));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, cowgirl));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, cowgirlReverse));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, missionary));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, scissoring));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, faceSitting));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, faceSittingReverse));
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(beside, lapPillow));
				}
			}
			
			// Those lying down can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR));

			// Those in missionary positions can kiss the ones next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// Characters riding the one lying down can use their bodyweight to force a creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_FOUR
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.COWGIRL_REVERSE_FOUR
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_TWO
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_THREE
							|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MATING_PRESS_FOUR) {
						return Util.newHashMapOfValues(
								new Value<>(Torso.class, genericGroinForceCreampieAreas));
					}
			}
			// Characters performing sixty-nine or lying down oral can use weight to force a facial creampie:
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.SIXTY_NINE_FOUR
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.MISSIONARY_ORAL_FOUR) {
				if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Torso.class, genericFaceForceCreampieAreas));
				}
			}
			// Characters lying down can use their legs to force a creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MISSIONARY_FOUR
				|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.MATING_PRESS_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Leg.class, genericGroinForceCreampieAreas),
							new Value<>(Tail.class, genericGroinForceCreampieAreas),
							new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
				}
			}
			// Characters performing oral on sixty-nine can use arms to force a facial creampie:
			if(Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotLyingDown.SIXTY_NINE_FOUR) {
				if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_TWO
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_THREE
						|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotLyingDown.LYING_DOWN_FOUR) {
					return Util.newHashMapOfValues(
							new Value<>(Arm.class, genericFaceForceCreampieAreas));
				}
			}
			
			return null;
		}
		@Override
		public Map<SexAreaPenetration, Integer> getRestrictedPenetrationCounts(GameCharacter penetrator) {
			List<SexSlot> slotsTop = Util.newArrayListOfValues(SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.MATING_PRESS_FOUR);
			List<SexSlot> slotsBottom = Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR);
			
			for(int i=0;i<4;i++) {
				GameCharacter top = Main.sex.getCharacterInPosition(slotsTop.get(i));
				GameCharacter bottom = Main.sex.getCharacterInPosition(slotsBottom.get(i));
				
				if(penetrator.equals(top) && bottom!=null) {
					return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(bottom.getArmRows()*2)));
				}
				if(penetrator.equals(bottom)&& top!=null) {
					return Util.newHashMapOfValues(new Value<>(SexAreaPenetration.FINGER, -(top.getArmRows()*2)));
				}
			}
			
			return super.getRestrictedPenetrationCounts(penetrator);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
//			// Restrict targeting lying down oral and missionary if there's another person in the same position focused on them:
//			if(performer!=target) {
//				List<SexSlot> slotsLyingDown = Util.newArrayListOfValues(SexSlotLyingDown.LYING_DOWN, SexSlotLyingDown.LYING_DOWN_TWO, SexSlotLyingDown.LYING_DOWN_THREE, SexSlotLyingDown.LYING_DOWN_FOUR);
//				List<SexSlot> slotsPerformingOral = Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY_ORAL, SexSlotLyingDown.MISSIONARY_ORAL_TWO, SexSlotLyingDown.MISSIONARY_ORAL_THREE, SexSlotLyingDown.MISSIONARY_ORAL_FOUR);
//				List<SexSlot> slotsMissionary = Util.newArrayListOfValues(SexSlotLyingDown.MISSIONARY, SexSlotLyingDown.MISSIONARY_TWO, SexSlotLyingDown.MISSIONARY_THREE, SexSlotLyingDown.MISSIONARY_FOUR);
//				for(int i=0; i<4; i++) {
//					if(Main.sex.getSexPositionSlot(target)!=slotsLyingDown.get(i) && (Main.sex.getSexPositionSlot(performer)==slotsPerformingOral.get(i) || Main.sex.getSexPositionSlot(performer)==slotsMissionary.get(i))) {
//						int targetIndex = 0;
//						for(int j=0; j<4; j++) {
//							if(Main.sex.getSexPositionSlot(target)==slotsLyingDown.get(j)) {
//								targetIndex = j;
//								break;
//							}
//						}
//						if(Main.sex.getSexPositionSlot(performer)==slotsPerformingOral.get(i) && Main.sex.getAllOccupiedSlots(false).containsValue(slotsPerformingOral.get(targetIndex))) {
//							return true;
//						}
//						if(Main.sex.getSexPositionSlot(performer)==slotsMissionary.get(i) && Main.sex.getAllOccupiedSlots(false).containsValue(slotsMissionary.get(targetIndex))) {
//							return true;
//						}
//					}
//				}
//			}
			
			// Restrict fucking breasts if cowgirl character is riding cock.
			if(Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.COWGIRL) && Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(performer.equals(cowgirl)
							&& action.getPerformingCharacterAreas().contains(SexAreaPenetration.PENIS)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.BREAST) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.NIPPLE))
							&& (Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.VAGINA, target).contains(SexAreaPenetration.PENIS)
									|| Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.ANUS, target).contains(SexAreaPenetration.PENIS)
									|| Main.sex.getOngoingSexAreas(performer, SexAreaOrifice.ASS, target).contains(SexAreaPenetration.PENIS))) {
						return true;
					}
				}
			}
			
			// Restrict vaginal actions if cowgirl is riding cock anally.
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.COWGIRL) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(target.equals(cowgirl)
							&& action.getTargetedCharacterAreas().contains(SexAreaOrifice.VAGINA)
							&& (Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.ANUS) || Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.ASS))) {
						return true;
					}
				}
			}
			
			// Restrict anal actions if reverse cowgirl is riding cock.
			if(Main.sex.getSexPositionSlot(target).hasTag(SexSlotTag.COWGIRL_REVERSE) && Main.sex.getSexPositionSlot(performer).hasTag(SexSlotTag.MISSIONARY)) {
				List<SexSlot> cowgirlList = Util.newArrayListOfValues(SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.COWGIRL_REVERSE_FOUR);
				for(int i=0; i<4; i++) {
					GameCharacter cowgirl = Main.sex.getCharacterInPosition(cowgirlList.get(i));
					if(target.equals(cowgirl)
							&& (action.getTargetedCharacterAreas().contains(SexAreaOrifice.ANUS) || action.getTargetedCharacterAreas().contains(SexAreaOrifice.ASS))
							&& Main.sex.isOrificeNonSelfOngoingAction(target, SexAreaOrifice.VAGINA)) {
						return true;
					}
				}
			}
			
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.LAP_PILLOW)
					|| Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.MATING_PRESS)
					|| Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.SIXTY_NINE)
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL, SexSlotLyingDown.COWGIRL_REVERSE, SexSlotLyingDown.FACE_SITTING, SexSlotLyingDown.FACE_SITTING_REVERSE, SexSlotLyingDown.MATING_PRESS, SexSlotLyingDown.SIXTY_NINE)))
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN_TWO
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL_TWO, SexSlotLyingDown.COWGIRL_REVERSE_TWO, SexSlotLyingDown.FACE_SITTING_TWO, SexSlotLyingDown.FACE_SITTING_REVERSE_TWO, SexSlotLyingDown.MATING_PRESS_TWO, SexSlotLyingDown.SIXTY_NINE_TWO)))
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN_THREE
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL_THREE, SexSlotLyingDown.COWGIRL_REVERSE_THREE, SexSlotLyingDown.FACE_SITTING_THREE, SexSlotLyingDown.FACE_SITTING_REVERSE_THREE, SexSlotLyingDown.MATING_PRESS_THREE, SexSlotLyingDown.SIXTY_NINE_THREE)))
				|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotLyingDown.LYING_DOWN_FOUR
						&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
							SexSlotLyingDown.COWGIRL_FOUR, SexSlotLyingDown.COWGIRL_REVERSE_FOUR, SexSlotLyingDown.FACE_SITTING_FOUR, SexSlotLyingDown.FACE_SITTING_REVERSE_FOUR, SexSlotLyingDown.MATING_PRESS_FOUR, SexSlotLyingDown.SIXTY_NINE_FOUR)))) {
				return false;
			}
			return true;
		}
	};
	
	public static final AbstractSexPosition SITTING = new AbstractSexPosition("坐姿",
			8,
			true,
			Util.mergeLists(
					SexActionPresets.positioningActionsNew,
					Util.newArrayListOfValues(ChairSex.class)),
			new ArrayList<>()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			List<List<SexSlot>> mutuallyExclusiveSlots = new ArrayList<>();
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.PERFORMING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));

			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL, SexSlotSitting.SITTING_BETWEEN_LEGS));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
			mutuallyExclusiveSlots.add(Util.newArrayListOfValues(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
			
			for(List<SexSlot> entry : mutuallyExclusiveSlots) {
				for(SexSlot s : entry) {
					if(s==slot) {
						for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
							if(entry.contains(e.getValue()) && e.getValue()!=slot && !e.getKey().equals(characterToTakeSlot)) {
								return new Value<Boolean, String>(
										false,
										"“"+Util.capitaliseSentence(slot.getDescription())+"”栏位不能再使用，因为"
												+ "“"+Util.capitaliseSentence(e.getValue().getDescription())+"”栏位已经被分配给"+(UtilText.parse(e.getKey(), "[npc.name]"))+"。");
							}
						}
					}
				}
			}
			
//			if(characterToTakeSlot.isTaur()
//					&& (slot==SexSlotSitting.SITTING
//							|| slot==SexSlotSitting.SITTING_TWO
//							|| slot==SexSlotSitting.SITTING_THREE
//							|| slot==SexSlotSitting.SITTING_FOUR)) {
//				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "Due to the proportions of [npc.her] animalistic lower body, [npc.nameIsFull] unable to use the '"+Util.capitaliseSentence(slot.getDescription())+"' slot."));
//			}
			if(!characterToTakeSlot.isTaur()
					&& (slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE
							|| slot==SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "只有下半身为兽态动物肢体的角色才能使用“"+Util.capitaliseSentence(slot.getDescription())+"”栏位。"));
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public Value<Boolean, String> isAcceptablePosition(Map<GameCharacter, SexSlot> positioningSlots) {
			boolean suitableSitting = false;
			for(Entry<GameCharacter, SexSlot> e : positioningSlots.entrySet()) {
				if(e.getValue()==SexSlotSitting.SITTING
						|| e.getValue()==SexSlotSitting.SITTING_TWO
						|| e.getValue()==SexSlotSitting.SITTING_THREE
						|| e.getValue()==SexSlotSitting.SITTING_FOUR) {
					suitableSitting = true;
				}
			}
			if(!suitableSitting) {
				return new Value<Boolean, String>(false, "这种姿势至少需要一个角色呈坐姿才能生效。");
			}
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			StringBuilder sb = new StringBuilder();

			List<String> sittingNames = new ArrayList<>();
			List<String> allInLapNames = new ArrayList<>();
			List<String> inLapNames = new ArrayList<>();
			List<String> inLapNamesTaur = new ArrayList<>();
			List<String> allBetweenLegsNames = new ArrayList<>();
			List<String> betweenLegsNames = new ArrayList<>();
			List<String> betweenLegsNamesTaur = new ArrayList<>();
			List<String> performingOralNames = new ArrayList<>();
			List<String> taurPresentingNames = new ArrayList<>();

			GameCharacter mainSitting = null;
			GameCharacter mainInLap = null;
			GameCharacter mainInLapTaur = null;
			GameCharacter mainBetweenLegs = null;
			GameCharacter mainPerformingOral = null;
			GameCharacter mainTaurPresenting = null;
			
			boolean playerSitting = false;
			boolean playerBetweenLegs = false;

			for(Entry<GameCharacter, SexSlot> e : occupiedSlots.entrySet()) {
				if(e.getValue()!=SexSlotGeneric.MISC_WATCHING) {
					if(e.getValue()==SexSlotSitting.SITTING
							|| e.getValue()==SexSlotSitting.SITTING_TWO
							|| e.getValue()==SexSlotSitting.SITTING_THREE
							|| e.getValue()==SexSlotSitting.SITTING_FOUR) {
						if(mainSitting==null) {
							mainSitting=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerSitting = true;
							sittingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							sittingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else if(e.getValue()==SexSlotSitting.SITTING_IN_LAP
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_TWO
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_THREE
							|| e.getValue()==SexSlotSitting.SITTING_IN_LAP_FOUR) {
						if(mainInLap==null && !e.getKey().isTaur()) {
							mainInLap=e.getKey();
						}
						if(mainInLapTaur==null && e.getKey().isTaur()) {
							mainInLapTaur=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							allInLapNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								inLapNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								inLapNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allInLapNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								inLapNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								inLapNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_TWO
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_THREE
							|| e.getValue()==SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR) {
						if(mainBetweenLegs==null) {
							mainBetweenLegs=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							playerBetweenLegs = true;
							allBetweenLegsNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								betweenLegsNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								betweenLegsNamesTaur.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
							}
						} else {
							allBetweenLegsNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							if(!e.getKey().isTaur()) {
								betweenLegsNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
							} else {
								betweenLegsNamesTaur.add(UtilText.parse(e.getKey(), "[npc.name]"));
							}
						}
						
					} else if(e.getValue()==SexSlotSitting.PERFORMING_ORAL
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_TWO
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_THREE
							|| e.getValue()==SexSlotSitting.PERFORMING_ORAL_FOUR) {
						if(mainPerformingOral==null) {
							mainPerformingOral=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							performingOralNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							performingOralNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
						
					} else {
						if(mainTaurPresenting==null) {
							mainTaurPresenting=e.getKey();
						}
						if(e.getKey().isPlayer()) {
							taurPresentingNames.add(0, UtilText.parse(e.getKey(), "[npc.name]"));
						} else {
							taurPresentingNames.add(UtilText.parse(e.getKey(), "[npc.name]"));
						}
					}
				}
			}
			
			
			if(sittingNames.size()>=2) {
				sb.append(Util.capitaliseSentence(Util.stringsToStringList(sittingNames, false))+"紧挨着坐了下来， 而"); 
				
			} else {
				sb.append(UtilText.parse(mainSitting, "[npc.Name]坐在椅子上，而")); 
			}
			
			
			// Sitting in lap:
			int sittingLapCount = allInLapNames.size();
			boolean sentenceContinuationFound = true;
			if(sittingLapCount>=2) {
				if(inLapNames.isEmpty()) {
					sb.append(Util.stringsToStringList(inLapNamesTaur, false)+ "则转过身去，稍稍后退，便将其兽态的身躯放低在"
					+(sittingNames.size()>1
							?(playerSitting?"你们的大腿上。":"他们的大腿上。")
							:UtilText.parse(mainSitting, "[npc.her]的大腿上。")));
					
				} else {
						sb.append(Util.stringsToStringList(allInLapNames, false)+"上前去，然后坐在了"
									+(sittingNames.size()>1
											?(playerSitting?"你们的大腿上。":"他们的大腿上。")
											:UtilText.parse(mainSitting, "[npc.her]的大腿上。")));
						if(!inLapNamesTaur.isEmpty()) {
							sb.append("为了做出这种姿势，"+Util.capitaliseSentence(Util.stringsToStringList(inLapNamesTaur, false))
								+"不得不转过身去，稍稍后退，将其兽态的身躯放低在"
								+(sittingNames.size()>1
										?(playerSitting?"你们的胯下。":"他们的胯下。")
										:UtilText.parse(mainSitting, "[npc.her]的胯下。")));
						}
				}
				
			} else if(inLapNames.size()==1) {
				sb.append(Util.stringsToStringList(inLapNames, false)+UtilText.parse(mainInLap, "上前去，然后坐在了")
						+(sittingNames.size()>1
								?(playerSitting?"你们的大腿上。":"他们的大腿上。")
								:UtilText.parse(mainSitting, "[npc.her]的大腿上。")));
				
			} else if(inLapNamesTaur.size()==1) {
				sb.append(Util.stringsToStringList(inLapNamesTaur, false)+UtilText.parse(mainInLapTaur, "转过身去，稍稍后退，便将[npc.her]兽态的[npc.legRace]身躯放低在")
				+(sittingNames.size()>1
						?(playerSitting?"你们的大腿上。":"他们的大腿上。")
						:UtilText.parse(mainSitting, "[npc.her]的大腿上。")));
			} else {
				sentenceContinuationFound=false;
			}
			
			// Between legs:
			int betweenLegsCount = allBetweenLegsNames.size();
			if(betweenLegsCount>=2) {
				if(betweenLegsNames.isEmpty()) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNamesTaur, false)):Util.stringsToStringList(betweenLegsNamesTaur, false))
						+ "上前去，跨过"
					+(sittingNames.size()>1
							?(playerSitting ?"你们" :"他们")
							:UtilText.parse(mainSitting, "[npc.name]"))
					+"，让动物般下肢的腹股沟处顶住了"
					+(sittingNames.size()>1
							?(playerSitting ?"你们的下体。" :"他们的下体。")
							:UtilText.parse(mainSitting, "[npc.hers]的下体。")));
					
				} else {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNames, false)):Util.stringsToStringList(betweenLegsNames, false))
							+"上前来，站立在"
								+(sittingNames.size()>1
										?(playerSitting?"你们的腿":"他们的腿")
										:UtilText.parse(mainSitting, "[npc.namePos]的[npc.legs]")));
					if(!betweenLegsNamesTaur.isEmpty()) {
						sb.append("之间，而"+Util.stringsToStringList(betweenLegsNamesTaur, false)+"则上前跨在"
								+(sittingNames.size()>1
										?(playerSitting ?"你们" :"他们")
										:UtilText.parse(mainSitting, "[npc.name]"))
								+(betweenLegsNamesTaur.size()==1
									?UtilText.parse(mainBetweenLegs, "的头顶，让[npc.her]动物般[npc.legRace]身躯的腹股沟顶住了")
									:"，让动物般下肢的腹股沟处顶住了")
								+(sittingNames.size()>1
										?(playerSitting ?"你们的下体。" :"他们的下体。")
										:UtilText.parse(mainSitting, "[npc.hers]的下体。")));
					} else {
						sb.append("。");
					}
				}
				sentenceContinuationFound = true;
				
			} else if(betweenLegsNames.size()==1) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNames, false)):Util.stringsToStringList(betweenLegsNames, false))
						+UtilText.parse(mainBetweenLegs, "上前来，正站在")
						+(sittingNames.size()>1
								?(playerSitting?"你们的腿之间。":"他们的腿之间。")
								:UtilText.parse(mainSitting, "[npc.her]的[npc.legs]之间。")));
				sentenceContinuationFound = true;
				
			} else if(betweenLegsNamesTaur.size()==1) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(betweenLegsNamesTaur, false)):Util.stringsToStringList(betweenLegsNamesTaur, false))
						+UtilText.parse(mainBetweenLegs, "上前跨在")
						+(sittingNames.size()>1
								?(playerSitting ?"你们" :"他们")
								:UtilText.parse(mainSitting, "[npc.name]"))
						+UtilText.parse(mainBetweenLegs, "的上方，让[npc.her]动物般[npc.legRace]身躯的腹股沟顶住了")
						+(sittingNames.size()>1
								?(playerSitting ?"你们的下体。" :"他们的下体。")
								:UtilText.parse(mainSitting, "[npc.hers]的下体。")));
				sentenceContinuationFound = true;
			}
			
			// Performing oral:
			if(performingOralNames.size()>=2) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false)):Util.stringsToStringList(performingOralNames, false))
							+"附身移动到"
							+(sittingNames.size()>1
									?(playerSitting?"你们的腿":"他们的腿")+"之间，准备好提供口交。"
									:UtilText.parse(mainSitting, "[npc.namePos]的[npc.legs]之间，准备好提供口交。")));
					sentenceContinuationFound = true;
				
			} else if(performingOralNames.size()==1) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(performingOralNames, false)):Util.stringsToStringList(performingOralNames, false))
							+UtilText.parse(mainPerformingOral,"正好移动到")
					+(sittingNames.size()>1
							?(playerSitting?"你们的腿":"他们的腿")+"之间，准备好提供口交。"
							:UtilText.parse(mainSitting, "[npc.namePos]的[npc.legs]之间，准备好提供口交。")));
					sentenceContinuationFound = true;
			}

			
			// Receiving oral:
			if(taurPresentingNames.size()>=2) {
				sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(taurPresentingNames, false)):Util.stringsToStringList(taurPresentingNames, false))
						+"转过身去，稍稍后退，将其动物般的下体展示在"
						+(sittingNames.size()>1
								?(playerSitting?"你们"+Util.intToString(sittingNames.size())+"个面前。":"他们"+Util.intToString(sittingNames.size())+"个面前。")
								:UtilText.parse(mainSitting, "[npc.name]面前。")));
			
				
			} else if(taurPresentingNames.size()==1) {
					sb.append((sentenceContinuationFound?Util.capitaliseSentence(Util.stringsToStringList(taurPresentingNames, false)):Util.stringsToStringList(taurPresentingNames, false))
							+UtilText.parse(mainTaurPresenting,"转过身去，稍稍后退，将其兽态的后半身展示在")
					+(sittingNames.size()>1
							?(playerSitting?"你们"+Util.intToString(sittingNames.size())+"个面前。":"他们"+Util.intToString(sittingNames.size())+"个面前。")
							:UtilText.parse(mainSitting, "[npc.name]面前。")));
			}
			
			
			return sb.toString();
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			// All performing oral can interact with one another and the sitting characters who don't have a sitting in lap
			
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();
			// Only not free if using the standing fucking position
			boolean performerFree1 = true;
			boolean performerFree2 = true;
			boolean performerFree3 = true;
			boolean performerFree4 = true;
			
			// Commented-out the interactions within if statements in v0.4.2.2, as having them be unavailable without someone in that slot was preventing NPCs from moving into those slots during sex
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING_BETWEEN_LEGS));
					performerFree1 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_BETWEEN_LEGS));
				performerFree1 = false;
			}

			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TWO));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
					performerFree2 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_TWO)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));
				performerFree2 = false;
			}
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_THREE));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
					performerFree3 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_THREE)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));
				performerFree3 = false;
			}
			
			if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_IN_LAP_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_FOUR));
				if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)!=null) {
//					interactions.add(StandardSexActionInteractions.sittingBetweenLegsCharacterSitting.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
					performerFree4 = false;
				}
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			} else if(Main.sex.getCharacterInPosition(SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)!=null) {
//				interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
				performerFree4 = false;
			}
			
			// v0.4.2.2 added these from the commented-out ones above
			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_BETWEEN_LEGS));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TWO));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_BETWEEN_LEGS_TWO));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_THREE));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_BETWEEN_LEGS_THREE));

			interactions.add(StandardSexActionInteractions.performingOralBehind.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR));
			interactions.add(StandardSexActionInteractions.sittingInLap.getSexActionInteractions(SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_FOUR));
			interactions.add(StandardSexActionInteractions.sittingBetweenLegs.getSexActionInteractions(SexSlotSitting.SITTING_FOUR, SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR));
			
			
			
			// Sitting characters can kiss/use appendages on ones sitting next to them:
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING, SexSlotSitting.SITTING_TWO));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING_TWO, SexSlotSitting.SITTING_THREE));
			interactions.add(StandardSexActionInteractions.besideOneAnother.getSexActionInteractions(SexSlotSitting.SITTING_THREE, SexSlotSitting.SITTING_FOUR));
			
			if(performerFree1) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING));
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_TWO));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree2) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_TWO));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_THREE));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_TWO, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree3) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_THREE));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_THREE));
				}
				if(performerFree4) {
					interactions.add(StandardSexActionInteractions.kneelingBeside.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.PERFORMING_ORAL_FOUR));
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_THREE, SexSlotSitting.SITTING_FOUR));
				}
			}
			if(performerFree4) {
				interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_FOUR));
				if(performerFree1) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING));
				}
				if(performerFree2) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_TWO));
				}
				if(performerFree3) {
					interactions.add(StandardSexActionInteractions.performingOral.getSexActionInteractions(SexSlotSitting.PERFORMING_ORAL_FOUR, SexSlotSitting.SITTING_THREE));
				}
			}

			return generateSlotTargetsMap(interactions);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			// The character sucking cock can use their arms to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.PERFORMING_ORAL_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Arm.class, genericFaceForceCreampieAreas));
			}
			// The character riding cock can use their body weight to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_IN_LAP_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Torso.class, genericGroinForceCreampieAreas));
			}
			// The character sitting getting fucked can use their legs, tail, or tentacles to force a creampie:
			if((Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_TWO
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_THREE
					|| Main.sex.getSexPositionSlot(cumTarget)==SexSlotSitting.SITTING_FOUR)
				&& (Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_TWO
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_THREE
					|| Main.sex.getSexPositionSlot(cumProvider)==SexSlotSitting.SITTING_BETWEEN_LEGS_FOUR)) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas),
						new Value<>(Tail.class, genericGroinForceCreampieAreas),
						new Value<>(Tentacle.class, genericGroinForceCreampieAreas));
			}
			
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			if(Main.sex.getSexPositionSlot(autoOralCharacter).hasTag(SexSlotTag.SITTING_IN_LAP)
					|| (Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL)))
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING_TWO
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP_TWO, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_TWO))
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING_THREE
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP_THREE, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_THREE))
					|| Main.sex.getSexPositionSlot(autoOralCharacter)==SexSlotSitting.SITTING_FOUR
							&& !Collections.disjoint(Main.sex.getAllOccupiedSlots(false).values(), Util.newArrayListOfValues(
									SexSlotSitting.SITTING_IN_LAP_FOUR, SexSlotSitting.SITTING_TAUR_PRESENTING_ORAL_FOUR))) {
				return false;
			}
			return true;
		}
	};
	
	
	
	
	//--- Unique and one-off sex scenes ---//
	
	public static final AbstractSexPosition BREEDING_STALL = new AbstractSexPosition("配种台",
			2,
			true,
			null, Util.newArrayListOfValues()) {
		@Override
		public Value<Boolean, String> isSlotUnlocked(GameCharacter characterToTakeSlot, SexSlot slot, Map<GameCharacter, SexSlot> positioningSlots) {
			if(characterToTakeSlot.isTaur() && (slot==SexSlotBreedingStall.BREEDING_STALL_BACK)) {
				return new Value<Boolean, String>(false, UtilText.parse(characterToTakeSlot, "由于[npc.namePos]动物般的下肢，[npc.her]没法平躺下接受配种。"));
			}
			
			return new Value<Boolean, String>(true, "");
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			boolean front = true;
			GameCharacter lyingDown = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_FRONT);
			if(lyingDown==null) {
				front = false;
				lyingDown = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_BACK);
			}
			
			if(lyingDown.isPlayer()) {
				return "你"+(front?"俯卧":"平躺")+"在一张长椅上，腿部和下腹部都通过墙上的洞伸了出去，向外面的配种员展示着你的小穴。"
						+ (Main.game.getPlayer().hasTail()
								?"等你摆好位置后，墙另一边有个人"
									+(Main.game.getPlayer().getTailCount()>1?"用金属夹子将你[pc.tailCount]条[pc.tails]都固定在了墙上":"用金属夹子将你的[pc.tails]固定在了墙上")
									+ "，防止你用[pc.tails]挡住你[pc.pussy+]。"
								:"");
			} else {
				GameCharacter character = Main.sex.getCharacterInPosition(SexSlotBreedingStall.BREEDING_STALL_FRONT);
				if(character!=null) {
					return "[npc.Name]"+(front?"俯卧":"平躺")+"在一张长椅上，腿部和下腹部都通过墙上的洞伸了出去，向外面的配种员展示着[npc.her]的小穴。"
							+ (character.hasTail()
									?"等[npc.she]摆好位置后，伊波娜走上前去，"
										+(character.getTailCount()>1?"用金属夹子将[npc.her][npc.tailCount]条[npc.tails]都固定在了墙上":"用金属夹子将[npc.her]的[npc.tails]固定在了墙上")
										+ "，防止[npc.herHim]用[npc.tails]挡住[npc.her][npc.pussy+]。"
									:"");
				} else {
					return "";
				}
			}
		}
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.breedingStallFucking.getSexActionInteractions(SexSlotBreedingStall.BREEDING_STALL_FUCKING, SexSlotBreedingStall.BREEDING_STALL_FRONT));
			interactions.add(StandardSexActionInteractions.breedingStallFucking.getSexActionInteractions(SexSlotBreedingStall.BREEDING_STALL_FUCKING, SexSlotBreedingStall.BREEDING_STALL_BACK));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			boolean performerIsMother = Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_FRONT || Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_BACK;
			if((performerIsMother
					&& (action.getSexAreaInteractions().keySet().contains(SexAreaPenetration.TAIL)
							|| ((action.getSexAreaInteractions().values().contains(SexAreaOrifice.VAGINA) || action.getSexAreaInteractions().values().contains(SexAreaPenetration.PENIS)) && action.getParticipantType()==SexParticipantType.SELF)))
				|| (Main.sex.getSexPositionSlot(performer)==SexSlotBreedingStall.BREEDING_STALL_FUCKING
						&& action.getSexAreaInteractions().values().contains(SexAreaPenetration.TAIL)
						&& action.getParticipantType()!=SexParticipantType.SELF)) {
				return true;
			}
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					PlayerTalk.PLAYER_OFFER_ANAL,
					PlayerTalk.PLAYER_OFFER_NAIZURI,
					PlayerTalk.PLAYER_OFFER_NIPPLE,
					PlayerTalk.PLAYER_OFFER_ORAL,
					PlayerTalk.PLAYER_OFFER_PAIZURI,
					!Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_OFFER_VAGINAL,
					PlayerTalk.PLAYER_REQUEST_ANAL,
					PlayerTalk.PLAYER_REQUEST_ORAL,
					Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_REQUEST_VAGINAL);
			if(blockedActions.contains(action)) {
				return true;
			}
			
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		protected Map<Class<? extends BodyPartInterface>,  List<SexAreaInterface>> getForcedCreampieMap(GameCharacter cumTarget, GameCharacter cumProvider) {
			if(Main.sex.getSexPositionSlot(cumTarget)==SexSlotBreedingStall.BREEDING_STALL_BACK
					&& Main.sex.getSexPositionSlot(cumProvider)==SexSlotBreedingStall.BREEDING_STALL_FUCKING) {
				return Util.newHashMapOfValues(
						new Value<>(Leg.class, genericGroinForceCreampieAreas));
			}
			return null;
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return false;
		}
	};
	

	
	public static final AbstractSexPosition GLORY_HOLE = new AbstractSexPosition("寻欢洞口交",
			3,
			true,
			null, Util.newArrayListOfValues(GloryHole.class)) {
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					GenericOrgasms.GENERIC_PREPARATION_DENIAL,
					GenericActions.GENERIC_DENY,
					PlayerTalk.PLAYER_OFFER_ANAL,
					PlayerTalk.PLAYER_OFFER_NAIZURI,
					PlayerTalk.PLAYER_OFFER_NIPPLE,
					!Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_OFFER_ORAL,
					PlayerTalk.PLAYER_OFFER_PAIZURI,
					PlayerTalk.PLAYER_OFFER_VAGINAL,
					PlayerTalk.PLAYER_REQUEST_ANAL,
					Main.sex.isDom(performer)?null:PlayerTalk.PLAYER_REQUEST_ORAL,
					PlayerTalk.PLAYER_REQUEST_VAGINAL);
			if(blockedActions.contains(action)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			List<GameCharacter> characters = new ArrayList<>();
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_KNEELING));
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			StringBuilder sb = new StringBuilder();

			boolean c1Taur = characters.get(1).isTaur();
			if(Main.sex.getTotalParticipantCount(false)==3) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
				
				boolean c2Taur = characters.get(2).isTaur();
				
				if(c1Taur || c2Taur) {
					sb.append("[npc.NameIsFull]跪在地上，无论[npc2.name]和[npc3.name]如何将下体对准两边寻欢洞，[npc.herHim]都会不遗余力地侍奉。");
					for(int i=1; i<3; i++) {
						GameCharacter character = characters.get(i);
						if(character.hasPenis() && character.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
							sb.append(UtilText.parse(characters.get(0), character,
									"<br/><i>由于[npc2.namePos][npc2.cock+]位于其[npc2.legRace]的下半身，"
										+ "[npc2.name]很快就意识到并没有办法找到一种姿势，能将阴茎伸过寻欢洞。"
									+ "于是[npc2.she]便只得转过身去，将[npc2.her][npc2.pussy+]对着[npc.name]……</i>"));
							
						} else {
							sb.append(UtilText.parse(characters.get(0), character,
									"<br/><i>由于拥有[npc2.a_legRace]的下半身，"
									+ "[npc2.name]在狭窄的隔间中转身时发出了巨大的噪声，十分尴尬地将[npc2.her][npc2.pussy+]对着[npc.name]……</i>"));
						}
					}
					
				} else {
					if(characters.get(1).hasPenis() && characters.get(1).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
							&& characters.get(2).hasPenis() && characters.get(2).isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						sb.append("[npc.NameIsFull]跪在地上，随时准备侍奉从一边伸出来[npc2.namePos][npc2.cock+]和另一边[npc3.namePos][npc3.cock+]。");
					} else {
						sb.append("[npc.NameIsFull]跪在地上，无论[npc2.name]和[npc3.name]如何将下体对准两边寻欢洞，[npc.herHim]都会不遗余力地侍奉。");
					}
				}
				
			} else {
				if(c1Taur) {
					sb.append("[npc.NameIsFull]跪在地上，张嘴堵住了寻欢洞，随时准备好侍奉[npc2.name]展露出来的下体。");
					GameCharacter character = characters.get(1);
					if(character.hasPenis() && character.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						sb.append(UtilText.parse(characters.get(0), character,
								"<br/><i>由于[npc2.namePos][npc2.cock+]位于其[npc2.legRace]的下半身，"
									+ "[npc2.name]很快就意识到并没有办法找到一种姿势，能将阴茎伸过寻欢洞。"
								+ "于是[npc2.she]便只得转过身去，将[npc2.her][npc2.pussy+]对着[npc.name]……</i>"));
						
					} else {
						sb.append(UtilText.parse(characters.get(0), character,
								"<br/><i>由于拥有[npc2.a_legRace]的下半身，"
								+ "[npc2.name]在狭窄的隔间中转身时发出了巨大的噪声，十分尴尬地将[npc2.her][npc2.pussy+]对着[npc.name]……</i>"));
					}
					
				} else {
					sb.append("[npc.NameIsFull]跪在地上，张嘴堵住了寻欢洞，随时准备好侍奉[npc2.name]展露出来的下体。");
				}
			}
			
			return UtilText.parse(characters, sb.toString());
		}
		
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_KNEELING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_KNEELING, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return false;
		}
	};
	
	public static final AbstractSexPosition GLORY_HOLE_SEX = new AbstractSexPosition("寻欢洞性爱",
			3,
			true,
			null, Util.newArrayListOfValues(GloryHole.class)) {
		@Override
		public boolean isActionBlocked(GameCharacter performer, GameCharacter target, SexActionInterface action) {
			List<SexActionInterface> blockedActions = Util.newArrayListOfValues(
					GenericOrgasms.GENERIC_PREPARATION_DENIAL,
					GenericActions.GENERIC_DENY);
			if(blockedActions.contains(action)) {
				return true;
			}
			return super.isActionBlocked(performer, target, action);
		}
		@Override
		public String getDescription(Map<GameCharacter, SexSlot> occupiedSlots) {
			List<GameCharacter> characters = new ArrayList<>();
			
			boolean analFucking = false;
			if(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKED)!=null) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKED));
			} else {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED));
				analFucking = true;
			}
			characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_FUCKING));
			
			StringBuilder sb = new StringBuilder();
			
			if(analFucking) {
				sb.append("[npc.NameIsFull]将[npc.her][npc.asshole+]紧紧贴着身后的寻欢洞，");
			} else {
				sb.append("[npc.NameIsFull]将[npc.her][npc.pussy+]紧紧贴着身后的寻欢洞，");
			}
			
			if(characters.get(1).hasPenis() && characters.get(1).isAbleToAccessCoverableArea(CoverableArea.PENIS, true) && !characters.get(1).isTaur()) {
				sb.append("期待着[npc2.name]将[npc2.cock+]挺入洞中，填满淫穴。");
			} else {
				sb.append("期待着[npc2.name]尽情享用。");
			}

			if(Main.sex.getTotalParticipantCount(false)==3) {
				characters.add(Main.sex.getCharacterInPosition(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
				sb.append("而在隔间的另一边，[npc.Name]前倾着身子，继续用嘴巴为[npc3.namePos]的寻欢洞侍奉着。");
			}
			
			return UtilText.parse(characters, sb.toString());
		}
		
		@Override
		public Map<SexSlot, Map<SexSlot, SexActionInteractions>> getSlotTargets() {
			List<Value<SexSlot, Map<SexSlot, SexActionInteractions>>> interactions = new ArrayList<>();

			interactions.add(StandardSexActionInteractions.gettingFuckedGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_FUCKED, SexSlotUnique.GLORY_HOLE_FUCKING));
			interactions.add(StandardSexActionInteractions.gettingAnallyFuckedGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED, SexSlotUnique.GLORY_HOLE_FUCKING));
			
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_FUCKED, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			interactions.add(StandardSexActionInteractions.performingOralGloryHole.getSexActionInteractions(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED, SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE));
			
			return generateSlotTargetsMap(interactions);
		}
		@Override
		public boolean isSelfOralAvailable(GameCharacter autoOralCharacter) {
			return false;
		}
	};
	
	

	public static List<AbstractSexPosition> allSexPositions;
	
	public static Map<AbstractSexPosition, String> sexPositionToIdMap = new HashMap<>();
	public static Map<String, AbstractSexPosition> idToSexPositionMap = new HashMap<>();
	
	public static AbstractSexPosition getSexPositionFromId(String id) {
		id = Util.getClosestStringMatch(id, idToSexPositionMap.keySet());
		
		return idToSexPositionMap.get(id);
	}
	
	public static String getIdFromSexPosition(AbstractSexPosition perk) {
		return sexPositionToIdMap.get(perk);
	}

	static {
		allSexPositions = new ArrayList<>();
		
		// Hard-coded status effects:
		// SexPosition:
		Field[] fields = SexPosition.class.getFields();
		for(Field f : fields){
			if (AbstractSexPosition.class.isAssignableFrom(f.getType())) {
				
				AbstractSexPosition sexPosition;
				
				try {
					sexPosition = ((AbstractSexPosition) f.get(null));

					sexPositionToIdMap.put(sexPosition, f.getName());
					idToSexPositionMap.put(f.getName(), sexPosition);
					allSexPositions.add(sexPosition);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		// SexPositionUnique:
		fields = SexPositionUnique.class.getFields();
		for(Field f : fields){
			if (AbstractSexPosition.class.isAssignableFrom(f.getType())) {
				
				AbstractSexPosition sexPosition;
				
				try {
					sexPosition = ((AbstractSexPosition) f.get(null));

					sexPositionToIdMap.put(sexPosition, f.getName());
					idToSexPositionMap.put(f.getName(), sexPosition);
					allSexPositions.add(sexPosition);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static List<AbstractSexPosition> getAllSexPositions() {
		return allSexPositions;
	}
}
