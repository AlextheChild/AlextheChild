package bronze.bad;

import java.util.*;
import java.io.*;

public class LivestockLineup {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("lineup.in")));
        int numConstraints = Integer.parseInt(in.readLine());

        String[] cows = new String[] { "Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue" };
        System.out.println(cows);

        ArrayList<String> cowOrder = new ArrayList<String>();
        for (int i = 0; i < numConstraints; i++) {
            String[] constraint = in.readLine().split("\\s");

            cowOrder.add(constraint[0]);
            cowOrder.add(constraint[5]);
        }

        PrintWriter out = new PrintWriter("lineup.out");
        for (String s : cowOrder) {
            out.println(s);
        }
        in.close();
        out.close();
    }
}