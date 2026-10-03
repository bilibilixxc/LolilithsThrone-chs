package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.dialogue.utils.ParserCommand;
import com.lilithsthrone.game.dialogue.utils.ParserTag;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

import static com.lilithsthrone.LolificationProject.ConfigStuff.ConfigurationManager.configFolder;
import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.LOG_UNPARSED_LINES;
import static com.lilithsthrone.game.dialogue.utils.UtilText.commandsList;

@SuppressWarnings({"unused", "WeakerAccess"})
public class TranslationUtils {
    public static String language = "en_US";
    public static final File translationFolder = new File("./res/Translation/"),
            parsingLinesOut = new File(configFolder, "UnparsedLines.log"),
    parsingContentFolder = new File(configFolder, "ParserReplacements");
    public static final HashMap<String, ArrayList<String>> swaps = new HashMap<>(), preswaps = new HashMap<>();

    public static void init() throws IOException {
        File[] shouldOnlyBeOne = translationFolder.listFiles(a -> a.getName().endsWith(".enabled"));
        language = (shouldOnlyBeOne != null && shouldOnlyBeOne.length == 1) ?
                shouldOnlyBeOne[0].getName().replace(".enabled", "") : "en_US";
        if (!parsingLinesOut.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(parsingLinesOut));
            bw.append("# This file contains unparsed lines that can be entirely replaced at your leisure for translation or adding new variations");
            bw.newLine();
            bw.append("# To add more content or replace content put the lines in ParserReplacements/Any_Name_You_Want.cfg");
            bw.close();
        }

        if (!parsingContentFolder.exists()) {
            boolean success = parsingContentFolder.mkdirs();
            if (success) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(parsingContentFolder, "example_replacer.cfg")));
                bw.append("# In these files (this one and others in the same folder with any name)");
                bw.newLine();
                bw.append("# You may replace lines said in the game before parsing to whatever you like Example Below");
                bw.newLine();
                bw.append("# Example 1 : [npc.her] [npc.pussy+] [verb(is)] so [adjective(wet+)]=[npc.her] [adjective(wet+)] [npc.pussy+]");
                bw.newLine();
                bw.append("# Much like chatswaps this will have custom variables and more variations, but unlike chatswaps this happens BEFORE parsing");
                bw.newLine();
                bw.append("# Unlike chatswaps the arguments will be on the value side and use § to declare argument start and stop regions");
                bw.newLine();
                bw.append("# Example 2 : [npc.her] [npc.pussy+] [verb(is)] so [adjective(wet+)]=§NPC:age(<5)§[npc.her] [adjective(tiny)] [adjective(wet+)] [npc.pussy+]");
                bw.newLine();
                bw.append("# Example 3 : [npc.her] [npc.pussy+] [verb(is)] so [adjective(wet+)]=§NPC:age(6-10)§[npc.her] [adjective(small)] [adjective(wet+)] [npc.pussy+]");
                bw.newLine();
                bw.append("# Example 4 : [npc.her] [npc.pussy+] [verb(is)] so [adjective(wet+)]=§NPC:age(>40)§[npc.her] [adjective(aged)] [adjective(wet+)] [npc.pussy+]");
                bw.newLine();
                bw.append("# All 4 examples can be used at the same time, the system will parse and pick the one that fits best, or if multiple with same requirements, and random one");
                bw.newLine();
                bw.append("# However the advanced feature is unfinished, and therefore not enabled, sorry about that... Only example 1 can be used.");
                bw.newLine();
                bw.close();
            }
        } else {
            for (File file : parsingContentFolder.listFiles()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.lines().forEach(line -> {
                    if (line.startsWith("#")) return;
                    String[] K_V = line.split("=");
                    String key = K_V[0];
                    String value = K_V[1];
                    if (!preswaps.containsKey(key)) {
                        preswaps.put(key, new ArrayList<>());
                    }

                    if (!preswaps.get(key).contains(value)) {
                        preswaps.get(key).add(value);
                    }
                });
            }
            if (!preswaps.isEmpty()) {
                System.out.println("Loaded " + preswaps.size() + " unparsed lines.");
                preswaps.forEach((key, value) -> {
                    System.out.println("Loaded " + value.size() + " alternative lines for " + key);
                });
            }
        }

        /*
         * User Added Names
         */
        File names = new File("./res/names.txt");
        BufferedReader reader = new BufferedReader(new FileReader(names));
        reader.lines().forEach(line -> {
            if (line.startsWith("#") || !line.contains("=")) return;
            String race = line.split("=")[0];
            String[] tripletNames = line.split("=")[1].split(",");
            List<String> tNames = Arrays.asList(tripletNames);
            if (!tNames.isEmpty() && tNames.size() != 3 && !race.equalsIgnoreCase("lilin")) {
                tNames.add(tNames.get(0));
                if (tNames.size() != 3)
                    tNames.add(tNames.get(0));
            }
            // What the fuck is going on here?
            if (tNames.isEmpty()) {
                tNames.add("Need_1-3_names_seperated_by_,'s_single_names_get_duplicated");
                tNames.add("Need_1-3_names_seperated_by_,'s_single_names_get_duplicated");
                tNames.add("Need_1-3_names_seperated_by_,'s_single_names_get_duplicated");
            }
            switch (race.toLowerCase()) {
                case ("equine") -> Name.equine.add(new NameTriplet(tNames.get(0), tNames.get(1), tNames.get(2)));
                case ("reindeer") -> Name.reindeer.add(new NameTriplet(tNames.get(0), tNames.get(1), tNames.get(2)));
                case ("prostitute") ->
                        Name.prostitute.add(new NameTriplet(tNames.get(0), tNames.get(1), tNames.get(2)));
                case ("lilin") -> Name.lilinNames.addAll(tNames);
                case ("human") -> Name.human.add(new NameTriplet(tNames.get(0), tNames.get(1), tNames.get(2)));
            }
        });
        reader.close();

        /*
         * User Added Surnames
         */
        File surnames = new File("./res/surnames.txt");
        reader = new BufferedReader(new FileReader(surnames));
        reader.lines().forEach(line -> {
            if (line.startsWith("#") || !line.contains("=")) return;

            String[] arr = line.split("=");
            String[] nms = arr[1].split(",");
            List<String> tnames = Arrays.asList(nms);
            switch (arr[0]) {
                case ("youkoSurnames") -> Name.youkoSurnames.addAll(tnames);
                case ("surnames") -> Name.surnames.addAll(tnames);
            }
        });
        reader.close();

        /*
         * User Added Dialogue Replacements
         */
        File lang = new File(translationFolder, language + "/chatswaps.lang");
        reader = new BufferedReader(new FileReader(lang));
        reader.lines().forEach(line -> {
            if (line.startsWith("#") || !line.contains("=")) return;

            String searchConditions = line.substring(0, line.lastIndexOf("="));
            String searchReplacement = line.substring(line.lastIndexOf("=") + 1);

            if (!searchConditions.contains("$")) addSwap(searchConditions, searchReplacement);
            else addSwap(searchConditions.substring(0, line.lastIndexOf("$")), searchReplacement);
        });
        reader.close();

//        System.err.println("!!!!! Debugging Swaps !!!!!!");
//        System.err.println("Loaded " + swaps.size() + " swap rules.");
//        swaps.forEach((key, value) -> {
//            System.err.println("Swapping " + key + " -> " + value);
//        });

        /*
         * My Added "Commands"
         */
        commandsList.add(new ParserCommand(
                Collections.singletonList("pee"),
                false,
                false,
                "",
                "Returns a renaming of pee.") {
            @Override
            public String parse(List<GameCharacter> specialNPCs, String command, String arguments, String target, GameCharacter character) {
                return getStringFor("pee");
            }
        });
    }

    public static String getStringFor(String unlocalized) {
        String[] arr = getStringsFor(unlocalized);
        return arr[Util.random.nextInt(arr.length - 1)];
    }
    public static String getStringFor(String fileName, String unlocalized, SexPace sexPace, boolean reversePerspective) {
        String unlocalized2 = fileName + ".";
        unlocalized2 += switch (sexPace) {
            case SUB_RESISTING -> "sub.resist";
            case SUB_NORMAL -> "sub.normal";
            case SUB_EAGER -> "sub.eager";
            case DOM_GENTLE -> "dom.gentle";
            case DOM_NORMAL -> "dom.normal";
            case DOM_ROUGH -> "dom.rough";
        };
        unlocalized2 += "." + unlocalized;
        String[] arr = getStringsFor(unlocalized2, reversePerspective);
        return arr[Util.random.nextInt(arr.length - 1)];
    }

    //"Add these to Translation Util under the existing ones", Thanks Keldon ^w^
    public static String getStringFor(String fileName, String unlocalized, String subType, boolean reversePerspective) {
        String unlocalized2 = fileName + ".";
        unlocalized2 += "." + unlocalized;
        String[] arr = getStringsFor(unlocalized2, reversePerspective);
        return arr[Util.random.nextInt(arr.length - 1)];
    }

    public static String getStringFor(String fileName, String unlocalized, boolean reversePerspective) {
        String unlocalized2 = fileName + "." + unlocalized;
        String[] arr = getStringsFor(unlocalized2, reversePerspective);
        return arr[Util.random.nextInt(arr.length - 1)];
    }

    public static String getStringFor(String unlocalized, boolean reversePerspective) {
        return reversePerspective ? reversePerspective(getStringFor(unlocalized)) : getStringFor(unlocalized);
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    public static String[] getStringsFor(String unlocalized) {
        List<String> translations = new ArrayList<>();
        try {
            File lang = new File(translationFolder, unlocalized.contains(".") ? language + "/" + unlocalized.split("\\.")[0] + ".lang" : language + ".lang");
            BufferedReader reader = new BufferedReader(new FileReader(lang));
            reader.lines().forEach(line -> {
                if (!line.startsWith("#") && line.contains("=")) {
                    if (line.split("=")[0].equals(unlocalized.replaceFirst(unlocalized.split("\\.")[0] + "\\.", ""))) {
                        translations.add(line.split("=")[1]);
                    }
                }
            });
            reader.close();
        } catch (IOException e) { // #Mandatory
            e.printStackTrace();
        }
        return translations.toArray(new String[translations.size()]);
    }

    public static String[] getStringsFor(String unlocalized, boolean reversePerspective) {
        String[] Outgoing = getStringsFor(unlocalized);
        return reversePerspective ? reversePerspectives(Outgoing) : Outgoing;
    }

    public static String reversePerspective(String localized) {
        return localized.replaceAll("npc2\\.", "to_err_is_man").replaceAll("npc\\.", "npc2\\.").replaceAll("to_err_is_man", "npc\\.");
    }

    public static String[] reversePerspectives(String[] localized) {
        for (int i = 0; i < localized.length; i++)
            localized[i] = reversePerspective(localized[i]);
        return localized;
    }

    private static final Pattern AGE_PATTERN = Pattern.compile("age\\(([0-9><=-]+)\\)");


    public static String clean(String dirtyDialogue, List<GameCharacter> npcs) {
        if (swaps.isEmpty() || npcs.isEmpty() || Main.game == null
                || dirtyDialogue.trim().equalsIgnoreCase("false")
                || dirtyDialogue.trim().equalsIgnoreCase("true")
                || dirtyDialogue.trim().endsWith(" does not have any hair!")
                || dirtyDialogue.trim().endsWith("the shady dealer cannot transform her body at will!")
                || dirtyDialogue.trim().endsWith("the shady dealer cannot transform his body at will!"))
            return dirtyDialogue;
        AtomicReference<String> str = new AtomicReference<>();
        /*if (ModProps.hasValue(PropertyValue.kinMode) && dirtyDialogue.contains("-morph")) { Not necessary and potentially dangerous
            System.out.println("Changing -morph to kin");
            System.out.println("Before : " + dirtyDialogue);
            dirtyDialogue = dirtyDialogue.replaceAll("-morphs", "kin").replaceAll("-morph", "kin");
            System.out.println("After : " + dirtyDialogue);
        }*/
        str.set(dirtyDialogue);

        GameCharacter c = npcs.getFirst();
//        System.out.println("NPC list size: " + npcs.size());
//        if (!npcs.isEmpty()) {
//            System.out.println("NPC List: ");
//            npcs.forEach(npc -> System.out.println(npc.getNameIgnoresPlayerKnowledge()));
//            System.out.println("Line: " + dirtyDialogue.trim());
//            System.out.println("");
//        }
        swaps.forEach((needle, replaceWith) -> {
            if (needle.startsWith("PLAYER:") && !npcs.isEmpty()/* && !npcs.getFirst().isPlayer()*/) return;
            if (needle.startsWith("NPC:") && ((!npcs.isEmpty() && npcs.getFirst() != null && npcs.getFirst().isPlayer()) || Main.game.getActiveNPC() == null)) return;
            needle = needle.replace("PLAYER:", "").replace("NPC:", "");
            GameCharacter npc = null, player = null;
            if (Main.game != null) {
                npc = Main.game.getActiveNPC();
                player = Main.game.getPlayer();
            }

            String parseRulesString = needle.contains(").") ? needle.split("\\)\\.")[0] + ")" : "";
            String toReplace = needle.contains(").") ? needle.split("\\)\\.")[1] : needle;
            List<String> parseRules = parseRulesString.contains("&&") ? List.of(parseRulesString.split("&&")) : List.of(parseRulesString);

            String newStr = str.get();
            int targetRules = parseRulesString.isEmpty() ? 0 : parseRules.size(), matchedRules = 0;

            for (String parseRule : parseRules) {
                if (parseRule.isEmpty()) continue;
                String rule = parseRule.substring(parseRule.indexOf("(") + 1, parseRule.indexOf(")"));
                if (parseRule.startsWith("age(")) {
                    if (checkAge(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("playerAge(")) {
                    if (checkAge(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("npcAge(")) {
                    if (checkAge(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("gender(")) {
                    if (checkGender(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcGender(")) {
                    if (checkGender(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerGender(")) {
                    if (checkGender(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("fem(")) {
                    if (checkFeminimity(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcFem(")) {
                    if (checkFeminimity(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerFem(")) {
                    if (checkFeminimity(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("breast(")) {
                    if (checkBreast(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcBreast(")) {
                    if (checkBreast(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerBreast(")) {
                    if (checkBreast(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("age2(")) {
                    if (checkAge2(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcAge2(")) {
                    if (checkAge2(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerAge2(")) {
                    if (checkAge2(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("penis(")) {
                    if (checkPenis(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcPenis(")) {
                    if (checkPenis(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerPenis(")) {
                    if (checkPenis(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("penisGirth(")) {
                    if (checkPenisGirth(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcPenisGirth(")) {
                    if (checkPenisGirth(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerPenisGirth(")) {
                    if (checkPenisGirth(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("testicle(")) {
                    if (checkTesticles(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcTesticle(")) {
                    if (checkTesticles(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerTesticle(")) {
                    if (checkTesticles(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("vCapacity(")) {
                    if (checkVaginalCapacity(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcVCapacity(")) {
                    if (checkVaginalCapacity(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerVCapacity(")) {
                    if (checkVaginalCapacity(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("vDepth(")) {
                    if (checkVaginalDepth(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcVDepth(")) {
                    if (checkVaginalDepth(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerVDepth(")) {
                    if (checkVaginalDepth(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("assCapacity(")) {
                    if (checkAnalCapacity(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcAssCapacity(")) {
                    if (checkAnalCapacity(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerAssCapacity(")) {
                    if (checkAnalCapacity(rule, player))
                        matchedRules++;
                } else if (parseRule.startsWith("assDepth(")) {
                    if (checkAnalDepth(rule, c))
                        matchedRules++;
                } else if (parseRule.startsWith("npcAssDepth(")) {
                    if (checkAnalDepth(rule, npc))
                        matchedRules++;
                } else if (parseRule.startsWith("playerAssDepth(")) {
                    if (checkAnalDepth(rule, player))
                        matchedRules++;
                }
            }

            newStr = newStr.replaceAll(targetRules == matchedRules ? toReplace : needle, getRandomInList(replaceWith));

            str.set(newStr);
        });
        return str.get();
    }

    private static boolean checkGender(String rule, GameCharacter c) {
        if (c == null) return false;
        List<String> flags = Arrays.asList(rule.split("_"));
        int flagCount = flags.size(), flagMatches = 0;
        for (String flag : flags) {
            if (flag.endsWith("F")) {
                if ((flag.startsWith("+") || flag.equals("F")) && c.getFemininityValue() >= 60)
                    flagMatches++;
                if (flag.startsWith("-") && c.getFemininityValue() < 60)
                    flagMatches++;
            } if (flag.endsWith("M")) {
                if ((flag.startsWith("+") || flag.equals("M")) && c.getFemininityValue() < 40)
                    flagMatches++;
                if (flag.startsWith("-") && c.getFemininityValue() >= 40)
                    flagMatches++;
            } if (flag.endsWith("N")) {
                if ((flag.startsWith("+") || flag.equals("N")) && c.getFemininityValue() >= 40 && c.getFemininityValue() < 60)
                    flagMatches++;
                if (flag.startsWith("-") && (c.getFemininityValue() < 40 || c.getFemininityValue() >= 60))
                    flagMatches++;
            } if (flag.endsWith("P")) {
                if ((flag.startsWith("+") || flag.equals("P")) && c.hasPenis())
                    flagMatches++;
                if (flag.startsWith("-") && !c.hasPenis())
                    flagMatches++;
            } if (flag.endsWith("V")) {
                if ((flag.startsWith("+") || flag.equals("V")) && c.hasVagina())
                    flagMatches++;
                if (flag.startsWith("-") && !c.hasVagina())
                    flagMatches++;
            } if (flag.endsWith("B")) {
                if ((flag.startsWith("+") || flag.equals("B")) && c.hasBreasts())
                    flagMatches++;
                if (flag.startsWith("-") && !c.hasBreasts())
                    flagMatches++;
            }
        }
        return flagCount == flagMatches;
    }

    private static boolean checkFeminimity(String rules, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rules, c.getFemininityValue());
    }

    private static boolean checkBreast(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getBreastSize().getMeasurement());
    }

    private static boolean checkVaginalCapacity(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getVaginaRawCapacityValue());
    }
    private static boolean checkVaginalDepth(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getVaginaDepth().getValue());
    }
    private static boolean checkAnalCapacity(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getAssRawCapacityValue());
    }
    private static boolean checkAnalDepth(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getAssDepth().getValue());
    }


    private static boolean checkPenis(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getPenisRawSizeValue());
    }

    private static boolean checkPenisGirth(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getPenisRawGirthValue());
    }
    private static boolean checkTesticles(String rule, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(rule, c.getTesticleSize().getValue());
    }

    public static boolean checkAge(String params, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(params, c.getAgeValue());
    }

    public static boolean checkAge2(String params, GameCharacter c) {
        if (c == null) return false;
        return compareValueUsingString(params, c.getAppearsAsAgeValue());
    }

    private static boolean compareValueUsingString(String comparison, int value) {
        int target1, target2 = 0;
        if (!comparison.contains("-"))
            target1 = Integer.parseInt(comparison.replaceAll("[>=<]", ""));
        else {
            String[] range = comparison.split("-");
            target1 = Integer.parseInt(range[0]);
            target2 = Integer.parseInt(range[1]);
        }

        if (comparison.contains("=")) {
            if (comparison.contains(">") && value >= target1)
                return true;
            else if (comparison.contains("<") && value <= target1)
                return true;
            else return value == target1;
        } else if (comparison.contains(">") && value > target1)
            return true;
        else if (comparison.contains("<") && value < target1)
            return true;
        else if (comparison.contains("-") && value >= target1 && value <= target2)
            return true;
        else return value == target1;
    }

    private static boolean compareValueUsingString(String comparison, float value) {
        float target1, target2 = 0;
        if (!comparison.contains("-"))
            target1 = Float.parseFloat(comparison.replaceAll("[>=<]", ""));
        else {
            String[] range = comparison.split("-");
            target1 = Float.parseFloat(range[0]);
            target2 = Float.parseFloat(range[1]);
        }

        if (comparison.contains("=")) {
            if (comparison.contains(">") && value >= target1)
                return true;
            else if (comparison.contains("<") && value <= target1)
                return true;
            else return value == target1;
        } else if (comparison.contains(">") && value > target1)
            return true;
        else if (comparison.contains("<") && value < target1)
            return true;
        else if (comparison.contains("-") && value >= target1 && value <= target2)
            return true;
        else return value == target1;
    }

    public static String weeabooMode() {
        return Main.game.getPlayer().isFeminine() ? "Onee" : "Onii";
    }

    public static String getRandomInList(ArrayList<String> list) {
        if (list.isEmpty())
            return "LIST EMPTY";
        return list.size() > 1 ? list.get(Util.random.nextInt(list.size())) : list.get(0);
    }

    private static void addSwap(String searchConditions, String searchReplacement) {
        if (swaps.containsKey(searchConditions))
            swaps.get(searchConditions).add(searchReplacement);
        else swaps.put(searchConditions, new ArrayList<>(Collections.singletonList(searchReplacement)));
    }


    private static final ArrayList<String> seenUnparsedLines = new ArrayList<>();

    public static String prehandleParse(List<GameCharacter> specialNPC, String input, boolean xmlParsing, List<ParserTag> tags) {
        // TODO make ability to log and replace/add random
        if (LOG_UNPARSED_LINES) {
            try {
                if (!seenUnparsedLines.contains(input)) {
                    seenUnparsedLines.add(input);
                    BufferedWriter bw = new BufferedWriter(new FileWriter(parsingLinesOut));
                    bw.append(input);
                    bw.newLine();
                    bw.close();
                }
            } catch (IOException ioe) {
                System.err.println("Error in logging preparsed lines = " + ioe.getMessage());
            }
        }

        if (preswaps.containsKey(input)) {
            input = Util.randomItemFrom(preswaps.get(input));
        }
        return input;
    }

    private static String advancedPreParseReplacement(List<GameCharacter> specialNPC, String input, boolean xmlParsing, List<ParserTag> tags, String advancedString) {
        String ageArgument = "";
        boolean NPCFlag = false;
        boolean playerFlag = false;
        if (advancedString.startsWith("NPC:")) {
            NPCFlag = true;
            advancedString = advancedString.replace("NPC:", "");
        }
        if (advancedString.startsWith("PLAYER:")) {
            playerFlag = true;
            advancedString = advancedString.replace("PLAYER:", "");
        }
        if (advancedString.startsWith("age(")) {
            int argLength = getPositionOfNextString(")", input);
            String argument = advancedString.substring(0, argLength);
            advancedString = advancedString.replace(argument, "");
            ageArgument = argument;
        }
        if (NPCFlag && specialNPC.size() == 1 && specialNPC.getFirst() instanceof NPC) {
            if (!ageArgument.isEmpty()) {
                String ageCheck = ageArgument.split("\\(")[1].split("\\)")[0];
                if (checkAge(ageCheck, specialNPC.getFirst())) {

                }
            }
        }
        return null;
    }

    private static int getPositionOfNextString(String needle, String haystack) {
        haystack = haystack.toLowerCase();
        for (int i = 0; i < haystack.length(); i++) {
            String scope = haystack.substring(i, i + needle.length());
            if (scope.equalsIgnoreCase(needle)) {
                return i + needle.length();
            }
        }
        return -1;
    }
}