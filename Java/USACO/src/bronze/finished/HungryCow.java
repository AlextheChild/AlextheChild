package bronze.finished;

import java.util.*;
import java.io.*;

public class HungryCow {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] nt = in.readLine().split(" ");
        long n = Long.parseLong(nt[0]);
        long t = Long.parseLong(nt[1]);

        // day, number of haybales added
        TreeMap<Long, Long> dayHay = new TreeMap<Long, Long>();
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            dayHay.put(Long.parseLong(line[0]), Long.parseLong(line[1]));
        }

        long balesInBarn = 0;
        long totalBalesEaten = 0;
        Iterator<Long> nextDayIterator = dayHay.keySet().iterator();
        nextDayIterator.next();
        for (long i : dayHay.keySet()) {
            balesInBarn += dayHay.get(i);

            long nextDayFed = nextDayIterator.hasNext() ? nextDayIterator.next() : t + 1;

            long balesEaten = Math.min(nextDayFed - i, balesInBarn);
            totalBalesEaten += balesEaten;
            balesInBarn -= balesEaten;
        }

        System.out.println(totalBalesEaten);
    }
}