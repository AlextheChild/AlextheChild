package silver.finished;

import java.util.*;
import java.io.*;

public class LemonadeLine {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("lemonade.in")));
        int n = Integer.parseInt(in.readLine());
        Integer[] waitingBehind = new Integer[n];
        int k = 0;
        for (String s : in.readLine().split(" ")) {
            waitingBehind[k] = Integer.parseInt(s);
            k++;
        }

        // sort cows in decreasing order
        Arrays.sort(waitingBehind, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return -Integer.compare(a, b);
            }
        });

        int cowsInLine = 0;
        for (int c : waitingBehind) {
            if (c >= cowsInLine) {
                cowsInLine++;
            } else {
                break;
            }
        }

        PrintWriter out = new PrintWriter("lemonade.out");
        out.println(cowsInLine);
        in.close();
        out.close();
    }
}