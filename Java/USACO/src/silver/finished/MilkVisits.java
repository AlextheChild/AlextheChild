package silver.finished;

import java.util.*;
import java.io.*;

public class MilkVisits {
    static String[] farmCows;
    static ArrayList<Integer>[] edgeList;

    static int componentIndex;
    static int[] componentAssignments;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("milkvisits.in")));
        PrintWriter out = new PrintWriter("milkvisits.out");

        String[] nm = in.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);
        farmCows = in.readLine().split("");
        edgeList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edgeList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) {
            String[] line = in.readLine().split(" ");
            int from = Integer.parseInt(line[0]) - 1;
            int to = Integer.parseInt(line[1]) - 1;
            edgeList[from].add(to);
            edgeList[to].add(from);
        }

        // process farm component numbers
        componentIndex = 1;
        componentAssignments = new int[n];
        for (int i = 0; i < n; i++) {
            if (componentAssignments[i] != 0) {
                continue;
            }
            DFS(i);
            componentIndex++;
        }

        for (int i = 0; i < m; i++) {
            String[] line = in.readLine().split(" ");
            int start = Integer.parseInt(line[0]) - 1;
            int end = Integer.parseInt(line[1]) - 1;
            String cowType = line[2];

            // if they stayed in one component
            if (componentAssignments[start] == componentAssignments[end]) {
                out.print(farmCows[start].equals(cowType) ? 1 : 0);
            } else {
                out.print(1);
            }
        }

        in.close();
        out.close();
    }

    public static void DFS(int farm) {
        componentAssignments[farm] = componentIndex;

        for (int i : edgeList[farm]) {
            if (farmCows[i].equals(farmCows[farm]) && componentAssignments[i] == 0) {
                DFS(i);
            }
        }
    }
}