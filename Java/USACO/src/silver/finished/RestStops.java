package silver.finished;

import java.io.*;

public class RestStops {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("reststops.in")));
        String[] potato = in.readLine().split(" ");
        // int l = Integer.parseInt(potato[0]);
        int n = Integer.parseInt(potato[1]);
        int johnRate = Integer.parseInt(potato[2]);
        int bessieRate = Integer.parseInt(potato[3]);
        int[] positions = new int[n];
        int[] tastiness = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            positions[i] = Integer.parseInt(line[0]);
            tastiness[i] = Integer.parseInt(line[1]);
        }

        // find the good stops
        boolean[] stopGood = new boolean[n];
        int maxNumSeen = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (tastiness[i] > maxNumSeen) {
                stopGood[i] = true;
                maxNumSeen = tastiness[i];
            }
        }

        // go through every stop
        long maxTasty = 0;
        int lastStopPos = 0;
        for (int i = 0; i < n; i++) {
            if (!stopGood[i]) {
                continue;
            }

            long distTraveled = positions[i] - lastStopPos;
            long johnTime = distTraveled * johnRate; // seconds per meter
            long bessieTime = distTraveled * bessieRate;
            long restTime = johnTime - bessieTime;
            maxTasty += restTime * tastiness[i];

            lastStopPos = positions[i];
        }

        PrintWriter out = new PrintWriter("reststops.out");
        out.println(maxTasty);
        in.close();
        out.close();
    }
}