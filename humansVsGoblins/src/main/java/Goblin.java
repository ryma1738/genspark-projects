import java.util.HashMap;

public class Goblin extends Entity {
    public int health = (int) Math.floor((Math.random() * 10) + 15); // health 15-25
    public int strength = (int) Math.floor((Math.random() * 5) + 3); // Strength 3-7
    public HashMap<Integer, Integer> currentPos;
    public Items[] equipped = new Items[2];

    public Goblin(int inventorySize, HashMap<Integer, Integer> cords) {
        super(inventorySize);
        this.addRandomItems(2, true, "all");
        this.currentPos = cords;
    }

    public void equipItem() {
        
    }
}