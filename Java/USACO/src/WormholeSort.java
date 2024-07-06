import java.util.*;
import java.io.*;

public class WormholeSort {
    static ArrayList<Edge>[] edgeList;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // ! locations are 1-indexed
        // ! fucking DSU

        BufferedReader in = new BufferedReader(new FileReader(new File("wormsort.in")));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        String[] cowLine = in.readLine().split(" ");
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(cowLine[i]) - 1;
        }
        edgeList = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            edgeList[i] = new ArrayList<Edge>();
        }
        int maxWidth = 0;
        for (int i = 0; i < m; i++) {
            String[] line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]) - 1;
            int to = Integer.parseInt(line[1]) - 1;
            int width = Integer.parseInt(line[2]);
            maxWidth = width > maxWidth ? width : maxWidth;
            edgeList[from].add(new Edge(to, width));
            edgeList[to].add(new Edge(from, width));
        }

        // binary search on width
        int l = 0;
        int r = maxWidth;
        while (l <= r) {
            int mid = l + ((r - l) / 2);
            if (check(mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        PrintWriter out = new PrintWriter("wormsort.out");
        out.println();
        in.close();
        out.close();
    }

    public static boolean check(int width) {
        // cows can be sorted if every cow is connected through the tree to where they
        // should be
        return false;
    }

    static class Edge {
        int to, width;

        public Edge(int to, int width) {
            this.to = to;
            this.width = width;
        }
    }
}