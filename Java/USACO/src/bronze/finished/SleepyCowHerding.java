package bronze.finished;

import java.util.*;
import java.io.*;

public class SleepyCowHerding {
    static int minMoves = Integer.MAX_VALUE;
    static int maxMoves = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("herding.in")));
        String[] sCowPositions = in.readLine().split(" ");

        int[] cowPositions = new int[3];
        for (int i = 0; i < 3; i++) {
            cowPositions[i] = Integer.parseInt(sCowPositions[i]);
        }
        Arrays.sort(cowPositions);

        // minMoves
        if (cowPositions[0] + 1 == cowPositions[1] && cowPositions[1] + 1 == cowPositions[2]) {
            minMoves = 0;
        } else if (cowPositions[0] + 2 == cowPositions[1] || cowPositions[1] + 2 == cowPositions[2]) {
            minMoves = 1;
        } else {
            minMoves = 2;
        }

        // maxMoves(take the ones closest together and leapfrog)
        maxMoves = cowPositions[2] - cowPositions[1] > cowPositions[1] - cowPositions[0]
                ? cowPositions[2] - cowPositions[1] - 1
                : cowPositions[1] - cowPositions[0] - 1;

        PrintWriter out = new PrintWriter("herding.out");
        out.println(minMoves);
        out.println(maxMoves);
        in.close();
        out.close();
    }
}