package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.ConfigStuff.Configurations;
import com.lilithsthrone.LolificationProject.Library;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import static com.lilithsthrone.main.Main.VERSION_NUMBER;

public class ErrorStream extends PrintStream {
    private static final ArrayList<String> KNOWN_ISSUES = new ArrayList<>();
    public static int informationLines = 1;
    public static boolean skipNextNL = false;

    public ErrorStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        String str = new String(buf, StandardCharsets.UTF_8); // Enables magic
        if (str.startsWith("|") || str.startsWith("=")) informationLines++;
        if (KNOWN_ISSUES.contains(str) && !str.startsWith("=")) return; // don't break my neat box
        if (str.trim().equalsIgnoreCase("null")) {
            System.err.println("Tried to print only 'null' throwing a new stack trace to find it.");
            NullPointerException nurupo = new NullPointerException();
            nurupo.printStackTrace();
            return;
        }
        if (!str.equals(System.lineSeparator()))
            KNOWN_ISSUES.add(str);
        if (str.contains("WARNING: Unsupported JavaFX configuration")) return; // HAHA We don't care!
        super.write(buf, off, len);// The important line.
    }

    public void printData() {
        System.err.print("=".repeat(64) + System.lineSeparator());
        printInformationLine("Game Version : " + VERSION_NUMBER + Library.VERSION/* + " -" + SUB_TEXT*/);
        printInformationLine("Java : " + System.getProperty("java.version") + " [" + System.getProperty("sun.arch.data.model") + "-bit] (" + System.getProperty("java.vendor") + ")");
        printInformationLine("OS : " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")");
        if (Configurations.EXPERIMENTAL) printInformationLine("Experimental Mode is Enabled");
        if (Configurations.DEVELOPER_MODE) printInformationLine("Developer Mode is Enabled");
        if (new File("res/mods").exists()) {
            StringBuilder mods = new StringBuilder();
            int i = 0;
            for (File f : Objects.requireNonNull(new File("res/mods").listFiles())) {
                if (f.isDirectory()) {
                    if (i > 0) {
                        mods.append(", ");
                    }
                    mods.append(f.getName());
                }
                i++;
            }
            String info = "Mod folders present: " + mods;
            if (info.length() > 60) {
                String[] infArr = mods.toString().split(", ");
                StringBuilder tInfo = new StringBuilder();
                printInformationLine("Mod folders present");
                for (int j = 0; j < infArr.length; j++) {
                    if ((tInfo + (j == 0 ? "" : ", ") + infArr[j]).length() < 60) {
                        tInfo.append(j == 0 ? "" : ", ").append(infArr[j]);
                    } else {
                        printInformationLine(tInfo.toString());
                        tInfo.setLength(0);
                    }
                }
                printInformationLine(tInfo.toString());
            } else {
                printInformationLine(info);
            }
        }
        printInformationLine("Send this log in the ATF thread or Loliphile's DMs");
        System.err.print("=".repeat(64) + System.lineSeparator());
        Configurations.ERR_LOG_INFO_LENGTH = informationLines;
        Configurations.saveConfiguration();
    }

    public void printInformationLine(String information) {
        int l = 62 - information.length();
        System.err.print("|" + " ".repeat((int) (l / 2f)) + information + " ".repeat(Math.round(l / 2f)) + "|" + System.lineSeparator());
    }

    public static long getLogLengthSafe(File errorLog) {
        long len = 0;
        if (!errorLog.exists()) return len;
        try { len = getLogLength(errorLog); } catch (IOException ignored) { }
        return len;
    }

    private static long getLogLength(File errorLog) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(errorLog));
        long length = br.lines().count();
        br.close();
        return length;
    }
}