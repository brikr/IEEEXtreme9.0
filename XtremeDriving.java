import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Xtreme Driving
// Score: 22.50
public class XtremeDriving {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();
        String[] nums;
        nums = line.split(" ");

        int highwayLength = Integer.valueOf(nums[0]);
        int numCows = Integer.valueOf(nums[1]);

        ArrayList<Point> cows = new ArrayList<>();

        // read in cow shit
        while (numCows-- > 0) {
            line = scan.nextLine();
            nums = line.split(" ");
            cows.add(new Point(Integer.valueOf(nums[1]) - 1, Integer.valueOf(nums[0]) - 1));
        }

        Stack<Point> st = new Stack<>();
        st.push(new Point(0,0));
        int possibleSolutions = 0;


        while (!st.empty()) {
            Boolean hitCow = false;
            Point pos = st.pop();
            // hit a cow
            for (Point cow : cows) {
                if (cow.x == pos.x && cow.y == pos.y) {
                    hitCow = true;
                }
            }

            // solved
            if (!hitCow && pos.x == highwayLength - 1 && pos.y == 0)
                possibleSolutions++;

            // add cases
            if (!hitCow && pos.x < highwayLength - 1) {
                // up right
                if (pos.y > 0)
                    st.push(new Point(pos.x + 1, pos.y - 1));
                // middle right
                st.push(new Point(pos.x + 1, pos.y));
                // bottom right
                if (pos.y < 3)
                    st.push(new Point(pos.x + 1, pos.y + 1));
            }
        }

        System.out.println(possibleSolutions);
    }
}