package bronze.finished;

import java.util.Arrays;
import java.io.*;

public class CowCollege {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] maxTuitionString = in.readLine().split(" ");
        long[] cowTuitionLimits = new long[n];
        for (int i = 0; i < n; i++) {
            cowTuitionLimits[i] = Integer.parseInt(maxTuitionString[i]);
        }
        Arrays.sort(cowTuitionLimits);

        // loop through every max tuition
        long maxProfit = 0, bestTuition = 0;
        for (int i = 0; i < n; i++) {
            long tuition = cowTuitionLimits[i];

            long profit = tuition * (n - i);

            if (profit > maxProfit) {
                maxProfit = profit;
                bestTuition = tuition;
            }
        }

        System.out.println(maxProfit + " " + bestTuition);
    }
}