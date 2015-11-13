import java.util.*;

// Light Gremlins
// Score: 43.46
public class Gremlins {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int testCases = Integer.parseInt(scan.nextLine());
        while (testCases-- > 0)
        {
            int switches = Integer.parseInt(scan.next());
            int numGremlins = Integer.parseInt(scan.next());
            boolean[] flipped = new boolean[switches];
            Arrays.fill(flipped, false);
            int count = 0;
            while (numGremlins-- > 0)
            {
                int prime = Integer.parseInt(scan.next());
                for (int i = prime - 1; i < switches; i += prime)
                {
                    count += flipped[i] ? -1 : 1;
                    flipped[i] = !flipped[i];
                }
            }
            System.out.println(count);
        }
    }
}