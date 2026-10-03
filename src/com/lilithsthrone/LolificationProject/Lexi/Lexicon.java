package com.lilithsthrone.LolificationProject.Lexi;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BodyPartType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.dialogue.AbstractDialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.ParserCommand;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import java.util.List;

import static com.lilithsthrone.game.dialogue.utils.UtilText.commandsList;
import static com.lilithsthrone.game.dialogue.utils.UtilText.parseCapitalise;

public class Lexicon {
    // Gentle
    public static final String[] PUSHING_SYNONYMS_GENTLE = new String[]{"gently pushing", "Gently shoving"};

    // Normal
    public static final String[] PUSHING_SYNONYMS_NORMAL = new String[]{"pushing", "shoving"};

    // Rough
    public static final String[] PUSHING_SYNONYMS_ROUGH = new String[]{"roughly pushing", "shoving", "Forcefully shoving"};

    // Eager
    public static final String[] PUSHING_SYNONYMS_EAGER = new String[]{"eagerly pushing", "shoving", "enthusiastically shoving"};

    // Resisting
    public static final String[] PUSHING_SYNONYMS_RESISTING = new String[]{"Roughly pushing", "shoving", "Forcefully shoving"};


    // Very incomplete array, just an example
    public static final String[][] PUSHING_BY_PACE = new String[][]{PUSHING_SYNONYMS_ROUGH, PUSHING_SYNONYMS_GENTLE, PUSHING_SYNONYMS_NORMAL, PUSHING_SYNONYMS_EAGER, PUSHING_SYNONYMS_RESISTING};

    public String getPush(SexPace pace) {
        String[] paceArr = PUSHING_BY_PACE[pace.ordinal()];
        return paceArr[Util.random.nextInt(paceArr.length - 1)];
    }


    // Cecilia:
    public static AbstractDialogueFlagValue ceciliaMet = new AbstractDialogueFlagValue();
    public static AbstractDialogueFlagValue ceciliaFucked = new AbstractDialogueFlagValue();
    public static AbstractDialogueFlagValue ceciliaDisabled = new AbstractDialogueFlagValue();
    public static AbstractDialogueFlagValue ceciliaExplained = new AbstractDialogueFlagValue();

    public static void addCommands() {
        
        // Tussy Parsers:

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussy"),
                true,
                true,
                "",
                "Returns the name of this character's tussy. (Always returns 'tussy'...)",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return "tussy";
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussy+",
                        "tussyD"),
                true,
                true,
                "",
                "Returns the name + descriptor of this character's tussy. (Returns a descriptor + ' tussy')",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getTussyDescriptor() + " tussy";
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyCapacity"),
                true,
                true,
                "",
                "Returns a descriptor of this character's tussy capacity.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return Capacity.getCapacityFromValue(character.getTussyStretchedCapacity()).getDescriptor();
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyDepth"),
                true,
                true,
                "",
                "Returns a descriptor of this character's tussy depth.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getTussyDepth().getDescriptor();
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyElasticity"),
                true,
                true,
                "",
                "Returns a descriptor of this character's tussy elasticity.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getTussyElasticity().getDescriptor();
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyPlasticity"),
                true,
                true,
                "",
                "Returns a descriptor of this character's tussy plasticity.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getTussyPlasticity().getDescriptor();
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyWetness"),
                true,
                true,
                "",
                "Returns a descriptor of this character's tussy wetness.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getTussyWetness().getDescriptor();
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyFullDescription"),
                true,
                true,
                "true If you want this colour's name to be coloured.",
                "Returns the Covering.getFullDescription() method for BodyCoveringType.TAIL.",
                BodyPartType.TAIL) {
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getCovering(character.getTussyCovering())
                        .getFullDescription(character, arguments != null && arguments.equalsIgnoreCase("true"));
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyColor",
                        "tussyColour"),
                true,
                true,
                "true If you want this colour's name to be coloured.",
                "Returns the Covering.getColourDescriptor() method for BodyCoveringType.TUSSY.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getCovering(character.getTussyCovering())
                        .getColourDescriptor(character, arguments != null && arguments.equalsIgnoreCase("true"), parseCapitalise);
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyColourPrimary",
                        "tussyColorPrimary",
                        "tussyPrimaryColour",
                        "tussyPrimaryColor"),
                true,
                true,
                "true If you want this colour's name to be coloured.",
                "Returns the Covering.getPrimaryColourDescriptor() method for BodyCoveringType.TUSSY.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getCovering(character.getTussyCovering())
                        .getPrimaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyColourSecondary",
                        "tussyColorSecondary",
                        "tussySecondaryColour",
                        "tussySecondaryColor"),
                true,
                true,
                "true If you want this colour's name to be coloured.",
                "Returns the Covering.getSecondaryColourDescriptor() method for BodyCoveringType.TUSSY.",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getCovering(character.getTussyCovering())
                        .getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
            }
        });
        
    }

}
