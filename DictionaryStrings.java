import java.io.*;
import java.util.*;

// Dictionary Strings
// Score: 16.71
public class DictionaryStrings {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); //faster reading

        int numTestcases = Integer.valueOf(reader.readLine()); //number of test cases between 1 and 100.

        while (numTestcases-- > 0)
        {
            String[] tokens = reader.readLine().split(" "); //number of words in the dictionary
            int numDictionaryWords = Integer.valueOf(tokens[0]);
            int numCheckwords = Integer.valueOf(tokens[1]);

            //initialize dictionary words to an array to fuck with
            String[] dictionary = new String[numDictionaryWords];

            for(int i = 0; i < numDictionaryWords; i++){ //populate the dictionary with the strings given.
                dictionary[i] = reader.readLine();
            }

            String[] words = new String[numCheckwords];

            for (int i = 0; i < numCheckwords; i++){
                words[i] = reader.readLine();
            }

            for (String word : words)
            {
                String og = word;
//                int missing = 0;
                int[] missing = new int[26]; // maxneeded
                int[] freq = new int[word.length()];
                Arrays.fill(freq, 0);
                for (String dictWord : dictionary)
                {
                    // check dictionary words
                    match(dictWord, word, freq, missing, og);
                }
                int totalMissing = 0;
                for(int i = 0; i < 26; i++) {
                    totalMissing += missing[i];
                }
                if (totalMissing != 0) {
                    System.out.println("No " + totalMissing);
                } else {
                    System.out.print("Yes ");
                    if (isPerfect(freq))
                        System.out.print("Yes\n");
                    else
                        System.out.print("No\n");
                }
            }
        }
    }

    static void match(String dictword, String word, int[] freq, int[] missing, String og)
    {
        for (int i = 0; i < dictword.length(); i++)
        {
            char c = dictword.charAt(i);
            int index = word.indexOf(c);
            if (index == -1)
            {
                int countNeeded = dictword.length() - dictword.replace("" + c, "").length();
                countNeeded -= og.length() - og.replace("" + c, "").length();
                if(countNeeded > missing[c - 'a']) missing[c - 'a'] = countNeeded;
            }
            else
            {
                word = word.substring(0, index) + "_" + word.substring(index + 1, word.length());
                freq[index]++;
//                System.out.println(index + "\t" + dictword + "\t" + word);
            }

        }
//        System.out.println(Arrays.toString(freq));
    }

    static boolean isPerfect(int[] freq)
    {
        for (int i : freq)
            if (i == 0)
                return false;
        return true;
    }
}