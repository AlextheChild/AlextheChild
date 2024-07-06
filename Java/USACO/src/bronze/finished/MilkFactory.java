package bronze.finished;

import java.io.*;

public class MilkFactory {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("factory.in")));
        int n = Integer.parseInt(in.readLine());
        int[] nodeNumOutPaths = new int[n];
        for (int i = 0; i < n - 1; i++) {
            String[] line = in.readLine().split(" ");
            int node = Integer.parseInt(line[0]);
            nodeNumOutPaths[node - 1]++;
        }

        int answer = -1;
        int index = 1;
        for (int i : nodeNumOutPaths) {
            if (i == 0) {
                if (answer == -1) {
                    answer = index;
                    continue;
                }
                answer = -1;
                break;
            }
            index++;
        }

        PrintWriter out = new PrintWriter("factory.out");
        out.println(answer);
        in.close();
        out.close();
    }
}