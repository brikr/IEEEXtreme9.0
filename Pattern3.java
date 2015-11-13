import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Pattern 3
// Score: 30.73
public class Pattern3
{

    public static void main (String args[])
    {
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());
        while (testCases-- > 0)
        {
            String line = scan.nextLine(), sub = "";
            int subStart = 0, subEnd = 0;
            boolean match = false;
            while(!match && subEnd < line.length())
            {
                sub = line.substring(subStart, subEnd + 1); // end is not inclusive
                int len = sub.length(), end = subEnd + 1;

                match = true;
                while(end < line.length())
                {
                    if (end + len > line.length())
                        len = line.length() - end;
                    String next = line.substring(end, end + len);
                    String pattern = sub.substring(0, len);
                    if (!next.equals(pattern))
                    {
                        match = false;
                        break;
                    }
                    end += len;
                }

                subEnd++;
            }
            System.out.println(sub.length());
        }
    }
}
