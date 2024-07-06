package bronze.finished;

import java.util.*;
import java.io.*;

public class Hoofball {
    public static int n;
    public static int[] cows;
    public static int[] recieving;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("hoofball.in")));
        n = Integer.parseInt(in.readLine());
        String[] cowLine = in.readLine().split(" ");
        cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(cowLine[i]);
        }
        Arrays.sort(cows);
        recieving = new int[n];
        for (int i = 0; i < n; i++) {
            recieving[getReciever(i)]++;
        }

        int numBalls = 0;
        for (int i = 0; i < n; i++) {
            if (recieving[i] == 0) {
                numBalls++;
            }

            if (isSink(i)) {
                numBalls++;
            }
        }

        PrintWriter out = new PrintWriter("hoofball.out");
        out.println(numBalls);
        in.close();
        out.close();
    }

    public static boolean isSink(int index) {
        return getReciever(getReciever(index)) == index && index < getReciever(index)
                && recieving[index] == 1 && recieving[getReciever(index)] == 1;
    }

    public static int getReciever(int index) {
        if (index > 0 && index < n - 1) {
            return cows[index] - cows[index - 1] <= cows[index + 1] - cows[index] ? index - 1 : index + 1;
        } else if (index == 0) {
            return 1;
        } else if (index == n - 1) {
            return n - 2;
        }
        return -1;
    }
}