import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

// Xtreme In Security
// Score: 17.00
public class XtremeInSecurity {

    static String codes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    static String base64;
    static long start;

    public static void main(String[] args) {
        start = System.currentTimeMillis();
        Scanner scan = new Scanner(System.in);

        base64 = scan.nextLine();
        for (int l = 1; l <= 20; l++) {
            crack(l);
            if(System.currentTimeMillis() - start > 3750) {
                System.out.println("password");
                System.exit(0);
            }
        }
    }

    private static void crack(int len) {
        char[] guess = new char[len];
        Arrays.fill(guess, 'a');

        do {
            String encoded = base64Encode(toSHA("IEEE" + new String(guess) + "Xtreme"));
            if(encoded.equals(base64)) {
                System.out.println(new String(guess));
                System.exit(0);
            }
            int i = guess.length - 1;
            while (i >= 0) {
                guess[i]++;
                if (guess[i] == '9'+1) { // rollover digit
                    if (i > 0) {
                        guess[i] = 'a';
                    }
                    i--;
                } else if(guess[i] == 'z'+1) { // skip from z to 0
                   guess[i] = '0';
                } else {
                    break;
                }
            }
            if(System.currentTimeMillis() - start > 3800) return;
        } while (guess[0] != '9'+1); // last string
    }

    private static byte[] toSHA(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes("UTF-8"));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // wikipedia
    // proctor said algorithms from wikipedia are ok
    private static String base64Encode(byte[] in) {
        StringBuffer out = new StringBuffer((in.length * 4) / 3);
        int b;
        for (int i = 0; i < in.length; i += 3)  {
            b = (in[i] & 0xFC) >> 2;
            out.append(codes.charAt(b));
            b = (in[i] & 0x03) << 4;
            if (i + 1 < in.length)      {
                b |= (in[i + 1] & 0xF0) >> 4;
                out.append(codes.charAt(b));
                b = (in[i + 1] & 0x0F) << 2;
                if (i + 2 < in.length)  {
                    b |= (in[i + 2] & 0xC0) >> 6;
                    out.append(codes.charAt(b));
                    b = in[i + 2] & 0x3F;
                    out.append(codes.charAt(b));
                } else  {
                    out.append(codes.charAt(b));
                    out.append('=');
                }
            } else      {
                out.append(codes.charAt(b));
                out.append("==");
            }
        }

        return out.toString();
    }
}