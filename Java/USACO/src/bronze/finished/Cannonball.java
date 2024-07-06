package bronze.finished;

import java.io.*;

public class Cannonball {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] ns = in.readLine().split(" ");
        int n = Integer.parseInt(ns[0]);
        int s = Integer.parseInt(ns[1]);
        int[] targets = new int[n];
        int[] jumpPads = new int[n];
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            if (line[0].equals("0")) {
                jumpPads[i] = Integer.parseInt(line[1]);
                targets[i] = -1;
            } else {
                targets[i] = Integer.parseInt(line[1]);
                jumpPads[i] = -1;
            }
        }
        int[] timesHitJumpPad = new int[n];
        int[] powerLevelWhenHitJumpPad = new int[n];

        int bessiePos = s - 1;
        int bessiePower = 1;
        int bessieDirection = 1;
        boolean looping = false;
        int targetsBroken = 0;
        while (bessiePos >= 0 && bessiePos < n && !looping) {
            // if there is a target
            if (targets[bessiePos] != -1 && bessiePower >= targets[bessiePos]) {
                targetsBroken++;
                targets[bessiePos] = -1;
            }
            // if there is a jump pad
            else if (jumpPads[bessiePos] != -1) {
                bessiePower += jumpPads[bessiePos];
                bessieDirection *= -1;

                timesHitJumpPad[bessiePos]++;
                if (powerLevelWhenHitJumpPad[bessiePos] == 0) {
                    powerLevelWhenHitJumpPad[bessiePos] = bessiePower;
                }
                if (timesHitJumpPad[bessiePos] >= 2) {
                    if (powerLevelWhenHitJumpPad[bessiePos] == bessiePower) {
                        looping = true;
                    }
                }
            }

            bessiePos += bessiePower * bessieDirection;
        }

        System.out.println(targetsBroken);
    }
}