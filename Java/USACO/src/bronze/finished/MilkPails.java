package bronze.finished;

import java.io.*;

public class MilkPails {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("pails.in")));
        String[] xym = in.readLine().split(" ");
        int x = Integer.parseInt(xym[0]);
        int y = Integer.parseInt(xym[1]);
        int m = Integer.parseInt(xym[2]);

        // all possible numbers of buckets of y
        int maxFilledAmount = 0;
        for (int i = 0; i <= (int) (m / y); i++) {
            int amountLeft = m - (i * y);
            int numX = (int) (amountLeft / x);
            maxFilledAmount = Math.max(maxFilledAmount, i * y + numX * x);
            if (maxFilledAmount == m) {
                break;
            }
        }

        PrintWriter out = new PrintWriter("pails.out");
        out.println(maxFilledAmount);
        in.close();
        out.close();
    }
}