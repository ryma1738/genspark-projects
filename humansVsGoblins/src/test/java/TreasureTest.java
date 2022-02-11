
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class TreasureTest {
    private static ArrayList<String> allItems = new ArrayList<>();
    private static ArrayList<Treasure> allTreasures = new ArrayList<>();
    private static final String[] allItemsNames = new String[] {
        "Steal Sword", "Bronze Dagger", "Wooden Club", "Gold Plated Sword", "Iron Sword", "Stick", "Axe",
        "Work Hammer", "Goblin Bomb", "Health Potion", "Great Health Potion", "Goblin Missile", "Cloth Robe",
        "Peasants Cloths", "Goblin Leather", "Padded Leather Suit", "Chain Mail Armor Piece", "Full Chain Mail Armor" 
    };

    @Before
    public void setUp() {
        allItems = new ArrayList<>();
        allTreasures = new ArrayList<>();
        for (int i = 0; i < allItemsNames.length; i++) allItems.add(allItemsNames[i]);
        for (int i = 0; i < 10000; i++) {
            int[] cords = new int[] {
                    (int) Math.floor(Math.random() * 21) + 2,
                    (int) Math.floor(Math.random() * 30) + 1
            };
            Treasure test = new Treasure(cords);
            allTreasures.add(test);
        }
    }

    @Test
    public void toStringTest() {
        for (int i = 0; i < allTreasures.size(); i++) {
            if (allTreasures.get(i).checkInventory()) {
                String contains = "";
                contains = "This chest contains: ";
                for (int j = 0; j < allTreasures.get(i).inventory.length; j++) {
                    if (allTreasures.get(i).inventory[j] != null)
                        contains += allTreasures.get(i).inventory[j].name;
                    if (j == allTreasures.get(i).inventory.length - 1)
                        contains += ".";
                    else if (j == allTreasures.get(i).inventory.length - 1 && allTreasures.get(i).inventory[j] != null)
                        contains += ", ";
                }
                assertEquals("Treasure Chest: " + contains, allTreasures.get(i).toString());
            } else {
                assertEquals("Treasure Chest: This chest is empty.", allTreasures.get(i).toString());
            }
        }
    }

    @After
    public void cleanUp() {
        allItems.removeAll(allItems);
    }
}
