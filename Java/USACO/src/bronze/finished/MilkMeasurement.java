package bronze.finished;

import java.util.*;
import java.io.*;

public class MilkMeasurement {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("measurement.in")));
        int n = Integer.parseInt(in.readLine());
        TreeMap<Integer, Integer> dayLineMap = new TreeMap<Integer, Integer>();
        String[] cows = new String[n];
        int[] changes = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            dayLineMap.put(Integer.parseInt(line[0]) - 1, i);
            cows[i] = line[1];
            changes[i] = Integer.parseInt(line[2]);
        }

        int b = 7, e = 7, m = 7;
        String best = "bem";
        int timesChanged = 0;
        // simulation
        for (int i : dayLineMap.keySet()) {
            int currentLine = dayLineMap.get(i);
            if (cows[currentLine].equals("Bessie")) {
                b += changes[currentLine];
            }
            if (cows[currentLine].equals("Elsie")) {
                e += changes[currentLine];
            }
            if (cows[currentLine].equals("Mildred")) {
                m += changes[currentLine];
            }

            int biggest = Math.max(b, Math.max(e, m));
            String newBest = "";
            if (b == biggest) {
                newBest += "b";
            }
            if (e == biggest) {
                newBest += "e";
            }
            if (m == biggest) {
                newBest += "m";
            }
            if (!best.equals(newBest)) {
                timesChanged++;
            }
            best = newBest;
        }

        PrintWriter out = new PrintWriter("measurement.out");
        out.println(timesChanged);
        in.close();
        out.close();
    }
}