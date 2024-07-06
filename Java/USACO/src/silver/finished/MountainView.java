package silver.finished;

import java.util.*;
import java.io.*;

public class MountainView {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("mountains.in")));
        int n = Integer.parseInt(in.readLine());
        Mountain[] mountains = new Mountain[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            mountains[i] = new Mountain(x - y, x + y);
        }

        // sort by leftness
        Arrays.sort(mountains);

        int numPeaks = 0;
        int rightMost = -1;
        for (Mountain m : mountains) {
            if (m.right > rightMost) {
                rightMost = m.right;
                numPeaks++;
            }
        }

        PrintWriter out = new PrintWriter("mountains.out");
        out.println(numPeaks);
        in.close();
        out.close();
    }

    static class Mountain implements Comparable<Mountain> {
        int left, right;

        public Mountain(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Mountain a) {
            if (this.left != a.left) {
                return Integer.compare(this.left, a.left);
            }
            return Integer.compare(a.right, this.right);
        }
    }
}