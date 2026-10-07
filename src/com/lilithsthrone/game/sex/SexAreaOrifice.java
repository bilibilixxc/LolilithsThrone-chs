package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.main.Main;

/**
 * Natural ordering is roughly in assumed order of severity of penetration, with associated areas next to one another (but all urethras and the spinneret are at the end as they're exceptional orifices).
 * 
 * 
 * @since 0.1.78
 * @version 0.4.10.12
 * @author Innoxia
 */
public enum SexAreaOrifice implements SexAreaInterface {
	TUSSY(4,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			return " tussy";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.TUSSY;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TAIL;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getTussyStretchedCapacity();
			}
			return owner.getTussyRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getTussyMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getTussyMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] pushed [npc2.her] [npc2.fingers+] deep into [npc.her] [npc2.tussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]握住[npc2.namePos][npc2.hand+]，"
												+ " [npc.name] rubbed [npc2.her] [npc2.fingers+] up and down over [npc.her] [npc2.tussy] before making [npc2.herHim] slip them inside and start fingering [npc.her] [npc2.tussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.fingers+] into [npc.her] [npc2.tussy] and start fingering [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+", and remained totally motionless while keeping [npc2.her] [npc2.fingers] shoved deep in [npc.namePos] [npc.tussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] curling [npc2.her] [npc2.fingers] up inside [npc.namePos] [npc2.tussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fingered [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.fingers] out of [npc.namePos] [npc2.tussy],"
												+ "只能一边哭泣一边被迫指交[npc.name]。");
										break;
								}
							}

						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] fingering [npc.namePos] [npc2.tussy+].");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.cock+] thrust deep into [npc.her] [npc.tussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Rubbing [npc.her] [npc2.tussy] over the [npc2.cockHead+] of [npc2.namePos] [npc2.cock],"
												+ " [npc.name] [npc.sexPaceVerb] bucked [npc.her] [npc.hips] and forced [npc2.herHim] to penetrate [npc.her] [npc2.tussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.cock] into [npc.her] [npc2.tussy] and start fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+", and remained totally motionless while keeping [npc2.her] [npc2.cock] hilted in [npc.namePos] [npc.tussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.cock+] into [npc.namePos] [npc2.tussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] fucked [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.cock] out of [npc.namePos] [npc2.tussy], and could do nothing but cry as [npc2.she] had [npc2.her] [npc2.cock] used against [npc2.her] will.");
										break;
								}
							}

						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]操。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tail+] thrust deep into [npc.her] [npc.tussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tail+], [npc.name] rubbed it up and down over [npc.her] [npc2.tussy] before making [npc2.herHim] penetrate and start tail-fucking [npc.her] [npc2.tussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tail+] into [npc.her] [npc2.tussy] and start tail-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tail] hilted in [npc.namePos] [npc.tussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tail] into [npc.namePos] [npc2.tussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tail-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tail] out of [npc.namePos] [npc2.tussy], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}

						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] having [npc.her] [npc2.tussy+] tail-fucked by [npc2.name].");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("Remaining "+(performer.isAsleep()?"asleep":"motionless")+", [npc.name] didn't react at all as [npc2.namePos] [npc2.tentacle+] thrust deep into [npc.her] [npc.tussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.tentacle+], [npc.name] rubbed it up and down over [npc.her] [npc2.tussy] before making [npc2.herHim] penetrate and start tentacle-fucking [npc.her] [npc2.tussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to struggle free, but was unable to stop [npc2.name] from pushing [npc2.her] [npc2.tentacle+] into [npc.her] [npc2.tussy] and start tentacle-fucking [npc.herHim].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+", and remained totally motionless while keeping [npc2.her] [npc2.tentacle] hilted in [npc.namePos] [npc.tussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.SexPaceVerb] thrusting [npc2.her] [npc2.tentacle] into [npc.namePos] [npc2.tussy], [npc2.name] [npc2.was] soon [npc2.moaning] in delight as [npc2.she] tentacle-fucked [npc.name].");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried, and failed, to pull [npc2.her] [npc2.tentacle] out of [npc.namePos] [npc2.tussy], and could do nothing but cry as [npc2.she] had it used against [npc2.her] will.");
										break;
								}
							}

						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] having [npc.her] [npc2.tussy] tentacle-fucked by [npc2.name].");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
										+" while [npc2.name] performed oral on [npc.her] [npc2.tussy+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name] pushed [npc.her] [npc2.tussy] against [npc2.namePos] [npc2.face], before [npc.sexPaceVerb] making [npc2.herHim] start performing oral on [npc.her] [npc2.tussy+].");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ " but [npc.was] unable to stop [npc2.name] from pressing [npc2.her] [npc2.face] against [npc.her] [npc2.tussy] and then start performing oral on [npc.her] [npc2.tussy+].");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"remained totally motionless")+" while keeping [npc2.her] [npc2.tongue] thrust into [npc.namePos] [npc.tussy+].");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" Letting out a series of [npc2.moans+], [npc2.name] [npc2.sexPaceVerb] pressed [npc2.her] [npc2.lips+] against [npc.namePos] [npc2.tussy] and continued performing oral on [npc.herHim].");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图反抗，"
												+ " but [npc2.was] unable to stop [npc.name] from planting [npc.her] [npc2.tussy] over [npc2.her] [npc2.face] and [npc.sexPaceVerb] forcing [npc2.herHim] to perform oral on [npc.herHim].");
										break;
								}
							}

						} else {
							sb.append("[npc2.NameIs] [npc2.sexPaceVerb] performing oral on [npc.namePos] [npc2.tussy].");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},

	VAGINA(4,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "小穴";
			}
			return owner.getVaginaName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getVaginaStretchedCapacity();
			}
			return owner.getVaginaRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getVaginaMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getVaginaMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.pussy+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.pussy+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.pussy+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.pussy+]压向自己的"+targetArea.getName(performer)+"。");
					}
				}
			}
			
			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.cock+]填入[npc.her][npc.pussy+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.labia+]在[npc2.namePos]的[npc2.clit][npc2.clitTip+]上摩擦，"
												+ "[npc.sexPaceVerb]摇晃着[npc.hips]，强迫[npc2.name]插入[npc.her][npc.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.clit+]推入[npc.her][npc.pussy+]并开始操[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her][npc2.lips+]继续插入[npc.namePos][npc.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.clit+]插入[npc.namePos][npc.pussy+]，[npc2.she]开始操[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.clit]从[npc.namePos]的[npc.pussy]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.clit]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]阴蒂交。");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.fingers+]深入[npc.her][npc.pussy+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]握住[npc2.namePos][npc2.hand+]，"
												+ "用[npc2.fingers+]在[npc.her][npc.labia+]上下摩擦，然后让它们滑入并开始指交[npc.her][npc.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.fingers+]插入[npc.her][npc.pussy+]并开始指交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时将[npc2.her]那[npc2.fingers]继续深入[npc.namePos][npc.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.fingers]插入[npc.namePos]的[npc.pussy]，[npc2.she]指交[npc.name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.fingers]从[npc.namePos]的[npc.pussy]撤出来，但失败了，"
												+ "只能一边哭泣一边被迫指交[npc.name]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]指交[npc.Name]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.cock+]深入[npc.her][npc.pussy+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.labia+]在[npc2.namePos]的[npc2.cock][npc2.cockHead+]上摩擦，"
												+ "[npc.sexPaceVerb]摇晃着[npc.hips]，强迫[npc2.name]插入[npc.her][npc.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]推入[npc.her][npc.pussy+]并开始操[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her][npc2.cock]继续插入[npc.namePos][npc.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.cock+]插入[npc.namePos][npc.pussy+]，[npc2.she]开始操[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.cock]从[npc.namePos]的[npc.pussy]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.cock]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]操。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tail+]送入[npc.her][npc.pussy+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tail+]，在[npc.her][npc.labia+]上下摩擦，让[npc2.Name]插入并开始尾交[npc.her][npc.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail+]插入[npc.her][npc.pussy+]并开始尾交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tail]继续插入[npc.namePos][npc.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tail]插入[npc.namePos]的[npc.pussy]，[npc2.she]开始尾交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tail]从[npc.namePos]的[npc.pussy]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tail]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]尾交。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tentacle+]深入[npc.her][npc.pussy+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tentacle+]，在[npc.her][npc.labia+]上下摩擦，让[npc2.Name]插入并开始触手交[npc.her][npc.pussy+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle+]插入[npc.her][npc.pussy+]并开始触手交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tentacle]继续插入[npc.namePos][npc.pussy+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.tentacle]插入[npc.namePos]的[npc.pussy]，开始触手交[npc.Name]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tentacle]从[npc.namePos]的[npc.pussy]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tentacle]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]触手交。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]在给[npc.herHim]舔阴.");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]将[npc.labia+]贴向[npc2.namePos]的[npc2.face]，让[npc2.name]开始舔[npc.herHim]下面。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc2.face]贴向[npc.her]的[npc.pussy]并开始舔[npc.herHim]下面。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，将[npc2.her]的[npc2.tongue]伸入[npc.namePos][npc.pussy+]里。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串[npc2.moans+]，[npc2.sexPaceVerb]将[npc2.lips+]贴向[npc.namePos][npc.pussy+]并开始对[npc.herHim]舔阴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图反抗，"
												+ "但无法阻止[npc.Name]将[npc.pussy+]压向[npc2.her]的[npc2.face]并开始[npc.sexPaceVerb]强迫[npc2.herHim]舔阴。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]对[npc.name]舔阴。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},

	ANUS(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "肛门";
			}
			return owner.getAnusName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ANUS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.ANUS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getAssStretchedCapacity();
			}
			return owner.getAssRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getAssMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getAssMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.asshole+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.asshole+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.asshole+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.asshole+]压向自己的"+targetArea.getName(performer)+"。");
					}
				}
			}

			if(targetArea.isPenetration()) {
				switch((SexAreaPenetration)targetArea) {
					case CLIT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]挤着[npc.her][npc.asshole+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc.ass]对准[npc2.namePos]的腹股沟，[npc.sexPaceVerb]向后推动到[npc2.clit+]上，让[npc2.Name]插入[npc.her][npc.asshole+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.clit+]推入[npc.her][npc.asshole+]并开始肛交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her][npc2.lips+]继续插入[npc.namePos][npc.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]压向[npc.namePos]的[npc.ass]，[npc2.she]开始阴蒂肛交[npc.namePos][npc.asshole+]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.clit]从[npc.namePos]的[npc.asshole]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.clit]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]阴蒂交屁股。");
						}
						break;
					case FINGER:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.fingers+]深入[npc.her][npc.asshole+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]抓住[npc2.namePos]的[npc2.hand]，[npc.sexPaceVerb]将[npc2.fingers]插入自己的[npc.asshole]，让[npc2.Name]肛门指交[npc.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.fingers]插入[npc.her][npc.asshole+]并开始肛门指交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时将[npc2.her]那[npc2.fingers]继续深入[npc.namePos][npc.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.fingers]插入[npc.namePos]的[npc.asshole]，[npc2.she]指交[npc.namePos][npc.asshole+]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.fingers]从[npc.namePos]的[npc.asshole]撤出来，但失败了，只能一边哭泣一边被迫指交[npc.namePos][npc.asshole+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]肛门指交。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.cock+]深入[npc.her][npc.asshole+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc.ass]对准[npc2.namePos]的腹股沟，[npc.sexPaceVerb]向后推动到[npc2.cock]上，让[npc2.Name]插入[npc.her][npc.asshole+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]插入[npc.her][npc.asshole+]并开始肛交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.cock]继续填满[npc.namePos][npc.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]压向[npc.namePos]的[npc.ass]，[npc2.she]开始肛交[npc.namePos][npc.asshole+]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.cock]从[npc.namePos]的[npc.asshole]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.cock]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]肛交。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tail+]送入[npc.her][npc.asshole+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tail+]，拉向[npc.her][npc.ass+]，让[npc2.Name]插入[npc.her][npc.asshole+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail+]插入[npc.her][npc.asshole+]并开始尾交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tail]继续填满[npc.namePos][npc.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tail]插入[npc.namePos]的[npc.asshole]，[npc2.she]开始肛门尾交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tail]从[npc.namePos]的[npc.asshole]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tail]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]肛门尾交。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc.asshole+]深入[npc.her][npc.asshole+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tentacle+]，拉向[npc.her][npc.ass+]，让[npc2.Name]插入[npc.her][npc.asshole+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle+]插入[npc.her][npc.asshole+]并开始触手肛交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tentacle]继续填满[npc.namePos][npc.asshole+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.tentacle]插入[npc.namePos]的[npc.asshole]，开始触手肛交[npc.Name]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tentacle]从[npc.namePos]的[npc.asshole]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tentacle]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]触手肛交。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]舔舐着[npc.her][npc.asshole+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]将[npc.ass+]贴向[npc2.namePos]的[npc2.face]，让[npc2.name]开始舔舐[npc.her][npc.asshole+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，"
												+ "但无法阻止[npc2.name]将[npc2.face]贴向[npc.her]的[npc.ass]并开始舔舐[npc.her][npc.asshole+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，将[npc2.her]的[npc2.tongue]伸入[npc.namePos][npc.asshole+]里。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串[npc2.moans+]，[npc2.sexPaceVerb]将[npc2.lips+]贴向[npc.namePos][npc.asshole+]并开始对[npc.herHim]舔肛。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图反抗，"
												+ "但无法阻止[npc.Name]将[npc.ass]压向[npc2.her]的[npc2.face]并开始[npc.sexPaceVerb]强迫[npc2.herHim]吻肛。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]对[npc.name]吻肛。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},

	ASS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			// Changed from "ass cheeks" to "ass" in v0.4.10.8
			if(standardName) {
				return "后穴";
			}
			return "后穴";
//			return owner.getAssName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ASS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"磨蹭自己[npc.ass+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"磨蹭自己[npc.ass+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.ass+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.ass+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]挤着[npc.her][npc.ass+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.ass+]，[npc.sexPaceVerb]让[npc2.name]揉捏并挤压它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱, 但无法阻止[npc2.name]揉捏并挤压[npc.her][npc.ass+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")+"，[npc2.her]的[npc2.hands]持续抓着[npc.namePos][npc.ass+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，把[npc2.hands]抚上[npc.namePos]的[npc.ass]，[npc2.sexPaceVerb]玩弄它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫玩弄[npc.Name][npc.ass+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]揉捏并挤压[npc.namePos][npc.ass+]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时[npc2.namePos][npc2.cock+]在[npc.her]的肉臀间来回磨蹭");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把屁股的臀肉推到一起，以便[npc2.name]用[npc2.her][npc2.cock+]在形成的臀缝中上下抽送。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图抵抗，但[npc.her]的臀肉还是被推到一起，被[npc2.namePos][npc2.cock+]股交。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]插入[npc.namePos]的肉臀");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，[npc2.sexPaceVerb]将[npc2.her][npc2.cock+]压上了[npc.namePos][npc.asshole+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，被迫使用[npc2.her]的[npc2.cock]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]股交[npc.namePos]的屁股。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]亲吻并舔舐着[npc.her][npc.ass+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.ass+]贴向[npc2.namePos][npc2.face]，让[npc2.herHim]亲吻并舔舐它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.lips]贴向[npc.her][npc.ass+]并开始亲吻舔舐它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.her][npc2.lips+]继续压入[npc.namePos]的[npc.ass]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，将[npc2.her][npc2.lips+]贴向[npc.namePos][npc.ass+]，[npc2.sexPaceVerb]亲吻它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]将[npc.ass+]贴上[npc2.her][npc2.lips+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]亲吻[npc.namePos][npc.ass+]。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	MOUTH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			2/60f, 15/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "嘴巴";
			}
			return owner.getMouthName(false);
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this) && Main.sex.isPenetrationTypeFree(owner, SexAreaPenetration.TONGUE);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getFaceStretchedCapacity();
			}
			return owner.getFaceRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getFaceMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getFaceMaximumPenetrationDepthUncomfortable();
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
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]"+(performer.isAsleep()?"沉睡着":"一动不动")+"，[npc.lips+]含住[npc2.namePos][npc2.clit+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]的[npc.face]靠向[npc2.namePos]的腹股沟，[npc.sexPaceVerb]用[npc.her][npc.lips+]含住[npc2.her][npc2.clit+]并开始进行口交。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.clit+]推入[npc.her]嘴里并强迫[npc.herHim]进行口交。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")+"，不对自己被舔弄的[npc2.clit]做出任何反应。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]挤到[npc.namePos][npc.face]上，[npc2.she]感受到[npc2.clit]被吮吸着，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.clit]从[npc.namePos]嘴里撤出来，但失败了，[npc2.she]只能一边哭泣一边被迫看着自己的[npc2.clit]被舔弄。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]吮吸[npc2.namePos][npc2.clit+]。");
						}
						break;
					case FINGER:
						break;
					case FOOT:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"保持沉睡着":"一动不动")+"，[npc.name]用[npc.lips+]含住[npc2.namePos]的[npc2.feet]，用嘴侍奉它们。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]的[npc.face]沿着[npc2.namePos]的[npc2.legs]逐渐下移，最后[npc.sexPaceVerb]将[npc.lips+]贴在[npc2.her]的[npc2.feet]上，开始用嘴侍奉起来。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.feet]推入[npc.her]嘴里并强迫[npc.herHim]用嘴侍奉。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"没有醒来的迹象":"保持完全不动")+"，将[npc2.her]的[npc2.feet]压到[npc.namePos][npc.face]上。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.feet]压到[npc.namePos][npc.face]上，[npc2.she]感受到[npc2.feet]被用嘴侍奉着，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.feet]从[npc.namePos]嘴边挪开，但失败了，[npc2.she]只能一边哭泣一边被迫看着自己的[npc2.feet]被用嘴侍奉。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]亲吻并舔舐[npc2.namePos]的[npc2.feet]。");
						}
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append(""+(performer.isAsleep()?"保持沉睡着":"如雕塑一般")+"，[npc.her][npc.lips+]含住[npc2.namePos]的[npc2.cock].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]的[npc.face]靠向[npc2.namePos]的腹股沟，[npc.sexPaceVerb]用[npc.her][npc.lips+]含住[npc2.her]的[npc2.cock]并开始进行口交。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]推入[npc.her]嘴里并强迫[npc.herHim]进行口交。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"继续睡觉":"不作反应")+"，[npc2.her][npc2.cock+]填满[npc.namePos]的喉咙。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.hips]挤到[npc.namePos][npc.face]上，[npc2.she]感受到[npc2.cock]被吮吸着，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.cock]从[npc.namePos]嘴里撤出来，但失败了，[npc2.she]只能一边哭泣一边被迫看着自己的[npc2.cock]被舔弄。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]给[npc2.name]进行口交。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("保持"+(performer.isAsleep()?"深度沉睡着":"像雕塑一般")+"，[npc.name][npc.lips+]含住[npc2.namePos]的[npc2.tail]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]用[npc.her][npc.lips+]含住[npc2.namePos]的[npc2.tail]并开始吮吸它。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail]推入[npc.her]嘴里并强迫[npc.herHim]进行吮吸。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"继续睡觉":"不作反应")+"，[npc2.her][npc2.tail+]填满[npc.namePos]的喉咙。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tail]插入[npc.namePos]的喉咙，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tail]从[npc.namePos]嘴里拉出来，但失败了，[npc2.she]只能一边哭泣一边被迫看着自己的[npc2.tail]被吮吸。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]吮吸[npc2.namePos]的[npc2.tail]。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("保持"+(performer.isAsleep()?"深度沉睡着":"像雕塑一般")+"，[npc.name][npc.lips+]含住[npc2.namePos]的[npc2.tentacle]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]用[npc.her][npc.lips+]含住[npc2.namePos]的[npc2.tentacle]并开始吮吸它。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle]推入[npc.her]嘴里并强迫[npc.herHim]进行吮吸。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"继续睡觉":"不作反应")+"，[npc2.her][npc2.tentacle+]填满[npc.namePos]的喉咙。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tentacle]插入[npc.namePos]的喉咙，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tentacle]从[npc.namePos]嘴里拉出来，但失败了，[npc2.she]只能一边哭泣一边被迫看着自己的[npc2.tentacle]被吮吸。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]吮吸[npc2.namePos]的[npc2.tentacle]。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时将[npc.her][npc.lips+]按在[npc2.namePos]的嘴上。");
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
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，将[npc2.her]的[npc2.tongue]伸入[npc.namePos]的[npc.mouth]里。");
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
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	NIPPLE(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "乳头";
			}
			return owner.getNippleName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NIPPLES;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.NIPPLE;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getNippleStretchedCapacity();
			}
			return owner.getNippleRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getNippleMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getNippleMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.nipples+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.nipples+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.nipples+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.nipples+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]捏着[npc.her][npc.nipples+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.breasts+]，[npc.sexPaceVerb]让[npc2.name]揉捏并挤压[npc.her][npc.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]揉捏并挤压[npc.her][npc.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")+"，[npc2.her]那[npc2.fingers]持续掐着[npc.namePos][npc.nipples+]");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，用[npc2.hands]抓向[npc.namePos]的[npc.breasts]并[npc2.sexPaceVerb]玩弄起[npc.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫玩弄着[npc.Name][npc.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]揉捏并玩弄[npc.namePos][npc.nipples+]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.namePos][npc2.cock+]深深插入[npc.her][npc.nipples+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.breasts+]，[npc.sexPaceVerb]将[npc2.namePos][npc2.cock+]引导至[npc.her][npc.nipples+]，让[npc2.name]开始操它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]靠向[npc.her][npc.nipples+]并开始操它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]填满[npc.namePos][npc.nipples+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.hips]压向[npc.namePos]的躯干，开始操[npc.namePos][npc.nipples+]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫操干[npc.Name][npc.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]操干[npc.namePos][npc.nipples+]。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.namePos][npc2.tail+]深深插入[npc.her][npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.breasts+]，[npc.sexPaceVerb]将[npc2.namePos][npc2.tail+]引导至[npc.her][npc.nipples+]，让[npc2.name]开始尾交它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail]靠向[npc.her][npc.nipples+]并开始尾交它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"没有醒来的迹象":"保持完全不动")+"，将[npc2.her]的[npc2.tail]填满[npc.namePos][npc.nipples+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.tail]插入[npc.namePos][npc.breasts+]，开始尾交[npc.namePos][npc.nipples+]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫尾交[npc.Name][npc.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]尾交[npc.namePos][npc.nipples+]。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.namePos][npc2.tentacle+]深深插入[npc.her][npc.nipples+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.breasts+]，[npc.sexPaceVerb]将[npc2.namePos][npc2.tentacle+]引导至[npc.her][npc.nipples+]，让[npc2.name]开始触手交它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle]靠向[npc.her][npc.nipples+]并开始触手交它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.tentacle]填满[npc.namePos][npc.nipples+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.tentacle]插入[npc.namePos][npc.breasts+]，开始触手交[npc.namePos][npc.nipples+]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫触手交[npc.Name][npc.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]触手交[npc.namePos][npc.nipples+]。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]吮吸着[npc.her][npc.nipples+].");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]将[npc.breasts+]贴向[npc2.namePos]的[npc2.face]，让[npc2.name]开始吮吸[npc.her][npc.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.lips]贴向[npc.her][npc.breasts+]并开始吮吸[npc.her][npc.nipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.her][npc2.lips+]继续包裹着[npc.namePos]的[npc.nipples]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，将[npc2.her][npc2.lips+]贴向[npc.namePos][npc.breasts+]，[npc2.sexPaceVerb]吮吸并亲吻[npc.namePos][npc.nipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]将[npc.nipples+]贴上[npc2.her][npc2.lips+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]吮吸[npc.namePos][npc.nipples+]。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	BREAST(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				if(owner.hasBreasts()) {
					return "胸部";
				} else {
					return "平板胸部";
				}
			}
			return owner.getBreastName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.CHEST;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.breasts+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.breasts+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.breasts+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.breasts+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]挤着[npc.her][npc.breasts+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.breasts+]，[npc.sexPaceVerb]让[npc2.name]揉捏并挤压它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱, 但无法阻止[npc2.name]揉捏并挤压[npc.her][npc.breasts+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")+"，[npc2.her]的[npc2.hands]持续抓着[npc.namePos][npc.breasts+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，把[npc2.hands]抚上[npc.namePos]的[npc.breasts]，[npc2.sexPaceVerb]玩弄它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫玩弄[npc.Name][npc.nipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]揉捏并挤压[npc.namePos][npc.breasts+]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = performer.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时[npc2.namePos][npc2.cock+]在[npc.her][npc.breasts+]间来回磨蹭");
								} else {
									sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时[npc2.namePos][npc2.cock+]在[npc.her][npc.breasts+]上来回磨蹭");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc.name]将[npc.breasts+]挤压在一起，以便[npc2.namePos][npc2.cock+]能在其中上下抽插。");
										} else {
											sb.append("[npc.NamePos]贫瘠的胸部无法提供乳交，但这无法阻止[npc.herHim]用[npc.breasts+]对[npc2.name][npc2.cock+]上下摩擦。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]把[npc.namePos][npc.breasts+]挤在一起，无视了[npc.her]抗议的呜咽，强迫[npc.herHim]提供乳交。");
										} else {
											sb.append("[npc2.name]并没有因为[npc.namePos]的平胸而停下，无视了[npc.her]抗拒的呜咽，将[npc.her][npc.cock+]在[npc2.her][npc2.breasts+]间大力摩擦。");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								if(paizuri) {
									sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]插入[npc.namePos][npc.breasts+]间");
								} else {
									sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]压入[npc.namePos][npc.breasts+]");
								}
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc2.name]发出一阵[npc2.moans]，很高兴能从[npc.name]得到乳交服务。");
										} else {
											sb.append("[npc2.name]发出一阵[npc2.moans]，很高兴能从[npc.name]得到贫乳乳交服务。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝[npc.name]的乳交服务，但无济于事。");
										} else {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝[npc.name]的贫乳乳交服务，但无济于事。");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs][npc.sexPaceVerb]为[npc2.name]提供乳交。");
							} else {
								sb.append("[npc.NameIs][npc.sexPaceVerb]为[npc2.name]提供贫乳乳交。");
							}
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]亲吻并舔舐着[npc.her][npc.breasts+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.breasts+]贴向[npc2.namePos][npc2.face]，让[npc2.herHim]亲吻并舔舐它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.lips]贴向[npc.her][npc.breasts+]并开始亲吻舔舐。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.her][npc2.lips+]继续压入[npc.namePos]的[npc.breasts]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，将[npc2.her][npc2.lips+]贴向[npc.namePos][npc.breasts+]，[npc2.sexPaceVerb]亲吻它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图反抗，但无法阻止[npc.name]将[npc.breasts+]贴上[npc2.her][npc2.lips+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]亲吻[npc.namePos][npc.breasts+]。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	NIPPLE_CROTCH(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "乳头";
			}
			return owner.getNippleCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.NIPPLES_CROTCH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.STOMACH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getNippleCrotchStretchedCapacity();
			}
			return owner.getNippleCrotchRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getNippleCrotchMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getNippleCrotchMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.crotchNipples+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.crotchNipples+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.crotchNipples+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.crotchNipples+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]捏着[npc.her][npc.crotchNipples+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]推出[npc.her][npc.crotchBoobs+]，[npc.sexPaceVerb]让[npc2.name]捏捏挤挤[npc.her][npc.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]尝试挣脱束缚，却无法阻止[npc2.name]掐捏[npc.her][npc.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"，[npc2.her]那[npc2.fingers]持续掐着[npc.namePos][npc.crotchNipples+]");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，把[npc2.hands]抚上[npc.namePos]的[npc.crotchBoobs]，[npc2.sexPaceVerb]玩弄[npc.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.name]的手中挣脱，但却失败了。[npc2.she]因此只能一边哭泣一边被迫地看着自己[npc.crotchNipples+]被人无情玩弄。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]揉捏玩弄着[npc.namePos][npc.crotchNipples+]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.namePos][npc2.cock+]深深插入[npc.her][npc.crotchNipples+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]露出[npc.crotchBoobs+]，将[npc2.namePos][npc2.cock+][npc.sexPaceVerb]引至[npc.crotchNipples+]，让[npc2.herHim]操了起来。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.Name]试图挣脱，但其依旧无法阻止[npc2.name]用[npc2.cock]操弄[npc.her][npc.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]填满[npc.namePos][npc.crotchNipples+]");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name]很快就开始[npc2.sexpaceVerb]扭动[npc2.her]的[npc2.hips]来摩擦[npc.namePos]的下体，而[npc2.she]在[npc2.moaning]着的同时操弄起[npc.her]那[npc.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫操干[npc.Name][npc.crotchNipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]操干[npc.namePos][npc.crotchNipples+]。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.namePos][npc2.tail+]深深插入[npc.her][npc.crotchNipples+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.crotchBoobs+]，[npc.sexPaceVerb]将[npc2.namePos][npc2.tail+]引导至[npc.her][npc.crotchNipples+]，让[npc2.name]开始尾交它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail]靠向[npc.her][npc.crotchNipples+]并开始尾交它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"保持完全不动")+"，将[npc2.her]的[npc2.tail]填满[npc.namePos][npc.crotchNipples+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.tail]插入[npc.namePos][npc.crotchBoobs+]，开始尾交[npc.namePos][npc.crotchNipples+]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫尾交[npc.Name][npc.crotchNipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]尾交[npc.namePos][npc.crotchNipples+]。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.namePos][npc2.tentacle+]深深插入[npc.her][npc.crotchNipples+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.crotchBoobs+]，[npc.sexPaceVerb]将[npc2.namePos][npc2.tentacle+]引导至[npc.her][npc.crotchNipples+]，让[npc2.name]开始触手交它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle]靠向[npc.her][npc.crotchNipples+]并开始触手交它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.tentacle]填满[npc.namePos][npc.crotchNipples+]");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append(" [npc2.Name][npc2.sexpaceVerb]将[npc2.tentacle]插入[npc.namePos][npc.crotchBoobs+]，"
												+ "开始触手交[npc.namePos][npc.crotchNipples+]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫触手交[npc.Name][npc.crotchNipples+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]触手交[npc.namePos][npc.crotchNipples+]。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]吮吸着[npc.her][npc.crotchNipples+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]将[npc.crotchBoobs+]贴向[npc2.namePos]的[npc2.face]，让[npc2.name]开始吮吸[npc.her][npc.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.lips]贴向[npc.her][npc.crotchBoobs+]并开始吮吸[npc.her][npc.crotchNipples+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.her][npc2.lips+]继续包裹着[npc.namePos]的[npc.crotchNipples]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，将[npc2.her][npc2.lips+]贴向[npc.namePos][npc.crotchBoobs+]，[npc2.sexPaceVerb]吮吸并亲吻[npc.namePos][npc.crotchNipples+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]将[npc.crotchNipples+]贴上[npc2.her][npc2.lips+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]吮吸[npc.namePos][npc.crotchNipples+]。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	BREAST_CROTCH(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "胯乳";
			}
			return owner.getBreastCrotchName();
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.BREASTS_CROTCH;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.STOMACH;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.crotchBoobs+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.crotchBoobs+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.crotchBoobs+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.crotchBoobs+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]挤着[npc.her][npc.crotchBoobs+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]露出[npc.crotchBoobs+]，[npc.sexPaceVerb]让[npc2.name]揉捏并挤压[npc.her]它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]揉捏并挤压[npc.her][npc.crotchBoobs+]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")+"，[npc2.her]的[npc2.hands]持续抓着[npc.namePos][npc.crotchBoobs+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，用[npc2.hands]抓向[npc.namePos]的[npc.crotchBoobs]，[npc2.sexPaceVerb]玩弄它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图从[npc.Name]身边逃开，但失败了，[npc2.she]只能一边哭泣一边被迫玩弄[npc.Name][npc.crotchBoobs+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]揉捏并挤压[npc.namePos][npc.crotchBoobs+]。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						boolean paizuri = performer.isBreastFuckablePaizuri();
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								if(paizuri) {
									sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时[npc2.namePos][npc2.cock+]在[npc.her][npc.crotchBoobs+]间来回磨蹭");
								} else {
									sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时[npc2.namePos][npc2.cock+]在[npc.her][npc.crotchBoobs+]上来回磨蹭");
								}
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc.name]将[npc.crotchBoobs+]挤压在一起，以便[npc2.namePos][npc2.cock+]能在其中上下抽插。");
										} else {
											sb.append("[npc.NamePos]贫瘠的[npc.crotchBoobs]无法提供胯乳乳交。"
													+ "但这无法阻止[npc.herHim]用[npc.crotchBoobs+]对[npc2.name][npc2.cock+]上下摩擦。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]把[npc.namePos][npc.crotchBoobs+]挤在一起，无视了[npc.her]抗议的呜咽，强迫[npc.herHim]提供胯乳乳交。");
										} else {
											sb.append("[npc2.name]并没有因为[npc.namePos]平坦的[npc.crotchBoobs]而停下，"
													+ "无视了[npc.her]抗拒的呜咽，将[npc.her][npc.cock+]在[npc2.her][npc2.crotchBoobs+]间上下摩擦。");
										}
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								if(paizuri) {
									sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]插入[npc.namePos][npc.crotchBoobs+]间");
								} else {
									sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]压入[npc.namePos][npc.crotchBoobs+]");
								}
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										if(paizuri) {
											sb.append("[npc2.name]发出一阵[npc2.moans]，很高兴能从[npc.name]得到胯乳乳交服务。");
										} else {
											sb.append("[npc2.name]发出一阵[npc2.moans]，很高兴能从[npc.name]得到胯乳贫乳交服务。");
										}
										break;
									case SUB_RESISTING:
										if(paizuri) {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝[npc.name]的胯乳乳交服务，但无济于事。");
										} else {
											sb.append("[npc2.name]呜咽并哭泣着，试图拒绝[npc.name]的胯乳贫乳交服务，但无济于事。");
										}
										break;
								}
							}
							
						} else {
							if(paizuri) {
								sb.append("[npc.NameIs][npc.sexPaceVerb]为[npc2.name]提供胯乳乳交。");
							} else {
								sb.append("[npc.NameIs][npc.sexPaceVerb]为[npc2.name]提供胯乳贫乳交。");
							}
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]亲吻并舔舐着[npc.her][npc.crotchBoobs+]");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.crotchBoobs+]贴向[npc2.namePos]的[npc2.face]，让[npc2.name]亲吻并舔舐它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.lips]贴向[npc.her][npc.crotchBoobs+]并开始亲吻舔舐它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.her][npc2.lips+]继续压入[npc.namePos]的[npc.crotchBoobs]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，将[npc2.her][npc2.lips+]贴向[npc.namePos][npc.crotchBoobs+]，[npc2.sexPaceVerb]亲吻它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]试图反抗，但无法阻止[npc.Name]将[npc.crotchBoobs+]贴上[npc2.her][npc2.lips+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]亲吻[npc.namePos][npc.crotchBoobs+]。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	
	
	THIGHS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "大腿";
			}
			return "大腿";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.THIGHS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.LEG;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"磨蹭着自己的股间。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"磨蹭着自己的股间。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用股间顶向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用股间顶向自己的"+targetArea.getName(performer)+"。");
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
								sb.append((performer.isAsleep()?"仍在沉睡中":"表现得像一个无生命的性爱玩偶")
										+"，[npc.name]把[npc.legs]继续[npc.Name]并到一起，以便[npc2.name]用[npc2.her][npc2.cock+]在形成的缝隙中进进出出。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc.legs]并到一起，以便[npc2.name]用[npc2.her][npc2.cock+]在形成的缝隙中进进出出。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图抵抗，但[npc.her]的[npc.legs]还是被并到一起，被[npc2.namePos][npc2.cock+]素股。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]插入[npc.namePos]的股间");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，[npc2.sexPaceVerb]将[npc2.her][npc2.cock+]插入了[npc.namePos]股间的缝隙。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，被迫使用[npc2.her]的[npc2.cock]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]素股[npc.Name]。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	ARMPITS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0f,
			25/60f, 0,
			false) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "腋窝";
			}
			return "腋窝";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public boolean isPlural() {
			return true;
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			return CoverableArea.ARMPITS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			return InventorySlot.TORSO_UNDER;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return 10_000;
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"磨蹭自己[npc.armpits+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"磨蹭自己[npc.armpits+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.armpits+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.armpits+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"同时[npc2.namePos][npc2.cock+]在[npc.her][npc.armpit+]上来回磨蹭");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]把[npc.her]的[npc.arm(true)]提起，以便[npc2.name]用[npc2.her][npc2.cock+]在[npc.her][npc.armpit+]中上下抽送。");
										break;
									case SUB_RESISTING:
										sb.append("尽管[npc.name]试图反抗，但[npc.her][npc.arm(true)]还是被强行提起，[npc2.namePos][npc2.cock+]在[npc.her][npc.armpit+]间上下磨蹭。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"没有醒来的迹象":"完全不动")+"，保持[npc2.her]的[npc2.cock]压入[npc.namePos]的腋窝");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出[npc2.a_moan+]，[npc2.sexPaceVerb]将[npc2.her][npc2.cock+]压入了[npc.namePos]的腋窝。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.name]呜咽并哭泣着，尽全力从[npc.Name]身边逃开，但最终还是被固定在原位，被迫使用[npc2.her]的[npc2.cock]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]操干[npc.namePos]的腋窝。");
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"[npc.name]仍在沉睡中，没有表现出即将醒来的迹象":"[npc.name]保持一动不动，表现得像一个无生命的性爱玩偶")
											+"，同时[npc2.name]亲吻并舔舐着[npc.her][npc.armpits+]。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name][npc.sexPaceVerb]将[npc.armpits+]贴向[npc2.namePos][npc2.face]，让[npc2.herHim]亲吻并舔舐它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图反抗，但无法阻止[npc2.name]将[npc2.lips]贴向[npc.her][npc.armpits+]并开始亲吻舔舐它们。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，[npc2.her][npc2.lips+]继续压入[npc.namePos]的[npc.armpit]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出低沉的[npc2.moans]，将[npc2.her][npc2.lips+]贴向[npc.namePos][npc.armpits+]，[npc2.sexPaceVerb]亲吻它们。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图反抗，但无法阻止[npc.name]将[npc.armpits+]贴上[npc2.her][npc2.lips+]。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]亲吻[npc.namePos][npc.armpits+]。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	URETHRA_VAGINA(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "阴道尿道";
			}
			return "尿道";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getVaginaUrethraStretchedCapacity();
			}
			return owner.getVaginaUrethraRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getVaginaUrethraMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getVaginaUrethraMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.vaginaUrethra+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.vaginaUrethra+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.vaginaUrethra+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.vaginaUrethra+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.cock+]深入[npc.her][npc.vaginaUrethra+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]用[npc.labia+]在[npc2.namePos]的[npc2.cock][npc2.cockHead+]上摩擦，"
												+ "[npc.sexPaceVerb]摇晃着[npc.hips]，强迫[npc2.name]插入[npc.her][npc.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]插入[npc.her]阴部[npc.vaginaUrethra+]并开始操[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.cock]继续填满[npc.namePos][npc.vaginaUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.cock+]插入[npc.namePos][npc.vaginaUrethra+]，[npc2.she]开始操[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.cock]从[npc.namePos]的[npc.vaginaUrethra]撤出来，但失败了，"
												+ "只能一边哭泣一边被迫看着自己的[npc2.cock]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]操[npc.namePos]的[npc.vaginaUrethra]。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tail+]深入[npc.her][npc.vaginaUrethra+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tail+]，在[npc.her][npc.labia+]上下摩擦，让[npc2.Name]插入并开始尾交[npc.her][npc.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail+]插入[npc.her]阴部[npc.vaginaUrethra+]并开始尾交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tail]继续填满[npc.namePos][npc.vaginaUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tail]插入[npc.namePos]的[npc.vaginaUrethra]，[npc2.she]开始尾交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tail]从[npc.namePos]的[npc.vaginaUrethra]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tail]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]尾交[npc.namePos]的[npc.vaginaUrethra]。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tentacle+]深入[npc.her][npc.vaginaUrethra+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tentacle+]，在[npc.her][npc.labia+]上下摩擦，让[npc2.Name]插入并开始触手交[npc.her][npc.vaginaUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle+]插入[npc.her]阴部[npc.vaginaUrethra+]并开始触手交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tentacle]继续填满[npc.namePos][npc.vaginaUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tentacle]插入[npc.namePos]的[npc.vaginaUrethra]，[npc2.she]开始触手交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tentacle]从[npc.namePos]的[npc.vaginaUrethra]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tentacle]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]触手交[npc.namePos]的[npc.vaginaUrethra]。");
						}
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	URETHRA_PENIS(1,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 2/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			if(standardName) {
				return "阴茎尿道";
			}
			return "尿道";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this) && Main.sex.isPenetrationTypeFree(owner, SexAreaPenetration.PENIS);
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
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getPenisStretchedCapacity();
			}
			return owner.getPenisRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getUrethraMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getUrethraMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.penisUrethra+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.penisUrethra+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.penisUrethra+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.penisUrethra+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.cock+]深入[npc.her][npc.penisUrethra+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]将[npc2.namePos]的[npc2.cock][npc2.cockHead+]在自己的龟头上摩擦，"
												+ "[npc.sexPaceVerb]向前摇动[npc.hips]，强迫[npc2.name]插入[npc.her][npc.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]插入[npc.her]阴茎[npc.penisUrethra+]并开始操[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.cock]继续填满[npc.namePos][npc.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.cock+]插入[npc.namePos][npc.penisUrethra+]，[npc2.she]开始操[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.cock]从[npc.namePos]的[npc.penisUrethra]撤出来，但失败了，"
												+ "只能一边哭泣一边被迫看着自己的[npc2.cock]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]操[npc.namePos]的[npc.penisUrethra]。");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tail+]深入[npc.her][npc.penisUrethra+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]握住[npc2.namePos][npc2.tail+]，"
												+ "在[npc.her]的[npc.cock][npc.cockHead+]上下摩擦，让[npc2.Name]插入并开始尾交[npc.her][npc.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail+]插入[npc.her]阴茎[npc.penisUrethra+]并开始尾交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tail]继续插入[npc.namePos][npc.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tail]插入[npc.namePos][npc.penisUrethra]，[npc2.she]开始尾交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tail]从[npc.namePos]的[npc.penisUrethra]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tail]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]尾交[npc.namePos]的[npc.penisUrethra]。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tentacle+]深入[npc.her][npc.penisUrethra+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.Name]握住[npc2.namePos][npc2.tentacle+]，"
												+ "在[npc.her]的[npc.cock][npc.cockHead+]上下摩擦，让[npc2.Name]插入并开始触手交[npc.her][npc.penisUrethra+]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle+]插入[npc.her]阴茎[npc.penisUrethra+]并开始触手交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name]"+(target.isAsleep()?"保持沉睡着":"继续表现的像个任人摆布的性玩具")
										+"并保持完全不动，同时[npc2.her]的[npc2.tentacle]继续填满[npc.namePos][npc.penisUrethra+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tentacle]插入[npc.namePos]的[npc.penisUrethra]，[npc2.she]开始触手交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tentacle]从[npc.namePos]的[npc.penisUrethra]撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tentacle]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]触手交[npc.namePos]的[npc.penisUrethra]。");
						}
						break;
					case TONGUE:
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	},
	
	SPINNERET(2,
			-0.5f, -0.5f, -1f,
			0.5f, -0.5f , 0.5f,
			4/60f, 4/60f,
			true) {
		@Override
		public String getName(GameCharacter owner, boolean standardName) {
			return "丝囊";
		}
		@Override
		public boolean isFree(GameCharacter owner) {
			return Main.sex.isOrificeFree(owner, this);
		}
		@Override
		public CoverableArea getRelatedCoverableArea(GameCharacter owner) {
			if(owner!=null && owner.hasTailSpinneret()) {
				return CoverableArea.TAIL;
			}
			return CoverableArea.ASS;
		}
		@Override
		public InventorySlot getRelatedInventorySlot(GameCharacter owner) {
			if(owner!=null && owner.hasTailSpinneret()) {
				return InventorySlot.TAIL;
			}
			return InventorySlot.ANUS;
		}
		@Override
		public float getCapacity(GameCharacter owner, boolean currentlyStretchedValue) {
			if(currentlyStretchedValue) {
				return owner.getSpinneretStretchedCapacity();
			}
			return owner.getSpinneretRawCapacityValue();
		}
		@Override
		public int getMaximumPenetrationDepthComfortable(GameCharacter target) {
			return target.getSpinneretMaximumPenetrationDepthComfortable();
		}
		@Override
		public int getMaximumPenetrationDepthUncomfortable(GameCharacter target) {
			return target.getSpinneretMaximumPenetrationDepthUncomfortable();
		}
		@Override
		public String getSexDescription(boolean pastTense, GameCharacter performer, SexPace performerPace, GameCharacter target, SexPace targetPace, SexAreaInterface targetArea) {
			StringBuilder sb = new StringBuilder();
			if(performer==target) {
				//TODO Improve
				if(targetArea.isPenetration()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.spinneret+]。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]用[npc.her]自己的"+targetArea.getName(performer)+"操干自己[npc.spinneret+]。");
					}
				}
				if(targetArea.isOrifice()) {
					if(pastTense) {
						sb.append("[npc.Name][npc.sexPaceVerb]将自己[npc.spinneret+]压向自己的"+targetArea.getName(performer)+"。");
					} else {
						sb.append("[npc.NameIs]正[npc.sexPaceVerb]将自己[npc.spinneret+]压向自己的"+targetArea.getName(performer)+"。");
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
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.fingers+]深入[npc.her]的丝囊不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("Taking hold of [npc2.namePos] [npc2.hand+],"
												+ "用[npc2.fingers+]在[npc.her]的丝囊上下摩擦，然后让它们滑入并开始指交[npc.her]的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.fingers+]插入[npc.her]的丝囊并开始指交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+"并保持完全不动，同时将[npc2.her]那[npc2.fingers]继续深入[npc.namePos][npc.spinneret+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.fingers]插入[npc.namePos]的丝囊，[npc2.she]指交[npc.name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.fingers]从[npc.namePos]的丝囊撤出来，但失败了，"
												+ " and could do nothing but cry as [npc2.she] was forced to finger [npc.herHim] against [npc2.her] will.");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]指交[npc.namePos]的丝囊。");
						}
						break;
					case FOOT:
						break;
					case PENIS:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.cock+]深入[npc.her][npc.spinneret+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]用丝囊在[npc2.namePos]的[npc2.cock][npc2.cockHead+]上摩擦，"
												+ "[npc.sexPaceVerb]摇晃着[npc.hips]，强迫[npc2.name]插入[npc.her]的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.cock]推入[npc.her]的丝囊并开始操[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+"并保持完全不动，同时[npc2.her]的[npc2.cock]继续填满[npc.namePos][npc.spinneret+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.cock+]插入[npc.namePos]的丝囊，[npc2.she]开始操[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.cock]从[npc.namePos]的丝囊撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.cock]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs] [npc.sexPaceVerb] being fucked by [npc2.name].");
						}
						break;
					case TAIL:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tail+]深入[npc.her][npc.spinneret+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tail+]，在[npc.her]的丝囊上下摩擦，让[npc2.Name]插入并开始尾交[npc.her]的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tail+]插入[npc.her]的丝囊并开始尾交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+"并保持完全不动，同时[npc2.her]的[npc2.tail]继续填满[npc.namePos][npc.spinneret+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name][npc2.SexPaceVerb]将[npc2.tail]插入[npc.namePos]的丝囊，[npc2.she]开始尾交[npc.Name]，发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tail]从[npc.namePos]的丝囊撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tail]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]尾交丝囊。");
						}
						break;
					case TENTACLE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append("[npc.name]保持"+(performer.isAsleep()?"沉睡着":"一动不动")+"，对[npc2.name]将[npc2.her][npc2.tentacle+]深入[npc.her][npc.spinneret+]不起任何反应。");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name]握住[npc2.namePos][npc2.tentacle+]，在[npc.her]的丝囊上下摩擦，让[npc2.Name]插入并开始触手交[npc.her]的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name]试图挣脱，但无法阻止[npc2.name]将[npc2.tentacle+]插入[npc.her]的丝囊并开始触手交[npc.herHim]。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append(" [npc2.Name] "+(target.isAsleep()?"remained deeply asleep":"continued to act like an inanimate sex toy")
										+"并保持完全不动，同时[npc2.her]的[npc2.tentacle]继续填满[npc.namePos][npc.spinneret+]。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.Name][npc2.sexpaceVerb]将[npc2.tentacle]插入[npc.namePos]的丝囊，开始触手交[npc.Name]，并发出了愉悦的[npc2.moaning]。");
										break;
									case SUB_RESISTING:
										sb.append("[npc2.Name]试图将[npc2.tentacle]从[npc.namePos]的丝囊撤出来，但失败了，只能一边哭泣一边被迫看着自己的[npc2.tentacle]被使用。");
										break;
								}
							}
							
						} else {
							sb.append("[npc.NameIs][npc.sexPaceVerb]被[npc2.name]触手交丝囊。");
						}
						break;
					case TONGUE:
						if(pastTense) {
							if(isCharacterInanimate(performer)) {
								sb.append((performer.isAsleep()?"Remaining deeply asleep, [npc.name] showed no sign of being close to waking up":"Continuing to act like an inanimate sex doll, [npc.name] kept perfectly still ")
											+"，同时[npc2.name]对[npc.her]的丝囊穴口交");
							} else {
								switch(performerPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc.name][npc.sexPaceVerb]将丝囊贴向[npc2.namePos]的[npc2.face]，让[npc2.name]开始口交[npc.her]的丝囊穴。");
										break;
									case SUB_RESISTING:
										sb.append("[npc.Name] tried to resist,"
												+ "但无法阻止[npc2.name]将[npc2.face]贴向[npc.her]的丝囊并开始口交[npc.her]的丝囊穴。");
										break;
								}
							}
							if(isCharacterInanimate(target)) {
								sb.append("[npc2.Name] "+(target.isAsleep()?"保持深度睡眠":"保持完全不动")+"，将[npc2.her]的[npc2.tongue]伸入[npc.namePos][npc.spinneret+]里。");
							} else {
								switch(targetPace) {
									case DOM_GENTLE:
									case DOM_NORMAL:
									case SUB_EAGER:
									case SUB_NORMAL:
									case DOM_ROUGH:
										sb.append("[npc2.name]发出一连串[npc2.moans+]，[npc2.sexPaceVerb]将[npc2.lips+]贴向[npc.namePos]的丝囊并开始口交[npc.herHim]。");
										break;
									case SUB_RESISTING:
										sb.append(" [npc2.Name] tried to resist,"
												+ "但无法阻止[npc.Name]将丝囊压向[npc2.her]的[npc2.face]并开始[npc.sexPaceVerb]强迫[npc2.herHim]口交。");
										break;
								}
							}
							
						} else {
							sb.append("[npc2.NameIs][npc2.sexPaceVerb]对[npc.namePos]的丝囊口交。");
						}
						break;
				}
			}
			return UtilText.parse(performer, target, sb.toString());
		}
	};

	private float baseArousalWhenPenetrated;
	private float arousalChangePenetratedStretching;
	private float arousalChangePenetratedTooLoose;
	private float arousalChangePenetratedDry;
	private float arousalChangePenetratingStretching;
	private float arousalChangePenetratingTooLoose;
	private float arousalChangePenetratingDry;
	private float cumLossPerSecond;
	private float cumAbsorptionPerSecond;
	private boolean takesPenisVirginity;

	/**
	 * @param baseArousalWhenPenetrated
	 * @param arousalChangePenetratedStretching
	 * @param arousalChangePenetratedTooLoose
	 * @param arousalChangePenetratedDry
	 * @param arousalChangePenetratingStretching
	 * @param arousalChangePenetratingTooLoose
	 * @param arousalChangePenetratingDry
	 * @param cumLossPerSecond The amount of cum or other fluids that leak out of this orifice every second.
	 * @param cumAbsorptionPerSecond The amount of cum or other fluids that are absorbed into the character's body through this orifice every second.
	 * @param takesPenisVirginity
	 */
	private SexAreaOrifice(float baseArousalWhenPenetrated,
			float arousalChangePenetratedStretching,
			float arousalChangePenetratedTooLoose,
			float arousalChangePenetratedDry,
			float arousalChangePenetratingStretching,
			float arousalChangePenetratingTooLoose,
			float arousalChangePenetratingDry,
			float cumLossPerSecond,
			float cumAbsorptionPerSecond,
			boolean takesPenisVirginity) {
		this.baseArousalWhenPenetrated = baseArousalWhenPenetrated;
		this.arousalChangePenetratedStretching = arousalChangePenetratedStretching;
		this.arousalChangePenetratedTooLoose = arousalChangePenetratedTooLoose;
		this.arousalChangePenetratedDry = arousalChangePenetratedDry;
		this.arousalChangePenetratingStretching = arousalChangePenetratingStretching;
		this.arousalChangePenetratingTooLoose = arousalChangePenetratingTooLoose;
		this.arousalChangePenetratingDry = arousalChangePenetratingDry;
		this.cumLossPerSecond = cumLossPerSecond;
		this.cumAbsorptionPerSecond = cumAbsorptionPerSecond;
		this.takesPenisVirginity = takesPenisVirginity;
	}

	@Override
	public boolean isOrifice() {
		return true;
	}
	
	public float getBaseArousalWhenPenetrated() {
		return baseArousalWhenPenetrated;
	}
	
	public float getArousalChangePenetratedStretching() {
		return arousalChangePenetratedStretching;
	}

	public float getArousalChangePenetratedTooLoose() {
		return arousalChangePenetratedTooLoose;
	}

	public float getArousalChangePenetratedDry() {
		return arousalChangePenetratedDry;
	}

	public float getArousalChangePenetratingStretching() {
		return arousalChangePenetratingStretching;
	}

	public float getArousalChangePenetratingTooLoose() {
		return arousalChangePenetratingTooLoose;
	}

	public float getArousalChangePenetratingDry() {
		return arousalChangePenetratingDry;
	}

	public float getCumLossPerSecond() {
		return cumLossPerSecond;
	}
	
	public float getCumAbsorptionPerSecond() {
		return cumAbsorptionPerSecond;
	}
	
	/**
	 * @return true If this orifice is a fully internal orifice, capable of taking penile virginity.<br/>
	 * Mouth, vagina, anus, urethras, and nipple are considered internal orifices.<br/>
	 * Ass, breasts, and thighs are not.
	 */
	public boolean isInternalOrifice() {
		return takesPenisVirginity;
	}
	
	public abstract float getCapacity(GameCharacter owner, boolean currentlyStretchedValue);
	
	public float getCharactersCumLossPerSecond(GameCharacter target) {
		if(target.hasCreampieRetentionArea(this)) {
			return 0;
		}
		
		float cumLost = this.getCumAbsorptionPerSecond();
		float fluidInArea = target.getTotalFluidInArea(this);
		// The rate obviously decreases as the fluid drains out, but assuming if the drain was applied all at once, it would take about 5.5 hours to all drain out (not factoring in absorption or natural loss):
		float secondPercentageLoss = fluidInArea/20_000;
		
		if(!target.isOrificePlugged(this)) {
			cumLost += this.getCumLossPerSecond() + secondPercentageLoss;
		}
		return cumLost;
	}
	
	public abstract int getMaximumPenetrationDepthComfortable(GameCharacter target);
	
	public abstract int getMaximumPenetrationDepthUncomfortable(GameCharacter target);
}
