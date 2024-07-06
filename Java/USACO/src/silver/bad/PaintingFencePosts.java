package silver.bad;

import java.util.*;
import java.io.*;

public class PaintingFencePosts {
    static int n, p;
    static Coord[] posts;
    static ArrayList<Coord> orderedPosts;
    static boolean verticalHorizontal;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] np = in.readLine().split(" ");
        n = Integer.parseInt(np[0]);
        p = Integer.parseInt(np[1]);
        posts = new Coord[p];
        for (int i = 0; i < p; i++) {
            String[] line = in.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            posts[i] = new Coord(x, y);
        }
        Coord[] starting = new Coord[n];
        Coord[] ending = new Coord[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);
            starting[i] = new Coord(x1, y1);
            ending[i] = new Coord(x2, y2);
        }

        // get posts as a straight line
        orderedPosts = new ArrayList<Coord>();
        verticalHorizontal = false;
        visited = new boolean[p];
        DFS(0);

        //

        System.out.println();
    }

    public static void DFS(int start) {
        visited[start] = true;

        Coord post = posts[start];
        orderedPosts.add(post);

        // get next post
        ArrayList<Integer> sameAxis = new ArrayList<Integer>();
        for (int i = 0; i < p; i++) {
            if (i == start) {
                continue;
            }
            if (!verticalHorizontal) {
                // vertical
                if (posts[i].x == post.x) {
                    sameAxis.add(i);
                }
            } else {
                // horizontal
                if (posts[i].y == post.y) {
                    sameAxis.add(i);
                }
            }
        }

        // get closest post on sameAxis
        int closest = 0;
        double minDist = Integer.MAX_VALUE;
        for (int i : sameAxis) {
            double dist = Math.pow(post.x - posts[i].x, 2) + Math.pow(post.y - posts[i].y, 2);
            if (dist < minDist) {
                minDist = dist;
                closest = i;
            }
        }

        verticalHorizontal = !verticalHorizontal;

        if (!visited[closest]) {
            DFS(closest);
        }
    }

    static class Coord {
        int x, y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}