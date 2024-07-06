package bronze.finished.betaHasNot;

import java.util.*;
import java.io.*;

public class FarmerJohnHasNoLargeBrownCow {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("nocow.in")));
        String[] nk = in.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]) - 1;

        // get all adjective lines
        ArrayList<String[]> adjectiveLines = new ArrayList<String[]>();
        for (int i = 0; i < n; i++) {
            String[] line = in.readLine().split(" ");
            String[] adjectives = new String[line.length - 5];
            for (int j = 4; j < line.length - 1; j++) {
                adjectives[j - 4] = line[j];
            }
            adjectiveLines.add(adjectives);
        }
        int numAdjectives = adjectiveLines.get(0).length;

        // create base system for adjective options and array of number of options
        ArrayList<HashSet<String>> adjectiveOptionSets = new ArrayList<HashSet<String>>();
        int[] numOptions = new int[numAdjectives];
        for (int i = 0; i < numAdjectives; i++) {
            HashSet<String> adjectiveOptionSet = new HashSet<String>();
            for (int j = 0; j < adjectiveLines.size(); j++) {
                adjectiveOptionSet.add(adjectiveLines.get(j)[i]);
            }
            adjectiveOptionSets.add(adjectiveOptionSet);

            numOptions[i] = adjectiveOptionSet.size();
        }
        ArrayList<ArrayList<String>> adjectiveOptionArrays = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < numAdjectives; i++) {
            ArrayList<String> adjectiveOptionArray = new ArrayList<String>();
            for (String s : adjectiveOptionSets.get(i)) {
                adjectiveOptionArray.add(s);
            }
            Collections.sort(adjectiveOptionArray);
            adjectiveOptionArrays.add(adjectiveOptionArray);
        }
        ArrayList<HashMap<Integer, String>> adjectiveBaseSystems = new ArrayList<HashMap<Integer, String>>();
        for (int i = 0; i < numAdjectives; i++) {
            ArrayList<String> adjectiveOptionArray = adjectiveOptionArrays.get(i);

            HashMap<Integer, String> adjectiveBaseSystem = new HashMap<Integer, String>();
            for (int j = 0; j < adjectiveOptionArray.size(); j++) {
                adjectiveBaseSystem.put(j, adjectiveOptionArray.get(j));
            }

            adjectiveBaseSystems.add(adjectiveBaseSystem);
        }

        // turn k into an int[]
        int[] multipliers = new int[numAdjectives];
        int multiplier = 1;
        for (int i = numAdjectives - 1; i >= 0; i--) {
            multipliers[i] = multiplier;
            multiplier *= numOptions[i];
        }
        int[] kArray = new int[numAdjectives];
        int kLeft = k;
        for (int i = 0; i < numAdjectives; i++) {
            kArray[i] = (int) Math.floor(kLeft / multipliers[i]);
            kLeft -= ((int) Math.floor(kLeft / multipliers[i])) * multipliers[i];
        }

        // get all of the int[]s that represent what cows john doesn't have
        ArrayList<int[]> unownedCows = new ArrayList<int[]>();
        for (String[] a : adjectiveLines) {
            int[] unownedCow = getCowFromStrings(a, numAdjectives, adjectiveBaseSystems);
            unownedCows.add(unownedCow);
        }

        // figure out how many cows he's missing are less than k and add to k
        for (int[] unownedCow : unownedCows) {
            if (getCowBaseTen(unownedCow, multipliers) <= getCowBaseTen(kArray, multipliers)) {
                kArray[numAdjectives - 1] += 1;
                for (int i = numAdjectives - 1; i >= 0; i--) {
                    if (kArray[i] >= numOptions[i]) {
                        int timesGoesInto = (int) Math.floor(kArray[i] / numOptions[i]);
                        kArray[i] -= numOptions[i] * timesGoesInto;

                        if (timesGoesInto > 0) {
                            kArray[i - 1] += timesGoesInto;
                        }
                    }
                }
            }
        }

        // output
        String kString = "";
        for (int i = 0; i < numAdjectives; i++) {
            kString += adjectiveBaseSystems.get(i).get(kArray[i]) + " ";
        }
        PrintWriter out = new PrintWriter("nocow.out");
        out.println(kString.substring(0, kString.length() - 1));
        in.close();
        out.close();
    }

    public static int[] getCowFromStrings(String[] line, int numAdjectives,
            ArrayList<HashMap<Integer, String>> adjectiveBaseSystems) {
        int[] cow = new int[numAdjectives];
        // loop through each adjective
        for (int i = 0; i < line.length; i++) {
            String adjective = line[i];
            HashMap<Integer, String> adjectiveBaseSystem = adjectiveBaseSystems.get(i);
            // find what number the adjective corresponds to
            for (int j = 0; j < adjectiveBaseSystem.size(); j++) {
                if (adjectiveBaseSystem.get(j).equals(adjective)) {
                    cow[i] = j;
                }
            }
        }
        return cow;
    }

    public static int getCowBaseTen(int[] cowArray, int[] multipliers) {
        int cow = 0;
        for (int i = 0; i < cowArray.length; i++) {
            cow += cowArray[i] * multipliers[i];
        }
        return cow;
    }
}