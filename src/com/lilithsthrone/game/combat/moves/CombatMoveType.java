package com.lilithsthrone.game.combat.moves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * Move type used in combat. Determines certain interactions, but doesn't have inherent power to itself.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Irbynx
 */
public enum CombatMoveType {

    ATTACK("攻击", PresetColour.DAMAGE_TYPE_PHYSICAL),
    DEFEND("防御", PresetColour.SPELL_SCHOOL_WATER),
    TEASE("挑逗", PresetColour.GENERIC_SEX),
    SPELL("法术", PresetColour.GENERIC_ARCANE),
	POWER("能力", PresetColour.GENERIC_ARCANE),
//    SKILL("Skill", PresetColour.GENERIC_GOOD),
    ATTACK_DEFEND("防御式攻击", PresetColour.SPELL_SCHOOL_WATER, new ArrayList<>(Arrays.asList(CombatMoveType.ATTACK, CombatMoveType.DEFEND)));

    private String name;
    private List<CombatMoveType> countsAsList;
    private Colour colour;

    CombatMoveType(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        countsAsList = new ArrayList<>();
        countsAsList.add(this);
    }

    CombatMoveType(String name, Colour colour, List<CombatMoveType> countsAsList) {
        this.name = name;
        this.colour = colour;
        this.countsAsList = countsAsList;
    }

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public List<CombatMoveType> getCountsAsList() {
    	return countsAsList;
    }

    /**
     * Returns true if the type can count as a different type. Use it for multitypes.
     * @param moveTypeCompared Type to compare against
     * @return
     */
    public boolean countsAs(CombatMoveType moveTypeCompared) {
        if(this == moveTypeCompared){
            return true;
        }
        for (CombatMoveType moveType: countsAsList) {
            if(moveTypeCompared == moveType) {
                return true;
            }
        }
        return false;
    }
}
