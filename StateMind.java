import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// IEEE State of Mind
// Score: 44.44
public class StateMind
{
    static String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    public static void main (String args [])
    {
        Scanner scan = new Scanner(System.in);

        int numStates = scan.nextInt();
        int numInputs = scan.nextInt();
        State[] states = new State[numStates];
        String[] output = new String[numInputs];
        Arrays.fill(output, "");
        String out = "";
        String state = "";
        for (int i = 0; i < numStates; i++)
        {
            int retVal = scan.nextInt();
            int numRules = scan.nextInt();
            states[i] = new State(retVal, i, numInputs);
            for (int n = 0; n < numRules; n++)
            {
                String rule = scan.next();
                states[i].addRule(rule);
            }
        }
        int numTimesteps = scan.nextInt();
        int currentState = scan.nextInt();
        scan.nextLine();
        while (scan.hasNextLine())
        {
            String[] inputs = scan.nextLine().split(" ");
            // record inputs
            for (int i = 0; i < inputs.length; i++)
            {
                if (inputs[i].equals("0"))
                    output[i] += "___";
                else
                    output[i] += "***";
            }

            // change states
            currentState = states[currentState].stateFromRule(inputs, currentState);

            // record state changes
            if (states[currentState].retVal == 1)
            {
                out += "***";
            }
            else
            {
                out += "___";
            }
            state += "  " + currentState;
        }
        int tickCount = 0;
        while (out.length() > 0)
        {
            int endString = out.length() > 47 ? 47 : out.length() - 1;
            System.out.println("Tick #" + (tickCount+1));
            tickCount += 16;

            // Print the inputs as they're read
            for (int i = 0; i < numInputs; i++)
            {
                System.out.println(alphabet[i] + "     " + output[i].substring(0, endString + 1));
                output[i] = output[i].substring(endString + 1);
            }

            System.out.println("OUT   " + out.substring(0, endString + 1));
            System.out.println("STATE " + state.substring(0, endString + 1));
            out = out.substring(endString + 1);
            state = state.substring(endString + 1);
            System.out.println();
        }
    }

    static class State
    {
        ArrayList<Rule> rules;
        int retVal, numInputs, index;
        public State(int retVal, int index, int numInputs)
        {
            this.retVal = retVal;
            this.index = index;
            this.numInputs = numInputs;
            rules = new ArrayList<>();
        }

        public void addRule(String rule)
        {
            String[] split = rule.split("/");
            rules.add(new Rule(Integer.valueOf(split[1]), split[0], numInputs));
        }

        public int stateFromRule(String[] inputs, int currentState)
        {
            for (Rule rule : rules)
            {
                boolean good = true;
                for (int i = 0; i < rule.inputs.length; i++)
                {
                    if (rule.inputs[i] != -1 && rule.inputs[i] != Integer.valueOf(inputs[i]))
                        good = false;
                }
                if (good)
                    return rule.toState;
            }
            return currentState;
        }
    }

    static class Rule
    {
        int toState;
        int[] inputs;
        public Rule(int toState, String rule, int numInputs)
        {
            inputs = new int[numInputs];
            Arrays.fill(inputs, -1);
            this.toState = toState;
            String[] split = rule.split(",");
            for (int i = 0; i < split.length; i++)
            {
                String[] input = split[i].split("=");
                int charIndex = java.util.Arrays.binarySearch(alphabet, input[0]);
                inputs[charIndex] = Integer.valueOf(input[1]);
            }
        }
    }
}
