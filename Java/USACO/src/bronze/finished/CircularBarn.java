package bronze.finished;

import java.io.*;

public class CircularBarn {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("cbarn.in")));
        int n = Integer.parseInt(in.readLine());
        int[] volumes = new int[n];
        for (int i = 0; i < n; i++) {
            volumes[i] = Integer.parseInt(in.readLine());
        }

        int minRoomsTraveled = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int roomsAway = 0;
            int index = i;
            int roomsTraveled = 0;
            while (roomsAway < n) {
                roomsTraveled += roomsAway * volumes[index];

                index = (index + 1) % n;
                roomsAway++;
            }
            if (roomsTraveled < minRoomsTraveled) {
                minRoomsTraveled = roomsTraveled;
            }
        }

        PrintWriter out = new PrintWriter("cbarn.out");
        out.println(minRoomsTraveled);
        in.close();
        out.close();
    }
}