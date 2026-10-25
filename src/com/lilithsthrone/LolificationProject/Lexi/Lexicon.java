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
    public static final String[] PUSHING_SYNONYMS_GENTLE = new String[]{"轻轻推", "轻轻推挤"};

    // Normal
    public static final String[] PUSHING_SYNONYMS_NORMAL = new String[]{"推", "推挤"};

    // Rough
    public static final String[] PUSHING_SYNONYMS_ROUGH = new String[]{"粗暴地推", "推挤", "用力推挤"};

    // Eager
    public static final String[] PUSHING_SYNONYMS_EAGER = new String[]{"急切地推", "推挤", "热情地推挤"};

    // Resisting
    public static final String[] PUSHING_SYNONYMS_RESISTING = new String[]{"粗暴地推", "推挤", "用力推挤"};


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
                "返回此角色的尾穴名称。（始终返回'尾穴'……）",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return "尾穴";
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussy+",
                        "tussyD"),
                true,
                true,
                "",
                "返回此角色的尾穴名称+描述符。（返回一个描述符+'尾穴'）",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getTussyDescriptor() + " 尾穴";
            }
        });

        commandsList.add(new ParserCommand(
                Util.newArrayListOfValues(
                        "tussyCapacity"),
                true,
                true,
                "",
                "返回此角色尾穴容量的描述符。",
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
                "返回此角色尾穴深度的描述符。",
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
                "返回此角色尾穴弹性的描述符。",
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
                "返回此角色尾穴可塑性的描述符。",
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
                "返回此角色尾穴湿润度的描述符。",
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
                "true 如果你希望这个颜色名称带有颜色。",
                "返回BodyCoveringType.TAIL的Covering.getFullDescription()方法结果。",
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
                "true 如果你希望这个颜色名称带有颜色。",
                "返回BodyCoveringType.TUSSY的Covering.getColourDescriptor()方法结果。",
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
                "true 如果你希望这个颜色名称带有颜色。",
                "返回BodyCoveringType.TUSSY的Covering.getPrimaryColourDescriptor()方法结果。",
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
                "true 如果你希望这个颜色名称带有颜色。",
                "返回BodyCoveringType.TUSSY的Covering.getSecondaryColourDescriptor()方法结果。",
                BodyPartType.TAIL){
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return character.getCovering(character.getTussyCovering())
                        .getSecondaryColourDescriptor(arguments != null && arguments.equalsIgnoreCase("true"));
            }
        });
        
    }

}