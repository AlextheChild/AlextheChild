package bronze.finished;

import java.util.*;
import java.io.*;

public class WhyDidTheCowCrossTheRoadIII {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("cowqueue.in")));
        int n = Integer.parseInt(in.readLine());
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            cows[i] = new Cow(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        Arrays.sort(cows, Comparator.comparingInt(c -> c.arrival));

        int time = 0;
        for (Cow cow : cows) {
            if (cow.arrival >= time) {
                time = cow.arrival + cow.duration;
            } else {
                time += cow.duration;
            }
        }

        PrintWriter out = new PrintWriter("cowqueue.out");
        out.println(time);
        in.close();
        out.close();
    }

    static class Cow {
        int arrival;
        int duration;

        public Cow(int arrival, int duration) {
            this.arrival = arrival;
            this.duration = duration;
        }
    }
}