package bronze.finished;

import java.io.*;

public class Photoshoot {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("photo.in")));
        int n = Integer.parseInt(in.readLine());
        int[] bessieOrder = new int[n - 1];
        String[] orderLine = in.readLine().split(" ");
        for (int i = 0; i < n - 1; i++) {
            bessieOrder[i] = Integer.parseInt(orderLine[i]);
        }

        int[] order = new int[n];
        // try every first value
        for (int i = 1; i < bessieOrder[0]; i++) {
            order[0] = i;
            boolean orderGood = true;
            for (int j = 1; j < n; j++) {
                order[j] = bessieOrder[j - 1] - order[j - 1];

                if (order[j] <= 0) {
                    orderGood = false;
                    break;
                }
                for (int k = 0; k < j; k++) {
                    if (order[k] == order[j]) {
                        orderGood = false;
                        break;
                    }
                }
            }
            if (orderGood) {
                break;
            }
        }

        PrintWriter out = new PrintWriter("photo.out");
        String output = "";
        for (int i : order) {
            output += i + " ";
        }
        out.println(output.substring(0, output.length() - 1));
        in.close();
        out.close();
    }
}