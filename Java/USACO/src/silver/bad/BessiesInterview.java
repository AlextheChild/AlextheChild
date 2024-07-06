package silver.bad;

import java.util.*;
import java.io.*;

public class BessiesInterview {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        String[] timesLine = in.readLine().split(" ");
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(timesLine[i]);
        }

        // start interviews
        int[] interviews = new int[k];
        for (int i = 0; i < k; i++) {
            interviews[i] = times[i];
        }
        int interviewIndex = k;
        int time = 0;
        int[] interviewerLinks = new int[k];
        for (int i = 0; i < k; i++) {
            interviewerLinks[i] = i;
        }
        ArrayList<Integer> finishedFarmers = new ArrayList<Integer>();
        while (interviewIndex <= n) {
            for (int i = 0; i < k; i++) {
                interviews[i]--;

                if (interviews[i] == 0) {
                    finishedFarmers.add(i);
                    try {
                        interviews[i] = times[interviewIndex];
                        interviewIndex++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        interviewIndex++;
                        break;
                    }
                }
            }
            if (finishedFarmers.size() > 1) {
                for (int i : finishedFarmers) {
                    interviewerLinks[i] = interviewerLinks[finishedFarmers.get(0)];
                }
            }
            time++;
        }

        boolean[] bessieInteviewers = new boolean[k];
        for (int i = 0; i < k; i++) {
            if (i == 0) {
                int linkNum = interviewerLinks[i];
                for (int j = 0; j < k; j++) {
                    if (interviewerLinks[j] == linkNum) {
                        bessieInteviewers[j] = true;
                    }
                }
            }
        }
        String interviewers = "";
        for (boolean b : bessieInteviewers) {
            if (b) {
                interviewers += "1";
            } else {
                interviewers += "0";
            }
        }

        System.out.println(time);
        System.out.println(interviewers);
    }
}