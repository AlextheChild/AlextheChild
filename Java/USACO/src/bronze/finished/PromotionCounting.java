package bronze.finished;

import java.io.*;

public class PromotionCounting {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("promote.in")));
        int[] beforeNums = new int[4];
        int[] afterNums = new int[4];
        for (int i = 0; i < 4; i++) {
            String[] line = in.readLine().split(" ");

            beforeNums[i] = Integer.parseInt(line[0]);
            afterNums[i] = Integer.parseInt(line[1]);
        }

        int platinumDifference = afterNums[3] - beforeNums[3];
        int goldDifference = afterNums[2] - beforeNums[2] + platinumDifference;
        int silverDifference = afterNums[1] - beforeNums[1] + goldDifference;

        PrintWriter out = new PrintWriter("promote.out");
        out.println(silverDifference);
        out.println(goldDifference);
        out.println(platinumDifference);
        in.close();
        out.close();
    }
}