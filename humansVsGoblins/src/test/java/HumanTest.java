
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HumanTest {
    private Human player = new Human(21);

    @Test
    public void hasConsumable() {
        assertFalse(player.hasConsumable());
        player.addRandomItems(1, false, "consumables");
        assertTrue(player.hasConsumable()); 
    }
}
