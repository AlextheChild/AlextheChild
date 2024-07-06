package silver.finished;

import java.util.*;
import java.io.*;

public class MooTube {
    static ArrayList<Edge>[] edgeList;

    static int minRelevance;
    static boolean[] visited;
    static int ans;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("mootube.in")));
        PrintWriter out = new PrintWriter("mootube.out");

        String[] nq = in.readLine().split(" ");
        int n = Integer.parseInt(nq[0]);
        int q = Integer.parseInt(nq[1]);
        edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<Edge>();
        }
        for (int i = 0; i < n - 1; i++) {
            String[] line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]) - 1;
            int to = Integer.parseInt(line[1]) - 1;
            int relevance = Integer.parseInt(line[2]);

            edgeList[from].add(new Edge(to, relevance));
            edgeList[to].add(new Edge(from, relevance));
        }

        while (q-- > 0) {
            String[] line = in.readLine().split(" ");
            minRelevance = Integer.parseInt(line[0]);
            int startVideo = Integer.parseInt(line[1]) - 1;

            visited = new boolean[n];
            ans = 0;
            DFS(startVideo);
            out.println(ans);
        }

        in.close();
        out.close();
    }

    public static void DFS(int video) {
        visited[video] = true;

        for (Edge e : edgeList[video]) {
            if (!visited[e.to] && e.relevance >= minRelevance) {
                ans++;
                DFS(e.to);
            }
        }
    }

    static class Edge {
        int to, relevance;

        public Edge(int t, int r) {
            to = t;
            relevance = r;
        }
    }
}