
public class Treasure extends Entity {
    public int[] cords;
    public Treasure(int[] cords) {
        super(5);
        this.cords = cords;
        this.addRandomItems(5, true, "all");
    }

    public Items lootSingleItem(int index) {
        Items loot = this.inventory[index];
        this.removeFromInventory(index);
        return loot;
    }

    public Items[] lootAllTreasure() {
        Items[] loot = this.inventory;
        for (int i = 0; i < loot.length; i++) {
            this.removeFromInventory(i);
        }
        return loot;
    }
}