import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Threesome Poker
// Score: 80.00
public class ThreesomePoker {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Queue<Game> games = new LinkedList<>();
        games.offer(new Game(scan.nextInt(), scan.nextInt(), scan.nextInt())); // add initial game
        while(!games.isEmpty()) {
            Game g = games.poll();

            if(g.player1.size() == 12) {
                System.out.println("Ok");
                break;
            }
            if(g.over()) {
                System.out.print(g);
                break;
            }

            int p1 = g.player1.get(g.player1.size()-1);
            int p2 = g.player2.get(g.player2.size()-1);
            int p3 = g.player3.get(g.player3.size()-1);

            // 1 vs 2
            Game g12 = new Game(g);
            if(p1 <= p2) { // p1 will win
                g12.add(p1 + p1, p2 - p1, p3);
            } else { // p2 will win
                g12.add(p1 - p2, p2 + p2, p3);
            }

            // 1 vs 3
            Game g13 = new Game(g);
            if(p1 <= p3) { // p1 will win
                g13.add(p1 + p1, p2, p3 - p1);
            } else { // p3 will win
                g13.add(p1 - p3, p2, p3 + p3);
            }

            // 2 vs 3
            Game g23 = new Game(g);
            if(p2 <= p3) { // p2 will win
                g23.add(p1, p2 + p2, p3 - p2);
            } else { // p3 will win
                g23.add(p1, p2 - p3, p3 + p3);
            }

            // offer new possibilities to queue
            games.offer(g12);
            games.offer(g13);
            games.offer(g23);
        }

    }
}

class Game {
    public ArrayList<Integer> player1, player2, player3;
    public Game(int player1, int player2, int player3) {
        this.player1 = new ArrayList<>();
        this.player2 = new ArrayList<>();
        this.player3 = new ArrayList<>();
        this.player1.add(player1);
        this.player2.add(player2);
        this.player3.add(player3);
    }

    public Game(Game g) {
        this.player1 = new ArrayList<>();
        this.player2 = new ArrayList<>();
        this.player3 = new ArrayList<>();
        this.player1.addAll(g.player1);
        this.player2.addAll(g.player2);
        this.player3.addAll(g.player3);
    }

    public void add(int p1, int p2, int p3) {
        this.player1.add(p1);
        this.player2.add(p2);
        this.player3.add(p3);
    }

    public boolean over() {
        return this.player1.get(this.player1.size()-1) == 0 ||
               this.player2.get(this.player2.size()-1) == 0 ||
               this.player3.get(this.player3.size()-1) == 0;
    }

    public String toString() {
        String rval = "";
        for(int i = 0; i < this.player1.size(); i++) {
            rval += this.player1.get(i) + " " + this.player2.get(i) + " " + this.player3.get(i) + "\n";
        }
        return rval;
    }
}