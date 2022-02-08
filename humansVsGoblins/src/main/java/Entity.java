import java.util.ArrayList;
import java.util.Scanner;

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

    public int[] moveEntity(String direction, int[] currentLocation) { 
        // if the entity is unable to move in that direction it will return the original cords
        int[] cords = currentLocation;
        direction = direction.toUpperCase();
        if (direction.equals("N")) {
            if (cords[0] != 2) cords[0] = cords[0] - 1;
        } else if (direction.equals("S")) {
            if (cords[0] != 22) cords[0] = cords[0] + 1;
        } else if (direction.equals("E")) {
            if (cords[1] != 30) cords[1] = cords[1] + 1;
        } else if (direction.equals("W")) {
            if (cords[1] != 1) cords[1] = cords[1] - 1;
        }
        return cords; 
    }

    public void interactWith(Human player, Goblin gob, Scanner input) { //fight

    }
    
    public void interactWith(Human player, Treasure chest, Scanner input) { //loot chest
        System.out.println("You encountered a chest! Lets see what is inside!");
        if (!chest.checkInventory()) {
            System.out.println("How Unfortunate this chest is empty!");
            return;
        }
        while (true) {
            chest.getInventory(null);
            System.out.println("Enter an inventory slot # to loot single items, or any # greater than 5 to loot all Items");
            int indexOfLoot = input.nextInt();
            input.next();
            if (indexOfLoot >= 5) {
                Items[] loot = chest.lootAllTreasure();
                for (Items item : loot) {
                    if (item != null) {
                        player.addToInventory(item);
                    }
                }
            } else if (indexOfLoot < 5 && indexOfLoot >= 0) {
                if (chest.inventory[indexOfLoot] != null) {
                    player.addToInventory(chest.lootSingleItem(indexOfLoot));
                } else System.out.println("Their is nothing in that slot to loot!");
            } else System.out.println("Invalid input, please try again.");
            if (!chest.checkInventory()) {
                System.out.println("You have looted All items from this chest!");
                player.getInventory(player.equipped);
                break;
            }
            System.out.println("Would you like to loot more items? (N or any key to continue)");
            String continueLooting = input.next().toUpperCase();
            if (continueLooting.equals("N") || continueLooting.equals("NO")) {
                player.getInventory(player.equipped);
                break;
            }
        }
    }
}