package silver.finished;

import java.util.*;
import java.io.*;

public class TheMooParticle {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("moop.in")));
        int n = Integer.parseInt(in.readLine());
        MooParticle[] particles = new MooParticle[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            particles[i] = new MooParticle(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        Arrays.sort(particles);

        // find the particles that have a lower y than before and a higher y than after
        int[] minimumYFromLeft = new int[n];
        minimumYFromLeft[0] = particles[0].y;
        for (int i = 1; i < n; i++) {
            minimumYFromLeft[i] = Math.min(minimumYFromLeft[i - 1], particles[i].y);
        }
        int[] maxYFromRight = new int[n];
        maxYFromRight[n - 1] = particles[n - 1].y;
        for (int i = n - 2; i >= 0; i--) {
            maxYFromRight[i] = Math.max(maxYFromRight[i + 1], particles[i].y);
        }

        int numGroups = 1;
        for (int i = 0; i < n - 1; i++) {
            if (minimumYFromLeft[i] > maxYFromRight[i + 1]) {
                numGroups++;
            }
        }

        PrintWriter out = new PrintWriter("moop.out");
        out.println(numGroups);
        in.close();
        out.close();
    }

    static class MooParticle implements Comparable<MooParticle> {
        int x, y;

        public MooParticle(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(MooParticle o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }
}