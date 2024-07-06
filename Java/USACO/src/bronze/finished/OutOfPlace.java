package bronze.finished;

import java.util.*;
import java.io.*;

public class OutOfPlace {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("outofplace.in")));
        int n = Integer.parseInt(in.readLine());
        int[] cowLine = new int[n];
        for (int i = 0; i < n; i++) {
            cowLine[i] = Integer.parseInt(in.readLine());
        }

        // figure out if bessie is left or right of where she needs to be and her index
        int bessieIndex = 0;
        boolean bessieLeft = false;
        boolean bessieRight = false;

        // get bessieIndex
        for (int i = 0; i < n; i++) {
            // create a new array with one index removed
            int[] tempCowLine = new int[n - 1];
            boolean iPassed = false;
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    iPassed = true;
                    continue;
                }
                tempCowLine[j - (iPassed ? 1 : 0)] = cowLine[j];
            }

            // check if that array is sorted
            int[] sortedTempCowLine = new int[n - 1];
            for (int j = 0; j < n - 1; j++) {
                sortedTempCowLine[j] = tempCowLine[j];
            }
            Arrays.sort(sortedTempCowLine);
            if (Arrays.equals(tempCowLine, sortedTempCowLine)) {
                bessieIndex = i;
                break;
            }
        }

        // everything except endpoints
        if (bessieIndex != 0 && bessieIndex != n - 1) {
            if (cowLine[bessieIndex] > cowLine[bessieIndex - 1] && cowLine[bessieIndex] > cowLine[bessieIndex + 1]) {
                bessieLeft = true;
            }
            if (cowLine[bessieIndex] < cowLine[bessieIndex - 1] && cowLine[bessieIndex] < cowLine[bessieIndex + 1]) {
                bessieRight = true;
            }
        }

        // endpoints
        if (bessieIndex == 0) {
            bessieLeft = true;
        }
        if (bessieIndex == n - 1) {
            bessieRight = true;
        }

        // find the distinct heights that are in between her and where she needs to be
        HashSet<Integer> cowsBetween = new HashSet<Integer>();
        if (bessieLeft) {
            for (int i = bessieIndex + 1; i < n; i++) {
                if (cowLine[i] < cowLine[bessieIndex]) {
                    cowsBetween.add(cowLine[i]);
                } else {
                    break;
                }
            }
        } else if (bessieRight) {
            for (int i = bessieIndex - 1; i >= 0; i--) {
                if (cowLine[i] > cowLine[bessieIndex]) {
                    cowsBetween.add(cowLine[i]);
                } else {
                    break;
                }
            }
        }

        PrintWriter out = new PrintWriter("outofplace.out");
        out.println(cowsBetween.size());
        in.close();
        out.close();
    }
}