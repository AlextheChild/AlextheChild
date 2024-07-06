package bronze.finished;

import java.util.*;
import java.io.*;

public class TeamTicTacToe {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("tttt.in")));
        String[][] board = new String[3][3];

        for (int i = 0; i < 3; i++) {
            String[] line = in.readLine().split("");
            board[i] = line;
        }

        HashSet<String> winningCows = new HashSet<String>();
        HashSet<String> winningTeams = new HashSet<String>();

        // rows
        for (int i = 0; i < 3; i++) {
            HashSet<String> cowsInRow = getCowsInRow(board[i][0], board[i][1], board[i][2]);
            if (cowsInRow.size() == 1) {
                winningCows.add(cowsInRow.iterator().next());
            } else if (cowsInRow.size() == 2) {
                Iterator<String> iterator = cowsInRow.iterator();
                String a = iterator.next();
                String b = iterator.next();
                if (a.compareTo(b) > 0) {
                    winningTeams.add(b + a);
                } else {
                    winningTeams.add(a + b);
                }
            }
        }
        // columns
        for (int i = 0; i < 3; i++) {
            HashSet<String> cowsInRow = getCowsInRow(board[0][i], board[1][i], board[2][i]);
            if (cowsInRow.size() == 1) {
                winningCows.add(cowsInRow.iterator().next());
            } else if (cowsInRow.size() == 2) {
                Iterator<String> iterator = cowsInRow.iterator();
                String a = iterator.next();
                String b = iterator.next();
                if (a.compareTo(b) > 0) {
                    winningTeams.add(b + a);
                } else {
                    winningTeams.add(a + b);
                }
            }
        }

        // diagonals
        HashSet<String> cowsInRow = getCowsInRow(board[0][0], board[1][1], board[2][2]);
        if (cowsInRow.size() == 1) {
            winningCows.add(cowsInRow.iterator().next());
        } else if (cowsInRow.size() == 2) {
            Iterator<String> iterator = cowsInRow.iterator();
            String a = iterator.next();
            String b = iterator.next();
            if (a.compareTo(b) > 0) {
                winningTeams.add(b + a);
            } else {
                winningTeams.add(a + b);
            }
        }

        cowsInRow = getCowsInRow(board[0][2], board[1][1], board[2][0]);
        if (cowsInRow.size() == 1) {
            winningCows.add(cowsInRow.iterator().next());
        } else if (cowsInRow.size() == 2) {
            Iterator<String> iterator = cowsInRow.iterator();
            String a = iterator.next();
            String b = iterator.next();
            if (a.compareTo(b) > 0) {
                winningTeams.add(b + a);
            } else {
                winningTeams.add(a + b);
            }
        }

        PrintWriter out = new PrintWriter("tttt.out");
        out.println(winningCows.size());
        out.println(winningTeams.size());
        in.close();
        out.close();
    }

    public static HashSet<String> getCowsInRow(String a, String b, String c) {
        HashSet<String> cows = new HashSet<String>();
        cows.add(a);
        cows.add(b);
        cows.add(c);
        return cows;
    }
}