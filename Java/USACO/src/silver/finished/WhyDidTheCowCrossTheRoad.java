package silver.finished;

import java.util.*;
import java.io.*;

public class WhyDidTheCowCrossTheRoad {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("helpcross.in")));
        String[] cn = in.readLine().split(" ");
        int c = Integer.parseInt(cn[0]);
        int n = Integer.parseInt(cn[1]);
        int[] chickens = new int[c];
        for (int i = 0; i < c; i++) {
            chickens[i] = Integer.parseInt(in.readLine());
        }
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            cows[i] = new Cow(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        Arrays.sort(chickens);
        Arrays.sort(cows);

        // be able to track which cows have been used
        // be able to pair chicken with eligible cow that ends earliest
        PriorityQueue<Integer> goodCowEndings = new PriorityQueue<Integer>();
        int nextCow = 0;
        int numPairs = 0;
        for (int chicken : chickens) {
            while (nextCow < n && cows[nextCow].start <= chicken) {
                goodCowEndings.add(cows[nextCow].end);
                nextCow++;
            }

            while (!goodCowEndings.isEmpty() && goodCowEndings.peek() < chicken) {
                goodCowEndings.remove();
            }

            if (!goodCowEndings.isEmpty()) {
                numPairs++;
                goodCowEndings.remove();
            }
        }

        PrintWriter out = new PrintWriter("helpcross.out");
        out.println(numPairs);
        in.close();
        out.close();
    }

    static class Cow implements Comparable<Cow> {
        int start, end;

        public Cow(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Cow o) {
            if (this.start == o.start) {
                return Integer.compare(this.end, o.end);
            }
            return Integer.compare(this.start, o.start);
        }
    }
}