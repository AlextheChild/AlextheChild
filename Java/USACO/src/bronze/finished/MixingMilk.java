package bronze.finished;

import java.io.*;

public class MixingMilk {
    static milkBucket[] buckets;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("mixmilk.in")));
        buckets = new milkBucket[3];

        for (int i = 0; i < 3; i++) {
            String[] line = in.readLine().split(" ");
            buckets[i] = new milkBucket(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }

        for (int i = 0; i < 100; i++) {
            pourOperation(i);
        }

        PrintWriter out = new PrintWriter("mixmilk.out");
        for (int i = 0; i < 3; i++) {
            out.println(buckets[i].currentAmount);
        }
        in.close();
        out.close();
    }

    public static void pourOperation(int i) {
        switch (i % 3) {
            // 1 into 2
            case 0:
                pour(buckets[0], buckets[1]);
                break;

            // 2 into 3
            case 1:
                pour(buckets[1], buckets[2]);
                break;

            // 3 into 1
            case 2:
                pour(buckets[2], buckets[0]);
                break;
        }
    }

    public static void pour(milkBucket pourer, milkBucket receiver) {
        receiver.currentAmount += pourer.currentAmount;
        int overflow = Math.max(0, receiver.currentAmount - receiver.capacity);
        receiver.currentAmount -= overflow;
        pourer.currentAmount = overflow;
    }

    static class milkBucket {
        int capacity;
        int currentAmount;

        public milkBucket(int capacity, int currentAmount) {
            this.capacity = capacity;
            this.currentAmount = currentAmount;
        }
    }
}