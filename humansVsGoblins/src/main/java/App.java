import java.util.ArrayList;

public class App {
    public static ArrayList<Goblin> gobs = new ArrayList<>();
    public static void main( String[] args ) {
        //game loop
        Land map = new Land();
        System.out.println(map.toString());
        Human player = new Human(21);
        player.getInventory(null);
        player.addRandomItems(2, true, "all");
        player.getInventory(player.equipped);
        ArrayList<int[]> gobsCords = map.addGoblins(10);
        for (int i =0; i < gobsCords.size(); i++) {
            gobs.add(new Goblin(2, gobsCords.get(i)));
        }
        if (gobs.get(1).checkInventory()) {
            gobs.get(1).getInventory(null);
        }
        
        
    }

    public void checkPlayerLocation() {
        //See if the player landed on a goblin or treasureChest
    }
}
