package silver.finished;

import java.io.*;

public class Moocast {
    static int n;
    static boolean[][] connected;

    static int reached;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("moocast.in")));
        n = Integer.parseInt(in.readLine());
        int[] xs = new int[n];
        int[] ys = new int[n];
        int[] powers = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            xs[i] = Integer.parseInt(line[0]);
            ys[i] = Integer.parseInt(line[1]);
            powers[i] = Integer.parseInt(line[2]);
        }

        // get which cows can actually communicate
        connected = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                double distSquared = Math.pow(xs[i] - xs[j], 2) + Math.pow(ys[i] - ys[j], 2);
                connected[i][j] = distSquared <= Math.pow(powers[i], 2);
            }
        }

        // loop through each starting cow
        int maxReached = 0;
        for (int i = 0; i < n; i++) {
            reached = 1;
            visited = new boolean[n];

            DFS(i);
            maxReached = Math.max(reached, maxReached);
        }

        PrintWriter out = new PrintWriter("moocast.out");
        out.println(maxReached);
        in.close();
        out.close();
    }

    public static void DFS(int cow) {
        visited[cow] = true;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && connected[cow][i]) {
                reached++;
                DFS(i);
            }
        }
    }
}