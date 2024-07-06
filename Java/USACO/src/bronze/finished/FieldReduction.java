package bronze.finished;

import java.util.*;
import java.io.*;

public class FieldReduction {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("reduce.in")));
        int n = Integer.parseInt(in.readLine());
        Cow[] cows = new Cow[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            cows[i] = new Cow(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        // loop through all of the outside border cows that don't have a partner
        ArrayList<Cow> borderCows = new ArrayList<Cow>();
        Arrays.sort(cows, Comparator.comparingInt(c -> c.x));
        if (cows[0].x != cows[1].x) {
            borderCows.add(cows[0]);
        }
        if (cows[n - 2].x != cows[n - 1].x) {
            borderCows.add(cows[n - 1]);
        }
        Arrays.sort(cows, Comparator.comparingInt(c -> c.y));
        if (cows[0].y != cows[1].y) {
            borderCows.add(cows[0]);
        }
        if (cows[n - 2].x != cows[n - 1].x) {
            borderCows.add(cows[n - 1]);
        }

        int minArea = Integer.MAX_VALUE;
        for (Cow c : borderCows) {
            ArrayList<Cow> newList = new ArrayList<Cow>(Arrays.asList(cows));
            newList.remove(c);
            minArea = Math.min(getArea(newList), minArea);
        }

        PrintWriter out = new PrintWriter("reduce.out");
        out.println(minArea);
        in.close();
        out.close();
    }

    public static int getArea(ArrayList<Cow> cows) {
        Collections.sort(cows, (a, b) -> a.x < b.x ? -1 : (a.x == b.x ? 0 : 1));
        int width = cows.get(cows.size() - 1).x - cows.get(0).x;

        Collections.sort(cows, (a, b) -> a.y < b.y ? -1 : (a.y == b.y ? 0 : 1));
        int height = cows.get(cows.size() - 1).y - cows.get(0).y;

        return width * height;
    }

    static class Cow {
        int x, y;

        public Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}