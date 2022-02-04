import java.util.HashMap;

public class Human extends Entity {
    public int health = 30; 
    public int strength = 3; 
    public int armor = 0;
    public HashMap<Integer, Integer> currentPos;
    public Items[] equipped = new Items[2];
    public int[] cords = {12, 15}; // middle of the map

    public Human(int inventorySize) {
        super(inventorySize);
        this.inventory[0] = new Items("armor", "Cloth Robe");
        this.equipped[1] = this.inventory[0];
    }

    public void equipItem(int inventorySlot) {

    }

    public void postBattle() {
        if (this.health + 5 > 30) this.health = 30;
        else this.health += 5;
    }
}