import java.util.ArrayList;
import java.util.HashMap;

public class Land {
    private final String[] topAndBottomArray = { "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
            "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-" };
    public HashMap<Integer, String[]> map = new HashMap<>(); // 23 by 32 - play area = 2-22 up and down, and 1 - 30 left to right 12 is the middle
    public Short goblinsRemaining = 0;

    public Land() { // constructor
        int i = 1;
        while (i <= 23) { // initialize base map;
            if (i == 1 || i == 23) {
                this.map.put(i, topAndBottomArray);
            } else
                this.map.put(i, new String[] { "|", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*",
                "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "|" });
            i++;
        }
        //Only change row 12 to have H in the middle
        String[] temp = this.map.get(12);
        temp[15] = "H";
        this.map.put(12, temp);
    }

    public ArrayList<int[]> addGoblins(int totalNum) {
        ArrayList<int[]> allGoblins = new ArrayList<int[]>();
        for (int i = 0; i < totalNum; i++) {
            int upAndDown = (int) Math.floor(Math.random() * 21) + 2;
            int sideToSide = (int) Math.floor(Math.random() * 30) + 1;
            String[] temp = this.map.get(upAndDown);
            if (temp[sideToSide] != "*") {
                i--;
            } else {
                temp[sideToSide] = "G";
                this.map.put(upAndDown, temp);
                allGoblins.add(new int[] { upAndDown, sideToSide });
            }
        }
        return allGoblins;        
    }

    public void addTressure(int totalNum) {

    }

    @Override
    public String toString() { // returns the whole map
        String stringToPrint = "";
        for (int i = 1; i <= 23; i++) {
            String tempLine = "";
            for (int j = 0; j < this.map.get(i).length; j++) {
                tempLine += this.map.get(i)[j] + " ";
            }
            stringToPrint += tempLine + "\n";
        }
        return stringToPrint;
    }

    public boolean withinRange(int[] cords) {

        return false;
    }
}