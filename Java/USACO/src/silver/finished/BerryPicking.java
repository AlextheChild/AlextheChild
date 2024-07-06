package silver.finished;

import java.util.*;
import java.io.*;

public class BerryPicking {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("berries.in")));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        String[] berries = in.readLine().split(" ");
        int[] numBerries = new int[n];
        int maxBerries = 0;
        for (int i = 0; i < n; i++) {
            numBerries[i] = Integer.parseInt(berries[i]);
            maxBerries = Math.max(maxBerries, numBerries[i]);
        }

        // loop through all possible max berries for Elsie
        int ans = 0;
        for (int i = 1; i <= maxBerries; i++) {
            int full = 0;
            int[] left = new int[n];

            for (int j = 0; j < n; j++) {
                full += numBerries[j] / i;
                left[j] = numBerries[j] % i;
            }

            int bessie = 0;
            // all baskets fillable
            if (full >= k) {
                bessie = i * (k / 2);
            }
            // elsie baskets filled
            else if (full > k / 2) {
                // add the rest of the filled
                int remainingFull = full - (k / 2);
                bessie += remainingFull * i;

                // add the rest of the largest leftovers
                Arrays.sort(left);
                int leftI = n - 1;
                for (int j = full - (k / 2); j < k / 2; j++) {
                    bessie += left[leftI];
                    leftI--;

                    if (leftI < 0) {
                        break;
                    }
                }
            }

            ans = Math.max(ans, bessie);
        }

        PrintWriter out = new PrintWriter("berries.out");
        out.println(ans);
        in.close();
        out.close();
    }
}