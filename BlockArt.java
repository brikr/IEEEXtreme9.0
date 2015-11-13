import java.util.*;

// Block Art
// Score: 38.29
public class BlockArt
{
    public static Long[][] art;

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        String[] tokens;
        tokens = line.split(" ");

        art = new Long[Integer.valueOf(tokens[0])][Integer.valueOf(tokens[1])];
        for (int i = 0; i < art.length; i++ )
        {
            for (int j = 0; j < art[0].length; j++)
            {
                art[i][j] = 0l;
            }
        }

        long numActions = Long.valueOf(scan.nextLine());

        while (numActions-- > 0)
        {
            line = scan.nextLine();
            String action = line.substring(0, 1);
            if (action.equals("a"))
            {
                add(line);
            }
            else if (action.equals("r"))
            {
                remove(line);
            }
            else
            {
                query(line);
            }
        }
    }

    public static void add (String str)
    {
        String[] tokens = str.split(" ");
        for (int i = Integer.valueOf(tokens[1]) - 1; i < Integer.valueOf(tokens[3]); i++)
        {
            for (int j = Integer.valueOf(tokens[2]) - 1; j < Integer.valueOf(tokens[4]); j++)
            {
                art[i][j] = art[i][j] + 1;
            }
        }
    }

    public static void remove (String str)
    {
        String[] tokens = str.split(" ");
        for (int i = Integer.valueOf(tokens[1]) - 1; i < Integer.valueOf(tokens[3]); i++)
        {
            for (int j = Integer.valueOf(tokens[2]) - 1; j < Integer.valueOf(tokens[4]); j++)
            {
                art[i][j] = art[i][j] == 0 ? 0 : art[i][j] - 1;
            }
        }
    }

    public static void query(String str)
    {
        String[] tokens = str.split(" ");
        long count = 0;
        for (int i = Integer.valueOf(tokens[1]) - 1; i < Integer.valueOf(tokens[3]); i++)
        {
            for (int j = Integer.valueOf(tokens[2]) - 1; j < Integer.valueOf(tokens[4]); j++)
            {
                count += art[i][j];
            }
        }

        System.out.println(count);
    }

}
