import java.util.ArrayList;

public class Goblin extends Entity {
    public int health = (int) Math.floor((Math.random() * 10) + 10); // health 15-20
    public int strength = (int) Math.floor((Math.random() * 5) + 3); // Strength 3-7
    public int armor = 0;
    public int[] cords;
    public Items[] equipped = new Items[2];

    public Goblin(int inventorySize, int[] cords) {
        super(inventorySize);
        this.addRandomItems(2, true, "all");
        this.cords = cords;
        equipItems();
    }

    public void equipItems() {
        if (this.inventory[0] != null) {
            if (this.inventory[0].type.equals("weapon") || this.inventory[0].type.equals("armor")) {
                equipped[0] = this.inventory[0];
                if (this.inventory[0].type.equals("weapon")) {
                    this.strength = this.strength + this.equipped[0].modifier;
                } else {
                    this.armor = this.armor + this.equipped[0].modifier;
                }
            }
        }
        if (this.inventory[1] != null) {
            if (this.inventory[1].type.equals("weapon") || this.inventory[1].type.equals("armor")) {
                if (this.equipped[0] == null || !this.equipped[0].type.equals(this.inventory[1].type)) { 
                    // gob can not have 2 of the same type of item equipped
                    this.equipped[1] = this.inventory[1];
                    if (this.inventory[1].type.equals("weapon")) {
                        this.strength = this.strength + this.equipped[1].modifier;
                    } else {
                        this.armor = this.armor + this.equipped[1].modifier;
                    }
                }
            }
        }
    }

    public String trackPlayer(Human player) {
        int y = this.cords[0];
        int x = this.cords[1];
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        
        while (true) { // checks to see if a player is within 4 gird points, if it is then move a given direction towards the player
            int selector = (int) Math.floor(Math.random() * 4); // adds randomness to the direction the goblin chooses
            if (!indexes.contains(selector)) {
                if (selector == 0) {
                    if (player.cords[0] <= y + 4 && player.cords[0] > y &&
                       (player.cords[1] >= x - 4 && player.cords[1] <= x + 4)) {
                        return "S";
                    }
                    indexes.add(0);
                } else if (selector == 1) {
                    if (player.cords[0] >= y - 4 && player.cords[0] < y && 
                       (player.cords[1] >= x - 4 && player.cords[1] <= x + 4)) {
                        return "N";
                    }
                    indexes.add(1);
                } else if (selector == 2) {
                    if (player.cords[1] <= x + 4 && player.cords[1] > x && 
                       (player.cords[0] >= y - 4 && player.cords[0] <= y + 4)) {
                        return "E";
                    }
                    indexes.add(2);
                } else {
                    if (player.cords[1] >= x - 4 && player.cords[1] < x && 
                       (player.cords[0] >= y - 4 && player.cords[0] <= y + 4)) {
                        return "W";
                    }
                    indexes.add(3);
                }
            } else if (indexes.size() == 4) {
                return null;
            }
        }
    }
}