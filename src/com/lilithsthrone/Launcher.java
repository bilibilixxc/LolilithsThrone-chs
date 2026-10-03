package com.lilithsthrone;

import com.lilithsthrone.LolificationProject.ConfigStuff.ConfigurationManager;
import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.Experiments.GCWatcher;
import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.main.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.lilithsthrone.LolificationProject.ConfigStuff.Configurations.CHECK_FOR_UPDATES;

/**
 * If JavaFX isn't part of the java package but rather is added separately, you need to wrap the main method in a launcher method, otherwise it will fail.
 * 
 * @since 0.4.6.6
 * @version 0.4.6.6
 * @author Tazze
 */
public class Launcher {
    public static long LaunchTimer = System.nanoTime();
    public static void main(String[] args) {
        ConfigurationManager.loadConfig();
        if (!Configurations.DEGEN_MODE && (new File("./data/properties.xml")).exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("./data/properties.xml"));
                boolean degenMode = false;
                Pattern pat = Pattern.compile("minAge value=\"(\\d+)\"");
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().startsWith("<minAge ")) {
                        Matcher m = pat.matcher(line);
                        if (m.find()) {
                            int minAge = Integer.valueOf(m.group(1));
                            degenMode = minAge < 18;
                            System.out.println("Setting DEGEN_MODE to TRUE");
                        }
                        break;
                    }
                }
                Configurations.DEGEN_MODE = degenMode;
                Configurations.AUTOMATIC_DEGEN_MODE = false;
            } catch (Exception ignored) {}
        }
        ConfigurationManager.saveConfig(Configurations.class, new File(ConfigurationManager.configFolder, Configurations.class.getSimpleName() + ".cfg"));
        if (CHECK_FOR_UPDATES) {
            Library.checkForUpdate();
        }
        if ((new File("./jvm_gc_alloc.log")).exists())
            new GCWatcher();
        Main.main(args);
    }
}
