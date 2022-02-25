
import java.util.Scanner;

public class Human extends Entity {
    public int health = 30; 
    public int strength = 4; 
    public int armor = 0;
    public Items[] equipped = new Items[2]; // 0 is a weapon, 1 is armor
    public int[] cords = {12, 15}; // middle of the map

    public Human(int inventorySize) {
        super(inventorySize);
        this.inventory[0] = new Items("armor", "Cloth Robe");
        this.equipped[1] = this.inventory[0];
    }

    public void equipItem(Items item, int index, Scanner input) {
        if (this.equipped[index] == null) {
            this.equipped[index] = item;
            System.out.println("You have equipped: " + this.equipped[index].toString());
            if (index == 0) {
                this.strength += this.equipped[index].modifier;
            } else {
                this.armor += this.equipped[index].modifier;
            }
        } else {
            System.out.println(
                    "You currently have: " +
                            this.equipped[index].toString() +
                            " equipped. Would you like to un-equip this item? (N or any key to continue)");
            String continueEquip = input.next().toUpperCase();
            if (continueEquip.equals("N") || continueEquip.equals("NO")) {
                System.out.println("Did not equip " + item.name);
            } else {
                if (index == 0) {
                    this.strength -= this.equipped[index].modifier;
                } else {
                    this.armor -= this.equipped[index].modifier;
                }
                this.equipped[index] = item;
                System.out.println("You have equipped: " + this.equipped[index].toString());
                if (index == 0) {
                    this.strength += this.equipped[index].modifier;
                } else {
                    this.armor += this.equipped[index].modifier;
                }
            }
        }
    }

    public void postBattle() {
        if (this.health + 5 > 30) this.health = 30;
        else this.health += 5;
    }

    public void manageInventory(Scanner input) {
        if (this.checkInventory()) {
            while (true) {
                this.getInventory(this.equipped);
                System.out.println("Would you like to remove an item or equip an item? (R, E or Q to quit");
                String action = input.next().toUpperCase();
                if (action.equals("E")) {
                    System.out.println("Which item (weapon or armor piece) would you like to equip? (index #)");
                    try {
                        int index = input.nextInt();
                        if (this.inventory[index].type.equals("weapon")) {
                            equipItem(this.inventory[index], 0, input);
                        } else if (this.inventory[index].type.equals("armor")) {
                            equipItem(this.inventory[index], 1, input);
                        } else if (this.inventory[index] == null) {
                            System.out.println("This slot is empty!");
                        } else {
                            System.out.println("Invalid input!");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input must be a number!");
                        input.next();
                    }
                } else if (action.equals("R")) {
                    System.out.println("Which item would you like to remove? (Index #)");
                    try {
                        int toRemove = input.nextInt();
                        if (this.inventory[toRemove] != null) {
                            System.out.println(this.inventory[toRemove].name + " was deleted from your inventory!");
                            this.removeFromInventory(toRemove);   
                        } else {
                            System.out.println("Invalid input given or item not found!");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input must be a number!");
                        input.next();
                    }
                } else if (action.equals("Q") || action.equals("QUIT")) {
                    break;
                } else {
                    System.out.println("Invalid input must be: (E, R, Q or quit");
                }
            }
        } else {
            System.out.println("Your inventory is empty, their is nothing to manage!");
        }
    }

    public Items useConsumable(Scanner input) { // for combat
        if (this.getConsumables()) {
            while (true) {
                System.out.println("Pick which consumable you would like to use. (The index #)");
                try {
                    int index = input.nextInt();
                    if (this.inventory[index] != null) {
                        Items item = this.inventory[index];
                        this.removeFromInventory(index);
                        return item;
                    } else if (index > 20) {
                        return null;
                    } else {
                        System.out.println(
                                "Item not found / index invalid, please try again or enter a # greater than 20 to skip");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input must be a number!");
                    input.next();
                }
            }
        } 
        return null;
    }

    public boolean hasConsumable() {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null && this.inventory[i].type.equals("consumable")) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Your Stats: strength = " + this.strength +
                ", health = " + this.health + ", armor = " + this.armor;
    }
}