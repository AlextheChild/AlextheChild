package bronze.finished;

import java.io.*;

public class TheBovineShuffle {
    static int n;
    static int[] places;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("shuffle.in")));
        n = Integer.parseInt(in.readLine());
        String[] forwardPlaceLine = in.readLine().split(" ");
        places = new int[n];
        for (int i = 0; i < n; i++) {
            places[i] = Integer.parseInt(forwardPlaceLine[i]) - 1;
        }
        String[] ids = in.readLine().split(" ");

        for (int i = 0; i < 3; i++) {
            ids = shuffle(ids);
        }

        PrintWriter out = new PrintWriter("shuffle.out");
        for (String id : ids) {
            out.println(id);
        }
        in.close();
        out.close();
    }

    public static String[] shuffle(String[] ids) {
        String[] newOrder = new String[n];
        for (int i = 0; i < n; i++) {
            newOrder[i] = ids[places[i]];
        }

        return newOrder;
    }
}