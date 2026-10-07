package com.lilithsthrone.game.character.attributes;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.97
 * @version 0.3.8.2
 * @author Innoxia
 */
public enum LustLevel {

	ZERO_COLD("淡然", 0, 10, 0.5f, PresetColour.LUST_STAGE_ZERO, SexPace.SUB_RESISTING, SexPace.DOM_GENTLE) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_0;
		}
	},

	ONE_HORNY("燥动", 10, 25, 0.75f, PresetColour.LUST_STAGE_ONE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_1;
		}
	},

	TWO_AMOROUS("愉悦", 25, 50, 1f, PresetColour.LUST_STAGE_TWO, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_2;
		}
	},

	THREE_LUSTFUL("热切", 50, 75, 1.25f, PresetColour.LUST_STAGE_THREE, SexPace.SUB_NORMAL, SexPace.DOM_NORMAL) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_3;
		}
	},

	FOUR_IMPASSIONED("饥渴", 75, 90, 1.5f, PresetColour.LUST_STAGE_FOUR, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_4;
		}
	},
	
	FIVE_BURNING("亢奋", 90, 100, 1.5f, PresetColour.LUST_STAGE_FIVE, SexPace.SUB_EAGER, SexPace.DOM_ROUGH) {
		@Override
		public AbstractStatusEffect getRelatedStatusEffect() {
			return StatusEffect.LUST_PERK_5;
		}
	};
	
	
	private String name;
	private int minimumValue, maximumValue;
	private float arousalModifier;
	private Colour colour;
	private SexPace sexPaceSubmissive;
	private SexPace sexPaceDominant;

	private LustLevel(String name, int minimumValue, int maximumValue, float arousalModifier, Colour colour, SexPace sexPaceSubmissive, SexPace sexPaceDominant) {
		this.name = name;
		this.minimumValue = minimumValue;
		this.maximumValue = maximumValue;
		this.arousalModifier = arousalModifier;
		this.colour = colour;
		this.sexPaceSubmissive = sexPaceSubmissive;
		this.sexPaceDominant = sexPaceDominant;
	}

	public abstract AbstractStatusEffect getRelatedStatusEffect();

	public String getName() {
		return name;
	}

	public int getMinimumValue() {
		return minimumValue;
	}

	public int getMaximumValue() {
		return maximumValue;
	}
	
	public int getMedianValue() {
		return (minimumValue + maximumValue) / 2;
	}
	
	public float getArousalModifier() {
		return arousalModifier;
	}

	public Colour getColour() {
		return colour;
	}

	public static LustLevel getLustLevelFromValue(float value){
		if(value<0) {
			return ZERO_COLD;
		}
		for(LustLevel al : LustLevel.values()) {
			if(value>=al.getMinimumValue() && value<al.getMaximumValue()) {
				return al;
			}
		}
		return FIVE_BURNING;
	}


	public SexPace getSexPaceSubmissive() {
		return sexPaceSubmissive;
	}

	public SexPace getSexPaceDominant() {
		return sexPaceDominant;
	}
	
	public boolean isResistingFromRapePlay(GameCharacter character) {
		return !Main.sex.isDom(character)
				&& (character.hasFetish(Fetish.FETISH_NON_CON_SUB) && !Main.sex.isCharacterBannedFromRapePlay(character))
				&& !((character instanceof NPC) && ((NPC)character).hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer) && !character.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(character.getId()))
				&& getSexPaceSubmissive()!=SexPace.SUB_RESISTING;
	}
	
	public SexPace getSexPace(boolean consensual, GameCharacter character) {
		SexPace pace;
		if(Main.sex.isDom(character)) {
			pace = getSexPaceDominant();
			
			if((character.hasFetish(Fetish.FETISH_SUBMISSIVE) && !character.hasFetish(Fetish.FETISH_SADIST) && !character.hasFetish(Fetish.FETISH_DOMINANT))
					|| character.getFetishDesire(Fetish.FETISH_SADIST) == FetishDesire.ZERO_HATE) {
				pace = SexPace.DOM_GENTLE;
				
			} else if(character.getFetishDesire(Fetish.FETISH_SADIST).isNegative()) {
				pace = SexPace.DOM_NORMAL;
				
			} else if(character.hasFetish(Fetish.FETISH_SADIST)) {
				return SexPace.DOM_ROUGH;
				
			} else { // Hate sex:
				for(GameCharacter target : Main.sex.getAllParticipants()) {
					if(!Main.sex.isDom(target) && character.getAffection(target)<AffectionLevel.NEGATIVE_TWO_DISLIKE.getMaximumValue()) {
						return SexPace.DOM_ROUGH;
					}
				}
			}
			
		} else {
			pace = getSexPaceSubmissive();
			if((character.hasFetish(Fetish.FETISH_NON_CON_SUB) && !Main.sex.isCharacterBannedFromRapePlay(character))
					|| ((character instanceof NPC) && ((NPC)character).hasFlag(NPCFlagValue.genericNPCBetrayedByPlayer) && !character.isSlave() && !Main.game.getPlayer().getFriendlyOccupants().contains(character.getId()))) {
				pace = SexPace.SUB_RESISTING;
			}
		}
		
		if(pace==SexPace.SUB_RESISTING && !Main.getProperties().hasValue(PropertyValue.nonConContent)) {
			pace = SexPace.SUB_NORMAL;
		}
		
		if(pace==SexPace.DOM_ROUGH
				&& ((!character.hasFetish(Fetish.FETISH_DOMINANT) && !character.hasFetish(Fetish.FETISH_SADIST) && !character.hasFetish(Fetish.FETISH_NON_CON_DOM))
						|| (character.getFetishDesire(Fetish.FETISH_SADIST).isNegative()))) {
			pace = SexPace.DOM_NORMAL;
		}
		
		return pace;
	}
	
	public List<String> getStatusEffectModifierDescription(boolean consensual, GameCharacter character) {
		List<String> modifiersList = new ArrayList<>();

		Colour levelColour = LustLevel.getLustLevelFromValue(character.getRestingLust()).getColour();
		modifiersList.add("日常性欲:<b style='color:"+levelColour.toWebHexString()+";'>"+character.getRestingLust()+"</b>");
		
		if(Main.game.isInSex()) {
			switch(this.getSexPace(consensual, character)) {
				case DOM_GENTLE:
					if(!character.isPlayer()) {
						modifiersList.add("偏爱<b style='color: " + SexPace.DOM_GENTLE.getColour().toWebHexString() + "'>缓和</b>的状态");
					}
					break;
				case DOM_NORMAL:
					if(!character.isPlayer()) {
						modifiersList.add("偏爱<b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>正常</b>的状态");
					}
					break;
				case DOM_ROUGH:
					if(!character.isPlayer()) {
						if(!character.hasFetish(Fetish.FETISH_DOMINANT) && !character.hasFetish(Fetish.FETISH_SADIST)) {
							modifiersList.add("偏爱<b style='color: " + SexPace.DOM_NORMAL.getColour().toWebHexString() + "'>正常</b>的状态");
							modifiersList.add("(<b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>粗暴</b>的状态需要"+Fetish.FETISH_DOMINANT.getName(character)
													+"、"+Fetish.FETISH_NON_CON_DOM.getName(character)+"或"+Fetish.FETISH_SADIST.getName(character)+"性癖)");
						} else {
							modifiersList.add("偏爱<b style='color: " + SexPace.DOM_ROUGH.getColour().toWebHexString() + "'>粗暴</b>的状态");
						}
					}
					break;
				case SUB_EAGER:
					if(!character.isPlayer()) {
						modifiersList.add("偏爱<b style='color: " + SexPace.SUB_EAGER.getColour().toWebHexString() + "'>急切</b>的状态");
					}
					break;
				case SUB_NORMAL:
					if(!character.isPlayer()) {
						modifiersList.add("偏爱<b style='color: " + SexPace.SUB_NORMAL.getColour().toWebHexString() + "'>正常</b>的状态");
					}
					break;
				case SUB_RESISTING:
					if(!character.isPlayer()) {
						if(character.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
							modifiersList.add("总是偏爱<b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>抵抗</b>的状态，原因是拥有"+Fetish.FETISH_NON_CON_SUB.getName(character)+"性癖");
						} else {
							modifiersList.add("偏爱<b style='color: " + SexPace.SUB_RESISTING.getColour().toWebHexString() + "'>抵抗</b>的状态");
						}
					}
					break;
			}
		
			int gains = (int)(this.getArousalModifier()*100);
			modifiersList.add((gains>=100?"[style.boldArousal("+gains+"%)]":"[style.boldBad("+gains+"%)]")+"快感获取");
			
		}
		
		return modifiersList;
	}
	
	public String getStatusEffectDescription(boolean consensual, GameCharacter character) {
		StringBuilder sb = new StringBuilder();

		if(Main.game.isInSex()) {
			switch(this.getSexPace(consensual, character)) {
				case DOM_GENTLE:
					switch(this) {
						case ZERO_COLD:
							sb.append("[npc.NameIsFull]现在真的没什么性趣，作为回应，[npc.she]期望可以慢慢来。");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull]已经燥动了起来，但依旧能控制住欲望，保有清醒的头脑，让性事以舒缓温柔的节奏继续下去。");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull]现在感受到的可不止是躁动，但依旧能控制住欲望，让性事以舒缓温柔的节奏继续下去。");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull]遭受着欲火的炙烤，但依旧能让性事以舒缓温柔的节奏继续下去。");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull]现今已经欲火中烧，但依旧能让性事以舒缓温柔的节奏继续下去。");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull]全身心淹没在欲望当中，但不知为何，依旧能让性事以舒缓温柔的节奏继续下去。");
							break;
					}
					break;
				case DOM_NORMAL:
				case SUB_NORMAL:
					switch(this) {
						case ZERO_COLD:
							sb.append("虽然[npc.nameIsFull]现在毫无性趣，但仍能强迫自己表现得很饥渴。");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull]已经燥动了起来，且很乐意享受性趣。");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull]现在感受到的可不止躁动，且很乐意享受性趣。");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull]遭受着欲火的炙烤，等不及要享受性事了。");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull]现今已经欲火中烧，但还能拉得住欲望的缰绳，防止自己太过沉浸。");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull]全身心淹没在欲望当中，但不知为何，还能拉得住欲望的缰绳，防止自己太过沉浸。");
							break;
					}
					break;
				case DOM_ROUGH:
				case SUB_EAGER:
					switch(this) {
						case ZERO_COLD:
							sb.append("虽然[npc.nameIsFull]现在没什么性趣，但依旧强迫自己表现得像是欲火中烧。");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull]已经燥动了起来，且很乐意享受性趣。");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull]现在感受到的可不止躁动，且很乐意享受性趣。");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull]遭受着欲火的炙烤，等不及要享受性事了。");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull]现今已经欲火中烧，越发沉浸于性事当中。");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull]全身心淹没在欲望当中，彻底迷失在性事带来的快乐里。");
							break;
					}
					break;
				case SUB_RESISTING:
					switch(this) {
						case ZERO_COLD:
							sb.append("[npc.NameIsFull]现在没什么性趣，且拼命抗拒着正发生在[npc.herHim]身上的事情。");
							break;
						case ONE_HORNY:
							sb.append("[npc.NameIsFull]已经燥动了起来，但除此外，[npc.sheIs]对现状并不怎么高兴，拼命地抗拒着性事。");
							break;
						case TWO_AMOROUS:
							sb.append("[npc.NameIsFull]现在感受到的可不止躁动，但除此外，[npc.sheIs]对现状并不怎么高兴，拼命地抗拒着性事。");
							break;
						case THREE_LUSTFUL:
							sb.append("[npc.NameIsFull]遭受着欲火的炙烤，但除此外，[npc.sheIs]对现状并不怎么高兴，拼命地抗拒着性事。");
							break;
						case FOUR_IMPASSIONED:
							sb.append("[npc.NameIsFull]现今已经欲火中烧，但除此外，[npc.sheIs]对现状并不怎么高兴，拼命地抗拒着性事。");
							break;
						case FIVE_BURNING:
							sb.append("[npc.NameIsFull]全身心淹没在欲望当中，但除此外，[npc.sheIs]对现状并不怎么高兴，拼命地抗拒着性事。");
							break;
					}
					break;
			}
			
		} else {
			switch(this) {
				case ZERO_COLD:
					sb.append("[npc.NameIsFull]现在真的没什么性趣。");
					break;
				case ONE_HORNY:
					sb.append("[npc.NameIsFull]已经燥动了起来，但还能控制得住欲望。");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br>你似乎正在吸引着麻烦的到来。");
					break;
				case TWO_AMOROUS:
					sb.append("[npc.NameIsFull]现在感受到的可不止躁动，开始更频繁地去想情事。");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br>你能感受到引来麻烦的目光正变得越来越多。");
					break;
				case THREE_LUSTFUL:
					sb.append("[npc.NameIsFull]遭受着欲火的炙烤，很难再去想性以外的事情。");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br>你没法为你那充斥着欲望的灵光找任何借口。");
					break;
				case FOUR_IMPASSIONED:
					sb.append("[npc.NameIsFull]现今已经欲火中烧，再难去想性以外的事情。");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br>几乎所有路过你的人都会投以好色的目光。");
					break;
				case FIVE_BURNING:
					sb.append("[npc.NameIsFull]全身心淹没在欲望当中，只能想着性事。");
					if(Main.game.isOpportunisticAttackersEnabled() && character.isPlayer())
						sb.append("<br><b style='color:" + PresetColour.BASE_GREY.toWebHexString() +";'>投机袭击者</b><br>谁都能看出你正身处欲火之中，或许会有人趁机占你便宜。");
					break;
			}
		}
		
		return UtilText.parse(character, sb.toString());
	
	}
}
