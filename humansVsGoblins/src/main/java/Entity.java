
public class Entity {
    public Items[] inventory;

    public Entity(int inventorySize) { // Constructor
        inventory = new Items[inventorySize];
    }

    public void getInventory() {
        for (Items i : this.inventory) {
            if (!i.equals(null)) {
                System.out.println(i.toString());
            }
        }
    }

    public void addToInventory(int index, Items item) {
       this.inventory[index] = item; 
    }

    public void addRandomItems(int howMany, boolean randomizeNum, String typesAllowed) {
        //Add random item to inventory (chests, and goblins);
        int quantity = howMany;
        if (randomizeNum) quantity = (int) Math.random() * howMany;
        for (int i = 0; i < quantity; i++) {
            if (typesAllowed.equals("all")) {
                addToInventory(i, new Items("all"));
            } else if (typesAllowed.equals("weapons")) {

            } else if (typesAllowed.equals("armor")) {

            } else if (typesAllowed.equals("consumables")) {

            }
        }
    }
}