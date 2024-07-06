package silver.finished;

import java.io.*;

public class MooBuzz {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("moobuzz.in")));
        int n = Integer.parseInt(in.readLine());

        int[] first8 = { 14, 1, 2, 4, 7, 8, 11, 13 };

        int ans = (n - 1) / 8 * 15 + first8[n % 8];

        PrintWriter out = new PrintWriter("moobuzz.out");
        out.println(ans);
        in.close();
        out.close();
    }
}