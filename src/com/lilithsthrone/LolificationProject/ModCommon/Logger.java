package com.lilithsthrone.LolificationProject.ModCommon;

import com.lilithsthrone.LolificationProject.Library;
import com.lilithsthrone.main.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    public static boolean DEBUG_MODE = false;
    static FileWriter heightLogger = null, testLogger = null, timeLogger = null, virginLogger = null;
    static final File testLog = new File("./data/test.log"), timeLog = new File("./data/time_test.log"),
            heightLog = new File("./data/height_calcs.log"), virginLog = new File("./data/virgin_calcs.log");


    public static void logDebug() {

    }

    public static void logTest(String output) {
        try {
            if (testLogger == null)
                testLogger = new FileWriter(testLog);
            testLogger.append(output);
            testLogger.append(System.lineSeparator());
            testLogger.flush();
        } catch (IOException e) {
            System.err.println("Issue Logging Test.");
            e.printStackTrace();
        }
    }

    public static void logInfo() {

    }

    public static void logWarning() {

    }

    /**
     * This logs time in a coloured, and truncated format
     * @param name The prefix of the time log
     * @param nanoTime The time delta from (System.nanoTime() - startTime) of which should be System.nanoTime()
     * */
    public static void logTime(String name, long nanoTime) {
        if (nanoTime < 1000L) {// Cyan Number Colour [Great]
            System.out.println("\u001B[32m" + name + ": \u001B[36m" + nanoTime + "\u001B[32m ns");
            logTimeToFile(name + ": " + nanoTime + " ns");
            return;
        }
        if (nanoTime < 100_000L) {// Yellow Number [Acceptable]
            float micros = nanoTime / 1000f;
            String nT = String.format("%.1f", micros);
            System.out.println("\u001B[32m" + name + ": \u001B[33m" + nT + "\u001B[32m µs");
            logTimeToFile(name + ": " + nT + " µs");
            return;
        }
        float millis = nanoTime / 1000000f;// Red Number [Concerning]
        String mT = String.format("%.1f", millis);
        System.out.println("\u001B[32m" + name + ": \u001B[31m" + mT + "\u001B[32m ms");
        logTimeToFile(name + ": " + mT + " ms");
    }

    private static void logTimeToFile(String s) {
        try {
            if (timeLogger == null) {
                timeLogger = new FileWriter(timeLog);
            }
            timeLogger.append(s);
            timeLogger.append(System.lineSeparator());
            timeLogger.flush();
        } catch (Exception ignored) {}
    }

    static void logHeightCalc(String str) {
        try {
            if (heightLogger == null) {
                heightLogger = new FileWriter(heightLog);
                heightLogger.append("Height Calculation Log [Version " + Main.VERSION_NUMBER + Library.VERSION + "]");
                heightLogger.append(System.lineSeparator());
            }
            heightLogger.append(str);
            heightLogger.append(System.lineSeparator());
            heightLogger.flush();
        } catch (Exception ignored) {}
    }

    static void logVirginCalc(String str) {
        try {
            if (virginLogger == null) {
                virginLogger = new FileWriter(virginLog);
                virginLogger.append("Virginity Calculation Log [Version " + Main.VERSION_NUMBER + Library.VERSION + "]");
                virginLogger.append(System.lineSeparator());
            }
            virginLogger.append(str);
            virginLogger.append(System.lineSeparator());
            virginLogger.flush();
        } catch (Exception ignored) {}
    }
}
