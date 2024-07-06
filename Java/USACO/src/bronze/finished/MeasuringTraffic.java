package bronze.finished;

import java.io.*;

public class MeasuringTraffic {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("traffic.in")));
        int numData = Integer.parseInt(in.readLine());
        String[][] data = new String[numData][3];
        for (int i = 0; i < numData; i++) {
            String[] line = in.readLine().split(" ");
            data[i] = line;
        }

        // first: back to front starting from last none
        // get last none
        int lastNoneIndex = 0;
        for (int i = numData - 1; i >= 0; i--) {
            String ramp = data[i][0];
            if (ramp.equals("none")) {
                lastNoneIndex = i;
                break;
            }
        }

        int[] startingRange = { Integer.parseInt(data[lastNoneIndex][1]), Integer.parseInt(data[lastNoneIndex][2]) };

        for (int i = lastNoneIndex; i >= 0; i--) {
            String ramp = data[i][0];
            int lower = Integer.parseInt(data[i][1]);
            int upper = Integer.parseInt(data[i][2]);
            if (ramp.equals("on")) {
                startingRange[0] -= upper;
                startingRange[1] -= lower;
            } else if (ramp.equals("off")) {
                startingRange[0] += lower;
                startingRange[1] += upper;
            } else if (ramp.equals("none")) {
                startingRange[0] = lower > startingRange[0] ? lower : startingRange[0];
                startingRange[1] = upper < startingRange[1] ? upper : startingRange[1];
            }
        }

        // last: front to back starting from first none
        // get first none
        int firstNoneIndex = 0;
        for (int i = 0; i < numData; i++) {
            String ramp = data[i][0];
            if (ramp.equals("none")) {
                firstNoneIndex = i;
                break;
            }
        }

        int[] endingRange = { Integer.parseInt(data[firstNoneIndex][1]), Integer.parseInt(data[firstNoneIndex][2]) };

        for (int i = firstNoneIndex; i < numData; i++) {
            String ramp = data[i][0];
            int lower = Integer.parseInt(data[i][1]);
            int upper = Integer.parseInt(data[i][2]);
            if (ramp.equals("on")) {
                endingRange[0] += lower;
                endingRange[1] += upper;
            } else if (ramp.equals("off")) {
                endingRange[0] -= upper;
                endingRange[1] -= lower;
            } else if (ramp.equals("none")) {
                endingRange[0] = lower > endingRange[0] ? lower : endingRange[0];
                endingRange[1] = upper < endingRange[1] ? upper : endingRange[1];
            }
        }

        PrintWriter out = new PrintWriter("traffic.out");
        out.println(Math.max(0, startingRange[0]) + " " + Math.max(0, startingRange[1]));
        out.println(Math.max(0, endingRange[0]) + " " + Math.max(0, endingRange[1]));
        in.close();
        out.close();
    }
}