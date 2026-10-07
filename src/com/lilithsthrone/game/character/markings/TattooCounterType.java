package com.lilithsthrone.game.character.markings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.pregnancy.Litter;
import com.lilithsthrone.game.character.pregnancy.PregnancyPossibility;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Units;

/**
 * @since 0.2.6
 * @version 0.3.7.2
 * @author Innoxia
 */
public enum TattooCounterType {
	
	NONE("none", "不要在这个纹身上添加追踪器。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return 0;
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},

	
	VALUE_AS_SLAVE("奴隶价值", "显示所有者作为奴隶时价值几何。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getValueAsSlave(false); 
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	SEX_SUB("服从性爱", "记录所有者进行过服从型性爱的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalSexAsSubCount();
		}
	},

	SEX_DOM("支配性爱", "记录所有者进行过支配型性爱的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalSexAsDomCount();
		}
	},

	UNIQUE_SEX_PARTNERS("性伴侣数量", "记录所有者跟多少个不同的人进行过性爱。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getUniqueSexPartnerCount();
		}
	},

	PUSSY_FUCKED("小穴被插入", "记录所有者的小穴被插入的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.VAGINA);
		}
	},

	ANUS_FUCKED("肛门被插入", "记录所有者的肛门被插入的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.ANUS);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	
	ORAL_FUCKED("口穴被插入", "记录所有者的口穴被插入的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.MOUTH);
		}
	},
	
	NIPPLES_FUCKED("乳头被插入", "记录所有者的乳头被插入的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.NIPPLE);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},
	
	NIPPLES_CROTCH_FUCKED("胯乳乳头被插入", "记录所有者的胯乳乳头被插入的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesOrificePenetrated(SexAreaOrifice.NIPPLE_CROTCH);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	PENIS_PENETRATIONS("阴茎插入", "记录所有者的阴茎插入腔穴的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.PENIS);
		}
	},

	TAIL_PENETRATIONS("尾巴插入", "记录所有者的尾巴插入腔穴的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.TAIL);
		}
	},

	TENTACLE_PENETRATIONS("触手插入", "记录所有者的触手插入腔穴的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalTimesPenetratedOrifices(SexAreaPenetration.TENTACLE);
		}
	},
	
	CUM_GIVEN("共计内射", "记录所有者内射的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(true, false);
		}
	},

	CUM_GIVEN_PUSSY("小穴内射", "记录所有者射在小穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.VAGINA, true, false);
		}
	},

	CUM_GIVEN_ANUS("肛穴内射", "记录所有者射在肛穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.ANUS, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},

	CUM_GIVEN_ORAL("口射次数", "记录所有者射在某人口中的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, true, false);
		}
	},

	CUM_GIVEN_NIPPLES("乳穴内射", "记录所有者射在乳穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	CUM_GIVEN_NIPPLES_CROTCH("胯乳乳穴内射", "记录所有者射在胯乳乳穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE_CROTCH, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	CUM_GIVEN_FEET("接受足交高潮", "记录所有者射在对象脚上的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, true, false);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isFootContentEnabled();
		}
	},
	
	CUM_TAKEN("共计接受内射", "记录所有者被射进腔穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCount(false, true);
		}

	},

	CUM_TAKEN_PUSSY("小穴接受内射", "记录所有者被射进小穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.VAGINA, false, true);
		}
	},

	CUM_TAKEN_ANUS("肛穴接受内射", "记录所有者被射进肛穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.ANUS, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},

	CUM_TAKEN_ORAL("吞咽次数", "记录所有者咽下精液的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.MOUTH, false, true);
		}
	},

	CUM_TAKEN_NIPPLES("乳穴接受内射", "记录所有者被射进乳穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled();
		}
	},

	CUM_TAKEN_NIPPLES_CROTCH("胯乳乳穴接受内射", "记录所有者被射进胯乳乳穴内的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaOrifice.NIPPLE_CROTCH, false, true);
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isNipplePenEnabled() && Main.game.isUdderContentEnabled();
		}
	},

	CUM_TAKEN_FEET("给予足交高潮", "记录所有者被某人的精液覆盖在脚上的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getTotalCumCountInArea(SexAreaPenetration.FOOT, false, true); 
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isFootContentEnabled();
		}
	},
	

	VIRGINITIES_TAKEN_TOTAL("共计破处", "记录所有者夺去了多少次贞操(包括阴道、肛门、口部、乳头、尿道等)。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(orifice.isInternalOrifice()) {
							for(SexAreaPenetration pen : SexAreaPenetration.values()) {
								if(pen.isTakesVirginity()
										&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen))!=null
										&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen)).getKey().equals(bearer.getId())) {
									count++;
									break;
								}
							}
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					if(orifice.isInternalOrifice()) {
						for(SexAreaPenetration pen : SexAreaPenetration.values()) {
							if(pen.isTakesVirginity()
									&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen))!=null
									&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, orifice, pen)).getKey().equals(bearer.getId())) {
								count++;
								break;
							}
						}
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_VAGINAL("阴道破处", "记录所有者夺去了多少次阴道贞操。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_ANAL("肛门破处", "记录所有者夺去了多少次肛门贞操。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
	},
	
	VIRGINITIES_TAKEN_ORAL("口部破处", "记录所有者夺去了多少次口部贞操。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaPenetration pen : SexAreaPenetration.values()) {
						if(pen.isTakesVirginity()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaPenetration pen : SexAreaPenetration.values()) {
					if(pen.isTakesVirginity()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, pen)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},
	
	VIRGINITIES_TAKEN_PENIS("夺取童贞", "记录所有者夺去了多少次阴茎童贞。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			
			for(GameCharacter character : Main.game.getAllNPCs()) {
				if(!character.equals(bearer)) {
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(orifice.isInternalOrifice()
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice))!=null
								&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice)).getKey().equals(bearer.getId())) {
							count++;
							break;
						}
					}
				}
			}
			
			GameCharacter character = Main.game.getPlayer();
			if(!character.equals(bearer)) {
				for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
					if(orifice.isInternalOrifice()
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice))!=null
							&& character.getVirginityLoss(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, orifice)).getKey().equals(bearer.getId())) {
						count++;
						break;
					}
				}
			}
			
			return count; 
		}
	},

	CURRENT_PREGNANCY("产仔数量", "显示所有者现在怀着的孩子数量。") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(bearer.getPregnantLitter()==null) {
				return 0;
			}
			return bearer.getPregnantLitter().getTotalLitterCount();
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},

	OFFSPRING_BIRTHED("娩出后代", "记录所有者分娩出的后代数量。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersBirthed()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	OFFSPRING_FATHERED("生殖后代", "记录所有者作为父亲拥有的后代数量。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersFathered()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},
	
	PREGNANCY("怀孕", "记录所有者受孕的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			return bearer.getLittersBirthed().size() + (bearer.isPregnant()?1:0);
		}
	},

	PREGNANCY_PARTNERS("怀孕对象", "记录所有者总共怀过多少个不同的人的孩子。") {
		@Override
		public int getCount(GameCharacter bearer) {
			Set<String> partners = new HashSet<>();
			for(Litter litter : bearer.getLittersBirthed()) {
				partners.add(litter.getFatherId());
			}
			return partners.size();
		}
	},
	
	IMPREGNATIONS("授孕", "记录所有者授孕的次数。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int potentials = 0;
			for(PregnancyPossibility pp : bearer.getPotentialPartnersAsFather()) {
				if(pp.getMother()!=null && pp.getMother().isPregnant() && Objects.equals(pp.getFather(), bearer)) {
					potentials++;
				}
			}
			return potentials + bearer.getLittersFathered().size();
		}
	},

	IMPREGNATION_PARTNERS("授孕对象", "记录所有者让多少个不同的人怀上过自己的孩子。") {
		@Override
		public int getCount(GameCharacter bearer) {
			Set<String> potentials = new HashSet<>();
			for(PregnancyPossibility pp : bearer.getPotentialPartnersAsFather()) {
				if(pp.getMother()!=null && pp.getMother().isPregnant() && Objects.equals(pp.getFather(), bearer)) {
					potentials.add(pp.getMotherId());
				}
			}
			Set<String> partners = new HashSet<>();
			for(Litter litter : bearer.getLittersFathered()) {
				partners.add(litter.getMotherId());
			}
			return potentials.size() + partners.size();
		}
	},

	EGGS_IMPLANTED("授卵数量", "记录所有者总共对他人授卵多少颗。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersImplanted()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	EGGS_INCUBATED("孵卵数量", "记录所有者总共对被授卵授卵多少颗，并且完全孵化后产下。") {
		@Override
		public int getCount(GameCharacter bearer) {
			int count = 0;
			for(Litter litter : bearer.getLittersIncubated()) {
				count+=litter.getTotalLitterCount();
			}
			return count;
		}
	},

	CUM_IN_VAGINA("子宫精液", "显示所有者的子宫内现在有多少精液(单位为"+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+")。") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.VAGINA));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.VAGINA)));
			}
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	CUM_IN_ASS("肛内精液", "显示所有者的肛内现在有多少精液(单位为"+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+")。") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.ANUS));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.ANUS)));
			}
		}
		@Override
		public boolean isAvailable() {
			return Main.game.isAnalContentEnabled();
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	},
	
	CUM_IN_STOMACH("腹部精液", "显示所有者的腹部现在有多少精液(单位为"+(Main.getProperties().hasValue(PropertyValue.metricFluids)?"mL":"oz")+")。") {
		@Override
		public int getCount(GameCharacter bearer) {
			if(Main.getProperties().hasValue(PropertyValue.metricFluids)) {
				return Math.round(bearer.getTotalFluidInArea(SexAreaOrifice.MOUTH));
			} else {
				return Math.round(Units.mlToOz(bearer.getTotalFluidInArea(SexAreaOrifice.MOUTH)));
			}
		}
		@Override
		public boolean isRetroactiveApplicationAvailable() {
			return false;
		}
	};
	
	private String name;
	private String description;
	
	private TattooCounterType(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public abstract int getCount(GameCharacter bearer);

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public boolean isRetroactiveApplicationAvailable() {
		return true;
	}

	/**
	 * @return The negative int which should be applied in order to start this TattooCounter at 0 (i.e. a value for removing retroactive application)
	 */
	public int getNonRetroactiveOffset(GameCharacter bearer) {
		if(!isRetroactiveApplicationAvailable()) {
			return 0;
		}
		return -getCount(bearer);
	}
	
	public boolean isAvailable() {
		return true;
	}
	
	public static List<TattooCounterType> getTattooCounterTypesWithContentFiltersApplied() {
		List<TattooCounterType> returnList = new ArrayList<>();
		for(TattooCounterType tct : TattooCounterType.values()) {
			if(tct.isAvailable()) {
				returnList.add(tct);
			}
		}
		return returnList;
	}
}
