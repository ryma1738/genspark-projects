import java.util.ArrayList;

public class Entity { 
    public Items[] inventory;

    public Entity(int inventorySize) { // Constructor
        inventory = new Items[inventorySize];
    }

    public void getInventory(Items[] equipped) {
        if (this.getClass() == Human.class) System.out.println("Your Inventory: ");
        else if (this.getClass() == Goblin.class) System.out.println("Goblins Drops: ");
        else if (this.getClass() == Treasure.class) System.out.println("Treasures Loot: ");
        ArrayList<Items> equippedItems = new ArrayList<Items>();
        if (equipped != null) for (int i = 0; i < equipped.length; i++) equippedItems.add(equipped[i]);
        if (this.getClass() == Human.class) {
            for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) {
                if (equippedItems.contains(this.inventory[i])) System.out.println("(" + i + "): " + this.inventory[i].toString() + " (Equipped)");
                else System.out.println("(" + i + "): " + this.inventory[i].toString());
            } else System.out.println("(" + i + "): Empty");
            }
        } else {
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] != null) {
                    System.out.println("(" + i + "): " + this.inventory[i].toString());
                }
            }
        }
       
        System.out.println("\n");
    }

    public boolean checkInventory() {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null) return true;
        }
        return false;
    }

    public boolean addToInventory(Items item) {
        int i = 0;
        while(true) {
            if (i == this.inventory.length) {
                System.out.println("Your Inventory is full!" );
                return false;
            } else if (this.inventory[i] == null) {
                this.inventory[i] = item;
                return true;
            }
            i++;
        }
    }

    public boolean removeFromInventory(int index) {
        if (this.inventory[index] == null) {
            System.out.println("Their is nothing in that inventory slot!");
            return false;
        }
        if (this.getClass() == Human.class) 
            System.out.println(this.inventory[index].name + " was deleted from your inventory!");
        this.inventory[index] = null;
        return true;
    }

    public void addRandomItems(int howMany, boolean randomizeNum, String typesAllowed) {
        //Add random item to inventory (chests, and goblins);
        int quantity = howMany;
        if (randomizeNum) quantity = (int) Math.floor(Math.random() * (howMany + 1));
        for (int i = 0; i < quantity; i++) {
            if (typesAllowed.equals("all")) {
                addToInventory(new Items("all"));
            } else if (typesAllowed.equals("weapons")) {
                addToInventory(new Items("weapon"));
            } else if (typesAllowed.equals("armor")) {
                addToInventory(new Items("armor"));
            } else if (typesAllowed.equals("consumables")) {
                addToInventory(new Items("consumables"));
            }
        }
    }
}