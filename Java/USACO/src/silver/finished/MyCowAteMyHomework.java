package silver.finished;

import java.util.*;
import java.io.*;

public class MyCowAteMyHomework {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("homework.in")));
        int n = Integer.parseInt(in.readLine());
        String[] scoreLine = in.readLine().split(" ");
        int[] scores = new int[n];
        for (int i = 0; i < n; i++) {
            scores[i] = Integer.parseInt(scoreLine[i]);
        }

        // iterate backwards
        ArrayList<Integer> workingLengths = new ArrayList<Integer>();
        double highestAverage = 0;
        double sum = scores[n - 1];
        double min = scores[n - 1];
        for (int eatenSize = n - 2; eatenSize > 0; eatenSize--) {
            sum += scores[eatenSize];
            min = Math.min(min, scores[eatenSize]);

            double average = (sum - min) / (n - eatenSize - 1);

            if (average >= highestAverage) {
                if (average > highestAverage) {
                    workingLengths.clear();
                    highestAverage = average;
                }
                workingLengths.add(0, eatenSize);
            }
        }

        PrintWriter out = new PrintWriter("homework.out");
        for (int i : workingLengths) {
            out.println(i);
        }
        in.close();
        out.close();
    }
}