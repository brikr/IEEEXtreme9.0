import java.math.BigInteger;
import java.util.*;

// Mr. Pippo's Pizza
// Score: 72.00
public class MrPipposPizza {

    static BigInteger two = new BigInteger("2");

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext())
        {
            BigInteger next = new BigInteger(scan.next());
            BigInteger i = new BigInteger("0"), cat = new BigInteger("-1");
            while (next.compareTo(cat) != 0)
            {
                i = i.add(BigInteger.ONE);
                cat = findCatalan(i);
            }
            System.out.println(i.add(two)); // for # of sides
        }
    }

    static BigInteger findCatalan(BigInteger n)
    {
        return factorial(n.multiply(two)).divide(factorial(n.add(BigInteger.ONE)).multiply(factorial(n)));
    }

    static BigInteger factorial(BigInteger n)
    {
        BigInteger result = BigInteger.ONE;
        BigInteger mult = BigInteger.ONE;
        while(!(mult.compareTo(n) > 0))
        {
            result = result.multiply(mult);
            mult = mult.add(BigInteger.ONE);
        }
        return result;
    }
}
