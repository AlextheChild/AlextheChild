package bronze.finished;

import java.util.*;
import java.io.*;

public class MajorityOpinion {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            String[] likeLine = in.readLine().split(" ");
            int[] likes = new int[n];
            for (int i = 0; i < n; i++) {
                likes[i] = Integer.parseInt(likeLine[i]);
            }

            if (n <= 2) {
                if (likes[0] == likes[1]) {
                    System.out.println(likes[0]);
                } else {
                    System.out.println("-1");
                }
                continue;
            }

            TreeSet<Integer> majorityCows = new TreeSet<Integer>();
            // loop through all groups of 3 cows
            for (int i = 0; i < n - 2; i++) {
                int[] line = { likes[i], likes[i + 1], likes[i + 2] };
                if (hasMajority(line) != -1) {
                    majorityCows.add(hasMajority(line));
                }
            }

            if (majorityCows.size() > 0) {
                String line = "";
                for (int i : majorityCows) {
                    line += i + " ";
                }
                System.out.println(line.substring(0, line.length() - 1));
            } else {
                System.out.println("-1");
            }

        }
    }

    public static int hasMajority(int[] line) {
        if (line[0] == line[1]) {
            return line[0];
        } else if (line[1] == line[2]) {
            return line[1];
        } else if (line[0] == line[2]) {
            return line[0];
        }

        return -1;
    }
}