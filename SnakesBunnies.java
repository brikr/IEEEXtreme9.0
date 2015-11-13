import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Snakes & Bunnies
// Score: 88.00
public class SnakesBunnies {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        char board[][] = new char[n][n];

        // fill initial 2d array
        for(int r = n-1; r >= 0; r--) {
            String line = scan.nextLine();
            for(int c = 0; c < n; c++) {
                board[r][c] = line.charAt(c);
            }
        }

        char path[] = new char[n*n];
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(r % 2 == 0) {
                    path[n * r + c] = board[r][c];
                } else {
                    path[n * r + c] = board[r][n - c - 1];
                }
            }
        }

        int numPlayers = scan.nextInt();
        int[] players = new int[numPlayers];
        Arrays.fill(players, -1);
        int dice1 = scan.nextInt();
        int dice2 = scan.nextInt();
        int currentPlayer = 0;
        boolean another = (dice1 == dice2);
        do {
            try {
                int dist = dice1 + dice2;
                players[currentPlayer] += dist;

                if(players[currentPlayer] >= n*n) {
                    players[currentPlayer] = n*n-1;
                    break; // game over
                }

                boolean done = false;
                int count = 0;
                do {
//                    int pos = players[currentPlayer];
                    count++;
                    if (path[players[currentPlayer]] != '-') {
                        if (path[players[currentPlayer]] >= 'a' && path[players[currentPlayer]] <= 'z') { // snake
                            players[currentPlayer] = snake(path, players[currentPlayer]);
                        } else { // bunny
                            players[currentPlayer] = bunny(path, players[currentPlayer]);
                        }
                    }

                    while (shouldBounce(players, currentPlayer)) {
                        players[currentPlayer]++;
                    }
                    if(count > 10000) { // hopefully no test cases involve doing this 1000 times without a cycle
                        System.out.println("PLAYER " + (currentPlayer+1) + " WINS BY EVIL CYCLE!");
                        System.exit(0);
                    }
                    done = ok(path, players, currentPlayer);
                } while (!done);
                if (another) {
                    another = false;
                    dice1 = scan.nextInt();
                    dice2 = 0;
                    continue;
                }
                dice1 = scan.nextInt();
                dice2 = scan.nextInt();
                another = (dice1 == dice2);
                currentPlayer++;
                if (currentPlayer == numPlayers) currentPlayer = 0;
            } catch (NoSuchElementException e ) {
                break;
            }
        } while(true);

        for(int i : players) {
            System.out.print((i+1) + " ");
        }
        System.out.println();
    }

    private static boolean ok(char[] path, int[] players, int currentPlayer) {
        if(snake(path, players[currentPlayer]) != players[currentPlayer]) {
            return false;
        }
        if(bunny(path, players[currentPlayer]) != players[currentPlayer]) {
            return false;
        }
        if(shouldBounce(players, currentPlayer)) {
            return false;
        }
        return true;
    }

    private static boolean shouldBounce(int[] players, int currentPlayer) {
        for(int i = 0; i < players.length; i++) {
            if(players[i] == players[currentPlayer] && i != currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static int snake(char[] path, int pos) {
        char c = path[pos];
        if(c < 'a' || c > 'z') return pos;
        int i = pos;
        while(i-- != 0) {
            if(path[i] == c) return i;
        }
        return pos;
    }

    private static int bunny(char[] path, int pos) {
        char c = path[pos];
        if(c < '0' || c > '9') return pos;
        int i = pos;
        while(++i < path.length) {
            if(path[i] == c) return i;
        }
        return pos;
    }
}