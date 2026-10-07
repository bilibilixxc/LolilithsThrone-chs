package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.gender.PronounType;

// TODO: needs plural forms
/**
 * @since 0.2.0
 * @version 0.4.4.1
 * @author Innoxia, orvail
 */
public enum Relationship {

	/** For ovipositor egg incubation */
    IncubatorParent("卵生母亲", "卵生父亲", "卵生父母", 0),
    IncubatorChild("卵生女儿", "卵生儿子", "卵生孩子", 0),
    
    Parent("母亲", "父亲", "父母", 0),
    GrandParent("祖母", "祖父", "祖辈", 1),
    GrandGrandParent("曾祖母", "曾祖父", "曾祖辈", 2),
    Child("女儿", "儿子", "孩子", 0),
    GrandChild("孙女", "孙子", "孙辈", 1),
    GrandGrandChild("曾孙女", "曾孙子", "曾孙辈", 2),
    Sibling("姐妹", "兄弟", "亲兄弟姐妹", 0),
    SiblingTwin("双胞胎姐妹", "双胞胎兄弟", "双胞胎", 0),
    HalfSibling("半亲姐妹", "半亲兄弟", "半亲", 1.25),
    Cousin("表亲", 2),
    Pibling("姨妈", "叔叔", "亲戚", 1.5),
    GrandPibling("曾祖姨", "曾祖叔", "祖亲戚", 2.5),
    Nibling("侄女", "侄子", "子侄辈", 1.5);

    private final String displayF;
    private final String displayM;
    private final String displayN;
    private final double distance;

    Relationship(String displayF, String displayM, String displayN, double distance) {
        this.displayF = displayF;
        this.displayM = displayM;
        this.displayN = displayN;
        this.distance = distance;
    }

    Relationship(String display, double distance) {
        this(display, display, display, distance);
    }

    public String toString(PronounType pronounType) {
        switch (pronounType) {
            case FEMININE:
                return displayF;
            case NEUTRAL:
                return displayN;
            case MASCULINE:
                return displayM;
            default:
                throw new RuntimeException();
        }
    }

    public String getName(GameCharacter character) {
    	if(character.isFeminine()) {
    		return getDisplayF();
    	} else {
    		return getDisplayM();
    	}
    }
    
    public String getDisplayF() {
        return displayF;
    }

    public String getDisplayM() {
        return displayM;
    }

    public String getDisplayN() {
        return displayN;
    }

    public double getDistance() {
        return distance;
    }
}