package bronze.finished;

import java.util.*;
import java.io.*;

public class CowGymnastics {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("gymnastics.in")));

        // nums
        String[] firstLine = in.readLine().split(" ");
        int numRankings = Integer.parseInt(firstLine[0]);
        int numCows = Integer.parseInt(firstLine[1]);

        // all rankings
        int[][] rankings = new int[numRankings][numCows];
        for (int i = 0; i < numRankings; i++) {
            String[] line = in.readLine().split(" ");
            for (int q = 0; q < numCows; q++) {
                rankings[i][q] = Integer.parseInt(line[q]);
            }
        }

        int numConsistentCows = 0;
        // loop through every cow
        for (int currentCowNumber = 1; currentCowNumber <= numCows; currentCowNumber++) {
            HashSet<Integer> consistentCows = new HashSet<Integer>();

            // loop through every ranking
            for (int currentRankingNumber = 0; currentRankingNumber < numRankings; currentRankingNumber++) {
                // get the cow's index
                int currentCowIndex = 0;
                for (int i = 0; i < rankings[currentRankingNumber].length; i++) {
                    if (rankings[currentRankingNumber][i] == currentCowNumber) {
                        currentCowIndex = i;
                        break;
                    }
                }

                // get initial hashset
                if (currentRankingNumber == 0) {
                    for (int i = currentCowIndex + 1; i < numCows; i++) {
                        consistentCows.add(rankings[0][i]);
                    }
                    continue;
                }

                HashSet<Integer> moreConsistentCows = new HashSet<Integer>();
                for (int i = currentCowIndex + 1; i < numCows; i++) {
                    if (consistentCows.contains(rankings[currentRankingNumber][i])) {
                        moreConsistentCows.add(rankings[currentRankingNumber][i]);
                    }
                }
                consistentCows = moreConsistentCows;
            }
            numConsistentCows += consistentCows.size();
        }

        PrintWriter out = new PrintWriter("gymnastics.out");
        out.println(numConsistentCows);
        in.close();
        out.close();
    }
}