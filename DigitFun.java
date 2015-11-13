import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Digit Fun!
// Score: 49.00
public class DigitFun {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);  
        String s = new String();
        long n = 0;
        
        while(scan.hasNextLine()){
            
            int count = 1;
            
            
            //Scan the next line and parse the integer from it.
            s = scan.nextLine();
            
            if(s.equals("END")) break;
            
            n = s.length() < 4 ? Long.valueOf(s) : 0;

            while (s.length() != n){
                count++; 
                n = s.length();
                s = Long.toString(n);
                
            }
            
            //print out the counter variable
            System.out.println(count);
        }
    }
}