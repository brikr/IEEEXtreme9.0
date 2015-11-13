import java.util.*;

// Taco Stand
// Score: 25.67
public class TacoStand {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numCases = scan.nextInt();
        while(numCases-- != 0) {
            long s = scan.nextLong();
            long m = scan.nextLong();
            long r = scan.nextLong();
            long b = scan.nextLong();

            List<Long> l = new ArrayList<>();
            l.add(m);
            l.add(r);
            l.add(b);
            Collections.sort(l);
            long total = Math.min(l.get(2), l.get(0) + l.get(1));

            System.out.print(Math.min(s, total));
            if(numCases > 0) System.out.println();
        }
    }
}