import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Zoom In
// Score: 48.39
public class ZoomIn {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();
        Hashtable<Character, String> big = new Hashtable<Character, String>();
        for(int i = 0; i < k; i++) {
            char c = scan.next().charAt(0);
            String bigC = "";
            scan.nextLine();
            for(int j = 0; j < m; j++) {
                bigC += String.format("%1$-" + n + "s", scan.nextLine()) + "\n";
            }
            bigC = bigC.substring(0, bigC.length() - 1); // trim last newline
            big.put(c, bigC);
        }

        int x = scan.nextInt();
        scan.nextLine();
        while(x-- != 0) {
            String input = scan.nextLine();
            for(int r = 0; r < m; r++) {
                String line = "";
                for(int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    String bigC = big.get(c);
                    line += bigC.split("\n")[r];
                }
                System.out.println(line);
            }
        }
    }
}