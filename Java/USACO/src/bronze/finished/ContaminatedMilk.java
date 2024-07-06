package bronze.finished;

import java.util.*;
import java.io.*;

public class ContaminatedMilk {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("badmilk.in")));
        String[] nmds = in.readLine().split(" ");
        int n = Integer.parseInt(nmds[0]); // number of friends
        int d = Integer.parseInt(nmds[2]); // number of drinking lines
        int s = Integer.parseInt(nmds[3]); // number of sick lines

        Person[] people = new Person[n];
        for (int i = 0; i < n; i++) {
            people[i] = new Person();
        }
        for (int i = 0; i < d; i++) {
            String[] line = in.readLine().split(" ");
            int personIndex = Integer.parseInt(line[0]) - 1;
            int milk = Integer.parseInt(line[1]);
            int time = Integer.parseInt(line[2]);

            people[personIndex].timesList.add(new MilkTime(milk, time));
            people[personIndex].milksList.add(milk);
        }
        for (Person p : people) {
            p.sort();
        }

        // figure out what milks had been drunk commonly by all the sick people
        HashSet<Integer> commonMilks = new HashSet<Integer>();
        for (int i = 0; i < s; i++) {
            String[] line = in.readLine().split(" ");
            Person currentPerson = people[Integer.parseInt(line[0]) - 1];
            int timeSick = Integer.parseInt(line[1]);

            // update commonMilks based on what milks the current person has had
            HashSet<Integer> newCommonMilks = new HashSet<Integer>();
            for (MilkTime mt : currentPerson.timesList) {
                if (mt.time >= timeSick) {
                    break;
                }

                if (i == 0) {
                    commonMilks.add(mt.milk);
                } else {
                    if (commonMilks.contains(mt.milk)) {
                        newCommonMilks.add(mt.milk);
                    }
                }
            }
            if (i != 0) {
                commonMilks = newCommonMilks;
            }
        }

        // loop through milks and figure out which one had the most people drink it
        int mostDrunk = 0;
        for (int cm : commonMilks) {
            int numPeopleDrank = 0;
            for (Person p : people) {
                if (p.milksList.contains(cm)) {
                    numPeopleDrank++;
                }
            }

            mostDrunk = Math.max(numPeopleDrank, mostDrunk);
        }

        PrintWriter out = new PrintWriter("badmilk.out");
        out.println(mostDrunk);
        in.close();
        out.close();
    }

    static class Person {
        ArrayList<MilkTime> timesList = new ArrayList<MilkTime>();
        HashSet<Integer> milksList = new HashSet<Integer>();

        public void sort() {
            Collections.sort(timesList, (a, b) -> a.time < b.time ? -1 : (a.time == b.time ? 0 : 1));
        }
    }

    static class MilkTime {
        int milk, time;

        public MilkTime(int milk, int time) {
            this.milk = milk;
            this.time = time;
        }
    }
}