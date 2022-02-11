
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class EntityTest {
    private Human player;
    private Items item;
    private Items consumable;
    private Treasure chest;

    @Before
    public void setUp() {
        player = new Human(21);
        item = new Items("weapon");
        consumable = new Items("consumable");
        while (true) {
            chest = new Treasure(new int[]{15, 10});
            if (chest.inventory.length > 2) {
                break;
            }
        }
    }

    @Test
    public void checkAddRemove() {
        assertFalse(player.getConsumables());
        assertTrue(player.checkInventory());
        assertTrue(player.removeFromInventory(0));
        assertFalse(player.checkInventory());
        assertTrue(player.addToInventory(item));
        assertTrue(player.checkInventory());
        assertTrue(player.addToInventory(consumable));
        assertTrue(player.getConsumables());
    }

    @Test
    public void moveEntity() {
        assertTrue(player.moveEntity("N", player.cords)[0] == 11);
        assertTrue(player.moveEntity("S", player.cords)[0] == 13);
        assertTrue(player.moveEntity("E", player.cords)[1] == 16);
        assertTrue(player.moveEntity("W", player.cords)[1] == 14);
    }

    @Test
    public void lootSingleItem() {
        assertTrue(chest.lootSingleItem(0).getClass() == Items.class);
    }

    @Test
    public void lootAllItems() {
        Items[] allItems = chest.lootAllItems();
        for (Items item : allItems) {
            if (item != null)
            assertTrue(item.getClass() == Items.class);
        }
    }
}
