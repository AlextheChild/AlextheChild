package bronze.finished;

import java.io.*;

public class FeedingTheCows {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        for (int i = 0; i < t; i++) {
            String[] nk = in.readLine().split(" ");
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            String[] cowLine = in.readLine().split("");

            int numPatches = 0;
            String[] grassLine = new String[n];
            for (int q = 0; q < n; q++) {
                grassLine[q] = ".";
            }

            for (int q = 0; q < n; q++) {
                // grass check
                boolean cowHasGrass = false;
                int bound1 = (q - k < 0 ? 0 : q - k);
                int bound2 = (q + k > n - 1 ? n - 1 : q + k);
                for (int w = bound1; w <= bound2; w++) {
                    if (grassLine[w].equals(cowLine[q])) {
                        cowHasGrass = true;
                        break;
                    }
                }

                // add grass
                if (!cowHasGrass) {
                    int farthestIndex = q + k > n - 1 ? n - 1 : q + k;
                    if (grassLine[farthestIndex].equals(".")) {
                        grassLine[farthestIndex] = cowLine[q];
                    } else {
                        // get dotIndex
                        int lastDotIndex = 0;
                        for (int w = n - 1; w >= 0; w--) {
                            if (grassLine[w].equals(".")) {
                                lastDotIndex = w;
                                break;
                            }
                        }
                        // shift everything to the left
                        for (int w = lastDotIndex; w < farthestIndex; w++) {
                            grassLine[w] = grassLine[w + 1];
                        }
                        grassLine[farthestIndex] = cowLine[q];
                    }

                    numPatches++;
                }
            }

            System.out.println(numPatches);
            for (String s : grassLine) {
                System.out.print(s);
            }
            System.out.println();
        }
    }

    public void shiftLeft(String[] grassLine, int index, String newVal) {
        if (!grassLine[index].equals(".")) {
            String temp = grassLine[index];
            grassLine[index] = grassLine[index + 1];
            shiftLeft(grassLine, index - 1, temp);
        }
    }
}