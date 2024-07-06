package bronze.finished;

import java.io.*;

public class ShellGame {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("shell.in")));
        int numSwaps = Integer.parseInt(in.readLine());
        int[][] swaps = new int[numSwaps][3];
        for (int i = 0; i < numSwaps; i++) {
            String[] line = in.readLine().split(" ");
            for (int q = 0; q < 3; q++) {
                swaps[i][q] = Integer.parseInt(line[q]);
            }
        }

        // for every shell starting position, see where the shell is, and then where she
        // guesses
        int maxScore = 0;
        for (int shellThePebbleIsUnder = 1; shellThePebbleIsUnder <= 3; shellThePebbleIsUnder++) {
            // 1, 2, 3
            int[] orderOfShells = new int[3];
            for (int i = 0; i < 3; i++) {
                orderOfShells[i] = i + 1;
            }

            // go through all the swaps, tally up the score
            int score = 0;
            for (int i = 0; i < numSwaps; i++) {
                int[] currentSwap = swaps[i];
                swap(orderOfShells, currentSwap[0] - 1, currentSwap[1] - 1);

                int guess = currentSwap[2];
                if (orderOfShells[guess - 1] == shellThePebbleIsUnder) {
                    score++;
                }
            }

            // set maxScore
            if (score > maxScore) {
                maxScore = score;
            }
        }

        PrintWriter out = new PrintWriter("shell.out");
        out.println(maxScore);
        in.close();
        out.close();
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}