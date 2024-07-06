package bronze.bad;

import java.io.*;

public class BalancingBacteria {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] line = in.readLine().split(" ");
        long[] bacValues = new long[n];
        for (int i = 0; i < n; i++) {
            bacValues[i] = Long.parseLong(line[i]);
        }

        // fix the next farthest bacteria
        int sprays = 0;
        for (int i = 0; i < n; i++) {
            if (bacValues[i] == 0) {
                continue;
            }

            long timesToSpray = -bacValues[i];
            // spray each bacteria that is reached
            for (int j = i; j < n; j++) {
                bacValues[j] += timesToSpray * (1 + j - i);
            }
            sprays += Math.abs(timesToSpray);
        }

        System.out.println(sprays);
    }
}