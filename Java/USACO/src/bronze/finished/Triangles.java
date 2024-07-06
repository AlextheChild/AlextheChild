package bronze.finished;

import java.io.*;

public class Triangles {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("triangles.in")));
        int n = Integer.parseInt(in.readLine());
        int[][] points = new int[n][2];

        // get all points
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            points[i][0] = x;
            points[i][1] = y;
        }

        int maxArea = 0;
        // choose 3 distinct points
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }

                    int[] a = points[i];
                    int[] b = points[j];
                    int[] c = points[k];

                    boolean hasXBase = false;
                    boolean hasYBase = false;
                    int area = 0;
                    if (isXBase(a, b)) {
                        hasXBase = true;
                        int height = Math.abs(a[1] - c[1]);
                        area = (int) length(a, b) * height;
                    } else if (isXBase(b, c)) {
                        hasXBase = true;
                        int height = Math.abs(b[1] - a[1]);
                        area = (int) length(b, c) * height;
                    } else if (isXBase(a, c)) {
                        hasXBase = true;
                        int height = Math.abs(a[1] - b[1]);
                        area = (int) length(b, c) * height;
                    }

                    if (isYBase(a, b)) {
                        hasYBase = true;
                        int height = Math.abs(a[0] - c[0]);
                        area = (int) length(a, b) * height;
                    } else if (isYBase(b, c)) {
                        hasYBase = true;
                        int height = Math.abs(b[0] - a[0]);
                        area = (int) length(b, c) * height;
                    } else if (isYBase(a, c)) {
                        hasYBase = true;
                        int height = Math.abs(a[0] - b[0]);
                        area = (int) length(a, c) * height;
                    }

                    if (!hasXBase || !hasYBase) {
                        continue;
                    }

                    maxArea = area > maxArea ? area : maxArea;
                }
            }
        }

        PrintWriter out = new PrintWriter("triangles.out");
        out.println(maxArea);
        in.close();
        out.close();
    }

    public static boolean isXBase(int[] a, int[] b) {
        if (a[1] == b[1]) {
            return true;
        }
        return false;
    }

    public static boolean isYBase(int[] a, int[] b) {
        if (a[0] == b[0]) {
            return true;
        }
        return false;
    }

    public static int length(int[] a, int[] b) {
        int length = (int) Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
        return length;
    }
}