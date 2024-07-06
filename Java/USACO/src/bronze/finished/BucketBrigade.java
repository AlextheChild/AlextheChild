package bronze.finished;

import java.io.*;

public class BucketBrigade {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("buckets.in")));
        int[] barnPos = new int[2];
        int[] lakePos = new int[2];
        int[] rockPos = new int[2];
        for (int i = 0; i < 10; i++) {
            String[] line = in.readLine().split("");
            for (int j = 0; j < 10; j++) {
                if (line[j].equals("B")) {
                    barnPos[0] = i;
                    barnPos[1] = j;
                }
                if (line[j].equals("L")) {
                    lakePos[0] = i;
                    lakePos[1] = j;
                }
                if (line[j].equals("R")) {
                    rockPos[0] = i;
                    rockPos[1] = j;
                }
            }
        }
        int xDist = Math.abs(barnPos[0] - lakePos[0]);
        int yDist = Math.abs(barnPos[1] - lakePos[1]);
        if (xDist == 0 && rockPos[0] == barnPos[0]
                && (rockPos[1] > Math.min(barnPos[1], lakePos[1]) && rockPos[1] < Math.max(barnPos[1], lakePos[1]))) {
            xDist = 2;
        }
        if (yDist == 0 && rockPos[1] == barnPos[1]
                && (rockPos[0] > Math.min(barnPos[0], lakePos[0]) && rockPos[0] < Math.max(barnPos[0], lakePos[0]))) {
            yDist = 2;
        }

        PrintWriter out = new PrintWriter("buckets.out");
        out.println(xDist + yDist - 1);
        in.close();
        out.close();
    }
}