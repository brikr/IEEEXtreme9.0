import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

// Alice and Bob Play Sheldon's Favorite Game
// Score: 28.10
public class SheldonsFavoriteGame
{
    static Move[] moves = new Move[5];

    public static void main (String args[])
    {
        Scanner scan = new Scanner(System.in);
        BigInteger testCases = new BigInteger(scan.nextLine());

        moves[0] = new Move("Rock", new String[]{"Scissors", "Lizard"}, new String[]{"Paper", "Spock"});
        moves[1] = new Move("Scissors", new String[]{"Lizard", "Paper"}, new String[]{"Spock", "Rock"});
        moves[2] = new Move("Lizard", new String[]{"Paper", "Spock"}, new String[]{"Rock", "Scissors"});
        moves[3] = new Move("Paper", new String[]{"Spock", "Rock"}, new String[]{"Scissors", "Lizard"});
        moves[4] = new Move("Spock", new String[]{"Rock", "Scissors"}, new String[]{"Lizard", "Paper"});

        // while greater than 0
        while (testCases.compareTo(BigInteger.ZERO) > 0)
        {
            testCases = testCases.subtract(BigInteger.ONE); // decrement one
            Move alice = getMove(scan.next());
            Move bob = getMove(scan.next());
            BigInteger numRounds = new BigInteger(scan.next());
            int aliceWins = 0, bobWins = 0, ties = 0;

            while (numRounds.compareTo(BigInteger.ZERO) > 0)
            {
                numRounds = numRounds.subtract(BigInteger.ONE);
                int outcome = alice.beats(bob);

                if (outcome == 0) // tied game
                {
                    ties++;
                    alice = getMove(alice.beatsMe[0]);
                    bob = bob.name.equals("Spock") ? getMove("Lizard") : getMove("Spock");
                }
                else if (outcome == 1) // alice wins
                {
                    aliceWins++;
                    bob = bob.name.equals("Spock") ? getMove("Paper") : getMove("Spock");
                }
                else    // bob wins
                {
                    bobWins++;
                    alice = getMove(bob.beatsMe[0]);
                    bob = bob.name.equals("Spock") ? getMove("Rock") : getMove("Spock");
                }
            }
            if (aliceWins == bobWins)
            {
                System.out.println("Alice and Bob tie, each winning " + aliceWins + " game(s) and tying " + ties + " game(s)");
            }
            else if (aliceWins > bobWins)
            {
                System.out.println("Alice wins, by winning " + aliceWins + " game(s) and tying " + ties + " game(s)");
            }
            else
            {
                System.out.println("Bob wins, by winning " + bobWins + " game(s) and tying " + ties + " game(s)");
            }
        }
    }

    static Move getMove(String name)
    {
        for (Move m : moves)
        {
            if (m.name.equals(name))
                return m;
        }
        return null;
    }

    static class Move {
        String name;
        String[] beats;
        String[] beatsMe;
        public Move(String name, String[] beats, String[] beatsMe)
        {
            this.name = name;
            this.beats = beats;
            this.beatsMe = beatsMe;
        }

        public int beats(Move m)
        {
            if (name.equals(m.name))
                return 0;
            else if (Arrays.asList(beats).contains(m.name))
                return 1;
            else
                return -1;
        }
    }
}
