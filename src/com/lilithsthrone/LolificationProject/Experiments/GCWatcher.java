package com.lilithsthrone.LolificationProject.Experiments;

import com.lilithsthrone.LolificationProject.ModCommon.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GCWatcher implements Runnable {
    Thread t;
    public GCWatcher() {
        t = new Thread(this, "GCWatcher");
        t.start();
    }

    Pattern gcPat = Pattern.compile("GC\\((\\d+)\\) .+ ((\\d+\\.?\\d+)([MGK]))->((\\d+\\.?\\d+)([MGK]))\\(\\d+[MGK]\\) (\\d+\\.\\d+[nmµs]+)");
    long totalµsecondsUsed = 0;
    long totalMBPurged = 0;
    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./jvm_gc_alloc.log"));
        } catch (FileNotFoundException e) {
            System.err.println("GC Watcher error");
        }
        while (t != null) {
            String line;
            try {
                if (br != null && (line = br.readLine()) != null) {
                    if (line.contains("[gc             ]")) {
                        Matcher mat = gcPat.matcher(line);
                        if (mat.find()) {
                            int round = Integer.parseInt(mat.group(1));
                            String beforeStr = mat.group(3), afterStr = mat.group(6);
                            int before = beforeStr.contains(".") ? (int)(Float.parseFloat(beforeStr)) * 1000 : Integer.parseInt(beforeStr);
                            int after = afterStr.contains(".") ? (int)(Float.parseFloat(afterStr)) * 1000 : Integer.parseInt(afterStr);
                            String type = mat.group(4); // K = KB  M = MB  G = GB
                            String time = mat.group(8);
                            float ms = Float.parseFloat(time.replaceAll("[µnms]+", ""));
                            int µs = (int)(ms * 1000);
                            long differenceInMB = switch (type) {
                                case "K" -> (after - before) / 1000;
                                case "M" -> after - before;
                                case "G" -> (after - before) * 1000;
                                default -> after - before;
                            };
                            totalMBPurged += differenceInMB;
                            totalµsecondsUsed += µs;
                            System.out.println("GC Round : " + round + " purged : " + differenceInMB + " MB in " + µs + "µs");
                            Logger.logTime("Total Purge : " + totalMBPurged + " MB using", totalµsecondsUsed * 1000);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("BR Error");
            }
        }
    }
}
