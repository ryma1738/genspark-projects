import java.util.HashMap;

public class Human extends Entity {
    public int health = (int) Math.floor((Math.random() * 10) + 15); // health 15-25
    public int strength = (int) Math.floor((Math.random() * 5) + 3); // Strength 3-7
    public HashMap<Integer, Integer> currentPos;
    public Items[] equipped = new Items[2];
    
    public Human(int inventorySize) {
        super(inventorySize);
    }
}