package silver.finished;

import java.util.*;
import java.io.*;

public class FencePlanning {
    static Cow[] cows;
    static boolean[] visited;
    static ArrayList<Integer> component;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("fenceplan.in")));
        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            cows[i] = new Cow(x, y);
        }
        for (int i = 0; i < m; i++) {
            String[] line = in.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            cows[a - 1].edgeList.add(b - 1);
            cows[b - 1].edgeList.add(a - 1);
        }

        visited = new boolean[n];

        // floodfill everything
        int minPerimeter = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            component = new ArrayList<Integer>();
            DFS(i);

            if (component.size() > 1) {
                int minX = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                int maxX = 0;
                int maxY = 0;
                for (int j : component) {
                    minX = Math.min(cows[j].x, minX);
                    minY = Math.min(cows[j].y, minY);
                    maxX = Math.max(cows[j].x, maxX);
                    maxY = Math.max(cows[j].y, maxY);
                }
                minPerimeter = Math.min(minPerimeter, 2 * (maxX - minX) + 2 * (maxY - minY));
            }
        }

        PrintWriter out = new PrintWriter("fenceplan.out");
        out.println(minPerimeter);
        in.close();
        out.close();
    }

    public static void DFS(int start) {
        visited[start] = true;
        component.add(start);

        for (int i : cows[start].edgeList) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    static class Cow {
        int x, y;
        ArrayList<Integer> edgeList; // 0-indexed

        public Cow(int x, int y) {
            this.x = x;
            this.y = y;
            edgeList = new ArrayList<Integer>();
        }
    }
}