package silver.finished;

import java.util.*;
import java.io.*;

public class HighCardWins {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("highcard.in")));
        int n = Integer.parseInt(in.readLine());
        boolean[] elsieHas = new boolean[2 * n];
        for (int i = 0; i < n; i++) {
            elsieHas[Integer.parseInt(in.readLine()) - 1] = true;
        }

        ArrayList<Integer> elsieCards = new ArrayList<Integer>();
        ArrayList<Integer> bessieCards = new ArrayList<Integer>();
        for (int i = 0; i < 2 * n; i++) {
            if (elsieHas[i]) {
                elsieCards.add(i + 1);
            } else {
                bessieCards.add(i + 1);
            }
        }

        // sort each and then figure out how many cards bessie has that are higher
        int points = 0;
        int bessiePointer = 0;
        int elsiePointer = 0;
        while (bessiePointer < n) {
            if (bessieCards.get(bessiePointer) > elsieCards.get(elsiePointer)) {
                points++;
                bessiePointer++;
                elsiePointer++;
            } else {
                bessiePointer++;
            }
        }

        PrintWriter out = new PrintWriter("highcard.out");
        out.println(points);
        in.close();
        out.close();
    }
}