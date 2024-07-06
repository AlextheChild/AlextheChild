package bronze.finished;

import java.io.*;

public class SleepyCowSorting {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("sleepy.in")));
        int numCows = Integer.parseInt(in.readLine());
        String[] stringCowNums = in.readLine().split(" ");
        int[] cows = new int[numCows];
        for (int i = 0; i < numCows; i++) {
            cows[i] = Integer.parseInt(stringCowNums[i]);
        }

        int lastCow = cows[numCows - 1];
        int numOrderedCowsFromBack = 1;
        for (int i = numCows - 2; i >= 0; i--) {
            if (cows[i] < lastCow) {
                numOrderedCowsFromBack++;
                lastCow = cows[i];
            } else {
                break;
            }
        }

        PrintWriter out = new PrintWriter("sleepy.out");
        out.println(numCows - numOrderedCowsFromBack);
        in.close();
        out.close();
    }
}