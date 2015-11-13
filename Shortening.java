import java.util.*;

// Shortening in the Real World
// Score: 55.00
public class Shortening {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String baseurl = scan.nextLine();
        byte[] baseurlEncode = baseurl.getBytes();
        int numurls = scan.nextInt();
        scan.nextLine();
        for(int t = 0; t < numurls; t++) {
            String targeturl = scan.nextLine();
            byte[] targeturlEncode = targeturl.getBytes();
            byte[] longbaseurlEncode = new byte[targeturlEncode.length];
            for(int i = 0; i < targeturlEncode.length; i++) {
                longbaseurlEncode[i] = baseurlEncode[i % baseurlEncode.length];
            }

            byte[] xor = xor(targeturlEncode, longbaseurlEncode);
            byte[] last8 = last8(xor);
            long val = toLong(last8);
            System.out.println(baseurl + "/" + toBase62(val));
        }
    }

    public static String toBase62(long i) {
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String rval = "";

        do {
            rval = String.format("%s%s", alphabet.charAt((int)(i % 62)), rval);
            i /= 62;
        } while(i > 0);
        return rval;
    }

    private static long toLong(byte[] b) {
        long rval = 0;
        for (int i = 0; i < b.length; i++)
        {
            rval += ((long) b[i] & 0xffL) << (8 * i);
        }
        return rval;
    }

    private static byte[] last8(byte[] b) {
        byte[] rval = new byte[8];
        for(int i = 0; i < 8; i++) {
            rval[i] = b[b.length-i-1];
        }
        return rval;
    }

    public static byte[] xor(byte[] a, byte[] b) {
        byte[] rval = new byte[a.length];
        for(int i = 0; i < a.length; i++) {
            rval[i] = (byte) (a[i] ^ b[i]);
        }
        return rval;
    }
}