package bronze.finished;

import java.util.*;
import java.io.*;

public class CowntactTracing2 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] cowLine = in.readLine().split("");

        ArrayList<Integer> sickLines = new ArrayList<>();
        int line = 0;
        for (int i = 0; i < n; i++) {
            if (cowLine[i].equals("1")) {
                line++;
            } else {
                if (line > 0)
                    sickLines.add(line);
                line = 0;
            }
        }
        if (line > 0) {
            sickLines.add(line);
        }

        // edge cases
        int minEdgeCows = Integer.MAX_VALUE;
        int sickStart = 0;
        int sickEnd = sickLines.size() - 1;
        if (cowLine[0].equals("1")) {
            minEdgeCows = Math.min(minEdgeCows, sickLines.get(sickStart));
            sickStart++;
        }
        if (cowLine[n - 1].equals("1")) {
            minEdgeCows = Math.min(minEdgeCows, sickLines.get(sickEnd));
            sickEnd--;
        }

        // get smallest even and odd line lengths
        int[] minBlock = { Integer.MAX_VALUE, Integer.MAX_VALUE };
        for (int i = sickStart; i <= sickEnd; i++) {
            minBlock[sickLines.get(i) % 2] = Math.min(minBlock[sickLines.get(i) % 2], sickLines.get(i));
        }

        int maxLineSize = Math.min(minEdgeCows * 2 - 1, Math.min(minBlock[0] - 1, minBlock[1]));

        int numInfected = 0;
        for (int lineSize : sickLines) {
            // gives you how many cows are in each sick line
            numInfected += (lineSize + maxLineSize - 1) / maxLineSize;
        }
        System.out.println(numInfected);
    }
}