package silver.finished;

import java.util.*;
import java.io.*;

public class CowDanceShow {
    static int n, t;
    static int[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("cowdance.in")));
        String[] nt = in.readLine().split(" ");
        n = Integer.parseInt(nt[0]);
        t = Integer.parseInt(nt[1]);
        times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(in.readLine());
        }

        int l = 0;
        int r = n;
        int stageSize = -1;
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (check(mid)) {
                stageSize = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        PrintWriter out = new PrintWriter("cowdance.out");
        out.println(stageSize);
        in.close();
        out.close();
    }

    public static boolean check(int size) {
        PriorityQueue<Integer> currentDancing = new PriorityQueue<Integer>();

        // get the last cows that are dancing
        for (int i = 0; i < n; i++) {
            if (currentDancing.size() < size) {
                currentDancing.add(times[i]);
            } else {
                currentDancing.add(currentDancing.remove() + times[i]);
            }
        }

        int biggestTime = 0;
        while (!currentDancing.isEmpty()) {
            biggestTime = currentDancing.remove();
        }

        return biggestTime <= t;
    }
}