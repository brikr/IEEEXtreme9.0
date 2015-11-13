import java.util.*;

// Prediction Games
// Score: 0.44
public class PredictionGames {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numCases = scan.nextInt();
        for(int t = 0; t < numCases; t++) {
            // Read input
            int numPlayers = scan.nextInt();
            Person[] players = new Person[numPlayers];
            int numCompetitions = scan.nextInt();
            for(int p = 0; p < numPlayers; p++) {
                String name = scan.next();
                players[p] = new Person(name, numCompetitions);
                for(int c = 0; c < numCompetitions; c++) {
                    int score1 = scan.nextInt();
                    int score2 = scan.nextInt();
                    players[p].guesses[c][0] = score1;
                    players[p].guesses[c][1] = score2;
                }
            }
            int[][] competitions = new int[numCompetitions][2];
            for(int c = 0; c < numCompetitions; c++) {
                String score1 = scan.next();
                String score2 = scan.next();
                competitions[c][0] = (score1.equals("?")) ? -1 : Integer.parseInt(score1);
                competitions[c][1] = (score2.equals("?")) ? -1 : Integer.parseInt(score2);
            }

            // Calculate current scores
            int[] scores = calculateScores(players, competitions);

            HashSet<String> winnersHash = new HashSet<String>();

            // Get current winners
            int highest = maxValue(scores);
            for(int i = 0; i < scores.length; i++) {
                if(scores[i] == highest) {
                    winnersHash.add(players[i].name);
                }
            }

            // Simulate potential winners
            for(int c = 0; c < numCompetitions; c++) {
                if(competitions[c][0] > -1) continue;

                // Simulate if each player got dead on
                for(int p = 0; p < numPlayers; p++) {
                    // Array deep copy for simulation
                    int[][] sim = new int[numCompetitions][2];
                    for(int i = 0; i < competitions.length; i++) {
                        for(int j = 0; j < competitions[i].length; j++) {
                            sim[i][j] = competitions[i][j];
                        }
                    }

                    sim[c][0] = players[p].guesses[c][0];
                    sim[c][1] = players[p].guesses[c][1];
                    int simScores[] = calculateScores(players, sim);
                    highest = maxValue(simScores);
                    for(int i = 0; i < simScores.length; i++) {
                        if(simScores[i] == highest) {
                            winnersHash.add(players[i].name);
                        }
                    }
                }
            }

            List<String> winnersList = new ArrayList<String>();
            winnersList.addAll(winnersHash);
            Collections.sort(winnersList);
            String output = "";
            for(String name : winnersList) {
                output += name + " ";
            }
            output = output.substring(0, output.length() - 1);
            System.out.println(output);
        }
    }

    private static int[] calculateScores(Person[] players, int[][] competitions) {
        int[] scores = new int[players.length];
        for(int p = 0; p < players.length; p++) {
            for(int c = 0; c < competitions.length; c++) {
                int s1 = competitions[c][0];
                int s2 = competitions[c][1];
                if(s1 == -1) continue;
                int p1 = players[p].guesses[c][0];
                int p2 = players[p].guesses[c][1];

                // Winner
                if((s1 > s2 && p1 > p2) || (s2 > s1 && p2 > p1)) scores[p] += 10;
                // Team Scores
                scores[p] += Math.max(0, 5 - Math.abs(s1 - p1));
                scores[p] += Math.max(0, 5 - Math.abs(s2 - p2));
                // Point Spread
                scores[p] += Math.max(0, 5 - Math.abs((p1 - p2) - (s1 - s2)));
            }
        }
        return scores;
    }

    private static int maxIndex(int[] arr) {
        int max = arr[0];
        int maxIndex = 0;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int maxValue(int[] arr) {
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}

class Person {
    public String name;
    public int[][] guesses;

    public Person(String name, int numCompetitions) {
        this.guesses = new int[numCompetitions][2];
        this.name = name;
    }
}