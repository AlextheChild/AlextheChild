package bronze.finished;

import java.io.*;

public class WhereAmI {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("whereami.in")));
        in.readLine();
        String mailboxColors = in.readLine();

        int checkStringLength = 1;

        String currentString;
        boolean minLengthFound = false;
        // loop through different string lengths
        while (!minLengthFound) {
            boolean lengthBad = false;

            // loop through all different strings of that length
            for (int i = 0; i < mailboxColors.length() - (checkStringLength - 1); i++) {
                currentString = mailboxColors.substring(i, i + checkStringLength);
                int numTimesFound = 0;

                // check currentString for matches
                for (int q = 0; q < mailboxColors.length() - (checkStringLength - 1); q++) {

                    // if any multiples found, currentLengthBad = true
                    if (mailboxColors.substring(q, q + checkStringLength).equals(currentString)) {
                        numTimesFound++;

                        if (numTimesFound > 1) {
                            lengthBad = true;
                        }
                    }
                }
            }
            if (!lengthBad) {
                minLengthFound = true;
            }
            checkStringLength++;
        }

        PrintWriter out = new PrintWriter("whereami.out");
        out.println(checkStringLength - 1);
        in.close();
        out.close();
    }
}