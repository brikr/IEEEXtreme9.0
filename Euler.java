import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// That's So Characteristically Euler!
// Score: 67.53
public class Euler {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numTests = scan.nextInt();
        scan.nextLine();
        for(int t = 0; t < numTests; t++) {
            int height = scan.nextInt();
            scan.nextLine();
            int width = scan.nextInt();
            scan.nextLine();
            char[][] pixels = new char[width][height]; // x, y, tl is 0
            for(int r = 0; r < height; r++) {
                char[] row = scan.nextLine().toCharArray();
                for(int c = 0; c < width; c++) {
                    pixels[c][r] = row[c];
                }
            }

            int connectedRegions = connectedRegions(pixels);
            int holes = holes(pixels);
//            System.out.println(connectedRegions);
//            System.out.println(holes);
            System.out.println(connectedRegions - holes);
        }
    }

    private static int holes(char[][] pixels) {
        char[][] p = new char[pixels.length][pixels[0].length];
        // deep copy with labeling edges
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                if((i == 0 || i == pixels.length - 1 || j == 0 || j == pixels[i].length - 1) && pixels[i][j] == 'O') {
                    p[i][j] = 'T';
                } else {
                    p[i][j] = pixels[i][j];
                }
            }
        }

        // spread T's to 'taint' edges. don't need to count so this is quick
        int T;
        int nextT = 0;
        do {
            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < p[i].length; j++) {
                    if (i > 0 && p[i][j] == 'O' && p[i - 1][j] == 'T') {
                        p[i][j] = 'T';
                    } else if (j > 0 && p[i][j] == 'O'  && p[i][j - 1] == 'T') {
                        p[i][j] = 'T';
                    } else if (i < p.length - 1 && p[i][j] == 'O'  && p[i + 1][j] == 'T') {
                        p[i][j] = 'T';
                    } else if (j < p[i].length - 1 && p[i][j] == 'O'  && p[i][j + 1] == 'T') {
                        p[i][j] = 'T';
                    }
                }
            }

            T = nextT;
            nextT = countT(p);
        } while (T != nextT);
        
        // find first O
        boolean found;
        int count = 0;
        do {
            found = false;
            findO:
            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[0].length; j++) {
                    if (p[i][j] == 'O') {
                        p[i][j] = 'o';
                        found = true;
                        break findO;
                    }
                }
            }
            if(!found) break;

            int O;
            int nextO = 0;
            do {
                for (int i = 0; i < p.length; i++) {
                    for (int j = 0; j < p[i].length; j++) {
                        if (i > 0 && p[i][j] == 'O' && p[i - 1][j] == 'o') {
                            p[i][j] = 'o';
                        } else if (j > 0 && p[i][j] == 'O' && p[i][j - 1] == 'o') {
                            p[i][j] = 'o';
                        } else if (i < p.length - 1 && p[i][j] == 'O' && p[i + 1][j] == 'o') {
                            p[i][j] = 'o';
                        } else if (j < p[i].length - 1 && p[i][j] == 'O' && p[i][j + 1] == 'o') {
                            p[i][j] = 'o';
                        }
                    }
                }

                O = nextO;
                nextO = countO(p);
            } while (O != nextO);
            count++;
//            for(int i = 0; i < p.length; i++) {
//                for(int j = 0; j < p[i].length; j++) {
//                    System.out.print(p[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println("\n");
        } while(found);

        return count;
    }

    private static int connectedRegions(char[][] pixels) {
        char[][] p = new char[pixels.length][pixels[0].length];
        // deep copy
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                p[i][j] = pixels[i][j];
            }
        }

        // find first X
        boolean found;
        int count = 0;
        do {
            found = false;
            findX:
            for (int i = 0; i < pixels.length; i++) {
                for (int j = 0; j < pixels[0].length; j++) {
                    if (p[i][j] == 'X') {
                        p[i][j] = 'x';
                        found = true;
                        break findX;
                    }
                }
            }
            if(!found) break;

            int X;
            int nextX = 0;
            do {
                for (int i = 0; i < p.length; i++) {
                    for (int j = 0; j < p[i].length; j++) {
                        if (i > 0 && j > 0 && p[i][j] == 'X' && p[i - 1][j - 1] == 'x') {
                            p[i][j] = 'x';
                        } else if (i > 0 && p[i][j] == 'X' && p[i - 1][j] == 'x'){
                            p[i][j] = 'x';
                        } else if (j > 0 && p[i][j] == 'X' && p[i][j - 1] == 'x') {
                            p[i][j] = 'x';
                        } else if (i > 0 && j < p[i].length - 1 && p[i][j] == 'X' && p[i - 1][j + 1] == 'x') {
                            p[i][j] = 'x';
                        } else if (i < p.length - 1 && j > 0 && p[i][j] == 'X' && p[i + 1][j - 1] == 'x') {
                            p[i][j] = 'x';
                        } else if (i < p.length - 1 && p[i][j] == 'X' && p[i + 1][j] == 'x') {
                            p[i][j] = 'x';
                        } else if (j < p[i].length - 1 && p[i][j] == 'X' && p[i][j + 1] == 'x') {
                            p[i][j] = 'x';
                        } else if (i < p.length - 1 && j < p[i].length - 1 && p[i][j] == 'X' && p[i + 1][j + 1] == 'x') {
                            p[i][j] = 'x';
                        }
                    }
                }

                X = nextX;
                nextX = countX(p);
            } while (X != nextX);
            count++;
        } while(found);

        return count;
    }

    private static int countT(char[][] pixels) {
        int rval = 0;
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                if(pixels[i][j] == 'T') rval++;
            }
        }
        return rval;
    }

    static int countO(char[][] pixels) {
        int rval = 0;
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                if(pixels[i][j] == 'O') rval++;
            }
        }
        return rval;
    }

    static int countX(char[][] pixels) {
        int rval = 0;
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[0].length; j++) {
                if(pixels[i][j] == 'X') rval++;
            }
        }
        return rval;
    }
}