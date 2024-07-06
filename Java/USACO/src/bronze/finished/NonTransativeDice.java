package bronze.finished;

import java.io.*;

public class NonTransativeDice {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        for (int i = 0; i < t; i++) {
            String[] diceSides = in.readLine().split(" ");
            int[] a = new int[4];
            for (int j = 0; j < 4; j++) {
                a[j] = Integer.parseInt(diceSides[j]);
            }
            int[] b = new int[4];
            for (int j = 4; j < 8; j++) {
                b[j - 4] = Integer.parseInt(diceSides[j]);
            }

            if (nonTransative(a, b)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
    }

    public static boolean beats(int[] a, int[] b) {
        int wins = 0;
        int losses = 0;
        for (int i : a) {
            for (int j : b) {
                if (i > j) {
                    wins++;
                } else if (i < j) {
                    losses++;
                }
            }
        }
        return wins > losses;
    }

    public static boolean nonTransative(int[] a, int[] b) {
        // loop through every single possible c
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                for (int k = 1; k <= 10; k++) {
                    for (int l = 1; l <= 10; l++) {
                        int[] c = { i, j, k, l };
                        if ((beats(a, b) && beats(b, c) && beats(c, a))
                                || (beats(b, a) && beats(c, b) && beats(a, c))) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}