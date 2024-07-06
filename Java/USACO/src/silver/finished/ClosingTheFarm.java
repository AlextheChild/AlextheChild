package silver.finished;

import java.util.*;
import java.io.*;

public class ClosingTheFarm {
    static int n, m;
    static ArrayList<Integer>[] paths;
    static HashSet<Integer> closedFarms;

    static boolean[] visited;
    static int numVisited;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("closing.in")));
        PrintWriter out = new PrintWriter("closing.out");

        String[] nm = in.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        paths = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            paths[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            String[] line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]) - 1;
            int to = Integer.parseInt(line[1]) - 1;
            paths[from].add(to);
            paths[to].add(from);
        }

        System.out.println("barns: ");
        for (int i = 0; i < n; i++) {
            System.out.println("barn " + i + ": ");
            System.out.println(paths[i]);
        }

        int[] closingOrder = new int[n];
        for (int i = 0; i < n; i++) {
            closingOrder[i] = Integer.parseInt(in.readLine()) - 1;
        }

        // DFS to see if they're all connected
        closedFarms = new HashSet<Integer>();
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            numVisited = 1;
            DFS(closingOrder[n - 1]);

            System.out.println("visited: " + numVisited);

            // i = num farms that are closed
            if (numVisited == n - i) {
                out.println("YES");
            } else {
                out.println("NO");
            }

            closedFarms.add(closingOrder[i]);
        }

        in.close();
        out.close();
    }

    public static void DFS(int barn) {
        visited[barn] = true;

        for (int i : paths[barn]) {
            if (!visited[i] && !closedFarms.contains(i)) {
                numVisited++;
                DFS(i);
            }
        }
    }
}