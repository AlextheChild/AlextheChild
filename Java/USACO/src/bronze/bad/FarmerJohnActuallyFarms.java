package bronze.bad;

import java.io.*;

public class FarmerJohnActuallyFarms {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(in.readLine());
            String[] heightLine = in.readLine().split(" ");
            long[] heights = new long[n];
            String[] growthLine = in.readLine().split(" ");
            long[] growth = new long[n];
            String[] wantLine = in.readLine().split(" ");
            long[] wants = new long[n];
            for (int j = 0; j < n; j++) {
                heights[j] = Long.parseLong(heightLine[j]);
                growth[j] = Long.parseLong(growthLine[j]);
                wants[j] = Long.parseLong(wantLine[j]);
            }

            int days = 0;
            boolean daysFound = false;
            while (!daysFound) {
                // impossible check O(n^2)
                boolean impossible = false;
                for (int j = 0; j < n; j++) {
                    // check how many plants will never overtake it
                    int shorterAndSlower = 0;
                    for (int k = 0; k < n; k++) {
                        if (j == k) {
                            continue;
                        }

                        if (heights[j] >= heights[k] && growth[j] >= growth[k]) {
                            shorterAndSlower++;
                        }
                    }

                    if ((n - 1) - shorterAndSlower < wants[j]) {
                        impossible = true;
                        break;
                    }
                }
                if (impossible) {
                    days = -1;
                    break;
                }

                // compare to other plants O(n^2)
                boolean allPlantsSatisfied = true;
                for (int j = 0; j < n; j++) {
                    int higherThan = 0;
                    for (int k = 0; k < n; k++) {
                        if (j == k) {
                            continue;
                        }

                        if (heights[j] < heights[k]) {
                            higherThan++;
                        }
                    }
                    if (higherThan < wants[j]) {
                        allPlantsSatisfied = false;
                        break;
                    }
                }

                if (allPlantsSatisfied) {
                    daysFound = true;
                    break;
                }

                // grow O(n)
                for (int j = 0; j < n; j++) {
                    heights[j] += growth[j];
                }
                days++;
            }

            System.out.println(days);
        }
    }
}