package bronze.finished;

import java.util.*;
import java.io.*;

public class MowingTheField {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("mowing.in")));
        int n = Integer.parseInt(in.readLine());
        String[] directions = new String[n];
        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            directions[i] = line[0];
            distances[i] = Integer.parseInt(line[1]);
        }

        Point location = new Point(0, 0);
        HashMap<Point, Integer> coordinateTimeMap = new HashMap<Point, Integer>();
        int time = 0;
        coordinateTimeMap.put(new Point(location.x, location.y), time);
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < distances[i]; j++) {
                if (directions[i].equals("N")) {
                    location.y++;
                } else if (directions[i].equals("E")) {
                    location.x++;
                } else if (directions[i].equals("S")) {
                    location.y--;
                } else if (directions[i].equals("W")) {
                    location.x--;
                }
                time++;

                if (coordinateTimeMap.containsKey(location)) {
                    minTime = Math.min(minTime, time - coordinateTimeMap.get(location));
                }
                coordinateTimeMap.put(new Point(location.x, location.y), time);
            }
        }

        PrintWriter out = new PrintWriter("mowing.out");
        out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
        in.close();
        out.close();
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}