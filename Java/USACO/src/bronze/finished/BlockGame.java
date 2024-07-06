package bronze.finished;

import java.io.*;

public class BlockGame {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("blocks.in")));
        int n = Integer.parseInt(in.readLine());
        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = in.readLine();
        }

        int[] letterCounts = new int[26];
        for (String s : lines) {
            String[] words = s.split(" ");
            int[] firstLetterCount = getLetterCount(words[0]);
            int[] secondLetterCount = getLetterCount(words[1]);

            for (int i = 0; i < 26; i++) {
                letterCounts[i] += firstLetterCount[i] > secondLetterCount[i] ? firstLetterCount[i]
                        : secondLetterCount[i];
            }
        }

        PrintWriter out = new PrintWriter("blocks.out");
        for (int i : letterCounts) {
            out.println(i);
        }
        in.close();
        out.close();
    }

    public static int[] getLetterCount(String word) {
        int[] counts = new int[26];
        for (char c : word.toCharArray()) {
            counts[c - 'a']++;
        }

        return counts;
    }
}