package bronze.finished;

import java.util.*;
import java.io.*;

public class Lifeguards {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("lifeguards.in")));
        int n = Integer.parseInt(in.readLine());
        Lifeguard[] guards = new Lifeguard[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            guards[i] = new Lifeguard(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        Arrays.sort(guards, Comparator.comparingInt(c -> c.startTime));

        int maxTime = 0;
        // loop through and skip every guard
        for (int i = 0; i < n; i++) {
            int time = 0;
            int lastEndTime = -1;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                time += guards[j].endTime - Math.max(lastEndTime, guards[j].startTime);
                lastEndTime = guards[j].endTime;
            }

            maxTime = Math.max(time, maxTime);
        }

        PrintWriter out = new PrintWriter("lifeguards.out");
        out.println(maxTime);
        in.close();
        out.close();
    }

    static class Lifeguard {
        int startTime, endTime;

        public Lifeguard(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
