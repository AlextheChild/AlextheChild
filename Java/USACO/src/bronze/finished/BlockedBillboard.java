package bronze.finished;

import java.io.*;

public class BlockedBillboard {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("billboard.in")));
        String[] board1line = in.readLine().split(" ");
        int[] board1 = { Integer.parseInt(board1line[0]), Integer.parseInt(board1line[1]),
                Integer.parseInt(board1line[2]), Integer.parseInt(board1line[3]) };
        String[] board2line = in.readLine().split(" ");
        int[] board2 = { Integer.parseInt(board2line[0]), Integer.parseInt(board2line[1]),
                Integer.parseInt(board2line[2]), Integer.parseInt(board2line[3]) };
        String[] truckline = in.readLine().split(" ");
        int[] truck = { Integer.parseInt(truckline[0]), Integer.parseInt(truckline[1]),
                Integer.parseInt(truckline[2]), Integer.parseInt(truckline[3]) };

        int b1Area = (board1[2] - board1[0]) * (board1[3] - board1[1]);
        int b2Area = (board2[2] - board2[0]) * (board2[3] - board2[1]);
        int b1BlockedArea = Math.max((Math.min(board1[2], truck[2]) - Math.max(board1[0], truck[0])), 0)
                * Math.max((Math.min(board1[3], truck[3]) - Math.max(board1[1], truck[1])), 0);
        int b2BlockedArea = Math.max((Math.min(board2[2], truck[2]) - Math.max(board2[0], truck[0])), 0)
                * Math.max((Math.min(board2[3], truck[3]) - Math.max(board2[1], truck[1])), 0);

        PrintWriter out = new PrintWriter("billboard.out");
        out.println((b1Area + b2Area) - (b1BlockedArea + b2BlockedArea));
        in.close();
        out.close();
    }
}