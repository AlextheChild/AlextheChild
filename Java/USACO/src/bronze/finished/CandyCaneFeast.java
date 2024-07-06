package bronze.finished;

import java.io.*;

public class CandyCaneFeast {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        String[] heightsStrings = in.readLine().split(" ");
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) {
            heights[i] = Long.parseLong(heightsStrings[i]);
        }
        String[] candyStrings = in.readLine().split(" ");

        for (String s : candyStrings) {
            long startingHeight = Long.parseLong(s);
            long currentHeight = startingHeight;

            for (int i = 0; i < n; i++) {
                long cowHeight = heights[i];
                long heightFromGround = startingHeight - currentHeight;

                // cow eats from cane how much overlap there is with her height
                long eatenAmount = Math.min(Math.max(cowHeight - heightFromGround, 0), currentHeight);

                if (eatenAmount > 0) {
                    currentHeight -= eatenAmount;
                    heights[i] += eatenAmount;
                }

                if (currentHeight == 0) {
                    break;
                }
            }
        }

        for (long l : heights) {
            System.out.println(l);
        }
    }
}