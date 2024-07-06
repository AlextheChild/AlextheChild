package bronze.finished;

import java.util.Arrays;
import java.io.*;

public class HaybaleStacking {
    static int[] stacks;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("stacking.in")));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        stacks = new int[n];

        // simulate stacking
        for (int i = 0; i < k; i++) {
            String[] line = in.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            stack(start, end);
        }

        // 0 1 2 3 4

        Arrays.sort(stacks);

        PrintWriter out = new PrintWriter("stacking.out");
        out.println(stacks[n / 2]);
        in.close();
        out.close();
    }

    public static void stack(int start, int end) {
        for (int i = start - 1; i < end; i++) {
            stacks[i] += 1;
        }
    }
}