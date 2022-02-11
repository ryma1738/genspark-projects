
public class Treasure extends Entity {
    public int[] cords;
    public Treasure(int[] cords) {
        super(5);
        this.cords = cords;
        this.addRandomItems(5, true, "all");
    }

    @Override
    public String toString() {
        String contains = "";
        if (this.checkInventory()) {
            contains = "This chest contains: ";
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] != null) contains += this.inventory[i].name;
                if (i == this.inventory.length - 1) contains += ".";
                else if (i == this.inventory.length - 1 && this.inventory[i] != null) contains += ", ";
            }   
        } else contains = "This chest is empty.";
        
        return "Treasure Chest: " + contains;
    }
}