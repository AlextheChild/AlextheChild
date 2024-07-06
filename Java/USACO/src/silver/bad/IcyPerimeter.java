package silver.bad;

import java.util.*;
import java.io.*;

public class IcyPerimeter {
    static int n;
    static boolean[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("perimeter.in")));
        n = Integer.parseInt(in.readLine());
        board = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split("");
            for (int j = 0; j < n; j++) {
                if (line[j].equals("#")) {
                    board[i][j] = true;
                } else {
                    board[i][j] = false;
                }
            }
        }

        int maxArea = 0;
        int minPerimeter = Integer.MAX_VALUE;
        HashSet<Integer> indicesToSkip = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // skip the indicies that are known to be in a blob
                if (indicesToSkip.contains(i * n + j)) {
                    continue;
                }

                // get blob coordinates
                HashSet<Coordinate> blobCoordinates = new HashSet<Coordinate>();
                if (board[i][j]) {
                    DFS(new Coordinate(i, j), blobCoordinates);
                }

                // check area and perimeter
                if (blobCoordinates.size() == maxArea) {
                    int perimeter = getPerimeter(blobCoordinates);
                    if (perimeter < minPerimeter) {
                        minPerimeter = perimeter;
                    }
                }
                if (blobCoordinates.size() > maxArea) {
                    maxArea = blobCoordinates.size();
                    minPerimeter = getPerimeter(blobCoordinates);
                }

                // add to the array of i's to skip
                for (Coordinate c : blobCoordinates) {
                    indicesToSkip.add(c.x * n + c.y);
                }
            }
        }

        PrintWriter out = new PrintWriter("perimeter.out");
        out.println(maxArea + " " + minPerimeter);
        in.close();
        out.close();
    }

    public static void DFS(Coordinate c, HashSet<Coordinate> blobCoordinates) {

        // check if it's been there before
        if (blobCoordinates.contains(c)) {
            return;
        }

        blobCoordinates.add(c);

        // check all adjacent points
        if (c.x > 0) {
            if (board[c.x - 1][c.y]) {
                DFS(new Coordinate(c.x - 1, c.y), blobCoordinates);
            }
        }
        if (c.x < n - 1) {
            if (board[c.x + 1][c.y]) {
                DFS(new Coordinate(c.x + 1, c.y), blobCoordinates);
            }
        }
        if (c.y > 0) {
            if (board[c.x][c.y - 1]) {
                DFS(new Coordinate(c.x, c.y - 1), blobCoordinates);
            }
        }
        if (c.y < n - 1) {
            if (board[c.x][c.y + 1]) {
                DFS(new Coordinate(c.x, c.y + 1), blobCoordinates);
            }
        }
    }

    public static int getPerimeter(HashSet<Coordinate> blobCoordinates) {
        int perimeter = 0;
        for (Coordinate c : blobCoordinates) {
            if (c.x > 0) {
                if (!board[c.x - 1][c.y]) {
                    perimeter++;
                }
            } else {
                perimeter++;
            }
            if (c.x < n - 1) {
                if (!board[c.x + 1][c.y]) {
                    perimeter++;
                }
            } else {
                perimeter++;
            }
            if (c.y > 0) {
                if (!board[c.x][c.y - 1]) {
                    perimeter++;
                }
            } else {
                perimeter++;
            }
            if (c.y < n - 1) {
                if (!board[c.x][c.y + 1]) {
                    perimeter++;
                }
            } else {
                perimeter++;
            }
        }

        return perimeter;
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }

            Coordinate c = (Coordinate) o;
            return this.x == c.x && this.y == c.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }
}