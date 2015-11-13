import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// SAD: Long Way from Home
// Score: 0.49
public class SAD {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numTests = scan.nextInt();
        for(int t = 0; t < numTests; t++) {
            int n = scan.nextInt();

            Cave[] caves = new Cave[16];
            for(int i = 0; i < 16; i++) {
                caves[i] = new Cave();
            }
            int[] sads = new int[16];
            Arrays.fill(sads, -1);

            // get SAD locations
            for(int s = 0; s < n; s++) {
                int di = scan.nextInt() - 1;
                int cj = scan.nextInt() - 1;
                sads[di] = cj;
            }

            // get cave connections
            int l = scan.nextInt();
            for(int c = 0; c < l; c++) {
                int ci = scan.nextInt() - 1;
                int cj = scan.nextInt() - 1;
                caves[ci].addConnection(cj);
                caves[cj].addConnection(ci);
            }

            // IDFS
            search:
            for(int i = 1; i <= 30; i++) {
//                System.out.println("Checking depth " + i);
                Stack<State> stack = new Stack<>();
                stack.push(new State(caves, sads, null));
                while(!stack.isEmpty()) {
                    State next = stack.pop();
//                    System.out.println(next.getDepth());
                    if (next.isHappy()) {
//                        System.out.println(next);
                        System.out.println(next.getDepth());
                        break search;
                    }

                    Set<State> legal = next.getLegalMoves();
                    for(State s : legal) {
                        if(s.getDepth() <= i) {
                            stack.push(s);
                        }
                    }
                }
            }
        }
    }
}

class Cave {
    public Set<Integer> connections;

    public Cave() {
        connections = new HashSet<>();
    }

    public void addConnection(int c) {
        connections.add(c);
    }
}

class State {
    public State parent;
    public Cave[] caves;
    public int[] sads;

    public State(Cave[] caves, int[] sads, State parent) {
        this.parent = parent;
        this.caves = caves;
        this.sads = sads;
    }

    public Set<State> getLegalMoves() {
        Set<State> states = new HashSet<State>();
        for(int i = 0; i < sads.length; i++) {
            if(sads[i] == -1) continue;
            Set<Integer> connections = caves[sads[i]].connections;
            for(int c : connections) {
                int[] nSads = Arrays.copyOf(sads, sads.length);
//                if(Arrays.asList(nSads).contains(new Integer(c))) continue;
                boolean taken = false;
                for(int j = 0; j < nSads.length; j++) {
                    if(nSads[j] == c) taken = true;
                }
                if(!taken) {
                    nSads[i] = c;
                    states.add(new State(caves, nSads, this));
                }
            }
        }
        return states;
    }

    public String toString() {
        String rval = "";
        if(this.parent != null) rval += this.parent.toString();
        rval += Arrays.toString(this.sads) + "\n";
        return rval;
    }

    public int getDepth() {
        if(this.parent == null) return 0;
        return this.parent.getDepth() + 1;
    }

    public boolean isHappy() {
        for(int i = 0; i < sads.length; i++) {
            if(sads[i] != -1 && sads[i] != i) return false;
        }
        return true;
    }
}