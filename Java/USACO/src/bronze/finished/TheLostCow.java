package bronze.finished;

import java.io.*;

public class TheLostCow {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("lostcow.in")));
        String[] xy = in.readLine().split(" ");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);

        int distance = 0;
        int pos = x;
        int constant = 1;
        int nextPoint = x + constant;

        boolean cowFound = false;
        while (!cowFound) {
            while (pos != nextPoint) {
                if (nextPoint > pos) {
                    pos++;
                } else {
                    pos--;
                }

                if (pos == y) {
                    cowFound = true;
                    break;
                }
                distance++;
            }

            constant *= -2;
            nextPoint = x + constant;
        }

        PrintWriter out = new PrintWriter("lostcow.out");
        out.println(distance + 1);
        in.close();
        out.close();
    }
}