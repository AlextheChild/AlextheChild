package silver.finished;

import java.util.*;
import java.io.*;

public class Convention {
    static int n, m, c;
    static int[] arriveTimes;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("convention.in")));
        String[] nmc = in.readLine().split(" ");
        n = Integer.parseInt(nmc[0]);
        m = Integer.parseInt(nmc[1]);
        c = Integer.parseInt(nmc[2]);
        String[] arriveLine = in.readLine().split(" ");
        arriveTimes = new int[n];
        for (int i = 0; i < n; i++) {
            arriveTimes[i] = Integer.parseInt(arriveLine[i]);
        }
        Arrays.sort(arriveTimes);

        // binary search the max wait times
        int l = 0;
        int r = arriveTimes[n - 1];
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (check(mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        PrintWriter out = new PrintWriter("convention.out");
        out.println(l);
        in.close();
        out.close();
    }

    public static boolean check(int waitTime) {
        // figure out how many busses for waitTime
        int numBusses = 1;

        int firstCow = arriveTimes[0];
        int numCowsInBus = 0;
        for (int i = 0; i < n; i++) {
            if (arriveTimes[i] - firstCow > waitTime || numCowsInBus >= c) {
                numBusses++;
                firstCow = arriveTimes[i];
                numCowsInBus = 0;
            }
            numCowsInBus++;
        }

        return numBusses <= m;
    }
}