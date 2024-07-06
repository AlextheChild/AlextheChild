package silver.finished;

import java.util.*;
import java.io.*;

public class AngryCows {
    static int n, k;
    static int[] hayPositions;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("angry.in")));
        String[] nk = in.readLine().split(" ");
        n = Integer.parseInt(nk[0]);
        k = Integer.parseInt(nk[1]);
        hayPositions = new int[n];
        for (int i = 0; i < n; i++) {
            hayPositions[i] = Integer.parseInt(in.readLine());
        }
        Arrays.sort(hayPositions);

        // binary search for power
        int l = 1;
        int r = hayPositions[n - 1];
        int minPower = -1;
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (check(mid)) {
                minPower = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        PrintWriter out = new PrintWriter("angry.out");
        out.println(minPower);
        in.close();
        out.close();
    }

    public static boolean check(int power) {
        int numCowsNeeded = 0;

        // shoot as far right as possible
        int coveredIndex = 0;
        for (int i = 0; i < n; i++) {
            if (coveredIndex > hayPositions[i]) {
                continue;
            } else {
                coveredIndex = hayPositions[i] + 2 * power + 1;
                numCowsNeeded++;
            }
        }

        return numCowsNeeded <= k;
    }
}