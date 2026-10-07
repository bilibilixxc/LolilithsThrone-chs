package com.lilithsthrone.game.sex.managers.dominion.gloryHole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.population.Population;

/**
 * @since 0.2.9
 * @version 0.3.7
 * @author Innoxia
 */
public class SMGloryHole extends SexManagerDefault {

	public SMGloryHole(AbstractSexPosition position, Map<GameCharacter, SexSlot> dominants, Map<GameCharacter, SexSlot> submissives) {
		super(position,
				dominants,
				submissives);
	}

	@Override
	public SexControl getSexControl(GameCharacter character) {
		if(Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_KNEELING)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKED)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			return SexControl.FULL;
			
		} else {
			return SexControl.ONGOING_ONLY;
		}
	}
	
	@Override
	public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return character.isPlayer()
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_KNEELING)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_FUCKED)
				|| Main.sex.getSexPositionSlot(character).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED);
	}

	@Override
	public boolean isAbleToRemoveSelfClothing(GameCharacter character){
		return character.isPlayer();
	}
	
	@Override
	public boolean isAbleToRemoveOthersClothing(GameCharacter character, AbstractClothing clothing){
		return false;
	}

	@Override
	public boolean isPlayerAbleToStopSex() {
		return true;
	}
	
	@Override
	public boolean isCharactersReactingToExposedAreas() {
		return false;
	}
	
	@Override
	public List<InventorySlot> getSlotsConcealed(GameCharacter characterBeingExposed, GameCharacter characterViewing) {
		List<InventorySlot> concealedSlots = new ArrayList<>();
		
		if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_KNEELING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			return concealedSlots;
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.PENIS);
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			return concealedSlots;
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_ANALLY_FUCKED)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			concealedSlots.remove(InventorySlot.MOUTH);
			concealedSlots.remove(InventorySlot.ANUS);
			concealedSlots.remove(InventorySlot.GROIN);
			return concealedSlots;
		}
		
		// The ones on the other side of the hole cannot see one another
		if(Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_FUCKING)
				|| Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
				|| Main.sex.getSexPositionSlot(characterViewing).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			return concealedSlots;
		}
		
		if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_FUCKING)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			if(!characterBeingExposed.isTaur()) {
				concealedSlots.remove(InventorySlot.PENIS);
			}
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			
		} else if(Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_ONE)
					|| Main.sex.getSexPositionSlot(characterBeingExposed).equals(SexSlotUnique.GLORY_HOLE_RECEIVING_ORAL_TWO)) {
			Collections.addAll(concealedSlots, InventorySlot.values());
			
			if(!characterBeingExposed.isTaur()) {
				concealedSlots.remove(InventorySlot.PENIS);
			}
			if(characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA
					|| characterBeingExposed.getGenitalArrangement()==GenitalArrangement.CLOACA_BEHIND) {
				concealedSlots.remove(InventorySlot.ANUS);
				concealedSlots.remove(InventorySlot.PENIS);
			}
			concealedSlots.remove(InventorySlot.VAGINA);
			concealedSlots.remove(InventorySlot.GROIN);
			
		}
		
		return concealedSlots;
	}

	@Override
	public String getPublicSexStartingDescription() {
		return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+ "你发出一声[pc.a_moan+]，引得厕所里好几个人一齐扭头，看看到底发生了什么事情。"
					+ "见到你大开着隔间的门，而且正准备侍奉你面前的肉棒，有几个家伙连忙围了上来……"
				+ "</p>";
	}

	@Override
	public String getRandomPublicSexDescription() {
		Set<AbstractSubspecies> subspeciesSet = new HashSet<>();
		for(Population pop : Main.game.getPlayer().getLocationPlace().getPlaceType().getPopulation()) {
			subspeciesSet.addAll(pop.getSpecies().keySet());
		}
		if(!subspeciesSet.isEmpty()) {
			AbstractSubspecies subspecies = Util.randomItemFrom(subspeciesSet);
			
			return "<p style='color:"+PresetColour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
						+ UtilText.returnStringAtRandom(
							"聚集上来观赏你淫秽表演的家伙一边看着，一边大笑欢呼起来。",
							"人们围观你正在寻欢洞服务，起着哄，你听见人群中传来一声流氓哨。",
							"一对斑马男推开人群，但并没有阻止你取乐，而是加入了围观，对着你的表演说笑评论着。",
							"你听见聚集起来围观的人群中有人在评价你的表演。",
							"几个围观群众看着你侍奉者面前的肉棒，欢呼大笑起来。",
							"你瞥了一眼，看到几个观众一边看你在寻欢洞服务，一边自慰。",
							"你继续在寻欢洞服务，四周不断响起呼和声。",
							"你继续在人群面前表现得像个欲求不满的婊子，四周不断响起呼和声。",
							"你继续在寻欢洞服务，有几个家伙高喊欢呼起来。",
							Util.capitaliseSentence(UtilText.generateSingularDeterminer(subspecies.getSingularFemaleName(null)))+subspecies.getSingularFemaleName(null)+"上前来，"
									+ "撸动着暴露在外的鸡巴，一下子在你的面前射得满地都是。",
							Util.capitaliseSentence(UtilText.generateSingularDeterminer(subspecies.getSingularMaleName(null)))+subspecies.getSingularMaleName(null)+"上前来，"
									+ "撸动着暴露在外的鸡巴，一下子在你的面前射得满地都是。")
					+"</p>";
		}
		
		return "";
	}
}
