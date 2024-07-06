package bronze.finished;

import java.io.*;

public class WordProcesser {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("word.in")));
        String[] nk = in.readLine().split(" ");
        int k = Integer.parseInt(nk[1]);
        String[] essay = in.readLine().split(" ");

        PrintWriter out = new PrintWriter("word.out");
        String line = "";
        int currentLineLength = 0;
        for (String s : essay) {
            if (s.length() + currentLineLength <= k) {
                line += s + " ";
                currentLineLength += s.length();
            } else {
                out.println(line.substring(0, line.length() - 1));
                line = s + " ";
                currentLineLength = s.length();
            }
        }
        out.println(line.substring(0, line.length() - 1));
        in.close();
        out.close();
    }
}