import java.util.HashMap;

public class Land {
    private final String[] baseArray = {"|", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "|"};
    private final String[] topAndBottomArray = {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
    public HashMap<Integer, String[]> map = new HashMap<>();

    public Land() { //constructor
        int i = 1;
        while (i <= 12) { // initialize base map;
            if (i == 1 || i == 12) {
                this.map.put(i, topAndBottomArray);
            } else this.map.put(i, baseArray);
        }
    }

    public void addGoblins(Goblin gob) {

    }

    public void addTressure() {
        
    }

    public void printMap(int[] humansCords){

    }

    public boolean withinRange(int[] cords) {

        return false;
    }
}