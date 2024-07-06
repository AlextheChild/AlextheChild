package silver.bad;

import java.util.*;
import java.io.*;

public class TestTubes {
    static Stack<Integer>[] containers;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(in.readLine());

        while (t-- > 0) {
            String[] np = in.readLine().split(" ");
            // int n = Integer.parseInt(np[0]);
            int p = Integer.parseInt(np[1]);

            String[] tube1 = in.readLine().split("");
            String[] tube2 = in.readLine().split("");
            containers = new Stack[3];
            for (int i = 0; i < 3; i++) {
                containers[i] = new Stack<Integer>();
            }
            for (String str : tube1) {
                containers[0].add(Integer.parseInt(str));
            }
            for (String str : tube2) {
                containers[1].add(Integer.parseInt(str));
            }
            containers[0] = simplify(containers[0]);
            containers[1] = simplify(containers[1]);

            // make sure they start with different liquids
            if (containers[0].get(0) == containers[1].get(0)) {
                containers[0].add(0, containers[0].get(0) ^ 3);
            }

            int numMoves = containers[0].size() + containers[1].size() - 2;
            numMoves += numMoves > 1 ? 1 : 0;
            System.out.println(numMoves);
            if (p == 1) {
                continue;
            }

            // if last characters equal, pour one into the other
            if (containers[0].peek() == containers[1].peek()) {
                if (containers[0].size() > containers[1].size()) {
                    move(0, 1);
                } else {
                    move(1, 0);
                }
            }

            for (int i = 0; i < 2; i++) {
                if (containers[i].size() > 1) {
                    move(i, 2); // pour tube into beaker

                    // empty one
                    int emptyIndex = 0;
                    if (containers[emptyIndex].get(0) == containers[2].get(0)) {
                        emptyIndex ^= 1;
                    }
                    while (containers[emptyIndex].size() > 1) {
                        if (containers[emptyIndex].peek().equals(containers[2].get(0))) {
                            move(emptyIndex, 2); // move to beaker
                        } else {
                            move(emptyIndex, emptyIndex ^ 1); // move to other tube
                        }
                    }
                    // empty the other
                    emptyIndex ^= 1;
                    while (containers[emptyIndex].size() > 1) {
                        if (containers[emptyIndex].peek().equals(containers[2].get(0))) {
                            move(emptyIndex, 2); // move to beaker
                        } else {
                            move(emptyIndex, emptyIndex ^ 1); // move to other tube
                        }
                    }

                    // pour back from beaker
                    if (containers[2].get(0) == containers[0].get(0)) {
                        move(2, 0);
                    } else {
                        move(2, 1);
                    }

                    break;
                }
            }
        }

        in.close();
    }

    public static void move(int source, int destination) {
        if (containers[destination].size() == 0 || containers[destination].peek() != containers[source].peek()) {
            containers[destination].add(containers[source].peek());
        }
        containers[source].pop();

        System.out.println((source + 1) + " " + (destination + 1));
    }

    public static Stack<Integer> simplify(Stack<Integer> container) {
        Stack<Integer> simple = new Stack<Integer>();

        simple.add(container.remove(0));
        while (!container.isEmpty()) {
            if (container.get(0) != simple.get(simple.size() - 1)) {
                simple.add(container.remove(0));
            } else {
                container.remove(0);
            }
        }

        return simple;
    }
}