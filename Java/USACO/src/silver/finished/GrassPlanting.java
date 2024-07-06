package silver.finished;

import java.io.*;

public class GrassPlanting {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("planting.in")));
        int n = Integer.parseInt(in.readLine());

        int[] count = new int[n];

        // get counts
        int highestCount = 0;
        for (int i = 0; i < n - 1; i++) {
            String[] line = in.readLine().split(" ");
            int field1 = Integer.parseInt(line[0]) - 1;
            int field2 = Integer.parseInt(line[1]) - 1;
            count[field1] += 1;
            count[field2] += 1;

            highestCount = Math.max(highestCount, Math.max(count[field1], count[field2]));
        }

        highestCount++;

        PrintWriter out = new PrintWriter("planting.out");
        out.println(highestCount);
        in.close();
        out.close();
    }
}