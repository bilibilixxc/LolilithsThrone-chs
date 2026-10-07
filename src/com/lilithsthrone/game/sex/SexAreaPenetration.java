package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.3.7
 * @author Innoxia
 */
public enum SexAreaPenetration implements SexAreaInterface {
	
	PENIS(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "阴茎";
			}
			return owner.getPenisName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getPenisRawSizeValue();
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			return owner.getPenisDiameter();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, SexAreaOrifice.URETHRA_PENIS) && Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.PENIS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.PENIS;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.cock+]磨蹭自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.cock+]磨蹭自己的"+targetArea.getName(performer)+"。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]操干着自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]操干着自己的"+targetArea.getName(performer)+"。");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]压入[npc2.namePos][npc2.clit+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.Name]用[npc.cock+]的[npc.cockHead]在[npc2.namePos]的[npc2.labia]和[npc2.clit]上[npc.sexPaceVerb]磨蹭，"
												+ "调戏[npc2.herHim]，威胁可以随时插入。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]强行把[npc.cock+]的[npc.cockHead]放到[npc2.namePos]的[npc2.labia]间、[npc2.clit]上，"
												+ "残忍地调戏[npc2.herHim]，威胁可以随时插入。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]被迫在[npc2.namePos]的[npc2.pussy]和[npc2.clit]上磨蹭[npc.cock+]，但[npc.her]尽力抵抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")+"，即使[npc.namePos]把[npc.her]的[npc.cock]在[npc2.her]的[npc2.clit]上摩擦。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("很快，[npc2.name]发现自己极度性奋并[npc2.moaning]着要[npc.name]开始操[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("很快，"
												+ "[npc2.name]发现自己极度性奋，开始在[npc.namePos][npc.cock+]上磨蹭[npc2.clit+]，并命令[npc.name]保持不动。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]哭着恳求放过，拼命想要挣脱但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs]用[npc.cock+]的[npc.cockHead]在[npc2.namePos][npc2.labia]间和[npc2.clit]上[npc.sexPaceVerb]磨蹭。");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.fingers+]深入[npc.her][npc.cock+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]接受[npc2.name]的手交。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]为[npc.herHim]手交，[npc.Name]试图挣脱。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"同时[npc2.her][npc2.fingers]包裹着[npc.namePos][npc.cock+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.SexPaceVerb]用[npc2.fingers+]包裹住[npc.namePos]的[npc.cock]，在手淫时不亦乐乎。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]用[npc2.fingers+]包裹住[npc.namePos]的[npc.cock]，霸道地命令[npc.herHim]在被撸的时候不许动。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出可怜的呜咽声，试图恳求[npc.name]放过自己，但[npc.she]根本不理会并继续手淫。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]接受[npc2.name]的手淫。");
						}
						break;
					case FOOT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]沉睡着":"[npc.name]一动不动")+"，[npc.cock+]压住[npc2.namePos]的[npc2.feet]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]在[npc2.namePos][npc2.feet+]上磨蹭[npc.cock]，感到不亦乐乎。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]用[npc2.feet+]反复在[npc.Name]的[npc.cock]上磨蹭，[npc.Name]试图挣脱。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.feet+]满足着[npc.Name]的欲望。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.moans+]，很享受[npc.name]用[npc2.her][npc2.feet+]取悦自己。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]竭尽全力反抗，却无法阻止[npc.name]如此淫荡地游移[npc2.her]的[npc2.feet]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]接受[npc2.name]的[npc2.footjob]。");
						}
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"如雕塑一般")+"，[npc.cock+]压住[npc2.namePos]的[npc2.cock]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]用[npc.cock+]在[npc2.namePos]的[npc2.cock]来回[npc.sexPaceVerb]磨蹭。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]被迫用[npc.cock+]在[npc2.namePos]的[npc2.cock]上磨蹭，但[npc.her]尽力抵抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"不起任何反应")+"，即便[npc2.her][npc2.cock+]被这般玩弄。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.cock+]遭到粗暴的摧残，[npc2.her]忍不住发出一长串[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]哭着恳求放过，绝望地尝试，拼命想远离[npc.Name]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs]用[npc.her][npc.cock+]在[npc2.namePos]的[npc2.cock]上下磨蹭。");
						}
						break;
					case TAIL:
						boolean multipleTails = target.getTailCount()>1;
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name][npc2.tail+]缠绕在[npc.her][npc.cock+]上不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]接受[npc2.name]的尾交。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]想要挣脱[npc2.name]的尾交。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，同时将[npc2.her]"+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+"缠绕在[npc.namePos][npc.cock+]上。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]用"+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+"[npc2.SexPaceVerb]缠住[npc.namePos]的[npc.cock]，"
													+ "[npc2.name]非常乐意用"+(multipleTails?"它们":"它")+"撸动[npc.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]用"+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+"[npc2.SexPaceVerb]缠住[npc.namePos]的[npc.cock]，"
													+ "[npc2.name]强横地命令[npc.herHim]在[npc2.name]用"+(multipleTails?"它们":"它")+"帮[npc.herHim]撸出来前都不许动。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出令人怜惜的呜咽，试图恳求[npc.name]放过[npc2.herHim]，"
													+ "但[npc.she]只是忽略了[npc2.herHim]并接着使用[npc.her]的"+(multipleTails?"[npc2.tails+]":"[npc2.tail+]")+"来为[npc.herHim]撸出来。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]接受了[npc2.name]的尾交。");
						}
						break;
					case TENTACLE:
						boolean multipleTentacles = target.getTentacleCount()>1;
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name][npc2.tentacle+]缠绕在[npc.her][npc.cock+]上不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]接受了[npc2.name]的触手交。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图从[npc2.name]给[npc.herHim]带来的触手交中抽离。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，同时将[npc2.her]"+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+"缠绕在[npc.namePos][npc.cock+]上。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.SexPaceVerb]用"+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+"缠住[npc.namePos]的[npc.cock]。"
													+ "[npc2.name]对于用"+(multipleTentacles?"它们":"它")+"来帮[npc.herHim]射出来感到十分享受。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.SexPaceVerb]用"+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+"缠住[npc.namePos]的[npc.cock]。"
													+ "[npc2.name]强横地命令[npc.herHim]在[npc2.name]用"+(multipleTentacles?"它们":"它")+"帮[npc.herHim]撸出来前都不许动。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出令人怜惜的呜咽，试图恳求[npc.name]放过[npc2.herHim]，"
													+ "但[npc.she]只是忽略了[npc2.herHim]并接着使用[npc.her]的"+(multipleTentacles?"[npc2.tentacles+]":"[npc2.tentacle+]")+"来为[npc.herHim]撸出来。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]接受了[npc2.name]的触手交。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
										+"，即便[npc2.namePos][npc2.tongue+]在[npc.her][npc.cock+]上反复舔弄.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]很快发现[npc.herself]正因[npc2.namePos][npc2.tongue+]在[npc.her][npc.cock+]上反复舔弄而愉悦地[npc.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]用[npc2.tongue+]舔舐[npc.Name][npc.cock+]，[npc.Name]哭着反抗[npc2.her]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"[npc2.her][npc2.tongue]继续紧贴着[npc.namePos]的[npc.cock]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]嘴里被鸡巴塞满，只能发出一连串模糊不清的[npc2.moans]来表示[npc2.she]现在很开心。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]试图反抗，但最终[npc2.she]还是被迫以这种方式为[npc.namePos]的[npc.cock]服务。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.Name]的[npc.cock]被[npc2.name]舔弄。");
						}
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos][npc2.asshole+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.her][npc.cock+]的[npc.cockHead]压向[npc2.namePos][npc2.asshole+]，然后向前猛推，[npc.sexPaceVerb]操[npc2.name][npc2.ass+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]将[npc2.her][npc2.asshole+]向后压向[npc.Name][npc.cock+]的[npc.cockHead]，[npc.Name]拼命地想要拉开距离，"
												+ "[npc2.she]向后猛压并强迫[npc.name]操干[npc2.her][npc2.ass+]，[npc.name]绝望地哭泣着。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]压向[npc.name]，"
												+ "把[npc.namePos][npc.cock+]深深插入[npc2.her]的[npc2.asshole]，发出一连串[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，同时用[npc.cock+]在[npc2.her][npc2.asshole+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos][npc2.asshole+]。");
						}
						break;
					case ASS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]在[npc2.namePos]的肉臀间磨蹭，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]将[npc2.namePos]的臀肉推到一起，使[npc.her][npc.cock+]能够在臀缝间来回磨蹭。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.she]还是被强行用[npc.cock+]在[npc2.namePos]的臀缝间来回磨蹭。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.ass+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.sexPaceVerb]上下抖动[npc2.hips+]，强迫[npc.namePos]的[npc.cock]插入[npc2.her][npc2.asshole+]，发出一阵[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，被迫使用[npc2.her]的[npc2.ass]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尻交[npc2.namePos]的屁股。");
						}
						break;
					case ARMPITS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]在[npc2.namePos][npc2.armpit+]间磨蹭，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]让[npc2.name]抬起[npc2.arm]，使[npc.her][npc.cock+]能够在腋窝上下磨蹭。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.she]还是被强行用[npc.cock+]在[npc2.namePos]的腋窝间上下磨蹭。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.armpit+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.sexPaceVerb]抬起[npc2.arm]，强迫[npc.namePos]的[npc.cock]插入[npc2.her][npc2.armpit+]，发出一阵[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，[npc2.her]的[npc2.armpits]被强行操干。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]将[npc.her][npc.cock+]滑入[npc2.namePos]的腋窝。");
						}
						break;
					case BREAST:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]在[npc2.namePos][npc2.breasts+]间磨蹭，却不起任何反应。");
								} else {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]压在[npc2.namePos][npc2.breasts+]上，却不起任何反应。");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc.name]将[npc2.namePos][npc2.breasts+]推到一起，使[npc.her][npc.cock+]能够在乳沟间来回磨蹭。");
										} else {
											sb.append("[npc.Name]无法乳交[npc2.NamePos]贫瘠的胸部，但这不能阻止[npc.herHim]用[npc.cock+]在[npc2.namePos][npc2.breasts+]上来回磨蹭。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]将[npc2.her][npc2.breasts+]推到一起，无视[npc.namePos]抗拒的呜咽，强迫[npc.herHim]用[npc.cock+]在乳沟上下磨蹭。");
										} else {
											sb.append("[npc2.name]并没有因为自己的平胸而停下，"
													+ "无视了[npc.namePos]抗拒的呜咽，强迫[npc.namePos][npc.cock+]在[npc2.her][npc2.breasts+]间上下摩擦。");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.breasts+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行乳交。");
										} else {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行贫乳乳交。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行乳交，但无济于事。");
										} else {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行贫乳乳交，但无济于事。");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos][npc2.breasts+]。");
							} else {
								sb.append("[npc.NameIs][npc.sexPaceVerb]用[npc.cock]磨蹭[npc2.namePos]平坦的胸部。");
							}
						}
						break;
					case BREAST_CROTCH:
						boolean crotchPaizuri = target.isBreastCrotchFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(crotchPaizuri) {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]在[npc2.namePos][npc2.crotchBoobs+]间磨蹭，却不起任何反应。");
								} else {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]压在[npc2.namePos][npc2.crotchBoobs+]上，却不起任何反应。");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append("[npc.name]把[npc2.namePos][npc2.crotchBoobs+]推到一起，使得[npc.her][npc.cock+]能够在胯乳沟间上下磨蹭。");
										} else {
											sb.append("[npc.name]无法胯乳交[npc2.NamePos]贫瘠的[npc2.crotchBoobs]，但这不能阻止[npc.herHim]用[npc.cock+]对它们上下磨蹭。");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append("[npc2.name]将[npc2.her][npc2.crotchBoobs+]推到一起，无视[npc.namePos]抗拒的呜咽，强迫[npc.herHim]用[npc.cock+]在胯乳沟上下磨蹭。");
										} else {
											sb.append("[npc2.name]并没有因为自己平坦的[npc2.crotchBoobs]而停下，无视了[npc.namePos]抗拒的呜咽，强迫[npc.namePos][npc.cock+]上下摩擦它们。");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.crotchBoobs+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行胯乳乳交。");
										} else {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行胯乳贫乳交。");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行胯乳乳交，但无济于事。");
										} else {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行胯乳贫乳交，但无济于事。");
										}
										break;
								}
							}
							
						} else {
							if(crotchPaizuri) {
								sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos][npc2.crotchBoobs+]。");
							} else {
								sb.append("[npc.NameIs][npc.sexPaceVerb]用[npc.cock]磨蹭[npc2.namePos]平坦的[npc2.crotchBoobs]。");
							}
						}
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos]的喉咙里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.her][npc.cock+]的[npc.cockHead]压向[npc2.namePos][npc2.lips+]，[npc.sexPaceVerb]向前推进[npc2.namePos]的嘴巴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]用[npc2.lips+]含住[npc.her]的[npc.cock]并将它送入口中。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操着[npc2.her]的脸。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]吮吸并舔舐着[npc2.herHim]面前[npc.cock+]，[npc2.she]给[npc.name]进行口交，不断发出低沉的[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出低沉的呜咽与哭泣声，试图推开[npc2.herHim]面前[npc.cock+]，但最终还是被迫给[npc.name]进行口交。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos]的面部。");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos]其中一个[npc2.nipples+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.her][npc.cock+]的[npc.cockHead]压上[npc2.namePos]其中一个[npc2.nipples+]，然后向前猛推，开始操干[npc2.her][npc2.breast+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并操干[npc2.namePos][npc2.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]将[npc.cock+]深深插入[npc2.her]的[npc2.nipple(true)]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]推动胸部来迎合每次抽插，使得[npc.namePos][npc.cock+]深深陷入自己的[npc2.nipple(true)]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，同时用[npc.cock+]在[npc2.her][npc2.nipple+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos][npc2.nipple+]。");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos]其中一个[npc2.crotchNipples+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.cock][npc.cockHead+]推到其中一个[npc2.namePos][npc2.crotchNipples+]上，然后向前猛推，[npc.sexPaceVerb]操弄着[npc2.her][npc2.crotchBoob+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并操干[npc2.namePos]的[npc2.crotchNipple(true)]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]将[npc.cock+]深深插入[npc2.her]的[npc2.crotchNipple(true)]里。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]扭动腹股沟来迎合每次抽插，使得[npc.namePos][npc.cock+]深深陷入自己的[npc2.crotchNipple(true)]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，用[npc.cock+]在[npc2.her][npc2.crotchNipple(true)+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos][npc2.crotchNipple+]。");
						}
						break;
					case THIGHS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]在[npc2.namePos]股间磨蹭，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc2.namePos][npc2.legs+]并到一起，以便[npc.cock+]能在腿窝中反复抽插。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.she]还是被强行用[npc.cock+]在[npc2.namePos]的股间进进出出。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her]股间。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.sexPaceVerb]上下抖动[npc2.hips+]，强迫[npc.namePos]的[npc.cock]插入[npc2.her][npc2.legs+]之间，发出一阵[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，[npc2.her]的股间被强行操干。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos]的股间。");
						}
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos][npc2.penisUrethra+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.cock][npc.cockHead+]压向[npc2.namePos]的[npc2.cock]，然后向前猛推，[npc.sexPaceVerb]操[npc2.name][npc2.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并操干[npc2.her]阴茎[npc2.penisUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.cock+]深深插入[npc2.her]的[npc2.penisUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，同时用[npc.cock+]在[npc2.her][npc2.penisUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操[npc2.namePos][npc2.penisUrethra+]。");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos][npc2.vaginaUrethra+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.her][npc.cock]的[npc.cockHead+]压向[npc2.namePos]的[npc2.pussy]，然后向前猛推，[npc.sexPaceVerb]操[npc2.name][npc2.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并操干[npc2.her]阴部[npc2.vaginaUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.vaginaUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.cock+]深深插入[npc2.her]的[npc2.vaginaUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，同时用[npc.cock+]在[npc2.her][npc2.vaginaUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操[npc2.namePos][npc2.vaginaUrethra+]。");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos][npc2.pussy+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.her]的[npc.cock][npc.cockHead+]在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]并开始操干[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.cock][npc.cockHead+]粗暴地在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "暴力地向前猛推，将[npc.cock+]深深插入[npc2.namePos][npc2.pussy+]并开始激烈地操干[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.cock+]然后引导它进入[npc2.name][npc2.pussy+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]骑上[npc.namePos][npc.cock+]，从中获得了极大的快乐。");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.sexPaceVerb]扭动臀部，让[npc.namePos][npc.cock+]得以深深进入[npc2.her][npc2.pussy+]，发出了愉悦的[npc2.Moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]操[npc2.her][npc2.pussy+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos][npc2.pussy+]。");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.cock+]塞在[npc2.namePos][npc2.spinneret+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.her]的[npc.cock][npc.cockHead+]在[npc2.namePos]的丝囊上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.cock+]深深插入[npc2.NamePos]的丝囊穴并开始操干[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.cock][npc.cockHead+]粗暴地在[npc2.namePos]的丝囊上下磨蹭，"
												+ "暴力地向前猛推，将[npc.cock+]深深插入[npc2.namePos]的丝囊穴并开始激烈地操干[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.cock+]然后引导它进入[npc2.name]的丝囊。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.spinneret+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]用[npc.her]的丝囊骑上[npc.namePos][npc.cock+]，从中获得了极大的快乐。");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.sexPaceVerb]引导[npc.namePos][npc.cock+]深深进入[npc2.her]的丝囊，发出了愉悦的[npc2.Moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]操[npc2.her]的丝囊。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos]的丝囊。");
						}
						break;
					case TUSSY:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc.she] kept [npc.her] [npc.cock+] hilted in [npc2.namePos] [npc2.tussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.tussy],"
												+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.tussy+] before starting to fuck [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down over [npc2.namePos] [npc2.tussy],"
												+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.cock+] into [npc2.her] [npc2.tussy+] before starting to forcefully fuck [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.cock+] from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.tussy].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+" while [npc.name] fucked [npc2.her] [npc2.tussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in using [npc.her] spinneret to [npc2.sexPaceVerb] ride [npc.namePos] [npc.cock+].");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] drove [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.tussy].");
										break;
									case SUB_RESISTING:
										sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] [npc2.tussy].");
										break;
								}
							}

						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	CLIT(4, -2f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "阴蒂";
			}
			return owner.getClitorisName(false);
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getVaginaRawClitorisSizeValue();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.VAGINA;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.VAGINA;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.clit+]磨蹭自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.clit+]磨蹭自己的"+targetArea.getName(performer)+"。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用阴蒂操干着自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用阴蒂操干着自己的"+targetArea.getName(performer)+"。");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]压入[npc2.namePos][npc2.pussy+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]用[npc.pussy+]磨蹭着[npc2.nameHers]的私处，用自己[npc.clit+]碰撞着[npc2.hers]的，发出了[npc.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]将[npc2.pussy+]磨向[npc.namePos][npc.pussy+]，[npc.Name]试图拉开距离，但徒劳无功。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"，即使[npc.namePos]把[npc.her]的[npc.cock]在[npc2.her]的[npc2.pussy]上摩擦。"); 
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]摩蹭着回应[npc.namePos]的动作，很快[npc2.she]就发出[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]拼命想挣脱束缚，乞求独处，但无法阻止[npc.name]对[npc2.herHim]的磨镜动作。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]与[npc2.name]磨镜。");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]在[npc2.namePos][npc2.armpit+]间磨蹭，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]让[npc2.name]抬起[npc2.arm]，使[npc.her][npc.clit+]能够在腋窝上下磨蹭。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.she]还是被强行用[npc.clit+]在[npc2.namePos]的腋窝间上下磨蹭。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.armpit+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.sexPaceVerb]抬起[npc2.arm]，强迫[npc.namePos]的[npc.clit]插入[npc2.her][npc2.armpit+]，发出一阵[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，[npc2.her]的[npc2.armpits]被强行操干。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]将[npc.her][npc.clit+]滑入[npc2.namePos]的腋窝。");
						}
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos][npc2.asshole+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.her][npc.clit+]压向[npc2.namePos][npc2.asshole+]，然后向前猛推，[npc.sexPaceVerb]操[npc2.her][npc2.ass+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]将[npc2.her][npc2.asshole+]向后压向[npc.Name][npc.clit+]，[npc.Name]拼命地想要拉开距离，"
												+ "[npc2.she]向后猛压并强迫[npc.name]操干[npc2.her][npc2.ass+]，[npc.name]绝望地哭泣着。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]压向[npc.name]，"
												+ "把[npc.namePos][npc.clit+]深深插入[npc2.her]的[npc2.asshole]，发出一连串[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.clit+]在[npc2.her][npc2.asshole+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.asshole+]。");
						}
						break;
					case ASS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]在[npc2.namePos]肉臀间磨蹭，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]将[npc2.namePos]的屁股瓣推到一起，使[npc.her][npc.clit+]能在其间上下滑动。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.she]还是被强行用[npc.clit+]在[npc2.namePos]的臀缝上下磨蹭。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.ass+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.sexPaceVerb]上下抖动[npc2.hips+]，强迫[npc.namePos]的[npc.clit]插入[npc2.her][npc2.asshole+]，发出一阵[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，被迫使用[npc2.her]的[npc2.ass]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]阴蒂尻交[npc2.namePos]的屁股。");
						}
						break;
					case BREAST:
						boolean paizuri = target.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]在[npc2.namePos][npc2.breasts+]间磨蹭，却不起任何反应。");
								} else {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]压在[npc2.namePos][npc2.breasts+]上，却不起任何反应。");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc.name]把[npc2.namePos][npc2.breasts+]推到一起，使得[npc.her][npc.clit+]能够在乳沟间上下磨蹭。");
										} else {
											sb.append("[npc.name]无法乳交[npc2.NamePos]贫瘠的胸部，但这不能阻止[npc.herHim]用[npc.clit+]对[npc2.namePos][npc2.breasts+]上下磨蹭。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]将[npc2.her][npc2.breasts+]推到一起，无视[npc.namePos]抗拒的呜咽，强迫[npc.herHim]用[npc.cock+]在乳沟上下磨蹭。");
										} else {
											sb.append("[npc2.name]并没有因为自己的平胸而停下，"
													+ "无视了[npc.namePos]抗拒的呜咽，强迫[npc.namePos][npc.clit+]在[npc2.her][npc2.breasts+]间上下摩擦。");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.breasts+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行乳交。");
										} else {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行贫乳乳交。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行乳交，但无济于事。");
										} else {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行贫乳乳交，但无济于事。");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.breasts+]。");
							} else {
								sb.append("[npc.NameIs][npc.sexPaceVerb]用[npc.clit]在[npc2.namePos]平坦的胸部上磨蹭。");
							}
						}
						break;
					case BREAST_CROTCH:
						boolean crotchPaizuri = target.isBreastCrotchFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(crotchPaizuri) {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]在[npc2.namePos][npc2.crotchBoobs+]间磨蹭，却不起任何反应。");
								} else {
									sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]压在[npc2.namePos][npc2.crotchBoobs+]上，却不起任何反应。");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append("[npc.name]将[npc2.namePos][npc2.crotchBoobs+]推到一起，使[npc.her][npc.clit+]能够在胯乳沟间上下磨蹭。");
										} else {
											sb.append("[npc.name]无法胯乳交[npc2.NamePos]贫瘠的[npc2.crotchBoobs]，但这不能阻止[npc.herHim]用[npc.clit+]对它们上下磨蹭。");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append("[npc2.name]将[npc2.her][npc2.crotchBoobs+]推到一起，无视[npc.namePos]抗拒的呜咽，强迫[npc.herHim]用[npc.clit+]在胯乳沟上下磨蹭。");
										} else {
											sb.append("[npc2.name]并没有因为自己贫瘠的[npc2.crotchBoobs]而停下，无视了[npc.namePos]抗拒的呜咽，强迫[npc.namePos][npc.clit+]在[npc2.her]的[npc2.crotchBoobs]间上下摩擦。");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]正在给[npc2.herHim]乳交。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(crotchPaizuri) {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行胯乳乳交。");
										} else {
											sb.append("[npc2.name]发出一阵[npc2.moans]，开心地对[npc.name]进行胯乳贫乳交。");
										}
										break;
									case SUB_RESISTING:
										if(crotchPaizuri) {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行胯乳乳交，但无济于事。");
										} else {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝对[npc.name]进行胯乳贫乳交，但无济于事。");
										}
										break;
								}
							}
							
						} else {
							if(crotchPaizuri) {
								sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.crotchBoobs+]。");
							} else {
								sb.append("[npc.NameIs][npc.sexPaceVerb]用[npc.clit]在[npc2.namePos]平坦的[npc2.crotchBoobs]上磨蹭。");
							}
						}
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos]的喉咙里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.clit+]压向[npc2.namePos][npc2.lips+]，然后向前推进[npc2.her]的嘴巴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]用[npc2.lips+]含住[npc.her]的[npc.clit]并送入口中。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操着[npc2.her]的脸。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]吮吸并舔舐着[npc2.herHim]面前[npc.clit+]，[npc2.she]给[npc.name]进行口交，不断发出低沉的[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出低沉的呜咽与悲鸣，试图推开[npc2.herHim]面前[npc.clit+]，但最终还是被迫给[npc.name]口交。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos]的脸。");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos]其中一个[npc2.nipples+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.clit]压上[npc2.namePos]其中一个[npc2.nipples+]，然后向前猛推，开始操干[npc2.her][npc2.breast+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并操干[npc2.namePos][npc2.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.nipple(true)]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]推动胸部来迎合每次抽插，使得[npc.namePos][npc.clit+]深深陷入自己的[npc2.nipple(true)]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.clit+]在[npc2.her][npc2.nipple+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.nipple+]。");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos]其中一个[npc2.crotchNipples+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.clit]压上[npc2.namePos]其中一个[npc2.crotchNipples+]，然后向前猛推，开始操干[npc2.her][npc2.crotchBoob+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并操干[npc2.namePos][npc2.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her][npc2.crotchNipple(true)]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]扭动腹股沟来迎合每次抽插，使得[npc.namePos][npc.clit+]深深陷入自己的[npc2.crotchNipple(true)]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，同时用[npc.clit+]在[npc2.her][npc2.crotchNipple(true)+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.crotchNipple+]。");
						}
						break;
					case THIGHS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]在[npc2.namePos]股间磨蹭，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc2.namePos][npc2.legs+]并到一起，以便[npc.clit+]能在腿窝中反复抽插。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.she]还是被强行用[npc.clit+]在[npc2.namePos]的股间进进出出。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]使用着[npc2.her][npc2.legs+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.sexPaceVerb]上下抖动[npc2.hips+]，强迫[npc.namePos]的[npc.clit]插入[npc2.her][npc2.legs+]之间，发出一阵[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，[npc2.her]的股间被强行操干。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos]的股间。");
						}
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos][npc2.penisUrethra+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.clit]推向[npc2.namePos]的[npc2.clit]，[npc.sexPaceVerb]向前猛推并开始操[npc2.name][npc2.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并操干[npc2.her]阴蒂[npc2.penisUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]操进[npc2.her][npc2.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.clit+]深深插入[npc2.her]的[npc2.penisUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.clit+]在[npc2.her][npc2.penisUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.penisUrethra+]。");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos][npc2.vaginaUrethra+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.clit]推向[npc2.namePos]的[npc2.clit]，[npc.sexPaceVerb]向前猛推并开始操[npc2.name][npc2.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并操干[npc2.her]阴部[npc2.vaginaUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]操进[npc2.her][npc2.vaginaUrethra]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.clit+]深深插入[npc2.her]的[npc2.vaginaUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.clit+]在[npc2.her][npc2.vaginaUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.vaginaUrethra+]。");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos][npc2.pussy+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.clit]在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.clit+]深深插入[npc2.NamePos][npc2.pussy+]并开始操干[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.clit]粗暴地在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "暴力地向前猛推，将[npc.clit+]深深插入[npc2.namePos][npc2.pussy+]并开始激烈地操干[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.clit+]然后引导它进入[npc2.name][npc2.pussy+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]操进[npc2.her][npc2.pussy]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]骑上[npc.namePos][npc.clit+]，从中获得了极大的快乐。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]操[npc2.her][npc2.pussy+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos][npc2.pussy+]。");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.clit+]塞在[npc2.namePos]的丝囊里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.clit]在[npc2.namePos]的丝囊上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.clit+]深深插入[npc2.NamePos]的丝囊穴并开始操干[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.clit]粗暴地在[npc2.namePos]的丝囊上下磨蹭，"
												+ "暴力地向前猛推，将[npc.clit+]深深插入[npc2.namePos]的丝囊穴并开始激烈地操干[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.clit+]然后引导它进入[npc2.name]的丝囊。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]操进[npc2.her]的丝囊。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]用[npc.her]的丝囊骑上[npc.namePos][npc.clit+]，从中获得了极大的快乐。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]操[npc2.her]的丝囊。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用阴蒂操[npc2.namePos]的丝囊。");
						}
						break;
					case TUSSY:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc.she] kept [npc.her] [npc.clit+] hilted in [npc2.namePos] [npc2.tussy].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("Sliding [npc.her] [npc.clit] up and down over [npc2.namePos] spinneret,"
												+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking [npc.her] [npc.clit+] into [npc2.her] [npc2.tussy+] before starting to fuck [npc2.herHim].");
										break;
									case DOM_ROUGH:
										sb.append("Roughly grinding [npc.her] [npc.clit] up and down over [npc2.namePos] [npc2.tussy],"
												+ " [npc.name] violently thrust forwards, sinking [npc.her] [npc.clit+] into [npc2.her] [npc2.tussy+] before starting to forcefully fuck [npc2.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent [npc.her] [npc.clit+] from being grabbed by [npc2.name] and guided into [npc2.her] spinneret.");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+" while [npc.name] fucked [npc2.her] spinneret.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in using [npc.her] spinneret to [npc2.sexPaceVerb] ride [npc.namePos] [npc.clit+].");
										break;
									case SUB_RESISTING:
										sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] spinneret.");
										break;
								}
							}

						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] clit-fucking [npc2.namePos] spinneret.");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TONGUE(2, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "舌头";
			}
			return owner.getTongueName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getTongueLengthValue();
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, SexAreaOrifice.MOUTH) && Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.MOUTH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.MOUTH;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]吮吸着[npc.her]自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]吮吸着[npc.her]自己的"+targetArea.getName(performer)+"。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]亲吻舔舐着[npc.her]自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]亲吻舔舐着[npc.her]自己的"+targetArea.getName(performer)+"。");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tongue+]压在[npc2.namePos][npc2.armpit+]上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos][npc2.armpits+]，然后开始[npc.sexPaceVerb]亲吻并舔舐它。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc.herHim]按在[npc2.namePos][npc2.armpits+]，强迫[npc.herHim]亲吻并舔舐它。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]舔着[npc2.her][npc2.armpit+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，[npc2.sexPaceVerb]将[npc2.armpits]贴向[npc.namePos][npc.face]，鼓励[npc.herHim]继续用嘴侍奉它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图挣脱，但无法阻止[npc.name]将[npc.her]的[npc.face]按进[npc2.her]的[npc2.armpits]中。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的[npc2.armpits]。");
						}
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]压在[npc2.namePos][npc2.asshole+]上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc.lips+]压在[npc2.namePos][npc2.ass]上，在[npc2.her][npc2.asshole+]上亲吻了一下，然后开始[npc.sexPaceVerb]舔弄。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]把[npc2.ass+]压在[npc.Name][npc.face]上并强迫[npc.herHim]吻肛，[npc.Name]挣扎着哭了起来。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]正在给[npc2.herHim]吻肛。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串的[npc2.moans+]，将[npc2.her]的[npc2.hips]压向[npc.namePos]的[npc.face]，帮助[npc.tongue]深入[npc2.asshole]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图让[npc2.ass]远离[npc.namePos]那讨厌的[npc.tongue]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]为[npc2.name]提供吻肛。");
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]压在[npc2.namePos][npc2.breast+(true)]上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos][npc2.breasts+]，然后开始[npc.sexPaceVerb]亲吻并舔舐它。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc.herHim]按在[npc2.namePos][npc2.breasts+]，强迫[npc.herHim]亲吻并舔舐它。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]亲吻着[npc2.her][npc2.breasts+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，[npc2.sexPaceVerb]将[npc2.breasts]贴向[npc.namePos][npc.face]，鼓励[npc.herHim]继续用嘴侍奉它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但无法阻止[npc.name]将[npc.face]贴向[npc2.her]的[npc2.breasts]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的[npc2.breasts]。");
						}
						break;
					case BREAST_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]压在[npc2.namePos][npc2.crotchBoob+(true)]上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos][npc2.crotchBoobs+]，然后开始[npc.sexPaceVerb]亲吻并舔舐它。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc.herHim]按在[npc2.namePos][npc2.crotchBoobs+]，强迫[npc.herHim]亲吻并舔舐它。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]亲吻着[npc2.her][npc2.crotchBoobs+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，[npc2.sexPaceVerb]将[npc2.crotchBoobs]贴向[npc.namePos][npc.face]，鼓励[npc.herHim]继续用嘴侍奉它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但无法阻止[npc.name]将[npc.face]贴向[npc2.her]的[npc2.crotchBoobs]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的[npc2.crotchBoobs]。");
						}
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]压在[npc2.namePos]的嘴巴上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos]的[npc2.mouth]，给了[npc2.herHim]一个吻并[npc.sexPaceVerb]与[npc2.herHim]亲热起来。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]拉着[npc.herHim]亲吻并开始[npc2.sexPaceVerb]与[npc.herHim]亲热。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]把[npc2.tongue]伸进了[npc2.her][npc.mouth]里。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，倒向[npc.name]，[npc2.sexPaceVerb]将[npc2.tongue]伸入[npc.Name][npc.mouth]里。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]将[npc.tongue]伸入[npc2.her]的[npc2.mouth]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.name]。");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]包裹着[npc2.namePos][npc2.nipple+(true)]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos]的[npc2.breasts]，然后开始[npc.sexPaceVerb]亲吻并吮吸[npc2.her][npc2.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc.herHim]按在[npc2.namePos]的[npc2.breasts]上，[npc2.sexPaceVerb]让[npc.herHim]亲吻[npc2.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc2.name]亲吻并舔舐着[npc.her][npc2.nipples+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]将胸部压向[npc.namePos]的[npc.face]，乞求[npc.herHim]继续玩弄自己[npc2.nipples+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]将胸部压向[npc.namePos]的[npc.face]，命令[npc.herHim]继续玩弄自己[npc2.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]继续玩弄[npc2.her][npc2.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的[npc2.nipples]。");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]包裹着[npc2.namePos][npc2.crotchBoob+(true)]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos]的[npc2.crotchBoobs]，然后开始[npc.sexPaceVerb]亲吻并吮吸[npc2.her][npc2.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc.herHim]按向[npc2.crotchBoobs]，[npc2.sexPaceVerb]让[npc.herHim]亲吻[npc2.namePos][npc2.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc2.name]亲吻并舔舐着[npc.her][npc2.crotchNipples+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，"
												+ "[npc2.sexPaceVerb]将[npc2.crotchBoobs]压向[npc.namePos]的[npc.face]，乞求[npc.herHim]继续玩弄自己[npc2.crotchNipples+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，"
												+ "[npc2.sexPaceVerb]将[npc2.crotchBoobs]压向[npc.namePos]的[npc.face]，命令[npc.herHim]继续玩弄自己[npc2.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]继续玩弄[npc2.her][npc2.crotchNipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的[npc2.crotchNipples]。");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]压在[npc2.namePos][npc2.pussy+]上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos][npc2.labia+]，然后[npc.sexPaceVerb]吮吸[npc2.her][npc2.clit]并开始舔舐下面。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.labia+]压在[npc.her]的[npc.lips]上，让[npc.herHim]舔舐它。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]正在给[npc2.herHim]舔阴。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.Moaning+]，[npc2.sexPaceVerb]将[npc2.hips]贴向[npc.namePos]的[npc.face]，让[npc.herHim]把[npc.tongue]深入自己[npc2.pussy+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.Moaning+]，粗暴地将[npc2.hips]压向[npc.namePos]的[npc.face]，强迫[npc.herHim]把[npc.tongue]深入自己[npc2.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图远离[npc.Name]，但无法阻止[npc.Name]将[npc.tongue]深入[npc2.her][npc2.pussy+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的[npc2.vagina]。");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]压在[npc2.namePos]的丝囊上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.lips+]贴向[npc2.namePos]的丝囊，然后[npc.sexPaceVerb]轻舔，随即开始给[npc2.herHim]舔下体。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将丝囊黏住[npc.her]的[npc.lips]，不得不给[npc2.hreHim]舔下体。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc2.name]用嘴侍奉着[npc.her]的丝囊穴");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.Moaning+]，[npc2.sexPaceVerb]将丝囊贴向[npc.namePos]的[npc.face]，让[npc.herHim]把[npc.tongue]深入自己的丝囊穴。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.Moaning+]，粗暴地将丝囊压向[npc.namePos]的[npc.face]，强迫[npc.herHim]把[npc.tongue]深入自己的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图远离[npc.Name]，但无法阻止[npc.Name]将[npc.tongue]深入[npc2.her]的丝囊。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻[npc2.namePos]的丝囊。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	FINGER(1, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "手指";
			}
			return owner.getArmType().getFingersNamePlural(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			System.err.println("Warning: Finger length is being called!");
			return 8;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.HANDS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.FINGER;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]爱抚着自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]爱抚着自己的"+targetArea.getName(performer)+"。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]指交自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]指交自己的"+targetArea.getName(performer)+"。");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]含住[npc2.namePos][npc2.hand+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.SexPaceVerb]用[npc.fingers]抓住[npc2.namePos][npc2.hand+]，开始与[npc2.her]牵手。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但无法阻止[npc2.name]牵住[npc.her]的[npc.hand]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")+"，即便[npc.name]拉住了[npc2.her]的[npc2.hand]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]用[npc2.fingers+]抓住[npc.namePos]的[npc.hand]，[npc2.she]享受着牵手，发出[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]拼命地尝试逃脱，乞求[npc.name]放[npc2.herHim]离开，"
												+ "但完全是白费力气，[npc2.her]的[npc2.hand]继续被牵着。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]牵着[npc2.namePos]的[npc2.hand]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.fingers+]抓着[npc2.namePos][npc2.cock+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.SexPaceVerb]用[npc.fingers]包裹住[npc2.namePos][npc2.cock+]，开始为[npc2.herHim]手交。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图抵抗，但[npc.she]还是被强行给[npc2.name]提供手交。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")+"，即便[npc.name]给[npc2.herHim]手淫。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]配合[npc.namePos]的动作[npc2.SexPaceVerb]扭动[npc2.hips]，[npc2.her]的[npc2.cock]被抚摸玩弄着，发出[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]拼命地尝试逃脱，乞求[npc.name]放[npc2.herHim]离开，"
												+ "但完全是白费力气，[npc2.her]的[npc2.cock]继续被抚摸并玩弄着。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]给[npc2.name]进行手交。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.fingers+]塞在[npc2.namePos][npc2.asshole+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]抓住[npc2.namePos]的[npc2.ass]，用[npc.fingers+]抚上[npc2.her][npc2.asshole+]，然后滑入其中，开始[npc.sexPaceVerb]指交[npc2.her]的[npc2.ass]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]抓住[npc.NamePos]的[npc.hand]并强迫[npc.fingers]伸入[npc2.namePos][npc2.asshole+]，[npc.Name]哭泣着反抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]的手指挑拨着[npc2.her][npc2.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸[npc2.hips]，让[npc.her]的[npc.fingers]深入自己的[npc2.asshole]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图让[npc2.ass]远离[npc.namePos]那讨厌的[npc.fingers]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]指交[npc2.namePos]的[npc2.asshole]。");
						}
						break;
					case ASS:
						break;
					case BREAST:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，抓着[npc2.namePos][npc2.breasts+]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]用[npc.hands+]握向[npc2.namePos]的[npc2.breasts]，然后开始[npc.sexPaceVerb]揉捏并挤压它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc.her]的[npc.hands]拉向[npc2.namePos][npc2.breasts]，[npc2.sexPaceVerb]让[npc.herHim]揉捏并挤压它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]抓着[npc2.her][npc2.breasts+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸胸部，乞求[npc.herHim]继续玩弄自己[npc2.breasts+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸胸部，命令[npc.herHim]继续玩弄自己[npc2.breasts+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]继续玩弄[npc2.her][npc2.breasts+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]揉捏并挤压[npc2.namePos]的[npc2.breasts]。");
						}
						break;
					case BREAST_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，抓着[npc2.namePos][npc2.crotchBoobs+]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]用[npc.hands+]握向[npc2.namePos]的[npc2.crotchBoobs]，然后开始[npc.sexPaceVerb]揉捏并挤压它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc.her]的[npc.hands]拉向[npc2.namePos][npc2.crotchBoobs]，[npc2.sexPaceVerb]让[npc.herHim]揉捏并挤压它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]抓着[npc2.her][npc2.crotchBoobs]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸腹股沟，乞求[npc.herHim]继续玩弄自己[npc2.crotchBoobs+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸腹股沟，命令[npc.herHim]继续玩弄自己[npc2.crotchBoobs+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]继续玩弄[npc2.her][npc2.crotchBoobs+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]揉捏并挤压[npc2.namePos]的[npc2.crotchBoobs]。");
						}
						break;
					case MOUTH:
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.fingers+]捏着[npc2.namePos][npc2.nipples+]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]用[npc.hands+]握向[npc2.namePos]的[npc2.breasts]，然后移动到[npc2.her][npc2.nipples+]并开始[npc.sexPaceVerb]揉捏并挤压它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc.herHim]按向[npc2.breasts]，[npc2.sexPaceVerb]让[npc.herHim]挤压并玩弄[npc2.namePos][npc2.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]把玩着[npc2.her]的[npc2.nipples]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸胸部，乞求[npc.herHim]继续玩弄自己[npc2.nipples+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸胸部，命令[npc.herHim]继续玩弄自己[npc2.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]继续玩弄[npc2.her][npc2.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]揉捏[npc2.namePos]的[npc2.nipples]。");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.fingers+]捏着[npc2.namePos][npc2.crotchNipples+]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]用[npc.hands+]握向[npc2.namePos]的[npc2.crotchBoobs]，然后移动到[npc2.her][npc2.crotchNipples+]并开始[npc.sexPaceVerb]揉捏并挤压它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc.herHim]按向[npc2.crotchBoobs]，[npc2.sexPaceVerb]让[npc.herHim]挤压并玩弄[npc2.namePos][npc2.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]把玩着[npc2.her]的[npc2.crotchNipples]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，"
												+ "[npc2.sexPaceVerb]让[npc.name]触摸[npc2.crotchBoobs]，乞求[npc.herHim]继续玩弄自己[npc2.crotchNipples+]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一阵[npc2.moans+]，"
												+ "[npc2.sexPaceVerb]让[npc.name]触摸[npc2.crotchBoobs]，命令[npc.herHim]继续玩弄自己[npc2.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]继续玩弄[npc2.her][npc2.crotchNipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]揉捏[npc2.namePos]的[npc2.crotchNipples]。");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.fingers+]塞在[npc2.namePos][npc2.pussy+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将手放到[npc2.namePos][npc2.legs]间，用[npc.fingers+]抚上[npc2.her][npc2.labia+]，"
												+ "然后滑入其中，[npc.sexPaceVerb]指交[npc2.her][npc2.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]抓住[npc.NamePos]的[npc.hand]并强迫[npc.fingers]伸入[npc2.namePos][npc2.pussy+]，[npc.Name]哭泣着反抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]用手指挑拨着[npc2.her][npc2.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸[npc2.hips]，让[npc.her]的[npc.fingers]深入自己[npc2.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图让[npc2.hips]远离[npc.namePos]那讨厌的[npc.fingers]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]指交[npc2.namePos][npc2.pussy+]。");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.fingers+]塞在[npc2.namePos]的丝囊里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]用手摸向[npc2.namePos]的丝囊，用[npc.fingers+]抚上[npc2.her]的丝囊穴，"
												+ "然后滑入其中，[npc.sexPaceVerb]指交[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]抓住[npc.NamePos]的[npc.hand]并强迫[npc.fingers]伸入[npc2.namePos]的丝囊，[npc.Name]哭泣着反抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.name]的手指挑拨着[npc2.her]的丝囊穴。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串[npc2.moans+]，[npc2.sexPaceVerb]让[npc.name]触摸丝囊，让[npc.her]的[npc.fingers]深入自己的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图让丝囊远离[npc.namePos]那讨厌的[npc.fingers]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]指交[npc2.namePos]的丝囊。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	FOOT(1, 0, false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "足部";
			}
			return owner.getLegType().getFootNameSingular(owner);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			System.err.println("Warning: Foot length is being called!");
			return 8;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.FEET;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.FOOT;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.feet+]裹住自己的"+targetArea.getName(performer)+"上下滑动。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.feet+]裹住自己的"+targetArea.getName(performer)+"上下滑动。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.feet+]顶向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.feet+]顶向自己的"+targetArea.getName(performer)+"。");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.feet+]压上[npc2.namePos][npc2.cock+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.SexPaceVerb]用[npc.feet+]夹住[npc2.namePos][npc2.cock+]，开始为[npc2.herHim]提供[npc.a_footjob]。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图抵抗，但[npc.she]还是被强行给[npc2.name]提供[npc.a_footjob]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"而[npc.namePos]给[npc2.herHim][npc.a_footjob]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]配合[npc.namePos]的动作[npc2.SexPaceVerb]扭动[npc2.hips]，[npc2.her]操干着[npc.namePos]的[npc.feet]，发出[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]拼命地尝试远离[npc.namePos]的[npc.feet]，乞求[npc.name]放[npc2.herHim]离开，但完全是白费力气。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]给[npc2.name]进行[npc.a_footjob]。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						break;
					case ASS:
						break;
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.feet+]压在[npc2.namePos]的脸上，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.SexPaceVerb]将[npc.feet+]贴向[npc2.namePos]的面部，让[npc2.herHim]用嘴侍奉它们。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图抵抗，但[npc.she]还是被强行用嘴侍奉[npc2.name][npc.feet+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"，而[npc.namePos][npc.feet+]盖在[npc2.her]的脸上.");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]舔舐并亲吻[npc.namePos][npc.feet+]，很快就发出了[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]拼命地尝试远离[npc.name]，试图停止用嘴侍奉[npc.her]的[npc.feet]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]让[npc2.name]用嘴侍奉[npc.her][npc.feet+]。");
						}
						break;
					case NIPPLE:
						break;
					case NIPPLE_CROTCH:
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						break;
					case URETHRA_VAGINA:
						break;
					case VAGINA:
						break;
					case SPINNERET:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TAIL(2, -1f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName || owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return "尾巴";
			}
			return owner.getTailName();
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return owner.getLegTailLength(penetrationLength);
			}
			return owner.getTailLength(penetrationLength);
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			if(owner.getLegConfiguration()==LegConfiguration.TAIL_LONG) {
				return owner.getLegTailDiameter(atLength);
			}
			return owner.getTailDiameter(atLength);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TAIL;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.tail+]磨蹭自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.tail+]磨蹭自己的"+targetArea.getName(performer)+"。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]尾交自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]尾交自己的"+targetArea.getName(performer)+"。");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tail+]压入[npc2.namePos]的[npc2.labia]间。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.Name]用[npc.tail+]在[npc2.namePos]的[npc2.labia]和[npc2.clit]上[npc.sexPaceVerb]磨蹭，"
												+ "调戏[npc2.herHim]，威胁可以随时插入。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]强行把[npc.tail+]送入[npc2.namePos]的[npc2.labia]间、[npc2.clit]上，"
												+ "残忍地调戏[npc2.herHim]，威胁可以随时插入。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]被迫用[npc.her][npc.tail+]在[npc2.namePos]的[npc2.pussy]和[npc2.clit]上磨蹭，但[npc.her]尽力抵抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"，而[npc.name]准备着尾操[npc2.herHim]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("很快，[npc2.name]发现自己极度性奋并[npc2.moaning]着要[npc.name]开始尾交[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("很快，"
												+ "[npc2.name]发现自己极度性奋，开始在[npc.namePos][npc.tail+]上磨蹭[npc2.clit+]，并命令[npc.name]保持不动。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]哭着恳求放过，拼命想要挣脱但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs]用[npc.her][npc.tail+]在[npc2.namePos][npc2.labia]间和[npc2.clit]上[npc.sexPaceVerb]磨蹭。");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tail+]抓着[npc2.namePos][npc2.cock+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]用[npc.tail+]缠住[npc2.namePos]的[npc2.cock]，然后开始给[npc2.herHim]提供尾巴活。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]被迫用[npc.her][npc.tail+]缠住[npc2.namePos]的[npc2.cock]并提供尾巴活，但[npc.her]尽力抵抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"而[npc.namePos]给[npc2.herHim]尾交。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.cock+]遭到粗暴的摧残，[npc2.her]忍不住发出一长串[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]哭着恳求放过，绝望地尝试，拼命想远离[npc.Name]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs]给[npc2.name]提供尾巴活。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]塞在[npc2.namePos][npc2.asshole+]里，但却无法作出任何有效反应");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tail+]压向[npc2.namePos][npc2.asshole+]，然后向前推动，开始[npc.sexPaceVerb]尾交[npc2.her][npc2.ass+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]抓住[npc.NamePos]的[npc.tail]并强迫[npc.herHim]尾交[npc2.namePos][npc2.ass+]，[npc.Name]拼命地想要拉开距离。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操进[npc2.her][npc2.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]压向[npc.name]，"
												+ "把[npc.namePos][npc.tail+]深深插入[npc2.her]的[npc2.asshole]，发出一连串[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tail+]在[npc2.her][npc2.asshole+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos][npc2.asshole+]。");
						}
						break;
					case ASS:
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]深深地塞进[npc2.namePos]喉咙里，而[npc.Name]却无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tail+]压向[npc2.namePos][npc2.lips+]，然后[npc.sexPaceVerb]向前推动并插入[npc2.her]的嘴巴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]用[npc2.lips+]含住[npc.her]的[npc.tail]并将它送入口中。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操着[npc2.her]的脸。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]吮吸并舔舐着[npc2.herHim]面前[npc.tail+]，不断发出低沉的[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出低沉的呜咽与哭泣声，试图推开[npc2.herHim]面前[npc.tail+]，但失败了。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos]的面部。");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]深深地塞在[npc2.namePos][npc2.nipple(true)]里，但[npc.she]却无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.tail]压上[npc2.namePos]其中一个[npc2.nipples+]，然后向前猛推，开始尾交[npc2.her][npc2.breast+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并尾交[npc2.namePos][npc2.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操[npc2.her][npc2.nipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]推动胸部来迎合每次抽插，使得[npc.namePos][npc.tail+]深深陷入自己的[npc2.nipple(true)]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tail+]在[npc2.her][npc2.nipple+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos][npc2.nipple+]。");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]深深地塞在[npc2.namePos][npc2.crotchNipple(true)]里，但[npc.she]却无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.tail]压上[npc2.namePos]其中一个[npc2.crotchNipples+]，然后向前猛推，开始尾交[npc2.her][npc2.crotchBoob+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并尾交[npc2.namePos][npc2.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操进[npc2.her][npc2.crotchNipple(true)]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]扭动腹股沟来迎合每次抽插，使得[npc.namePos][npc.tail+]深深陷入自己的[npc2.crotchNipple]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.name]控制在原处，同时用[npc.her][npc.tail+]在[npc2.her][npc2.crotchNipple+(true)]里进进出出");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos][npc2.crotchNipple+]。");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]深深地进入[npc2.namePos][npc2.penisUrethra+]，但[npc.Name]却无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tail]推向[npc2.namePos][npc2.cock]的[npc2.cockHead]，[npc.sexPaceVerb]向前猛推并开始尾交[npc2.name][npc2.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并尾交[npc2.her]阴茎[npc2.penisUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操进[npc2.her][npc2.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.tail+]深深插入[npc2.her]的[npc2.penisUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tail+]在[npc2.her][npc2.penisUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos][npc2.penisUrethra+]。");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]深深地进入[npc2.namePos][npc2.vaginaUrethra+]，但[npc.Name]却无法作出任何有效反应");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tail]推向[npc2.namePos]的[npc2.pussy]，[npc.sexPaceVerb]向前猛推并开始尾交[npc2.name][npc2.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并尾交[npc2.her]阴部[npc2.vaginaUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操进[npc2.her][npc2.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.tail+]深深插入[npc2.her]的[npc2.vaginaUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tail+]在[npc2.her][npc2.vaginaUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos][npc2.vaginaUrethra+]。");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]深深地进入[npc2.namePos][npc2.pussy+]，而[npc.Name]无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.tail]在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.tail+]深深插入[npc2.NamePos][npc2.pussy+]并开始尾交[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.tail]粗暴地在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "暴力地向前猛推，将[npc.tail+]深深插入[npc2.namePos][npc2.pussy+]并开始激烈地尾交[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.tail+]然后引导它进入[npc2.name][npc2.pussy+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴进入[npc2.her][npc2.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]骑上[npc.namePos][npc.tail+]，从中获得了极大的快乐。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]尾交[npc2.her][npc2.pussy+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos][npc2.pussy+]。");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tail+]塞在[npc2.namePos]的丝囊里，但[npc.Name]却无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.tail]在[npc2.namePos]的丝囊上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.tail+]深深插入[npc2.NamePos]的丝囊穴并开始尾交[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.tail]粗暴地在[npc2.namePos]的丝囊上下磨蹭，"
												+ "暴力地向前猛推，将[npc.tail+]深深插入[npc2.namePos]的丝囊穴并开始激烈地尾交[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.tail+]然后引导它进入[npc2.name]的丝囊。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴进入[npc2.her]丝囊。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]用[npc.her]的丝囊骑上[npc.namePos][npc.tail+]，从中获得了极大的快乐。");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.sexPaceVerb]引导[npc.namePos][npc.tail+]深深进入[npc2.her]的丝囊，发出了愉悦的[npc2.Moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]尾交[npc2.her]的丝囊。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]尾交[npc2.namePos]的丝囊。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	TENTACLE(3, -1.5f, true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "触手";
			}
			return owner.getTentacleName(false);
		}
		@Override
		public int getLength(GameCharacter owner, boolean penetrationLength) {
			return owner.getTentacleLength(penetrationLength);
		}
		@Override
		public float getDiameter(GameCharacter owner, int atLength) {
			return owner.getTentacleDiameter(atLength);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isPenetrationTypeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NONE;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.tentacle+]磨蹭自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.tentacle+]磨蹭自己的"+targetArea.getName(performer)+"。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]触手交自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]触手交自己的"+targetArea.getName(performer)+"。");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，继续让[npc.tentacle+]压在[npc2.namePos][npc2.labia]间，顶着[npc2.her][npc2.clit+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.Name]用[npc.tentacle+]在[npc2.namePos]的[npc2.labia]和[npc2.clit]上[npc.sexPaceVerb]磨蹭，"
												+ "调戏[npc2.herHim]，威胁可以随时插入。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]强行把[npc.tentacle+]送入[npc2.namePos]的[npc2.labia]间、[npc2.clit]上，"
												+ "残忍地调戏[npc2.herHim]，威胁可以随时插入。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]被迫用[npc.her][npc.tentacle+]在[npc2.namePos]的[npc2.pussy]和[npc2.clit]上磨蹭，但[npc.her]尽力抵抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"[npc.name]准备好了用触手进入[npc2.herHim]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("很快，[npc2.name]发现自己极度性奋并[npc2.moaning]着要[npc.name]开始触手交[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("很快，"
												+ "[npc2.name]发现自己极度性奋，开始在[npc.namePos][npc.tentacle+]上磨蹭[npc2.clit+]，并命令[npc.name]保持不动。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]哭着恳求放过，拼命想要挣脱但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs]用[npc.her][npc.tentacle+]在[npc2.namePos][npc2.labia]间和[npc2.clit]上[npc.sexPaceVerb]磨蹭。");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]抓着[npc2.namePos][npc2.cock+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]用[npc.tentacle+]缠住[npc2.namePos]的[npc2.cock]，然后开始给[npc2.herHim]提供触手活。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]被迫用[npc.her][npc.tentacle+]缠住[npc2.namePos]的[npc2.cock]并提供触手活，但[npc.her]尽力抵抗。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"而[npc.namePos]给[npc2.herHim]触手交。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.cock+]遭到粗暴的摧残，[npc2.her]忍不住发出一长串[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]哭着恳求放过，绝望地尝试，拼命想远离[npc.Name]，但无济于事。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs]给[npc2.name]提供触手活。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
				
			} else {
				switch((SexAreaOrifice)targetArea) {
					case ARMPITS:
						break;
					case ANUS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]在[npc2.namePos][npc2.asshole+]里进进出出，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tentacle+]压向[npc2.namePos][npc2.asshole+]，然后向前推动，开始[npc.sexPaceVerb]触手交[npc2.her][npc2.ass+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]抓住[npc.NamePos]的[npc.tentacle]并强迫[npc.herHim]触手交[npc2.namePos][npc2.ass+]，[npc.Name]拼命地想要拉开距离。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用触手操进[npc2.her][npc2.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]压向[npc.name]，"
												+ "把[npc.namePos][npc.tentacle+]深深插入[npc2.her]的[npc2.asshole]，发出一连串[npc2.moans+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tentacle+]在[npc2.her][npc2.asshole+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos][npc2.asshole+]。");
						}
						break;
					case ASS:
					case BREAST:
						break;
					case BREAST_CROTCH:
						break;
					case MOUTH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]在[npc2.namePos]喉咙里进进出出，而[npc.Name]无法作出任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tentacle+]压向[npc2.namePos][npc2.lips+]，然后[npc.sexPaceVerb]向前推动并插入[npc2.her]的嘴巴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]用[npc2.lips+]含住[npc.her]的[npc.tentacle]并将它送入口中。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用触手操[npc2.her]嘴巴");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]吮吸并舔舐着[npc2.herHim]面前[npc.tentacle+]，不断发出低沉的[npc2.moans]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]发出低沉的呜咽与哭泣声，试图推开[npc2.herHim]面前[npc.tentacle+]，但失败了。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]操干[npc2.namePos]的面部。");
						}
						break;
					case NIPPLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]深深地塞在[npc2.namePos][npc2.vaginaUrethra+]，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.tentacle]压上[npc2.namePos]其中一个[npc2.nipples+]，然后向前猛推，开始触手交[npc2.her][npc2.breast+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并触手交[npc2.namePos][npc2.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用尾巴操入[npc2.her][npc2.nipple(true)]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]推动胸部来迎合每次抽插，使得[npc.namePos][npc.tentacle+]深深陷入自己的[npc2.nipple(true)]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tentacle+]在[npc2.her][npc2.nipple+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos][npc2.nipple+]。");
						}
						break;
					case NIPPLE_CROTCH:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.Name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.her][npc.tentacle+]深深地塞在[npc2.namePos][npc2.crotchNipple(true)]里，但[npc.she]却无法作出任何有效反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.tentacle]压上[npc2.namePos]其中一个[npc2.crotchNipples+]，然后向前猛推，开始触手交[npc2.her][npc2.crotchBoob+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]命令[npc.herHim]插入并触手交[npc2.namePos][npc2.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用触手操入[npc2.her][npc2.crotchNipple(true)].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]扭动腹股沟来迎合每次抽插，使得[npc.namePos][npc.tentacle+]深深陷入自己的[npc2.crotchNipple]，发出了一阵[npc2.moaning+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tentacle+]在[npc2.her][npc2.crotchNipples+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos][npc2.crotchNipple+]。");
						}
						break;
					case THIGHS:
						break;
					case URETHRA_PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]塞在[npc2.namePos][npc2.vaginaUrethra+]里进进出出，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tentacle]推向[npc2.namePos][npc2.cock]的[npc2.cockHead]，[npc.sexPaceVerb]向前猛推并开始触手交[npc2.name][npc2.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并触手交[npc2.her]阴茎[npc2.penisUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用触手操进[npc2.her][npc2.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.tentacle+]深深插入[npc2.her]的[npc2.penisUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tentacle+]在[npc2.her][npc2.penisUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos][npc2.penisUrethra+]。");
						}
						break;
					case URETHRA_VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]塞在[npc2.namePos][npc2.vaginaUrethra+]里，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]将[npc.tentacle]推向[npc2.namePos]的[npc2.pussy]，[npc.sexPaceVerb]向前猛推并开始触手交[npc2.name][npc2.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]让[npc.Name]插入并触手交[npc2.her]阴部[npc2.vaginaUrethra+]，[npc.name]挣扎并哭泣着，试图逃开。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着，没有任何醒来的迹象":"继续表现的像个任人摆布的性玩具，一动不动")
										+"而[npc.name]用触手操进[npc2.her][npc2.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexPaceVerb]将[npc2.hips]推向[npc.name]，使得[npc.NamePos][npc.tentacle+]深深插入[npc2.her]的[npc2.vaginaUrethra]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图逃脱，但被[npc.Name]控制在原处，同时用[npc.tentacle+]在[npc2.her][npc2.vaginaUrethra+]进进出出。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos][npc2.vaginaUrethra+]。");
						}
						break;
					case VAGINA:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]在[npc2.namePos][npc2.pussy+]里进进出出，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.tentacle]在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.tentacle+]深深插入[npc2.NamePos][npc2.pussy+]并开始触手交[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.tentacle]粗暴地在[npc2.namePos][npc2.labia+]上下磨蹭，"
												+ "暴力地向前猛推，将[npc.tentacle+]深深插入[npc2.namePos][npc2.pussy+]并开始激烈地触手交[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.tentacle+]然后引导它进入[npc2.name][npc2.pussy+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep, and showed no sign of being close to waking up":"continued to act like an inanimate sex toy, and remained totally motionless")
										+"而[npc.name]用触手操进[npc2.her][npc2.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]骑上[npc.namePos][npc.tentacle+]，从中获得了极大的快乐。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]触手交[npc2.her][npc2.pussy+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos][npc2.pussy+]。");
						}
						break;
					case SPINNERET:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.tentacle+]在[npc2.namePos]丝囊里进进出出，却不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc.name]用[npc.tentacle]在[npc2.namePos]的丝囊上下磨蹭，"
												+ "[npc.sexPaceVerb]向前猛推，将[npc.tentacle+]深深插入[npc2.NamePos]的丝囊穴并开始触手交[npc2.herHim]。");
										break;
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.tentacle]粗暴地在[npc2.namePos]的丝囊上下磨蹭，"
												+ "暴力地向前猛推，将[npc.tentacle+]深深插入[npc2.namePos]的丝囊穴并开始激烈地触手交[npc2.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]挣扎并大喊着，要求[npc2.name]远离，但根本无法阻止[npc2.name]抓住[npc.her][npc.tentacle+]然后引导它进入[npc2.name]的丝囊。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep, and showed no sign of being close to waking up":"continued to act like an inanimate sex toy, and remained totally motionless")
										+"而[npc.name]用触手操进[npc2.her]丝囊。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]愉悦又[npc2.Moaning+]，[npc2.sexPaceVerb]用[npc.her]的丝囊骑上[npc.namePos][npc.tentacle+]，从中获得了极大的快乐。");
										break;
									case SUB_EAGER:
									case SUB_NORMAL:
										sb.append("[npc2.name][npc2.sexPaceVerb]引导[npc.namePos][npc.tentacle+]深深进入[npc2.her]的丝囊，发出了愉悦的[npc2.Moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc2.name]哭着恳求[npc.name]放过自己，但无法阻止[npc.name]触手交[npc2.her]的丝囊。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]用触手操[npc2.namePos]的丝囊。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	};
	
//	TOY(4, -2f, false) {
//		@Override
//		public String getName(GameCharacter owner, boolean standardName) {
//			// Cannot know which orifice is being penetrated, so always return "toy":
//			return "toy";
//		}
//		@Override
//		public int getLength(GameCharacter owner, boolean penetrationLength) {
//			System.err.println("Warning: Toy length is being called!");
//			return 8;
//		}
//		@Override
//		public boolean isFree(GameCharacter owner) {
//			return Main.sex.isPenetrationTypeFree(owner, this);
//		}
//		@Override
//		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
//			return CoverableArea.NONE;
//		}
//		@Override
//		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
//			StringBuilder sb = new StringBuilder();
//			if(performer==target) {
//				System.err.println("SexAreaPenetration.TOY getSexDescription() error: Does not support self actions!");
//				return "";
//			}
//			
//			if(targetArea.isPenetration()) {
//				switch((SexAreaPenetration)targetArea) {
//					case CLIT:
//						break;
//					case FINGER:
//						break;
//					case FOOT:
//						break;
//					case PENIS:
//						break;
//					case TAIL:
//						break;
//					case TENTACLE:
//						break;
//					case TONGUE:
//						break;
//					case TOY:
//						break;
//				}
//				
//			} else {
//				switch((SexAreaOrifice)targetArea) {
//					case ASS:
//						break;
//					case BREAST:
//						break;
//					case BREAST_CROTCH:
//						break;
//					case THIGHS:
//						break;
//					case ANUS:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] pressed the tip of a toy against [npc2.namePos] [npc2.asshole+], before thrusting forwards and starting to [npc.sexPaceVerb] fuck [npc2.her] [npc2.ass+] with it.");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.ass+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.SexPaceVerb] bucking [npc2.her] [npc2.hips] back against [npc.herHim],"
//											+ " [npc2.name] let out a series of [npc2.moans+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.asshole].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place while [npc.she] thrust the toy in and out of [npc2.her] [npc2.asshole+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.asshole+].");
//						}
//						break;
//					case MOUTH:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] pressed the tip of a toy against [npc2.namePos] [npc2.lips+], before [npc.sexPaceVerb] pushing it forwards into [npc2.her] mouth.");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] throat using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] kept on letting out muffled [npc2.moans] as [npc2.she] sucked and licked the toy.");
//									break;
//								case SUB_RESISTING:
//									sb.append(" Letting out muffled sobs and cries, [npc2.name] tried to pull away from the toy, but ended up being forced to take it deep down [npc2.her] throat.");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] face.");
//						}
//						break;
//					case NIPPLE:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the tip of a toy against one of [npc2.namePos] [npc2.nipples+], before thrusting forwards and using it to fuck [npc2.her] [npc2.breast+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.nipples+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] chest on every thrust, [npc2.moaning+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.nipple(true)].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while the toy thrust in and out of [npc2.her] [npc2.nipple+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.nipple+].");
//						}
//						break;
//					case NIPPLE_CROTCH:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("[npc.Name] [npc.sexPaceVerb] pushed the tip of a toy against one of [npc2.namePos] [npc2.crotchNipples+], before thrusting forwards and using it to fuck [npc2.her] [npc2.crotchBoob+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] desperately tried to pull away as [npc2.name] forced [npc.herHim] to start fucking [npc2.her] [npc2.crotchNipples+] using a toy.");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] pushed out [npc2.her] groin on every thrust, [npc2.moaning+] as [npc2.she] helped to sink the toy deep into [npc2.her] [npc2.crotchNipple].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while the toy thrust in and out of [npc2.her] [npc2.crotchNipple+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] toy-fucking [npc2.namePos] [npc2.crotchNipple+].");
//						}
//						break;
//					case URETHRA_PENIS:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] own [npc2.cock], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.penisUrethra+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] cock's [npc2.penisUrethra+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink a toy deep into [npc2.her] [npc2.penisUrethra].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while a toy thrust in and out of [npc2.her] [npc2.penisUrethra+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.penisUrethra+].");
//						}
//						break;
//					case URETHRA_VAGINA:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append("Pressing the [npc.cockHead+] of [npc.her] [npc.cock] against [npc2.namePos] [npc2.pussy], [npc.name] [npc.sexPaceVerb] thrust forwards and started fucking [npc2.her] [npc2.vaginaUrethra+].");
//									break;
//								case SUB_RESISTING:
//									sb.append("Struggling and crying, [npc.name] tried to pull away as [npc2.name] made [npc.herHim] penetrate and start fucking [npc2.her] pussy's [npc2.vaginaUrethra+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Name] [npc2.sexPaceVerb] thrust [npc2.her] [npc2.hips] out against [npc.name], helping [npc.herHim] to sink a toy deep into [npc2.her] [npc2.vaginaUrethra].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" [npc2.Name] tried to pull away, but [npc.name] [npc.was] able to keep [npc2.herHim] in place even while a toy thrust in and out of [npc2.her] [npc2.vaginaUrethra+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.vaginaUrethra+].");
//						}
//						break;
//					case VAGINA:
//						if(pastTense) {
//							switch(performerPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case SUB_EAGER:
//								case SUB_NORMAL:
//									sb.append("Sliding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
//											+ " [npc.name] [npc.sexPaceVerb] thrust forwards, sinking a toy into [npc2.namePos] [npc2.pussy+] before starting to fuck [npc2.herHim].");
//									break;
//								case DOM_ROUGH:
//									sb.append("Roughly grinding the [npc.cockHead+] of [npc.her] [npc.cock] up and down between [npc2.namePos] [npc2.labia+],"
//											+ " [npc.name] violently thrust forwards, sinking a toy into [npc2.namePos] [npc2.pussy+] before starting to forcefully fuck [npc2.herHim].");
//									break;
//								case SUB_RESISTING:
//									sb.append("[npc.Name] struggled and cried out to be left alone, but [npc.was] ultimately unable to prevent a toy from being grabbed by [npc2.name] and guided into [npc2.her] [npc2.pussy+].");
//									break;
//							}
//							switch(targetPace) {
//								case DOM_GENTLE:
//								case DOM_NORMAL:
//								case DOM_ROUGH:
//									sb.append(" [npc2.Moaning+] in delight, [npc2.name] took great pleasure in [npc2.sexPaceVerb] riding [npc.namePos] [npc.cock+].");
//									break;
//								case SUB_EAGER:
//								case SUB_NORMAL:
//									sb.append(" [npc2.Moaning] in delight, [npc2.name] [npc2.sexPaceVerb] bucked [npc2.her] hips to help drive [npc.namePos] [npc.cock+] deep into [npc2.her] [npc2.pussy+].");
//									break;
//								case SUB_RESISTING:
//									sb.append(" Although [npc2.she] cried and pleaded to be left alone, [npc2.name] [npc2.was] unable to stop [npc.name] from fucking [npc2.her] [npc2.pussy+].");
//									break;
//							}
//							
//						} else {
//							sb.append("[npc.NameIs] [npc.sexPaceVerb] fucking [npc2.namePos] [npc2.pussy+].");
//						}
//						break;
//				}
//			}
//			return UtilText.parse(performer, target, sb.toString());
//		}
//	};

	
	private float baseArousalWhenPenetrating;
	private float arousalChangePenetratingDry;
	private boolean takesVirginity;

	private SexAreaPenetration(float baseArousalWhenPenetrating, float arousalChangePenetratingDry, boolean takesVirginity) {
		this.baseArousalWhenPenetrating = baseArousalWhenPenetrating;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.takesVirginity = takesVirginity;
	}

	@Override
	public boolean isOrifice() {
		return false;
	}
	
	public boolean appliesStretchEffects(GameCharacter owner) {
		return getDiameter(owner, 0)!=-1;
	}

	public abstract int getLength(GameCharacter owner, boolean penetrationLength);

	/** The diameter of the owner's SexAreaPenetration at the length specified, measured from the base. Diameter is the unit of measurement for all Capacity values. */
	public float getDiameter(GameCharacter owner, int atLength) {
		return -1;
	};
	
	public float getBaseArousalWhenPenetrating() {
		return baseArousalWhenPenetrating;
	}
	
	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}
	
	public boolean isTakesVirginity() {
		return takesVirginity;
	}
	
}
