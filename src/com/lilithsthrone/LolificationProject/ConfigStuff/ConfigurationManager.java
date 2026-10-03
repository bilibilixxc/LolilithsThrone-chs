package com.lilithsthrone.LolificationProject.ConfigStuff;

import com.lilithsthrone.LolificationProject.ModCommon.VirginityMenu;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigurationManager {
    public static BufferedWriter configurationFile;
    public static final File configFolder = new File(System.getProperty("user.home"), "Lilith's Throne");
    private static final List<Class<?>> configClasses = List.of(Configurations.class);
    static {
        if(!configFolder.exists()) configFolder.mkdirs();
    }

    public static void writeCustomConfig(String fileName, List<String> values) {
        try {
            FileWriter fw = new FileWriter(new File(configFolder, fileName));
            for (String value : values) {
                fw.append(value);
                fw.append(System.lineSeparator());
            }
            fw.close();
        } catch (Exception ignored) {}
    }

    public static ArrayList<String> readCustomConfig(String fileName) {
        ArrayList<String> values = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(configFolder, fileName)));
            String value;
            while ((value = br.readLine()) != null) {
                values.add(value.trim());
            }
            br.close();
        } catch (Exception ignored) {}
        return values;
    }

    private static void saveOptions(Class<?> clazz) throws Exception {
        for (Field f : clazz.getFields()) {
            if (f.isAnnotationPresent(Setting.class)) {
                f.setAccessible(true);
                Setting s = f.getAnnotation(Setting.class);
                if (!s.comment().isEmpty())
                    writeComment(s.comment());
                String settingName = f.getName();
                String settingValue = String.valueOf(f.get(clazz));
                String settingType = f.getType().getSimpleName();
                writeConfig(settingType, settingName, settingValue);
            }
        }
    }

    public static void saveConfig(Class<?> clazz, File configFile) {
        try {
            openConfig(configFile);
            saveOptions(clazz);
            closeConfig();
        } catch (Exception e) {
            System.err.println("Failed to save config!");
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            for (Class<?> clazz : configClasses) {
                File configFile = new File(configFolder, clazz.getSimpleName() + ".cfg");
                if (!configFile.exists())
                    saveConfig(clazz, configFile);
                readConfig(clazz, configFile);
            }
            VirginityMenu.loadVirginities();
        } catch (Exception e) {
            System.err.println("Failed to load config!");
            e.printStackTrace();
        }
    }

    private static void openConfig(File configFile) throws IOException {
        if (!configFile.exists()) configFile.getParentFile().mkdirs();
        configurationFile = new BufferedWriter(new FileWriter(configFile));
    }

    private static void writeComment(String comment) throws IOException {
        configurationFile.append("# ").append(comment);
        configurationFile.newLine();
    }

    private static void writeConfig(String settingType, String settingName, String settingValue) throws IOException {
        configurationFile.append(settingType).append(" - ").append(settingName).append(" = ").append(settingValue);
        configurationFile.newLine();
        configurationFile.newLine();
    }

    private static void closeConfig() throws Exception {
        configurationFile.close();
    }

    private static void readConfig(Class<?> cfgClazz, File configFile) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(configFile));
        reader.lines().forEach(line -> {
            if (line.startsWith("#") || line.trim().isEmpty()) return;
            String settingName, settingValue, settingType;
            settingType = line.split("-")[0].trim();
            settingName = line.substring(line.indexOf(" - ") + 3, line.indexOf(" = "));
            settingValue = line.substring(line.indexOf(" = ") + 3);
            Class<?> clazz = getType(settingType);
            try {
                Method m = clazz.getDeclaredMethod("valueOf", String.class);
                Object o = m.invoke(cfgClazz, settingValue);
                Configurations.class.getDeclaredField(settingName.trim()).set(clazz, o);
            } catch (Exception ignored) {}
        });
        reader.close();
    }

    private static Class<?> getType(String name) {
        return switch (name) {
            case "boolean" -> Boolean.class;
            case "int" -> Integer.class;
            case "short" -> Short.class;
            case "long" -> Long.class;
            case "byte" -> Byte.class;
            case "float" -> Float.class;
            case "double" -> Double.class;
            case "String" -> String.class;
            case "char" -> Character.class;
            default -> Object.class;
        };
    }
}