package bronze.finished;

import java.io.*;

public class SquarePasture {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("square.in")));
        String[] pasture1Line = in.readLine().split(" ");
        int[] pasture1 = { Integer.parseInt(pasture1Line[0]), Integer.parseInt(pasture1Line[1]),
                Integer.parseInt(pasture1Line[2]), Integer.parseInt(pasture1Line[3]) };
        String[] pasture2Line = in.readLine().split(" ");
        int[] pasture2 = { Integer.parseInt(pasture2Line[0]), Integer.parseInt(pasture2Line[1]),
                Integer.parseInt(pasture2Line[2]), Integer.parseInt(pasture2Line[3]) };

        int length = Math.max(pasture1[3], pasture2[3]) - Math.min(pasture1[1], pasture2[1]);
        int width = Math.max(pasture1[2], pasture2[2]) - Math.min(pasture1[0], pasture2[0]);
        int longerSide = Math.max(length, width);

        PrintWriter out = new PrintWriter("square.out");
        out.println(longerSide * longerSide);
        in.close();
        out.close();
    }
}