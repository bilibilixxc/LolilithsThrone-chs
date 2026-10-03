package com.lilithsthrone.LolificationProject.Magic;

import com.lilithsthrone.main.Main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModLoader {
    public static void loadMods() throws IOException, ClassNotFoundException, NoSuchMethodException {
        File modsDir = new File("./data/mods");
        if (!modsDir.exists()) { modsDir.mkdirs(); }
        for (File file : modsDir.listFiles()) {
            if (file.isDirectory()) {
                findAndLoadJar(file);
            }
            if (file.getName().endsWith(".jar")) {
                load(file);
            }
        }
    }

    public static void findAndLoadJar(File directory) throws IOException, ClassNotFoundException, NoSuchMethodException {
        for (File file : directory.listFiles()) {
            if (file.getName().endsWith(".jar")) {
                load(file);
            }
        }
    }

    public static void load(File pathToJar) throws IOException, ClassNotFoundException, NoSuchMethodException {
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> jar_enumerator = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
        URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);

        while (jar_enumerator.hasMoreElements()) {
            JarEntry jarEntry = jar_enumerator.nextElement();
            if(jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")){
                continue;
            }

            String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);// .class is 6 long
            className = className.replace('/', '.');
            Class clazz = urlClassLoader.loadClass(className);
            if (clazz.isAnnotationPresent(ModInfo.class)) {
                ModInfo info = (ModInfo) clazz.getAnnotation(ModInfo.class);
                String mod_author = info.mod_author();
                String mod_version = info.mod_version();
                String mod_description = info.mod_description();
                String mod_name = info.mod_name();
                String mod_url = info.mod_url();
                String mod_license = info.mod_license();
                System.out.println("Discovered mod: " + mod_name + " version : " + mod_version + " by : " + mod_author + " with description : " + mod_description);
                try {
                    System.out.println("Looking for init");
                    Method initMethod = clazz.getDeclaredMethod("init");
                    initMethod.setAccessible(true);
                    System.out.println("Calling Init");
                    initMethod.invoke(Main.instance);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    System.err.println("Failed to call init method: " + e.getMessage());
                    System.out.println("Failed to call init method! Check Error Log for More Information");
                }
            }
        }
    }
}