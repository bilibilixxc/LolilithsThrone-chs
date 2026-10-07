package com.lilithsthrone.game.sex.managers.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.7.7
 * @version 0.4.2.2
 * @author Innoxia
 */
public class SMDominionExpressEncounter extends SexManagerDefault {
	
	private Map<GameCharacter, SexType> preferences;
	private Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap;
	
	public SMDominionExpressEncounter(AbstractSexPosition position,
			Map<GameCharacter, SexSlot> dominants,
			Map<GameCharacter, SexSlot> submissives,
			Map<GameCharacter, SexType> preferences,
			Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap) {
		super(position,
				dominants,
				submissives);
		this.preferences = preferences;
		this.exposeAtStartOfSexMap = exposeAtStartOfSexMap;
	}
	
	@Override
	public boolean isPublicSex() {
		return true;
	}
	
	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(!Main.sex.isDom(character)) {
			return SexControl.ONGOING_ONLY; // So the player can't start anything else.
		}
		return super.getSexControl(character);
	}
	
	@Override
	public boolean isAbleToEquipSexClothing(GameCharacter equippingCharacter, GameCharacter targetedCharacter, AbstractClothing clothingToEquip){
		return clothingToEquip.isCondom();
	}
	
	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return true;
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}
	
	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return false;
	}
	
	@Override
	public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
		return exposeAtStartOfSexMap;
	}
	
	@Override
	public List<CoverableArea> getAdditionalAreasToExposeDuringSex(GameCharacter performer, GameCharacter target) {
		return new ArrayList<>();
	}
	
	@Override
	public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(preferences.containsKey(character)) {
			return preferences.get(character);
		}
		return super.getForeplayPreference(character, targetedCharacter);
	}
	
	@Override
	public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
		if(!character.isPlayer()) {
			return getForeplayPreference(character, targetedCharacter);
		}
		return character.getMainSexPreference(targetedCharacter);
	}
	
	@Override
	public String getPublicSexStartingDescription() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION_EXPRESS) {
			return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+ "一匹路过的半人马奴隶看到你屈服于[npc.name]发出一声嗤笑……"
					+ "</p>";
		}
		
		return super.getPublicSexStartingDescription();
	}
	
	@Override
	public String getRandomPublicSexDescription() {
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION_EXPRESS) {
			return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+ UtilText.parse(Main.sex.getTargetedPartner(Main.game.getPlayer()),
							UtilText.returnStringAtRandom(
								"几匹路过的女半人马看到你后就笑了起来。",
								"一匹半人马奴隶匆忙经过，没有时间看你。",
								"一匹女半人马奴隶匆忙经过，没有时间看你。",
								"一群半人马奴隶稍作停留，评价了你的表演，然后继续前往马厩。",
								"你听到一个女半人马从你身后的某个地方发出了开心的笑声。",
								"你听到一个半人马从你身后的某个地方发出了开心的笑声。",
								"你瞥见一匹青铜等级的[style.mule]正好奇地看着你如何为半人马提供服务。",
								"一个肌肉紧致的半人马奴隶停顿了一下，评价着你的淫秽表演，然后小跑着去工作了。",
								"一个丰满的女半人马奴隶停顿了一下，评价着你的淫秽表演，然后小跑着去工作了。"))
					+"</p>";
			
		}
		return super.getRandomPublicSexDescription();
	}
}
