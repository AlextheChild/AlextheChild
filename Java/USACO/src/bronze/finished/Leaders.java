package bronze.finished;

import java.io.*;

public class Leaders {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        String[] cowLine = in.readLine().split("");
        String[] lists = in.readLine().split(" ");
        int[] listLine = new int[n];
        for (int i = 0; i < n; i++) {
            listLine[i] = Integer.parseInt(lists[i]) - 1;
        }

        // get index of first and last guernsey and first and last holstein O(n)
        int firstGuernsey = 0;
        int lastGuernsey = 0;
        boolean guernseyFound = false;
        int firstHolstein = 0;
        int lastHolstein = 0;
        boolean holsteinFound = false;
        for (int i = 0; i < n; i++) {
            if (cowLine[i].equals("G")) {
                lastGuernsey = i;
                if (!guernseyFound) {
                    firstGuernsey = i;
                    guernseyFound = true;
                }
            }

            if (cowLine[i].equals("H")) {
                lastHolstein = i;
                if (!holsteinFound) {
                    firstHolstein = i;
                    holsteinFound = true;
                }
            }
        }

        int numPairs = 0;
        // if earliest guernsey is leader
        if (listLine[firstGuernsey] >= lastGuernsey) {
            for (int i = 0; i < firstGuernsey; i++) {
                if (i == firstHolstein) {
                    continue;
                }

                if (listLine[i] >= firstGuernsey) {
                    numPairs++;
                }
            }
        }
        // if earliest holstein is leader
        if (listLine[firstHolstein] >= lastHolstein) {
            for (int i = 0; i < firstHolstein; i++) {
                if (i == firstGuernsey) {
                    continue;
                }

                if (listLine[i] >= firstHolstein) {
                    numPairs++;
                }
            }
        }

        // add special case
        if (((firstGuernsey <= firstHolstein && listLine[firstGuernsey] >= firstHolstein)
                || listLine[firstGuernsey] >= lastGuernsey)
                &&
                ((firstHolstein <= firstGuernsey && listLine[firstHolstein] >= firstGuernsey)
                        || listLine[firstHolstein] >= lastHolstein)) {
            numPairs++;
        }

        System.out.println(numPairs);
    }
}